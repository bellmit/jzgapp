package com.jzg.jzgoto.phone.view.carmanager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.adapter.carmanager.ExpandableFocusCarAdapter;
import com.jzg.jzgoto.phone.models.business.carmanager.FocusCarData;
import com.jzg.jzgoto.phone.view.ViewUtility;
import com.jzg.jzgoto.phone.view.common.InScrollExpandableListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MyFocuseCarView extends LinearLayout implements View.OnClickListener{

    @Bind(R.id.btn_focus_car_manager)
    TextView mFocusCarManagerBtn;
    @Bind(R.id.my_focus_car_view_container)
    View mFocusCarListContainer;
    @Bind(R.id.my_focus_car_empty_container)
    View mFocusCarEmptyContainer;
    @Bind(R.id.replacement_car_banner_view)
    MyCarRecommendCarBannerView mRecommendCarBannerView;
    @Bind(R.id.btn_add_focus_car)
    View mAddFocusCarBtn;
    @Bind(R.id.focus_car_list_view)
    InScrollExpandableListView mFocusCarListView;
    @Bind(R.id.car_replace_recommend_textview)
    TextView mCarReplaceRecommendTextView;

    private List<FocusCarData> mRecommendFocusCarDataList;
    private List<FocusCarData> mFocusCarDataList;
    private ExpandableFocusCarAdapter mFocusCarAdapter;

    public MyFocuseCarView(Context context) {
        super(context);
        initViews();
    }

    public MyFocuseCarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    public MyFocuseCarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    public List<FocusCarData> getRecommendFocusCarData(){
        return mRecommendFocusCarDataList;
    }

    public void setRecommendFocusCarData(List<FocusCarData> carDataList) {
        mRecommendFocusCarDataList = carDataList;
        mFocusCarListContainer.setVisibility(View.GONE);
        mFocusCarEmptyContainer.setVisibility(View.VISIBLE);
        if (mRecommendFocusCarDataList != null && mRecommendFocusCarDataList.size() > 0){
            mRecommendCarBannerView.setVisibility(View.VISIBLE);
            int columnNum = 3;
            List<List<FocusCarData>> carDataListInBanner = new ArrayList<List<FocusCarData>>();
            List<FocusCarData> carDataListInPage = null;
            for (int i = 0; i < mRecommendFocusCarDataList.size(); i += columnNum){
                carDataListInPage = new ArrayList<FocusCarData>();
                if (i + columnNum < mRecommendFocusCarDataList.size()) {
                    carDataListInPage.addAll(carDataList.subList(i, i + columnNum));
                }else{
                    carDataListInPage.addAll(carDataList.subList(i, mRecommendFocusCarDataList.size()));
                }
                carDataListInBanner.add(carDataListInPage);
            }
            if (mRecommendCarBannerView != null) {
                mRecommendCarBannerView.stopShow();
                mRecommendCarBannerView.setData(carDataListInBanner);
                mRecommendCarBannerView.startShow();
            }
        }else{
            mRecommendCarBannerView.setVisibility(View.GONE);
        }
    }

    public void setFocusCarData(List<FocusCarData> carDataList) {
        mFocusCarDataList = carDataList;
        if (mFocusCarDataList != null && mFocusCarDataList.size() > 0){
            mFocusCarListContainer.setVisibility(View.VISIBLE);
            mFocusCarEmptyContainer.setVisibility(View.GONE);
            if (mFocusCarAdapter != null) {
                mFocusCarAdapter.setData(mFocusCarDataList);
            }
        }else{
            mFocusCarListContainer.setVisibility(View.GONE);
            mFocusCarEmptyContainer.setVisibility(View.VISIBLE);
            mCarReplaceRecommendTextView.setText("");
        }
    }

    private void updateViews() {
    	
    }

    public void setCarReplaceRecommendText(String text){
        mCarReplaceRecommendTextView.setText(text);
    }

    private void initViews() {
    	LayoutInflater inflater = LayoutInflater.from(getContext());
    	View rootView = inflater.inflate(R.layout.view_carmanager_focus_car_layout, this);
        ButterKnife.bind(this);
        mFocusCarListView.setFocusable(false);
        mFocusCarListView.setFocusableInTouchMode(false);
        mFocusCarAdapter = new ExpandableFocusCarAdapter(this.getContext());
        mFocusCarListView.setAdapter(mFocusCarAdapter);
        mFocusCarListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                FocusCarData carData = (FocusCarData) mFocusCarAdapter.getGroup(groupPosition);
                if (carData != null){
                }
                return true;
            }
        });
    }

    @Override
    @OnClick({R.id.btn_focus_car_manager, R.id.btn_add_focus_car})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_focus_car_manager:
                handleFocusCarManagerClicked();
                break;
            case R.id.btn_add_focus_car:
                handleAddFocusCarClicked();
                break;
        }
    }

    private void handleFocusCarManagerClicked(){
        ViewUtility.navigateToMyFocuseCarListActivity(this.getContext());
    }

    private void handleAddFocusCarClicked(){
        ViewUtility.navigateToMyFocuseCarListActivity(this.getContext());
    }

}
