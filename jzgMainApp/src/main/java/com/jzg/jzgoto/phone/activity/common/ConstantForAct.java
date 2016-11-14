package com.jzg.jzgoto.phone.activity.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.CarValuateOptionData;
import com.jzg.jzgoto.phone.models.business.login.LoginResultModels;

/**
 * @Description: 存储显示数据
 * @Package com.jzg.jzgoto.phone.activity.common Constant.java
 * @author gf
 * @date 2015-12-23 上午11:05:11
 */
public class ConstantForAct {
	public static List<String> getYearsList(){
		List<String> mYearsList = new ArrayList<String>();
		mYearsList.add("不限");
		mYearsList.add("1年内");
		mYearsList.add("1-3年");
		mYearsList.add("3-5年");
		mYearsList.add("5-8年");
		mYearsList.add("8年以上");
		return mYearsList;
	}
	
	public static List<String> getMileageList(){
		List<String> mMileageList = new ArrayList<String>();
		mMileageList.add("不限");
		mMileageList.add("小于5000公里");
		mMileageList.add("5000-10000公里");
		mMileageList.add("1万-3万公里");
		mMileageList.add("3万-5万公里");
		mMileageList.add("5万-8万公里");
		mMileageList.add("8万-10万公里");
		return mMileageList;
	}
	
	public static List<String> getCarInfoList(){
		List<String> mCarInfoList = new ArrayList<String>();
		mCarInfoList.add("不限");
		mCarInfoList.add("准新车");
		mCarInfoList.add("车况良好");
		mCarInfoList.add("正常损耗");
		mCarInfoList.add("车况较差");
		return mCarInfoList;
	}
	
	public static List<String> getPriceList(){
		List<String> mPriceList = new ArrayList<String>();
		mPriceList.add("不限");
		mPriceList.add("3万以内");
		mPriceList.add("3-5万");
		mPriceList.add("5-10万");
		mPriceList.add("10-15万");
		mPriceList.add("15-20万");
		mPriceList.add("20-30万");
		mPriceList.add("30-50万");
		mPriceList.add("50万以上");
		return mPriceList;
	}
	
	public static List<String> getSourceList(){
		List<String> mSourceList = new ArrayList<String>();
		mSourceList.add("不限");//Id=0  不限
		mSourceList.add("个人");//Id=1  个人
		mSourceList.add("商家");//Id=2  商家
		return mSourceList;
	}
	
	public static String getDKLX(String year){
		Map<String, String> map = new HashMap<String, String>();
		map.put("1", "6.31");
		map.put("2", "6.4");
		map.put("3", "6.4");
		map.put("4", "6.65");
		map.put("5", "6.65");
		return (String) map.get(year);
	}
	
	public static List<String> getNewCarMileageList(){
		List<String> mMileageList = new ArrayList<String>();
		mMileageList.add("5000");
		mMileageList.add("10000");
		mMileageList.add("15000");
		mMileageList.add("20000");
		mMileageList.add("25000");
		mMileageList.add("30000");
		mMileageList.add("35000");
		mMileageList.add("40000");
		mMileageList.add("45000");
		mMileageList.add("50000");
		mMileageList.add("55000");
		mMileageList.add("60000");
		mMileageList.add("65000");
		mMileageList.add("70000");
		return mMileageList;
	}
	
	public static List<String> getNewCarSFBXList(){
		List<String> mResultList = new ArrayList<String>();
		mResultList.add("30");
		mResultList.add("35");
		mResultList.add("40");
		mResultList.add("45");
		mResultList.add("50");
		mResultList.add("55");
		mResultList.add("60");
		return mResultList;
	}
	public static List<String> getMileageListForCarLoan(){
		List<String> mMileageList = new ArrayList<String>();
		mMileageList.add("2万以下");
		mMileageList.add("2-4万");
		mMileageList.add("4-6万");
		mMileageList.add("6-8万");
		mMileageList.add("8-10万");
		mMileageList.add("10万以上");
		return mMileageList;
	}
	public static List<String> getMileageListValueForCarLoan(){
		List<String> mMileageList = new ArrayList<String>();
		mMileageList.add("2");
		mMileageList.add("4");
		mMileageList.add("6");
		mMileageList.add("8");
		mMileageList.add("10");
		mMileageList.add("10");
		return mMileageList;
	}
	public static List<String> getMortgateForCarLoan(){
		List<String> mMileageList = new ArrayList<String>();
		mMileageList.add("有抵押(含贷款未还清)");
		mMileageList.add("无抵押");
		return mMileageList;
	}
	public static List<String> getMortgateValueForCarLoan(){
		List<String> mMileageList = new ArrayList<String>();
		mMileageList.add("1");
		mMileageList.add("0");
		return mMileageList;
	}
	public static List<String> getLoanTimeForCarLoan(){
		List<String> mMileageList = new ArrayList<String>();
		mMileageList.add("1个月");
		mMileageList.add("3个月");
		mMileageList.add("6个月");
		mMileageList.add("1年");
		mMileageList.add("2年");
		mMileageList.add("3年");
		return mMileageList;
	}
	public static List<String> getLoanTimeValueForCarLoan(){
		List<String> mMileageList = new ArrayList<String>();
		mMileageList.add("1");
		mMileageList.add("3");
		mMileageList.add("6");
		mMileageList.add("12");
		mMileageList.add("24");
		mMileageList.add("36");
		return mMileageList;
	}
	public static List<String> getGenderForCarLoan(){
		List<String> mMileageList = new ArrayList<String>();
		mMileageList.add("男");
		mMileageList.add("女");
		return mMileageList;
	}
	public static List<String> getCreditForCarLoan(){
		List<String> mMileageList = new ArrayList<String>();
		mMileageList.add("信用良好");
		mMileageList.add("少数逾期");
		mMileageList.add("长期多数逾期");
		mMileageList.add("无信用记录");
		return mMileageList;
	}
	public static List<String> getCreditValueForCarLoan(){
		List<String> mMileageList = new ArrayList<String>();
		mMileageList.add("71");
		mMileageList.add("72");
		mMileageList.add("70");
		mMileageList.add("256");
		return mMileageList;
	}
	
