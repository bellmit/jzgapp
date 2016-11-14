package com.jzg.jzgoto.phone.models.business.carmanager;


import java.util.List;
import com.jzg.jzgoto.phone.models.RecommendCardData;
import com.jzg.jzgoto.phone.models.RecommendCardList;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;

public class RequestCarManagerGetMyCarDetailResult extends BaseResultModels {


	private static final long serialVersionUID = 1L;

	public String MyCarId;
	public String CarNumberProvence;
	public String CarNumber;
	public String Vin;
	public String EngineNumber;
	public String CommercialInsurancePicUrl;
	public String StrongInsurancePicUrl;


	@Override
	public void setResult(Object obj) {
	}

	@Override
	public String toResultString() {
		return null;
	}

	public String getCarId(){
		return MyCarId;
	}

	public String getProvince(){
		return CarNumberProvence;
	}

	public String getCarNumber(){
		return CarNumber;
	}

	public String getEngineNumber(){
		return EngineNumber;
	}

	public String getBodyNumber(){
		return Vin;
	}

	public String getCommercialInsuranceImageUrl(){
		return CommercialInsurancePicUrl;
	}

	public String getTrafficInsuranceImageUrl(){
		return StrongInsurancePicUrl;
	}
}
