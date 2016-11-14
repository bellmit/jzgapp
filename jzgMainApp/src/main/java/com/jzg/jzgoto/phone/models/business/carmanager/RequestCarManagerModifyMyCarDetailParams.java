package com.jzg.jzgoto.phone.models.business.carmanager;

import java.util.HashMap;
import java.util.Map;
import com.jzg.jzgoto.phone.global.HttpServiceHelper;
import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.models.common.CommonModelSettings;
import com.jzg.jzgoto.phone.tools.MD5Utils;

public class RequestCarManagerModifyMyCarDetailParams extends BaseParamsModels {

	private String mUrl =  HttpServiceHelper.BASE_CAR_MANAGER_URL + "/APPV5/CarButler.ashx";
	public String TerminalType = CommonModelSettings.TERMINAL_TYPE_ANDROID;
	public String operateStr = "UpMyLoveCarOtherInfoForOne";
	public String uid = "0";
	public String ButlerId = "0";
	public String MyCarId;
	public String ProvShort;
	public String carNumber;
	public String Vin;
	public String EngineNumber;
	public String JQXianPath;
	public String SYXianPath;

	@Override
	public Map<String, String> getParams() {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("op", operateStr);
		//map.put("TerminalType", TerminalType);
		//map.put("uid", uid);
		//map.put("ButlerId", ButlerId);
		map.put("MyCarId", MyCarId);
		map.put("ProvShort", ProvShort);
		map.put("carNumber", carNumber);
		map.put("Vin", Vin);
		map.put("EngineNumber", EngineNumber);
		map.put("JQXianPath", JQXianPath);
		map.put("SYXianPath", SYXianPath);

		Map<String, String> params = new HashMap<String, String>();
		params.put("op", operateStr);
		//params.put("TerminalType", TerminalType);
		//params.put("uid", uid);
		//params.put("ButlerId", ButlerId);
		params.put("MyCarId", MyCarId);
		params.put("ProvShort", ProvShort);
		params.put("carNumber", carNumber);
		params.put("Vin", Vin);
		params.put("EngineNumber", EngineNumber);
		params.put("JQXianPath", JQXianPath);
		params.put("SYXianPath", SYXianPath);
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
