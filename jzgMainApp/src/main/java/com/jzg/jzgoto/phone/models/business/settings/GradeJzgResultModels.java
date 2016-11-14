package com.jzg.jzgoto.phone.models.business.settings;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;
/**
 * @Description: 打分
 * @Package com.jzg.jzgoto.phone.models.business.settings GradeJzgResultModels.java
 * @author gf
 * @date 2016-1-12 下午5:05:53
 */
public class GradeJzgResultModels extends BaseResultModels {

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
