package com.jzg.jzgoto.phone.models.business.login;

import android.util.Log;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;
/**
 * @Description: 上传图片结果
 * @Package com.jzg.jzgoto.phone.models.business.login LoadPicResultModels.java
 * @author gf
 * @date 2015-12-23 下午7:19:49
 */
public class LoadPicResultModels extends BaseResultModels {
	// {"success":true,"status":100,"msg":"","pictureid":"3043"}
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	
	private String pictureid;
	
	@Override
	public void setResult(Object obj) {
		Log.i("gf", "上传图片接口:" + (String)obj);
		
		Gson gson = new Gson();
		LoadPicResultModels models = gson.fromJson((String)obj, LoadPicResultModels.class);
		setMsg(models.getMsg());
		setStatus(models.getStatus());
		
		setPictureid(models.getPictureid());
	}
	@Override
	public String toResultString() {
		return null;
	}
	public String getPictureid() {
		return pictureid;
	}
	public void setPictureid(String pictureid) {
		this.pictureid = pictureid;
	}
}
