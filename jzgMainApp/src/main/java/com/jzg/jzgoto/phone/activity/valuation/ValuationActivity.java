package com.jzg.jzgoto.phone.activity.valuation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.activity.choosecity.ChooseCityActivity;
import com.jzg.jzgoto.phone.constant.Constant;
import com.jzg.jzgoto.phone.global.AppContext;
import com.jzg.jzgoto.phone.models.business.valuation.ValuationBuyCarParams;
import com.jzg.jzgoto.phone.models.business.valuation.ValuationBuyCarResult;
import com.jzg.jzgoto.phone.models.business.valuation.ValuationSellCarParams;
import com.jzg.jzgoto.phone.models.business.valuation.ValuationSellCarResult;
import com.jzg.jzgoto.phone.models.business.valuation.sell.RequestValFragmentHistory;
import com.jzg.jzgoto.phone.services.business.valuation.ValuationService;
import com.jzg.jzgoto.phone.services.common.OnRequestFinishListener;
import com.jzg.jzgoto.phone.tools.ClickControlTool;
import com.jzg.jzgoto.phone.tools.CountClickTool;
import com.jzg.jzgoto.phone.tools.EditTextUtils;
import com.jzg.jzgoto.phone.tools.ShowDialogTool;
import com.jzg.jzgoto.phone.tools.StringUtil;
import com.jzg.jzgoto.phone.tools.ValHistoryCacheUtils;
import com.jzg.jzgoto.phone.view.ViewUtility;
import com.jzg.jzgoto.phone.view.valuation.ValuationHistoryView;
import com.jzg.pricechange.phone.JzgCarChooseConstant;
import com.jzg.pricechange.phone.JzgCarChooseStyle;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by WY on 2016/9/19.
 * Function :估值界面
 */
public class ValuationActivity extends Activity implements OnRequestFinishListener{

