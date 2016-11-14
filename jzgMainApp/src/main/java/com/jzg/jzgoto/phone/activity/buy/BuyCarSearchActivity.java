package com.jzg.jzgoto.phone.activity.buy;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.models.business.buy.BuyCarSearchSaveKeyWordsParams;
import com.jzg.jzgoto.phone.models.business.buy.BuyCarSearchSaveKeyWordsResult;
import com.jzg.jzgoto.phone.models.business.buy.RequestAutoSearchTextParams;
import com.jzg.jzgoto.phone.models.business.buy.SearchAutoComValueResult;
import com.jzg.jzgoto.phone.services.business.buy.BuyCarService;
import com.jzg.jzgoto.phone.services.common.OnRequestFinishListener;
import com.jzg.jzgoto.phone.tools.ClickControlTool;
import com.jzg.jzgoto.phone.tools.ShowDialogTool;
import com.jzg.jzgoto.phone.view.buy.BuyCarSearchHistoryView;
import com.jzg.jzgoto.phone.view.buy.BuyCarSearchListView;

/**
 * Created by WY on 2016/9/14.
 * Function :全网搜车
 */
public class BuyCarSearchActivity extends Activity implements OnRequestFinishListener{

    private RelativeLayout mViewContainer;
    private BuyCarSearchListView mSearchListView;
    private BuyCarSearchHistoryView mSearchHistoryView;
    private EditText mSearchEditText;
    private TextView mCancelText;
    private ImageView mClearEdit;
    public static final int TO_GET_KEYWORD = 2001;
    public static final String TO_GET_KEYWORD_STRING = "to_get_keyword_string";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buycar_search_layout);
        init();
    }

    @Override
    public void onRequestSuccess(Message msg) {
        ShowDialogTool.dismissLoadingDialog();
        switch (msg.what) {
            case R.id.getAutoComTextSuc:
                SearchAutoComValueResult value = (SearchAutoComValueResult) msg.obj;
                mSearchListView.setVisibility(View.VISIBLE);
                mSearchHistoryView.setVisibility(View.GONE);
                mSearchListView.toShowAutoText(value);
                break;
            case R.id.request_save_search_keywords:
                //保存关键词
                returnBuyCarActivity(mKeyWords);
                break;
            default:
                break;
        }
    }

    @Override
    public void onRequestFault(Message msg) {
        ShowDialogTool.dismissLoadingDialog();
        switch (msg.what){
            case R.id.request_save_search_keywords:
                //保存关键词
                returnBuyCarActivity(mKeyWords);
                break;
        }
    }

    private void toSaveKeyWords(String keyWords){
        ShowDialogTool.showLoadingDialog(this);
        BuyCarSearchSaveKeyWordsParams params = new BuyCarSearchSaveKeyWordsParams();
        params.Keyword = keyWords;
        new BuyCarService(this,this).toResuestSaveSearchHotWords(params, BuyCarSearchSaveKeyWordsResult.class,R.id.request_save_search_keywords);
    }
    private String mKeyWords = "";
    private void init(){
        mViewContainer = (RelativeLayout) findViewById(R.id.buycar_search_viewContainer);
        mSearchListView = new BuyCarSearchListView(BuyCarSearchActivity.this);
        mSearchListView.setFinishAndToBackCallback(new BuyCarSearchListView.FinishAndToBuyCarCallBack() {
            @Override
            public void finishToBuyCarActivity(String keyWord) {
                mKeyWords = keyWord;
                toSaveKeyWords(keyWord);
            }
        });
        mSearchHistoryView = new BuyCarSearchHistoryView(BuyCarSearchActivity.this);
        mSearchHistoryView.setFinishAndBackTagCallback(new BuyCarSearchHistoryView.FinishAndToBuyCarCallBackTag() {
            @Override
            public void finishTagToBuyCarActivity(String keyWord) {
                returnBuyCarActivity(keyWord);
            }
        });
        mViewContainer.addView(mSearchListView);
        mViewContainer.addView(mSearchHistoryView);
        mSearchListView.setVisibility(View.GONE);
        mSearchEditText = (EditText) findViewById(R.id.buycar_search_EditText);
        mCancelText = (TextView) findViewById(R.id.buycar_search_cancel);
        mClearEdit = (ImageView) findViewById(R.id.buycar_search_clearEdit);
        mCancelText.setOnClickListener(mClickListener);
        mClearEdit.setOnClickListener(mClickListener);
        mSearchEditText.addTextChangedListener(mTextWatcher);
        mSearchEditText.setOnKeyListener(mOnKeyListener);
    }

    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!ClickControlTool.isCanToClick())return;
            switch (v.getId()){
                case R.id.buycar_search_cancel:
                    //取消
                    returnBuyCarActivity("");
                    break;
                case R.id.buycar_search_clearEdit:
                    //删除搜索词
                    mSearchEditText.setText("");
                    break;
            }
        }
    };

    private void returnBuyCarActivity(String keyWords){
        mSearchHistoryView.addSearchWordToHistory(keyWords);
        Intent in = new Intent();
        in.putExtra(TO_GET_KEYWORD_STRING, keyWords);
        setResult(TO_GET_KEYWORD, in);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSearchHistoryView.saveHistoryWordsList();
    }

    private View.OnKeyListener mOnKeyListener = new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if(keyCode==KeyEvent.KEYCODE_ENTER){
                //修改回车键功能
                // 先隐藏键盘
                ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                        .hideSoftInputFromWindow(BuyCarSearchActivity.this.getCurrentFocus()
                                        .getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);
                mKeyWords = mSearchEditText.getText().toString().trim();
                toSaveKeyWords(mKeyWords);
            }
            return false;
        }
    };
    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String searchWord = mSearchEditText.getText().toString().trim();
            if(!TextUtils.isEmpty(searchWord)){
                mClearEdit.setVisibility(View.VISIBLE);
                RequestAutoSearchTextParams params = new RequestAutoSearchTextParams();
                params.searchValue = searchWord;
                ShowDialogTool.dismissLoadingDialog();
                new BuyCarService(BuyCarSearchActivity.this, BuyCarSearchActivity.this).toResuestSearchAutoComList(params, SearchAutoComValueResult.class, R.id.getAutoComTextSuc);
            //   请求单词补全
            }else{
                mClearEdit.setVisibility(View.GONE);
                mSearchListView.setVisibility(View.GONE);
                mSearchHistoryView.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
