/**
 * Project Name:JZGPingGuShi
 * File Name:UIUtils.java
 * Package Name:com.gc.jzgpinggushi.uitls
 * Date:2014-9-1上午10:40:47
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.jzg.jzgoto.phone.tools;

import java.util.Calendar;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * ClassName:UIUtils <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-9-1 上午10:40:47 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class UIUtils
{
	private static long lastClickTime;

	/**
	 * 请求EditText控件焦点 EditTextRequestFocus: <br/>
	 * 
	 * @author wang
	 * @param editText
	 * @since JDK 1.6
	 */
	public static void editTextRequestFocus(EditText editText)
	{
		editText.setFocusable(true);
		editText.setFocusableInTouchMode(true);
		editText.requestFocus();
		editText.requestFocusFromTouch();
	}

	/**
	 * 显示软键盘 showSoftInput: <br/>
	 * 
	 * @author wang
	 * @param view
	 * @since JDK 1.6
	 */
	public static void showSoftInput(View view)
	{
		InputMethodManager imm = (InputMethodManager) view.getContext()
				.getSystemService(Context.INPUT_METHOD_SERVICE);

		if (imm.isActive())
		{
			// // 隐藏键盘
			// imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0
			// );
			// 显示键盘
			imm.showSoftInput(view, 0);
		}
	}

	/**
	 * 隐藏软键盘 hideSoftInput: <br/>
	 * 
	 * @author wang
	 * @param view
	 * @since JDK 1.6
	 */
	public static void hideSoftInput(View view)
	{
		InputMethodManager imm = (InputMethodManager) view.getContext()
				.getSystemService(Context.INPUT_METHOD_SERVICE);

		if (imm.isActive())
		{
			// // 隐藏键盘
			imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
			// 显示键盘
			// imm.showSoftInput(view, 0);
		}
	}

	/**
	 * 隐藏软键盘 hideSoftInput: <br/>
	 * 
	 * @author wang
	 * @param view
	 * @since JDK 1.6
	 */
	public static void hideSoftInput(Activity activity)
	{
		View view = activity.getWindow().peekDecorView();
		if (view != null)
		{
			InputMethodManager inputmanger = (InputMethodManager) activity
					.getSystemService(Context.INPUT_METHOD_SERVICE);
			inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
		}
	}

	/**
	 * 获取最大行驶公里数 getMileage: <br/>
	 * 
	 * @author wang
	 * @param startYear
	 * @return
	 * @since JDK 1.6
	 */
	public static int getMileage(int startYear, int startMonth)
	{
		int curMonth = Calendar.getInstance().get(Calendar.MONTH);
		int curYear = Calendar.getInstance().get(Calendar.YEAR);
		int totalMonth = (curYear - startYear) * 12
				+ (curMonth - startMonth + 2);
		return totalMonth * 10000;
	}

	/**
	 * 启动360度旋转动画 startRotateAnimation: <br/>
	 * 
	 * @author wang
	 * @param view
	 * @since JDK 1.6
	 */
	public static void startRotateAnimation(View view)
	{
		// 设置为匀速
		LinearInterpolator lin = new LinearInterpolator();
		Animation am = new RotateAnimation(0, +360, Animation.RELATIVE_TO_SELF,
				0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		// 动画开始到结束的执行时间(1000 = 1 秒)
		am.setDuration(1000);

		// 动画重复次数(-1 表示一直重复)
		am.setRepeatCount(-1);
		am.setRepeatCount(Animation.INFINITE);
		am.setInterpolator(lin);
		// 图片配置动画
		view.setAnimation(am);
		am.startNow();
	}

	/**
	 * 防止按钮连续被点击 isFastDoubleClick: <br/>
	 * 
	 * @author wang
	 * @return
	 * @since JDK 1.6
	 */
	public static boolean isFastDoubleClick()
	{
		// long time = System.currentTimeMillis();
		// if (time - lastClickTime < 500)
		// {
		// return true;
		// }
		// lastClickTime = time;
		// return false;
		
		
		   long time = System.currentTimeMillis();
	        long timeD = time - lastClickTime;
	        if ( 0 < timeD && timeD < 3000) {   
	            return true;   
	        }   
	        lastClickTime = time;   
	        return false; 
	}
}
