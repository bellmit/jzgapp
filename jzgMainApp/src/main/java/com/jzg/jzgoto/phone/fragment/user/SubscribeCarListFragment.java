package com.jzg.jzgoto.phone.fragment.user;


import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.adapter.user.SubscribeListAdapter;
import com.jzg.jzgoto.phone.event.SubscribeCancelEvent;
import com.jzg.jzgoto.phone.fragment.shared.BaseFragment;
import com.jzg.jzgoto.phone.global.AppContext;
import com.jzg.jzgoto.phone.models.CarConditionData;
import com.jzg.jzgoto.phone.models.CarData;
import com.jzg.jzgoto.phone.models.business.user.RequestSubscribeCarParams;
import com.jzg.jzgoto.phone.models.business.user.RequestSubscribeCarRemoveOneParams;
import com.jzg.jzgoto.phone.models.business.user.RequestSubscribeCarRemoveOneResult;
import com.jzg.jzgoto.phone.models.business.user.RequestSubscribeCarResult;
import com.jzg.jzgoto.phone.models.common.CommonModelSettings;
import com.jzg.jzgoto.phone.services.business.user.SubscribeCarService;;
import com.jzg.jzgoto.phone.tools.ToastManager;
import com.jzg.jzgoto.phone.tools.view.swipemenulistview.SwipeMenuListView;
import com.jzg.jzgoto.phone.view.error.BaseErrorView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;


