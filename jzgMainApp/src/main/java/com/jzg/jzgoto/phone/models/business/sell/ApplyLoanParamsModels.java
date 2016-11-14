package com.jzg.jzgoto.phone.models.business.sell;

import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.tools.MD5Utils;

import java.util.HashMap;
import java.util.Map;
/**
 * @Description: 申请贷款
 * @Package com.jzg.jzgoto.phone.models.business.sell ApplyLoanParamsModels.java
 * @author gf
 * @date 2015-12-30 上午10:37:34
 */
public class ApplyLoanParamsModels extends BaseParamsModels {
	
	private final String url = BASE_HTTP + "/APP/" + "CarOwnerLoan.ashx";
//	private final String url = BASE_URL + "CarOwnerLoan.ashx";
	/**
	 * 来源
	 */
	public String from = "3";
	/**
	 * 
	 */
	public String orderid;
	/**
	 * 省ID
	 */
	public String provinceid;
	/**
	 * 城市ID
	 */
	public String cityid;
	/**
	 * 贷款期限
	 */
	public String loanperiod;
	/**
	 * 用户ID
	 */
	public String uid;
	/**
	 * 电话
	 */
	public String mobile;
	/**
	 * 用户名
	 */
	public String username;
	/**
	 * 车型ID
	 */
	public String styleid;
	/**
	 * 行驶里程
	 */
	public String mileage;
	/**
	 * 上牌时间
	 */
	public String regdate;
	/**
	 * 0.无，1.有
	 */
	public String hasMortgate;
	/**
	 * 男女
	 */
	public String gender;
	/**
	 * 年龄
	 */
	public String age;
	/**
	 * 信用记录
	 */
	public String credit;
	public String cityname;
	
	@Override
	public Map<String, String> getParams() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("op", "CarOenerLoan");
		
		params.put("from", from);
		params.put("orderid", orderid);
		params.put("provinceid", provinceid);
		params.put("cityid", cityid);
		
		params.put("loanperiod", loanperiod);
		params.put("uid", uid);
		params.put("mobile", mobile);
		params.put("username", username);
		params.put("styleid", styleid);
		params.put("mileage", mileage);
		params.put("regdate", regdate);
		params.put("hasMortgate", hasMortgate);
		params.put("gender", gender);
		params.put("age", age);
		params.put("credit", credit);
		params.put("cityname", cityname);

		Map<String, Object> md = new HashMap<String,Object>();
		md.put("op", "CarOenerLoan");
		
		md.put("from", from);
		md.put("orderid", orderid);
		md.put("provinceid", provinceid);
		md.put("cityid", cityid);
		
		md.put("loanperiod", loanperiod);
		md.put("uid", uid);
		md.put("mobile", mobile);
		md.put("username", username);
		md.put("styleid", styleid);
		md.put("mileage", mileage);
		md.put("regdate", regdate);
		md.put("hasMortgate", hasMortgate);
		md.put("gender", gender);
		md.put("age", age);
		md.put("credit", credit);
		md.put("cityname", cityname);

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
