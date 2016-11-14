package com.jzg.jzgoto.phone.models.business.carmanager;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.jzg.jzgoto.phone.global.HttpServiceHelper;
import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.models.common.CommonModelSettings;
import com.jzg.jzgoto.phone.tools.MD5Utils;

public class RequestCarManagerAddMyCarParams extends BaseParamsModels {

	private String mUrl = HttpServiceHelper.BASE_CAR_MANAGER_URL + "/APPV5/CarButler.ashx";
	public String TerminalType = CommonModelSettings.TERMINAL_TYPE_ANDROID;
	public String operateStr = "AddMyLoveCar";
	public String uid = "0";
	public String ButlerId = "0";
	public String styleId;
	public String CityId;
	public String cityname;
	public String mileage;
	public String Regdate;

	@Override
	public Map<String, String> getParams() {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("op", operateStr);
		map.put("uid", uid);
		map.put("ButlerId", ButlerId);
		map.put("styleId", styleId);
		map.put("CityId", CityId);
		map.put("cityname", cityname);
		map.put("mileage", mileage);
		map.put("Regdate", Regdate);

		Map<String, String> params = new HashMap<String, String>();
		params.put("op", operateStr);
		params.put("uid", uid);
		params.put("ButlerId", ButlerId);
		params.put("styleId", styleId);
		params.put("CityId", CityId);
		params.put("cityname", cityname);
		params.put("mileage", mileage);
		params.put("Regdate", Regdate);
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
