package com.jzg.jzgoto.phone.models.business.carmanager;


import java.util.List;
import com.jzg.jzgoto.phone.models.RecommendCardData;
import com.jzg.jzgoto.phone.models.RecommendCardList;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;

public class RequestCarManagerAddMyCarResult extends BaseResultModels {


	private static final long serialVersionUID = 1L;

	public String CarButlerId;
	public String MyLoveCarId;

	@Override
	public void setResult(Object obj) {
	}

	@Override
	public String toResultString() {
		return null;
	}

	public String getCarMangerId(){
		return CarButlerId;
	}

}
