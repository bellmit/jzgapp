package com.jzg.jzgoto.phone.activity.valuation;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.activity.choosecity.ChooseCity;
import com.jzg.jzgoto.phone.activity.choosecity.ChooseCity.CityListEntity;
import com.jzg.jzgoto.phone.activity.choosecity.ChooseCityJsonObjectImpl;
import com.jzg.jzgoto.phone.adapter.choosecity.ChooseCityAdapter;
import com.jzg.jzgoto.phone.components.self.discover.xlistview.XListView;
import com.jzg.jzgoto.phone.constant.Constant;
import com.jzg.jzgoto.phone.global.HttpServiceHelper;
import com.jzg.jzgoto.phone.json.JsonObjectImpl;
import com.jzg.jzgoto.phone.models.business.buy.PopListDataModel;
import com.jzg.jzgoto.phone.models.business.valuation.hedge.HedgeRatioParamsModels;
import com.jzg.jzgoto.phone.models.business.valuation.hedge.HedgeRatioResultModels;
import com.jzg.jzgoto.phone.services.business.citylist.CityListService;
import com.jzg.jzgoto.phone.services.business.valuation.buy.ValuationBuyService;
import com.jzg.jzgoto.phone.services.common.OnRequestFinishListener;
import com.jzg.jzgoto.phone.tools.MD5Utils;
import com.jzg.jzgoto.phone.view.valuation.ValuationHedgeItemView;
import com.jzg.pricechange.phone.JzgCarChooseChineseUtil;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WY on 2016/9/21.
 * Function :保值率排行界面
 */
