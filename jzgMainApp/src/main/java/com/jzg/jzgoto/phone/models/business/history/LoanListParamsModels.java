package com.jzg.jzgoto.phone.models.business.history;

import java.util.HashMap;
import java.util.Map;

import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.tools.MD5Utils;
/**
 * @Description: 车抵贷列表
 * @Package com.jzg.jzgoto.phone.models.business.history LoanListParamsModels.java
 * @author gf
 * @date 2015-12-29 下午2:48:11
 */
public class LoanListParamsModels extends BaseParamsModels {
	
	private final String url = BASE_URL + "MyCollectionV2.ashx";
	
	@Override
	public Map<String, String> getParams() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("op", "GetMyCollection");
		Map<String, Object> md = new HashMap<String,Object>();
		md.put("op", "GetMyCollection");
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
