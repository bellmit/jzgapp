package com.jzg.jzgoto.phone.view.user;

import android.content.Context;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.event.SubscribeCancelEvent;
import com.jzg.jzgoto.phone.global.AppContext;
import com.jzg.jzgoto.phone.models.CarConditionData;
import com.jzg.jzgoto.phone.models.CarData;
import com.jzg.jzgoto.phone.models.business.buy.BuyCarDetailParams;
import com.jzg.jzgoto.phone.models.business.buy.BuyCarDetailResult;
import com.jzg.jzgoto.phone.models.common.CommonModelSettings;
import com.jzg.jzgoto.phone.services.business.buy.BuyCarService;
import com.jzg.jzgoto.phone.services.common.OnRequestFinishListener;
import com.jzg.jzgoto.phone.tools.CarFilterUtils;
import com.jzg.jzgoto.phone.tools.DisplayUtils;
import com.jzg.jzgoto.phone.tools.InfoDialogManager;
import com.jzg.jzgoto.phone.tools.ToastManager;
import com.jzg.jzgoto.phone.view.ViewUtility;
import com.jzg.jzgoto.phone.view.common.TagFlowLayout;
import com.jzg.jzgoto.phone.view.shared.CarBaseItemView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;


public class SubscribeItemView extends LinearLayout implements View.OnClickListener, OnRequestFinishListener {
    private static final int REQUEST_CODE_GET_CAR_DETAIL_DATA = 1;

    @Bind(R.id.view_subscribe_car_list_container)
    LinearLayout mCarListContainer;
    @Bind(R.id.view_subscribe_condition_tab_container)
    TagFlowLayout mConditionTagView;
    @Bind(R.id.btn_subscribe_cancel)
    View mSubscribeCancelBtn;
    @Bind(R.id.view_item_divider)
    View mItemDividerView;

    private CarConditionData mSubscribeData;
    private BuyCarService mBuyCarService;


    public SubscribeItemView(Context context) {
        super(context);
        initViews();
    }

