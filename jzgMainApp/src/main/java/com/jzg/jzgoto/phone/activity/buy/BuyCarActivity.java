package com.jzg.jzgoto.phone.activity.buy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.activity.choosecity.ChooseCityActivity;
import com.jzg.jzgoto.phone.adapter.buy.BuyCarListAdapter;
import com.jzg.jzgoto.phone.constant.Constant;
import com.jzg.jzgoto.phone.global.AppContext;
import com.jzg.jzgoto.phone.models.CarConditionData;
import com.jzg.jzgoto.phone.models.business.buy.BuyCarDetailParams;
import com.jzg.jzgoto.phone.models.business.buy.BuyCarDetailResult;
import com.jzg.jzgoto.phone.models.business.buy.BuyCarFilterIndexModel;
import com.jzg.jzgoto.phone.models.business.buy.BuyCarItemModel;
import com.jzg.jzgoto.phone.models.business.buy.BuyCarListParams;
import com.jzg.jzgoto.phone.models.business.buy.BuyCarListResult;
import com.jzg.jzgoto.phone.services.business.buy.BuyCarService;
import com.jzg.jzgoto.phone.services.common.OnRequestFinishListener;
import com.jzg.jzgoto.phone.tools.BuyCarFilterParamsUtils;
import com.jzg.jzgoto.phone.tools.ClickControlTool;
import com.jzg.jzgoto.phone.tools.CountClickTool;
import com.jzg.jzgoto.phone.tools.ShowDialogTool;
import com.jzg.jzgoto.phone.tools.view.swipemenulistview.SwipeMenuListView;
import com.jzg.jzgoto.phone.tools.view.swipemenulistview.SwipeMenuListView.IXListViewListener;

import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

import com.jzg.jzgoto.phone.view.ViewUtility;
import com.jzg.jzgoto.phone.view.buy.BuyCarFilterTagView;
import com.jzg.jzgoto.phone.view.buy.BuyCarNewOrOldView;
import com.jzg.jzgoto.phone.view.buy.BuyCarTitleFilterView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WY on 2016/9/13.
 * Function: 买车界面
 */
