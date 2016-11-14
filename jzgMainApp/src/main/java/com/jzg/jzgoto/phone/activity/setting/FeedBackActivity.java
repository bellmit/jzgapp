package com.jzg.jzgoto.phone.activity.setting;


import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.activity.shared.BaseActivity;
import com.jzg.jzgoto.phone.global.AppContext;
import com.jzg.jzgoto.phone.models.business.settings.FeedBackParamsModels;
import com.jzg.jzgoto.phone.models.business.settings.FeedBackResultModels;
import com.jzg.jzgoto.phone.services.business.login.LoginService;
import com.jzg.jzgoto.phone.tools.CountClickTool;
import com.jzg.jzgoto.phone.tools.ShowDialogTool;
import com.jzg.jzgoto.phone.view.ViewUtility;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class FeedBackActivity extends BaseActivity implements View.OnClickListener{
	private final int REQUEST_TO_FEED_BACK = 0x1001;

	@Bind(R.id.edit_feed_back_msg)
	EditText mEditFeedBackMsg;
	@Bind(R.id.btn_submit)
	Button mBtnSubmit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feed_back_layout);
		ButterKnife.bind(this);
	}

	@Override
	@OnClick({R.id.btn_submit})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.btn_submit:
				handleSubmitClicked();
				break;
		}
	}

	private void handleSubmitClicked(){
		String content = mEditFeedBackMsg.getText().toString();
		if(TextUtils.isEmpty(content)){
			ShowDialogTool.showCenterToast(FeedBackActivity.this, "内容不能为空!");
			return;
		}
		toFeedback(content);
	}

	private void toFeedback(String content){
		if(!AppContext.isLogin()){
			ViewUtility.navigateToLoginActivity(this);
			return;
		}
		toShowLoadingDialog();
		FeedBackParamsModels params = new FeedBackParamsModels();
		params.content = content;
		params.uid = AppContext.mLoginResultModels.getId();
		new LoginService(FeedBackActivity.this, FeedBackActivity.this).toRequestNet(params, 
				FeedBackResultModels.class, REQUEST_TO_FEED_BACK);
	}
	
	@Override
	public void onRequestSuccess(Message msg) {
		super.onRequestSuccess(msg);
		switch(msg.what){
		case REQUEST_TO_FEED_BACK:
			FeedBackResultModels result = (FeedBackResultModels) msg.obj;
			if(result.getStatus() == 100){
				CountClickTool.onEvent(getApplicationContext(), "my_center_yijianfankui_success");
				ShowDialogTool.showCenterToast(FeedBackActivity.this, "意见反馈成功!");
				FeedBackActivity.this.finish();
			} else {
				ShowDialogTool.showCenterToast(FeedBackActivity.this, "意见反馈失败!");
			}
			break;
		}
	}
	@Override
	public void onRequestFault(Message msg) {
		super.onRequestFault(msg);
		switch(msg.what){
		case REQUEST_TO_FEED_BACK:
			ShowDialogTool.showCenterToast(FeedBackActivity.this, "无法与服务器建立连接，请重试。");
			break;
		}
	}
	
}
