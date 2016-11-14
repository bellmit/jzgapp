package com.jzg.jzgoto.phone.activity.sellcar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.activity.choosecity.ChooseCityActivity;
import com.jzg.jzgoto.phone.activity.common.ConstantForAct;
import com.jzg.jzgoto.phone.activity.valuation.ValuationTimeSheetActivity;
import com.jzg.jzgoto.phone.constant.Constant;
import com.jzg.jzgoto.phone.global.AppContext;
import com.jzg.jzgoto.phone.models.ValuationDetail;
import com.jzg.jzgoto.phone.models.business.sell.ApplyLoanParamsModels;
import com.jzg.jzgoto.phone.models.business.sell.ApplyLoanResultModels;
import com.jzg.jzgoto.phone.models.business.sell.LoanTimeParamsModels;
import com.jzg.jzgoto.phone.models.business.sell.LoanTimeResultModels;
import com.jzg.jzgoto.phone.services.business.login.LoginService;
import com.jzg.jzgoto.phone.services.common.OnRequestFinishListener;
import com.jzg.jzgoto.phone.tools.ShowDialogTool;
import com.jzg.jzgoto.phone.valuationchoosecarstyle.CarReleaseIndexCarActivity;
import com.jzg.jzgoto.phone.valuationchoosecarstyle.CarReleaseIndexValuationActivity;
import com.jzg.pricechange.phone.JzgCarChooseStyle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by jzg on 2016/9/22.
 * Function :
 */
