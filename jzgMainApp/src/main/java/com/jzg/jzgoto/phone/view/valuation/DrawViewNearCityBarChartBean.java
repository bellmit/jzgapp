package com.jzg.jzgoto.phone.view.valuation;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextUtils;

/**
 * Created by jzg on 2016/9/29.
 * Function :
 */
public class DrawViewNearCityBarChartBean extends DrawViewBaseBean{

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
    private float mMaxShow = 6f;
    public void setShowMaxAndMin(float max,float min){
        mMinShow = min;
        mMaxShow = max;
    }
    /**
     * 每等分的值
     */
    private float mShowLevel = 1f;

    private float[] mShowLineOne = {4.01f,5.77f,3.66f,4.56f};
    private float[] mDrawLineWidthOne = new float[4];
    public void setShowBarValue(final float[] values){
        if(values != null && values.length == mShowLineOne.length){
            for(int i = 0;i < mShowLineOne.length;i++){
                mShowLineOne[i] = values[i];
            }
        }
    }
    /**
     * 开始
     */
    private float mLineXBegin = 0f;
    /**
     * 结束
     */
    private float mLineXEnd = 0f;

    private float mLineYBegin = 0f;

    private float mTextSpace = 0f;

    private String[] mXShow = {"0","1","2","3","4","5","6"};
    private String[] mYShowOne = {"济南","太原","石家庄","沈阳"};
    private String[] mYShowTwo = {"280辆","420辆","370辆","1280辆"};

    public void setXAndYShow(String[] xShow,String[] yShowOne,String[] yShowTwo){
        if(xShow != null && xShow.length == mXShow.length){
            for(int i = 0;i < mXShow.length;i++){
                mXShow[i] = xShow[i];
            }
        }
        if(yShowOne != null && yShowOne.length == mYShowOne.length){
            for(int i = 0;i < mYShowOne.length;i++){
                mYShowOne[i] = yShowOne[i];
            }
        }
        if(yShowTwo != null && yShowTwo.length == mYShowTwo.length){
            for(int i = 0;i < mYShowTwo.length;i++){
                mYShowTwo[i] = yShowTwo[i];
            }
        }
    }

    /**
     * 柱体宽度
     */
    private float mBarHeight = 40;

    @Override
    public void initShowData() {
        final float ratio = getRatio();
        mPadLeft=ratio*30;
        mPadTop=ratio*30;
        mPadBottom=ratio*30;
        mPadRight=ratio*30;

        mLineHeight = (mHeight - mPadBottom - mPadTop)/4;
        mLineWidth = (mWidth - mPadLeft - mPadRight)/8;
        mBeginX = mPadLeft;
        mBeginY = mHeight - mPadBottom - mLineHeight*4;
        mEndX = mWidth - mPadRight;
        mEndY = mHeight - mPadBottom;
        mBarHeight = mLineHeight/3;

        mShowLevel = (mMaxShow - mMinShow)/6;

        mLineXBegin = mPadLeft + mLineWidth;
        mLineXEnd = mWidth - mPadRight - mLineWidth;
        mLineYBegin = mPadBottom + mLineHeight/2 - mBarHeight/2;
//有圆角
//        for(int i = 0;i < mShowLineOne.length;i++){
//            mDrawLineWidthOne[i] = mShowLineOne[i]*(mLineWidth/mShowLevel) + mLineXBegin - mBarHeight/4;
//            if(mDrawLineWidthOne[i] < mBarHeight){
//                mDrawLineWidthOne[i] = mBarHeight;
//            }
//        }
        //无圆角
        for(int i = 0;i < mShowLineOne.length;i++){
            mDrawLineWidthOne[i] = mShowLineOne[i]*(mLineWidth/mShowLevel) + mLineXBegin;
            if(mDrawLineWidthOne[i] < mBarHeight){
                mDrawLineWidthOne[i] = mBarHeight;
            }
        }

        mTextHeight = getTextHeight();
        mTextSpace = mTextHeight/2;
    }

