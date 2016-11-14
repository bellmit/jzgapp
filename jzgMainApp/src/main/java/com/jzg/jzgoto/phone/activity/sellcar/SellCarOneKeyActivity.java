package com.jzg.jzgoto.phone.activity.sellcar;

import android.app.Activity;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.adapter.sell.OneKeyReleasePlatformAdapter;
import com.jzg.jzgoto.phone.event.RefreshMyCarEvent;
import com.jzg.jzgoto.phone.global.AppContext;
import com.jzg.jzgoto.phone.global.GlobalData;
import com.jzg.jzgoto.phone.models.business.login.GetAutoCodeParamsModels;
import com.jzg.jzgoto.phone.models.business.login.GetAutoCodeResultModels;
import com.jzg.jzgoto.phone.models.business.sell.OneKeyReleasePlatformParams;
import com.jzg.jzgoto.phone.models.business.sell.OneKeyReleasePlatformResult;
import com.jzg.jzgoto.phone.models.business.sell.ReleaseSellTruckMessageParams;
import com.jzg.jzgoto.phone.models.business.sell.ReleaseSellTruckMessageResult;
import com.jzg.jzgoto.phone.services.business.login.LoginService;
import com.jzg.jzgoto.phone.services.common.OnRequestFinishListener;
import com.jzg.jzgoto.phone.tools.ClickControlTool;
import com.jzg.jzgoto.phone.tools.CountClickTool;
import com.jzg.jzgoto.phone.tools.ShowDialogTool;

import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

import static com.jzg.jzgoto.phone.R.id.edit_phone;

/**
 * Created by WY on 2016/9/21.
 * Function :一键卖车界面
 */
public class SellCarOneKeyActivity extends Activity implements OnRequestFinishListener {

    @Bind(R.id.close_img)
    ImageView mCloseBtn;
    @Bind(edit_phone)
    EditText editPhone;
    @Bind(R.id.text_verify)
    TextView textVerify;
    @Bind(R.id.onekey_edit_verify)
    EditText onekeyEditVerify;
    @Bind(R.id.one_key_btn)
    Button oneKeyBtn;
    @Bind(R.id.listview)
    ListView listView;
    public final int REQUEST_PLATFORM_DATA = 0x0007;
    public final int REQUEST_VERIFY = 0x0008;
    public final int RELEASE_SELL_TRUCK_MESSAGE = 0x0009;
    private String mAutoPhoneNum = null;
    private String mAutoCodeFromNet = null;
    private String styleid;
    private String butlerId;
    private String regdate;
    private String mileage;
    private String cityid;
    private String cityname;
    private String sellprice;
    private OneKeyReleasePlatformAdapter oneKeyReleasePlatformAdapter;
    private OneKeyReleasePlatformResult oneKeyReleasePlatformResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 隐藏标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sellcar_onekey_layout);
        ButterKnife.bind(this);
        editPhone.clearFocus();
        onekeyEditVerify.clearFocus();
        styleid = getIntent().getStringExtra("styleid");
        //车管家id
