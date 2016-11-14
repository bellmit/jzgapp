package com.jzg.jzgoto.phone.tools;

import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jzg.jzgoto.phone.R;


public class ToastManager {

    public static enum TOAST_TYPE {
        DEFAULT, ERROR, DONE, ATTENTION
    };

    private static ToastManager sInstance = new ToastManager();
    private Toast toast;

    private ToastManager() {
    }

    public static ToastManager getInstance() {
        return sInstance;
    }

    public void showToastCenter(Context context, String text) {
        if (context == null) {
            return;
        }
        showToast(context, text, Gravity.CENTER);
    }

    public void showToastCenter(Context context, int textResId) {
        if (context == null) {
            return;
        }
        showToast(context, context.getResources().getString(textResId), Gravity.CENTER);
    }

    public void showToastCenter(Context context, int textResId, int imageResId) {
        if (context == null) {
            return;
        }
        showToast(context, context.getResources().getString(textResId), imageResId, Gravity.CENTER);
    }

    public void showToastCenter(Context context, String text, int imageResId) {
        if (context == null) {
            return;
        }
        showToast(context, text, imageResId, Gravity.CENTER);
    }

    public void showToastCenter(Context context, int textResID, TOAST_TYPE type) {
        if (context == null) {
            return;
        }
        showToastCenter(context, context.getResources().getString(textResID), type);
    }

    public void showToastCenter(Context context, String text, TOAST_TYPE type) {
        if (context == null) {
            return;
        }
        int imageResID = 0;
        switch (type) {
        case ERROR:
            break;
        case DONE:
            break;
        case ATTENTION:
            break;
        default:
            imageResID = 0;
            break;
        }
        
        if(imageResID > 0) {
            showToastCenter(context, text, imageResID);
        } else {
            showToastCenter(context, text);
        }
        
    }

    public void showToast(Context context, String text) {
        if (context == null) {
            return;
        }
        showToast(context, text, Gravity.BOTTOM);
    }

    public void showToast(Context context, int textResId) {
        if (context == null) {
            return;
        }
        showToast(context, context.getResources().getString(textResId), Gravity.BOTTOM);
    }

    public void showToast(Context context, String text, int gravity) {
        showToast(context, text, 0, gravity);
    }

    public void showToast(Context context, String text, int imageResourceID, int gravity) {
        if (context == null) {
            return;
        }
        cancelToast();
        toast = new Toast(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View toastView = inflater.inflate(R.layout.view_shared_toast_tipview, null);
        ((TextView) toastView.findViewById(R.id.toast_tip_text)).setText(text);
        ((ImageView) toastView.findViewById(R.id.toast_tip_image)).setImageResource(imageResourceID);
        if (gravity == Gravity.CENTER) {
            // (toastView.findViewById(R.id.toast_tip_image)).setVisibility(View.VISIBLE);
            toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast.setGravity(Gravity.BOTTOM, 0, DisplayUtils.dpToPx(context, 70));
        }
        toast.setView(toastView);
        toast.show();
    }

    public void cancelToast() {
        if (toast != null) {
            toast.cancel();
        }
    }

    public void onDistroy() {
        cancelToast();
        toast = null;
    }

    public int dpToPx(Context context, int dp) {
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context
                .getResources().getDisplayMetrics());
        return (int) px;
    }
}
