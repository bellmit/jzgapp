package com.jzg.jzgoto.phone.models.business.carmanager;


import java.util.List;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;

public class RequestCarManagerGetFocusCarListResult extends BaseResultModels {


	private static final long serialVersionUID = 1L;

	public String TotalMyCareCar;
	public List<FocusCarData> ListMyCareCar;

	@Override
	public void setResult(Object obj) {
	}

	@Override
	public String toResultString() {
		return null;
	}

	public List<FocusCarData> getFocusCarList(){
		return ListMyCareCar;
	}

}
