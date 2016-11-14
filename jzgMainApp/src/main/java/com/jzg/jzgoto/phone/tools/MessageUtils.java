/**
 * Project Name:JZGPingGuShi
 * File Name:MessageUtils.java
 * Package Name:com.gc.jzgpinggushi.uitls
 * Date:2014-9-1上午10:39:27
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.jzg.jzgoto.phone.tools;

import android.os.Handler;
import android.os.Message;

/**
 * ClassName:MessageUtils <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-9-1 上午10:39:27 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class MessageUtils
{
	public static synchronized void sendMessage(Handler handler, int id,
			Object object)
	{
		Message msg = new Message();
		msg.what = id;
		if (object != null)
			msg.obj = object;
		handler.sendMessage(msg);
	}
}
