package com.jzg.jzgoto.phone.adapter.carmanager;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.jzg.jzgoto.phone.models.business.carmanager.CarManagerMyCarData;
import com.jzg.jzgoto.phone.view.carmanager.MyCarItemView;
import java.util.ArrayList;
import java.util.List;

public class MyCarListAdpater extends BaseAdapter {

    private Context context;
    private List<CarManagerMyCarData> mCarDataList;
    private View.OnClickListener mClickListener = null;
	
    public MyCarListAdpater(Context context) {
        super();
        this.context = context;
        mCarDataList = new ArrayList<CarManagerMyCarData>();
    }

    public void setOnClickListener(View.OnClickListener clickListener){
        mClickListener = clickListener;
    }

    public void setCarDataList(List<CarManagerMyCarData> dataList) {
        if (dataList != null) {
            mCarDataList.clear();
            mCarDataList.addAll(dataList);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return  mCarDataList == null ? 0 : mCarDataList.size();
    }

    @Override
    public CarManagerMyCarData getItem(int position) {
        return mCarDataList == null || mCarDataList.size() <= position ? null : mCarDataList.get(position);
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
        MyCarItemView carView = null;
        if (convertView != null && convertView instanceof MyCarItemView) {
            carView = (MyCarItemView) convertView;
        } else {
            carView = new MyCarItemView(context);
        }

        carView.setData(mCarDataList.get(position));
        carView.setDivider(position == getCount() - 1);
        return carView;
    }

}
