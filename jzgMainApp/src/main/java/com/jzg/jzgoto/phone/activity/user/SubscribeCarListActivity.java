package com.jzg.jzgoto.phone.activity.user;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.activity.shared.BaseActivity;
import com.jzg.jzgoto.phone.fragment.user.SubscribeCarListFragment;



public class SubscribeCarListActivity extends BaseActivity{
    private SubscribeCarListFragment mMyCarListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe_car_list_layout);
        FragmentManager fm = this.getSupportFragmentManager();
        mMyCarListFragment = (SubscribeCarListFragment) fm.findFragmentById(R.id.my_car_list_fragment);
    }

}
