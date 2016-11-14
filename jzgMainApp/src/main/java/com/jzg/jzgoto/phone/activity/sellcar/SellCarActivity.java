package com.jzg.jzgoto.phone.activity.sellcar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.activity.choosecity.ChooseCityActivity;
import com.jzg.jzgoto.phone.activity.valuation.ValuationTimeSheetActivity;
import com.jzg.jzgoto.phone.global.AppContext;
import com.jzg.jzgoto.phone.models.business.sell.LoanTimeParamsModels;
import com.jzg.jzgoto.phone.models.business.sell.LoanTimeResultModels;
import com.jzg.jzgoto.phone.models.business.sell.OneKeySellCarParams;
import com.jzg.jzgoto.phone.models.business.sell.OneKeySellCarResult;
import com.jzg.jzgoto.phone.models.business.sell.ReplaceNewCarIntentModel;
import com.jzg.jzgoto.phone.models.business.valuation.ValuationSellCarParams;
import com.jzg.jzgoto.phone.models.business.valuation.ValuationSellCarResult;
import com.jzg.jzgoto.phone.services.business.login.LoginService;
import com.jzg.jzgoto.phone.services.business.valuation.ValuationService;
import com.jzg.jzgoto.phone.services.common.OnRequestFinishListener;
import com.jzg.jzgoto.phone.tools.ClickControlTool;
import com.jzg.jzgoto.phone.tools.EditTextUtils;
import com.jzg.jzgoto.phone.tools.ShowDialogTool;
import com.jzg.jzgoto.phone.valuationchoosecarstyle.CarReleaseIndexCarActivity;
import com.jzg.jzgoto.phone.view.ViewUtility;
import com.jzg.pricechange.phone.JzgCarChooseStyle;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by WY on 2016/9/20.
 * Function :卖车界面
 */
public class SellCarActivity extends Activity implements OnRequestFinishListener {
    @Bind(R.id.sell_truck_carStyle_show_textView)
    TextView sellTruckCarStyleShowTextView;
    @Bind(R.id.sell_truck_carStyle_layout)
    RelativeLayout sellTruckCarStyleLayout;//品牌车型
    @Bind(R.id.sell_truck_carRegDate_show_textView)
    TextView sellTruckCarRegDateShowTextView;
    @Bind(R.id.sell_truck_carRegDate_layout)
    RelativeLayout sellTruckCarRegDateLayout;//上牌时间
    @Bind(R.id.sell_truck_carMileage_textView)
    TextView sellTruckCarMileageRightTextView;
    @Bind(R.id.sell_truck_carMileage_show_textView)
    EditText sellTruckCarMileageShowTextView;
    @Bind(R.id.valuation_carMileage_layout)
    RelativeLayout valuationCarMileageLayout;//行驶里程
    @Bind(R.id.sell_truck_show_city_textView)
    TextView sellTruckShowCityTextView;
    @Bind(R.id.sell_truck_city_layout)
    RelativeLayout sellTruckCityLayout;//所在城市
    @Bind(R.id.expect_price_show_textView)
    EditText expectPriceShowTextView;
    @Bind(R.id.expect_price_layout)
    RelativeLayout expectPriceLayout;
    @Bind(R.id.sellcar_loan_apply_imageView)
    ImageView sellcarLoanApplyImageView;
    @Bind(R.id.btn_buy_onekey_sell_truck)
    TextView btnBuyOnekeySellTruck;//一键卖车
    @Bind(R.id.btn_buy_replacement)
    TextView btnBuyReplacement;//置换新车
    @Bind(R.id.btn_buy_detailed_valuation)
    TextView btnBuyDetailedValuation;//详细估值
    @Bind(R.id.tvresult)
    TextView tvResult;
    private ImageView mApplyCarLoan;

    public static final String GET_SELLCAR_INTENT_MODEL = "get_sellcar_intent_model";
    private final int ONE_KEY_SELL_CAR_REQUEST = 0x1005;
    private final int TO_GET_CITY = 0x1002;

