package com.jzg.jzgoto.phone.models.business.carmanager;


import com.jzg.jzgoto.phone.models.common.BaseResultModels;

public class RequestCarManagerModifyMyCarImageResult extends BaseResultModels {

	private static final long serialVersionUID = 1L;

	public String PicUrl;

	@Override
	public void setResult(Object obj) {
	}

	@Override
	public String toResultString() {
		return null;
	}

	public String getImageUrl(){
		return PicUrl;
	}


}
