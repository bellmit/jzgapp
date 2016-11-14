package com.jzg.jzgoto.phone.models.business.valuation.buy;

import java.util.HashMap;
import java.util.Map;

import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.tools.MD5Utils;
/**
 * @Description: 满意度调查
 * @Package com.jzg.jzgoto.phone.models.business.valuation.buy SendAppraiseParamsModels.java
 * @author gf
 * @date 2016-6-8 上午10:29:49
 */
public class SendAppraiseParamsModels extends BaseParamsModels {
	private final String url = BASE_URL_V3 + "BuyCarAppraiseResult.ashx";
	/**
	 * 3 安卓  4 苹果
	 */
	public String Sourcetype = "3";
	/**
	 * 车型ID
	 */
	public String Styleid;
	/**
	 * 省ID
	 */
	public String ProvinceId;
	/**
	 * 市ID
	 */
	public String CityId;
	/**
	 * 行驶里程 万
	 */
	public String Mileage;
	/**
	 * 上牌时间
	 */
	public String Regdate;
	/**
	 * 1 偏低 2 合适 3 偏高
	 */
	public String Satisfied;
	/**
	 * 估值类型  1、我是车主 2、我要买车
	 */
	public String guzhiType;
	
	@Override
	public Map<String, String> getParams() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("op", "AppraiserIsSatisfied");
		
		params.put("Sourcetype", Sourcetype);
		params.put("Styleid", Styleid);
		params.put("ProvinceId", ProvinceId);
		
		params.put("CityId", CityId);
		params.put("Mileage", Mileage);
		params.put("Regdate", Regdate);
		params.put("Satisfied", Satisfied);
		params.put("guzhiType", guzhiType);
		
		Map<String, Object> md = new HashMap<String,Object>();
		md.put("op", "AppraiserIsSatisfied");

		md.put("Sourcetype", Sourcetype);
		md.put("Styleid", Styleid);
		md.put("ProvinceId", ProvinceId);
		
		md.put("CityId", CityId);
		md.put("Mileage", Mileage);
		md.put("Regdate", Regdate);
		md.put("Satisfied", Satisfied);
		md.put("guzhiType", guzhiType);
		
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
