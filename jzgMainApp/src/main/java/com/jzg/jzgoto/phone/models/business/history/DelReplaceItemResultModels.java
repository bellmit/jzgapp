package com.jzg.jzgoto.phone.models.business.history;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;
/**
 * @Description: 删除置换
 * @Package com.jzg.jzgoto.phone.models.business.history DelReplaceItemResultModels.java
 * @author gf
 * @date 2015-12-29 下午2:55:33
 */
public class DelReplaceItemResultModels extends BaseResultModels {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void setResult(Object obj) {
		Gson gson = new Gson();
		DelReplaceItemResultModels models = gson.fromJson((String)obj, DelReplaceItemResultModels.class);
		setMsg(models.getMsg());
		setStatus(models.getStatus());
	}

	@Override
	public String toResultString() {
		return null;
	}

}
