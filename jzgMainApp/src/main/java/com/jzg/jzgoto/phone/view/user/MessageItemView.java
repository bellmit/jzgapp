package com.jzg.jzgoto.phone.view.user;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.adapter.user.MessageListAdapter;
import com.jzg.jzgoto.phone.models.business.user.MessageData;
import com.jzg.jzgoto.phone.view.ViewUtility;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MessageItemView extends LinearLayout implements View.OnClickListener{

    @Bind(R.id.btn_show_all)
    View mShowAllBtn;
    @Bind(R.id.view_message_title_textview)
    TextView mTitleTextView;
    @Bind(R.id.view_message_description_textview)
    TextView mDescriptionTextView;
    @Bind(R.id.view_message_time_textview)
    TextView mTimeTextView;
    @Bind(R.id.view_item_divider)
    View mItemDividerView;
    @Bind(R.id.view_message_description_expand_textview)
    TextView mExpandDescriptionTextView;

    private MessageData mMessageData;
    private MessageListAdapter mListAdapter;
    private boolean mIsExpand = false;


    public MessageItemView(Context context) {
        super(context);
        initViews();
    }

    public MessageItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    public MessageItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        updateViews();
    }

    public void setListAdapter(MessageListAdapter listAdapter){
        mListAdapter = listAdapter;
    }

    public void setData(MessageData data) {
        mMessageData = data;
        updateViews();
    }

    public MessageData getData(){
        return mMessageData;
    }

    private void updateViews() {
        if (mMessageData != null){
            mTitleTextView.setText(mMessageData.getTitle());
            mDescriptionTextView.setText(mMessageData.getDetail());
            mTimeTextView.setText(mMessageData.getTime());
            mExpandDescriptionTextView.setText(mMessageData.getDetail());
            if (mMessageData.isExpand){
                mExpandDescriptionTextView.setVisibility(View.VISIBLE);
                mDescriptionTextView.setVisibility(View.GONE);
                mShowAllBtn.setVisibility(View.GONE);
            }
        }
    }

    private void initViews() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View rootView = inflater.inflate(R.layout.view_user_message_item_view, this);
        ButterKnife.bind(this);
    }

    public void setDivider(boolean isLast){
        //mItemDividerView.setVisibility(isLast ? View.VISIBLE : View.GONE);
    }
    @Override
    @OnClick({R.id.btn_show_all})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_show_all:
                handleShowAllClicked();
                break;
        }
    }

    private void handleShowAllClicked(){
        mIsExpand = true;
        mExpandDescriptionTextView.setVisibility(View.VISIBLE);
        mDescriptionTextView.setVisibility(View.GONE);
        mShowAllBtn.setVisibility(View.GONE);
        if (mListAdapter != null){
            mListAdapter.handleItemExpand(this);
            mListAdapter.handleResizeListHeight();
        }
//        if (mMessageData != null && !TextUtils.isEmpty(mMessageData.getWebUrl())) {
//            ViewUtility.navigateToWebView(this.getContext(), "", mMessageData.getWebUrl());
//        }
    }

}
