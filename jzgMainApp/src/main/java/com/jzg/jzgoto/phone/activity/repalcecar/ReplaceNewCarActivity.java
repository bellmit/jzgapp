package com.jzg.jzgoto.phone.activity.repalcecar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.activity.buy.BuyCarStyleChooseActivity;
import com.jzg.jzgoto.phone.activity.choosecity.ChooseCityActivity;
import com.jzg.jzgoto.phone.activity.valuation.ValuationTimeSheetActivity;
import com.jzg.jzgoto.phone.constant.Constant;
import com.jzg.jzgoto.phone.global.AppContext;
import com.jzg.jzgoto.phone.models.business.sell.LoanTimeParamsModels;
import com.jzg.jzgoto.phone.models.business.sell.LoanTimeResultModels;
import com.jzg.jzgoto.phone.models.business.sell.NewCarListParamsModels;
import com.jzg.jzgoto.phone.models.business.sell.NewCarRepalceParamsModels;
import com.jzg.jzgoto.phone.models.business.sell.ReplaceNewCarIntentModel;
import com.jzg.jzgoto.phone.services.business.login.LoginService;
import com.jzg.jzgoto.phone.services.common.OnRequestFinishListener;
import com.jzg.jzgoto.phone.tools.ShowDialogTool;
import com.jzg.jzgoto.phone.tools.StringUtil;
import com.jzg.jzgoto.phone.valuationchoosecarstyle.CarReleaseIndexCarActivity;
import com.jzg.jzgoto.phone.view.buy.BuyCarMoreFilterGridView;
import com.jzg.jzgoto.phone.view.buy.BuyCarMoreFilterGridView.GridViewData;
import com.jzg.jzgoto.phone.view.buy.BuyCarMoreFilterGridView.GridItemClickCallback;
import com.jzg.pricechange.phone.JzgCarChooseStyle;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by WY on 2016/9/21.
 * Function :新车置换界面
 */
