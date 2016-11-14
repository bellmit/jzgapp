package com.jzg.jzgoto.phone.tools;

import com.jzg.jzgoto.phone.R;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 底部弹出框
 */
public class ActionSheet {

    public interface OnActionSheetListener {
        public void onActionClicked(int whichButton);
        public void onActionCancel();
    }

    private ActionSheet() {
    }

    public static Dialog showSheet(Context context,
                                   final OnActionSheetListener listener, String action1, String action2,
                                   String cancel, final int actionId1, final int actionId2, boolean isTake) {
        final Dialog dlg = new Dialog(context, R.style.ActionSheet);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout layout = (LinearLayout) inflater.inflate(
                R.layout.view_action_sheet_layout, null);
        final int cFullFillWidth = 10000;
        layout.setMinimumWidth(cFullFillWidth);
        TextView mTitle = (TextView) layout.findViewById(R.id.title);
        mTitle.setText(action1);
        TextView mContent = (TextView) layout.findViewById(R.id.content);
        mContent.setText(action2);
        if (!isTake)
            mContent.setVisibility(View.GONE);
        TextView mCancel = (TextView) layout.findViewById(R.id.cancel);
        mCancel.setText(cancel);
        mTitle.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (listener != null){
                    listener.onActionClicked(actionId1);
                }
                dlg.dismiss();
            }
        });
        mContent.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (listener != null){
                    listener.onActionClicked(actionId2);
                }
                dlg.dismiss();
            }
        });

        mCancel.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                dlg.dismiss();
            }
        });
        dlg.setOnCancelListener(new OnCancelListener(){
            public void onCancel(DialogInterface dialog){
                if (listener != null){
                    listener.onActionCancel();
                }
            }
        });

        Window w = dlg.getWindow();
        WindowManager.LayoutParams lp = w.getAttributes();
        lp.x = 0;
        final int cMakeBottom = -1000;
        lp.y = cMakeBottom;
        lp.gravity = Gravity.BOTTOM;
        dlg.onWindowAttributesChanged(lp);
        dlg.setContentView(layout);
        dlg.show();

        return dlg;
    }

}
