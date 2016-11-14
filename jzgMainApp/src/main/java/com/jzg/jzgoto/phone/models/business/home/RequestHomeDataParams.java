package com.jzg.jzgoto.phone.models.business.home;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.jzg.jzgoto.phone.global.HttpServiceHelper;
import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.models.common.CommonModelSettings;
import com.jzg.jzgoto.phone.tools.MD5Utils;

public class RequestHomeDataParams extends BaseParamsModels {

	private String mUrl = HttpServiceHelper.BASE_URL + "/appv5/HomeInfo.ashx";
	public String TerminalType = "3";//CommonModelSettings.TERMINAL_TYPE_ANDROID;
	public String uid = "0";
	public String Type = "3";
	public String operateStr = "BannerAndCard";

	@Override
	public Map<String, String> getParams() {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("op", operateStr);
		map.put("TerminalType", TerminalType);
		map.put("Type", Type);

		Map<String, String> params = new HashMap<String, String>();
		params.put("op", operateStr);
		params.put("TerminalType", TerminalType);
		params.put("Type", Type);
		
		params.put("sign", MD5Utils.getMD5Sign(map) );
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
