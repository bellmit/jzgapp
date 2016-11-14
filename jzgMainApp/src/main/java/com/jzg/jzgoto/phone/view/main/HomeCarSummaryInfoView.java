package com.jzg.jzgoto.phone.view.main;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.activity.common.ConstantForAct;
import com.jzg.jzgoto.phone.activity.valuation.ValuationTimeSheetActivity;
import com.jzg.jzgoto.phone.event.CarChooseEvent;
import com.jzg.jzgoto.phone.event.CityChooseEvent;
import com.jzg.jzgoto.phone.event.TimeChooseEvent;
import com.jzg.jzgoto.phone.fragment.carmanager.CarManagerFragment;
import com.jzg.jzgoto.phone.global.AppContext;
import com.jzg.jzgoto.phone.models.CarValuateOptionData;
import com.jzg.jzgoto.phone.tools.AppUtils;
import com.jzg.jzgoto.phone.tools.StringUtil;
import com.jzg.jzgoto.phone.tools.ToastManager;
import com.jzg.jzgoto.phone.view.ViewUtility;
import com.jzg.pricechange.phone.JzgCarChooseStyle;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;


public class HomeCarSummaryInfoView extends LinearLayout implements View.OnClickListener {

    @Bind(R.id.view_car_style_container)
    View mCarStyleContainer;
    @Bind(R.id.view_car_style_textview)
    TextView mCarStyleTextView;
    @Bind(R.id.view_car_license_date_container)
    View mCarLicenseDateContainer;
    @Bind(R.id.view_car_license_date_textview)
    TextView mCarLicenseDateTextView;
    @Bind(R.id.view_car_place_container)
    View mCarPlaceContainer;
    @Bind(R.id.view_car_place_textview)
    TextView mCarPlaceTextView;
    @Bind(R.id.view_mileage_editor)
    EditText mMileageEditor;

    private JzgCarChooseStyle mCarChooseStyle;
    private int mYear;
    private int mMonth;
    private String mCityName = "";
    private String mCityId = "";
    private String mRegisterDate = "";
    private String mMileage = "";
    private CarValuateOptionData mCarValuateOptionData;
    private Runnable mSaveRunnable;


    public HomeCarSummaryInfoView(Context context) {
        super(context);
        initViews();
    }

