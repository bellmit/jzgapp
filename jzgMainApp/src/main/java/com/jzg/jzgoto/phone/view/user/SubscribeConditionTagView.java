package com.jzg.jzgoto.phone.view.user;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jzg.jzgoto.phone.R;
import butterknife.Bind;
import butterknife.ButterKnife;



public class SubscribeConditionTagView extends LinearLayout{

    @Bind(R.id.view_tag_text)
    TextView mTagTextView;

    private String mTagText;


    public SubscribeConditionTagView(Context context) {
        super(context);
        initViews();
    }

    public SubscribeConditionTagView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    public SubscribeConditionTagView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        updateViews();
    }

    public void setTagText(String tagStr) {
        mTagText = tagStr;
        updateViews();
    }

    private void updateViews() {
        mTagTextView.setText(mTagText);
    }

    private void initViews() {
    	LayoutInflater inflater = LayoutInflater.from(getContext());
    	View rootView = inflater.inflate(R.layout.view_user_subscribe_condition_tag_layout, this);
        ButterKnife.bind(this);
    }


}
