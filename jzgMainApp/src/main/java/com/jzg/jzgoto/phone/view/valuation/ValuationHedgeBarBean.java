package com.jzg.jzgoto.phone.view.valuation;

import android.graphics.Canvas;
import android.text.TextUtils;

/**
 * Created by jzg on 2016/9/21.
 * Function :
 */
public class ValuationHedgeBarBean extends DrawViewBaseBean{

    private float mPadLeft = 60;
    private float mPadTop = 60;
    private float mPadRight = 60;
    private float mPadBottom = 60;

    private float mLineHeight = 0f;
    private float mLineWidth = 0f;

    private float mBeginX = 0f;
    private float mBeginY = 0f;
    private float mEndX = mWidth;
    private float mEndY = mHeight;

    private float mMinShow = 0f;
    private float mMaxShow = 100f;
    private float mShowLevel = 20f;
    public void setShowMaxAndMin(float max,float min){
        mMinShow = min;
        mMaxShow = max;
    }

    private float[] mShowHeightValue = {80f,71f,64f,42f,35f};
    private String[] mShowTextOne = {"80%","71%","64%","42%","35%"};
    private float[] mShowHeight = new float[5];
    public void setShowValueAndStr(float[] values,String[] topShow){
        if(values != null && values.length == mShowHeightValue.length){
            for(int i = 0;i < mShowHeightValue.length;i++){
                mShowHeightValue[i] = values[i];
            }
        }
        if(topShow != null && topShow.length == mShowTextOne.length){
            for(int i = 0;i < mShowTextOne.length;i++){
                mShowTextOne[i] = topShow[i];
            }
        }
    }

    private String[] mXShow = {"2017年","2018年","2019年","2020年","2021年"};
    public void setXShow(String[] x ){
        if(x != null && x.length == mXShow.length)
            for(int i = 0;i < mXShow.length;i++){
                mXShow[i] = x[i];
            }
    }

    private float mLineXBegin = 0f;
    private float mTextSpace = 0f;

    private float mBarWidth = 0f;
    private float mHeightShow = 0f;
    @Override
    public void initShowData() {
        final float ratio = getRatio();
        mPadLeft=ratio*30;
        mPadTop=ratio*30;
        mPadBottom=ratio*30;
        mPadRight=ratio*30;

        mHeightShow = mHeight - mPadBottom - mPadTop;
        mLineHeight = mHeightShow/5;
        mLineWidth = (mWidth - mPadLeft - mPadRight)/5;

        mBarWidth = mLineWidth/2;

        mBeginX = mPadLeft;
        mBeginY = mPadTop;
        mEndX = mWidth - mPadRight;
        mEndY = mHeight - mPadBottom;

        mLineXBegin = mBeginX + mLineWidth/2;
        mTextHeight = getTextHeight();

        for(int i = 0;i < mShowHeightValue.length;i++){
            mShowHeight[i] = (mMaxShow-mShowHeightValue[i])*(mHeightShow/mMaxShow);
        }

    }

