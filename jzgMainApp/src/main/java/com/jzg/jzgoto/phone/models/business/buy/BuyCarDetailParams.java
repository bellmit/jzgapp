package com.jzg.jzgoto.phone.models.business.buy;

import java.util.HashMap;
import java.util.Map;

import com.jzg.jzgoto.phone.global.HttpServiceHelper;
import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.tools.MD5Utils;

public class BuyCarDetailParams extends BaseParamsModels {
	private String mUrl = HttpServiceHelper.BASE_URL+"/APPV5/CarSourceDetail.ashx";
// op=GetCarSource&uid=13&CarSourceId=1492&CarSourceFrom=2
	public String uid = "0";//默认不登录状态为"0"
	public String CarSourceId;
	public String CarSourceFrom;
	@Override
	public Map<String, String> getParams() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("op", "GetCarSource");
		map.put("uid", uid);
		map.put("CarSourceId", CarSourceId);
		map.put("CarSourceFrom", CarSourceFrom);
		Map<String,String>params = new HashMap<String, String>();
		params.put("op", "GetCarSource");
		params.put("uid", uid);
		params.put("CarSourceId", CarSourceId);
		params.put("CarSourceFrom", CarSourceFrom);
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
