package com.jzg.jzgoto.phone.models.business.valuation.sell;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;

public class RequestTipsListResult extends BaseResultModels {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
//	"status": "100",
//    "msg": "成功",
//    "totalPages": "161",
//    "totalRecords": "1604",
//    "curPage": "1",
//    "EveryPageNum": "10"
//     "list":
	private String totalPages;
	private String totalRecords;
	private String curPage;
	private String EveryPageNum;
	private List<RequestTipsItemModel> list = new ArrayList<>();
	@Override
	public void setResult(Object obj) {
		System.out.println("贴士列表——"+obj.toString());
		Gson gson = new Gson();
		RequestTipsListResult result = gson.fromJson(obj.toString(), RequestTipsListResult.class);
		setStatus(result.getStatus());
		setMsg(result.getMsg());
		if(result.getStatus()==SUCCESS){
			setTotalPages(result.getTotalPages());
			setTotalRecords(result.getTotalRecords());
			setCurPage(result.getCurPage());
			setEveryPageNum(result.getEveryPageNum());
			list.addAll(result.getList());
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

	public List<RequestTipsItemModel> getList() {
		return list;
	}

	public void setList(List<RequestTipsItemModel> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "RequestTipsListResult [totalPages=" + totalPages
				+ ", totalRecords=" + totalRecords + ", curPage=" + curPage
				+ ", EveryPageNum=" + EveryPageNum + ", list=" + list + "]";
	}
}
