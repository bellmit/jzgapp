package com.jzg.jzgoto.phone.models.business.history;

import java.util.HashMap;
import java.util.Map;

import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.tools.MD5Utils;
/**
 * @Description: 删除置换
 * @Package com.jzg.jzgoto.phone.models.business.history DelReplaceItemParamsModels.java
 * @author gf
 * @date 2015-12-29 下午2:55:55
 */
public class DelReplaceItemParamsModels extends BaseParamsModels {
	// http://10.58.0.68/APPNew/OldChangeNew.ashx?op=delZH&Collecid=
	private final String url = BASE_URL + "OldChangeNew.ashx";
	public String Collecid;
	@Override
	public Map<String, String> getParams() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("op", "delZH");
		params.put("Collecid", Collecid);
		Map<String, Object> md = new HashMap<String,Object>();
		md.put("op", "delZH");
		md.put("Collecid", Collecid);
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
