package com.jzg.jzgoto.phone.models.business.valuation.sell;

import java.util.HashMap;
import java.util.Map;

import com.jzg.jzgoto.phone.global.HttpServiceHelper;
import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.tools.MD5Utils;

public class RequestValuationBuyParams extends BaseParamsModels {
//	http://192.168.6.39:4444/APPV3/BuyCarAppraiseResult.ashx?
//	op=GetValuationInfo&sourcetype=3&uid=13&styleid=15190
//	&CityId=201&cityname=北京&&mileage=2.1
//	&regdate=2015-01-01&color=&invoiceprice=12.0&
//	option=04-01-00,04-02-02,04-03-03
//	SellCarAppraiseResult.ashx
	/**
	 * true :买车估值
	 * false 卖车估值
	 * @param isBuy
	 */
	private String mUrl = "";
//	public String sourcetype = "3";
	public String uid = "";
	public String styleid = "";
	public String CityId = "";
	public String cityname = "";
	public String mileage = "";
	public String regdate = "";
	public String color = "";
	public String invoiceprice = "";
	public String option = "";
	public boolean isBuyUrl = true;
	@Override
	public Map<String, String> getParams() {
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("op", "GetValuationInfo");
		map.put("sourcetype", "3");//Sourcetype: 3 安卓 4 苹果
		map.put("uid", uid);
		map.put("styleid", styleid);
		map.put("CityId", CityId);
		map.put("cityname", cityname);
		map.put("mileage", mileage);
		map.put("regdate", regdate);
		map.put("color", color);
		map.put("invoiceprice", invoiceprice);
		map.put("option", option);
		
		Map<String,String> params = new HashMap<String, String>();
		params.put("op", "GetValuationInfo");
		params.put("sourcetype", "3");
		params.put("uid", uid);
		params.put("styleid", styleid);
		params.put("CityId", CityId);
		params.put("cityname", cityname);
		params.put("mileage", mileage);
		params.put("regdate", regdate);
		params.put("color", color);
		params.put("invoiceprice", invoiceprice);
		params.put("option", option);
		
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
		if(isBuyUrl){
			mUrl = HttpServiceHelper.BASE_URL+"/APPV3/BuyCarAppraiseResult.ashx";
			}else{
			mUrl = HttpServiceHelper.BASE_URL+"/APPV3/SellCarAppraiseResult.ashx";
		}
		return mUrl;
	}

}
