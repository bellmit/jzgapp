package com.jzg.jzgoto.phone.services.business.login;

import com.lidroid.xutils.HttpUtils;

/**
 * 网络请求HttpUtils对象
 * 
 * @author jzg
 * 
 */
public class HttpUtilSingleton {

	private static HttpUtils httpUtils = new HttpUtils();

	public HttpUtilSingleton() {
		super();
	}

	public static synchronized HttpUtils getInstance() {
		return httpUtils;
	}
}
