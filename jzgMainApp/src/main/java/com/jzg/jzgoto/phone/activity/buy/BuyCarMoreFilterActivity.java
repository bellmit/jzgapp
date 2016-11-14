package com.jzg.jzgoto.phone.activity.buy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.constant.Constant;
import com.jzg.jzgoto.phone.models.business.buy.BuyCarFilterIndexModel;
import com.jzg.jzgoto.phone.models.business.buy.BuyCarListParams;
import com.jzg.jzgoto.phone.models.business.buy.BuyCarListResult;
import com.jzg.jzgoto.phone.services.business.buy.BuyCarService;
import com.jzg.jzgoto.phone.services.common.OnRequestFinishListener;
import com.jzg.jzgoto.phone.tools.ClickControlTool;
import com.jzg.jzgoto.phone.view.buy.BuyCarMoreFilterGridView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jzg on 2016/9/14.
 * Function :买车—更多筛选条件
 */
public class BuyCarMoreFilterActivity extends Activity
implements OnRequestFinishListener{

    private LinearLayout mTopContainer;
    private LinearLayout mBottomContainer;
    private TextView mReturnBtn,mClearBtn;
    private TextView mCarNumber,mSubmitBtn;

    private BuyCarFilterIndexModel mFilterParams,mFromBuyCarParams;
    public static final int CAR_STYLE_CODE = 1002;
    public static final int GET_FILTER_PARAMS_CODE = 1003;

    public static final int CAR_TYPE_SUV = 51;
    public static final int CAR_TYPE_MPV = 53;
    public static final String GET_FILTER_PARAMS = "get_filter_activity_params";
    private BuyCarMoreFilterGridView mCarTypeView,mCarPlatformView,
            mCarPriceView,mCarAgeView,mCarMileageView,mCarVolumeView,
            mCarBianSuView,mCarSeatsView,mCarCountryView;
    private TextView mStyleChooseBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buycar_morefilter_layout);
        mFromBuyCarParams = (BuyCarFilterIndexModel) getIntent().getSerializableExtra(GET_FILTER_PARAMS);
        initFilterParams();
        init();
        toRequestCarNumber();
    }
    private void initFilterParams(){
        if(mFilterParams==null)
            mFilterParams = new BuyCarFilterIndexModel();
        mFilterParams.setParams(new BuyCarListParams());
        if(mFromBuyCarParams!=null){
            mFilterParams = mFromBuyCarParams;
            mFilterParams.setPriceText(mFromBuyCarParams.getPriceText());
//            mFilterParams.setParams(mFromBuyCarParams.getParams());
//            mFilterParams.getParams().IsLoan = mFromBuyCarParams.getParams().IsLoan;
//            mFilterParams.getParams().MakeID = mFromBuyCarParams.getParams().MakeID;
//            mFilterParams.getParams().ModelID = mFromBuyCarParams.getParams().ModelID;
//            mFilterParams.getParams().CityID = mFromBuyCarParams.getParams().CityID;
//            mFilterParams.getParams().CityName = mFromBuyCarParams.getParams().CityName;
//            mFilterParams.getParams().ProvID = mFromBuyCarParams.getParams().ProvID;
//            mFilterParams.getParams().BeginSellPrice = mFromBuyCarParams.getParams().BeginSellPrice;
//            mFilterParams.getParams().EndSellPrice = mFromBuyCarParams.getParams().EndSellPrice;
  //          mFilterParams.getParams().CarType = mFromBuyCarParams.getParams().CarType;
        }
    }
    private void toRequestCarNumber(){
        //请求车源数量
        new BuyCarService(BuyCarMoreFilterActivity.this, BuyCarMoreFilterActivity.this)
                .toResuestBuyCarList(mFilterParams.getParams(), BuyCarListResult.class,R.id.request_buy_car_list);
    }

    @Override
    public void onRequestSuccess(Message msg) {
        switch (msg.what){
            case R.id.request_buy_car_list:
                BuyCarListResult result =(BuyCarListResult) msg.obj;
                if(result.getStatus()==Constant.SUCCESS){
                    System.out.println("车源条数--"+result.getNewCartotalNum());
                    mCarNumber.setText("为您找到"+result.getNewCartotalNum()+"辆车");
                }else if(result.getStatus()==0){
                    mCarNumber.setText("共0辆符合条件");
                }else{
                    mCarNumber.setText("共"+result.getNewCartotalNum()+"辆符合条件");
                }
                break;
        }
    }

    @Override
    public void onRequestFault(Message msg) {

    }

    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           if(!ClickControlTool.isCanToClick())return;
            switch (v.getId()) {
                case R.id.buycar_moreFilter_return_textView:
                    //返回
                    finish();
                    break;
                case R.id.buycar_moreFilter_right_textView:
                    //清空
                    clearChooseParams();
                    break;
                case R.id.advance_carsource_submit:
                    //提交按钮
                    if(mFromBuyCarParams!=null){
                        mFilterParams.setBrandName(mFromBuyCarParams.getBrandName());
                        mFilterParams.setModeName(mFromBuyCarParams.getModeName());
                        mFilterParams.getParams().MakeID = mFromBuyCarParams.getParams().MakeID;
                        mFilterParams.getParams().ModelID = mFromBuyCarParams.getParams().ModelID;
                        mFilterParams.getParams().IsLoan = mFromBuyCarParams.getParams().IsLoan;
                        mFilterParams.getParams().Sore = mFromBuyCarParams.getParams().Sore;
                        mFilterParams.getParams().CityID = mFromBuyCarParams.getParams().CityID;
                        mFilterParams.getParams().ProvID = mFromBuyCarParams.getParams().ProvID;
                        mFilterParams.getParams().CityName = mFromBuyCarParams.getParams().CityName;
                        mFilterParams.getParams().keyword = mFromBuyCarParams.getParams().keyword;
                        Intent in = new Intent();
                        in.putExtra(GET_FILTER_PARAMS,mFilterParams);
                        setResult(GET_FILTER_PARAMS_CODE,in);
                        finish();
                    }
                    break;
                case R.id.view_car_style_choosebtn:
                    //车型选择
                    Intent styleColor = new Intent(BuyCarMoreFilterActivity.this,BuyCarStyleChooseActivity.class);
                    startActivityForResult(styleColor,CAR_STYLE_CODE);
                    break;
                case R.id.view_car_style_all:
                    toChangeStyleBackground(0);
                    mFilterParams.getParams().ModelLevel = "0";
                    mStyleChooseBtn.setText("不限");
                    mFilterParams.setCarStyleIndex(0);
                    mFilterParams.setCarStyleText("不限");
                    toRequestCarNumber();
                    break;
                case R.id.view_car_source_style_jincou:
                    toChangeStyleBackground(1);
                    mFilterParams.getParams().ModelLevel = "3";
                    mStyleChooseBtn.setText("紧凑型");
                    mFilterParams.setCarStyleIndex(1);
                    mFilterParams.setCarStyleText("紧凑型");
                    toRequestCarNumber();
                    break;
                case R.id.view_car_source_style_zhongxing:
                    mFilterParams.getParams().ModelLevel = "4";
                    toChangeStyleBackground(2);
                    mStyleChooseBtn.setText("中型车");
                    mFilterParams.setCarStyleIndex(2);
                    mFilterParams.setCarStyleText("中型车");
                    toRequestCarNumber();
                    break;
                case R.id.view_car_source_style_daxing:
                    mFilterParams.getParams().ModelLevel = "6";
                    toChangeStyleBackground(3);
                    mStyleChooseBtn.setText("大型车");
                    mFilterParams.setCarStyleIndex(3);
                    mFilterParams.setCarStyleText("大型车");
                    toRequestCarNumber();
                    break;
                case R.id.view_car_source_style_suv:
                    toChangeStyleBackground(4);
                    //ModelLevel = 51
                    mFilterParams.getParams().ModelLevel = String.valueOf(CAR_TYPE_SUV);
                    mStyleChooseBtn.setText("SUV");
                    mFilterParams.setCarStyleIndex(4);
                    mFilterParams.setCarStyleText("SUV");
                    toRequestCarNumber();
                    break;
                case R.id.view_car_source_style_mvp:
                    toChangeStyleBackground(5);
                    //ModelLevel = 53
                    mFilterParams.getParams().ModelLevel = String.valueOf(CAR_TYPE_MPV);
                    mStyleChooseBtn.setText("MPV");
                    mFilterParams.setCarStyleIndex(5);
                    mFilterParams.setCarStyleText("MPV");
                    toRequestCarNumber();
                    break;
            }
        }
    };
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==CAR_STYLE_CODE&resultCode==CAR_STYLE_CODE){
            String style = data.getStringExtra(BuyCarStyleChooseActivity.CHOOSE_CAR_STYLE);
            String styleId = data.getStringExtra(BuyCarStyleChooseActivity.CHOOSE_CAR_STYLE_ID);
            mStyleChooseBtn.setText(style);
            mFilterParams.setCarStyleIndex(-1);
            mFilterParams.setCarStyleText(style);
            mFilterParams.getParams().ModelLevel = styleId;
            switch (styleId) {
                case "0":
                    //不限
                    toChangeStyleBackground(0);
                    break;
                case "3":
                    toChangeStyleBackground(1);
                    break;
                case "4":
                    toChangeStyleBackground(2);
                    break;
                case "6":
                    toChangeStyleBackground(3);
                    break;
                default:
                    toChangeStyleBackground(-1);
                    break;
            }
            toRequestCarNumber();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private void toInitCarType(){
        int index = 0;
        if(mFromBuyCarParams!=null)index = mFromBuyCarParams.getSourceType();
        mCarTypeView = new BuyCarMoreFilterGridView(this);
        mCarTypeView.toInitShowData("车源", index , Constant.BUY_CAR_FILTER_SOUYCE_TYPE);
        mCarTypeView.setGridItemClickCallback(new BuyCarMoreFilterGridView.GridItemClickCallback() {
            @Override
            public void onGridItemClick(BuyCarMoreFilterGridView.GridViewData itemData) {
                mFilterParams.getParams().CSUserType = String.valueOf(itemData.getTextId());
                mFilterParams.setSourceType(itemData.getTextId());
                toRequestCarNumber();
            }
        });
        mTopContainer.addView(mCarTypeView);
    }
    private void toInitCarPlatform(){
        int index = 0;
        if(mFromBuyCarParams!=null)index = mFromBuyCarParams.getCarPlatformIndex();
        mCarPlatformView = new BuyCarMoreFilterGridView(this);
        mCarPlatformView.toInitShowData("平台",index , Constant.BUY_CAR_FILTER_PLATFORM_TYPE);
        mCarPlatformView.setGridItemClickCallback(new BuyCarMoreFilterGridView.GridItemClickCallback() {
            @Override
            public void onGridItemClick(BuyCarMoreFilterGridView.GridViewData itemData) {
                int carAgeKey = itemData.getTextId();
              //  1.精真估  3.易车二手车,4.大搜车,5.58,6.二手车之家,7.看车网
                if(carAgeKey>=2){
                    mFilterParams.getParams().CarSourceFrom = String.valueOf(carAgeKey+1);
                }else{
                    mFilterParams.getParams().CarSourceFrom = String.valueOf(carAgeKey);
                }
                mFilterParams.setCarPlatformIndex(carAgeKey);
                toRequestCarNumber();
            }
        });
        mTopContainer.addView(mCarPlatformView);
    }
    private void toInitCarPrice(){
        int index = 0;
        if(mFromBuyCarParams!=null)index = mFromBuyCarParams.getPriceIndex();
        mCarPriceView = new BuyCarMoreFilterGridView(this);
        mCarPriceView.toInitShowData("价格", index, Constant.BUY_CAR_FILTER_PRICE);
        mCarPriceView.setGridItemClickCallback(new BuyCarMoreFilterGridView.GridItemClickCallback() {
            @Override
            public void onGridItemClick(BuyCarMoreFilterGridView.GridViewData itemData) {
                int carAgeKey = itemData.getTextId();
                mFilterParams.getParams().BeginSellPrice = Constant.BUY_CAR_FILTER_PRICE_BEGIN[carAgeKey];
                mFilterParams.getParams().EndSellPrice = Constant.BUY_CAR_FILTER_PRICE_END[carAgeKey];
                mFilterParams.setPriceIndex(carAgeKey);
                mFilterParams.setPriceText("");
                toRequestCarNumber();
            }
        });
        mBottomContainer.addView(mCarPriceView);
    }
    private Map<Integer,String> mBeginCarAge = new HashMap<Integer,String>();
    private Map<Integer,String> mEndCarAge = new HashMap<Integer,String>();
    private void toInitCarAge(){
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH)+1;
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

        String monthStr = month+"";
        if(month<10)monthStr = "0"+month;

        String dayStr = day+"";
        if(day<10)dayStr = "0"+day;

        mBeginCarAge.put(0,"0");mEndCarAge.put(0,"0");
        mBeginCarAge.put(1,(year-1)+""+monthStr+dayStr);mEndCarAge.put(1,year+""+monthStr+dayStr);
        mBeginCarAge.put(2,(year-3)+""+monthStr+dayStr);mEndCarAge.put(2,(year-1)+""+monthStr+dayStr);
        mBeginCarAge.put(3,(year-5)+""+monthStr+dayStr);mEndCarAge.put(3,(year-3)+""+monthStr+dayStr);
        mBeginCarAge.put(4,"19900101");mEndCarAge.put(4,(year-5)+""+monthStr+dayStr);
        //不限、1年以内、1-3年、3-5年、5年以上
        int index = 0;
        if(mFromBuyCarParams!=null)index = mFromBuyCarParams.getCheling();
        mCarAgeView = new BuyCarMoreFilterGridView(this);
        mCarAgeView.toInitShowData("车龄", index, Constant.BUY_CAR_FILTER_CARAGR);
        mCarAgeView.setGridItemClickCallback(new BuyCarMoreFilterGridView.GridItemClickCallback() {
            @Override
            public void onGridItemClick(BuyCarMoreFilterGridView.GridViewData itemData) {
                int carAgeKey = itemData.getTextId();
                mFilterParams.getParams().BeginCarAge = mBeginCarAge.get(carAgeKey);
                mFilterParams.getParams().EndCarAge = mEndCarAge.get(carAgeKey);
                mFilterParams.setCheling(carAgeKey);
                toRequestCarNumber();
            }
        });
        mBottomContainer.addView(mCarAgeView);
    }
    private Map<Integer,String> mBeginMileage = new HashMap<Integer,String>();
    private Map<Integer,String> mEndMileage = new HashMap<Integer,String>();
    private void toInitCarMileage(){
        mBeginMileage.put(0,"0");mEndMileage.put(0,"0");
        mBeginMileage.put(1,"0");mEndMileage.put(1,"10000");
        mBeginMileage.put(2,"0");mEndMileage.put(2,"30000");
        mBeginMileage.put(3,"0");mEndMileage.put(3,"50000");
        mBeginMileage.put(4,"0");mEndMileage.put(4,"100000");
        mBeginMileage.put(5,"100000");mEndMileage.put(5,"990000");
        //	不限、1万公里以下、3万公里以下、5万公里以下、10万公里以下、10万公里以上
        int index = 0;
        if(mFromBuyCarParams!=null)index = mFromBuyCarParams.getLicheng();
        mCarMileageView = new BuyCarMoreFilterGridView(this);
        mCarMileageView.toInitShowData("里程(公里)", index, Constant.BUY_CAR_FILTER_MILEAGE);
        mCarMileageView.setGridItemClickCallback(new BuyCarMoreFilterGridView.GridItemClickCallback() {
            @Override
            public void onGridItemClick(BuyCarMoreFilterGridView.GridViewData itemData) {
                int carAgeKey = itemData.getTextId();
                mFilterParams.getParams().BeginMileage = mBeginMileage.get(carAgeKey);
                mFilterParams.getParams().EndMileage = mEndMileage.get(carAgeKey);
                mFilterParams.setLicheng(carAgeKey);
                toRequestCarNumber();
            }
        });
        mBottomContainer.addView(mCarMileageView);
    }
    private Map<Integer,String> mBeginPailiang = new HashMap<>();
    private Map<Integer,String> mEndPailiang = new HashMap<>();
    private void toInitCarVolume(){
        mBeginPailiang.put(0,"");mEndPailiang.put(0,"");
        mBeginPailiang.put(1,"0");mEndPailiang.put(1,"1.0");
        mBeginPailiang.put(2,"1.0");mEndPailiang.put(2,"1.6");
        mBeginPailiang.put(3,"1.7");mEndPailiang.put(3,"2.0");
        mBeginPailiang.put(4,"2.1");mEndPailiang.put(4,"2.5");
        mBeginPailiang.put(5,"2.6");mEndPailiang.put(5,"3.5");
        mBeginPailiang.put(6,"3.5");mEndPailiang.put(6,"100");
        int index = 0;
        if(mFromBuyCarParams!=null)index = mFromBuyCarParams.getPailiang();
        mCarVolumeView = new BuyCarMoreFilterGridView(this);
        mCarVolumeView.toInitShowData("排量", index, Constant.BUY_CAR_FILTER_PAILIANG);
        mCarVolumeView.setGridItemClickCallback(new BuyCarMoreFilterGridView.GridItemClickCallback() {
            @Override
            public void onGridItemClick(BuyCarMoreFilterGridView.GridViewData itemData) {
                int carPlKey = itemData.getTextId();
                mFilterParams.setPailiang(carPlKey);
                mFilterParams.getParams().BeginPL = mBeginPailiang.get(carPlKey);
                mFilterParams.getParams().EndPL = mEndPailiang.get(carPlKey);
                toRequestCarNumber();
            }
        });
        mBottomContainer.addView(mCarVolumeView);
    }

    private void toInitCarBianSu(){
        int index = 0;
        if(mFromBuyCarParams!=null)index = mFromBuyCarParams.getBiansuxiang();
        mCarBianSuView = new BuyCarMoreFilterGridView(this);
        mCarBianSuView.toInitShowData("变速箱", index, Constant.BUY_CAR_FILTER_BIANSU_TYPE);
        mCarBianSuView.setGridItemClickCallback(new BuyCarMoreFilterGridView.GridItemClickCallback() {
            @Override
            public void onGridItemClick(BuyCarMoreFilterGridView.GridViewData itemData) {
                mFilterParams.setBiansuxiang(itemData.getTextId());
                //	0：全部 2：手动 3：自动
                switch (itemData.getTextId()) {
                    case 0:
                        mFilterParams.getParams().BsqSimpleValue = "0";
                        break;
                    case 1:
                        mFilterParams.getParams().BsqSimpleValue = "2";
                        break;
                    case 2:
                        mFilterParams.getParams().BsqSimpleValue = "3";
                        break;
                    default:
                        break;
                }
                toRequestCarNumber();
            }
        });
        mBottomContainer.addView(mCarBianSuView);
    }

    private void toInitCarSeatNumber(){
        int index = 0;
        if(mFromBuyCarParams!=null)index = mFromBuyCarParams.getCarSeatIndex();
        mCarSeatsView = new BuyCarMoreFilterGridView(this);
        mCarSeatsView.toInitShowData("座位数", index, Constant.BUY_CAR_FILTER_SEATS);
        mCarSeatsView.setGridItemClickCallback(new BuyCarMoreFilterGridView.GridItemClickCallback() {
            @Override
            public void onGridItemClick(BuyCarMoreFilterGridView.GridViewData itemData) {
                int carAgeKey = itemData.getTextId();
                mFilterParams.setCarSeatIndex(carAgeKey);
                switch (carAgeKey){
                    case 0:
                        mFilterParams.getParams().Seats = 0+"";
                        break;
                    case 1:
                        mFilterParams.getParams().Seats = 2+"";
                        break;
                    case 2:
                        mFilterParams.getParams().Seats = 5+"";
                        break;
                    case 3:
                        mFilterParams.getParams().Seats = 7+"";
                        break;
                    case 4:
                        mFilterParams.getParams().Seats = 999+"";
                        break;
                }
                toRequestCarNumber();
            }
        });
        mBottomContainer.addView(mCarSeatsView);
    }

    private void toInitCarCountry(){
        int index = 0;
        if(mFromBuyCarParams!=null)index = mFromBuyCarParams.getCountryIndex();
        mCarCountryView = new BuyCarMoreFilterGridView(this);
        mCarCountryView.toInitShowData("国别", index, Constant.BUY_CAR_FILTER_COUNTRY);
        mCarCountryView.setGridItemClickCallback(new BuyCarMoreFilterGridView.GridItemClickCallback() {
            @Override
            public void onGridItemClick(BuyCarMoreFilterGridView.GridViewData itemData) {
                int carAgeKey = itemData.getTextId();
                if(carAgeKey == 8){
                    mFilterParams.getParams().Countries = "999";
                }else{
                    mFilterParams.getParams().Countries = carAgeKey+"";
                }
                mFilterParams.setCountryIndex(carAgeKey);
                toRequestCarNumber();
            }
        });
        mBottomContainer.addView(mCarCountryView);
    }
    private List<TextView> mStyleList = new ArrayList<>();
    private void toInitCarStyle(){
        mStyleChooseBtn = (TextView) findViewById(R.id.view_car_style_choosebtn);
    //    mStyleChooseBtn.setOnClickListener(mClickListener);
        mStyleList.add((TextView) findViewById(R.id.view_car_style_all));
        mStyleList.add((TextView) findViewById(R.id.view_car_source_style_jincou));
        mStyleList.add((TextView) findViewById(R.id.view_car_source_style_zhongxing));
        mStyleList.add((TextView) findViewById(R.id.view_car_source_style_daxing));
        mStyleList.add((TextView) findViewById(R.id.view_car_source_style_suv));
        mStyleList.add((TextView) findViewById(R.id.view_car_source_style_mvp));
        for(int i=0;i<mStyleList.size();i++){
            mStyleList.get(i).setOnClickListener(mClickListener);
        }
        if(mFromBuyCarParams!=null){
            mStyleChooseBtn.setText(mFromBuyCarParams.getCarStyleText());
            toChangeStyleBackground(mFromBuyCarParams.getCarStyleIndex());
        }
    }
    private void clearChooseParams(){
        mCarTypeView.toChangeGridItemColor(0);
        mCarPlatformView.toChangeGridItemColor(0);
        mCarPriceView.toChangeGridItemColor(0);
        mCarAgeView.toChangeGridItemColor(0);
        mCarMileageView.toChangeGridItemColor(0);
        mCarVolumeView.toChangeGridItemColor(0);
        mCarBianSuView.toChangeGridItemColor(0);
        mCarSeatsView.toChangeGridItemColor(0);
        mCarCountryView.toChangeGridItemColor(0);
        toChangeStyleBackground(0);
        mStyleChooseBtn.setText("不限");
        String makeId =  mFilterParams.getParams().MakeID;
        String modelId = mFilterParams.getParams().ModelID;
        String cityId = mFilterParams.getParams().CityID;
        String cityName = mFilterParams.getParams().CityName;
        mFilterParams =null;
        if(mFilterParams == null){
            mFilterParams = new BuyCarFilterIndexModel();
            mFilterParams.setParams(new BuyCarListParams());
            mFilterParams.getParams().MakeID = makeId;
            mFilterParams.getParams().ModelID = modelId;
            mFilterParams.getParams().CityID = cityId;
            mFilterParams.getParams().CityName = cityName;
        }
        toRequestCarNumber();
    }
    private void init(){
        mReturnBtn = (TextView)findViewById(R.id.buycar_moreFilter_return_textView);
        mClearBtn = (TextView) findViewById(R.id.buycar_moreFilter_right_textView);
        TextView titleText = (TextView) findViewById(R.id.buycar_moreFilter_title_textView);
        titleText.setText("更多筛选");
        mCarNumber = (TextView) findViewById(R.id.advance_carsource_number);
        mSubmitBtn = (TextView) findViewById(R.id.advance_carsource_submit);
        mReturnBtn.setOnClickListener(mClickListener);
        mClearBtn .setOnClickListener(mClickListener);
        mSubmitBtn .setOnClickListener(mClickListener);
        mTopContainer = (LinearLayout) findViewById(R.id.buycar_moreFilter_sourceAndFrom_layout);
        mBottomContainer = (LinearLayout) findViewById(R.id.buycar_moreFilter_bottomContainer_layout);
        toInitCarStyle();
        toInitCarType();
        toInitCarPlatform();
        toInitCarPrice();
        toInitCarAge();
        toInitCarMileage();
        toInitCarVolume();
        toInitCarBianSu();
        toInitCarSeatNumber();
        toInitCarCountry();
        if("2".equals(mFromBuyCarParams.getParams().CarType)){
            mCarTypeView.setVisibility(View.GONE);
            mCarPlatformView.setVisibility(View.GONE);
            mCarAgeView.setVisibility(View.GONE);
            mCarMileageView.setVisibility(View.GONE);
        }else{
            mCarTypeView.setVisibility(View.VISIBLE);
            mCarPlatformView.setVisibility(View.VISIBLE);
            mCarAgeView.setVisibility(View.VISIBLE);
            mCarMileageView.setVisibility(View.VISIBLE);
        }
    }
    private static final int[] styleChooseBg = new int[]{R.drawable.all,R.drawable.jcxb,R.drawable.zxb
            ,R.drawable.dxb,R.drawable.suvb,R.drawable.mpvb};
    private static final int[] styleNotChooseBg = new int[]{R.drawable.allw,R.drawable.jcx,R.drawable.zx
            ,R.drawable.dx,R.drawable.suv,R.drawable.mpv};
    private void toChangeStyleBackground(int index){
        for(int i=0;i<mStyleList.size();i++){
            if(index ==i){
                mStyleList.get(i).setBackgroundResource(styleChooseBg[i]);
            }else{
                mStyleList.get(i).setBackgroundResource(styleNotChooseBg[i]);
            }
        }
    }
}
