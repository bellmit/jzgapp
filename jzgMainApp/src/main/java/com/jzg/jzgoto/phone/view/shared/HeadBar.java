package com.jzg.jzgoto.phone.view.shared;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jzg.jzgoto.phone.R;


public class HeadBar extends RelativeLayout {
    public static enum ClickType {
        Menu, Back, Search, Setting, Share, Left, Right, Cancel, Close
    }

    public interface OnHeadBarClickListener {
        public void onHeadBarClick(ClickType type);
    }

    public static final int LEFT_BUTTON_NONE = 0x00;
    public static final int LEFT_BUTTON_BACK = 0x01;
    public static final int LEFT_BUTTON_MENU = 0x02;
    public static final int LEFT_BUTTON_TEXT = 0x03;
    public static final int LEFT_BUTTON_CLOSE = 0x04;

    private static final int RIGHT_BUTTON_NONE = 0x00;
    private static final int RIGHT_BUTTON_SEARCH = 0x01;
    private static final int RIGHT_BUTTON_SETTING = 0x02;
    private static final int RIGHT_BUTTON_SHARE = 0x03;
    private static final int RIGHT_BUTTON_TEXT = 0x04;
    private static final int RIGHT_BUTTON_CANCEL = 0x05;

    private int leftButtonType;
    private int rightButtonType;
    private String title;
    private String leftButtonText;
    private String rightButtonText;

    private TextView titleTextView;
    private View rightBtnContainer;
    private View leftBtnContainer;
    private ImageView rightBtn;
    private ImageView leftBtn;
    private TextView leftTextView;
    private TextView rightTextView;
    private RelativeLayout headerContainer;

    private OnHeadBarClickListener listener;

    public HeadBar(Context context) {
        super(context);
        throw new RuntimeException("Not support yet.");
    }

    public HeadBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public void setTitle(String title) {
        this.title = title;
        this.titleTextView.setText(this.title);
    }

    public void setLeftButtonType(int type) {
        this.leftButtonType = type;
        if (this.leftButtonType == LEFT_BUTTON_NONE) {
            this.leftBtnContainer.setVisibility(View.GONE);
        } else if (this.leftButtonType == LEFT_BUTTON_TEXT) {
            this.leftBtn.setVisibility(View.GONE);
            this.leftTextView.setVisibility(View.VISIBLE);
        } else if (this.leftButtonType == LEFT_BUTTON_CLOSE) {
            this.leftBtnContainer.setVisibility(View.VISIBLE);
            this.leftTextView.setVisibility(View.GONE);
            this.leftBtn.setImageResource(R.drawable.header_bar_nav_closed_selector);
        } else {
            this.leftBtnContainer.setVisibility(View.VISIBLE);
            if (this.leftButtonType == LEFT_BUTTON_BACK) {
                this.leftTextView.setText(R.string.back_text);
                this.leftTextView.setVisibility(View.GONE);
                this.leftBtn.setImageResource(R.drawable.header_bar_nav_back_selector);
            }
        }
    }

    public void setOnHeadBarClickListener(OnHeadBarClickListener listener) {
        this.listener = listener;
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.HeadBar);
        this.leftButtonType = a.getInt(R.styleable.HeadBar_leftButton, LEFT_BUTTON_BACK);
        this.rightButtonType = a.getInt(R.styleable.HeadBar_rightButton, RIGHT_BUTTON_NONE);
        this.title = a.getString(R.styleable.HeadBar_barTitle);
        this.leftButtonText = a.getString(R.styleable.HeadBar_leftButtonText);
        this.rightButtonText = a.getString(R.styleable.HeadBar_rightButtonText);
        a.recycle();

        LayoutInflater.from(context).inflate(R.layout.view_shared_headbar, this, true);
        this.titleTextView = (TextView) this.findViewById(R.id.headbar_title);
        this.rightBtnContainer = this.findViewById(R.id.headbar_right_btn_container);
        this.leftBtnContainer = this.findViewById(R.id.headbar_left_btn_container);
        this.rightBtn = (ImageView) this.findViewById(R.id.headbar_right_btn);
        this.leftBtn = (ImageView) this.findViewById(R.id.headbar_left_btn);
        this.leftTextView = (TextView) this.findViewById(R.id.headbar_left_text);
        this.rightTextView = (TextView) this.findViewById(R.id.headbar_right_text);
        this.headerContainer = (RelativeLayout) this.findViewById(R.id.header_container);

