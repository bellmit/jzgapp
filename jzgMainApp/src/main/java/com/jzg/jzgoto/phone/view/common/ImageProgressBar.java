package com.jzg.jzgoto.phone.view.common;


import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Paint.Cap;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Property;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.tools.DisplayUtils;

public class ImageProgressBar extends View {
    
    private Paint mPaint;
    private boolean mRunning;
    private ObjectAnimator mObjectAnimatorSweep;
    private ObjectAnimator mObjectAnimatorAngle;
    private float mBorderWidth = 10;
    private int mBorderColor;
    private final RectF fBounds = new RectF();
    private static final int ANGLE_START = -90;
    private static final int ANGLE_START_ANIMATION_DURATION = 600;
    private static final int ANGLE_END_ANIMATION_DURATION = 1000;
    
    private float mCurrentStartAngle;
    private float mCurrentEndAngle;
    
    private boolean bAutoAnimation = true;
    
    public ImageProgressBar(Context context) {
        super(context);
        init(context, null);
    }
    
    public ImageProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ImageProgressBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }
    
    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ImageProgressBar);
            mBorderColor = a.getColor(R.styleable.ImageProgressBar_progressRimColor, Color.WHITE);
            mBorderWidth = a.getDimensionPixelSize(R.styleable.ImageProgressBar_progressRimWidth, DisplayUtils.dpToPx(context, 2));
            bAutoAnimation = a.getBoolean(R.styleable.ImageProgressBar_autoAnimationEnabled, true);
            a.recycle();
        }
        
        setupAnimations();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Cap.ROUND);
        mPaint.setStrokeWidth(mBorderWidth);
        mPaint.setColor(mBorderColor);
    }
    
    
    public void startAnim() {
        start();
    }
    
    public void stopAnim() {
        stop();
    }
    
    public void updateArcPosition(float rotatationOffset) {
        if(isRunning()) {
            return;
        }
        mCurrentStartAngle = 360* rotatationOffset;
        mCurrentEndAngle = 0;
        invalidate();
    }
    
    private void start() {
        if (isRunning()) {
            return;
        }
        mRunning = true;
        //mObjectAnimatorAngle.start();
        mCurrentStartAngle = 0;
        mCurrentEndAngle = 0;
        mObjectAnimatorSweep.start();
        invalidate();
    }

    private void stop() {
        if (!isRunning()) {
            return;
        }
        mRunning = false;
        mObjectAnimatorAngle.cancel();
        mObjectAnimatorSweep.cancel();
        invalidate();
    }
    
    private boolean isRunning() {
        return mRunning;
    }
    
    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (visibility == VISIBLE) {
            if(bAutoAnimation) {
                start();
            }
        } else {
            if(bAutoAnimation) {
                stop();
            }
        }
    }
    
    @Override
    protected void onAttachedToWindow() {
        if(bAutoAnimation) {
            start();
        }
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        if(bAutoAnimation) {
            stop();    
        }
        super.onDetachedFromWindow();
    }
    
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        float startAngle = mCurrentStartAngle + ANGLE_START;
        float endAngle = mCurrentEndAngle + ANGLE_START;
        
        canvas.drawArc(fBounds, endAngle, startAngle - endAngle, false, mPaint);
    }
    
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        fBounds.left = mBorderWidth / 2f + .5f;
        fBounds.right = w - mBorderWidth / 2f - .5f;
        fBounds.top = mBorderWidth / 2f + .5f;
        fBounds.bottom = h - mBorderWidth / 2f - .5f;
    }
    
    public float getCurrentAngle() {
        return mCurrentStartAngle;
    }
    
    public void setCurrentAngle(float value) {
        mCurrentStartAngle = value;
        postInvalidate();
    }
    
    public float getCurrentEndAngle() {
        return mCurrentEndAngle;
    }
    
    public void setCurrentEndAngle(float value) {
        mCurrentEndAngle = value;
        postInvalidate();
    }
    
    
    
    private Property<ImageProgressBar, Float> mAngleProperty = new Property<ImageProgressBar, Float>(Float.class, "angle") {
        @Override
        public Float get(ImageProgressBar object) {
            return object.getCurrentAngle();
        }

        @Override
        public void set(ImageProgressBar object, Float value) {
            object.setCurrentAngle(value);
        }
    };

    private Property<ImageProgressBar, Float> mSweepProperty = new Property<ImageProgressBar, Float>(Float.class, "endangle") {
        @Override
        public Float get(ImageProgressBar object) {
            return object.getCurrentEndAngle();
        }

        @Override
        public void set(ImageProgressBar object, Float value) {
            object.setCurrentEndAngle(value);
        }
    };

    
    private void setupAnimations() {
        mObjectAnimatorAngle = ObjectAnimator.ofFloat(this, mAngleProperty, 360f);
        mObjectAnimatorAngle.setInterpolator(new AccelerateDecelerateInterpolator());
        mObjectAnimatorAngle.setDuration(ANGLE_START_ANIMATION_DURATION);
        //mObjectAnimatorAngle.setRepeatMode(ValueAnimator.RESTART);
        //mObjectAnimatorAngle.setRepeatCount(ValueAnimator.INFINITE);

        mObjectAnimatorSweep = ObjectAnimator.ofFloat(this, mSweepProperty, 360f);
        mObjectAnimatorSweep.setInterpolator(new LinearInterpolator());
        mObjectAnimatorSweep.setDuration(ANGLE_END_ANIMATION_DURATION);
        mObjectAnimatorSweep.setRepeatMode(ValueAnimator.RESTART);
        mObjectAnimatorSweep.setRepeatCount(ValueAnimator.INFINITE);
        mObjectAnimatorSweep.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                mObjectAnimatorAngle.start();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                mObjectAnimatorAngle.cancel();
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                mObjectAnimatorAngle.start();
            }
        });
    }
}
