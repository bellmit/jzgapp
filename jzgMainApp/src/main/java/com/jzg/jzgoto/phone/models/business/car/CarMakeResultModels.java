package com.jzg.jzgoto.phone.models.business.car;

import java.util.List;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;
/**
 * @Description: 车品牌返回值
 * @Package com.jzg.jzgoto.phone.models.business.car CarMakeResultModels.java
 * @author gf
 * @date 2016-6-16 下午3:23:53
 */
public class CarMakeResultModels extends BaseResultModels {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 更新时间
	 */
	private String LastUpData;
	/**
	 * 品牌列表
	 */
	private List<CarMakeBean> MakeList;
	
	@Override
	public void setResult(Object obj) {
		
//		Log.i("gf","<品牌列表>>>" + (String)obj);
		Gson gson = new Gson();
		CarMakeResultModels models = gson.fromJson((String)obj, CarMakeResultModels.class);
		setMsg(models.getMsg());
		setStatus(models.getStatus());
		
		setLastUpData(models.getLastUpData());
		setMakeList(models.getMakeList());
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

	public List<CarMakeBean> getMakeList() {
		return MakeList;
	}

	public void setMakeList(List<CarMakeBean> makeList) {
		MakeList = makeList;
	}

}
