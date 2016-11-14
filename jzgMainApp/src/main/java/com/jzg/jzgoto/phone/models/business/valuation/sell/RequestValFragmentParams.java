package com.jzg.jzgoto.phone.models.business.valuation.sell;

import java.util.HashMap;
import java.util.Map;

import com.jzg.jzgoto.phone.global.HttpServiceHelper;
import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.tools.MD5Utils;

public class RequestValFragmentParams extends BaseParamsModels {

	private String mUrl = HttpServiceHelper.BASE_URL+"/APPV3/AppraiseReport.ashx";	
	//?op=BannerListAndValuationNum&TerminalType=1&uid=1
	//TerminalType：软件类型 1：PC端 2：M站 3：Android客户端 4：IOS客户端
	public String TerminalType = "0";
	public String uid = "0";
	@Override
	public Map<String, String> getParams() {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("op", "BannerListAndValuationNum");
		map.put("TerminalType", TerminalType);
		map.put("uid", uid);
		
		Map<String,String> params = new HashMap<String, String>();
		params.put("op", "BannerListAndValuationNum");
		params.put("TerminalType", TerminalType );
		params.put("uid", uid );
		
		params.put("sign", MD5Utils.getMD5Sign(map) );
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
