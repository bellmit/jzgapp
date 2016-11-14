package com.jzg.jzgoto.phone.models.business.user;


import java.util.List;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;

public class RequestUserMessageListResult extends BaseResultModels {


	private static final long serialVersionUID = 1L;

	public List<MessageData> InformList;
	public List<MessageData> NoticeList;

	@Override
	public void setResult(Object obj) {
	}

	@Override
	public String toResultString() {
		return null;
	}

	public List<MessageData> getPrivateMessageDataList(){
		return InformList;
	}

	public List<MessageData> getPublicMessageDataList(){
		return NoticeList;
	}

}
