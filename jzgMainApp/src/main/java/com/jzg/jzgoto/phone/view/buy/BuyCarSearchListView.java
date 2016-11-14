package com.jzg.jzgoto.phone.view.buy;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.models.business.buy.BuyCarFilterIndexModel;
import com.jzg.jzgoto.phone.models.business.buy.SearchAutoComValueResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jzg on 2016/9/18.
 * Function :
 */
public class BuyCarSearchListView extends LinearLayout{
    private Context mContext;
    public BuyCarSearchListView(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public BuyCarSearchListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    public BuyCarSearchListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
        initView();
    }

    private ListView mListView;
    private void initView(){
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_buycar_searchlist_layout,null);
        mListView = (ListView)view.findViewById(R.id.view_buycar_search_listView);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mRequestCallBack.finishToBuyCarActivity(mAutoComTxtList.get(position));
            }
        });
        this.addView(view);
    }
    private List<String> mAutoComTxtList = new ArrayList<>();
    private BuyCarSearchAdapter mAdapter;
    public void toShowAutoText(SearchAutoComValueResult value){
        mAutoComTxtList.clear();
        mAutoComTxtList.addAll(value.getReturnValue());
        if(mAutoComTxtList.size()>0){
            mListView.setVisibility(View.VISIBLE);
        }
        if(mAdapter==null){
            mAdapter = new BuyCarSearchAdapter(getContext(), mAutoComTxtList);
            mListView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        }else{
            mAdapter.setSearchType(mAutoComTxtList);
            mAdapter.notifyDataSetChanged();
        }
    }
    private FinishAndToBuyCarCallBack mRequestCallBack;

    public void setFinishAndToBackCallback(FinishAndToBuyCarCallBack callback) {
        mRequestCallBack = callback;
    }
    public interface FinishAndToBuyCarCallBack {
        public void finishToBuyCarActivity(String keyWord);
    }
    public class BuyCarSearchAdapter extends BaseAdapter {

        private Context mContext;
        private List<String> textList;

        public BuyCarSearchAdapter(Context mContext, List<String> textList) {
            super();
            this.mContext = mContext;
            this.textList = textList;
        }

        public void setSearchType(List<String> mList) {
            this.textList = mList;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return textList.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return textList.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if(convertView == null){
                holder = new ViewHolder();
                LinearLayout linear = new LinearLayout(mContext);
                linear.setOrientation(LinearLayout.HORIZONTAL);
                linear.setLayoutParams(new AbsListView.LayoutParams(
                        AbsListView.LayoutParams.MATCH_PARENT,
                        AbsListView.LayoutParams.WRAP_CONTENT));
                linear.setGravity(Gravity.CENTER_VERTICAL);
                linear.setPadding(20, 20, 5, 20);
                TextView tv = new TextView(mContext);
                tv.setTextSize(16f);
                tv.setText("");
                tv.setTextColor(mContext.getResources()
                        .getColor(R.color.text_item_lightgrey));
                holder.textView = tv;
                linear.addView(tv);
                convertView = linear;
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder) convertView.getTag();
            }
            String str = textList.get(position);
            holder.textView.setText(str);
            return convertView;
        }

        class ViewHolder{
            TextView textView;
        }
    }
}
