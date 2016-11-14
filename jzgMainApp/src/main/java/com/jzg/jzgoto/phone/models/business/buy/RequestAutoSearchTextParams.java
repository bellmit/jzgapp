package com.jzg.jzgoto.phone.models.business.buy;

import java.util.HashMap;
import java.util.Map;

import com.jzg.jzgoto.phone.global.HttpServiceHelper;
import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.tools.MD5Utils;

public class RequestAutoSearchTextParams extends BaseParamsModels {
	private final String mUrl = HttpServiceHelper.BASE_URL+"/APPV5/Carsearchsuggest.ashx";
	public String searchValue;
	
	@Override
	public Map<String, String> getParams() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchvalue", searchValue);
		Map <String, String>params = new HashMap<String, String>();
		params.put("searchvalue", searchValue);
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
