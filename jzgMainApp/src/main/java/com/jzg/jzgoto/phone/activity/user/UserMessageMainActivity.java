package com.jzg.jzgoto.phone.activity.user;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.activity.shared.BaseActivity;
import com.jzg.jzgoto.phone.adapter.common.FragmentTabAdapter;
import com.jzg.jzgoto.phone.fragment.user.UserPrivateMessageFragment;
import com.jzg.jzgoto.phone.fragment.user.UserPublicMessageFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class UserMessageMainActivity extends BaseActivity implements View.OnClickListener {
    protected static final int TAB_INDEX_PRIVATE_MESSAGE = 0;
    protected static final int TAB_INDEX_PUBLIC_MESSAGE = 1;

    @Bind(R.id.btn_message_private_message)
    Button mPrivateMessageBtn;
    @Bind(R.id.btn_message_public_message)
    Button mPublicMessageBtn;

    private FragmentTabAdapter mFragmentTabAdapter;
    private List<Fragment> mFragments = null;
    private int mCurrentTabIndex;
    private UserPrivateMessageFragment mPrivateMessageFragment = null;
    private UserPublicMessageFragment mPublicMessageFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_center_main_message_layout);
        ButterKnife.bind(this);
        init();
    }

    public void init() {
        mFragments = new ArrayList<Fragment>();
        mPrivateMessageFragment = new UserPrivateMessageFragment();
        mPublicMessageFragment = new UserPublicMessageFragment();

        if(!mPrivateMessageFragment.isAdded()){
            mFragments.add(mPrivateMessageFragment);
        };
        if(!mPublicMessageFragment.isAdded()){
            mFragments.add(mPublicMessageFragment);
        };

        mCurrentTabIndex = TAB_INDEX_PRIVATE_MESSAGE;
        mFragmentTabAdapter = new FragmentTabAdapter(mFragments, this,
                mCurrentTabIndex);

        switchToTab(mCurrentTabIndex);
    }

    private void switchToTab(int tabIndex){
        mFragmentTabAdapter.settingFragment(tabIndex);
        mCurrentTabIndex = tabIndex;
        if (mCurrentTabIndex == TAB_INDEX_PRIVATE_MESSAGE){
            mPrivateMessageBtn.setSelected(true);
            mPublicMessageBtn.setSelected(false);
        }else{
            mPrivateMessageBtn.setSelected(false);
            mPublicMessageBtn.setSelected(true);
        }
    }

    @Override
    @OnClick({R.id.btn_message_private_message, R.id.btn_message_public_message})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_message_private_message:
                handlePrivateMessageClicked();
                break;
            case R.id.btn_message_public_message:
                handlePublicMessageClicked();
                break;
        }
    }

    private void handlePrivateMessageClicked(){
        switchToTab(TAB_INDEX_PRIVATE_MESSAGE);
    }

    private void handlePublicMessageClicked(){
        switchToTab(TAB_INDEX_PUBLIC_MESSAGE);
    }
}
