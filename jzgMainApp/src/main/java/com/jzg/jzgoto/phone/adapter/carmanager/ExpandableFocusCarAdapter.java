package com.jzg.jzgoto.phone.adapter.carmanager;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.jzg.jzgoto.phone.models.RecommendCardData;
import com.jzg.jzgoto.phone.models.business.carmanager.FocusCarData;
import com.jzg.jzgoto.phone.view.carmanager.MyCarFocusCarGroupChildView;
import com.jzg.jzgoto.phone.view.carmanager.MyCarFocusCarGroupView;

import java.util.ArrayList;
import java.util.List;


public class ExpandableFocusCarAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<FocusCarData> mDataList;
    private LayoutInflater layoutInflater;

    
    public ExpandableFocusCarAdapter(Context cxt) {
        context = cxt;
        mDataList = new ArrayList<FocusCarData>();
        layoutInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    
    
    public void setData(List<FocusCarData>  data) {
        if(data == null) {
            mDataList.clear();
        } else {
            mDataList = data;
        }
        notifyDataSetChanged();
    }
    
    @Override
    public int getGroupCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        FocusCarData carInfo =  (FocusCarData)getGroup(groupPosition);
        if(carInfo == null) {
            return 0;
        }
        
        if(carInfo.getCardDataList() != null) {
            return carInfo.getCardDataList().size();
        }
        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        if(mDataList != null && groupPosition < mDataList.size()) {
            return mDataList.get(groupPosition);
        }
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        FocusCarData carInfo =  (FocusCarData)getGroup(groupPosition);
        if(carInfo == null) {
            return null;
        }
        
        if(carInfo.getCardDataList() != null && childPosition < carInfo.getCardDataList().size()) {
            return carInfo.getCardDataList().get(childPosition);
        }
        
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        MyCarFocusCarGroupView groupView = null;
        if (convertView != null && convertView instanceof MyCarFocusCarGroupView) {
            groupView = (MyCarFocusCarGroupView) convertView;
        } else {
            groupView = new MyCarFocusCarGroupView(context);
        }
        FocusCarData groupData = mDataList.get(groupPosition);
        groupView.setData(groupData);
        groupView.setExpand(isExpanded);
        return groupView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
            ViewGroup parent) {
        MyCarFocusCarGroupChildView childView = null;
        if (convertView != null && convertView instanceof MyCarFocusCarGroupChildView) {
            childView = (MyCarFocusCarGroupChildView) convertView;
        } else {
            childView = new MyCarFocusCarGroupChildView(context);
        }
        RecommendCardData childCardInfo = (RecommendCardData)getChild(groupPosition, childPosition);
        FocusCarData carInfo = (FocusCarData)getGroup(groupPosition);
        if(childPosition==0){
            childCardInfo.setTile("促销");
        }else if(childPosition==1){
            childCardInfo.setTile("导购");
        }else if(childPosition==2){
            childCardInfo.setTile("评测");
        }else{
            childCardInfo.setTile("");
        }
        childView.setData(childCardInfo);
        childView.setDivider(childPosition == getChildrenCount(groupPosition)-1);
        return childView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
