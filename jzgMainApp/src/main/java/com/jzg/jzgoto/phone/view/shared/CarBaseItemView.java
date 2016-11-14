package com.jzg.jzgoto.phone.view.shared;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.models.CarData;
import com.jzg.jzgoto.phone.tools.ImageRender;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class CarBaseItemView extends LinearLayout {

    @Bind(R.id.view_car_imageview)
    ImageView mCarImageView;
    @Bind(R.id.view_car_name_textview)
    TextView mCarNameTextView;
    @Bind(R.id.view_car_mileage_textview)
    TextView mMileageTextView;
    @Bind(R.id.view_car_date_textview)
    TextView mDateTextView;
    @Bind(R.id.view_car_region_textview)
    TextView mRegionTextView;
    @Bind(R.id.view_car_price_textview)
    TextView mCarPriceTextView;
    @Bind(R.id.view_car_evaluate_price_textview)
    TextView mCarEvaluatePriceTextView;
    @Bind(R.id.view_car_evaluate_price_container)
    View mCarEvaluatePriceContainer;
    @Bind(R.id.view_car_offline)
    View mCarOfflineContainer;
    @Bind(R.id.view_item_divider)
    View mItemDividerView;

    private CarData mCarData;


    public CarBaseItemView(Context context) {
        super(context);
        initViews();
    }

    public CarBaseItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    public CarBaseItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        updateViews();
    }

    public void setData(CarData data) {
        mCarData = data;
        updateViews();
    }

    public void setOffline(boolean isOffline){
        if (mCarOfflineContainer != null){
            mCarOfflineContainer.setVisibility(isOffline ? View.VISIBLE : View.GONE);
        }
    }

    public CarData getData(){
        return mCarData;
    }

    private void updateViews() {
    	if (mCarData != null){
            ImageRender.getInstance().setImage(mCarImageView, mCarData.getImageUrl(), R.drawable.jingzhengu_moren);
            mCarNameTextView.setText(mCarData.getCarName());
            mMileageTextView.setText(mCarData.getMileage());
            mDateTextView.setText(mCarData.getReleaseTime());
            mRegionTextView.setText(mCarData.getCityName());
            mCarPriceTextView.setText(mCarData.getSellPrice());
            if (!TextUtils.isEmpty(mCarData.getApprisePrice())){
                double price = Double.parseDouble(mCarData.getApprisePrice());
                if (Double.compare(price, 0.0f) > 0) {
                    mCarEvaluatePriceContainer.setVisibility(View.VISIBLE);
                    mCarEvaluatePriceTextView.setText(mCarData.getApprisePrice() + "万");
                }else{
                    mCarEvaluatePriceContainer.setVisibility(View.GONE);
                }
            }else{
                mCarEvaluatePriceContainer.setVisibility(View.GONE);
            }

        }

    }

    private void initViews() {
    	LayoutInflater inflater = LayoutInflater.from(getContext());
    	View rootView = inflater.inflate(R.layout.view_car_base_item_view_layout, this);
        ButterKnife.bind(this);
    }

    public void setDivider(boolean isLast){
        //mItemDividerView.setVisibility(isLast ? View.VISIBLE : View.GONE);
    }

}
