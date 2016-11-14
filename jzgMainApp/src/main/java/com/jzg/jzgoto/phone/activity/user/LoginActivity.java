package com.jzg.jzgoto.phone.activity.user;

import java.util.regex.Pattern;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;
import de.greenrobot.event.EventBus;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.activity.common.ConstantForAct;
import com.jzg.jzgoto.phone.activity.shared.BaseActivity;
import com.jzg.jzgoto.phone.event.LoginEvent;
import com.jzg.jzgoto.phone.global.AppContext;
import com.jzg.jzgoto.phone.global.GlobalData;
import com.jzg.jzgoto.phone.models.business.login.GetAutoCodeParamsModels;
import com.jzg.jzgoto.phone.models.business.login.GetAutoCodeResultModels;
import com.jzg.jzgoto.phone.models.business.login.LoginParamsModels;
import com.jzg.jzgoto.phone.models.business.login.LoginResultModels;
import com.jzg.jzgoto.phone.models.business.valuation.sell.RequestSynValHistoryParams;
import com.jzg.jzgoto.phone.models.business.valuation.sell.RequestSysValHistoryResult;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;
import com.jzg.jzgoto.phone.services.business.login.LoginService;
import com.jzg.jzgoto.phone.services.business.valuation.ValuationService;
import com.jzg.jzgoto.phone.tools.ClickControlTool;
import com.jzg.jzgoto.phone.tools.CountClickTool;
import com.jzg.jzgoto.phone.tools.ShowDialogTool;
import com.jzg.jzgoto.phone.tools.ValHistoryCacheUtils;


public class LoginActivity extends BaseActivity {

