package com.jzg.jzgoto.phone.services.business.valuation;

import android.content.Context;

import com.jzg.jzgoto.phone.models.business.valuation.ValuationBuyCarParams;
import com.jzg.jzgoto.phone.models.business.valuation.ValuationRecommendCarParams;
import com.jzg.jzgoto.phone.models.business.valuation.ValuationSellCarParams;
import com.jzg.jzgoto.phone.models.business.valuation.sell.RequestHistoryDealParams;
import com.jzg.jzgoto.phone.models.business.valuation.sell.RequestKouBeiListParams;
import com.jzg.jzgoto.phone.models.business.valuation.sell.RequestKoubeiZanParams;
import com.jzg.jzgoto.phone.models.business.valuation.sell.RequestSynValHistoryParams;
import com.jzg.jzgoto.phone.models.business.valuation.sell.RequestTJCarListParams;
import com.jzg.jzgoto.phone.models.business.valuation.sell.RequestTipsListParams;
import com.jzg.jzgoto.phone.models.business.valuation.sell.RequestValFragmentParams;
import com.jzg.jzgoto.phone.models.business.valuation.sell.RequestValuationBuyParams;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;
import com.jzg.jzgoto.phone.services.common.BaseService;
import com.jzg.jzgoto.phone.services.common.OnRequestFinishListener;

public class ValuationService extends BaseService {

	public ValuationService(Context context, OnRequestFinishListener listener) {
		super(context, listener);
	}


	/**
	 * 历史成交记录
	 * @param params
	 * @param clazz
	 * @param requestCode
	 */
	public void toResuestHistoryValDealList(RequestHistoryDealParams params,Class<? extends BaseResultModels> clazz,int requestCode) {
		toRequest(params, clazz, requestCode);
	}

	/**
	 * 登录成功同步上传估值历史列表
	 * @param params
	 * @param clazz
	 * @param requestCode
	 */
	public void toResuestSynValHistory(RequestSynValHistoryParams params,Class<? extends BaseResultModels> clazz,int requestCode){
		toRequest(params, clazz,requestCode);
	}

	/**
	 * 请求卖车估值结果
	 * @param params
	 * @param clazz
	 * @param requestCode
     */
	public void toResuestValuationSellCar(ValuationSellCarParams params, Class<? extends BaseResultModels> clazz, int requestCode){
		toRequest(params, clazz,requestCode);
	}

	/**
	 * 请求买家估值结果
	 * @param params
	 * @param clazz
	 * @param requestCode
     */
	public void toResuestValuationBuyCar(ValuationBuyCarParams params, Class<? extends BaseResultModels> clazz, int requestCode){
		toRequest(params, clazz,requestCode);
	}

	/**
	 * 加价刷新推荐列表
	 * @param params
	 * @param clazz
	 * @param requestCode
     */
	public void toResuestValuationRecommendRefresh(ValuationRecommendCarParams params, Class<? extends BaseResultModels> clazz, int requestCode){
		toRequest(params, clazz,requestCode);
	}

}
