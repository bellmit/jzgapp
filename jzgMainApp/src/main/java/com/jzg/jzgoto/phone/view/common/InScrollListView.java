package com.jzg.jzgoto.phone.view.common;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class InScrollListView extends ListView {

    public InScrollListView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public InScrollListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public InScrollListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
        MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