	@Bind(R.id.et_phone_number)
	EditText mLoginName;
	@Bind(R.id.et_password)
	EditText mPassword;
	@Bind(R.id.tv_login_get_auto_code)
	TextView mTvGetAutoCode;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_login);
		ButterKnife.bind(this);
		initListener();
	}

	public void onSubmitAll(View view) {
		String phone = mLoginName.getText().toString();
		if(TextUtils.isEmpty(phone)){
			ShowDialogTool.showCenterToast(LoginActivity.this, "手机号码不能为空!");
			return;
		}
		if(!Pattern.matches("^[1][34578][0-9]{1}[0-9]{8}$", phone)){
			ShowDialogTool.showCenterToast(LoginActivity.this, "手机格式不正确!");
			return;
		}
		String autoCode = mPassword.getText().toString();
		if(TextUtils.isEmpty(autoCode)){
			ShowDialogTool.showCenterToast(LoginActivity.this, "验证码不能为空!");
			return;
		}

		toShowLoadingDialog();
		LoginParamsModels params = new LoginParamsModels();
		params.mLoginName = phone;
		params.validCodes = autoCode;
		params.CarButlerId = ConstantForAct.getCarManagerId(this);
		new LoginService(LoginActivity.this, LoginActivity.this).toRequestNet(params, 
				LoginResultModels.class, TO_LOGIN);
	}

	private void toUpLoadValHistoryList(){
		RequestSynValHistoryParams params = new RequestSynValHistoryParams();
		if(AppContext.isLogin()){
			params.uid = AppContext.mLoginResultModels.getId();
			params.historylist = ValHistoryCacheUtils.getLogionUploadSting(
					ValHistoryCacheUtils.getValHistoryList(this,"没有登录"));
			if(!TextUtils.isEmpty(params.historylist)){
				toShowLoadingDialog();
				new ValuationService(this, this).toResuestSynValHistory(
						params, RequestSysValHistoryResult.class,R.id.request_syn_val_history);
			}else{
				LoginActivity.this.finish();
			}
		}else{
			LoginActivity.this.finish();
		}
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
					publishProgress("重新发送 " + i + "秒");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				return "重新发送";
			}
			@Override
			protected void onPostExecute(String result) {
				super.onPostExecute(result);
				tv.setText("重新发送");
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

	public void initListener() {
		View.OnClickListener listener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(!ClickControlTool.isCanToClick()){
					return;
				}
				// TODO 获取验证码
				mAutoPhoneNum = null;
				mAutoCodeFromNet = null;
				String phone = mLoginName.getText().toString();
				if(TextUtils.isEmpty(phone)){
					ShowDialogTool.showCenterToast(LoginActivity.this, "手机号码不能为空!");
					return;
				}
				if(phone.length() != 11){
					ShowDialogTool.showCenterToast(LoginActivity.this, "手机号码不正确!");
					return;
				}
				
				if(!Pattern.matches("^[1][34578][0-9]{1}[0-9]{8}$", phone)){
					ShowDialogTool.showCenterToast(LoginActivity.this, "手机格式不正确!");
					return;
				}
				mTvGetAutoCode.setClickable(false);
				mAutoPhoneNum = phone;
				toShowLoadingDialog();
				GetAutoCodeParamsModels params = new GetAutoCodeParamsModels();
				params.mMobile = phone;
				new LoginService(LoginActivity.this, LoginActivity.this).toRequestNet(params, 
						GetAutoCodeResultModels.class, TO_GET_AUTO);
			}
		};
		mTvGetAutoCode.setOnClickListener(listener);
		mLoginName.addTextChangedListener(mPhoneEditorTexWatcher);
	}

	private <T extends BaseResultModels> String getStringFromObj(T obj){
		if(obj == null)
			return null;
		Gson g = new Gson();
		return g.toJson(obj);
	}
	/**
	 * 极光推送设置别名
	 * @param alias
	 */
	private void toSetJPushAlias(String alias){
		JPushInterface.setAliasAndTags(getApplicationContext(),alias,null ,null);
	}
	
	private String mAutoPhoneNum = null;
	private String mAutoCodeFromNet = null;
	private final int TO_LOGIN = 0x1001;
	private final int TO_GET_AUTO = 0x1002;
	@Override
	public void onRequestSuccess(Message msg) {
		super.onRequestSuccess(msg);
		switch(msg.what){
		case TO_LOGIN:
			LoginResultModels result = (LoginResultModels) msg.obj;
			if(result.getStatus() == 100){
//				ShowDialogTool.showCenterToast(LoginActivity.this, "登录成功!");
				ConstantForAct.saveUserMobile(LoginActivity.this, mAutoPhoneNum);
				ConstantForAct.saveUserLoginState(LoginActivity.this, mAutoPhoneNum,true);
				ConstantForAct.saveUserMsg(LoginActivity.this, getStringFromObj(result));
				AppContext.mLoginResultModels = result.getGetPersonalInfo();
				if (result.getGetPersonalInfo() != null) {
					ConstantForAct.saveUserProfile(LoginActivity.this,result.getGetPersonalInfo() );
					ConstantForAct.saveUserId(LoginActivity.this, result.getGetPersonalInfo().getId());
					ConstantForAct.saveCarManagerId(LoginActivity.this, result.getGetPersonalInfo().getCarButlerId());
					GlobalData.getInstance().setCarManagerId(result.getGetPersonalInfo().getCarButlerId());
				}
				EventBus.getDefault().post(LoginEvent.build(true));

				String first = result.getIsFirstLogin();
				if(!TextUtils.isEmpty(first)){
					if(first.equals("1")){
						CountClickTool.onEvent(LoginActivity.this, "login_suc_first");
					}
				}else{
					CountClickTool.onEvent(LoginActivity.this, "login_suc");
				}
				//toSetJPushAlias(AppContext.mLoginResultModels.getId());
				toUpLoadValHistoryList();
			} else {
				if(!TextUtils.isEmpty(result.getMsg())){
					ShowDialogTool.showCenterToast(LoginActivity.this, result.getMsg());
				}else{
					ShowDialogTool.showCenterToast(LoginActivity.this, "登录失败!");
				}
				ConstantForAct.saveUserLoginState(LoginActivity.this, mAutoPhoneNum,false);
				AppContext.mLoginResultModels = null;
			}
			break;
		case R.id.request_syn_val_history:
			dismissLoadingDialog();
			ValHistoryCacheUtils.clearValHistoryCache(LoginActivity.this,"没有登录");
			LoginActivity.this.finish();
			break;
		case TO_GET_AUTO:
			mAutoCodeFromNet = null;
			GetAutoCodeResultModels autoCode = (GetAutoCodeResultModels) msg.obj;
			if(autoCode.getStatus() == 100){
				mAutoCodeFromNet = autoCode.getMobileCookie();
				toCountDown(mTvGetAutoCode);
//				ShowDialogTool.showCenterToast(LoginActivity.this, "获取验证码成功!");
			} else {
				if(mAsync != null && mAsync.getStatus() != AsyncTask.Status.FINISHED){
					mAsync.cancel(true);
				}
				mAutoPhoneNum = null;
				mTvGetAutoCode.setText("获取验证码");
				mTvGetAutoCode.setClickable(true);
				ShowDialogTool.showCenterToast(LoginActivity.this, "获取验证码失败!");
			}
			break;
		}
	}
	@Override
	public void onRequestFault(Message msg) {
		super.onRequestFault(msg);
		switch(msg.what){
		case TO_GET_AUTO:
			mAutoPhoneNum = null;
			mAutoCodeFromNet = null;
			mTvGetAutoCode.setClickable(true);
			break;
		case TO_LOGIN:
			break;
		}
		ShowDialogTool.showCenterToast(LoginActivity.this, "无法与服务器建立连接，请重试");
	}

	private TextWatcher mPhoneEditorTexWatcher = new TextWatcher() {

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			if (s.length() == 0) {
				return;
			}
			if (s.length() == 1 && !Pattern.matches("^[1]", s.toString())
					|| s.length() >= 2 && !Pattern.matches("^[1][34578][0-9]*", s.toString())
					|| s.length() == 11 && !Pattern.matches("^[1][34578][0-9]{1}[0-9]{8}$", s.toString())){
				ShowDialogTool.showCenterToast(LoginActivity.this, "手机格式不正确!");
				return;
			}
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		}

		@Override
		public void afterTextChanged(Editable s) {
		}
	};
}
