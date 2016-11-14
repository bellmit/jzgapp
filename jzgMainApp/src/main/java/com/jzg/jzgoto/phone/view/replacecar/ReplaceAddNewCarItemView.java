package com.jzg.jzgoto.phone.view.replacecar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.jzg.jzgoto.phone.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.Serializable;

/**
 * @Description: 新车信息
 * @Package com.jzg.jzgoto.phone.customview.business.sell AddItemNewCarFrame.java
 * @author gf
 * @date 2015-12-15 下午1:37:14
 */
public class ReplaceAddNewCarItemView extends FrameLayout {

	public ReplaceAddNewCarItemView(Context context, AttributeSet attrs,
									int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	public ReplaceAddNewCarItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public ReplaceAddNewCarItemView(Context context) {
		super(context);
		initView();
	}
	private void initView(){
		LayoutInflater.from(getContext()).inflate(R.layout.item_replace_apply_newcar_layout, this);
		initWidget();
		initListener();
	}
	private ImageView mImgCarImg;
	private TextView mTvCarFullName;
	private TextView mTvCarPrice;
	private TextView mTvCarPriceBetween;
	private TextView mTvCount;
	private void initWidget(){
		mImgCarImg = (ImageView) findViewById(R.id.img_add_item_new_car_pic);
		mTvCarFullName = (TextView) findViewById(R.id.tv_add_item_new_car_fullname);
		mTvCarPrice = (TextView) findViewById(R.id.tv_add_item_new_car_price);
		mTvCarPriceBetween = (TextView) findViewById(R.id.tv_add_item_new_car_price_between);
		mTvCount = (TextView) findViewById(R.id.tv_add_item_new_car_count);

		mImgCarImg.setImageResource(R.drawable.icon_launcher);
		mTvCarFullName.setText("");
		mTvCarPrice.setText("--万");
		mTvCarPriceBetween.setText("补差价--万");
		mTvCount.setText("");

	}
	private void initListener(){
		this.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO click to do
			}
		});
	}
	public void setShowData(NewCarItemBean bean){
		
//		mImgCarImg.setImageResource(R.drawable.ic_launcher);
		ImageLoader.getInstance().displayImage(bean.picPath, mImgCarImg);
		mTvCarFullName.setText(bean.carFullname);
		mTvCarPrice.setText(bean.carPrice + "万");
		mTvCarPriceBetween.setText("补差价" + bean.carPriceBetween + "万");
//		mTvCount.setText("1234");
		
	}
	public static class NewCarItemBean implements Serializable{
		public Object mObj;
		public String picPath;
		public String carFullname;
		public String carPrice;
		public String carPriceBetween;
		public String count;
	}
}