public class ValuationHedgeActivity extends Activity
        implements OnRequestFinishListener{

    public static final String GET_HEDGE_PROVINCEID = "get_hedge_provinceId";
    public static final String GET_HEDGE_CITYID = "get_hedge_cityId";
    public static final String GET_HEDGE_CITYNAME = "get_hedge_cityName";
    public static final String GET_HEDGE_MODELLEVELID = "get_hedge_modelLevelId";
    public static final String GET_HEDGE_MODELLEVELNAME = "get_hedge_modelLevelName";
    private LinearLayout mLinearCity;
    private TextView mTvCity;
    private LinearLayout mLinearStyle;
    private TextView mTvStyle;
    private View mPopDropLine;

    private XListView mListContent;
    private RelativeLayout mRelativeListNull;

    private ValuationBuyService mValuationBuyService;
    private List<PopListDataModel> mStyleTypeList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valuation_hedge_layout);
        initWidget();
        initListener();
    }
    public void initWidget() {
        mStyleTypeList = Constant.getCarStyleList();
        mValuationBuyService = new ValuationBuyService(this, this);

        mPopDropLine = findViewById(R.id.v_hedge_ratio_popshow);

        mLinearCity = (LinearLayout) findViewById(R.id.linear_hedge_ratio_city);
        mTvCity = (TextView) findViewById(R.id.tv_hedge_ratio_city);
        mLinearStyle = (LinearLayout) findViewById(R.id.linear_hedge_ratio_style);
        mTvStyle = (TextView) findViewById(R.id.tv_hedge_ratio_style);

        mListContent = (XListView) findViewById(R.id.list_hedge_ratio_content);
        mListContent.setPullRefreshEnable(false);
        mListContent.setPullLoadEnable(false);
        mListContent.getmFooterView().hide();
        mRelativeListNull = (RelativeLayout) findViewById(R.id.relative_hedge_ratio_listnull);
        mRelativeListNull.setVisibility(View.GONE);
    }
    public void initListener() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.linear_hedge_ratio_city:
                        controlPopWindowShow(initPopWindowView());
                        initCityList();
                        break;
                    case R.id.linear_hedge_ratio_style:
                        controlPopWindowShow(initPopWindowViewCarType());
                        break;
                }
            }
        };
        mLinearCity.setOnClickListener(listener);
        mLinearStyle.setOnClickListener(listener);
        initListView();
        initPopWindow();

        if( null == mHedgeRatioParamsModels){
            mHedgeRatioParamsModels = new HedgeRatioParamsModels();
        }

        String provinceId = getIntent().getStringExtra(GET_HEDGE_PROVINCEID);
        String cityId = getIntent().getStringExtra(GET_HEDGE_CITYID);
        String cityName = getIntent().getStringExtra(GET_HEDGE_CITYNAME);
        String modelLevel = getIntent().getStringExtra(GET_HEDGE_MODELLEVELID);
        String levelName = getIntent().getStringExtra(GET_HEDGE_MODELLEVELNAME);
        mHedgeRatioParamsModels.ProvinceId = provinceId;
        mHedgeRatioParamsModels.CityId = cityId;
        mHedgeRatioParamsModels.ModelLevelId = modelLevel;
        mTvCity.setText(cityName);
        mTvStyle.setText(levelName);
        carStyleClickPosition = Integer.valueOf(modelLevel)-1;
        changeStyleClickColor();
    }
    private void initListView(){
        if(mMyAdapter == null){
            mMyAdapter = new MyAdapter();
        }
        mListContent.setAdapter(mMyAdapter);
    }
    private MyAdapter mMyAdapter;
    private class MyAdapter extends BaseAdapter {

        private final List<ItemBean> mList = new ArrayList<ItemBean>();
        public void setData(List<ItemBean> list){
            mList.clear();
            mList.addAll(list);
            this.notifyDataSetChanged();
        }
        public void addData(List<ItemBean> list){
            mList.addAll(list);
            this.notifyDataSetChanged();
        }

        @Override
        public int getItemViewType(int position) {
            if(position < 3){
                return 0;
            }
            return 1;
        }
        @Override
        public int getViewTypeCount() {
            return 2;
        }
        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            switch(getItemViewType(position)){
                case 0:
                    if(convertView == null){
                        convertView = new ValuationHedgeItemView(ValuationHedgeActivity.this);
                    }
                    ItemBean bean = (ItemBean) getItem(position);
                    if( null != mHedgeRatioParamsModels){
                        bean.cityId = mHedgeRatioParamsModels.CityId;
                        bean.cityName =mTvCity.getText().toString().trim();
                        bean.provId = mHedgeRatioParamsModels.ProvinceId;
                    }
                    ((ValuationHedgeItemView)convertView).setPosition(position);
                    ((ValuationHedgeItemView)convertView).initShowData(bean);
                    break;
                case 1:
                    if(convertView == null){
                        convertView = new ValuationHedgeItemView(ValuationHedgeActivity.this);
                    }
                    ItemBean bean2 = (ItemBean) getItem(position);
                    if( null != mHedgeRatioParamsModels){
                        bean2.cityId = mHedgeRatioParamsModels.CityId;
                        bean2.cityName = mTvCity.getText().toString().trim();
                        bean2.provId = mHedgeRatioParamsModels.ProvinceId;
                    }
                    ((ValuationHedgeItemView)convertView).setPosition(position);
                    ((ValuationHedgeItemView)convertView).initShowData(bean2);
                    break;
            }
            return convertView;
        }

    }
    public class ItemBean{
        public String rank;
        public String makeId;
        public String makeName;
        public String modelId;
        public String modelName;
        public String modelPic;
        public String[] years;
        public String[] data;
        public String cityId;
        public String cityName;
        public String provId;
    }
    private PopupWindow mPopWindow;
    private RelativeLayout mPopContainer;
    private void initPopWindow() {
        if (mPopWindow == null) {
            mPopWindow = new PopupWindow(ValuationHedgeActivity.this);
            View contentView = LayoutInflater.from(ValuationHedgeActivity.this).inflate(
                    R.layout.view_buycar_popupwindow, null);
            mPopContainer = (RelativeLayout) contentView
                    .findViewById(R.id.view_buycar_popWindow);
            contentView.findViewById(R.id.view_buycar_out_popWindow)
                    .setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mPopWindow.dismiss();
                        }
                    });
            mPopWindow.setContentView(contentView);
            mPopWindow.setWindowLayoutMode(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            mPopWindow.setBackgroundDrawable(new ColorDrawable(getResources()
                    .getColor(android.R.color.transparent)));
        }