//        butlerId = getIntent().getStringExtra("ButlerId");
        regdate = getIntent().getStringExtra("regdate");
        mileage = getIntent().getStringExtra("mileage");
        cityid = getIntent().getStringExtra("cityid");
        cityname = getIntent().getStringExtra("cityname");
        //销售价格
        sellprice = getIntent().getStringExtra("sellprice");
        initListener();
        initWindowShow();
        initData();

    }

    private void initData() {
        oneKeyReleasePlatformData();
    }

    private void oneKeyReleasePlatformData() {
        LoginService loginService = new LoginService(this, this);
        OneKeyReleasePlatformParams oneKeyReleasePlatformParams = new OneKeyReleasePlatformParams();
        ShowDialogTool.showLoadingDialog(this);
        loginService.toRequestNet(oneKeyReleasePlatformParams, OneKeyReleasePlatformResult.class, REQUEST_PLATFORM_DATA);
    }

    public void initListener() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ClickControlTool.isCanToClick()) {
                    return;
                }
                switch (v.getId()) {
                    case R.id.text_verify:
                        acquireGetCode();
                        break;
                    case R.id.close_img:
                        finish();
                        break;
                    case R.id.one_key_btn://发布卖车信息
                        ReleaseSellTruckMessage();
                        CountClickTool.onEvent(SellCarOneKeyActivity.this, "V5_valuation_sell_car_oneKey");
                        break;
                    default:

                        break;
                }

            }
        };
        textVerify.setOnClickListener(listener);
        mCloseBtn.setOnClickListener(listener);
        oneKeyBtn.setOnClickListener(listener);

    }

    //发布卖车信息
    private void ReleaseSellTruckMessage() {
        if(checkoutParams()){
            LoginService loginService = new LoginService(this, this);
            ReleaseSellTruckMessageParams releaseSellTruckMessageParams = new ReleaseSellTruckMessageParams();
            releaseSellTruckMessageParams.ButlerId = GlobalData.getInstance().getCarManagerId();
            releaseSellTruckMessageParams.cityid = cityid;
            releaseSellTruckMessageParams.cityname = cityname;
            releaseSellTruckMessageParams.mileage = mileage;
            releaseSellTruckMessageParams.mobile = editPhone.getText().toString().trim();
            releaseSellTruckMessageParams.regdate = regdate;
            releaseSellTruckMessageParams.sellprice = sellprice;
            releaseSellTruckMessageParams.styleid = styleid;
            if (AppContext.isLogin()) {
                releaseSellTruckMessageParams.uid = AppContext.mLoginResultModels.getId();
            }else{
                releaseSellTruckMessageParams.uid = "0";
            }

            String tripartiteId = getTripartiteId();
            if (TextUtils.isEmpty(tripartiteId)) {
                tripartiteId = tripartiteId.substring(0, tripartiteId.length() - 1);
            }
            releaseSellTruckMessageParams.Tripartite = tripartiteId;
            releaseSellTruckMessageParams.ValidCodes = onekeyEditVerify.getText().toString().trim();//服务端验证码

            ShowDialogTool.showLoadingDialog(this);
            loginService.toRequestNet(releaseSellTruckMessageParams, ReleaseSellTruckMessageResult.class, RELEASE_SELL_TRUCK_MESSAGE);
        }


    }

    private boolean checkoutParams() {
        if (TextUtils.isEmpty(editPhone.getText().toString().trim())) {
            ShowDialogTool.showCenterToast(this, "请输入手机号");
            return false;
        }
        if (TextUtils.isEmpty(onekeyEditVerify.getText().toString().trim())) {
            ShowDialogTool.showCenterToast(this, "请输入验证码");
            return false;
        }
        if (TextUtils.isEmpty(styleid)) {
            ShowDialogTool.showCenterToast(this, "请选择车型车系");
            return false;
        }
        if (TextUtils.isEmpty(GlobalData.getInstance().getCarManagerId())) {
            ShowDialogTool.showCenterToast(this, "车管家不能为空");
            return false;
        }
        if (TextUtils.isEmpty(regdate)) {
            ShowDialogTool.showCenterToast(this, "请选择上牌时间");
            return false;
        }
        if (TextUtils.isEmpty(mileage)) {
            ShowDialogTool.showCenterToast(this, "请输入行驶里程");
            return false;
        }
        if (TextUtils.isEmpty(cityid)) {
            ShowDialogTool.showCenterToast(this, "请选择城市");
            return false;
        }
        if (TextUtils.isEmpty(cityname)) {
            ShowDialogTool.showCenterToast(this, "请选择城市");
            return false;
        }
        if (TextUtils.isEmpty(sellprice)) {
            ShowDialogTool.showCenterToast(this, "请输入预期售价");
            return false;
        }
        if (TextUtils.isEmpty(getTripartiteId())) {
            ShowDialogTool.showCenterToast(this, "请选择平台");
            return false;
        }
        return true;
    }


    //获取验证码
    private void acquireGetCode() {
        // TODO 获取验证码
        mAutoPhoneNum = null;
        mAutoCodeFromNet = null;
        String phone = editPhone.getText().toString();
        if (TextUtils.isEmpty(phone)) {
            ShowDialogTool.showCenterToast(SellCarOneKeyActivity.this, "手机号码不能为空!");
            return;
        }
        if (phone.length() != 11) {
            ShowDialogTool.showCenterToast(SellCarOneKeyActivity.this, "手机号码不正确!");
            return;
        }

        if (!Pattern.matches("^[1][34578][0-9]{1}[0-9]{8}$", phone)) {
            ShowDialogTool.showCenterToast(SellCarOneKeyActivity.this, "手机格式不正确!");
            return;
        }

        toCountDown(textVerify);
        mAutoPhoneNum = phone;
        GetAutoCodeParamsModels params = new GetAutoCodeParamsModels();
        params.mMobile = phone;
        new LoginService(SellCarOneKeyActivity.this, SellCarOneKeyActivity.this).toRequestNet(params,
                GetAutoCodeResultModels.class, REQUEST_VERIFY);
    }


    private void initWindowShow() {

        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay(); // 为获取屏幕宽、高
        WindowManager.LayoutParams p = getWindow().getAttributes(); // 获取对话框当前的参数值
        Point size = new Point();
        d.getSize(size);
        int win_width = size.x;
        int win_height = size.y;
        p.height = (int) (win_height * 1); // 高度设置为屏幕的0.6
        p.width = (int) (win_width * 1); // 宽度设置为屏幕的0.95
        p.alpha = 1.0f;// 设置透明度
        p.dimAmount = 0.8f; // 设置背景透明度，1完成不透明。
        this.getWindow().setAttributes(p);
    }

    @Override
    public void onRequestSuccess(Message msg) {
        ShowDialogTool.dismissLoadingDialog();
        switch (msg.what) {
            case REQUEST_PLATFORM_DATA:
                oneKeyReleasePlatformResult = (OneKeyReleasePlatformResult) msg.obj;
                if (oneKeyReleasePlatformResult != null) {
                    if (oneKeyReleasePlatformResult.getStatus() == 100) {
                        oneKeyReleasePlatformAdapter = new OneKeyReleasePlatformAdapter(oneKeyReleasePlatformResult.getSendCarPlatTotalList(), this);
                        listView.setAdapter(oneKeyReleasePlatformAdapter);
                    } else {
                        ShowDialogTool.showCenterToast(this, oneKeyReleasePlatformResult.getMsg());
                    }
                } else {
                    ShowDialogTool.showCenterToast(this, "获取平台失败");
                }
                break;
            case REQUEST_VERIFY:
                mAutoCodeFromNet = null;
                GetAutoCodeResultModels autoCode = (GetAutoCodeResultModels) msg.obj;
                if (autoCode.getStatus() == 100) {
                    mAutoCodeFromNet = autoCode.getMobileCookie();
//				ShowDialogTool.showCenterToast(LoginActivity.this, "获取验证码成功!");
                } else {
                    if (mAsync != null && mAsync.getStatus() != AsyncTask.Status.FINISHED) {
                        mAsync.cancel(true);
                    }
                    mAutoPhoneNum = null;
                    textVerify.setText("获取验证码");
                    textVerify.setClickable(true);
                    ShowDialogTool.showCenterToast(SellCarOneKeyActivity.this, "获取验证码失败!");
                }

                break;
            case RELEASE_SELL_TRUCK_MESSAGE:
                ReleaseSellTruckMessageResult releaseSellTruckMessageResult = (ReleaseSellTruckMessageResult) msg.obj;
                if (releaseSellTruckMessageResult.getStatus() == 100) {
                    GlobalData.getInstance().setCarManagerId(releaseSellTruckMessageResult.getCarButlerId()+"");
                    ShowDialogTool.showCenterToast(SellCarOneKeyActivity.this, "车辆发布成功");
                    EventBus.getDefault().post(new RefreshMyCarEvent());//提示刷新车管家
                    finish();
                } else {
                    ShowDialogTool.showCenterToast(SellCarOneKeyActivity.this, releaseSellTruckMessageResult.getMsg());
                }

                break;

            default:

                break;
        }

    }

    @Override
    public void onRequestFault(Message msg) {
        ShowDialogTool.dismissLoadingDialog();
        ShowDialogTool.showCenterToast(this, msg.toString());
        switch (msg.what) {
            case REQUEST_VERIFY:
                mAutoPhoneNum = null;
                mAutoCodeFromNet = null;
                break;
            default:

                break;
        }

    }

    private static AsyncTask<String, String, String> mAsync;

    private void toCountDown(final TextView tv) {
        if (mAsync != null && mAsync.getStatus() != AsyncTask.Status.FINISHED) {
            mAsync.cancel(true);
            mAsync = null;
        }
        mAsync = new AsyncTask<String, String, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                tv.setClickable(false);
            }

            @Override
            protected String doInBackground(String... params) {
                for (int i = 60; i >= 0; i--) {
                    if (isCancelled()) break;
                    publishProgress(i + "秒");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return "获取验证码";
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                tv.setText("获取验证码");
                tv.setClickable(true);
            }

            @Override
            protected void onProgressUpdate(String... values) {
                super.onProgressUpdate(values);
                if (TextUtils.isEmpty(values[0])) {
                    return;
                }
                tv.setText(values[0]);
                tv.setClickable(false);
            }
        };
        mAsync.execute("do");
    }

    public String getTripartiteId() {
        String TripartiteId = "";
//        if(oneKeyReleasePlatformAdapter!=null){
//            for(int i=0;i<oneKeyReleasePlatformAdapter.imageViews.size();i++){
//                if(oneKeyReleasePlatformAdapter.imageViews.get(i).isSelect){
//                    TripartiteId = TripartiteId+oneKeyReleasePlatformAdapter.imageViews.get(i).getTag()+",";
//                }
//
//            }
//
//        }

        if (oneKeyReleasePlatformAdapter != null) {
            for (Integer key : oneKeyReleasePlatformAdapter.selectimages.keySet()) {
                for (int i = 0; i < oneKeyReleasePlatformAdapter.selectimages.get(key).size(); i++) {
                    if(oneKeyReleasePlatformAdapter.selectimages.get(key).get(i).isSelect){
                        TripartiteId = TripartiteId + oneKeyReleasePlatformResult.getSendCarPlatTotalList().get(oneKeyReleasePlatformAdapter.selectimages.get(key).get(i)
                                .getPosition()).getSendCarPlatList().get(oneKeyReleasePlatformAdapter.selectimages.get(key).get(i)
                                .getItemPosition()).getID()+",";
                    }

                }
            }
        }

        return TripartiteId;

    }
}
