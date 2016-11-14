package com.jzg.jzgoto.phone.models.business.sell;

import android.util.Log;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;
/**
 * @Description: 卖车结果
 * @Package com.jzg.jzgoto.phone.models.business.sell SellCarResultModels.java
 * @author gf
 * @date 2015-12-23 下午5:21:29
 */
public class SellCarResultModels extends BaseResultModels {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void setResult(Object obj) {
		Log.i("gf", "卖车结果:" + (String)obj);
		Gson gson = new Gson();
		SellCarResultModels models = gson.fromJson((String)obj, SellCarResultModels.class);
		setMsg(models.getMsg());
		setStatus(models.getStatus());
	}
	@Override
	public String toResultString() {
		return null;
	}
}
