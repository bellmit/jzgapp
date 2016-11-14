package com.jzg.jzgoto.phone.view.carmanager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.event.DeleteMyFocusCarEvent;
import com.jzg.jzgoto.phone.models.business.carmanager.FocusCarData;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;


public class MyFocusCarListItemView extends LinearLayout implements View.OnClickListener {

    @Bind(R.id.view_car_name_text)
    TextView mCarNameTextView;
    @Bind(R.id.view_drag_imageview)
    ImageView mDragImageView;
    @Bind(R.id.view_delete_imageview)
    ImageView mDeleteImageView;
    @Bind(R.id.view_notice_imageview)
    ImageView mNoticeImageView;
    @Bind(R.id.view_item_divider)
    View mItemDividerView;
    @Bind(R.id.ll_lineaer)
    LinearLayout llLineaer;

    private FocusCarData mCarData;

    public ImageView getmDragImageView() {
        return mDragImageView;
    }

    public MyFocusCarListItemView(Context context) {
        super(context);
        initViews();
    }

    public LinearLayout getLlLineaer() {
        return llLineaer;
    }

    public MyFocusCarListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    public MyFocusCarListItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        updateViews();
    }

    public void setData(FocusCarData carData) {
        mCarData = carData;
        updateViews();
    }

    private void updateViews() {
        if (mCarData != null) {
            mCarNameTextView.setText(mCarData.getMkMdName());
        }
    }

    private void initViews() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View rootView = inflater.inflate(R.layout.view_carmanager_myfocuscar_item_view, this);
        ButterKnife.bind(this);
    }

    public void setDivider(boolean isLast) {
        //mItemDividerView.setVisibility(isLast ? View.VISIBLE : View.GONE);
    }

    @Override
    @OnClick({ R.id.view_delete_imageview, R.id.view_notice_imageview})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.view_delete_imageview:
                handleDeleteClicked();
                break;
            case R.id.view_notice_imageview:
                handleNoticeClicked();
                break;
        }
    }

//    private void handleDragClicked() {
//
//    }

    private void handleDeleteClicked() {
        EventBus.getDefault().post(DeleteMyFocusCarEvent.build(mCarData));
    }

    private void handleNoticeClicked() {

    }

}
