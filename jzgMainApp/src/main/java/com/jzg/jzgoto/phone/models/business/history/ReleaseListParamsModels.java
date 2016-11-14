package com.jzg.jzgoto.phone.models.business.history;

import java.util.HashMap;
import java.util.Map;

import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.tools.MD5Utils;
/**
 * @Description: 发布列表结果
 * @Package com.jzg.jzgoto.phone.models.business.history ReleaseListParamsModels.java
 * @author gf
 * @date 2015-12-29 下午2:46:30
 */
public class ReleaseListParamsModels extends BaseParamsModels {
	// http://10.58.0.68/APPNew/MyCollectionV2.ashx?op=GetMySendCarList&uid=13&PageIndex=2 
	private final String url = BASE_URL + "MyCollectionV2.ashx";
	
	public String uid;
	public String PageIndex;
	@Override
	public Map<String, String> getParams() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("op", "GetMySendCarList");
		params.put("uid", uid);
		params.put("PageIndex", PageIndex);
		Map<String, Object> md = new HashMap<String,Object>();
		md.put("op", "GetMySendCarList");
		md.put("uid", uid);
		md.put("PageIndex", PageIndex);
		params.put("sign", MD5Utils.getMD5Sign(md));
		return params;
	}

	@Override
	public String toParamsString() {
		return null;
	}

	@Override
	public String getUrl() {
		return url;
	}

}
