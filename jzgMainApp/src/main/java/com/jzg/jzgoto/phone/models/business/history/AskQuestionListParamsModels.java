package com.jzg.jzgoto.phone.models.business.history;

import java.util.HashMap;
import java.util.Map;

import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.tools.MD5Utils;
/**
 * @Description: 提问列表
 * @Package com.jzg.jzgoto.phone.models.business.history AskQuestionListParamsModels.java
 * @author gf
 * @date 2016-1-6 上午9:34:32
 */
public class AskQuestionListParamsModels extends BaseParamsModels {
	// http://10.58.0.68/APPNew/InvitationInfo.ashx?op=MyInviationInfo&PageIndex=1&uid=668 
	private final String url = BASE_URL + "InvitationInfo.ashx";
	public String PageIndex;
	public String uid;
	@Override
	public Map<String, String> getParams() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("op", "MyInviationInfo");
		params.put("uid", uid);
		params.put("PageIndex", PageIndex);
		params.put("uid", uid);
		Map<String, Object> md = new HashMap<String,Object>();
		md.put("op", "MyInviationInfo");
		md.put("uid", uid);
		md.put("PageIndex", PageIndex);
		md.put("uid", uid);
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