	private static final String SAVE_MSG = "JZG_MSG";
	private static final String SAVE_KEY_MOBILE = "key_mobile";
	private static final String SAVE_KEY_LOGIN = "key_mobile_";
	private static final String SAVE_KEY_MSG = "key_msg";
	private static final String SAVE_KEY_USER_ID = "key_user_id";
	private static final String SAVE_KEY_CAR_MANAGER_ID = "key_car_manager_id";
	private static final String SAVE_KEY_USER_PROFILE_ID = "key_user_profile_id";
	private static final String SAVE_KEY_QUERY_SHOW_CAR_MANAGER_HOME = "key_query_show_car_manager_home_id";
	private static final String SAVE_KEY_SHOW_CAR_MANAGER_HOME = "key_show_car_manager_home_id";
	private static final String SAVE_KEY_HAS_ADD_MY_CAR = "key_has_add_my_car_id";
	private static final String SAVE_KEY_APP_VERSION_NAME = "key_app_version_name";
	private static final String SAVE_KEY_CAR_VALUATION_OPTION = "key_car_valuation_option";
	private static final String SAVE_KEY_HOME_BANNER_IMAGE_URL = "key_home_banner_image_url";
	public static void saveUserMobile(Context context,String mobile){
		Editor editor = context.getSharedPreferences(SAVE_MSG,Context.MODE_PRIVATE).edit();
		editor.putString(SAVE_KEY_MOBILE, mobile);
		editor.commit();
	}
	public static void saveUserLoginState(Context context,String mobile,boolean isLogin){
		Editor editor = context.getSharedPreferences(SAVE_MSG,Context.MODE_PRIVATE).edit();
		editor.putBoolean(SAVE_KEY_LOGIN + mobile, isLogin);
		editor.commit();
	}
	public static String getUserMobile(Context context){
		return context.getSharedPreferences(SAVE_MSG,Context.MODE_PRIVATE).getString(SAVE_KEY_MOBILE, null);
	}
	public static boolean getUserLogin(Context context,String mobile){
		return context.getSharedPreferences(SAVE_MSG,Context.MODE_PRIVATE).getBoolean(SAVE_KEY_LOGIN + mobile, false);
	}
	public static void saveUserMsg(Context context,String msg){
		Editor editor = context.getSharedPreferences(SAVE_MSG,Context.MODE_PRIVATE).edit();
		editor.putString(SAVE_KEY_MSG, msg);
		editor.commit();
	}
	public static void saveUserProfile(Context context, LoginResultModels.PersonalInfo userInfo){
		String userInfoStr = new Gson().toJson(userInfo);
		Editor editor = context.getSharedPreferences(SAVE_MSG, Context.MODE_PRIVATE).edit();
		editor.putString(SAVE_KEY_USER_PROFILE_ID, userInfoStr);
		editor.commit();
	}

	public static LoginResultModels.PersonalInfo getUserProfile(Context context){
		LoginResultModels.PersonalInfo userInfo = null;
		String userInfoStr = null;
		userInfoStr = context.getSharedPreferences(SAVE_MSG, Context.MODE_PRIVATE).getString(SAVE_KEY_USER_PROFILE_ID, null);
		if (!TextUtils.isEmpty(userInfoStr)){
			userInfo = new Gson().fromJson(userInfoStr.replaceAll("null", ""), LoginResultModels.PersonalInfo.class);
		}
		return userInfo;
	}
	public static String getUserMsg(Context context){
		return context.getSharedPreferences(SAVE_MSG, Context.MODE_PRIVATE).getString(SAVE_KEY_MSG, null);
	}

