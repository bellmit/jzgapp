package com.jzg.jzgoto.phone.view.valuation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Build;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;

import com.jzg.jzgoto.phone.tools.ClickControlTool;

/**
 * Description 扇形图
 * Package com.jzg.jzgoto.phone.view.sector
 * Author gf
 * DATE 2016/9/21
 */
public class DrawViewSectorBean extends DrawViewBaseBean{
    private Context context;

    public DrawViewSectorBean(Context context,DrawViewChartBaseView view){
        this.context = context;
        setChartBaseView(view);
    }
    private float mPadLeft = 30;
    private float mPadTop = 30;
    private float mPadRight = 30;
    private float mPadBottom = 30;

    private float mCenterX = 0f;
    private float mCenterY = 0f;

    private float mPading = 0f;
    private float mSpace = 0f;
    public void setShowValueAndStr(float[] values,String[] topShow){
    }
    private int mShowType = 1;
    public void setShowType(int type){
        mShowType = type;
    }
    private boolean mToDrawSmallCir = false;
    public void setDrawSmallCir(boolean need){
        mToDrawSmallCir = need;
    }
    private DrawViewChartBaseView mChartBaseView;
    public void setChartBaseView(DrawViewChartBaseView view){
        mChartBaseView = view;
    }
    private final String[] mPricesShows = new String[3];
    public void setPricesShow(String[] strs){
        if(null == strs || strs.length < mPricesShows.length)return;
        for(int i = 0;i < mPricesShows.length;i++){
            mPricesShows[i] = strs[i];
        }
    }

    /**
     * Type = 3时，车源详情
     */
    private boolean isShowPriceText = true;

