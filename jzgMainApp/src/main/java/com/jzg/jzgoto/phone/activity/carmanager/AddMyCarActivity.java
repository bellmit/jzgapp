package com.jzg.jzgoto.phone.activity.carmanager;


import android.os.Bundle;
import android.os.Message;
import android.view.View;
import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.activity.common.ConstantForAct;
import com.jzg.jzgoto.phone.activity.shared.BaseActivity;
import com.jzg.jzgoto.phone.event.AddMyCarEvent;
import com.jzg.jzgoto.phone.event.CityChooseEvent;
import com.jzg.jzgoto.phone.global.AppContext;
import com.jzg.jzgoto.phone.global.GlobalData;
import com.jzg.jzgoto.phone.models.business.carmanager.CarManagerMyCarData;
import com.jzg.jzgoto.phone.models.business.carmanager.RequestCarManagerAddMyCarParams;
import com.jzg.jzgoto.phone.models.business.carmanager.RequestCarManagerAddMyCarResult;
import com.jzg.jzgoto.phone.models.business.valuation.ValuationSellCarParams;
import com.jzg.jzgoto.phone.models.business.valuation.ValuationSellCarResult;
import com.jzg.jzgoto.phone.models.common.CommonModelSettings;
import com.jzg.jzgoto.phone.services.business.carmanager.CarManagerService;
import com.jzg.jzgoto.phone.services.business.valuation.ValuationService;
import com.jzg.jzgoto.phone.tools.StringUtil;
import com.jzg.jzgoto.phone.tools.ToastManager;
import com.jzg.jzgoto.phone.view.ViewUtility;
import com.jzg.jzgoto.phone.view.carmanager.CarSummaryInfoView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;


public class AddMyCarActivity extends BaseActivity implements View.OnClickListener {

    private static final int REQUEST_CODE_ADD_MY_CAR = 1;
    private static final int REQUEST_CODE_EVALUATE_CAR = 2;


    @Bind(R.id.btn_add_car)
    View mAddCarBtn;
    @Bind(R.id.btn_start_evaluate)
    View mEvaluateBtn;
    @Bind(R.id.view_car_summary_info_view)
    CarSummaryInfoView mCarInfoView;

