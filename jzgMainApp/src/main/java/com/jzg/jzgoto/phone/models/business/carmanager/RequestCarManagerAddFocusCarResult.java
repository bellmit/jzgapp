package com.jzg.jzgoto.phone.models.business.carmanager;


import com.jzg.jzgoto.phone.models.common.BaseResultModels;

public class RequestCarManagerAddFocusCarResult extends BaseResultModels {


	private static final long serialVersionUID = 1L;

	public String CarButlerId;
	public String MyCareCarId;

	@Override
	public void setResult(Object obj) {
	}

	@Override
	public String toResultString() {
		return null;
	}

	public String getFocusCarId(){
		return MyCareCarId;
	}

	public String getCarManagerId(){
		return CarButlerId;
	}

}