    public static final String GET_INTENT_CHOOSE_CARSTYLE = "get_intent_choose_carStyle";
    private TextView mSellCar,mBuyCar;
    private RelativeLayout mStyleLayout,mRegDateLayout,mMileageLayout,mCityLayout;
    private TextView mStyleText,mRegDateText,mCityText;
    private EditText mMileageEdit;
    private Button mSubmitButton;
    private ValuationHistoryView mValuationHistoryView;
    private ValuationService mValuationService;
    private JzgCarChooseStyle mChooseStyle;
    private String paramUid,paramStyleId = "",paramCityId,
            paramCityName = "",paramMileage = "",paramRegDate = "";
    /**
     * 上牌月数
     */
    private int mMonths;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valuation_layout);
        getIntentData();
        init();
        mValuationService = new ValuationService(this,this);
        getFirstHistoryToShowDefault();
    }

    private void getIntentData(){
        mChooseStyle = (JzgCarChooseStyle) getIntent().getSerializableExtra(GET_INTENT_CHOOSE_CARSTYLE);
    }


    /**
     * true-添加到历史估值记录,填选项进行估值
     * false- 不添加到历史估值记录，点击历史记录进行估值
     */
    private boolean isAddToValHistory = true;
    @Override
    public void onRequestSuccess(Message msg) {
        ShowDialogTool.dismissLoadingDialog();
        switch (msg.what){
            case R.id.request_valuation_sellcar_detail:
                //车主估值结果
                ValuationSellCarResult sellCarResult = (ValuationSellCarResult) msg.obj;
                if(sellCarResult.getStatus()== Constant.SUCCESS){
                    if (isAddToValHistory) {
                        //点击选项进行估值，添加到历史记录
                        mValuationHistoryView.toAddNewHistoryModel(getHistoryModel(null,
                                sellCarResult));
                    } else {
                        //点击历史记录进行估值，恢复添加历史记录状态为true,默认添加历史记录
                        isAddToValHistory = true;
                    }
                    ViewUtility.navigateToValuationSellActivity(ValuationActivity.this,sellCarResult);
                }else{
                    ShowDialogTool.showCenterToast(ValuationActivity.this,getResources().getString(R.string.error_net));
                }
                break;
            case R.id.request_valuation_buycar_detail:
                //买家估值结果
                ValuationBuyCarResult buyCarResult = (ValuationBuyCarResult) msg.obj;
                if(buyCarResult.getStatus()== Constant.SUCCESS){
                    if (isAddToValHistory) {
                        //点击选项进行估值，添加到历史记录
                        mValuationHistoryView.toAddNewHistoryModel(getHistoryModel(buyCarResult,
                                null));
                    } else {
                        //点击历史记录进行估值，恢复添加历史记录状态为true,默认添加历史记录
                        //点击历史记录进行估值时，跳转详情——跳转至卖车时，带数据为历史记录Model
                        isAddToValHistory = true;
                    }
                    ViewUtility.navigateToValuationBuyActivity(ValuationActivity.this,buyCarResult);
                }else{
                    ShowDialogTool.showCenterToast(ValuationActivity.this,getResources().getString(R.string.error_net));
                }
                break;
        }
    }

    @Override
    public void onRequestFault(Message msg) {
        ShowDialogTool.dismissLoadingDialog();
        ShowDialogTool.showCenterToast(ValuationActivity.this,getResources().getString(R.string.error_net));
    }

    private boolean isSellCar = true;
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!ClickControlTool.isCanToClick())return;
            switch (v.getId()){
                case R.id.valuation_sellCar_textView:
                    //我是车主
                    isSellCar = true;
                    controlBtnColor();
                    isAddToValHistory = true;
                    CountClickTool.onEvent(ValuationActivity.this, "V5_valuation_seller_button");
                    break;
                case R.id.valuation_buyCar_textView:
                    //我是买家
                    isSellCar = false;
                    isAddToValHistory = true;
                    CountClickTool.onEvent(ValuationActivity.this, "V5_valuation_buyer_button");
                    controlBtnColor();
                    break;
                case R.id.valuation_carStyle_layout:
                    //车型选择
                    isAddToValHistory = true;
                    ViewUtility.navigateToCarReleaseIndexValuationActivity(ValuationActivity.this);
                    break;
                case R.id.valuation_carRegDate_layout:
                    //上牌时间
                    chooseCarTime();
                    isAddToValHistory = true;
                    break;
                case R.id.valuation_carCity_layout:
                    //上牌城市
                    isAddToValHistory = true;
                    ViewUtility.navigateToChooseCityActivity(ValuationActivity.this,true);
                    break;
                case R.id.valuation_submit_button:
                    //开始估值
                    paramMileage = mMileageEdit.getText().toString().trim();
                    if(isSellCar){
                        //跳转卖车估值界面
                        if(checkEmpty())
                        toRequestSellValuation(null);
                    }else{
                        //跳转买车估值界面
                        if (checkEmpty())
                        toRequestBuyCarValuation(null);
                    }
                    break;
            }
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case JzgCarChooseConstant.Valuation_QUERYCAR_MSG:
                if (data != null) {
                    JzgCarChooseStyle style = (JzgCarChooseStyle) data
                            .getSerializableExtra("mQueryCarStyle");
                    if (style != null) {
                        mChooseStyle = style;
                        mStyleText.setText(style.getFullName());

                        paramStyleId = mChooseStyle.getId()+"";
                        mRegDateText.setText("");
                        mMileageEdit.setText("");
                        mMonths = 0;
                        paramMileage = "";
                        paramMileage = "";
                    }
                }
                break;
            case Constant.Valuation_Time_MSG:
                if (data != null) {
                    int year = data.getIntExtra("year", 2016);
                    int monthOfYear = data.getIntExtra("month", 1);
                    int thisYear = Calendar.getInstance().get(Calendar.YEAR);
                    int thisMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
                    mMonths = (thisYear - year) * 12 + (thisMonth - monthOfYear);

                    mRegDateText.setText(year + "年" + monthOfYear+"月");

                    paramRegDate = year + "-" + monthOfYear + "-01";
                }
                break;
            case Constant.QUERYCAR_REGION:
                if (data != null) {
                    String requestcityname = StringUtil.returnShi(data
                            .getStringExtra(ChooseCityActivity.return_flag_cityname));
                    mCityText.setText(requestcityname);
                    paramCityId = StringUtil.returnShi(data
                            .getStringExtra(ChooseCityActivity.return_flag_cityid));
                    paramCityName = requestcityname;
                }
                break;
            default:
                break;
        }
    }
    private boolean checkEmpty(){
        if(TextUtils.isEmpty(paramStyleId)){
            ShowDialogTool.showCenterToast(ValuationActivity.this, "请选择车型");
            return false;
        }
        if(TextUtils.isEmpty(paramRegDate)){
            ShowDialogTool.showCenterToast(ValuationActivity.this, "请选择上牌时间");
            return false;
        }
        if(TextUtils.isEmpty(paramMileage)){
            ShowDialogTool.showCenterToast(ValuationActivity.this, "请填写行驶里程");
            return false;
        }
        if(Double.valueOf(paramMileage)==0){
            ShowDialogTool.showCenterToast(ValuationActivity.this, "请输入正确里程数");
            return false;
        }
        if(Double.valueOf(paramMileage)>mMonths){
            ShowDialogTool.showCenterToast(ValuationActivity.this, "行驶里程请小于月均1万公里");
            return false;
        }
        if(TextUtils.isEmpty(paramCityName)){
            ShowDialogTool.showCenterToast(ValuationActivity.this, "请选择城市");
            return false;
        }
        return true;
    }
    private void chooseCarTime() {
        int thisMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
        if (mChooseStyle == null) {
            ShowDialogTool.showCenterToast(ValuationActivity.this, "请选择车型");
        } else {
            Intent in = new Intent(ValuationActivity.this, ValuationTimeSheetActivity.class);
            in.putExtra("Maxyear", StringUtil
                    .getYear4String(mChooseStyle.getMaxYEAR()));
            in.putExtra("Minyear", StringUtil
                    .getYear4String(mChooseStyle.getMinYEAR()));
            in.putExtra("MaxMonth", StringUtil
                    .getMonth4String(mChooseStyle.getMaxYEAR()));
            in.putExtra("MinMonth", StringUtil
                    .getMonth4String(mChooseStyle.getMinYEAR()));
            in.putExtra("CurMonth", thisMonth);
            startActivityForResult(in, Constant.Valuation_Time_MSG);
        }
    }

    /**
     * 点击历史估值记录进行估值
     * @param historyModel
     */
    private void toRequestValDetailFromHistory(RequestValFragmentHistory historyModel){
        //最后一次点击的历史记录
        //OperationType;//1-卖车估值	2-买车估值
        isAddToValHistory = false;
        if("1".equals(historyModel.getOperationType())){
            //卖车估值
            ValuationSellCarParams sellParams = new ValuationSellCarParams();
            if (AppContext.isLogin())
                sellParams.uid = AppContext.mLoginResultModels.getId();
            else
                sellParams.uid = "";
            sellParams.CityId = historyModel.getCityID();
            sellParams.cityname = historyModel.getCityName();
            sellParams.mileage = historyModel.getMils();
            sellParams.regdate = historyModel.getRegdate();
            sellParams.styleid = historyModel.getStyleID();
            toRequestSellValuation(sellParams);
        }else{
            //买车估值
            ValuationBuyCarParams buyParams = new ValuationBuyCarParams();
            if (AppContext.isLogin())
                buyParams.uid = AppContext.mLoginResultModels.getId();
            else
                buyParams.uid = "";
            buyParams.CityId = historyModel.getCityID();
            buyParams.cityname = historyModel.getCityName();
            buyParams.mileage = historyModel.getMils();
            buyParams.regdate = historyModel.getRegdate();
            buyParams.styleid = historyModel.getStyleID();
            toRequestBuyCarValuation(buyParams);
        }
    }

    private void toRequestBuyCarValuation(ValuationBuyCarParams params){
        //买家估值
        ShowDialogTool.showLoadingDialog(ValuationActivity.this);
        if(params==null){
            ValuationBuyCarParams buyCarParams = new ValuationBuyCarParams();
            buyCarParams.uid = paramUid;
            buyCarParams.styleid = paramStyleId;
            buyCarParams.regdate = paramRegDate;
            buyCarParams.mileage = paramMileage;
            buyCarParams.cityname = paramCityName;
            buyCarParams.CityId = paramCityId;
            mValuationService.toResuestValuationBuyCar(buyCarParams, ValuationBuyCarResult.class,R.id.request_valuation_buycar_detail);
        }else{
            //点击历史估值记录买车估值请求
            mValuationService.toResuestValuationBuyCar(params, ValuationBuyCarResult.class,R.id.request_valuation_buycar_detail);
        }
    }
    private void toRequestSellValuation( ValuationSellCarParams params){
        //车主估值
        ShowDialogTool.showLoadingDialog(ValuationActivity.this);
        if(params==null){
            ValuationSellCarParams sellCarParams = new ValuationSellCarParams();
            sellCarParams.uid = paramUid;
            sellCarParams.styleid = paramStyleId;
            sellCarParams.regdate = paramRegDate;
            sellCarParams.mileage = paramMileage;
            sellCarParams.cityname = paramCityName;
            sellCarParams.CityId = paramCityId;
            mValuationService.toResuestValuationSellCar(sellCarParams, ValuationSellCarResult.class,R.id.request_valuation_sellcar_detail);
        }else{
            //点击历史估值记录卖车估值请求
            mValuationService.toResuestValuationSellCar(params, ValuationSellCarResult.class,R.id.request_valuation_sellcar_detail);
        }
    }
    private void init(){
        if(AppContext.isLogin()){
            paramUid = AppContext.mLoginResultModels.getId();
        }else{
            paramUid = "0";
        }
        mSellCar = (TextView) findViewById(R.id.valuation_sellCar_textView);
        mBuyCar = (TextView) findViewById(R.id.valuation_buyCar_textView);
        mStyleLayout = (RelativeLayout) findViewById(R.id.valuation_carStyle_layout);
        mRegDateLayout = (RelativeLayout) findViewById(R.id.valuation_carRegDate_layout);
        mMileageLayout = (RelativeLayout) findViewById(R.id.valuation_carMileage_layout);
        mCityLayout = (RelativeLayout) findViewById(R.id.valuation_carCity_layout);
        mStyleText = (TextView) findViewById(R.id.valuation_carStyle_show_textView);
        mRegDateText = (TextView) findViewById(R.id.valuation_carRegDate_show_textView);
        mCityText = (TextView) findViewById(R.id.valuation_carCity_show_textView);
        mMileageEdit = (EditText) findViewById(R.id.valuation_carMileage_show_textView);
        EditTextUtils.mileageTextWatcher(mMileageEdit,ValuationActivity.this);
        mSubmitButton = (Button) findViewById(R.id.valuation_submit_button);
        mSellCar.setOnClickListener(mOnClickListener);
        mBuyCar.setOnClickListener(mOnClickListener);
        mStyleLayout.setOnClickListener(mOnClickListener);
        mRegDateLayout.setOnClickListener(mOnClickListener);
        mCityLayout.setOnClickListener(mOnClickListener);
        mSubmitButton.setOnClickListener(mOnClickListener);
        mValuationHistoryView = (ValuationHistoryView) findViewById(R.id.valuation_history_view);
        mValuationHistoryView.setRequestValCallback(new ValuationHistoryView.RequestValCallback() {
            @Override
            public void toRequestValResult(RequestValFragmentHistory historyModel) {
                CountClickTool.onEvent(ValuationActivity.this, "V5_valuation_history_item");
                toRequestValDetailFromHistory(historyModel);
            }
        });
        if(mChooseStyle!=null){
            mStyleText.setText(mChooseStyle.getFullName());
            paramStyleId = mChooseStyle.getId()+"";
            mRegDateText.setText("");
            mMileageEdit.setText("");
            paramMileage = "";
            paramMileage = "";
        }
    }
    private void controlBtnColor(){
        if(isSellCar){
            mSellCar.setTextColor(getResources().getColor(R.color.blue));
            mSellCar.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.woshichezhu_xuanzhong,0,0);
            mBuyCar.setTextColor(getResources().getColor(R.color.grey_for_valuation));
            mBuyCar.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.woshimaijia_moren,0,0);
        }else{
            mBuyCar.setTextColor(getResources().getColor(R.color.blue));
            mBuyCar.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.woshimaijia_xuanzhong,0,0);
            mSellCar.setTextColor(getResources().getColor(R.color.grey_for_valuation));
            mSellCar.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.woshichezhu_moren,0,0);
        }
    }

    /**
     * 默认显示估值记录最近一条
     */
    private void getFirstHistoryToShowDefault(){
        List<RequestValFragmentHistory> list= ValHistoryCacheUtils.getValHistoryList(ValuationActivity.this,"");
        if(list!=null){
            if(list.size()>0){
                if(mChooseStyle==null){
                    RequestValFragmentHistory historyModel = list.get(0);
                    if (AppContext.isLogin())
                        paramUid = AppContext.mLoginResultModels.getId();
                    else
                        paramUid = "0";
                    paramCityId = historyModel.getCityID();
                    paramCityName = historyModel.getCityName();
                    paramMileage = historyModel.getMils();
                    paramRegDate = historyModel.getRegdate();
                    paramStyleId = historyModel.getStyleID();
                    mStyleText.setText(historyModel.getStyleName());
                    mRegDateText.setText(historyModel.getRDate());
                    mCityText.setText(historyModel.getCityName());
                    mMileageEdit.setText(historyModel.getMils());
                    mMonths = StringUtil.getMonthFromRegDate(historyModel.getRDate());
                    mChooseStyle = new JzgCarChooseStyle();
                    mChooseStyle.setBrandId(historyModel.getStyleBrandId());
                    mChooseStyle.setMaxYEAR(historyModel.getMaxYEAR());
                    mChooseStyle.setMinYEAR(historyModel.getMinYEAR());
                    mChooseStyle.setFullName(historyModel.getStyleFullName());
                    mChooseStyle.setItemColor(historyModel.getStyleItemColor());
                    mChooseStyle.setName(historyModel.getStyleNameOther());
                    mChooseStyle.setNowMsrp(historyModel.getStyleNowMsrp());
                    mChooseStyle.setYear(historyModel.getStyleYear());
                    mChooseStyle.setBrandId(historyModel.getStyleBrandId());
                    mChooseStyle.setBrandName(historyModel.getStyleBrandName());
                    mChooseStyle.setModeId(historyModel.getStyleModeId());
                    mChooseStyle.setModeName(historyModel.getStyleModeName());
                    mChooseStyle.setManufacturerName(historyModel.getStyleManufacturerName());
                    mChooseStyle.setFontColor(historyModel.getStyleFontColor());
                    mChooseStyle.setModelimgpath(historyModel.getStyleModelimgpath());
                    isAddToValHistory = false;
                }
                mValuationHistoryView.setVisibility(View.VISIBLE);
                mValuationHistoryView.toShowListView(null);
            }else{
                mValuationHistoryView.setVisibility(View.GONE);
            }
        }else{
            mValuationHistoryView.setVisibility(View.GONE);
        }
    }
    /**
     * 初始化历史估值Model
     * @param buyResult
     * @param mSellResult
     * @return
     */
    private RequestValFragmentHistory getHistoryModel( ValuationBuyCarResult buyResult,
            ValuationSellCarResult mSellResult) {
        RequestValFragmentHistory mHistoryModel = null;
        if (mSellResult != null) {
            mHistoryModel = new RequestValFragmentHistory();
            mHistoryModel.setStyleID(mSellResult.getStyleId());
            mHistoryModel.setImageUrl(mSellResult.getImgUrl());
            mHistoryModel.setProvID(mSellResult.getProvId());
            mHistoryModel.setCityID(mSellResult.getCityId());
            mHistoryModel.setCityName(mSellResult.getCityName());
            mHistoryModel.setStyleName(mSellResult.getFullName());
            mHistoryModel.setOperationType("1");
            mHistoryModel.setOperationTypeName("进行卖车估值");
            mHistoryModel.setMils(mSellResult.getMileage());
            mHistoryModel.setRegdate(mSellResult.getRegDateTime());
            mHistoryModel.setModelName(mSellResult.getMakeName() + " "
                    + mSellResult.getModelName());
            mHistoryModel.setRDate(mSellResult.getRegDate());
        } else if (buyResult != null) {
            mHistoryModel = new RequestValFragmentHistory();
            mHistoryModel.setStyleID(buyResult.getStyleId());
            mHistoryModel.setImageUrl(buyResult.getImgUrl());
            mHistoryModel.setProvID(buyResult.getProvId());
            mHistoryModel.setCityID(buyResult.getCityId());
            mHistoryModel.setCityName(buyResult.getCityName());
            mHistoryModel.setStyleName(buyResult.getFullName());
            mHistoryModel.setOperationType("2");
            mHistoryModel.setOperationTypeName("进行买车估值");
            mHistoryModel.setMils(buyResult.getMileage());
            mHistoryModel.setRegdate(buyResult.getRegDateTime());
            mHistoryModel.setModelName(buyResult.getMakeName() + " "
                    + buyResult.getModelName());
            mHistoryModel.setRDate(buyResult.getRegDate());
        }
        mHistoryModel.setMaxYEAR(mChooseStyle.getMaxYEAR());
        mHistoryModel.setMinYEAR(mChooseStyle.getMinYEAR());
        mHistoryModel.setStyleFullName(mChooseStyle.getFullName());
        mHistoryModel.setStyleItemColor(mChooseStyle.getItemColor());
        mHistoryModel.setStyleNameOther(mChooseStyle.getName());
        mHistoryModel.setStyleNowMsrp(mChooseStyle.getNowMsrp());
        mHistoryModel.setStyleYear(mChooseStyle.getYear());
        mHistoryModel.setStyleBrandId(mChooseStyle.getBrandId());
        mHistoryModel.setStyleBrandName(mChooseStyle.getBrandName());
        mHistoryModel.setStyleModeId(mChooseStyle.getModeId());
        mHistoryModel.setStyleModeName(mChooseStyle.getModeName());
        mHistoryModel.setStyleManufacturerName(mChooseStyle.getManufacturerName());
        mHistoryModel.setStyleFontColor(mChooseStyle.getFontColor());
        mHistoryModel.setStyleModelimgpath(mChooseStyle.getModelimgpath());
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        mHistoryModel.setUpdateTime(sDateFormat.format(new Date()));
        return mHistoryModel;
    }
}

