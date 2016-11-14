package com.jzg.jzgoto.phone.adapter.user;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.jzg.jzgoto.phone.models.CarConditionData;
import com.jzg.jzgoto.phone.view.user.SubscribeItemView;
import java.util.ArrayList;
import java.util.List;


public class SubscribeListAdapter extends BaseAdapter {
    private Context mContext;
    private List<CarConditionData> mDataList = new ArrayList<CarConditionData>();

	
    public SubscribeListAdapter(Context context) {
        super();
        this.mContext = context;
    }

    public void setDataList(List<CarConditionData> dataList) {
        if (dataList != null) {
            mDataList.clear();
            mDataList.addAll(dataList);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return  mDataList == null ? 0 : mDataList.size();
    }

    @Override
    public CarConditionData getItem(int position) {
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
        SubscribeItemView itemView = null;
        if (convertView != null && convertView instanceof SubscribeItemView) {
            itemView = (SubscribeItemView) convertView;
        } else {
            itemView = new SubscribeItemView(mContext);
        }

        itemView.setData(mDataList.get(position));
        itemView.setDivider(position == getCount() - 1);
        return itemView;
    }

}
