package com.jzg.jzgoto.phone.tools;

import android.content.Context;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.view.common.MyShareBroadDialog;
import com.jzg.jzgoto.phone.models.business.buy.ShareModel;
import com.umeng.socialize.PlatformConfig;

public class ShareTools {

	private static Context mContext;
	
	public ShareTools(Context context) {
		mContext = context;
		initShare();
		if(mShareDialog==null&mContext!=null){
			mShareDialog = 
					new MyShareBroadDialog(mContext,R.style.ActionSheet);
		}
	}
	
	private MyShareBroadDialog mShareDialog = null;
	
	private void initShare(){
		//微信
		String wxAppId = "wx8d1c2ac954468d39";
		String wxAppSecret = "18121ba74ffacf37c9864c688c3f7086";
		PlatformConfig.setWeixin(wxAppId, wxAppSecret);
		//新浪微博
		
		String sinaAppId = "4076013896";
		String sinaAppSecret = "ecf1ea8a4eac4927ea68aeeb0b9f60dd";
		PlatformConfig.setSinaWeibo(sinaAppId,sinaAppSecret);
		//QQ 
		String qqAppId = "1104639912";
		String qqAppSecret = "UuXeKQ5eKmcsVDCa";
		PlatformConfig.setQQZone(qqAppId, qqAppSecret); 
	}
	
	public void openShareBroad(ShareModel shareModel){
		if(mShareDialog==null&mContext!=null){
			mShareDialog =new MyShareBroadDialog(mContext, R.style.ActionSheet);
		}
		if(mShareDialog!=null){
			mShareDialog.setShareModel(shareModel);
			mShareDialog.show();
		}
	}
	public void closeShareBroad(){
		if(mShareDialog!=null){
			if(mShareDialog.isShowing())
				mShareDialog.dismiss();
		}
	}
}
