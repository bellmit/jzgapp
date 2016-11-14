package com.jzg.jzgoto.phone.view.buy;

import android.app.Activity;
import android.content.Context;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.constant.Constant;
import com.jzg.jzgoto.phone.models.business.buy.BuyCarSearchHotWordsParams;
import com.jzg.jzgoto.phone.models.business.buy.BuyCarSearchHotWordsResult;
import com.jzg.jzgoto.phone.services.business.buy.BuyCarService;
import com.jzg.jzgoto.phone.services.common.OnRequestFinishListener;
import com.jzg.jzgoto.phone.tools.ClickControlTool;
import com.jzg.jzgoto.phone.tools.MessageUtils;
import com.jzg.jzgoto.phone.tools.ShowDialogTool;
import com.jzg.jzgoto.phone.tools.ValHistoryCacheUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WY on 2016/9/18.
 * Function :买车搜索-历史记录界面
 */
public class BuyCarSearchHistoryView extends LinearLayout {
    private Context mContext;
    public BuyCarSearchHistoryView(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public BuyCarSearchHistoryView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    public BuyCarSearchHistoryView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
        initView();
    }

    private TextView mHotWords,mHistoryWords;
    private TagFlowLayout mTagFlowLayout;

    private void initView(){
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_buycar_searchhistory_layout,null);
        mHotWords = (TextView) view.findViewById(R.id.view_buycar_search_hotWords);
        mHistoryWords = (TextView) view.findViewById(R.id.view_buycar_search_historyWords);
        mTagFlowLayout = (TagFlowLayout) view.findViewById(R.id.view_buycar_search_tagFlowLayout);
        mHotWords.setOnClickListener(mClickListener);
        mHistoryWords.setOnClickListener(mClickListener);
        WindowManager windowManager = ((Activity)getContext()).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        LayoutParams params = new LayoutParams(display.getWidth(), LayoutParams.MATCH_PARENT);
        view.setLayoutParams(params);
        this.setLayoutParams(params);
        changeBtnSelect(mHotWords,mHistoryWords);
        toRequestHotWords();
        this.addView(view);
    }

    private List<String> mHistoryList = new ArrayList<>();
    private List<String>mHotWordsList = new ArrayList<>();
    private OnRequestFinishListener mRequestListener = new OnRequestFinishListener() {
        @Override
        public void onRequestSuccess(Message msg) {
            ShowDialogTool.dismissLoadingDialog();
            switch (msg.what){
                case R.id.request_buy_car_compare:
                    BuyCarSearchHotWordsResult result = (BuyCarSearchHotWordsResult)msg.obj;
                    if(result.getStatus()== Constant.SUCCESS){
                        mHotWordsList.clear();
                        for(int i=0;i<result.getSearchHistoryList().size();i++){
                            mHotWordsList.add(result.getSearchHistoryList().get(i).getName());
                        }
                    }
                    changeBtnSelect(mHotWords,mHistoryWords);
                    showTagFlow(mHotWordsList);
                    isHotWords = true;
                    break;
            }
        }

        @Override
        public void onRequestFault(Message msg) {
            ShowDialogTool.dismissLoadingDialog();
            ShowDialogTool.showCenterToast(getContext(),getResources().getString(R.string.error_net));
        }
    };


    private void toRequestHotWords(){
        BuyCarSearchHotWordsParams params = new BuyCarSearchHotWordsParams();
        ShowDialogTool.showLoadingDialog(getContext());
        new BuyCarService(getContext(),mRequestListener).toResuestSearchHotWordsList(
                params,BuyCarSearchHotWordsResult.class,R.id.request_buy_car_compare );
    }
    private boolean isHotWords = true;
    private OnClickListener mClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!ClickControlTool.isCanToClick())return;
            switch (v.getId()){
                case R.id.view_buycar_search_hotWords:
                    if(mHotWordsList.size()==0){
                        toRequestHotWords();
                    }else{
                        isHotWords = true;
                        changeBtnSelect(mHotWords,mHistoryWords);
                        showTagFlow(mHotWordsList);
                    }
                    break;
                case R.id.view_buycar_search_historyWords:
                    if(mHistoryList.size()==0){
                        mHistoryList.addAll(getHistoryWordsFromCache());
                    }
                    changeBtnSelect(mHistoryWords,mHotWords);
                    showTagFlow(mHistoryList);
                    isHotWords = false;
                    break;
            }
        }
    };

    private List<String> getHistoryWordsFromCache(){
        return ValHistoryCacheUtils.getSearchHistoryList(getContext());
    }
    public void saveHistoryWordsList(){
        ValHistoryCacheUtils.toSaveSearchHistoryListCache(getContext(),mHistoryList);
    }
    public void addSearchWordToHistory(String keyWord){
        boolean isRepeat = false;//是否重复
        for(int i=0;i<mHistoryList.size();i++){
            if(keyWord.equals(mHistoryList.get(i))){
                isRepeat = true;
            }
        }
        if(!isRepeat){
            //不重复：添加到历史记录
            List<String> list = new ArrayList<>();
            list.clear();
            if(mHistoryList.size()>=10){
                mHistoryList.remove(mHistoryList.size()-1);
            }
            list.addAll(mHistoryList);
            mHistoryList.clear();
            mHistoryList.add(keyWord);
            mHistoryList.addAll(list);
            list.clear();
            list = null;
        }
    }
    private void showTagFlow(List<String> tagList){
        mTagFlowLayout.removeAllViews();
        MarginLayoutParams lp = new MarginLayoutParams(
                LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        lp.leftMargin = 5;
        lp.rightMargin = 10;
        lp.topMargin = 10;
        lp.bottomMargin = 5;
        for(int i = 0; i < tagList.size(); i ++){
            if(!TextUtils.isEmpty(tagList.get(i))){
                TextView textView = new TextView(mContext);
                textView.setText(tagList.get(i));
                textView.setTextSize(14f);
                textView.setBackgroundResource(R.drawable.buycar_filter_tag_bg);
                textView.setTextColor(mContext.getResources()
                        .getColor(R.color.text_item_lightgrey));
                textView.setOnClickListener(new TagClickListener(i).tagClickListener);
                mTagFlowLayout.addView(textView,lp);
                textView = null;
            }
        }
    }
    class TagClickListener{
        private int index;

        public TagClickListener(int index) {
            super();
            this.index = index;
        }

        private OnClickListener tagClickListener = new OnClickListener() {

            @Override
            public void onClick(View v) {
                if(isHotWords){
                    String keyWord = mHotWordsList.get(index);
                    mFinishCallBack.finishTagToBuyCarActivity(keyWord);
                }else{
                    String keyWord = mHistoryList.get(index);
                    mFinishCallBack.finishTagToBuyCarActivity(keyWord);
                }
            }
        };
    }
    private void changeBtnSelect(TextView blueBtn,TextView whiteBtn){
        if(blueBtn!=null){
            blueBtn.setSelected(true);
            blueBtn.setTextColor(getResources().getColor(R.color.white));
        }
        if(whiteBtn!=null){
            whiteBtn.setSelected(false);
            whiteBtn.setTextColor(getResources().getColor(R.color.text_blue));
        }
    }
    private FinishAndToBuyCarCallBackTag mFinishCallBack;

    public void setFinishAndBackTagCallback(FinishAndToBuyCarCallBackTag callback) {
        mFinishCallBack = callback;
    }
    public interface FinishAndToBuyCarCallBackTag {
        public void finishTagToBuyCarActivity(String keyWord);
    }
}
