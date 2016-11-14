package com.jzg.jzgoto.phone.view.carmanager;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.event.ModifyMyCarMileageEvent;
import com.jzg.jzgoto.phone.models.business.carmanager.CarManagerMyCarData;
import com.jzg.jzgoto.phone.view.ViewUtility;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;


public class MyCarToolInfoView extends LinearLayout implements View.OnClickListener {

    @Bind(R.id.textview_offence_num)
    TextView mCarOffenceNumTextView;
    @Bind(R.id.textview_car_maintenance_time)
    TextView mCarMaintenanceTextView;
    @Bind(R.id.textview_car_insurance_time)
    TextView mCarInsuranceTextView;

    private CarManagerMyCarData mCarData;

    public MyCarToolInfoView(Context context) {
        super(context);
        initViews();
    }

    public MyCarToolInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    public MyCarToolInfoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    @Override
    @OnClick({R.id.textview_offence_num, R.id.textview_car_maintenance_time, R.id.textview_car_insurance_time})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textview_offence_num:
                handleOffenceCountClicked();
                break;
            case R.id.textview_car_maintenance_time:
                handleMaintenanceTimeClicked();
                break;
            case R.id.textview_car_insurance_time:
                handleInsuranceTimeClicked();
                break;
        }
    }

    public void setData(CarManagerMyCarData carData) {
        mCarData = carData;
        updateViews();
    }

    private void updateViews() {
        if (mCarData != null){
            if (TextUtils.isEmpty(mCarData.getOffenceCount())){
                mCarOffenceNumTextView.setText(getResources().getString(R.string.car_manager_offence_count_empty));
            }else{
                mCarOffenceNumTextView.setText(
                        getResources().getString(R.string.car_manager_offence_count_format, mCarData.getOffenceCount()));
            }
            if (TextUtils.isEmpty(mCarData.getNextMaintenanceTime())){
                mCarMaintenanceTextView.setText(getResources().getString(R.string.car_manager_maintenance_time_empty));
            }else{
                mCarMaintenanceTextView.setText(
                        getResources().getString(R.string.car_manager_maintenance_time_format, mCarData.getNextMaintenanceTime()));
            }
            if (TextUtils.isEmpty(mCarData.getNextRepairTime())){
                mCarInsuranceTextView.setText(getResources().getString(R.string.car_manager_insurance_time_empty));
            }else{
                mCarInsuranceTextView.setText(
                        getResources().getString(R.string.car_manager_insurance_time_format, mCarData.getNextRepairTime()));
            }
        }else{
            String emptyStr = "--";
            mCarOffenceNumTextView.setText("--次");
            mCarMaintenanceTextView.setText(getResources().getString(R.string.car_manager_maintenance_time_empty));
            mCarInsuranceTextView.setText("--个月");
        }
    }

    private void initViews() {
    	LayoutInflater inflater = LayoutInflater.from(getContext());
    	View rootView = inflater.inflate(R.layout.view_carmanager_tool_info_layout, this);
        ButterKnife.bind(this);
    }

    private void handleOffenceCountClicked(){
        if (mCarData != null) {
            if (TextUtils.isEmpty(mCarData.getOffenceCount())) {
                ViewUtility.navigateToCompleteCarInfoActivity(this.getContext(), mCarData);
            }else{
                ViewUtility.navigateToOffenceCountQueryActivity(this.getContext());
            }
        }
    }

    private void handleMaintenanceTimeClicked(){
//        if (mCarData != null) {
//            EventBus.getDefault().post(ModifyMyCarMileageEvent.build(mCarData, false));
//        };
    }

    private void handleInsuranceTimeClicked(){
        if (mCarData != null) {
            ViewUtility.navigateToCompleteCarInfoActivity(this.getContext(), mCarData);
        }
    }
}
