package com.jzg.jzgoto.phone.models.business.valuation.buy;

import java.util.HashMap;
import java.util.Map;

import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.tools.MD5Utils;
/**
 * @Description: 买车估值计算使用成本
 * @Package com.jzg.jzgoto.phone.models.business.valuation.buy GetCostYearParamsModels.java
 * @author gf
 * @date 2016-6-15 下午3:03:12
 */
public class GetCostYearParamsModels extends BaseParamsModels {
	private final String url = BASE_URL_V3 + "AppraiseReport.ashx";
	
	/**
	 * 来源，android还是ios
	 * android:3    IOS:4
	 */
	private String sourcetype = "3";
	/**
	 * 车型ID
	 */
	public String styleid;
	/**
	 * 省ID
	 */
	public String ProvinceId = "";
	/**
	 * 省名称
	 */
	public String provname = "";
	/**
	 * 市ID
	 */
	public String CityId;
	/**
	 * 市名称
	 */
	public String cityname = "";
	/**
	 * 公里数
	 */
	public String mileage;
	/**
	 * 上牌时间
	 */
	public String regdate;
	
	@Override
	public Map<String, String> getParams() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("op", "CostYear");
		
		params.put("sourcetype", sourcetype);
		params.put("styleid", styleid);
		params.put("ProvinceId", ProvinceId);
		
		params.put("provname", provname );
		params.put("CityId", CityId );
		params.put("cityname", cityname );
		params.put("mileage", mileage );
		params.put("regdate", regdate );
		
		Map<String, Object> md = new HashMap<String,Object>();
		md.put("op", "CostYear");
		md.put("sourcetype", sourcetype);
		md.put("styleid", styleid);
		md.put("ProvinceId", ProvinceId);
		
		md.put("provname", provname );
		md.put("CityId", CityId );
		md.put("cityname", cityname );
		md.put("mileage", mileage );
		md.put("regdate", regdate );
		
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
