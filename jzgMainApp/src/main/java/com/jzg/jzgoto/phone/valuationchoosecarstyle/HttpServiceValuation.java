/**
 * Project Name:JZGPingGuShi
 * File Name:HttpService.java
 * Package Name:com.gc.jzgpinggushi.app
 * Date:2014-9-1上午10:36:02
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.jzg.jzgoto.phone.valuationchoosecarstyle;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.jzg.jzgoto.phone.global.HttpServiceHelper;
import com.jzg.jzgoto.phone.tools.MD5Utils;
import com.jzg.jzgoto.phone.tools.MessageUtils;
import com.jzg.pricechange.phone.JzgCarChooseConstant;
import com.jzg.pricechange.phone.JzgCarChooseMakeList;
import com.jzg.pricechange.phone.JzgCarChooseModelList;
import com.jzg.pricechange.phone.JzgCarChooseStyleList;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName:HttpService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-9-1 上午10:36:02 <br/>
 * 
 * @author zyq
 * @version
 * @since JDK 1.6
 * @see
 */
public class HttpServiceValuation
{
	private static final String ENCODING = "utf-8";
	private static int SUCCESS = 100;

	/**
	 * 获取汽车品牌列表-新接口
	 * 
	 * @param mHandler
	 * @param requestQueue
	 * @param successId
	 */
	public static void getMakeList(final Context context, final Handler mHandler,
								   RequestQueue requestQueue, final int successId, final String tokenid)
	{
		if (!checkNetwork(mHandler))
			return;
		StringRequest jsonObjRequest;
		String url = HttpServiceHelper.BASE_URL_COMMON_valuation_new
				+ "/APP/PublicInt/GetMakeAndModelAndStyle.ashx";
		jsonObjRequest = new StringRequest(Request.Method.POST, url,
				new Response.Listener<String>()
				{
					@Override
					public void onResponse(String response)
					{
						try
						{
							Log.i("serviceValuation", "返回估值品牌"+response);
							JsonObjectImplValuation jsonObjectImpl = new JsonObjectImplValuation();
							if(JsonObjectImplValuation.isSuccess(context, response)){
								JzgCarChooseMakeList makeList = jsonObjectImpl
										.parserMakeList(response);
								MessageUtils.sendMessage(mHandler, JzgCarChooseConstant.makelist,
										makeList);
							}else{
								MessageUtils.sendMessage(mHandler, JzgCarChooseConstant.net_error_back,
										response.toString());
							}
						} catch (Exception e)
						{
							e.printStackTrace();
							MessageUtils.sendMessage(mHandler, JzgCarChooseConstant.net_error_back,
									response.toString());
						}
					}
				}, new Response.ErrorListener()
				{
					@Override
					public void onErrorResponse(VolleyError error)
					{
						MessageUtils.sendMessage(mHandler, JzgCarChooseConstant.net_error_back,
								error.getMessage());
					}
				})
		{
			@Override
			protected Map<String, String> getParams() throws AuthFailureError
			{
				Map<String, String> params = new HashMap<String, String>();
				params.put("op", "GetValuationMake");
				params.put("tokenid", tokenid);
				Map<String, Object> md = new HashMap<String, Object>();
				md.put("op", "GetValuationMake");
				md.put("tokenid", tokenid);
				params.put("sign", MD5Utils.getMD5Sign(md));
				return params;
			}
		};
		jsonObjRequest.setRetryPolicy(new DefaultRetryPolicy(5000,
				DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
				DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		requestQueue.add(jsonObjRequest);
	}

	/**
	 * 获取汽车车系列表 getModelList: <br/>
	 * 
	 * @author wang
	 *            品牌id
	 * @return
	 * @throws Exception
	 * @since JDK 1.6
	 */
	public static void getModelList(final Context context, final Handler mHandler,
									RequestQueue requestQueue, final int successId, final String makeid,
									final String tokenid)
	{
		if (!checkNetwork(mHandler))
			return;
		StringRequest jsonObjRequest;
		String url = HttpServiceHelper.BASE_URL_COMMON_valuation_new
				+ "/APP/PublicInt/GetMakeAndModelAndStyle.ashx";
		jsonObjRequest = new StringRequest(Request.Method.POST, url,
				new Response.Listener<String>()
				{
					@Override
					public void onResponse(String response)
					{
						try
						{
							Log.i("serviceValuation", "返回估值车系"+response);
							JsonObjectImplValuation jsonObjectImpl = new JsonObjectImplValuation();
							if(JsonObjectImplValuation.isSuccess(context, response)){
								JzgCarChooseModelList modelList = jsonObjectImpl
										.parserModelList(response);
								MessageUtils.sendMessage(mHandler, successId,
										modelList);
							}
							
							
						} catch (Exception e)
						{
							e.printStackTrace();
						}
					}
				}, new Response.ErrorListener()
				{
					@Override
					public void onErrorResponse(VolleyError error)
					{

					}
				})
		{
			@Override
			protected Map<String, String> getParams() throws AuthFailureError
			{
				Map<String, String> params = new HashMap<String, String>();
				params.put("op", "GetValuationModel");
				params.put("MakeId", makeid);
				params.put("tokenid", tokenid);
				Map<String, Object> md = new HashMap<String, Object>();
				md.put("op", "GetValuationModel");
				md.put("MakeId", makeid);
				md.put("tokenid", tokenid);
				params.put("sign", MD5Utils.getMD5Sign(md));
				return params;
			}
		};
		jsonObjRequest.setRetryPolicy(new DefaultRetryPolicy(10000,
				DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
				DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		jsonObjRequest.setTag("my_tag");
		requestQueue.add(jsonObjRequest);
	}

	/**
	 * 获取汽车类型数据 getStyleList: <br/>
	 * 
	 * @author wang
	 * @param modelid
	 * @return
	 * @throws Exception
	 * @since JDK 1.6
	 */
	public static void getStyleList(final Context context, final Handler mHandler,
									RequestQueue requestQueue, final int successId, final String modelid,
									final String tokenid)
	{
		if (!checkNetwork(mHandler))
			return;
		StringRequest jsonObjRequest;
		String url = HttpServiceHelper.BASE_URL_COMMON_valuation_new
				+ "/APP/PublicInt/GetMakeAndModelAndStyle.ashx";
		jsonObjRequest = new StringRequest(Request.Method.POST, url,
				new Response.Listener<String>()
				{
					@Override
					public void onResponse(String response)
					{
						try
						{
							Log.i("serviceValuation", "返回估值车型"+response);
							JsonObjectImplValuation jsonObjectImpl = new JsonObjectImplValuation();
							if(JsonObjectImplValuation.isSuccess(context, response)){
								JzgCarChooseStyleList styleList = jsonObjectImpl
										.parserStyleList(response);

								MessageUtils.sendMessage(mHandler, successId,
										styleList);
							}
							
							
						} catch (Exception e)
						{
							e.printStackTrace();
						}
					}
				}, new Response.ErrorListener()
				{
					@Override
					public void onErrorResponse(VolleyError error)
					{

					}
				})
		{
			@Override
			protected Map<String, String> getParams() throws AuthFailureError
			{
				Map<String, String> params = new HashMap<String, String>();
				params.put("op", "GetValuationStyle");
				params.put("ModelId", modelid);
				params.put("tokenid", tokenid);
				Map<String, Object> md = new HashMap<String, Object>();
				md.put("op", "GetValuationStyle");
				md.put("ModelId", modelid);
				md.put("tokenid", tokenid);
				params.put("sign", MD5Utils.getMD5Sign(md));
				return params;
			}
		};
		jsonObjRequest.setRetryPolicy(new DefaultRetryPolicy(5000,
				DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
				DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		jsonObjRequest.setTag("my_tag");
		requestQueue.add(jsonObjRequest);

	}
	
	

	/**
	 * 检查网络并弹出Toast提示
	 * 
	 * @return
	 */

	private static boolean checkNetwork(final Handler hand)
	{
		/*if (!PhoneJzgApplication.isNetworkConnected())
		{
			if (hand != null)
				hand.obtainMessage(JzgCarChooseConstant.close_dialog).sendToTarget();
			return false;
		}*/
		return true;
	}

}
