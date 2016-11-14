/**
 * Project Name:JZGPingGuShi
 * File Name:HttpService.java
 * Package Name:com.gc.jzgpinggushi.app
 * Date:2014-9-1上午10:36:02
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.jzg.jzgoto.phone.updateapp;

import java.util.Map;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jzg.jzgoto.phone.global.HttpServiceHelper;

/**
 */
public class HttpUpdateAppService
{
	private static final String ENCODING = "utf-8";
	public static int SUCCESS = 100;
	private static int Fail = 101;


	/**
	 * 更新应用
	 */
	public static void updateApp(Context context,final Handler handler,final Map<String, String> requestMap){
		
		String httpurl = HttpServiceHelper.BASE_URL+ "/APP/GetVersion.ashx";
		
		RequestQueue requestQueue = Volley.newRequestQueue(context);
		
		StringRequest stringRequest = new StringRequest(Request.Method.POST,httpurl,
		    new Response.Listener<String>() {
		        @Override
		        public void onResponse(String response) {
		            Log.d("HttpUpdateAppService", "网络返回数据 -> " + response);
		            Message msg = new Message();
		            msg.obj = response;
		            msg.what = SUCCESS;
		            handler.sendMessage(msg);
		        }
		    }, new Response.ErrorListener() {
		        @Override
		        public void onErrorResponse(VolleyError error) {
		            Log.e("HttpUpdateAppService", error.getMessage(), error);
		            Message msg = new Message();
		            msg.what = Fail;
		            handler.sendMessage(msg);
		        }
		    }) {
		    @Override
		    protected Map<String, String> getParams() {
		      return requestMap;
		    }
		};     
		stringRequest.setShouldCache(false);
		stringRequest.setRetryPolicy(new DefaultRetryPolicy(10000,
				DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
				DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		requestQueue.add(stringRequest);
	}
	
	

}
