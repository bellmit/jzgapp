/**
 * Project Name:JZGPingGuShi
 * File Name:BaseActivity.java
 * Package Name:com.gc.jzgpinggushi.ui
 * Date:2014-9-3下午12:09:28
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.jzg.jzgoto.phone.activity.shared;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import cn.jpush.android.api.JPushInterface;

import com.google.android.gms.common.api.GoogleApiClient;
import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.constant.Constant;
import com.jzg.jzgoto.phone.global.AppManager;
import com.jzg.jzgoto.phone.global.AppContext;
import com.jzg.jzgoto.phone.tools.InfoDialogManager;
import com.jzg.jzgoto.phone.tools.ShowDialogTool;
import com.jzg.jzgoto.phone.services.common.OnRequestFinishListener;
import com.jzg.jzgoto.phone.tools.ToastManager;
import com.jzg.jzgoto.phone.view.error.BaseErrorView;
import com.jzg.jzgoto.phone.view.error.DefaultErrorView;

/**
 * ClassName:BaseActivity <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-9-3 下午12:09:28 <br/>
 *
 * @see
 * @since JDK 1.6
 */
public class BaseActivity extends FragmentActivity implements
		OnRequestFinishListener {

	public AppContext appContext;
	public static final int SUCCESS = 100;// 成功
	protected BaseErrorView errorView;
	protected BaseErrorView.ErrorType errorType;
	private Dialog mWaitingDialog = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		appContext = (AppContext) getApplicationContext();

		// 添加Activity到堆栈
		AppManager.getAppManager().addActivity(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		JPushInterface.onResume(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		JPushInterface.onPause(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// 结束Activity&从堆栈中移除
		AppManager.getAppManager().finishActivity(this);
	}

	/**
	 * 请求成功调用
	 */
	@Override
	public void onRequestSuccess(Message msg) {
		dismissLoadingDialog();
	}

	/**
	 * 请求失败调用.Message 包含错误信息等.具体什么原因的错误，前台不用关心。
	 */
	@Override
	public void onRequestFault(Message msg) {
		dismissLoadingDialog();
	}

	/**
	 * 弹出Toast提示。
	 *
	 * @param msg 需要显示的文字
	 */
	public void showMsgToast(String msg) {
		if (msg == null)
			msg = "";
//		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
		ShowDialogTool.showCenterToast(this, msg);
	}

	public void toShowLoadingDialog() {
		ShowDialogTool.showLoadingDialog(BaseActivity.this);
	}

	public void dismissLoadingDialog() {
		if (BaseActivity.this != null)
			ShowDialogTool.dismissLoadingDialog();
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		if (ev.getAction() == MotionEvent.ACTION_DOWN) {
			View v = getCurrentFocus();
			if (isShouldHideInput(v, ev)) {

				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				if (imm != null) {
					imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
				}
			}
			return super.dispatchTouchEvent(ev);
		}
		if (getWindow().superDispatchTouchEvent(ev)) {
			return true;
		}
		return onTouchEvent(ev);
	}

	public boolean isShouldHideInput(View v, MotionEvent event) {
		if (v != null && (v instanceof EditText)) {
			int[] leftTop = {0, 0};
			v.getLocationInWindow(leftTop);
			int left = leftTop[0];
			int top = leftTop[1];
			int bottom = top + v.getHeight();
			int right = left + v.getWidth();
			if (event.getX() > left && event.getX() < right
					&& event.getY() > top && event.getY() < bottom) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}

	protected void onRefreshData() {

	}

	protected void showLoadingView(){
		setupErrorView(BaseErrorView.ErrorType.Loading);
	}

	protected void hideLoadingView(){
		hideErrorView();
	}

	protected void showNetworkErrorView(){
		setupErrorView(BaseErrorView.ErrorType.NetworkNotAvailable);
	}

	protected void showDataEmptyView(){
		setupErrorView(BaseErrorView.ErrorType.NoData);
	}

	public void showToast(int message) {
		ToastManager.getInstance().showToastCenter(this, message, ToastManager.TOAST_TYPE.DEFAULT);
	}
	public void showToast(String message) {
		ToastManager.getInstance().showToastCenter(this, message, ToastManager.TOAST_TYPE.DEFAULT);
	}

	public void setupErrorView(ViewGroup.LayoutParams layoutParams) {
		if (this.errorView != null) {
			this.errorView.setLayoutParams(layoutParams);
		}
	}

	public void setupErrorView(ViewGroup rootView) {
		setupErrorView(rootView, null);
	}

	protected void createErrorView(Context context) {
		this.errorView = new DefaultErrorView(context);
	}

	protected void setupErrorView(ViewGroup rootView, ViewGroup.LayoutParams layoutParams) {
		createErrorView(rootView.getContext());
		this.errorView.setErrorListener(new BaseErrorView.ErrorListener() {
			@Override
			public void OnErrorRefresh() {
				setupErrorView(BaseErrorView.ErrorType.Loading);
				onRefreshData();
			}
		});
		if (layoutParams == null) {
			if (rootView instanceof LinearLayout) {
				int index = rootView.getChildCount();
				LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
						ViewGroup.LayoutParams.MATCH_PARENT);
				params.gravity = Gravity.CENTER;
				params.topMargin = getErrorViewTopMargin();
				rootView.addView(errorView, index, params);
			} else if (rootView instanceof RelativeLayout) {
				RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
						ViewGroup.LayoutParams.MATCH_PARENT);
				params.topMargin = getErrorViewTopMargin();
				rootView.addView(errorView, params);
			} else if (rootView instanceof FrameLayout) {
				FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
						ViewGroup.LayoutParams.MATCH_PARENT);
				params.topMargin = getErrorViewTopMargin();
				rootView.addView(errorView, params);
			}
		} else {
			rootView.addView(errorView, layoutParams);
		}
	}

	protected int getErrorViewTopMargin(){
		return getResources().getDimensionPixelSize(R.dimen.view_shared_headbar_height);
	}

	public void setupErrorView(BaseErrorView.ErrorType type) {
		if (this.errorView != null) {
			this.errorType = type;
			this.errorView.setVisibility(View.VISIBLE);
			this.errorView.setErrorType(this.errorType);
		}
	}

	public void setupErrorView(BaseErrorView.ErrorType type, int imageResId, int textResId) {
		setEmptyErrorView(imageResId, textResId);
		if (this.errorView != null) {
			this.errorType = type;
			this.errorView.setVisibility(View.VISIBLE);
			this.errorView.setErrorType(this.errorType);
		}
	}

	public void hideErrorView() {
		if (this.errorView != null) {
			this.errorView.setVisibility(View.GONE);
		}
	}

	protected void setEmptyErrorView(int imageResId, int textResId) {
		if (this.errorView != null) {
			this.errorView.setEmptyDataImageResId(imageResId);
			this.errorView.setEmptyDataResId(textResId);
		}
	}

	protected void showWaitingDialog(String msg){
		mWaitingDialog = InfoDialogManager.getInstance(this).showDialog(true, msg);
	}

	protected void hideWaitingDialog(){
		if (mWaitingDialog != null && mWaitingDialog.isShowing()) {
			mWaitingDialog.dismiss();
		}
	}
}
