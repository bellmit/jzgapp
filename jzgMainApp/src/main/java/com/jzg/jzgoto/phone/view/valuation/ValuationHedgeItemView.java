package com.jzg.jzgoto.phone.view.valuation;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.activity.valuation.ValuationHedgeActivity;
import com.jzg.jzgoto.phone.activity.valuation.ValuationHedgeActivity.ItemBean;
import com.jzg.jzgoto.phone.tools.CountClickTool;
import com.jzg.jzgoto.phone.tools.ShowDialogTool;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by jzg on 2016/9/21.
 * Function :
 */
public class ValuationHedgeItemView extends ValuationBaseView {

	public ValuationHedgeItemView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public ValuationHedgeItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ValuationHedgeItemView(Context context) {
		super(context);
	}
	private TextView mTvLevel;
	
	private TextView mTvCarBrand;
	private TextView mTvCarModels;
	private ImageView mImgCarPic;
	
	private LinearLayout mLinearOne;
	private LinearLayout mLinearTwo;
	
	private TextView mTvOneOne;
	private TextView mTvOneTwo;
	private TextView mTvOneThree;
	private TextView mTvOneFour;
	private TextView mTvOneFive;
	
	private TextView mTvTwoOne;
	private TextView mTvTwoTwo;
	private TextView mTvTwoThree;
	private TextView mTvTwoFour;
	private TextView mTvTwoFive;

	public void initView() {
		View.inflate(getContext(), R.layout.item_valuation_hedge_view_layout, this);
		
		mTvLevel = (TextView) findViewById(R.id.tv_item_hedge_view_level);
		
		mTvCarBrand = (TextView) findViewById(R.id.tv_item_hedge_view_brand);
		mTvCarModels = (TextView) findViewById(R.id.img_item_hedge_view_model);
		mImgCarPic = (ImageView) findViewById(R.id.img_item_hedge_view_carpic);
		mLinearOne = (LinearLayout) findViewById(R.id.linear_item_hedge_view_one);
		mLinearTwo = (LinearLayout) findViewById(R.id.linear_item_hedge_view_two);
		
		mTvOneOne = (TextView) findViewById(R.id.tv_item_hedge_view_one_one);
		mTvOneTwo = (TextView) findViewById(R.id.tv_item_hedge_view_one_two);
		mTvOneThree = (TextView) findViewById(R.id.tv_item_hedge_view_one_three);
		mTvOneFour = (TextView) findViewById(R.id.tv_item_hedge_view_one_four);
		mTvOneFive = (TextView) findViewById(R.id.tv_item_hedge_view_one_five);
		
		mTvTwoOne = (TextView) findViewById(R.id.tv_item_hedge_view_two_one);
		mTvTwoTwo = (TextView) findViewById(R.id.tv_item_hedge_view_two_two);
		mTvTwoThree = (TextView) findViewById(R.id.tv_item_hedge_view_two_three);
		mTvTwoFour = (TextView) findViewById(R.id.tv_item_hedge_view_two_four);
		mTvTwoFive = (TextView) findViewById(R.id.tv_item_hedge_view_two_five);
	}
	private ValuationHedgeDialogView mDialogHedgeShowView;
	@Override
	public void initListener() {
		this.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(mDialogHedgeShowView == null){
					mDialogHedgeShowView = new ValuationHedgeDialogView(getContext());
					mDialogHedgeShowView.initShowData(mPosition);
					mDialogHedgeShowView.setBarValues(mItemBean);
				}else{
					mDialogHedgeShowView.initShowData(mPosition);
					mDialogHedgeShowView.setBarValues(mItemBean);
				}
				CountClickTool.onEvent(getContext(), "buy_gedge_item");
				ShowDialogTool.showSelfViewDialog((ValuationHedgeActivity)getContext(), mDialogHedgeShowView, true, null);
			}
		});
	}
	private ItemBean mItemBean;
	public void initShowData(ItemBean bean){
		mItemBean = bean;
		mTvCarBrand.setText(bean.makeName);
		mTvCarModels.setText(bean.modelName);
		
		if(TextUtils.isEmpty(bean.modelPic)){
			mImgCarPic.setImageResource(R.drawable.jingzhengu_moren);
		} else {
			ImageLoader.getInstance().displayImage(bean.modelPic, mImgCarPic);
		}
		
		if(bean.data != null && bean.data.length == 5){
			mTvOneOne.setText(bean.data[0]);
			mTvOneTwo.setText(bean.data[1]);
			mTvOneThree.setText(bean.data[2]);
			mTvOneFour.setText(bean.data[3]);
			mTvOneFive.setText(bean.data[4]);
		}
		if(bean.years != null && bean.years.length == 5){
			mTvTwoOne.setText(bean.years[0]);
			mTvTwoTwo.setText(bean.years[1]);
			mTvTwoThree.setText(bean.years[2]);
			mTvTwoFour.setText(bean.years[3]);
			mTvTwoFive.setText(bean.years[4]);
		}
	}
	private void setTextOneColor(int color){
		mTvOneOne.setTextColor(color);
		mTvOneTwo.setTextColor(color);
		mTvOneThree.setTextColor(color);
		mTvOneFour.setTextColor(color);
		mTvOneFive.setTextColor(color);
	}
	private int mPosition = 1;
	public void setPosition(int position){
		if(position < 0)return;
		mPosition = position;
		if(mPosition >= 3){
			mLinearOne.setVisibility(View.GONE);
			mLinearTwo.setVisibility(View.GONE);
		} else {
			mLinearOne.setVisibility(View.VISIBLE);
			mLinearTwo.setVisibility(View.VISIBLE);
		}
		
		switch(position){
		case 0:
			mTvLevel.setText("");
			Drawable dra1 = getResources().getDrawable(R.drawable.img_n1);
			dra1.setBounds(0, 0, dra1.getMinimumWidth(), dra1.getMinimumHeight());
			mTvLevel.setCompoundDrawables(dra1, null, null, null);
			setTextOneColor(getResources().getColor(R.color.red_for_valuation));
			break;
		case 1:
			mTvLevel.setText("");
			Drawable dra2 = getResources().getDrawable(R.drawable.img_n2);
			dra2.setBounds(0, 0, dra2.getMinimumWidth(), dra2.getMinimumHeight());
			mTvLevel.setCompoundDrawables(dra2, null, null, null);
			setTextOneColor(getResources().getColor(R.color.yellow_for_valuation));
			break;
		case 2:
			mTvLevel.setText("");
			Drawable dra3 = getResources().getDrawable(R.drawable.img_n3);
			dra3.setBounds(0, 0, dra3.getMinimumWidth(), dra3.getMinimumHeight());
			mTvLevel.setCompoundDrawables(dra3, null, null, null);
			setTextOneColor(getResources().getColor(R.color.blue_for_valuation));
			break;
		default:
			mTvLevel.setText(Integer.toString(position+1));
			mTvLevel.setCompoundDrawables(null, null, null, null);
			break;
		}
		
	}
}
