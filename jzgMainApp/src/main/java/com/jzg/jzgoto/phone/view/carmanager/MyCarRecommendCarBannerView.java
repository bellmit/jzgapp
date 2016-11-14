package com.jzg.jzgoto.phone.view.carmanager;

import java.util.ArrayList;
import java.util.List;
import com.jzg.jzgoto.phone.models.business.carmanager.FocusCarData;
import com.jzg.jzgoto.phone.view.common.BannerViewBase;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;


public class MyCarRecommendCarBannerView extends BannerViewBase<List<FocusCarData>> {

    private List<MyCarRecommendCarPageView> mViewList = new ArrayList<MyCarRecommendCarPageView>();

    public MyCarRecommendCarBannerView(Context context) {
        super(context);
    }

    public MyCarRecommendCarBannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyCarRecommendCarBannerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    protected void initView(Context context){
        mPageIndicatorPosition = PageIndicatorPositionType.TOP_RIGHT;
        super.initView(context);
    }

    protected BannerViewBasePagerAdapter createPagerAdapter(){
        return new MyPagerAdapter();
    }

    protected void addChildViews() {
        mViewList.clear();
        for (int i = 0; i < mBannerDatalList.size(); i++) {
            MyCarRecommendCarPageView carView = new MyCarRecommendCarPageView(mContext);
            carView.setData(mBannerDatalList.get(i));
            mViewList.add(carView);
        }
    }

    private class MyPagerAdapter extends BannerViewBasePagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            if (mViewList != null && mViewList.size() > 0) {
                final int actualPos = getPosition(position);
                MyCarRecommendCarPageView iv = mViewList.get(actualPos);
                container.removeView(iv);
                ((ViewPager) container).addView(iv);
                iv.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 跳转详情
                        List<FocusCarData> carItemData = (mBannerDatalList.get(actualPos));
                        if (carItemData != null) {
                        }
                    }
                });
                return iv;
            }
            return null;
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
        }
    }
}
