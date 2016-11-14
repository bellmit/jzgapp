
package com.jzg.jzgoto.phone.activity.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.activity.common.ConstantForAct;
import com.jzg.jzgoto.phone.adapter.common.FragmentTabAdapter;
import com.jzg.jzgoto.phone.event.CityChooseEvent;
import com.jzg.jzgoto.phone.event.RefreshMyCarEvent;
import com.jzg.jzgoto.phone.fragment.carmanager.CarManagerFragment;
import com.jzg.jzgoto.phone.fragment.main.HomeFragment;
import com.jzg.jzgoto.phone.global.AppContext;
import com.jzg.jzgoto.phone.global.Statistical;
import com.jzg.jzgoto.phone.services.common.OnRequestFinishListener;
import com.jzg.jzgoto.phone.tools.ClickControlTool;
import com.jzg.jzgoto.phone.tools.CountClickTool;
import com.jzg.jzgoto.phone.tools.DialogUtils;
import com.jzg.jzgoto.phone.tools.StringUtil;
import com.jzg.jzgoto.phone.tools.Utils;
import com.jzg.jzgoto.phone.updateapp.UpdateManager;
import com.jzg.jzgoto.phone.activity.shared.BaseActivity;
import com.jzg.jzgoto.phone.view.ViewUtility;
import com.jzg.jzgoto.phone.view.common.CustomDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;


public class HomeActivity extends BaseActivity implements OnClickListener, OnRequestFinishListener, AMapLocationListener {

    public static final String PARAMS_KEY_FORWARD_TARGET = "forward";
    public static final String PARAMS_KEY_REFRESH_CAR_MANAGER = "refreshcarmanager";
    public static final String FORWARD_TO_CARMANAGER = "carmanager";

    protected static final int SOURCE_FROM_HOME_ACTIVITY = 0x1001;

    protected static final String TAG = HomeActivity.class.getSimpleName();
    protected static final int TAB_INDEX_HOME = 0;
    protected static final int TAB_INDEX_CARMANAGER = 1;

    @Bind(R.id.btn_choosecity)
    TextView mCityTextView;
    @Bind(R.id.btn_home)
    TextView mHomeTextView;
    @Bind(R.id.btn_carmanager)
    TextView mCarManagerTextView;
    @Bind(R.id.view_home_tab_indicator)
    View mHomeTabIndicatorView;
    @Bind(R.id.view_carmanager_tab_indicator)
    View mCarManagerTabIndicatorView;
    @Bind(R.id.tab_item_container)
    View mHeaderBarView;

    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;
    public String mCityName = "";
    public String mCityId = "";

    public static HomeActivity self;
    private FragmentTabAdapter mFragmentTabAdapter;// Fragment试图切换控制器
    private List<Fragment> mFragments = null;// 所有Fragment页
    private int mCurrentTabIndex;
    private HomeFragment mHomePageFragment = null;
    private CarManagerFragment mCarManagerFragment = null;
    private DrawerLayout mDrawerLayout;
    private ViewGroup mMenuLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        HomeActivity.self = this;
        setContentView(R.layout.activity_home_layout);
        ButterKnife.bind(this);
        mMenuLayout = (ViewGroup)findViewById(R.id.menu_frame);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerListener(mDrawerListener);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        init();

