package com.jzg.jzgoto.phone.view.common;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.tools.DisplayUtils;
import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


public abstract class BannerViewBase<T> extends LinearLayout {

    public enum PageIndicatorPositionType {
        TOP_RIGHT,
        BOTTOM
    }


    public interface BannerViewListener{
        void handlePageChanged(int pageIndex);
    }
    private BannerViewListener mBannerViewListener = null;

    protected Context mContext;
    protected List<T> mBannerDatalList = new ArrayList<T>();
    protected int mCurrentItem = 0; // 当前图片的索引号
    protected ScheduledExecutorService mScheduledExecutorService;
    protected ViewPager mViewPager;
    protected BannerViewBasePagerAdapter mPagerAdapter;
    protected LinearLayout mPointContainer;
    private List<View> pointList = new ArrayList<View>();
    protected PageIndicatorPositionType mPageIndicatorPosition = PageIndicatorPositionType.BOTTOM;
    protected long mBannerTimerInterval = 2000;

    public BannerViewBase(Context context) {
        super(context);
        this.mContext = context;
        initView(mContext);
    }

    public BannerViewBase(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView(context);
    }

    public BannerViewBase(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
        initView(context);
    }

    protected void initView(Context mContext) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_banner_view_layout, this);
        mViewPager = (ViewPager) view.findViewById(R.id.banner_viewpager);
        //mPointContainer = (LinearLayout) view.findViewById(R.id.banner_point_container);
        mPagerAdapter = createPagerAdapter();
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOnPageChangeListener(new MyPageChangeListener());
        createPageIndicator(mContext, (ViewGroup)view.findViewById(R.id.banner_rootview));
    }

    private void createPageIndicator(Context context, ViewGroup rootView){
        mPointContainer = new LinearLayout(context);
        mPointContainer.setOrientation(LinearLayout.HORIZONTAL);
        if (rootView instanceof RelativeLayout) {
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            if (mPageIndicatorPosition == PageIndicatorPositionType.BOTTOM){
                params.bottomMargin = DisplayUtils.dpToPx(context, 10);
                params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
                params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
            }else if (mPageIndicatorPosition == PageIndicatorPositionType.TOP_RIGHT){
                params.topMargin = DisplayUtils.dpToPx(context, 10);
                params.rightMargin = DisplayUtils.dpToPx(context, 10);
                params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
                params.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
            }
            rootView.addView(mPointContainer, params);
        }
    }

    protected abstract BannerViewBasePagerAdapter createPagerAdapter();

    protected void addChildViews(){};

    public void setData(List<T> bannerList) {
        if (mBannerDatalList != null) {
            mBannerDatalList.clear();
        } else {
            mBannerDatalList = new ArrayList<T>();
        }
        mCurrentItem = 0;
        if (bannerList != null) {
            mBannerDatalList.addAll(bannerList);
        }
        addPointView();
        addChildViews();
        mViewPager.setAdapter(mPagerAdapter);
        if (mBannerDatalList != null && mBannerDatalList.size() > 0) {
            mViewPager.setVisibility(View.VISIBLE);
            mPointContainer.setVisibility(View.VISIBLE);
            mViewPager.setCurrentItem(mCurrentItem, false);
            changePointColor(mCurrentItem);
            if (mBannerDatalList.size() <= 1 &&  mPagerAdapter.isInfiniteLoop()){
                mPagerAdapter.setInfiniteLoop(false);
            }
//            if (mBannerDatalList.size() == 1){
//                mPointContainer.setVisibility(View.INVISIBLE);
//            }
        }else{
            mViewPager.setVisibility(View.INVISIBLE);
            mPointContainer.setVisibility(View.INVISIBLE);
        }
        mPagerAdapter.notifyDataSetChanged();
    }

    public void notifyDataSetChanged(){
        if (mPagerAdapter != null) {
            mPagerAdapter.notifyDataSetChanged();
        }
    }

    public void setItemData(int position, T itemData){

    }

    protected Handler mVPHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            int totalCount = mViewPager.getAdapter().getCount();
            int currItem = mViewPager.getCurrentItem();
            currItem++;
            if (currItem < 0) {
                currItem = totalCount - 1;
            } else if (currItem == totalCount) {
                currItem = 0;
            }
            mViewPager.setCurrentItem(currItem);
        }
    };

    public void setBannerViewListener(BannerViewListener listener){
        mBannerViewListener = listener;
    }

    public void stopShow() {
        if (mScheduledExecutorService != null) {
            mScheduledExecutorService.shutdownNow();
            mScheduledExecutorService = null;
        }
    }

    public void startShow() {
        if (mBannerDatalList != null && mBannerDatalList.size() > 1) {
            if (mScheduledExecutorService == null) {
                mScheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
            } else {
                mScheduledExecutorService.shutdownNow();
            }
            // 显示出来后，每两秒切换一次图片显示
            mScheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 1000, mBannerTimerInterval,
                    TimeUnit.MILLISECONDS);
        }
    }

    private void addPointView() {
        // 动态添加图片和下面指示的圆点
        // 初始化图片资源
        pointList.clear();
        mPointContainer.removeAllViews();
        for (int i = 0; i < mBannerDatalList.size(); i++) {
            ImageView view = new ImageView(mContext);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(20, 20);
            layoutParams.setMargins(5, 5, 5, 5);
            view.setLayoutParams(layoutParams);
            pointList.add(view);
            mPointContainer.addView(view);
        }
    }

    private class ScrollTask implements Runnable {

        @Override
        public void run() {
            synchronized (mViewPager) {
                if (mBannerDatalList != null && mBannerDatalList.size() > 0) {
                    //mCurrentItem = (mCurrentItem + 1) % mBannerDatalList.size();
                    mVPHandler.obtainMessage().sendToTarget();
                }
            }
        }
    }

    private class MyPageChangeListener implements OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
            //TODO
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            //TODO
        }

        @Override
        public void onPageSelected(int position) {
            mCurrentItem = position;
            int actualPos = ((BannerViewBasePagerAdapter)mPagerAdapter).getPosition(position);
            changePointColor(actualPos);
            if (mBannerViewListener != null){
                mBannerViewListener.handlePageChanged(actualPos);
            }
        }
    }

    public class BannerViewBasePagerAdapter extends PagerAdapter {
        private boolean isInfiniteLoop = true;

        @Override
        public int getCount() {
            // Infinite loop
            return isInfiniteLoop ? Integer.MAX_VALUE : mBannerDatalList == null ? 0 : mBannerDatalList.size();
        }

        public int getPosition(int position) {
            return isInfiniteLoop ? position % ((mBannerDatalList == null || mBannerDatalList.size() == 0) ? 1 : mBannerDatalList.size()) : position;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {

            return arg0 == arg1;
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView((View) arg2);
        }

        public boolean isInfiniteLoop() {
            return isInfiniteLoop;
        }

        public BannerViewBasePagerAdapter setInfiniteLoop(boolean isInfiniteLoop) {
            this.isInfiniteLoop = isInfiniteLoop;
            return this;
        }

    }

    private void changePointColor(int index) {
        if (pointList != null && pointList.size() > 0) {
            for (int i = 0; i < pointList.size(); i++) {
                if (i == index) {
                    pointList.get(i).setBackgroundResource(R.drawable.banner_point_focused);
                } else {
                    pointList.get(i).setBackgroundResource(R.drawable.banner_point_normal);
                }
            }
        }
    }
}
