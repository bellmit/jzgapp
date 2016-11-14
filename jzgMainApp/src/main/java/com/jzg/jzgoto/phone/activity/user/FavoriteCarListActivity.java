package com.jzg.jzgoto.phone.activity.user;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.activity.shared.BaseActivity;
import com.jzg.jzgoto.phone.fragment.user.FavoriteCarListFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class FavoriteCarListActivity extends BaseActivity implements View.OnClickListener{
    private FavoriteCarListFragment mMyCarListFragment;

    @Bind(R.id.btn_clean)
    View mCleanBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_car_list_layout);
        ButterKnife.bind(this);
        FragmentManager fm = this.getSupportFragmentManager();
        mMyCarListFragment = (FavoriteCarListFragment) fm.findFragmentById(R.id.my_car_list_fragment);
        mMyCarListFragment.setClearBtn(mCleanBtn);
    }
    @Override
    @OnClick({R.id.btn_clean})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_clean:
                handleCleanClicked();
                break;
        }
    }

    private void handleCleanClicked(){
        mMyCarListFragment.cleanFavorite();
    }

}
