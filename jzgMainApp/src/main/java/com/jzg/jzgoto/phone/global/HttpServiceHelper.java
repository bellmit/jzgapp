package com.jzg.jzgoto.phone.global;

import java.net.URL;
import java.util.Map;

import org.json.JSONObject;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.json.JsonObjectImpl;
import com.jzg.jzgoto.phone.models.CityList;
import com.jzg.jzgoto.phone.models.ProvinceList;
import com.jzg.jzgoto.phone.tools.MessageUtils;
import com.jzg.jzgoto.phone.tools.SignUtils;

public class HttpServiceHelper
{

	private static String TAG = "HttpServiceHelper";
	
	private static int SUCCESS = 100;
	
	//测试
	//public static final String  BASE_URL = "http://testptvapi.guchewang.com";
	
	//正式
	public static final String  BASE_URL = "http://ptvapi.guchewang.com";
	//测试
//	public static final String  BASE_URL = "http://192.168.6.147:9001";
	//立娟
//	public static final String  BASE_URL = "http://192.168.6.48:9001";
	//小段
//	public static final String  BASE_URL = "http://192.168.6.39:4444";
	
	//分享地址线上
	public static final String BASE_SHARE_URL = "http://m.jingzhengu.com";
	
	//分享地址测试
//	public static final String BASE_SHARE_URL = "http://m2.jingzhengu.com";
	
	
	// 选择地区线上
	private static final String jzgGuyeHttp = "http://api.jingzhengu.com";
	// 选择地区测试
//	private static final String jzgGuyeHttp = "http://apitest.jingzhengu.com";
	
	
	//类似二手车源详情
	public static final String BASE_URL_DETAIL = BASE_SHARE_URL;

	//车管家
	public static final String  BASE_CAR_MANAGER_URL = BASE_URL;

	//h5活动页
	public static final String  BASE_HTML_URL= "http://m.jingzhengu.com";
	
	
	//估值选车地址
	public static final String BASE_URL_COMMON_valuation_new = jzgGuyeHttp;
	
