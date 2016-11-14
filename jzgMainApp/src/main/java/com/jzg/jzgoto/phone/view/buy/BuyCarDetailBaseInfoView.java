package com.jzg.jzgoto.phone.view.buy;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.global.HttpServiceHelper;
import com.jzg.jzgoto.phone.models.business.buy.BuyCarDetailResult;
import com.jzg.jzgoto.phone.models.business.valuation.sell.RequestValFragmentHistory;
import com.jzg.jzgoto.phone.tools.ClickControlTool;
import com.jzg.jzgoto.phone.tools.CountClickTool;
import com.jzg.jzgoto.phone.tools.ImageRender;
import com.jzg.jzgoto.phone.view.ViewUtility;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WY on 2016/9/18.
 * Function :买车详情 图片/基本信息界面
 */
public class BuyCarDetailBaseInfoView extends LinearLayout{

    private Context mContext;
    public BuyCarDetailBaseInfoView(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public BuyCarDetailBaseInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    public BuyCarDetailBaseInfoView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
        initView();
    }
    private ViewPager mViewPager;
    private ImageView mReturnBtn,mShareBtn;
    private TextView mPicNumber,mPublishDate;
    private TextView mCarName,mSellPrice,mJzgPrice,mSourceType,mSourceFrom;
    private TextView mLoanInfo,mApplyLoan;
    private LinearLayout mIsLoanLayout;
    private void initView(){
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_buycar_detail_baseinfo_layout,null);
        mViewPager = (ViewPager) view.findViewById(R.id.buycar_detail_baseInfo_viewPager);
        mReturnBtn = (ImageView) view.findViewById(R.id.buycar_detail_baseInfo_return);
        mShareBtn = (ImageView) view.findViewById(R.id.buycar_detail_baseInfo_share);
        mPicNumber = (TextView) view.findViewById(R.id.buycar_detail_baseInfo_imageNumber);
        mPublishDate = (TextView) view.findViewById(R.id.buycar_detail_baseInfo_publishDate);
        mCarName = (TextView) view.findViewById(R.id.buycar_detail_baseInfo_carName);
        mSellPrice = (TextView) view.findViewById(R.id.buycar_detail_baseInfo_sellPrice);
        mJzgPrice = (TextView) view.findViewById(R.id.buycar_detail_baseInfo_jzgPrice);
        mSourceType = (TextView) view.findViewById(R.id.buycar_detail_baseInfo_sourceType);
        mSourceFrom = (TextView) view.findViewById(R.id.buycar_detail_baseInfo_sourceFrom);
        mIsLoanLayout = (LinearLayout) view.findViewById(R.id.buycar_detail_baseInfo_loanLayout);
        mLoanInfo = (TextView) view.findViewById(R.id.buycar_detail_baseInfo_loanInfo);
        mApplyLoan = (TextView) view.findViewById(R.id.buycar_detail_baseInfo_applyLoan);
        mReturnBtn.setOnClickListener(mClickListener);
        mShareBtn.setOnClickListener(mClickListener);
        mApplyLoan.setOnClickListener(mClickListener);
        this.addView(view);
    }
    private BuyCarDetailResult mDetailResult;
    public void showBaseInfoData(BuyCarDetailResult result){
        mDetailResult = result;
        if(result.getPicList().size()>0){
            initViewPager();
        }
        mPicNumber.setText("1/"+result.getTotalPic());
        mPublishDate.setText(result.getPublishTime());
        mCarName.setText(result.getStyleFullName());
        mSellPrice.setText(result.getSellPrice()+"万");
        mJzgPrice.setText(result.getJzgGuzhiPrice()+"万");
        if("个人".equals(result.getCstype())){
            mSourceType.setBackgroundResource(R.drawable.geren);
        }else{
            mSourceType.setBackgroundResource(R.drawable.shangjia);
        }

        if(TextUtils.isEmpty(result.getShouFuPrice())
                ||TextUtils.isEmpty(result.getYuegongPrice())
                ||TextUtils.isEmpty(result.getDaikuanDuohuai())){
            mIsLoanLayout.setVisibility(View.GONE);
        }
        mLoanInfo.setText("首付 "+result.getShouFuPrice()+"    月供 "+result.getYuegongPrice()
                +"\n"+result.getDaikuanYear()+" 年期    比全款多花 "+result.getDaikuanDuohuai());
        mSourceFrom.setText(result.getFrom());
    }

    private List<ImageView> mImageList = new ArrayList<>();

    private void initViewPager(){
        mImageList.clear();
        int size = Integer.valueOf(mDetailResult.getTotalPic());
        for(int i=0;i<size;i++){
            ImageView image = new ImageView(mContext);
            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
            mImageList.add(image);
        }
        mViewPager.setAdapter(new MyPagerAdapter());
        mPicNumber.setText(1/mImageList.size()+"");
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mPicNumber.setText((position+1)+"/"+mDetailResult.getTotalPic());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private OnClickListener mClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!ClickControlTool.isCanToClick())return;
            switch (v.getId()){
                case R.id.buycar_detail_baseInfo_return:
                    //返回
                    ((Activity)mContext).finish();
                    break;
                case R.id.buycar_detail_baseInfo_share:
                    //分享
                    mShareCallBack.toSetShareContent();
                    break;
                case R.id.buycar_detail_baseInfo_applyLoan:
                    //申请贷款
                    CountClickTool.onEvent(getContext(), "V5_buyCar_detail_loan");
                    if(!TextUtils.isEmpty(mDetailResult.getLoanUrl()))
                        ViewUtility.navigateToWebView(getContext(),"选择贷款方式", HttpServiceHelper.BASE_SHARE_URL+mDetailResult.getLoanUrl());
                    break;
            }
        }
    };

    private BuyCarDetailShareCallBack mShareCallBack;

    public void setBuyCarDetailShareCallBack(BuyCarDetailShareCallBack callback) {
        mShareCallBack = callback;
    }
    public interface BuyCarDetailShareCallBack {
        public void toSetShareContent();
    }
    private class MyPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mImageList.size();
        }
        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            ImageView iv = mImageList.get(position);
            ImageRender.getInstance().setImage(iv, mDetailResult.getPicList().get(position).getPic(), R.drawable.jingzhengu_moren);
            ((ViewPager) container).addView(iv);
            // 设置图片的点击事件
            iv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 跳转大图

                }
            });
            return iv;
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
}
