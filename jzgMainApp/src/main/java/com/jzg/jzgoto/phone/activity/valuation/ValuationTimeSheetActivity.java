package com.jzg.jzgoto.phone.activity.valuation;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.constant.Constant;
import com.jzg.jzgoto.phone.event.CityChooseEvent;
import com.jzg.jzgoto.phone.event.TimeChooseEvent;

import net.simonvt.numberpicker.NumberPicker;

import de.greenrobot.event.EventBus;

/**
 * Created by WY on 2016/9/22.
 * Function :时间选择界面
 */
public class ValuationTimeSheetActivity extends Activity{
    public static final String PARAMS_KEY_SOURCEFROM = "key_source_from_id";
    private int mSourceFrom = 0;

    int win_width ;
    int win_height;
    private NumberPicker yearNumberPicker;
    private NumberPicker monthNumberPicker;


    private int maxYear;
    private int minYear;

    private int maxMonth = 12;
    private int minMonth = 1;
    private int maxYearMonth = 12;
    private int minYearMonth = 1;

    private int currentYear;
    private int currentMonth;
    private int currentMAXMonth;

    private TextView mTvTitleShow;
    private TextView mTvTitleYiguoqi;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 隐藏标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setFinishOnTouchOutside(true);

        setContentView(R.layout.activity_valuation_timesheet_layout);

        mSourceFrom = getIntent().getIntExtra(PARAMS_KEY_SOURCEFROM, 0);
        yearNumberPicker = (NumberPicker)findViewById(R.id.yearNumberPicker);
        monthNumberPicker = (NumberPicker)findViewById(R.id.monthNumberPicker);

        mTvTitleShow = (TextView) findViewById(R.id.tv_sheet_time_show);
        mTvTitleYiguoqi = (TextView) findViewById(R.id.time_yiguoqi);

        String title = getIntent().getStringExtra("title");
        if(!TextUtils.isEmpty(title)){
            mTvTitleShow.setText(title);
            mTvTitleYiguoqi.setVisibility(View.VISIBLE);
            mTvTitleYiguoqi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent();
                    in.putExtra("yiguoqi", "yiguoqi");
                    setResult(Constant.Valuation_Time_MSG, in);
                    ValuationTimeSheetActivity.this.finish();
                }
            });
        }
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay(); // 为获取屏幕宽、高
        WindowManager.LayoutParams p = getWindow().getAttributes(); // 获取对话框当前的参数值
        Point size = new Point();
        d.getSize(size);
        win_width = size.x;
        win_height = size.y;
        p.gravity = Gravity.BOTTOM;
        p.height = (int) (win_height * 0.5); // 高度设置为屏幕的0.6
        p.width = (int) (win_width * 1); // 宽度设置为屏幕的1
        p.alpha = 1.0f;// 设置透明度

        maxYear = getIntent().getIntExtra("Maxyear", 2015);
        minYear = getIntent().getIntExtra("Minyear", 2015);
        maxYearMonth = getIntent().getIntExtra("MaxMonth", 12);
        minYearMonth = getIntent().getIntExtra("MinMonth", 1);
        currentMAXMonth = getIntent().getIntExtra("CurMonth", 1);

        this.getWindow().setAttributes(p);


        currentYear = maxYear;			//默认显示最大年限
        currentMonth = maxYearMonth;
        yearNumberPicker.setMaxValue(maxYear);
        yearNumberPicker.setMinValue(minYear);
        yearNumberPicker.setValue(currentYear);

        monthNumberPicker.setMaxValue(maxYearMonth);
        monthNumberPicker.setMinValue(minMonth);
        monthNumberPicker.setValue(currentMonth);

        yearNumberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        monthNumberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        yearNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                int year  = picker.getValue();
                currentYear = year;

                updateMonth();
            }
        });
        monthNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                int month  = picker.getValue();
                currentMonth = month;
            }
        });


        updateMonth();

    }


    public void updateMonth(){
        if(yearNumberPicker.getValue() == maxYear){
			/*if(StringUtil.getMonth() !=1){
				monthNumberPicker.setMaxValue(StringUtil.getMonth()-1);
			}else{
				yearNumberPicker.setMaxValue(maxYear-1);
				monthNumberPicker.setMaxValue(12);
			}*/
            monthNumberPicker.setMaxValue(maxYearMonth);
            monthNumberPicker.setMinValue(minMonth);

        }else if(yearNumberPicker.getValue() == minYear){
            monthNumberPicker.setMaxValue(maxMonth);
            monthNumberPicker.setMinValue(minYearMonth);
        }else{
            monthNumberPicker.setMaxValue(maxMonth);
            monthNumberPicker.setMinValue(minMonth);
        }
    }


    public void time_done(View v){
        back_data();
        this.finish();
    }


    public void back_data(){
        Intent in = new Intent();
        in.putExtra("year", yearNumberPicker.getValue());
        in.putExtra("month", monthNumberPicker.getValue());
        setResult(Constant.Valuation_Time_MSG, in);
        EventBus.getDefault().post(TimeChooseEvent.build(yearNumberPicker.getValue(), monthNumberPicker.getValue(), mSourceFrom));
    }
}