public class SubscribeCarListFragment extends BaseFragment implements AbsListView.OnItemClickListener,
		SwipeMenuListView.IXListViewListener {

	public final static int REQUEST_CODE_SUBSCRIBE_CAR_LIST = 1;
	public final static int REQUEST_CODE_REMOVE_SUBSCRIBE_CAR = 2;

	private AppContext mAppContext;
	private SubscribeListAdapter mSubscribeListAdapter;
	private List<CarConditionData> mSubscribeDataList = new ArrayList<CarConditionData>();
	private SubscribeCarService mSubscribeService = null;
	private RequestSubscribeCarParams mRequestParams;
	private int mSubscribeCarId;
	private int mPageIndex = 1;
	private String mRefreshTime = "";
	private static final SimpleDateFormat mTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Bind(R.id.subscribe_car_list_view)
	SwipeMenuListView mSubscribeListView;
	@Bind(R.id.empty_view)
	View mEmptyView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		EventBus.getDefault().register(this);
		mAppContext = (AppContext) getActivity().getApplicationContext();
		mSubscribeService = new SubscribeCarService(getActivity(), this);
		mRequestParams = new RequestSubscribeCarParams();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_user_subscribe_car_list_layout, null);
		ButterKnife.bind(this, view);
		//setupErrorView((ViewGroup) view);
		initViews();
		requestData();
		return view;
	}
	
	@Override
	public void onResume() {
		super.onResume();
	}

	
	@Override
	public void onRequestSuccess(Message msg) {
		switch (msg.what) {
			case REQUEST_CODE_SUBSCRIBE_CAR_LIST:
				handleRequestDataSuccess(msg);
				break;
			case REQUEST_CODE_REMOVE_SUBSCRIBE_CAR:
				handleRequestRemoveSubscribeCarSuccess(msg);
				break;
		}
	}

	@Override
	public void onRequestFault(Message msg) {
		switch (msg.what) {
			case REQUEST_CODE_SUBSCRIBE_CAR_LIST:
				handleRequestDataFailed(msg);
				break;
			case REQUEST_CODE_REMOVE_SUBSCRIBE_CAR:
				handleRequestRemoveSubscribeCarFailed(msg);
				break;
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onDestroy() {
		EventBus.getDefault().unregister(this);
		super.onDestroy();
	}

	public void onEvent(SubscribeCancelEvent event) {
		if (event != null && event.subscribeInfo != null){
			mSubscribeCarId = event.subscribeInfo.getId();
			requestRemoveSubscribeCar(mSubscribeCarId);
//			if (mSubscribeDataList != null){
//				int size = mSubscribeDataList.size();
//				mSubscribeDataList.remove(event.subscribeInfo);
//				mSubscribeListAdapter.setDataList(mSubscribeDataList);
//			}
		}
	}

	@Override
	public void onRefresh() {
		mPageIndex = 1;
		mRequestParams.PageIndex = mPageIndex;
		if(TextUtils.isEmpty(mRefreshTime)){
			mSubscribeListView.setRefreshTime("首次刷新");
		}else{
			mSubscribeListView.setRefreshTime(mRefreshTime);
		}
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		mRefreshTime = mTimeFormatter.format(curDate);
		requestData();
	}

	@Override
	public void onLoadMore() {
		mPageIndex++;
		mRequestParams.PageIndex = mPageIndex;
		requestData();
	}

	public void stopRequest(){
		hideLoadingView();
		if (mSubscribeService != null) {
			mSubscribeService.stopRequest();
		}
		if (mSubscribeListView != null){
			mSubscribeListView.stopLoadMore();
			mSubscribeListView.stopRefresh();
		}
	}

	private void initViews() {
		mSubscribeListAdapter = new SubscribeListAdapter(this.getActivity());
		mSubscribeListView.setAdapter(mSubscribeListAdapter);
		mSubscribeListView.setOnItemClickListener(this);
		mSubscribeListView.setXListViewListener(this);
		mSubscribeListView.setPullLoadEnable(true);
		mSubscribeListView.setPullRefreshEnable(true);
		mSubscribeListView.getmFooterView().hide();
		//mSubscribeListView.setEmptyView(mEmptyView);
		// 设置HeaderView 是否有旋转动画
		mSubscribeListView.isShowCarRefreshAndLoadMoreAnim(true, true);
	}

	private void setData(){
		if (mSubscribeDataList != null && mSubscribeDataList.size() > 0){
			mSubscribeListView.setVisibility(View.VISIBLE);
		}else{
			mSubscribeListView.setVisibility(View.GONE);
		}
	}

	private void removeSubscribe(int subscribeId){
		if (mSubscribeDataList != null){
			int size = mSubscribeDataList.size();
			CarConditionData carConditionData = null;
			for(int i = 0; i < size; i++){
				carConditionData = mSubscribeDataList.get(i);
				if (carConditionData != null && carConditionData.getId() == subscribeId){
					mSubscribeDataList.remove(i);
					mSubscribeListAdapter.setDataList(mSubscribeDataList);
					return;
				}
			}
		}
	}

	private void handleRequestDataSuccess(Message msg){
		if (getActivity() == null){
			return;
		}
		hideLoadingView();
		mSubscribeListView.stopLoadMore();
		mSubscribeListView.stopRefresh();
		mSubscribeListView.getmFooterView().hide();
		RequestSubscribeCarResult subscribeCarResult = (RequestSubscribeCarResult)msg.obj;
		if (subscribeCarResult != null && subscribeCarResult.getStatus() == CommonModelSettings.STATUS_SUCCESS
				&& subscribeCarResult.getSubscribeDataList() != null) {
			List<CarConditionData> dataList = subscribeCarResult.getSubscribeDataList();
			if (mPageIndex == 1) {
				mSubscribeDataList.clear();
			}
			if (dataList.size() >= CommonModelSettings.PAGE_SIZE) {
				mSubscribeListView.setPullLoadEnable(true);
			}else{
				mSubscribeListView.setPullLoadEnable(false);
			}
			mSubscribeDataList.addAll(dataList);
			mSubscribeListAdapter.setDataList(mSubscribeDataList);
			if (mSubscribeDataList.size() == 0){
				showDataEmptyView();
			}
		}else{
			showDataEmptyView();
		}
	}

	private void handleRequestDataFailed(Message msg){
		if (getActivity() == null){
			return;
		}
		hideLoadingView();
		mSubscribeListView.stopLoadMore();
		mSubscribeListView.stopRefresh();
		showToast(getResources().getString(R.string.error_noConnect));
	}

	private void handleRequestRemoveSubscribeCarSuccess(Message msg){
		hideWaitingDialog();
		removeSubscribe(mSubscribeCarId);
		if (mSubscribeDataList.size() == 0){
			showDataEmptyView();
		}
	}

	private void handleRequestRemoveSubscribeCarFailed(Message msg){
		hideWaitingDialog();
		ToastManager.getInstance().showToastCenter(this.getActivity(), R.string.main_tip_operate_failed);
	}

	private void requestData(){
		//execTest();
		showLoadingView();
		mRequestParams.uid = "0";//test code
		if (AppContext.isLogin()) {
			mRequestParams.uid = AppContext.mLoginResultModels.getId();
		}
		mSubscribeService.toResuestSubscribeCarListData(mRequestParams, RequestSubscribeCarResult.class, REQUEST_CODE_SUBSCRIBE_CAR_LIST);
	}

	private void requestRemoveSubscribeCar(int subscribeId){
		RequestSubscribeCarRemoveOneParams requestParams = new RequestSubscribeCarRemoveOneParams();
		requestParams.uid = "0";
		requestParams.Cid = subscribeId;
		if (AppContext.isLogin()) {
			requestParams.uid = AppContext.mLoginResultModels.getId();
		}
		mSubscribeService.toResuestRemoveSubscribeCar(requestParams, RequestSubscribeCarRemoveOneResult.class, REQUEST_CODE_REMOVE_SUBSCRIBE_CAR);
		showWaitingDialog(this.getResources().getString(R.string.main_tip_operate_waiting));
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
		Object obj = adapterView.getAdapter().getItem(position);
		if (obj != null && obj instanceof CarConditionData) {
		}
	}

	protected void showLoadingView(){
		showWaitingDialog(this.getResources().getString(R.string.main_tip_data_loading));
	}

	protected void hideLoadingView(){
		hideWaitingDialog();
	}

	protected void showDataEmptyView(){
		mEmptyView.setVisibility(View.VISIBLE);
	}

	private void execTest() {
		try {
			AppContext.getHandler().postDelayed(new Runnable() {
				public void run() {
					List<CarData> carList = new ArrayList<CarData>();
					CarData carItem = null;
					carItem = new CarData();
					carList.add(carItem);
					carItem = new CarData();
					carList.add(carItem);

					List<CarConditionData> dataList = new ArrayList<CarConditionData>();
					CarConditionData dataItem = null;
					dataItem = new CarConditionData();
					dataItem.setCarList(carList);
					dataList.add(dataItem);
					dataItem = new CarConditionData();
					dataItem.setCarList(carList);
					dataList.add(dataItem);
					mSubscribeDataList = dataList;
					mSubscribeListAdapter.setDataList(dataList);
					setData();
				}
			}, 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
