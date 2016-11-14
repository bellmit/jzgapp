package com.jzg.jzgoto.phone.view.carmanager;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.global.GlobalData;
import com.jzg.jzgoto.phone.models.business.carmanager.CarManagerMyCarData;
import com.jzg.jzgoto.phone.models.business.carmanager.FocusCarData;
import com.jzg.jzgoto.phone.models.business.sell.ReplaceNewCarIntentModel;
import com.jzg.jzgoto.phone.view.ViewUtility;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MyCarFocusCarGroupView extends RelativeLayout implements View.OnClickListener{

    private FocusCarData mCarData;

    @Bind(R.id.view_car_maker_name_text)
    TextView mCarMakerNameTextView;
    @Bind(R.id.view_car_model_name_text)
    TextView mCarModelNameTextView;
    @Bind(R.id.view_expand_suggestion_info_container)
    View mExpandedSuggestionInfoContainer;
    @Bind(R.id.view_suggestion_info_text)
    TextView mSuggestionTextView;
    @Bind(R.id.view_car_arrow_imageview)
    ImageView mArrowImageView;

    private boolean mIsExpanded = false;

    public MyCarFocusCarGroupView(Context context) {
        super(context);
        initViews();
    }

    public MyCarFocusCarGroupView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    private void initViews() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View rootView = inflater.inflate(R.layout.view_carmanager_focus_car_group_view_layout, this);
        ButterKnife.bind(this);
    }

    private void updateViews() {
        if (mCarData != null) {
            mCarMakerNameTextView.setText(mCarData.getMkMdName());
            mCarModelNameTextView.setText("");
        }
    }

    public void setData(FocusCarData data){
        if (data == null) {
            return;
        }
        mCarData = data;
        updateViews();
    }

    public void setExpand(boolean isExpanded){
        mIsExpanded = isExpanded;
        mExpandedSuggestionInfoContainer.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        mArrowImageView.setImageResource(isExpanded ? R.drawable.arrow_up : R.drawable.arrow_down);
    }

    @Override
    @OnClick({R.id.view_expand_suggestion_info_container})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.view_expand_suggestion_info_container:
                skipToCarReplacement();
                break;
        }
    }

    private void skipToCarReplacement(){
        CarManagerMyCarData myCarData = GlobalData.getInstance().getMyCarData();
        if (myCarData != null){
            ReplaceNewCarIntentModel params = new ReplaceNewCarIntentModel();
            params.setMakeId(myCarData.getMakeId());
            params.setModelId(myCarData.getModelId());
            params.setStyleId(myCarData.getStyleId());
            params.setFullName(myCarData.getFullName());
            params.setMileage(myCarData.getMileage());
            params.setCityName(myCarData.getCityName());
            params.setCityId(myCarData.getCityId());
            String dateStr = myCarData.getRegisterDate();
            if (!TextUtils.isEmpty(dateStr)) {
                String[] dateArray = dateStr.split("-");
                if (dateArray.length > 1) {
                    params.setRegDate(dateArray[0] + "年" + dateArray[1] + "月");
                }
            }
            ViewUtility.navigateToReplaceNewCarActivity(this.getContext(), params);
        }else {
            ViewUtility.navigateToReplaceNewCarActivity(this.getContext());
        }
    }

}
