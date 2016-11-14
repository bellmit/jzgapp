package com.jzg.jzgoto.phone.activity.valuation;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.models.business.buy.ShareModel;
import com.jzg.jzgoto.phone.models.business.valuation.ValuationBuyCarNearCityModel;
import com.jzg.jzgoto.phone.models.business.valuation.ValuationBuyCarResult;
import com.jzg.jzgoto.phone.tools.ShareTools;
import com.jzg.jzgoto.phone.view.buy.BuyCarDetailUserLikeView;
import com.jzg.jzgoto.phone.view.valuation.ValuationBaseInfoView;
import com.jzg.jzgoto.phone.view.valuation.ValuationBuyNearCityView;
import com.jzg.jzgoto.phone.view.valuation.ValuationBuyPriceView;
import com.jzg.jzgoto.phone.view.valuation.ValuationRecommendCarView;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.media.UMImage;

/**
 * Created by WY on 2016/9/19.
 * Function :我是买家——买车估值
 */
public class ValuationBuyActivity extends Activity{

    public static final String GET_VALUATION_BUYCAR_RESULT = "get_valuation_buyCar_result";
    private ValuationBaseInfoView mBaseInfoView;
    private ValuationBuyPriceView mBuyPriceView;
    private ValuationBuyNearCityView mNearCityView;
    private BuyCarDetailUserLikeView mUserLikeView;
    private ValuationRecommendCarView mRecommendView;
    private ValuationBuyCarResult mValuationBuyResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valuation_buy_layout);
        mValuationBuyResult = (ValuationBuyCarResult)
                getIntent().getSerializableExtra(GET_VALUATION_BUYCAR_RESULT);
        init();
        if(mValuationBuyResult!=null){
            initShowData();
        }
    }
    private void init(){
        TextView title = (TextView) findViewById(R.id.view_title_textView);
        title.setText("买车估值");
        findViewById(R.id.view_title_return_textView).setOnClickListener(mOnCliclListener);
        findViewById(R.id.view_title_right_textView).setOnClickListener(mOnCliclListener);
        mBaseInfoView = (ValuationBaseInfoView) findViewById(R.id.valuation_buy_baseInfo_view);
        mBuyPriceView = (ValuationBuyPriceView) findViewById(R.id.valuation_buy_price_view);
        mNearCityView = (ValuationBuyNearCityView) findViewById(R.id.valuation_buy_nearCity_view);
        mUserLikeView = (BuyCarDetailUserLikeView) findViewById(R.id.valuation_buy_userLike_view);
        mRecommendView = (ValuationRecommendCarView) findViewById(R.id.valuation_buy_recommend_view);
    }
    private void initShowData(){
        mBaseInfoView.setValuationBuyCarBaseInfoData(mValuationBuyResult);
        mBuyPriceView.initBuyCarPriceData(mValuationBuyResult);
        mUserLikeView.startShow(mValuationBuyResult);
        mRecommendView.showBuyCarSimilarData(mValuationBuyResult);
        if(mValuationBuyResult.getNearCity().size()>0){
            mNearCityView.initNearCityData(mValuationBuyResult);
        }else{
            mNearCityView.setVisibility(View.GONE);
        }
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
        //设置分享内容
//        标题：【精真估】品牌-车系-年款 估值：车商成交车况良好估值点价格 万
//        摘要：我在精真估查询了这辆 车系 的价格，估值 车商成交车况良好估值点价格 万，买卖、置换二手车，先上精真估。
//        图片：精真估logo
        ShareModel shareModel = new ShareModel();
        shareModel.setShareTitle("【精真估】" + mValuationBuyResult.getFullName() +"估值："
                +mValuationBuyResult.getB2CBMidPrice()+"万");
        shareModel.setShareText("我在精真估查询了这辆"+mValuationBuyResult.getModelName()+"的价格，"
                +"估值"+mValuationBuyResult.getB2CBMidPrice()+"万，买卖、置换二手车，先上精真估。");
        shareModel.setShareUrl(mValuationBuyResult.getShareUrl());
            UMImage image = new UMImage(ValuationBuyActivity.this,
                    BitmapFactory.decodeResource(getResources(),
                            R.drawable.jzg_icon));
//        if(!TextUtils.isEmpty( mValuationBuyResult.getImgUrl())){
//            image = new UMImage(ValuationBuyActivity.this, mValuationBuyResult.getImgUrl());
//        }
        shareModel.setUMImage(image);
        if (mShareTools == null) {
            mShareTools = new ShareTools(ValuationBuyActivity.this);
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
