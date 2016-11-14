package com.jzg.jzgoto.phone.models.business.valuation.buy;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;
/**
 * @Description: 满意度调差结果
 * @Package com.jzg.jzgoto.phone.models.business.valuation.buy SendAppraiseResultModels.java
 * @author gf
 * @date 2016-6-8 上午10:29:20
 */
public class SendAppraiseResultModels extends BaseResultModels {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void setResult(Object obj) {
		Gson gson = new Gson();
		SendAppraiseResultModels models = gson.fromJson((String)obj, SendAppraiseResultModels.class);
		setMsg(models.getMsg());
		setStatus(models.getStatus());
	}

	@Override
	public String toResultString() {
		return null;
	}

}
