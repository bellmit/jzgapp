package com.jzg.jzgoto.phone.models.business.sell;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.tools.MD5Utils;
/**
 * @Description: 请求经销商列表
 * @Package com.jzg.jzgoto.phone.models.business.sell RequestDealersMsgParamsModels.java
 * @author gf
 * @date 2015-12-25 下午2:10:52
 */
public class RequestDealersMsgParamsModels extends BaseParamsModels implements Serializable{
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;

	// http://10.58.0.68/APPNew/OldChangeNew.ashx?op=JXSList&CityId=201&StyleId=115634,115633
	private final String url = BASE_URL + "OldChangeNew.ashx";
	
	private String CityId;
	private String StyleId;
	
	@Override
	public Map<String, String> getParams() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("op", "JXSList");
		
		params.put("CityName", CityId);
		params.put("StyleId", StyleId);
		
		Map<String, Object> md = new HashMap<String,Object>();
		md.put("op", "JXSList");
		md.put("CityName", CityId);
		md.put("StyleId", StyleId);
		
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

	public String getCityId() {
		return CityId;
	}

	public void setCityId(String cityId) {
		CityId = cityId;
	}

	public String getStyleId() {
		return StyleId;
	}

	public void setStyleId(String styleId) {
		StyleId = styleId;
	}
}