    private final int INTENT_REQUEST_TIME = 0x1010;
    private final int TO_GET_CARBRAND = 0x1003;
    private final int REQUEST_STYLE_TIME = 0x2002;
    private LoginService loginService;
    private int maxYear;
    private int minYear;
    private int maxMonth;
    private int minMonth;
    private JzgCarChooseStyle jzgCarChooseStyle;
    private String cityId;
    private String mRequestTime;
    private String cityName;
    private int mYear;
    private int mMonth;
    private int mMonths;
    private ReplaceNewCarIntentModel replaceNewCarIntentModel;
    private String styleid;
    private int selectYear;
    private int selectMonth;
    private String modeId;
    private String brandId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellcar_layout);
        ButterKnife.bind(this);
        replaceNewCarIntentModel = (ReplaceNewCarIntentModel) getIntent().getSerializableExtra(GET_SELLCAR_INTENT_MODEL);
        init();
        if(replaceNewCarIntentModel !=null){
            initView();
        }
        initListener();
    }

    private void initView() {
        sellTruckCarStyleShowTextView.setText(replaceNewCarIntentModel.getFullName());
        styleid = replaceNewCarIntentModel.getStyleId();
        modeId = replaceNewCarIntentModel.getModelId();
        brandId = replaceNewCarIntentModel.getMakeId();
        sellTruckCarRegDateShowTextView.setText(replaceNewCarIntentModel.getRegDate());
        mRequestTime = replaceNewCarIntentModel.getRegDate();
        sellTruckShowCityTextView.setText(replaceNewCarIntentModel.getCityName());
        cityId = replaceNewCarIntentModel.getCityId();
        cityName = replaceNewCarIntentModel.getCityName();
        sellTruckCarMileageShowTextView.setText(replaceNewCarIntentModel.getMileage());
        String[] RequestTimes = replaceNewCarIntentModel.getRegDate().split("年");
        try {
            selectYear =  Integer.parseInt(RequestTimes[0]);
            selectMonth = Integer.parseInt(RequestTimes[1].trim().substring(0,RequestTimes[1].length()-1));
        } catch (Exception e) {
            e.printStackTrace();
        }
        mRequestTime  = selectYear + "-" + selectMonth;
        mMonths = (mYear - selectYear) * 12 + (mMonth - selectMonth);
        startSellTruck();

    }

    private void initListener() {
        sellTruckCarStyleLayout.setOnClickListener(mOnClickListener);
        sellTruckCarRegDateLayout.setOnClickListener(mOnClickListener);
        sellTruckCityLayout.setOnClickListener(mOnClickListener);
        btnBuyOnekeySellTruck.setOnClickListener(mOnClickListener);
        btnBuyReplacement.setOnClickListener(mOnClickListener);
        btnBuyDetailedValuation.setOnClickListener(mOnClickListener);

        EditTextUtils.mileageTextWatcher(sellTruckCarMileageShowTextView, this);
        EditTextUtils.priceWatcher(expectPriceShowTextView, this);
        sellTruckCarMileageShowTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(checkoutParams()){
                    startSellTruck();
                }

            }
        });

        expectPriceShowTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(checkoutParams()){
                    startSellTruck();
                }
            }
        });
    }

    private void init() {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH) + 1;
        loginService = new LoginService(this, this);
        mApplyCarLoan = (ImageView) findViewById(R.id.sellcar_loan_apply_imageView);
        mApplyCarLoan.setOnClickListener(mOnClickListener);

    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!ClickControlTool.isCanToClick()) return;
            switch (v.getId()) {
                case R.id.sellcar_loan_apply_imageView:
                    //车抵贷
                    ViewUtility.navigateToSellCarLoanActivity(SellCarActivity.this);
                    break;
                case R.id.sell_truck_carStyle_layout:
                    //品牌车型
                    Intent intent = new Intent(SellCarActivity.this, CarReleaseIndexCarActivity.class);
                    intent.putExtra("newOrOld",0);
                    startActivityForResult(intent, TO_GET_CARBRAND);
                    break;
                case R.id.sell_truck_carRegDate_layout:
                    //上牌时间
                    if (!TextUtils.isEmpty(styleid)) {
                        startMakeYearThread(styleid);
                        return;
                    } else {
                        ShowDialogTool.showCenterToast(SellCarActivity.this, "请选择品牌车型");
                    }
                    break;

                case R.id.sell_truck_city_layout:
                    //所在城市
                    Intent intent1 = new Intent(SellCarActivity.this, ChooseCityActivity.class);
                    startActivityForResult(intent1, TO_GET_CITY);
                    break;
                case R.id.btn_buy_onekey_sell_truck:
                    //一键卖车
                    btnBuyOnekeySellTruck.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_blue_round_normal_style));
                    btnBuyOnekeySellTruck.setTextColor(getResources().getColor(R.color.white));//btn_blue_round_border_white_disabled_style

                    btnBuyDetailedValuation.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_blue_round_border_white_normal_style));
                    btnBuyDetailedValuation.setTextColor(getResources().getColor(R.color.blue));

                    btnBuyReplacement.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_blue_round_border_white_normal_style));
                    btnBuyReplacement.setTextColor(getResources().getColor(R.color.blue));
                    if(checkoutParamsJump()){
                        Intent intent2 = new Intent(SellCarActivity.this,SellCarOneKeyActivity.class);
                        intent2.putExtra("styleid",styleid);
//                        intent2.putExtra("ButlerId",jzgCarChooseStyle.getId()+"");
                        intent2.putExtra("regdate", mRequestTime + "-01");
                        intent2.putExtra("mileage",sellTruckCarMileageShowTextView.getText().toString().trim());
                        intent2.putExtra("cityid",cityId);
//                        intent2.putExtra("uid",);
                        intent2.putExtra("cityname", cityName);
                        intent2.putExtra("sellprice",expectPriceShowTextView.getText().toString().trim());//销售价格
                        startActivity(intent2);
                    }

                    break;
                case R.id.btn_buy_replacement:
                    //置换新车;
