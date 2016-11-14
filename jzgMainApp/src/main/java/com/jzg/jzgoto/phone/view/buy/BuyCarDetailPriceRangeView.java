package com.jzg.jzgoto.phone.view.buy;

import android.content.Context;
import android.os.Build;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.constant.Constant;
import com.jzg.jzgoto.phone.global.AppContext;
import com.jzg.jzgoto.phone.models.business.buy.BuyCarDetailResult;
import com.jzg.jzgoto.phone.models.business.valuation.DrawViewSectorViewTypeModel;
import com.jzg.jzgoto.phone.models.business.valuation.ValuationBuyCarParams;
import com.jzg.jzgoto.phone.models.business.valuation.ValuationBuyCarResult;
import com.jzg.jzgoto.phone.services.business.valuation.ValuationService;
import com.jzg.jzgoto.phone.services.common.OnRequestFinishListener;
import com.jzg.jzgoto.phone.tools.ClickControlTool;
import com.jzg.jzgoto.phone.tools.CountClickTool;
import com.jzg.jzgoto.phone.tools.DisplayUtils;
import com.jzg.jzgoto.phone.tools.ShowDialogTool;
import com.jzg.jzgoto.phone.view.ViewUtility;
import com.jzg.jzgoto.phone.view.valuation.DrawViewSectorView;

/**
 * Created by WY on 2016/9/18.
 * Function :买车详情——价格范围
 */
public class BuyCarDetailPriceRangeView extends LinearLayout implements OnRequestFinishListener{
    private Context mContext;

