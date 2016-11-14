package com.jzg.jzgoto.phone.models.business.history;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;
/**
 * @Description: 车抵贷列表
 * @Package com.jzg.jzgoto.phone.models.business.history LoanListResultModels.java
 * @author gf
 * @date 2015-12-29 下午2:47:56
 */
public class LoanListResultModels extends BaseResultModels {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void setResult(Object obj) {
		Gson gson = new Gson();
		LoanListResultModels models = gson.fromJson((String)obj, LoanListResultModels.class);
		setMsg(models.getMsg());
		setStatus(models.getStatus());
	}

	@Override
	public String toResultString() {
		return null;
	}

}
