package com.jzg.jzgoto.phone.models.business.settings;

import java.util.HashMap;
import java.util.Map;

import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.tools.MD5Utils;
/**
 * @Description: 编辑个人信息
 * @Package com.jzg.jzgoto.phone.models.business.settings EditUserInfoParmasModels.java
 * @author gf
 * @date 2016-1-5 上午10:46:31
 */
public class EditUserInfoParmasModels extends BaseParamsModels {
	private final String url = BASE_URL + "Userinfo.ashx";
	// op=ModifyPersonalData，Id，LoginName，Gendor，Age，TrueName，mobile，ProvinceName,CityName 
	
	public String Id;
	public String LoginName;
	public String Gendor;
	public String Age;
	
	public String TrueName;
	public String mobile;
	public String ProvinceName;
	public String CityName;
	@Override
	public Map<String, String> getParams() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("op", "ModifyPersonalData");
		
		params.put("Id", Id);
		params.put("LoginName", LoginName);
		params.put("Gendor", Gendor);
		params.put("Age", Age);
		
		params.put("TrueName", TrueName);
		params.put("mobile", mobile);
		params.put("ProvinceName", ProvinceName);
		params.put("CityName", CityName);
		
		Map<String, Object> md = new HashMap<String,Object>();
		md.put("op", "ModifyPersonalData");
		
		md.put("Id", Id);
		md.put("LoginName", LoginName);
		md.put("Gendor", Gendor);
		md.put("Age", Age);
		
		md.put("TrueName", TrueName);
		md.put("mobile", mobile);
		md.put("ProvinceName", ProvinceName);
		md.put("CityName", CityName);
		
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
