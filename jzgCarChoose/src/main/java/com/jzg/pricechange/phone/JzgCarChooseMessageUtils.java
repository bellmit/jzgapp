package com.jzg.pricechange.phone;



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
public class JzgCarChooseMessageUtils
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
