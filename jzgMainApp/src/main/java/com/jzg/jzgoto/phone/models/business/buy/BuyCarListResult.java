package com.jzg.jzgoto.phone.models.business.buy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;

public class BuyCarListResult extends BaseResultModels implements Serializable{

	/**
	 *  NewCarAndOldCarlist[],
	 *  "NewCartotalNum": 176,
     *	"success": true,
     *  "msg": "成功",
     *  "status": 100
	 */

	private static final long serialVersionUID = 1L;

	private List<BuyCarItemModel> NewCarAndOldCarlist = new ArrayList<>();
	private int NewCartotalNum;
	@Override
	public void setResult(Object obj) {
		Gson gson = new Gson();
		System.out.println("买车列表--"+obj.toString());
		BuyCarListResult result = gson.fromJson(obj.toString(), BuyCarListResult.class);
		setMsg(result.getMsg());
		setStatus(result.getStatus());
		if(result.getStatus()==SUCCESS){
			setNewCartotalNum(result.getNewCartotalNum());
			NewCarAndOldCarlist.addAll(result.getNewCarAndOldCarlist());
		}
	}

	@Override
	public String toResultString() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<BuyCarItemModel> getNewCarAndOldCarlist() {
		return NewCarAndOldCarlist;
	}

	public void setNewCarAndOldCarlist(List<BuyCarItemModel> newCarAndOldCarlist) {
		this.NewCarAndOldCarlist = newCarAndOldCarlist;
	}

	public int getNewCartotalNum() {
		return NewCartotalNum;
	}

	public void setNewCartotalNum(int newCartotalNum) {
		this.NewCartotalNum = newCartotalNum;
	}

}
