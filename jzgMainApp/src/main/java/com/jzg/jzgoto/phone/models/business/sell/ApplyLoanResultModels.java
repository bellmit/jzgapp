package com.jzg.jzgoto.phone.models.business.sell;

import android.util.Log;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;
/**
 * @Description: 申请贷款
 * @Package com.jzg.jzgoto.phone.models.business.sell ApplyLoanResultModels.java
 * @author gf
 * @date 2015-12-30 上午10:36:32
 */
public class ApplyLoanResultModels extends BaseResultModels {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 估车价格
	 */
	private String B2C;
	/**
	 * 可贷款额度
	 */
	private String B2CLoan;

	@Override
	public void setResult(Object obj) {
		Log.i("gf", "申请贷款结果:" + (String)obj);
		Gson gson = new Gson();
		ApplyLoanResultModels models = gson.fromJson((String)obj, ApplyLoanResultModels.class);
		setMsg(models.getMsg());
		setStatus(models.getStatus());
		setSuccess(models.isSuccess());
		setB2C(models.getB2C());
		setB2CLoan(models.getB2CLoan());
	}

	@Override
	public String toResultString() {
		return null;
	}

	public String getB2C() {
		return B2C;
	}

	public void setB2C(String b2c) {
		B2C = b2c;
	}

	public String getB2CLoan() {
		return B2CLoan;
	}

	public void setB2CLoan(String b2cLoan) {
		B2CLoan = b2cLoan;
	}
}
