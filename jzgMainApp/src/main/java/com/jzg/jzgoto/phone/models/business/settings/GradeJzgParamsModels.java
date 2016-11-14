package com.jzg.jzgoto.phone.models.business.settings;

import java.util.HashMap;
import java.util.Map;

import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.tools.MD5Utils;
/**
 * @Description: 打分
 * @Package com.jzg.jzgoto.phone.models.business.settings GradeJzgParamsModels.java
 * @author gf
 * @date 2016-1-12 下午5:06:04
 */
public class GradeJzgParamsModels extends BaseParamsModels {
//	private final String url = "http://10.58.0.64:8003/APPNew/InvitationInfo.ashx";
	private final String url = BASE_URL + "InvitationInfo.ashx";
	
	public String uid;
	public String type;
	@Override
	public Map<String, String> getParams() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("op", "addScore");
		
		params.put("uid", uid);
		params.put("type", type);
		Map<String, Object> md = new HashMap<String,Object>();
		md.put("op", "addScore");
		
		md.put("uid", uid);
		md.put("type", type);
		
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
