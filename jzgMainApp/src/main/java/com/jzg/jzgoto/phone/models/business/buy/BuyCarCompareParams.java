package com.jzg.jzgoto.phone.models.business.buy;

import java.util.HashMap;
import java.util.Map;

import com.jzg.jzgoto.phone.global.HttpServiceHelper;
import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.tools.MD5Utils;

public class BuyCarCompareParams extends BaseParamsModels {

	private String mUrl = HttpServiceHelper.BASE_URL+"/APPNew/CarsourceCompare.ashx";
	//?op=goduibi&userid=13&csinfo=1658|1|116744$1245|2|113850$1272|2|116518
	
	public String userid = "0";
	public String csinfo;
	@Override
	public Map<String, String> getParams() {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("op", "goduibi");
		map.put("userid", userid);
		map.put("csinfo", csinfo);
		Map<String,String> params = new HashMap<String, String>();
		params.put("op", "goduibi");
		params.put("userid", userid);
		params.put("csinfo", csinfo);
		params.put("sign", MD5Utils.getMD5Sign(map));
		return params;
	}

	@Override
	public String toParamsString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUrl() {
		// TODO Auto-generated method stub
		return mUrl;
	}

}
