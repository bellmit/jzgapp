package com.jzg.jzgoto.phone.models.business.user;

import java.util.HashMap;
import java.util.Map;
import com.jzg.jzgoto.phone.global.HttpServiceHelper;
import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.models.common.CommonModelSettings;
import com.jzg.jzgoto.phone.tools.MD5Utils;

public class RequestSubscribeCarParams extends BaseParamsModels {

	private String mUrl = HttpServiceHelper.BASE_URL + "/appv5/CarSource.ashx";
	public String uid = "0";
	public String operateStr = "GetCarSourceConditionsByUid";

	public int PageIndex = 1;
	public int PageSize = CommonModelSettings.PAGE_SIZE;


	@Override
	public Map<String, String> getParams() {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("op", operateStr);
		map.put("uid", uid);
		map.put("PageIndex", String.valueOf(PageIndex));
		map.put("PageSize", String.valueOf(PageSize));

		Map<String, String> params = new HashMap<String, String>();
		params.put("op", operateStr);
		params.put("uid", uid);
		params.put("PageIndex", String.valueOf(PageIndex));
		params.put("PageSize", String.valueOf(PageSize));

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
