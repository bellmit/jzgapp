package com.jzg.pricechange.phone;



import java.util.HashMap;
import java.util.Map;

import android.os.Handler;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.jzg.jzgcarchoose.R;

/**
 * ClassName:HttpService <br/>
 * Date: 2014-9-1 上午10:36:02 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class JzgCarChooseHttpService
{
	private static final String ENCODING = "utf-8";
	private static int SUCCESS = 100;
	
	private static String httpurl = "";
//	private static String httpurl_url = "/GetMakeAndModelAndStyle.ashx";
	//金阳光和捷通达新接口
	private static String httpurl_url = "/GetMakeModelStyleAll.ashx";
	
	

	public static String getHttpurl() {
		return httpurl;
	}

	public static void setHttpurl(String httpurl) {
		JzgCarChooseHttpService.httpurl = httpurl;
	}

	/**
	 * 获取汽车品牌列表
	 * 
	 * @param mHandler
	 * @param requestQueue
	 * @param successId
	 */
	public static void getMakeList(final Handler mHandler,
			RequestQueue requestQueue, final int successId,final String InSale)
	{
		/*if (!checkNetwork(mHandler))
			return;*/
		StringRequest jsonObjRequest;
		String url = getHttpurl()+httpurl_url;
		jsonObjRequest = new StringRequest(Request.Method.POST, url,
				new Response.Listener<String>()
				{
					@Override
					public void onResponse(String response)
					{
						try
						{
							JzgCarChooseJsonObjectImpl jsonObjectImpl = new JzgCarChooseJsonObjectImpl();
							JzgCarChooseMakeList makeList = jsonObjectImpl
									.parserMakeList(response);
							JzgCarChooseMessageUtils.sendMessage(mHandler, successId,
									makeList);
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
						JzgCarChooseMessageUtils.sendMessage(mHandler, JzgCarChooseConstant.net_error_back,
								error.getMessage());
					}
				})
		{
			@Override
			protected Map<String, String> getParams() throws AuthFailureError
			{
				Map<String, String> params = new HashMap<String, String>();
				params.put("op", "GetMake");
				params.put("InSale", InSale);
				Map<String, Object> md = new HashMap<String, Object>();
				md.put("op", "GetMake");
				md.put("InSale", InSale);
				params.put("sign", JzgCarChooseMD5Utils.getMD5Sign(md));
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
	 * @param makeId
	 *            品牌id
	 * @return
	 * @throws Exception
	 * @since JDK 1.6
	 */
	public static void getModelList(final Handler mHandler,
			RequestQueue requestQueue, final int successId, final String makeid,final String InSale)
	{
		if (!checkNetwork(mHandler))
			return;
		StringRequest jsonObjRequest;
		String url = getHttpurl()+httpurl_url;
		jsonObjRequest = new StringRequest(Request.Method.POST, url,
				new Response.Listener<String>()
				{
					@Override
					public void onResponse(String response)
					{
						try
						{
							
							JzgCarChooseJsonObjectImpl jsonObjectImpl = new JzgCarChooseJsonObjectImpl();
							JzgCarChooseModelList modelList = jsonObjectImpl
									.parserModelList(response);
							JzgCarChooseMessageUtils.sendMessage(mHandler, successId,
									modelList);
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
				params.put("op", "GetModel");
				params.put("MakeId", makeid);
				params.put("InSale", InSale);
				Map<String, Object> md = new HashMap<String, Object>();
				md.put("op", "GetModel");
				md.put("MakeId", makeid);
				md.put("InSale", InSale);
				params.put("sign", JzgCarChooseMD5Utils.getMD5Sign(md));
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
	public static void getStyleList(final Handler mHandler,
			RequestQueue requestQueue, final int successId, final String modelid,final String InSale)
	{
		if (!checkNetwork(mHandler))
			return;
		StringRequest jsonObjRequest;
		String url = getHttpurl()+httpurl_url;
		jsonObjRequest = new StringRequest(Request.Method.POST, url,
				new Response.Listener<String>()
				{
					@Override
					public void onResponse(String response)
					{
						try
						{
							JzgCarChooseJsonObjectImpl jsonObjectImpl = new JzgCarChooseJsonObjectImpl();
							JzgCarChooseStyleList styleList = jsonObjectImpl
									.parserStyleList(response);

							JzgCarChooseMessageUtils.sendMessage(mHandler, successId,
									styleList);
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
				params.put("op", "GetStyle");
				params.put("ModelId", modelid);
				params.put("InSale", InSale);
				Map<String, Object> md = new HashMap<String, Object>();
				md.put("op", "GetStyle");
				md.put("ModelId", modelid);
				md.put("InSale", InSale);
				params.put("sign", JzgCarChooseMD5Utils.getMD5Sign(md));
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
	 * 获取汽车类型数据 getStyleList: <br/>
	 * 
	 * @author wang
	 * @param modelid
	 * @return
	 * @throws Exception
	 * @since JDK 1.6
	 */
	public static void getMakeAndModelAndStyle(final Handler mHandler,
			RequestQueue requestQueue, final int successId, final int failId,final String styleid)
	{
		if (!checkNetwork(mHandler))
			return;
		StringRequest jsonObjRequest;
		String url = getHttpurl()+httpurl_url;
		jsonObjRequest = new StringRequest(Request.Method.POST, url,
				new Response.Listener<String>()
				{
			@Override
			public void onResponse(String response)
			{
				try
				{
					Log.i("HttpService", "返回结果--》" + response);
					JzgCarChooseMessageUtils.sendMessage(mHandler, successId,response);
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
						JzgCarChooseMessageUtils.sendMessage(mHandler, failId,error.getMessage());
					}
				})
		{
			@Override
			protected Map<String, String> getParams() throws AuthFailureError
			{
				Map<String, String> params = new HashMap<String, String>();
				params.put("op", "GetLowMixYear");
				params.put("styleid", styleid);
				Map<String, Object> md = new HashMap<String, Object>();
				md.put("op", "GetLowMixYear");
				md.put("styleid", styleid);
				params.put("sign", JzgCarChooseMD5Utils.getMD5Sign(md));
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
		return true;
	}

}
