package com.jzg.jzgoto.phone.models.business.carmanager;

import java.util.HashMap;
import java.util.Map;

import com.jzg.jzgoto.phone.global.HttpServiceHelper;
import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.models.common.CommonModelSettings;
import com.jzg.jzgoto.phone.tools.MD5Utils;

public class RequestCarManagerModifyMyCarMileageParams extends BaseParamsModels {

	private String mUrl = HttpServiceHelper.BASE_CAR_MANAGER_URL + "/APPV5/CarButler.ashx";
	public String TerminalType = CommonModelSettings.TERMINAL_TYPE_ANDROID;
	public String operateStr = "UpMyLoveCarMileageForOne";
	public String uid = "0";
	public String ButlerId = "0";
	public String MyCarId = "";
	public String isGuzhi = "";
	public String mileage = "";

	@Override
	public Map<String, String> getParams() {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("op", operateStr);
		map.put("MyCarId", MyCarId);
		map.put("isGuzhi", isGuzhi);
		map.put("mileage", mileage);

		Map<String, String> params = new HashMap<String, String>();
		params.put("op", operateStr);
		params.put("MyCarId", MyCarId);
		params.put("isGuzhi", isGuzhi);
		params.put("mileage", mileage);
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