package com.jzg.jzgoto.phone.models.common;

import java.util.Map;

import com.jzg.jzgoto.phone.global.HttpServiceHelper;

/**
 * 请求类父类
 * @author gf
 * @Date 2015-05-13
 */
public abstract class BaseParamsModels {
	
	public final String BASE_HTTP = HttpServiceHelper.BASE_URL;
//	public final String BASE_HTTP = "http://testptvapi.guchewang.com";
	public final String BASE_URL = BASE_HTTP + "/APPNew/";
	public final String BASE_URL_V3 = BASE_HTTP + "/APPV3/";
	public abstract Map<String,String> getParams();
	public abstract String toParamsString();
	public abstract String getUrl();
}
