package com.jzg.jzgoto.phone.view.valuation;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.models.business.valuation.DrawViewSectorViewTypeModel;
import com.jzg.jzgoto.phone.models.business.valuation.ValuationSellCarResult;
import com.jzg.jzgoto.phone.models.business.valuation.sell.RequestHistoryDealParams;
import com.jzg.jzgoto.phone.tools.ClickControlTool;
import com.jzg.jzgoto.phone.tools.CountClickTool;
import com.jzg.jzgoto.phone.tools.DisplayUtils;
import com.jzg.jzgoto.phone.view.ViewUtility;
import com.jzg.jzgoto.phone.view.valuation.DrawViewSectorBean.OnSelfClickListener;

/**
 * Created by WY on 2016/9/20.
 * Function :车主估值——车商/个人报价
 */
public class ValuationSellPriceView extends LinearLayout{

    public ValuationSellPriceView(Context context) {
        super(context);
        initView();
    }
    public ValuationSellPriceView(Context context, AttributeSet attrs) {
        super(context,attrs);
        initView();
    }
    public ValuationSellPriceView(Context context, AttributeSet attrs,
                                 int defStyleAttr) {
        super(context,attrs,defStyleAttr);
        initView();
    }
    private RelativeLayout mPriceBLayout, mPriceCLayout;
    private TextView mPriceBText, mPriceCText;
    private View mPriceBView, mPriceCView;
    private Button mHistoryPrice;
    private TextView mDescriptionText,mPriceRangeText,mSellPriceText;
    private DrawViewSectorView mSectorView;
    private void initView(){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_valuation_sell_price_layout,null);
        mPriceBLayout = (RelativeLayout) view.findViewById(R.id.valuation_sell_priceB_layout);
        mPriceCLayout = (RelativeLayout) view.findViewById(R.id.valuation_sell_priceC_layout);
        mPriceBText = (TextView) view.findViewById(R.id.valuation_sell_priceB_textView);
        mPriceCText = (TextView) view.findViewById(R.id.valuation_sell_priceC_textView);
        mPriceBView =  view.findViewById(R.id.valuation_sell_priceB_bottomView);
        mPriceCView =  view.findViewById(R.id.valuation_sell_priceC_bottomView);
        mHistoryPrice = (Button) view.findViewById(R.id.valuation_sell_price_history_button);
        mSectorView = (DrawViewSectorView) view.findViewById(R.id.valuation_sell_price_sectorView);
        mPriceBLayout.setOnClickListener(mClickListener);
        mPriceCLayout.setOnClickListener(mClickListener);
        mHistoryPrice.setOnClickListener(mClickListener);
        mDescriptionText = (TextView) view.findViewById(R.id.valuation_sell_price_description);
        mPriceRangeText = (TextView) view.findViewById(R.id.valuation_sell_price_range);
        mSellPriceText = (TextView) view.findViewById(R.id.valuation_sell_price_jzg);
        if("MI NOTE LTE".equals(Build.MODEL)){
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DisplayUtils.dpToPx(getContext(),230));
            mSectorView.setLayoutParams(layoutParams);
        }

        this.addView(view);
    }
    private ValuationSellCarResult mValuationSellresult;
    private DrawViewSectorViewTypeModel mSectorViewTypeModel;
    public void showSellPriceData(final ValuationSellCarResult sellresult){
        mValuationSellresult = sellresult;
        mSectorViewTypeModel = new DrawViewSectorViewTypeModel();
        mSectorViewTypeModel.setType(1);
        mSectorViewTypeModel.setShowText(null);
        mSectorViewTypeModel.setClickIndex(2);
        changeSectorViewData();
        mSectorView.toShowData(mSectorViewTypeModel, 1f, mOnSelfClickListener);
    }
    private final OnSelfClickListener mOnSelfClickListener = new OnSelfClickListener() {
        @Override
        public void onClickModel(int index) {
            mSectorViewTypeModel.setClickIndex(index);
            changeSectorViewData();
        }
    };
    private boolean isBussinessCheck = true;
    private OnClickListener mClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!ClickControlTool.isCanToClick())return;
            switch (v.getId()){
                case R.id.valuation_sell_priceB_layout:
                    //商家报价
                    isBussinessCheck = true;
                    changeBtnBlue(mPriceBText, mPriceBView);
                    changeBtnGrey(mPriceCText, mPriceCView);
                    mDescriptionText.setText("卖给车商成交速度快，由于车商需要赚取差价，所以价格相对低。");
                    mSectorViewTypeModel.setClickIndex(2);
                    mSectorView.toShowData(mSectorViewTypeModel,1f,mOnSelfClickListener);
                    changeSectorViewData();
                    break;
                case R.id.valuation_sell_priceC_layout:
                    //个人报价
                    isBussinessCheck = false;
                    changeBtnBlue(mPriceCText, mPriceCView);
                    changeBtnGrey(mPriceBText, mPriceBView);
                    mDescriptionText.setText("卖给个人通常能卖个好价格，但需要遇到有缘人，成交速度较慢。");
                    mSectorViewTypeModel.setClickIndex(2);
                    mSectorView.toShowData(mSectorViewTypeModel,1f,mOnSelfClickListener);
                    changeSectorViewData();
                    break;
                case R.id.valuation_sell_price_history_button:
                    //查看历史成交记录
                    CountClickTool.onEvent(getContext(), "V5_valuation_sell_transaction_price");
                    RequestHistoryDealParams params = new RequestHistoryDealParams();
                    params.cityId = mValuationSellresult.getCityId();
                    params.styleId = mValuationSellresult.getStyleId();
                    params.pageindex = "1";
                    params.type = "2";
                    ViewUtility.navigateToValuationSellHistoryActivity(getContext(),params);
                    break;
            }
        }
    };
    private void changeBtnBlue(TextView blueTextView,View blueBg){
        if(blueTextView!=null)
            blueTextView.setTextColor(getResources().getColor(R.color.text_blue));
        if(blueBg!=null)
            blueBg.setBackgroundColor(getResources().getColor(R.color.text_blue));
    }
    private void changeBtnGrey(TextView greyTextView,View greyBg){
        if(greyTextView!=null)
            greyTextView.setTextColor(getResources().getColor(R.color.grey_for_valuation));
        if(greyBg!=null)
            greyBg.setBackgroundColor(getResources().getColor(R.color.background));
    }

    //控制扇形图点击数据颜色改变
    private void changeSectorViewData(){
        if(isBussinessCheck){
            //车商报价
            if(mSectorViewTypeModel.getClickIndex() == 1){
                //点击车况一般
                mPriceRangeText.setText(getValuationRange(mValuationSellresult.getC2BCLowPrice(),mValuationSellresult.getC2BCUpPrice()));
                mSellPriceText.setText(getValuationSellPrice(mValuationSellresult.getC2BCMidPrice()));
            }else if(mSectorViewTypeModel.getClickIndex() == 2){
                //点击车况良好
                mPriceRangeText.setText(getValuationRange(mValuationSellresult.getC2BBLowPrice(),mValuationSellresult.getC2BBUpPrice()));
                mSellPriceText.setText(getValuationSellPrice(mValuationSellresult.getC2BBMidPrice()));
            }else if(mSectorViewTypeModel.getClickIndex() == 3){
                //点击车况极好
                mPriceRangeText.setText(getValuationRange(mValuationSellresult.getC2BALowPrice(),mValuationSellresult.getC2BAUpPrice()));
                mSellPriceText.setText(getValuationSellPrice(mValuationSellresult.getC2BAMidPrice()));
            }
        }else{
            //个人报价
            if(mSectorViewTypeModel.getClickIndex() == 1){
                //点击车况一般
                mPriceRangeText.setText(getValuationRange(mValuationSellresult.getC2CCLowPrice(),mValuationSellresult.getC2CCUpPrice()));
                mSellPriceText.setText(getValuationSellPrice(mValuationSellresult.getC2CCMidPrice()));
            }else if(mSectorViewTypeModel.getClickIndex() == 2){
                //点击车况良好
                mPriceRangeText.setText(getValuationRange(mValuationSellresult.getC2CBLowPrice(),mValuationSellresult.getC2CBUpPrice()));
                mSellPriceText.setText(getValuationSellPrice(mValuationSellresult.getC2CBMidPrice()));
            }else if(mSectorViewTypeModel.getClickIndex() == 3){
                //点击车况极好
                mPriceRangeText.setText(getValuationRange(mValuationSellresult.getC2CALowPrice(),mValuationSellresult.getC2CAUpPrice()));
                mSellPriceText.setText(getValuationSellPrice(mValuationSellresult.getC2CAMidPrice()));
            }
        }
    }

    private String getValuationRange(String lowPrice,String upPrice){
        String returnStr = "估值范围  "+lowPrice+"万-"+upPrice+"万";
        return returnStr;
    }
    private String getValuationSellPrice(String midPrice){
        String returnStr = "建议售卖价："+midPrice+"万";
        return returnStr;
    }
}
