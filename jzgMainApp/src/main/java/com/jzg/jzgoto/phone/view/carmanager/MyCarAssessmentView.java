package com.jzg.jzgoto.phone.view.carmanager;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.event.ModifyMyCarMileageEvent;
import com.jzg.jzgoto.phone.models.business.carmanager.CarManagerMyCarData;
import com.jzg.jzgoto.phone.models.business.carmanager.PriceTrendData;
import com.jzg.jzgoto.phone.view.carmanager.DrawViewLineChartDataModel;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;


public class MyCarAssessmentView extends LinearLayout implements View.OnClickListener {

    @Bind(R.id.textview_assessment_time)
    TextView mCarAssessmentTimeTextView;
    @Bind(R.id.textview_car_price)
    TextView mCarPriceTextView;
    @Bind(R.id.btn_query_update)
    TextView mQueryUpdateBtn;
    @Bind(R.id.view_price_trend_view)
    PriceTrendView mPriceTrendView;


    private CarManagerMyCarData mCarData;

    public MyCarAssessmentView(Context context) {
        super(context);
        initViews();
    }

    public MyCarAssessmentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    public MyCarAssessmentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    public void setData(CarManagerMyCarData carData) {
        mCarData = carData;
        updateViews();
    }

    private void updateViews() {
    	if (mCarData != null){
            mCarAssessmentTimeTextView.setText(mCarData.getLastEvaluationTime() + "估值");
            mCarPriceTextView.setText(mCarData.getLastEvaluationPrice() + "万");
            showPriceTrend();
        }else{
            mCarAssessmentTimeTextView.setText("估值");
            mCarPriceTextView.setText("--");
            showDefaultPriceTrend();
        }
    }

    private void initViews() {
    	LayoutInflater inflater = LayoutInflater.from(getContext());
    	View rootView = inflater.inflate(R.layout.view_carmanager_assessment_layout, this);
        ButterKnife.bind(this);
    }

    @Override
    @OnClick({R.id.btn_query_update, R.id.view_assessment_container})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_query_update:
            case R.id.view_assessment_container:
                EventBus.getDefault().post(ModifyMyCarMileageEvent.build(mCarData, true));
                break;
        }
    }

    private void showPriceTrend(){
        DrawViewLineChartDataModel dataModel = getCarCycleViewData(mCarData.getPriceTrend());
        mPriceTrendView.toShowData(dataModel,getSizeRatio());
    }

    private void showDefaultPriceTrend(){
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

        String[] xShow = new String[7];
        for (int i = 0; i < xShow.length; i++){
            int currMonth = month - i;
            if (currMonth <= 0){
                currMonth += 12;
            }
            String monthStr = String.valueOf(currMonth);
            monthStr += "月";
            xShow[xShow.length - 1 - i] = monthStr;
        }
        String[] yShow = {"5.0","3.8","2.5","1.2","0.0"};
        mPriceTrendView.toShowDefaultData(xShow, yShow,getSizeRatio());
    }

    private float mSizeRatio = 0f;
    public float getSizeRatio(){
        if(mSizeRatio > 0f)return mSizeRatio;
        DisplayMetrics dm = new DisplayMetrics();
        dm = getResources().getDisplayMetrics();
        final float ratio = dm.density;
        mSizeRatio = ratio;
        return ratio;
    }

    private DrawViewLineChartDataModel getCarCycleViewData(List<PriceTrendData> priceTrendDataList){
        DrawViewLineChartDataModel dataModel = new DrawViewLineChartDataModel();
        List<PriceTrendData> modelList = priceTrendDataList;
        List<Float> pointList = new ArrayList<Float>();
        float[] pointValues = new float[7];
        String[] xShow = new String[7];
        DecimalFormat df=new DecimalFormat(".00");
        for(int i=0;i<7;i++){
            if(i<modelList.size()){
                String str = modelList.get(i).getPrice();
                str = df.format(Double.valueOf(str));
                pointList.add(Float.valueOf(str));
                pointValues[i] = Float.valueOf(str);
                xShow[i] = modelList.get(i).getDate();
            }else{
                pointList.add(0.0f);
                pointValues[i] = 0.0f;
                xShow[i] = "";
            }
        }
        dataModel.setPointDataList(pointList);
        dataModel.setPointShowData(pointValues);
        dataModel.setxShowValue(xShow);
        dataModel.setShowYPercent(false);
        return dataModel;
    }
}
