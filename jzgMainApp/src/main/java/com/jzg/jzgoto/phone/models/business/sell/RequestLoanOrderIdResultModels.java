package com.jzg.jzgoto.phone.models.business.sell;

import android.util.Log;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;
/**
 * 请求车贷订单ID 的返回结果
 * @author gf
 * @Date 2015-07-22
 */
public class RequestLoanOrderIdResultModels extends BaseResultModels {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 我方订单ID
	 */
	private String OrderID;
	@Override
	public void setResult(Object obj) {
		Gson gson = new Gson();
		Log.i("gf", "车抵贷-"+obj.toString());
		RequestLoanOrderIdResultModels models = gson.fromJson((String)obj, RequestLoanOrderIdResultModels.class);
		setMsg(models.getMsg());
		setStatus(models.getStatus());
		
		setOrderID(models.getOrderID());
	}
	@Override
	public String toResultString() {
		return null;
	}
	public String getOrderID() {
		return OrderID;
	}
	public void setOrderID(String orderID) {
		OrderID = orderID;
	}
}
