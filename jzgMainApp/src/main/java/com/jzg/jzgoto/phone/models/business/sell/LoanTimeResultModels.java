package com.jzg.jzgoto.phone.models.business.sell;

import android.util.Log;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;
/**
 * @Description: 根据车型获取时间
 * @Package com.jzg.jzgoto.phone.models.business.sell LoanTimeResultModels.java
 * @author gf
 * @date 2015-12-31 上午11:01:35
 */
public class LoanTimeResultModels extends BaseResultModels {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	private String MinYEAR;
	private String MaxYEAR;
	@Override
	public void setResult(Object obj) {
		Log.i("gf", "根据车型获取时间结果:" + (String)obj);
		Gson gson = new Gson();
		LoanTimeResultModels models = gson.fromJson((String)obj, LoanTimeResultModels.class);
		setMsg(models.getMsg());
		setStatus(models.getStatus());
		
		setMaxYEAR(models.getMaxYEAR());
		setMinYEAR(models.getMinYEAR());
	}
	@Override
	public String toResultString() {
		return null;
	}
	public String getMinYEAR() {
		return MinYEAR;
	}
	public void setMinYEAR(String minYEAR) {
		MinYEAR = minYEAR;
	}
	public String getMaxYEAR() {
		return MaxYEAR;
	}
	public void setMaxYEAR(String maxYEAR) {
		MaxYEAR = maxYEAR;
	}
	
}
