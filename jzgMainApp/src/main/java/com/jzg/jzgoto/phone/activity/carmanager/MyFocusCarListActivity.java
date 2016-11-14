package com.jzg.jzgoto.phone.activity.carmanager;


import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.activity.common.ConstantForAct;
import com.jzg.jzgoto.phone.activity.repalcecar.ReplaceChooseCarBrandActivity;
import com.jzg.jzgoto.phone.activity.shared.BaseActivity;
import com.jzg.jzgoto.phone.adapter.carmanager.MyFocusCarListAdpater1;
import com.jzg.jzgoto.phone.event.CarChooseEvent;
import com.jzg.jzgoto.phone.event.DeleteMyFocusCarEvent;
import com.jzg.jzgoto.phone.event.RefreshMyCarEvent;
import com.jzg.jzgoto.phone.global.AppContext;
import com.jzg.jzgoto.phone.global.GlobalData;
import com.jzg.jzgoto.phone.models.business.carmanager.FocusCarData;
import com.jzg.jzgoto.phone.models.business.carmanager.RequestCarManagerAddFocusCarParams;
import com.jzg.jzgoto.phone.models.business.carmanager.RequestCarManagerAddFocusCarResult;
import com.jzg.jzgoto.phone.models.business.carmanager.RequestCarManagerDeleteFocusCarParams;
import com.jzg.jzgoto.phone.models.business.carmanager.RequestCarManagerDeleteFocusCarResult;
import com.jzg.jzgoto.phone.models.business.carmanager.RequestCarManagerGetFocusCarListParams;
import com.jzg.jzgoto.phone.models.business.carmanager.RequestCarManagerGetFocusCarListResult;
import com.jzg.jzgoto.phone.models.business.carmanager.SaveConcernCarOrderParams;
import com.jzg.jzgoto.phone.models.business.carmanager.SaveConcernCarOrderResult;
import com.jzg.jzgoto.phone.models.common.CommonModelSettings;
import com.jzg.jzgoto.phone.services.business.carmanager.CarManagerService;
import com.jzg.jzgoto.phone.tools.ToastManager;
import com.jzg.jzgoto.phone.view.sellcar.CustomListView;
import com.jzg.pricechange.phone.JzgCarChooseStyle;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;


public class MyFocusCarListActivity extends BaseActivity implements View.OnClickListener{

    private static final int REQUEST_CODE_GET_MY_FOCUS_CAR_LIST = 1;
    private static final int REQUEST_CODE_DELETE_MY_FOCUS_CAR = 2;
    private static final int REQUEST_CODE_ADD_MY_FOCUS_CAR = 3;
    private static final int REQUEST_SAVE_CONCERNCAR_ORDER = 4;
    private static final int REQUEST_CODE_GET_CAR_BRAND = 5;

    @Bind(R.id.btn_save)
    View mSaveCarBtn;
    @Bind(R.id.btn_add_car)
    View mAddCarBtn;
    @Bind(R.id.my_car_list_view)
    CustomListView mFocusCarListView;
    @Bind(R.id.car_empty_tip_textview)
    TextView mFocusCarEmptyTipTextView;
    @Bind(R.id.view_car_list_container)
    View mCarListContainer;

