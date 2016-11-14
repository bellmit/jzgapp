package com.jzg.jzgoto.phone.models.business.buy;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;

public class SearchAutoComValueResult extends BaseResultModels {

	private static final long serialVersionUID = 1L;

	private List<String> returnValue  = new ArrayList<String>() ;
	
	public SearchAutoComValueResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SearchAutoComValueResult(List<String> returnValue) {
		super();
		this.returnValue = returnValue;
	}

	public List<String> getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(List<String> returnValue) {
		this.returnValue = returnValue;
	}

	@Override
	public void setResult(Object obj) {
		Gson gson = new Gson();
		SearchAutoComValueResult result = gson.fromJson(obj.toString(), SearchAutoComValueResult.class);
		setStatus(result.getStatus());
		setMsg(result.getMsg());
		if(result.getStatus() ==SUCCESS){
			returnValue .addAll(result.getReturnValue());
		}
	}

	@Override
	public String toResultString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
