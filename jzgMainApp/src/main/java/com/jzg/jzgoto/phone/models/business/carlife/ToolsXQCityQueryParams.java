package com.jzg.jzgoto.phone.models.business.carlife;

import java.util.HashMap;
import java.util.Map;

import com.jzg.jzgoto.phone.global.HttpServiceHelper;
import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.tools.MD5Utils;

public class ToolsXQCityQueryParams extends BaseParamsModels {

	private String mUrl =HttpServiceHelper.BASE_URL_COMMON_valuation_new+"/app/bbs/CarSource/GetCityStandard.ashx?";
	public String cityId;
	@Override
	public Map<String, String> getParams() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cityId", cityId);
		Map<String, String> params = new HashMap<String, String>();
		params.put("cityId", cityId);
		params.put("sign", MD5Utils.getMD5Sign(map));
		return params;
	}

	@Override
	public String toParamsString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUrl() {
		// TODO Auto-generated method stub
		return mUrl;
	}

}
