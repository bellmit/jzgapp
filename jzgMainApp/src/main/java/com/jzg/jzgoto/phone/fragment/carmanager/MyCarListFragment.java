package com.jzg.jzgoto.phone.fragment.carmanager;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.TextView;
import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.adapter.carmanager.MyCarListAdpater;
import com.jzg.jzgoto.phone.event.AddMyCarEvent;
import com.jzg.jzgoto.phone.event.ModifyMyCarImageEvent;
import com.jzg.jzgoto.phone.event.RefreshMyCarEvent;
import com.jzg.jzgoto.phone.fragment.shared.BaseFragment;
import com.jzg.jzgoto.phone.global.AppContext;
import com.jzg.jzgoto.phone.global.GlobalData;
import com.jzg.jzgoto.phone.models.business.carmanager.CarManagerMyCarData;
import com.jzg.jzgoto.phone.models.business.carmanager.RequestCarManagerDeleteMyCarParams;
import com.jzg.jzgoto.phone.models.business.carmanager.RequestCarManagerDeleteMyCarResult;
import com.jzg.jzgoto.phone.models.business.carmanager.RequestCarManagerGetMyCarListParams;
import com.jzg.jzgoto.phone.models.business.carmanager.RequestCarManagerGetMyCarListResult;
import com.jzg.jzgoto.phone.models.business.carmanager.RequestCarManagerModifyMyCarImageParams;
import com.jzg.jzgoto.phone.models.business.carmanager.RequestCarManagerModifyMyCarImageResult;
import com.jzg.jzgoto.phone.models.common.CommonModelSettings;
import com.jzg.jzgoto.phone.services.business.carmanager.CarManagerService;
import com.jzg.jzgoto.phone.services.business.carmanager.ImageUploadService;
import com.jzg.jzgoto.phone.tools.ActionSheet;
import com.jzg.jzgoto.phone.tools.DisplayUtils;
import com.jzg.jzgoto.phone.tools.FileUtil;
import com.jzg.jzgoto.phone.tools.ShowDialogTool;
import com.jzg.jzgoto.phone.tools.ToastManager;
import com.jzg.jzgoto.phone.tools.view.swipemenulistview.SwipeMenu;
import com.jzg.jzgoto.phone.tools.view.swipemenulistview.SwipeMenuCreator;
import com.jzg.jzgoto.phone.tools.view.swipemenulistview.SwipeMenuItem;
import com.jzg.jzgoto.phone.tools.view.swipemenulistview.SwipeMenuListView;
import com.jzg.jzgoto.phone.view.ViewUtility;
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


