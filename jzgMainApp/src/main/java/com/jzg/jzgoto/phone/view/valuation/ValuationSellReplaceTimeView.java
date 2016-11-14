package com.jzg.jzgoto.phone.view.valuation;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.models.business.valuation.DrawViewLineChartDataModel;
import com.jzg.jzgoto.phone.models.business.valuation.ValuationSellCarReplaceCycleModel;
import com.jzg.jzgoto.phone.models.business.valuation.ValuationSellCarResult;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WY on 2016/9/20.
 * Function :车主估值——置换周期
 */
public class ValuationSellReplaceTimeView extends LinearLayout{

    public ValuationSellReplaceTimeView(Context context) {
        super(context);
        initView();
    }
    public ValuationSellReplaceTimeView(Context context, AttributeSet attrs) {
        super(context,attrs);
        initView();
    }
    public ValuationSellReplaceTimeView(Context context, AttributeSet attrs,
                                  int defStyleAttr) {
        super(context,attrs,defStyleAttr);
        initView();
    }
    private DrawViewBlueLineView mCycleView;
    private TextView mDescriptionText,mMakeName;
    private void initView(){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_valuation_sell_replacetime_layout,null);
        mCycleView = (DrawViewBlueLineView) view.findViewById(R.id.valuation_sell_replace_cycleView);
        mDescriptionText = (TextView) view.findViewById(R.id.valuation_sell_replace_description);
        mMakeName = (TextView) view.findViewById(R.id.valuation_sell_replace_makeName);
        this.addView(view);
    }
    public void showReplaceCycleData(ValuationSellCarResult result){
        mCycleView.toShowData(result,getSizeRatio());
        String str = result.getBestChangePerc();
        mMakeName.setText(result.getModelName()+"车主换车周期分布图");
        String description = str+"的"+result.getMakeName()+"车主选择在第"+result.getBestChangeYear()+"年卖掉爱车"+"\n"+"精真估预测"+result.getBestChangeYear()+"年后该车价格或将大幅下降";
        mDescriptionText.setText(description);
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
