package com.jzg.jzgoto.phone.models.business.valuation.sell;

import java.util.HashMap;
import java.util.Map;

import com.jzg.jzgoto.phone.global.HttpServiceHelper;
import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.tools.MD5Utils;

public class RequestSynValHistoryParams extends BaseParamsModels {

//	http://192.168.6.48:9001/APPV3/AppraiseReport.ashx?op=SynchronousData
//	int uid = context.Request["uid"].ParseToInt32();//用户id
//	string historylist = context.Request["historylist"]
	private String mUrl = HttpServiceHelper.BASE_URL +"/APPV3/AppraiseReport.ashx";
	public String uid = "0";
	public String historylist = "";
	@Override
	public Map<String, String> getParams() {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("op", "SynchronousData");
		map.put("uid", uid);
		map.put("SourceType", "3");
		map.put("historylist", historylist);
		
		Map<String,String> params = new HashMap<String, String>();
		params.put("op", "SynchronousData");
		params.put("uid", uid);
		params.put("SourceType", "3");
		params.put("historylist", historylist);
		
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