//		mPopWindow.setOnDismissListener(this);
        mPopWindow.setFocusable(true);
        mPopWindow.setOutsideTouchable(true);
    }
    private void controlPopWindowShow(View view) {

        if (mPopContainer != null)
            mPopContainer.removeAllViews();
        if (mPopWindow.isShowing()) {
            mPopWindow.dismiss();
        } else {
            mPopContainer.addView(view);
            mPopWindow.showAsDropDown(mPopDropLine);
        }
    }
    private ListView mCityListView;
    private View initPopWindowView(){
        if(null == mCityListView){
            mCityListView = new ListView(ValuationHedgeActivity.this);
            mCityListView.setAdapter(chooseCityAdapter);
            mCityListView.setDivider(getResources().getDrawable(R.drawable.long_line));
            mCityListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    CityListEntity entity = (CityListEntity) ((Map<String, Object>) parent.getItemAtPosition(position)).get("name");
                    String cityName = entity.getName();

                    String cityId = entity.getCityID();

                    mHedgeRatioParamsModels.CityId = cityId;
                    mHedgeRatioParamsModels.ProvinceId = entity.getProvID();
                    mHedgeRatioParamsModels.PageIndex = 1;
                    mTvCity.setText(cityName);
                    if (mPopWindow.isShowing()) {
                        mPopWindow.dismiss();
                    }
                    toGetHedgeRatioList(REQUEST_HEDGE_RATIO);
                    mListContent.setPullLoadEnable(false);
                    mListContent.getmFooterView().hide();
                }
            });

        }
        return mCityListView;
    }
    private ListView mCarTypeListView;
    private int carStyleClickPosition = -1;
    private View initPopWindowViewCarType(){
        if(null == mCarTypeListView){
            mCarTypeListView = new ListView(ValuationHedgeActivity.this);
            mCarTypeListView.setAdapter(mCarListAdapter);
            mCarTypeListView.setDivider(getResources().getDrawable(R.drawable.long_line));
            mCarTypeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    // 车型
                    carStyleClickPosition = position;
                    mTvStyle.setText(mStyleTypeList.get(position).getText());
                    changeStyleClickColor();
                    mHedgeRatioParamsModels.ModelLevelId = mStyleTypeList.get(position).getTextId();
                    mHedgeRatioParamsModels.PageIndex = 1;
                    if (mPopWindow.isShowing()) {
                        mPopWindow.dismiss();
                    }
                    toGetHedgeRatioList(REQUEST_HEDGE_RATIO);
                    mListContent.setPullLoadEnable(false);
                    mListContent.getmFooterView().hide();
                }
            });

        }
        return mCarTypeListView;
    }

    private void changeStyleClickColor(){
        if(carStyleClickPosition!=-1)
        for(int i=0;i<mStyleTypeList.size();i++){
            if(carStyleClickPosition==i){
                mStyleTypeList.get(i).setTextColor(R.color.text_blue);
            }else{
                mStyleTypeList.get(i).setTextColor(R.color.text_item_lightgrey);
            }
        }
    }
    private BaseAdapter mCarListAdapter = new BaseAdapter() {
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                TextView tv = new TextView(ValuationHedgeActivity.this);
                tv.setBackgroundColor(Color.WHITE);
                tv.setTextColor(getResources().getColor(R.color.text_for_valuation));
                tv.setPadding(40, 40, 40, 40);
                convertView = tv;
            }
            ((TextView)convertView).setText(mStyleTypeList.get(position).getText());
            ((TextView)convertView).setTextColor(getResources().getColor(mStyleTypeList.get(position).getTextColor()));
            return convertView;
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public Object getItem(int position) {
            return mStyleTypeList.get(position);
        }
        @Override
        public int getCount() {
            return mStyleTypeList.size();
        }
    };

    private HedgeRatioResultModels mHedgeRatioResultModels;
    @Override
    protected void onResume() {
        super.onResume();
        if(null == mHedgeRatioResultModels){
            toGetHedgeRatioList(REQUEST_HEDGE_RATIO);
            mListContent.setPullLoadEnable(false);
            mListContent.getmFooterView().hide();
        }
    }
    private final int REQUEST_HEDGE_RATIO = 0x1000;
    private final int REQUEST_HEDGE_RATIO_LOAD = 0x1001;
    private HedgeRatioParamsModels mHedgeRatioParamsModels = null;
    private void toGetHedgeRatioList(int requestType){
        //	toShowLoadingDialog();
//        mLoadingView.setVisibility(View.VISIBLE);
        if( null == mHedgeRatioParamsModels){
            mHedgeRatioParamsModels = new HedgeRatioParamsModels();
            mHedgeRatioParamsModels.ProvinceId = "2";
            mHedgeRatioParamsModels.CityId = "201";
            mHedgeRatioParamsModels.ModelLevelId = "3";
            mHedgeRatioParamsModels.PageIndex = 1;
            mHedgeRatioParamsModels.PageSize = 20;
        }
        mHedgeRatioParamsModels.PageSize = 20;
      //  http://192.168.6.39:4444/APPV3/BuyCarAppraiseResult.ashx?op=GetBZLRankList&ProvinceId=2&CityId=201&ModelLevelId=3&PageIndex=1&PageSize=20
        mValuationBuyService.toGetHedgeRatioList(mHedgeRatioParamsModels, HedgeRatioResultModels.class, requestType);
    }

    @Override
    public void onRequestSuccess(Message msg) {
     //   mLoadingView.setVisibility(View.GONE);
        switch(msg.what){
            case REQUEST_HEDGE_RATIO:
                HedgeRatioResultModels models = (HedgeRatioResultModels) msg.obj;
                if(models.getStatus() == 100){
                    mHedgeRatioResultModels = models;
                    final List<HedgeRatioResultModels.Rank> ranks = models.getRankList();
                    List<ItemBean> list = new ArrayList<ItemBean>();
                    for(int i = 0;i < ranks.size();i++){
                        HedgeRatioResultModels.Rank rank = ranks.get(i);
                        ItemBean item = new ItemBean();
                        item.rank = rank.getRank();
                        item.makeId = rank.getMakeID();
                        item.makeName = rank.getMakeName();
                        item.modelPic = rank.getModelPic();

                        item.modelId = rank.getModelId();
                        item.modelName = rank.getModelName();

                        List<HedgeRatioResultModels.Detail> details = rank.getDetailList();
                        String[] years = new String[details.size()];
                        String[] datas = new String[details.size()];
                        for(int j = 0;j < details.size();j++){
                            HedgeRatioResultModels.Detail detail = details.get(j);
                            years[j] = detail.getYear();
                            datas[j] = detail.getData();
                        }
                        item.years = years;
                        item.data = datas;
                        list.add(item);
                    }
                    if(list.size() < 5){
                        mListContent.setPullLoadEnable(false);
                        mListContent.getmFooterView().hide();
                    }
                    if(list.size() == 0){
                        mRelativeListNull.setVisibility(View.VISIBLE);
                    } else {
                        mRelativeListNull.setVisibility(View.GONE);
                    }
                    mMyAdapter.setData(list);
                } else {
                    mRelativeListNull.setVisibility(View.VISIBLE);
                }
                break;
            case REQUEST_HEDGE_RATIO_LOAD:
                HedgeRatioResultModels modelsLoad = (HedgeRatioResultModels) msg.obj;
                if(modelsLoad.getStatus() == 100){
                    mHedgeRatioResultModels = modelsLoad;
                    final List<HedgeRatioResultModels.Rank> ranks = modelsLoad.getRankList();
                    List<ItemBean> list = new ArrayList<ItemBean>();
                    for(int i = 0;i < ranks.size();i++){
                        HedgeRatioResultModels.Rank rank = ranks.get(i);
                        ItemBean item = new ItemBean();
                        item.rank = rank.getRank();
                        item.makeId = rank.getMakeID();
                        item.makeName = rank.getMakeName();
                        item.modelPic = rank.getModelPic();

                        item.modelId = rank.getModelId();
                        item.modelName = rank.getModelName();
                        List<HedgeRatioResultModels.Detail> details = rank.getDetailList();
                        String[] years = new String[details.size()];
                        String[] datas = new String[details.size()];
                        for(int j = 0;j < details.size();j++){
                            HedgeRatioResultModels.Detail detail = details.get(j);
                            years[j] = detail.getYear();
                            datas[j] = detail.getData();
                        }
                        item.years = years;
                        item.data = datas;
                        list.add(item);
                    }
                    if(list.size() < 5){
                        mListContent.setPullLoadEnable(false);
                        mListContent.getmFooterView().hide();
                    }
                    mMyAdapter.addData(list);
                }
                break;
        }

    }

    @Override
    public void onRequestFault(Message msg) {
     //   mLoadingView.setVisibility(View.GONE);
        switch(msg.what){
            case REQUEST_HEDGE_RATIO:

                break;
        }
    }
    private ChooseCity chooseCity = null;
    private void initCityList(){
        CityListService cityListService = CityListService.getInstance(getApplicationContext());
        chooseCity = cityListService.queryListList();
        // 如果缓存有数据就显示缓存，没有就请求网络
        if (chooseCity.getCityList().size()>0) {
            showCityList(chooseCity);
            requestLastUpdateTime = chooseCity.getLastUpdateTime()+"";
            requestCity();
        } else {
            requestCity();
        }
    }
    public void requestCity() {
        //	toShowLoadingDialog();
     //   mLoadingView.setVisibility(View.VISIBLE);
        HttpServiceHelper.requestCitys(this, mHandler, getParams(),
                R.id.city_suc, R.id.city_fail);
    }
    private String requestLastUpdateTime = "";
    /**
     * 请求城市列表提交的参数
     */
    public Map<String, String> getParams() {
        // 在这里设置需要post的参数
        Map<String, String> map = new HashMap<String, String>();
        map.put("op", "GetCityList");
        map.put("LastUpData", requestLastUpdateTime);
        Map<String, Object> maps = new HashMap<String, Object>();
        maps.put("op", "GetCityList");
        maps.put("LastUpData", requestLastUpdateTime);
        map.put("sign", MD5Utils.getMD5Sign(maps));
        return map;
    }
    private Handler mHandler = new Handler() {
        public void dispatchMessage(android.os.Message msg) {
//			dismissLoadingDialog();
      //      mLoadingView.setVisibility(View.GONE);
            switch (msg.what) {
                // 请求城市成功
                case R.id.city_suc:
                    String result = (String) msg.obj;
                    if (JsonObjectImpl.isSuccess(ValuationHedgeActivity.this, result)) {
                        updateCity(result);
                    }
                    break;

                default:
                    break;
            }
        };
    };
    public static final String update_flag = "2";
    public void updateCity(String cityjson) {
        ChooseCityJsonObjectImpl chooseCityJsonObjectImpl = new ChooseCityJsonObjectImpl();
        ChooseCity chooseCityjson = chooseCityJsonObjectImpl
                .parserChooseCity(cityjson);
        CityListService cityListService = CityListService.getInstance(getApplicationContext());
        if (chooseCity.getCityList().size() >0) {
            for (int i = 0; i < chooseCityjson.getCityList().size(); i++) {
                if (update_flag.equals(chooseCityjson.getCityList().get(i)
                        .getStatus())) {
                    cityListService.updateListList(chooseCityjson);
                }
            }
        } else {
            // 第一次请求，全部插入数据
//			toShowLoadingDialog();
            cityListService.insertCityList(chooseCityjson);
        }
        chooseCity = cityListService.queryListList();
//		dismissLoadingDialog();
        showCityList(chooseCity);

    }
    private ChooseCityAdapter chooseCityAdapter;
    /**
     * 显示城市数据
     */
    protected void showCityList(ChooseCity chooseCity) {

        List<CityListEntity> lists = chooseCity.getCityList();
        if (lists != null) {

            ArrayList<CityListEntity> citys = new ArrayList<CityListEntity>();
            ArrayList<CityListEntity> recitys = new ArrayList<CityListEntity>();
            for (int i = 0; i < lists.size(); i++) {
                CityListEntity chooseCity1 = new CityListEntity();
                chooseCity1.setCityID(lists.get(i).getCityID());
                chooseCity1.setGroupName(lists.get(i).getGroupName());
                chooseCity1.setIsHotCity(lists.get(i).getIsHotCity());
                chooseCity1.setName(lists.get(i).getName());
                chooseCity1.setProvID(lists.get(i).getProvID());
                chooseCity1.setOrderIndex(lists.get(i).getOrderIndex());
                chooseCity1.setStatus(lists.get(i).getStatus());

                citys.add(chooseCity1);
                if ("1".equals(lists.get(i).getIsHotCity())) {
                    recitys.add(chooseCity1);
                }
            }

            String contactSort = null;
            ArrayList<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
            if (citys != null) {
                Map<String, Object> map = null;
                for (int i = 0; i < citys.size(); i++) {
                    map = new HashMap<String, Object>();
                    map.put("name", citys.get(i));
                    contactSort = JzgCarChooseChineseUtil
                            .getFullSpell(
                                    ((CityListEntity) map.get("name"))
                                            .getName().toString())
                            .toUpperCase().substring(0, 1);
                    map.put("Sort", contactSort);
                    items.add(map);
                }
                Comparator comp = new Mycomparator();
                Collections.sort(items, comp);

                Comparator order = new MyOrderIndex();
                Collections.sort(recitys, order);

                HashMap<String, Object> mapre = null;
                for (int i = 0; i < recitys.size(); i++) {
                    mapre = new HashMap<String, Object>();
                    mapre.put("name", recitys.get(i));
                    mapre.put("Sort", "热");
                    items.add(0, mapre);
                }

                chooseCityAdapter = new ChooseCityAdapter(
                        getApplicationContext(), items);
                mCityListView.setAdapter(chooseCityAdapter);
                chooseCityAdapter.notifyDataSetChanged();
//				mCityListView.setOnItemClickListener(this);
            }
        }
    }
    public class Mycomparator implements Comparator {
        @Override
        public int compare(Object o1, Object o2) {
            Map<String, Object> c1 = (Map<String, Object>) o1;
            Map<String, Object> c2 = (Map<String, Object>) o2;
            Comparator cmp = Collator.getInstance(java.util.Locale.ENGLISH);
            return cmp.compare(c1.get("Sort"), c2.get("Sort"));
        }
    }

    class MyOrderIndex implements Comparator {
        public int compare(Object o1, Object o2) {
            CityListEntity s1 = (CityListEntity) o1;
            CityListEntity s2 = (CityListEntity) o2;
            if (Integer.parseInt(s1.getOrderIndex()) < Integer.parseInt(s2
                    .getOrderIndex()))
                return 1;
            return -1;
        }
    }

}
