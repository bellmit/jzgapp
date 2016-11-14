package com.jzg.jzgoto.phone.view.buy;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by WY on 2016/9/14.
 * Function :Scroll中GridView，用于买车更多筛选界面
 */
public class BuyCarGridViewInScroll extends GridView{

    public BuyCarGridViewInScroll(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BuyCarGridViewInScroll(Context context) {
        super(context);
    }

    public BuyCarGridViewInScroll(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
