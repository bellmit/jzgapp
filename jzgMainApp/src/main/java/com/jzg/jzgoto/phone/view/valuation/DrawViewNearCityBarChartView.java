package com.jzg.jzgoto.phone.view.valuation;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.jzg.jzgoto.phone.models.business.valuation.ValuationBuyCarNearCityModel;
import com.jzg.jzgoto.phone.models.business.valuation.ValuationBuyCarResult;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jzg on 2016/9/29.
 * Function :
 */
public class DrawViewNearCityBarChartView extends DrawViewChartBaseView{

    public DrawViewNearCityBarChartView(Context context, AttributeSet attrs,
                            int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DrawViewNearCityBarChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawViewNearCityBarChartView(Context context) {
        super(context);
    }
    /**
     * 处理显示信息
     * @param obj
     * @param ratio
     */
    @Override
    public void toShowData(Object obj,float ratio){
        DrawViewNearCityBarChartBean bean = new DrawViewNearCityBarChartBean();
        if(null != obj) {

            ValuationBuyCarResult result = (ValuationBuyCarResult) obj;
            final List<ValuationBuyCarNearCityModel> cityList = new ArrayList<>();
            cityList.addAll(result.getNearCity());
            final int size = cityList.size();
            if (size < 4) {
                for (int i = 0; i < (4 - size); i++) {
                    ValuationBuyCarNearCityModel m = new ValuationBuyCarNearCityModel();
                    m.setCityName("");
                    m.setSum("");
                    m.setPrice("0");
                    cityList.add(m);
                }
            }
            float[] showValue = new float[4];
            String[] yShowOne = new String[4];
            String[] yShowTwo = new String[4];
            float max = 0f;
            for(int i = 0;i < 4;i++){
                ValuationBuyCarNearCityModel item = cityList.get(i);
                yShowOne[i] = item.getCityName();
                if(!TextUtils.isEmpty(item.getSum())){
                    yShowTwo[i] = item.getSum() + "辆";
                } else {
                    yShowTwo[i] = "";
                }
                String price = item.getPrice();
                showValue[i] = Float.valueOf(price);
                if(showValue[i] > max){
                    max = showValue[i];
                }
            }
            float space = max/6f;

            BigDecimal bdSpace = new BigDecimal(space);
            space = bdSpace.setScale(0,BigDecimal.ROUND_UP).floatValue();
			/*
			int scale = 0;
			if(space < 1f){
				scale = 1;
			}
			BigDecimal bdMax = new BigDecimal(max);
			max = bdMax.setScale(scale,BigDecimal.ROUND_UP).floatValue();
			*/

            bean.setShowBarValue(showValue);
            bean.setShowMaxAndMin(space*6, 0f);

            String[] xShow = new String[7];
            for(int i = 0;i < 7;i++){
                xShow[i] = getFloatStrNu(0f+(space*i));
            }
            bean.setXAndYShow(xShow, yShowOne, yShowTwo);
        }
        bean.setRatio(ratio);
        setDrawBean(bean);


//            final List<RequestValuNearCityModel> cityList = new ArrayList<RequestValuNearCityModel>();
//            cityList.addAll(result.getNearCity());
//
//            final int size = cityList.size();
//            if(size<4){
//                for(int i = 0;i < (4-size);i++){
//                    RequestValuNearCityModel m = new RequestValuNearCityModel();
//                    m.setCityName("");
//                    m.setSum("");
//                    m.setPrice("0");
//                    cityList.add(m);
//                }
//            }
//
//            float[] showValue = new float[4];
//            String[] yShowOne = new String[4];
//            String[] yShowTwo = new String[4];
//            float max = 0f;
//            for(int i = 0;i < 4;i++){
//                RequestValuNearCityModel item = cityList.get(i);
//                yShowOne[i] = item.getCityName();
//                if(!TextUtils.isEmpty(item.getSum())){
//                    yShowTwo[i] = item.getSum() + "辆";
//                } else {
//                    yShowTwo[i] = "";
//                }
//                String price = item.getPrice();
//                showValue[i] = Float.valueOf(price);
//                if(showValue[i] > max){
//                    max = showValue[i];
//                }
//            }
//            float space = max/6f;
//
//            BigDecimal bdSpace = new BigDecimal(space);
//            space = bdSpace.setScale(0,BigDecimal.ROUND_UP).floatValue();
//			/*
//			int scale = 0;
//			if(space < 1f){
//				scale = 1;
//			}
//			BigDecimal bdMax = new BigDecimal(max);
//			max = bdMax.setScale(scale,BigDecimal.ROUND_UP).floatValue();
//			*/
//
//            bean.setShowBarValue(showValue);
//            bean.setShowMaxAndMin(space*6, 0f);
//
//            String[] xShow = new String[7];
//            for(int i = 0;i < 7;i++){
//                xShow[i] = getFloatStrNu(0f+(space*i));
//            }
//            bean.setXAndYShow(xShow, yShowOne, yShowTwo);
//        }
//        bean.setRatio(ratio);
//        setDrawBean(bean);
    }
}
