package com.jzg.jzgoto.phone.activity.valuation;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.activity.common.ConstantForAct;
import com.jzg.jzgoto.phone.constant.Constant;
import com.jzg.jzgoto.phone.global.AppContext;
import com.jzg.jzgoto.phone.global.GlobalData;
import com.jzg.jzgoto.phone.models.business.buy.ShareModel;
import com.jzg.jzgoto.phone.models.business.carmanager.RequestCarManagerAddMyCarParams;
import com.jzg.jzgoto.phone.models.business.carmanager.RequestCarManagerAddMyCarResult;
import com.jzg.jzgoto.phone.models.business.sell.ReplaceNewCarIntentModel;
import com.jzg.jzgoto.phone.models.business.valuation.ValuationSellCarResult;
import com.jzg.jzgoto.phone.services.business.carmanager.CarManagerService;
import com.jzg.jzgoto.phone.services.common.OnRequestFinishListener;
import com.jzg.jzgoto.phone.tools.ClickControlTool;
import com.jzg.jzgoto.phone.tools.CountClickTool;
import com.jzg.jzgoto.phone.tools.ShareTools;
import com.jzg.jzgoto.phone.tools.ShowDialogTool;
import com.jzg.jzgoto.phone.view.ViewUtility;
import com.jzg.jzgoto.phone.view.buy.BuyCarDetailUserLikeView;
import com.jzg.jzgoto.phone.view.shared.HeadBar;
import com.jzg.jzgoto.phone.view.valuation.ValuationBaseInfoView;
import com.jzg.jzgoto.phone.view.valuation.ValuationRecommendCarView;
import com.jzg.jzgoto.phone.view.valuation.ValuationSellPriceView;
import com.jzg.jzgoto.phone.view.valuation.ValuationSellReplaceTimeView;
import com.lidroid.xutils.bitmap.core.BitmapDecoder;
import com.umeng.socialize.media.UMImage;

/**
 * Created WY jzg on 2016/9/19.
 * Function :我是车主——卖车估值
 */
public class ValuationSellActivity extends Activity implements OnRequestFinishListener{

    public static final String GET_VALUATION_SELLCAR_RESULT = "get_valuation_sellCar_result";
    private TextView mSellCarFast,mReplaceCar,mAddNewCar;

