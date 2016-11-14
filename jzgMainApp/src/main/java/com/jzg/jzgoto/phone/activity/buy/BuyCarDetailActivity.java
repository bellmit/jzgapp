package com.jzg.jzgoto.phone.activity.buy;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.constant.Constant;
import com.jzg.jzgoto.phone.global.AppContext;
import com.jzg.jzgoto.phone.global.HttpServiceHelper;
import com.jzg.jzgoto.phone.models.business.buy.AddConcernParams;
import com.jzg.jzgoto.phone.models.business.buy.AddConcernResult;
import com.jzg.jzgoto.phone.models.business.buy.BuyCarDetailResult;
import com.jzg.jzgoto.phone.models.business.buy.DelConcernParams;
import com.jzg.jzgoto.phone.models.business.buy.DelConcernResult;
import com.jzg.jzgoto.phone.models.business.buy.ShareModel;
import com.jzg.jzgoto.phone.services.business.buy.BuyCarService;
import com.jzg.jzgoto.phone.services.common.OnRequestFinishListener;
import com.jzg.jzgoto.phone.tools.BitmapUtils;
import com.jzg.jzgoto.phone.tools.ClickControlTool;
import com.jzg.jzgoto.phone.tools.CountClickTool;
import com.jzg.jzgoto.phone.tools.DialogManager;
import com.jzg.jzgoto.phone.tools.MessageUtils;
import com.jzg.jzgoto.phone.tools.ShareTools;
import com.jzg.jzgoto.phone.tools.ShowDialogTool;
import com.jzg.jzgoto.phone.view.buy.BuyCarDetailBargainDialogView;
import com.jzg.jzgoto.phone.view.buy.BuyCarDetailBaseInfoView;
import com.jzg.jzgoto.phone.view.buy.BuyCarDetailConfigView;
import com.jzg.jzgoto.phone.view.buy.BuyCarDetailHedgeView;
import com.jzg.jzgoto.phone.view.buy.BuyCarDetailPriceRangeView;
import com.jzg.jzgoto.phone.view.buy.BuyCarDetailSimilarCarView;
import com.jzg.jzgoto.phone.view.buy.BuyCarDetailUserLikeView;
import com.lidroid.xutils.bitmap.core.BitmapDecoder;
import com.umeng.socialize.media.UMImage;

import java.math.BigDecimal;

/**
 * Created by WY on 2016/9/18.
 * Function :二手车——买车详情
 */
public class BuyCarDetailActivity extends Activity implements OnRequestFinishListener {

    public static final String GET_BUYCAR_DETAIL = "get_buycar_detail";

