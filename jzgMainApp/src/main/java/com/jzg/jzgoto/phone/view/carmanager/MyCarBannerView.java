package com.jzg.jzgoto.phone.view.carmanager;

import java.util.ArrayList;
import java.util.List;
import com.jzg.jzgoto.phone.models.business.carmanager.CarManagerMyCarData;
import com.jzg.jzgoto.phone.view.ViewUtility;
import com.jzg.jzgoto.phone.view.common.BannerViewBase;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;


public class MyCarBannerView extends BannerViewBase<CarManagerMyCarData> {

    private List<MyCarItemView> mViewList = new ArrayList<MyCarItemView>();

    public MyCarBannerView(Context context) {
        super(context);
    }

    public MyCarBannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyCarBannerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    protected BannerViewBasePagerAdapter createPagerAdapter(){
        return new MyPagerAdapter();
    }

    private void skipToWebView(String url) {
        ViewUtility.navigateToWebView(mContext, "详情", url);
    }

    protected void addChildViews() {
        mViewList.clear();
        for (int i = 0; i < mBannerDatalList.size(); i++) {
            MyCarItemView carView = new MyCarItemView(mContext);
            carView.setData(mBannerDatalList.get(i));
            mViewList.add(carView);
        }
    }

    public void setItemData(int position, CarManagerMyCarData itemData){
        if (position >= 0 && position < mBannerDatalList.size()){
            MyCarItemView carView = mViewList.get(position);
            carView.setData(itemData);
        }
    }

    private class MyPagerAdapter extends BannerViewBasePagerAdapter {

        public MyPagerAdapter(){
            setInfiniteLoop(false);
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            if (mViewList != null && mViewList.size() > 0) {
                final int actualPos = getPosition(position);
                MyCarItemView iv = mViewList.get(actualPos);
                container.removeView(iv);
                ((ViewPager) container).addView(iv);
                // 在这个方法里面设置图片的点击事件
                iv.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 跳转详情
                        CarManagerMyCarData carItemData = (mBannerDatalList.get(actualPos));
                        if (carItemData != null && carItemData.getImageUrl() != null) {
                        }
                    }
                });
                return iv;
            }
            return null;
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
//            ((ViewPager) arg0).removeView((View) arg2);
        }

    }
}
