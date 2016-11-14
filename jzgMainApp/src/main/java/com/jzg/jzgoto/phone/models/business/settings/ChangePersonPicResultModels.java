package com.jzg.jzgoto.phone.models.business.settings;

import android.util.Log;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;
/**
 * @Description: 修改头像
 * @Package com.jzg.jzgoto.phone.models.business.settings ChangePersonPicResultModels.java
 * @author gf
 * @date 2016-1-5 上午10:54:40
 */
public class ChangePersonPicResultModels extends BaseResultModels {
	// 01-13 15:46:42.102: I/gf(1402): 上传头像返回:{"status":100,"msg":"修改头像成功","AvatorPicPath":"http://imageup.jingzhengu.com/2016/01/13/img15464282_601.jpg"}
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	private String AvatorPicPath;

	@Override
	public void setResult(Object obj) {
		Log.i("gf", "上传头像返回:" + (String)obj);
		Gson gson = new Gson();
		ChangePersonPicResultModels models = gson.fromJson((String)obj, ChangePersonPicResultModels.class);
		setMsg(models.getMsg());
		setStatus(models.getStatus());
		
		setAvatorPicPath(models.getAvatorPicPath());
	}

	@Override
	public String toResultString() {
		return null;
	}

	public String getAvatorPicPath() {
		return AvatorPicPath;
	}

	public void setAvatorPicPath(String avatorPicPath) {
		AvatorPicPath = avatorPicPath;
	}
}