public class MyCarListFragment extends BaseFragment implements OnClickListener, ActionSheet.OnActionSheetListener,
		SwipeMenuListView.IXListViewListener, SwipeMenuListView.OnMenuItemClickListener {

	private static final int REQUEST_CODE_GET_MY_CAR_LIST = 1;
	private static final int REQUEST_CODE_DELETE_MY_CAR = 2;
	private static final int REQUEST_CODE_MODIFY_MY_CAR_IMAGE = 3;

	private static final int ACTION_GET_FROM_PHOTO_ALBUM = 0x3001;
	private static final int ACTION_GET_FROM_CAMERA = 0x3002;

	private static final int MAX_CAR_COUNT = 5;

	@Bind(R.id.btn_add_car)
	TextView mAddCarBtnView;
	@Bind(R.id.my_car_list_view)
	SwipeMenuListView mCarListView;
	@Bind(R.id.car_empty_container)
	View mCarEmptyContainer;

	private MyCarListAdpater mCarListAdapter;
	private List<CarManagerMyCarData> mCarDataList = new ArrayList<CarManagerMyCarData>();

	private CarManagerService mDataService;
	private RequestCarManagerGetMyCarListParams mRequestParams;
	private RequestCarManagerGetMyCarListResult mRequestResult;
	private CarManagerMyCarData mCurrentCarData;
	private File mCameraFile;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		EventBus.getDefault().register(this);
		mDataService = new CarManagerService(this.getActivity(), this);
		mRequestParams = new RequestCarManagerGetMyCarListParams();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_my_car_list_layout, null);
		ButterKnife.bind(this, view);
		setupErrorView((ViewGroup) view);
		initViews();
		requestMyCarListData();
		return view;
	}
	
	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onDestroy() {
		EventBus.getDefault().unregister(this);
		super.onDestroy();
	}

	public void onEvent(AddMyCarEvent event){
		if (event != null){
			requestMyCarListData();
		}
	}

	public void onEvent(ModifyMyCarImageEvent event) {
		if (event != null && event.carInfo != null) {
			mCurrentCarData = event.carInfo;
			if (this.getActivity() == event.activity) {
				handleChangeCarImage();
			}
		}
	}

	public void onEvent(RefreshMyCarEvent event){
		if (event != null){
			requestMyCarListData();
		}
	}


	@Override
	protected void onRefreshData() {
		requestMyCarListData();
	}

	@Override
	public void onRequestSuccess(Message msg) {
		switch (msg.what) {
			case REQUEST_CODE_GET_MY_CAR_LIST:
				handleRequestGetMyCarListSuccess(msg);
				break;
			case REQUEST_CODE_DELETE_MY_CAR:
				handleRequestDeleteMyCarSuccess(msg);
				break;
			case REQUEST_CODE_MODIFY_MY_CAR_IMAGE:
				handleRequestModifyMyCarImageSuccess(msg);
				break;
		}
	}

	@Override
	public void onRequestFault(Message msg) {
		switch (msg.what) {
			case REQUEST_CODE_GET_MY_CAR_LIST:
				handleRequestGetMyCarListFailed(msg);
				break;
			case REQUEST_CODE_DELETE_MY_CAR:
				handleRequestDeleteMyCarFailed(msg);
				break;
			case REQUEST_CODE_MODIFY_MY_CAR_IMAGE:
				handleRequestModifyMyCarImageFailed(msg);
				break;
		}
	}

	@Override
	protected int getErrorViewTopMargin(){
		return 0;
	}

	private void initViews() {
		mCarListAdapter = new MyCarListAdpater(this.getActivity());
		mCarListView.setAdapter(mCarListAdapter);
		mCarListView.setOnMenuItemClickListener(this);
		mCarListView.setPullLoadEnable(false);
		mCarListView.setPullRefreshEnable(true);
		mCarListView.setXListViewListener(this);
		mCarListView.getmFooterView().hide();
		mCarListView.getmFooterView().setVisibility(View.GONE);

		SwipeMenuCreator creator = new SwipeMenuCreator() {
			@Override
			public void create(SwipeMenu menu) {
				createMenu(menu);
			}

			private void createMenu(SwipeMenu menu) {
				Context context = MyCarListFragment.this.getActivity();
				SwipeMenuItem deleteItem = new SwipeMenuItem(context);
				deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
						0x3F, 0x25)));
				deleteItem.setWidth(DisplayUtils.dpToPx(context, 90));
				deleteItem
						.setTitle(getResources().getString(R.string.del_text));
				deleteItem.setTitleSize(18);
				deleteItem.setTitleColor(Color.WHITE);
				menu.addMenuItem(deleteItem);

			}

		};
		mCarListView.setMenuCreator(creator);
	}

	@Override
	@OnClick({R.id.btn_add_car})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.btn_add_car:
				handleAddCarClicked();
				break;
		}
	}

	@Override
	public void onRefresh() {
		requestMyCarListData();
	}

	@Override
	public void onLoadMore() {
	}

	protected void showLoadingView(){
		super.showLoadingView();
	}

	protected void hideLoadingView(){
		hideErrorView();
	}

	private void stopLoadData() {
		mCarListView.stopRefresh();
		mCarListView.stopLoadMore();
	}

	@Override
	public void onMenuItemClick(int position, SwipeMenu menu, int index) {
		handleDeleteItem(position);
	}

	public void showDataEmptyView(){
		mCarEmptyContainer.setVisibility(View.VISIBLE);
		mCarListView.setVisibility(View.GONE);
	}

	private void onStopLoad() {
		mCarListView.stopRefresh();
		mCarListView.stopLoadMore();
	}

	private void handleDeleteItem(final int position) {

		ShowDialogTool.showTitleAndMsgViewDialog(this.getActivity(), "提示", "是否确认删除", new ShowDialogTool.DialogCallBack() {

			@Override
			public void cancelBack(View v) {
				// TODO Auto-generated method stub

			}

			@Override
			public void applyBack(View v) {
				requestDeleteMyCar(position);
			}
		});
	}

	private void handleAddCarClicked(){
		if (mCarDataList != null && mCarDataList.size() >= MAX_CAR_COUNT){
			String tip = getResources().getString(R.string.car_manager_meet_max_car_count_format, MAX_CAR_COUNT);
			ToastManager.getInstance().showToastCenter(this.getActivity(), tip);
			return;
		}
		ViewUtility.navigateToAddMyCarActivity(this.getActivity());
	}

	private void requestMyCarListData(){
		//execTest();
		showLoadingView();
		mRequestParams.uid = "0";//test code
		mRequestParams.ButlerId = GlobalData.getInstance().getCarManagerId();
		if (AppContext.isLogin()) {
			mRequestParams.uid = AppContext.mLoginResultModels.getId();
		}
		mDataService.toResuestMyCarList(mRequestParams, RequestCarManagerGetMyCarListResult.class, REQUEST_CODE_GET_MY_CAR_LIST);

	}

	private void requestDeleteMyCar(int position){
		CarManagerMyCarData carData = mCarDataList.get(position);
		mCurrentCarData  = carData;
		RequestCarManagerDeleteMyCarParams requestParams = new RequestCarManagerDeleteMyCarParams();
		requestParams.uid = "0";//test code
		requestParams.ButlerId = GlobalData.getInstance().getCarManagerId();
		if (AppContext.isLogin()) {
			requestParams.uid = AppContext.mLoginResultModels.getId();
		}
		requestParams.MyCarId = String.valueOf(carData.getCarId());
		mDataService.toRequestDeleteMyCar(requestParams, RequestCarManagerDeleteMyCarResult.class, REQUEST_CODE_DELETE_MY_CAR);
		showWaitingDialog(this.getResources().getString(R.string.main_tip_operate_waiting));
	}

	private void handleRequestGetMyCarListSuccess(Message msg){
		hideLoadingView();
		stopLoadData();
		mRequestResult = (RequestCarManagerGetMyCarListResult)msg.obj;
		if (mRequestResult != null && mRequestResult.getStatus() == CommonModelSettings.STATUS_SUCCESS) {
			mCarDataList = mRequestResult.getMyCarList();
			mCarListAdapter.setCarDataList(mCarDataList);
			if (mCarDataList == null || mCarDataList.size() == 0){
				showDataEmptyView();
			}else{
				mCarEmptyContainer.setVisibility(View.GONE);
				mCarListView.setVisibility(View.VISIBLE);
			}
		}else {
			showDataEmptyView();
		}
	}

	private void handleRequestGetMyCarListFailed(Message msg){
		stopLoadData();
		showNetworkErrorView();
		ToastManager.getInstance().showToastCenter(this.getActivity(), R.string.error_noConnect);
	}

	private void handleRequestDeleteMyCarSuccess(Message msg){
		hideWaitingDialog();
		RequestCarManagerDeleteMyCarResult requestResult = (RequestCarManagerDeleteMyCarResult)msg.obj;
		if (requestResult != null && requestResult.getStatus() == CommonModelSettings.STATUS_SUCCESS) {
			mCarDataList.remove(mCurrentCarData);
			mCarListAdapter.setCarDataList(mCarDataList);
			if (mCarDataList == null || mCarDataList.size() == 0){
				showDataEmptyView();
			}
			EventBus.getDefault().post(new RefreshMyCarEvent());
			ToastManager.getInstance().showToastCenter(this.getActivity(), R.string.car_manager_delete_my_car_success);
		}
	}

	private void handleRequestDeleteMyCarFailed(Message msg){
		hideWaitingDialog();
		ToastManager.getInstance().showToastCenter(this.getActivity(), R.string.main_tip_operate_failed);
	}

	private void handleRequestModifyMyCarImageSuccess(Message msg){
		hideWaitingDialog();
		RequestCarManagerModifyMyCarImageResult requestResult = (RequestCarManagerModifyMyCarImageResult)msg.obj;
		if (requestResult != null && requestResult.getStatus() == CommonModelSettings.STATUS_SUCCESS) {
			mCurrentCarData.setImageUrl(requestResult.getImageUrl());
			mCarListAdapter.notifyDataSetChanged();
			EventBus.getDefault().post(new RefreshMyCarEvent());
		}
	}

	private void handleRequestModifyMyCarImageFailed(Message msg){
		hideWaitingDialog();
		ToastManager.getInstance().showToastCenter(this.getActivity(), R.string.main_tip_operate_failed);
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
		requestParams.MyCarId = String.valueOf(mCurrentCarData.getCarId());
		requestParams.uid = "0";
		requestParams.ButlerId = GlobalData.getInstance().getCarManagerId();
		if (AppContext.isLogin()) {
			requestParams.uid = AppContext.mLoginResultModels.getId();
		}
		imageUploadService.uploadMyCarImage(requestParams, RequestCarManagerModifyMyCarImageResult.class, REQUEST_CODE_MODIFY_MY_CAR_IMAGE);
		showWaitingDialog(getResources().getString(R.string.main_tip_operate_waiting));
	}

	private void execTest() {
		showLoadingView();
		try {
			AppContext.getHandler().postDelayed(new Runnable() {
				public void run() {
					List<CarManagerMyCarData> myCarDataList = new ArrayList<CarManagerMyCarData>();
					CarManagerMyCarData carData = null;
					carData = new CarManagerMyCarData();
					myCarDataList.add(carData);
					carData = new CarManagerMyCarData();
					myCarDataList.add(carData);
					mCarListAdapter.setCarDataList(myCarDataList);
					hideLoadingView();
				}
			}, 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
