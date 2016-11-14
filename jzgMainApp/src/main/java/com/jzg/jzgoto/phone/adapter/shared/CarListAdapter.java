package com.jzg.jzgoto.phone.adapter.shared;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.jzg.jzgoto.phone.models.CarData;
import com.jzg.jzgoto.phone.view.shared.CarBaseItemView;
import java.util.ArrayList;
import java.util.List;


public class CarListAdapter extends BaseAdapter {
    private Context mContext;
    private List<CarData> mDataList = new ArrayList<CarData>();
    private boolean mIsOffline = false;

	
    public CarListAdapter(Context context) {
        super();
        this.mContext = context;
    }

    public void setDataList(List<CarData> dataList) {
        if (dataList != null) {
            mDataList.clear();
            mDataList.addAll(dataList);
            notifyDataSetChanged();
        }
    }

    public void setOffline(boolean offline){
        mIsOffline = offline;
    }

    @Override
    public int getCount() {
        return  mDataList == null ? 0 : mDataList.size();
    }

    @Override
    public CarData getItem(int position) {
        return mDataList == null || mDataList.size() <= position ? null : mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getItemView(position, convertView);
    }
    
    protected View getItemView(int position, View convertView) {
        CarBaseItemView itemView = null;
        if (convertView != null && convertView instanceof CarBaseItemView) {
            itemView = (CarBaseItemView) convertView;
        } else {
            itemView = new CarBaseItemView(mContext);
        }

        itemView.setData(mDataList.get(position));
        itemView.setOffline(mIsOffline);
        itemView.setDivider(position == getCount() - 1);
        return itemView;
    }

}
