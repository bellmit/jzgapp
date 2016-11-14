package com.jzg.jzgoto.phone.view.carmanager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.models.business.carmanager.CarManagerMyCarData;
import com.jzg.jzgoto.phone.view.ViewUtility;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MyCarManagerView extends LinearLayout implements View.OnClickListener {

    private MyCarBannerView mMyCarBannerView;

    @Bind(R.id.car_banner_view_container)
    LinearLayout mBannerContainer;
    @Bind(R.id.car_empty_container)
    LinearLayout mEmptyContainer;
    @Bind(R.id.btn_add_car)
    TextView mAddCarBtnView;
    @Bind(R.id.btn_manager_my_car)
    TextView mManageCarBtnView;


    private ImageView mCarImageView;
    private CarManagerMyCarData mCarData;


    public MyCarManagerView(Context context) {
        super(context);
        initViews();
    }

    public MyCarManagerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    public MyCarManagerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    public MyCarBannerView getBannerView(){
        return mMyCarBannerView;
    }

    public void setMyCarData(List<CarManagerMyCarData> carList) {
        if (carList != null && carList.size() > 0) {
            if (mMyCarBannerView != null) {
                //mMyCarBannerView.stopShow();
                mMyCarBannerView.setData(carList);
                //mMyCarBannerView.startShow();
            }
            mBannerContainer.setVisibility(View.VISIBLE);
            mEmptyContainer.setVisibility(View.GONE);
            mManageCarBtnView.setVisibility(View.VISIBLE);
        }else{
            mBannerContainer.setVisibility(View.GONE);
            mEmptyContainer.setVisibility(View.VISIBLE);
            mManageCarBtnView.setVisibility(View.GONE);
        }
    }

    public void notifyDataSetChanged(){
        mMyCarBannerView.notifyDataSetChanged();
    }

    private void updateViews() {
    	
    }

    private void initViews() {
    	LayoutInflater inflater = LayoutInflater.from(getContext());
    	View rootView = inflater.inflate(R.layout.view_my_car_manager_view_layout, this);
        ButterKnife.bind(this);
        mMyCarBannerView = new MyCarBannerView(this.getContext());
        mBannerContainer.addView(mMyCarBannerView);
    }

    @Override
    @OnClick({R.id.btn_add_car, R.id.btn_manager_my_car})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_car:
                handleAddCarClicked();
                break;
            case R.id.btn_manager_my_car:
                handleManageCarClicked();
                break;
        }
    }

    private void handleAddCarClicked(){
        ViewUtility.navigateToAddMyCarActivity(this.getContext());
    }

    private void handleManageCarClicked(){
        ViewUtility.navigateToMyCarListActivity(this.getContext());
    }
}
