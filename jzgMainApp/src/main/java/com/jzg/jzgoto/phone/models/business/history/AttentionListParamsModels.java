package com.jzg.jzgoto.phone.models.business.history;

import java.util.HashMap;
import java.util.Map;

import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.tools.MD5Utils;
/**
 * @Description: 关注列表
 * @Package com.jzg.jzgoto.phone.models.business.history AttentionListParamsModels.java
 * @author gf
 * @date 2015-12-28 上午9:58:22
 */
public class AttentionListParamsModels extends BaseParamsModels {
	// http://10.58.0.68/APPNew/MyCollectionV2.ashx?op=GetMyCollection&uid=748&PageIndex=1 
	private final String url = BASE_URL + "MyCollectionV2.ashx";
	private String uid;
	private String PageIndex;
	private String pagesize="10";
	@Override
	public Map<String, String> getParams() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("op", "GetMyCollection");
		params.put("uid", uid);
		params.put("PageIndex", PageIndex);
		params.put("PageSizes", pagesize);
		Map<String, Object> md = new HashMap<String,Object>();
		md.put("op", "GetMyCollection");
		md.put("uid", uid);
		md.put("PageIndex", PageIndex);
		md.put("PageSizes", pagesize);
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

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getPageIndex() {
		return PageIndex;
	}

	public void setPageIndex(String pageIndex) {
		PageIndex = pageIndex;
	}
}
