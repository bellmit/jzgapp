package com.jzg.jzgoto.phone.models.business.valuation.buy;

import java.util.HashMap;
import java.util.Map;

import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.tools.MD5Utils;
/**
 * @Description: 获取推荐旧车
 * @Package com.jzg.jzgoto.phone.models.business.valuation.buy GetTjOldCarParamsModels.java
 * @author gf
 * @date 2016-6-14 下午3:12:14
 */
public class GetTjOldCarParamsModels extends BaseParamsModels {
	private final String url = BASE_URL_V3 + "BuyCarAppraiseResult.ashx";
	/**
	 * 省ID
	 */
	public String ProvinceId;
	/**
	 * 市ID
	 */
	public String CityId;
	/**
	 * 开始价
	 */
	public String startPrice;
	/**
	 * 结束价
	 */
	public String endPrice;
	/**
	 * 页码
	 */
	public int PageIndex = 1;
	/**
	 * 条数
	 */
	public int PageSize = 5;
	
	@Override
	public Map<String, String> getParams() {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("op", "GetTjoldCar");
		map.put("PageSize", PageSize);
		map.put("ProvinceId", ProvinceId);
		map.put("CityId", CityId);
		map.put("startPrice", startPrice);
		map.put("endPrice", endPrice);
		map.put("PageIndex", PageIndex );
		
		Map<String,String> params = new HashMap<String, String>();
		params.put("op", "GetTjoldCar");
		params.put("PageSize", PageSize+"");
		params.put("ProvinceId", ProvinceId );
		params.put("CityId", CityId);
		params.put("startPrice", startPrice);
		params.put("endPrice", endPrice);
		params.put("PageIndex", PageIndex +"");
		
		params.put("sign", MD5Utils.getMD5Sign(map) );
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
