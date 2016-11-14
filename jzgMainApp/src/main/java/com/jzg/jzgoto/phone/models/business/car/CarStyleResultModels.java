package com.jzg.jzgoto.phone.models.business.car;

import java.util.List;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;
/**
 * @Description: 汽车车型返回值
 * @Package com.jzg.jzgoto.phone.models.business.car CarStyleResultModels.java
 * @author gf
 * @date 2016-6-16 下午3:25:26
 */
public class CarStyleResultModels extends BaseResultModels {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	/** 更新时间 **/
	private String LastUpData;
	/** 总共多少页 **/
	private String TotalPage;
	/** 现在是第几页 **/
	private String CurrentPage;
	/** 车型列表 **/
	private List<CarStyleBean> StyleList;
	
	@Override
	public void setResult(Object obj) {
//		Log.i("gf","<车型列表>>>" + (String)obj);
		Gson gson = new Gson();
		CarStyleResultModels models = gson.fromJson((String)obj, CarStyleResultModels.class);
		setMsg(models.getMsg());
		setStatus(models.getStatus());
		
		setLastUpData(models.getLastUpData());
		setTotalPage(models.getTotalPage());
		setCurrentPage(models.getCurrentPage());
		setStyleList(models.getStyleList());
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

	public String getTotalPage() {
		return TotalPage;
	}

	public void setTotalPage(String totalPage) {
		TotalPage = totalPage;
	}

	public String getCurrentPage() {
		return CurrentPage;
	}

	public void setCurrentPage(String currentPage) {
		CurrentPage = currentPage;
	}

	public List<CarStyleBean> getStyleList() {
		return StyleList;
	}

	public void setStyleList(List<CarStyleBean> styleList) {
		StyleList = styleList;
	}

}
