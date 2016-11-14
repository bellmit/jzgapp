/**
 * Project Name:JZGPingGuShi
 * File Name:DialogManager.java
 * Package Name:com.gc.jzgpinggushi.uitls
 * Date:2014-9-1上午10:38:10
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.jzg.jzgoto.phone.tools;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.activity.user.LoginActivity;
import com.jzg.jzgoto.phone.tools.ShowDialogTool.DialogCallBack;

/**
 * ClassName:DialogManager <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-9-1 上午10:38:10 <br/>
 *
 * @version
 * @since JDK 1.6
 * @see
 */
public class DialogManager
{
	private Dialog showDialog(Context context)
	{
		RerefshDialog dialog = new RerefshDialog(context, R.style.dialog);
		dialog.show();
		return dialog;
	}

	/**
	 * @Title: show
	 * @Description: TODO
	 * @param @param dialogEntity
	 * @param @param dialogListBg
	 * @param @return
	 * @return PopDialog
	 * @throws
	 */
	public Dialog show(Context context, Activity activity, int resId)
	{
		Dialog dialog = showDialog(context);
		setDialogScale(activity, dialog, resId);
		return dialog;
	}

	/**
	 * 设置Dialog弹出窗口大小
	 *
	 * @param dialog
	 */
	public void setDialogScale(Activity activity, Dialog dialog, int resId)
	{
		Display display = activity.getWindowManager().getDefaultDisplay();
		int width = display.getWidth();
		int height = display.getHeight();
		Window window = dialog.getWindow();
		window.setLayout(width * 2 / 4, height / 7);
		window.setGravity(Gravity.CENTER);
		window.setBackgroundDrawableResource(resId);
	}


	public static void toLoginDialog(final Context context) {

		ShowDialogTool.showTitleAndMsgViewDialog(context, "提示", "此功能需要登录后使用", new DialogCallBack() {

			@Override
			public void cancelBack(View v) {
				// TODO Auto-generated method stub

			}

			@Override
			public void applyBack(View v) {
				// TODO Auto-generated method stub
				context.startActivity(new Intent(context, LoginActivity.class));

			}
		});



//		AlertDialog.Builder builder = new AlertDialog.Builder(context,AlertDialog.THEME_HOLO_DARK) {
//		}; // 先得到构造器
////		builder.setTitle("删除"); // 设置标题
//		builder.setMessage("该功能需要登录，是否登录"); // 设置内容
//		builder.setPositiveButton("登录", new DialogInterface.OnClickListener() { // 设置确定按钮
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//						context.startActivity(new Intent(context, LoginActivity.class));
//
//						dialog.dismiss(); // 关闭dialog
//					}
//				});
//		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() { // 设置取消按钮
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//						dialog.dismiss();
//					}
//				});
//
//		// 参数都设置完成了，创建并显示出来
//		builder.create().show();
	}
}