    @Override
    public void toDrawBase(Canvas canvas) {

        canvas.drawLine(mBeginX-10, mEndY, mEndX, mEndY, mPintBaseGreyLine);

        canvas.drawLine(mBeginX + mLineWidth, mBeginY - 10, mBeginX + mLineWidth, mEndY, mPintBaseGreyLine);
        canvas.drawLine(mBeginX + mLineWidth*2, mBeginY - 10, mBeginX + mLineWidth*2, mEndY, mPintBaseGreyLine);
        canvas.drawLine(mBeginX + mLineWidth*3, mBeginY - 10, mBeginX + mLineWidth*3, mEndY, mPintBaseGreyLine);
        canvas.drawLine(mBeginX + mLineWidth*4, mBeginY - 10, mBeginX + mLineWidth*4, mEndY, mPintBaseGreyLine);
        canvas.drawLine(mBeginX + mLineWidth*5, mBeginY - 10, mBeginX + mLineWidth*5, mEndY, mPintBaseGreyLine);
        canvas.drawLine(mBeginX + mLineWidth*6, mBeginY - 10, mBeginX + mLineWidth*6, mEndY, mPintBaseGreyLine);
        canvas.drawLine(mBeginX + mLineWidth*7, mBeginY - 10, mBeginX + mLineWidth*7, mEndY, mPintBaseGreyLine);

    }

