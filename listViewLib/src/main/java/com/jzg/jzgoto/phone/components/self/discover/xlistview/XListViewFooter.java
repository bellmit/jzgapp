/**
 * Project Name:JZGPingGuShi
 * File Name:XListViewFooter.java
 * Package Name:com.gc.jzgpinggushi.view.pulllist
 * Date:2014-9-1上午11:05:28
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
*/

package com.jzg.jzgoto.phone.components.self.discover.xlistview;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zheng.listviewdemo.R;

/**
 * XListViewFooter <br/>
 *
 */
public class XListViewFooter extends LinearLayout {
	public final static int STATE_NORMAL = 0;
	public final static int STATE_READY = 1;
	public final static int STATE_LOADING = 2;

	private Context mContext;

	private View mContentView;
	private View mProgressBar;
	private TextView mHintView;
	private ImageView mCarLoadMore;//精真估加载更多动画
	private AnimationDrawable mAnimDraw;
	private boolean isShowCarLoadMoreAnim = false;
	public XListViewFooter(Context context) {
		super(context);
		initView(context);
	}
	
	public XListViewFooter(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	
	public void setState(int state) {
		mHintView.setVisibility(View.INVISIBLE);
		mProgressBar.setVisibility(View.INVISIBLE);
		mHintView.setVisibility(View.INVISIBLE);
		if (state == STATE_READY) {
			mHintView.setVisibility(View.VISIBLE);
			mHintView.setText(R.string.xlistview_footer_hint_ready);
		} else if (state == STATE_LOADING&&!isShowCarLoadMoreAnim) {
			mProgressBar.setVisibility(View.VISIBLE);
		} else {
			mHintView.setVisibility(View.VISIBLE);
			mHintView.setText(R.string.xlistview_footer_hint_normal);
		}
	}
	
	public void setBottomMargin(int height) {
		if (height < 0) return ;
		LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams)mContentView.getLayoutParams();
		lp.bottomMargin = height;
		mContentView.setLayoutParams(lp);
	}
	
	public int getBottomMargin() {
		LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams)mContentView.getLayoutParams();
		return lp.bottomMargin;
	}
	
	
	/**
	 * normal status
	 */
	public void normal() {
		mHintView.setVisibility(View.VISIBLE);
		mProgressBar.setVisibility(View.GONE);
	}
	
	
	/**
	 * loading status 
	 */
	public void loading() {
		if(isShowCarLoadMoreAnim){
			//显示加载更多动画
			mHintView.setVisibility(View.VISIBLE);
			mProgressBar.setVisibility(View.GONE);
		}else{
			mHintView.setVisibility(View.GONE);
			mProgressBar.setVisibility(View.VISIBLE);
		}
	}
	
	/**
	 * hide footer when disable pull load more
	 */
	public void hide() {
		LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams)mContentView.getLayoutParams();
		lp.height = 0;
		mContentView.setLayoutParams(lp);
	}
	
	/**
	 * show footer
	 */
	public void show() {
		LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams)mContentView.getLayoutParams();
		lp.height = android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
		mContentView.setLayoutParams(lp);
	}
	public void setIsShowLoadMoreAnim(boolean isShow){
		isShowCarLoadMoreAnim = isShow;
		mCarLoadMore.setVisibility(View.VISIBLE);
		mAnimDraw.start();
	}
	private void initView(Context context) {
		mContext = context;
		LinearLayout moreView = (LinearLayout)LayoutInflater.from(mContext).inflate(R.layout.xlistview_footer, null);
		addView(moreView);
		moreView.setLayoutParams(new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.FILL_PARENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
		
		mContentView = moreView.findViewById(R.id.xlistview_footer_content);
		mProgressBar = moreView.findViewById(R.id.xlistview_footer_progressbar);
		mHintView = (TextView)moreView.findViewById(R.id.xlistview_footer_hint_textview);
	
		mCarLoadMore = (ImageView) moreView.findViewById(R.id.xlistview_footer_car_imageview);
		mCarLoadMore.setBackgroundResource(R.anim.refresh_anim);
		mAnimDraw = (AnimationDrawable) mCarLoadMore.getDrawable();
	}
	
	
}