	public static void saveUserId(Context context,String uid){
		Editor editor = context.getSharedPreferences(SAVE_MSG, Context.MODE_PRIVATE).edit();
		editor.putString(SAVE_KEY_USER_ID, uid);
		editor.commit();
	}

	public static String getUserId(Context context){
		return context.getSharedPreferences(SAVE_MSG, Context.MODE_PRIVATE).getString(SAVE_KEY_USER_ID, "");
	}

	public static void saveCarManagerId(Context context,String uid){
		Editor editor = context.getSharedPreferences(SAVE_MSG, Context.MODE_PRIVATE).edit();
		editor.putString(SAVE_KEY_CAR_MANAGER_ID, uid);
		editor.commit();
	}

	public static String getCarManagerId(Context context){
		return context.getSharedPreferences(SAVE_MSG, Context.MODE_PRIVATE).getString(SAVE_KEY_CAR_MANAGER_ID, "0");
	}

	public static void setQueryShowCarManagerAsHome(Context context, boolean isQueried){
		Editor editor = context.getSharedPreferences(SAVE_MSG, Context.MODE_PRIVATE).edit();
		editor.putBoolean(SAVE_KEY_QUERY_SHOW_CAR_MANAGER_HOME, isQueried);
		editor.commit();
	}

	public static boolean getQueryShowCarManagerAsHome(Context context){
		return context.getSharedPreferences(SAVE_MSG, Context.MODE_PRIVATE).getBoolean(SAVE_KEY_QUERY_SHOW_CAR_MANAGER_HOME, false);
	}

	public static void setShowCarManagerAsHome(Context context, boolean isShow){
		Editor editor = context.getSharedPreferences(SAVE_MSG, Context.MODE_PRIVATE).edit();
		editor.putBoolean(SAVE_KEY_SHOW_CAR_MANAGER_HOME, isShow);
		editor.commit();
	}

	public static boolean getShowCarManagerAsHome(Context context){
		return context.getSharedPreferences(SAVE_MSG, Context.MODE_PRIVATE).getBoolean(SAVE_KEY_SHOW_CAR_MANAGER_HOME, false);
	}

	public static void setHasAddMyCar(Context context, boolean hasAdded){
		Editor editor = context.getSharedPreferences(SAVE_MSG, Context.MODE_PRIVATE).edit();
		editor.putBoolean(SAVE_KEY_HAS_ADD_MY_CAR, hasAdded);
		editor.commit();
	}

	public static boolean getHasAddMyCar(Context context){
		return context.getSharedPreferences(SAVE_MSG, Context.MODE_PRIVATE).getBoolean(SAVE_KEY_HAS_ADD_MY_CAR, false);
	}

	public static String getAppVersionName(Context context){
		return context.getSharedPreferences(SAVE_MSG, Context.MODE_PRIVATE).getString(SAVE_KEY_APP_VERSION_NAME, "");
	}

	public static void setAppVersionName(Context context, String versionName){
		Editor editor = context.getSharedPreferences(SAVE_MSG, Context.MODE_PRIVATE).edit();
		editor.putString(SAVE_KEY_APP_VERSION_NAME, versionName);
		editor.commit();
	}

	public static CarValuateOptionData getCarValuationOption(Context context){
		CarValuateOptionData carInfo = null;
		String carInfoStr = null;
		carInfoStr = context.getSharedPreferences(SAVE_MSG, Context.MODE_PRIVATE).getString(SAVE_KEY_CAR_VALUATION_OPTION, null);
		if (!TextUtils.isEmpty(carInfoStr)){
			carInfo = new Gson().fromJson(carInfoStr.replaceAll("null", ""), CarValuateOptionData.class);
		}
		return carInfo;
	}

	public static void setCarValuationOption(Context context, CarValuateOptionData carInfo){
		String carInfoStr = new Gson().toJson(carInfo);
		Editor editor = context.getSharedPreferences(SAVE_MSG, Context.MODE_PRIVATE).edit();
		editor.putString(SAVE_KEY_CAR_VALUATION_OPTION, carInfoStr);
		editor.commit();
	}

	public static void setHomeBannerImageUrl(Context context, String imageUrl){
		Editor editor = context.getSharedPreferences(SAVE_MSG, Context.MODE_PRIVATE).edit();
		editor.putString(SAVE_KEY_HOME_BANNER_IMAGE_URL, imageUrl);
		editor.commit();
	}

	public static String getHomeBannerImageUrl(Context context){
		return context.getSharedPreferences(SAVE_MSG, Context.MODE_PRIVATE).getString(SAVE_KEY_HOME_BANNER_IMAGE_URL, "");
	}
}
