package com.jzg.jzgoto.phone.models.business.home;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.BannerData;
import com.jzg.jzgoto.phone.models.RecommendCardList;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;

public class RequestHomeDataResult extends BaseResultModels {


	private static final long serialVersionUID = 1L;

	private int BannerTotalCount;
	private List<BannerData> BannerList;
	private int CardNum;
	private List<RecommendCardList> CardTotalList;
	private String DefaultBanner;

	public int getBannerCount(){
		return BannerTotalCount;
	}

	public List<BannerData> getBannerList(){
		return BannerList;
	}

	public List<RecommendCardList> getRecommendCardList(){
		return CardTotalList;
	}

	public String getDefaultBannerImageUrl(){
		return DefaultBanner;
	}

	@Override
	public void setResult(Object obj) {
//		Gson gson = new Gson();
//		RequestHomeDataResult result = gson.fromJson(obj.toString(), RequestHomeDataResult.class);
//		setStatus(result.getStatus());
//		setMsg(result.getMsg());
//		if(result.getStatus() == SUCCESS){
//			BannerTotalCount = result.BannerTotalCount;
//			BannerList.addAll(result.BannerList);
//			CardTotalList.addAll(result.CardTotalList);
//		}
	}

	@Override
	public String toResultString() {
		return null;
	}


	@Override
	public String toString() {
		return "RequestHomeDataResult [CardNum=" + CardNum
				+ ", BannerTotalCount=" + BannerTotalCount + "]";
	}

}
