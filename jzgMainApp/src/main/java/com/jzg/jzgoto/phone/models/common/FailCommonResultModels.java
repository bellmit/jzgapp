package com.jzg.jzgoto.phone.models.common;
/**
 * @Description: 失败返回的类
 * @Package com.jzg.jzgoto.phone.models.common FailCommonResultModels.java
 * @author gf
 * @date 2016-5-3 下午8:06:58
 */
public class FailCommonResultModels extends BaseResultModels {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	@Override
	public void setResult(Object obj) {
		setMsg("请求失败!");
		setStatus(404);
	}
	@Override
	public String toResultString() {
		return null;
	}

}
