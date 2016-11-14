package com.jzg.jzgoto.phone.fragment.carmanager;


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
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.activity.common.ConstantForAct;
import com.jzg.jzgoto.phone.adapter.shared.RecommendListAdpater;
import com.jzg.jzgoto.phone.event.AddMyCarEvent;
import com.jzg.jzgoto.phone.event.AddMyFocusCarEvent;
import com.jzg.jzgoto.phone.event.LoginEvent;
import com.jzg.jzgoto.phone.event.LogoutEvent;
import com.jzg.jzgoto.phone.event.ModifyMyCarImageEvent;
import com.jzg.jzgoto.phone.event.ModifyMyCarMileageEvent;
import com.jzg.jzgoto.phone.event.NetworkStatusEvent;
import com.jzg.jzgoto.phone.event.RefreshMyCarEvent;
import com.jzg.jzgoto.phone.fragment.shared.BaseFragment;
import com.jzg.jzgoto.phone.global.AppContext;
import com.jzg.jzgoto.phone.global.GlobalData;
import com.jzg.jzgoto.phone.models.BannerData;
import com.jzg.jzgoto.phone.models.RecommendCardData;
import com.jzg.jzgoto.phone.models.RecommendCardList;
import com.jzg.jzgoto.phone.models.business.carmanager.CarManagerMyCarData;
import com.jzg.jzgoto.phone.models.business.carmanager.FocusCarData;
import com.jzg.jzgoto.phone.models.business.carmanager.RequestCarManagerAddFocusCarParams;
import com.jzg.jzgoto.phone.models.business.carmanager.RequestCarManagerAddFocusCarResult;
import com.jzg.jzgoto.phone.models.business.carmanager.RequestCarManagerMainDataParams;
import com.jzg.jzgoto.phone.models.business.carmanager.RequestCarManagerMainDataResult;
import com.jzg.jzgoto.phone.models.business.carmanager.RequestCarManagerModifyMyCarImageParams;
import com.jzg.jzgoto.phone.models.business.carmanager.RequestCarManagerModifyMyCarImageResult;
import com.jzg.jzgoto.phone.models.business.carmanager.RequestCarManagerModifyMyCarMileageParams;
import com.jzg.jzgoto.phone.models.business.carmanager.RequestCarManagerModifyMyCarMileageResult;
import com.jzg.jzgoto.phone.models.business.valuation.ValuationSellCarParams;
import com.jzg.jzgoto.phone.models.business.valuation.ValuationSellCarResult;
import com.jzg.jzgoto.phone.models.common.CommonModelSettings;
import com.jzg.jzgoto.phone.services.business.carmanager.CarManagerService;
import com.jzg.jzgoto.phone.services.business.carmanager.ImageUploadService;
import com.jzg.jzgoto.phone.services.business.valuation.ValuationService;
import com.jzg.jzgoto.phone.tools.ActionSheet;
import com.jzg.jzgoto.phone.tools.DialogUtils;
import com.jzg.jzgoto.phone.tools.FileUtil;
import com.jzg.jzgoto.phone.tools.NetworkUtils;
import com.jzg.jzgoto.phone.tools.StringUtil;
import com.jzg.jzgoto.phone.tools.ToastManager;
import com.jzg.jzgoto.phone.view.ViewUtility;
import com.jzg.jzgoto.phone.view.carmanager.MileageInputDialog;
import com.jzg.jzgoto.phone.view.carmanager.MyCarAssessmentView;
import com.jzg.jzgoto.phone.view.carmanager.MyCarManagerView;
import com.jzg.jzgoto.phone.view.carmanager.MyCarToolInfoView;
import com.jzg.jzgoto.phone.view.carmanager.MyFocuseCarView;
import com.jzg.jzgoto.phone.view.common.BannerViewBase;
import com.jzg.jzgoto.phone.view.common.CustomDialog;
import com.jzg.jzgoto.phone.view.common.InScrollListView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CarManagerFragment extends BaseFragment implements OnClickListener, BannerViewBase.BannerViewListener,
		ActionSheet.OnActionSheetListener{

	private static final int REQUEST_CODE_GET_MAIN_DATA = 1;
	private static final int REQUEST_CODE_MODIFY_MY_CAR_IMAGE = 2;
	private static final int REQUEST_CODE_MODIFY_MY_CAR_MILEAGE = 3;
	private static final int REQUEST_CODE_ADD_MY_FOCUS_CAR = 4;
	private static final int REQUEST_CODE_EVALUATE_CAR = 5;

	private static final int ACTION_GET_FROM_PHOTO_ALBUM = 0x3001;
	private static final int ACTION_GET_FROM_CAMERA = 0x3002;

	private AppContext mAppContext;

	@Bind(R.id.view_my_car_manager)
	MyCarManagerView mMyCarManagerView;
	@Bind(R.id.view_my_car_assessment)
	MyCarAssessmentView mCarAssessmentView;
	@Bind(R.id.view_my_car_tool_info)
	MyCarToolInfoView mCarToolInfoView;
	@Bind(R.id.view_my_focus_car)
	MyFocuseCarView mMyFocusCarView;
	@Bind(R.id.recommend_listview)
	InScrollListView mRecommendListView;

	private RecommendListAdpater mRecommendListAdapter;
	private List<RecommendCardList> mRecommendDataList = new ArrayList<RecommendCardList>();
	private List<CarManagerMyCarData> mMyCarDataList = new ArrayList<CarManagerMyCarData>();
	private List<FocusCarData> mMyFocusCarDataList = new ArrayList<FocusCarData>();
	private CarManagerService mDataService;
	private RequestCarManagerMainDataParams mRequestParams;
	private CarManagerMyCarData mCurrCarData;
	private int mCurrCarIndex;
	private File mCameraFile;
	private FocusCarData mFocusCarData;
	private String mMileageValue;
	private boolean mIsEvaluate;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		EventBus.getDefault().register(this);
		mAppContext = (AppContext) getActivity().getApplicationContext();
		mDataService = new CarManagerService(getActivity(), this);
		mRequestParams = new RequestCarManagerMainDataParams();

		//showConfirmCarManagerDefaultDialog();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_car_manager_layout, null);
		ButterKnife.bind(this, view);
		//View footerView = inflater.inflate(R.layout.view_home_footer_view, null);
		//mRecommendListView.addFooterView(footerView);
		initViews();
		requestMyCarManagerData();
		return view;
	}
	
	@Override
	public void onResume() {
		super.onResume();
	}

	public void onEvent(AddMyCarEvent event){
		if (event != null){
			requestMyCarManagerData();
		}
	}

	public void onEvent(RefreshMyCarEvent event){
		if (event != null){
			requestMyCarManagerData();
		}
	}

	public void onEvent(ModifyMyCarImageEvent event) {
		if (event != null && event.carInfo != null) {
			mCurrCarData = event.carInfo;
			if (this.getActivity() == event.activity) {
				handleChangeCarImage();
			}
		}
	}

	public void onEvent(AddMyFocusCarEvent event) {
		if (event != null && event.carInfo != null) {
			mFocusCarData = event.carInfo;
			requestAddMyFocusCar(mFocusCarData);
		}
	}

	public void onEvent(LoginEvent event) {
		if (event != null && event.isSuccess) {
			requestMyCarManagerData();
		}
	}

	public void onEvent(LogoutEvent event) {
		if (event != null && event.isSuccess) {
			requestMyCarManagerData();
		}
	}

	public void onEvent(ModifyMyCarMileageEvent event) {
		if (event != null && event.carInfo != null) {
			modifyMyCarMileage(event.carInfo, event.isEvaluate);
		}
	}

	public void onEvent(NetworkStatusEvent event){
		if (event != null && event.getConnected()){
			requestMyCarManagerData();
		}
	}

	@Override
	public void onRequestSuccess(Message msg) {
		switch (msg.what) {
			case REQUEST_CODE_GET_MAIN_DATA:
				handleRequestMainDataSuccess(msg);
				break;
			case REQUEST_CODE_MODIFY_MY_CAR_IMAGE:
				handleRequestModifyMyCarImageSuccess(msg);
				break;
			case REQUEST_CODE_MODIFY_MY_CAR_MILEAGE:
				handleRequestModifyMyCarMileageSuccess(msg);
				break;
			case REQUEST_CODE_ADD_MY_FOCUS_CAR:
				handleRequestAddFocusCarSuccess(msg);
				break;
			case REQUEST_CODE_EVALUATE_CAR:
				handleRequestEvaluateMyCarSuccess(msg);
				break;
		}
	}

	@Override
	public void onRequestFault(Message msg) {
		switch (msg.what) {
			case REQUEST_CODE_GET_MAIN_DATA:
				handleRequestMainDataFailed(msg);
				break;
			case REQUEST_CODE_MODIFY_MY_CAR_IMAGE:
				handleRequestModifyMyCarImageFailed(msg);
				break;
			case REQUEST_CODE_MODIFY_MY_CAR_MILEAGE:
				handleRequestModifyMyCarMileageFailed(msg);
				break;
			case REQUEST_CODE_ADD_MY_FOCUS_CAR:
				handleRequestAddFocusCarFailed(msg);
				break;
			case REQUEST_CODE_EVALUATE_CAR:
				handleRequestEvaluateMyCarFailed(msg);
				break;
		}
	}


	@Override
	@OnClick({R.id.sellcar_loan_apply_imageview})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.sellcar_loan_apply_imageview:
				handleCarLoanClicked();
				break;
		}
	}

	@Override
	public void onDestroy() {
		EventBus.getDefault().unregister(this);
		super.onDestroy();
	}

	private void handleCarLoanClicked(){
		ViewUtility.navigateToSellCarLoanActivity(this.getActivity());
	}

	public void handlePageChanged(int pageIndex){
		if (mMyCarDataList != null && mMyCarDataList.size() > pageIndex) {
			mCurrCarIndex = pageIndex;
			mCurrCarData = mMyCarDataList.get(pageIndex);
			mCarAssessmentView.setData(mCurrCarData);
			mCarToolInfoView.setData(mCurrCarData);
			if (mMyFocusCarDataList == null || mMyFocusCarDataList.size() == 0){
				mMyFocusCarView.setRecommendFocusCarData(mCurrCarData.getRecommendFocusCarList());
			}
			mMyFocusCarView.setCarReplaceRecommendText(getResources().getString(R.string.car_manager_replace_recommend_format, mCurrCarData.getCarModelName()));
			GlobalData.getInstance().setMyCarData(mCurrCarData);
		}else{
			mCurrCarIndex = 0;
			mCarAssessmentView.setData(null);
			mCarToolInfoView.setData(null);
			if (mMyFocusCarDataList == null || mMyFocusCarDataList.size() == 0) {
				mMyFocusCarView.setRecommendFocusCarData(null);
				mMyFocusCarView.setCarReplaceRecommendText("");
			}else{
				mMyFocusCarView.setFocusCarData(mMyFocusCarDataList);
			}
		}
	}

	private void initViews() {
		mRecommendListView.setFocusable(false);
		mRecommendListView.setFocusableInTouchMode(false);
		mRecommendListAdapter = new RecommendListAdpater(this.getActivity());
		mRecommendListView.setAdapter(mRecommendListAdapter);
		mMyCarManagerView.getBannerView().setBannerViewListener(this);
	}

	private void showConfirmCarManagerDefaultDialog(){
		final Context context = this.getActivity();
		boolean isQueried = ConstantForAct.getQueryShowCarManagerAsHome(context);
		boolean hasAddMyCar = ConstantForAct.getHasAddMyCar(context);
		if (!hasAddMyCar){
			return;
		}
		if (isQueried){
			return;
		}
		String buttonCancelText = "否";
		String buttonOKText = "是";
		String content = getResources().getString(R.string.car_manager_show_default_tip);
		DialogUtils.dialogMessage(this.getActivity(), "", content, buttonOKText, buttonCancelText,
				new CustomDialog.OnCustomDialogClickListener() {
					@Override
					public boolean onCustomDialogClick(CustomDialog.CustomDialogClickType type) {
						switch (type) {
							case Ok:
								ConstantForAct.setQueryShowCarManagerAsHome(context, true);
								ConstantForAct.setShowCarManagerAsHome(context, true);
								break;
							case Cancel:
								ConstantForAct.setQueryShowCarManagerAsHome(context, true);
								ConstantForAct.setShowCarManagerAsHome(context, false);
								break;
						}
						return false;
					}
				});
	}

	private void requestMyCarManagerData(){
		//execTest();
		if (NetworkUtils.isConnected(this.getActivity())) {
			showLoadingView();
			mRequestParams.uid = "0";
			mRequestParams.ButlerId = GlobalData.getInstance().getCarManagerId();
			mRequestParams.cityname = AppContext.cityName;
			if (AppContext.isLogin()) {
				mRequestParams.uid = AppContext.mLoginResultModels.getId();
			}
			mDataService.toRequestMainData(mRequestParams, RequestCarManagerMainDataResult.class, REQUEST_CODE_GET_MAIN_DATA);
		}else{
			mCurrCarIndex = 0;
			mCarAssessmentView.setData(null);
			mCarToolInfoView.setData(null);
			mMyCarManagerView.setVisibility(View.GONE);
			mMyFocusCarView.setFocusCarData(null);
			mMyFocusCarView.setRecommendFocusCarData(null);
		}
	}

	private void requestModifyMileage(CarManagerMyCarData carInfo, String mileage, boolean isEvaluate){
		RequestCarManagerModifyMyCarMileageParams requestParams = new RequestCarManagerModifyMyCarMileageParams();
		CarManagerService dataService = new CarManagerService(this.getActivity(), this);;
		requestParams.MyCarId = String.valueOf(carInfo.getCarId());
		requestParams.mileage = mileage;
		requestParams.isGuzhi = isEvaluate ? "1" : "0";
		dataService.toRequestModifyMyCarMileage(requestParams, RequestCarManagerModifyMyCarMileageResult.class, REQUEST_CODE_MODIFY_MY_CAR_MILEAGE);
		showWaitingDialog(this.getResources().getString(R.string.main_tip_operate_waiting));
	}

	private void requestAddMyFocusCar(FocusCarData focusCarData){
		if (focusCarData == null){
			return;
		}
		RequestCarManagerAddFocusCarParams requestParams = new RequestCarManagerAddFocusCarParams();
		requestParams.uid = "0";
		requestParams.ButlerId = GlobalData.getInstance().getCarManagerId();
		requestParams.MakeId = focusCarData.getMakeId();
		requestParams.ModelId = focusCarData.getModelId();
		if (AppContext.isLogin()) {
			requestParams.uid = AppContext.mLoginResultModels.getId();
		}
		mDataService.toRequestAddFocusCar(requestParams, RequestCarManagerAddFocusCarResult.class, REQUEST_CODE_ADD_MY_FOCUS_CAR);
		showWaitingDialog(this.getResources().getString(R.string.main_tip_operate_waiting));
	}

	private void requestCarEvaluate(CarManagerMyCarData carInfo){
		if (carInfo == null){
			return;
		}
		ValuationSellCarParams sellCarParams = new ValuationSellCarParams();
		ValuationService dataService = new ValuationService(this.getActivity(), this);;
		if (AppContext.isLogin()) {
			sellCarParams.uid = AppContext.mLoginResultModels.getId();
		}else{
			sellCarParams.uid = "0";
		}
		sellCarParams.styleid = carInfo.getStyleId();
		sellCarParams.regdate = carInfo.getRegisterDate();
		sellCarParams.mileage = carInfo.getMileage();
		sellCarParams.cityname = "";
		sellCarParams.CityId = carInfo.getCityId();
		dataService.toResuestValuationSellCar(sellCarParams, ValuationSellCarResult.class, REQUEST_CODE_EVALUATE_CAR);
	}

	public void setMyCarData(List<CarManagerMyCarData> carList) {
		mMyCarDataList.clear();
		mMyCarDataList.addAll(carList);
		mMyCarManagerView.setVisibility(View.VISIBLE);
		mMyCarManagerView.setMyCarData(carList);
		//mMyCarManagerView.setMyCarData(null);
		if (mMyCarDataList != null && mMyCarDataList.size() > 0) {
			ConstantForAct.setHasAddMyCar(this.getActivity(), true);
		}

	}

	public void setRecommendFocusCarData(List<FocusCarData> carDataList){
		mMyFocusCarView.setRecommendFocusCarData(carDataList);
	}

	public void setFocusCarData(List<FocusCarData> carDataList){
		mMyFocusCarView.setFocusCarData(carDataList);
	}

	private void handleRequestMainDataSuccess(Message msg){
		hideLoadingView();
		RequestCarManagerMainDataResult mainDataResult = (RequestCarManagerMainDataResult)msg.obj;
		if (mainDataResult != null && mainDataResult.getStatus() == CommonModelSettings.STATUS_SUCCESS) {
			setMyCarData(mainDataResult.getMyCarList());
			mRecommendDataList = mainDataResult.getRecommendCardList();
			mRecommendListAdapter.setRecommendDataList(mRecommendDataList);
			mMyFocusCarDataList = mainDataResult.getFocusCarList();
			if (mMyFocusCarDataList != null && mMyFocusCarDataList.size() > 0){
				mMyFocusCarView.setFocusCarData(mMyFocusCarDataList);
			}else{
				mMyFocusCarView.setFocusCarData(null);
			}
			handlePageChanged(mCurrCarIndex);
		}
	}

	private void handleRequestMainDataFailed(Message msg){
		showNetworkErrorView();
		ToastManager.getInstance().showToastCenter(this.getActivity(), R.string.error_noConnect);
	}

	private void handleRequestModifyMyCarImageSuccess(Message msg){
		hideWaitingDialog();
		RequestCarManagerModifyMyCarImageResult requestResult = (RequestCarManagerModifyMyCarImageResult)msg.obj;
		if (requestResult != null && requestResult.getStatus() == CommonModelSettings.STATUS_SUCCESS) {
			mCurrCarData.setImageUrl(requestResult.getImageUrl());
			int position = 0;
			position = mMyCarDataList.indexOf(mCurrCarData);
			mMyCarManagerView.getBannerView().setItemData(position, mCurrCarData);
		}
	}

	private void handleRequestModifyMyCarImageFailed(Message msg){
		hideWaitingDialog();
		ToastManager.getInstance().showToastCenter(this.getActivity(), R.string.main_tip_operate_failed);
	}

	private void handleRequestModifyMyCarMileageSuccess(Message msg){
		hideWaitingDialog();
		RequestCarManagerModifyMyCarMileageResult requestResult = (RequestCarManagerModifyMyCarMileageResult)msg.obj;
		if (requestResult != null && requestResult.getStatus() == CommonModelSettings.STATUS_SUCCESS) {
			if (mCurrCarData != null) {
				mCurrCarData.setMileage(mMileageValue);
				if (mIsEvaluate) {
					requestCarEvaluate(mCurrCarData);
				}
			}
		}
	}

	private void handleRequestModifyMyCarMileageFailed(Message msg){
		hideWaitingDialog();
		ToastManager.getInstance().showToastCenter(this.getActivity(), R.string.main_tip_operate_failed);
	}

	private void handleRequestAddFocusCarSuccess(Message msg){
		hideWaitingDialog();
		requestMyCarManagerData();
		ToastManager.getInstance().showToastCenter(this.getActivity(), R.string.car_manager_add_focus_success);
	}

	private void handleRequestEvaluateMyCarFailed(Message msg){
		hideWaitingDialog();
		ToastManager.getInstance().showToastCenter(this.getActivity(), R.string.main_tip_operate_failed);
	}

	private void handleRequestEvaluateMyCarSuccess(Message msg){
		hideWaitingDialog();
		ValuationSellCarResult requestResult = (ValuationSellCarResult) msg.obj;
		if (requestResult != null && requestResult.getStatus() == CommonModelSettings.STATUS_SUCCESS) {
			ViewUtility.navigateToValuationSellActivity(this.getActivity(), requestResult);
			requestMyCarManagerData();
		}
	}

	private void handleRequestAddFocusCarFailed(Message msg){
		hideWaitingDialog();
		ToastManager.getInstance().showToastCenter(this.getActivity(), R.string.main_tip_operate_failed);
	}

	MileageInputDialog mMileageDialog = null;
	private void modifyMyCarMileage(final CarManagerMyCarData carInfo, final boolean isEvaluate){
		mIsEvaluate = isEvaluate;
		mMileageDialog = DialogUtils.dialogMileageInput(this.getActivity(), new CustomDialog.OnCustomDialogClickListener() {
			@Override
			public boolean onCustomDialogClick(CustomDialog.CustomDialogClickType type) {
				switch (type) {
					case Ok:
						mMileageValue = mMileageDialog.getMileage();
						String registerData = mCurrCarData.getRegisterDate();
						String temp[] = registerData.split("-");
						String tempStr = registerData;
						if (temp.length >= 2){
							tempStr = temp[0] + "年" + temp[1] + "月";
						}
						int months = StringUtil.getMonthFromRegDate(tempStr);
						if(TextUtils.isEmpty(mMileageValue)){
							ToastManager.getInstance().showToastCenter(CarManagerFragment.this.getActivity(), R.string.car_manager_input_mileage_empty_msg);
							return true;
						}
						if(mMileageValue.equals("0") || mMileageValue.equals("0.") || Double.compare(Double.valueOf(mMileageValue), 0.0f) <= 0){
							ToastManager.getInstance().showToastCenter(CarManagerFragment.this.getActivity(), R.string.car_manager_input_mileage_err_msg);
							return true;
						}
						if(Double.valueOf(mMileageValue) > months){
							ToastManager.getInstance().showToastCenter(CarManagerFragment.this.getActivity(), R.string.car_manager_input_mileage_not_in_limited_err_msg);
							return true;
						}
						requestModifyMileage(carInfo, mMileageValue, isEvaluate);
						break;
					case Cancel:
						break;
				}
				return false;
			}
		});
		mMileageDialog.setNegativeButtonText(isEvaluate ? "确定" : "确定");
		mMileageDialog.setTitle(isEvaluate ? R.string.car_manager_input_mileage_evaluate_msg : R.string.car_manager_input_mileage_msg);
	}

	private void handleChangeCarImage(){
		ActionSheet.showSheet(this.getActivity(), this, "相机", "相册中选择", "取消",
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
				mCameraFile = FileUtil.getTempImageFile(this.getActivity(), ".jpg");
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
		Cursor cursor = this.getActivity().getContentResolver().query(selectedImage, null, null, null, null);
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
			ToastManager.getInstance().showToastCenter(this.getActivity(), R.string.album_photo_pic_can_not_found);
			return;
		}
		File ff = new File(picturePath);
		if( ff.exists() && ff.canRead() ){
			toZipAllPic(picturePath);
		} else {
			ToastManager.getInstance().showToastCenter(this.getActivity(), R.string.album_photo_pic_can_not_found);
		}
	}

	private void toZipAllPic(String path){
		final Context context = this.getActivity();
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
		ImageUploadService imageUploadService = new ImageUploadService(this.getActivity(), this);
		RequestCarManagerModifyMyCarImageParams requestParams = new RequestCarManagerModifyMyCarImageParams();
		requestParams.imgpath = filePath;
		requestParams.MyCarId = String.valueOf(mCurrCarData.getCarId());
		requestParams.uid = "0";
		requestParams.ButlerId = GlobalData.getInstance().getCarManagerId();
		if (AppContext.isLogin()) {
			requestParams.uid = AppContext.mLoginResultModels.getId();
		}
		imageUploadService.uploadMyCarImage(requestParams, RequestCarManagerModifyMyCarImageResult.class, REQUEST_CODE_MODIFY_MY_CAR_IMAGE);
		showWaitingDialog(getResources().getString(R.string.main_tip_operate_waiting));
	}

	private void execTest() {
		try {
			AppContext.getHandler().postDelayed(new Runnable() {
				public void run() {
					List<CarManagerMyCarData> myCarDataList = new ArrayList<CarManagerMyCarData>();
					CarManagerMyCarData carData = null;
					carData = new CarManagerMyCarData();
					myCarDataList.add(carData);
					carData = new CarManagerMyCarData();
					myCarDataList.add(carData);
					setMyCarData(myCarDataList);

					myCarDataList = new ArrayList<CarManagerMyCarData>();
					carData = new CarManagerMyCarData();
					myCarDataList.add(carData);
					carData = new CarManagerMyCarData();
					myCarDataList.add(carData);
					carData = new CarManagerMyCarData();
					myCarDataList.add(carData);
					carData = new CarManagerMyCarData();
					myCarDataList.add(carData);
					carData = new CarManagerMyCarData();
					myCarDataList.add(carData);
					carData = new CarManagerMyCarData();
					myCarDataList.add(carData);
					//setRecommendCarData(myCarDataList);

					String testUrl = "http://m.hao123.com/";
					String testUrl2 = "https://m.baidu.com/";
					String testImgUrl = "http://imageup.jingzhengu.com/2016/06/07/1ed3fb4c-67f7-4e7c-8285-509b3f50b045_501.jpg";
					BannerData bannerItem = null;
					List<BannerData> bannerItemOne = new ArrayList<BannerData>();
					bannerItem = new BannerData();
					bannerItem.setImageUrl(testImgUrl);
					bannerItem.setWebUrl(testUrl);
					bannerItemOne.add(bannerItem);

					List<BannerData> bannerItemTwo = new ArrayList<BannerData>();
					bannerItem = new BannerData();
					bannerItem.setImageUrl(testImgUrl);
					bannerItem.setWebUrl(testUrl);
					bannerItemTwo.add(bannerItem);
					bannerItem = new BannerData();
					bannerItem.setImageUrl(testImgUrl);
					bannerItem.setWebUrl(testUrl2);
					bannerItemTwo.add(bannerItem);
					myCarDataList = new ArrayList<CarManagerMyCarData>();
					carData = new CarManagerMyCarData();
					carData.setBannerList(bannerItemTwo);
					myCarDataList.add(carData);
					carData = new CarManagerMyCarData();
					carData.setBannerList(bannerItemOne);
					myCarDataList.add(carData);
					carData = new CarManagerMyCarData();
					carData.setBannerList(bannerItemTwo);
					myCarDataList.add(carData);
					//setFocusCarData(myCarDataList);

					RecommendCardData cardItem = null;
					List<RecommendCardData> carItemOne = new ArrayList<RecommendCardData>();
					cardItem = new RecommendCardData();
					cardItem.setImageUrl("http://imageup.jingzhengu.com/2016/06/07/1ed3fb4c-67f7-4e7c-8285-509b3f50b045_501.jpg");
					cardItem.setWebUrl(testUrl);
					carItemOne.add(cardItem);

					List<RecommendCardData> carItemTwo = new ArrayList<RecommendCardData>();
					cardItem = new RecommendCardData();
					cardItem.setImageUrl("http://imageup.jingzhengu.com/2016/06/07/1ed3fb4c-67f7-4e7c-8285-509b3f50b045_501.jpg");
					cardItem.setWebUrl(testUrl);
					carItemTwo.add(cardItem);
					cardItem = new RecommendCardData();
					cardItem.setImageUrl("http://imageup.jingzhengu.com/2016/06/07/1ed3fb4c-67f7-4e7c-8285-509b3f50b045_501.jpg");
					cardItem.setWebUrl(testUrl2);
					carItemTwo.add(cardItem);

					List<RecommendCardData> carItemThree = new ArrayList<RecommendCardData>();
					cardItem = new RecommendCardData();
					cardItem.setImageUrl("http://imageup.jingzhengu.com/2016/06/07/1ed3fb4c-67f7-4e7c-8285-509b3f50b045_501.jpg");
					cardItem.setWebUrl(testUrl);
					carItemThree.add(cardItem);
					cardItem = new RecommendCardData();
					cardItem.setImageUrl("http://imageup.jingzhengu.com/2016/06/07/1ed3fb4c-67f7-4e7c-8285-509b3f50b045_501.jpg");
					cardItem.setWebUrl(testUrl2);
					carItemThree.add(cardItem);
					cardItem = new RecommendCardData();
					cardItem.setImageUrl("http://imageup.jingzhengu.com/2016/06/07/1ed3fb4c-67f7-4e7c-8285-509b3f50b045_501.jpg");
					cardItem.setWebUrl(testUrl2);
					carItemThree.add(cardItem);

					List<RecommendCardList> recommendList = new ArrayList<RecommendCardList>();
					RecommendCardList recommendEntity = new RecommendCardList();
					recommendEntity.setCardList(carItemOne);
					recommendList.add(recommendEntity);
					recommendEntity = new RecommendCardList();
					recommendEntity.setCardList(carItemTwo);
					recommendList.add(recommendEntity);
					recommendEntity = new RecommendCardList();
					recommendEntity.setCardList(carItemThree);
					recommendList.add(recommendEntity);
					mRecommendListAdapter.setRecommendDataList(recommendList);

				}
			}, 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
