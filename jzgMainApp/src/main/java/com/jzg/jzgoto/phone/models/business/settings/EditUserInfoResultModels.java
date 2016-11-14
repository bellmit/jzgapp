package com.jzg.jzgoto.phone.models.business.settings;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;
/**
 * @Description: 编辑个人信息
 * @Package com.jzg.jzgoto.phone.models.business.settings EditUserInfoResultModels.java
 * @author gf
 * @date 2016-1-5 上午10:45:20
 */
public class EditUserInfoResultModels extends BaseResultModels {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void setResult(Object obj) {
		Gson gson = new Gson();
		EditUserInfoResultModels models = gson.fromJson((String)obj, EditUserInfoResultModels.class);
		setMsg(models.getMsg());
		setStatus(models.getStatus());
	}
	@Override
	public String toResultString() {
		return null;
	}
}
