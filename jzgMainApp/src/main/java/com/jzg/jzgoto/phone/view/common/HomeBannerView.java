package com.jzg.jzgoto.phone.view.common;

import java.util.ArrayList;
import java.util.List;
import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.global.Statistical;
import com.jzg.jzgoto.phone.models.BannerData;
import com.jzg.jzgoto.phone.tools.CountClickTool;
import com.jzg.jzgoto.phone.tools.ImageRender;
import com.jzg.jzgoto.phone.view.ViewUtility;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;


public class HomeBannerView extends BannerViewBase<BannerData> {

    private List<ImageView> mViewList = new ArrayList<ImageView>();

    public HomeBannerView(Context context) {
        super(context);
    }

    public HomeBannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HomeBannerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    protected BannerViewBasePagerAdapter createPagerAdapter(){
        return new MyPagerAdapter();
    }

    private void skipToWebView(String url) {
        ViewUtility.navigateToWebView(mContext, "", url);
    }

    protected void addChildViews() {
        mViewList.clear();
        for (int i = 0; i < mBannerDatalList.size(); i++) {
            ImageView imageView = new ImageView(mContext);
            // 异步加载图片
            ImageRender.getInstance().setImage(imageView, mBannerDatalList.get(i).getImageUrl(), R.drawable.banner_default);
            imageView.setScaleType(ScaleType.CENTER_CROP);
            mViewList.add(imageView);
        }
    }

    private class MyPagerAdapter extends BannerViewBasePagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            if (mViewList != null && mViewList.size() > 0) {
                final int actualPos = getPosition(position);
                ImageView iv = mViewList.get(actualPos);
                container.removeView(iv);
                ((ViewPager) container).addView(iv);
                // 在这个方法里面设置图片的点击事件
                iv.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 跳转详情
                        if (mBannerDatalList.get(actualPos).getWebUrl() != null) {
                            if (!TextUtils.isEmpty(mBannerDatalList.get(actualPos).getWebUrl())) {
                                skipToWebView(mBannerDatalList.get(actualPos).getWebUrl());
                                CountClickTool.onEvent(getContext(), Statistical.KEY_HOME_BANNER_CLICK_COUNT + (actualPos + 1));
                            }
                        }
                    }
                });
                return iv;
            }
            return null;
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            //((ViewPager) arg0).removeView((View) arg2);
        }

    }
}
