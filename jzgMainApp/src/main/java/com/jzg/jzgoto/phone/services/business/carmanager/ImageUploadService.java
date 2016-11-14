package com.jzg.jzgoto.phone.services.business.carmanager;

import android.content.Context;
import android.os.Message;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.business.carmanager.RequestCarManagerModifyMyCarImageParams;
import com.jzg.jzgoto.phone.models.business.carmanager.RequestCarManagerUploadImageParams;
import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;
import com.jzg.jzgoto.phone.services.business.login.HttpUtilSingleton;
import com.jzg.jzgoto.phone.services.common.BaseService;
import com.jzg.jzgoto.phone.services.common.OnRequestFinishListener;
import com.jzg.jzgoto.phone.tools.MD5Utils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ImageUploadService extends BaseService {

	public ImageUploadService(Context context, OnRequestFinishListener listener) {
		super(context, listener);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 上传图片
	 * @param baseParams
	 * @param requestCode
	 */
	public void uploadMyCarImage(final RequestCarManagerModifyMyCarImageParams baseParams, final Class<? extends BaseResultModels> clazz,
							   final int requestCode) {
		if (baseParams == null || TextUtils.isEmpty(baseParams.imgpath)) {
			Message msg = new Message();
			msg.what = requestCode;
			msg.obj = "请求参数不能为空!图片路径不能为空!";
			getListener().onRequestFault(msg);
			return;
		}
		File file = new File(baseParams.imgpath);
		if (!file.exists()) {
			Message msg = new Message();
			msg.what = requestCode;
			msg.obj = "图片不存在!";
			getListener().onRequestFault(msg);
			return;
		}

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("op", baseParams.operateStr);
		map.put("MyCarId", baseParams.MyCarId);
		map.put("imgpath", baseParams.imgpath);
		RequestParams params = new RequestParams();
		params.addBodyParameter("op", baseParams.operateStr);
		params.addBodyParameter("MyCarId", baseParams.MyCarId);
		params.addBodyParameter("imgpath", baseParams.imgpath);
		params.addBodyParameter("file", file);
		params.addBodyParameter("sign", MD5Utils.getMD5Sign(map));

		sendRequest(baseParams, params, clazz, requestCode);
	}

	public void uploadInsuranceImage(final RequestCarManagerUploadImageParams baseParams, final Class<? extends BaseResultModels> clazz,
									 final int requestCode) {
		if (baseParams == null || TextUtils.isEmpty(baseParams.Imgpath)) {
			Message msg = new Message();
			msg.what = requestCode;
			msg.obj = "请求参数不能为空!图片路径不能为空!";
			getListener().onRequestFault(msg);
			return;
		}
		File file = new File(baseParams.Imgpath);
		if (!file.exists()) {
			Message msg = new Message();
			msg.what = requestCode;
			msg.obj = "图片不存在!";
			getListener().onRequestFault(msg);
			return;
		}

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("op", baseParams.operateStr);
		map.put("Imgpath", baseParams.Imgpath);
		RequestParams params = new RequestParams();
		params.addBodyParameter("op", baseParams.operateStr);
		params.addBodyParameter("Imgpath", baseParams.Imgpath);
		params.addBodyParameter("file", file);
		params.addBodyParameter("sign", MD5Utils.getMD5Sign(map));

		sendRequest(baseParams, params, clazz, requestCode);
	}

	private void sendRequest(BaseParamsModels baseParams, RequestParams requestParams, final Class<? extends BaseResultModels> clazz,
							 final int requestCode){
		HttpUtils http = HttpUtilSingleton.getInstance();
		http.configTimeout(15000);
		http.send(HttpRequest.HttpMethod.POST, baseParams.getUrl(), requestParams,
				new RequestCallBack<String>() {
					@Override
					public void onStart() {
					}
					@Override
					public void onLoading(long total, long current,
										  boolean isUploading) {
					}
					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						try {
							BaseResultModels result = null;
							Gson gson = new Gson();
							if (!TextUtils.isEmpty(responseInfo.result)) {
								result = gson.fromJson(responseInfo.result, clazz);
							}else{
								result = clazz.newInstance();
								result.setStatus(404);
								result.setMsg("no result return!");
							}
							Message msg = new Message();
							msg.what = requestCode;
							msg.obj = result;
							getListener().onRequestSuccess(msg);
						} catch (InstantiationException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						}
					}

					@Override
					public void onFailure(HttpException error, String mss) {
						Message msg = new Message();
						msg.what = requestCode;
						msg.obj = error.getMessage() + "-没有找到服务器地址-";
						getListener().onRequestFault(msg);
					}
				});
	}
	
}
