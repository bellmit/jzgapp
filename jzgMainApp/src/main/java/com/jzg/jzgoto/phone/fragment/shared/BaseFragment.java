package com.jzg.jzgoto.phone.fragment.shared;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.global.AppContext;
import com.jzg.jzgoto.phone.services.common.OnRequestFinishListener;
import com.jzg.jzgoto.phone.tools.InfoDialogManager;
import com.jzg.jzgoto.phone.tools.ToastManager;
import com.jzg.jzgoto.phone.view.error.BaseErrorView;
import com.jzg.jzgoto.phone.view.error.BaseErrorView.ErrorType;
import com.jzg.jzgoto.phone.view.error.DefaultErrorView;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


/**
 * @Description: Fragment的父类
 * @Package com.jzg.jzgoto.phone.activity.common BaseFragment.java
 * @author gf
 * @date 2015-12-11 下午1:48:41
 */
public abstract class BaseFragment extends Fragment implements OnRequestFinishListener {

	public Context mAppContext;
    protected BaseErrorView errorView;
    protected ErrorType errorType;
    private Dialog mWaitingDialog = null;

	public BaseFragment() {
		super();
		mAppContext = AppContext.mAppContext;
	}


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	/**
	 * 请求成功调用
	 */
	@Override
	public void onRequestSuccess(Message msg) {
	}

	/**
	 * 请求失败调用.Message 包含错误信息等.具体什么原因的错误，前台不用关心。
	 */
	@Override
	public void onRequestFault(Message msg) {
	}

    protected void onRefreshData() {

    }

	protected void showLoadingView(){
        setupErrorView(ErrorType.Loading);
	}

	protected void hideLoadingView(){
        hideErrorView();
	}

    protected void showNetworkErrorView(){
        setupErrorView(ErrorType.NetworkNotAvailable);
    }

	protected void showDataEmptyView(){
        setupErrorView(ErrorType.NoData);
	}

	public void showToast(int message) {
		if (this.getActivity() == null) {
			return;
		}
		ToastManager.getInstance().showToastCenter(getActivity(), message, ToastManager.TOAST_TYPE.DEFAULT);
	}
    public void showToast(String message) {
        if (getActivity() == null) {
            return;
        }
        ToastManager.getInstance().showToastCenter(getActivity(), message, ToastManager.TOAST_TYPE.DEFAULT);
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
                setupErrorView(ErrorType.Loading);
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

    public void setupErrorView(ErrorType type) {
        if (this.errorView != null) {
            this.errorType = type;
            this.errorView.setVisibility(View.VISIBLE);
            this.errorView.setErrorType(this.errorType);
        }
    }

    public void setupErrorView(ErrorType type, int imageResId, int textResId) {
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
        mWaitingDialog = InfoDialogManager.getInstance(this.getActivity()).showDialog(true, msg);
    }

    protected void hideWaitingDialog(){
        if (mWaitingDialog != null && mWaitingDialog.isShowing()) {
            mWaitingDialog.dismiss();
        }
    }


}
