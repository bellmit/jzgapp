package com.jzg.jzgoto.phone.view.carmanager;


import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.tools.AppUtils;
import com.jzg.jzgoto.phone.view.common.CustomDialog;

public class MileageInputDialog extends CustomDialog {

    private EditText mMileageEditor;

    public MileageInputDialog(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public MileageInputDialog(Context context, int theme) {
        super(context, theme);
        // TODO Auto-generated constructor stub
    }

    protected MileageInputDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_mileage_input_dialog_view);

        this.dialogTitle = (TextView) this.findViewById(R.id.custom_dialog_title);
        this.mMileageEditor = (EditText) this.findViewById(R.id.view_mileage_editor);
        this.dialogNegative = (TextView) this.findViewById(R.id.custom_dialog_ok);
        this.dialogPositive = (TextView) this.findViewById(R.id.custom_dialog_cancel);
        this.closeBarContainer = this.findViewById(R.id.view_close_bar_container);
        this.closeBtn = this.findViewById(R.id.view_close_btn);

        this.findViewById(R.id.custom_dialog_cancel).setOnClickListener(mCancelListener);
        this.findViewById(R.id.custom_dialog_ok).setOnClickListener(mConfirmListener);
        this.closeBtn.setOnClickListener(mCancelListener);

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

        mMileageEditor.setOnEditorActionListener(mMileageEditorActionListener);
        mMileageEditor.addTextChangedListener(mMileageEditorTexWatcher);
    }

    public String getMileage(){
        String mileage = null;
        if (mMileageEditor != null){
            mileage = mMileageEditor.getText().toString().trim();
        }
        return mileage;
    }

    private View.OnClickListener mCancelListener = new View.OnClickListener() {
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

    private View.OnClickListener mConfirmListener = new View.OnClickListener() {
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

    private EditText.OnEditorActionListener mMileageEditorActionListener = new EditText.OnEditorActionListener() {

        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            switch (actionId) {
                case EditorInfo.IME_ACTION_DONE:
                    return true;
            }
            return false;
        }
    };

    private TextWatcher mMileageEditorTexWatcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() == 0) {
                return;
            }
            AppUtils.VerifyMileageValid(MileageInputDialog.this.getContext(), mMileageEditor, s);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

}
