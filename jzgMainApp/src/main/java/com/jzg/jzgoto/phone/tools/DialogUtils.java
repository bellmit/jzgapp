package com.jzg.jzgoto.phone.tools;

import android.app.Activity;
import android.content.Context;
import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.view.carmanager.MileageInputDialog;
import com.jzg.jzgoto.phone.view.common.CustomDialog;
import com.jzg.jzgoto.phone.view.common.CustomDialog.OnCustomDialogClickListener;


public class DialogUtils {

    /**
     * 简单消息对话框
     * 
     * @param context
     * @param title
     *            标题
     * @param message
     *            内容
     * @param onClickListener
     *            点击事件监听
     */
    public static CustomDialog dialogMessage(Context context, String title, String message,
                                             final OnCustomDialogClickListener onClickListener) {

        if (context instanceof Activity && ((Activity) context).isFinishing()) {
            return null;
        }

        CustomDialog dialog = new CustomDialog(context, R.style.CustomDialogStyle);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setClickListener(onClickListener);
        dialog.show();
        return dialog;
    }

    /**
     * 简单消息对话框
     * 
     * @param context
     * @param titleId
     *            标题
     * @param messageId
     *            内容
     * @param onClickListener
     *            点击事件监听
     */
    public static CustomDialog dialogMessage(Context context, int titleId, int messageId,
            final OnCustomDialogClickListener onClickListener) {

        if (context instanceof Activity && ((Activity) context).isFinishing()) {
            return null;
        }

        CustomDialog dialog = new CustomDialog(context, R.style.CustomDialogStyle);
        dialog.setTitle(titleId);
        dialog.setMessage(messageId);
        dialog.setClickListener(onClickListener);
        dialog.show();
        return dialog;
    }

    /**
     * 
     * @param context
     * @param title
     *            标题
     * @param message
     *            内容
     * @param negativeButtonText
     *            右面按钮的文本
     * @param positiveButtonText
     *            左面按钮的文本
     * @param onClickListener
     *            点击事件监听
     * @return
     */
    public static CustomDialog dialogMessage(Context context, String title, String message, String negativeButtonText,
            String positiveButtonText, final OnCustomDialogClickListener onClickListener) {

        if (context instanceof Activity && ((Activity) context).isFinishing()) {
            return null;
        }

        CustomDialog dialog = new CustomDialog(context, R.style.CustomDialogStyle);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setNegativeButtonText(negativeButtonText);
        dialog.setPositiveButtonText(positiveButtonText);
        dialog.setClickListener(onClickListener);
        dialog.show();
        return dialog;
    }

    /**
     * 
     * @param context
     * @param title
     *            标题
     * @param message
     *            内容
     * @param negativeButtonText
     *            右面按钮的文本
     * @param positiveButtonText
     *            左面按钮的文本
     * @param onClickListener
     *            点击事件监听
     * @return
     */
    public static CustomDialog dialogMessage(Context context, String title, String message, int negativeButtonText,
            int positiveButtonText, final OnCustomDialogClickListener onClickListener) {

        if (context instanceof Activity && ((Activity) context).isFinishing()) {
            return null;
        }

        CustomDialog dialog = new CustomDialog(context, R.style.CustomDialogStyle);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setNegativeButtonText(negativeButtonText);
        dialog.setPositiveButtonText(positiveButtonText);
        dialog.setClickListener(onClickListener);
        dialog.show();
        return dialog;
    }

    /**
     * 简单消息对话框（只有一个确定按钮）
     * 
     * @param context
     * @param titleId
     *            标题
     * @param messageId
     *            内容
     * @param onClickListener
     *            点击事件监听
     */
    public static CustomDialog dialogSingleButtonMessage(Context context, int titleId, int messageId,
            final OnCustomDialogClickListener onClickListener) {
        if (context instanceof Activity && ((Activity) context).isFinishing()) {
            return null;
        }

        CustomDialog dialog = new CustomDialog(context, R.style.CustomDialogStyle);
        dialog.setTitle(titleId);
        dialog.setMessage(messageId);
        dialog.setPositiveButtonEnable(false);
        dialog.setClickListener(onClickListener);
        dialog.show();
        return dialog;
    }

    /**
     * 简单消息对话框（只有一个确定按钮）
     * 
     * @param context
     * @param title
     *            标题
     * @param message
     *            内容
     * @param onClickListener
     *            点击事件监听
     */
    public static CustomDialog dialogSingleButtonMessage(Context context, String title, String message,
            final OnCustomDialogClickListener onClickListener) {
        if (context == null) {
            return null;
        }
        if (context instanceof Activity && ((Activity) context).isFinishing()) {
            return null;
        }

        CustomDialog dialog = new CustomDialog(context, R.style.CustomDialogStyle);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setPositiveButtonEnable(false);
        dialog.setClickListener(onClickListener);
        dialog.show();
        return dialog;
    }

    /**
     * 简单消息对话框（只有一个确定按钮）
     * 
     * @param context
     * @param title
     *            标题
     * @param message
     *            内容
     * @param onClickListener
     *            点击事件监听
     */
    public static CustomDialog dialogSingleButtonMessage(Context context, String title, String message,
            final OnCustomDialogClickListener onClickListener, boolean isShow) {
        if (context instanceof Activity && ((Activity) context).isFinishing()) {
            return null;
        }

        CustomDialog dialog = new CustomDialog(context, R.style.CustomDialogStyle);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setPositiveButtonEnable(false);
        dialog.setClickListener(onClickListener);
        if (isShow) {
            dialog.show();
        }
        return dialog;
    }

    /**
     * 简单消息对话框（只有一个确定按钮）
     * 
     * @param context
     * @param titleId
     *            标题
     * @param messageId
     *            内容
     * @param onClickListener
     *            点击事件监听
     */
    public static CustomDialog dialogSingleButtonMessage(Context context, int titleId, int messageId,
            int negativeButtonText, final OnCustomDialogClickListener onClickListener) {
        if (context instanceof Activity && ((Activity) context).isFinishing()) {
            return null;
        }

        CustomDialog dialog = new CustomDialog(context, R.style.CustomDialogStyle);
        dialog.setTitle(titleId);
        dialog.setMessage(messageId);
        dialog.setNegativeButtonText(negativeButtonText);
        dialog.setPositiveButtonEnable(false);
        dialog.setClickListener(onClickListener);
        dialog.show();
        return dialog;
    }

    /**
     * 简单消息对话框（只有一个确定按钮）
     * 
     * @param context
     * @param title
     *            标题
     * @param message
     *            内容
     * @param onClickListener
     *            点击事件监听
     */
    public static CustomDialog dialogSingleButtonMessage(Context context, String title, String message,
            String negativeButtonText, final OnCustomDialogClickListener onClickListener) {
        if (context instanceof Activity && ((Activity) context).isFinishing()) {
            return null;
        }

        CustomDialog dialog = new CustomDialog(context, R.style.CustomDialogStyle);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setNegativeButtonText(negativeButtonText);
        dialog.setPositiveButtonEnable(false);
        dialog.setClickListener(onClickListener);
        dialog.show();
        return dialog;
    }

    public static MileageInputDialog dialogMileageInput(Context context, final OnCustomDialogClickListener onClickListener) {
        if (context instanceof Activity && ((Activity) context).isFinishing()) {
            return null;
        }

        MileageInputDialog dialog = new MileageInputDialog(context, R.style.CustomDialogStyle);
        dialog.setClickListener(onClickListener);
        dialog.show();
        return dialog;
    }

}
