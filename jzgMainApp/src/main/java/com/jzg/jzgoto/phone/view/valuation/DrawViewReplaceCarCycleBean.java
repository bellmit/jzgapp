package com.jzg.jzgoto.phone.view.valuation;

import android.graphics.Canvas;
import android.text.TextUtils;

/**
 * Created by jzg on 2016/9/29.
 * Function :
 */
public class DrawViewReplaceCarCycleBean extends DrawViewBaseBean{

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
    private float mShowLevel = 1f;
    public void setShowMaxAndMin(float max,float min){
        mMinShow = min;
        mMaxShow = max;
    }

    private float[] mShowLineOne = {5.33f,5.21f,4.88f,4.56f,4.32f,3.86f,3.45f};
    private float[] mShowLineTwo = {4.80f,3.84f,3.68f,3.80f,2.32f,2.10f,1.95f};
    private float[] mDrawLineHeigthOne = new float[7];
    private float[] mDrawLineHeigthTwo = new float[7];
    public void setShowValues(float[] xValues,float[] yVlaues){
        if(xValues != null && xValues.length == mShowLineOne.length){
            for(int i = 0;i < mShowLineOne.length;i++){
                mShowLineOne[i] = xValues[i];
            }
        } else {
            mShowLineOne = xValues;
        }
        if(yVlaues != null && yVlaues.length == mShowLineTwo.length){
            for(int i = 0;i < mShowLineTwo.length;i++){
                mShowLineTwo[i] = yVlaues[i];
            }
        } else {
            mShowLineTwo = yVlaues;
        }
    }

    private float mLineXBegin = 0f;
    private float mTextSpace = 0f;

    private String[] mXShow = {"16/01","16/02","16/03","现在","06/05","06/06","06/07"};
    private String[] mYShow = {"5","4","3","2","1","0"};
    public void setXAndYShow(String[] xShow,String[] yShow){
        if(xShow != null && xShow.length == mXShow.length){
            for(int i = 0;i < mXShow.length;i++){
                mXShow[i] = xShow[i];
            }
        }
        if(yShow != null && yShow.length == mYShow.length){
            for(int i = 0;i < mYShow.length;i++){
                mYShow[i] = yShow[i];
            }
        }
    }

    @Override
    public void initShowData() {
        final float ratio = getRatio();
        mPadLeft=ratio*45;
        mPadTop=ratio*40;
        mPadBottom=ratio*30;
        mPadRight=ratio*30;

        mLineHeight = (mHeight - mPadBottom - mPadTop)/5;
        mLineWidth = (mWidth - mPadLeft - mPadRight)/7;
        mBeginX = mPadLeft;
        mBeginY = mHeight - mPadBottom - mLineHeight*5;
        mEndX = mWidth - mPadRight;
        mEndY = mHeight - mPadBottom;

        mShowLevel = (mMaxShow - mMinShow)/5;
        if(null != mShowLineOne)
            for(int i = 0;i < mShowLineOne.length;i++){
                mDrawLineHeigthOne[i] = (mMaxShow - mShowLineOne[i])*(mLineHeight/mShowLevel) + mPadTop ;
            }
        if(null != mShowLineTwo)
            for(int i = 0;i < mShowLineTwo.length;i++){
                mDrawLineHeigthTwo[i] = (mMaxShow - mShowLineTwo[i])*(mLineHeight/mShowLevel) + mPadTop ;
            }

        mLineXBegin = mPadLeft + mLineWidth/2;

        mTextHeight = getTextHeight();
        mTextSpace = mTextHeight/2;

    }

    @Override
    public void toDrawBase(Canvas canvas) {

        canvas.drawLine(mLineXBegin, mBeginY - 10, mLineXBegin, mEndY, mPintBaseGreyLine);
        canvas.drawLine(mLineXBegin + mLineWidth, mBeginY - 10, mLineXBegin + mLineWidth, mEndY, mPintBaseGreyLine);
        canvas.drawLine(mLineXBegin + mLineWidth*2, mBeginY - 10, mLineXBegin + mLineWidth*2, mEndY, mPintBaseGreyLine);
        canvas.drawLine(mLineXBegin + mLineWidth*3, mBeginY - 10, mLineXBegin + mLineWidth*3, mEndY, mPintBaseGreyLine);
        canvas.drawLine(mLineXBegin + mLineWidth*4, mBeginY - 10, mLineXBegin + mLineWidth*4, mEndY, mPintBaseGreyLine);
        canvas.drawLine(mLineXBegin + mLineWidth*5, mBeginY - 10, mLineXBegin + mLineWidth*5, mEndY, mPintBaseGreyLine);
        canvas.drawLine(mLineXBegin + mLineWidth*6, mBeginY - 10, mLineXBegin + mLineWidth*6, mEndY, mPintBaseGreyLine);

    }

