package com.jzg.jzgoto.phone.models.business.valuation.sell;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.jzg.jzgoto.phone.global.HttpServiceHelper;
import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.tools.MD5Utils;

public class RequestTipsListParams extends BaseParamsModels implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//	92.168.6.39:4444/APPNew/InvitationInfo.ashx?
//	op=ArticleList&channelId=27&PageIndex=1   
//	channelId: 27 买车小贴士 28 卖车小贴士
	private String mUrl = HttpServiceHelper.BASE_URL+"/APPNew/InvitationInfo.ashx";
	public String channelId = "27";
	public String PageIndex = "1";
	@Override
	public Map<String, String> getParams() {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("op", "ArticleList");
		map.put("channelId", channelId);
		map.put("PageIndex", PageIndex);
		
		Map<String,String> params = new HashMap<String, String>();
		params.put("op", "ArticleList");
		params.put("channelId", channelId);
		params.put("PageIndex", PageIndex);
		
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
