package com.jzg.jzgoto.phone.adapter.carmanager;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.jzg.jzgoto.phone.models.business.carmanager.ProvinceSummaryData;
import com.jzg.jzgoto.phone.view.carmanager.CarLicensePlaceItemView;
import java.util.ArrayList;
import java.util.List;

public class CarLicensePlaceAdpater extends BaseAdapter {
    private Context mContext;
    private List<ProvinceSummaryData> mDataList = new ArrayList<ProvinceSummaryData>();

	
    public CarLicensePlaceAdpater(Context context) {
        super();
        this.mContext = context;
    }

    public void setDataList(List<ProvinceSummaryData> dataList) {
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
    public ProvinceSummaryData getItem(int position) {
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
        CarLicensePlaceItemView carView = null;
        if (convertView != null && convertView instanceof CarLicensePlaceItemView) {
            carView = (CarLicensePlaceItemView) convertView;
        } else {
            carView = new CarLicensePlaceItemView(mContext);
        }

        carView.setData(mDataList.get(position));
        carView.setDivider(position == getCount() - 1);
        return carView;
    }

}
