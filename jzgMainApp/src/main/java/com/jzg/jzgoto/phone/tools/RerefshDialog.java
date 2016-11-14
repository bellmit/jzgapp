/**
 * Project Name:JZGPingGuShi
 * File Name:RerefshDialog.java
 * Package Name:com.gc.jzgpinggushi.view
 * Date:2014-9-1上午11:04:35
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
*/

package com.jzg.jzgoto.phone.tools;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;

import com.jzg.jzgoto.phone.R;


/**
 * ClassName:RerefshDialog <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-9-1 上午11:04:35 <br/>
 * @author   汪渝栋
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class RerefshDialog extends Dialog
{
	private Context context;

	public RerefshDialog(Context context, int theme)
	{
		super(context, theme);
		this.context = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.refresh_loading);
		ImageView imageView = (ImageView) findViewById(R.id.load_img);
		UIUtils.startRotateAnimation(imageView);
		setCanceledOnTouchOutside(false);
	}
}

