package com.jzg.jzgoto.phone.models.business.carmanager;


import java.util.List;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;

public class RequestCarManagerGetMyCarListResult extends BaseResultModels {


	private static final long serialVersionUID = 1L;

	public List<CarManagerMyCarData> List;

	@Override
	public void setResult(Object obj) {
	}

	@Override
	public String toResultString() {
		return null;
	}

	public List<CarManagerMyCarData> getMyCarList(){
		return List;
	}

}
