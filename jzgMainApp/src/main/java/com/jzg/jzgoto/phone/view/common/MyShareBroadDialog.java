package com.jzg.jzgoto.phone.view.common;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.models.business.buy.ShareModel;
import com.jzg.jzgoto.phone.tools.CountClickTool;
import com.jzg.jzgoto.phone.tools.ShowDialogTool;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

@SuppressLint("InflateParams")
public class MyShareBroadDialog extends Dialog 
implements OnClickListener{

	private Activity mActivity;
	private View mRootView;
	public MyShareBroadDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	public MyShareBroadDialog(Context context, boolean cancelable,
			OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
	}

	public MyShareBroadDialog(Context context, int theme) {
		super(context, theme);
		mActivity = (Activity) context;
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mRootView = LayoutInflater.from(getContext()).inflate(R.layout.view_share_broad, null);
		setContentView(mRootView);
		
		WindowManager.LayoutParams lp = getWindow().getAttributes();
		lp.width = LayoutParams.FILL_PARENT;
		lp.y = 0;
		lp.gravity = Gravity.BOTTOM;
		getWindow().setAttributes(lp);
		onWindowAttributesChanged(lp);
		setCanceledOnTouchOutside(true);
		initListener(mRootView);
	}
	private void initListener(View rootView){
		rootView.findViewById(R.id.share_weChart).setOnClickListener(this);
        rootView.findViewById(R.id.share_wxcircle).setOnClickListener(this);
        rootView.findViewById(R.id.share_sina).setOnClickListener(this);
        rootView.findViewById(R.id.share_QQ).setOnClickListener(this);
        rootView.findViewById(R.id.share_QQZone).setOnClickListener(this);
	}
	private ShareModel shareModel;
	public void setShareModel(ShareModel shareModel){
		this.shareModel =shareModel;
	}
	@Override
	public void onClick(View v) {
		if(shareModel!=null){
			switch (v.getId()) {
			case R.id.share_weChart:
				new ShareAction(mActivity)
				.setPlatform(SHARE_MEDIA.WEIXIN)
				.setCallback(umShareListener)
				.withTitle(shareModel.getShareTitle())
				.withText(shareModel.getShareText())
				.withTargetUrl(shareModel.getShareUrl())
				.withMedia(shareModel.getUMImage())
				.share();
				break;
			case R.id.share_wxcircle:
				new ShareAction(mActivity)
				.setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
				.setCallback(umShareListener)
				.withTitle(shareModel.getShareTitle())
				.withText(shareModel.getShareTitle())
				.withTargetUrl(shareModel.getShareUrl())
				.withMedia(shareModel.getUMImage())
				.share();
				break;
			case R.id.share_sina:
				new ShareAction(mActivity)
				.setPlatform(SHARE_MEDIA.SINA)
				.setCallback(umShareListener)
				.withText(shareModel.getShareTitle())
				.withTargetUrl(shareModel.getShareUrl())
				.withMedia(shareModel.getUMImage())
				.share();
				break;
			case R.id.share_QQ:
				new ShareAction(mActivity)
				.setPlatform(SHARE_MEDIA.QQ)
				.setCallback(umShareListener)
				.withTitle(shareModel.getShareTitle())
				.withText(shareModel.getShareText())
				.withTargetUrl(shareModel.getShareUrl())
				.withMedia(shareModel.getUMImage())
				.share();
				break;
			case R.id.share_QQZone:
				new ShareAction(mActivity)
				.setPlatform(SHARE_MEDIA.QZONE)
				.setCallback(umShareListener)
				.withTitle(shareModel.getShareTitle())
				.withText(shareModel.getShareText())
				.withTargetUrl(shareModel.getShareUrl())
				.withMedia(shareModel.getUMImage())
				.share();
				break;
			default:
				break;
			}
		}
	}
	
	UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
        	MyShareBroadDialog.this.dismiss();
        	if(platform==SHARE_MEDIA.SINA
        			||platform==SHARE_MEDIA.WEIXIN_CIRCLE){
        		ShowDialogTool.showCenterToast(mActivity, "分享成功！");
        		CountClickTool.onEvent(getContext(), "my_center_share_success");
        	}
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
        	MyShareBroadDialog.this.dismiss();
        	ShowDialogTool.showCenterToast(mActivity, "分享失败！");
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
        	MyShareBroadDialog.this.dismiss();
        	ShowDialogTool.showCenterToast(mActivity, "分享取消！");
        }
    };
}
