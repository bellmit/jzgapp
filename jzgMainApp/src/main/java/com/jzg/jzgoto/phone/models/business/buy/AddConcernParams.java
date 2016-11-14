package com.jzg.jzgoto.phone.models.business.buy;

import java.util.HashMap;
import java.util.Map;

import com.jzg.jzgoto.phone.global.HttpServiceHelper;
import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.tools.MD5Utils;

public class AddConcernParams extends BaseParamsModels {
	private String mUrl =HttpServiceHelper.BASE_URL+ "/appv5/MyCollection.ashx";

	//op=AddMyCollection&CarSourceId=%20197&CarSourceFrom=7&uid=1034
	public String uid;
	public String CarSourceId;
	public String CarSourceFrom;
	@Override
	public Map<String, String> getParams() {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("op", "AddMyCollection");
		map.put("uid", uid);
		map.put("CarSourceId", CarSourceId);
		map.put("CarSourceFrom", CarSourceFrom);
		Map<String,String> params = new HashMap<String, String>();
		params.put("op", "AddMyCollection");
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