//                    btnBuyReplacement.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_blue_round_normal_style));
//                    btnBuyReplacement.setTextColor(getResources().getColor(R.color.white));
                    btnBuyReplacement.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_blue_round_border_white_normal_style));
                    btnBuyReplacement.setTextColor(getResources().getColor(R.color.blue));

//                    btnBuyOnekeySellTruck.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_blue_round_border_white_normal_style));
//                    btnBuyOnekeySellTruck.setTextColor(getResources().getColor(R.color.blue));

                    btnBuyDetailedValuation.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_blue_round_border_white_normal_style));
                    btnBuyDetailedValuation.setTextColor(getResources().getColor(R.color.blue));
                    if(checkOutJumpReplace()){
                    ReplaceNewCarIntentModel replaceNewCarIntentModel = new ReplaceNewCarIntentModel();
                    replaceNewCarIntentModel.setCityId(cityId);
                    replaceNewCarIntentModel.setCityName(cityName);
                    replaceNewCarIntentModel.setRegDate(selectYear+"年"+selectMonth+"月");
                    replaceNewCarIntentModel.setStyleId(styleid);
                    replaceNewCarIntentModel.setModelId(modeId);
                    replaceNewCarIntentModel.setMakeId(brandId);
                    replaceNewCarIntentModel.setFullName(sellTruckCarStyleShowTextView.getText().toString().trim());
                    replaceNewCarIntentModel.setMileage(sellTruckCarMileageShowTextView.getText().toString().trim());
                    ViewUtility.navigateToReplaceNewCarActivity(SellCarActivity.this,replaceNewCarIntentModel);
                    }

                    break;
                case R.id.btn_buy_detailed_valuation:
                    //信息估值
//                    btnBuyDetailedValuation.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_blue_round_normal_style));
//                    btnBuyDetailedValuation.setTextColor(getResources().getColor(R.color.white));
                    btnBuyDetailedValuation.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_blue_round_border_white_normal_style));
                    btnBuyDetailedValuation.setTextColor(getResources().getColor(R.color.blue));
