package com.jzg.jzgoto.phone.models.business.buy;

import java.util.HashMap;
import java.util.Map;

import com.jzg.jzgoto.phone.global.HttpServiceHelper;
import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.tools.MD5Utils;

public class MyConcernParams extends BaseParamsModels {

	private String mUrl = HttpServiceHelper.BASE_URL+"/APPNew/MyCollectionV2.ashx";
	//op=GetMyCollection&uid=748
	public String uid="0";
	public int PageIndex = 1;
	public String PageSizes = "10";
	@Override
	public Map<String, String> getParams() {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("op", "GetMyCollection");
		map.put("uid", uid);
		map.put("PageIndex", String.valueOf(PageIndex));
		map.put("PageSizes", PageSizes);
		Map<String,String> params = new HashMap<String, String>();
		params.put("op", "GetMyCollection");
		params.put("uid", uid);
		params.put("PageIndex", String.valueOf(PageIndex));
		params.put("PageSizes", PageSizes);
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
