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

import butterknife.Bind;
import butterknife.ButterKnife;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.adapter.shared.CarListAdapter;
import com.jzg.jzgoto.phone.fragment.shared.BaseFragment;
import com.jzg.jzgoto.phone.global.AppContext;
import com.jzg.jzgoto.phone.models.CarData;
import com.jzg.jzgoto.phone.models.business.buy.BuyCarDetailParams;
import com.jzg.jzgoto.phone.models.business.buy.BuyCarDetailResult;
import com.jzg.jzgoto.phone.models.business.user.RequestFavoriteCarClearAllParams;
import com.jzg.jzgoto.phone.models.business.user.RequestFavoriteCarClearAllResult;
import com.jzg.jzgoto.phone.models.business.user.RequestFavoriteCarClearOneParams;
import com.jzg.jzgoto.phone.models.business.user.RequestFavoriteCarClearOneResult;
import com.jzg.jzgoto.phone.models.business.user.RequestFavoriteCarListParams;
import com.jzg.jzgoto.phone.models.business.user.RequestFavoriteCarListResult;
import com.jzg.jzgoto.phone.models.common.CommonModelSettings;
import com.jzg.jzgoto.phone.services.business.buy.BuyCarService;
import com.jzg.jzgoto.phone.services.business.user.FavoriteCarService;
import com.jzg.jzgoto.phone.tools.ListViewUtils;
import com.jzg.jzgoto.phone.tools.ToastManager;
import com.jzg.jzgoto.phone.view.ViewUtility;
import com.jzg.jzgoto.phone.view.common.InScrollListView;

import java.util.ArrayList;
import java.util.List;


public class FavoriteCarListFragment extends BaseFragment implements AbsListView.OnItemClickListener {
	public final static int REQUEST_CODE_FAVORITE_CAR_LIST = 1;
	public final static int REQUEST_CODE_FAVORITE_CAR_CLEAR_ALL = 2;
	public final static int REQUEST_CODE_FAVORITE_CAR_CLEAR_SPECIFIC_ONE = 3;
	private static final int REQUEST_CODE_GET_CAR_DETAIL_DATA = 4;

	private AppContext mAppContext;
	private CarListAdapter mCarListAdapter;
	private List<CarData> mCarDataList = new ArrayList<CarData>();
	private CarListAdapter mExpiredCarListAdapter;
	private List<CarData> mExpiredCarDataList = new ArrayList<CarData>();
	private FavoriteCarService mDataService = null;
	private RequestFavoriteCarListParams mRequestParams;
	private int mFavoriteCarId;
	private BuyCarService mBuyCarService;

	@Bind(R.id.favorite_car_list_view)
	InScrollListView mFavoriteListView;
	@Bind(R.id.favorite_expired_car_list_view)
	InScrollListView mExpiredListView;
	@Bind(R.id.favorite_expired_car_container)
	View mExpiredContainer;

