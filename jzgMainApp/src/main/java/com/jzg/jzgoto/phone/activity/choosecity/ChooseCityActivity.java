package com.jzg.jzgoto.phone.activity.choosecity;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.location.AMapLocationListener;
import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.activity.choosecity.ChooseCity.CityListEntity;
import com.jzg.jzgoto.phone.activity.shared.BaseActivity;
import com.jzg.jzgoto.phone.adapter.choosecity.ChooseCityAdapter;
import com.jzg.jzgoto.phone.event.CityChooseEvent;
import com.jzg.jzgoto.phone.global.AppContext;
import com.jzg.jzgoto.phone.global.HttpServiceHelper;
import com.jzg.jzgoto.phone.json.JsonObjectImpl;
import com.jzg.jzgoto.phone.models.business.user.CityChooseData;
import com.jzg.jzgoto.phone.services.business.citylist.CityListService;
import com.jzg.jzgoto.phone.tools.CountClickTool;
import com.jzg.jzgoto.phone.tools.MD5Utils;
import com.jzg.jzgoto.phone.tools.StringUtil;
import com.jzg.jzgoto.phone.tools.Utils;
import com.jzg.jzgoto.phone.view.choosecity.ChooseCityMyLetterListView;
import com.jzg.jzgoto.phone.view.choosecity.ChooseCityMyLetterListView.OnTouchingLetterChangedListener;
import com.umeng.socialize.utils.Log;

