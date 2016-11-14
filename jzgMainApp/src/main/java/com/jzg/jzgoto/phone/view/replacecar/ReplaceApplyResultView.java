package com.jzg.jzgoto.phone.view.replacecar;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.view.replacecar.ReplaceAddDealerItemView.DealerItemBean;
import com.jzg.jzgoto.phone.view.replacecar.ReplaceAddDealerItemView.ClickCallBack;
import com.jzg.jzgoto.phone.models.business.sell.RequestDealersMsgResultModels.CarStyleList;
import com.jzg.jzgoto.phone.models.business.sell.RequestDealersMsgResultModels.DealersList;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * @Description: 置换结果显示
 * @Package com.jzg.jzgoto.phone.customview.business.sell ReplaceResultCarFrame.java
 * @author gf
 * @date 2015-12-15 下午2:17:57
 */
public class ReplaceApplyResultView extends FrameLayout {

	public ReplaceApplyResultView(Context context, AttributeSet attrs,
								  int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	public ReplaceApplyResultView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public ReplaceApplyResultView(Context context) {
		super(context);
		initView();
	}
	private void initView(){
		LayoutInflater.from(getContext()).inflate(R.layout.view_replace_apply_result_layout, this);
		initWidget();
		initListener();
	}
	private void initWidget(){
		initCarView();
		initDealerView();
	}
	private void initListener(){
		
		
	}
	private ImageView mImgCarImg;
	private TextView mTvCarFullName;
	private TextView mTvCarPrice;
	private void initCarView(){
		mImgCarImg = (ImageView) findViewById(R.id.img_add_item_replace_result_car_img);
		mTvCarFullName = (TextView) findViewById(R.id.tv_add_item_replace_result_car_fullname);
		mTvCarPrice = (TextView) findViewById(R.id.tv_add_item_replace_result_newcar_price);
		
		mImgCarImg.setImageResource(R.drawable.icon_launcher);
		mTvCarFullName.setText("");
		mTvCarPrice.setText("暂无");
	}
	private LinearLayout mLinearDealers;
	private void initDealerView(){
		mLinearDealers = (LinearLayout) findViewById(R.id.linear_add_item_replace_result_car_dealers);
		if(mLinearDealers.getChildCount() != 0){
			mLinearDealers.removeAllViews();
		}
	}
	public void setCarStyleShow(final CarStyleList carStyle, boolean bl){
		ImageLoader.getInstance().displayImage(carStyle.getNewImageUrl(), mImgCarImg);
//		mImgCarImg.setImageResource(R.drawable.ic_launcher);
		mTvCarFullName.setText(carStyle.getNewFullName());
		
		mTvCarPrice.setText(checkStr(carStyle.getNewNowMsrp()) + "万");
		
		List<DealersList> list = carStyle.getNew4SList();
		if(list == null || list.size() == 0){
			TextView empty = new TextView(getContext());
			empty.setText("对不起！该车该地区暂时没有提供服务经销商.");
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 
					LinearLayout.LayoutParams.WRAP_CONTENT);
			lp.setMargins(10, 15, 10, 15);
			lp.gravity = Gravity.CENTER;
			empty.setTextColor(getResources().getColor(R.color.text_red));
			empty.setLayoutParams(lp);
			mLinearDealers.addView(empty);
			return;
		}
		for(int i = 0;i < list.size();i++){
			ReplaceAddDealerItemView item = new ReplaceAddDealerItemView(getContext());
			DealerItemBean dealer = new DealerItemBean();
			DealersList bean = list.get(i);
			dealer.setmObj(bean);
			dealer.setAddress(bean.getAddress());
			
			dealer.setName(bean.getFullName());
			dealer.setPhoneNum(bean.getTel400());
			dealer.setDealerId(bean.getDealerId());
			item.setCallback(new ClickCallBack() {
				@Override
				public void callBack(DealerItemBean dealer, boolean bl) {
					if(mDataCallback != null){
						mDataCallback.callback(carStyle.getStyleId(), dealer, bl);
					}
				}
			});
			dealer.setBl(bl);
			item.setDealerBean(dealer);
			mLinearDealers.addView(item);
			if(i != list.size()-1){
				View line = new View(getContext());
				line.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1));
				line.setBackgroundResource(R.drawable.long_line);
				mLinearDealers.addView(line);
			}
		}
	}
	public void setCarStyleShow(final CarStyleList carStyle){
		setCarStyleShow(carStyle,true);
	}
	private DataCallback mDataCallback;
	public void setClickCallback(DataCallback callback){
		mDataCallback = callback;
	}
	public interface DataCallback{
		public void callback(String styleId, DealerItemBean dealer, boolean bl);
	}
	private String getPrice(String price){
		if(TextUtils.isEmpty(price)){
			return "--";
		}
		if(!price.contains(".")){
			return price;
		}
		String strs[] = price.split("\\.");
		if(TextUtils.isEmpty(strs[1])){
			return strs[0]+".00";
		}
		if(strs[1].length() > 2){
			return strs[0]+"." + strs[1].substring(0, 2);
		}
		return price;
	}
	private String checkStr(String price){
		if(TextUtils.isEmpty(price)){
			return "暂无";
		}
		double dou = Double.valueOf(price);
		if(dou == 0){
			return "暂无";
		}
		return getPrice(price);
	}
}
