package com.jzg.jzgoto.phone.models.business.car;

import java.util.List;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;
/**
 * @Description: 汽车车系返回值
 * @Package com.jzg.jzgoto.phone.models.business.car CarModelResultModels.java
 * @author gf
 * @date 2016-6-16 下午3:24:44
 */
public class CarModelResultModels extends BaseResultModels {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 更新时间
	 */
	private String LastUpData;
	/**
	 * 车系列表
	 */
	private List<CarModelBean> ModelList;
	@Override
	public void setResult(Object obj) {
//		Log.i("gf","<车系列表>>>" + (String)obj);
		Gson gson = new Gson();
		CarModelResultModels models = gson.fromJson((String)obj, CarModelResultModels.class);
		setMsg(models.getMsg());
		setStatus(models.getStatus());
		
		setLastUpData(models.getLastUpData());
		setModelList(models.getModelList());
	}

	@Override
	public String toResultString() {
		return null;
	}

	public String getLastUpData() {
		return LastUpData;
	}

	public void setLastUpData(String lastUpData) {
		LastUpData = lastUpData;
	}

	public List<CarModelBean> getModelList() {
		return ModelList;
	}

	public void setModelList(List<CarModelBean> modelList) {
		ModelList = modelList;
	}
	
}
