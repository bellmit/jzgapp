package com.jzg.jzgoto.phone.models.business.carmanager;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.jzg.jzgoto.phone.global.HttpServiceHelper;
import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.models.common.CommonModelSettings;
import com.jzg.jzgoto.phone.tools.MD5Utils;

public class RequestCarManagerMainDataParams extends BaseParamsModels {

	private String mUrl = HttpServiceHelper.BASE_CAR_MANAGER_URL + "/APPV5/CarButler.ashx";
	public String TerminalType = "3";//CommonModelSettings.TERMINAL_TYPE_ANDROID;
	public String uid = "0";
	public String ButlerId = "0";
	public String operateStr = "GetCarButlerInfo";
	public String cityname;

	@Override
	public Map<String, String> getParams() {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("op", operateStr);
		map.put("ButlerId", ButlerId);
		map.put("cityname", cityname);

		Map<String, String> params = new HashMap<String, String>();
		params.put("op", operateStr);
		params.put("ButlerId", ButlerId);
		params.put("cityname", cityname);
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
