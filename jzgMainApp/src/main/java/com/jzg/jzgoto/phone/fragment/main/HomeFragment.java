package com.jzg.jzgoto.phone.fragment.main;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.ListView;

import butterknife.ButterKnife;
import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.activity.common.ConstantForAct;
import com.jzg.jzgoto.phone.adapter.main.HomeListAdpater;
import com.jzg.jzgoto.phone.fragment.shared.BaseFragment;
import com.jzg.jzgoto.phone.global.AppContext;
import com.jzg.jzgoto.phone.models.BannerData;
import com.jzg.jzgoto.phone.models.RecommendCardData;
import com.jzg.jzgoto.phone.models.RecommendCardList;
import com.jzg.jzgoto.phone.models.business.home.RequestHomeDataParams;
import com.jzg.jzgoto.phone.models.business.home.RequestHomeDataResult;
import com.jzg.jzgoto.phone.services.business.home.HomeService;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends BaseFragment implements OnClickListener, ViewTreeObserver.OnScrollChangedListener {
	public final static int REQUEST_CODE_HOMEDATA = 1;

	private AppContext mAppContext;
	private HomeListAdpater mListAdapter;
	protected ListView mListView;
	private View mHeaderBarView = null;
	private HomeService mHomeService = null;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mAppContext = (AppContext) getActivity().getApplicationContext();
		mHomeService = new HomeService(getActivity(), this);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_home_main_layout, null);
		ButterKnife.bind(this, view);
		this.mListView =  (ListView) view.findViewById(R.id.homefragment_listview);
		View footerView = inflater.inflate(R.layout.view_home_footer_view, null);
		mListView.addFooterView(footerView);
		mListView.getViewTreeObserver().addOnScrollChangedListener(this);
		initAdapter();
		requestHomeData();
		return view;
	}

	public void setHeaderBarView(View headerBarView){
		mHeaderBarView = headerBarView;
	}
	
	@Override
	public void onResume() {
		super.onResume();
	}

	
	@Override
	public void onRequestSuccess(Message msg) {
		switch (msg.what) {
			case REQUEST_CODE_HOMEDATA:
				handleRequestHomeDataSuccess(msg);
				break;
		}
	}

	@Override
	public void onRequestFault(Message msg) {
		switch (msg.what) {
			case REQUEST_CODE_HOMEDATA:
				handleRequestHomeDataFailed(msg);
				break;
		}
	}

	
	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.btn_start_evaluate:
				handleStartEvaluate();
				break;
			case R.id.btn_buy_car_use_total:
				handleBuyCarUseTotal();
				break;
			case R.id.btn_buy_car_use_loan:
				handleBuyCarUseLoan();
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

	protected void initAdapter() {
		if (this.mListAdapter == null) {
			this.mListAdapter = new HomeListAdpater(this.getActivity());
			this.mListAdapter.setOnClickListener(this);
		}
		this.mListView.setAdapter(this.mListAdapter);
	}

	private void handleSelectCarStyle(){
	}

	private void handleStartEvaluate(){

	}

	private void handleBuyCarUseTotal(){

	}

	private void handleBuyCarUseLoan(){

	}

	private void handleRequestHomeDataSuccess(Message msg){
		RequestHomeDataResult homeDataResult = (RequestHomeDataResult)msg.obj;
		if (homeDataResult != null) {
			List<BannerData> bannerDataList = homeDataResult.getBannerList();
			mListAdapter.setBanners(bannerDataList);
			mListAdapter.setRecommendDataList(homeDataResult.getRecommendCardList());
			String defaultBannerImageUrl = homeDataResult.getDefaultBannerImageUrl();
			if (!TextUtils.isEmpty(defaultBannerImageUrl)){
				ConstantForAct.setHomeBannerImageUrl(this.getActivity(), defaultBannerImageUrl);
				mListAdapter.setHomeBannerImageUrl(defaultBannerImageUrl);
			}
		}
	}

	private void handleRequestHomeDataFailed(Message msg){
		mListAdapter.setRecommendDataList(null);
	}

	private void requestHomeData(){
		//execTest();
		RequestHomeDataParams requestParams = new RequestHomeDataParams();
		mHomeService.toResuestHomeData(requestParams, RequestHomeDataResult.class, REQUEST_CODE_HOMEDATA);
	}

	private void stopRequest(){
		if (mHomeService != null){
			mHomeService.stopRequest();
		}
	}

	@Override
	public void onScrollChanged() {
		if(this.isVisible()) {
			int alpha = getHeaderViewAlpha();
			mHeaderBarView.getBackground().setAlpha(alpha);
		}
	}

	public int getHeaderViewAlpha(){
		int alpha = 0;
		if (mListView != null) {
			int scrolledY = getScrollY();
			alpha = scrolledY * 255 / mListAdapter.getBannerHeight();
			if (alpha > 255) alpha = 255;
		}
		return alpha;
	}

	public int getScrollY() {
		if (mListView != null) {
			View c = mListView.getChildAt(0);
			if (c == null) {
				return 0;
			}
			int firstVisiblePosition = mListView.getFirstVisiblePosition();
			int top = c.getTop();
			return -top + firstVisiblePosition * c.getHeight();
		}
		return 0;
	}


	private void execTest(){
		try{
			AppContext.getHandler().postDelayed(new Runnable() {
				public void run() {
					String testUrl = "http://m.hao123.com/";
					String testUrl2 = "https://m.baidu.com/";
					String imageUrl = "http://imageup.jingzhengu.com/2016/06/07/1ed3fb4c-67f7-4e7c-8285-509b3f50b045_501.jpg";
					List<BannerData> banners = new ArrayList<BannerData>();
					BannerData bannerItem = new BannerData();
					bannerItem.setImageUrl(imageUrl);
					bannerItem.setWebUrl(testUrl);
					banners.add(bannerItem);
					bannerItem = new BannerData();
					bannerItem.setImageUrl(imageUrl);
					bannerItem.setWebUrl(testUrl);
					banners.add(bannerItem);
					mListAdapter.setBanners(banners);

					RecommendCardData cardItem = null;
					List<RecommendCardData> bannerItemOne = new ArrayList<RecommendCardData>();
					cardItem = new RecommendCardData();
					cardItem.setImageUrl(imageUrl);
					cardItem.setWebUrl(testUrl);
					bannerItemOne.add(cardItem);

					List<RecommendCardData> bannerItemTwo = new ArrayList<RecommendCardData>();
					cardItem = new RecommendCardData();
					cardItem.setImageUrl(imageUrl);
					cardItem.setWebUrl(testUrl);
					bannerItemTwo.add(cardItem);
					cardItem = new RecommendCardData();
					cardItem.setImageUrl(imageUrl);
					cardItem.setWebUrl(testUrl2);
					bannerItemTwo.add(cardItem);

					List<RecommendCardData> bannerItemThree = new ArrayList<RecommendCardData>();
					cardItem = new RecommendCardData();
					cardItem.setImageUrl(imageUrl);
					cardItem.setWebUrl(testUrl);
					bannerItemThree.add(cardItem);
					cardItem = new RecommendCardData();
					cardItem.setImageUrl(imageUrl);
					cardItem.setWebUrl(testUrl2);
					bannerItemThree.add(cardItem);
					cardItem = new RecommendCardData();
					cardItem.setImageUrl(imageUrl);
					cardItem.setWebUrl(testUrl2);
					bannerItemThree.add(cardItem);

					List<RecommendCardList> recommendList = new ArrayList<RecommendCardList>();
					RecommendCardList recommendEntity = new RecommendCardList();
					recommendEntity.setCardList(bannerItemOne);
					recommendList.add(recommendEntity);
					recommendEntity = new RecommendCardList();
					recommendEntity.setCardList(bannerItemTwo);
					recommendList.add(recommendEntity);
					recommendEntity = new RecommendCardList();
					recommendEntity.setCardList(bannerItemThree);
					recommendList.add(recommendEntity);
					//mListAdapter.setRecommendDataList(null);
					mListAdapter.setRecommendDataList(recommendList);
				}
			}, 3000);

		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
