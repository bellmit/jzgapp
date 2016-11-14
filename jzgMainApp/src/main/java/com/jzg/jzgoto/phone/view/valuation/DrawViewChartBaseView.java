package com.jzg.jzgoto.phone.view.valuation;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import java.text.DecimalFormat;

/**
 * Created by jzg on 2016/9/21.
 * Function :
 */
public abstract class DrawViewChartBaseView extends View
        implements DrawViewMethodInterface{

    public DrawViewChartBaseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public DrawViewChartBaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public DrawViewChartBaseView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        toDrawContent(canvas);
    }
    private DrawViewBaseBean mBeanDrawBase;

    @Override
    public void toDrawContent(Canvas canvas) {
        canvas.save();
        if(mBeanDrawBase != null){
            mBeanDrawBase.initShowData();
            mBeanDrawBase.initAllPaint();
            mBeanDrawBase.toDrawBase(canvas);
            mBeanDrawBase.toDrawLine(canvas);
            mBeanDrawBase.toDrawColumn(canvas);
            mBeanDrawBase.toDrawPie(canvas);
            mBeanDrawBase.toDrawText(canvas);
        }
        canvas.restore();
    }
    @Override
    public void setDrawBean(DrawViewBaseBean bean) {
        mBeanDrawBase = bean;
        mBeanDrawBase.mHeight = this.getHeight();
        String model = Build.MODEL;
        mBeanDrawBase.mWidth = this.getWidth();
        this.invalidate();
    }
    @Override
    public DrawViewBaseBean getDrawBean() {
        return mBeanDrawBase;
    }
    @Override
    public void setBeanHeightAndWidth(int height, int width) {
        if(mBeanDrawBase != null){
            mBeanDrawBase.mHeight = height;
            mBeanDrawBase.mWidth = width;
        }
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        setBeanHeightAndWidth(height, width);
    }
    @Override
    public void toRefreshView() {
        this.invalidate();
    }
    public String getFloatStr(Float f){
        DecimalFormat df = new DecimalFormat("#0.00");
        String str = df.format(f);
        return str;
    }
    public String getFloatStrNu(Float f){
        DecimalFormat df = new DecimalFormat("#0");
        String str = df.format(f);
        return str;
    }
    public String getStrFromL(String old){
        if(!old.contains(".")){
            return old;
        }
        String[] strs = old.split("\\.");
        if(strs.length<2){
            return old;
        }
        if(strs[1].length() < 2){
            return old;
        }
        return old.substring(0,old.indexOf(".") + 3);
    }
}