    public BuyCarDetailPriceRangeView(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public BuyCarDetailPriceRangeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    public BuyCarDetailPriceRangeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
        initView();
    }
    private TextView mSellNumber,mPriceRange,mPriceJudge,toBuyCarList,toValuationDetail;
    private DrawViewSectorView mSectorView;
    private LinearLayout mPriceRangeLayout,mValuationResultIsNull;
    private void initView(){
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_buycar_detail_pricerange_layout,null);
        mSellNumber = (TextView) view.findViewById(R.id.buycar_detail_priceRange_sellCarNumber);
        mPriceRangeLayout = (LinearLayout) view.findViewById(R.id.buycar_detail_priceRange_Layout);
        mValuationResultIsNull = (LinearLayout) view.findViewById(R.id.buycar_detail_priceRange_hideLayout);
        mPriceRange = (TextView) view.findViewById(R.id.buycar_detail_priceRange_textView);
        mPriceJudge = (TextView) view.findViewById(R.id.buycar_detail_priceRange_priceJudge);
        toBuyCarList = (TextView) view.findViewById(R.id.buycar_detail_priceRange_sellCars);
        toValuationDetail = (TextView) view.findViewById(R.id.buycar_detail_priceRange_toValuation);
        mSectorView = (DrawViewSectorView) view.findViewById(R.id.buycar_detail_priceRange_sectorView);
        toBuyCarList.setOnClickListener(mClickListener);
        toValuationDetail.setOnClickListener(mClickListener);
        if("MI NOTE LTE".equals(Build.MODEL)){
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,DisplayUtils.dpToPx(mContext,230));
            mSectorView.setLayoutParams(layoutParams);
        }
        this.addView(view);
    }
    private BuyCarDetailResult mDetailResult;
    public void showPriceRangeData(BuyCarDetailResult result){
        mDetailResult = result;
        mSellNumber.setText(result.getQwZsTotal());
        boolean isShowPriceText = true;//只有一辆车时，价格区间不显示
        if(!TextUtils.isEmpty(result.getQwZsTotal()))
        if(Integer.valueOf(result.getQwZsTotal())<2){
            mPriceRangeLayout.setVisibility(View.GONE);
            isShowPriceText = false;
        }
        if("0".equals(result.getB2CBUpPrice())&&"0".equals(result.getB2CBLowPrice())){
            mValuationResultIsNull.setVisibility(View.GONE);
        }else{
            mValuationResultIsNull.setVisibility(View.VISIBLE);
            DrawViewSectorViewTypeModel model = new DrawViewSectorViewTypeModel();
            model.setType(3);
            model.setShowPriceText(isShowPriceText);
            model.setShowText(new String[]{result.getQwZsMinPrice()+"万",
                    result.getB2CBLowPrice()+"-"+result.getB2CBUpPrice()+"万",result.getQwZsMaxPrice()+"万"});
            mSectorView.toShowData(model,1f);
        }
        mPriceRange.setText(result.getQwZsMinPrice()+"万-"+result.getQwZsMaxPrice()+"万");
        setPriceDescription();
    }
    private void setPriceDescription(){
        double sellPrice = Double.valueOf(mDetailResult.getSellPrice());
        double jzgMinPrice = Double.valueOf(mDetailResult.getB2CBLowPrice());
        double jzgMaxPrice = Double.valueOf(mDetailResult.getB2CBUpPrice());
        if(sellPrice>jzgMaxPrice){
            mPriceJudge.setText("该车报价略高，请详细了解车况，确认是否有加装改装的配置，可适当砍价");
        }else if(sellPrice<jzgMinPrice){
            mPriceJudge.setText("该车报价过低，请详细了解车况，排除事故车的可能，尽快下手");
        }else{
            mPriceJudge.setText("该车报价合理，可以电话咨询看车");
        }
    }
    private OnClickListener mClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!ClickControlTool.isCanToClick())return;
            switch (v.getId()){
                case R.id.buycar_detail_priceRange_sellCars:
                    //全网在售车源
                    CountClickTool.onEvent(getContext(), "V5_buyCar_carSource");
                    ViewUtility.navigateToBuyCarActivity(getContext(),"0",
                            mDetailResult.getCityId(),mDetailResult.getCityName(),
                            mDetailResult.getMakeID(),mDetailResult.getMakeName(),
                            mDetailResult.getModelID(),mDetailResult.getModelName(), "","","");
                    break;
                case R.id.buycar_detail_priceRange_toValuation:
                    //查看详细估值结果
                    CountClickTool.onEvent(getContext(), "V5_buyCar_valuation");
                    toRequestBuyCarValuation();
                    break;
            }
        }
    };

    private void toRequestBuyCarValuation(){
        //买家估值
        ShowDialogTool.showLoadingDialog(getContext());
        ValuationBuyCarParams buyCarParams = new ValuationBuyCarParams();
        if(AppContext.isLogin()){
            buyCarParams.uid = AppContext.mLoginResultModels.getId();
        }else{
            buyCarParams.uid = "0";
        }
        buyCarParams.styleid = mDetailResult.getStyleID();
        buyCarParams.regdate = mDetailResult.getRegDate();
        buyCarParams.mileage = mDetailResult.getMileage();
        buyCarParams.cityname = mDetailResult.getCityName();
        buyCarParams.CityId = mDetailResult.getCityId();
        new ValuationService(getContext(),this).toResuestValuationBuyCar(buyCarParams, ValuationBuyCarResult.class,R.id.request_valuation_buycar_detail);
    }

    @Override
    public void onRequestSuccess(Message msg) {
        ShowDialogTool.dismissLoadingDialog();
        if(msg.what == R.id.request_valuation_buycar_detail){
            ValuationBuyCarResult buyCarResult = (ValuationBuyCarResult) msg.obj;
            if(buyCarResult.getStatus()== Constant.SUCCESS){
                ViewUtility.navigateToValuationBuyActivity(mContext,buyCarResult);
            }else{
                ShowDialogTool.showCenterToast(mContext,getResources().getString(R.string.error_net));
            }
        }
    }

    @Override
    public void onRequestFault(Message msg) {
        ShowDialogTool.dismissLoadingDialog();
        ShowDialogTool.showCenterToast(mContext,getResources().getString(R.string.error_net));
    }
}
