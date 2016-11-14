package com.jzg.jzgoto.phone.services.common;

import java.util.Map;

import android.content.Context;
import android.os.Message;
import android.text.TextUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.jzg.jzgoto.phone.global.AppContext;
import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;
import com.jzg.jzgoto.phone.models.common.FailCommonResultModels;
import com.jzg.jzgoto.phone.tools.MyLogger;

/**
 * 请求父类
 * @author gf
 * @Date 2015-05-13
 */
public abstract class BaseService {
	public BaseService(Context context,OnRequestFinishListener listener){
		mOnRequestFinishListener = listener;
		mContext = context;
	}
	private Context mContext;
	private OnRequestFinishListener mOnRequestFinishListener;
	public OnRequestFinishListener getListener(){
		return mOnRequestFinishListener;
	}
	public Context getContext(){
		return mContext;
	}
	public <T extends BaseParamsModels> void toRequest(final T params ,final Class<? extends BaseResultModels> clazz,final int requestCode){
		if(!AppContext.isNetworkConnected()){
			Message msg = new Message();
			msg.what = requestCode;
			msg.obj = "请检查网络设置!";
			getListener().onRequestFault(msg);
			return;
		}
		StringRequest stringRequest = new StringRequest(Request.Method.POST, params.getUrl(),
				new Response.Listener<String>(){
					@Override
					public void onResponse(String response) {
						BaseResultModels result = null;
						final Message msg = new Message();
						try {
							//result = clazz.newInstance();
							if (!TextUtils.isEmpty(response.toString())) {
								MyLogger.d("http response", "[url=" + params.getUrl() + "]" + response.toString() );
								Gson gson = new Gson();
								result = gson.fromJson(response.toString(), clazz);
								//result.setResult(response.toString());
							} else {
								MyLogger.d("http response", "[url=" + params.getUrl() + "]" + "return error" );
								result = clazz.newInstance();
								result.setStatus(404);
								result.setMsg("no result return!");
							}
						} catch (Exception e) {
							e.printStackTrace();
							if(result == null){
								result = new FailCommonResultModels();
							}
						}
						msg.what = requestCode;
						msg.obj = result;
						getListener().onRequestSuccess(msg);
					}
				},
				new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						Message msg = new Message();
						msg.what = requestCode;
						msg.obj = error.getMessage();
						getListener().onRequestFault(msg);
					}
				}){
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				return params.getParams();
			}
		};
		stringRequest.setRetryPolicy(new DefaultRetryPolicy(10000,
				DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
				DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		stringRequest.setShouldCache(false);
		mRequest = AppContext.getRequestQueue().add(stringRequest);
	}
	private Request<String> mRequest = null;
	public void stopRequest(){
		if(null != mRequest && !mRequest.isCanceled()){
			mRequest.cancel();
		}
	}
}
