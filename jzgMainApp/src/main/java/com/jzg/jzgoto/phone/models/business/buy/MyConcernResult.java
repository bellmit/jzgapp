package com.jzg.jzgoto.phone.models.business.buy;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;

public class MyConcernResult extends BaseResultModels {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	"totalPages": "2",
//    "totalRecords": "15",
//    "curPage": "1",
//    "EveryPageNum": "10",
//    "GZCarList":[] 
	
	private String totalPages;
	private String totalRecords;
	private String curPage;
	private String EveryPageNum;
	private List<BuyCarItemModel> carlist = new ArrayList<BuyCarItemModel>();
	@Override
	public void setResult(Object obj) {
		System.out.println(obj.toString());
		Gson gson = new Gson();
		MyConcernResult result = gson.fromJson(obj.toString(), MyConcernResult.class);
		setStatus(result.getStatus());
		setMsg(result.getMsg());
		if(result.getStatus()==SUCCESS){
			setTotalPages(result.getTotalPages());
			setTotalRecords(result.getTotalRecords());
			setCurPage(result.getCurPage());
			setEveryPageNum(result.getEveryPageNum());
			carlist.addAll(result.getCarlist());
		}
	}

	@Override
	public String toResultString() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(String totalPages) {
		this.totalPages = totalPages;
	}

	public String getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}

	public String getCurPage() {
		return curPage;
	}

	public void setCurPage(String curPage) {
		this.curPage = curPage;
	}

	public String getEveryPageNum() {
		return EveryPageNum;
	}

	public void setEveryPageNum(String everyPageNum) {
		EveryPageNum = everyPageNum;
	}

	public List<BuyCarItemModel> getCarlist() {
		return carlist;
	}

	public void setCarlist(List<BuyCarItemModel> carlist) {
		this.carlist = carlist;
	}

	@Override
	public String toString() {
		return "MyConcernResult [totalPages=" + totalPages + ", totalRecords="
				+ totalRecords + ", curPage=" + curPage + ", EveryPageNum="
				+ EveryPageNum + ", carlist=" + carlist + "]";
	}
}
