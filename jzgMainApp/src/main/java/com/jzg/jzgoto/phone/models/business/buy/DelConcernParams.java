package com.jzg.jzgoto.phone.models.business.buy;

import java.util.HashMap;
import java.util.Map;

import com.jzg.jzgoto.phone.global.HttpServiceHelper;
import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.tools.MD5Utils;

public class DelConcernParams extends BaseParamsModels {

	private String mUrl = HttpServiceHelper.BASE_URL+"/appv5/MyCollection.ashx";
	//http://192.168.6.147:9001/appv5/MyCollection.ashx?op=DelMyCollection&id=222
	public String id;
	@Override
	public Map<String, String> getParams() {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("op", "DelMyCollection");
		map.put("id", id);
		Map<String,String> params = new HashMap<String, String>();
		params.put("op", "DelMyCollection");
		params.put("id", id);
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
