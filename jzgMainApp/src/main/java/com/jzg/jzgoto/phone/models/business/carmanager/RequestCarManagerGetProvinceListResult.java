package com.jzg.jzgoto.phone.models.business.carmanager;


import com.jzg.jzgoto.phone.models.common.BaseResultModels;

import java.util.List;

public class RequestCarManagerGetProvinceListResult extends BaseResultModels {

	private static final long serialVersionUID = 1L;

	public List<ProvinceSummaryData> ProvList;

	@Override
	public void setResult(Object obj) {
	}

	@Override
	public String toResultString() {
		return null;
	}

	public List<ProvinceSummaryData> getProvinceDataList(){
		return ProvList;
	}

}