    @Override
    public void toDrawLine(Canvas canvas) {
        final float mBigRadius = 16*getRatio()/2;
        final float mSmallRadius = 12*getRatio()/2;
        if(null != mShowLineOne){
            float[] lineOneLeft = {mLineXBegin,mDrawLineHeigthOne[0],mLineXBegin+mLineWidth,mDrawLineHeigthOne[1],
                    mLineXBegin+mLineWidth,mDrawLineHeigthOne[1],mLineXBegin+mLineWidth*2,mDrawLineHeigthOne[2],
                    mLineXBegin+mLineWidth*2,mDrawLineHeigthOne[2],mLineXBegin+mLineWidth*3,mDrawLineHeigthOne[3]};

            float[] lineOneRight = {mLineXBegin+mLineWidth*3,mDrawLineHeigthOne[3],mLineXBegin+mLineWidth*4,mDrawLineHeigthOne[4],
                    mLineXBegin+mLineWidth*4,mDrawLineHeigthOne[4],mLineXBegin+mLineWidth*5,mDrawLineHeigthOne[5],
                    mLineXBegin+mLineWidth*5,mDrawLineHeigthOne[5],mLineXBegin+mLineWidth*6,mDrawLineHeigthOne[6]};

            canvas.drawLines(lineOneLeft, mPintColorBlue);
            canvas.drawLines(lineOneRight, mPintColorBlueLight);

            canvas.drawCircle(mLineXBegin, mDrawLineHeigthOne[0], mBigRadius, mPintColorWhite);
            canvas.drawCircle(mLineXBegin, mDrawLineHeigthOne[0], mSmallRadius, mPintColorBlue);

            canvas.drawCircle(mLineXBegin+mLineWidth, mDrawLineHeigthOne[1], mBigRadius, mPintColorWhite);
            canvas.drawCircle(mLineXBegin+mLineWidth, mDrawLineHeigthOne[1], mSmallRadius, mPintColorBlue);

            canvas.drawCircle(mLineXBegin+mLineWidth*2, mDrawLineHeigthOne[2], mBigRadius, mPintColorWhite);
            canvas.drawCircle(mLineXBegin+mLineWidth*2, mDrawLineHeigthOne[2], mSmallRadius, mPintColorBlue);

            canvas.drawCircle(mLineXBegin+mLineWidth*3, mDrawLineHeigthOne[3], mBigRadius, mPintColorBlue);
            canvas.drawCircle(mLineXBegin+mLineWidth*3, mDrawLineHeigthOne[3], mSmallRadius, mPintColorWhite);

            canvas.drawCircle(mLineXBegin+mLineWidth*4, mDrawLineHeigthOne[4], mBigRadius, mPintColorWhite);
            canvas.drawCircle(mLineXBegin+mLineWidth*4, mDrawLineHeigthOne[4], mSmallRadius, mPintColorBlueLight);

            canvas.drawCircle(mLineXBegin+mLineWidth*5, mDrawLineHeigthOne[5], mBigRadius, mPintColorWhite);
            canvas.drawCircle(mLineXBegin+mLineWidth*5, mDrawLineHeigthOne[5], mSmallRadius, mPintColorBlueLight);

            canvas.drawCircle(mLineXBegin+mLineWidth*6, mDrawLineHeigthOne[6], mBigRadius, mPintColorWhite);
            canvas.drawCircle(mLineXBegin+mLineWidth*6, mDrawLineHeigthOne[6], mSmallRadius, mPintColorBlueLight);
        }
        if(null != mShowLineTwo){
            float[] lineTwoLeft = {mLineXBegin,mDrawLineHeigthTwo[0],mLineXBegin+mLineWidth,mDrawLineHeigthTwo[1],
                    mLineXBegin+mLineWidth,mDrawLineHeigthTwo[1],mLineXBegin+mLineWidth*2,mDrawLineHeigthTwo[2],
                    mLineXBegin+mLineWidth*2,mDrawLineHeigthTwo[2],mLineXBegin+mLineWidth*3,mDrawLineHeigthTwo[3]};
            float[] lineTwoRight = {mLineXBegin+mLineWidth*3,mDrawLineHeigthTwo[3],mLineXBegin+mLineWidth*4,mDrawLineHeigthTwo[4],
                    mLineXBegin+mLineWidth*4,mDrawLineHeigthTwo[4],mLineXBegin+mLineWidth*5,mDrawLineHeigthTwo[5],
                    mLineXBegin+mLineWidth*5,mDrawLineHeigthTwo[5],mLineXBegin+mLineWidth*6,mDrawLineHeigthTwo[6]};

            canvas.drawLines(lineTwoLeft, mPintColorOrange);
            canvas.drawLines(lineTwoRight, mPintColorOrangeLight);

            canvas.drawCircle(mLineXBegin, mDrawLineHeigthTwo[0], mBigRadius, mPintColorWhite);
            canvas.drawCircle(mLineXBegin, mDrawLineHeigthTwo[0], mSmallRadius, mPintColorOrange);

            canvas.drawCircle(mLineXBegin+mLineWidth, mDrawLineHeigthTwo[1], mBigRadius, mPintColorWhite);
            canvas.drawCircle(mLineXBegin+mLineWidth, mDrawLineHeigthTwo[1], mSmallRadius, mPintColorOrange);

            canvas.drawCircle(mLineXBegin+mLineWidth*2, mDrawLineHeigthTwo[2], mBigRadius, mPintColorWhite);
            canvas.drawCircle(mLineXBegin+mLineWidth*2, mDrawLineHeigthTwo[2], mSmallRadius, mPintColorOrange);

            canvas.drawCircle(mLineXBegin+mLineWidth*3, mDrawLineHeigthTwo[3], mBigRadius, mPintColorOrange);
            canvas.drawCircle(mLineXBegin+mLineWidth*3, mDrawLineHeigthTwo[3], mSmallRadius, mPintColorWhite);

            canvas.drawCircle(mLineXBegin+mLineWidth*4, mDrawLineHeigthTwo[4], mBigRadius, mPintColorWhite);
            canvas.drawCircle(mLineXBegin+mLineWidth*4, mDrawLineHeigthTwo[4], mSmallRadius, mPintColorOrangeLight);

            canvas.drawCircle(mLineXBegin+mLineWidth*5, mDrawLineHeigthTwo[5], mBigRadius, mPintColorWhite);
            canvas.drawCircle(mLineXBegin+mLineWidth*5, mDrawLineHeigthTwo[5], mSmallRadius, mPintColorOrangeLight);

            canvas.drawCircle(mLineXBegin+mLineWidth*6, mDrawLineHeigthTwo[6], mBigRadius, mPintColorWhite);
            canvas.drawCircle(mLineXBegin+mLineWidth*6, mDrawLineHeigthTwo[6], mSmallRadius, mPintColorOrangeLight);

        }
    }
    @Override
    public void toDrawColumn(Canvas canvas) {
    }
    @Override
    public void toDrawPie(Canvas canvas) {
    }
    @Override
    public void toDrawText(Canvas canvas) {
        if(null != mShowLineOne){
            canvas.drawText(Float.toString(mShowLineOne[0]), mLineXBegin, mDrawLineHeigthOne[0]-mTextHeight, mPintBaseGreyTextDark);
            canvas.drawText(Float.toString(mShowLineOne[1]), mLineXBegin+mLineWidth, mDrawLineHeigthOne[1]-mTextHeight, mPintBaseGreyTextDark);
            canvas.drawText(Float.toString(mShowLineOne[2]), mLineXBegin+mLineWidth*2, mDrawLineHeigthOne[2]-mTextHeight, mPintBaseGreyTextDark);
            canvas.drawText(Float.toString(mShowLineOne[3]), mLineXBegin+mLineWidth*3, mDrawLineHeigthOne[3]-mTextHeight, mPintBaseGreyTextDark);
            canvas.drawText(Float.toString(mShowLineOne[4]), mLineXBegin+mLineWidth*4, mDrawLineHeigthOne[4]-mTextHeight, mPintBaseGreyTextDark);
            canvas.drawText(Float.toString(mShowLineOne[5]), mLineXBegin+mLineWidth*5, mDrawLineHeigthOne[5]-mTextHeight, mPintBaseGreyTextDark);
            canvas.drawText(Float.toString(mShowLineOne[6]), mLineXBegin+mLineWidth*6, mDrawLineHeigthOne[6]-mTextHeight, mPintBaseGreyTextDark);
        }

        if(null != mShowLineTwo){
            canvas.drawText(Float.toString(mShowLineTwo[0]), mLineXBegin, mDrawLineHeigthTwo[0]+mTextHeight*2, mPintBaseGreyTextDark);
            canvas.drawText(Float.toString(mShowLineTwo[1]), mLineXBegin+mLineWidth, mDrawLineHeigthTwo[1]+mTextHeight*2, mPintBaseGreyTextDark);
            canvas.drawText(Float.toString(mShowLineTwo[2]), mLineXBegin+mLineWidth*2, mDrawLineHeigthTwo[2]+mTextHeight*2, mPintBaseGreyTextDark);
            canvas.drawText(Float.toString(mShowLineTwo[3]), mLineXBegin+mLineWidth*3, mDrawLineHeigthTwo[3]+mTextHeight*2, mPintBaseGreyTextDark);
            canvas.drawText(Float.toString(mShowLineTwo[4]), mLineXBegin+mLineWidth*4, mDrawLineHeigthTwo[4]+mTextHeight*2, mPintBaseGreyTextDark);
            canvas.drawText(Float.toString(mShowLineTwo[5]), mLineXBegin+mLineWidth*5, mDrawLineHeigthTwo[5]+mTextHeight*2, mPintBaseGreyTextDark);
            canvas.drawText(Float.toString(mShowLineTwo[6]), mLineXBegin+mLineWidth*6, mDrawLineHeigthTwo[6]+mTextHeight*2, mPintBaseGreyTextDark);
        }

        canvas.drawText(mXShow[0], mLineXBegin, mEndY + mTextHeight*2, mPintBaseGreyTextDark);
        canvas.drawText(mXShow[1], mLineXBegin+mLineWidth, mEndY + mTextHeight*2, mPintBaseGreyTextDark);
        canvas.drawText(mXShow[2], mLineXBegin+mLineWidth*2, mEndY + mTextHeight*2, mPintBaseGreyTextDark);
        canvas.drawText(mXShow[3], mLineXBegin+mLineWidth*3, mEndY + mTextHeight*2, mPintBaseGreyTextDark);
        canvas.drawText(mXShow[4], mLineXBegin+mLineWidth*4, mEndY + mTextHeight*2, mPintBaseGreyTextDark);
        canvas.drawText(mXShow[5], mLineXBegin+mLineWidth*5, mEndY + mTextHeight*2, mPintBaseGreyTextDark);
        canvas.drawText(mXShow[6], mLineXBegin+mLineWidth*6, mEndY + mTextHeight*2, mPintBaseGreyTextDark);


        canvas.drawText(mYShow[0], mBeginX-10-getTextWidth(mYShow[0])/2, mPadTop + mLineHeight, mPintBaseGreyTextDark);
        canvas.drawText(mYShow[1], mBeginX-10-getTextWidth(mYShow[1])/2, mPadTop + mLineHeight*2, mPintBaseGreyTextDark);
        canvas.drawText(mYShow[2], mBeginX-10-getTextWidth(mYShow[2])/2, mPadTop + mLineHeight*3, mPintBaseGreyTextDark);
        canvas.drawText(mYShow[3], mBeginX-10-getTextWidth(mYShow[3])/2, mPadTop + mLineHeight*4, mPintBaseGreyTextDark);
        canvas.drawText(mYShow[4], mBeginX-10-getTextWidth(mYShow[4])/2, mPadTop + mLineHeight*5, mPintBaseGreyTextDark);
   //     canvas.drawText(mYShow[5], mBeginX-10-getTextWidth(mYShow[5])/2, mPadTop + mLineHeight*6, mPintBaseGreyTextDark);

    }
    public float getTextWidth(String str){
        if(TextUtils.isEmpty(str))return 0f;
        return mPintBaseGreyText.measureText(str);
    }
}
