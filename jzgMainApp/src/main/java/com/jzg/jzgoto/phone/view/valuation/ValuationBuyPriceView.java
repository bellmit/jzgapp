package com.jzg.jzgoto.phone.view.valuation;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.models.business.valuation.DrawViewSectorViewTypeModel;
import com.jzg.jzgoto.phone.models.business.valuation.ValuationBuyCarResult;
import com.jzg.jzgoto.phone.tools.ClickControlTool;
import com.jzg.jzgoto.phone.tools.CountClickTool;
import com.jzg.jzgoto.phone.tools.DisplayUtils;
import com.jzg.jzgoto.phone.view.ViewUtility;
import com.jzg.jzgoto.phone.view.valuation.DrawViewSectorBean.OnSelfClickListener;

/**
 * Created by WY on 2016/9/20.
 * Function :买家估值——车商/个人报价
 */
public class ValuationBuyPriceView extends LinearLayout{

    public ValuationBuyPriceView(Context context) {
        super(context);
        initView();
    }
    public ValuationBuyPriceView(Context context, AttributeSet attrs) {
        super(context,attrs);
        initView();
    }
    public ValuationBuyPriceView(Context context, AttributeSet attrs,
                                 int defStyleAttr) {
        super(context,attrs,defStyleAttr);
        initView();
    }
    private RelativeLayout mPriceBLayout, mPriceCLayout;
    private TextView mPriceBText, mPriceCText;
    private View mPriceBView, mPriceCView;
    private TextView mDescrptionText,mPriceMidText;
    private TextView mSellCarNumbers,mPriceRange,toBuyCarList;
    private DrawViewSectorView mSectorView;
    private LinearLayout mPriceRangeLayout;
    private void initView(){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_valuation_buy_price_layout,null);
        mPriceBLayout = (RelativeLayout) view.findViewById(R.id.valuation_buy_priceB_layout);
        mPriceCLayout = (RelativeLayout) view.findViewById(R.id.valuation_buy_priceC_layout);
        mPriceRangeLayout = (LinearLayout) view.findViewById(R.id.valuation_buy_price_range_layout);
        mPriceBText = (TextView) view.findViewById(R.id.valuation_buy_priceB_textView);
        mPriceCText = (TextView) view.findViewById(R.id.valuation_buy_priceC_textView);
        mPriceBView =  view.findViewById(R.id.valuation_buy_priceB_bottomView);
        mPriceCView =  view.findViewById(R.id.valuation_buy_priceC_bottomView);
        mDescrptionText = (TextView) view.findViewById(R.id.valuation_buy_price_description);
        mPriceMidText = (TextView) view.findViewById(R.id.valuation_buy_price_Mid);
        mSellCarNumbers = (TextView) view.findViewById(R.id.valuation_buy_price_sellCarNumber);
        mPriceRange = (TextView) view.findViewById(R.id.valuation_buy_price_range);
        toBuyCarList = (TextView) view.findViewById(R.id.valuation_buy_price_sellCars);
        mSectorView = (DrawViewSectorView) view.findViewById(R.id.valuation_buy_price_sectorView);
        mPriceBLayout.setOnClickListener(mClickListener);
        mPriceCLayout.setOnClickListener(mClickListener);
        toBuyCarList.setOnClickListener(mClickListener);

