package com.jzg.jzgoto.phone.models.business.buy;

import java.util.HashMap;
import java.util.Map;

import com.jzg.jzgoto.phone.global.HttpServiceHelper;
import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.tools.MD5Utils;

public class SimilarCarSourceParams extends BaseParamsModels {

//	APPNEW/CarSourceV4.ashx
//	op=SimilarCarSource 
//	StyleID
//	ProvID
//	CityID
//	SellPrice

	private String mUrl = HttpServiceHelper.BASE_URL+"/APPNEW/CarSourceV4.ashx";
	public String StyleID;
	public String ProvID;
	public String CityID;
	public String SellPrice;
	public String CarSourceID;
	public String CarSourceFrom;
	
	@Override
	public Map<String, String> getParams() {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("op", "SimilarCarSource");
		map.put("StyleID", StyleID);
		map.put("ProvID", ProvID);
		map.put("CityID", CityID);
		map.put("SellPrice", SellPrice);
		map.put("CarSourceID", CarSourceID);
		map.put("CarSourceFrom", CarSourceFrom);
		Map<String,String> params = new HashMap<String, String>();
		params.put("op", "SimilarCarSource");
		params.put("StyleID", StyleID);
		params.put("ProvID", ProvID);
		params.put("CityID", CityID);
		params.put("SellPrice", SellPrice);
		params.put("CarSourceID", CarSourceID);
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