    public SubscribeItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    public SubscribeItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        updateViews();
    }

    public void setData(CarConditionData data) {
        mSubscribeData = data;
        initConditionTagView(createTagList(data));
        updateViews();
    }

    private void updateViews() {
    	if (mSubscribeData != null){
            mCarListContainer.removeAllViews();
            CarBaseItemView itemView = null;
            List<CarData> carList =  mSubscribeData.getCarList();
            if (carList != null) {
                mCarListContainer.setVisibility(View.VISIBLE);
                for (int i = 0; i < carList.size(); i++) {
                    itemView = new CarBaseItemView(this.getContext());
                    itemView.setOnClickListener(this);
                    itemView.setData(carList.get(i));
                    mCarListContainer.addView(itemView);
                }
            }else{
                mCarListContainer.setVisibility(View.GONE);
            }
        }
    }

    private void initViews() {
    	LayoutInflater inflater = LayoutInflater.from(getContext());
    	View rootView = inflater.inflate(R.layout.view_user_subscribe_item_view_layout, this);
        ButterKnife.bind(this);
    }

    public void setDivider(boolean isLast){
        //mItemDividerView.setVisibility(isLast ? View.VISIBLE : View.GONE);
    }

    @Override
    @OnClick({R.id.btn_subscribe_cancel, R.id.view_subscribe_condition_container})
    public void onClick(View view) {
        if (view instanceof  CarBaseItemView){
            CarData carData = ((CarBaseItemView)view).getData();
            requestCarDetailData(carData);
        }

        switch (view.getId()) {
            case R.id.btn_subscribe_cancel:
                handleSubscribeCancelClicked();
                break;
            case R.id.view_subscribe_condition_container:
                ViewUtility.navigateToBuyCarActivity(this.getContext(), mSubscribeData);
                break;
        }
    }

    private void handleSubscribeCancelClicked(){
        EventBus.getDefault().post(SubscribeCancelEvent.build(mSubscribeData));
    }

    private List<String> createTagList(CarConditionData carConditionData){
        List<String> tagStrList = new ArrayList<String>();
        String tagText = null;
        if (carConditionData != null){

            //品牌
            if(!TextUtils.isEmpty(carConditionData.MakeName)){
                tagStrList.add(carConditionData.MakeName);
            }
            //车系
            if(!TextUtils.isEmpty(carConditionData.ModelName)){
                tagStrList.add(carConditionData.ModelName);
            }
            //车源
            if(carConditionData.CSUserType != 0){
                tagText = CarFilterUtils.getInstance().getCarUserTypeTagString(String.valueOf(carConditionData.CSUserType));
                if(!TextUtils.isEmpty(tagText) && !tagText.equals("不限")) {
                    tagStrList.add(tagText);
                }
            }
            //平台
            if(carConditionData.CarSourceFrom != 0){
                tagText = CarFilterUtils.getInstance().getCarSourceFromTagString(String.valueOf(carConditionData.CarSourceFrom));
                if(!TextUtils.isEmpty(tagText)){
                    tagStrList.add(tagText);
                }
            }
            //车型
            if(!TextUtils.isEmpty(carConditionData.ModelLevelName)){
                tagStrList.add(carConditionData.ModelLevelName);
            }
            //价格
            if (!TextUtils.isEmpty(carConditionData.BeginSellPrice) && !TextUtils.isEmpty(carConditionData.EndSellPrice)) {
                tagText = CarFilterUtils.getInstance().getCarPriceFilterTagData(
                        String.valueOf(carConditionData.BeginSellPrice), String.valueOf(carConditionData.EndSellPrice)).getFilterText();
                if (!TextUtils.isEmpty(tagText) && !tagText.equals("不限")) {
                    tagStrList.add(tagText);
                }
            }
            //车龄
            if(!TextUtils.isEmpty(carConditionData.BeginCarAge) && !TextUtils.isEmpty(carConditionData.EndCarAge)){
                tagText = CarFilterUtils.getInstance().getCarAgeFilterTagData(
                        String.valueOf(carConditionData.BeginCarAge), String.valueOf(carConditionData.EndCarAge)).getFilterText();
                if(!TextUtils.isEmpty(tagText) && !tagText.equals("不限")) {
                    tagStrList.add(tagText);
                }
            }
            //里程
            if(carConditionData.EndMileage != 0){
                tagText = CarFilterUtils.getInstance().getCarMileageFilterTagData(
                        String.valueOf(carConditionData.BeginMileage), String.valueOf(carConditionData.EndMileage)).getFilterText();
                if(!TextUtils.isEmpty(tagText) && !tagText.equals("不限")) {
                    tagStrList.add(tagText);
                }
            }
            //排量
            if(!TextUtils.isEmpty(carConditionData.BeginPL) && !TextUtils.isEmpty(carConditionData.EndPL)){
                tagText = CarFilterUtils.getInstance().getCarPaiLiangFilterTagData(
                        String.valueOf(carConditionData.BeginPL), String.valueOf(carConditionData.EndPL)).getFilterText();
                if(!TextUtils.isEmpty(tagText) && !tagText.equals("不限")) {
                    tagStrList.add(tagText);
                }
            }
            //变速箱
            if(!TextUtils.isEmpty(carConditionData.BsqSimpleText)){
                tagStrList.add(carConditionData.BsqSimpleText);
            }
            //座位数
            if(carConditionData.Seats != 0){
                tagText = CarFilterUtils.getInstance().getCarSeatsFilterTagData(
                        String.valueOf(carConditionData.Seats)).getFilterText();
                if(!TextUtils.isEmpty(tagText) && !tagText.equals("不限")) {
                    tagStrList.add(tagText);
                }
            }
            //国别
            if(!TextUtils.isEmpty(carConditionData.CountriesName) && !carConditionData.CountriesName.equals("0")){
                tagStrList.add(carConditionData.CountriesName);
            }
        }
        return tagStrList;
    }

    private void initConditionTagView(List<String> tagList){
//        tagList = new ArrayList<String>();
//        tagList.add("tag1");
//        tagList.add("tag2");
        if (tagList != null) {
            MarginLayoutParams lp = new MarginLayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            lp.leftMargin = DisplayUtils.dpToPx(this.getContext(), 3);
            lp.rightMargin = DisplayUtils.dpToPx(this.getContext(), 3);
            lp.topMargin = DisplayUtils.dpToPx(this.getContext(), 5);
            lp.bottomMargin = DisplayUtils.dpToPx(this.getContext(), 5);

            mConditionTagView.removeAllViews();
            SubscribeConditionTagView tagItemView = null;
            for (int i = 0; i < tagList.size(); i++){
                tagItemView = new SubscribeConditionTagView(this.getContext());
                tagItemView.setTagText(tagList.get(i));
                mConditionTagView.addView(tagItemView,lp);
            }
        }
    }

    private void requestCarDetailData(CarData carData){
        BuyCarDetailParams params = new BuyCarDetailParams();
        mBuyCarService = new BuyCarService(this.getContext(), this);
        if (AppContext.isLogin()) {
            params.uid = AppContext.mLoginResultModels.getId();
        }
        params.CarSourceId = String.valueOf(carData.getCarId());
        params.CarSourceFrom =  String.valueOf(carData.getCarSourceFrom());
        mBuyCarService.toResuestBuyCarDetail(params, BuyCarDetailResult.class, REQUEST_CODE_GET_CAR_DETAIL_DATA);
        showWaitingDialog(getResources().getString(R.string.main_tip_data_loading));
    }

    @Override
    public void onRequestSuccess(Message msg) {
        switch (msg.what) {
            case REQUEST_CODE_GET_CAR_DETAIL_DATA:
                handleRequestCarDetailDataSuccess(msg);
                break;
        }
    }

    @Override
    public void onRequestFault(Message msg) {
        switch (msg.what) {
            case REQUEST_CODE_GET_CAR_DETAIL_DATA:
                handleRequestCarDetailDataFailed(msg);
                break;
        }
    }

    private void handleRequestCarDetailDataSuccess(Message msg){
        hideWaitingDialog();
        BuyCarDetailResult requestResult = (BuyCarDetailResult)msg.obj;
        if (requestResult != null && requestResult.getStatus() == CommonModelSettings.STATUS_SUCCESS) {
            ViewUtility.navigateToBuyCarDetailActivity(this.getContext(), requestResult);
        }
    }

    private void handleRequestCarDetailDataFailed(Message msg){
        ToastManager.getInstance().showToastCenter(this.getContext(), R.string.error_noConnect);
    }

    protected void showWaitingDialog(String msg){
        InfoDialogManager.getInstance(this.getContext()).showDialog(true, msg);
    }

    protected void hideWaitingDialog(){
        InfoDialogManager.getInstance(this.getContext()).dismissDialog();
    }
}
