package com.jzg.jzgoto.phone.models.business.sell;

import java.util.HashMap;
import java.util.Map;

import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.tools.MD5Utils;
/**
 * @Description: 根据车型获取时间
 * @Package com.jzg.jzgoto.phone.models.business.sell LoanTimeParamsModels.java
 * @author gf
 * @date 2015-12-31 上午11:01:18
 */
public class LoanTimeParamsModels extends BaseParamsModels {
	private final String url = BASE_HTTP + "/APP/GetMakeAndModelAndStyle.ashx";
//	private final String url = BASE_URL + "GetMakeAndModelAndStyle.ashx";
	public String styleid;
	@Override
	public Map<String, String> getParams() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("op", "GetLowMixYear");
		params.put("styleid", styleid);
		Map<String, Object> md = new HashMap<String, Object>();
		md.put("op", "GetLowMixYear");
		md.put("styleid", styleid);
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
