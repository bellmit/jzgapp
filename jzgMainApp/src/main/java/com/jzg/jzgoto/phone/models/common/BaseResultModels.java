package com.jzg.jzgoto.phone.models.common;

import java.io.Serializable;

/**
 * 请求结果类
 * @author gf
 * @Date 2015-05-13
 */
public abstract class BaseResultModels implements Serializable{
	/**
	 * UID
	 */
	public static final int SUCCESS = 100;
	private static final long serialVersionUID = 1L;
	/**
	 * 返回成功还是失败
	 */
	private boolean success;
	/**
	 * 状态码
	 */
	private int status;
	/**
	 * 返回的信息
	 */
	private String msg;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public abstract void setResult(Object obj);
	public abstract String toResultString();
}
