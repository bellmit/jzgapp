package com.jzg.jzgoto.phone.services.business.home;

import android.content.Context;


import com.jzg.jzgoto.phone.models.business.home.RequestHomeDataParams;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;
import com.jzg.jzgoto.phone.services.common.BaseService;
import com.jzg.jzgoto.phone.services.common.OnRequestFinishListener;

public class HomeService extends BaseService {

	public HomeService(Context context, OnRequestFinishListener listener) {
		super(context, listener);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 获取首页数据
	 * @param params
	 * @param clazz
	 * @param requestCode
	 */
	public void toResuestHomeData(RequestHomeDataParams params, Class<? extends BaseResultModels> clazz, int requestCode){
		toRequest(params, clazz,requestCode);
	}
	
}
