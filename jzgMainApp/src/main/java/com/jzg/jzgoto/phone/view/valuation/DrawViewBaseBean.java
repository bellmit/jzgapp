package com.jzg.jzgoto.phone.view.valuation;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;

/**
 * Created by jzg on 2016/9/21.
 * Function :
 */
public abstract class DrawViewBaseBean implements DrawViewBaseBeanInterface{
    public int mWidth = 0;
    public int mHeight = 0;
    /**
     * 文字笔
     */
    public final Paint mPintBaseGreyText = new Paint();
    public final Paint mPintBaseGreyTextDark = new Paint();
    public final Paint mPintBaseGreyTextRed = new Paint();
    public final Paint mPintBaseWhiteText = new Paint();
    /**
     * 底层划线笔
     */
    public final Paint mPintBaseGreyLine = new Paint();
    /**
     * 蓝色笔
     */
    public final Paint mPintColorBlue = new Paint();
    public final Paint mPintColorBlueLight = new Paint();
    /**
     * 橘黄色笔
     */
    public final Paint mPintColorOrange = new Paint();
    public final Paint mPintColorOrangeLight = new Paint();
    public final Paint mPintColorOrangeTwo = new Paint();
    /**
     * 橘黄色笔附近城市
     */
    public final Paint mPintColorNearCityOrange1 = new Paint();
    public final Paint mPintColorNearCityOrange2 = new Paint();
    public final Paint mPintColorNearCityOrange3 = new Paint();
    public final Paint mPintColorNearCityOrange4 = new Paint();
    /**
     * 绿色笔
     */
    public final Paint mPintColorGreen = new Paint();
    /**
     * 黄色笔
     */
    public final Paint mPintColorYellow = new Paint();
    /**
     * 红色
     */
    public final Paint mPintColorRed = new Paint();
    public final Paint mPintColorRedTwo = new Paint();
    /**
     * 透明笔
     */
    public final Paint mPintColorTransparent = new Paint();
    /**
     * 白色笔
     */
    public final Paint mPintColorWhite = new Paint();
    public final Paint mPaint = new Paint();

    public int[] mPaintColors;
    private float mSizeRatio = 0f;
    protected int mTextSize = 12;
    public void setTextSize(int size){
        mTextSize = size;
    }
    @Override
    public void setRatio(float ratio) {
        mSizeRatio = ratio;
    }
    public float getRatio(){
        return mSizeRatio;
    }
    public float mTextHeight = 0f;
    public float getTextHeight(){
        if(mTextHeight > 0){
            return mTextHeight;
        }
        FontMetrics fm = mPintBaseGreyText.getFontMetrics();
        return (float) (Math.ceil(fm.descent - fm.top) + 2) * (getRatio()/2);
    }
    @Override
    public void initAllPaint() {
        mPintBaseGreyText.setColor(Color.rgb(0x9c, 0x9c, 0x9c));
        mPintBaseGreyTextDark.setColor(Color.rgb(0x6c, 0x6c, 0x6c));
        mPintBaseGreyTextRed.setColor(Color.rgb(0xff, 0x65, 0x5c));
        mPintBaseGreyLine.setColor(Color.rgb(0xe9, 0xea, 0xec));
        mPintBaseWhiteText.setColor(Color.rgb(255, 255, 255));

        mPintBaseGreyText.setTextSize(mTextSize*mSizeRatio);
        mPintBaseGreyText.setTextAlign(Paint.Align.CENTER);
        mPintBaseGreyTextDark.setTextSize(mTextSize*mSizeRatio);
        mPintBaseGreyTextDark.setTextAlign(Paint.Align.CENTER);
        mPintBaseGreyTextRed.setTextSize(mTextSize*mSizeRatio);
        mPintBaseGreyTextRed.setTextAlign(Paint.Align.CENTER);
        mPintBaseWhiteText.setTextAlign(Paint.Align.CENTER);

        mPintBaseGreyLine.setStrokeWidth(1);
        mPintBaseGreyLine.setAntiAlias(true);

        mPintColorBlue.setColor(Color.rgb(0x47, 0x9c, 0xf3));
        mPintColorBlue.setStrokeWidth(3*mSizeRatio);
        mPintColorBlue.setAntiAlias(true);

        mPintColorBlueLight.setColor(Color.rgb(149, 197, 255));
        mPintColorBlueLight.setStrokeWidth(3*mSizeRatio);
        mPintColorBlueLight.setAntiAlias(true);

        mPintColorOrange.setColor(Color.rgb(0xff, 0xaa, 0x3b));
        mPintColorOrange.setStrokeWidth(3*mSizeRatio);
        mPintColorOrange.setAntiAlias(true);

        mPintColorOrangeTwo.setColor(Color.rgb(255, 175, 0));
        mPintColorOrangeTwo.setStrokeWidth(3*mSizeRatio);
        mPintColorOrangeTwo.setAntiAlias(true);

        mPintColorOrangeLight.setColor(Color.rgb(254, 227, 181));
        mPintColorOrangeLight.setStrokeWidth(3*mSizeRatio);
        mPintColorOrangeLight.setAntiAlias(true);

        mPintColorNearCityOrange1.setColor(Color.rgb(255, 88, 0));
        mPintColorNearCityOrange1.setStrokeWidth(3*mSizeRatio);
        mPintColorNearCityOrange1.setAntiAlias(true);

        mPintColorNearCityOrange2.setColor(Color.rgb(255, 68, 0));
        mPintColorNearCityOrange2.setStrokeWidth(3*mSizeRatio);
        mPintColorNearCityOrange2.setAntiAlias(true);

        mPintColorNearCityOrange3.setColor(Color.rgb(255, 127, 0));
        mPintColorNearCityOrange3.setStrokeWidth(3*mSizeRatio);
        mPintColorNearCityOrange3.setAntiAlias(true);

        mPintColorNearCityOrange4.setColor(Color.rgb(255, 113, 36));
        mPintColorNearCityOrange4.setStrokeWidth(3*mSizeRatio);
        mPintColorNearCityOrange4.setAntiAlias(true);

        mPintColorGreen.setColor(Color.rgb(0x86, 0xc2, 0x26));
        mPintColorGreen.setAntiAlias(true);
        mPintColorGreen.setStrokeWidth(3*mSizeRatio);

        mPintColorYellow.setColor(Color.rgb(0xff, 0xcc, 0x00));
        mPintColorYellow.setAntiAlias(true);
        mPintColorYellow.setStrokeWidth(3*mSizeRatio);
        mPintColorRed.setColor(Color.rgb(255, 109, 0));
        mPintColorRed.setAntiAlias(true);
        mPintColorRed.setStrokeWidth(3*mSizeRatio);
        mPintColorRedTwo.setColor(Color.rgb(255, 240, 209));
        mPintColorRedTwo.setAntiAlias(true);
        mPintColorRedTwo.setStrokeWidth(3*mSizeRatio);

        mPintColorTransparent.setColor(Color.TRANSPARENT);
        mPintColorWhite.setColor(Color.rgb(0xff, 0xff, 0xff));
        mPintColorWhite.setAntiAlias(true);
    }
    @Override
    public void setColors(int[] colors){
        mPaintColors = colors;
    }
    @Override
    public abstract void toDrawBase(Canvas canvas);
    @Override
    public abstract void toDrawLine(Canvas canvas);
    @Override
    public abstract void toDrawColumn(Canvas canvas);
    @Override
    public abstract void toDrawPie(Canvas canvas);
    @Override
    public abstract void toDrawText(Canvas canvas);
}
