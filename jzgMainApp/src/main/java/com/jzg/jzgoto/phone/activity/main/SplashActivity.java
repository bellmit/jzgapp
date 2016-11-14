package com.jzg.jzgoto.phone.activity.main;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.activity.common.ConstantForAct;
import com.jzg.jzgoto.phone.activity.shared.BaseActivity;
import com.jzg.jzgoto.phone.global.AppContext;
import com.jzg.jzgoto.phone.global.GlobalData;
import com.jzg.jzgoto.phone.global.Statistical;
import com.jzg.jzgoto.phone.models.business.login.LoginParamsModels;
import com.jzg.jzgoto.phone.models.business.login.LoginResultModels;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;
import com.jzg.jzgoto.phone.services.business.login.LoginService;
import com.jzg.jzgoto.phone.services.contentprovider.GetDataFromDBToolBase;
import com.jzg.jzgoto.phone.tools.AppUtils;
import com.jzg.jzgoto.phone.tools.CountClickTool;
import com.jzg.jzgoto.phone.view.ViewUtility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Description: 加载界面
 * @Package com.jzg.jzgoto.phone SplashActivity.java
 * @author gf
 * @date 2015-12-11 上午9:32:14
 */
public class SplashActivity extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_layout);
		mHandler.sendEmptyMessageDelayed(0, 100);
		initData();
		CountClickTool.onEvent(this, Statistical.KEY_APP_START_COUNT);
	}

	private void initData(){
		GlobalData.getInstance().setCarManagerId(ConstantForAct.getCarManagerId(this));
		GlobalData.getInstance().setUserId(ConstantForAct.getUserId(this));
		AppContext.mLoginResultModels = ConstantForAct.getUserProfile(this);
	}

	private boolean isShowGuide(){
		boolean isFirstIn = false;
		String oldVersionName = ConstantForAct.getAppVersionName(this);
		String newVersionName = AppUtils.getVersionName(this);
		if (!TextUtils.isEmpty(newVersionName)) {
			if (TextUtils.isEmpty(oldVersionName) || !oldVersionName.equals(newVersionName)) {
				isFirstIn = true;
				ConstantForAct.setAppVersionName(this, newVersionName);
				return true;
			}
		}
		//onCreate中
		SharedPreferences preferences = getSharedPreferences("first_in",
		    MODE_PRIVATE);
		isFirstIn = preferences.getBoolean("isFirstIn", true);
		return isFirstIn ;
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onResume() {
		super.onResume();
		toInitDb();
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK)return true;
		return super.onKeyDown(keyCode, event);
	}
	private long mStartTime = 0L;
	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch(msg.what){
			case 0:
				mStartTime = System.currentTimeMillis();
				String mobile = ConstantForAct.getUserMobile(SplashActivity.this);
				if(TextUtils.isEmpty(mobile)){
					mHandler.sendEmptyMessageDelayed(2, 2000);
					return;
				}
				if(ConstantForAct.getUserLogin(SplashActivity.this, mobile)){
					toLogin(mobile);
				} else {
					mHandler.sendEmptyMessageDelayed(2, 2000);
				}
				break;
			case 1:
				if(isShowGuide()){
					ViewUtility.navigateToWelcomeActivity(SplashActivity.this);
				}else{
					ViewUtility.navigateToHomeActivity(SplashActivity.this);

				}
				finish();
				break;
			case 2:
				AppContext.mLoginResultModels = null;
				if(isShowGuide()){
					ViewUtility.navigateToWelcomeActivity(SplashActivity.this);
				}else{
					ViewUtility.navigateToHomeActivity(SplashActivity.this);
//					Intent intent = new Intent(SplashActivity.this,MyFocusCarListActivity.class);
//					Intent intent = new Intent(SplashActivity.this,SellCarOneKeyActivity.class);
//					startActivity(intent);
				}
				finish();
				break;
			}
		};
	};
	private void toLogin(String mobile){
		toShowLoadingDialog();
		LoginParamsModels params = new LoginParamsModels();
		params.mLoginName = mobile;
		new LoginService(SplashActivity.this, SplashActivity.this).toRequestNet(params, 
				LoginResultModels.class, TO_LOGIN);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	private final int TO_LOGIN = 0x1001;
	private <T extends BaseResultModels> String getStringFromObj(T obj){
		if(obj == null)
			return null;
		Gson g = new Gson();
		return g.toJson(obj);
	}
	@Override
	public void onRequestSuccess(Message msg) {
		super.onRequestSuccess(msg);
		switch(msg.what){
		case TO_LOGIN:
			LoginResultModels result = (LoginResultModels) msg.obj;
			if( result.getStatus() == 100){
//				ShowDialogTool.showCenterToast(SplashActivity.this, "登录成功!");
				ConstantForAct.saveUserLoginState(SplashActivity.this, ConstantForAct.getUserMobile(SplashActivity.this),true);
				ConstantForAct.saveUserMsg(SplashActivity.this, getStringFromObj(result));
				AppContext.mLoginResultModels = result.getGetPersonalInfo();
			} else {
				//ConstantForAct.saveUserLoginState(SplashActivity.this, ConstantForAct.getUserMobile(SplashActivity.this),false);
//				ShowDialogTool.showCenterToast(SplashActivity.this, "登录失败!");
				AppContext.mLoginResultModels = null;
			}
			break;
		}
		long endTime = System.currentTimeMillis();
		if((endTime-mStartTime) > 2000){
			mHandler.sendEmptyMessage(1);
		} else {
			mHandler.sendEmptyMessageDelayed(1, 2000-(endTime-mStartTime));
		}
	}
	@Override
	public void onRequestFault(Message msg) {
		super.onRequestFault(msg);
		switch(msg.what){
		case TO_LOGIN:
//			ShowDialogTool.showCenterToast(SplashActivity.this, "无法与服务器建立连接，请重试");
			break;
		}
		long endTime = System.currentTimeMillis();
		if((endTime-mStartTime) > 2000){
			mHandler.sendEmptyMessage(1);
		} else {
			mHandler.sendEmptyMessageDelayed(1, 2000-(endTime-mStartTime));
		}
	}
	private void toInitDb(){
		final String dbPath = GetDataFromDBToolBase.getSelfDatabaseName();
		if(TextUtils.isEmpty(dbPath))return;
		File f = getDatabasePath(dbPath);
		if(f.exists()){
			if(f.length() > 0){
				return;
			}
			f.delete();
		}
		
		if(!f.getParentFile().exists()){
			f.getParentFile().mkdirs();
		}
		
		try {
			f.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
			return;
		}
		try {
			String[] strs = getAssets().list("jzgdb");
			copyAssertToDb(strs,f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void copyAssertToDb(final String[] dbFiles,final File sdFile){
		if(!sdFile.exists())return;
		FileOutputStream output = null;
		try {
			output = new FileOutputStream(sdFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if( null == output)return;
		final byte[] buffer = new byte[1024];
		for(String file:dbFiles){
			try {
				InputStream input = getAssets().open("jzgdb/" + file);
				int length = 0;
				
				while((length = input.read(buffer) )!= -1){
					output.write(buffer, 0, length);
				}
				output.flush();
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
