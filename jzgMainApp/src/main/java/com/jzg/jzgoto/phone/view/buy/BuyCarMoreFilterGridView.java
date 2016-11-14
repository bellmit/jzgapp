package com.jzg.jzgoto.phone.view.buy;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jzg.jzgoto.phone.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WY on 2016/9/14.
 * Function :买车——更多筛选GridView
 */
public class BuyCarMoreFilterGridView extends LinearLayout{
    private Context mContext;

    private int chooseColor = R.color.text_blue;
    private int noChooseColor = R.color.text_item_lightgrey;

    public BuyCarMoreFilterGridView(Context context) {
        super(context);
        this.mContext = context;
        initView(mContext);
    }

    public BuyCarMoreFilterGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    private TextView mTitleView;
    private BuyCarGridViewInScroll mGridView;
    private List<GridViewData> mDataList = new ArrayList<GridViewData>();
    private final MyGridViewAdapter mAdapter = new MyGridViewAdapter();
    private void initView(Context context) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_buycar_morefilter_layout, null);
        mTitleView = (TextView) view.findViewById(R.id.view_advance_filter_title);
        mGridView = (BuyCarGridViewInScroll) view.findViewById(R.id.view_advance_filter_gridview);
        mGridView.setAdapter(mAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                toChangeGridItemColor(position);
                mGridItemClickCallback.onGridItemClick(mDataList.get(position));
            }
        });
        this.addView(view);
        this.setOrientation(LinearLayout.HORIZONTAL);
        DisplayMetrics metric = new DisplayMetrics();
        Activity activity = (Activity) mContext;
        activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;     // 屏幕宽度（像素）
        LayoutParams params = new LayoutParams(width, LayoutParams.WRAP_CONTENT);
        mGridView.setLayoutParams(params);
        this.setLayoutParams(params);
    }

    public void toInitShowData(String title,int blue,String[] list){
        mTitleView.setText(title);
        if(mDataList.size()>0){
            mDataList.clear();
        }
        GridViewData data = null;
        for(int i= 0;i<list.length;i++){
            if(i==blue){
                data = new GridViewData(list[i],i,chooseColor,1);
            }else{
                data = new GridViewData(list[i],i,noChooseColor,0);
            }
            mDataList.add(data);
            data = null;
        }
        mAdapter.notifyDataSetChanged();
    }

    public void toChangeGridItemColor(int index){
        if(mDataList!=null&&mDataList.size()>0)
        for(int i=0;i<mDataList.size();i++){
            if(i==index){
                mDataList.get(i).setTextColor(chooseColor);
                mDataList.get(i).setBackgrondType(1);
            }else{
                mDataList.get(i).setTextColor(noChooseColor);
                mDataList.get(i).setBackgrondType(0);
            }
        }
        mAdapter.notifyDataSetChanged();
    }
    private  GridItemClickCallback mGridItemClickCallback;

    public void setGridItemClickCallback(GridItemClickCallback callback){
        mGridItemClickCallback = callback;
    }
    public interface GridItemClickCallback{
        /**
         * 自动检测回调
         * @param itemData
         */
        public void onGridItemClick(GridViewData itemData);
    }
    public class GridViewData{
        private String text;
        private int textId;
        private int textColor;
        private int backgrondType;//0:未选中  1:选中

        public GridViewData(String text, int textId, int textColor,
                            int backgrondType) {
            super();
            this.text = text;
            this.textId = textId;
            this.textColor = textColor;
            this.backgrondType = backgrondType;
        }
        public String getText() {
            return text;
        }
        public void setText(String text) {
            this.text = text;
        }
        public int getTextId() {
            return textId;
        }
        public void setTextId(int textId) {
            this.textId = textId;
        }
        public int getTextColor() {
            return textColor;
        }
        public void setTextColor(int textColor) {
            this.textColor = textColor;
        }
        public int getBackgrondType() {
            return backgrondType;
        }
        public void setBackgrondType(int backgrondType) {
            this.backgrondType = backgrondType;
        }
        @Override
        public String toString() {
            return "GridViewData [text=" + text + ", textId=" + textId
                    + ", textColor=" + textColor + ", backgrondType="
                    + backgrondType + "]";
        }

    }
    class MyGridViewAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return mDataList.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return mDataList.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_buycar_morefilter_layout, null);
            TextView view = (TextView) convertView.findViewById(R.id.item_advance_filter_choose_text);
            GridViewData data = mDataList.get(position);
            view.setText(data.getText());
            view.setTextColor(getResources().getColor(data.getTextColor()));
            switch (data.getBackgrondType()) {
                case 0:
                    view.setBackgroundResource(R.drawable.write);
                    break;
                case 1:
                    view.setBackgroundResource(R.drawable.blue);
                    break;
                default:
                    break;
            }
            return convertView;
        }

    }
}
