package com.jzg.jzgoto.phone.models.business.login;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;
/**
 * @Description: 验证码获取
 * @Package com.jzg.jzgoto.phone.models.business.login GetAutoCodeResultModels.java
 * @author gf
 * @date 2015-12-23 下午1:55:00
 */
public class GetAutoCodeResultModels extends BaseResultModels {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	private String MobileCookie;
	@Override
	public void setResult(Object obj) {
		Gson gson = new Gson();
		GetAutoCodeResultModels models = gson.fromJson((String)obj, GetAutoCodeResultModels.class);
		setMsg(models.getMsg());
		setStatus(models.getStatus());
		setMobileCookie(models.getMobileCookie());
	}

	@Override
	public String toResultString() {
		return null;
	}

	public String getMobileCookie() {
		return MobileCookie;
	}

	public void setMobileCookie(String mobileCookie) {
		MobileCookie = mobileCookie;
	}
}
