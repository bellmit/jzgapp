package com.jzg.jzgoto.phone.models.business.settings;

import java.util.HashMap;
import java.util.Map;

import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.tools.MD5Utils;
/**
 * @Description: 修改头像
 * @Package com.jzg.jzgoto.phone.models.business.settings ChangePersonPicParamsModels.java
 * @author gf
 * @date 2016-1-5 上午10:55:46
 */
public class ChangePersonPicParamsModels extends BaseParamsModels {
	private final String url = BASE_URL + "Userinfo.ashx";
	// op=UpdateUserImage,uid,imgpath
	public String uid;
	public String imgpath;
	@Override
	public Map<String, String> getParams() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("op", "UpdateUserImage");
		
		params.put("uid", uid);
		params.put("imgpath", imgpath);
		
		Map<String, Object> md = new HashMap<String,Object>();
		md.put("op", "UpdateUserImage");
		
		md.put("uid", uid);
		md.put("imgpath", imgpath);
		
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
