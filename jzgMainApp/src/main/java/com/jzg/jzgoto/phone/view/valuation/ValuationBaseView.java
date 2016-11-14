package com.jzg.jzgoto.phone.view.valuation;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.RelativeLayout;

/**
 * Created by WY on 2016/9/21.
 * Function :
 */
public abstract class ValuationBaseView extends RelativeLayout{

    public ValuationBaseView(Context context, AttributeSet attrs,
                                 int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initThisWidget();
    }

    public ValuationBaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initThisWidget();
    }

    public ValuationBaseView(Context context) {
        super(context);
        initThisWidget();
    }
    private void initThisWidget(){
        initView();
        initListener();
    }
    public abstract void initView();
    public abstract void initListener();
    /*
    public float getSizeRatio(){
        return ((ValuationBaseActivity)getContext()).getSizeRatio();
    }
    */
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