    public HomeCarSummaryInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    public HomeCarSummaryInfoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        updateViews();
    }

    @Override
    protected void onAttachedToWindow() {
        EventBus.getDefault().register(this);
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        EventBus.getDefault().unregister(this);
        super.onDetachedFromWindow();
    }

    public void onEvent(CarChooseEvent event) {
        if (event == null || event.sourceFrom != this.getId()) {
            return;
        }
        mCarChooseStyle = event.carInfo;
        if (mCarChooseStyle != null){
            mCarStyleTextView.setText(mCarChooseStyle.getFullName());
            mCarLicenseDateTextView.setText(getResources().getString(R.string.car_summary_info_select_car_register_date_tip));
            mMileageEditor.setText("");
            setDataToCache();
        }
    }

    public void onEvent(TimeChooseEvent event) {
        if (event != null && event.sourceFrom == this.getId()) {
            mYear = event.year;
            mMonth = event.month;
            if (mMonth < 10){
                mRegisterDate = mYear + "-" + "0" + mMonth + "-01";
            }else {
                mRegisterDate = mYear + "-" + mMonth + "-01";
            }
            mCarLicenseDateTextView.setText( mYear + "年" + mMonth + "月");
            setDataToCache();
        }
    }

    public void onEvent(CityChooseEvent event) {
        if (event != null && event.cityInfo != null && event.sourceFrom == this.getId()) {
            mCityId = event.cityInfo.cityId;
            mCityName = StringUtil.returnShi(event.cityInfo.cityName);
            mCarPlaceTextView.setText(mCityName);
            setDataToCache();
        }
    }

    public boolean isDataReady(){
        if(mCarChooseStyle == null){
            ToastManager.getInstance().showToastCenter(this.getContext(), getResources().getString(R.string.car_summary_info_select_car_brand_empty_tip));
            return false;
        }else if(TextUtils.isEmpty(mRegisterDate)){
            ToastManager.getInstance().showToastCenter(this.getContext(), getResources().getString(R.string.car_summary_info_select_car_register_date_empty_tip));
            return false;
        }else if(TextUtils.isEmpty(mMileage)){
            ToastManager.getInstance().showToastCenter(this.getContext(), getResources().getString(R.string.car_summary_info_select_car_mileage_empty_tip));
            return false;
        }else if(TextUtils.isEmpty(mCityId) && TextUtils.isEmpty(mCityName)){
            ToastManager.getInstance().showToastCenter(this.getContext(), getResources().getString(R.string.car_summary_info_select_place_empty_tip));
            return false;
        }
        if(mMileage.equals("0") || mMileage.equals("0.") || Double.compare(Double.valueOf(mMileage), 0.0f) <= 0){
            ToastManager.getInstance().showToastCenter(this.getContext(), R.string.car_manager_input_mileage_err_msg);
            return false;
        }
        String temp[] = mRegisterDate.split("-");
        String tempStr = mRegisterDate;
        if (temp.length >= 2){
            tempStr = temp[0] + "年" + temp[1] + "月";
        }
        int months = StringUtil.getMonthFromRegDate(tempStr);
        if(Double.valueOf(mMileage) > months){
            ToastManager.getInstance().showToastCenter(this.getContext(), R.string.car_manager_input_mileage_not_in_limited_err_msg);
            return false;
        }
        return true;
    }

    public JzgCarChooseStyle getCarStyle(){
        return mCarChooseStyle;
    }

    public String getCityId(){
        return mCityId;
    }

    public String getCityName(){
        return mCityName;
    }

    public String getRegisterDate(){
        return mRegisterDate;
    }

    public String getMileage(){
        return mMileage;
    }

    public void setData() {
        updateViews();
    }

    protected int getLayoutId() {
        return R.layout.view_home_car_summary_info_view_layout;
    }

    protected void updateViews() {
        if (mCarChooseStyle != null) {
            mCarStyleTextView.setText(mCarChooseStyle.getFullName());
        }
        if (!TextUtils.isEmpty(mRegisterDate)) {
            String temp[] = mRegisterDate.split("-");
            String tempStr = mRegisterDate;
            if (temp.length >= 2){
                tempStr = temp[0] + "年" + temp[1] + "月";
            }
            mCarLicenseDateTextView.setText(tempStr);
        }
        if (!TextUtils.isEmpty(mMileage)) {
            mMileageEditor.setText(mMileage);
        }
        if (!TextUtils.isEmpty(mCityName)) {
            mCarPlaceTextView.setText(mCityName);
        }
    }

    protected void initViews() {
    	LayoutInflater inflater = LayoutInflater.from(getContext());
    	View rootView = inflater.inflate(getLayoutId(), this);
        ButterKnife.bind(this);
        mMileageEditor.setOnEditorActionListener(mMileageEditorActionListener);
        mMileageEditor.addTextChangedListener(mMileageEditorTexWatcher);
        getDataFromCache();
    }

    private void getDataFromCache(){
        CarValuateOptionData carValuateOptionData = ConstantForAct.getCarValuationOption(this.getContext());
        if (carValuateOptionData != null){
            mCarValuateOptionData = carValuateOptionData;
            mCityName = carValuateOptionData.mCityName;
            mCityId = carValuateOptionData.mCityId;
            mRegisterDate = carValuateOptionData.mRegisterDate;
            mMileage = carValuateOptionData.mMileage;
            mCarChooseStyle = carValuateOptionData.mCarChooseStyle;
        }else{
            mCarValuateOptionData = new CarValuateOptionData();
        }
        updateViews();
    }

    private void setDataToCache(){
        CarValuateOptionData carValuateOptionData = new CarValuateOptionData();
        carValuateOptionData.mCarChooseStyle = mCarChooseStyle;
        carValuateOptionData.mRegisterDate = mRegisterDate;
        carValuateOptionData.mCityId = mCityId;
        carValuateOptionData.mCityName = mCityName;
        carValuateOptionData.mMileage = mMileage;
        mCarValuateOptionData = carValuateOptionData;
        try{
            AppContext.getHandler().removeCallbacks(mSaveRunnable);
            mSaveRunnable = new Runnable() {
                public void run() {
                    ConstantForAct.setCarValuationOption(HomeCarSummaryInfoView.this.getContext(), mCarValuateOptionData);
                }
            };
            AppContext.getHandler().postDelayed(mSaveRunnable, 3000);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    @OnClick({R.id.view_car_style_container, R.id.view_car_license_date_container, R.id.view_car_place_container})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.view_car_style_container:
                selectCarStyle();
                break;
            case R.id.view_car_license_date_container:
                selectLicenseDate();
                break;
            case R.id.view_car_place_container:
                selectPlace();
                break;
        }
    }

    private void selectCarStyle(){
        Context context = this.getContext();
        ViewUtility.navigateToCarReleaseIndexValuationActivity(context, this.getId());
    }

    private void selectLicenseDate(){
        Context context = this.getContext();
        if(mCarChooseStyle == null){
            ToastManager.getInstance().showToastCenter(context, getResources().getString(R.string.car_summary_info_select_car_first));
        }else{
            Intent in = new Intent(context, ValuationTimeSheetActivity.class);
            in.putExtra("Maxyear", StringUtil.getYear4String(mCarChooseStyle.getMaxYEAR()));
            in.putExtra("Minyear", StringUtil.getYear4String(mCarChooseStyle.getMinYEAR()));
            in.putExtra("MaxMonth", StringUtil.getMonth4String(mCarChooseStyle.getMaxYEAR()));
            in.putExtra("MinMonth", StringUtil.getMonth4String(mCarChooseStyle.getMinYEAR()));
            in.putExtra(ValuationTimeSheetActivity.PARAMS_KEY_SOURCEFROM, this.getId());
            context.startActivity(in);
        }
    }

    private void selectPlace(){
        ViewUtility.navigateToChooseCityActivity(this.getContext(), this.getId());
    }

    private EditText.OnEditorActionListener mMileageEditorActionListener = new EditText.OnEditorActionListener() {

        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            switch (actionId) {
                case EditorInfo.IME_ACTION_DONE:
                    return true;
            }
            return false;
        }
    };

    private TextWatcher mMileageEditorTexWatcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() == 0) {
                return;
            }
            AppUtils.VerifyMileageValid(HomeCarSummaryInfoView.this.getContext(), mMileageEditor, s);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            mMileage = mMileageEditor.getText().toString().trim();
            setDataToCache();
        }
    };

}
