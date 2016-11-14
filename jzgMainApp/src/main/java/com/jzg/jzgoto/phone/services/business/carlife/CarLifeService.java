package com.jzg.jzgoto.phone.services.business.carlife;

import android.content.Context;

import com.jzg.jzgoto.phone.models.business.carlife.ToolsXQCityQueryParams;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;
import com.jzg.jzgoto.phone.services.common.BaseService;
import com.jzg.jzgoto.phone.services.common.OnRequestFinishListener;

public class CarLifeService extends BaseService{

	public CarLifeService(Context context, OnRequestFinishListener listener) {
		super(context, listener);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 限迁，按照城市查询
	 * @param params
	 * @param clazz
	 * @param requestCode
	 */
	public void toResuestToolsXQCity(ToolsXQCityQueryParams params,Class<? extends BaseResultModels> clazz,int requestCode){
		toRequest(params, clazz,requestCode);
	}
}