    private CarManagerService mDataService;
    private RequestCarManagerAddMyCarParams mRequestParams;
    private RequestCarManagerAddMyCarResult mRequestResult;
    private String mSytleId;
    private String mCityId;
    private String mCityName;
    private String mMileage;
    private String mRegisterDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_carmanager_add_car_layout);
        ButterKnife.bind(this);
        mDataService = new CarManagerService(this, this);
        mRequestParams = new RequestCarManagerAddMyCarParams();

        mSytleId = "15190";
        mCityId = "201";
        mCityName = "北京";
        mMileage = "2.1";
        mRegisterDate = "2015-01-01";
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    public void onEvent(CityChooseEvent event) {
        if (event != null && event.cityInfo != null) {
            mCityId = event.cityInfo.cityId;
            mCityName = StringUtil.returnShi(event.cityInfo.cityName);
        }
    }

    @Override
    @OnClick({R.id.btn_add_car, R.id.btn_start_evaluate})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_car:
                handleAddCarClicked();
                break;
            case R.id.btn_start_evaluate:
                handleEvaluateClicked();
                break;
        }
    }

    private void handleAddCarClicked(){
        requestData();
    }

    private void handleEvaluateClicked(){
        requestCarEvaluate();
    }

    @Override
    public void onRequestSuccess(Message msg) {
        switch (msg.what) {
            case REQUEST_CODE_ADD_MY_CAR:
                handleRequesAddMyCarSuccess(msg);
                break;
            case REQUEST_CODE_EVALUATE_CAR:
                handleRequestEvaluateMyCarSuccess(msg);
                break;
        }
    }

    @Override
    public void onRequestFault(Message msg) {
        switch (msg.what) {
            case REQUEST_CODE_ADD_MY_CAR:
                handleRequesAddMyCarFailed(msg);
                break;
            case REQUEST_CODE_EVALUATE_CAR:
                handleRequestEvaluateMyCarFailed(msg);
                break;
        }
    }

    private void handleRequesAddMyCarSuccess(Message msg){
        hideWaitingDialog();
        mRequestResult = (RequestCarManagerAddMyCarResult)msg.obj;
        if (mRequestResult != null) {
            if (mRequestResult.getStatus() == CommonModelSettings.STATUS_SUCCESS) {
                ConstantForAct.saveCarManagerId(this, mRequestResult.getCarMangerId());
                GlobalData.getInstance().setCarManagerId(mRequestResult.getCarMangerId());
                EventBus.getDefault().post(new AddMyCarEvent());
                this.finish();
            }else if (mRequestResult.getStatus() == CommonModelSettings.STATUS_MY_CAR_HAS_EXIST){
                ToastManager.getInstance().showToastCenter(this, R.string.car_manager_my_car_has_exist);
            } else {
                ToastManager.getInstance().showToastCenter(this, R.string.main_tip_operate_failed);
            }
        }else{
            ToastManager.getInstance().showToastCenter(this, R.string.error_net);
        }
    }

    private void handleRequesAddMyCarFailed(Message msg){
        hideWaitingDialog();
        ToastManager.getInstance().showToastCenter(this, R.string.error_net);
    }

    private void requestData(){
        if (mCarInfoView.isDataReady()) {
            mCityId = mCarInfoView.getCityId();
            mCityName = mCarInfoView.getCityName();
            mSytleId = String.valueOf(mCarInfoView.getCarStyle().getId());
            mMileage = mCarInfoView.getMileage();
            mRegisterDate = mCarInfoView.getRegisterDate();

            mRequestParams.uid = "0";//test code
            mRequestParams.ButlerId = GlobalData.getInstance().getCarManagerId();
            mRequestParams.styleId = mSytleId;
            mRequestParams.CityId = mCityId;
            mRequestParams.cityname = mCityName;
            mRequestParams.mileage = mMileage;
            mRequestParams.Regdate = mRegisterDate;
            if (AppContext.isLogin()) {
                mRequestParams.uid = AppContext.mLoginResultModels.getId();
            }
            showWaitingDialog(this.getResources().getString(R.string.main_tip_operate_waiting));
            mDataService.toRequestAddMyCar(mRequestParams, RequestCarManagerAddMyCarResult.class, REQUEST_CODE_ADD_MY_CAR);
        }
    }

    private void requestCarEvaluate(){
        if (mCarInfoView.isDataReady()) {
            mCityId = mCarInfoView.getCityId();
            mCityName = mCarInfoView.getCityName();
            mSytleId = String.valueOf(mCarInfoView.getCarStyle().getId());
            mMileage = mCarInfoView.getMileage();
            mRegisterDate = mCarInfoView.getRegisterDate();

            ValuationSellCarParams sellCarParams = new ValuationSellCarParams();
            ValuationService dataService = new ValuationService(this, this);
            ;
            if (AppContext.isLogin()) {
                sellCarParams.uid = AppContext.mLoginResultModels.getId();
            } else {
                sellCarParams.uid = "0";
            }
            sellCarParams.styleid = mSytleId;
            sellCarParams.regdate = mRegisterDate;
            sellCarParams.mileage = mMileage;
            sellCarParams.cityname = mCityName;
            sellCarParams.CityId = mCityId;
            showWaitingDialog(this.getResources().getString(R.string.main_tip_operate_waiting));
            dataService.toResuestValuationSellCar(sellCarParams, ValuationSellCarResult.class, REQUEST_CODE_EVALUATE_CAR);
        }
    }

    private void handleRequestEvaluateMyCarFailed(Message msg){
        hideWaitingDialog();
        ToastManager.getInstance().showToastCenter(this, R.string.error_net);
    }

    private void handleRequestEvaluateMyCarSuccess(Message msg){
        hideWaitingDialog();
        ValuationSellCarResult requestResult = (ValuationSellCarResult) msg.obj;
        if (requestResult != null && requestResult.getStatus() == CommonModelSettings.STATUS_SUCCESS) {
            ViewUtility.navigateToValuationSellActivity(this, requestResult);
        }
    }
}
