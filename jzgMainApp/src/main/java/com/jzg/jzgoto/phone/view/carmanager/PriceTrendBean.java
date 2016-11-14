package com.jzg.jzgoto.phone.view.carmanager;

import android.graphics.Canvas;
import android.text.TextUtils;
import com.jzg.jzgoto.phone.view.valuation.DrawViewBaseBean;

/**
 * Created by jzg on 2016/9/29.
 * Function :
 */
public class PriceTrendBean extends DrawViewBaseBean {
    private static final int PADDING_LEFT = 30;
    private static final int PADDING_RIGHT = 5;
    private static final int PADDING_TOP = 10;
    private static final int PADDING_BOTTOM = 30;

    private float mPadLeft = 0;
    private float mPadTop = 0;
    private float mPadRight = 0;
    private float mPadBottom = 0;

    private float mLineHeight = 0f;
    private float mLineWidth = 0f;
    private float mBeginX = 0f;
    private float mBeginY = 0f;
    private float mEndX = mWidth;
    private float mEndY = mHeight;

    private float mMinShow = 0f;
    private float mMaxShow = 6f;
    private float mShowLevel = 1f;

    private int mXLineNum = 7;
    private int mYLineNum = 5;
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
        }else{
            mXShow = xShow;
        }
        if(yShow != null && yShow.length == mYShow.length){
            for(int i = 0;i < mYShow.length;i++){
                mYShow[i] = yShow[i];
            }
        }else{
            mYShow = yShow;
        }
    }

    @Override
    public void initAllPaint() {
        setTextSize(10);
        super.initAllPaint();
    }

    @Override
    public void initShowData() {
        final float ratio = getRatio();
        mPadLeft = ratio * PADDING_LEFT;
        mPadTop = ratio * PADDING_TOP;
        mPadBottom = ratio * PADDING_BOTTOM;
        mPadRight = ratio * PADDING_RIGHT;

        mLineHeight = (mHeight - mPadBottom - mPadTop)/(mYLineNum);
        mLineWidth = (mWidth - mPadLeft - mPadRight)/7;
        mBeginX = mPadLeft;
        mBeginY = mHeight - mPadBottom - mLineHeight*(mYLineNum);
        mEndX = mWidth - mPadRight;
        mEndY = mHeight - mPadBottom;

        mShowLevel = (mMaxShow - mMinShow)/(mYLineNum);
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
            float[] lineOneLeft = new float[] {mLineXBegin,mDrawLineHeigthOne[0],mLineXBegin+mLineWidth,mDrawLineHeigthOne[1],
                    mLineXBegin+mLineWidth,mDrawLineHeigthOne[1],mLineXBegin+mLineWidth*2,mDrawLineHeigthOne[2],
                    mLineXBegin+mLineWidth*2,mDrawLineHeigthOne[2],mLineXBegin+mLineWidth*3,mDrawLineHeigthOne[3]};

            float[] lineOneRight = new float[]{mLineXBegin+mLineWidth*3,mDrawLineHeigthOne[3],mLineXBegin+mLineWidth*4,mDrawLineHeigthOne[4],
                    mLineXBegin+mLineWidth*4,mDrawLineHeigthOne[4],mLineXBegin+mLineWidth*5,mDrawLineHeigthOne[5],
                    mLineXBegin+mLineWidth*5,mDrawLineHeigthOne[5],mLineXBegin+mLineWidth*6,mDrawLineHeigthOne[6]};

            canvas.drawLines(lineOneLeft, mPintColorBlue);
            //canvas.drawLines(lineOneRight, mPintColorBlueLight);
            canvas.drawLines(lineOneRight, mPintColorBlue);

            canvas.drawCircle(mLineXBegin, mDrawLineHeigthOne[0], mBigRadius, mPintColorWhite);
            canvas.drawCircle(mLineXBegin, mDrawLineHeigthOne[0], mSmallRadius, mPintColorBlue);

            canvas.drawCircle(mLineXBegin+mLineWidth, mDrawLineHeigthOne[1], mBigRadius, mPintColorWhite);
            canvas.drawCircle(mLineXBegin+mLineWidth, mDrawLineHeigthOne[1], mSmallRadius, mPintColorBlue);

            canvas.drawCircle(mLineXBegin+mLineWidth*2, mDrawLineHeigthOne[2], mBigRadius, mPintColorWhite);
            canvas.drawCircle(mLineXBegin+mLineWidth*2, mDrawLineHeigthOne[2], mSmallRadius, mPintColorBlue);

            canvas.drawCircle(mLineXBegin+mLineWidth*3, mDrawLineHeigthOne[3], mBigRadius, mPintColorWhite);
            canvas.drawCircle(mLineXBegin+mLineWidth*3, mDrawLineHeigthOne[3], mSmallRadius, mPintColorBlue);

            canvas.drawCircle(mLineXBegin+mLineWidth*4, mDrawLineHeigthOne[4], mBigRadius, mPintColorWhite);
            canvas.drawCircle(mLineXBegin+mLineWidth*4, mDrawLineHeigthOne[4], mSmallRadius, mPintColorBlue);

            canvas.drawCircle(mLineXBegin+mLineWidth*5, mDrawLineHeigthOne[5], mBigRadius, mPintColorWhite);
            canvas.drawCircle(mLineXBegin+mLineWidth*5, mDrawLineHeigthOne[5], mSmallRadius, mPintColorBlue);

            canvas.drawCircle(mLineXBegin+mLineWidth*6, mDrawLineHeigthOne[6], mBigRadius, mPintColorWhite);
            canvas.drawCircle(mLineXBegin+mLineWidth*6, mDrawLineHeigthOne[6], mSmallRadius, mPintColorBlue);
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
            for (int i = 0; i < mShowLineOne.length; i++){
                if (Float.compare(mShowLineOne[i], 0.0f) > 0) {
                    canvas.drawText(Float.toString(mShowLineOne[i]), mLineXBegin + mLineWidth * i, mDrawLineHeigthOne[i] - mTextHeight, mPintBaseGreyTextDark);
                }
            }
        }

        if(null != mXShow){
            for (int i = 0; i < mXShow.length; i++){
                if (!TextUtils.isEmpty(mXShow[i])) {
                    canvas.drawText(mXShow[i], mLineXBegin+mLineWidth*i, mEndY + mTextHeight*2, mPintBaseGreyTextDark);
                }
            }
        }

        int margin = 5;
        if(null != mYShow){
            for (int i = 0; i < mYShow.length; i++){
                if (!TextUtils.isEmpty(mYShow[i])) {
                    canvas.drawText(mYShow[i], mBeginX - margin - getTextWidth(mYShow[i]) / 2, mPadTop + mLineHeight * (i + 1), mPintBaseGreyTextDark);
                }
            }
        }

    }
    public float getTextWidth(String str){
        if(TextUtils.isEmpty(str))return 0f;
        return mPintBaseGreyText.measureText(str);
    }
}
