package com.jzg.jzgoto.phone.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.jzg.jzgoto.phone.activity.buy.BuyCarActivity;
import com.jzg.jzgoto.phone.activity.buy.BuyCarDetailActivity;
import com.jzg.jzgoto.phone.activity.buy.BuyCarMoreFilterActivity;
import com.jzg.jzgoto.phone.activity.buy.BuyCarSearchActivity;
import com.jzg.jzgoto.phone.activity.carmanager.AddMyCarActivity;
import com.jzg.jzgoto.phone.activity.carmanager.CompleteCarInfoActivity;
import com.jzg.jzgoto.phone.activity.carmanager.MyCarListActivity;
import com.jzg.jzgoto.phone.activity.carmanager.MyFocusCarListActivity;
import com.jzg.jzgoto.phone.activity.choosecity.ChooseCityActivity;
import com.jzg.jzgoto.phone.activity.common.CustomWebViewActivity;
import com.jzg.jzgoto.phone.activity.main.HomeActivity;
import com.jzg.jzgoto.phone.activity.main.WelcomeActivity;
import com.jzg.jzgoto.phone.activity.repalcecar.ReplaceNewCarActivity;
import com.jzg.jzgoto.phone.activity.sellcar.SellCarActivity;
import com.jzg.jzgoto.phone.activity.sellcar.SellCarLoanActivity;
import com.jzg.jzgoto.phone.activity.sellcar.SellCarOneKeyActivity;
import com.jzg.jzgoto.phone.activity.setting.AboutUsActivity;
import com.jzg.jzgoto.phone.activity.setting.FeedBackActivity;
import com.jzg.jzgoto.phone.activity.setting.SettingActivity;
import com.jzg.jzgoto.phone.activity.user.EditUserInfoActivity;
import com.jzg.jzgoto.phone.activity.user.FavoriteCarListActivity;
import com.jzg.jzgoto.phone.activity.user.LoginActivity;
import com.jzg.jzgoto.phone.activity.user.SubscribeCarListActivity;
import com.jzg.jzgoto.phone.activity.user.UserMessageMainActivity;
import com.jzg.jzgoto.phone.activity.valuation.ValuationActivity;
import com.jzg.jzgoto.phone.activity.valuation.ValuationBuyActivity;
import com.jzg.jzgoto.phone.activity.valuation.ValuationHedgeActivity;
import com.jzg.jzgoto.phone.activity.valuation.ValuationHistoryDealPriceActivity;
import com.jzg.jzgoto.phone.activity.valuation.ValuationSellActivity;
import com.jzg.jzgoto.phone.constant.Constant;
import com.jzg.jzgoto.phone.global.AppContext;
import com.jzg.jzgoto.phone.models.CarConditionData;
import com.jzg.jzgoto.phone.models.business.buy.BuyCarDetailResult;
import com.jzg.jzgoto.phone.models.business.buy.BuyCarFilterIndexModel;
import com.jzg.jzgoto.phone.models.business.carmanager.CarManagerMyCarData;
import com.jzg.jzgoto.phone.models.business.sell.ReplaceNewCarIntentModel;
import com.jzg.jzgoto.phone.models.business.valuation.ValuationBuyCarResult;
import com.jzg.jzgoto.phone.models.business.valuation.ValuationSellCarResult;
import com.jzg.jzgoto.phone.models.business.valuation.sell.RequestHistoryDealParams;
import com.jzg.jzgoto.phone.valuationchoosecarstyle.CarReleaseIndexCarActivity;
import com.jzg.jzgoto.phone.valuationchoosecarstyle.CarReleaseIndexValuationActivity;
import com.jzg.pricechange.phone.JzgCarChooseConstant;
import com.jzg.pricechange.phone.JzgCarChooseStyle;
import com.jzg.pricechange.phone.MainActivity;

public class ViewUtility {

    public static void navigateToWelcomeActivity(Context context) {
        Intent intent = new Intent(context, WelcomeActivity.class);
        context.startActivity(intent);
    }

