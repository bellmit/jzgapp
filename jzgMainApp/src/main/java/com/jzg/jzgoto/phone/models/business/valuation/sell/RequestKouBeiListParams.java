package com.jzg.jzgoto.phone.models.business.valuation.sell;

import java.util.HashMap;
import java.util.Map;

import com.jzg.jzgoto.phone.global.HttpServiceHelper;
import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.tools.MD5Utils;

public class RequestKouBeiListParams extends BaseParamsModels {
//	http://192.168.6.39:4444?
//		op=GetKoubeiList&ModelId=2971&type=1&PageIndex=1&PageSize=5
	private String mUrl = HttpServiceHelper.BASE_URL +"/APPV3/BuyCarAppraiseResult.ashx";
	public String ModelId = "0";
	public String type = "0";//	Type /1 优点 2 缺点
	public String PageIndex = "";
	@Override
	public Map<String, String> getParams() {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("op", "GetKoubeiList");
		map.put("PageSize", "5");
		map.put("ModelId", ModelId);
		map.put("type", type);
		map.put("PageIndex", PageIndex);
		
		Map<String,String> params = new HashMap<String, String>();
		params.put("op", "GetKoubeiList");
		params.put("PageSize", "5");
		params.put("ModelId", ModelId );
		params.put("type", type );
		params.put("PageIndex", PageIndex);
		
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