public class BuyCarActivity extends Activity
        implements OnItemClickListener, IXListViewListener, OnRequestFinishListener {

    public static final String GET_INTENT_CARTYPE = "get_intent_carType";
    public static final String GET_INTENT_CITYID = "get_intent_cityId";
    public static final String GET_INTENT_CITYNAME = "get_intent_cityName";
    public static final String GET_INTENT_BRANDID = "get_intent_brandId";
    public static final String GET_INTENT_BRANDNAME = "get_intent_brandName";
    public static final String GET_INTENT_MODEID = "get_intent_modeId";
    public static final String GET_INTENT_MODENAME = "get_intent_modeName";
    public static final String GET_INTENT_PRICEBEGIN = "get_intent_priceBegin";
    public static final String GET_INTENT_PRICEEDN = "get_intent_priceEnd";
    public static final String GET_INTENT_ISLOAN = "get_intent_isLoan";
    public static final String GET_INTENT_CARCONDITIONDATA = "get_intent_conditionData";
    private SwipeMenuListView mListView;
    private BuyCarFilterTagView mFilterTagView;
    private BuyCarTitleFilterView mFilterTitleView;
    private BuyCarNewOrOldView mNewCarOrOldView;
    private RelativeLayout mSearchLayout;
    private LinearLayout mEmptyView;
    private TextView mAddCondition;
    private TextView mKeyWordsText;
    private BuyCarService mBuyCarService;
    private BuyCarFilterIndexModel mToFilterParams;
    private BuyCarListAdapter mBuyCarListAdapter;
    private TextView mReturnBtn, mCityBtn;
    private List<BuyCarItemModel> mBuyCarList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buycar_layout);
        CountClickTool.onEvent(BuyCarActivity.this, "V5_buyCar_List");
        initRequestParams();
        init();
        toRequestBuyCarList();
    }

    private void initRequestParams() {
        if (mBuyCarService == null) {
            mBuyCarService = new BuyCarService(BuyCarActivity.this, BuyCarActivity.this);
        }
        if(mToFilterParams==null){
            mToFilterParams= new BuyCarFilterIndexModel();
            mToFilterParams.setParams(new BuyCarListParams());
        }
        // 其他界面跳转带数据
        getIntentDataFromActivity();
        //订阅跳转带数据
        getIntentFromCondition();
    }

    /**
     * 订阅列表跳转
     */
    private void getIntentFromCondition(){
        CarConditionData conditionData = (CarConditionData)
                getIntent().getSerializableExtra(GET_INTENT_CARCONDITIONDATA);
        if(conditionData!=null){
            mToFilterParams = BuyCarFilterParamsUtils.getInstance().getFilterIndexModel(conditionData);
        }
    }

    /**
     * 首页游标价格范围Tag显示文字
     */
    private String mPriceRangeText = "";
    /**
     * 全网搜车跳转
     */
    private void getIntentDataFromActivity(){
        String carType = getIntent().getStringExtra(GET_INTENT_CARTYPE);
        String cityId = getIntent().getStringExtra(GET_INTENT_CITYID);
        String cityName = getIntent().getStringExtra(GET_INTENT_CITYNAME);
        String brandId = getIntent().getStringExtra(GET_INTENT_BRANDID);
        String brandName = getIntent().getStringExtra(GET_INTENT_BRANDNAME);
        String modelId = getIntent().getStringExtra(GET_INTENT_MODEID);
        String modelName = getIntent().getStringExtra(GET_INTENT_MODENAME);
        String priceBegin = getIntent().getStringExtra(GET_INTENT_PRICEBEGIN);
        String priceEnd = getIntent().getStringExtra(GET_INTENT_PRICEEDN);
        String isLoan = getIntent().getStringExtra(GET_INTENT_ISLOAN);

    //  mToFilterParams.getParams().CarType = carType;
        mToFilterParams.getParams().CityID = cityId;
        mToFilterParams.getParams().CityName = cityName;
        mToFilterParams.getParams().MakeID = brandId;
        mToFilterParams.getParams().ModelID = modelId;
        if (!TextUtils.isEmpty(priceBegin) && !TextUtils.isEmpty(priceEnd)) {
            if(Double.valueOf(priceBegin)==100&Double.valueOf(priceEnd)==99990000){
                mPriceRangeText = "100万以上";
                mToFilterParams.getParams().BeginSellPrice = (int) (Double.valueOf(priceBegin) * 10000) + "";
                mToFilterParams.getParams().EndSellPrice ="99990000";
            } else if(Double.valueOf(priceBegin)==0&&Double.valueOf(priceEnd)==99990000){
                mPriceRangeText = "0-100万+";
                mToFilterParams.getParams().BeginSellPrice = "0";
                mToFilterParams.getParams().EndSellPrice ="99990000";
            }else if(Double.valueOf(priceBegin)>0&&Double.valueOf(priceBegin)!=99990000&&Double.valueOf(priceEnd)==99990000){
                mPriceRangeText = priceBegin + "-100万+";
                mToFilterParams.getParams().BeginSellPrice = (int) (Double.valueOf(priceBegin) * 10000)+"";
                mToFilterParams.getParams().EndSellPrice ="99990000";
            }else if(Double.valueOf(priceBegin)==99990000&&Double.valueOf(priceEnd)==99990000){
                mPriceRangeText = "";
                mToFilterParams.getParams().BeginSellPrice = "";
                mToFilterParams.getParams().EndSellPrice ="";
            }else{
                mPriceRangeText = priceBegin + "-" + priceEnd + "万";
                mToFilterParams.getParams().BeginSellPrice = (int) (Double.valueOf(priceBegin) * 10000) + "";
                mToFilterParams.getParams().EndSellPrice = (int) (Double.valueOf(priceEnd) * 10000) + "";
            }
            mToFilterParams.setPriceText(mPriceRangeText);
            if(Double.valueOf(priceBegin)==50&&Double.valueOf(priceEnd)==99990000){
                mToFilterParams.setPriceIndex(8);
            }
        }
        mToFilterParams.setBrandName(brandName);
        mToFilterParams.setModeName(modelName);
        if (!TextUtils.isEmpty(isLoan)) {
            mToFilterParams.getParams().IsLoan = isLoan;
        }
    }

    private void toRequestBuyCarList() {
        ShowDialogTool.showLoadingDialog(BuyCarActivity.this);
        mBuyCarService.toResuestBuyCarList(mToFilterParams.getParams(), BuyCarListResult.class, R.id.request_buy_car_list);
    }

    /**
     * 请求买车详情
     * @param model Item数据
     */
    private void toRequestBuyCarDetail(BuyCarItemModel model) {
        ShowDialogTool.showLoadingDialog(BuyCarActivity.this);
        BuyCarDetailParams params = new BuyCarDetailParams();
        if (AppContext.isLogin()) {
            params.uid = AppContext.mLoginResultModels.getId();
        }
        params.CarSourceId = model.getCarSourceID();
        params.CarSourceFrom = model.getCarSourceFrom();
        mBuyCarService.toResuestBuyCarDetail(params, BuyCarDetailResult.class, R.id.request_buy_car_detail);
    }

    private void init() {
        mListView = (SwipeMenuListView) findViewById(R.id.buycar_swipelistView);
        mFilterTagView = new BuyCarFilterTagView(BuyCarActivity.this);
        mListView.addHeaderView(mFilterTagView);
        mFilterTagView.setRequestBuyCarCallback(new BuyCarFilterTagView.RequestBuyCarCallBack() {
            @Override
            public void toRequestBuyCarResult(BuyCarFilterIndexModel toFilterParams) {
                toFilterParams.setSortIndex(mToFilterParams.getSortIndex());
                toFilterParams.getParams().IsLoan = mToFilterParams.getParams().IsLoan;
                mToFilterParams = toFilterParams;
                mToFilterParams.getParams().PageIndex = 1;
                toRequestBuyCarList();
            }
        });
        mEmptyView = (LinearLayout) findViewById(R.id.buycar_emptyView_layout);
        mAddCondition = (TextView) findViewById(R.id.view_buycar_add_subscribe_TextView);
        mAddCondition.setOnClickListener(mClickListener);
        mKeyWordsText = (TextView) findViewById(R.id.buycar_search_keyWords);
        mListView.setOnItemClickListener(this);
        mListView.setXListViewListener(this);
        // mListView.setEmptyView(mEmptyView);
        mSearchLayout = (RelativeLayout) findViewById(R.id.buycar_search_layout);
        mSearchLayout.setOnClickListener(mClickListener);
        mReturnBtn = (TextView) findViewById(R.id.buycar_return_textView);
        mReturnBtn.setOnClickListener(mClickListener);
        mCityBtn = (TextView) findViewById(R.id.buycar_city_textView);
        mReturnBtn.setOnClickListener(mClickListener);
        mCityBtn.setOnClickListener(mClickListener);
        mNewCarOrOldView = (BuyCarNewOrOldView) findViewById(R.id.buycar_newOrOldCarView);
        mNewCarOrOldView.setRequestNewOrOldBuyCarCallback(new BuyCarNewOrOldView.RequestNewCarOrOldListCallBack() {
            @Override
            public void toRequestBuyCarResult(String carType) {
                mToFilterParams.getParams().PageIndex = 1;
                mToFilterParams.getParams().CarType = carType;
                if ("2".equals(carType)) {
                    //新车时隐藏贷款功能按钮
                    if (mFilterTitleView != null)
                        mFilterTitleView.hideIsLoanButton();
                } else {
                    if (mFilterTitleView != null)
                        mFilterTitleView.showIsLoanButton();
                }
                toRequestBuyCarList();
            }
        });
        mFilterTitleView = (BuyCarTitleFilterView) findViewById(R.id.buycar_filterView);
        mFilterTitleView.initTitleView(mToFilterParams);
        mFilterTitleView.setRequestBuyCarCallback(new BuyCarTitleFilterView.RequestBuyCarCallback() {
            @Override
            public void toRequestBuyCarListCallBack(BuyCarFilterIndexModel requestParams, boolean isClearKeyWords) {
                //请求买车列表
                mToFilterParams.getParams().PageIndex = 1;
                mToFilterParams.getParams().IsLoan = requestParams.getParams().IsLoan;
                mToFilterParams.getParams().MakeID = requestParams.getParams().MakeID;
                mToFilterParams.getParams().ModelID = requestParams.getParams().ModelID;
                mToFilterParams.getParams().Sore = requestParams.getParams().Sore;
                if (isClearKeyWords) {
                    mToFilterParams.getParams().keyword = "";
                    mKeyWordsText.setText("全网搜车");
                }else{
                    mToFilterParams.getParams().keyword = requestParams.getParams().keyword;
                    if(!TextUtils.isEmpty(requestParams.getParams().keyword))
                    mKeyWordsText.setText(requestParams.getParams().keyword);
                }
                mToFilterParams.setBrandName(requestParams.getBrandName());
                mToFilterParams.setModeName(requestParams.getModeName());
                mToFilterParams.setSortIndex(requestParams.getSortIndex());
                toRequestBuyCarList();
            }

            @Override
            public void toFilterMoreActivity() {
                ViewUtility.navigateToBuyCarMoreFilterActivity(BuyCarActivity.this, mToFilterParams);
            }
        });
    }

    @Override
    public void onRequestSuccess(Message msg) {
        //请求成功
        ShowDialogTool.dismissLoadingDialog();
        switch (msg.what) {
            case R.id.request_buy_car_list:
                //请求成功
                BuyCarListResult result = (BuyCarListResult) msg.obj;
                if(result.getStatus()==Constant.SUCCESS&result.getNewCarAndOldCarlist()!=null){
                    toShowBuyCarList(result);
                }else if(result.getStatus()==Constant.SUCCESS&result.getNewCartotalNum()==0){
                    if("0".equals(mToFilterParams.getParams().CarType)
                            ||"1".equals(mToFilterParams.getParams().CarType)){
                        mAddCondition.setVisibility(View.VISIBLE);
                    }else{
                        mAddCondition.setVisibility(View.INVISIBLE);
                    }
                    if(mBuyCarList.size()>0)mBuyCarList.clear();
                    if(mBuyCarListAdapter==null){
                        mBuyCarListAdapter= new BuyCarListAdapter(BuyCarActivity.this,mBuyCarList);
                        mListView.setAdapter(mBuyCarListAdapter);
                    }else{
                        mBuyCarListAdapter.notifyDataSetChanged();
                    }
                    if (!TextUtils.isEmpty(mToFilterParams.getParams().CityName)) {
                        mCityBtn.setText(mToFilterParams.getParams().CityName);
                    }
                    mFilterTagView.initTagsShow(mToFilterParams);
                    mEmptyView.setVisibility(View.VISIBLE);
                    mListView.getmFooterView().hide();
                }else{
                    ShowDialogTool.showCenterToast(BuyCarActivity.this,getResources().getString(R.string.error_net));
                }
                break;
            case R.id.request_buy_car_detail:
                //请求二手车详情
                BuyCarDetailResult detailResult = (BuyCarDetailResult) msg.obj;
                if (detailResult.getStatus() == Constant.SUCCESS) {
                    ViewUtility.navigateToBuyCarDetailActivity(BuyCarActivity.this, detailResult);
                } else {
                    ShowDialogTool.showCenterToast(this, getResources().getString(R.string.error_net));
                }
                break;
        }
    }

    @Override
    public void onRequestFault(Message msg) {
        //请求失败
        ShowDialogTool.dismissLoadingDialog();
        ShowDialogTool.showCenterToast(this, getResources().getString(R.string.error_net));
        if(msg.what==R.id.request_buy_car_list){
            if("0".equals(mToFilterParams.getParams().CarType)
                    ||"1".equals(mToFilterParams.getParams().CarType)){
                mAddCondition.setVisibility(View.VISIBLE);
            }else{
                mAddCondition.setVisibility(View.INVISIBLE);
            }
            if(mBuyCarList.size()>0)mBuyCarList.clear();
            if(mBuyCarListAdapter==null){
                mBuyCarListAdapter= new BuyCarListAdapter(BuyCarActivity.this,mBuyCarList);
                mListView.setAdapter(mBuyCarListAdapter);
            }else{
                mBuyCarListAdapter.notifyDataSetChanged();
            }
            if (!TextUtils.isEmpty(mToFilterParams.getParams().CityName)) {
                mCityBtn.setText(mToFilterParams.getParams().CityName);
            }
            mFilterTagView.initTagsShow(mToFilterParams);
            mEmptyView.setVisibility(View.VISIBLE);
            mListView.getmFooterView().hide();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        if(position<2){
            return;
        }
        BuyCarItemModel model = mBuyCarList.get(position - 2);
        if ("1".equals(model.getIsNewCar())) {
            //新车
            if (!TextUtils.isEmpty(model.getUrl()))
                ViewUtility.navigateToWebView(BuyCarActivity.this, "", model.getUrl());
        } else {
            //二手车
            toRequestBuyCarDetail(model);
        }
    }

    @Override
    public void onRefresh() {
        //刷新
        mToFilterParams.getParams().PageIndex = 1;
        toRequestBuyCarList();
    }

    @Override
    public void onLoadMore() {
        //翻页
        mToFilterParams.getParams().PageIndex++;
        toRequestBuyCarList();
    }

    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!ClickControlTool.isCanToClick()) return;
            switch (v.getId()) {
                case R.id.buycar_search_layout:
                    //搜车
                    CountClickTool.onEvent(BuyCarActivity.this, "V5_buyCar_search");
                    ViewUtility.navigateToBuyCarSearchActivity(BuyCarActivity.this);
                    break;
                case R.id.buycar_return_textView:
                    finish();
                    break;
                case R.id.view_buycar_add_subscribe_TextView:
                    mFilterTagView.addConditionsRequest(mToFilterParams);
                    break;
                case R.id.buycar_city_textView:
                    Intent cityIn = new Intent(BuyCarActivity.this, ChooseCityActivity.class);
                    cityIn.putExtra(ChooseCityActivity.HIDE_LOCATION_LAYOUT, ChooseCityActivity.HIDE_LOCATION_LAYOUT);
                    cityIn.putExtra(ChooseCityActivity.ADD_ALL_CITY, ChooseCityActivity.ADD_ALL_CITY);
                    startActivityForResult(cityIn, Constant.QUERYCAR_REGION);
                    break;
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == BuyCarMoreFilterActivity.GET_FILTER_PARAMS_CODE
                && resultCode == BuyCarMoreFilterActivity.GET_FILTER_PARAMS_CODE) {
            mToFilterParams = (BuyCarFilterIndexModel) data.getSerializableExtra(BuyCarMoreFilterActivity.GET_FILTER_PARAMS);
            if (TextUtils.isEmpty(mToFilterParams.getParams().keyword)) {
                mKeyWordsText.setText("全网搜车");
            }else{
                mKeyWordsText.setText(mToFilterParams.getParams().keyword);
            }
            toRequestBuyCarList();
        }
        if (requestCode == Constant.QUERYCAR_REGION) {
            if (data != null) {
                String provinceID = String.valueOf(data.getIntExtra(ChooseCityActivity.return_flag_provinceid, 0));
                String cityID = String.valueOf(data.getIntExtra(ChooseCityActivity.return_flag_cityid, 0));
                String cityName = data.getStringExtra(ChooseCityActivity.return_flag_cityname);
                if ("全国".equals(cityName)) {
                    cityID = "0";
                    provinceID = "0";
                    cityName = "";
                }
                mToFilterParams.getParams().ProvID = provinceID;
                mToFilterParams.getParams().CityID = cityID;
                mToFilterParams.getParams().CityName = cityName;
                if(TextUtils.isEmpty(cityName)){
                    mCityBtn.setText("全国");
                }else{
                    mCityBtn.setText(cityName);
                }
                toRequestBuyCarList();

            }
        }
        if (resultCode == BuyCarSearchActivity.TO_GET_KEYWORD
                && requestCode == BuyCarSearchActivity.TO_GET_KEYWORD) {
            if (data != null) {
                String keyWord = data.getStringExtra(BuyCarSearchActivity.TO_GET_KEYWORD_STRING);
                //清空所有筛选条件，排序除外
                //  if(!TextUtils.isEmpty(keyWord)){
                String sortType = mToFilterParams.getParams().Sore;
                String city = mToFilterParams.getParams().CityName;
                String cityId = mToFilterParams.getParams().CityID;
                int sortIndex = mToFilterParams.getSortIndex();
                String carType = mToFilterParams.getParams().CarType;
                String isLoan = mToFilterParams.getParams().IsLoan;
                mToFilterParams = null;
            //    initRequestParams();
                if(mToFilterParams==null){
                    mToFilterParams = new BuyCarFilterIndexModel();
                    mToFilterParams.setParams(new BuyCarListParams());
                }
                mToFilterParams.getParams().Sore = sortType;
                mToFilterParams.getParams().CityName = city;
                mToFilterParams.getParams().CityID = cityId;
                mToFilterParams.getParams().IsLoan = isLoan;
                mToFilterParams.getParams().CarType = carType;
                mToFilterParams.setSortIndex(sortIndex);
                mFilterTitleView.initTitleView(mToFilterParams);
                mToFilterParams.getParams().keyword = keyWord;
                if (TextUtils.isEmpty(keyWord)) {
                    mKeyWordsText.setText("全网搜车");
                }else{
                    mKeyWordsText.setText(keyWord);
                }
                //  }
                toRequestBuyCarList();
            }
        }
    }

    private void toShowBuyCarList(BuyCarListResult result) {
        if (result.getStatus() == Constant.SUCCESS) {
            if (mToFilterParams.getParams().PageIndex == 1) {
                // 清空对比列表
                mBuyCarList.clear();
                if (result.getNewCarAndOldCarlist().size() >= 10) {
                    mListView.getmFooterView().hide();
                    mListView.setPullLoadEnable(true);
                }
            }
            if (mToFilterParams.getParams().PageIndex >= 1) {
                if (result.getNewCarAndOldCarlist().size() < 10) {
                    mListView.getmFooterView().hide();
                    mListView.setPullLoadEnable(false);
                }
            }
            mBuyCarList.addAll(result.getNewCarAndOldCarlist());
            mListView.stopLoadMore();
            mListView.stopRefresh();
            if (mBuyCarListAdapter == null) {
                mBuyCarListAdapter = new BuyCarListAdapter(BuyCarActivity.this, mBuyCarList);
                mListView.setAdapter(mBuyCarListAdapter);
            } else {
                mBuyCarListAdapter.setDataList(mBuyCarList);
                mBuyCarListAdapter.notifyDataSetChanged();
            }
            if (!TextUtils.isEmpty(mToFilterParams.getParams().CityName)) {
                mCityBtn.setText(mToFilterParams.getParams().CityName);
            }
            mEmptyView.setVisibility(View.GONE);
            mFilterTitleView.setSortTextView(mToFilterParams.getSortIndex(),mToFilterParams.getParams().keyword);
            mFilterTagView.initTagsShow(mToFilterParams);
            if(TextUtils.isEmpty(mToFilterParams.getParams().keyword)){
                mKeyWordsText.setText("全网搜车");
            }
        } else {

//            if (mBuyCarList == null)
//                mBuyCarList = new ArrayList<>();
//            mBuyCarList.clear();
//            if (mBuyCarListAdapter != null) {
//                mBuyCarListAdapter.setDataList(mBuyCarList);
//                mBuyCarListAdapter.notifyDataSetChanged();
//            }
//            mEmptyView.setVisibility(View.VISIBLE);
//            mListView.stopLoadMore();
//            mListView.stopRefresh();
//            mListView.getmFooterView().hide();
//            mFilterTagView.initTagsShow(mToFilterParams);
//            mListView.setPullLoadEnable(false);
        }
    }
}
