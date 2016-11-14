package com.jzg.jzgoto.phone.models.business.car;

import java.util.HashMap;
import java.util.Map;

import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.tools.MD5Utils;
/**
 * @Description: 车系请求参数
 * @Package com.jzg.jzgoto.phone.models.business.car CarModelParamsModels.java
 * @author gf
 * @date 2016-6-17 上午10:10:21
 */
public class CarModelParamsModels extends BaseParamsModels {
	private final String url = BASE_URL_V3 + "GetMakeModelStyleAll.ashx";
	public static final String MODEL = "getPreModel";
	public static final String MODEL_VALU = "GetValuationModel";
	public String requestOp = MODEL;
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
