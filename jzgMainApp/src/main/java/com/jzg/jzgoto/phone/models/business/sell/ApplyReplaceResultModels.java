package com.jzg.jzgoto.phone.models.business.sell;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;
/**
 * @Description: 申请置换--最后一步
 * @Package com.jzg.jzgoto.phone.models.business.sell ApplyReplaceResultModels.java
 * @author gf
 * @date 2015-12-25 下午4:01:01
 */
public class ApplyReplaceResultModels extends BaseResultModels {
	// {"status":100,"msg":"数据提交成功"}
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void setResult(Object obj) {
		Gson gson = new Gson();
		ApplyReplaceResultModels models = gson.fromJson((String)obj, ApplyReplaceResultModels.class);
		setMsg(models.getMsg());
		setStatus(models.getStatus());
	}

	@Override
	public String toResultString() {
		return null;
	}

}
