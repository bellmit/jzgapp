package com.jzg.jzgoto.phone.view.valuation;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.adapter.valuation.ValuationHistoryAdapter;
import com.jzg.jzgoto.phone.models.business.valuation.sell.RequestValFragmentHistory;
import com.jzg.jzgoto.phone.tools.ListViewUtils;
import com.jzg.jzgoto.phone.tools.ValHistoryCacheUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WY on 2016/9/19.
 * Function :估值界面——历史估值记录
 */
public class ValuationHistoryView extends LinearLayout{

    public ValuationHistoryView(Context context) {
        super(context);
        initView();
    }
    public ValuationHistoryView(Context context, AttributeSet attrs) {
        super(context,attrs);
        initView();
    }
    public ValuationHistoryView(Context context, AttributeSet attrs,
                                int defStyleAttr) {
        super(context,attrs,defStyleAttr);
        initView();
    }

    private ListView mListView;
    private TextView mTextShow;
    @SuppressWarnings("deprecation")
    private void initView(){
        View view = LayoutInflater.from(getContext()).inflate( R.layout.view_valuation_history_layout, null);
        mListView = (ListView) view.findViewById(R.id.view_valuation_history_ListView);
        mTextShow = (TextView) view.findViewById(R.id.view_valuation_history_textView);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //请求
                mRequestCallBack.toRequestValResult(mCacheList.get(position));
            }
        });
        WindowManager windowManager = ((Activity)getContext()).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        int screenWidth = display.getWidth();
        LayoutParams params = new LayoutParams(screenWidth, LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);
        this.addView(view);
    }
    private ValuationHistoryAdapter mAdapter;
    public void toShowListView(List<RequestValFragmentHistory> list){
        //显示请求列表,没有登录状态下，请求列表为空时
        if(list==null){
            list = ValHistoryCacheUtils.getValHistoryList(getContext(),"");
            mCacheList.clear();
            mCacheList.addAll(list);
        }else{
            //登录状态，请求列表不为空时
            mCacheList.clear();
            mCacheList.addAll(list);
            toSaveHistoryList();
        }
        if(list.size()==0){
            mTextShow.setVisibility(View.VISIBLE);
        }else{
            mTextShow.setVisibility(View.VISIBLE);
        }
        this.setVisibility(View.VISIBLE);
        if(mAdapter==null){
            mAdapter = new ValuationHistoryAdapter(getContext(),list);
            mListView.setAdapter(mAdapter);
            ListViewUtils.setListViewHeight(mListView);
        }else{
            mAdapter.setmList(list);
            mAdapter.notifyDataSetChanged();
            ListViewUtils.setListViewHeight(mListView);
        }
    }
    private List<RequestValFragmentHistory> mCacheList = new ArrayList<>();
    public void toAddNewHistoryModel(RequestValFragmentHistory model){
        //添加
        List<RequestValFragmentHistory> list = new ArrayList<>();
        list.add(model);
        if(mCacheList.size()==10){
            mCacheList.remove(9);
        }
        list.addAll(mCacheList);
        mCacheList.clear();
        mCacheList.addAll(list);
        list = null;
        this.setVisibility(View.VISIBLE);
        if(mAdapter==null){
            mAdapter = new ValuationHistoryAdapter(getContext(),mCacheList);
            mListView.setAdapter(mAdapter);
            ListViewUtils.setListViewHeight(mListView);
        }else{
            mAdapter.setmList(mCacheList);
            mAdapter.notifyDataSetChanged();
            ListViewUtils.setListViewHeight(mListView);
        }
        toSaveHistoryList();
    }
    public void toSaveHistoryList(){
        //保存
        ValHistoryCacheUtils.toSaveHistoryListCache(getContext(), mCacheList);
    }
    private RequestValCallback mRequestCallBack;

    public void setRequestValCallback(RequestValCallback callback) {
        mRequestCallBack = callback;
    }
    public interface RequestValCallback {
        /**
         * @param historyModel
         */
        public void toRequestValResult(RequestValFragmentHistory historyModel);
    }
}
