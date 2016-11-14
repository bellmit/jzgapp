package com.jzg.jzgoto.phone.models.business.login;

import java.util.HashMap;
import java.util.Map;

import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.tools.MD5Utils;
/**
 * @Description: 获取验证码
 * @Package com.jzg.jzgoto.phone.models.business.login GetAutoCodeParamsModels.java
 * @author gf
 * @date 2015-12-23 下午1:45:28
 */
public class GetAutoCodeParamsModels extends BaseParamsModels {
	private final String url = BASE_URL + "Userinfo.ashx";
	public String mMobile;
	@Override
	public Map<String, String> getParams() {
		Map<String, String> params = new HashMap<String, String>();
		
	//	params.put("op", "SendMobileCodeOnly");
		params.put("op", "SendMobileCodeOnlyNew");
		params.put("mobile", mMobile);
		Map<String, Object> md = new HashMap<String,Object>();
	//	md.put("op", "SendMobileCodeOnly");
		md.put("op", "SendMobileCodeOnlyNew");
		md.put("mobile", mMobile);
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
