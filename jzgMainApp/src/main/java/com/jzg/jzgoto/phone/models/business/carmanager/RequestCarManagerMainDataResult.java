package com.jzg.jzgoto.phone.models.business.carmanager;


import java.util.List;
import com.jzg.jzgoto.phone.models.RecommendCardList;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;

public class RequestCarManagerMainDataResult extends BaseResultModels {


	private static final long serialVersionUID = 1L;

	public int BulterId;
	public int TotalMyLoveCar;
	public List<CarManagerMyCarData> ListMyLoveCar;
	public int TotalMyCareCar;
	public List<FocusCarData> ListMyCareCar;
	public List<RecommendCardList> CardTotalList;

	@Override
	public void setResult(Object obj) {
	}

	@Override
	public String toResultString() {
		return null;
	}

	public int getCarManagerId(){
		return BulterId;
	}

	public int getMyCarCount(){
		return TotalMyLoveCar;
	}

	public List<CarManagerMyCarData> getMyCarList(){
		return ListMyLoveCar;
	}

	public int getFocusCarCount(){
		return TotalMyCareCar;
	}

	public List<FocusCarData> getFocusCarList(){
		return ListMyCareCar;
	}

	public List<RecommendCardList> getRecommendCardList(){
		return CardTotalList;
	}

}
