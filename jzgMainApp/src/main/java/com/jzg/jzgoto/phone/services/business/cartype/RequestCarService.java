package com.jzg.jzgoto.phone.services.business.cartype;

import android.content.Context;

import com.jzg.jzgoto.phone.models.business.car.CarMakeParamsModels;
import com.jzg.jzgoto.phone.models.business.car.CarModelParamsModels;
import com.jzg.jzgoto.phone.models.business.car.CarStyleParamsModels;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;
import com.jzg.jzgoto.phone.services.common.BaseService;
import com.jzg.jzgoto.phone.services.common.OnRequestFinishListener;
/**
 * @Description: 请求车型列表
 * @Package com.jzg.jzgoto.phone.services.business.cartype RequestCarService.java
 * @author gf
 * @date 2016-6-17 上午10:24:56
 */
public class RequestCarService extends BaseService {

	public RequestCarService(Context context, OnRequestFinishListener listener) {
		super(context, listener);
	}
	/**
	 * 请求品牌列表
	 * @param params
	 * @param clazz
	 * @param requestCode
	 */
	public void toRequestMakeList(CarMakeParamsModels params,Class<? extends BaseResultModels> clazz,int requestCode){
		toRequest(params, clazz,requestCode);
	}
	/**
	 * 请求车系列表
	 * @param params
	 * @param clazz
	 * @param requestCode
	 */
	public void toRequestModelList(CarModelParamsModels params,Class<? extends BaseResultModels> clazz,int requestCode){
		toRequest(params, clazz,requestCode);
	}
	/**
	 * 请求车型列表
	 * @param params
	 * @param clazz
	 * @param requestCode
	 */
	public void toRequestStyleList(CarStyleParamsModels params,Class<? extends BaseResultModels> clazz,int requestCode){
		toRequest(params, clazz,requestCode);
	}
}
