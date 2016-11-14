package com.jzg.jzgoto.phone.models.business.valuation.sell;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;

public class RequestKoubeiZanResult extends BaseResultModels {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void setResult(Object obj) {
		System.out.println("口碑列表点赞——"+obj.toString());
		Gson gson = new Gson();
		RequestKoubeiZanResult result = gson.fromJson(obj.toString(), RequestKoubeiZanResult.class);
		setStatus(result.getStatus());
		setMsg(result.getMsg());
	}

	@Override
	public String toResultString() {
		// TODO Auto-generated method stub
		return null;
	}

}