        /**
         * 检查新版本 2015-4-20 zhengyq
         */
        UpdateManager.getUpdateManager().checkAppUpdate(this, false);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null && FORWARD_TO_CARMANAGER.equals(intent.getStringExtra(PARAMS_KEY_FORWARD_TARGET))) {
            boolean isRefreshCar = intent.getBooleanExtra(PARAMS_KEY_REFRESH_CAR_MANAGER, false);
            switchToTab(TAB_INDEX_CARMANAGER);
            if (isRefreshCar){
                EventBus.getDefault().post(new RefreshMyCarEvent());
            }
        }
    }

    public void onEvent(CityChooseEvent event) {
        if (event != null && event.cityInfo != null && event.sourceFrom == SOURCE_FROM_HOME_ACTIVITY) {
            mCityId = event.cityInfo.cityId;
            mCityName = StringUtil.returnShi(event.cityInfo.cityName);
            if (!TextUtils.isEmpty(mCityName)) {
                mCityTextView.setText(mCityName);
                setCityName(mCityName);
                AppContext.cityName = mCityName;
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private DrawerListener mDrawerListener = new DrawerListener() {

        public void onDrawerStateChanged(int arg0) {
        }

        public void onDrawerSlide(View arg0, float arg1) {
        }

        public void onDrawerOpened(View arg0) {
        }

        public void onDrawerClosed(View arg0) {
        }
    };

    public void init() {
        initCity();
        mFragments = new ArrayList<Fragment>();
        mHomePageFragment = new HomeFragment();
        mCarManagerFragment = new CarManagerFragment();

        if(!mHomePageFragment.isAdded()){
            mFragments.add(mHomePageFragment);
        };
        if(!mCarManagerFragment.isAdded()){
            mFragments.add(mCarManagerFragment);
        };

        final Context context = this;
        boolean isQueried = ConstantForAct.getQueryShowCarManagerAsHome(context);
        boolean hasAddMyCar = ConstantForAct.getHasAddMyCar(context);
        boolean skipToMyCar = ConstantForAct.getShowCarManagerAsHome(context);
        if (isQueried && skipToMyCar){
            mCurrentTabIndex = TAB_INDEX_CARMANAGER;
        }else{
            mCurrentTabIndex = TAB_INDEX_HOME;
        }

        mFragmentTabAdapter = new FragmentTabAdapter(mFragments, this,
                mCurrentTabIndex);
        mFragmentTabAdapter.setUseAnimation(false);

        switchToTab(mCurrentTabIndex);
        showConfirmCarManagerDefaultDialog();
        mHomePageFragment.setHeaderBarView(mHeaderBarView);
    }

    @Override
    @OnClick({R.id.btn_choosecity, R.id.view_home_tab_container, R.id.view_carmanager_tab_container, R.id.btn_menu})
    public void onClick(View view) {
        if (!ClickControlTool.isCanToClickFor200()) {
            return;
        }

        switch (view.getId()) {
            case R.id.btn_choosecity:
                handChooseCityClicked();
                break;
            case R.id.view_home_tab_container:
                handHomeClicked();
                break;
            case R.id.view_carmanager_tab_container:
                handCarManagerClicked();
                break;
            case R.id.btn_menu:
                handMenuClicked();
                break;
            default:
                break;
        }
    }

    private void showConfirmCarManagerDefaultDialog(){
        final Context context = this;
        boolean isQueried = ConstantForAct.getQueryShowCarManagerAsHome(context);
        boolean hasAddMyCar = ConstantForAct.getHasAddMyCar(context);
        if (!hasAddMyCar){
            return;
        }
        if (isQueried){
            return;
        }
        String buttonCancelText = "否";
        String buttonOKText = "是";
        String content = getResources().getString(R.string.car_manager_show_default_tip);
        DialogUtils.dialogMessage(context, "", content, buttonOKText, buttonCancelText,
                new CustomDialog.OnCustomDialogClickListener() {
                    @Override
                    public boolean onCustomDialogClick(CustomDialog.CustomDialogClickType type) {
                        switch (type) {
                            case Ok:
                                ConstantForAct.setQueryShowCarManagerAsHome(context, true);
                                ConstantForAct.setShowCarManagerAsHome(context, true);
                                CountClickTool.onEvent(HomeActivity.this, Statistical.KEY_CAR_MANAGER_SHOW_YES_CLICK_COUNT);
                                break;
                            case Cancel:
                                ConstantForAct.setQueryShowCarManagerAsHome(context, true);
                                ConstantForAct.setShowCarManagerAsHome(context, false);
                                CountClickTool.onEvent(HomeActivity.this, Statistical.KEY_CAR_MANAGER_SHOW_NO_CLICK_COUNT);
                                break;
                        }
                        return false;
                    }
                });
    }

    private void handChooseCityClicked(){
        ViewUtility.navigateToChooseCityActivity(this, SOURCE_FROM_HOME_ACTIVITY);
    }

    private void handHomeClicked(){
        switchToTab(TAB_INDEX_HOME);
    }

    private void handCarManagerClicked(){
        switchToTab(TAB_INDEX_CARMANAGER);
    }

    private void handMenuClicked(){
        showMenu();
    }

    public void showMenu() {
        if (!mDrawerLayout.isDrawerOpen(mMenuLayout)){
            mDrawerLayout.openDrawer(mMenuLayout);
        }
    }

    public void closeMenu() {
        mDrawerLayout.closeDrawer(mMenuLayout);
    }

    private void switchToTab(int tabIndex){
        mFragmentTabAdapter.settingFragment(tabIndex);
        mCurrentTabIndex = tabIndex;
        if (mCurrentTabIndex == TAB_INDEX_HOME){
            mHomeTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                    getResources().getDimensionPixelSize(R.dimen.home_fragment_tab_selected_text_size));
            mCarManagerTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                    getResources().getDimensionPixelSize(R.dimen.home_fragment_tab_normal_text_size));
            mHomeTextView.setSelected(true);
            mCarManagerTextView.setSelected(false);
            mHomeTabIndicatorView.setVisibility(View.VISIBLE);
            mCarManagerTabIndicatorView.setVisibility(View.INVISIBLE);
            mHeaderBarView.getBackground().setAlpha(mHomePageFragment.getHeaderViewAlpha());
        }else{
            mHomeTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                    getResources().getDimensionPixelSize(R.dimen.home_fragment_tab_normal_text_size));
            mCarManagerTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                    getResources().getDimensionPixelSize(R.dimen.home_fragment_tab_selected_text_size));
            mHomeTextView.setSelected(false);
            mCarManagerTextView.setSelected(true);
            mHomeTabIndicatorView.setVisibility(View.INVISIBLE);
            mCarManagerTabIndicatorView.setVisibility(View.VISIBLE);
            mHeaderBarView.getBackground().setAlpha(255);
        }
    }

    @Override
    public void onRequestSuccess(Message msg) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onRequestFault(Message msg) {
        // TODO Auto-generated method stub

    }

    public void initCity() {

        //mLeftCity.setText(getCityName());

        locationClient = new AMapLocationClient(HomeActivity.this.getApplicationContext());
        locationOption = new AMapLocationClientOption();
        // 设置定位模式为高精度模式
        locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        // // 设置定位监听
        locationClient.setLocationListener(this);
        initOption();
        // 设置定位参数
        locationClient.setLocationOption(locationOption);

        mHandler.sendEmptyMessageDelayed(Utils.MSG_LOCATION_START, 1000);
    }

    // 根据控件的选择，重新设置定位参数
    private void initOption() {
        // 设置是否需要显示地址信息
        locationOption.setNeedAddress(true);
        /**
         * 设置是否优先返回GPS定位结果，如果30秒内GPS没有返回定位结果则进行网络定位 注意：只有在高精度模式下的单次定位有效，其他方式无效
         */
        locationOption.setGpsFirst(true);
        // 设置发送定位请求的时间间隔,最小值为2000，如果小于2000，按照2000算
        locationOption.setInterval(Long.valueOf("2000"));

        // 设置是否只定位一次,默认为false
        locationOption.setOnceLocation(false);
        // 设置是否强制刷新WIFI，默认为强制刷新
        locationOption.setWifiActiveScan(true);

    }

    Handler mHandler = new Handler() {
        public void dispatchMessage(android.os.Message msg) {
            switch (msg.what) {
                // 开始定位
                case Utils.MSG_LOCATION_START:
                    mCityTextView.setText("正在定位...");
                    // 启动定位
                    if (locationClient != null) {
                        locationClient.startLocation();
                        CountClickTool.onEvent(HomeActivity.this, "location_start");
                    }
                    break;
                // 定位完成
                case Utils.MSG_LOCATION_FINISH:
                    AMapLocation loc = (AMapLocation) msg.obj;
                    if (loc.getErrorCode() == 0) {
                        //	定位成功
                        CountClickTool.onEvent(HomeActivity.this, "location_success");
                    }
                    // String result = Utils.getLocationStr(loc);
                    String city = Utils.getLocationStrCity(loc);
                    String province = Utils.getLocationStrProvince(loc);
                    if ("全国".equals(city)) {
                        mCityName = getCityName();
                        mCityId = "";
                        mCityTextView.setText(mCityName);
                        AppContext.cityName = StringUtil.returnShi(mCityName);
                    } else {
                        mCityName = StringUtil.returnShi(city);
                        mCityTextView.setText(mCityName);
                        setCityName(mCityName);
                        mCityId = "";
                        AppContext.cityName = mCityName;
                        AppContext.provinceName = StringUtil.returnShi(province);
                    }

                    // 停止定位
                    if (locationClient != null)
                        locationClient.stopLocation();
                    mHandler.sendEmptyMessage(Utils.MSG_LOCATION_STOP);

                    break;
                // 停止定位
                case Utils.MSG_LOCATION_STOP:
                    break;


                default:
                    break;
            }
        }

        ;
    };

    // 定位监听
    @Override
    public void onLocationChanged(AMapLocation loc) {
        if (null != loc) {
            Message msg = mHandler.obtainMessage();
            msg.obj = loc;
            msg.what = Utils.MSG_LOCATION_FINISH;
            mHandler.sendMessage(msg);
        }
    }

    private String getCityName() {
        String city = "北京";
        SharedPreferences sp = HomeActivity.this.getSharedPreferences("CITY", 1);
        city = sp.getString("CityName", "北京");
        return city;
    }

    private void setCityName(String _cityName) {
        SharedPreferences sp = HomeActivity.this.getSharedPreferences("CITY", 1);
        Editor editor = sp.edit();
        editor.putString("CityName", _cityName);
        editor.commit();
    }

}
