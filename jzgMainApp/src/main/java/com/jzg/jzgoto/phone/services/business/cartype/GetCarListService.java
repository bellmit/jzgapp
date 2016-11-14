package com.jzg.jzgoto.phone.services.business.cartype;

import android.content.Context;

/**
 * @Description: 获取车源信息
 * @Package com.jzg.jzgoto.phone.services.business.cartype GetCarListService.java
 * 1 新车，0旧车
 * @author gf
 * @date 2016-6-16 下午6:05:53
 */
public class GetCarListService {
	private static final CarListService mCarListService = new CarListService();
	/**
	 * 获取请求接口
	 * 1 新车，0旧车
	 * @param context
	 * @return
	 */
	public static GetCarListInterface getInstance(Context context){
		mCarListService.initSelfDb(context);
		return mCarListService;
	}
}