    private List<FocusCarData> mCarDataList = new ArrayList<FocusCarData>();
    private MyFocusCarListAdpater1 mCarListAdapter;
    private FocusCarData mDeleteFocusCarData;
    private CarManagerService mDataService;
    private RequestCarManagerGetFocusCarListParams mRequestParams;
    private RequestCarManagerGetFocusCarListResult mRequestResult;
    private JzgCarChooseStyle mCarChooseStyle;
    private boolean mFocusCarChanged = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_my_focus_car_list_layout);
        ButterKnife.bind(this);
        initViews();
        initListtener();
        mDataService = new CarManagerService(this, this);
        mRequestParams = new RequestCarManagerGetFocusCarListParams();
        requestMyFocusCarListData();
    }

    private void initListtener() {
        mCarListAdapter.setOnListener(new MyFocusCarListAdpater1.Listener() {
            @Override
            public void onGrab(int position, LinearLayout row) {
                mFocusCarListView.onGrab(position, row);
            }
        });

        mFocusCarListView.setListener(new CustomListView.Listener() {
            @Override
            public void swapElements(int indexOne, int indexTwo) {
                FocusCarData temp = mCarListAdapter.getmCarDataList().get(indexOne);
                mCarListAdapter.getmCarDataList().set(indexOne, mCarListAdapter.getmCarDataList().get(indexTwo));
                mCarListAdapter.getmCarDataList().set(indexTwo, temp);
            }
        });
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    private void initViews() {
        mCarListAdapter = new MyFocusCarListAdpater1(this);
        mFocusCarListView.setAdapter(mCarListAdapter);
    }

    @Override
    protected void onRefreshData() {
        requestMyFocusCarListData();
    }

    @Override
    public void onRequestSuccess(Message msg) {
        switch (msg.what) {
            case REQUEST_CODE_GET_MY_FOCUS_CAR_LIST:
                handleRequestGetMyFocusCarListSuccess(msg);
                break;
            case REQUEST_CODE_DELETE_MY_FOCUS_CAR:
                handleRequestRemoveFocusCarSuccess(msg);
                break;
            case REQUEST_CODE_ADD_MY_FOCUS_CAR:
                handleRequestAddFocusCarSuccess(msg);
                break;
            case REQUEST_SAVE_CONCERNCAR_ORDER:
                hideWaitingDialog();
                SaveConcernCarOrderResult saveConcernCarOrderResult = (SaveConcernCarOrderResult) msg.obj;
                if(saveConcernCarOrderResult.getStatus() == CommonModelSettings.STATUS_SUCCESS){
                    EventBus.getDefault().post(new RefreshMyCarEvent());
                    finish();
                }
                break;
        }
    }

    @Override
    public void onRequestFault(Message msg) {
        switch (msg.what) {
            case REQUEST_CODE_GET_MY_FOCUS_CAR_LIST:
                handleRequestGetMyFocusCarListFailed(msg);
                break;
            case REQUEST_CODE_DELETE_MY_FOCUS_CAR:
                handleRequestRemoveFocusCarFailed(msg);
                break;
            case REQUEST_CODE_ADD_MY_FOCUS_CAR:
                handleRequestAddFocusCarFailed(msg);
                break;
            case REQUEST_SAVE_CONCERNCAR_ORDER:
                hideWaitingDialog();
                ToastManager.getInstance().showToastCenter(this, R.string.error_net);
                break;
        }
    }

    public void onEvent(DeleteMyFocusCarEvent event) {
        if (event != null && event.carInfo != null) {
            mDeleteFocusCarData = event.carInfo;
            requestDeleteMyFocusCar(mDeleteFocusCarData);
        }
    }

    public void onEvent(CarChooseEvent event) {
        if (event == null) {
            return;
        }
//        mCarChooseStyle = event.carInfo;
//        if (mCarChooseStyle != null){
//            requestAddMyFocusCar();
//        }
    }

    protected void showDataEmptyView(){
        mCarListContainer.setVisibility(View.GONE);
        mFocusCarEmptyTipTextView.setVisibility(View.VISIBLE);
        mSaveCarBtn.setVisibility(View.GONE);
    }

    @Override
    @OnClick({R.id.btn_save, R.id.btn_add_car})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_save:
                handleSaveClicked();
                break;
            case R.id.btn_add_car:
                handleAddFocusCarClicked();
                break;
        }
    }

    private void handleSaveClicked(){
        if (mCarDataList == null || mCarDataList.size() == 0){
            return;
        }
        String strId = "";
        for (int i = 0; i < mCarListAdapter.getmCarDataList().size(); i++) {
            strId = strId + mCarListAdapter.getmCarDataList().get(i).getFocusCarId()+"," + i;
            if (i < mCarListAdapter.getmCarDataList().size() - 1){
                strId += "|";
            }
        }
        showWaitingDialog(this.getResources().getString(R.string.main_tip_operate_waiting));
        SaveConcernCarOrderParams requestParams = new SaveConcernCarOrderParams();
        requestParams.MyCareListStr = strId;
        mDataService.toRequestSortFocusCar(requestParams, SaveConcernCarOrderResult.class,REQUEST_SAVE_CONCERNCAR_ORDER);
    }

    private void handleAddFocusCarClicked(){
        selectCarStyle();
    }

    private void requestMyFocusCarListData(){
        //execTest();
        showLoadingView();
        mRequestParams.uid = "0";
        mRequestParams.ButlerId = GlobalData.getInstance().getCarManagerId();
        if (AppContext.isLogin()) {
            mRequestParams.uid = AppContext.mLoginResultModels.getId();
        }
        mDataService.toRequestFocusCarList(mRequestParams, RequestCarManagerGetFocusCarListResult.class, REQUEST_CODE_GET_MY_FOCUS_CAR_LIST);
    }

    private void requestDeleteMyFocusCar(FocusCarData carData){
        showWaitingDialog(this.getResources().getString(R.string.main_tip_operate_waiting));
        RequestCarManagerDeleteFocusCarParams requestParams = new RequestCarManagerDeleteFocusCarParams();
        requestParams.uid = "0";
        requestParams.ButlerId = GlobalData.getInstance().getCarManagerId();
        requestParams.MyCareCarId = carData.getFocusCarId();
        if (AppContext.isLogin()) {
            requestParams.uid = AppContext.mLoginResultModels.getId();
        }
        mDataService.toRequestDeleteFocusCar(requestParams, RequestCarManagerDeleteFocusCarResult.class, REQUEST_CODE_DELETE_MY_FOCUS_CAR);
    }

    private void requestAddMyFocusCar(){
        if (mCarChooseStyle == null){
            return;
        }
        showWaitingDialog(this.getResources().getString(R.string.main_tip_operate_waiting));
        RequestCarManagerAddFocusCarParams requestParams = new RequestCarManagerAddFocusCarParams();
        requestParams.uid = "0";
        requestParams.ButlerId = GlobalData.getInstance().getCarManagerId();
        requestParams.MakeId = String.valueOf(mCarChooseStyle.getBrandId());
        requestParams.ModelId = String.valueOf(mCarChooseStyle.getModeId());
        if (AppContext.isLogin()) {
            requestParams.uid = AppContext.mLoginResultModels.getId();
        }
        mDataService.toRequestAddFocusCar(requestParams, RequestCarManagerAddFocusCarResult.class, REQUEST_CODE_ADD_MY_FOCUS_CAR);
    }

    private void handleRequestGetMyFocusCarListSuccess(Message msg){
        hideLoadingView();
        mRequestResult = (RequestCarManagerGetFocusCarListResult)msg.obj;
        if (mRequestResult != null && mRequestResult.getStatus() == CommonModelSettings.STATUS_SUCCESS) {
            mCarDataList = mRequestResult.getFocusCarList();
            mCarListAdapter.setCarDataList(mCarDataList);
            if (mCarDataList == null || mCarDataList.size() == 0){
                showDataEmptyView();
            }else{
                mCarListContainer.setVisibility(View.VISIBLE);
                mFocusCarEmptyTipTextView.setVisibility(View.GONE);
                mSaveCarBtn.setVisibility(View.VISIBLE);
            }
        }else {
            showDataEmptyView();
        }
    }

    private void handleRequestGetMyFocusCarListFailed(Message msg){
        showDataEmptyView();
        ToastManager.getInstance().showToastCenter(this, R.string.error_net);
    }

    private void handleRequestRemoveFocusCarSuccess(Message msg){
        mFocusCarChanged = true;
        hideWaitingDialog();
        removeFocusCar(mDeleteFocusCarData);
        EventBus.getDefault().post(new RefreshMyCarEvent());
    }

    private void handleRequestRemoveFocusCarFailed(Message msg){
        hideWaitingDialog();
        ToastManager.getInstance().showToastCenter(this, R.string.error_net);
    }

    private void handleRequestAddFocusCarSuccess(Message msg){
        mFocusCarChanged = true;
        hideWaitingDialog();
        RequestCarManagerAddFocusCarResult requestResult = (RequestCarManagerAddFocusCarResult)msg.obj;
        if (requestResult != null) {
            if (requestResult.getStatus() == CommonModelSettings.STATUS_SUCCESS) {
                ConstantForAct.saveCarManagerId(this, requestResult.getCarManagerId());
                GlobalData.getInstance().setCarManagerId(requestResult.getCarManagerId());
                requestMyFocusCarListData();
                EventBus.getDefault().post(new RefreshMyCarEvent());
            }else if (requestResult.getStatus() == CommonModelSettings.STATUS_CAR_HAS_FOCUSED) {
                ToastManager.getInstance().showToastCenter(this, R.string.car_manager_focus_car_has_exist);
            }else{
                ToastManager.getInstance().showToastCenter(this, R.string.main_tip_operate_failed);
            }
        }else{
            ToastManager.getInstance().showToastCenter(this, R.string.error_net);
        }
    }

    private void handleRequestAddFocusCarFailed(Message msg){
        hideWaitingDialog();
        ToastManager.getInstance().showToastCenter(this, R.string.error_net);
    }

    private void removeFocusCar(FocusCarData carData){
        if (carData != null && mCarDataList != null){
            mCarDataList.remove(carData);
            mCarListAdapter.setCarDataList(mCarDataList);
        }
        if (mCarDataList == null || mCarDataList.size() == 0){
            showDataEmptyView();
        }
    }

    private void selectCarStyle(){
        //ViewUtility.navigateToCarReleaseIndexValuationActivity(this);
        Intent intent1 = new Intent(MyFocusCarListActivity.this, ReplaceChooseCarBrandActivity.class);
        intent1.putExtra(ReplaceChooseCarBrandActivity.IS_CHOOSE_MODEL, "");
        startActivityForResult(intent1, REQUEST_CODE_GET_CAR_BRAND);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) return;
        switch (requestCode) {
            case REQUEST_CODE_GET_CAR_BRAND:
                if (data != null) {
                    JzgCarChooseStyle carBrandStyle = (JzgCarChooseStyle) data.getSerializableExtra("mQueryCarStyle");
                    mCarChooseStyle = carBrandStyle;
                    if (mCarChooseStyle != null){
                        requestAddMyFocusCar();
                    }
                }
                break;
        }
    }

    private void execTest() {
        try {
            AppContext.getHandler().postDelayed(new Runnable() {
                public void run() {
                    List<FocusCarData> myCarDataList = new ArrayList<FocusCarData>();
                    FocusCarData carData = null;
                    carData = new FocusCarData();
                    myCarDataList.add(carData);
                    carData = new FocusCarData();
                    myCarDataList.add(carData);
                    mCarListAdapter.setCarDataList(myCarDataList);
                }
            }, 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
