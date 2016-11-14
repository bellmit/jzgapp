package com.jzg.jzgoto.phone.view.sellcar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.jzg.jzgoto.phone.R;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DoubleSeekBar extends View {
    public static int MIN_VALUE_BETWEEN_SLIDERS = 1;
    public static int SLIDER_DISTANCE_THRESHOLD = 1;
    public static int MIN_RIGHT_SLIDERS_VALUE = 2;

    public static int SLIDER_TYPE_NONE = 0;
    public static int SLIDER_TYPE_LEFT = 1;
    public static int SLIDER_TYPE_RIGHT = 2;

    public static int SLIDER_DIRECTION_NONE = 0;
    public static int SLIDER_DIRECTION_LEFT = 1;
    public static int SLIDER_DIRECTION_RIGHT = 2;

    private Bitmap mBackground, mFrontground, mButton;
    private Paint mBackgroundPaint, mNumPaint;
    private Context mContext;
    private int mTotalWidth;
    private int mTotalHeight;
    private int defaultPadding = 20;
    private int mbackgroundHeight = 20;
    private int mRadius = 40;
    private String[] data;
    private int mSrcollbarWidth;
    private int mLeftballX;
    private int mRightballX;
    private DoubleSeekBar.OnSeekBarChangeListener mBarChangeListener;
    private Float[] gaps;
    private String low="0";//左边
    private String high="不限";//右边

    public String getHigh() {
        return high;
    }

    public String getLow() {
        return low;
    }

    public DoubleSeekBar(Context context) {
        super(context);
        init(context);

    }

    public DoubleSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DoubleSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mRadius = dip2px(mContext,16);
        defaultPadding = dip2px(mContext,10);
        mbackgroundHeight = dip2px(mContext,10);
        mBackground = BitmapFactory.decodeResource(getResources(), R.drawable.backb);
        mFrontground = BitmapFactory.decodeResource(getResources(), R.drawable.umeng_socialize_button_login_normal);
        mButton = BitmapFactory.decodeResource(getResources(), R.drawable.btn);
        mBackgroundPaint = createPaint(Color.GRAY, 0, Paint.Style.FILL, 2);
        mNumPaint = createPaint(Color.GRAY, dip2px(mContext, 12), Paint.Style.FILL, 3);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mTotalWidth = measureWidth(widthMeasureSpec);
        mTotalHeight = measureHeight(heightMeasureSpec);
        mSrcollbarWidth = mTotalWidth - 2 * defaultPadding - 2 * mRadius;
        mLeftballX = defaultPadding;
        mRightballX = mTotalWidth - defaultPadding - 2 * mRadius;
        setMeasuredDimension(mTotalWidth, mTotalHeight);
    }

    private int measureWidth(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        // 声明一个临时变量来存储计算出的测量值
        int resultWidth = 0;
        //wrap_content
        if (specMode == MeasureSpec.AT_MOST) {
        }
        //fill_parent或者精确值
        else if (specMode == MeasureSpec.EXACTLY) {
        }
        return specSize;
    }


    private int measureHeight(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        int defaultHeight = 500;
        //wrap_content
        if (specMode == MeasureSpec.AT_MOST) {
        }
        //fill_parent或者精确值
        else if (specMode == MeasureSpec.EXACTLY) {
            // defaultHeight = specSize-getPaddingLeft()-getPaddingRight();
            defaultHeight = specSize;
        }
        return defaultHeight;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBackground(canvas);
        drawMark(canvas);
        drawFrontground(canvas);
        drawLeftBall(canvas);
        drawRightBall(canvas);
    }

    private void drawLeftBall(Canvas canvas) {
        Rect left = null;
        left = new Rect(mLeftballX, dip2px(getContext(), Float.valueOf(80)), mLeftballX + 2 * mRadius, dip2px(getContext(), Float.valueOf(110)));
        canvas.drawBitmap(mButton, null, left, mBackgroundPaint);
        Paint paintNum0 = new Paint();
        paintNum0.setColor(Color.parseColor("#EAF1FF"));
        paintNum0.setStrokeWidth(3);
        paintNum0.setTextAlign(Paint.Align.CENTER);
        paintNum0.setTextSize(dip2px(getContext(), Float.valueOf(10)));
        float baseline = (left.bottom - left.top) / 2 + left.top + paintNum0.getTextSize()/2 - paintNum0.getFontMetrics().descent;
        if(low.equals("不限")){
            canvas.drawText(data[data.length - 2] + "+" + "", (left.right -left.left) / 2 +left.left, baseline+dip2px(mContext,2), paintNum0);
        }else{
            canvas.drawText(low + "", (left.right -left.left) / 2 +left.left, baseline+dip2px(mContext,2), paintNum0);
        }
    }

    private void drawRightBall(Canvas canvas) {
        Rect right = new Rect(mRightballX, dip2px(getContext(), Float.valueOf(80)), mRightballX + 2 * mRadius, dip2px(getContext(), Float.valueOf(110)));
        canvas.drawBitmap(mButton, null, right, mBackgroundPaint);

        Paint paintNum1 = new Paint();
        paintNum1.setColor(Color.parseColor("#EAF1FF"));
        paintNum1.setStrokeWidth(3);
        paintNum1.setTextAlign(Paint.Align.CENTER);
        paintNum1.setTextSize(dip2px(getContext(), Float.valueOf(10)));
        float baseline = (right.bottom - right.top) / 2 + right.top + paintNum1.getTextSize()/2 - paintNum1.getFontMetrics().descent;
        if(high.equals("不限")){
            canvas.drawText(data[data.length - 2] + "+" + "", (right.right -right.left) / 2 +right.left, baseline+ dip2px(mContext,2), paintNum1);
        }else{
            canvas.drawText(high + "", (right.right -right.left) / 2 +right.left, baseline+dip2px(mContext,2), paintNum1);
        }

    }

    private void drawBackground(Canvas canvas) {
        Rect rectBack = new Rect(defaultPadding+mRadius, dip2px(mContext,50), mTotalWidth-defaultPadding-mRadius, mbackgroundHeight + dip2px(mContext,50));
        canvas.drawBitmap(mBackground, null, rectBack, mBackgroundPaint);
    }

    private void drawFrontground(Canvas canvas) {
        Rect rectFront = null;
        rectFront = new Rect(mRadius + mLeftballX, dip2px(mContext,50), mRightballX + mRadius, mbackgroundHeight + dip2px(mContext,50));
        canvas.drawBitmap(mFrontground, null, rectFront, mBackgroundPaint);
    }

    private void drawMark(Canvas canvas) {
        if (data == null || data.length == 0) {
            return;
        }
        //画多少格子
        int count = (data.length - 1) * 5;
        int markCount = count + 1;
        int startX = 0;
        int textWidth = 0;
        for (int i = 0; i < markCount; i++) {
            startX = i * mSrcollbarWidth / count + defaultPadding + mRadius;
            if (i % 5 == 0) {
                //长线 画数字  e3e8f3
                int index = i / 5;
                mNumPaint.reset();
                mNumPaint = createPaint(Color.GRAY, dip2px(mContext, 12), Paint.Style.FILL, 3);
                if (index == data.length - 1){
                    textWidth = (int)mNumPaint.measureText(data[index]);
                    canvas.drawText(data[index] + "", startX - textWidth/2, dip2px(mContext, 25), mNumPaint);
                }else if (index == 0){
                    textWidth = (int)mNumPaint.measureText(data[index]);
                    canvas.drawText(data[index] + "", startX + textWidth/2, dip2px(mContext, 25), mNumPaint);
                }else{
                    canvas.drawText(data[index] + "", startX, dip2px(mContext, 25), mNumPaint);
                }
                mNumPaint.reset();
                mNumPaint = createPaint(Color.parseColor("#E3E8F3"), dip2px(mContext, 12), Paint.Style.FILL, 3);
                canvas.drawLine(startX, dip2px(mContext,30), startX, dip2px(mContext,80), mNumPaint);//画长线
                gaps[index] = Float.valueOf(startX);
            } else {
                //短线
                mNumPaint.reset();
                mNumPaint = createPaint(Color.parseColor("#E3E8F3"), dip2px(mContext, 12), Paint.Style.FILL, 3);
                canvas.drawLine(startX, dip2px(mContext,40), startX, dip2px(mContext,70), mNumPaint);
            }

        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    private Paint createPaint(int color, int textsize, Paint.Style style, int lineWidth) {
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(lineWidth);
        paint.setDither(true);
        paint.setTextSize(textsize);
        paint.setStyle(style);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setTextAlign(Paint.Align.CENTER);
        return paint;
    }


    public void setData(String[] data) {
        if (data == null || data.length == 0) {
            return;
        }
        this.data = data;
        gaps = new Float[data.length];
        invalidate();
    }

    int mSliderType = SLIDER_TYPE_NONE;
    int mSliderDirection = SLIDER_DIRECTION_NONE;
    private int mPreX;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //判断点到了左边还是右边按钮
                mSliderType = getTouchType(event);
                mPreX = (int)event.getX();
                mSliderDirection = SLIDER_DIRECTION_NONE;
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                int hitDataRegionIndex;
                int minSliderInterval;
                int sliderInterval;
                int deltaX = (int) event.getX() - mPreX;
                int leftBoundary = 0, rightBoundary = 0;
                int minRightSliderValue = MIN_RIGHT_SLIDERS_VALUE;
                boolean needInvalidate = true;
                if (Math.abs(deltaX) >= SLIDER_DISTANCE_THRESHOLD){
                    if (deltaX > 0){
                        mSliderDirection = SLIDER_DIRECTION_RIGHT;
                    }else{
                        mSliderDirection = SLIDER_DIRECTION_LEFT;
                    }
                    if (mSliderType == SLIDER_TYPE_LEFT) {
                        leftBoundary = gaps[0].intValue() - mRadius;
                        rightBoundary = gaps[gaps.length - 2].intValue() - mRadius;
                        if (mSliderDirection == SLIDER_DIRECTION_LEFT){
                            mLeftballX = mLeftballX + deltaX;
                            if (mLeftballX < leftBoundary) {
                                mLeftballX = leftBoundary;
                            }
                        }else{
                            if (mLeftballX < rightBoundary) {
                                hitDataRegionIndex = getHitDataRegionIndex(mLeftballX + mRadius);
                                minSliderInterval = getMinIntervalForDataRegion(hitDataRegionIndex);
                                sliderInterval = mRightballX - (mLeftballX + deltaX);
                                if (sliderInterval > minSliderInterval) {
                                    mLeftballX = mLeftballX + deltaX;
                                    if (mLeftballX >= rightBoundary) {
                                        mLeftballX = rightBoundary;
                                    }
                                } else {
                                    //左右滑块距离小于阈值，需要一起向右滑动
                                    mLeftballX = mLeftballX + deltaX;
                                    mRightballX = mLeftballX + minSliderInterval;
                                    if (mLeftballX >= rightBoundary) {
                                        mLeftballX = rightBoundary;
                                        mRightballX = gaps[gaps.length - 1].intValue() - mRadius;
                                    }
                                }
                            }
                        }
                    }else if(mSliderType == SLIDER_TYPE_RIGHT){
                        leftBoundary = gaps[0].intValue() - mRadius;
                        rightBoundary = gaps[gaps.length - 1].intValue() - mRadius;
                        if (mSliderDirection == SLIDER_DIRECTION_RIGHT){
                            mRightballX = mRightballX + deltaX;
                            if (mRightballX > rightBoundary) {
                                mRightballX = rightBoundary;
                            }
                        }else{
                            if (isNumeric(high) && Integer.valueOf(high) <= minRightSliderValue){
                                return true;
                            }
                            hitDataRegionIndex = getHitDataRegionIndex(mRightballX + mRadius);
                            minSliderInterval = getMinIntervalForDataRegion(hitDataRegionIndex);
                            sliderInterval = (mRightballX + deltaX) - mLeftballX;
                            if (sliderInterval > minSliderInterval) {
                                mRightballX = mRightballX + deltaX;
                            }else{
                                //左右滑块距离小于阈值，需要一起向左滑动
                                mRightballX = mRightballX + deltaX;
                                mLeftballX = mRightballX - minSliderInterval;
                                if (mLeftballX < leftBoundary){
                                    mLeftballX = leftBoundary;
                                    mRightballX = mLeftballX + minSliderInterval;
                                }
                            }
                            getHighData();
                            if (high.equals("100") && low.equals("100")){
                                mLeftballX = getPosXForValue(100) - minSliderInterval - mRadius;
                            }
                            if (isNumeric(high) && Integer.valueOf(high) < minRightSliderValue){
                                mRightballX = getPosXForValue(minRightSliderValue) - mRadius;
                                mLeftballX = mRightballX - minSliderInterval;
                                if (mLeftballX < leftBoundary) {
                                    mLeftballX = leftBoundary;
                                }
                            }
                        }
                    }
                    mPreX = (int) event.getX();
                    if (mBarChangeListener != null) {
                        mBarChangeListener.onProgressChanged(this, low, high);
                    }
                    getLowData();
                    getHighData();
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                mSliderType = SLIDER_TYPE_NONE;
                invalidate();
                break;
        }
        return true;
    }

    private int getTouchType(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        int padding = 20;
        if (mRightballX - mLeftballX > 2 * mRadius + 2 * padding) {
            if (x >= mLeftballX - dip2px(mContext, padding) && x <= mLeftballX + dip2px(mContext, padding) + 2 * mRadius && y <= dip2px(mContext, 110) && y >= dip2px(mContext, 80)) {
                return SLIDER_TYPE_LEFT;
            } else if (x >= mRightballX - dip2px(mContext, padding) && x <= mRightballX + 2 * mRadius + dip2px(mContext, padding) && y <= dip2px(mContext, 110) && y >= dip2px(mContext, 80)) {
                return SLIDER_TYPE_RIGHT;
            }
        }else{
            //两滑块太近，触摸区域重叠，要做特殊处理
            int middleX = (mRightballX + mLeftballX)/2;
            if (x >= mLeftballX - dip2px(mContext, padding) && x <= middleX  && y <= dip2px(mContext, 110) && y >= dip2px(mContext, 80)) {
                return SLIDER_TYPE_LEFT;
            } else if (x >= middleX && x <= mRightballX + 2 * mRadius + dip2px(mContext, padding) && y <= dip2px(mContext, 110) && y >= dip2px(mContext, 80)) {
                return SLIDER_TYPE_RIGHT;
            }
        }
        return SLIDER_TYPE_NONE;
    }


    //回调函数，在滑动时实时调用，改变输入框的值
    public interface OnSeekBarChangeListener {
        //滑动前
        public void onProgressBefore(DoubleSeekBar seekBar, String progressLow,
                                     String progressHigh);

        //滑动时
        public void onProgressChanged(DoubleSeekBar seekBar, String progressLow,
                                      String progressHigh);

        //滑动后
        public void onProgressAfter(DoubleSeekBar seekBar, String progressLow,
                                    String progressHigh);
    }


    public void setOnSeekBarChangeListener(DoubleSeekBar.OnSeekBarChangeListener mListener) {
        this.mBarChangeListener = mListener;
    }


    private void getHighData() {
        int maxValueIndex = data.length - 2;
        //Log.e("test", "mRightballX-mRadius>>>>" + (mRightballX-mRadius) + "gaps" + gaps[maxValueIndex]);
        for (int i = 1; i < gaps.length - 1; i++) {
            if (gaps[i] > mRightballX + mRadius) {
                //high = Math.round((mRightballX + mRadius - gaps[i - 1]) * (Integer.valueOf(data[i]) - Integer.valueOf(data[i - 1])) / (gaps[i] - gaps[i - 1])) + Integer.valueOf(data[i - 1]) + "";
                high = ((int)((mRightballX + mRadius - gaps[i - 1]) * (Integer.valueOf(data[i]) - Integer.valueOf(data[i - 1])) / (gaps[i] - gaps[i - 1]))) + Integer.valueOf(data[i - 1]) + "";
                break;
            }
        }
        if (mRightballX + mRadius <= gaps[maxValueIndex]+5 && mRightballX + mRadius >= gaps[maxValueIndex]){
            high = data[maxValueIndex];
        }else if (mRightballX + mRadius > gaps[maxValueIndex]+5){
            high = data[data.length-1];
        }
    }

    private void getLowData() {
        int maxValueIndex = data.length - 2;
        for (int i = 1; i < gaps.length; i++) {
            if (gaps[i] > mLeftballX + mRadius && i != data.length - 1) {
                //low = Math.round((mLeftballX + mRadius - gaps[i - 1]) * (Integer.valueOf(data[i]) - Integer.valueOf(data[i - 1])) / (gaps[i] - gaps[i - 1])) + Integer.valueOf(data[i - 1]) + "";
                low = ((int)((mLeftballX + mRadius - gaps[i - 1]) * (Integer.valueOf(data[i]) - Integer.valueOf(data[i - 1])) / (gaps[i] - gaps[i - 1]))) + Integer.valueOf(data[i - 1]) + "";
                //Log.e("test", "mLeftballX>>>>" + mLeftballX + "gaps" + gaps[i - 1]);
                break;
            }else if (i == data.length-1){
                low = data[maxValueIndex];
                break;
            }
        }
    }

    private static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale /1.5 + 0.5f);
    }

    private int getHitDataRegionIndex(int posX){
        int index = 0;
        int maxValueIndex = gaps.length;
        for (int i = 1; i < maxValueIndex; i++) {
            if (posX >= gaps[i-1] && posX < gaps[i]){
                index = i;
                break;
            }
        }
        return index;
    }

    private int getMinIntervalForDataRegion(int index){
        int minInterval = 0;
        if (index >= data.length - 1){
            index = data.length - 2;
        }
        if (index >= 1 && index < data.length - 1) {
            minInterval = (int) (MIN_VALUE_BETWEEN_SLIDERS * (gaps[index] - gaps[index - 1]) / (Integer.valueOf(data[index]) - Integer.valueOf(data[index - 1])));
        }
        return minInterval;
    }

    private int getPosXForValue(int value){
        float posX = 0;
        for (int i = 1; i < data.length - 1; i++){
            if (value <= Integer.valueOf(data[i])){
                posX = gaps[i-1] + (value - Integer.valueOf(data[i-1]))* (gaps[i] - gaps[i - 1]) / (Integer.valueOf(data[i]) - Integer.valueOf(data[i - 1]));
                break;
            }
        }
        return (int)posX;
    }

    public boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }


}
