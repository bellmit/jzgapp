package com.jzg.jzgoto.phone.models.business.valuation.sell;

import java.util.HashMap;
import java.util.Map;

import com.jzg.jzgoto.phone.global.HttpServiceHelper;
import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.tools.MD5Utils;

public class RequestTJCarListParams extends BaseParamsModels {
//	http://192.168.6.39:4444?
//	op=GetSellTjoldCar&ProvinceId=2&CityId=201&startPrice=3.1
//	&endPrice=100.1&PageIndex=1&PageSize=5
	private String mUrl = HttpServiceHelper.BASE_URL +"/APPV3/SellCarAppraiseResult.ashx";
	public String ProvinceId = "0";
	public String CityId = "0";
	public String startPrice = "";
	public String endPrice = "";
	public String PageIndex = "1";
	@Override
	public Map<String, String> getParams() {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("op", "GetSellTjoldCar");
		map.put("PageSize", "5");
		map.put("ProvinceId", ProvinceId);
		map.put("CityId", CityId);
		map.put("startPrice", startPrice);
		map.put("endPrice", endPrice);
		map.put("PageIndex", PageIndex);
		
		Map<String,String> params = new HashMap<String, String>();
		params.put("op", "GetSellTjoldCar");
		params.put("PageSize", "5");
		params.put("ProvinceId", ProvinceId );
		params.put("CityId", CityId);
		params.put("startPrice", startPrice);
		params.put("endPrice", endPrice);
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
