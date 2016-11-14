package com.jzg.jzgoto.phone.models.business.carlife;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;

public class ToolsXQCityQueryResult extends BaseResultModels {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	public void setResult(Object obj) {
		Gson gson = new Gson();
		ToolsXQCityQueryResult result = gson.fromJson(obj.toString(), ToolsXQCityQueryResult.class);
		setMsg(result.getMsg());
		setStatus(result.getStatus());
		if(result.getStatus() == SUCCESS ){
			setHasStandard(result.getHasStandard());
			setCityStandar(result.getCityStandar());
		} 
	}

	@Override
	public String toResultString() {
		// TODO Auto-generated method stub
		return null;
	}
// 	"status": "100",
//    "msg": "",
//    "hasStandard": 0, 0-没有,1-有
//    "cityStandar": "该城市暂无标准信息"
	private int hasStandard;
	private String cityStandar;
	public int getHasStandard() {
		return hasStandard;
	}

	public void setHasStandard(int hasStandard) {
		this.hasStandard = hasStandard;
	}

	public String getCityStandar() {
		return cityStandar;
	}

	public void setCityStandar(String cityStandar) {
		this.cityStandar = cityStandar;
	}
	
}
