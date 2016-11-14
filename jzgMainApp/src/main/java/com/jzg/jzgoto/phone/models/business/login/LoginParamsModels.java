package com.jzg.jzgoto.phone.models.business.login;

import java.util.HashMap;
import java.util.Map;

import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.tools.MD5Utils;
/**
 * @Description: 登录请求参数 
 * @Package com.jzg.jzgoto.phone.models.business.login LoginParamsModels.java
 * @author gf
 * @date 2015-12-23 上午9:43:12
 */
public class LoginParamsModels extends BaseParamsModels {
	private final String url = BASE_URL + "Userinfo.ashx";
	public String mLoginName;
	public String validCodes;
	public String CarButlerId;
//	op=RegistOrLoginNew&RegistMobile=13261297798&ValidCodes=755403
	@Override
	public Map<String, String> getParams() {
		Map<String, String> params = new HashMap<String, String>();
	//	params.put("op", "RegistOrLogin");
		params.put("op", "RegistOrLoginNew");
		params.put("RegistMobile", mLoginName);
		params.put("ValidCodes", validCodes);
		params.put("SourceType", "3");
		params.put("CarButlerId", CarButlerId);
		Map<String, Object> md = new HashMap<String,Object>();
	//	md.put("op", "RegistOrLogin");
		md.put("op", "RegistOrLoginNew");
		md.put("RegistMobile", mLoginName);
		md.put("ValidCodes", validCodes);
		md.put("SourceType", "3");
		md.put("CarButlerId", CarButlerId);
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
