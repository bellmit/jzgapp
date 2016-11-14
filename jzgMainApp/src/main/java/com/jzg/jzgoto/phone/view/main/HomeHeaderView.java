package com.jzg.jzgoto.phone.view.main;

import android.app.Dialog;
import android.content.Context;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.activity.common.ConstantForAct;
import com.jzg.jzgoto.phone.constant.Constant;
import com.jzg.jzgoto.phone.event.CarChooseEvent;
import com.jzg.jzgoto.phone.global.AppContext;
import com.jzg.jzgoto.phone.global.Statistical;
import com.jzg.jzgoto.phone.models.BannerData;
import com.jzg.jzgoto.phone.models.business.valuation.ValuationBuyCarParams;
import com.jzg.jzgoto.phone.models.business.valuation.ValuationBuyCarResult;
import com.jzg.jzgoto.phone.models.business.valuation.ValuationSellCarParams;
import com.jzg.jzgoto.phone.models.business.valuation.ValuationSellCarResult;
import com.jzg.jzgoto.phone.services.business.valuation.ValuationService;
import com.jzg.jzgoto.phone.services.common.OnRequestFinishListener;
import com.jzg.jzgoto.phone.tools.CountClickTool;
import com.jzg.jzgoto.phone.tools.DisplayUtils;
import com.jzg.jzgoto.phone.tools.ImageRender;
import com.jzg.jzgoto.phone.tools.InfoDialogManager;
import com.jzg.jzgoto.phone.tools.ToastManager;
import com.jzg.jzgoto.phone.view.ViewUtility;
import com.jzg.jzgoto.phone.view.common.HomeBannerView;
import com.jzg.jzgoto.phone.view.sellcar.DoubleSeekBar;
import com.jzg.pricechange.phone.JzgCarChooseStyle;

import java.util.List;

import de.greenrobot.event.EventBus;


public class HomeHeaderView extends LinearLayout implements View.OnClickListener, OnRequestFinishListener {

    private static final int REQUEST_CODE_SELL_CAR_VALUATION = 1;
    private static final int REQUEST_CODE_BUY_CAR_VALUATION = 2;

    private FrameLayout mBannerContainer;
    private LinearLayout mBannerViewContainer;
    private ImageView mBannerDefaultImageView;
    private HomeBannerView mBannerView;
    private HomeCarSummaryInfoView mCarInfoView;
    private DoubleSeekBar mPriceAdjustView;
    private View mLatestNewsView;
    private List<BannerData> mBannerDatalList ;
    private View.OnClickListener mClickListener = null;
    private JzgCarChooseStyle mCarChooseStyle;
    private int mBannerHeight = 0;

    private String mSytleId;
    private String mCityId;
    private String mCityName;
    private String mMileage;
    private String mRegisterDate;
    private ValuationService mValuationService;
    private Dialog mWaitingDialog = null;

    public HomeHeaderView(Context context) {
        super(context);
        initViews();
    }

    public HomeHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    @Override
    protected void onAttachedToWindow() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        super.onDetachedFromWindow();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    public void onEvent(CarChooseEvent event) {
        if (event == null) {
            return;
        }
        mCarChooseStyle = event.carInfo;
        if (mCarChooseStyle != null) {
        }
    }

    private void initViews() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View rootView = inflater.inflate(R.layout.view_home_homeheaderview, this);