    private BuyCarDetailResult mDetailResult;
    private BuyCarDetailBaseInfoView mBaseInfoView;
    private BuyCarDetailPriceRangeView mPriceRangeView;
    private BuyCarDetailConfigView mConfigView;
    private BuyCarDetailHedgeView mHedgeView;
    private BuyCarDetailUserLikeView mUserLikeView;
    private BuyCarDetailSimilarCarView mSimilarCarView;
    private TextView mCollectTextView, mBargainTextView, mPhoneTextView;
    private BuyCarService mBuyCarService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buycar_detail_layout);
        CountClickTool.onEvent(BuyCarDetailActivity.this, "V5_buyCar_Detail");
        mBuyCarService = new BuyCarService(this, this);
        mDetailResult = (BuyCarDetailResult) getIntent().getSerializableExtra(GET_BUYCAR_DETAIL);
        init();
    }

    private void init() {
        mCollectTextView = (TextView) findViewById(R.id.buycar_detail_collect_textView);
        mBargainTextView = (TextView) findViewById(R.id.buycar_detail_bargain_textView);
        mPhoneTextView = (TextView) findViewById(R.id.buycar_detail_call_textView);
        mCollectTextView.setOnClickListener(mClickListener);
        mBargainTextView.setOnClickListener(mClickListener);
        mPhoneTextView.setOnClickListener(mClickListener);
        mBaseInfoView = (BuyCarDetailBaseInfoView) findViewById(R.id.buycar_detail_baseInfo_View);
        mBaseInfoView.setBuyCarDetailShareCallBack(new BuyCarDetailBaseInfoView.BuyCarDetailShareCallBack() {
            @Override
            public void toSetShareContent() {
                if(mDetailResult.getPicList()!=null)
                    if(!mDetailResult.getPicList().isEmpty()){
                        getBitmapThread(mDetailResult.getPicList().get(0).getPic());
                    }
            }
        });
        mPriceRangeView = (BuyCarDetailPriceRangeView) findViewById(R.id.buycar_detail_priceRange_View);
        mConfigView = (BuyCarDetailConfigView) findViewById(R.id.buycar_detail_config_View);
        mHedgeView = (BuyCarDetailHedgeView) findViewById(R.id.buycar_detail_hedge_View);
        mUserLikeView = (BuyCarDetailUserLikeView) findViewById(R.id.buycar_detail_userLike_View);
        mSimilarCarView = (BuyCarDetailSimilarCarView) findViewById(R.id.buycar_detail_similarCar_View);
        if (mDetailResult != null) {
            initViewData();
        }
    }

    private void initViewData() {
        mBaseInfoView.showBaseInfoData(mDetailResult);
        mPriceRangeView.showPriceRangeData(mDetailResult);
        mConfigView.showConfigData(mDetailResult);
        if ("0".equals(mDetailResult.getBaoZhilvRank())
                || TextUtils.isEmpty(mDetailResult.getBaoZhilvCityName())) {
            mHedgeView.setVisibility(View.GONE);
        } else {
            mHedgeView.showHedgeData(mDetailResult);
        }
        if (mDetailResult.getFirstLikeModelList().size() > 0) {
            mUserLikeView.startShow(mDetailResult);
        } else {
            mUserLikeView.setVisibility(View.GONE);
        }
        if(mDetailResult.getTjOldCarList()!=null
                &&mDetailResult.getTjNewCarList()!=null){
            mSimilarCarView.showSimilarListData(mDetailResult.getTjOldCarList(), mDetailResult.getTjNewCarList());
            setSimilarCarParamsPrice();
        }else{
            mSimilarCarView.setVisibility(View.GONE);
        }
        if ("0".equals(mDetailResult.getIsCollection())) {
            //未收藏
            mCollectTextView.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.icon_shoucang, 0, 0, 0);
            mCollectTextView.setText("收藏");
        } else {
            //已收藏
            mCollectTextView.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.iocn_yishoucang, 0, 0, 0);
            mCollectTextView.setText("已收藏");
        }

        if(TextUtils.isEmpty(mDetailResult.getLinkPhone())){
            mPhoneTextView.setClickable(false);
            mPhoneTextView.setBackgroundColor(getResources().getColor(R.color.buy_car_number_btn_color));
        }
    }

    private void setSimilarCarParamsPrice() {
        //设置类似二手车查看更多——价格参数
        double sellPrice = Double.valueOf(mDetailResult.getSellPrice());
        int beginPrice = new BigDecimal((sellPrice) * 0.8).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
        int endPrice = new BigDecimal((sellPrice) * 1.2).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
        mSimilarCarView.setParamsPrice(beginPrice + "", endPrice + "");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDetailResult != null)
            mUserLikeView.stopShow();
    }

    @Override
    protected void onResume() {
        if (mShareTools != null) {
            mShareTools.closeShareBroad();
        }
        super.onResume();
    }

    @Override
    public void onRequestSuccess(Message msg) {
        ShowDialogTool.dismissLoadingDialog();
        switch (msg.what) {
            case R.id.request_buy_car_addconcern:
                AddConcernResult result = (AddConcernResult) msg.obj;
                if (result.getStatus() == Constant.SUCCESS) {
                    CountClickTool.onEvent(BuyCarDetailActivity.this, "V5_buyCar_collection_success");
               //     ShowDialogTool.showCenterToast(this, "收藏成功！");
                    mCollectTextView.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.iocn_yishoucang, 0, 0, 0);
                    mCollectTextView.setText("已收藏");
                    mDetailResult.setIsCollection("1");
                    mDetailResult.setCollectionId(result.getId());
                } else {
                    ShowDialogTool.showCenterToast(this, getResources().getString(R.string.error_net));
                }

                break;
            case R.id.request_buy_car_delconcern:
                DelConcernResult delResult = (DelConcernResult) msg.obj;
                if (delResult.getStatus() == Constant.SUCCESS) {
                    mCollectTextView.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.icon_shoucang, 0, 0, 0);
                    mCollectTextView.setText("收藏");
                    mDetailResult.setIsCollection("0");
                    mDetailResult.setCollectionId("0");
                } else {
                    ShowDialogTool.showCenterToast(this, getResources().getString(R.string.error_net));
                }

                break;
        }
    }

    @Override
    public void onRequestFault(Message msg) {
        ShowDialogTool.dismissLoadingDialog();
        ShowDialogTool.showCenterToast(this, getResources().getString(R.string.error_net));
    }

    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!ClickControlTool.isCanToClick()) return;
            switch (v.getId()) {
                case R.id.buycar_detail_collect_textView:
                    //收藏
                    if (AppContext.isLogin()) {
                        if ("0".equals(mDetailResult.getIsCollection())) {
                            //未收藏状态
                            toRequestAddCollect();
                            CountClickTool.onEvent(BuyCarDetailActivity.this, "V5_buyCar_collection");
                        } else {
                            //已收藏状态
                            toRequestDelCollect();
                        }
                    } else {
                        DialogManager.toLoginDialog(BuyCarDetailActivity.this);
                    }
                    break;
                case R.id.buycar_detail_bargain_textView:
                    //砍价
                    CountClickTool.onEvent(BuyCarDetailActivity.this, "V5_buyCar_bargain");
                    showBargainDialog();
                    break;
                case R.id.buycar_detail_call_textView:
                    //预约看车
                    CountClickTool.onEvent(BuyCarDetailActivity.this, "V5_buyCar_call");
                    call(mDetailResult.getLinkPhone());
                    break;
            }
        }
    };
    private void getBitmapThread(final String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap bitMap = BitmapUtils.getHttpBitmap(url);
                MessageUtils.sendMessage(mHandler, R.id.getBitmapByUrl, url);
            }
        }).start();
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case R.id.getBitmapByUrl:
                    // Bitmap shareBitmap = (Bitmap) msg.obj;
