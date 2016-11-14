package com.jzg.jzgoto.phone.activity.sellcar;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.activity.common.ConstantForAct;
import com.jzg.jzgoto.phone.global.AppContext;
import com.jzg.jzgoto.phone.models.ValuationDetail;
import com.jzg.jzgoto.phone.models.business.login.GetAutoCodeParamsModels;
import com.jzg.jzgoto.phone.models.business.login.GetAutoCodeResultModels;
import com.jzg.jzgoto.phone.models.business.login.LoginParamsModels;
import com.jzg.jzgoto.phone.models.business.login.LoginResultModels;
import com.jzg.jzgoto.phone.models.business.sell.RequestLoanOrderIdParamsModels;
import com.jzg.jzgoto.phone.models.business.sell.RequestLoanOrderIdResultModels;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;
import com.jzg.jzgoto.phone.services.business.login.LoginService;
import com.jzg.jzgoto.phone.services.common.OnRequestFinishListener;
import com.jzg.jzgoto.phone.tools.ShowDialogTool;

/**
 * Created by jzg on 2016/9/22.
 * Function :
 */
public class SellCarLoanActivity extends Activity implements OnRequestFinishListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellcar_loan_layout);
        initView();
        initListener();
    }
    private LoginService mLoginService;
    public void initView() {
        mLoginService = new LoginService(this, this);
        mEditName = (EditText) findViewById(R.id.edit_loan_car_main_name);
        mEditPhoneNum = (EditText) findViewById(R.id.edit_loan_car_main_phonenum);
        mEditAutoCode = (EditText) findViewById(R.id.edit_loan_car_main_autocode);
        mTvGetAutoCode = (TextView) findViewById(R.id.tv_loan_car_main_get_autocode);
		/*
		if(getIntent().getSerializableExtra("valuationDetail") != null){
			mValuationDetail = (ValuationDetail) getIntent().getSerializableExtra("valuationDetail");
		}
		*/
        if(AppContext.isLogin()){
            mEditPhoneNum.setText(AppContext.mLoginResultModels.getMobile());
            mEditName.setText(AppContext.mLoginResultModels.getTrueName());
        }
    }
    /**
     * 估值结果
     */
    private ValuationDetail mValuationDetail = null;

    private EditText mEditName;
    private EditText mEditPhoneNum;
    private EditText mEditAutoCode;
    private TextView mTvGetAutoCode;

    public void onApplyAll(View view){
		/*
		Intent intent1 = new Intent(CarLoanActivity.this,ApplyLoanActivity.class);
		intent1.putExtra("name", "sss");
		intent1.putExtra("phone", "111");
		startActivity(intent1);
		CarLoanActivity.this.finish();
		if(true)return;
		*/
        String name = mEditName.getText().toString();
        if(TextUtils.isEmpty(name)){
            ShowDialogTool.showCenterToast(SellCarLoanActivity.this, "姓名不能为空!");
            return;
        }
        String phone = mEditPhoneNum.getText().toString();
        if(TextUtils.isEmpty(phone)){
            ShowDialogTool.showCenterToast(SellCarLoanActivity.this, "手机号码不能为空!");
            return;
        }

        String autoCode = mEditAutoCode.getText().toString();
        if(TextUtils.isEmpty(autoCode)){
            ShowDialogTool.showCenterToast(SellCarLoanActivity.this, "验证码不能为空!");
            return;
        }

        toGetLoanOrderId();
    }
    public void initListener() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.tv_loan_car_main_get_autocode:
                        mAutoPhoneNum = null;
                        mAutoCodeFromNet = null;
                        String phone = mEditPhoneNum.getText().toString();
                        if(TextUtils.isEmpty(phone)){
                            ShowDialogTool.showCenterToast(SellCarLoanActivity.this, "手机号码不能为空!");
                            return;
                        }
                        toCountDown(mTvGetAutoCode);
                        mAutoPhoneNum = phone;
                        ShowDialogTool.showLoadingDialog(SellCarLoanActivity.this);
                        GetAutoCodeParamsModels params = new GetAutoCodeParamsModels();
                        params.mMobile = phone;
                        mLoginService.toRequestNet(params,
                                GetAutoCodeResultModels.class, TO_GET_AUTO);
                        break;
                }
            }
        };
        if(!mTvGetAutoCode.isClickable()){
            mTvGetAutoCode.setClickable(true);
        }
        mTvGetAutoCode.setOnClickListener(listener);
    }
    private static AsyncTask<String, String, String> mAsync ;
    private void toCountDown(final TextView tv){
        if(mAsync != null && mAsync.getStatus() != AsyncTask.Status.FINISHED){
            mAsync.cancel(true);
            mAsync = null;
        }
        mAsync = new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                tv.setClickable(false);
            }
            @Override
            protected String doInBackground(String... params) {
                for(int i = 60;i>=0;i--){
                    if(isCancelled())break;
                    publishProgress(i + "秒");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return "获取验证码";
            }
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                tv.setText("获取验证码");
                tv.setClickable(true);
            }
            @Override
            protected void onProgressUpdate(String... values) {
                super.onProgressUpdate(values);
                if(TextUtils.isEmpty(values[0])){
                    return;
                }
                tv.setText(values[0]);
                tv.setClickable(false);
            }
        };
        mAsync.execute("do");
    }

    private void toLogin(){
        String phone = mEditPhoneNum.getText().toString();
        if(TextUtils.isEmpty(phone)){
            ShowDialogTool.showCenterToast(SellCarLoanActivity.this, "手机号码不能为空!");
            return;
        }
        // TODO 开始登录
        ShowDialogTool.showLoadingDialog(SellCarLoanActivity.this);
        LoginParamsModels params = new LoginParamsModels();
        params.mLoginName = phone;
        params.validCodes = mEditAutoCode.getText().toString().trim();
        mLoginService.toRequestNet(params,
                LoginResultModels.class, TO_LOGIN);
    }
    private <T extends BaseResultModels> String getStringFromObj(T obj){
        if(obj == null)
            return null;
        Gson g = new Gson();
        return g.toJson(obj);
    }
    private void toGetLoanOrderId(){
        RequestLoanOrderIdParamsModels params = new RequestLoanOrderIdParamsModels();
        final String phoneNum = mEditPhoneNum.getText().toString().trim();
        params.mTelNum = phoneNum;
        final String name = mEditName.getText().toString().trim();
        params.mUserName = name;
        params.validCodes = mEditAutoCode.getText().toString().trim();
		ShowDialogTool.showLoadingDialog(SellCarLoanActivity.this);
        mLoginService.toRequestNet(
                params, RequestLoanOrderIdResultModels.class,GET_LOAN_ORDER_ID);
    }
    private String mAutoPhoneNum = null;
    private String mAutoCodeFromNet = null;
    private final int TO_LOGIN = 0x1001;
    private final int TO_GET_AUTO = 0x1002;
    private final int GET_LOAN_ORDER_ID = 0x1004;
    @Override
    public void onRequestSuccess(Message msg) {
        ShowDialogTool.dismissLoadingDialog();
        switch(msg.what){
            case TO_LOGIN:
                LoginResultModels result = (LoginResultModels) msg.obj;
                if(result.getStatus() == 100){
//				ShowDialogTool.showCenterToast(CarLoanActivity.this, "登录成功!");
                    ConstantForAct.saveUserMobile(SellCarLoanActivity.this, mAutoPhoneNum);
                    ConstantForAct.saveUserLoginState(SellCarLoanActivity.this, mAutoPhoneNum,true);
                    ConstantForAct.saveUserMsg(SellCarLoanActivity.this, getStringFromObj(result));
                    AppContext.mLoginResultModels = result.getGetPersonalInfo();

                    toGetLoanOrderId();
                } else {
//				ShowDialogTool.showCenterToast(CarLoanActivity.this, "登录失败!");
                    ConstantForAct.saveUserLoginState(SellCarLoanActivity.this, mAutoPhoneNum,false);
                    AppContext.mLoginResultModels = null;
                }
                break;
            case TO_GET_AUTO:
                mAutoCodeFromNet = null;
                GetAutoCodeResultModels autoCode = (GetAutoCodeResultModels) msg.obj;
                if(autoCode.getStatus() == 100){
                    mAutoCodeFromNet = autoCode.getMobileCookie();
//				ShowDialogTool.showCenterToast(CarLoanActivity.this, "获取验证码成功!");
                } else {
                    if(mAsync != null && mAsync.getStatus() != AsyncTask.Status.FINISHED){
                        mAsync.cancel(true);
                    }
                    mAutoPhoneNum = null;
                    mTvGetAutoCode.setText("获取验证码");
                    mTvGetAutoCode.setClickable(true);
                    ShowDialogTool.showCenterToast(SellCarLoanActivity.this, autoCode.getMsg());
                }
                break;
            case GET_LOAN_ORDER_ID:
                RequestLoanOrderIdResultModels resultL = (RequestLoanOrderIdResultModels) msg.obj;
                if(resultL.getStatus() == 100){
                    final String orderId = resultL.getOrderID();
                    String name = mEditName.getText().toString();
                    String phone = mEditPhoneNum.getText().toString();
                    Intent intent = new Intent(SellCarLoanActivity.this,SellCarLoanApplyActivity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("phone", phone);
                    intent.putExtra("orderId", orderId);
                    if(mValuationDetail != null)
                        intent.putExtra("valuationDetail", mValuationDetail);

                    startActivity(intent);
                    SellCarLoanActivity.this.finish();
                } else {
                    ShowDialogTool.showCenterToast(SellCarLoanActivity.this, resultL.getMsg());
                }
                break;
        }
    }
    @Override
    public void onRequestFault(Message msg) {
        ShowDialogTool.dismissLoadingDialog();
        switch(msg.what){
            case TO_GET_AUTO:
                mAutoPhoneNum = null;
                mAutoCodeFromNet = null;
            case TO_LOGIN:
            case GET_LOAN_ORDER_ID:
                ShowDialogTool.showCenterToast(SellCarLoanActivity.this, "无法与服务器建立连接，请重试。");
                break;
        }
    }
}
