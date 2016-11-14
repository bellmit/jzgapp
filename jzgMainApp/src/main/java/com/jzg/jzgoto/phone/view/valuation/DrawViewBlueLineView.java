package com.jzg.jzgoto.phone.view.valuation;

import android.content.Context;
import android.util.AttributeSet;

import com.jzg.jzgoto.phone.models.business.valuation.DrawViewLineChartDataModel;
import com.jzg.jzgoto.phone.models.business.valuation.ValuationSellCarReplaceCycleModel;
import com.jzg.jzgoto.phone.models.business.valuation.ValuationSellCarResult;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by WY on 2016/9/21.
 * Function :换车周期分布折线图
 */
public class DrawViewBlueLineView extends DrawViewChartBaseView {

    public DrawViewBlueLineView(Context context, AttributeSet attrs,
                                int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DrawViewBlueLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawViewBlueLineView(Context context) {
        super(context);
    }

    @Override
    public void toShowData(Object obj, float ratio) {
        if(obj!=null){
            ValuationSellCarResult mSellResult = (ValuationSellCarResult)obj;
            List<ValuationSellCarReplaceCycleModel> modelList = mSellResult.getChangeCycle();
            List<Float> pointList = new ArrayList<>();
            float[] pointValues = new float[8];
            String[] xShowValues = new String[8];
            DecimalFormat df=new DecimalFormat(".##");
            for(int i=0;i<8;i++){
                if(i<modelList.size()){
                    String str = modelList.get(i).getPercentage();
                    if(str.contains("%")){
                        str = str.replace("%","");
                    }
                    str = df.format(Double.valueOf(str));
                    pointList.add(Float.valueOf(str));
                    pointValues[i] = Float.valueOf(str);
                    xShowValues[i] = modelList.get(i).getYear();
                }else{
                    pointList.add(0f);
                    pointValues[i] = 0f;
                    xShowValues[i] = (i+1)+"年";
                }
            }

            float max = Collections.max(pointList);
            float min = Collections.min(pointList);

            float space = (max-min)/5f;
            int scale = 0;
            if(space < 1f){
                scale = 1;
            }
            BigDecimal bdMax = new BigDecimal(max);
            max = bdMax.setScale(scale,BigDecimal.ROUND_UP).floatValue();
            BigDecimal bdMin = new BigDecimal(min);
            min = bdMin.setScale(scale,BigDecimal.ROUND_DOWN).floatValue();
            space = (max-min)/5f;
            if(space>=0.1f && space<1f){
                space *=10f;
                BigDecimal bd = new BigDecimal(space);
                float va = bd.setScale(0,BigDecimal.ROUND_UP).floatValue();
                space=va/10f;
            } else if(space>=1f) {
                BigDecimal bd = new BigDecimal(space);
                space = bd.setScale(0,BigDecimal.ROUND_UP).floatValue();
            } else {
                space = 0.1f;
            }
            space=space*5f/4f;
            min -=space;
            if(min<0){
                min = 0;
            }
            max = min + space*5;
            final String[] yShowValues = new String[6];
            for(int i = 0;i<6;i++){
                yShowValues[i] = getFloatStrNu(min + space*(5-i))+"%";
            }
            DrawViewBlueLineBean bean = new DrawViewBlueLineBean();
            bean.setShowMaxAndMin(max, min);
            bean.setShowValues(pointValues);
            bean.setXAndYShow(xShowValues, yShowValues);
            bean.setRatio(ratio);
            setDrawBean(bean);
        }else{
            DrawViewBlueLineBean bean = new DrawViewBlueLineBean();
            bean.setRatio(ratio);
            setDrawBean(bean);
        }
    }
}
