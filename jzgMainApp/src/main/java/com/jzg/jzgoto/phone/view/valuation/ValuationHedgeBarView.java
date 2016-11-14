package com.jzg.jzgoto.phone.view.valuation;

import android.content.Context;
import android.util.AttributeSet;

import com.jzg.jzgoto.phone.activity.valuation.ValuationHedgeActivity.ItemBean;
import java.util.Calendar;

/**
 * Created by WY on 2016/9/21.
 * Function :
 */
public class ValuationHedgeBarView extends DrawViewChartBaseView{

    public ValuationHedgeBarView(Context context, AttributeSet attrs,
                            int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ValuationHedgeBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ValuationHedgeBarView(Context context) {
        super(context);
    }

    @Override
    public void toShowData(Object obj, float ratio) {
        ItemBean item = (ItemBean) obj;
        ValuationHedgeBarBean bean = new ValuationHedgeBarBean();
        if( null != item){
            bean.setXShow(getBottomYears());
            bean.setShowMaxAndMin(100, 0);
            String[] topShow = item.data;
            float[] values = new float[item.data.length];
            for(int i = 0;i < item.data.length;i++){
                String v = item.data[i];
                v = v.replace("%", "");
                values[i] = Float.valueOf(v);
            }
            bean.setShowValueAndStr(values, topShow);
        }
        bean.setRatio(ratio);
        setDrawBean(bean);
    }
    private String[] getBottomYears(){
        Calendar calendar = Calendar.getInstance();
        final int now = calendar.get(Calendar.YEAR);
        final String[] years = new String[5];
        for(int i = 0;i < years.length;i++){
            years[i] = Integer.toString(now+i+1) + "年";
        }
        return years;
    }
}
