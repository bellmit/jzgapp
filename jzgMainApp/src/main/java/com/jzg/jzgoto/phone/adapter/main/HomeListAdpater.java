package com.jzg.jzgoto.phone.adapter.main;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.models.BannerData;
import com.jzg.jzgoto.phone.models.RecommendCardList;
import com.jzg.jzgoto.phone.view.main.HomeHeaderView;
import com.jzg.jzgoto.phone.view.main.HomeRecommentListItemView;

import java.util.ArrayList;
import java.util.List;

public class HomeListAdpater extends BaseAdapter {
    private static final int ITEM_PER_ROW_COUNT = 2;

    private static final int TYPE_LIST_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private static final int TYPE_COUNT = 2;
    private static final int FIXED_HEADER_COUNT = 1;

    private int fixedHeaderCount = FIXED_HEADER_COUNT;

    private Context context;
    
    private HomeHeaderView headerView;
    private List<BannerData> mBanners;
    private List<RecommendCardList> mRecommendDataList;
    private View.OnClickListener mClickListener = null;
	
    public HomeListAdpater(Context context) {
        super();
        this.context = context;
        mRecommendDataList = new ArrayList<RecommendCardList>();
        mBanners = new ArrayList<BannerData>();
    }

    public void setOnClickListener(View.OnClickListener clickListener){
        mClickListener = clickListener;
    }

    public void setRecommendDataList(List<RecommendCardList> dataList) {
        if (dataList != null) {
            mRecommendDataList.clear();
            mRecommendDataList.addAll(dataList);
            notifyDataSetChanged();
        }else{
            mRecommendDataList.clear();
            notifyDataSetChanged();
        }
    }

    public void setBanners(List<BannerData> banners) {
        if (banners != null){
            mBanners.clear();
            mBanners.addAll(banners);
            if (headerView != null) {
                headerView.setBanners(mBanners);
            }
        }
    }

    public void startAutoScroll() {
        if (headerView != null) {
            headerView.startAutoScroll();
        }
    }

    public void stopAutoScroll() {
        if (headerView != null) {
            headerView.stopAutoScroll();
        }
    }
    
    public void resetAutoScroll() {
        if (headerView != null) {
            headerView.resetAutoScroll();
        }
    }

    @Override
    public int getCount() {
        int size = fixedHeaderCount;
        size += mRecommendDataList.size();
        return size;
    }

    @Override
    public RecommendCardList getItem(int position) {
        if (position <= 0) {
            return null;
        }
        return null;
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
        if (position == 0) {
            return TYPE_LIST_HEADER;
        }
        return TYPE_ITEM;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int itemType = getItemViewType(position);
        if (itemType == TYPE_LIST_HEADER) {
            return getHeaderView(convertView);
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

        card.setData(mRecommendDataList.get(position - fixedHeaderCount).getCardList());
        card.setPosition(position - fixedHeaderCount);
        card.setDivider(position == getCount() - 1);
        return card;
    }

    protected View getHeaderView(View convertView) {
        if (convertView != null && convertView instanceof HomeHeaderView) {
            headerView = (HomeHeaderView) convertView;
        } else {
            headerView = new HomeHeaderView(context);
        }
        headerView.setBanners(this.mBanners);
        headerView.setOnClickListener(mClickListener);
        if (mRecommendDataList == null || mRecommendDataList.isEmpty()){
            headerView.getLatestNewsView().setVisibility(View.GONE);
        }else{
            headerView.getLatestNewsView().setVisibility(View.VISIBLE);
        }
        return headerView;
    }

    public int getBannerHeight(){
        int height = 0;
        if (headerView != null){
            height = headerView.getBannerHeight();
        }
        return height;
    }

    public void setHomeBannerImageUrl(String imageUrl){
        if (headerView != null){
            headerView.setHomeBannerImageUrl(imageUrl);
        }
    }
}
