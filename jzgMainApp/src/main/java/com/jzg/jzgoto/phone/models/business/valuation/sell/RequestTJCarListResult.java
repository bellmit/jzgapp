package com.jzg.jzgoto.phone.models.business.valuation.sell;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;
/**
 * 买车估值--推荐车源
 * @author wangying
 * @date 2016年6月13日
 * @className
 */
public class RequestTJCarListResult extends BaseResultModels {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String PageIndex;
	private List<RequestValuCarLikeModel> CarList = new ArrayList<>();
	@Override
	public void setResult(Object obj) {
		System.out.println("买车推荐车源——"+obj.toString());
		Gson gson = new Gson();
		RequestTJCarListResult result = gson.fromJson(obj.toString(), RequestTJCarListResult.class);
		setStatus(result.getStatus());
		setMsg(result.getMsg());
		if(result.getStatus()==SUCCESS){
			setPageIndex(result.getPageIndex());
			CarList.addAll(result.getCarList());
		}
	}

	@Override
	public String toResultString() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPageIndex() {
		return PageIndex;
	}

	public void setPageIndex(String pageIndex) {
		PageIndex = pageIndex;
	}

	public List<RequestValuCarLikeModel> getCarList() {
		return CarList;
	}

	public void setCarList(List<RequestValuCarLikeModel> carList) {
		CarList = carList;
	}

	@Override
	public String toString() {
		return "RequestTJCarListResult [PageIndex=" + PageIndex + ", CarList="
				+ CarList + "]";
	}

}
