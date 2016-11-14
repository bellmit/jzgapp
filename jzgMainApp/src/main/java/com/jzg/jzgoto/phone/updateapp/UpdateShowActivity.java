package com.jzg.jzgoto.phone.updateapp;

import com.jzg.jzgoto.phone.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
/**
 * @Description: 更新界面显示
 * @Package com.jzg.jzgoto.phone.updateapp UpdateShowActivity.java
 * @author gf
 * @date 2016-6-29 下午7:41:38
 */
public class UpdateShowActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setTheme(android.R.style.Theme_Translucent_NoTitleBar);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.act_update_version_layout);
		mUpdateMsg = getIntent().getStringExtra("update_msg");
		mIsForceUpdate = getIntent().getBooleanExtra("is_force_update", false);
		mUpdate = (UpdateApp) getIntent().getSerializableExtra("app_download_version");
		initWidget();
		initListener();
	}
	private UpdateApp mUpdate;
	
	private String mUpdateMsg = "";
	private boolean mIsForceUpdate = false;
	private void initWidget(){
		TextView title = (TextView) findViewById(R.id.tv_dialog_hint_view_title);
		title.setText("软件版本更新");
		TextView msg = (TextView) findViewById(R.id.tv_dialog_hint_view_msg);
		msg.setText(mUpdateMsg);
		
		Button ok = (Button)findViewById(R.id.btn_dialog_hint_ok);
		ok.setText("立刻更新");
		ok.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(UpdateShowActivity.this,DownloandApkActivity.class);
				intent.putExtra("app_download_version",mUpdate);
				intent.putExtra("update_msg",mUpdateMsg);
				intent.putExtra("is_force_update",mIsForceUpdate);
				UpdateShowActivity.this.startActivity(intent);
				UpdateShowActivity.this.finish();
			}
		});

		// 关闭alert对话框架
		Button cancel = (Button)findViewById(R.id.btn_dialog_hint_cancel);
		
		//是否强制更新
		if(mIsForceUpdate){
			cancel.setText("退出");
			cancel.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					UpdateShowActivity.this.finish();
					System.exit(0);
				}
			});
		}else{
			cancel.setText("以后再说");
			cancel.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					UpdateShowActivity.this.finish();
				}
			});
		}
		
	}
	private void initListener(){
		
	}
	
	@Override
	public void onBackPressed() {
		if(!mIsForceUpdate)
		super.onBackPressed();
	}
}
