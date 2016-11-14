package com.jzg.jzgoto.phone.activity.carmanager;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.activity.shared.BaseActivity;
import com.jzg.jzgoto.phone.fragment.carmanager.MyCarListFragment;


public class MyCarListActivity extends BaseActivity {
    private MyCarListFragment mMyCarListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_car_list_layout);
        FragmentManager fm = this.getSupportFragmentManager();
        mMyCarListFragment = (MyCarListFragment) fm.findFragmentById(R.id.my_car_list_fragment);
    }
}
