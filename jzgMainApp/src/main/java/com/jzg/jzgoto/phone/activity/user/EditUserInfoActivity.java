package com.jzg.jzgoto.phone.activity.user;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;;
import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.activity.common.ConstantForAct;
import com.jzg.jzgoto.phone.activity.shared.BaseActivity;
import com.jzg.jzgoto.phone.event.CityChooseEvent;
import com.jzg.jzgoto.phone.global.AppContext;
import com.jzg.jzgoto.phone.models.business.settings.ChangePersonPicParamsModels;
import com.jzg.jzgoto.phone.models.business.settings.ChangePersonPicResultModels;
import com.jzg.jzgoto.phone.models.business.settings.EditUserInfoParmasModels;
import com.jzg.jzgoto.phone.models.business.settings.EditUserInfoResultModels;
import com.jzg.jzgoto.phone.models.common.CommonModelSettings;
import com.jzg.jzgoto.phone.services.business.login.LoginService;
import com.jzg.jzgoto.phone.tools.ActionSheet;
import com.jzg.jzgoto.phone.tools.FileUtil;
import com.jzg.jzgoto.phone.tools.ImageRender;
import com.jzg.jzgoto.phone.tools.ShowDialogTool;
import com.jzg.jzgoto.phone.tools.StringUtil;
import com.jzg.jzgoto.phone.tools.ToastManager;
import com.jzg.jzgoto.phone.view.ViewUtility;
import com.jzg.jzgoto.phone.view.common.CircularImageView;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;


public class EditUserInfoActivity extends BaseActivity implements View.OnClickListener, ActionSheet.OnActionSheetListener, DialogInterface.OnCancelListener{

	private static final int REQUEST_CODE_MODIFY_USER_IMAGE = 1;
	private static final int REQUEST_CODE_SAVE_USER_INFO = 2;

	private static final int ACTION_GET_FROM_PHOTO_ALBUM = 0x3001;
	private static final int ACTION_GET_FROM_CAMERA = 0x3002;

	@Bind(R.id.btn_save)
	View mSaveUserInfoBtn;
	@Bind(R.id.selfimg_user_img)
	CircularImageView mImgUserShow;
	@Bind(R.id.tv_about_me_msg_sex_value)
	TextView mTvSexValue;
	@Bind(R.id.edit_about_me_msg_age_value)
	EditText mEditAgeValue;
	@Bind(R.id.edit_about_me_msg_truename_value)
	EditText mEditTrueNameValue;
	@Bind(R.id.tv_about_me_msg_phonenum_value)
	TextView mTvPhoneNumValue;
	@Bind(R.id.tv_about_me_msg_address_value)
	TextView mTvAddressValue;