//
//                    btnBuyOnekeySellTruck.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_blue_round_border_white_normal_style));
//                    btnBuyOnekeySellTruck.setTextColor(getResources().getColor(R.color.blue));

                    btnBuyReplacement.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_blue_round_border_white_normal_style));
                    btnBuyReplacement.setTextColor(getResources().getColor(R.color.blue));
                    messageGushi();
                    break;

            }
        }
    };

    private boolean checkOutJumpReplace() {
        if (TextUtils.isEmpty(styleid)) {
            ShowDialogTool.showCenterToast(SellCarActivity.this, "请选择车型车系");
            return false;
        }
        if (TextUtils.isEmpty(modeId)) {
            ShowDialogTool.showCenterToast(SellCarActivity.this, "请选择车型车系");
            return false;
        }
        if (TextUtils.isEmpty(brandId)) {
            ShowDialogTool.showCenterToast(SellCarActivity.this, "请选择车型车系");
            return false;
        }
        if (TextUtils.isEmpty(sellTruckCarRegDateShowTextView.getText().toString().trim())) {
            ShowDialogTool.showCenterToast(SellCarActivity.this, "请选择上牌时间");
            return false;
        }
        String mileageValue = sellTruckCarMileageShowTextView.getText().toString().trim();
        if (TextUtils.isEmpty(mileageValue)) {
            ShowDialogTool.showCenterToast(SellCarActivity.this, "请输入行驶里程");
            return false;
        }
        if(mileageValue.equals("0") || mileageValue.equals("0.") || Double.compare(Double.valueOf(mileageValue), 0.0f) <= 0){
            ShowDialogTool.showCenterToast(this, "请输入正确的行驶里程");
            return false;
        }
        try {
            double melies = Double.valueOf(mileageValue);
            if (melies > mMonths) {
                ShowDialogTool.showCenterToast(SellCarActivity.this, "行驶里程请小于月均1万公里");
                return false;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }

        if (TextUtils.isEmpty(cityId)) {
            ShowDialogTool.showCenterToast(SellCarActivity.this, "请选择城市");
            return false;
        }
        return true;
    }

    private void messageGushi() {
        if(checkoutAppraisementParams()){
            ValuationSellCarParams sellCarParams = new ValuationSellCarParams();
            if (AppContext.isLogin()) {
                sellCarParams.uid = AppContext.mLoginResultModels.getId();
            }else{
                sellCarParams.uid = "0";
            }
            sellCarParams.styleid = styleid;
            sellCarParams.regdate = mRequestTime + "-01";
            sellCarParams.mileage = sellTruckCarMileageShowTextView.getText().toString().trim();
            sellCarParams.cityname = cityName;
            sellCarParams.CityId = cityId;
            new ValuationService(this,this).toResuestValuationSellCar(sellCarParams, ValuationSellCarResult.class,R.id.request_valuation_sellcar_detail);
        }

    }


    private void startMakeYearThread(final String styleid) {
        LoanTimeParamsModels params = new LoanTimeParamsModels();
        ShowDialogTool.showLoadingDialog(this);
        params.styleid = styleid;
        loginService.toRequestNet(params, LoanTimeResultModels.class, REQUEST_STYLE_TIME);
    }

    private boolean checkoutParams() {
        if (TextUtils.isEmpty(styleid)) {
            tvResult.setText("");
            return false;
        }
        if (TextUtils.isEmpty(mRequestTime)) {
            tvResult.setText("");
            return false;
        }
        if (TextUtils.isEmpty(sellTruckCarMileageShowTextView.getText().toString().trim())) {
            tvResult.setText("");
            return false;
        }
        try {
            double melies = Double.valueOf(sellTruckCarMileageShowTextView.getText().toString().trim());
            if (melies > mMonths) {
                ShowDialogTool.showCenterToast(SellCarActivity.this, "行驶里程请小于月均1万公里");
                return false;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }

        if (TextUtils.isEmpty(cityId)) {
            tvResult.setText("");
            return false;
        }


        return true;
    }
    private boolean checkoutParamsJump() {
        if (TextUtils.isEmpty(styleid)) {
            ShowDialogTool.showCenterToast(SellCarActivity.this, "请选择车型车系");
            return false;
        }
        if (TextUtils.isEmpty(modeId)) {
            ShowDialogTool.showCenterToast(SellCarActivity.this, "请选择车型车系");
            return false;
        }
        if (TextUtils.isEmpty(brandId)) {
            ShowDialogTool.showCenterToast(SellCarActivity.this, "请选择车型车系");
            return false;
        }
        if (TextUtils.isEmpty(sellTruckCarRegDateShowTextView.getText())) {
            ShowDialogTool.showCenterToast(SellCarActivity.this, "请选择上牌时间");
            return false;
        }
        String mileageValue = sellTruckCarMileageShowTextView.getText().toString().trim();
        if (TextUtils.isEmpty(mileageValue)) {
            ShowDialogTool.showCenterToast(SellCarActivity.this, "请输入行驶里程");
            return false;
        }
        if(mileageValue.equals("0") || mileageValue.equals("0.") || Double.compare(Double.valueOf(mileageValue), 0.0f) <= 0){
            ShowDialogTool.showCenterToast(this, "请输入正确的行驶里程");
            return false;
        }
        try {
            double melies = Double.valueOf(mileageValue);
            if (melies > mMonths) {
                ShowDialogTool.showCenterToast(SellCarActivity.this, "行驶里程请小于月均1万公里");
                return false;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }

        if (TextUtils.isEmpty(cityId)) {
            ShowDialogTool.showCenterToast(SellCarActivity.this, "请选择城市");
            return false;
        }
        if (TextUtils.isEmpty(expectPriceShowTextView.getText().toString().trim())) {
            ShowDialogTool.showCenterToast(SellCarActivity.this, "请输入预期售价");
            return false;
        }

        return true;
    }
    private boolean checkoutAppraisementParams() {
        if (TextUtils.isEmpty(styleid)) {
            ShowDialogTool.showCenterToast(this, "请选择车型车系");
            return false;
        }
        if (TextUtils.isEmpty(sellTruckCarRegDateShowTextView.getText().toString().trim())) {
            ShowDialogTool.showCenterToast(this, "请选择上牌时间");
            return false;
        }

        String mileageValue = sellTruckCarMileageShowTextView.getText().toString().trim();
        if (TextUtils.isEmpty(mileageValue)) {
            ShowDialogTool.showCenterToast(this, "请输入行驶里程");
            return false;
        }
        if(mileageValue.equals("0") || mileageValue.equals("0.") || Double.compare(Double.valueOf(mileageValue), 0.0f) <= 0){
            ShowDialogTool.showCenterToast(this, "请输入正确的行驶里程");
            return false;
        }

        if (TextUtils.isEmpty(cityId)) {
            ShowDialogTool.showCenterToast(this, "请选择城市");
            return false;
        }
        try {
            double melies = Double.valueOf(sellTruckCarMileageShowTextView.getText().toString().trim());
            if (melies > mMonths) {
                ShowDialogTool.showCenterToast(SellCarActivity.this, "行驶里程请小于月均1万公里");
                return false;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    private void startSellTruck() {
        OneKeySellCarParams oneKeySellCarParams = new OneKeySellCarParams();
        oneKeySellCarParams.cityid = cityId;
        oneKeySellCarParams.mileage = sellTruckCarMileageShowTextView.getText().toString().trim();
        oneKeySellCarParams.regdate = mRequestTime + "-01";
        oneKeySellCarParams.styleid = styleid + "";
        loginService.toRequestNet(oneKeySellCarParams, OneKeySellCarResult.class, ONE_KEY_SELL_CAR_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) return;
        switch (requestCode) {
            case TO_GET_CARBRAND:
                jzgCarChooseStyle = (JzgCarChooseStyle) data.getSerializableExtra("mQueryCarStyle");
                if (jzgCarChooseStyle != null) {
                    sellTruckCarStyleShowTextView.setText(jzgCarChooseStyle.getFullName());
                    styleid = jzgCarChooseStyle.getId()+"";
                    modeId = jzgCarChooseStyle.getModeId()+"";
                    brandId = jzgCarChooseStyle.getBrandId()+"";
                }
                sellTruckCarRegDateShowTextView.setText("");
                sellTruckCarMileageShowTextView.setText("");
                if(checkoutParams()){
                    startSellTruck();
                }

                break;
            case INTENT_REQUEST_TIME://上牌时间
                selectYear = data.getIntExtra("year", -1);
                selectMonth = data.getIntExtra("month", -1);
                mRequestTime = selectYear + "-" + selectMonth;
                sellTruckCarRegDateShowTextView.setText(selectYear+"年"+selectMonth+"月");
                mMonths = (mYear - selectYear) * 12 + (mMonth - selectMonth);

                if(checkoutParams()){
                    startSellTruck();
                }

                break;
            case TO_GET_CITY://选择城市
                String province = data.getStringExtra("province");
                cityName = data.getStringExtra(ChooseCityActivity.return_flag_cityname);
                cityId = data.getStringExtra(ChooseCityActivity.return_flag_cityid);
                if (!TextUtils.isEmpty(cityName)) {
                    sellTruckShowCityTextView.setText(cityName);
                }
                if(checkoutParams()){
                    startSellTruck();
                }
            default:

                break;
        }

    }

    @Override
    public void onRequestSuccess(Message msg) {
        ShowDialogTool.dismissLoadingDialog();
        switch (msg.what) {
            case REQUEST_STYLE_TIME:
                LoanTimeResultModels timeResult = (LoanTimeResultModels) msg.obj;
                if (timeResult.getStatus() == 100) {
                    String MinYEAR = timeResult.getMinYEAR();
                    String MaxYEAR = timeResult.getMaxYEAR();

                    int maxYear = getYear4String(MaxYEAR);
                    int MaxMonth = getMonth4String(MaxYEAR);
                    int minYear = getYear4String(MinYEAR);
                    int MinMonth = getMonth4String(MinYEAR);

                    this.maxYear = maxYear;
                    this.maxMonth = MaxMonth;
                    this.minYear = minYear;
                    this.minMonth = MinMonth;

                    toShowDatePickerNew(this.maxYear,
                            this.minYear,
                            this.maxMonth,
                            this.minMonth);
                } else {
                    ShowDialogTool.showCenterToast(this, "获取车型时间失败!");
                }
                break;
            case ONE_KEY_SELL_CAR_REQUEST:
                OneKeySellCarResult oneKeySellCarResult = (OneKeySellCarResult) msg.obj;
                if (oneKeySellCarResult.getStatus() == 100) {
                    tvResult.setText(oneKeySellCarResult.getBeginC2BPrice() + "万" + "--" + oneKeySellCarResult.getEndC2BPrice() + "万");
                } else {
                    ShowDialogTool.showCenterToast(this, oneKeySellCarResult.getMsg());
                }
                break;
            case R.id.request_valuation_sellcar_detail:
                ValuationSellCarResult valuationSellCarResult = (ValuationSellCarResult) msg.obj;
                if(valuationSellCarResult.getStatus()==100){
                    ViewUtility.navigateToValuationSellActivity(SellCarActivity.this,valuationSellCarResult);
                }else{
                    ShowDialogTool.showCenterToast(this, valuationSellCarResult.getMsg());
                }
                break;

            default:

                break;
        }

    }

    private void toShowDatePickerNew(int maxYear, int minYear, int maxMonth, int minMonth) {
        Intent in = new Intent(this, ValuationTimeSheetActivity.class);
        in.putExtra("Maxyear", maxYear);
        in.putExtra("Minyear", minYear);
        in.putExtra("MaxMonth", maxMonth);
        in.putExtra("MinMonth", minMonth);
        startActivityForResult(in, INTENT_REQUEST_TIME);
    }

    @Override
    public void onRequestFault(Message msg) {

    }

    public static int getYear4String(String yearStr) {
        int year = 0;
        try {
            if ("".equals(yearStr) || null == yearStr) {
                year = 0;
            } else {
                if (yearStr.indexOf("-") != -1) {
                    year = Integer.parseInt(yearStr.substring(0,
                            yearStr.indexOf("-")));
                } else {
                    year = 0;
                }
            }
        } catch (Exception e) {
            year = 0;
        }
        return year;
    }

    public static int getMonth4String(String yearStr) {
        int year = 0;
        try {
            if ("".equals(yearStr) || null == yearStr) {
                year = 0;
            } else {
                if (yearStr.indexOf("-") != -1) {
                    year = Integer.parseInt(yearStr.substring(yearStr.indexOf("-") + 1));
                } else {
                    year = 0;
                }
            }
        } catch (Exception e) {
            year = 0;
        }
        return year;
    }
}
