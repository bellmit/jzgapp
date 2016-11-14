package com.jzg.jzgoto.phone.view.carmanager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.event.AddMyFocusCarEvent;
import com.jzg.jzgoto.phone.models.business.carmanager.FocusCarData;
import com.jzg.jzgoto.phone.tools.ImageRender;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;


public class MyCarRecommendCarItemView extends RelativeLayout implements View.OnClickListener{

    private FocusCarData mCarData;

    @Bind(R.id.view_car_maker_name_text)
    TextView mCarMakerNameTextView;
    @Bind(R.id.view_car_model_name_text)
    TextView mCarModelNameTextView;
    @Bind(R.id.view_car_imageview)
    ImageView mCarImageView;
    @Bind(R.id.view_car_add_focus_btn)
    View mAddFocusCarView;

    public MyCarRecommendCarItemView(Context context) {
        super(context);
        initViews();
    }

    public MyCarRecommendCarItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //initViews();
    }

    private void initViews() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View rootView = inflater.inflate(R.layout.view_carmanager_recommend_car_item_view_layout, this);
        ButterKnife.bind(this);
    }

    private void updateViews() {
        if (mCarData != null) {
            ImageRender.getInstance().setImage(mCarImageView, mCarData.getImageUrl(), R.drawable.touxiang);
            mCarMakerNameTextView.setText(mCarData.getMakeModelName());
            mCarModelNameTextView.setText("");
        }
    }

    public void setCardHeight(int height){
        ViewGroup.LayoutParams lp = (ViewGroup.LayoutParams) this.getLayoutParams();
        lp.height = height;
        this.setLayoutParams(lp);
    }

    public void setData(FocusCarData data){
        if (data == null) {
            return;
        }
        mCarData = data;
        updateViews();
    }

    public FocusCarData getData(){
        return mCarData;
    }

    @Override
    @OnClick({R.id.view_car_add_focus_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.view_car_add_focus_btn:
                handleAddFocusCarClicked();
                break;
        }
    }

    private void handleAddFocusCarClicked(){
        EventBus.getDefault().post(AddMyFocusCarEvent.build(mCarData));
    }

}