public class ChooseCityActivity extends BaseActivity implements OnItemClickListener,
        AMapLocationListener {
    public static final String PARAMS_KEY_SOURCEFROM = "key_source_from_id";

    @Bind(R.id.choose_city_listview)
    ChooseCityMyLetterListView choose_city_listview;
    @Bind(R.id.index_city_list)
    ListView index_city_list;

    @Bind(R.id.dingwei_city_text)
    TextView dingwei_city_text;

    @Bind(R.id.dingwei_city_tishi_text)
    TextView dingwei_city_tishi_text;

    public static final int RESULT_OK = 0x000010;
    public static final String return_flag_cityname = "return_flag_cityname";
    public static final String return_flag_cityid = "return_flag_cityid";
    public static final String return_flag_provinceid = "return_flag_provinceid";
    public static final String update_flag = "2";
    public String cityName = "";
    public String cityId = "";
    public String provinceId = "0";

    public static final String HIDE_LOCATION_LAYOUT = "hide_location_layout";
    public static final String ADD_ALL_CITY = "add_all_city";
    private boolean isAddAllCity = false; //是否显示全国
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;
    private int mSourceFrom = 0;

    /**
     * 城市集合
     */
    private ArrayList<Map<String, Object>> items;

    private ChooseCityAdapter chooseCityAdapter;

    ArrayList<CityListEntity> citys;
    ArrayList<CityListEntity> recitys;

    ChooseCity chooseCity = null;

    CityListEntity cityListEntity = null;
    CityListService cityListService = null;

    private String requestLastUpdateTime = "";
    @Bind(R.id.chooseLayout)
    LinearLayout mLocationLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_city_layout);
        ButterKnife.bind(this);
        if(!TextUtils.isEmpty(getIntent().getStringExtra(HIDE_LOCATION_LAYOUT))){
            hideLoaction();
        };
        if(!TextUtils.isEmpty(getIntent().getStringExtra(ADD_ALL_CITY))){
            isAddAllCity = true;
        }
        mSourceFrom = getIntent().getIntExtra(PARAMS_KEY_SOURCEFROM, 0);
        init();
        initCity();
    }

    public void city_left_back(View v) {
        this.finish();
    }

    private void hideLoaction(){
        mLocationLayout.setVisibility(View.GONE);
    }
    public void init() {

        cityListService = CityListService.getInstance(getApplicationContext());
        chooseCity = cityListService.queryListList();

        // 如果缓存有数据就显示缓存，没有就请求网络
        if(chooseCity.getCityList().size()>0){
            showCityList(chooseCity);
            requestLastUpdateTime = chooseCity.getLastUpdateTime();
            requestCity();
        } else {
            requestCity();
        }

        choose_city_listview
                .setOnTouchingLetterChangedListener(new OnTouchingLetterChangedListener() {

                    @Override
                    public void onTouchingLetterChanged(String s) {
                        System.out.println("sss===" + s);
                        if(chooseCityAdapter!=null)
                            if (chooseCityAdapter.getPosition(s) != -1) {
                                index_city_list.requestFocusFromTouch();
                                index_city_list.setSelection(chooseCityAdapter
                                        .getPosition(s));
                            }

                    }
                });

    }

    public void initCity() {

        dingwei_city_text.setText(getCityName());
        cityName = getCityName();
        locationClient = new AMapLocationClient(getApplicationContext());
        locationOption = new AMapLocationClientOption();
        // 设置定位模式为高精度模式
        locationOption.setLocationMode(AMapLocationMode.Hight_Accuracy);
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

    @OnClick({ R.id.chooseLayout })
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.chooseLayout:
                returnCityInfo();
                break;

            default:
                break;
        }
    }

    // 请求城市列表
    public void requestCity() {
//		toShowLoadingDialog();
        HttpServiceHelper.requestCitys(this, mHandler, getParams(),
                R.id.city_suc, R.id.city_fail);
    }

    /**
     * 请求城市列表提交的参数
     */
    public Map<String, String> getParams() {

        // 在这里设置需要post的参数
        Map<String, String> map = new HashMap<String, String>();
        map.put("op", "GetCityList");
        map.put("LastUpData", requestLastUpdateTime);

        StringUtil.log("ChooseCityAty", "请求城市列表提交的参数" + map.toString());
        Map<String, Object> maps = new HashMap<String, Object>();
        maps.put("op", "GetCityList");
        maps.put("LastUpData", requestLastUpdateTime);
        map.put("sign", MD5Utils.getMD5Sign(maps));

        return map;
    }

    long startTime, endTime;

    Handler mHandler = new Handler() {
        public void dispatchMessage(android.os.Message msg) {
            dismissLoadingDialog();
            switch (msg.what) {
                // 开始定位
                case Utils.MSG_LOCATION_START:
                    dingwei_city_tishi_text.setText("GPS定位中...");
                    Log.i("HomePageFragment", "开始定位");
                    // 启动定位
                    if (locationClient != null) {
                        locationClient.startLocation();
                    }

                    startTime = System.currentTimeMillis();
                    break;
                // 定位完成
                case Utils.MSG_LOCATION_FINISH:
                    endTime = System.currentTimeMillis();
                    Log.i("HomePageFragment", "定位成功用时---" + (endTime - startTime));

                    AMapLocation loc = (AMapLocation) msg.obj;
                    // String result = Utils.getLocationStr(loc);
                    if(loc.getErrorCode() ==0 ){
                        //	定位成功
                        CountClickTool.onEvent(ChooseCityActivity.this, "location_success");
                    }
                    String city = Utils.getLocationStrCity(loc);
                    String province = Utils.getLocationStrProvince(loc);

                    Log.i("HomePageFragment", "定位城市---" + city);

                    if ("全国".equals(city)) {
                        cityName = getCityName();
                        cityId = "0";
                        dingwei_city_text.setText(cityName);
                        AppContext.cityName = StringUtil.returnShi(cityName);
                        // AppContext.provinceName =
                        // StringUtil.returnShi(getProvinceName());
                    } else {
                        cityName = StringUtil.returnShi(city);
                        dingwei_city_text.setText(cityName);
                        setCityName(cityName);
                        cityId = "0";
                        // setProvinceName(StringUtil.returnShi(province));
                        AppContext.cityName = cityName;
                        AppContext.provinceName = StringUtil.returnShi(province);
                    }

                    // 停止定位
                    if (locationClient != null)
                        locationClient.stopLocation();
                    mHandler.sendEmptyMessage(Utils.MSG_LOCATION_STOP);

                    break;
                // 停止定位
                case Utils.MSG_LOCATION_STOP:
                    dingwei_city_tishi_text.setText("GPS定位");
                    break;
                // 请求城市成功
                case R.id.city_suc:
                    String result = (String) msg.obj;
                    if (JsonObjectImpl.isSuccess(ChooseCityActivity.this, result)) {
                        updateCity(result);
                    }
                    break;

                default:
                    break;
            }
        };
    };

    public void updateCity(String cityjson) {
        // String result = getResources().getString(R.string.cityjson);
        ChooseCityJsonObjectImpl chooseCityJsonObjectImpl = new ChooseCityJsonObjectImpl();
        ChooseCity chooseCityjson = chooseCityJsonObjectImpl.parserChooseCity(cityjson);

        if (chooseCity.getCityList().size() >0) {
            for (int i = 0; i < chooseCityjson.getCityList().size(); i++) {
                if (update_flag.equals(chooseCityjson.getCityList().get(i)
                        .getStatus())) {
                    // 更新
//					showMsgToast("更新==" + i);
                    cityListService.updateListList(chooseCityjson);
                }
            }

        } else {
            // 第一次请求，全部插入数据
            toShowLoadingDialog();
            cityListService.insertCityList(chooseCityjson);

        }


        chooseCity = cityListService.queryListList();
//		dismissLoadingDialog();
        showCityList(chooseCity);
    }

    /**
     * 显示城市数据
     *
     */
    protected void showCityList(ChooseCity chooseCity) {

        List<CityListEntity> lists = chooseCity.getCityList();

        if (lists != null) {

            citys = new ArrayList<CityListEntity>();
            recitys = new ArrayList<CityListEntity>();
            if(isAddAllCity){
                CityListEntity chooseCity1 = new CityListEntity();
                chooseCity1.setCityID("0");
                chooseCity1.setGroupName("热");
                chooseCity1.setIsHotCity("1");
                chooseCity1.setName("全国");
                chooseCity1.setProvID("0");
                chooseCity1.setOrderIndex("00");
                chooseCity1.setStatus("1");
                recitys.add(chooseCity1);
            }


            for (int i = 0; i < lists.size(); i++) {
                CityListEntity chooseCity1 = new CityListEntity();
                chooseCity1.setCityID(lists.get(i).getCityID());
                chooseCity1.setGroupName(lists.get(i).getGroupName());
                chooseCity1.setIsHotCity(lists.get(i).getIsHotCity());
                chooseCity1.setName(lists.get(i).getName());
                chooseCity1.setProvID(lists.get(i).getProvID());
                chooseCity1.setOrderIndex(lists.get(i).getOrderIndex());
                chooseCity1.setStatus(lists.get(i).getStatus());

                citys.add(chooseCity1);
                if ("1".equals(lists.get(i).getIsHotCity())) {
                    recitys.add(chooseCity1);
                }
            }

            items = new ArrayList<Map<String, Object>>();
            if (citys != null) {
                for (int i = 0; i < citys.size(); i++) {
//					String contactSort = null;
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("name", citys.get(i));
                    final CityListEntity entity = (CityListEntity) map.get("name");
//					String contactSort =
//					JzgCarChooseChineseUtil
//							.getFullSpell(entity.getName())
//							.toUpperCase().substring(0, 1);
                    map.put("Sort", entity.getGroupName());
                    items.add(map);
                }
                Comparator comp = new Mycomparator();
                Collections.sort(items, comp);

                Comparator order = new MyOrderIndex();
                Collections.sort(recitys, order);

                HashMap<String, Object> mapre = null;
                for (int i = 0; i < recitys.size(); i++) {
                    mapre = new HashMap<String, Object>();
                    mapre.put("name", recitys.get(i));
                    mapre.put("Sort", "热");
                    items.add(0, mapre);
                }

                chooseCityAdapter = new ChooseCityAdapter(
                        getApplicationContext(), items);
                index_city_list.setAdapter(chooseCityAdapter);
                index_city_list.setOnItemClickListener(this);
            }
        }
    }

    // 按中文拼音排序
    public class Mycomparator implements Comparator {
        @Override
        public int compare(Object o1, Object o2) {
            Map<String, Object> c1 = (Map<String, Object>) o1;
            Map<String, Object> c2 = (Map<String, Object>) o2;
            Comparator cmp = Collator.getInstance(java.util.Locale.ENGLISH);
            return cmp.compare(c1.get("Sort"), c2.get("Sort"));
        }
    }

    class MyOrderIndex implements Comparator {
        public int compare(Object o1, Object o2) {
            CityListEntity s1 = (CityListEntity) o1;
            CityListEntity s2 = (CityListEntity) o2;
            if (Integer.parseInt(s1.getOrderIndex()) < Integer.parseInt(s2
                    .getOrderIndex()))
                return 1;
            return -1;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        System.out.println("po==" + position + "----id--" + id);
        cityName = ((CityListEntity) items.get(position).get("name")).getName()
                .toString();
        cityId = ((CityListEntity) items.get(position).get("name")).getCityID()
                .toString();
        provinceId = ((CityListEntity) items.get(position).get("name")).getProvID()
                .toString();
        // showMsgToast(cityName);

        returnCityInfo();
    }

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
        SharedPreferences sp = this.getSharedPreferences("CITY", 1);
        city = sp.getString("CityName", "北京");
        return city;
    }

    private void setCityName(String _cityName) {
        SharedPreferences sp = this.getSharedPreferences("CITY", 1);
        Editor editor = sp.edit();
        editor.putString("CityName", _cityName);
        editor.commit();
    }

    private void returnCityInfo() {
        CityChooseData cityInfo = new CityChooseData();
        cityInfo.cityId = cityId;
        cityInfo.cityName = cityName;
        cityInfo.provinceId = provinceId;
        EventBus.getDefault().post(CityChooseEvent.build(cityInfo, mSourceFrom));

        Intent it = new Intent();
        it.putExtra(return_flag_cityname, cityName);
        it.putExtra(return_flag_cityid, cityId);
        it.putExtra(return_flag_provinceid, provinceId);
        setResult(RESULT_OK, it);
        finish();

    }

}
