package com.jzg.jzgoto.phone.models.business.valuation.sell;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;

public class RequestValFragmentResult extends BaseResultModels {

	/**
	 * "status": 100,
     *	"AppraiseNum": "12345678",
     *	"msg": "成功",
     *	"BannerTotalCount": 2,
     *	"BannerList": [RequestValFragmentBanner,RequestValFragmentBanner]
     *	"HistoryTotalCount": 2,
     *	"HistoryList": [RequestValFragmentHistory,RequestValFragmentHistory]
     *
	 */
	private static final long serialVersionUID = 1L;
	
	private String AppraiseNum;
	private String BannerTotalCount;
	private List<RequestValFragmentBanner> BannerList = new ArrayList<RequestValFragmentBanner>();
	private String HistoryTotalCount;
	private List<RequestValFragmentHistory> HistoryList = new ArrayList<RequestValFragmentHistory>();
	
	@Override
	public void setResult(Object obj) {
		System.out.println("估值页Banner，历史————"+obj.toString());
		Gson gson = new Gson();
		RequestValFragmentResult result = gson.fromJson(obj.toString(), RequestValFragmentResult.class);
		setStatus(result.getStatus());
		setMsg(result.getMsg());
		if(result.getStatus()==SUCCESS){
			setAppraiseNum(result.getAppraiseNum());
			setBannerTotalCount(result.getBannerTotalCount());
			setHistoryTotalCount(result.getHistoryTotalCount());
			BannerList.addAll(result.getBannerList());
			HistoryList.addAll(result.getHistoryList());
		}
	}

	@Override
	public String toResultString() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getAppraiseNum() {
		return AppraiseNum;
	}

	public void setAppraiseNum(String appraiseNum) {
		AppraiseNum = appraiseNum;
	}

	public String getBannerTotalCount() {
		return BannerTotalCount;
	}

	public void setBannerTotalCount(String bannerTotalCount) {
		BannerTotalCount = bannerTotalCount;
	}

	public List<RequestValFragmentBanner> getBannerList() {
		return BannerList;
	}

	public void setBannerList(List<RequestValFragmentBanner> bannerList) {
		BannerList = bannerList;
	}

	public String getHistoryTotalCount() {
		return HistoryTotalCount;
	}

	public void setHistoryTotalCount(String historyTotalCount) {
		HistoryTotalCount = historyTotalCount;
	}

	public List<RequestValFragmentHistory> getHistoryList() {
		return HistoryList;
	}

	public void setHistoryList(List<RequestValFragmentHistory> historyList) {
		HistoryList = historyList;
	}

	@Override
	public String toString() {
		return "RequestValFragmentResult [AppraiseNum=" + AppraiseNum
				+ ", BannerTotalCount=" + BannerTotalCount + ", BannerList="
				+ BannerList + ", HistoryTotalCount=" + HistoryTotalCount
				+ ", HistoryList=" + HistoryList + "]";
	}

}
