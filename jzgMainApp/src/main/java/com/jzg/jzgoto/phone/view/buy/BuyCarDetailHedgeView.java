package com.jzg.jzgoto.phone.view.buy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.activity.valuation.ValuationHedgeActivity;
import com.jzg.jzgoto.phone.models.business.buy.BuyCarDetailResult;
import com.jzg.jzgoto.phone.tools.CountClickTool;
import com.jzg.jzgoto.phone.view.ViewUtility;

/**
 * Created by WY on 2016/9/18.
 * Function :买车详情 保值率排行界面
 */
public class BuyCarDetailHedgeView extends LinearLayout{
    private Context mContext;
    public BuyCarDetailHedgeView(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public BuyCarDetailHedgeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    public BuyCarDetailHedgeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
        initView();
    }
    private TextView mHedgeImage;
    private TextView mHedgeInfo,toHedgeList;
    private TextView mShowTextView;
    private void initView(){
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_buycar_detail_hedge_layout,null);
        mHedgeImage = (TextView) view.findViewById(R.id.buycar_detail_hedge_Image);
        mHedgeInfo = (TextView) view.findViewById(R.id.buycar_detail_hedge_carStyle);
        mShowTextView = (TextView) view.findViewById(R.id.buycar_detail_hedge_showText);
        toHedgeList = (TextView) view.findViewById(R.id.buycar_detail_hedge_List);
        toHedgeList.setOnClickListener(mClickListener);
        WindowManager windowManager = ((Activity)getContext()).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        LayoutParams params = new LayoutParams(display.getWidth(), LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);
        this.addView(view);
    }
    private BuyCarDetailResult mDetailResult;
    private OnClickListener mClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.buycar_detail_hedge_List:
                    //保值率排行
                    CountClickTool.onEvent(getContext(), "V5_buyCar_hedgeRatio");
                    ViewUtility.navigateToValuationHedgeActivity(getContext(),
                            mDetailResult.getCityId(),mDetailResult.getCityName(),
                            mDetailResult.getCurrentModelLevelId(),mDetailResult.getCurrentModelLevelName());
                    break;
            }
        }
    };
    public void showHedgeData(BuyCarDetailResult result){
        mDetailResult = result;
        String baoZhilvRank = result.getBaoZhilvRank();
        if(Integer.valueOf(baoZhilvRank)>20){
            baoZhilvRank = "20+";
            mShowTextView.setVisibility(View.GONE);
        }
        String hedgeInfo = "该车为 "+result.getBaoZhilvCityName()+" "
                +result.getCurrentModelLevelName()+" 保值率第"+baoZhilvRank;
        mHedgeInfo.setText(hedgeInfo);
        switch (result.getBaoZhilvRank()){
            case "1":
                mHedgeImage.setBackgroundResource(R.drawable.img_n1);
                break;
            case "2":
                mHedgeImage.setBackgroundResource(R.drawable.img_n2);
                break;
            case "3":
                mHedgeImage.setBackgroundResource(R.drawable.img_n3);
                break;
            default:
                mHedgeImage.setBackgroundResource(R.drawable.img_nduo);
                mHedgeImage.setText(baoZhilvRank);
                break;
        }
    }
}
