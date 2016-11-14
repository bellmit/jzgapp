package com.jzg.jzgoto.phone.view.sellcar;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.tools.ClickControlTool;
import com.jzg.jzgoto.phone.view.ViewUtility;

/**
 * Created by WY on 2016/9/20.
 * Function :卖车 车型/上牌时间等 选择界面
 */
public class SellCarChooseView extends LinearLayout{
    public SellCarChooseView(Context context) {
        super(context);
        initView();
    }
    public SellCarChooseView(Context context, AttributeSet attrs) {
        super(context,attrs);
        initView();
    }
    public SellCarChooseView(Context context, AttributeSet attrs,
                                int defStyleAttr) {
        super(context,attrs,defStyleAttr);
        initView();
    }
    private Button mSellCarBtn,mRepalceBtn,mValuationBtn;

    private void initView(){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_sellcar_choose_layout,null);
        mSellCarBtn = (Button) view.findViewById(R.id.sellcar_choose_sellCar_button);
        mRepalceBtn = (Button) view.findViewById(R.id.sellcar_choose_replace_button);
        mValuationBtn = (Button) view.findViewById(R.id.sellcar_choose_valuation_button);
        mSellCarBtn.setOnClickListener(mOnClickListener);
        mRepalceBtn.setOnClickListener(mOnClickListener);
        mValuationBtn.setOnClickListener(mOnClickListener);
        WindowManager windowManager = ((Activity)getContext()).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        LayoutParams params = new LayoutParams(display.getWidth(), LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);
        this.addView(view);
    }
    private OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!ClickControlTool.isCanToClick())return;
            switch (v.getId()){
                case R.id.sellcar_choose_sellCar_button:
                    //一键卖车
                    ViewUtility.navigateToSellCarOneKeyActivity(getContext());
                break;
                case R.id.sellcar_choose_replace_button:
                    //置换新车
                    ViewUtility.navigateToReplaceNewCarActivity(getContext());
                    break;
                case R.id.sellcar_choose_valuation_button:
                    //详细估值
                    break;

            }
        }
    };
}