public class ReplaceNewCarActivity extends Activity implements OnRequestFinishListener{
    public static final String GET_VALUATION_SELL_RESULT = "get_valuation_sell_result";
    public static final String GET_REPLACE_INTENT_MODEL = "get_replace_intent_model";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replace_newcar_activity);
        initView();
        initListener();
    }
    private LoginService mLoginService;

    public void initView() {
        mLoginService = new LoginService(this, this);
        mFramePrice = (FrameLayout) findViewById(R.id.frame_replace_main_price);
        mFrameGearBox = (FrameLayout) findViewById(R.id.frame_replace_main_gearbox);
        addPriceView();
        addGearBoxView();
        mLinearCarBrand = (LinearLayout) findViewById(R.id.linear_replace_main_carstyle);
        mTvCarBrandName = (EditText) findViewById(R.id.edit_replace_main_carstyle_name);

        mLinearPlateNum = (LinearLayout) findViewById(R.id.linear_replace_main_plate_num);
        mEditPlateNumValue = (EditText) findViewById(R.id.edit_replace_main_plate_num_value);

        mEditMileValue = (EditText) findViewById(R.id.edit_replace_main_mile);
        mLinearAddress = (LinearLayout) findViewById(R.id.linear_replace_main_address);
        mTvAddressValue = (EditText) findViewById(R.id.edit_replace_main_address_name);

        mLinearCarStyleChoose = (LinearLayout) findViewById(R.id.linear_replace_main_to_carstyle_choose);
        mTvCarStyleValue = (TextView) findViewById(R.id.tv_replace_main_to_carstyle_value);

        mTvCountryOne = (TextView) findViewById(R.id.tv_replace_main_to_country_one);
        mTvCountryTwo = (TextView) findViewById(R.id.tv_replace_main_to_country_two);
        mTvCountryThree = (TextView) findViewById(R.id.tv_replace_main_to_country_three);
        mTvCountryFour = (TextView) findViewById(R.id.tv_replace_main_to_country_four);

    }
    private void initParams(){
        mNewCarListParamsModels.PageIndex = "0";
        mNewCarListParamsModels.NewMakeID = "";// 初始化为奥迪
        mNewCarListParamsModels.NewMsrpRange = "0-0";
        mNewCarListParamsModels.NewLevel = "0";
        mNewCarListParamsModels.NewTypeID = "0";
        mNewCarListParamsModels.NewProductType = "0";

        if(getIntent().getSerializableExtra(GET_REPLACE_INTENT_MODEL) != null){
            mReplaceIntentModel = (ReplaceNewCarIntentModel) getIntent().getSerializableExtra(GET_REPLACE_INTENT_MODEL);
            initValuationResult();
        }
    }
    private void initValuationResult(){
        if(mReplaceIntentModel == null)return;

        mJzgCarChooseStyle = new JzgCarChooseStyle();
        mJzgCarChooseStyle.setId(Integer.valueOf(mReplaceIntentModel.getStyleId()));
        mJzgCarChooseStyle.setFullName(mReplaceIntentModel.getFullName());
        mJzgCarChooseStyle.setModeId(Integer.valueOf(mReplaceIntentModel.getModelId()));
        mJzgCarChooseStyle.setBrandId(Integer.valueOf(mReplaceIntentModel.getMakeId()));

        mNewCarRepalceParamsModels.setMyMakeID(mReplaceIntentModel.getMakeId());
        mNewCarRepalceParamsModels.setMyModelID(mReplaceIntentModel.getModelId());
        mNewCarRepalceParamsModels.setMyStyleID(mReplaceIntentModel.getStyleId());

        mLinearCarBrand.setOnClickListener(null);
        mTvCarBrandName.setText(mReplaceIntentModel.getFullName());
        mTvCarBrandName.setTextColor(getResources().getColor(R.color.grey4));

        String regDate = mReplaceIntentModel.getRegDate();
        if(!TextUtils.isEmpty(regDate)){// 2014/12/1 0:00:00 2014-06-01 2014年06月
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月");
            try {
                Date date = sdf.parse(regDate);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH)+1;
                mEditPlateNumValue.setText(year + "-" + month);
                mEditPlateNumValue.setTextColor(getResources().getColor(R.color.grey4));

                mNewCarRepalceParamsModels.setMyFirstRegistrationTime(year + "-" + month);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        mLinearPlateNum.setOnClickListener(null);
        mNewCarRepalceParamsModels.setMyMileage(String.valueOf(mReplaceIntentModel.getMileage()));
        mEditMileValue.setText(String.valueOf(mReplaceIntentModel.getMileage()));
        mEditMileValue.setTextColor(getResources().getColor(R.color.grey4));

        mEditMileValue.setEnabled(false);
        String province = mReplaceIntentModel.getProvinceName();
        String city = mReplaceIntentModel.getCityName();
        mTvAddressValue.setText(city);
		/*
		if(province.equals(city)){
		} else {
			mTvAddressValue.setText(province + " " + city);
		}
		*/
        mTvAddressValue.setTextColor(getResources().getColor(R.color.grey4));

        mLinearAddress.setOnClickListener(null);
        mNewCarRepalceParamsModels.setMyProvinceID("0");
        mNewCarRepalceParamsModels.setMyCItyID("0");
        if("全国".equals(province) || "全国".equals(city)){
            mNewCarRepalceParamsModels.setMyProvinceName("");
            mNewCarRepalceParamsModels.setMyCityName("");
        } else {
            mNewCarRepalceParamsModels.setMyProvinceName(province);
            mNewCarRepalceParamsModels.setMyCityName(city);
        }

        mNewCarRepalceParamsModels.setRecordId(String.valueOf(mReplaceIntentModel.getReportId()));
    }
    /**
     * 估值结果
     */
    private ReplaceNewCarIntentModel mReplaceIntentModel = null;

    private LinearLayout mLinearCarBrand;
    private EditText mTvCarBrandName;
    private LinearLayout mLinearPlateNum;
    private EditText mEditPlateNumValue;
    private EditText mEditMileValue;
    private LinearLayout mLinearAddress;
    private EditText mTvAddressValue;

    private TextView mTvCountryOne;
    private TextView mTvCountryTwo;
    private TextView mTvCountryThree;
    private TextView mTvCountryFour;

    public void initListener() {

        View.OnClickListener listenerOne = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.linear_replace_main_carstyle:
                        Intent intent1 = new Intent(ReplaceNewCarActivity.this, CarReleaseIndexCarActivity.class);
                        intent1.putExtra("newOrOld",0);
                        startActivityForResult(intent1,TO_GET_CARBRAND);
                        break;
                    case R.id.linear_replace_main_plate_num:
                        // 选择上牌时间
                        if (mJzgCarChooseStyle == null) {
                            ShowDialogTool.showCenterToast(ReplaceNewCarActivity.this,"请先选择车型");
                            break;
                        }
//                        if (mHasCarStyle != mJzgCarChooseStyle.getId()) {
//                            mRequestDataParams = null;
//                            startMakeYearThread(Integer.toString(mJzgCarChooseStyle.getId()));
//                            return;
//                        }
                        toShowDatePickerNew();
                        break;
                    case R.id.linear_replace_main_address:
                        // 选择城市
                       Intent intent = new Intent(ReplaceNewCarActivity.this, ChooseCityActivity.class);
                        intent.putExtra("hideAllRegion", "hideAllRegion");
                        startActivityForResult(intent, Constant.QUERYCAR_REGION);
                        break;
                    case R.id.linear_replace_main_to_carstyle_choose:
                        //选择车型
                        startActivityForResult(new Intent(ReplaceNewCarActivity.this,BuyCarStyleChooseActivity.class),
                                TO_GET_CARSTYLE);
                        break;
                }
            }
        };
        mLinearCarBrand.setOnClickListener(listenerOne);
        mTvCarBrandName.setOnClickListener(listenerOne);
        mLinearPlateNum.setOnClickListener(listenerOne);
        mLinearAddress.setOnClickListener(listenerOne);
        mTvAddressValue.setOnClickListener(listenerOne);
        mLinearCarStyleChoose.setOnClickListener(listenerOne);

        mEditMileValue.setHint("请输入公里数");
        mEditMileValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (TextUtils.isEmpty(s))
                    return;

                if (!s.toString().contains(".")
                        && Integer.valueOf(String.valueOf(s)) > 100) {
                    mEditMileValue.setText("100");
                    mEditMileValue.setSelection("100".length());
                } else if (s.length() > 3 && !s.toString().contains(".")) {
                    mEditMileValue.setText(s.toString().subSequence(0,
                            s.length() - 1));
                    mEditMileValue.setSelection(s.length() - 1);
//					ShowDialogTool.showCenterToast(MyReplaceCarActivity.this, "请输入小数点");
                } else if (s.toString().contains(".")
                        && s.toString()
                        .substring(s.toString().lastIndexOf("."))
                        .length() > 3) {
                    mEditMileValue.setText(s.toString().substring(0,
                            s.toString().lastIndexOf("."))
                            + s.toString()
                            .substring(s.toString().lastIndexOf("."))
                            .subSequence(0, 3));
                    mEditMileValue.setSelection(s.length() - 1);
//					ShowDialogTool.showCenterToast(MyReplaceCarActivity.this, "只能输入小数点后两位");
                } else if ("0".equals(String.valueOf(s.charAt(0)))
                        && !s.toString().contains(".")) {
                    if (s.length() == 2
                            && "0".equals(String.valueOf(s.charAt(0)))) {
                        mEditMileValue.setText("0");
                        mEditMileValue.setSelection(1);
                    }
//					ShowDialogTool.showCenterToast(MyReplaceCarActivity.this, "请输入小数点");
                } else if (".".equals(String.valueOf(s.charAt(0)))) {
                    mEditMileValue.setText("");
//					ShowDialogTool.showCenterToast(MyReplaceCarActivity.this, "第一位不能为小数点");
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        initCarStyleState();
        final String[] values = new String[]{"0","1","2","3"};
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initBtnStyle();
                v.setSelected(true);
                switch(v.getId()){
                    case R.id.tv_replace_main_to_country_one:
                        mNewCarListParamsModels.NewProductType = values[0];
                        break;
                    case R.id.tv_replace_main_to_country_two:
                        mNewCarListParamsModels.NewProductType = values[1];
                        break;
                    case R.id.tv_replace_main_to_country_three:
                        mNewCarListParamsModels.NewProductType = values[2];
                        break;
                    case R.id.tv_replace_main_to_country_four:
                        mNewCarListParamsModels.NewProductType = values[3];
                        break;
                }
            }
        };
        mTvCountryOne.setOnClickListener(listener);
        mTvCountryTwo.setOnClickListener(listener);
        mTvCountryThree.setOnClickListener(listener);
        mTvCountryFour.setOnClickListener(listener);
        mTvCountryOne.setSelected(true);

        initParams();
    }

    private void initBtnStyle(){
        mTvCountryOne.setSelected(false);
        mTvCountryTwo.setSelected(false);
        mTvCountryThree.setSelected(false);
        mTvCountryFour.setSelected(false);
    }
    private LinearLayout mLinearCarStyleChoose;
    private TextView mTvCarStyleValue;

    private ImageView mImgCarStyleOne;
    private ImageView mImgCarStyleTwo;
    private ImageView mImgCarStyleThree;
    private ImageView mImgCarStyleFour;
    private ImageView mImgCarStyleFive;
    private ImageView mImgCarStyleSix;
    private void initViewSelectedState(View[] views){
        for(View view:views){
            view.setSelected(false);
        }
    }
    private void initCarStyleState(){
        mImgCarStyleOne = (ImageView) findViewById(R.id.img_replace_main_to_carstyle_one);
        mImgCarStyleTwo = (ImageView) findViewById(R.id.img_replace_main_to_carstyle_two);
        mImgCarStyleThree = (ImageView) findViewById(R.id.img_replace_main_to_carstyle_three);
        mImgCarStyleFour = (ImageView) findViewById(R.id.img_replace_main_to_carstyle_four);
        mImgCarStyleFive = (ImageView) findViewById(R.id.img_replace_main_to_carstyle_five);
        mImgCarStyleSix = (ImageView) findViewById(R.id.img_replace_main_to_carstyle_six);
        mImgCarStyleOne.setSelected(true);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initViewSelectedState(new View[]{mImgCarStyleOne,mImgCarStyleTwo,mImgCarStyleThree
                        ,mImgCarStyleFour,mImgCarStyleFive,mImgCarStyleSix});
                v.setSelected(true);
                switch(v.getId()){
                    case R.id.img_replace_main_to_carstyle_one:
                        mTvCarStyleValue.setText("不限");
                        mNewCarListParamsModels.NewLevel = "0";
                        break;
                    case R.id.img_replace_main_to_carstyle_two:
                        mTvCarStyleValue.setText("紧凑车");
                        mNewCarListParamsModels.NewLevel = "3";
                        break;
                    case R.id.img_replace_main_to_carstyle_three:
                        mTvCarStyleValue.setText("中型");
                        mNewCarListParamsModels.NewLevel = "4";
                        break;
                    case R.id.img_replace_main_to_carstyle_four:
                        mTvCarStyleValue.setText("大型");
                        mNewCarListParamsModels.NewLevel = "6";
                        break;
                    case R.id.img_replace_main_to_carstyle_five:
                        mTvCarStyleValue.setText("SUV");
                        mNewCarListParamsModels.NewLevel = "51";
                        break;
                    case R.id.img_replace_main_to_carstyle_six:
                        mTvCarStyleValue.setText("MPV");
                        mNewCarListParamsModels.NewLevel = "53";
                        break;
                }
            }
        };
        mImgCarStyleOne.setOnClickListener(listener);
        mImgCarStyleTwo.setOnClickListener(listener);
        mImgCarStyleThree.setOnClickListener(listener);
        mImgCarStyleFour.setOnClickListener(listener);
        mImgCarStyleFive.setOnClickListener(listener);
        mImgCarStyleSix.setOnClickListener(listener);

        initReplaceCarBrandAndColor();
    }
    private LinearLayout mLinearToBrand;
    private EditText mTvToBrandName;
    private LinearLayout mLinearToCarAddress;
    private EditText mTvToCarAddressName;
    private void initReplaceCarBrandAndColor(){
        mLinearToBrand = (LinearLayout) findViewById(R.id.linear_replace_main_to_brand);
        mTvToBrandName = (EditText) findViewById(R.id.edit_replace_main_to_brand_name);
        mLinearToCarAddress = (LinearLayout) findViewById(R.id.linear_replace_main_to_address);
        mTvToCarAddressName = (EditText) findViewById(R.id.edit_replace_main_to_address_value);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.linear_replace_main_to_brand:
                        //选择品牌
                        Intent intent1 = new Intent(ReplaceNewCarActivity.this, ReplaceChooseCarBrandActivity.class);
                        intent1.putExtra(ReplaceChooseCarBrandActivity.IS_CHOOSE_MODEL, "is_choose_model");
                        startActivityForResult(intent1,TO_GET_CARBRAND_TWO);

                        break;
                    case R.id.linear_replace_main_to_address:
                        //选择城市
                        Intent intent = new Intent(ReplaceNewCarActivity.this, ChooseCityActivity.class);
                        startActivityForResult(intent, TO_GET_TO_CARBRAND);
                        break;
                }
            }
        };
        mLinearToBrand.setOnClickListener(listener);

        mLinearToCarAddress.setOnClickListener(listener);

        String city = AppContext.cityName;
        String province = AppContext.provinceName;

        if("全国".equals(city) || "全国".equals(province)){
            city = "";
            province = "";
        }

        if(TextUtils.isEmpty(city)){
            mTvToCarAddressName.setText("");
            mTvAddressValue.setText("");
            city = "";
            province = "";
        } else {
            mTvToCarAddressName.setText(province);
            mTvAddressValue.setText(province);
        }
        mNewCarRepalceParamsModels.setNewProvinceName(province);
        mNewCarRepalceParamsModels.setNewCityName(city);

        mNewCarRepalceParamsModels.setMyProvinceName(province);
        mNewCarRepalceParamsModels.setMyCityName(city);
    }

    private NewCarListParamsModels mNewCarListParamsModels = new NewCarListParamsModels();
    private NewCarRepalceParamsModels mNewCarRepalceParamsModels = new NewCarRepalceParamsModels();
    public void onSubmitAll(View view){
        String plateNum = mEditPlateNumValue.getText().toString();
        final String mile = mEditMileValue.getText().toString();
        if(TextUtils.isEmpty(mNewCarRepalceParamsModels.getMyMakeID())||
                TextUtils.isEmpty(mNewCarRepalceParamsModels.getMyModelID())||
                TextUtils.isEmpty(mNewCarRepalceParamsModels.getMyStyleID())){
            ShowDialogTool.showCenterToast(ReplaceNewCarActivity.this, "请选择爱车车型!");
            return;
        }
        final String cityName = mNewCarRepalceParamsModels.getMyCityName();
        final String provinceName = mNewCarRepalceParamsModels.getMyProvinceName();
        if(TextUtils.isEmpty(provinceName)){
            mNewCarRepalceParamsModels.setMyProvinceName("");
        }
        if(TextUtils.isEmpty(cityName) ){
            ShowDialogTool.showCenterToast(ReplaceNewCarActivity.this, "请选择上牌地区!");
            return;
        }

        if("全国".equals(cityName) ){
            ShowDialogTool.showCenterToast(ReplaceNewCarActivity.this, "请选择具体城市!");
            return;
        }

//		plateNum = plateNum.substring(0, plateNum.lastIndexOf("-"));
        if(TextUtils.isEmpty(plateNum)){
            ShowDialogTool.showCenterToast(ReplaceNewCarActivity.this, "请选择上牌时间!");
            return;
        }

        final int months = getBetweenTime(plateNum);
        if(months < 0){
            ShowDialogTool.showCenterToast(ReplaceNewCarActivity.this, "上牌时间格式错误!");
            return;
        }
        if(TextUtils.isEmpty(mile)){
            ShowDialogTool.showCenterToast(ReplaceNewCarActivity.this, "请输入行驶公里!");
            return;
        }

        final double mileB = Double.valueOf(mile);
        if(mileB > 100){
            ShowDialogTool.showCenterToast(ReplaceNewCarActivity.this, "公里数不能大于100万!");
            return;
        }
        if(mileB <= 0){
            ShowDialogTool.showCenterToast(ReplaceNewCarActivity.this, "公里数不能小于0万公里!");
            return;
        }
        if( mileB > months){
            ShowDialogTool.showCenterToast(ReplaceNewCarActivity.this, "每个月最大公里数不能超过1万公里!");
            return;
        }

        final String newCityName = mNewCarRepalceParamsModels.getNewCityName();
        final String newProvinceName = mNewCarRepalceParamsModels.getNewProvinceName();
        if(TextUtils.isEmpty(newProvinceName)){
            mNewCarRepalceParamsModels.setNewProvinceName("");
        }

        if(TextUtils.isEmpty(newCityName)){
            ShowDialogTool.showCenterToast(ReplaceNewCarActivity.this, "请选择新车置换地区!");
            return;
        }

        if("全国".equals(newCityName)){
            ShowDialogTool.showCenterToast(ReplaceNewCarActivity.this, "请选择新车置换具体城市!");
            return;
        }

        if(TextUtils.isEmpty(mNewCarListParamsModels.NewMakeID)){
            ShowDialogTool.showCenterToast(ReplaceNewCarActivity.this, "请选择新车品牌!");
            return;
        }
        mNewCarRepalceParamsModels.setMyMileage(mile);
        mNewCarRepalceParamsModels.setMyFirstRegistrationTime(plateNum);

        // TODO
        Intent intent = new Intent(ReplaceNewCarActivity.this,ReplaceNewCarListActivity.class);
        mNewCarListParamsModels.PageIndex = "0";
        intent.putExtra("new_car", mNewCarListParamsModels);
        intent.putExtra("my_car", mNewCarRepalceParamsModels);
        startActivityForResult(intent, 99);
    }
    private FrameLayout mFramePrice;
    private void addPriceView(){
        final String[] strs = new String[]{"不限",
                "5-10万","10-20万","20-30万","30-50万","50万以上"};
        final String[] values = new String[]{"0-0",
                "5-10","10-20","20-30","30-50","50-100000000"};
        BuyCarMoreFilterGridView gridView = new BuyCarMoreFilterGridView(this);
        gridView.toInitShowData("价格",0, strs);
        gridView.setGridItemClickCallback(new GridItemClickCallback() {
            @Override
            public void onGridItemClick(GridViewData itemData) {
                mNewCarListParamsModels.NewMsrpRange = values[itemData.getTextId()];
            }
        });
        if(mFramePrice.getChildCount() != 0){
            mFramePrice.removeAllViews();
        }
        mFramePrice.addView(gridView);
    }
    private FrameLayout mFrameGearBox;
    private void addGearBoxView(){
        final String[] strs = new String[]{"不限","手动","自动"};
        final String[] values = new String[]{"0","1","2"};
        BuyCarMoreFilterGridView gridView = new BuyCarMoreFilterGridView(this);
        gridView.toInitShowData("变速箱",0, strs);
        gridView.setGridItemClickCallback(new GridItemClickCallback() {
            @Override
            public void onGridItemClick(GridViewData itemData) {
                mNewCarListParamsModels.NewTypeID = values[itemData.getTextId()];
            }
        });
        if(mFrameGearBox.getChildCount() != 0){
            mFrameGearBox.removeAllViews();
        }
        mFrameGearBox.addView(gridView);
    }
    private final int TO_GET_CARSTYLE = 0x1000;
    private final int TO_GET_TO_CARBRAND = 0x1001;
    private final int TO_GET_CARBRAND = 0x1002;
    private final int TO_GET_CARBRAND_TWO = 0x1003;

    private JzgCarChooseStyle mJzgCarChooseStyle;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data==null)return;
        switch(requestCode){
            case TO_GET_CARSTYLE:
                final String carStyle = data.getStringExtra(BuyCarStyleChooseActivity.CHOOSE_CAR_STYLE);
                final String carStyleId = data.getStringExtra(BuyCarStyleChooseActivity.CHOOSE_CAR_STYLE_ID);
                if(!TextUtils.isEmpty(carStyle)){
                    initViewSelectedState(new View[]{mImgCarStyleOne,mImgCarStyleTwo,mImgCarStyleThree
                            ,mImgCarStyleFour,mImgCarStyleFive,mImgCarStyleSix});
                    if("不限".equals(carStyle)){
                        mImgCarStyleOne.setSelected(true);
                    } else if("紧凑型车".equals(carStyle)){
                        mImgCarStyleTwo.setSelected(true);
                    } else if("中型车".equals(carStyle)){
                        mImgCarStyleThree.setSelected(true);
                    } else if("大型车".equals(carStyle)){
                        mImgCarStyleFour.setSelected(true);
                    } else if("SUV".equals(carStyle)){
                        mImgCarStyleFive.setSelected(true);
                    } else if("MPV".equals(carStyle)){
                        mImgCarStyleSix.setSelected(true);
                    }
                    mTvCarStyleValue.setText(carStyle);
                }
                mNewCarListParamsModels.NewLevel = carStyleId;
                break;
            case TO_GET_TO_CARBRAND:
                if (data != null) {
                    String province = data.getStringExtra("province");
                    String city = data.getStringExtra(ChooseCityActivity.return_flag_cityname);
                    if(TextUtils.isEmpty(city)){
                        city = "";
                    }

                    mTvToCarAddressName.setText(city);
                    mNewCarRepalceParamsModels.setNewProvinceName("");
                    mNewCarRepalceParamsModels.setNewCityName(city);
                }
                break;
            case Constant.QUERYCAR_REGION:
                if (data != null) {
                    String province = data.getStringExtra("province");
                    String city = data.getStringExtra(ChooseCityActivity.return_flag_cityname);
                    if(TextUtils.isEmpty(city)){
                        city = "";
                    }
                    if(TextUtils.isEmpty(province)){
                        province = "";
                    }
                    mTvAddressValue.setText(city);
                    String provinceID = String.valueOf(data.getIntExtra(ChooseCityActivity.return_flag_provinceid, 0));
                    String cityID = String.valueOf(data.getIntExtra(ChooseCityActivity.return_flag_cityid, 0));

                    mNewCarRepalceParamsModels.setMyProvinceID(provinceID);
                    mNewCarRepalceParamsModels.setMyCItyID(cityID);

                    mNewCarRepalceParamsModels.setMyProvinceName(province);
                    mNewCarRepalceParamsModels.setMyCityName(city);
                }
                break;
            case TO_GET_CARBRAND:
                if (data != null) {
                    JzgCarChooseStyle carBrand = (JzgCarChooseStyle) data.getSerializableExtra("mQueryCarStyle");
                    if(carBrand != null){
                        mJzgCarChooseStyle = carBrand;
                        mTvCarBrandName.setText(carBrand.getFullName());
                        // TODO
                        mNewCarRepalceParamsModels.setMyMakeID(String.valueOf(carBrand.getBrandId()));
                        mNewCarRepalceParamsModels.setMyModelID(String.valueOf(carBrand.getModeId()));
                        mNewCarRepalceParamsModels.setMyStyleID(String.valueOf(carBrand.getId()));
                        mEditPlateNumValue.setText("");
                        mEditMileValue.setText("");
                    }
                }
                break;
            case TO_GET_CARBRAND_TWO:
                if (data != null) {
                    JzgCarChooseStyle carBrandTwo = (JzgCarChooseStyle) data.getSerializableExtra("mQueryCarStyle");
                    if(carBrandTwo != null){
                        mTvToBrandName.setText(carBrandTwo.getBrandName());
                        // TODO
                        mNewCarListParamsModels.NewMakeID = String.valueOf(carBrandTwo.getBrandId());

                    }
                }
                break;
            case INTENT_REQUEST_TIME:
                if (data != null) {
                    int selectYear = data.getIntExtra("year", -1);
                    int selectMonth = data.getIntExtra("month", -1);
                    String mRequestTime = selectYear + "-" + selectMonth;

                    mEditPlateNumValue.setText(mRequestTime);
                }
                break;
            case 99:
                if(resultCode == 100){
                    if(data != null){
                        if(data.getBooleanExtra("result", false)){
                            Intent intent = new Intent();
                            intent.putExtra("result", true);
                            setResult(100,intent);
                            ReplaceNewCarActivity.this.finish();
                        }
                    }
                }
                break;
        }
    }
    private int getBetweenTime(String start){
        if(TextUtils.isEmpty(start)){
            return -1;
        }
        final int nowYear = getNowYear();
        final int nowMonth = getNowMonth();
        String[] starts = start.split("-");
        if(starts.length<=1){
            return -1;
        }
        int startYear = Integer.valueOf(starts[0]);
        int startMonth = Integer.valueOf(starts[1]);
        if(startYear>nowYear){
            return 0;
        }
        if(startYear == nowYear && nowMonth <= startMonth){
            return 0;
        }
        if(startYear == nowYear && nowMonth > startMonth){
            return nowMonth-startMonth;
        }
        return (nowYear-startYear)*12 + nowMonth - startMonth;
    }
    public int getNowYear(){
        DateFormat sdf = new SimpleDateFormat("yyyy");
        final String date = sdf.format(new Date());
        if(TextUtils.isEmpty(date)){
            return 2015;
        }
        return Integer.valueOf(date);
    }
    public int getNowMonth(){
        DateFormat sdf = new SimpleDateFormat("MM");
        final String date = sdf.format(new Date());
        if(TextUtils.isEmpty(date)){
            return 8;
        }
        return Integer.valueOf(date);
    }
    private void startMakeYearThread(final String styleid) {
        LoanTimeParamsModels params = new LoanTimeParamsModels();
        ShowDialogTool.showLoadingDialog(ReplaceNewCarActivity.this);
        params.styleid = styleid;
        mLoginService.toRequestNet(
                params, LoanTimeResultModels.class, REQUEST_STYLE_TIME);
    }

    private final int INTENT_REQUEST_TIME = 0x1010;
    private void toShowDatePickerNew() {
        int thisMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
        Intent in = new Intent(ReplaceNewCarActivity.this, ValuationTimeSheetActivity.class);
        in.putExtra("Maxyear", StringUtil
                .getYear4String(mJzgCarChooseStyle.getMaxYEAR()));
        in.putExtra("Minyear", StringUtil
                .getYear4String(mJzgCarChooseStyle.getMinYEAR()));
        in.putExtra("MaxMonth", StringUtil
                .getMonth4String(mJzgCarChooseStyle.getMaxYEAR()));
        in.putExtra("MinMonth", StringUtil
                .getMonth4String(mJzgCarChooseStyle.getMinYEAR()));
        in.putExtra("CurMonth", thisMonth);
//        in.putExtra("Maxyear", maxYear);
//        in.putExtra("Minyear", minYear);
//        in.putExtra("MaxMonth", maxMonth);
//        in.putExtra("MinMonth", minMonth);minMonth
        startActivityForResult(in, INTENT_REQUEST_TIME);
    }

    private final int REQUEST_STYLE_TIME = 0x2002;
    private int mHasCarStyle = -1;

    @Override
    public void onRequestSuccess(Message msg) {
        ShowDialogTool.dismissLoadingDialog();
        switch(msg.what){
            case REQUEST_STYLE_TIME:
                LoanTimeResultModels timeResult = (LoanTimeResultModels) msg.obj;
                if(timeResult.getStatus() == 100){
//				Toast.makeText(MyReplaceCarActivity.this, "获取车型时间成功!", Toast.LENGTH_SHORT).show();
                    mHasCarStyle = mJzgCarChooseStyle.getId();
                    String MinYEAR = timeResult.getMinYEAR();
                    String MaxYEAR = timeResult.getMaxYEAR();
                    mRequestDataParams = new RequestDataParams();

                    int maxYear = getYear4String(MaxYEAR);
                    int MaxMonth = getMonth4String(MaxYEAR);
                    int minYear = getYear4String(MinYEAR);
                    int MinMonth = getMonth4String(MinYEAR);

                    mRequestDataParams.maxYear = maxYear;
                    mRequestDataParams.maxMonth = MaxMonth;
                    mRequestDataParams.minYear = minYear;
                    mRequestDataParams.minMonth = MinMonth;

                    toShowDatePickerNew();
                } else {
                    ShowDialogTool.showCenterToast(ReplaceNewCarActivity.this, "获取车型时间失败!");
                }
                break;
        }
    }
    @Override
    public void onRequestFault(Message msg) {
        switch(msg.what){
            case REQUEST_STYLE_TIME:
                ShowDialogTool.dismissLoadingDialog();
                ShowDialogTool.showCenterToast(ReplaceNewCarActivity.this, "无法与服务器建立连接，请重试。");
                break;
        }
    }
    private RequestDataParams mRequestDataParams;
    private class RequestDataParams {
        int maxYear;
        int minYear;
        int maxMonth;
        int minMonth;
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
                    year = Integer.parseInt(yearStr.substring(yearStr.indexOf("-")+1));
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
