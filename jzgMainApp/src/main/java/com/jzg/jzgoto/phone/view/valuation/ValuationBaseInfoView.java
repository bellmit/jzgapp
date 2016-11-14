package com.jzg.jzgoto.phone.view.valuation;

import android.content.Context;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.models.business.valuation.ValuationBuyCarResult;
import com.jzg.jzgoto.phone.models.business.valuation.ValuationSellCarResult;
import com.jzg.jzgoto.phone.tools.ClickControlTool;
import com.jzg.jzgoto.phone.tools.CountClickTool;
import com.jzg.jzgoto.phone.tools.ImageRender;
import com.jzg.jzgoto.phone.view.ViewUtility;

/**
 * Created by WY on 2016/9/20.
 * Function :估值详情——基本信息界面
 */
public class ValuationBaseInfoView extends LinearLayout{

    public ValuationBaseInfoView(Context context) {
        super(context);
        initView();
    }
    public ValuationBaseInfoView(Context context, AttributeSet attrs) {
        super(context,attrs);
        initView();
    }
    public ValuationBaseInfoView(Context context, AttributeSet attrs,
                                 int defStyleAttr) {
        super(context,attrs,defStyleAttr);
        initView();
    }

    private ImageView mCarIcon;
    private TextView mCarName,mRegDate,mMileage,mCity;
    private LinearLayout mHedgeView;
    private TextView mHedgeInfo,toHedgeList,mHedgeImage;
    private void initView(){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_valuation_sell_carinfo_layout,null);
        mCarIcon = (ImageView) view.findViewById(R.id.valuation_sell_carInfo_imageView);
        mHedgeImage = (TextView) view.findViewById(R.id.valuation_sell_carInfo_hedgeImage);
        mCarName = (TextView) view.findViewById(R.id.valuation_sell_carInfo_name);
        mRegDate = (TextView) view.findViewById(R.id.valuation_sell_carInfo_date);
        mMileage = (TextView) view.findViewById(R.id.valuation_sell_carInfo_mileage);
        mCity = (TextView) view.findViewById(R.id.valuation_sell_carInfo_region);
        mHedgeView = (LinearLayout) view.findViewById(R.id.valuation_sell_carInfo_hedgeLayout);
        mHedgeInfo = (TextView) view.findViewById(R.id.valuation_sell_carInfo_hedge_carStyle);
        toHedgeList = (TextView) view.findViewById(R.id.valuation_sell_carInfo_hedge_List);
        toHedgeList.setOnClickListener(mOnClickListener);
        this.addView(view);
    }

    public void setValuationSellCarBaseInfoData(ValuationSellCarResult mValuationSellresult){
        ImageRender.getInstance().setImage(mCarIcon,mValuationSellresult.getImgUrl());
        mCarName.setText(mValuationSellresult.getFullName());
        mRegDate.setText(mValuationSellresult.getRegDate());
        mMileage.setText(mValuationSellresult.getMileage()+"万公里");
        mCity.setText(mValuationSellresult.getCityName());
    }
 //   private ValuationBuyCarResult mValuationBuyResult;
    private String mCityId,mCityName,mModelLevel,mModelLevelName;
    public void setValuationBuyCarBaseInfoData(ValuationBuyCarResult valuationBuyresult){
        setHedgeViewVisible(true);
        mCityId = valuationBuyresult.getCityId();
        mCityName = valuationBuyresult.getCityName();
        mModelLevel = valuationBuyresult.getCurrentModelLevelId();
        mModelLevelName = valuationBuyresult.getCurrentModelLevelName();
        ImageRender.getInstance().setImage(mCarIcon,valuationBuyresult.getImgUrl());
        mCarName.setText(valuationBuyresult.getFullName());
        mRegDate.setText(valuationBuyresult.getRegDate());
        mMileage.setText(valuationBuyresult.getMileage()+"万公里");
        mCity.setText(valuationBuyresult.getCityName());
        String baoZhilvRank = valuationBuyresult.getBaoZhilvRank();
        if(Integer.valueOf(baoZhilvRank)>20){
            baoZhilvRank = "20+";
        }else if("0".equals(baoZhilvRank)|| TextUtils.isEmpty(valuationBuyresult.getBaoZhilvCityName())){
            setHedgeViewVisible(false);
        }
        String hedgeInfo = "该车为 "+valuationBuyresult.getBaoZhilvCityName()+" "+
                valuationBuyresult.getBaoZhilvLevelName()+" 保值率第"+baoZhilvRank;
        mHedgeInfo.setText(hedgeInfo);
        if("1".equals(valuationBuyresult.getBaoZhilvRank())){
            mHedgeImage.setBackgroundResource(R.drawable.img_n1);
            mHedgeImage.setVisibility(View.VISIBLE);
        }else if("2".equals(valuationBuyresult.getBaoZhilvRank())){
            mHedgeImage.setBackgroundResource(R.drawable.img_n2);
            mHedgeImage.setVisibility(View.VISIBLE);
        }else if("3".equals(valuationBuyresult.getBaoZhilvRank())){
            mHedgeImage.setBackgroundResource(R.drawable.img_n3);
            mHedgeImage.setVisibility(View.VISIBLE);
        }else{
            mHedgeImage.setBackgroundResource(R.drawable.img_nduo);
            mHedgeImage.setText(baoZhilvRank);
        }
    }
    /**
     * 设置保值率排行榜可见
     */
    private void setHedgeViewVisible(boolean isShow){
        if(isShow){
            if(mHedgeView!=null)mHedgeView.setVisibility(View.VISIBLE);
        }else{
            if(mHedgeView!=null)mHedgeView.setVisibility(View.GONE);
        }
    }
    private OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!ClickControlTool.isCanToClick())return;
            switch (v.getId()){
                case R.id.valuation_sell_carInfo_hedge_List:
                    //查看保值率排行榜
                    CountClickTool.onEvent(getContext(), "V5_valuation_buy_hedgeRatio");
                    ViewUtility.navigateToValuationHedgeActivity(getContext(),
                            mCityId,mCityName,mModelLevel,mModelLevelName);
                    break;
            }
        }
    };
}