    @Override
    public void toDrawBase(Canvas canvas) {

//		canvas.drawLine(mBeginX-10, mBeginY, mEndX, mBeginY, mPintBaseGreyLine);
        canvas.drawLine(mBeginX-10, mBeginY + mLineHeight, mEndX, mBeginY + mLineHeight, mPintBaseGreyLine);
        canvas.drawLine(mBeginX-10, mBeginY + mLineHeight*2, mEndX, mBeginY + mLineHeight*2, mPintBaseGreyLine);
        canvas.drawLine(mBeginX-10, mBeginY + mLineHeight*3, mEndX, mBeginY + mLineHeight*3, mPintBaseGreyLine);
        canvas.drawLine(mBeginX-10, mBeginY + mLineHeight*4, mEndX, mBeginY + mLineHeight*4, mPintBaseGreyLine);
        canvas.drawLine(mBeginX-10, mBeginY + mLineHeight*5, mEndX, mBeginY + mLineHeight*5, mPintBaseGreyLine);

    }
    @Override
    public void toDrawLine(Canvas canvas) {
    }
    @Override
    public void toDrawColumn(Canvas canvas) {
    }
    @Override
    public void toDrawPie(Canvas canvas) {

//		canvas.drawRect(rect, mPintColorBlue);
        mPintColorBlue.setAlpha(26);
        canvas.drawRect(mLineXBegin - mBarWidth/2, mBeginY,
                mLineXBegin + mBarWidth/2, mEndY, mPintColorBlue);
        mPintColorBlue.setAlpha(200);
        canvas.drawRect(mLineXBegin - mBarWidth/2, mBeginY + mShowHeight[0],
                mLineXBegin + mBarWidth/2, mEndY, mPintColorBlue);

        mPintColorGreen.setAlpha(26);
        canvas.drawRect(mLineXBegin - mBarWidth/2 + mLineWidth, mBeginY,
                mLineXBegin + mBarWidth/2 + mLineWidth, mEndY, mPintColorGreen);
        mPintColorGreen.setAlpha(200);
        canvas.drawRect(mLineXBegin - mBarWidth/2 + mLineWidth, mBeginY + mShowHeight[1],
                mLineXBegin + mBarWidth/2 + mLineWidth, mEndY, mPintColorGreen);

        mPintColorYellow.setAlpha(26);
        canvas.drawRect(mLineXBegin - mBarWidth/2 + mLineWidth*2, mBeginY,
                mLineXBegin + mBarWidth/2 + mLineWidth*2, mEndY, mPintColorYellow);
        mPintColorYellow.setAlpha(200);
        canvas.drawRect(mLineXBegin - mBarWidth/2 + mLineWidth*2, mBeginY + mShowHeight[2],
                mLineXBegin + mBarWidth/2 + mLineWidth*2, mEndY, mPintColorYellow);

        mPintColorOrange.setAlpha(26);
        canvas.drawRect(mLineXBegin - mBarWidth/2 + mLineWidth*3, mBeginY,
                mLineXBegin + mBarWidth/2 + mLineWidth*3, mEndY, mPintColorOrange);
        mPintColorOrange.setAlpha(200);
        canvas.drawRect(mLineXBegin - mBarWidth/2 + mLineWidth*3, mBeginY + mShowHeight[3],
                mLineXBegin + mBarWidth/2 + mLineWidth*3, mEndY, mPintColorOrange);

        mPintColorRed.setAlpha(26);
        canvas.drawRect(mLineXBegin - mBarWidth/2 + mLineWidth*4, mBeginY,
                mLineXBegin + mBarWidth/2 + mLineWidth*4, mEndY, mPintColorRed);
        mPintColorRed.setAlpha(200);
        canvas.drawRect(mLineXBegin - mBarWidth/2 + mLineWidth*4, mBeginY + mShowHeight[4],
                mLineXBegin + mBarWidth/2 + mLineWidth*4, mEndY, mPintColorRed);

    }
    @Override
    public void toDrawText(Canvas canvas) {

        canvas.drawText(mShowTextOne[0], mLineXBegin, mShowHeight[0]+mTextHeight*2, mPintBaseGreyText);
        canvas.drawText(mShowTextOne[1], mLineXBegin+mLineWidth, mShowHeight[1]+mTextHeight*2, mPintBaseGreyText);
        canvas.drawText(mShowTextOne[2], mLineXBegin+mLineWidth*2, mShowHeight[2]+mTextHeight*2, mPintBaseGreyText);
        canvas.drawText(mShowTextOne[3], mLineXBegin+mLineWidth*3, mShowHeight[3]+mTextHeight*2, mPintBaseGreyText);
        canvas.drawText(mShowTextOne[4], mLineXBegin+mLineWidth*4, mShowHeight[4]+mTextHeight*2, mPintBaseGreyText);

        canvas.drawText(mXShow[0], mLineXBegin, mEndY + mTextHeight*2, mPintBaseGreyText);
        canvas.drawText(mXShow[1], mLineXBegin+mLineWidth, mEndY + mTextHeight*2, mPintBaseGreyText);
        canvas.drawText(mXShow[2], mLineXBegin+mLineWidth*2, mEndY + mTextHeight*2, mPintBaseGreyText);
        canvas.drawText(mXShow[3], mLineXBegin+mLineWidth*3, mEndY + mTextHeight*2, mPintBaseGreyText);
        canvas.drawText(mXShow[4], mLineXBegin+mLineWidth*4, mEndY + mTextHeight*2, mPintBaseGreyText);

    }
    public float getTextWidth(String str){
        if(TextUtils.isEmpty(str))return 0f;
        return mPintBaseGreyText.measureText(str);
    }

}
