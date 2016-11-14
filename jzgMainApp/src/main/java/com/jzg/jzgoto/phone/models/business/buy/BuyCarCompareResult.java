package com.jzg.jzgoto.phone.models.business.buy;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;

public class BuyCarCompareResult extends BaseResultModels {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<CompareNameAndData> Canshu = new ArrayList<CompareNameAndData>();
	private List<CompareNameAndData> peizhi = new ArrayList<CompareNameAndData>();
	private List<CompareCarData> list = new ArrayList<CompareCarData>();
	@Override
	public void setResult(Object obj) {
		System.out.println(obj.toString());
		Gson gson = new Gson();
		BuyCarCompareResult result = gson.fromJson(obj.toString(), BuyCarCompareResult.class);
			setStatus(result.getStatus());
			setMsg(result.getMsg());
		if(result.getStatus()==SUCCESS){
			Canshu.addAll(result.getCanshu());
			peizhi.addAll(result.getPeizhi());
			list.addAll(result.getList());
		}
	}

	@Override
	public String toResultString() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<CompareNameAndData> getCanshu() {
		return Canshu;
	}

	public void setCanshu(List<CompareNameAndData> canshu) {
		Canshu = canshu;
	}

	public List<CompareNameAndData> getPeizhi() {
		return peizhi;
	}

	public void setPeizhi(List<CompareNameAndData> peizhi) {
		this.peizhi = peizhi;
	}

	public List<CompareCarData> getList() {
		return list;
	}

	public void setList(List<CompareCarData> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "BuyCarCompareResult [Canshu=" + Canshu + ", peizhi=" + peizhi
				+ ", list=" + list + "]";
	}

}
