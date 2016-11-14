package com.jzg.jzgoto.phone.view.carmanager;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import com.jzg.jzgoto.phone.view.carmanager.DrawViewLineChartDataModel;
import com.jzg.jzgoto.phone.view.valuation.DrawViewChartBaseView;

/**
 * @Description: 价格走势
 * @Package com.jzg.jzgoto.phone.customview.business.valuation.sell SelfBrokenLineNowView.java
 * @author gf
 * @date 2016-6-3 上午9:44:04
 */
public class PriceTrendView extends DrawViewChartBaseView {

    public PriceTrendView(Context context, AttributeSet attrs,
                                 int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PriceTrendView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PriceTrendView(Context context) {
        super(context);
    }

    @Override
    public void toShowData(Object obj, float ratio) {
        int yPointNum = 5;
        DrawViewLineChartDataModel dataModel = (DrawViewLineChartDataModel)obj;
        List<Float> pointList = dataModel.getPointDataList();
        float[] pointValues = dataModel.getPointShowData();
        String[] xShow = dataModel.getxShowValue();
        float max = Collections.max(pointList);
        float min = Collections.min(pointList);
        float space = (max-min)/(yPointNum - 1);
        int scale = 0;
        if(space < 1f){
            scale = 1;
        }
        BigDecimal bdMax = new BigDecimal(max);
        max = bdMax.setScale(scale,BigDecimal.ROUND_UP).floatValue();
        BigDecimal bdMin = new BigDecimal(min);
        min = bdMin.setScale(scale,BigDecimal.ROUND_DOWN).floatValue();
        space = (max-min)/(yPointNum - 1);
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
        //space=space*6f/5f;
        min -=space;
        if(min<0){
            min = 0;
        }
        //max = min + space*6;
        max += space;
        final String[] yShow = new String[6];
        for(int i = 0;i<yPointNum;i++){
            yShow[i] = getYMarkFloatStr(min + space*(yPointNum-i));
        }
        PriceTrendBean bean = new PriceTrendBean();
        bean.setShowMaxAndMin(max, min);
        bean.setShowValues(pointValues, null);
        bean.setXAndYShow(xShow, yShow);
        bean.setRatio(ratio);
        setDrawBean(bean);
    }

    public void toShowDefaultData(String[] xShow,String[] yShow, float ratio) {
        PriceTrendBean bean = new PriceTrendBean();
        bean.setShowValues(null, null);
        bean.setXAndYShow(xShow, yShow);
        bean.setRatio(ratio);
        setDrawBean(bean);
    }

    public String getYMarkFloatStr(Float f){
        DecimalFormat df = new DecimalFormat("#0.0");
        String str = df.format(f);
        return str;
    }
}
