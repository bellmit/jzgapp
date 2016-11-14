package com.jzg.jzgoto.phone.services.business.carmanager;

import android.content.Context;

import com.jzg.jzgoto.phone.models.business.carmanager.RequestCarManagerAddFocusCarParams;
import com.jzg.jzgoto.phone.models.business.carmanager.RequestCarManagerAddMyCarParams;
import com.jzg.jzgoto.phone.models.business.carmanager.RequestCarManagerDeleteFocusCarParams;
import com.jzg.jzgoto.phone.models.business.carmanager.RequestCarManagerDeleteMyCarParams;
import com.jzg.jzgoto.phone.models.business.carmanager.RequestCarManagerGetFocusCarListParams;
import com.jzg.jzgoto.phone.models.business.carmanager.RequestCarManagerGetMyCarDetailParams;
import com.jzg.jzgoto.phone.models.business.carmanager.RequestCarManagerGetMyCarListParams;
import com.jzg.jzgoto.phone.models.business.carmanager.RequestCarManagerGetProvinceListParams;
import com.jzg.jzgoto.phone.models.business.carmanager.RequestCarManagerMainDataParams;
import com.jzg.jzgoto.phone.models.business.carmanager.RequestCarManagerModifyMyCarDetailParams;
import com.jzg.jzgoto.phone.models.business.carmanager.RequestCarManagerModifyMyCarImageParams;
import com.jzg.jzgoto.phone.models.business.carmanager.RequestCarManagerModifyMyCarMileageParams;
import com.jzg.jzgoto.phone.models.business.carmanager.SaveConcernCarOrderParams;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;
import com.jzg.jzgoto.phone.services.common.BaseService;
import com.jzg.jzgoto.phone.services.common.OnRequestFinishListener;

public class CarManagerService extends BaseService {

	public CarManagerService(Context context, OnRequestFinishListener listener) {
		super(context, listener);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 获取首页数据
	 * @param params
	 * @param clazz
	 * @param requestCode
	 */
	public void toRequestMainData(RequestCarManagerMainDataParams params, Class<? extends BaseResultModels> clazz, int requestCode){
		toRequest(params, clazz,requestCode);
	}

	/**
	 * 获取爱车列表
	 * @param params
	 * @param clazz
	 * @param requestCode
	 */
	public void toResuestMyCarList(RequestCarManagerGetMyCarListParams params, Class<? extends BaseResultModels> clazz, int requestCode){
		toRequest(params, clazz,requestCode);
	}

	/**
	 * 添加爱车
	 * @param params
	 * @param clazz
	 * @param requestCode
	 */
	public void toRequestAddMyCar(RequestCarManagerAddMyCarParams params, Class<? extends BaseResultModels> clazz, int requestCode){
		toRequest(params, clazz,requestCode);
	}

	/**
	 * 删除爱车（单个爱车）
	 * @param params
	 * @param clazz
	 * @param requestCode
	 */
	public void toRequestDeleteMyCar(RequestCarManagerDeleteMyCarParams params, Class<? extends BaseResultModels> clazz, int requestCode){
		toRequest(params, clazz,requestCode);
	}

	/**
	 * 获取爱车的扩展信息
	 * @param params
	 * @param clazz
	 * @param requestCode
	 */
	public void toRequestGetMyCarDetailInfo(RequestCarManagerGetMyCarDetailParams params, Class<? extends BaseResultModels> clazz, int requestCode){
		toRequest(params, clazz,requestCode);
	}

	/**
	 * 爱车（完善爱车信息）
	 * @param params
	 * @param clazz
	 * @param requestCode
	 */
	public void toRequestModifyMyCarDetailInfo(RequestCarManagerModifyMyCarDetailParams params, Class<? extends BaseResultModels> clazz, int requestCode){
		toRequest(params, clazz,requestCode);
	}

	/**
	 * 爱车（修改爱车图片）
	 * @param params
	 * @param clazz
	 * @param requestCode
	 */
	public void toRequestModifyMyCarImage(RequestCarManagerModifyMyCarImageParams params, Class<? extends BaseResultModels> clazz, int requestCode){
		toRequest(params, clazz,requestCode);
	}

	/**
	 * 爱车（更新爱车里程）
	 * @param params
	 * @param clazz
	 * @param requestCode
	 */
	public void toRequestModifyMyCarMileage(RequestCarManagerModifyMyCarMileageParams params, Class<? extends BaseResultModels> clazz, int requestCode){
		toRequest(params, clazz,requestCode);
	}

	/**
	 * 完善爱车信息（车牌号省份简称）
	 * @param params
	 * @param clazz
	 * @param requestCode
	 */
	public void toRequestProvinceSummaryNameList(RequestCarManagerGetProvinceListParams params, Class<? extends BaseResultModels> clazz, int requestCode){
		toRequest(params, clazz,requestCode);
	}

	/**
	 * 添加关注车
	 * @param params
	 * @param clazz
	 * @param requestCode
	 */
	public void toRequestAddFocusCar(RequestCarManagerAddFocusCarParams params, Class<? extends BaseResultModels> clazz, int requestCode){
		toRequest(params, clazz,requestCode);
	}

	/**
	 * 获取关注车列表
	 * @param params
	 * @param clazz
	 * @param requestCode
	 */
	public void toRequestFocusCarList(RequestCarManagerGetFocusCarListParams params, Class<? extends BaseResultModels> clazz, int requestCode){
		toRequest(params, clazz,requestCode);
	}

	/**
	 * 删除关注车
	 * @param params
	 * @param clazz
	 * @param requestCode
	 */
	public void toRequestDeleteFocusCar(RequestCarManagerDeleteFocusCarParams params, Class<? extends BaseResultModels> clazz, int requestCode){
		toRequest(params, clazz,requestCode);
	}

	/**
	 * 关注车（列表拖动顺序）
	 * @param params
	 * @param clazz
	 * @param requestCode
	 */
	public void toRequestSortFocusCar(SaveConcernCarOrderParams params, Class<? extends BaseResultModels> clazz, int requestCode){
		toRequest(params, clazz,requestCode);
	}
	
}