        this.titleTextView.setText(this.title);
        this.leftTextView.setText(this.leftButtonText);
        this.rightTextView.setText(this.rightButtonText);

        setLeftButtonType(leftButtonType);

        // init right button
        if (this.rightButtonType == RIGHT_BUTTON_NONE) {
            this.rightBtnContainer.setVisibility(View.GONE);
            this.rightTextView.setVisibility(View.GONE);
        } else if (this.rightButtonType == RIGHT_BUTTON_TEXT) {
            this.rightBtnContainer.setVisibility(View.VISIBLE);
            this.rightTextView.setVisibility(View.VISIBLE);
        } else if (this.rightButtonType == RIGHT_BUTTON_SHARE) {
            this.rightBtnContainer.setVisibility(View.VISIBLE);
            this.rightTextView.setVisibility(View.GONE);
            this.rightBtn.setImageResource(R.drawable.header_bar_nav_share_selector);
        } else {
            this.rightBtnContainer.setVisibility(View.VISIBLE);
            this.rightTextView.setVisibility(View.GONE);
            if (this.rightButtonType == RIGHT_BUTTON_SEARCH) {
                this.rightBtn.setImageResource(R.drawable.header_bar_nav_search_selector);
            } else if (this.rightButtonType == RIGHT_BUTTON_CANCEL) {
                this.rightBtn.setVisibility(View.GONE);
                this.rightTextView.setVisibility(View.VISIBLE);
                this.rightTextView.setText(R.string.cancel_text);
            }
        }
        this.leftBtnContainer.setOnClickListener(onLeftBtnClickListener);
        this.rightBtnContainer.setOnClickListener(onRightBtnClickListener);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        this.leftBtn.setOnClickListener(null);
        this.rightBtn.setOnClickListener(null);
        this.leftTextView.setOnClickListener(null);
        this.rightTextView.setOnClickListener(null);
        super.onDetachedFromWindow();
    }

    public void setContainerBackground(Drawable drawable) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
            headerContainer.setBackground(drawable);
        } else {
            headerContainer.setBackgroundDrawable(drawable);
        }
    }

    public void setContainerBackgroundColor(int color) {
        this.headerContainer.setBackgroundColor(color);
    }

    private View.OnClickListener onLeftBtnClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            if (listener == null) {
                if (leftButtonType == LEFT_BUTTON_BACK && getContext() instanceof Activity) {
                    ((Activity) getContext()).finish();
                }
                return;
            }
            if (leftButtonType == LEFT_BUTTON_BACK) {
                listener.onHeadBarClick(ClickType.Back);
            } else if (leftButtonType == LEFT_BUTTON_MENU) {
                listener.onHeadBarClick(ClickType.Menu);
            } else if (leftButtonType == LEFT_BUTTON_TEXT) {
                listener.onHeadBarClick(ClickType.Left);
            } else if (leftButtonType == LEFT_BUTTON_CLOSE) {
                listener.onHeadBarClick(ClickType.Close);
            }
        }
    };

    private View.OnClickListener onRightBtnClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            if (listener == null) {
                return;
            }
            if (rightButtonType == RIGHT_BUTTON_SEARCH) {
                listener.onHeadBarClick(ClickType.Search);
            } else if (rightButtonType == RIGHT_BUTTON_SETTING) {
                listener.onHeadBarClick(ClickType.Setting);
            } else if (rightButtonType == RIGHT_BUTTON_SHARE) {
                listener.onHeadBarClick(ClickType.Share);
            } else if (rightButtonType == RIGHT_BUTTON_TEXT) {
                listener.onHeadBarClick(ClickType.Right);
            } else if (rightButtonType == RIGHT_BUTTON_CANCEL) {
                listener.onHeadBarClick(ClickType.Cancel);
            }
        }
    };

    public TextView getRightTextView() {
        return rightTextView;
    }

    public TextView getTitleTextView() {
        return titleTextView;
    }

    public View getRightBtnContainer() {
        return rightBtnContainer;
    }
}