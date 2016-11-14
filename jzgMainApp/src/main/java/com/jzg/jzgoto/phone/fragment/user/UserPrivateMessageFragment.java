package com.jzg.jzgoto.phone.fragment.user;


import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.adapter.user.MessageListAdapter1;
import com.jzg.jzgoto.phone.fragment.shared.BaseFragment;
import com.jzg.jzgoto.phone.global.AppContext;
import com.jzg.jzgoto.phone.models.business.user.MessageData;
import com.jzg.jzgoto.phone.models.business.user.RequestUserMessageListParams;
import com.jzg.jzgoto.phone.models.business.user.RequestUserMessageListResult;
import com.jzg.jzgoto.phone.models.common.CommonModelSettings;
import com.jzg.jzgoto.phone.services.business.user.UserMessageService;
import com.jzg.jzgoto.phone.tools.ToastManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class UserPrivateMessageFragment extends BaseFragment implements AbsListView.OnItemClickListener{

	public final static int REQUEST_CODE_GET_MESSAGE_LIST = 1;

	private AppContext mAppContext;
	private MessageListAdapter1 mMessageListAdapter;
	private List<MessageData> mMessageDataList = new ArrayList<MessageData>();
	private UserMessageService mMessageService;
	private RequestUserMessageListParams mRequestParams;
	private int mPageIndex = 1;
	private boolean mIsLoadingData = false;

	@Bind(R.id.message_list_view)
	ListView mMessageListView;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mAppContext = (AppContext) getActivity().getApplicationContext();
		mMessageService = new UserMessageService(getActivity(), this);
		mRequestParams = new RequestUserMessageListParams();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_user_private_message_layout, null);
		ButterKnife.bind(this, view);
		setupErrorView((ViewGroup) view);
		initViews();
		requestData();
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		requestData();
	}


	@Override
	public void onRequestSuccess(Message msg) {
		switch (msg.what) {
			case REQUEST_CODE_GET_MESSAGE_LIST:
				handleRequestDataSuccess(msg);
				break;
		}
	}

	@Override
	public void onRequestFault(Message msg) {
		switch (msg.what) {
			case REQUEST_CODE_GET_MESSAGE_LIST:
				handleRequestDataFailed(msg);
				break;
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
		Object obj = adapterView.getAdapter().getItem(position);
		if (obj != null && obj instanceof MessageData) {
			MessageData messageData = (MessageData)obj;
			//ViewUtility.navigateToWebView(this.getActivity(), "", messageData.getWebUrl());
		}
	}

	private void initViews() {
		mMessageListAdapter = new MessageListAdapter1(this.getActivity());
		mMessageListView.setAdapter(mMessageListAdapter);
		mMessageListView.setOnItemClickListener(this);
//		mMessageListAdapter.setListView(mMessageListView);
	}

	private void requestData(){
		if (mIsLoadingData){
			return;
		}
		mIsLoadingData = true;
		//execTest();
		showLoadingView();
		mRequestParams.uid = "1";//test code
		if (AppContext.isLogin()) {
			mRequestParams.uid = AppContext.mLoginResultModels.getId();
		}
		mMessageService.toResuestMessageListData(mRequestParams, RequestUserMessageListResult.class, REQUEST_CODE_GET_MESSAGE_LIST);
	}

	private void handleRequestDataSuccess(Message msg){
		mIsLoadingData = false;
		if (getActivity() == null){
			return;
		}
		hideLoadingView();
		RequestUserMessageListResult messageListResult = (RequestUserMessageListResult)msg.obj;
		if (messageListResult != null && messageListResult.getStatus() == CommonModelSettings.STATUS_SUCCESS
				&& messageListResult.getPrivateMessageDataList() != null) {
			List<MessageData> dataList = messageListResult.getPrivateMessageDataList();
			MessageData messageData = null;
			if (mPageIndex == 1) {
				mMessageDataList.clear();
			}
			mMessageDataList.addAll(dataList);
			mMessageListAdapter.setDataList(mMessageDataList);
			mMessageListView.setVisibility(View.VISIBLE);
			if (mMessageDataList.size() == 0){
				showDataEmptyView();
				mMessageListView.setVisibility(View.GONE);
			}
		}else{
			showDataEmptyView();
		}
	}

	private void handleRequestDataFailed(Message msg){
		mIsLoadingData = false;
		if (getActivity() == null){
			return;
		}
		showNetworkErrorView();
		ToastManager.getInstance().showToastCenter(this.getActivity(), R.string.error_noConnect);
	}

	private void execTest() {
		try {
			AppContext.getHandler().postDelayed(new Runnable() {
				public void run() {
					List<MessageData> dataList = new ArrayList<MessageData>();
					MessageData dataItem = null;
					dataItem = new MessageData();
					dataList.add(dataItem);
					dataItem = new MessageData();
					dataList.add(dataItem);
					mMessageListAdapter.setDataList(dataList);
				}
			}, 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
