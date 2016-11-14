package com.jzg.jzgoto.phone.tools;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jzg.jzgoto.phone.global.AppContext;
import com.jzg.jzgoto.phone.models.business.valuation.sell.RequestValFragmentHistory;
/**
 * 估值历史记录
 * @author wangying
 * @date 2016年6月16日
 * @className
 */
public class ValHistoryCacheUtils {

	/**
	 * 保存历史估值记录列表
	 * @param context
	 * @param cacheList
	 */
	public static void toSaveHistoryListCache(Context context,List<RequestValFragmentHistory> cacheList){
		Gson gson = new Gson();
		String str = gson.toJson(cacheList);
		String uid ="没有登录";
		if(AppContext.isLogin()){
			uid = AppContext.mLoginResultModels.getId();
		}
		SharedPreferences sharePreferences = 
				context.getSharedPreferences("Jzg_Val_History", Context.MODE_PRIVATE);
		System.out.println("缓存历史记录"+str);
		Editor editor = sharePreferences.edit();
		editor.putString(uid, str);
		editor.commit();
	}
	/**
	 * 获取历史估值记录列表
	 * @param context
	 * @return
	 */
	public static List<RequestValFragmentHistory> getValHistoryList(Context context,String getHistoryId){
		List<RequestValFragmentHistory> list = null;
		String uid = getHistoryId;
		if(TextUtils.isEmpty(uid)){
			uid ="没有登录";
			if(AppContext.isLogin()){
				uid = AppContext.mLoginResultModels.getId();
			}
		}
		String jsonStr = context.getSharedPreferences("Jzg_Val_History", Context.MODE_PRIVATE).getString(uid, "");
		System.out.println(uid+"--读取缓存历史记录"+jsonStr);
		if(!TextUtils.isEmpty(jsonStr)){
			Gson gson = new Gson();
			list = gson.fromJson(jsonStr, new TypeToken<ArrayList<RequestValFragmentHistory>>() {}.getType());
		}
		if(list==null){
			return new ArrayList<RequestValFragmentHistory>();
		}
		return list;
	}
	/**
	 * 清除历史估值记录列表
	 * @param context
	 */
	public static void clearValHistoryCache(Context context,String clearUid){
		String uid = clearUid;
		if(TextUtils.isEmpty(uid)){
			uid ="没有登录";
			if(AppContext.isLogin()){
				uid = AppContext.mLoginResultModels.getId();
			}
		}
		SharedPreferences sharePreferences = 
				context.getSharedPreferences("Jzg_Val_History", Context.MODE_PRIVATE);
		Editor editor = sharePreferences.edit();
		editor.putString(uid, "");
		editor.commit();
	}
	//	估值类型(1、快速评估 2、精准评估)$
//		操作类型（1、我是车主 2、我要买车)$
//		车型ID$
//		上牌时间$
//		省份ID$
//		地区ID$
//		行驶里程$
//		缓存时间$
//		破损ID(多个id用逗号分开)
	public static String getLogionUploadSting(List<RequestValFragmentHistory> cacheList){
		String params = "";
		RequestValFragmentHistory model = null;
		if(cacheList.size()>0){
			for(int i=0;i<cacheList.size();i++){
				model = cacheList.get(i);
				String mileage = "";
				if(model.getMils()!=null&&!TextUtils.isEmpty(model.getMils())){
					mileage = (int)(Float.valueOf(model.getMils())*10000)+"";
				}
				String updateTime = model.getUpdateTime();
				if(updateTime.contains(".")){
					updateTime.replace(".", "-");
				}
				String modelStr = model.getAppraiseType()+"$"
						+model.getOperationType()+"$"
						+model.getStyleID()+"$"
						+model.getRegdate()+"$"
						+model.getProvID()+"$"
						+model.getCityID()+"$"
						+mileage+"$"
						+updateTime+"$"
						+model.getDamageID();
				if(i==cacheList.size()-1){
					params = params +modelStr;
				}else{
					params = params +modelStr+"|";
				}
				updateTime = null;
				mileage = null;
				model =null;
			}
		}
		return params;
	}
	/**
	 * 保存历史搜索记录列表
	 * @param context
	 * @param cacheList
	 */
	public static void toSaveSearchHistoryListCache(Context context,List<String> cacheList){
		Gson gson = new Gson();
		String str = gson.toJson(cacheList);
		SharedPreferences sharePreferences =
				context.getSharedPreferences("Jzg_Search_History", Context.MODE_PRIVATE);
		System.out.println("缓存历史记录"+str);
		Editor editor = sharePreferences.edit();
		editor.putString("jzg_buycar_searchHistory", str);
		editor.commit();
	}

	/**
	 * 读取搜索历史缓存记录
	 * @param context
	 * @return
     */
	public static List<String> getSearchHistoryList(Context context){
		List<String> list = null;
		String jsonStr = context.getSharedPreferences("Jzg_Search_History", Context.MODE_PRIVATE).getString("jzg_buycar_searchHistory", "");
		if(!TextUtils.isEmpty(jsonStr)){
			Gson gson = new Gson();
			list = gson.fromJson(jsonStr, new TypeToken<ArrayList<String>>() {}.getType());
		}
		if(list==null){
			return new ArrayList<String>();
		}
		return list;
	}


}
