package com.jzg.jzgoto.phone.tools;

import java.util.HashMap;

import com.jzg.jzgoto.phone.global.AppContext;
import com.umeng.analytics.MobclickAgent;

import android.content.Context;
import android.text.TextUtils;

/**
 * @Description: 统计数据埋点
 * @Package com.jzg.jzgoto.phone.tools CountClickTool.java
 * @author gf
 * @date 2016-6-21 下午1:59:50
 */
public class CountClickTool {
	public final static void onEvent(final Context context,String tag){
		if(TextUtils.isEmpty(tag))return;
		if(AppContext.isLogin()){
			HashMap<String, String> maps = new HashMap<String,String>();
			String userId = AppContext.mLoginResultModels.getId();
			if(!TextUtils.isEmpty(userId))
			maps.put("user_id", userId);
			MobclickAgent.onEvent(context, tag,maps);
			return;
		}
		MobclickAgent.onEvent(context, tag);
	}
}
