package com.jzg.jzgoto.phone.models.business.valuation.sell;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;
/**
 * 请求历史成交记录 结果
 * @author wangying
 * @date 2016年6月14日
 * @className
 */
public class RequestHistoryDealResult extends BaseResultModels {

	/**
	 * "status": 100,
    "msg": "成功!",
    "totalPages": "3",
    "totalRecords": "21",
    "curPage": "1",
    "HistoryList": 
	 */
	private static final long serialVersionUID = 1L;
	
	private String totalPages;
	private String totalRecords;
	private String curPage;
	private List<RequestHistoryDealItemModel> HistoryList = new ArrayList<>();

	@Override
	public void setResult(Object obj) {
		System.out.println("历史成交记录列表——"+obj.toString());
		Gson gson = new Gson();
		RequestHistoryDealResult result = gson.fromJson(obj.toString(), RequestHistoryDealResult.class);
		setStatus(result.getStatus());
		setMsg(result.getMsg());
		if(result.getStatus()==SUCCESS){
			setTotalPages(result.getTotalPages());
			setTotalRecords(result.getTotalRecords());
			setCurPage(result.getCurPage());
			HistoryList.addAll(result.getHistoryList());
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

	public List<RequestHistoryDealItemModel> getHistoryList() {
		return HistoryList;
	}

	public void setHistoryList(List<RequestHistoryDealItemModel> historyList) {
		HistoryList = historyList;
	}

	@Override
	public String toString() {
		return "RequestHistoryDealResult [totalPages=" + totalPages
				+ ", totalRecords=" + totalRecords + ", curPage=" + curPage
				+ ", HistoryList=" + HistoryList + "]";
	}

	
}