	public static void navigateToHomeActivity(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转到买车更多筛选界面
     * @param context Context
     * @param params 筛选参数Model
     */
	public static void navigateToBuyCarMoreFilterActivity(Context context,BuyCarFilterIndexModel params) {
        Intent intent = new Intent(context, BuyCarMoreFilterActivity.class);
        intent.putExtra(BuyCarMoreFilterActivity.GET_FILTER_PARAMS,params);
        ((Activity)context).startActivityForResult(intent,BuyCarMoreFilterActivity.GET_FILTER_PARAMS_CODE);
    }

    public static void navigateToWebView(Context context, String title, String urlStr) {
        Intent intent = new Intent(context, CustomWebViewActivity.class);
        intent.putExtra(CustomWebViewActivity.BUNDLE_CUSTOM_WEBKIT_TITLE, title);
        intent.putExtra(CustomWebViewActivity.BUNDLE_CUSTOM_WEBKIT_URL, urlStr);
        context.startActivity(intent);
    }

    public static void navigateToWebView(Context context, String title, String urlStr, boolean useCustomTitle) {
        Intent intent = new Intent(context, CustomWebViewActivity.class);
        intent.putExtra(CustomWebViewActivity.BUNDLE_CUSTOM_WEBKIT_TITLE, title);
        intent.putExtra(CustomWebViewActivity.BUNDLE_CUSTOM_WEBKIT_URL, urlStr);
        if (!TextUtils.isEmpty(title) && useCustomTitle){
            intent.putExtra(CustomWebViewActivity.BUNDLE_CUSTOM_USE_CUSTOM_TITLE, true);
        }
        context.startActivity(intent);
    }

    /**
     * 跳转至卖车界面
     * @param context Context
     * @param carType 默认"0"
     * @param cityId 城市Id
     * @param cityName 城市名称
     * @param brandId 品牌Id
     * @param brandName 品牌名称
     * @param modeId 车系Id
     * @param modeName 车系名
     * @param priceBegin 开始价格区间
     * @param priceEnd 结束价格区间
     * @param isLoan 是否贷款1有贷款 0无
     */
    public static void navigateToBuyCarActivity(Context context,String carType,
            String cityId,String cityName,String brandId,String brandName,
            String modeId,String modeName,String priceBegin,String priceEnd,String isLoan) {
        Intent intent = new Intent(context, BuyCarActivity.class);
        intent.putExtra(BuyCarActivity.GET_INTENT_CARTYPE,carType);
        intent.putExtra(BuyCarActivity.GET_INTENT_CITYID,cityId);
        intent.putExtra(BuyCarActivity.GET_INTENT_CITYNAME,cityName);
        intent.putExtra(BuyCarActivity.GET_INTENT_BRANDID,brandId);
        intent.putExtra(BuyCarActivity.GET_INTENT_BRANDNAME,brandName);
        intent.putExtra(BuyCarActivity.GET_INTENT_MODEID,modeId);
        intent.putExtra(BuyCarActivity.GET_INTENT_MODENAME,modeName);
        intent.putExtra(BuyCarActivity.GET_INTENT_PRICEBEGIN,priceBegin);
        intent.putExtra(BuyCarActivity.GET_INTENT_PRICEEDN,priceEnd);
        intent.putExtra(BuyCarActivity.GET_INTENT_ISLOAN,isLoan);
        context.startActivity(intent);
    }

    /**
     * 跳转到买车列表
     * @param context Context
     * @param conditionData 订阅条件
     */
    public static void navigateToBuyCarActivity(Context context, CarConditionData conditionData) {
        Intent intent = new Intent(context, BuyCarActivity.class);
        intent.putExtra(BuyCarActivity.GET_INTENT_CARCONDITIONDATA,conditionData);
        context.startActivity(intent);
    }
    /**
     * 跳转到买车搜索界面
     * @param context Context
     */
    public static void navigateToBuyCarSearchActivity(Context context) {
        Intent inSearch = new Intent(context, BuyCarSearchActivity.class);
        ((Activity)context).startActivityForResult(inSearch, BuyCarSearchActivity.TO_GET_KEYWORD);
    }

    /**
     * 跳转到买车详情界面
     * @param context Context
     * @param result 买车详情Model
     */
    public static void navigateToBuyCarDetailActivity(Context context, BuyCarDetailResult result) {
        Intent intent = new Intent(context, BuyCarDetailActivity.class);
        intent.putExtra(BuyCarDetailActivity.GET_BUYCAR_DETAIL,result);
        context.startActivity(intent);
    }
    /**
     * 跳转到卖车估值页面
     * @param context Context
     * @param style 所选车型Model
     */
    public static void navigateToValuationActivity(Context context, JzgCarChooseStyle style) {
        Intent intent = new Intent(context, ValuationActivity.class);
        intent.putExtra(ValuationActivity.GET_INTENT_CHOOSE_CARSTYLE,style);
        context.startActivity(intent);
    }
    /**
     * 跳转到卖车估值详情(我是车主)
     * @param context Context
     * @param result 买车估值详情Model
     */
    public static void navigateToValuationSellActivity(Context context,ValuationSellCarResult result) {
        Intent intent = new Intent(context, ValuationSellActivity.class);
        intent.putExtra(ValuationSellActivity.GET_VALUATION_SELLCAR_RESULT,result);
        context.startActivity(intent);
    }
    /**
     * 跳转到买车估值详情(我是买家)
     * @param context Context
     * @param result  买车估值详情
     * */
    public static void navigateToValuationBuyActivity(Context context,ValuationBuyCarResult result) {
        Intent intent = new Intent(context, ValuationBuyActivity.class);
        intent.putExtra(ValuationBuyActivity.GET_VALUATION_BUYCAR_RESULT,result);
        context.startActivity(intent);
    }
    /**
     * 跳转到卖车界面
     * @param context Context
     */
    public static void navigateToSellCarActivity(Context context,ReplaceNewCarIntentModel model) {
        Intent intent = new Intent(context, SellCarActivity.class);
        intent.putExtra(SellCarActivity.GET_SELLCAR_INTENT_MODEL,model);
        context.startActivity(intent);
    }
    /**
     * 跳转到保值率排行
     * @param context
     * @param cityId 城市Id
     * @param cityName 城市名
     * @param modelLevel 车型等级Id
     * @param modelLevelName 车型等级名
     */
    public static void navigateToValuationHedgeActivity(Context context
            ,String cityId,String cityName,String modelLevel,String modelLevelName) {
        Intent intent = new Intent(context, ValuationHedgeActivity.class);
        intent.putExtra(ValuationHedgeActivity.GET_HEDGE_PROVINCEID,"0");
        intent.putExtra(ValuationHedgeActivity.GET_HEDGE_CITYID,cityId);
        intent.putExtra(ValuationHedgeActivity.GET_HEDGE_CITYNAME,cityName);
        intent.putExtra(ValuationHedgeActivity.GET_HEDGE_MODELLEVELID,modelLevel);
        intent.putExtra(ValuationHedgeActivity.GET_HEDGE_MODELLEVELNAME,modelLevelName);
        context.startActivity(intent);
    }

    public static void navigateToMyCarListActivity(Context context) {
        Intent intent = new Intent(context, MyCarListActivity.class);
        context.startActivity(intent);
    }

    public static void navigateToAddMyCarActivity(Context context) {
        Intent intent = new Intent(context, AddMyCarActivity.class);
        context.startActivity(intent);
    }

    public static void navigateToMyFocuseCarListActivity(Context context) {
        Intent intent = new Intent(context, MyFocusCarListActivity.class);
        context.startActivity(intent);
    }

    public static void navigateToCompleteCarInfoActivity(Context context, CarManagerMyCarData carData) {
        Intent intent = new Intent(context, CompleteCarInfoActivity.class);
        intent.putExtra(CompleteCarInfoActivity.PARAM_EXTRA_CAR_DATA, carData);
        context.startActivity(intent);
    }
    /**
     * 跳转到一键卖车
     * @param context Context
     */
    public static void navigateToSellCarOneKeyActivity(Context context) {
        Intent intent = new Intent(context, SellCarOneKeyActivity.class);
        context.startActivity(intent);
    }
    /**
     * 跳转到新车置换
     * @param context Context
     */
    public static void navigateToReplaceNewCarActivity(Context context) {
        Intent intent = new Intent(context, ReplaceNewCarActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转至新车置换
     * @param context Context
     * @param model 带数据模型
     */
    public static void navigateToReplaceNewCarActivity(Context context,ReplaceNewCarIntentModel model) {
        Intent intent = new Intent(context, ReplaceNewCarActivity.class);
        intent.putExtra(ReplaceNewCarActivity.GET_REPLACE_INTENT_MODEL,model);
        context.startActivity(intent);
    }
    /**
     * 跳转到车抵贷
     * @param context
     */
    public static void navigateToSellCarLoanActivity(Context context) {
        Intent intent = new Intent(context, SellCarLoanActivity.class);
        context.startActivity(intent);
    }

    public static void navigateToUserMessageMainActivity(Context context) {
        Intent intent = new Intent(context, UserMessageMainActivity.class);
        context.startActivity(intent);
    }

    public static void navigateToFavoriteCarListActivity(Context context) {
        Intent intent = new Intent(context, FavoriteCarListActivity.class);
        context.startActivity(intent);
    }

    public static void navigateToSubscribeCarListActivity(Context context) {
        Intent intent = new Intent(context, SubscribeCarListActivity.class);
        context.startActivity(intent);
    }

    public static void navigateToLoginActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    public static void navigateToAboutUsActivity(Context context) {
        Intent intent = new Intent(context, AboutUsActivity.class);
        context.startActivity(intent);
    }

    public static void navigateToFeedbackActivity(Context context) {
        Intent intent = new Intent(context, FeedBackActivity.class);
        context.startActivity(intent);
    }

    public static void navigateToSettingActivity(Context context) {
        Intent intent = new Intent(context, SettingActivity.class);
        context.startActivity(intent);
    }

    public static void navigateToChooseCityActivity(Context context) {
        Intent intent = new Intent(context, ChooseCityActivity.class);
        context.startActivity(intent);
    }

    public static void navigateToChooseCityActivity(Context context, int sourceFrom) {
        Intent intent = new Intent(context, ChooseCityActivity.class);
        intent.putExtra(ChooseCityActivity.PARAMS_KEY_SOURCEFROM, sourceFrom);
        context.startActivity(intent);
    }

    public static void navigateToChooseCarActivity(Context context) {
        Intent intent = new Intent(context, CarReleaseIndexCarActivity.class);
        intent.putExtra("newOrOld", 0);
        context.startActivity(intent);
    }

    /**
     * 估值车型选择界面
     * @param context
     */
    public static void navigateToCarReleaseIndexValuationActivity(Context context) {
        Intent intent = new Intent(context,CarReleaseIndexValuationActivity.class);
        ((Activity)context).startActivityForResult(intent,JzgCarChooseConstant.Valuation_QUERYCAR_MSG);
    }

    public static void navigateToCarReleaseIndexValuationActivity(Context context, int sourceFrom) {
        Intent intent = new Intent(context,CarReleaseIndexValuationActivity.class);
        intent.putExtra(CarReleaseIndexValuationActivity.PARAMS_KEY_SOURCEFROM, sourceFrom);
        ((Activity)context).startActivityForResult(intent,JzgCarChooseConstant.Valuation_QUERYCAR_MSG);
    }

    /**
     *
     * @param context
     * @param isHideAllCity
     */
    public static void navigateToChooseCityActivity(Context context,boolean isHideAllCity) {
        Intent intent = new Intent(context,ChooseCityActivity.class);
        if(isHideAllCity)
            intent.putExtra("hideAllRegion", "hideAllRegion");
        ((Activity)context).startActivityForResult(intent,Constant.QUERYCAR_REGION);
    }
    /**
     * 跳转到历史成交界面
     * @param context
     */
    public static void navigateToValuationSellHistoryActivity(Context context,RequestHistoryDealParams params) {
        Intent intent = new Intent(context, ValuationHistoryDealPriceActivity.class);
        intent.putExtra(ValuationHistoryDealPriceActivity.GET_VAL_TYPE_PARAMS,params);
        context.startActivity(intent);
    }

    /**
     * 跳转到违章查询界面
     *
     */
    public static void navigateToOffenceCountQueryActivity(Context context){
        String url = "http://m.jingzhengu.com/v5/Illegal.ashx?userid=";
        if(AppContext.isLogin()){
            url = url + AppContext.mLoginResultModels.getId();
        }else{
            url = url + "0";
        }
        ViewUtility.navigateToWebView(context, "违章查询", url, true);
    }

    public static void navigateToCarManagerActivity(Context context, boolean isRefresh) {
        Intent intent = new Intent(context, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(HomeActivity.PARAMS_KEY_FORWARD_TARGET, HomeActivity.FORWARD_TO_CARMANAGER);
        intent.putExtra(HomeActivity.PARAMS_KEY_REFRESH_CAR_MANAGER, isRefresh);
        context.startActivity(intent);
    }

    public static void navigateEditUserInfoActivity(Context context) {
        Intent intent = new Intent(context, EditUserInfoActivity.class);
        context.startActivity(intent);
    }
}

