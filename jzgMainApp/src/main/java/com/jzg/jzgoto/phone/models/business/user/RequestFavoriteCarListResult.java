package com.jzg.jzgoto.phone.models.business.user;


import java.util.List;
import com.jzg.jzgoto.phone.models.CarData;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;

public class RequestFavoriteCarListResult extends BaseResultModels {


	private static final long serialVersionUID = 1L;

	public List<CarData> SaleCarList;
	public List<CarData> ShelfCarList;

	@Override
	public void setResult(Object obj) {
	}

	@Override
	public String toResultString() {
		return null;
	}

	public List<CarData> getFavoriteDataList(){
		return SaleCarList;
	}

	public List<CarData> getExpiredFavoriteDataList(){
		return ShelfCarList;
	}

}
