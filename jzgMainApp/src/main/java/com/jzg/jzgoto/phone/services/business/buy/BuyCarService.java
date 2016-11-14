package com.jzg.jzgoto.phone.services.business.buy;

import android.content.Context;

import com.jzg.jzgoto.phone.models.business.buy.AddConcernParams;
import com.jzg.jzgoto.phone.models.business.buy.BuyCarCompareParams;
import com.jzg.jzgoto.phone.models.business.buy.BuyCarDetailBargainParams;
import com.jzg.jzgoto.phone.models.business.buy.BuyCarDetailParams;
import com.jzg.jzgoto.phone.models.business.buy.BuyCarListAddConditionsParams;
import com.jzg.jzgoto.phone.models.business.buy.BuyCarListParams;
import com.jzg.jzgoto.phone.models.business.buy.BuyCarSearchHotWordsParams;
import com.jzg.jzgoto.phone.models.business.buy.BuyCarSearchSaveKeyWordsParams;
import com.jzg.jzgoto.phone.models.business.buy.DelConcernParams;
import com.jzg.jzgoto.phone.models.business.buy.MyConcernParams;
import com.jzg.jzgoto.phone.models.business.buy.MyRecomandListPrams;
import com.jzg.jzgoto.phone.models.business.buy.RequestAutoSearchTextParams;
import com.jzg.jzgoto.phone.models.business.buy.SimilarCarSourceParams;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;
import com.jzg.jzgoto.phone.services.common.BaseService;
import com.jzg.jzgoto.phone.services.common.OnRequestFinishListener;

public class BuyCarService extends BaseService {

	public BuyCarService(Context context, OnRequestFinishListener listener) {
		super(context, listener);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 买车列表查询
	 * @param params
	 * @param clazz
	 * @param requestCode
	 */
	public void toResuestBuyCarList(BuyCarListParams params,Class<? extends BaseResultModels> clazz,int requestCode){
		toRequest(params, clazz,requestCode);
	}
	/**
	 * 买车详情
	 * @param params
	 * @param clazz
	 * @param requestCode
	 */
	public void toResuestBuyCarDetail(BuyCarDetailParams params,Class<? extends BaseResultModels> clazz,int requestCode){
		toRequest(params, clazz,requestCode);
	}
	/**
	 * 请求对比列表数据
	 * @param params
	 * @param clazz
	 * @param requestCode
	 */
	public void toResuestBuyCarCompare(BuyCarCompareParams params,Class<? extends BaseResultModels> clazz,int requestCode){
		toRequest(params, clazz,requestCode);
	}
	/**
	 * 添加关注
	 * @param params
	 * @param clazz
	 * @param requestCode
	 */
	public void toResuestAddConcern(AddConcernParams params,Class<? extends BaseResultModels> clazz,int requestCode){
		toRequest(params, clazz,requestCode);
	}
	/**
	 * 删除关注
	 * @param params
	 * @param clazz
	 * @param requestCode
	 */
	public void toResuestDelConcern(DelConcernParams params,Class<? extends BaseResultModels> clazz,int requestCode){
		toRequest(params, clazz,requestCode);
	}
	/**
	 * 我的关注列表
	 * @param params
	 * @param clazz
	 * @param requestCode
	 */
	public void toResuestConcernList(MyConcernParams params,Class<? extends BaseResultModels> clazz,int requestCode){
		toRequest(params, clazz,requestCode);
	}
	/**
	 * 推荐列表
	 * @param params
	 * @param clazz
	 * @param requestCode
	 */
	public void toResuestRecomandList(MyRecomandListPrams params,Class<? extends BaseResultModels> clazz,int requestCode){
		toRequest(params, clazz,requestCode);
	}
	/**
	 * 类似二手车售价
	 * @param params
	 * @param clazz
	 * @param requestCode
	 */
	public void toResuestSimilarList(SimilarCarSourceParams params,Class<? extends BaseResultModels> clazz,int requestCode){
		toRequest(params, clazz,requestCode);
	}
	/**
	 * 词语自动补全
	 * @param params
	 * @param clazz
	 * @param requestCode
	 */
	public void toResuestSearchAutoComList(RequestAutoSearchTextParams params,Class<? extends BaseResultModels> clazz,int requestCode){
		toRequest(params, clazz,requestCode);
	}
	/**
	 * 请求热门搜索词
	 * @param params
	 * @param clazz
	 * @param requestCode
	 */
	public void toResuestSearchHotWordsList(BuyCarSearchHotWordsParams params, Class<? extends BaseResultModels> clazz, int requestCode){
		toRequest(params, clazz,requestCode);
	}

	/**
	 * 保存搜索关键词
	 * @param params
	 * @param clazz
	 * @param requestCode
     */
	public void toResuestSaveSearchHotWords(BuyCarSearchSaveKeyWordsParams params, Class<? extends BaseResultModels> clazz, int requestCode){
		toRequest(params, clazz,requestCode);
	}
	/**
	 * 保存搜索关键词
	 * @param params
	 * @param clazz
	 * @param requestCode
     */
	public void toResuestBuyCarDetailBargain(BuyCarDetailBargainParams params, Class<? extends BaseResultModels> clazz, int requestCode){
		toRequest(params, clazz,requestCode);
	}

	/**
	 * 买车列表——添加订阅
	 * @param params
	 * @param clazz
	 * @param requestCode
     */
	public void toResuestBuyCarAddConditions(BuyCarListAddConditionsParams params, Class<? extends BaseResultModels> clazz, int requestCode){
		toRequest(params, clazz,requestCode);
	}
}
