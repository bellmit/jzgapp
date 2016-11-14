package com.jzg.jzgoto.phone.view.carmanager;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.models.RecommendCardData;
import com.jzg.jzgoto.phone.tools.ImageRender;
import com.jzg.jzgoto.phone.view.ViewUtility;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MyCarFocusCarGroupChildView extends RelativeLayout implements View.OnClickListener{

    private RecommendCardData mCarData;

    @Bind(R.id.view_car_imageview)
    ImageView mCarImageView;
    @Bind(R.id.view_title_text)
    TextView mTitleTextView;
    @Bind(R.id.view_description_text)
    TextView mDescriptionTextView;
    @Bind(R.id.view_item_divider)
    View mItemDividerView;

    public MyCarFocusCarGroupChildView(Context context) {
        super(context);
        initViews();
    }

    public MyCarFocusCarGroupChildView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    private void initViews() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View rootView = inflater.inflate(R.layout.view_carmanager_focus_car_group_child_view_layout, this);
        ButterKnife.bind(this);
    }

    private void updateViews() {
        if (mCarData != null) {
//            ImageLoader.getInstance().getDiskCache().clear();
            ImageLoader.getInstance().getMemoryCache().clear();
            if(TextUtils.isEmpty(mCarData.getImageUrl())){
                ImageLoader.getInstance().displayImage("drawable://"+ R.drawable.jingzhengu_moren, mCarImageView);
            }else{
                ImageRender.getInstance().setImage(mCarImageView, mCarData.getImageUrl(), R.drawable.jingzhengu_moren);
            }
            Log.e("imageurl",mCarData.getImageUrl());

            mTitleTextView.setText(mCarData.getTile());
            mDescriptionTextView.setText(mCarData.getDescription());
        }
    }

    public void setViewHeight(int height){
        ViewGroup.LayoutParams lp = (ViewGroup.LayoutParams) this.getLayoutParams();
        lp.height = height;
        this.setLayoutParams(lp);
    }

    public void setDivider(boolean isLast){
        mItemDividerView.setVisibility(isLast ? View.VISIBLE : View.GONE);
    }

    public void setData(RecommendCardData data){
        if (data == null) {
            return;
        }
        mCarData = data;
        updateViews();
    }

    @Override
    @OnClick({R.id.view_car_imageview, R.id.view_item_container})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.view_item_container:
            case R.id.view_car_imageview:
                skepToWebView();
                break;
        }
    }

    private void skepToWebView(){
        if (mCarData != null && !TextUtils.isEmpty(mCarData.getWebUrl())) {
            ViewUtility.navigateToWebView(this.getContext(), "", mCarData.getWebUrl());
        }
    }

}
