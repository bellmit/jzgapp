package com.jzg.jzgoto.phone.view.valuation;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.models.business.valuation.ValuationBuyCarResult;
import com.jzg.jzgoto.phone.models.business.valuation.sell.RequestHistoryDealParams;
import com.jzg.jzgoto.phone.tools.CountClickTool;
import com.jzg.jzgoto.phone.view.ViewUtility;

/**
 * Created by WY on 2016/9/20.
 * Function :买家估值——附近城市售价
 */
public class ValuationBuyNearCityView extends LinearLayout{

    public ValuationBuyNearCityView(Context context) {
        super(context);
        initView();
    }
    public ValuationBuyNearCityView(Context context, AttributeSet attrs) {
        super(context,attrs);
        initView();
    }
    public ValuationBuyNearCityView(Context context, AttributeSet attrs,
                                        int defStyleAttr) {
        super(context,attrs,defStyleAttr);
        initView();
    }
    private DrawViewNearCityBarChartView mBarChartView;
    private Button mHistroyValuation;
    private void initView(){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_valuation_buy_nearcity_layout,null);
        mBarChartView = (DrawViewNearCityBarChartView)view.findViewById(R.id.valuation_buy_nearCity_barChartView);
        mHistroyValuation = (Button) view.findViewById(R.id.valuation_buy_nearCity_history);
        mHistroyValuation.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mBuyCarResult!=null){
                    CountClickTool.onEvent(getContext(), "V5_valuation_buy_transaction_price");
                    RequestHistoryDealParams params = new RequestHistoryDealParams();
                    params.type = "1";
                    params.pageindex = "1";
                    params.styleId = mBuyCarResult.getStyleId();
                    params.cityId = mBuyCarResult.getCityId();
                    ViewUtility.navigateToValuationSellHistoryActivity(getContext(),params);
                }
            }
        });
        this.addView(view);
    }

    private ValuationBuyCarResult mBuyCarResult;
    public void initNearCityData(ValuationBuyCarResult result){
        mBuyCarResult = result;
        mBarChartView.toShowData(result, getSizeRatio());
    }
    private float mSizeRatio = 0f;
    public float getSizeRatio(){
        if(mSizeRatio > 0f)return mSizeRatio;
        DisplayMetrics dm = new DisplayMetrics();
        dm = getResources().getDisplayMetrics();
        final float ratio = dm.density;
        mSizeRatio = ratio;
        return ratio;
    }
}
