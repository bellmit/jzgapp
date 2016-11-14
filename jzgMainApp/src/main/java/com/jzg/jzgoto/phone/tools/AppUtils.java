package com.jzg.jzgoto.phone.tools;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.widget.EditText;

import com.jzg.jzgoto.phone.R;

public class AppUtils {

    public static String getVersionName(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (Exception e) {
        }
        return "";
    }

    public static boolean VerifyMileageValid(final Context context, final EditText editText, CharSequence s) {
        // 包含小数点
        if (s.toString().contains(".")) {

            // 如果第一位为小数点
            if (".".equals(String.valueOf(s.charAt(0)))) {
                editText.setText("");
                ShowDialogTool.showCenterToast(context,
                        "第一位不能为小数点");

            } else if (Double.valueOf(String.valueOf(s)) > 100) {
                editText.setText("100");
                editText.setSelection("100".length());
            }
            // 如果小数点后长度大于2
            else if (s.toString()
                    .substring(s.toString().lastIndexOf(".")).length() > 3) {
                editText.setText(s.toString().substring(0,
                        s.length() - 1));
                editText.setSelection(s.toString()
                        .substring(0, s.length() - 1).length());
                ShowDialogTool.showCenterToast(context,
                        "只能输入小数点后两位");
            } else if ("0".equals(String.valueOf(s.charAt(0)))
                    && !".".equals(String.valueOf(s.charAt(1)))) {
                editText.setText("0");
                editText.setSelection(1);
            }

            // 不包含小数点
        } else {
            if (Integer.valueOf(String.valueOf(s)) > 100) {
                editText.setText("100");
                editText.setSelection("100".length());
            } else if ("0".equals(String.valueOf(s.charAt(0)))) {
                if (s.length() >= 2) {
                    editText.setText("0");
                    editText.setSelection(1);
                }
            }
        }
        return true;
    }

    public static boolean VerifyMoneyValid(final Context context, final EditText editText, CharSequence s) {
        // 包含小数点
        if (s.toString().contains(".")) {

            // 如果第一位为小数点
            if (".".equals(String.valueOf(s.charAt(0)))) {
                editText.setText("");
                ShowDialogTool.showCenterToast(context,
                        "第一位不能为小数点");

            } else if (Double.valueOf(String.valueOf(s)) > 1000) {
                editText.setText("1000");
                editText.setSelection("1000".length());
            }
            // 如果小数点后长度大于2
            else if (s.toString()
                    .substring(s.toString().lastIndexOf(".")).length() > 3) {
                editText.setText(s.toString().substring(0,
                        s.length() - 1));
                editText.setSelection(s.toString()
                        .substring(0, s.length() - 1).length());
                ShowDialogTool.showCenterToast(context,
                        "只能输入小数点后两位");
            } else if ("0".equals(String.valueOf(s.charAt(0)))
                    && !".".equals(String.valueOf(s.charAt(1)))) {
                editText.setText("0");
                editText.setSelection(1);
            }

            // 不包含小数点
        } else {
            if (Integer.valueOf(String.valueOf(s)) > 1000) {
                editText.setText("1000");
                editText.setSelection("1000".length());
            } else if ("0".equals(String.valueOf(s.charAt(0)))) {
                if (s.length() >= 2) {
                    editText.setText("0");
                    editText.setSelection(1);
                }
                ShowDialogTool.showCenterToast(context, "请输入小数点");
            }
        }
        return true;
    }
}
