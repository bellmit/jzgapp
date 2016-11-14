package com.jzg.jzgoto.phone.activity.main;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.activity.main.HomeActivity;
import com.jzg.jzgoto.phone.activity.shared.BaseActivity;
import com.jzg.jzgoto.phone.view.ViewUtility;

public class WelcomeActivity extends BaseActivity {

	private ViewPager mViewPager;
	private LinearLayout mPointContainer;
	private TextView mStartInto;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome_layout);
		init();
		addPointView();
	}
	
	private void init(){
		mViewPager = (ViewPager) findViewById(R.id.act_donghua_viewpager);
		mPointContainer = (LinearLayout) findViewById(R.id.act_donghua_viewpager_point);
		mStartInto = (TextView) findViewById(R.id.act_donghua_textView_into);
		mStartInto.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				SharedPreferences preferences = getSharedPreferences(
					      "first_in", MODE_PRIVATE);
					    Editor editor = preferences.edit();
					    editor.putBoolean("isFirstIn", false);
					    editor.commit();
				ViewUtility.navigateToHomeActivity(WelcomeActivity.this);
				WelcomeActivity.this.finish();
			}
		});
		mImageList.add(R.drawable.donghua_1);
		mImageList.add(R.drawable.donghua_2);
		mImageList.add(R.drawable.donghua_3);
		mImageList.add(R.drawable.donghua_4);
	}
	private List<Integer> mImageList = new ArrayList<>();
	private List<ImageView> pointList = new ArrayList<>();
	private List<ImageView> imgList = new ArrayList<>();
	private void addPointView() {
		// 动态添加图片和下面指示的圆点
		// 初始化图片资源
		for (int i = 0; i < mImageList.size(); i++) {
			ImageView imageView = new ImageView(this);
			// 异步加载图片
			imageView.setImageResource(mImageList.get(i));
			imageView.setScaleType(ScaleType.CENTER_CROP);
			imgList.add(imageView);
			ImageView view = new ImageView(this);
			view.setScaleType(ScaleType.CENTER_CROP);
			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(40, 40);
			layoutParams.setMargins(15, 15, 15, 15);
			view.setLayoutParams(layoutParams);
			pointList.add(view);
			mPointContainer.addView(view);
		}
		mViewPager.setAdapter(new MyPagerAdapter());
		mViewPager.setOnPageChangeListener(new MyPageChangeListener());
		changePointColor(0);
	}
	
	private class MyPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {
			//TODO 
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			//TODO 
		}

		@Override
		public void onPageSelected(int position) {
			changePointColor(position);
		}
	}
	
	private void changePointColor(int index){
		if (index == mImageList.size() - 1){
			mStartInto.setVisibility(View.VISIBLE);
		}else{
			mStartInto.setVisibility(View.GONE);
		}
		for(int i = 0; i < pointList.size(); i++){
			if ( i== index){
				pointList.get(i).setBackgroundResource(R.drawable.qidong_point_focused);
			}else{
				pointList.get(i).setBackgroundResource(R.drawable.qidong_point_normal);
			}
		}
	}
	
	private class MyPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return imgList.size();
		}
		@Override
		public Object instantiateItem(ViewGroup container, final int position) {
			ImageView iv = imgList.get(position);
			((ViewPager) container).addView(iv);
			// 在这个方法里面设置图片的点击事件
			return iv;
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView((View) arg2);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			
			return arg0 == arg1;
		}

	}
}
