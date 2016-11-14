package com.jzg.jzgoto.phone.activity.carmanager;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.activity.shared.BaseActivity;
import com.jzg.jzgoto.phone.adapter.carmanager.CarLicensePlaceAdpater;
import com.jzg.jzgoto.phone.event.RefreshMyCarEvent;
import com.jzg.jzgoto.phone.global.AppContext;
import com.jzg.jzgoto.phone.global.GlobalData;
import com.jzg.jzgoto.phone.models.business.carmanager.CarManagerMyCarData;
import com.jzg.jzgoto.phone.models.business.carmanager.ProvinceSummaryData;
import com.jzg.jzgoto.phone.models.business.carmanager.RequestCarManagerGetMyCarDetailParams;
import com.jzg.jzgoto.phone.models.business.carmanager.RequestCarManagerGetMyCarDetailResult;
import com.jzg.jzgoto.phone.models.business.carmanager.RequestCarManagerGetProvinceListParams;
import com.jzg.jzgoto.phone.models.business.carmanager.RequestCarManagerGetProvinceListResult;
import com.jzg.jzgoto.phone.models.business.carmanager.RequestCarManagerModifyMyCarDetailParams;
import com.jzg.jzgoto.phone.models.business.carmanager.RequestCarManagerModifyMyCarDetailResult;
import com.jzg.jzgoto.phone.models.business.carmanager.RequestCarManagerUploadImageParams;
import com.jzg.jzgoto.phone.models.business.carmanager.RequestCarManagerUploadImageResult;
import com.jzg.jzgoto.phone.models.common.CommonModelSettings;
import com.jzg.jzgoto.phone.services.business.carmanager.CarManagerService;
import com.jzg.jzgoto.phone.services.business.carmanager.ImageUploadService;
import com.jzg.jzgoto.phone.tools.ActionSheet;
import com.jzg.jzgoto.phone.tools.DialogUtils;
import com.jzg.jzgoto.phone.tools.FileUtil;
import com.jzg.jzgoto.phone.tools.ImageRender;
import com.jzg.jzgoto.phone.tools.ToastManager;
import com.jzg.jzgoto.phone.view.common.CustomDialog;
import com.jzg.jzgoto.phone.view.common.PopupListWindowManager;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;


