package com.jzg.jzgoto.phone.view.valuation;

import android.app.Activity;
import android.content.Context;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.constant.Constant;
import com.jzg.jzgoto.phone.models.business.valuation.ValuationBuyCarResult;
import com.jzg.jzgoto.phone.models.business.valuation.ValuationRecommendCarParams;
import com.jzg.jzgoto.phone.models.business.valuation.ValuationRecommendCarResult;
import com.jzg.jzgoto.phone.models.business.valuation.ValuationSellCarResult;
import com.jzg.jzgoto.phone.services.business.valuation.ValuationService;
import com.jzg.jzgoto.phone.services.common.OnRequestFinishListener;
import com.jzg.jzgoto.phone.tools.ClickControlTool;
import com.jzg.jzgoto.phone.tools.ShowDialogTool;
import com.jzg.jzgoto.phone.view.buy.BuyCarDetailSimilarCarView;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by WY on 2016/9/20.
 * Function :估值详情 类似车源推荐
 */
public class ValuationRecommendCarView extends LinearLayout implements OnRequestFinishListener{

    private Context mContext;
    public ValuationRecommendCarView(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public ValuationRecommendCarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    public ValuationRecommendCarView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
        initView();
    }

    private EditText mAddPrice;
    private TextView mReplaceCarName,mRefreshRecommend;
    private BuyCarDetailSimilarCarView mSimilarCarView;
    private void initView(){
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_valuation_sell_recommend_layout,null);
        mAddPrice = (EditText) view.findViewById(R.id.valuation_sell_recommend_addPrice);
        mReplaceCarName = (TextView) view.findViewById(R.id.valuation_sell_recommend_carName);
        mRefreshRecommend = (TextView) view.findViewById(R.id.valuation_sell_recommend_refresh);
        mRefreshRecommend.setOnClickListener(mClickListener);
        mSimilarCarView = (BuyCarDetailSimilarCarView) view.findViewById(R.id.valuation_sell_recommend_similarView);
        mSimilarCarView.hidePriceHintShow();
        WindowManager windowManager = ((Activity)getContext()).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        LayoutParams params = new LayoutParams(display.getWidth(), LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);
        this.addView(view);
    }
    private String jzgPrice = "";
    public void showSellCarSimilarData(ValuationSellCarResult mSellCarResult){
        mReplaceCarName.setText("万，可以换个"+mSellCarResult.getTjModelName());
        switch (mSellCarResult.getPriceLevel()){
            case"A":
                jzgPrice =  mSellCarResult.getC2BAMidPrice();
                break;
            case"B":
                jzgPrice =  mSellCarResult.getC2BBMidPrice();
                break;
            case"C":
                jzgPrice =  mSellCarResult.getC2BCMidPrice();
                break;
        }
        mSimilarCarView.showSimilarListData(mSellCarResult.getTjOldCarList(),mSellCarResult.getTjNewCarList());
        setSimilarCarParamsPrice();
    }
    public void showBuyCarSimilarData(ValuationBuyCarResult mBuyCarResult){
        mReplaceCarName.setText("万，可以换个"+mBuyCarResult.getTjModelName());
        jzgPrice = mBuyCarResult.getB2CBMidPrice();
        mSimilarCarView.showSimilarListData(mBuyCarResult.getTjOldCarList(),mBuyCarResult.getTjNewCarList());
        setSimilarCarParamsPrice();
    }

    private void setSimilarCarParamsPrice(){
        //设置类似二手车查看更多——价格参数
        double sellPrice = Double.valueOf(jzgPrice);
        double addPrice = 0f;
        if(!TextUtils.isEmpty(mAddPrice.getText().toString().trim())){
            addPrice = Double.valueOf(mAddPrice.getText().toString().trim());
        }
        int beginPrice = new BigDecimal((sellPrice+addPrice)*0.8).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
        int endPrice = new BigDecimal((sellPrice+addPrice)*1.2).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
        mSimilarCarView.setParamsPrice(beginPrice+"",endPrice+"");
    }
    private OnClickListener mClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!ClickControlTool.isCanToClick())return;
            switch (v.getId()){
                case R.id.valuation_sell_recommend_refresh:
                    //刷新推荐车源
                    if(!TextUtils.isEmpty(mAddPrice.getText().toString().trim())){
                        if(Double.valueOf(mAddPrice.getText().toString().trim())>100
                                ||Double.valueOf(mAddPrice.getText().toString().trim())<=0){
                            ShowDialogTool.showCenterToast(getContext(),"请输入1-100之间的整数。");
                        }else{
                            toReqestRecommendCar();
                        }
                    }
                    break;
            }
        }
    };

    private void toReqestRecommendCar(){
        ShowDialogTool.showLoadingDialog(getContext());
        ValuationRecommendCarParams params  = new ValuationRecommendCarParams();
        params.AddPrice = mAddPrice.getText().toString().trim();
        params.guzhiPrice = jzgPrice;
        new ValuationService(getContext(),this).toResuestValuationRecommendRefresh(params,
                ValuationRecommendCarResult.class,R.id.request_valuation_recommend_list);
    }

    @Override
    public void onRequestSuccess(Message msg) {
        ShowDialogTool.dismissLoadingDialog();
        if(msg.what == R.id.request_valuation_recommend_list){
            ValuationRecommendCarResult result = (ValuationRecommendCarResult) msg.obj;
            if(result.getStatus()== Constant.SUCCESS){
                mSimilarCarView.showSimilarListData(result.getTjOldCarList(),result.getTjNewCarList());
                setSimilarCarParamsPrice();
                mReplaceCarName.setText("万，可以换个"+result.getTjModelName());
            }else{
                ShowDialogTool.showCenterToast(getContext(),getResources().getString(R.string.error_net));
            }
        }
    }

    @Override
    public void onRequestFault(Message msg) {
        ShowDialogTool.dismissLoadingDialog();
        ShowDialogTool.showCenterToast(getContext(),getResources().getString(R.string.error_net));
    }
}
