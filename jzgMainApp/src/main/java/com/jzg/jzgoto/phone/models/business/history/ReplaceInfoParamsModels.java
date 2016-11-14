package com.jzg.jzgoto.phone.models.business.history;

import java.util.HashMap;
import java.util.Map;

import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.tools.MD5Utils;
/**
 * @Description: 置换详情
 * @Package com.jzg.jzgoto.phone.models.business.history ReplaceInfoParamsModels.java
 * @author gf
 * @date 2015-12-30 下午4:39:53
 */
public class ReplaceInfoParamsModels extends BaseParamsModels {
	// http://10.58.0.68/APPNew/OldChangeNew.ashx?op=MYZHDetail&ocnid=627  
	private final String url = BASE_URL + "OldChangeNew.ashx";
	public String ocnid;
	@Override
	public Map<String, String> getParams() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("op", "MYZHDetail");
		params.put("ocnid", ocnid);
		Map<String, Object> md = new HashMap<String,Object>();
		md.put("op", "MYZHDetail");
		md.put("ocnid", ocnid);
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
