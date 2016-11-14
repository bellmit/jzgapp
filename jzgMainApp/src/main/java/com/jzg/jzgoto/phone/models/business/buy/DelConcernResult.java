package com.jzg.jzgoto.phone.models.business.buy;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;

public class DelConcernResult extends BaseResultModels {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void setResult(Object obj) {
		Gson gson = new Gson();
		DelConcernResult result = gson.fromJson(obj.toString(), DelConcernResult.class);
		setStatus(result.getStatus());
		setMsg(result.getMsg());
	}

	@Override
	public String toResultString() {
		// TODO Auto-generated method stub
		return null;
	}

}
