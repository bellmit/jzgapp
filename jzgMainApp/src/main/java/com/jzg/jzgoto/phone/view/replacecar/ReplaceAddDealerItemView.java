package com.jzg.jzgoto.phone.view.replacecar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.jzg.jzgoto.phone.R;

import java.io.Serializable;

/**
 * @Description: 经销商
 * @Package com.jzg.jzgoto.phone.customview.business.sell AddItemDealerFrame.java
 * @author gf
 * @date 2015-12-15 下午3:00:04
 */
public class ReplaceAddDealerItemView extends FrameLayout {

	public ReplaceAddDealerItemView(Context context, AttributeSet attrs,
									int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	public ReplaceAddDealerItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public ReplaceAddDealerItemView(Context context) {
		super(context);
		initView();
	}
	private void initView(){
		LayoutInflater.from(getContext()).inflate(R.layout.item_replace_submit_apply_adddealer_layout, this);
		initWidget();
	}
	private ImageView mImgDealerChecked;
	private TextView mTvDealerName;
	private TextView mTvDealerAddress;
	private TextView mTvDealerPhoneNum;
	private void initWidget(){
		mImgDealerChecked = (ImageView) findViewById(R.id.img_add_item_dealer_msg_checked);
		mTvDealerName = (TextView) findViewById(R.id.tv_add_item_dealer_msg_name);
		mTvDealerAddress = (TextView) findViewById(R.id.tv_add_item_dealer_msg_address);
		mTvDealerPhoneNum = (TextView) findViewById(R.id.tv_add_item_dealer_msg_phongnum);
		mImgDealerChecked.setSelected(false);
//		mImgDealerChecked.setImageResource(R.drawable.radio_tradition_pressed);

		mTvDealerName.setText("");
		mTvDealerAddress.setText("地址：");
		mTvDealerPhoneNum.setText("电话：");
		/*
		mImgDealerChecked.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(v.isSelected()){
					v.setSelected(false);
				} else {
					v.setSelected(true);
				}
				if(mClickCallBack != null){
					if(mDealerItemBean != null)
					mClickCallBack.callBack(mDealerItemBean, v.isSelected());
				}
			}
		});
		*/
		this.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(mImgDealerChecked.isSelected()){
					mImgDealerChecked.setSelected(false);
				} else {
					mImgDealerChecked.setSelected(true);
				}
				if(mClickCallBack != null){
					if(mDealerItemBean != null)
					mClickCallBack.callBack(mDealerItemBean, mImgDealerChecked.isSelected());
				}
			}
		});
	}
	private ClickCallBack mClickCallBack;
	public void setCallback(ClickCallBack callback){
		mClickCallBack = callback;
	}
	public interface ClickCallBack{
		public void callBack(DealerItemBean dealer, boolean bl);
	}
	private DealerItemBean mDealerItemBean;
	public void setDealerBean(DealerItemBean bean){
		mDealerItemBean = bean;
		mTvDealerName.setText(bean.getName());
		mTvDealerAddress.setText("地址：" + bean.getAddress());
		mTvDealerPhoneNum.setText("电话：" + bean.getPhoneNum());
		if(!bean.bl){
			mImgDealerChecked.setSelected(true);
			this.setOnClickListener(null);
		}
	}
	public static class DealerItemBean implements Serializable{
		/**
		 * UID
		 */
		private static final long serialVersionUID = 1L;
		private String dealerId;
		private Object mObj;
		private String picPath;
		private String name;
		private String address;
		private String phoneNum;
		private boolean bl = true;
		public boolean isBl() {
			return bl;
		}
		public void setBl(boolean bl) {
			this.bl = bl;
		}
		public Object getmObj() {
			return mObj;
		}
		public void setmObj(Object mObj) {
			this.mObj = mObj;
		}
		public String getPicPath() {
			return picPath;
		}
		public void setPicPath(String picPath) {
			this.picPath = picPath;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getPhoneNum() {
			return phoneNum;
		}
		public void setPhoneNum(String phoneNum) {
			this.phoneNum = phoneNum;
		}
		public String getDealerId() {
			return dealerId;
		}
		public void setDealerId(String dealerId) {
			this.dealerId = dealerId;
		}
	}
}
