package com.jzg.jzgoto.phone.services.business.valuation.buy;

import android.content.Context;

import com.jzg.jzgoto.phone.models.business.valuation.buy.GetCostYearParamsModels;
import com.jzg.jzgoto.phone.models.business.valuation.buy.GetLikeCarParamsModels;
import com.jzg.jzgoto.phone.models.business.valuation.buy.GetNewCarParamsModels;
import com.jzg.jzgoto.phone.models.business.valuation.buy.GetTjOldCarParamsModels;
import com.jzg.jzgoto.phone.models.business.valuation.buy.SendAppraiseParamsModels;
import com.jzg.jzgoto.phone.models.business.valuation.hedge.HedgeRatioParamsModels;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;
import com.jzg.jzgoto.phone.services.common.BaseService;
import com.jzg.jzgoto.phone.services.common.OnRequestFinishListener;
/**
 * @Description: 买车估价
 * @Package com.jzg.jzgoto.phone.services.business.valuation.buy ValuationBuyBservice.java
 * @author gf
 * @date 2016-6-8 上午10:54:35
 */
public class ValuationBuyService extends BaseService {
	public ValuationBuyService(Context context,
			OnRequestFinishListener listener) {
		super(context, listener);
	}
	/**
	 * 满意度调查
	 * @param params
	 * @param clazz
	 * @param requestCode
	 */
	public void toSendAppraise(SendAppraiseParamsModels params,Class<? extends BaseResultModels> clazz,int requestCode){
		toRequest(params, clazz,requestCode);
	}
	/**
	 * 保值率排行获取
	 * @param params
	 * @param clazz
	 * @param requestCode
	 */
	public void toGetHedgeRatioList(HedgeRatioParamsModels params,Class<? extends BaseResultModels> clazz,int requestCode){
		toRequest(params, clazz,requestCode);
	}
	/**
	 * 获取推荐旧车
	 * @param params
	 * @param clazz
	 * @param requestCode
	 */
	public void toGetOldCarList(GetTjOldCarParamsModels params,Class<? extends BaseResultModels> clazz,int requestCode){
		toRequest(params, clazz,requestCode);
	}
	/**
	 * 获取推荐新车
	 * @param params
	 * @param clazz
	 * @param requestCode
	 */
	public void toGetNewCarList(GetNewCarParamsModels params,Class<? extends BaseResultModels> clazz,int requestCode){
		toRequest(params, clazz,requestCode);
	}
	/**
	 * 获取猜你喜欢车源
	 * @param params
	 * @param clazz
	 * @param requestCode
	 */
	public void toGetLikeCarList(GetLikeCarParamsModels params,Class<? extends BaseResultModels> clazz,int requestCode){
		toRequest(params, clazz,requestCode);
	}
	/**
	 * 买车估值计算使用成本
	 * @param params
	 * @param clazz
	 * @param requestCode
	 */
	public void toGetBuyCarCostYear(GetCostYearParamsModels params,Class<? extends BaseResultModels> clazz,int requestCode){
		toRequest(params, clazz,requestCode);
	}
}
