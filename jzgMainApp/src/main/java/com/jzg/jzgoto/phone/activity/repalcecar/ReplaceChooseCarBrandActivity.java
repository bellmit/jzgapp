package com.jzg.jzgoto.phone.activity.repalcecar;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.global.AppContext;
import com.jzg.jzgoto.phone.models.business.car.CarMakeBean;
import com.jzg.jzgoto.phone.models.business.car.CarMakeParamsModels;
import com.jzg.jzgoto.phone.models.business.car.CarMakeResultModels;
import com.jzg.jzgoto.phone.models.business.car.CarModelBean;
import com.jzg.jzgoto.phone.models.business.car.CarModelResultModels;
import com.jzg.jzgoto.phone.services.business.cartype.GetCarListInterface;
import com.jzg.jzgoto.phone.services.business.cartype.GetCarListService;
import com.jzg.jzgoto.phone.services.business.cartype.RequestCarService;
import com.jzg.jzgoto.phone.services.common.OnRequestFinishListener;
import com.jzg.jzgoto.phone.tools.ShowDialogTool;
import com.jzg.jzgoto.phone.valuationchoosecarstyle.HttpServiceCar;
import com.jzg.jzgoto.phone.view.buy.BuyCarSelfCarStyleListRelative;
import com.jzg.jzgoto.phone.view.buy.BuyCarSelfCarStyleListRelative.CallbackForCarList;
import com.jzg.pricechange.phone.JzgCarChooseConstant;
import com.jzg.pricechange.phone.JzgCarChooseMake;
import com.jzg.pricechange.phone.JzgCarChooseMakeList;
import com.jzg.pricechange.phone.JzgCarChooseModelList;
import com.jzg.pricechange.phone.JzgCarChooseStyle;

import java.util.ArrayList;
import java.util.List;

