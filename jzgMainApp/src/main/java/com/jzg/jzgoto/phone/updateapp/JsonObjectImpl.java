/**
 * Project Name:JZGPingGuShi
 * File Name:JsonObjectImpl.java
 * Package Name:com.gc.jzgpinggushi.json
 * Date:2014-9-1上午10:52:01
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.jzg.jzgoto.phone.updateapp;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.tools.ShowDialogTool;


public class JsonObjectImpl 
{

	private JSONObject jsonObject;

	private int SUCCESS = 100;

	public static boolean isSuccess(Context context, String resu){
		boolean isSuccess = false;
		try {
			JSONObject jsonObject = new JSONObject(resu);
			String status = jsonObject.getString("status");
			if("100".equals(status)){
				isSuccess = true;
			}else{
//				Toast.makeText(context, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
				ShowDialogTool.showCenterToast(context, jsonObject.getString("msg"));
				isSuccess = false;
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isSuccess = false;
		}
		
		return isSuccess;
	}
	
	/**
	 * 获取应用更新
	 * 
	 * @return
	 */
	public UpdateApp parserUpdateApp(String jsonData)
	{
		UpdateApp result = null;
		Gson gson = new Gson();
		result = gson.fromJson(jsonData, UpdateApp.class);
		return result;
	}



}