public class CompleteCarInfoActivity extends BaseActivity implements View.OnClickListener,
        ActionSheet.OnActionSheetListener{
    public static final String PARAM_EXTRA_CAR_DATA = "cardata";

    private static final int REQUEST_CODE_GET_MY_CAR_DETAIL = 1;
    private static final int REQUEST_CODE_MODIFY_MY_CAR_DETAIL = 2;
    private static final int REQUEST_CODE_UPLOAD_INSURANCE_IMAGE = 3;
    private static final int REQUEST_CODE_GET_PROVINCE_LIST = 4;

    private static final int ACTION_GET_FROM_PHOTO_ALBUM = 0x3001;
    private static final int ACTION_GET_FROM_CAMERA = 0x3002;

    @Bind(R.id.btn_save)
    View mSaveCarBtn;
    @Bind(R.id.view_car_number_type_textview)
    TextView mCarNumberRegionTextView;
    @Bind(R.id.view_car_complete_info_tip_textview)
    TextView mCompleteTipTextView;
    @Bind(R.id.view_car_number_editor)
    EditText mCarNumberEditor;
    @Bind(R.id.view_car_engine_number_editor)
    EditText mCarEngineNumberEditor;
    @Bind(R.id.view_car_body_number_editor)
    EditText mCarBodyNumberEditor;

    @Bind(R.id.view_commercial_insurance_image_container)
    View mCommercialInsuranceContainer;
    @Bind(R.id.view_commercial_insurance_imageview)
    ImageView mCommercialInsuranceImageView;
    @Bind(R.id.btn_add_commercial_insurance_image)
    ImageView mCommercialInsuranceAddBtnView;
    @Bind(R.id.view_traffic_insurance_image_container)
    View mTrafficInsuranceContainer;
    @Bind(R.id.view_traffic_insurance_imageview)
    ImageView mTrafficInsuranceImageView;
    @Bind(R.id.btn_add_traffic_insurance_image)
    ImageView mTrafficInsuranceAddBtnView;
    @Bind(R.id.view_audit_rule_textview)
    TextView mAuditRuleView;

    private ImageView mTargetImageView;
    private String mCommercialImagePath = "";
    private String mTrafficImagePath = "";
    private String mCarNumber;
    private String mCarEngineNumber;
    private String mCarBodyNumber;
    private String mProvinceShortName;
    private CarManagerMyCarData mCarData;
    private CarManagerService mDataService;
    private RequestCarManagerGetMyCarDetailParams mRequestParams;
    private RequestCarManagerGetMyCarDetailResult mRequestResult;
    private ImageUploadService mImageUploadService;
    private File mCameraFile;
    private List<ProvinceSummaryData> mProvinceList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        mCarData = (CarManagerMyCarData) intent.getSerializableExtra(PARAM_EXTRA_CAR_DATA);
        mProvinceList = GlobalData.getInstance().getProvinceList();
        setContentView(R.layout.activity_complete_car_info_layout);
        ButterKnife.bind(this);
        initViews();
        updateViews();

        mDataService = new CarManagerService(this, this);
        mRequestParams = new RequestCarManagerGetMyCarDetailParams();
        mImageUploadService = new ImageUploadService(this, this);
        requestMyCarData();
        if (mProvinceList == null || mProvinceList.size() == 0){
            requestProvinceData();
        }
    }

    private void initViews() {
        mCarNumberEditor.setOnEditorActionListener(mEditorActionListener);
        mCarNumberEditor.addTextChangedListener(mEditorTexWatcher);
        mCarEngineNumberEditor.setOnEditorActionListener(mEditorActionListener);
        mCarEngineNumberEditor.addTextChangedListener(mEditorTexWatcher);
        mCarBodyNumberEditor.setOnEditorActionListener(mEditorActionListener);
        mCarBodyNumberEditor.addTextChangedListener(mEditorTexWatcher);
    }

    private void updateViews(){
        mCompleteTipTextView.setText(this.getResources().getString(R.string.car_manager_complete_car_info_tip_format, 500));
        if (mProvinceList != null && mProvinceList.size() > 0){
            mCarNumberRegionTextView.setText(mProvinceList.get(0).getSummaryName());
        }else{
            mCarNumberRegionTextView.setText("京");
        }
    }

    @Override
    public void onRequestSuccess(Message msg) {
        switch (msg.what) {
            case REQUEST_CODE_GET_MY_CAR_DETAIL:
                handleRequestGetMyCarDetailSuccess(msg);
                break;
            case REQUEST_CODE_MODIFY_MY_CAR_DETAIL:
                handleRequestModifyMyCarDetailSuccess(msg);
                break;
            case REQUEST_CODE_UPLOAD_INSURANCE_IMAGE:
                handleRequestUploadImageSuccess(msg);
                break;
            case REQUEST_CODE_GET_PROVINCE_LIST:
                handleRequestGetProvinceListSuccess(msg);
                break;
        }
    }

    @Override
    public void onRequestFault(Message msg) {
        switch (msg.what) {
            case REQUEST_CODE_GET_MY_CAR_DETAIL:
                handleRequestGetMyCarDetailFailed(msg);
                break;
            case REQUEST_CODE_MODIFY_MY_CAR_DETAIL:
                handleRequestModifyMyCarDetailFailed(msg);
                break;
            case REQUEST_CODE_UPLOAD_INSURANCE_IMAGE:
                handleRequestUploadImageFailed(msg);
                break;
            case REQUEST_CODE_GET_PROVINCE_LIST:
                handleRequestGetProvinceListFailed(msg);
                break;
        }
    }

    private void handleRequestGetMyCarDetailSuccess(Message msg){
        hideLoadingView();
        mRequestResult = (RequestCarManagerGetMyCarDetailResult)msg.obj;
        if (mRequestResult != null && mRequestResult.getStatus() == CommonModelSettings.STATUS_SUCCESS) {
            mCarNumberEditor.setText(mRequestResult.getCarNumber());
            if (TextUtils.isEmpty(mRequestResult.getProvince())){
                mCarNumberRegionTextView.setText("京");
            }else {
                mCarNumberRegionTextView.setText(mRequestResult.getProvince());
            }
            mCarEngineNumberEditor.setText(mRequestResult.getEngineNumber());
            mCarBodyNumberEditor.setText(mRequestResult.getBodyNumber());
            mTrafficImagePath = mRequestResult.getTrafficInsuranceImageUrl();
            mCommercialImagePath = mRequestResult.getCommercialInsuranceImageUrl();
            ImageRender.getInstance().setImage(mCommercialInsuranceImageView,
                    mRequestResult.getCommercialInsuranceImageUrl() == null ? null : mRequestResult.getCommercialInsuranceImageUrl());
            ImageRender.getInstance().setImage(mTrafficInsuranceImageView,
                    mRequestResult.getTrafficInsuranceImageUrl() == null ? null : mRequestResult.getTrafficInsuranceImageUrl());
        }
    }

    private void handleRequestGetMyCarDetailFailed(Message msg){
        showNetworkErrorView();
        ToastManager.getInstance().showToastCenter(this, R.string.error_noConnect);
    }

    private void handleRequestModifyMyCarDetailSuccess(Message msg){
        hideWaitingDialog();
        RequestCarManagerModifyMyCarDetailResult requestResult = (RequestCarManagerModifyMyCarDetailResult)msg.obj;
        if (requestResult != null && requestResult.getStatus() == CommonModelSettings.STATUS_SUCCESS) {
            EventBus.getDefault().post(new RefreshMyCarEvent());
            this.finish();
        }
    }

    private void handleRequestModifyMyCarDetailFailed(Message msg){
        hideWaitingDialog();
        ToastManager.getInstance().showToastCenter(this, R.string.main_tip_operate_failed);
    }

    private void handleRequestUploadImageSuccess(Message msg){
        hideWaitingDialog();
        RequestCarManagerUploadImageResult requestResult = (RequestCarManagerUploadImageResult)msg.obj;
        if (requestResult != null && requestResult.getStatus() == CommonModelSettings.STATUS_SUCCESS) {
            if (mTargetImageView == mCommercialInsuranceImageView) {
                mCommercialImagePath = requestResult.getImageUrl();
            }else if (mTargetImageView == mTrafficInsuranceImageView){
                mTrafficImagePath = requestResult.getImageUrl();
            }
        }
    }

    private void handleRequestUploadImageFailed(Message msg){
        hideWaitingDialog();
        ToastManager.getInstance().showToastCenter(this, R.string.main_tip_operate_failed);
    }

    private void handleRequestGetProvinceListSuccess(Message msg){
        RequestCarManagerGetProvinceListResult requestResult = (RequestCarManagerGetProvinceListResult)msg.obj;
        if (requestResult != null && requestResult.getStatus() == CommonModelSettings.STATUS_SUCCESS) {
            mProvinceList = requestResult.getProvinceDataList();
            GlobalData.getInstance().setProvinceList(mProvinceList);
            if (mProvinceList != null && mProvinceList.size() > 0){
                mCarNumberRegionTextView.setText(mProvinceList.get(0).getSummaryName());
            }
        }
    }

    private void handleRequestGetProvinceListFailed(Message msg){
    }

    @Override
    @OnClick({R.id.btn_save, R.id.view_car_number_type_textview, R.id.btn_add_commercial_insurance_image,
            R.id.btn_add_traffic_insurance_image, R.id.view_audit_rule_textview})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_save:
                handleSaveClicked();
                break;
            case R.id.view_car_number_type_textview:
                selectCarNumberType();
                break;
            case R.id.btn_add_commercial_insurance_image:
                selectPhotoImage(mCommercialInsuranceImageView);
                break;
            case R.id.btn_add_traffic_insurance_image:
                selectPhotoImage(mTrafficInsuranceImageView);
                break;
            case R.id.view_audit_rule_textview:
                handleAuditRuleClicked();
                break;
        }
    }

    private void handleSaveClicked(){
        requestModifyMyCarData();
    }

    private void handleAuditRuleClicked(){
        CustomDialog dlg = DialogUtils.dialogSingleButtonMessage(this, R.string.car_manager_complete_car_info_audit_rule_title,
                R.string.car_manager_complete_car_info_audit_rule_content, null);
        dlg.setNegativeButtonText("关闭");
    }

    private void selectCarNumberType(){
        if (mProvinceList == null || mProvinceList.size() == 0){
            return;
        }
        CarLicensePlaceAdpater licensePlaceAdpater = new CarLicensePlaceAdpater(this);
        licensePlaceAdpater.setDataList(mProvinceList);
        PopupListWindowManager.getInstance().prepare(this);
        PopupListWindowManager.getInstance().setAdapter(licensePlaceAdpater);
        PopupListWindowManager.getInstance().showPopupListWindow(mCarNumberRegionTextView);
        PopupListWindowManager.getInstance().getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                PopupListWindowManager.getInstance().hidePopupListWindow();
                ProvinceSummaryData provinceData = mProvinceList.get(position);
                mCarNumberRegionTextView.setText(provinceData.getSummaryName());
            }
        });
    }

    private void selectPhotoImage(ImageView targetImageView){
        mTargetImageView = targetImageView;
        handleChangeInsuranceImage();
    }

    private EditText.OnEditorActionListener mEditorActionListener = new EditText.OnEditorActionListener() {

        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            switch (actionId) {
                case EditorInfo.IME_ACTION_DONE:
                    return true;
            }
            return false;
        }
    };

    private TextWatcher mEditorTexWatcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() == 0) {
                return;
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    private void onCompletePictureSelection(String path){
        ImageRender.getInstance().setImage(mTargetImageView, "file://" + path);
    }

    private void requestMyCarData(){
        showLoadingView();
        mRequestParams.uid = "0";//test code
        mRequestParams.ButlerId = GlobalData.getInstance().getCarManagerId();
        if (AppContext.isLogin()) {
            mRequestParams.uid = AppContext.mLoginResultModels.getId();
        }
        mRequestParams.MyCarId = String.valueOf(mCarData.getCarId());
        mDataService.toRequestGetMyCarDetailInfo(mRequestParams, RequestCarManagerGetMyCarDetailResult.class, REQUEST_CODE_GET_MY_CAR_DETAIL);

    }

    private void requestProvinceData(){
        RequestCarManagerGetProvinceListParams requestParams = new RequestCarManagerGetProvinceListParams();
        mDataService.toRequestProvinceSummaryNameList(requestParams, RequestCarManagerGetProvinceListResult.class, REQUEST_CODE_GET_PROVINCE_LIST);
    }

    private void requestModifyMyCarData(){
        prepareCarDetailData();
        showLoadingView();
        RequestCarManagerModifyMyCarDetailParams requestParams = new RequestCarManagerModifyMyCarDetailParams();
        requestParams.uid = "0";
        requestParams.ButlerId = GlobalData.getInstance().getCarManagerId();
        if (AppContext.isLogin()) {
            requestParams.uid = AppContext.mLoginResultModels.getId();
        }
        requestParams.MyCarId = String.valueOf(mCarData.getCarId());
        requestParams.carNumber = mCarNumber;
        requestParams.EngineNumber = mCarEngineNumber;
        requestParams.Vin = mCarBodyNumber;
        requestParams.ProvShort = mProvinceShortName;
        requestParams.JQXianPath = mTrafficImagePath;
        requestParams.SYXianPath = mCommercialImagePath;
        mDataService.toRequestModifyMyCarDetailInfo(requestParams, RequestCarManagerModifyMyCarDetailResult.class, REQUEST_CODE_MODIFY_MY_CAR_DETAIL);
        showWaitingDialog(getResources().getString(R.string.main_tip_operate_waiting));
    }

    private void prepareCarDetailData(){
        mCarNumber = mCarNumberEditor.getText().toString().trim();
        mCarEngineNumber = mCarEngineNumberEditor.getText().toString().trim();
        mCarBodyNumber = mCarBodyNumberEditor.getText().toString().trim();
        mProvinceShortName = mCarNumberRegionTextView.getText().toString();
        if (TextUtils.isEmpty(mTrafficImagePath)){
            mTrafficImagePath = "";
        }
        if (TextUtils.isEmpty(mCommercialImagePath)){
            mCommercialImagePath = "";
        }
    }

    private void handleChangeInsuranceImage(){
        ActionSheet.showSheet(this, this, "相机", "相册中选择", "取消",
                ACTION_GET_FROM_CAMERA, ACTION_GET_FROM_PHOTO_ALBUM, true);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK){
            return;
        }

        switch (requestCode) {
            case ACTION_GET_FROM_PHOTO_ALBUM:
                if (data != null) {
                    Uri selectedImage = data.getData();
                    if (selectedImage != null) {
                        selectPicByUri(selectedImage);
                    }
                }
                break;
            case ACTION_GET_FROM_CAMERA:
                if (mCameraFile != null && mCameraFile.exists()) {
                    toZipAllPic(mCameraFile.getAbsolutePath());
                }
                break;
        }
    }

    public void onActionClicked(int whichButton){
        switch (whichButton) {
            // 相册选取
            case ACTION_GET_FROM_PHOTO_ALBUM:
                Intent intent;
                if (Build.VERSION.SDK_INT < 19) {
                    intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                } else {
                    intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                }
                startActivityForResult(intent, ACTION_GET_FROM_PHOTO_ALBUM);
                break;
            // 拍照上传
            case ACTION_GET_FROM_CAMERA:
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                mCameraFile = FileUtil.getTempImageFile(this, ".jpg");
                Uri imageUri = Uri.fromFile(mCameraFile);
                // 指定照片保存路径（SD卡），workupload.jpg为一个临时文件，每次拍照后这个图片都会被替换
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                cameraIntent.putExtra(MediaStore.Images.Media.ORIENTATION, 90);
                startActivityForResult(cameraIntent, ACTION_GET_FROM_CAMERA);
                break;
            default:
                break;
        }
    }

    public void onActionCancel(){
    }

    private void selectPicByUri(Uri selectedImage) {
        String picturePath = null;
        Cursor cursor = getContentResolver().query(selectedImage, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex("_data");
            picturePath = cursor.getString(columnIndex);
            cursor.close();
            cursor = null;
        } else {
            picturePath = selectedImage.getPath();
        }

        if (TextUtils.isEmpty(picturePath) || picturePath.equals("null")) {
            ToastManager.getInstance().showToastCenter(this, R.string.album_photo_pic_can_not_found);
            return;
        }
        File ff = new File(picturePath);
        if( ff.exists() && ff.canRead() ){
            toZipAllPic(picturePath);
        } else {
            ToastManager.getInstance().showToastCenter(this, R.string.album_photo_pic_can_not_found);
        }
    }

    private void toZipAllPic(String path){
        final Context context = this;
        new AsyncTask<String, Integer, String>(){
            protected void onPreExecute() {
                showWaitingDialog(getResources().getString(R.string.main_tip_operate_waiting));
            };
            @Override
            protected String doInBackground(String... params) {
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                Bitmap bitmap = decodeThumbBitmapForFile(params[0],700,600);

                int degree  = 0;
                try {
                    ExifInterface exifInterface = new ExifInterface(params[0]);
                    int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
                    switch (orientation) {
                        case ExifInterface.ORIENTATION_ROTATE_90:
                            degree = 90;
                            break;
                        case ExifInterface.ORIENTATION_ROTATE_180:
                            degree = 180;
                            break;
                        case ExifInterface.ORIENTATION_ROTATE_270:
                            degree = 270;
                            break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(degree != 0){
                    Matrix matrix = new Matrix();
                    matrix.postRotate(+degree); /*翻转90度*/
                    int width = bitmap.getWidth();
                    int height =bitmap.getHeight();
                    bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
                }

                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArray);
                long bitLength = byteArray.toByteArray().length/1024;
                int qulity = 80;

                if(bitLength > 50l ){
                    while(byteArray.toByteArray().length/1024 > 50l){
                        qulity = qulity-10;
                        if(qulity < 0){
                            qulity = 10;
                            break;
                        }
                        byteArray.reset();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, qulity, byteArray);
                    }
                }
                try {
                    byteArray.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                File newFile = FileUtil.getTempImageFile(context, ".jpg");

                if( newFile.exists()){
                    newFile.delete();
                }
                try {
                    newFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                FileOutputStream fileOutput = null;
                try {
                    fileOutput = new FileOutputStream(newFile);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                bitmap.compress(Bitmap.CompressFormat.JPEG, qulity, fileOutput);
                try {
                    fileOutput.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    fileOutput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(bitmap != null){
                    bitmap.recycle();
                    bitmap = null;
                }
                return newFile.getPath();
            }
            protected void onPostExecute(String result) {
                hideWaitingDialog();
                //  TODO  更新显示
                if(TextUtils.isEmpty(result))return;
                startToUploadPic(result);
            };
        }.execute(path);
    }

    private Bitmap decodeThumbBitmapForFile(String path,int width,int height){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        options.inSampleSize = computeScale(options, width, height);
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(path, options);
    }
    private int computeScale(BitmapFactory.Options options,int width,int height){
        int inSampleSize = 1;
        if(width == 0 || height == 0){
            return inSampleSize;
        }
        int bitmapWidth = options.outWidth;
        int bitmapHeight = options.outHeight;
        if(bitmapWidth > width || bitmapHeight > height){
            int widthScale = Math.round((float)bitmapWidth/(float)width);
            int heightScale = Math.round((float)bitmapHeight/(float)height);
            inSampleSize = widthScale<heightScale?widthScale:heightScale;
        }
        return inSampleSize;
    }

    private void startToUploadPic(String filePath){
        onCompletePictureSelection(filePath);
        mImageUploadService = new ImageUploadService(this, this);
        RequestCarManagerUploadImageParams requestParams = new RequestCarManagerUploadImageParams();
        requestParams.Imgpath = filePath;
        mImageUploadService.uploadInsuranceImage(requestParams, RequestCarManagerUploadImageResult.class, REQUEST_CODE_UPLOAD_INSURANCE_IMAGE);
        showWaitingDialog(getResources().getString(R.string.main_tip_operate_waiting));
    }

}