    private ValuationBaseInfoView mBaseInfoView;
    private ValuationSellPriceView mSellPriceView;
    private ValuationSellReplaceTimeView mReplaceCycleView;
    private BuyCarDetailUserLikeView mUserLikeView;
    private ValuationRecommendCarView mRecommendCarView;
    private ValuationSellCarResult mSellCarResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valuation_sell_layout);
        mSellCarResult = (ValuationSellCarResult) getIntent().getSerializableExtra(GET_VALUATION_SELLCAR_RESULT);
        init();
        if(mSellCarResult!=null){
            initShowData();
        }
    }

    private void initShowData(){
        mBaseInfoView.setValuationSellCarBaseInfoData(mSellCarResult);
        mSellPriceView.showSellPriceData(mSellCarResult);
        mRecommendCarView.showSellCarSimilarData(mSellCarResult);
        mUserLikeView.startShow(mSellCarResult);
        mRecommendCarView.setVisibility(View.VISIBLE);
        if(mSellCarResult.getChangeCycle().isEmpty()){
            mReplaceCycleView.setVisibility(View.GONE);
        }else{
            mReplaceCycleView.setVisibility(View.VISIBLE);
            mReplaceCycleView.showReplaceCycleData(mSellCarResult);
        }

    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!ClickControlTool.isCanToClick())return;
            switch (v.getId()){
                case R.id.valuation_sell_carFast:
                    //快速卖掉爱车
                    CountClickTool.onEvent(ValuationSellActivity.this, "V5_valuation_sell_car");
                    ViewUtility.navigateToSellCarActivity(ValuationSellActivity.this,getReplaceNewCarIntentModel());
                    break;
                case R.id.valuation_sell_replace:
                    //置换新车
                    CountClickTool.onEvent(ValuationSellActivity.this, "V5_valuation_sell_replace");
                    ViewUtility.navigateToReplaceNewCarActivity(ValuationSellActivity.this,getReplaceNewCarIntentModel());
                    break;
                case R.id.valuation_sell_addNewCar:
                    //添加爱车
                    if("我的车".equals(mAddNewCar.getText().toString().trim())){
                        //跳转到车管家界面
                        ViewUtility.navigateToCarManagerActivity(ValuationSellActivity.this, true);
                    }else{
                        //添加爱车
                        CountClickTool.onEvent(ValuationSellActivity.this, "V5_valuation_sell_addCar");
                        toRequestAddMyCar();
                    }
                    break;
            }
        }
    };

    @Override
    public void onRequestSuccess(Message msg) {
        ShowDialogTool.dismissLoadingDialog();
        if(msg.what == R.id.request_valuation_sell_add_myCar){
            RequestCarManagerAddMyCarResult result = (RequestCarManagerAddMyCarResult) msg.obj;
            if(result.getStatus() == Constant.SUCCESS){
                ConstantForAct.saveCarManagerId(this, result.getCarMangerId());
                GlobalData.getInstance().setCarManagerId(result.getCarMangerId());
                mAddNewCar.setText("我的车");
                ShowDialogTool.showCenterToast(ValuationSellActivity.this,"已添加至爱车库");
            }else{
                ShowDialogTool.showCenterToast(ValuationSellActivity.this,result.getMsg());
            }
        }
    }

    @Override
    public void onRequestFault(Message msg) {
        ShowDialogTool.dismissLoadingDialog();
        ShowDialogTool.showCenterToast(ValuationSellActivity.this,getResources().getString(R.string.error_net));
    }

    private void toRequestAddMyCar(){
        ShowDialogTool.showLoadingDialog(ValuationSellActivity.this);
        RequestCarManagerAddMyCarParams params = new RequestCarManagerAddMyCarParams();
        params.uid = "0";
        if (AppContext.isLogin()) {
            params.uid = AppContext.mLoginResultModels.getId();
        }
        params.ButlerId = GlobalData.getInstance().getCarManagerId();
        params.styleId = mSellCarResult.getStyleId();
        params.Regdate = mSellCarResult.getRegDateTime();
        params.CityId = mSellCarResult.getCityId();
        params.cityname = mSellCarResult.getCityName();
        params.mileage = mSellCarResult.getMileage();
        new CarManagerService(this,this).toRequestAddMyCar(params,
                RequestCarManagerAddMyCarResult.class,R.id.request_valuation_sell_add_myCar);
    }
    private ReplaceNewCarIntentModel getReplaceNewCarIntentModel(){
        ReplaceNewCarIntentModel model = new ReplaceNewCarIntentModel();
        model.setMakeId(mSellCarResult.getMakeId());
        model.setModelId(mSellCarResult.getModelId());
        model.setStyleId(mSellCarResult.getStyleId());
        model.setFullName(mSellCarResult.getFullName());
        model.setRegDate(mSellCarResult.getRegDate());
        model.setMileage(mSellCarResult.getMileage());
        model.setProvinceName(mSellCarResult.getProvName());
        model.setCityName(mSellCarResult.getCityName());
        model.setReportId(mSellCarResult.getReportID());
        model.setCityId(mSellCarResult.getCityId());
        return model;
    }
    private void init(){
        TextView title = (TextView) findViewById(R.id.view_title_textView);
        title.setText("车主估值");
        findViewById(R.id.view_title_return_textView).setOnClickListener(mOnCliclListener);
        findViewById(R.id.view_title_right_textView).setOnClickListener(mOnCliclListener);
        mSellCarFast = (TextView) findViewById(R.id.valuation_sell_carFast);
        mReplaceCar = (TextView) findViewById(R.id.valuation_sell_replace);
        mAddNewCar = (TextView) findViewById(R.id.valuation_sell_addNewCar);
        mSellCarFast.setOnClickListener(mOnClickListener);
        mReplaceCar.setOnClickListener(mOnClickListener);
        mAddNewCar.setOnClickListener(mOnClickListener);
        mBaseInfoView = (ValuationBaseInfoView) findViewById(R.id.valuation_sell_baseInfo_view);
        mSellPriceView = (ValuationSellPriceView) findViewById(R.id.valuation_sell_price_view);
        mReplaceCycleView = (ValuationSellReplaceTimeView) findViewById(R.id.valuation_sell_replaceTime_view);
        mUserLikeView = (BuyCarDetailUserLikeView) findViewById(R.id.valuation_sell_userLike_view);
        mRecommendCarView = (ValuationRecommendCarView) findViewById(R.id.valuation_sell_recommend_view);
    }

    private View.OnClickListener mOnCliclListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.view_title_return_textView:
                    finish();
                    break;
                case R.id.view_title_right_textView:
                    //分享
                    setShareContent();
                    break;
            }
        }
    };

    private void setShareContent(){
//        标题：【精真估】品牌-车系-年款 估值：车商成交车况良好估值点价格 万
//        摘要：我在精真估查询了这辆 车系 的价格，估值 车商成交车况良好估值点价格 万，买卖、置换二手车，先上精真估。
//        图片：精真估logo
        ShareModel shareModel = new ShareModel();
        shareModel.setShareTitle("【精真估】"+mSellCarResult.getFullName()+"  估值："
                +mSellCarResult.getC2BBMidPrice()+"万");
        shareModel.setShareText(
                "我在精真估查询了这辆"+mSellCarResult.getModelName()+"的价格，"
                        +"估值"+mSellCarResult.getC2BBMidPrice()+"万，买卖、置换二手车，先上精真估。");
        shareModel.setShareUrl(mSellCarResult.getShareUrl());
     //   if (TextUtils.isEmpty(mSellCarResult.getImgUrl())) {
        UMImage  image = new UMImage(ValuationSellActivity.this,
                    BitmapDecoder.decodeResource(getResources(),
                            R.drawable.jzg_icon));
//        } else {
//            image = new UMImage(ValuationSellActivity.this, mSellCarResult.getImgUrl());
//        }
        shareModel.setUMImage(image);

        if (mShareTools == null) {
            mShareTools = new ShareTools(ValuationSellActivity.this);
        }
        mShareTools.openShareBroad(shareModel);
    }

    private ShareTools mShareTools;
    @Override
    protected void onResume() {
        super.onResume();
        if(null != mShareTools){
            mShareTools.closeShareBroad();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mUserLikeView!=null)
            mUserLikeView.stopShow();
    }
}
