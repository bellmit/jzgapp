package com.jzg.jzgoto.phone.view.carmanager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.models.business.carmanager.ProvinceSummaryData;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class CarLicensePlaceItemView extends LinearLayout {

    @Bind(R.id.view_license_place_summary_text)
    TextView mLicensePlaceSummaryTextView;
    View mItemDividerView;

    private ProvinceSummaryData mProvinceData;


    public CarLicensePlaceItemView(Context context) {
        super(context);
        initViews();
    }

    public CarLicensePlaceItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    public CarLicensePlaceItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        updateViews();
    }

    public void setData(ProvinceSummaryData data) {
        mProvinceData = data;
        updateViews();
    }

    private void updateViews() {
    	if (mProvinceData != null){
            mLicensePlaceSummaryTextView.setText(mProvinceData.getSummaryName());
        }
    }

    private void initViews() {
    	LayoutInflater inflater = LayoutInflater.from(getContext());
    	View rootView = inflater.inflate(R.layout.view_carmanager_license_place_item_view, this);
        ButterKnife.bind(this);
    }

    public void setDivider(boolean isLast){
        //mItemDividerView.setVisibility(isLast ? View.VISIBLE : View.GONE);
    }

}
