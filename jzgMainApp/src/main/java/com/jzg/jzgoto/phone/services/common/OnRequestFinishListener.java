package com.jzg.jzgoto.phone.services.common;

import android.os.Message;

/**
 * 请求完成监听事件
 * @author gf
 * @Date 2015-05-06
 */
public interface OnRequestFinishListener {
	/**
	 * 请求成功返回
	 * @param msg
	 */
	public void onRequestSuccess(Message msg);
	/**
	 * 请求失败返回
	 * @param msg
	 */
	public void onRequestFault(Message msg);
}