        if("MI NOTE LTE".equals(Build.MODEL)){
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DisplayUtils.dpToPx(getContext(),230));
            mSectorView.setLayoutParams(layoutParams);
        }
        this.addView(view);
    }

    private ValuationBuyCarResult mValBuyCarResult;
    private DrawViewSectorViewTypeModel mTypeModel = new DrawViewSectorViewTypeModel();
    public void initBuyCarPriceData(ValuationBuyCarResult valuationBuyResult){
        mValBuyCarResult = valuationBuyResult;
        mTypeModel = new DrawViewSectorViewTypeModel();
        mTypeModel.setClickIndex(5);
        mTypeModel.setType(2);
        mTypeModel.setShowText(null);
        mSellCarNumbers.setText(valuationBuyResult.getInSaleSum());
        if(Integer.valueOf(valuationBuyResult.getInSaleSum())<2){
            mPriceRangeLayout.setVisibility(View.GONE);
        }
        mPriceRange.setText(valuationBuyResult.getInSaleMinPrice()+"万-"+
                valuationBuyResult.getInSaleMaxPrice() +"万");
        controlBtnChange();
        mSectorView.toShowData(mTypeModel, 1f, mSelfClickListener);
        getPriceShowText();
    }
    private boolean isBusinessPrice = true;
    private OnClickListener mClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!ClickControlTool.isCanToClick())return;
            switch (v.getId()){
                case R.id.valuation_buy_priceB_layout:
                    //商家报价
                    isBusinessPrice = true;
                    controlBtnChange();
                    mTypeModel.setClickIndex(5);
                    getPriceShowText();
                    mSectorView.toShowData(mTypeModel, 1f, mSelfClickListener);
                    break;
                case R.id.valuation_buy_priceC_layout:
                    //个人报价
                    isBusinessPrice = false;
                    controlBtnChange();
                    mTypeModel.setClickIndex(5);
                    getPriceShowText();
                    mSectorView.toShowData(mTypeModel, 1f, mSelfClickListener);
                    break;
                case R.id.valuation_buy_price_sellCars:
                    //查看全网在售车源
                    CountClickTool.onEvent(getContext(), "V5_valuation_buy_carSource");
                    ViewUtility.navigateToBuyCarActivity(getContext(),"0",mValBuyCarResult.getCityId(),mValBuyCarResult.getCityName(),
                            mValBuyCarResult.getMakeId(),mValBuyCarResult.getMakeName(),
                            mValBuyCarResult.getModelId(),mValBuyCarResult.getModelName(),"","","");
                    break;
            }
        }
    };

    private void getPriceShowText(){
        if(isBusinessPrice){
            //车商报价
            if(mTypeModel.getClickIndex() == 4){
                mPriceMidText.setText("车况一般："+mValBuyCarResult.getB2CCMidPrice()+"万");
            }else if(mTypeModel.getClickIndex() == 5){
                mPriceMidText.setText( "车况良好："+mValBuyCarResult.getB2CBMidPrice()+"万");
            }else if(mTypeModel.getClickIndex() == 6){
                mPriceMidText.setText( "车况极好："+mValBuyCarResult.getB2CAMidPrice()+"万");
            }
        }else{
            //个人报价
            if(mTypeModel.getClickIndex() == 4){
                mPriceMidText.setText( "车况一般："+mValBuyCarResult.getC2CCMidPrice()+"万");
            }else if(mTypeModel.getClickIndex() == 5){
                mPriceMidText.setText( "车况良好："+mValBuyCarResult.getC2CBMidPrice()+"万");
            }else if(mTypeModel.getClickIndex() == 6){
                mPriceMidText.setText( "车况极好："+mValBuyCarResult.getC2CAMidPrice()+"万");
            }
        }
    }

private OnSelfClickListener mSelfClickListener = new OnSelfClickListener() {
    @Override
    public void onClickModel(int index) {
        mTypeModel.setClickIndex(index);
        getPriceShowText();
    }
};

    private void controlBtnChange(){
        if(isBusinessPrice){
            changeBtnBlue(mPriceBText, mPriceBView);
            changeBtnGrey(mPriceCText, mPriceCView);
            mDescrptionText.setText("车商出售的车辆外观内饰进行了修正，看起来干净整洁，请注意仔细检查车况。");
        }else{
            changeBtnBlue(mPriceCText, mPriceCView);
            changeBtnGrey(mPriceBText, mPriceBView);
            mDescrptionText.setText("个人车源车况比较真实，但卖家心理价位偏高，留有砍价余地。");
        }
    }

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
}