	private View mClearAllBtn;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mAppContext = (AppContext) getActivity().getApplicationContext();
		mDataService = new FavoriteCarService(getActivity(), this);
		mRequestParams = new RequestFavoriteCarListParams();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_user_favorite_car_list_layout, null);
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
			case REQUEST_CODE_FAVORITE_CAR_LIST:
				handleRequestDataSuccess(msg);
				break;
			case REQUEST_CODE_FAVORITE_CAR_CLEAR_ALL:
				handleRequestClearAllSuccess(msg);
				break;
			case REQUEST_CODE_FAVORITE_CAR_CLEAR_SPECIFIC_ONE:
				handleRequestClearSpecificOneSuccess(msg);
				break;
			case REQUEST_CODE_GET_CAR_DETAIL_DATA:
				handleRequestCarDetailDataSuccess(msg);
				break;
		}
	}

	@Override
	public void onRequestFault(Message msg) {
		switch (msg.what) {
			case REQUEST_CODE_FAVORITE_CAR_LIST:
				handleRequestDataFailed(msg);
				break;
			case REQUEST_CODE_FAVORITE_CAR_CLEAR_ALL:
				handleRequestClearAllFailed(msg);
				break;
			case REQUEST_CODE_FAVORITE_CAR_CLEAR_SPECIFIC_ONE:
				handleRequestClearSpecificOneFailed(msg);
				break;
			case REQUEST_CODE_GET_CAR_DETAIL_DATA:
				handleRequestCarDetailDataFailed(msg);
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
	protected void onRefreshData(){
		requestData();
	}

	private void initViews() {
		mCarListAdapter = new CarListAdapter(this.getActivity());
		mFavoriteListView.setAdapter(mCarListAdapter);
		mExpiredCarListAdapter = new CarListAdapter(this.getActivity());
		mExpiredListView.setAdapter(mExpiredCarListAdapter);
		mFavoriteListView.setOnItemClickListener(this);
		mCarListAdapter.setOffline(false);
		mExpiredCarListAdapter.setOffline(true);
	}

	private void updateViews(){
		ListViewUtils.setListViewHeight(mFavoriteListView);
		ListViewUtils.setListViewHeight(mExpiredListView);
	}

	public void setClearBtn(View clearBtn){
		mClearAllBtn = clearBtn;
        mClearAllBtn.setEnabled(false);
	}

	private void setData(){
		if (mCarDataList != null && mCarDataList.size() > 0){
			mFavoriteListView.setVisibility(View.VISIBLE);
		}else{
			mFavoriteListView.setVisibility(View.GONE);
		}
		if (mExpiredCarDataList != null && mExpiredCarDataList.size() > 0){
			mExpiredContainer.setVisibility(View.VISIBLE);
		}else{
			mExpiredContainer.setVisibility(View.GONE);
		}
	}

	private void handleRequestDataSuccess(Message msg){
		if (getActivity() == null){
			return;
		}
		hideLoadingView();
		RequestFavoriteCarListResult favoriteCarResult = (RequestFavoriteCarListResult)msg.obj;
		if (favoriteCarResult != null && favoriteCarResult.getStatus() == CommonModelSettings.STATUS_SUCCESS
				&& (favoriteCarResult.getFavoriteDataList() != null) || favoriteCarResult.getExpiredFavoriteDataList() != null){
			List<CarData> dataList = favoriteCarResult.getFavoriteDataList();
			List<CarData> expiredDataList = favoriteCarResult.getExpiredFavoriteDataList();
			CarData carData = null;
			mCarDataList.clear();
			mExpiredCarDataList.clear();
			if (dataList != null){
				mCarDataList.addAll(dataList);
			}
			if (expiredDataList != null){
				mExpiredCarDataList.addAll(expiredDataList);
			}

			mCarListAdapter.setDataList(mCarDataList);
			mExpiredCarListAdapter.setDataList(mExpiredCarDataList);
			mFavoriteListView.setVisibility(View.VISIBLE);
			mExpiredContainer.setVisibility(View.VISIBLE);
            mClearAllBtn.setEnabled(true);
			if (mCarDataList.size() == 0 && mExpiredCarDataList.size() == 0){
				showDataEmptyView();
				mFavoriteListView.setVisibility(View.GONE);
				mExpiredContainer.setVisibility(View.GONE);
			}else if (mCarDataList.size() == 0){
				mFavoriteListView.setVisibility(View.GONE);
			}else if (mExpiredCarDataList.size() == 0){
				mExpiredContainer.setVisibility(View.GONE);
			}
			this.updateViews();
		}else{
			showDataEmptyView();
		}
	}

	protected void showDataEmptyView(){
		super.showDataEmptyView();
        mClearAllBtn.setEnabled(false);
	}

	private void handleRequestDataFailed(Message msg){
		if (getActivity() == null){
			return;
		}
		showNetworkErrorView();
		ToastManager.getInstance().showToastCenter(this.getActivity(), R.string.error_noConnect);
	}

	private void handleRequestClearAllSuccess(Message msg) {
		hideWaitingDialog();
		ToastManager.getInstance().showToastCenter(this.getActivity(), R.string.main_tip_operate_success);
		RequestFavoriteCarClearAllResult favoriteCarResult = (RequestFavoriteCarClearAllResult)msg.obj;
		if (favoriteCarResult != null && favoriteCarResult.getStatus() == CommonModelSettings.STATUS_SUCCESS){
			mCarDataList.clear();
			mExpiredCarDataList.clear();
			mCarListAdapter.setDataList(mCarDataList);
			mExpiredCarListAdapter.setDataList(mExpiredCarDataList);
			mFavoriteListView.setVisibility(View.GONE);
			mExpiredContainer.setVisibility(View.GONE);
			showDataEmptyView();
			mClearAllBtn.setEnabled(false);
		}else{
			mClearAllBtn.setEnabled(true);
		}
	}

	private void handleRequestClearAllFailed(Message msg) {
		mClearAllBtn.setEnabled(true);
		ToastManager.getInstance().showToastCenter(this.getActivity(), R.string.main_tip_operate_failed);
		hideWaitingDialog();
	}

	private void handleRequestClearSpecificOneSuccess(Message msg) {
		hideWaitingDialog();
		removeFavorite(mFavoriteCarId);
	}

	private void handleRequestClearSpecificOneFailed(Message msg) {
		ToastManager.getInstance().showToastCenter(this.getActivity(), R.string.main_tip_operate_failed);
		hideWaitingDialog();
	}

	private void handleRequestCarDetailDataSuccess(Message msg){
		hideWaitingDialog();
		BuyCarDetailResult requestResult = (BuyCarDetailResult)msg.obj;
		if (requestResult != null && requestResult.getStatus() == CommonModelSettings.STATUS_SUCCESS) {
			ViewUtility.navigateToBuyCarDetailActivity(this.getActivity(), requestResult);
		}
	}

	private void handleRequestCarDetailDataFailed(Message msg){
		ToastManager.getInstance().showToastCenter(this.getActivity(), R.string.error_noConnect);
	}

	private void removeFavorite(int favoriteCarId){
		CarData carData = null;
		for (int i = 0; i < mCarDataList.size(); i++){
			carData = mCarDataList.get(i);
			if (carData != null && carData.getFavoriteId() == favoriteCarId){
				mCarDataList.remove(i);
				mCarListAdapter.setDataList(mCarDataList);
				break;
			}
		}
		for (int i = 0; i < mExpiredCarDataList.size(); i++){
			carData = mExpiredCarDataList.get(i);
			if (carData != null && carData.getFavoriteId() == favoriteCarId){
				mExpiredCarDataList.remove(i);
				mExpiredCarListAdapter.setDataList(mExpiredCarDataList);
				break;
			}
		}
		if (mCarDataList.size() == 0 && mExpiredCarDataList.size() == 0){
			showDataEmptyView();
			mFavoriteListView.setVisibility(View.GONE);
			mExpiredContainer.setVisibility(View.GONE);
		}else if (mCarDataList.size() == 0){
			mFavoriteListView.setVisibility(View.GONE);
		}else if (mExpiredCarDataList.size() == 0){
			mExpiredContainer.setVisibility(View.GONE);
		}
	}

	private void requestData(){
		//execTest();
		showLoadingView();
		mRequestParams.uid = "0";
		if (AppContext.isLogin()) {
			mRequestParams.uid = AppContext.mLoginResultModels.getId();
		}
		mDataService.toResuestFavoriteCarListData(mRequestParams, RequestFavoriteCarListResult.class, REQUEST_CODE_FAVORITE_CAR_LIST);
	}

	private void requestClearAll(){
		showWaitingDialog(this.getResources().getString(R.string.main_tip_operate_waiting));
		RequestFavoriteCarClearAllParams requestParams = new RequestFavoriteCarClearAllParams();
		mRequestParams.uid = "0";
		if (AppContext.isLogin()) {
			requestParams.uid = AppContext.mLoginResultModels.getId();
		}
		mDataService.toResuestClearAllFavoriteCar(requestParams, RequestFavoriteCarClearAllResult.class, REQUEST_CODE_FAVORITE_CAR_CLEAR_ALL);
	}

	private void requestClearSpecificOne(int id){
		showWaitingDialog(this.getResources().getString(R.string.main_tip_operate_waiting));
		RequestFavoriteCarClearOneParams requestParams = new RequestFavoriteCarClearOneParams();
		requestParams.uid = "0";//test code
		requestParams.id = id;
		if (AppContext.isLogin()) {
			requestParams.uid = AppContext.mLoginResultModels.getId();
		}
		mDataService.toResuestClearOneFavoriteCar(requestParams, RequestFavoriteCarClearOneResult.class, REQUEST_CODE_FAVORITE_CAR_CLEAR_SPECIFIC_ONE);
	}

	public void cleanFavorite(){
		mClearAllBtn.setEnabled(false);
		requestClearAll();
	}

	private void requestCarDetailData(CarData carData){
		showWaitingDialog(getResources().getString(R.string.main_tip_data_loading));
		BuyCarDetailParams params = new BuyCarDetailParams();
		mBuyCarService = new BuyCarService(this.getActivity(), this);
		if (AppContext.isLogin()) {
			params.uid = AppContext.mLoginResultModels.getId();
		}
		params.CarSourceId = String.valueOf(carData.getCarId());
		params.CarSourceFrom =  String.valueOf(carData.getCarSourceFrom());
		mBuyCarService.toResuestBuyCarDetail(params, BuyCarDetailResult.class, REQUEST_CODE_GET_CAR_DETAIL_DATA);
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
		Object obj = adapterView.getAdapter().getItem(position);
		if (obj != null && obj instanceof CarData) {
			CarData carData = (CarData)obj;
			requestCarDetailData(carData);
		}
	}

	private void execTest() {
		try {
			AppContext.getHandler().postDelayed(new Runnable() {
				public void run() {
					List<CarData> dataList = new ArrayList<CarData>();
					CarData dataItem = null;
					dataItem = new CarData();
					dataList.add(dataItem);
					dataItem = new CarData();
					dataList.add(dataItem);
					mCarDataList = dataList;
					mExpiredCarDataList = dataList;
					mCarListAdapter.setDataList(dataList);
					mExpiredCarListAdapter.setDataList(dataList);
					setData();
				}
			}, 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
