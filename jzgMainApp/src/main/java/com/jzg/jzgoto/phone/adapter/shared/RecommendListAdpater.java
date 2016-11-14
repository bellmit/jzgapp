package com.jzg.jzgoto.phone.adapter.shared;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.models.RecommendCardList;
import com.jzg.jzgoto.phone.view.main.HomeHeaderView;
import com.jzg.jzgoto.phone.view.main.HomeRecommentListItemView;

import java.util.ArrayList;
import java.util.List;

public class RecommendListAdpater extends BaseAdapter {

    private static final int TYPE_COUNT = 2;
    private static final int FIXED_FOOTER_COUNT = 1;
    private static final int TYPE_LIST_FOOTER = 0;
    private static final int TYPE_ITEM = 1;

    private int fixedFooterCount = FIXED_FOOTER_COUNT;

    private Context context;
    private List<RecommendCardList> mRecommendDataList;
    private View.OnClickListener mClickListener = null;
	
    public RecommendListAdpater(Context context) {
        super();
        this.context = context;
        mRecommendDataList = new ArrayList<RecommendCardList>();
    }

    public void setOnClickListener(View.OnClickListener clickListener){
        mClickListener = clickListener;
    }

    public void setRecommendDataList(List<RecommendCardList> dataList) {
        if (dataList != null) {
            mRecommendDataList.clear();
            mRecommendDataList.addAll(dataList);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        int size = fixedFooterCount;
        size += mRecommendDataList.size();
        return size;
    }

    @Override
    public RecommendCardList getItem(int position) {
        return mRecommendDataList == null || mRecommendDataList.size() <= position ? null : mRecommendDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return TYPE_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        if (position >= mRecommendDataList.size()) {
            return TYPE_LIST_FOOTER;
        }
        return TYPE_ITEM;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int itemType = getItemViewType(position);
        if (itemType == TYPE_LIST_FOOTER) {
            return getFooterView(convertView);
        }else {
            return getItemView(position, convertView);
        }
    }
    
    protected View getItemView(int position, View convertView) {
        HomeRecommentListItemView card = null;
        if (convertView != null && convertView instanceof HomeRecommentListItemView) {
            card = (HomeRecommentListItemView) convertView;
        } else {
            card = new HomeRecommentListItemView(context);
        }

        card.setData(mRecommendDataList.get(position).getCardList());
        card.setDivider(position == getCount() - 1);
        return card;
    }

    protected View getFooterView(View convertView) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View footerView = inflater.inflate(R.layout.view_home_footer_view, null);
            convertView = footerView;
        }
        return convertView;
    }

}
