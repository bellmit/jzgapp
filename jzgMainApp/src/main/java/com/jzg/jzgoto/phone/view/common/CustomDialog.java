package com.jzg.jzgoto.phone.view.common;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.jzg.jzgoto.phone.R;

public class CustomDialog extends Dialog {

    protected TextView dialogTitle;
    protected TextView dialogMessage;
    protected TextView dialogNegative;
    protected TextView dialogPositive;
    protected View closeBarContainer;
    protected View closeBtn;
    protected CharSequence title;
    protected CharSequence message;
    protected CharSequence negativeButtonText; // 右面按钮的文本
    protected CharSequence positiveButtonText; // 左面按钮的文本
    protected boolean positiveButtonEnable = true; // 是否启用左面的按钮
    protected boolean isAttachWindow;

    protected OnCustomDialogClickListener clickListener;

    public static enum CustomDialogClickType {
        Cancel, Ok
    }

    public interface OnCustomDialogClickListener {
        public boolean onCustomDialogClick(CustomDialogClickType type);
    }

    public CustomDialog(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public CustomDialog(Context context, int theme) {
        super(context, theme);
        // TODO Auto-generated constructor stub
    }

    protected CustomDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_custom_dialog_view);

        this.dialogTitle = (TextView) this.findViewById(R.id.custom_dialog_title);
        this.dialogMessage = (TextView) this.findViewById(R.id.custom_dialog_message);
        this.dialogNegative = (TextView) this.findViewById(R.id.custom_dialog_ok);
        this.dialogPositive = (TextView) this.findViewById(R.id.custom_dialog_cancel);
        this.closeBarContainer = this.findViewById(R.id.view_close_bar_container);
        this.closeBtn = this.findViewById(R.id.view_close_btn);

        this.findViewById(R.id.custom_dialog_cancel).setOnClickListener(mCancelListener);
        this.findViewById(R.id.custom_dialog_ok).setOnClickListener(mConfirmListener);
        this.closeBtn.setOnClickListener(mCancelListener);

        if (TextUtils.isEmpty(title)) {
            this.dialogTitle.setVisibility(View.GONE);
        } else {
            this.dialogTitle.setText(title);
        }

        if (TextUtils.isEmpty(message)) {
            this.dialogMessage.setVisibility(View.GONE);
        } else {
            this.dialogMessage.setText(message);
        }

        if (!TextUtils.isEmpty(negativeButtonText)) {
            this.dialogNegative.setText(negativeButtonText);
        }

        if (!TextUtils.isEmpty(positiveButtonText)) {
            this.dialogPositive.setText(positiveButtonText);
        }

        if (positiveButtonEnable) {
            dialogPositive.setVisibility(View.VISIBLE);
        } else {
            dialogPositive.setVisibility(View.GONE);
            dialogNegative.setBackgroundResource(R.drawable.btn_blue_round_selector);
        }
    }

    protected View.OnClickListener mCancelListener = new View.OnClickListener() {
        public void onClick(View v) {
            // try to fix dialog will not dismiss after activity finish issue
            if (isShowing()) {
                dismiss();
            }
            if (clickListener != null) {
                clickListener.onCustomDialogClick(CustomDialogClickType.Cancel);
            }
        }
    };

    protected View.OnClickListener mConfirmListener = new View.OnClickListener() {
        public void onClick(View v) {
            // to fix dialog will not dismiss after activity finish issue
            if (clickListener != null) {
                if (!clickListener.onCustomDialogClick(CustomDialogClickType.Ok)){
                    if (isShowing()) {
                        dismiss();
                    }
                }
            }else{
                if (isShowing()) {
                    dismiss();
                }
            }
        }
    };

    @Override
    public void show() {
        // TODO Auto-generated method stub
        super.show();
    }

    @Override
    public void setTitle(int titleId) {
        // TODO Auto-generated method stub
        if (titleId > 0)
            setTitle(getContext().getResources().getString(titleId));
    }

    @Override
    public void setTitle(CharSequence title) {
        // TODO Auto-generated method stub
        this.title = title;
        if (dialogTitle != null){
            dialogTitle.setText(title);
        }

    }
    
    @Override
    public void dismiss() {
    	if (isAttachWindow){
    		super.dismiss();
    	}
    }
    
    @Override
    public void onAttachedToWindow() {
    	isAttachWindow = true;
    	super.onAttachedToWindow();
    }
    
    @Override
    public void onDetachedFromWindow() {
    	isAttachWindow = false;
    	super.onDetachedFromWindow();
    }

    public void setMessage(int messageId) {
        if (messageId > 0)
            setMessage(getContext().getResources().getString(messageId));
    }

    public void setMessage(CharSequence message) {
        this.message = message;
    }

    public void setPositiveButtonEnable(boolean enable) {
        this.positiveButtonEnable = enable;
    }

    public void setClickListener(OnCustomDialogClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setNegativeButtonText(CharSequence negativeButtonText) {
        this.negativeButtonText = negativeButtonText;
        if (dialogNegative != null){
            dialogNegative.setText(negativeButtonText);
        }
    }

    public void setPositiveButtonText(CharSequence positiveButtonText) {
        this.positiveButtonText = positiveButtonText;
        if (dialogPositive != null){
            dialogPositive.setText(positiveButtonText);
        }
    }

    public void setNegativeButtonText(int negativeButtonText) {
        setNegativeButtonText(getContext().getResources().getString(negativeButtonText));
    }

    public void setPositiveButtonText(int positiveButtonText) {
        setPositiveButtonText(getContext().getResources().getString(positiveButtonText));
    }

    public TextView getDialogMessage(){
        return dialogMessage;
    }

    public void showCloseBar(boolean show){
        this.closeBarContainer.setVisibility(show ? View.VISIBLE : View.GONE);
    }

}
