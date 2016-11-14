package com.jzg.jzgoto.phone.services.business.user;


import android.content.Context;
import com.jzg.jzgoto.phone.models.business.user.RequestUserMessageListParams;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;
import com.jzg.jzgoto.phone.services.common.BaseService;
import com.jzg.jzgoto.phone.services.common.OnRequestFinishListener;

public class UserMessageService extends BaseService {

	public UserMessageService(Context context, OnRequestFinishListener listener) {
		super(context, listener);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 获取消息列表
	 * @param params
	 * @param clazz
	 * @param requestCode
	 */
	public void toResuestMessageListData(RequestUserMessageListParams params, Class<? extends BaseResultModels> clazz, int requestCode){
		toRequest(params, clazz,requestCode);
	}
}
