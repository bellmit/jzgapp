package com.jzg.jzgoto.phone.view.buy;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.models.business.buy.BuyCarDetailFirstLikeModel;
import com.jzg.jzgoto.phone.models.business.buy.BuyCarDetailResult;
import com.jzg.jzgoto.phone.models.business.valuation.ValuationBuyCarResult;
import com.jzg.jzgoto.phone.models.business.valuation.ValuationSellCarResult;
import com.jzg.jzgoto.phone.tools.ImageRender;
import com.jzg.jzgoto.phone.tools.view.swipemenulistview.SwipeMenuView;
import com.jzg.jzgoto.phone.view.ViewUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by WY on 2016/9/18.
 * Function :买车详情 用户喜欢
 */
public class BuyCarDetailUserLikeView extends LinearLayout {
    private Context mContext;

    public BuyCarDetailUserLikeView(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public BuyCarDetailUserLikeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    public BuyCarDetailUserLikeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
        initView();
    }

    private ViewPager mViewPager;
    private LinearLayout mPointContainer;
    private TextView mModeName;
    private void initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_buycar_detail_userlike_layout, null);
        mViewPager = (ViewPager) view.findViewById(R.id.buycar_detail_userLike_viewPager);
        mModeName = (TextView) view.findViewById(R.id.buycar_detail_userLike_carName);
        mPointContainer = (LinearLayout) view.findViewById(R.id.buycar_detail_userLike_pointContainer);
        this.addView(view);
    }

    private List<ImageView> mPointList = new ArrayList<>();
    private List<View> mViewList = new ArrayList<>();
    private List<BuyCarDetailFirstLikeModel> mUserLikeModelList = new ArrayList<>();

    public void startShow(BuyCarDetailResult result) {
        mUserLikeModelList.clear();
        mUserLikeModelList.addAll(result.getFirstLikeModelList());
        mModeName.setText("喜欢"+result.getModelName()+"的用户，还喜欢");
        startPagerShow();
    }
    public void startShow(ValuationSellCarResult valSellResult) {
        mUserLikeModelList.clear();
        mUserLikeModelList.addAll(valSellResult.getFirstChangeModelList());
        mModeName.setText(valSellResult.getModelName()+"车主，换车首选");
        startPagerShow();
    }
    public void startShow(ValuationBuyCarResult valBuyResult) {
        mUserLikeModelList.clear();
        mUserLikeModelList.addAll(valBuyResult.getFirstLikeModelList());
        mModeName.setText("喜欢"+valBuyResult.getModelName()+"的用户，还喜欢");
        startPagerShow();
    }
    private void startPagerShow(){
        addPointView();
        mViewPager.setAdapter(new MyPagerAdapter());
        mViewPager.setOnPageChangeListener(new MyPageChangeListener());
        mScheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        // 显示出来后，每两秒切换一次图片显示
        mScheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 1, 2,
                TimeUnit.SECONDS);
    }
    private int mCurrentItem = 0; // 当前图片的索引号
    private ScheduledExecutorService mScheduledExecutorService;
    private Handler mVPHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            mViewPager.setCurrentItem(mCurrentItem);
        };
    };

    public void stopShow(){
        if(mScheduledExecutorService!=null){
            mScheduledExecutorService.shutdownNow();
            mScheduledExecutorService = null;
        }
    }
    private class ScrollTask implements Runnable {
        @Override
        public void run() {
            synchronized (mViewPager) {
                mCurrentItem = (mCurrentItem + 1) % 3;
                mVPHandler.obtainMessage().sendToTarget();
            }
        }
    }

    /**
     * 点击对应车型，跳转到买车列表
     */
    class onPagerItemClick{
        private int index;
        public onPagerItemClick(int index){
            this.index = index;
        }
        private OnClickListener mOnClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mUserLikeModelList.size()>index){
                    BuyCarDetailFirstLikeModel model = mUserLikeModelList.get(index);
                    ViewUtility.navigateToBuyCarActivity(getContext(),"0","0","",
                            model.getMakeId(),model.getMakeName(),model.getModelId(),model.getModelName(),"","","");
                }else{
                    return;
                }
            }
        };
    }

    private class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mViewList.size();
        }
        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View itemView = mViewList.get(position);
            ((ViewPager) container).addView(itemView);
            // 设置图片的点击事件
            itemView.findViewById(R.id.item_buycar_detail_userLike_leftLayout)
                    .setOnClickListener(new onPagerItemClick((position%3)*3).mOnClickListener);
            itemView.findViewById(R.id.item_buycar_detail_userLike_midLayout)
                    .setOnClickListener(new onPagerItemClick((position%3)*3+1).mOnClickListener);
            itemView.findViewById(R.id.item_buycar_detail_userLike_rightLayout)
                    .setOnClickListener(new onPagerItemClick((position%3)*3+2).mOnClickListener);
            return itemView;
        }
        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView((View) arg2);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {

            return arg0 == arg1;
        }
    }

    private class MyPageChangeListener implements ViewPager.OnPageChangeListener {

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
            changePointColor(position);
        }
    }

    private void addPointView() {
        // 动态添加图片和下面指示的圆点
        // 初始化图片资源
        mPointList.clear();
        mViewList.clear();
        mPointContainer.removeAllViews();
        int m = mUserLikeModelList.size()%3;
        int n = mUserLikeModelList.size()/3;
        for(int i=0;i<n;i++){
            View itemView = LayoutInflater.from(getContext()).inflate(R.layout.item_buycar_detail_userlike_layout, null);
            ImageView leftImage = (ImageView) itemView.findViewById(R.id.item_buycar_detail_userLike_leftImage);
            TextView leftName = (TextView) itemView.findViewById(R.id.item_buycar_detail_userLike_leftName);
            ImageView midImage = (ImageView) itemView.findViewById(R.id.item_buycar_detail_userLike_midImage);
            TextView midName = (TextView) itemView.findViewById(R.id.item_buycar_detail_userLike_midName);
            ImageView rightImage = (ImageView) itemView.findViewById(R.id.item_buycar_detail_userLike_rightImage);
            TextView rightName = (TextView) itemView.findViewById(R.id.item_buycar_detail_userLike_rightName);
            BuyCarDetailFirstLikeModel leftMode = mUserLikeModelList.get(i*3);
            BuyCarDetailFirstLikeModel midMode = mUserLikeModelList.get(i*3+1);
            BuyCarDetailFirstLikeModel rightMode = mUserLikeModelList.get(i*3+2);
            ImageRender.getInstance().setImage(leftImage,leftMode.getModelPic());
            leftName.setText(leftMode.getMakeModelName());
            ImageRender.getInstance().setImage(midImage,midMode.getModelPic());
            midName.setText(midMode.getMakeModelName());
            ImageRender.getInstance().setImage(rightImage,rightMode.getModelPic());
            rightName.setText(rightMode.getMakeModelName());
            ImageView pointView = new ImageView(mContext);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(20, 20);
            layoutParams.setMargins(5, 5, 5, 5);
            pointView.setLayoutParams(layoutParams);
            mViewList.add(itemView);
            mPointList.add(pointView);
            mPointContainer.addView(pointView);
        }
        if(m==1){
            View itemView = LayoutInflater.from(getContext()).inflate(R.layout.item_buycar_detail_userlike_layout, null);
            ImageView leftImage = (ImageView) itemView.findViewById(R.id.item_buycar_detail_userLike_leftImage);
            TextView leftName = (TextView) itemView.findViewById(R.id.item_buycar_detail_userLike_leftName);
            itemView.findViewById(R.id.item_buycar_detail_userLike_midImage).setVisibility(View.GONE);
            itemView.findViewById(R.id.item_buycar_detail_userLike_midName).setVisibility(View.GONE);
            itemView.findViewById(R.id.item_buycar_detail_userLike_rightImage).setVisibility(View.GONE);
            itemView.findViewById(R.id.item_buycar_detail_userLike_rightName).setVisibility(View.GONE);
            ImageRender.getInstance().setImage(leftImage,mUserLikeModelList.get(mUserLikeModelList.size()-1).getModelPic());
            leftName.setText(mUserLikeModelList.get(mUserLikeModelList.size()-1).getMakeModelName());
            ImageView pointView = new ImageView(mContext);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(20, 20);
            layoutParams.setMargins(5, 5, 5, 5);
            pointView.setLayoutParams(layoutParams);
            mViewList.add(itemView);
            mPointList.add(pointView);
            mPointContainer.addView(pointView);
        }else if(m==2){
            View itemView = LayoutInflater.from(getContext()).inflate(R.layout.item_buycar_detail_userlike_layout, null);
            ImageView leftImage = (ImageView) itemView.findViewById(R.id.item_buycar_detail_userLike_leftImage);
            TextView leftName = (TextView) itemView.findViewById(R.id.item_buycar_detail_userLike_leftName);
            ImageView midImage = (ImageView) itemView.findViewById(R.id.item_buycar_detail_userLike_midImage);
            TextView midName = (TextView) itemView.findViewById(R.id.item_buycar_detail_userLike_midName);
            itemView.findViewById(R.id.item_buycar_detail_userLike_rightImage).setVisibility(View.GONE);
            itemView.findViewById(R.id.item_buycar_detail_userLike_rightName).setVisibility(View.GONE);
            ImageRender.getInstance().setImage(leftImage,mUserLikeModelList.get(mUserLikeModelList.size()-1).getModelPic());
            leftName.setText(mUserLikeModelList.get(mUserLikeModelList.size()-1).getMakeModelName());
            ImageRender.getInstance().setImage(midImage,mUserLikeModelList.get(mUserLikeModelList.size()-2).getModelPic());
            midName.setText(mUserLikeModelList.get(mUserLikeModelList.size()-2).getMakeModelName());
            ImageView pointView = new ImageView(mContext);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(20, 20);
            layoutParams.setMargins(5, 5, 5, 5);
            pointView.setLayoutParams(layoutParams);
            mViewList.add(itemView);
            mPointList.add(pointView);
            mPointContainer.addView(pointView);
        }
    }
    private void changePointColor(int index){
        for(int i=0;i<mPointList.size();i++){
            if(i==index){
                mPointList.get(i).setBackgroundResource(R.drawable.banner_point_focused);
            }else{
                mPointList.get(i).setBackgroundResource(R.drawable.banner_point_normal);
            }
        }
    }
}
