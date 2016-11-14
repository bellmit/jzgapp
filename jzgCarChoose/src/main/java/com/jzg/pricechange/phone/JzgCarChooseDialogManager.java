package com.jzg.pricechange.phone;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.Window;

import com.jzg.jzgcarchoose.R;

/**
 * ClassName:DialogManager <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-9-1 上午10:38:10 <br/>
 * 
 * @author 汪渝�? * @version
 * @since JDK 1.6
 * @see
 */
public class JzgCarChooseDialogManager
{
	private Dialog showDialog(Context context)
	{
		JzgCarChooseRerefshDialog dialog = new JzgCarChooseRerefshDialog(context, R.style.dialog);
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
	 * @param context
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
}
