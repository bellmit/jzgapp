package com.jzg.jzgoto.phone.models.business.history;

import java.util.HashMap;
import java.util.Map;

import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.tools.MD5Utils;
/**
 * @Description: 置换列表结果
 * @Package com.jzg.jzgoto.phone.models.business.history ReplaceListParamsModels.java
 * @author gf
 * @date 2015-12-29 下午2:42:43
 */
public class ReplaceListParamsModels extends BaseParamsModels {
	// http://10.58.0.68/APPNew/OldChangeNew.ashx?op=GetNewCarZhInfo&id=668&pageindex=2&pagesize=10
	private final String url = BASE_URL + "OldChangeNew.ashx";
	
	public String id;
	public String pageindex;
	public String pagesize;
	
	@Override
	public Map<String, String> getParams() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("op", "GetNewCarZhInfo");
		params.put("id", id);
		params.put("pageindex", pageindex);
		params.put("pagesize", pagesize);
		Map<String, Object> md = new HashMap<String,Object>();
		md.put("op", "GetNewCarZhInfo");
		md.put("id", id);
		md.put("pageindex", pageindex);
		md.put("pagesize", pagesize);
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
