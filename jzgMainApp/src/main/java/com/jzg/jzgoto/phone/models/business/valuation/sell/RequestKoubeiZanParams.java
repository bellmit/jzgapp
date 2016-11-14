package com.jzg.jzgoto.phone.models.business.valuation.sell;

import java.util.HashMap;
import java.util.Map;

import com.jzg.jzgoto.phone.global.HttpServiceHelper;
import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.tools.MD5Utils;

public class RequestKoubeiZanParams extends BaseParamsModels {
//	http://192.168.6.39:4444/APPV3/BuyCarAppraiseResult.ashx?
//		op=KouBeiClickFavor&Id=1
	private String mUrl = HttpServiceHelper.BASE_URL +"/APPV3/BuyCarAppraiseResult.ashx";
	public String Id = "0";
	public String type = "";//1：优点，2：缺点
	@Override
	public Map<String, String> getParams() {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("op", "KouBeiClickFavor");
		map.put("Id", Id);
		map.put("type", type);
		
		Map<String,String> params = new HashMap<String, String>();
		params.put("op", "KouBeiClickFavor");
		params.put("Id", Id);
		params.put("type", type);
		
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
