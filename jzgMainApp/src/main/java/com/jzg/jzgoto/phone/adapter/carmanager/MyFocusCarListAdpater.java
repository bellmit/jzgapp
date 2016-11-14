package com.jzg.jzgoto.phone.adapter.carmanager;

import android.content.Context;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.models.business.carmanager.FocusCarData;
import com.jzg.jzgoto.phone.view.carmanager.MyFocusCarListItemView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyFocusCarListAdpater extends BaseAdapter {

    private Context context;
    private List<FocusCarData> mCarDataList;
    private View.OnClickListener mClickListener = null;
    final int INVALID_ID = -1;

     Listener listener;
    final Map<FocusCarData, Integer> mIdMap = new HashMap<>();
	
    public MyFocusCarListAdpater(Context context) {
        super();
        this.context = context;
        mCarDataList = new ArrayList<FocusCarData>();

    }

    public void setOnClickListener(View.OnClickListener clickListener){
        mClickListener = clickListener;
    }

    public void setCarDataList(List<FocusCarData> dataList) {
        if (dataList != null) {
            mCarDataList.clear();
            mCarDataList.addAll(dataList);
            mIdMap.clear();
            for (int i = 0; i < mCarDataList.size(); ++i) {
                mIdMap.put(mCarDataList.get(i), i);
            }
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return  mCarDataList == null ? 0 : mCarDataList.size();
    }

    @Override
    public FocusCarData getItem(int position) {
        return mCarDataList == null || mCarDataList.size() <= position ? null : mCarDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        if (position < 0 || position >= mIdMap.size()) {
            return INVALID_ID;
        }
        FocusCarData item = mCarDataList.get(position);
        return mIdMap.get(item);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getItemView(position, convertView);
    }
    
    protected View getItemView(final int position, View convertView) {
        MyFocusCarListItemView carView = null;
        if (convertView != null && convertView instanceof MyFocusCarListItemView) {
            carView = (MyFocusCarListItemView) convertView;
        } else {
            carView = new MyFocusCarListItemView(context);
        }
        final LinearLayout row = (LinearLayout) carView.findViewById(R.id.ll_lineaer);
        carView.setData(mCarDataList.get(position));
        carView.setDivider(position == getCount() - 1);
        carView.getmDragImageView().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(listener!=null){
                    listener.onGrab(position, row);
                }
                return false;
            }
        });
        return carView;
    }

    public void setOnListener(Listener listener){
        this.listener = listener;
    }
    public interface Listener {
        void onGrab(int position, LinearLayout row);
    }


    @Override
    public boolean hasStableIds() {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP;
    }

}
