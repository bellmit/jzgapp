package com.jzg.jzgoto.phone.models.business.settings;

import java.util.HashMap;
import java.util.Map;

import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.tools.MD5Utils;
/**
 * @Description: 意见反馈
 * @Package com.jzg.jzgoto.phone.models.business.settings FeedBackParamsModels.java
 * @author gf
 * @date 2015-12-31 下午2:04:03
 */
public class FeedBackParamsModels extends BaseParamsModels {
	// http://10.58.0.68/APPNew/MyCollectionV2.ashx?op=AddFeedback&uid=359&content=
	private final String url = BASE_URL + "MyCollectionV2.ashx";
	public String uid;
	public String content;
	@Override
	public Map<String, String> getParams() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("op", "AddFeedback");
		
		params.put("uid", uid);
		params.put("content", content);
		Map<String, Object> md = new HashMap<String,Object>();
		md.put("op", "AddFeedback");
		
		md.put("uid", uid);
		md.put("content", content);
		
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
