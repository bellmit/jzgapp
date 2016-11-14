package com.jzg.jzgoto.phone.models.business.buy;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;

public class SimilarCarSourceResult extends BaseResultModels {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<SimilarItemData> SimilarCarSource = new ArrayList<SimilarItemData>();
	@Override
	public void setResult(Object obj) {
		Gson gson = new Gson();
		SimilarCarSourceResult result = gson.fromJson(obj.toString(), SimilarCarSourceResult.class);
		
		setStatus(result.getStatus());
		setMsg(result.getMsg());
		if(result.getStatus()==SUCCESS){
			SimilarCarSource.addAll(result.getSimilarCarSource());
		}
		System.out.println("类似二手车售价—— "+obj.toString());
	}

	@Override
	public String toResultString() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<SimilarItemData> getSimilarCarSource() {
		return SimilarCarSource;
	}

	public void setSimilarCarSource(List<SimilarItemData> similarCarSource) {
		SimilarCarSource = similarCarSource;
	}

	@Override
	public String toString() {
		return "SimilarCarSourceResult [SimilarCarSource=" + SimilarCarSource
				+ "]";
	}

	
}
