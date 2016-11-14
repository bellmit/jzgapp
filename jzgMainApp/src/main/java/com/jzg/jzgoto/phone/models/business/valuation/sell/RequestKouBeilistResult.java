package com.jzg.jzgoto.phone.models.business.valuation.sell;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;

public class RequestKouBeilistResult extends BaseResultModels {

	/**
	 * "status": 100,
    	"msg": "",
    	"PageIndex": "1",
    	"KoubeiList": []

	 */
	private String PageIndex;
	private List<RequestValuKoubeiModel> KoubeiList = new ArrayList<>();
	private static final long serialVersionUID = 1L;

	@Override
	public void setResult(Object obj) {
		System.out.println("口碑列表——"+obj.toString());
		Gson gson = new Gson();
		RequestKouBeilistResult result = gson.fromJson(obj.toString(), RequestKouBeilistResult.class);
		setStatus(result.getStatus());
		setMsg(result.getMsg());
		if(result.getStatus()==SUCCESS){
			setPageIndex(result.getPageIndex());
			KoubeiList.addAll(result.getKoubeiList());
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

	public List<RequestValuKoubeiModel> getKoubeiList() {
		return KoubeiList;
	}

	public void setKoubeiList(List<RequestValuKoubeiModel> koubeiList) {
		KoubeiList = koubeiList;
	}

	@Override
	public String toString() {
		return "RequestKouBeilistResult [PageIndex=" + PageIndex
				+ ", KoubeiList=" + KoubeiList + "]";
	}

}