    @Override
    public void toDrawLine(Canvas canvas) {
    }
    @Override
    public void toDrawColumn(Canvas canvas) {
        // TODO
        mPintColorBlue.setStyle(Paint.Style.FILL);
        if(mShowLineOne[3] != 0f){
            canvas.drawRect(mLineXBegin, mLineYBegin, mDrawLineWidthOne[3], mLineYBegin + mBarHeight, mPintColorNearCityOrange3);
     //       canvas.drawCircle(mDrawLineWidthOne[0], mPadBottom + mLineHeight/2, mBarHeight/2, mPintColorNearCityOrange1);
        }
        if(mShowLineOne[2] != 0f){
            canvas.drawRect(mLineXBegin, mLineYBegin + mLineHeight, mDrawLineWidthOne[2], mLineYBegin + mLineHeight + mBarHeight, mPintColorNearCityOrange4);
        //    canvas.drawCircle(mDrawLineWidthOne[1], mPadBottom + mLineHeight/2 + mLineHeight, mBarHeight/2, mPintColorNearCityOrange2);
        }
        if(mShowLineOne[1] != 0f){
            canvas.drawRect(mLineXBegin, mLineYBegin + mLineHeight*2, mDrawLineWidthOne[1], mLineYBegin + mLineHeight*2 + mBarHeight, mPintColorNearCityOrange1);
       //     canvas.drawCircle(mDrawLineWidthOne[2], mPadBottom + mLineHeight/2 + mLineHeight*2, mBarHeight/2, mPintColorNearCityOrange3);
        }
        if(mShowLineOne[0] != 0f){
            canvas.drawRect(mLineXBegin, mLineYBegin + mLineHeight*3, mDrawLineWidthOne[0], mLineYBegin + mLineHeight*3 + mBarHeight, mPintColorNearCityOrange2);
       //     canvas.drawCircle(mDrawLineWidthOne[3], mPadBottom + mLineHeight/2 + mLineHeight*3, mBarHeight/2, mPintColorNearCityOrange4);
        }
    }
    @Override
    public void toDrawPie(Canvas canvas) {
    }
    @Override
    public void toDrawText(Canvas canvas) {
        if(mShowLineOne[3] != 0f)
            canvas.drawText(Float.toString(mShowLineOne[3]), mDrawLineWidthOne[3] + getTextWidth(Float.toString(mShowLineOne[3])), mPadBottom + mLineHeight/2 + mTextSpace, mPintBaseGreyText);
        if(mShowLineOne[2] != 0f)
            canvas.drawText(Float.toString(mShowLineOne[2]), mDrawLineWidthOne[2] + getTextWidth(Float.toString(mShowLineOne[2])), mPadBottom + mLineHeight/2 + mLineHeight + mTextSpace, mPintBaseGreyText);
        if(mShowLineOne[1] != 0f)
            canvas.drawText(Float.toString(mShowLineOne[1]), mDrawLineWidthOne[1] + getTextWidth(Float.toString(mShowLineOne[1])), mPadBottom + mLineHeight/2 + mLineHeight*2 + mTextSpace, mPintBaseGreyText);
        if(mShowLineOne[0] != 0f)
            canvas.drawText(Float.toString(mShowLineOne[0]), mDrawLineWidthOne[0] + getTextWidth(Float.toString(mShowLineOne[0])), mPadBottom + mLineHeight/2 + mLineHeight*3 + mTextSpace, mPintBaseGreyText);

        canvas.drawText(mXShow[0], mLineXBegin, mEndY + mTextHeight*2, mPintBaseGreyText);
        canvas.drawText(mXShow[1], mLineXBegin+mLineWidth, mEndY + mTextHeight*2, mPintBaseGreyText);
        canvas.drawText(mXShow[2], mLineXBegin+mLineWidth*2, mEndY + mTextHeight*2, mPintBaseGreyText);
        canvas.drawText(mXShow[3], mLineXBegin+mLineWidth*3, mEndY + mTextHeight*2, mPintBaseGreyText);
        canvas.drawText(mXShow[4], mLineXBegin+mLineWidth*4, mEndY + mTextHeight*2, mPintBaseGreyText);
        canvas.drawText(mXShow[5], mLineXBegin+mLineWidth*5, mEndY + mTextHeight*2, mPintBaseGreyText);
        canvas.drawText(mXShow[6], mLineXBegin+mLineWidth*6, mEndY + mTextHeight*2, mPintBaseGreyText);

        canvas.drawText(mYShowOne[3], mLineXBegin-10*getRatio()-getTextWidth(mYShowOne[3])/2, mPadBottom + mLineHeight/2 + mTextSpace, mPintBaseGreyText);
        canvas.drawText(mYShowOne[2], mLineXBegin-10*getRatio()-getTextWidth(mYShowOne[2])/2, mPadBottom + mLineHeight/2 + mLineHeight + mTextSpace, mPintBaseGreyText);
        canvas.drawText(mYShowOne[1], mLineXBegin-10*getRatio()-getTextWidth(mYShowOne[1])/2, mPadBottom + mLineHeight/2 + mLineHeight*2 + mTextSpace, mPintBaseGreyText);
        canvas.drawText(mYShowOne[0], mLineXBegin-10*getRatio()-getTextWidth(mYShowOne[0])/2, mPadBottom + mLineHeight/2 + mLineHeight*3 + mTextSpace, mPintBaseGreyText);

//        canvas.drawText(mYShowTwo[0], mLineXBegin-10*getRatio()-getTextWidth(mYShowTwo[0])/2, mLineYBegin + mBarHeight + mTextHeight, mPintBaseGreyText);
//        canvas.drawText(mYShowTwo[1], mLineXBegin-10*getRatio()-getTextWidth(mYShowTwo[1])/2, mLineYBegin + mBarHeight + mLineHeight + mTextHeight, mPintBaseGreyText);
//        canvas.drawText(mYShowTwo[2], mLineXBegin-10*getRatio()-getTextWidth(mYShowTwo[2])/2, mLineYBegin + mBarHeight + mLineHeight*2 + mTextHeight, mPintBaseGreyText);
//        canvas.drawText(mYShowTwo[3], mLineXBegin-10*getRatio()-getTextWidth(mYShowTwo[3])/2, mLineYBegin + mBarHeight + mLineHeight*3 + mTextHeight, mPintBaseGreyText);

    }
    public float getTextWidth(String str){
        if(TextUtils.isEmpty(str))return 0f;
        return mPintBaseGreyText.measureText(str);
    }
}
