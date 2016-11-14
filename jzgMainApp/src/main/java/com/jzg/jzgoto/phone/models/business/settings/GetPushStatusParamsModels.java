package com.jzg.jzgoto.phone.models.business.settings;

import java.util.HashMap;
import java.util.Map;

import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.tools.MD5Utils;
/**
 * @Description: 推送设置
 * @Package com.jzg.jzgoto.phone.models.business.settings GetPushStatusParamsModels.java
 * @author gf
 * @date 2016-1-14 下午8:13:50
 */
public class GetPushStatusParamsModels extends BaseParamsModels {
	
	private final String url = BASE_URL + "Userinfo.ashx";
	public String Id;
	public String Status;
	@Override
	public Map<String, String> getParams() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("op", "UpdatePush");
		
		params.put("Id", Id);
		params.put("Status", Status);
		Map<String, Object> md = new HashMap<String,Object>();
		md.put("op", "UpdatePush");
		
		md.put("Id", Id);
		md.put("Status", Status);
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
