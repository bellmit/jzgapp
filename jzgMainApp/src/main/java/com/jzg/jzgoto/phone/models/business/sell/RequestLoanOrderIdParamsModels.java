package com.jzg.jzgoto.phone.models.business.sell;

import java.util.HashMap;
import java.util.Map;

import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.tools.MD5Utils;
/**
 * 请求车贷订单ID
 * @author gf
 * @Date 2015-07-22
 */
public class RequestLoanOrderIdParamsModels extends BaseParamsModels {
	/**
	 * 请求的url
	 */
	private final String mUrl = BASE_HTTP + "/APP/" + "CarOwnerLoan.ashx";
//	private final String mUrl = BASE_URL + "/CarOwnerLoan.ashx";
	/**
	 * 用户名称
	 */
	public String mUserName;
	/**
	 * 用户电话
	 */
	public String mTelNum;
	/**
	 * 验证码
	 */
	public String validCodes;
	@Override
	public Map<String, String> getParams() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("op", "VerificationMobileCodeNew");
		params.put("from", "jz03");
		params.put("username", mUserName);
		params.put("mobile", mTelNum);
		params.put("ValidCodes", validCodes);
		Map<String, Object> md = new HashMap<String,Object>();
		md.put("op", "VerificationMobileCodeNew");
		md.put("from", "jz03");
		md.put("username", mUserName);
		md.put("mobile", mTelNum);
		md.put("ValidCodes", validCodes);
		params.put("sign", MD5Utils.getMD5Sign(md));
		return params;
	}

	@Override
	public String toParamsString() {
		return null;
	}

	@Override
	public String getUrl() {
		return mUrl;
	}

}
