package com.jzg.jzgoto.phone.models.business.user;


import java.util.List;
import com.jzg.jzgoto.phone.models.CarConditionData;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;

public class RequestSubscribeCarResult extends BaseResultModels {


	private static final long serialVersionUID = 1L;

	public List<CarConditionData> ConditionsList;

	@Override
	public void setResult(Object obj) {
	}

	@Override
	public String toResultString() {
		return null;
	}

	public List<CarConditionData> getSubscribeDataList(){
		return ConditionsList;
	}

}
