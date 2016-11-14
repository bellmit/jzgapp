package com.jzg.jzgoto.phone.models.business.valuation.sell;

import com.jzg.jzgoto.phone.models.common.BaseResultModels;

public class RequestSysValHistoryResult extends BaseResultModels {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void setResult(Object obj) {
		System.out.println("同步上传——"+obj.toString());
		
	}

	@Override
	public String toResultString() {
		// TODO Auto-generated method stub
		return null;
	}

}
