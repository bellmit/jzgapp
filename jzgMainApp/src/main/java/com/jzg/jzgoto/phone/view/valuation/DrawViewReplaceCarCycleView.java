package com.jzg.jzgoto.phone.view.valuation;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.util.AttributeSet;

import com.jzg.jzgoto.phone.models.business.valuation.DrawViewLineChartDataModel;
import com.jzg.jzgoto.phone.models.business.valuation.ValuationSellCarReplaceCycleModel;
import com.jzg.jzgoto.phone.models.business.valuation.ValuationSellCarResult;

/**
 * @Description: 价格走势
 * @Package com.jzg.jzgoto.phone.customview.business.valuation.sell SelfBrokenLineNowView.java
 * @author gf
 * @date 2016-6-3 上午9:44:04
 */
public class DrawViewReplaceCarCycleView extends DrawViewChartBaseView {

    public DrawViewReplaceCarCycleView(Context context, AttributeSet attrs,
                                 int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DrawViewReplaceCarCycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawViewReplaceCarCycleView(Context context) {
        super(context);
    }

    @Override
    public void toShowData(Object obj, float ratio) {
        DrawViewLineChartDataModel dataModel = (DrawViewLineChartDataModel)obj;
        List<Float> pointList = dataModel.getPointDataList();
        float[] pointValues = dataModel.getPointShowData();
        String[] xShow = dataModel.getxShowValue();
        float max = Collections.max(pointList);
        float min = Collections.min(pointList);
        float space = (max-min)/6f;
        int scale = 0;
        if(space < 1f){
            scale = 1;
        }
        BigDecimal bdMax = new BigDecimal(max);
        max = bdMax.setScale(scale,BigDecimal.ROUND_UP).floatValue();
        BigDecimal bdMin = new BigDecimal(min);
        min = bdMin.setScale(scale,BigDecimal.ROUND_DOWN).floatValue();
        space = (max-min)/6f;
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
        space=space*6f/5f;
        min -=space;
        if(min<0){
            min = 0;
        }
        max = min + space*6;
        final String[] yShow = new String[6];
        for(int i = 0;i<6;i++){
            if(dataModel.isShowYPercent()){
                yShow[i] = getFloatStr(min + space*(5-i))+"%";
            }else{
                yShow[i] = getFloatStr(min + space*(5-i));
            }
        }
        DrawViewReplaceCarCycleBean bean = new DrawViewReplaceCarCycleBean();
        bean.setShowMaxAndMin(max, min);
        bean.setShowValues(pointValues, null);
        bean.setXAndYShow(xShow, yShow);
        bean.setRatio(ratio);
        setDrawBean(bean);
//        List<HistoryChengjiaoPrice> priceList = mSellResult.getYearPrice();
//        List<Float> perList = new ArrayList<Float>();
//        List<Float> busList = new ArrayList<Float>();
//        float[] xValues = new float[7];
//        float[] yValues = new float[7];
//        String[] xShow = new String[7];
//        for(int i=0;i<7;i++){
//            if(i<priceList.size()){
//                xValues[i] = priceList.get(i).getPersonal();
//                yValues[i] = priceList.get(i).getBusiness();
//                perList.add(priceList.get(i).getPersonal());
//                busList.add(priceList.get(i).getBusiness());
//                if(i==3){
//                    xShow[i] = "现在";
//                }else{
//                    xShow[i] = priceList.get(i).getDate();
//                }
//            }else{
//                xValues[i] = 0;
//                yValues[i] = 0;
//                perList.add(0f);
//                busList.add(0f);
//                xShow[i] = "";
//            }
//        }
//        float max = Collections.max(perList);
//        float min = Collections.min(busList);
//        if(Collections.max(perList)<Collections.max(busList)){
//            max = Collections.max(busList);
//        }
//        if(Collections.min(perList)<Collections.min(busList)){
//            min = Collections.min(perList);
//        }
//
//        float space = (max-min)/6f;
//        int scale = 0;
//        if(space < 1f){
//            scale = 1;
//        }
//        BigDecimal bdMax = new BigDecimal(max);
//        max = bdMax.setScale(scale,BigDecimal.ROUND_UP).floatValue();
//        BigDecimal bdMin = new BigDecimal(min);
//        min = bdMin.setScale(scale,BigDecimal.ROUND_DOWN).floatValue();
//        space = (max-min)/6f;
//        if(space>=0.1f && space<1f){
//            space *=10f;
//            BigDecimal bd = new BigDecimal(space);
//            float va = bd.setScale(0,BigDecimal.ROUND_UP).floatValue();
//            space=va/10f;
//        } else if(space>=1f) {
//            BigDecimal bd = new BigDecimal(space);
//            space = bd.setScale(0,BigDecimal.ROUND_UP).floatValue();
//        } else {
//            space = 0.1f;
//        }
//        space=space*6f/5f;
//        min -=space;
//        if(min<0){
//            min = 0;
//        }
//        max = min + space*6;
//        final String[] yShow = new String[6];
//        for(int i = 0;i<6;i++){
//            yShow[i] = getFloatStr(min + space*(5-i));
//        }
//        DrawViewReplaceCarCycleBean bean = new DrawViewReplaceCarCycleBean();
//        bean.setShowMaxAndMin(max, min);
//        bean.setShowValues(xValues, yValues);
//        bean.setXAndYShow(xShow, yShow);
//        bean.setRatio(ratio);
//        setDrawBean(bean);
    }
}