	private EditUserInfoParmasModels mEditUserInfoParmasModels = new EditUserInfoParmasModels();
	private EditUserInfoResultModels mEditUserInfoResultModels;
	private File mCameraFile;
	private String mCityName = "";
	private String mCityId = "";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		EventBus.getDefault().register(this);
		setContentView(R.layout.activity_edit_user_info_layout);
		ButterKnife.bind(this);
		initViews();
	}

	public void initViews() {
		if(AppContext.isLogin()){
			mEditUserInfoParmasModels.ProvinceName = AppContext.mLoginResultModels.getProvinceName();
			mEditUserInfoParmasModels.CityName = AppContext.mLoginResultModels.getCityName();
			mEditUserInfoParmasModels.mobile = AppContext.mLoginResultModels.getMobile();
			mTvPhoneNumValue.setText(AppContext.mLoginResultModels.getMobile());
			mEditTrueNameValue.setText(AppContext.mLoginResultModels.getTrueName());
			mTvSexValue.setText(AppContext.mLoginResultModels.getGendor());
			mEditAgeValue.setText(AppContext.mLoginResultModels.getAge());
			String imageUrl = AppContext.mLoginResultModels.getAvatorPicPath();
			if(!TextUtils.isEmpty(imageUrl)){
				ImageRender.getInstance().setImage(mImgUserShow,
						imageUrl == null ? null :  imageUrl, R.drawable.jingzhengu_moren);
			}

			String province = AppContext.mLoginResultModels.getProvinceName();
			String city = AppContext.mLoginResultModels.getCityName();
			mTvAddressValue.setText(city);
		}
		mEditAgeValue.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (TextUtils.isEmpty(s))
					return;
				if(s.toString().startsWith("0")){
					ShowDialogTool.showCenterToast(EditUserInfoActivity.this, "不能输入0岁!");
					mEditAgeValue.setText("");
				}
				if(s.toString().startsWith("-")){
					ShowDialogTool.showCenterToast(EditUserInfoActivity.this, "不能输入负值!");
					mEditAgeValue.setText("");
				}
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			@Override
			public void afterTextChanged(Editable s) {
			}
		});
	}

	@Override
	@OnClick({R.id.btn_save, R.id.linear_about_me_msg_sex, R.id.linear_about_me_msg_address, R.id.selfimg_user_img})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.btn_save:
				toSaveUserInfo();
				break;
			case R.id.selfimg_user_img:
				handleChangeCarImage();
				break;
			case R.id.linear_about_me_msg_sex:
				toShowDialogForChoose(new String[]{"男","女"},new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						if(which == 0){
							mTvSexValue.setText("男");
						} else {
							mTvSexValue.setText("女");
						}
					}
				});
				break;
			case R.id.linear_about_me_msg_address:
				ViewUtility.navigateToChooseCityActivity(this);
				break;
		}
	}

	@Override
	public void onCancel(DialogInterface dialog) {
	}


	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	public void onDestroy() {
		EventBus.getDefault().unregister(this);
		super.onDestroy();
	}

	public void onEvent(CityChooseEvent event) {
		if (event != null && event.cityInfo != null) {
			mCityId = event.cityInfo.cityId;
			mCityName = StringUtil.returnShi(event.cityInfo.cityName);
			mTvAddressValue.setText(mCityName);
			mEditUserInfoParmasModels.ProvinceName = "";
			mEditUserInfoParmasModels.CityName = mCityName;
		}
	}

	@Override
	public void onRequestSuccess(Message msg) {
		switch (msg.what) {
			case REQUEST_CODE_SAVE_USER_INFO:
				handleRequestSaveUserInfoSuccess(msg);
				break;
			case REQUEST_CODE_MODIFY_USER_IMAGE:
				handleRequestModifyUserImageSuccess(msg);
				break;
		}
	}

	@Override
	public void onRequestFault(Message msg) {
		switch (msg.what) {
			case REQUEST_CODE_SAVE_USER_INFO:
				handleRequestSaveUserInfoFailed(msg);
				break;
			case REQUEST_CODE_MODIFY_USER_IMAGE:
				handleRequestModifyUserImageFailed(msg);
				break;
		}
	}

	private void handleRequestModifyUserImageSuccess(Message msg){
		hideWaitingDialog();
		ChangePersonPicResultModels requestResult = (ChangePersonPicResultModels) msg.obj;
		if (requestResult != null && requestResult.getStatus() == CommonModelSettings.STATUS_SUCCESS) {
			ToastManager.getInstance().showToastCenter(this, R.string.user_center_modify_user_image_success);
			String imageUrl = requestResult.getAvatorPicPath();
			if(!TextUtils.isEmpty(imageUrl)){
				ImageRender.getInstance().setImage(mImgUserShow,
						imageUrl == null ? null :  imageUrl, R.drawable.jingzhengu_moren);
			}
			if(AppContext.isLogin()){
				AppContext.mLoginResultModels.setAvatorPicPath(imageUrl);
				ConstantForAct.saveUserProfile(this, AppContext.mLoginResultModels);
			}
		}else{
			ToastManager.getInstance().showToastCenter(this, R.string.user_center_modify_user_image_failed);
		}
	}

	private void handleRequestModifyUserImageFailed(Message msg){
		hideWaitingDialog();
		ToastManager.getInstance().showToastCenter(this, R.string.user_center_modify_user_image_failed);
	}

	private void handleRequestSaveUserInfoSuccess(Message msg){
		hideWaitingDialog();
		mEditUserInfoResultModels = (EditUserInfoResultModels) msg.obj;
		if (mEditUserInfoResultModels != null && mEditUserInfoResultModels.getStatus() == CommonModelSettings.STATUS_SUCCESS) {
			ToastManager.getInstance().showToastCenter(this, R.string.user_center_save_user_info_success);
			AppContext.mLoginResultModels.setGendor(mEditUserInfoParmasModels.Gendor);
			AppContext.mLoginResultModels.setAge(mEditUserInfoParmasModels.Age);
			AppContext.mLoginResultModels.setTrueName(mEditUserInfoParmasModels.TrueName);
			AppContext.mLoginResultModels.setCityName(mEditUserInfoParmasModels.CityName);
			ConstantForAct.saveUserProfile(this, AppContext.mLoginResultModels);
			this.finish();
		}else{
			ToastManager.getInstance().showToastCenter(this, R.string.user_center_save_user_info_failed);
		}
	}

	private void handleRequestSaveUserInfoFailed(Message msg){
		hideWaitingDialog();
		ToastManager.getInstance().showToastCenter(this, R.string.user_center_save_user_info_failed);
	}

	private void toShowDialogForChoose(String[] strs,DialogInterface.OnClickListener onclick){
		AlertDialog.Builder builder = new AlertDialog.Builder(EditUserInfoActivity.this);
		builder.setItems(strs, onclick);
		builder.show();
	}

	private void toSaveUserInfo(){
		if(!AppContext.isLogin()){
			ViewUtility.navigateToLoginActivity(this);
			return;
		}
		mEditUserInfoParmasModels.Id = AppContext.mLoginResultModels.getId();
		mEditUserInfoParmasModels.LoginName = AppContext.mLoginResultModels.getLoginName();
		mEditUserInfoParmasModels.Gendor = mTvSexValue.getText().toString().trim();
		mEditUserInfoParmasModels.Age = mEditAgeValue.getText().toString().trim();
		mEditUserInfoParmasModels.TrueName = mEditTrueNameValue.getText().toString().trim();

		showWaitingDialog(getResources().getString(R.string.main_tip_operate_waiting));
		new LoginService(EditUserInfoActivity.this, EditUserInfoActivity.this).toRequestNet(
				mEditUserInfoParmasModels, EditUserInfoResultModels.class, REQUEST_CODE_SAVE_USER_INFO);
	}

	private void startToUploadPic(String filePath){
		ChangePersonPicParamsModels params = new ChangePersonPicParamsModels();
		params.uid = AppContext.mLoginResultModels.getId();
		params.imgpath = filePath;
		new LoginService(EditUserInfoActivity.this, EditUserInfoActivity.this).uploadUserIcon(params, REQUEST_CODE_MODIFY_USER_IMAGE);
		showWaitingDialog(getResources().getString(R.string.main_tip_operate_waiting));
	}

	private void handleChangeCarImage(){
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

}