    public void setPriceTextHide(boolean isShowPrice){
        this.isShowPriceText = isShowPrice;
    }
    @Override
    public void initShowData() {
        final float ratio = getRatio();
        mPadLeft=ratio*30;
        mPadTop=ratio*30;
        mPadBottom=ratio*30;
        mPadRight=ratio*30;

        mCenterX = mWidth /2f;
        mPading = mWidth /5f;

        mSpace = mPading/7f;
        mCenterY = mHeight - 3*mSpace;
        mSin = Math.sin(Math.PI/3);
        mCos = Math.cos(Math.PI/3);
        mRadiusBig = mCenterX-mPading+mSpace/2;

        mRadiusCenter = mCenterX-mPading+mSpace;
        mRadiusThree = mCenterX-mPading;
    }
    private enum TYPE_INDEX{
        ONE,TWO,THREE,FOUR,FIVE,SIX;
    }
    private TYPE_INDEX mTypeInde = TYPE_INDEX.TWO;
    private float mRadiusBig = 0f;
    private float mRadiusCenter = 0f;
    private float mRadiusThree = 0f;
    private double mSin = 0d;
    private double mCos = 0d;
    private double mTan = 0d;
    public View.OnTouchListener getOntouchListener(){
        mTan = Math.tan(Math.PI/3);
        return new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(!ClickControlTool.isCanToClickFor200())return false;
                final int action = event.getAction();
                float x = event.getX();
                float y = event.getY();
                //给小圆点添加点击事件
                if(mShowType==2) {
                    if (x > mCenterX - (mSpace + mSpace / 3)
                            && x < mCenterX + (mSpace + mSpace / 3)
                            && y > mCenterY - mRadiusBig - (mSpace + mSpace / 3)
                            && y < mCenterY - mRadiusBig + (mSpace + mSpace / 3)) {
                        mTypeInde = TYPE_INDEX.FIVE;
                        mOnSelfClickListener.onClickModel(5);
                        mChartBaseView.toRefreshView();
                    } else if (x > mCenterX - (float) (mRadiusBig * mCos) - (mSpace + mSpace / 3)
                            && x < mCenterX - (float) (mRadiusBig * mCos) + (mSpace + mSpace / 3)
                            && y > mCenterY - (float) (mRadiusBig * mSin) - (mSpace + mSpace / 3)
                            && y < mCenterY - (float) (mRadiusBig * mSin) + (mSpace + mSpace / 3)) {
                        mTypeInde = TYPE_INDEX.FOUR;
                        mOnSelfClickListener.onClickModel(4);
                        mChartBaseView.toRefreshView();
                    } else if (x > mCenterX + (float) (mRadiusBig * mCos) - (mSpace + mSpace / 3)
                            && x < mCenterX + (float) (mRadiusBig * mCos) + (mSpace + mSpace / 3)
                            && y > mCenterY - (float) (mRadiusBig * mSin) - (mSpace + mSpace / 3)
                            && y < mCenterY - (float) (mRadiusBig * mSin) + (mSpace + mSpace / 3)) {
                        mTypeInde = TYPE_INDEX.SIX;
                        mOnSelfClickListener.onClickModel(6);
                        mChartBaseView.toRefreshView();
                    }
                }else{
                    float yH = mCenterY-y;
                    float a = mCenterX-x;
                    if(a<0){
                        a = -a;
                    }
                    double ra = Math.sqrt(yH*yH + a*a);
                    if(ra > mCenterX-mPading)return false;
                    float b = (float)(mTan*a);
                    if(x < mCenterX && mPading < x){
                        if(yH < b){
                            // 第一部分
                            if(null != mOnSelfClickListener){
                                mTypeInde = TYPE_INDEX.ONE;
                                mOnSelfClickListener.onClickModel(1);
                            }
                        } else if (yH < (mCenterX-mPading)){
                            // 中间部分
                            if(null != mOnSelfClickListener){
                                mTypeInde = TYPE_INDEX.TWO;
                                mOnSelfClickListener.onClickModel(2);
                            }
                        }
                    } else if(x>mCenterX && x < (mWidth -mPading)){
                        if(yH < b){
                            // 第三部分
                            if(null != mOnSelfClickListener){
                                mTypeInde = TYPE_INDEX.THREE;
                                mOnSelfClickListener.onClickModel(3);
                            }
                        } else if (yH < (mCenterX-mPading)){
                            // 中间部分
                            if(null != mOnSelfClickListener){
                                mTypeInde = TYPE_INDEX.TWO;
                                mOnSelfClickListener.onClickModel(2);
                            }
                        }
                    } else {
                        return false;
                    }
                    if(mShowType!=2)
                        mChartBaseView.toRefreshView();
                }
                return false;
            }
        };
    }
    public void toRefreshClick(int clickIndex){
        switch (clickIndex){
            case 1:
                mTypeInde = TYPE_INDEX.ONE;
                mChartBaseView.toRefreshView();
                break;
            case 2:
                mTypeInde = TYPE_INDEX.TWO;
                mChartBaseView.toRefreshView();
                break;
            case 3:
                mTypeInde = TYPE_INDEX.THREE;
                mChartBaseView.toRefreshView();
                break;
            case 4:
                mTypeInde = TYPE_INDEX.FOUR;
                mChartBaseView.toRefreshView();
                break;
            case 5:
                mTypeInde = TYPE_INDEX.FIVE;
                mChartBaseView.toRefreshView();
                break;
            case 6:
                mTypeInde = TYPE_INDEX.SIX;
                mChartBaseView.toRefreshView();
                break;

        }
    }
    private OnSelfClickListener mOnSelfClickListener;
    public void setOnSelfClickListener(OnSelfClickListener listener){
        mOnSelfClickListener = listener;
    }
    public static interface OnSelfClickListener{
        public void onClickModel(int index);
    }
    @Override
    public void toDrawBase(Canvas canvas) {
    }
    @Override
    public void toDrawLine(Canvas canvas) {
    }
    @Override
    public void toDrawColumn(Canvas canvas) {
    }
    @Override
    public void toDrawPie(Canvas canvas) {
      float widt = mWidth -mPading;

        RectF ovalF = new RectF(mPading,mPading, mWidth -mPading,mCenterY*2-mPading);
        RectF ovalFTwo = new RectF(mPading-mSpace,mPading-mSpace, mWidth -mPading+mSpace,mCenterY*2-mPading+mSpace);
        switch (mTypeInde){
            case ONE:
                canvas.drawArc(ovalF,300-1,60+1,true,mPintColorRedTwo);
                canvas.drawArc(ovalF,240,60,true,mPintColorOrangeTwo);
                canvas.drawArc(ovalFTwo,180,60+1,true,mPintColorRedTwo);
                break;
            case TWO:
                canvas.drawArc(ovalF,180,60+1,true,mPintColorRedTwo);
                canvas.drawArc(ovalF,300-1,60+1,true,mPintColorRedTwo);
                canvas.drawArc(ovalFTwo,240,60,true,mPintColorOrangeTwo);
                break;
            case THREE:
                canvas.drawArc(ovalF,180,60+1,true,mPintColorRedTwo);
                canvas.drawArc(ovalF,240,60,true,mPintColorOrangeTwo);
                canvas.drawArc(ovalFTwo,300-1,60+1,true,mPintColorRedTwo);
                break;
            default:
                canvas.drawArc(ovalF,180,60+1,true,mPintColorRedTwo);
                canvas.drawArc(ovalF,300-1,60+1,true,mPintColorRedTwo);
                canvas.drawArc(ovalFTwo,240,60,true,mPintColorOrangeTwo);
                break;
        }

        Shader shader = new RadialGradient(mCenterX,mCenterY,mSpace*2+mSpace/2, Color.argb(255,255,109,0),Color.argb(0,255,109,0),
                Shader.TileMode.REPEAT);
        mPintColorRed.setShader(shader);
        canvas.drawCircle(mCenterX,mCenterY,mSpace*2+mSpace/2,mPintColorRed);
        mPintColorRed.setShader(null);
        mPintColorRed.setAlpha(250);
        canvas.drawCircle(mCenterX,mCenterY,mSpace*2,mPintColorRed);

        if(!mToDrawSmallCir)return;
        Shader shaderSmallOne = new RadialGradient(mCenterX-(float)(mRadiusBig*mCos),mCenterY-(float)(mRadiusBig*mSin),mSpace+mSpace/3, Color.argb(255,255,109,0),Color.argb(0,255,109,0),
                Shader.TileMode.REPEAT);
        mPintColorRed.setShader(shaderSmallOne);
        canvas.drawCircle(mCenterX-(float)(mRadiusBig*mCos),mCenterY-(float)(mRadiusBig*mSin),mSpace+mSpace/3,mPintColorRed);

        shaderSmallOne = new RadialGradient(mCenterX,mCenterY-mRadiusBig,mSpace+mSpace/3, Color.argb(255,255,109,0),Color.argb(0,255,109,0),
                Shader.TileMode.REPEAT);
        mPintColorRed.setShader(shaderSmallOne);
        canvas.drawCircle(mCenterX,mCenterY-mRadiusBig,mSpace+mSpace/3,mPintColorRed);
        shaderSmallOne = new RadialGradient(mCenterX+(float)(mRadiusBig*mCos),mCenterY-(float)(mRadiusBig*mSin),mSpace+mSpace/3, Color.argb(255,255,109,0),Color.argb(0,255,109,0),
                Shader.TileMode.REPEAT);
        mPintColorRed.setShader(shaderSmallOne);
        canvas.drawCircle(mCenterX+(float)(mRadiusBig*mCos),mCenterY-(float)(mRadiusBig*mSin),mSpace+mSpace/3,mPintColorRed);

        mPintColorRed.setShader(null);
        mPintColorRed.setAlpha(250);
        canvas.drawCircle(mCenterX-(float)(mRadiusBig*mCos),mCenterY-(float)(mRadiusBig*mSin),mSpace,mPintColorRed);
        canvas.drawCircle(mCenterX,mCenterY-mRadiusBig,mSpace,mPintColorRed);
        canvas.drawCircle(mCenterX+(float)(mRadiusBig*mCos),mCenterY-(float)(mRadiusBig*mSin),mSpace,mPintColorRed);
        switch (mTypeInde){
            case FOUR:
                canvas.drawLine(mCenterX-(float)(mRadiusBig*mCos), 0, mCenterX-(float)(mRadiusBig*mCos), mCenterY-(float)(mRadiusBig*mSin), mPintColorRed);
                break;
            case FIVE:
                canvas.drawLine(mCenterX, 0, mCenterX, mCenterY-mRadiusBig, mPintColorRed);
                break;
            case SIX:
                canvas.drawLine(mCenterX+(float)(mRadiusBig*mCos), 0, mCenterX+(float)(mRadiusBig*mCos), mCenterY-(float)(mRadiusBig*mSin), mPintColorRed);
                break;
        }
    }
    @Override
    public void toDrawText(Canvas canvas) {
        if(mShowType == 1){
            mPintBaseWhiteText.setTextSize(32f);
            if("MI NOTE LTE".equals(Build.MODEL)){
                canvas.drawText("车况良好", mCenterX,mPading + (mCenterY-mPading)/2-mSpace*2, mPintBaseWhiteText);
            }else {
                canvas.drawText("车况良好", mCenterX, mPading + (mCenterY - mPading) / 2, mPintBaseWhiteText);
            }

            mPintBaseGreyTextDark.setTextSize(32f);
            if("MI NOTE LTE".equals(Build.MODEL)){
                canvas.drawText("车况一般", mPading + (mCenterX-mPading)/2,mPading + (mCenterY-mPading-mSpace)/2 + 1*mSpace, mPintBaseGreyTextDark);
            }else {
                canvas.drawText("车况一般", mPading + (mCenterX - mPading) / 2, mPading + (mCenterY - mPading - mSpace) / 2 + 3 * mSpace, mPintBaseGreyTextDark);
            }
            if("MI NOTE LTE".equals(Build.MODEL)){
                canvas.drawText("车况极好", mCenterX + (mCenterX-mPading)/2,mPading + (mCenterY-mPading-mSpace)/2 + 1*mSpace, mPintBaseGreyTextDark);
            }else {
                canvas.drawText("车况极好", mCenterX + (mCenterX - mPading) / 2, mPading + (mCenterY - mPading - mSpace) / 2 + 3 * mSpace, mPintBaseGreyTextDark);
            }
            return;
        }
        if(mShowType == 2){
            mPintBaseWhiteText.setTextSize(32f);
            if("MI NOTE LTE".equals(Build.MODEL)){
                canvas.drawText("合理售价", mCenterX,mPading + (mCenterY-mPading)/2-mSpace*2, mPintBaseWhiteText);
            }else {
                canvas.drawText("合理售价", mCenterX, mPading + (mCenterY - mPading) / 2, mPintBaseWhiteText);
            }
//                canvas.drawText("合理售价", mCenterX, mPading + (mCenterY - mPading) / 2, mPintBaseWhiteText);
            mPintBaseGreyTextDark.setTextSize(26f);
            mPintBaseGreyText.setTextSize(32f);
            if("MI NOTE LTE".equals(Build.MODEL)){
                canvas.drawText("价格偏低,请", mPading + (mCenterX-mPading)/2-mSpace,mPading + (mCenterY-mPading-mSpace)/2 + 1*mSpace, mPintBaseGreyTextDark);
                canvas.drawText("谨防价格欺诈", mPading + (mCenterX-mPading)/2-mSpace,mPading + (mCenterY-mPading-mSpace)/2 + 1*mSpace + getTextHeight()*2, mPintBaseGreyTextDark);

                canvas.drawText("价格偏高,可能", mCenterX + (mCenterX-mPading)/2+mSpace,mPading + (mCenterY-mPading-mSpace)/2 + 1*mSpace, mPintBaseGreyTextDark);
                canvas.drawText("较新或有改装加装", mCenterX + (mCenterX-mPading)/2+mSpace,mPading + (mCenterY-mPading-mSpace)/2 + 1*mSpace + getTextHeight()*2, mPintBaseGreyTextDark);
            }else {
                canvas.drawText("价格偏低,请", mPading + (mCenterX - mPading) / 2 - mSpace, mPading + (mCenterY - mPading - mSpace) / 2 + 3 * mSpace, mPintBaseGreyTextDark);
                canvas.drawText("谨防价格欺诈", mPading + (mCenterX - mPading) / 2 - mSpace, mPading + (mCenterY - mPading - mSpace) / 2 + 3 * mSpace + getTextHeight() * 2, mPintBaseGreyTextDark);

                canvas.drawText("价格偏高,可能", mCenterX + (mCenterX - mPading) / 2 + mSpace, mPading + (mCenterY - mPading - mSpace) / 2 + 3 * mSpace, mPintBaseGreyTextDark);
                canvas.drawText("较新或有改装加装", mCenterX + (mCenterX - mPading) / 2 + mSpace, mPading + (mCenterY - mPading - mSpace) / 2 + 3 * mSpace + getTextHeight() * 2, mPintBaseGreyTextDark);
            }
        }
        if(mShowType == 3){
            mPintBaseWhiteText.setTextSize(32f);
            mPintBaseGreyText.setTextSize(32f);


                canvas.drawText("精真估估值", mCenterX, mPading + (mCenterY - mPading) / 3 - getTextHeight() * 2, mPintBaseWhiteText);
                canvas.drawText(mPricesShows[1], mCenterX,mPading + (mCenterY-mPading)/3 , mPintBaseWhiteText);

            mPintBaseGreyTextDark.setTextSize(32f);
            if(isShowPriceText) {
                if("MI NOTE LTE".equals(Build.MODEL)){
                    canvas.drawText("最低报价", mPading + (mCenterX - mPading) / 2 - mSpace, mPading + (mCenterY - mPading - mSpace) / 2 + 1 * mSpace, mPintBaseGreyTextDark);
                }else {
                    canvas.drawText("最低报价", mPading + (mCenterX - mPading) / 2 - mSpace, mPading + (mCenterY - mPading - mSpace) / 2 + 3 * mSpace, mPintBaseGreyTextDark);
                }
                if(TextUtils.isEmpty(mPricesShows[0])){
                    if("MI NOTE LTE".equals(Build.MODEL)){
                        canvas.drawText("--万", mPading + (mCenterX-mPading)/2-mSpace,mPading + (mCenterY-mPading-mSpace)/2 + 1*mSpace + getTextHeight()*2, mPintBaseGreyTextDark);
                    }else {
                        canvas.drawText("--万", mPading + (mCenterX - mPading) / 2 - mSpace, mPading + (mCenterY - mPading - mSpace) / 2 + 3 * mSpace + getTextHeight() * 2, mPintBaseGreyTextDark);
                    }
                } else {
                    if("MI NOTE LTE".equals(Build.MODEL)){
                        canvas.drawText(mPricesShows[0], mPading + (mCenterX-mPading)/2-mSpace,mPading + (mCenterY-mPading-mSpace)/2 + 1*mSpace + getTextHeight()*2, mPintBaseGreyTextDark);
                    }else {
                        canvas.drawText(mPricesShows[0], mPading + (mCenterX - mPading) / 2 - mSpace, mPading + (mCenterY - mPading - mSpace) / 2 + 3 * mSpace + getTextHeight() * 2, mPintBaseGreyTextDark);
                    }
                }
            }
            if(isShowPriceText) {
                if("MI NOTE LTE".equals(Build.MODEL)){
                    canvas.drawText("最高报价", mCenterX + (mCenterX - mPading) / 2 + mSpace, mPading + (mCenterY - mPading - mSpace) / 2 + 1 * mSpace, mPintBaseGreyTextDark);
                }else {
                    canvas.drawText("最高报价", mCenterX + (mCenterX - mPading) / 2 + mSpace, mPading + (mCenterY - mPading - mSpace) / 2 + 3 * mSpace, mPintBaseGreyTextDark);
                }
                if(TextUtils.isEmpty(mPricesShows[2])){
                    if("MI NOTE LTE".equals(Build.MODEL)){
                        canvas.drawText("--万", mCenterX + (mCenterX-mPading)/2+mSpace,mPading + (mCenterY-mPading-mSpace)/2 + 1*mSpace + getTextHeight()*2, mPintBaseGreyTextDark);
                    }else {
                        canvas.drawText("--万", mCenterX + (mCenterX - mPading) / 2 + mSpace, mPading + (mCenterY - mPading - mSpace) / 2 + 3 * mSpace + getTextHeight() * 2, mPintBaseGreyTextDark);
                    }
                } else {
                    if("MI NOTE LTE".equals(Build.MODEL)){
                        canvas.drawText(mPricesShows[2], mCenterX + (mCenterX-mPading)/2+mSpace,mPading + (mCenterY-mPading-mSpace)/2 + 1*mSpace + getTextHeight()*2, mPintBaseGreyTextDark);
                    }else {
                        canvas.drawText(mPricesShows[2], mCenterX + (mCenterX - mPading) / 2 + mSpace, mPading + (mCenterY - mPading - mSpace) / 2 + 3 * mSpace + getTextHeight() * 2, mPintBaseGreyTextDark);
                    }
                }
            }
        }
        switch (mTypeInde){
            case FOUR:
                canvas.drawText("良好",mCenterX,mCenterY-mRadiusBig-mSpace-mSpace/2,mPintBaseGreyTextDark);
                canvas.drawText("极好",mCenterX+(float)(mRadiusBig*mCos)+getTextWidth("极好")/3,mCenterY-(float)(mRadiusBig*mSin)-mSpace-mSpace/2,mPintBaseGreyTextDark);
                break;
            case FIVE:
                canvas.drawText("一般",mCenterX-(float)(mRadiusBig*mCos)-getTextWidth("一般")/3,mCenterY-(float)(mRadiusBig*mSin)-mSpace-mSpace/2,mPintBaseGreyTextDark);
                canvas.drawText("极好",mCenterX+(float)(mRadiusBig*mCos)+getTextWidth("极好")/3,mCenterY-(float)(mRadiusBig*mSin)-mSpace-mSpace/2,mPintBaseGreyTextDark);
                break;
            case SIX:
                canvas.drawText("一般",mCenterX-(float)(mRadiusBig*mCos)-getTextWidth("一般")/3,mCenterY-(float)(mRadiusBig*mSin)-mSpace-mSpace/2,mPintBaseGreyTextDark);
                canvas.drawText("良好",mCenterX,mCenterY-mRadiusBig-mSpace-mSpace/2,mPintBaseGreyTextDark);
                break;
        }
    }
    public float getTextWidth(String str){
        if(TextUtils.isEmpty(str))return 0f;
        return mPintBaseGreyText.measureText(str);
    }


}
