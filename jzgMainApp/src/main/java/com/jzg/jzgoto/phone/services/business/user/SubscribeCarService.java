package com.jzg.jzgoto.phone.services.business.user;


import android.content.Context;
import com.jzg.jzgoto.phone.models.business.user.RequestSubscribeCarParams;
import com.jzg.jzgoto.phone.models.business.user.RequestSubscribeCarRemoveOneParams;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;
import com.jzg.jzgoto.phone.services.common.BaseService;
import com.jzg.jzgoto.phone.services.common.OnRequestFinishListener;

public class SubscribeCarService extends BaseService {

	public SubscribeCarService(Context context, OnRequestFinishListener listener) {
		super(context, listener);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 获取订阅列表
	 * @param params
	 * @param clazz
	 * @param requestCode
	 */
	public void toResuestSubscribeCarListData(RequestSubscribeCarParams params, Class<? extends BaseResultModels> clazz, int requestCode){
		toRequest(params, clazz,requestCode);
	}

	/**
	 * 取消指定订阅
	 * @param params
	 * @param clazz
	 * @param requestCode
	 */
	public void toResuestRemoveSubscribeCar(RequestSubscribeCarRemoveOneParams params, Class<? extends BaseResultModels> clazz, int requestCode){
		toRequest(params, clazz,requestCode);
	}
}
