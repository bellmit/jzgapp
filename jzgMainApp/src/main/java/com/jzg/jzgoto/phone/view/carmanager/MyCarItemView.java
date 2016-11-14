package com.jzg.jzgoto.phone.view.carmanager;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.event.ModifyMyCarImageEvent;
import com.jzg.jzgoto.phone.models.business.carmanager.CarManagerMyCarData;
import com.jzg.jzgoto.phone.tools.DisplayUtils;
import com.jzg.jzgoto.phone.tools.ImageRender;
import com.jzg.jzgoto.phone.view.ViewUtility;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;


public class MyCarItemView extends LinearLayout implements View.OnClickListener {

    @Bind(R.id.view_car_maker_name_text)
    TextView mCarMakerNameTextView;
    @Bind(R.id.view_car_model_name_text)
    TextView mCarModelNameTextView;
    @Bind(R.id.view_car_license_add_container)
    View mCarLicenseAddContainer;
    @Bind(R.id.view_car_license_container)
    View mCarLicenseContainer;
    @Bind(R.id.view_car_license_text)
    TextView mCarLicenseTextView;
    @Bind(R.id.view_car_license_modify_btn)
    View mCarLicenseModifyBtn;
    @Bind(R.id.view_item_divider)
    View mItemDividerView;

    private ImageView mCarImageView;
    private CarManagerMyCarData mCarData;


    public MyCarItemView(Context context) {
        super(context);
        initViews();
    }

    public MyCarItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    public MyCarItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        updateViews();
    }

    public void setData(CarManagerMyCarData carData) {
        mCarData = carData;
        updateViews();
    }

    private void updateViews() {
    	if (mCarData != null){
//            ImageRender.getInstance().setImage(mCarImageView,
//                    mCarData.getImageUrl() == null ? null :  mCarData.getImageUrl(), R.drawable.jingzhengu_moren);
            mCarMakerNameTextView.setText(mCarData.getMakerModelName());
            mCarModelNameTextView.setText("");
            if (TextUtils.isEmpty(mCarData.getCarLicenseNo())){
                mCarLicenseAddContainer.setVisibility(View.VISIBLE);
                mCarLicenseContainer.setVisibility(View.GONE);
            }else{
                mCarLicenseAddContainer.setVisibility(View.GONE);
                mCarLicenseContainer.setVisibility(View.VISIBLE);
                mCarLicenseTextView.setText(mCarData.getCarLicenseNo());
            }
            ImageRender.getInstance().setImage(this.mCarImageView, mCarData.getImageUrl() == null ? null :  mCarData.getImageUrl(),
                    R.drawable.jingzhengu_moren, 0, true, new ImageLoadingListener() {
                        @Override
                        public void onLoadingStarted(String arg0, View arg1) {
                        }

                        @Override
                        public void onLoadingFailed(String arg0, View arg1,
                                                    FailReason arg2) {
                        }

                        @Override
                        public void onLoadingComplete(String arg0, View arg1,
                                                      Bitmap loadedImage) {
                            // TODO Auto-generated method stub
                            Context context = MyCarItemView.this.getContext();
                            Bitmap croppedBitmap = null;
                            croppedBitmap = ThumbnailUtils.extractThumbnail(loadedImage, DisplayUtils.dpToPx(context, 120), DisplayUtils.dpToPx(context, 80));
                            mCarImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            mCarImageView.setAdjustViewBounds(true);
                            mCarImageView.setImageBitmap(croppedBitmap);
                        }

                        @Override
                        public void onLoadingCancelled(String arg0, View arg1) {
                        }
                    });
        }
    }

    private void initViews() {
    	LayoutInflater inflater = LayoutInflater.from(getContext());
    	View rootView = inflater.inflate(R.layout.view_carmanager_mycar_item_view, this);
        mCarImageView = (ImageView)rootView.findViewById(R.id.view_car_imageview);
        ButterKnife.bind(this);
    }

    public void setDivider(boolean isLast){
        //mItemDividerView.setVisibility(isLast ? View.VISIBLE : View.GONE);
    }

    @Override
    @OnClick({R.id.view_car_license_add_container, R.id.view_car_license_modify_btn, R.id.view_car_imageview,
                R.id.view_change_car_image})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.view_car_license_add_container:
                handleLicenseAddClicked();
                break;
            case R.id.view_car_license_modify_btn:
                handleLicenseModifyClicked();
                break;
            case R.id.view_car_imageview:
            case R.id.view_change_car_image:
                handleChangeCarImage();
                break;
        }
    }

    private void handleLicenseAddClicked(){
        ViewUtility.navigateToCompleteCarInfoActivity(this.getContext(), mCarData);
    }

    private void handleLicenseModifyClicked(){
        ViewUtility.navigateToCompleteCarInfoActivity(this.getContext(), mCarData);
    }

    private void handleChangeCarImage(){
        EventBus.getDefault().post(ModifyMyCarImageEvent.build(mCarData, this.getContext()));
    }
}