	//测试选车型
	public static final String  BASE_URL_COMMON_new = jzgGuyeHttp;	
	/**
	 * 获取省数据 getProvinceList: <br/>
	 * 
	 * @return
	 * @throws Exception
	 * @since JDK 1.6
	 */
	public static void getProvinceList(final Handler mHandler,
			RequestQueue requestQueue)
	{
		JsonObjectRequest jsonObjRequest;
		String url = jzgGuyeHttp + "/app/area/GetProv.ashx?&sign="
				+ SignUtils.signForPost();
		jsonObjRequest = new JsonObjectRequest(Request.Method.POST, url, null,
				new Response.Listener<JSONObject>()
				{
					@Override
					public void onResponse(JSONObject response)
					{
						try
						{
							JsonObjectImpl jsonObjectImpl = new JsonObjectImpl();
							ProvinceList provinceList = jsonObjectImpl
									.parserProvinceList(response.toString());
							MessageUtils.sendMessage(mHandler,
									R.id.provinceList, provinceList);
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
						if (error instanceof NetworkError)
						{
							System.out.println(error.toString());
						} else if (error instanceof ServerError)
						{
							System.out.println(error.toString());
						} else if (error instanceof AuthFailureError)
						{
							System.out.println(error.toString());
						} else if (error instanceof ParseError)
						{
							System.out.println(error.toString());
						} else if (error instanceof NoConnectionError)
						{
							System.out.println(error.toString());
						} else if (error instanceof TimeoutError)
						{
							System.out.println(error.toString());
						}
						MessageUtils.sendMessage(mHandler, R.id.net_error_back,
								error.getMessage());
					}
				});
		jsonObjRequest.setRetryPolicy(new DefaultRetryPolicy(5000,
				DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
				DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		jsonObjRequest.setTag("my_tag");
		requestQueue.add(jsonObjRequest);
	}

	/**
	 * 获取市数据 getCityList: <br/>
	 * 
	 * @author wang
	 * @param provinceid
	 * @return
	 * @throws Exception
	 * @since JDK 1.6
	 */
	public static void getCityList(final Handler mHandler,
			RequestQueue requestQueue, int provinceid, boolean isHideAll)
	{
		JsonObjectRequest jsonObjRequest;
		String url = "";
		if (isHideAll)
		{
			// 直辖市
			url = jzgGuyeHttp + "/app/area/GetCityByProvId.ashx";
		} else
		{
			url = jzgGuyeHttp + "/app/area/GetAreaList.ashx";
		}
		Uri.Builder builder = Uri.parse(url).buildUpon();
		builder.appendQueryParameter("ProvId", String.valueOf(provinceid));
		builder.appendQueryParameter("sign",
				SignUtils.signForCityList(String.valueOf(provinceid)));
		jsonObjRequest = new JsonObjectRequest(Request.Method.POST,
				builder.toString(), null, new Response.Listener<JSONObject>()
				{
					@Override
					public void onResponse(JSONObject response)
					{
						try
						{
							JsonObjectImpl jsonObjectImpl = new JsonObjectImpl();
							CityList cityList = jsonObjectImpl
									.parserCityList(response.toString());
							MessageUtils.sendMessage(mHandler, R.id.cityList,
									cityList);
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
						if (error instanceof NetworkError)
						{
							System.out.println(error.toString());
						} else if (error instanceof ServerError)
						{
							System.out.println(error.toString());
						} else if (error instanceof AuthFailureError)
						{
							System.out.println(error.toString());
						} else if (error instanceof ParseError)
						{
							System.out.println(error.toString());
						} else if (error instanceof NoConnectionError)
						{
							System.out.println(error.toString());
						} else if (error instanceof TimeoutError)
						{
							System.out.println(error.toString());
						}
						MessageUtils.sendMessage(mHandler, R.id.net_error_back,
								error.getMessage());
					}
				});
		jsonObjRequest.setRetryPolicy(new DefaultRetryPolicy(5000,
				DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
				DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		jsonObjRequest.setTag("my_tag");
		requestQueue.add(jsonObjRequest);

	}
	
	
	
	
	/**
	 * 历史记录
	 */
	public static void requestMyHistory(Context context, final Handler handler,
			final Map<String, String> requestMap, final int sucId,
			final int failId)
	{
		String httpurl = BASE_URL + "/APPV3/AppraiseReport.ashx";
		request(context, handler, requestMap, sucId, failId, httpurl);
	}
	/**
	 * 卖车估价
	 */
	public static void requestSellCarValuation(Context context, final Handler handler,
			final Map<String, String> requestMap, final int sucId,
			final int failId)
	{
		String httpurl = BASE_URL + "/APPV3/SendCar.ashx";
		request(context, handler, requestMap, sucId, failId, httpurl);
	}
	/**
	 * 请求城市
	 */
	public static void requestCitys(Context context, final Handler handler,
			final Map<String, String> requestMap, final int sucId,
			final int failId)
	{
		String httpurl = BASE_URL + "/APP/areacity.ashx";
		request(context, handler, requestMap, sucId, failId, httpurl);
	}
	/**
	 * 请求车市头条-寻车问诊列表-寻车问诊详情
	 */
	public static void carLifeNews(Context context, final Handler handler,
			final Map<String, String> requestMap, final int sucId,
			final int failId)
	{
		String httpurl = BASE_URL + "/APPNew/InvitationInfo.ashx";
		request(context, handler, requestMap, sucId, failId, httpurl);
	}
	/**
	 * 保值率
	 */
	public static void baozhi(Context context, final Handler handler,
			final Map<String, String> requestMap, final int sucId,
			final int failId)
	{
		String httpurl = BASE_URL + "/APPNew/MaintainValue.ashx";
		request(context, handler, requestMap, sucId, failId, httpurl);
	}
	/**
	 * 快速估值
	 */
	public static void valuationFastThread(Context context, final Handler handler,
			final Map<String, String> requestMap, final int sucId,
			final int failId)
	{
		String httpurl = BASE_URL + "/APPNew/GetValuationData.ashx";
		request(context, handler, requestMap, sucId, failId, httpurl);
	}
	/**
	 * 估值历史
	 */
	public static void valuationListThread(Context context, final Handler handler,
			final Map<String, String> requestMap, final int sucId,
			final int failId)
	{
		String httpurl = BASE_URL + "/APPNew/AppraiseReport.ashx";
		request(context, handler, requestMap, sucId, failId, httpurl);
	}
	/**
	 * 获取验证码
	 */
	public static void verifyThread(Context context, final Handler handler,
			final Map<String, String> requestMap, final int sucId,
			final int failId)
	{
		String httpurl = BASE_URL + "/APPNew/Userinfo.ashx";
		request(context, handler, requestMap, sucId, failId, httpurl);
	}
	/**
	 * 贷款
	 */
	public static void loanThread(Context context, final Handler handler,
			final Map<String, String> requestMap, final int sucId,
			final int failId)
	{
		String httpurl = BASE_URL + "/APPNew/CarLoanChannel.ashx";
		request(context, handler, requestMap, sucId, failId, httpurl);
	}
	/**
	 * 首页获取评估数
	 */
	public static void valuationNumThread(Context context, final Handler handler,
			final Map<String, String> requestMap, final int sucId,
			final int failId)
	{
		String httpurl = BASE_URL + "/appnew/AppraiseReport.ashx";
		request(context, handler, requestMap, sucId, failId, httpurl);
	}
	
	public static void request(Context context, final Handler handler,
			final Map<String, String> requestMap, final int sucId,
			final int failId, String httpurl)
	{

		RequestQueue requestQueue = Volley.newRequestQueue(context);
		StringRequest stringRequest = new StringRequest(Request.Method.POST,
				httpurl, new Response.Listener<String>()
				{
					@Override
					public void onResponse(String response)
					{
						Log.d(TAG, "网络返回数据 -> " + response);
						Message msg = new Message();
						msg.obj = response;
						msg.what = sucId;
						handler.sendMessage(msg);
					}
				}, new Response.ErrorListener()
				{
					@Override
					public void onErrorResponse(VolleyError error)
					{
						Log.e(TAG, error.getMessage(), error);
						Message msg = new Message();
						msg.what = failId;
						handler.sendMessage(msg);
					}
				})
		{
			@Override
			protected Map<String, String> getParams()
			{
				return requestMap;
			}
		};
		stringRequest.setRetryPolicy(new DefaultRetryPolicy(5000,
				DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
				DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		requestQueue.add(stringRequest);
	}
	
}
