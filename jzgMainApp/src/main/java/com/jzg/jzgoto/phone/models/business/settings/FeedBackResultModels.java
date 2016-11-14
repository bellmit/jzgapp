package com.jzg.jzgoto.phone.models.business.settings;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;
/**
 * @Description: 意见反馈
 * @Package com.jzg.jzgoto.phone.models.business.settings FeedBackResultModels.java
 * @author gf
 * @date 2015-12-31 下午2:03:44
 */
public class FeedBackResultModels extends BaseResultModels {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void setResult(Object obj) {
		Gson gson = new Gson();
		FeedBackResultModels models = gson.fromJson((String)obj, FeedBackResultModels.class);
		setMsg(models.getMsg());
		setStatus(models.getStatus());
	}

	@Override
	public String toResultString() {
		return null;
	}

}