public class SellCarLoanApplyActivity extends Activity implements OnRequestFinishListener{
    private LoginService mLoginService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellcar_loan_apply_layout);
        initView();
    }

    public void initView() {
        mLoginService = new LoginService(this, this);
        mPhoneNum = getIntent().getStringExtra("phone");
        mName = getIntent().getStringExtra("name");
        mOrderId = getIntent().getStringExtra("orderId");
        initOne();
        initTwo();
        initThree();

        if(getIntent().getSerializableExtra("valuationDetail") != null){
            mValuationDetail = (ValuationDetail) getIntent().getSerializableExtra("valuationDetail");
            initValuationResult();
        }
    }

    private void initValuationResult(){
        if(mValuationDetail == null)return;

        mJzgCarChooseStyle = new JzgCarChooseStyle();
        mJzgCarChooseStyle.setId(mValuationDetail.getValuation().getStyleId());
        mJzgCarChooseStyle.setFullName(mValuationDetail.getValuation().getFullName());
        mJzgCarChooseStyle.setModeId(mValuationDetail.getModelId());
        mJzgCarChooseStyle.setBrandId(mValuationDetail.getMakeId());
        mTvCarStyleValue.setText(mValuationDetail.getValuation().getFullName());

        mApplyLoanParamsModels.styleid = String.valueOf(mJzgCarChooseStyle.getId());
        String regDate = mValuationDetail.getValuation().getRegDate();
        if(!TextUtils.isEmpty(regDate)){// 2014/12/1 0:00:00 2014-06-01 2014年06月
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月");
            try {
                Date date = sdf.parse(regDate);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH)+1;
                String mRequestTime = year + "年" + month + "月";
                mEditRelTimeValue.setText(mRequestTime);
                mApplyLoanParamsModels.regdate=mRequestTime;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

		/*
		mNewCarRepalceParamsModels.setMyMakeID(String.valueOf(mJzgCarChooseStyle.getBrandId()));
		mNewCarRepalceParamsModels.setMyModelID(String.valueOf(mJzgCarChooseStyle.getModeId()));
		mNewCarRepalceParamsModels.setMyStyleID(String.valueOf(mJzgCarChooseStyle.getId()));

		mTvCarBrandName.setText(mValuationDetail.getValuation().getFullName());
		String regDate = mValuationDetail.getValuation().getRegDate();
		if(!TextUtils.isEmpty(regDate)){// 2014/12/1 0:00:00 2014-06-01
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date date = sdf.parse(regDate);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				int year = calendar.get(Calendar.YEAR);
				int month = calendar.get(Calendar.MONTH)+1;
				mEditPlateNumValue.setText(year + "-" + month);
				mNewCarRepalceParamsModels.setMyFirstRegistrationTime(year + "-" + month);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		mNewCarRepalceParamsModels.setMyMileage(String.valueOf(mValuationDetail.getValuation().getMileage()));
		mEditMileValue.setText(String.valueOf(mValuationDetail.getValuation().getMileage()));
		String province = mValuationDetail.getValuation().getProvName();
		String city = mValuationDetail.getValuation().getCityName();
		if(province.equals(city)){
			mTvAddressValue.setText(city);
		} else {
			mTvAddressValue.setText(province + " " + city);
		}
		mNewCarRepalceParamsModels.setMyProvinceID("0");
		mNewCarRepalceParamsModels.setMyCItyID("0");

		mNewCarRepalceParamsModels.setMyProvinceName(province);
		mNewCarRepalceParamsModels.setMyCityName(city);
		*/
    }
    /**
     * 估值结果
     */
    private ValuationDetail mValuationDetail = null;

    private String mPhoneNum;
    private String mName;
    private String mOrderId;

    private LinearLayout mLinearCarStyle;
    private EditText mTvCarStyleValue;
    private LinearLayout mLinearRelTime;
    private EditText mEditRelTimeValue;
    private LinearLayout mLinearMileage;
    private EditText mEditMileage;

    private void initOne(){
        mLinearCarStyle = (LinearLayout) findViewById(R.id.linear_apply_loan_carfullname);
        mTvCarStyleValue = (EditText) findViewById(R.id.edit_apply_loan_carfullname);

        mLinearRelTime = (LinearLayout) findViewById(R.id.linear_apply_loan_reltime);
        mEditRelTimeValue = (EditText) findViewById(R.id.edit_apply_loan_reltime_value);
        mLinearMileage = (LinearLayout) findViewById(R.id.linear_apply_loan_mileage);
        mEditMileage = (EditText) findViewById(R.id.edit_apply_loan_mileage_value);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.linear_apply_loan_mileage:
                        toShowAlert(mEditMileage, ConstantForAct.getMileageListForCarLoan()
                                .toArray(new String[]{}),ConstantForAct.getMileageListValueForCarLoan()
                                .toArray(new String[]{}));
                        break;
                    case R.id.linear_apply_loan_carfullname:
                        Intent intent = new Intent(SellCarLoanApplyActivity.this, CarReleaseIndexCarActivity.class);
                        intent.putExtra("newOrOld",0);
                        startActivityForResult(intent, TO_GET_CARBRAND);
//                        Intent intent1 = new Intent(SellCarLoanApplyActivity.this, CarReleaseIndexValuationActivity.class);
//                        startActivityForResult(intent1,TO_GET_CARBRAND);
                        break;
                    case R.id.linear_apply_loan_reltime:
//					showTimeDialog(mEditRelTimeValue);
                        if (mJzgCarChooseStyle == null) {
                            ShowDialogTool.showCenterToast(SellCarLoanApplyActivity.this, "请选择车型");
                            break;
                        }
                        if (mHasCarStyle != mJzgCarChooseStyle.getId()) {
                            mRequestDataParams = null;
                            startMakeYearThread(Integer.toString(mJzgCarChooseStyle.getId()));
                            return;
                        }
                        toShowDatePickerNew(mRequestDataParams.maxYear,
                                mRequestDataParams.minYear,
                                mRequestDataParams.maxMonth,
                                mRequestDataParams.minMonth);
//					toShowDatePickerNew(2015,1995,12,1);
                        break;
                }
            }
        };
        mLinearMileage.setOnClickListener(listener);
        mLinearCarStyle.setOnClickListener(listener);
        mLinearRelTime.setOnClickListener(listener);
        mEditRelTimeValue.setOnClickListener(listener);
    }

    private LinearLayout mLinearRelAddress;
    private EditText mEditRelAddressValue;
    private LinearLayout mLinearMoratgate;
    private EditText mEditMoratgateValue;
    private LinearLayout mLinearLoanTime;
    private EditText mEditLoanTimeValue;
    private void initTwo(){
        mLinearRelAddress = (LinearLayout) findViewById(R.id.linear_apply_loan_reladdress);
        mEditRelAddressValue = (EditText) findViewById(R.id.edit_apply_loan_reladdress_value);
        mLinearMoratgate = (LinearLayout) findViewById(R.id.linear_apply_loan_moartgate);
        mEditMoratgateValue = (EditText) findViewById(R.id.edit_apply_loan_moartgage_value);
        mLinearLoanTime = (LinearLayout) findViewById(R.id.linear_apply_loan_loantime);
        mEditLoanTimeValue = (EditText) findViewById(R.id.edit_apply_loan_loantime_value);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.linear_apply_loan_reladdress:
                        Intent intent = new Intent(SellCarLoanApplyActivity.this, ChooseCityActivity.class);
                        intent.putExtra("hideAllRegion", "hideAllRegion");
                        startActivityForResult(intent, Constant.QUERYCAR_REGION);
                        break;
                    case R.id.linear_apply_loan_moartgate:
                        toShowAlert(mEditMoratgateValue,ConstantForAct.getMortgateForCarLoan()
                                .toArray(new String[]{}),ConstantForAct.getMortgateValueForCarLoan()
                                .toArray(new String[]{}));
                        break;
                    case R.id.linear_apply_loan_loantime:
                        toShowAlert(mEditLoanTimeValue,ConstantForAct.getLoanTimeForCarLoan()
                                .toArray(new String[]{}),ConstantForAct.getLoanTimeValueForCarLoan()
                                .toArray(new String[]{}));
                        break;
                }
            }
        };
        mLinearRelAddress.setOnClickListener(listener);
        mLinearMoratgate.setOnClickListener(listener);
        mLinearLoanTime.setOnClickListener(listener);

    }
    private LinearLayout mLinearSex;
    private EditText mEditSexValue;
    private EditText mEditAge;
    private LinearLayout mLinearCredit;
    private EditText mEditCreditValue;
    private void initThree(){
        mLinearSex = (LinearLayout) findViewById(R.id.linear_apply_loan_sex);
        mEditSexValue = (EditText) findViewById(R.id.edit_apply_loan_sex_value);
        mEditAge = (EditText) findViewById(R.id.edit_apply_loan_age_value);
        mLinearCredit = (LinearLayout) findViewById(R.id.linear_apply_loan_credit);
        mEditCreditValue = (EditText) findViewById(R.id.edit_apply_loan_credit_value);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.linear_apply_loan_sex:
                        toShowAlert(mEditSexValue,ConstantForAct.getGenderForCarLoan()
                                .toArray(new String[]{}),ConstantForAct.getGenderForCarLoan()
                                .toArray(new String[]{}));
                        break;
                    case R.id.linear_apply_loan_credit:
                        toShowAlert(mEditCreditValue,ConstantForAct.getCreditForCarLoan()
                                .toArray(new String[]{}),ConstantForAct.getCreditValueForCarLoan()
                                .toArray(new String[]{}));
                        break;
                }
            }
        };
        mLinearSex.setOnClickListener(listener);
        mLinearCredit.setOnClickListener(listener);

        mEditAge.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (TextUtils.isEmpty(s))
                    return;
                if(s.toString().startsWith("0")){
                    ShowDialogTool.showCenterToast(SellCarLoanApplyActivity.this, "不能输入0岁");
                    mEditAge.setText("");
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

    }
    /**
     * 车系出厂时间和停产时间 startMakeYearThread: <br/>
     */
    private void startMakeYearThread(final String styleid) {
        LoanTimeParamsModels params = new LoanTimeParamsModels();
        ShowDialogTool.showLoadingDialog(SellCarLoanApplyActivity.this);
        params.styleid = styleid;
        mLoginService.toRequestNet(
                params, LoanTimeResultModels.class, REQUEST_STYLE_TIME);
    }
    public void onSubmitAll(View view){
        // TODO 申请贷款
        toApplyLoan();
    }
    private final ApplyLoanParamsModels mApplyLoanParamsModels = new ApplyLoanParamsModels();
    private void toApplyLoan(){
        if(TextUtils.isEmpty(mApplyLoanParamsModels.styleid)){
            ShowDialogTool.showCenterToast(SellCarLoanApplyActivity.this, "请选择车型");
            return;
        }
        if(TextUtils.isEmpty(mApplyLoanParamsModels.regdate)){
            ShowDialogTool.showCenterToast(SellCarLoanApplyActivity.this, "请选择上牌时间");
            return;
        }
        if(mEditMileage.getTag() != null){
            String mileage = (String) mEditMileage.getTag();
            if(TextUtils.isEmpty(mileage)){
                ShowDialogTool.showCenterToast(SellCarLoanApplyActivity.this, "请选择行驶里程");
                return;
            }
            mApplyLoanParamsModels.mileage=mileage;
        } else {
            ShowDialogTool.showCenterToast(SellCarLoanApplyActivity.this, "请选择行驶里程");
            return;
        }
        if(TextUtils.isEmpty(mApplyLoanParamsModels.cityid)&&TextUtils.isEmpty(mApplyLoanParamsModels.cityname)){
            ShowDialogTool.showCenterToast(SellCarLoanApplyActivity.this, "请选择上牌地区");
            return;
        }
        if(mEditMoratgateValue.getTag() != null){
            String moratgate = (String) mEditMoratgateValue.getTag();
            if(TextUtils.isEmpty(moratgate)){
                ShowDialogTool.showCenterToast(SellCarLoanApplyActivity.this, "请选择抵押状态");
                return;
            }
            mApplyLoanParamsModels.hasMortgate=moratgate;
        } else {
            ShowDialogTool.showCenterToast(SellCarLoanApplyActivity.this, "请选择抵押状态");
            return;
        }

        if(mEditLoanTimeValue.getTag() != null){
            String loanTime = (String) mEditLoanTimeValue.getTag();
            if(TextUtils.isEmpty(loanTime)){
                ShowDialogTool.showCenterToast(SellCarLoanApplyActivity.this, "请选择贷款期限");
                return;
            }
            mApplyLoanParamsModels.loanperiod=loanTime;
        } else {
            ShowDialogTool.showCenterToast(SellCarLoanApplyActivity.this, "请选择贷款期限");
            return;
        }

        if(TextUtils.isEmpty(mOrderId)){
            ShowDialogTool.showCenterToast(SellCarLoanApplyActivity.this, "贷款单号申请失败，请返回重新提交");
            return;
        }
        if(TextUtils.isEmpty(mPhoneNum)){
            ShowDialogTool.showCenterToast(SellCarLoanApplyActivity.this, "申请手机号码未提交成功，请返回重新提交");
            return;
        }
        if(TextUtils.isEmpty(mName)){
            ShowDialogTool.showCenterToast(SellCarLoanApplyActivity.this, "姓名未提交成功，请返回重新提交");
            return;
        }
        mApplyLoanParamsModels.orderid=mOrderId;
        if(AppContext.isLogin()){
            mApplyLoanParamsModels.uid=AppContext.mLoginResultModels.getId();
        }else{
            mApplyLoanParamsModels.uid="0";
        }
        mApplyLoanParamsModels.mobile=mPhoneNum;
        mApplyLoanParamsModels.username=mName;
        if(mEditSexValue.getTag() != null){
            String sex = (String) mEditSexValue.getTag();
            if(TextUtils.isEmpty(sex)){
                ShowDialogTool.showCenterToast(SellCarLoanApplyActivity.this, "请选择性别");
                return;
            }
            mApplyLoanParamsModels.gender=sex;
        } else {
            ShowDialogTool.showCenterToast(SellCarLoanApplyActivity.this, "请选择性别");
            return;
        }
        final String age = mEditAge.getText().toString();

        if (TextUtils.isEmpty(age)) {
            ShowDialogTool.showCenterToast(SellCarLoanApplyActivity.this, "请填写年龄");
            return;
        }
		/*
		else {
			final int ageI = Integer.valueOf(age);
			if (ageI < 18) {
				ShowDialogTool.showCenterToast(ApplyLoanActivity.this, "申款人必须年满18岁！");
				return;
			}
		}
		*/
        mApplyLoanParamsModels.age=age;
        if(mEditCreditValue.getTag() != null){
            String credit = (String) mEditCreditValue.getTag();
            if(TextUtils.isEmpty(credit)){
                ShowDialogTool.showCenterToast(SellCarLoanApplyActivity.this, "请选择信用记录");
                return;
            }
            mApplyLoanParamsModels.credit=credit;
        } else {
            ShowDialogTool.showCenterToast(SellCarLoanApplyActivity.this, "请选择信用记录");
            return;
        }

        ShowDialogTool.showLoadingDialog(SellCarLoanApplyActivity.this);
        mLoginService.toRequestNet(
                mApplyLoanParamsModels, ApplyLoanResultModels.class, REQUEST_APPLY_LOAN);
    }

    private final int TO_GET_CARBRAND = 0x1002;
    private JzgCarChooseStyle mJzgCarChooseStyle;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case TO_GET_CARBRAND:
                if (data != null) {
                    JzgCarChooseStyle carBrand = (JzgCarChooseStyle) data.getSerializableExtra("mQueryCarStyle");
                    if(carBrand != null){
                        mJzgCarChooseStyle = carBrand;
                        mTvCarStyleValue.setText(carBrand.getFullName());
                        // TODO
//					mNewCarRepalceParamsModels.setMyMakeID(String.valueOf(carBrand.getBrandId()));
//					mNewCarRepalceParamsModels.setMyModelID(String.valueOf(carBrand.getModeId()));
//					mNewCarRepalceParamsModels.setMyStyleID(String.valueOf(carBrand.getId()));
                        mApplyLoanParamsModels.styleid = String.valueOf(carBrand.getId());
//					startMakeYearThread(String.valueOf(carBrand.getId()));
                    }
                }
                break;
            case Constant.QUERYCAR_REGION:
                if (data != null) {
                    String provinceID = data.getStringExtra(ChooseCityActivity.return_flag_provinceid);
                    String cityID = data.getStringExtra(ChooseCityActivity.return_flag_cityid);
//				String province = data.getStringExtra("province");
                    String cityName = data.getStringExtra(ChooseCityActivity.return_flag_cityname);
                    if(TextUtils.isEmpty(cityName)){
                        cityName = "";
                    }
                    if(TextUtils.isEmpty(cityID)){
                        cityID = "";
                    }

                    mEditRelAddressValue.setText(cityName);
//				mNewCarRepalceParamsModels.setMyProvinceID(provinceID);
//				mNewCarRepalceParamsModels.setMyCItyID(cityID);

//				mNewCarRepalceParamsModels.setMyProvinceName(province);
//				mNewCarRepalceParamsModels.setMyCityName(city);
                    mApplyLoanParamsModels.provinceid=provinceID;
                    mApplyLoanParamsModels.cityid=cityID;
                    mApplyLoanParamsModels.cityname = cityName;
                }
                break;
            case VALUATION_TIME_MSG:
                if (data != null) {
                    int selectYear = data.getIntExtra("year", -1);
                    int selectMonth = data.getIntExtra("month", -1);
                    String mRequestTime = selectYear + "年" + selectMonth + "月";
                    mEditRelTimeValue.setText(mRequestTime);
                    mApplyLoanParamsModels.regdate=mRequestTime;
				/*
				mTvFirstDate.setTag(R.id.oneCost, Integer.toString(selectYear));
				mTvFirstDate
						.setTag(R.id.twoCost, Integer.toString(selectMonth));
				*/
                }
                break;
        }
    }

    private int mHasCarStyle = -1;
    private final int REQUEST_APPLY_LOAN = 0x2001;
    private final int REQUEST_STYLE_TIME = 0x2002;
    @Override
    public void onRequestSuccess(Message msg) {
        ShowDialogTool.dismissLoadingDialog();
        switch(msg.what){
            case REQUEST_APPLY_LOAN:
                ApplyLoanResultModels loanResult = (ApplyLoanResultModels) msg.obj;
                if(loanResult.isSuccess()){
                    final View view = LayoutInflater.from(
                            SellCarLoanApplyActivity.this).inflate(
                            R.layout.view_sellcar_loanapply_dialog_layout, null);
                    final String b2c = loanResult.getB2C();
                    if (TextUtils.isEmpty(b2c) || Double.valueOf(b2c) == 0d) {
                        view.findViewById(R.id.linear_dialog_car_loan_price)
                                .setVisibility(View.GONE);
                    } else {
                        final TextView price = (TextView) view
                                .findViewById(R.id.tv_dialog_car_loan_recom);
                        final TextView loan = (TextView) view
                                .findViewById(R.id.tv_dialog_car_loan_price);
                        price.setText(b2c + "万");
                        loan.setText(loanResult.getB2CLoan() + "万");
                    }

                    ShowDialogTool.showSelfViewDialog(SellCarLoanApplyActivity.this,
                            view, false, null);
                    mHandler.sendEmptyMessageDelayed(CLOSE_DIALOG_SHOW, 5 * 1000);

                } else {
                    ShowDialogTool.showCenterToast(SellCarLoanApplyActivity.this, "申请失败");
//				Log.i("gf", "申请失败!" + (String)msg.obj);
                }
                break;
            case REQUEST_STYLE_TIME:
                LoanTimeResultModels timeResult = (LoanTimeResultModels) msg.obj;
                if(timeResult.getStatus() == 100){
//				Log.i("gf", "获取车型时间成功!");
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

                    toShowDatePickerNew(mRequestDataParams.maxYear,
                            mRequestDataParams.minYear,
                            mRequestDataParams.maxMonth,
                            mRequestDataParams.minMonth);
                } else {
                    ShowDialogTool.showCenterToast(SellCarLoanApplyActivity.this, "获取车型时间失败");
//				Log.i("gf", "获取车型时间失败!" + (String)msg.obj);
                }
                break;
        }
    }
    @Override
    public void onRequestFault(Message msg) {
        ShowDialogTool.dismissLoadingDialog();
        switch(msg.what){
            case REQUEST_STYLE_TIME:
            case REQUEST_APPLY_LOAN:
//			Log.i("gf", "请求失败:" + (String)msg.obj);
                ShowDialogTool.showCenterToast(SellCarLoanApplyActivity.this, "无法与服务器建立连接，请重试。");
                break;
        }
    }
    private final int CLOSE_DIALOG_SHOW = 0x1001;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CLOSE_DIALOG_SHOW:
                    ShowDialogTool.dismissSelfViewDialog();
                    SellCarLoanApplyActivity.this.finish();
                    break;
            }

        };
    };
    private void toShowAlert(final TextView textView,final String[] shows,final String[] values){
        AlertDialog.Builder builer = new AlertDialog.Builder(SellCarLoanApplyActivity.this);
		/*
		builer.setItems(shows, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				textView.setText(shows[which]);
				textView.setTag(values[which]);
			}
		});
		*/
        builer.setAdapter(new BaseAdapter() {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if(convertView == null){
                    convertView = new TextView(SellCarLoanApplyActivity.this);
                }
                final TextView tv = (TextView)convertView;
                tv.setTextSize(14);
                tv.setText(shows[position]);
                tv.setTextColor(getResources().getColor(R.color.grey3));
                tv.setPadding(40, 25, 40, 25);
                return convertView;
            }
            @Override
            public long getItemId(int position) {
                return position;
            }
            @Override
            public Object getItem(int position) {
                return shows[position];
            }
            @Override
            public int getCount() {
                return shows.length;
            }
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                textView.setText(shows[which]);
                textView.setTag(values[which]);
            }
        });
        builer.show();
    }
    private final int VALUATION_TIME_MSG = 0x1020;
    private void toShowDatePickerNew(int maxYear, int minYear, int maxMonth,
                                     int minMonth) {
        Intent in = new Intent(SellCarLoanApplyActivity.this, ValuationTimeSheetActivity.class);
        in.putExtra("Maxyear", maxYear);
        in.putExtra("Minyear", minYear);
        in.putExtra("MaxMonth", maxMonth);
        in.putExtra("MinMonth", minMonth);
        startActivityForResult(in, VALUATION_TIME_MSG);
    }
    private RequestDataParams mRequestDataParams;
    private class RequestDataParams {
        int maxYear;
        int minYear;
        int maxMonth;
        int minMonth;
    }
    /**
     * 2015-12  --> 2015
     */
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
    /**
     * 2015-03  --> 3
     */
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
