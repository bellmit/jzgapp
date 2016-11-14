package com.jzg.jzgoto.phone.view.buy;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.models.business.buy.BuyCarFilterIndexModel;
import com.jzg.jzgoto.phone.tools.ClickControlTool;

/**
 * Created by WY on 2016/9/13.
 * Function :买车 新车/二手车选择界面
 */
public class BuyCarNewOrOldView extends LinearLayout{
    private Context mContext;

    public BuyCarNewOrOldView(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public BuyCarNewOrOldView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    public BuyCarNewOrOldView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
        initView();
    }

    private RelativeLayout mAllCarBtn,mOldCarBtn,mNewCarBtn;
    private TextView mAllCarText,mOldCarText,mNewCarText;
    private View mAllCarBottom,mOldCarBottom,mNewCarBottom;


    private void initView(){
        View view = LayoutInflater.from(mContext).inflate( R.layout.view_buycar_neworold_layout, null);
        mAllCarBtn = (RelativeLayout) view.findViewById(R.id.view_buycar_allCar_RelativeLayout);
        mOldCarBtn = (RelativeLayout)view.findViewById(R.id.view_buycar_oldCar_RelativeLayout);
        mNewCarBtn = (RelativeLayout)view.findViewById(R.id.view_buycar_newCar_RelativeLayout);
        mAllCarText = (TextView) view.findViewById(R.id.view_buycar_allCar_TextView);
        mOldCarText = (TextView) view.findViewById(R.id.view_buycar_oldCar_textView);
        mNewCarText = (TextView) view.findViewById(R.id.view_buycar_newCar_textView);
        mAllCarBottom = view.findViewById(R.id.view_buycar_allCar_bottomView);
        mOldCarBottom = view.findViewById(R.id.view_buycar_oldCar_bottomView);
        mNewCarBottom = view.findViewById(R.id.view_buycar_newCar_bottomView);
        mAllCarBtn.setOnClickListener(mClickListener);
        mOldCarBtn.setOnClickListener(mClickListener);
        mNewCarBtn.setOnClickListener(mClickListener);

        WindowManager windowManager = ((Activity)getContext()).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        LayoutParams params = new LayoutParams(display.getWidth(), LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);
        this.addView(view);
    }
    private OnClickListener mClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!ClickControlTool.isCanToClick())return;
            switch (v.getId()){
                case R.id.view_buycar_allCar_RelativeLayout:
                    // 全部车源
                    changeBtnBlue(mAllCarText,mAllCarBottom);
                    changeBtnGrey(mOldCarText,mOldCarBottom);
                    changeBtnGrey(mNewCarText,mNewCarBottom);
                    mRequestCallBack.toRequestBuyCarResult("0");
                    break;
                case R.id.view_buycar_oldCar_RelativeLayout:
                    //二手车
                    changeBtnGrey(mAllCarText,mAllCarBottom);
                    changeBtnBlue(mOldCarText,mOldCarBottom);
                    changeBtnGrey(mNewCarText,mNewCarBottom);
                    mRequestCallBack.toRequestBuyCarResult("1");
                    break;
                case R.id.view_buycar_newCar_RelativeLayout:
                    //新车
                    changeBtnGrey(mAllCarText,mAllCarBottom);
                    changeBtnGrey(mOldCarText,mOldCarBottom);
                    changeBtnBlue(mNewCarText,mNewCarBottom);
                    mRequestCallBack.toRequestBuyCarResult("2");
                    break;
            }
        }
    };

    private void changeBtnBlue(TextView blueTextView,View blueBg){
        if(blueTextView!=null)
            blueTextView.setTextColor(getResources().getColor(R.color.text_blue));
        if(blueBg!=null)
            blueBg.setBackgroundColor(getResources().getColor(R.color.text_blue));
    }
    private void changeBtnGrey(TextView greyTextView,View greyBg){
        if(greyTextView!=null)
            greyTextView.setTextColor(getResources().getColor(R.color.grey_for_valuation));
        if(greyBg!=null)
            greyBg.setBackgroundColor(getResources().getColor(R.color.background));
    }
    private RequestNewCarOrOldListCallBack mRequestCallBack;

    public void setRequestNewOrOldBuyCarCallback(RequestNewCarOrOldListCallBack callback) {
        mRequestCallBack = callback;
    }
    public interface RequestNewCarOrOldListCallBack{
        public void toRequestBuyCarResult(String carType);
    }
}
