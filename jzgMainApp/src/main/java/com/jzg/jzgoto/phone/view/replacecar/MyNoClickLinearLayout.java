package com.jzg.jzgoto.phone.view.replacecar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;
/**
 * @Description: 自定义用
 * @Package com.jzg.jzgoto.phone.customview.business.sell MyNoClickLinearLayout.java
 * @author gf
 * @date 2015-12-18 上午11:11:13
 */
public class MyNoClickLinearLayout extends LinearLayout {

    public MyNoClickLinearLayout(Context context, AttributeSet attrs,
                                 int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyNoClickLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyNoClickLinearLayout(Context context) {
        super(context);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

}