//                    车源：【精真估】品牌-车系-年款 车主报价 车辆报价 万
//                    标题：我在精真估看到一辆 车系 ，车主报价车辆报价万，买卖、置换二手车，先上精真估。
//                    图标：车源第一张照片
                    String imgUrl = (String) msg.obj;
                    ShareModel shareModel = new ShareModel();
                    shareModel.setShareTitle("【精真估】"+mDetailResult.getStyleFullName()+"车主报价"
                            + mDetailResult.getSellPrice() + "万");
                    shareModel.setShareText("我在精真估看到一辆"+mDetailResult.getModelName()
                            +"，车主报价"+ mDetailResult.getSellPrice() + "万，买卖、置换二手车，先上精真估。");
                    String url = HttpServiceHelper.BASE_SHARE_URL
                            + "/carsourcedetail-" + mDetailResult.getCarSourceId()
                            + "-" + mDetailResult.getCarSourceFrom() + "-2";
                    shareModel.setShareUrl(url);
                    UMImage image;
                    if (TextUtils.isEmpty(imgUrl)) {
                        image = new UMImage(BuyCarDetailActivity.this,
                                BitmapDecoder.decodeResource(getResources(),
                                        R.drawable.jzg_icon));
                    } else {
                        image = new UMImage(BuyCarDetailActivity.this, imgUrl);
                    }
				/*
				 * if(shareBitmap!=null){ image = new
				 * UMImage(BuyCarDetailActivity.this, shareBitmap); }else{ image
				 * = new UMImage(BuyCarDetailActivity.this,
				 * BitmapDecoder.decodeResource(getResources(),
				 * R.drawable.jzg_icon)); }
				 */
                    shareModel.setUMImage(image);

                    if (mShareTools == null) {
                        mShareTools = new ShareTools(BuyCarDetailActivity.this);
                    }
                    mShareTools.openShareBroad(shareModel);
                    break;

                default:
                    break;
            }
        }
    };
    private ShareTools mShareTools = null;

    private void toRequestAddCollect() {
        ShowDialogTool.showLoadingDialog(BuyCarDetailActivity.this);
        AddConcernParams params = new AddConcernParams();
        params.uid = AppContext.mLoginResultModels.getId();
        params.CarSourceId = mDetailResult.getCarSourceId();
        params.CarSourceFrom = mDetailResult.getCarSourceFrom();
        mBuyCarService.toResuestAddConcern(params, AddConcernResult.class, R.id.request_buy_car_addconcern);
    }

    private void toRequestDelCollect() {
        ShowDialogTool.showLoadingDialog(BuyCarDetailActivity.this);
        DelConcernParams params = new DelConcernParams();
        params.id = mDetailResult.getCollectionId();
        mBuyCarService.toResuestDelConcern(params, DelConcernResult.class, R.id.request_buy_car_delconcern);
    }

    private void call(String mobile) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.DIAL");
        intent.setData(Uri.parse("tel:" + mobile));
        startActivity(intent);
    }

    private BuyCarDetailBargainDialogView mBargainDialogView;

    private void showBargainDialog() {
        if (mBargainDialogView == null) {
            mBargainDialogView = new BuyCarDetailBargainDialogView(BuyCarDetailActivity.this);
            mBargainDialogView.setBuyCarDetailResult(mDetailResult);
        }
        ShowDialogTool.showSelfViewDialog(BuyCarDetailActivity.this, mBargainDialogView, true, null);
    }
}