        mBannerContainer = (FrameLayout) rootView.findViewById(R.id.banner_container);
        mBannerViewContainer = (LinearLayout) rootView.findViewById(R.id.banner_view_container);
        mBannerDefaultImageView = (ImageView) rootView.findViewById(R.id.banner_view_place_hoder_imageview);
        //mBannerView = new HomeBannerView(this.getContext());
        //mBannerViewContainer.addView(mBannerView);
        ViewGroup.LayoutParams params = mBannerContainer.getLayoutParams();
        mBannerHeight = params.height = (int) (DisplayUtils.getDisplayPixelWidth(getContext()) * 410/750);
        mBannerContainer.setLayoutParams(params);
        mCarInfoView = (HomeCarSummaryInfoView) rootView.findViewById(R.id.view_car_summary_info_view);
        mLatestNewsView = rootView.findViewById(R.id.view_latest_news);
        rootView.findViewById(R.id.btn_car_query_price).setOnClickListener(this);
        rootView.findViewById(R.id.btn_car_evaluate).setOnClickListener(this);
        rootView.findViewById(R.id.btn_buy_car_use_total).setOnClickListener(this);
        rootView.findViewById(R.id.btn_buy_car_use_loan).setOnClickListener(this);
        mPriceAdjustView = ((DoubleSeekBar) rootView.findViewById(R.id.dsb));
        mPriceAdjustView.setData(new String[]{"0","5","10","15","20","30","50","70","100","不限"});
        mValuationService = new ValuationService(this.getContext(), this);
        String imageUrl = ConstantForAct.getHomeBannerImageUrl(this.getContext());
        if (!TextUtils.isEmpty(imageUrl)){
            ImageRender.getInstance().setImage(mBannerDefaultImageView, imageUrl, R.drawable.banner_default);
        }else{
            mBannerDefaultImageView.setImageResource(R.drawable.banner_default);
        }
    }

    public void updateViews() {
        if (mBannerView != null){
            mBannerView.startShow();
        }
    }

    public View getLatestNewsView(){
        return mLatestNewsView;
    }

    public void setHomeBannerImageUrl(String imageUrl){
        if (!TextUtils.isEmpty(imageUrl)) {
            ImageRender.getInstance().setImage(mBannerDefaultImageView, imageUrl, R.drawable.banner_default);
        }
    }

    public int getBannerHeight(){
        return mBannerHeight;
    }

    public void setOnClickListener(View.OnClickListener clickListener){
        mClickListener = clickListener;
    }

    public void setBanners(List<BannerData> bannerList) {
        if (mBannerView != null){
            if (bannerList != null && bannerList.size() > 0) {
                mBannerView.stopShow();
                mBannerView.setData(bannerList);
                mBannerView.startShow();
                mBannerViewContainer.setVisibility(View.VISIBLE);
                mBannerDefaultImageView.setVisibility(View.GONE);
            }else{
                mBannerViewContainer.setVisibility(View.GONE);
                mBannerDefaultImageView.setVisibility(View.VISIBLE);
            }
        }
    }

    public void startAutoScroll() {
        if (mBannerView != null && mBannerDatalList != null && mBannerDatalList.size() > 1) {
            mBannerView.startShow();
        }
    }

    public void stopAutoScroll() {
        if (mBannerView != null && mBannerDatalList != null && mBannerDatalList.size() > 1) {
            mBannerView.stopShow();
        }
    }

    public void resetAutoScroll() {
        if (mBannerView != null && mBannerDatalList != null && mBannerDatalList.size() > 1) {
            mBannerView.stopShow();
        }
    }

    @Override
    public void onClick(View view) {
        if (mClickListener != null){
            mClickListener.onClick(view);
        }
        switch(view.getId()){
            case R.id.btn_car_evaluate:
                handEvaluateClicked();
                break;
            case R.id.btn_car_query_price:
                handQueryCarPriceClicked();
                break;
            case R.id.btn_buy_car_use_total:
                handBuyCarClicked(false);
                break;
            case R.id.btn_buy_car_use_loan:
                handBuyCarClicked(true);
                break;
        }
    }

    private void handEvaluateClicked() {
        if (mCarInfoView.isDataReady()) {
            mCityId = mCarInfoView.getCityId();
            mCityName = mCarInfoView.getCityName();
            mSytleId = String.valueOf(mCarInfoView.getCarStyle().getId());
            mMileage = mCarInfoView.getMileage();
            mRegisterDate = mCarInfoView.getRegisterDate();
            CountClickTool.onEvent(getContext(), Statistical.KEY_HOME_VALUATION_CLICK_COUNT);
            //ViewUtility.navigateToValuationActivity(this.getContext(), mCarChooseStyle);
            requestSellCarValuation();
        }
    }

    private void handQueryCarPriceClicked(){
        if (mCarInfoView.isDataReady()) {
            mCityId = mCarInfoView.getCityId();
            mCityName = mCarInfoView.getCityName();
            mSytleId = String.valueOf(mCarInfoView.getCarStyle().getId());
            mMileage = mCarInfoView.getMileage();
            mRegisterDate = mCarInfoView.getRegisterDate();
            CountClickTool.onEvent(getContext(), Statistical.KEY_HOME_VALUATION_CLICK_COUNT);
            //ViewUtility.navigateToValuationActivity(this.getContext(), mCarChooseStyle);
            requestBuyCarValuation();
        }
    }

    private void handBuyCarClicked(boolean useLoan){
        String carType = "0";
        String cityId = "";
        String cityName = "";
        String brandId = "";
        String brandName = "";
        String modeId = "";
        String modeName = "";
        String priceBegin = String.valueOf(mPriceAdjustView.getLow());
        String priceEnd = String.valueOf(mPriceAdjustView.getHigh());
        if (priceBegin.equals("不限")){
            priceBegin = "99990000";
        }
        if (priceEnd.equals("不限")){
            priceEnd = "99990000";
        }
        String isLoan = useLoan ? "1" : "0";
        ViewUtility.navigateToBuyCarActivity(this.getContext(), carType, cityId, cityName, brandId, brandName,
                modeId, modeName, priceBegin, priceEnd, isLoan);
        CountClickTool.onEvent(getContext(), Statistical.KEY_HOME_BUY_CAR_CLICK_COUNT);
    }

    private void selectCarStyle(){
        Context context = this.getContext();
        //ViewUtility.navigateToChooseCarActivity(context);
        ViewUtility.navigateToCarReleaseIndexValuationActivity(context);
    }

    protected void showWaitingDialog(String msg){
        mWaitingDialog = InfoDialogManager.getInstance(this.getContext()).showDialog(true, msg);
    }

    protected void hideWaitingDialog(){
        if (mWaitingDialog != null && mWaitingDialog.isShowing()) {
            mWaitingDialog.dismiss();
        }
    }

    private void requestSellCarValuation(){
        showWaitingDialog(this.getResources().getString(R.string.main_tip_data_loading));
        ValuationSellCarParams requestParams = new ValuationSellCarParams();
        requestParams.uid = "0";
        requestParams.styleid = mSytleId;
        requestParams.regdate = mRegisterDate;
        requestParams.mileage = mMileage;
        requestParams.cityname = mCityName;
        requestParams.CityId = mCityId;
        if (AppContext.isLogin()) {
            requestParams.uid = AppContext.mLoginResultModels.getId();
        }
        mValuationService.toResuestValuationSellCar(requestParams, ValuationSellCarResult.class, REQUEST_CODE_SELL_CAR_VALUATION);
    }

    private void requestBuyCarValuation(){
        showWaitingDialog(this.getResources().getString(R.string.main_tip_data_loading));
        ValuationBuyCarParams requestParams = new ValuationBuyCarParams();
        requestParams.uid = "0";
        requestParams.styleid = mSytleId;
        requestParams.regdate = mRegisterDate;
        requestParams.mileage = mMileage;
        requestParams.cityname = mCityName;
        requestParams.CityId = mCityId;
        if (AppContext.isLogin()) {
            requestParams.uid = AppContext.mLoginResultModels.getId();
        }
        mValuationService.toResuestValuationBuyCar(requestParams, ValuationBuyCarResult.class, REQUEST_CODE_BUY_CAR_VALUATION);
    }

    @Override
    public void onRequestSuccess(Message msg) {
        switch (msg.what) {
            case REQUEST_CODE_SELL_CAR_VALUATION:
                handleRequestSellCarValuationSuccess(msg);
                break;
            case REQUEST_CODE_BUY_CAR_VALUATION:
                handleRequestBuyCarValuationSuccess(msg);
                break;
        }
    }

    @Override
    public void onRequestFault(Message msg) {
        switch (msg.what) {
            case REQUEST_CODE_SELL_CAR_VALUATION:
                handleRequestSellCarValuationFailed(msg);
                break;
            case REQUEST_CODE_BUY_CAR_VALUATION:
                handleRequestBuyCarValuationFailed(msg);
                break;
        }
    }

    private void handleRequestSellCarValuationSuccess(Message msg){
        hideWaitingDialog();
        ValuationSellCarResult sellCarResult = (ValuationSellCarResult) msg.obj;
        if (sellCarResult != null && sellCarResult.getStatus()== Constant.SUCCESS){
            ViewUtility.navigateToValuationSellActivity(this.getContext(), sellCarResult);
        }else{
            ToastManager.getInstance().showToastCenter(this.getContext(), R.string.error_net);
        }
    }

    private void handleRequestSellCarValuationFailed(Message msg){
        hideWaitingDialog();
        ToastManager.getInstance().showToastCenter(this.getContext(), R.string.error_net);
    }

    private void handleRequestBuyCarValuationSuccess(Message msg){
        hideWaitingDialog();
        ValuationBuyCarResult buyCarResult = (ValuationBuyCarResult) msg.obj;
        if (buyCarResult != null && buyCarResult.getStatus() == Constant.SUCCESS){
            ViewUtility.navigateToValuationBuyActivity(this.getContext(), buyCarResult);
        }else{
            ToastManager.getInstance().showToastCenter(this.getContext(), R.string.error_net);
        }
    }

    private void handleRequestBuyCarValuationFailed(Message msg){
        hideWaitingDialog();
        ToastManager.getInstance().showToastCenter(this.getContext(), R.string.error_net);
    }

}
