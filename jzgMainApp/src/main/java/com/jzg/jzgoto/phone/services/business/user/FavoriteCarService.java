package com.jzg.jzgoto.phone.services.business.user;


import android.content.Context;

import com.jzg.jzgoto.phone.models.business.user.RequestFavoriteCarClearAllParams;
import com.jzg.jzgoto.phone.models.business.user.RequestFavoriteCarClearOneParams;
import com.jzg.jzgoto.phone.models.business.user.RequestFavoriteCarListParams;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;
import com.jzg.jzgoto.phone.services.common.BaseService;
import com.jzg.jzgoto.phone.services.common.OnRequestFinishListener;

public class FavoriteCarService extends BaseService {

	public FavoriteCarService(Context context, OnRequestFinishListener listener) {
		super(context, listener);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 获取收藏列表
	 * @param params
	 * @param clazz
	 * @param requestCode
	 */
	public void toResuestFavoriteCarListData(RequestFavoriteCarListParams params, Class<? extends BaseResultModels> clazz, int requestCode){
		toRequest(params, clazz, requestCode);
	}

	/**
	 * 清除所有收藏列表
	 * @param params
	 * @param clazz
	 * @param requestCode
	 */
	public void toResuestClearAllFavoriteCar(RequestFavoriteCarClearAllParams params, Class<? extends BaseResultModels> clazz, int requestCode){
		toRequest(params, clazz, requestCode);
	}

	/**
	 * 清除单个收藏列表
	 * @param params
	 * @param clazz
	 * @param requestCode
	 */
	public void toResuestClearOneFavoriteCar(RequestFavoriteCarClearOneParams params, Class<? extends BaseResultModels> clazz, int requestCode){
		toRequest(params, clazz, requestCode);
	}
}
