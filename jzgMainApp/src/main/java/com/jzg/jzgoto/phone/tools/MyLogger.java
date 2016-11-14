package com.jzg.jzgoto.phone.tools;

import android.text.TextUtils;
import android.util.Log;

public class MyLogger {

    public static boolean isDebug = true;

    public static void e(String tag, String msg) {
        if (isDebug && !TextUtils.isEmpty(tag) && !TextUtils.isEmpty(msg)) {
            Log.e(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (isDebug && !TextUtils.isEmpty(tag) && !TextUtils.isEmpty(msg)) {
            Log.w(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (isDebug && !TextUtils.isEmpty(tag) && !TextUtils.isEmpty(msg)) {
            Log.i(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (isDebug && !TextUtils.isEmpty(tag) && !TextUtils.isEmpty(msg)) {
            Log.d(tag, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (isDebug && !TextUtils.isEmpty(tag) && !TextUtils.isEmpty(msg)) {
            Log.v(tag, msg);
        }
    }
}