public class ReplaceChooseCarBrandActivity extends Activity
implements OnRequestFinishListener{
	
	private BuyCarSelfCarStyleListRelative mBrandModelView;
	private String isOldOrNew = "1";//默认新车
	
	private String canChooseCarModel = "";
	public static final String IS_CHOOSE_MODEL = "is_choose_model";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_replace_choose_brand_layout);
		isOldOrNew = getIntent().getStringExtra("mChooseCarBrand_BrandList_OldOrNew");
		canChooseCarModel = getIntent().getStringExtra(IS_CHOOSE_MODEL);
		if(TextUtils.isEmpty(isOldOrNew)){
			isOldOrNew = "1";
		}
		init();
	}

	private void init(){
		mBrandModelView = (BuyCarSelfCarStyleListRelative)
				findViewById(R.id.buy_car_choose_brand_layout);
		mBrandModelView.toHideBottomView();
		if(!TextUtils.isEmpty(canChooseCarModel)){
			mBrandModelView.setCanChooseModel(false);
		}
		mBrandModelView.setCallbackForCarList(new CallbackForCarList() {
			
			@Override
			public void toRequestCarModel(String brandId) {
				CarModelResultModels carModelResultModels  = getCarListInterface.queryCarModel(brandId, "0");
				List<CarModelBean> modelLists = carModelResultModels.getModelList();
				mBrandModelView.showModelListNew(modelLists);
				//startModelListThread(brandId);
			}
			
			@Override
			public void toCallBackCarStyle(JzgCarChooseStyle choseStyle) {
				Intent in = new Intent();
				in.putExtra("mQueryCarStyle", choseStyle);
				setResult(JzgCarChooseConstant.Valuation_QUERYCAR_MSG, in);
				finish();
			}
		});
		if(!mBrandModelView.checkHasMakesList()){
			startMakeListThread();
		}
	}
	private GetCarListInterface getCarListInterface = null;
	/**
	 * 如果是新车参数为1，旧车为0
	 */
	private void startMakeListThread() {
		
		getCarListInterface = GetCarListService.getInstance(this);
		CarMakeResultModels carMakeResultModels = getCarListInterface.queryCarBrand("1");
		List<CarMakeBean> ds = carMakeResultModels.getMakeList();
		if(null == ds || ds.size() == 0){
			toRequestMakeList(CarMakeParamsModels.MAKE,REQUEST_MAKE);
		} else {
			makes.clear();
			for(int i = 0;i<ds.size();i++){
				JzgCarChooseMake JzgCarChooseMake = new JzgCarChooseMake();
				JzgCarChooseMake.setMakeName(ds.get(i).getMakeName());
				JzgCarChooseMake.setGroupName(ds.get(i).getGroupName());
				JzgCarChooseMake.setMakeId(Integer.parseInt(ds.get(i).getMakeId()));
				JzgCarChooseMake.setMakeLogo(ds.get(i).getMakeLogo());
				
				makes.add(JzgCarChooseMake);
			}
			//显示品牌
			mBrandModelView.showMakeList(makes);
		}
	}
	private final ArrayList<JzgCarChooseMake> makes = new ArrayList<JzgCarChooseMake>();
	/**
	 * 车系查询线程 startModelListThread: 
	 */
	private void startModelListThread(final String makeid) {
		ShowDialogTool.showLoadingDialog(this);
		HttpServiceCar.getModelList(this,mHandForRequest,
				AppContext.getRequestQueue(), JzgCarChooseConstant.modellist, makeid,"1",isOldOrNew);
	}
	private final int REQUEST_MAKE = 0x1001;
	public void toGetMakeList(View view){
		toRequestMakeList(CarMakeParamsModels.MAKE,REQUEST_MAKE);
	}
	private void toRequestMakeList(String valua,int code){
		CarMakeParamsModels params = new CarMakeParamsModels();
		params.requestOp = valua;
		new RequestCarService(this, this).toRequestMakeList(params, CarMakeResultModels.class, code);
	}
	
	@Override
	public void onRequestSuccess(Message msg) {
		switch(msg.what){
		case REQUEST_MAKE:
			final CarMakeResultModels make = (CarMakeResultModels) msg.obj;
			if(make != null && 100 == make.getStatus()){
				new AsyncTask<String, String, String>(){
					protected void onPreExecute() {
					//	toShowLoadingDialog();
					};
					@Override
					protected String doInBackground(String... params) {
						GetCarListService.getInstance(ReplaceChooseCarBrandActivity.this).insertCarBrand(make);
						return null;
					}
					protected void onPostExecute(String result) {
					//	dismissLoadingDialog();
						CarMakeResultModels carMakeResultModels = getCarListInterface.queryCarBrand("1");
						List<CarMakeBean> ds = carMakeResultModels.getMakeList();
						makes.clear();
						for(int i = 0;i<ds.size();i++){
							JzgCarChooseMake JzgCarChooseMake = new JzgCarChooseMake();
							JzgCarChooseMake.setMakeName(ds.get(i).getMakeName());
							JzgCarChooseMake.setGroupName(ds.get(i).getGroupName());
							JzgCarChooseMake.setMakeId(Integer.parseInt(ds.get(i).getMakeId()));
							JzgCarChooseMake.setMakeLogo(ds.get(i).getMakeLogo());
							
							makes.add(JzgCarChooseMake);
						}
						//显示品牌
						mBrandModelView.showMakeList(makes);
					};
				}.execute("");
			}
			break;
		}
		
	}
	@Override
	public void onRequestFault(Message msg) {
		//请求失败
	}
	
	private Handler mHandForRequest = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			ShowDialogTool.dismissLoadingDialog();
			int id = msg.what;
			switch (id) {
			case JzgCarChooseConstant.makelist:
				// 组装汽车品牌数据
				final JzgCarChooseMakeList mMakeList = (JzgCarChooseMakeList) msg.obj;
				if(mMakeList.getMakes() == null || mMakeList.getMakes().size() == 0){
					ShowDialogTool.showCenterToast(ReplaceChooseCarBrandActivity.this, getResources().getString(R.string.error_noData));
					break;
				}
				mBrandModelView.showMakeList(mMakeList.getMakes());
				break;
			case JzgCarChooseConstant.modellist:
				final JzgCarChooseModelList modelList = (JzgCarChooseModelList) msg.obj;
				if(modelList.getModels() == null || modelList.getModels().size()==0){
					ShowDialogTool.showCenterToast(ReplaceChooseCarBrandActivity.this, getResources().getString(R.string.error_noData));
					break;
				}
				mBrandModelView.showModelList(modelList.getModels());
				break;
			case JzgCarChooseConstant.net_error_back:
				 // TODO 网络错误提示
				ShowDialogTool.showCenterToast(ReplaceChooseCarBrandActivity.this, getResources().getString(R.string.error_noConnect));
				break;
			default:
				break;
			}
		}
	};
}
