package com.jzg.jzgoto.phone.view.error;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public abstract class BaseErrorView extends FrameLayout {
    public interface ErrorListener {
        void OnErrorRefresh();
    }

    public enum ErrorType {
        NetworkNotAvailable, NoData, NotFound, Loading, Error, Delete
    }

    protected ErrorType errorType;
    protected ErrorListener errorlistener;
    protected int emptyDataResId;
    protected int emptyDataImageResId;

    public BaseErrorView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public BaseErrorView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseErrorView(Context context) {
        super(context);
    }

    public ErrorType getErrorType() {
        return this.errorType;
    }

    public void setErrorListener(ErrorListener errorListener) {
        this.errorlistener = errorListener;
    }

    public void setEmptyDataResId(int emptyDataResId) {
        this.emptyDataResId = emptyDataResId;
    }

    public void setEmptyDataImageResId(int emptyDataImageResId) {
        this.emptyDataImageResId = emptyDataImageResId;
    }

    public abstract void setErrorType(ErrorType errorType);
}
