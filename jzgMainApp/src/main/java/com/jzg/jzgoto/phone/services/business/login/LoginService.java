package com.jzg.jzgoto.phone.services.business.login;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.os.Message;
import android.text.TextUtils;

import com.jzg.jzgoto.phone.models.business.login.LoadPicParamsModels;
import com.jzg.jzgoto.phone.models.business.login.LoadPicResultModels;
import com.jzg.jzgoto.phone.models.business.settings.ChangePersonPicParamsModels;
import com.jzg.jzgoto.phone.models.business.settings.ChangePersonPicResultModels;
import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;
import com.jzg.jzgoto.phone.services.common.BaseService;
import com.jzg.jzgoto.phone.services.common.OnRequestFinishListener;
import com.jzg.jzgoto.phone.tools.MD5Utils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

/**
 * @Description: 登录用
 * @Package com.jzg.jzgoto.phone.services.business LoginService.java
 * @author gf
 * @date 2015-12-23 下午2:12:51
 */
public class LoginService extends BaseService {

	public LoginService(Context context, OnRequestFinishListener listener) {
		super(context, listener);
	}

	public <T extends BaseParamsModels> void toRequestNet(T params,
			Class<? extends BaseResultModels> clazz, int requestCode) {
		toRequest(params, clazz, requestCode);
	}

	/**
	 * 上传图片
	 * 
	 * @param context
	 * @param handler
	 * @param httpurl
	 * @param params
	 */
	public void uploadPicture(final LoadPicParamsModels baseParams,
			final int requestCode) {
		if (baseParams == null || TextUtils.isEmpty(baseParams.getFilePath())) {
			Message msg = new Message();
			msg.what = requestCode;
			msg.obj = "请求参数不能为空!图片路径不能为空!";
			getListener().onRequestFault(msg);
			return;
		}
		File file = new File(baseParams.getFilePath());
		file.setReadable(true,false);
		if (!file.exists()) {
			Message msg = new Message();
			msg.what = requestCode;
			msg.obj = "图片不存在!";
			getListener().onRequestFault(msg);
			return;
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("op", "Addpicture");
		map.put("CarId", baseParams.getCarId());
		map.put("filePath", baseParams.getFilePath());
		map.put("fileName", baseParams.getFileName());
		map.put("Position", baseParams.getPosition());
		RequestParams params = new RequestParams();
		params.addBodyParameter("op", "Addpicture");
		params.addBodyParameter("CarId", baseParams.getCarId());
		params.addBodyParameter("filePath", baseParams.getFilePath());
		params.addBodyParameter("fileName", baseParams.getFileName());
		params.addBodyParameter("Position", baseParams.getPosition());
		params.addBodyParameter("file", file);
		params.addBodyParameter("sign", MD5Utils.getMD5Sign(map));

		HttpUtils http = HttpUtilSingleton.getInstance();
		http.configTimeout(15000);
		http.send(HttpMethod.POST, baseParams.getUrl(), params,
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
							BaseResultModels result = LoadPicResultModels.class
									.newInstance();
							if (!TextUtils.isEmpty(responseInfo.result)) {
								result.setResult(responseInfo.result);
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
	/**
	 * 上传头像
	 * @param baseParams
	 * @param requestCode
	 */
	public void uploadUserIcon(final ChangePersonPicParamsModels baseParams,
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

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("op", "UpdateUserImage");
		map.put("uid", baseParams.uid);
		map.put("imgpath", baseParams.imgpath);
		RequestParams params = new RequestParams();
		params.addBodyParameter("op", "UpdateUserImage");
		params.addBodyParameter("uid", baseParams.uid);
		params.addBodyParameter("imgpath", baseParams.imgpath);
		params.addBodyParameter("file", file);
		params.addBodyParameter("sign", MD5Utils.getMD5Sign(map));

		HttpUtils http = HttpUtilSingleton.getInstance();
		http.configTimeout(15000);
		http.send(HttpMethod.POST, baseParams.getUrl(), params,
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
							BaseResultModels result = ChangePersonPicResultModels.class
									.newInstance();
							if (!TextUtils.isEmpty(responseInfo.result)) {
								result.setResult(responseInfo.result);
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
