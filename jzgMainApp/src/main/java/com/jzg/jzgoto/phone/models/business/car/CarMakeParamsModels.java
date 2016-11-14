package com.jzg.jzgoto.phone.models.business.car;

import java.util.HashMap;
import java.util.Map;

import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.tools.MD5Utils;
/**
 * @Description: 品牌请求
 * @Package com.jzg.jzgoto.phone.models.business.car CarMakeParamsModels.java
 * @author gf
 * @date 2016-6-17 上午10:09:02
 */
public class CarMakeParamsModels extends BaseParamsModels {
	private final String url = BASE_URL_V3 + "GetMakeModelStyleAll.ashx";
	
	public static final String MAKE = "getPreMake";
	public static final String MAKE_VALU = "getPreGetValuationMake";
	public String requestOp = MAKE;
	@Override
	public Map<String, String> getParams() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("op", requestOp);
		Map<String, Object> md = new HashMap<String,Object>();
		md.put("op", requestOp);
		params.put("sign", MD5Utils.getMD5Sign(md));
		return params;
	}
	@Override
	public String toParamsString() {
		return null;
	}
	@Override
	public String getUrl() {
		return url;
	}
}
