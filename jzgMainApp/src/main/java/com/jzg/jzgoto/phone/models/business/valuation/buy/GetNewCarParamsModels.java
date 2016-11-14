package com.jzg.jzgoto.phone.models.business.valuation.buy;

import java.util.HashMap;
import java.util.Map;

import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.tools.MD5Utils;
/**
 * @Description: 获取推荐新车
 * @Package com.jzg.jzgoto.phone.models.business.valuation.buy GetNewCarParamsModels.java
 * @author gf
 * @date 2016-6-14 上午10:47:54
 */
public class GetNewCarParamsModels extends BaseParamsModels {
	private final String url = BASE_URL_V3 + "BuyCarAppraiseResult.ashx";
	/**
	 * 指导价
	 */
	public String NowMsrp;
	/**
	 * 车系级别
	 */
	public String ModelLevelId;
	/**
	 * 页码
	 */
	public int PageIndex;
	/**
	 * 条数
	 */
	public int PageSize;
	
	@Override
	public Map<String, String> getParams() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("op", "GetTjNewCar");
		
		params.put("NowMsrp", NowMsrp);
		params.put("ModelLevelId", ModelLevelId);
		params.put("PageIndex", PageIndex+"");
		
		params.put("PageSize", PageSize+"");
		
		Map<String, Object> md = new HashMap<String,Object>();
		md.put("op", "GetTjNewCar");

		md.put("NowMsrp", NowMsrp);
		md.put("ModelLevelId", ModelLevelId);
		md.put("PageIndex", PageIndex+"");
		
		md.put("PageSize", PageSize+"");

		
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
