package com.jzg.jzgoto.phone.updateapp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jzg.jzgoto.phone.R;
/**
 * @Description: 下载界面
 * @Package com.jzg.jzgoto.phone.updateapp DownloandApkActivity.java
 * @author gf
 * @date 2016-6-29 下午8:11:30
 */
public class DownloandApkActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setTheme(android.R.style.Theme_Translucent_NoTitleBar);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.act_download_apk_layout);
		mUpdateMsg = getIntent().getStringExtra("update_msg");
		mIsForceUpdate = getIntent().getBooleanExtra("is_force_update", false);
		mUpdate = (UpdateApp) getIntent().getSerializableExtra("app_download_version");
		if(null != mUpdate)
		apkUrl = mUpdate.getDownloadUrl();
		initWidget();
		initListener();
	}
	private UpdateApp mUpdate;
	
	private String mUpdateMsg = "";
	private boolean mIsForceUpdate = false;
	//进度条
    private ProgressBar mProgress;
    //显示下载数值
    private TextView mProgressText;
  //进度值
    private int progress;
    //下载线程
    private Thread downLoadThread;
    //终止标记
    private boolean interceptFlag;
    
	private void initWidget(){
		mProgress = (ProgressBar)findViewById(R.id.update_progress);
		mProgressText = (TextView) findViewById(R.id.update_progress_text);
		findViewById(R.id.btn_download_apk_cancel).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				interceptFlag = true;
				DownloandApkActivity.this.finish();
				if(mIsForceUpdate){
					System.exit(0);
				}
			}
		});
	}
	
	private void initListener(){
		downloadApk();
	}
	@Override
	protected void onResume() {
		super.onResume();
		if(null == mUpdate){
			DownloandApkActivity.this.finish();
		}
	}
	@Override
	public void onBackPressed() {
	}
	/**
	* 下载apk
	* @param url
	*/	
	private void downloadApk(){
		downLoadThread = new Thread(mdownApkRunnable);
		downLoadThread.start();
	}
	//返回的安装包url
	private String apkUrl = "";
	//下载包保存路径
	private String savePath = "";
	//apk保存完整路径
	private String apkFilePath = "";
	//临时下载文件路径
	private String tmpFilePath = "";
	//下载文件大小
	private String apkFileSize;
	//已下载文件大小
	private String tmpFileSize;

	private static final int DOWN_NOSDCARD = 0;
    private static final int DOWN_UPDATE = 1;
    private static final int DOWN_OVER = 2;
    private static final int DOWN_FAIL = 3;
    
    private Handler mHandler = new Handler(){
    	public void handleMessage(Message msg) {
    		switch (msg.what) {
			case DOWN_UPDATE:
				mProgress.setProgress(progress);
				mProgressText.setText(tmpFileSize + "/" + apkFileSize);
				break;
			case DOWN_OVER:
				installApk();
				break;
			case DOWN_NOSDCARD:
				Toast.makeText(DownloandApkActivity.this, "无法下载安装文件，请检查SD卡是否挂载", 3000).show();
				DownloandApkActivity.this.finish();
				break;
			case DOWN_FAIL:
				Toast.makeText(DownloandApkActivity.this, "获取下载安装文件失败，请检查网络", 3000).show();
				DownloandApkActivity.this.finish();
				break;
			}
    	};
    };
	
	private Runnable mdownApkRunnable = new Runnable() {	
		@Override
		public void run() {
			try {
				String apkName = "JZG_"+mUpdate.getVersionName()+".apk";
				String tmpApk = "JZG_"+mUpdate.getVersionName()+".tmp";
				//判断是否挂载了SD卡
				String storageState = Environment.getExternalStorageState();		
				if(storageState.equals(Environment.MEDIA_MOUNTED)){
					savePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/jzg/Update/";
					File file = new File(savePath);
					if(!file.exists()){
						file.mkdirs();
					}
					apkFilePath = savePath + apkName;
					tmpFilePath = savePath + tmpApk;
				}
				
				//没有挂载SD卡，无法下载文件
				if(apkFilePath == null || apkFilePath == ""){
					mHandler.sendEmptyMessage(DOWN_NOSDCARD);
					return;
				}
				
				System.out.println("安装文件apkFilePath"+apkFilePath);
				File ApkFile = new File(apkFilePath);
				
				//是否已下载更新文件
				if(ApkFile.exists()){
					installApk();
					return;
				}
				
				//输出临时下载文件
				File tmpFile = new File(tmpFilePath);
				FileOutputStream fos = new FileOutputStream(tmpFile);
				
				System.out.println("apk下载地址" + apkUrl);
				URL url = new URL(apkUrl);
				HttpURLConnection conn = (HttpURLConnection)url.openConnection();
				conn.connect();
				int length = conn.getContentLength();
				InputStream is = conn.getInputStream();
				
				//显示文件大小格式：2个小数点显示
		    	DecimalFormat df = new DecimalFormat("0.00");
		    	//进度条下面显示的总文件大小
		    	apkFileSize = df.format((float) length / 1024 / 1024) + "MB";
				
				int count = 0;
				byte buf[] = new byte[1024];
				
				do{   		   		
		    		int numread = is.read(buf);
		    		count += numread;
		    		//进度条下面显示的当前下载文件大小
		    		tmpFileSize = df.format((float) count / 1024 / 1024) + "MB";
		    		//当前进度值
		    	    progress =(int)(((float)count / length) * 100);
		    	    //更新进度
		    	    mHandler.sendEmptyMessage(DOWN_UPDATE);
		    		if(numread <= 0){	
		    			//下载完成 - 将临时下载文件转成APK文件
						if(tmpFile.renameTo(ApkFile)){
							//通知安装
							mHandler.sendEmptyMessage(DOWN_OVER);
						}
		    			break;
		    		}
		    		fos.write(buf,0,numread);
		    	}while(!interceptFlag);//点击取消就停止下载
				
				fos.close();
				is.close();
			} catch (MalformedURLException e) {
				mHandler.sendEmptyMessage(DOWN_FAIL);
				e.printStackTrace();
			} catch(IOException e){
				mHandler.sendEmptyMessage(DOWN_FAIL);
				e.printStackTrace();
			}
			
		}
	};
	
	
	
	/**
    * 安装apk
    * @param url
    */
	private void installApk(){
		File apkfile = new File(apkFilePath);
        if (!apkfile.exists()) {
            return;
        }    
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setDataAndType(Uri.parse("file://" + apkfile.toString()), "application/vnd.android.package-archive"); 
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(i);
        this.finish();
        if(mIsForceUpdate){
        	System.exit(0);
        }
	}
	
}
