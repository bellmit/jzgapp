package com.jzg.jzgoto.phone.models.business.buy;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;

public class AddConcernResult extends BaseResultModels {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String Id;
	@Override
	public void setResult(Object obj) {
		System.out.println(obj.toString());
		Gson gson = new Gson();
		AddConcernResult result = gson.fromJson(obj.toString(), AddConcernResult.class);
		setStatus(result.getStatus());
		setMsg(result.getMsg());
		setId(result.getId());
	}

	@Override
	public String toResultString() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	@Override
	public String toString() {
		return "AddConcernResult [Id=" + Id + "]";
	}

	
}
