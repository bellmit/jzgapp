package com.jzg.jzgoto.phone.models.business.settings;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;
import com.umeng.socialize.utils.Log;
/**
 * @Description: 推送设置
 * @Package com.jzg.jzgoto.phone.models.business.settings GetPushStatusResultModels.java
 * @author gf
 * @date 2016-1-14 下午8:13:30
 */
public class GetPushStatusResultModels extends BaseResultModels {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	@Override
	public void setResult(Object obj) {
		Log.i("gf","<<推送>>>" + (String)obj);
		Gson gson = new Gson();
		GetPushStatusResultModels models = gson.fromJson((String)obj, GetPushStatusResultModels.class);
		setMsg(models.getMsg());
		setStatus(models.getStatus());
	}
	@Override
	public String toResultString() {
		return null;
	}

}
