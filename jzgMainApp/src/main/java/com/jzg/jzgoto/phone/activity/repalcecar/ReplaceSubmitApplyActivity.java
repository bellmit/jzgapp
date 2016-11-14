package com.jzg.jzgoto.phone.activity.repalcecar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.activity.common.ConstantForAct;
import com.jzg.jzgoto.phone.global.AppContext;
import com.jzg.jzgoto.phone.models.business.login.GetAutoCodeParamsModels;
import com.jzg.jzgoto.phone.models.business.login.GetAutoCodeResultModels;
import com.jzg.jzgoto.phone.models.business.login.LoginResultModels;
import com.jzg.jzgoto.phone.models.business.sell.ApplyReplaceParamsModels;
import com.jzg.jzgoto.phone.models.business.sell.ApplyReplaceResultModels;
import com.jzg.jzgoto.phone.models.business.sell.NewCarRepalceParamsModels;
import com.jzg.jzgoto.phone.models.business.sell.RequestDealersMsgParamsModels;
import com.jzg.jzgoto.phone.models.business.sell.RequestDealersMsgResultModels;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;
import com.jzg.jzgoto.phone.services.business.login.LoginService;
import com.jzg.jzgoto.phone.services.common.OnRequestFinishListener;
import com.jzg.jzgoto.phone.tools.ClickControlTool;
import com.jzg.jzgoto.phone.tools.ShowDialogTool;
import com.jzg.jzgoto.phone.view.replacecar.ReplaceAddDealerItemView;
import com.jzg.jzgoto.phone.view.replacecar.ReplaceAddDealerItemView.ClickCallBack;
import com.jzg.jzgoto.phone.view.replacecar.ReplaceAddDealerItemView.DealerItemBean;
import com.jzg.jzgoto.phone.view.replacecar.ReplaceApplyResultView;
import com.jzg.jzgoto.phone.view.replacecar.ReplaceApplyResultView.DataCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by jzg on 2016/9/22.
 * Function :
 */
public class ReplaceSubmitApplyActivity extends Activity implements OnRequestFinishListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replace_submit_apply_layout);
        initView();
        initListener();
    }
    private LoginService mLoginService;
    private LinearLayout mUserInfoLayout;
    public void initView() {
        mLoginService = new LoginService(this, this);
        initApplyCarView();
        initBottomView();
        mRequestDealersMsgParamsModels = (RequestDealersMsgParamsModels) getIntent().getSerializableExtra("request_jxs");
        mNewCarRepalceParamsModels = (NewCarRepalceParamsModels) getIntent().getSerializableExtra("my_car_msg");
    }
    private RequestDealersMsgParamsModels mRequestDealersMsgParamsModels;
    private NewCarRepalceParamsModels mNewCarRepalceParamsModels;

    public void initListener() {
        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(!ClickControlTool.isCanToClickFor200()){
                    return;
                }
                // TODO 获取验证码
                mAutoPhoneNum = null;
                mAutoCodeFromNet = null;
                String phone = mEditPhoneNum.getText().toString();
                if(TextUtils.isEmpty(phone)){
                    ShowDialogTool.showCenterToast(ReplaceSubmitApplyActivity.this, "手机号码不能为空");
                    return;
                }
                toCountDown(mTvGetAutoCode);
                mAutoPhoneNum = phone;
                ShowDialogTool.showLoadingDialog(ReplaceSubmitApplyActivity.this);
                GetAutoCodeParamsModels params = new GetAutoCodeParamsModels();
                params.mMobile = phone;
                mLoginService.toRequestNet(params,
                        GetAutoCodeResultModels.class, TO_GET_AUTO);
            }
        };
        mTvGetAutoCode.setOnClickListener(listener);
    }
    private static AsyncTask<String, String, String> mAsync ;
    private void toCountDown(final TextView tv){
        if(mAsync != null && mAsync.getStatus() != AsyncTask.Status.FINISHED){
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
                for(int i = 60;i>=0;i--){
                    if(isCancelled())break;
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
                if(TextUtils.isEmpty(values[0])){
                    return;
                }
                tv.setText(values[0]);
                tv.setClickable(false);
            }
        };
        mAsync.execute("do");
    }
    @Override
    protected void onResume() {
        super.onResume();
        toRequestJxsList();
    }

    private LinearLayout mLinearApplyCar;
    private void initApplyCarView(){
        mLinearApplyCar = (LinearLayout) findViewById(R.id.linear_apply_request_result_allcarmsg);
        if(mLinearApplyCar.getChildCount() != 0){
            mLinearApplyCar.removeAllViews();
        }
    }
    private EditText mEditName;
    private EditText mEditPhoneNum;
    private EditText mEditAutoCode;
    private TextView mTvGetAutoCode;
    private void initBottomView(){
        mUserInfoLayout = (LinearLayout) findViewById(R.id.linear_apply_request_result_info);
        mEditName = (EditText) findViewById(R.id.edit_apply_request_result_name);
        mEditPhoneNum = (EditText) findViewById(R.id.edit_apply_request_result_phonenum);
        mEditAutoCode = (EditText) findViewById(R.id.edit_apply_request_result_autocode);
        mTvGetAutoCode = (TextView) findViewById(R.id.tv_apply_request_result_getautocode);

        mEditName.setText("");
        mEditPhoneNum.setText("");
        mEditAutoCode.setText("");
        if(AppContext.isLogin()){
            mEditName.setText(AppContext.mLoginResultModels.getTrueName());
            mEditPhoneNum.setText(AppContext.mLoginResultModels.getMobile());
        }
    }
    private final ApplyReplaceParamsModels mApplyReplaceParamsModels = new ApplyReplaceParamsModels();
    public void onSubmitAll(View view){
        if(!ClickControlTool.isCanToClickFor200()){
            return;
        }
        if(!checkDealers()){
            ShowDialogTool.showCenterToast(ReplaceSubmitApplyActivity.this, "每款车至少选择一个经销商");
            return;
        }

        String name = mEditName.getText().toString();
        if(TextUtils.isEmpty(name)){
            ShowDialogTool.showCenterToast(ReplaceSubmitApplyActivity.this, "姓名不能为空");
            return;
        }
        mApplyReplaceParamsModels.setName(name);
        String phoneNum = mEditPhoneNum.getText().toString();
        if(TextUtils.isEmpty(phoneNum)){
            ShowDialogTool.showCenterToast(ReplaceSubmitApplyActivity.this, "手机号不能为空");
            return;
        }
        mApplyReplaceParamsModels.setMobile(phoneNum);
        String autoCode = mEditAutoCode.getText().toString();
        if(TextUtils.isEmpty(autoCode)){
            ShowDialogTool.showCenterToast(ReplaceSubmitApplyActivity.this, "验证码不能为空");
            return;
        }
        mApplyReplaceParamsModels.setValidCodes(autoCode);
        mApplyReplaceParamsModels.setMyproid(mNewCarRepalceParamsModels.getMyProvinceID());
        if(mApplyReplaceParamsModels.getMyproid()==null){
            mApplyReplaceParamsModels.setMyproid("");
        }
        mApplyReplaceParamsModels.setMyproname(mNewCarRepalceParamsModels.getMyProvinceName());

        mApplyReplaceParamsModels.setMyctid(mNewCarRepalceParamsModels.getMyCItyID());
        if(mApplyReplaceParamsModels.getMyctid()==null){
            mApplyReplaceParamsModels.setMyctid("");
        }
        mApplyReplaceParamsModels.setMyctname(mNewCarRepalceParamsModels.getMyCityName());
        mApplyReplaceParamsModels.setOldmkid(mNewCarRepalceParamsModels.getMyMakeID());

        mApplyReplaceParamsModels.setOldmlid(mNewCarRepalceParamsModels.getMyModelID());
        mApplyReplaceParamsModels.setOldstid(mNewCarRepalceParamsModels.getMyStyleID());
        mApplyReplaceParamsModels.setHdMilage(mNewCarRepalceParamsModels.getMyMileage());

        mApplyReplaceParamsModels.setNewctname(mNewCarRepalceParamsModels.getNewCityName());
        mApplyReplaceParamsModels.setNewproname(mNewCarRepalceParamsModels.getNewProvinceName());

        String date = mNewCarRepalceParamsModels.getMyFirstRegistrationTime();
        if(TextUtils.isEmpty(date)){
            ShowDialogTool.showCenterToast(ReplaceSubmitApplyActivity.this, "上牌日期不能为空");
            return;
        }
        String[] strs = date.split("-");
        if(strs.length != 2){
            ShowDialogTool.showCenterToast(ReplaceSubmitApplyActivity.this, "上牌日期传递错误");
            return;
        }
        mApplyReplaceParamsModels.setHdYear(strs[0]);
        mApplyReplaceParamsModels.setHdMonth(strs[1]);

        mApplyReplaceParamsModels.setSmes(getDealers());

        if(AppContext.isLogin()){
            mApplyReplaceParamsModels.setUserId(AppContext.mLoginResultModels.getId());
        }else{
            mApplyReplaceParamsModels.setUserId("0");
        }
        ShowDialogTool.showLoadingDialog(ReplaceSubmitApplyActivity.this);
        mLoginService.toRequestNet(
                mApplyReplaceParamsModels, ApplyReplaceResultModels.class, REQUEST_APPLY_REPLACE);
    }

    private void toApplyReplace(){
        ShowDialogTool.showLoadingDialog(ReplaceSubmitApplyActivity.this);
        mLoginService.toRequestNet(
                mApplyReplaceParamsModels, ApplyReplaceResultModels.class, REQUEST_APPLY_REPLACE);
    }

    private void initDealersData(){
        if(mRequestDealersMsgResultModels == null)return;
        if(mLinearApplyCar.getChildCount() != 0){
            mLinearApplyCar.removeAllViews();
        }

        List<RequestDealersMsgResultModels.CarStyleList> list = mRequestDealersMsgResultModels.getNewStyleList();
        mUserInfoLayout.setVisibility(View.VISIBLE);
        for(int i = 0;i < list.size();i++){
            ReplaceApplyResultView item = new ReplaceApplyResultView(this);
            item.setClickCallback(new DataCallback() {
                @Override
                public void callback(String styleId, DealerItemBean dealer, boolean bl) {
                    if(!mDealerIds.containsKey(styleId)){
                        mDealerIds.put(styleId, new ArrayList<String>());
                    }
                    mDealers.put(dealer.getDealerId(), dealer);
                    if(bl){
                        if(!mDealerIds.get(styleId).contains(dealer.getDealerId())){
                            mDealerIds.get(styleId).add(dealer.getDealerId());
                        }
                    } else {
                        if(mDealerIds.get(styleId).contains(dealer.getDealerId())){
                            mDealerIds.get(styleId).remove(dealer.getDealerId());
                        }
                    }
                }
            });
            if(list.get(i).getNew4SList()!=null){
                if(list.get(i).getNew4SList().size()>0){
                    mDealerIds.put(list.get(i).getStyleId(), new ArrayList<String>());
                }
            }
            item.setCarStyleShow(list.get(i));
            mLinearApplyCar.addView(item);
        }
    }
    private final Map<String,DealerItemBean> mDealers = new HashMap<String,DealerItemBean>();
    private final Map<String,List<String>> mDealerIds = new HashMap<String,List<String>>();
    private boolean checkDealers(){
        if(mDealerIds.size()==0){
            return false;
        }
        Iterator<String> iterator = mDealerIds.keySet().iterator();
        while(iterator.hasNext()){
            String styleId = iterator.next();
            if(mDealerIds.get(styleId).size() == 0){
                return false;
            }
        }
        return true;
    }
    private String getDealers(){
        final StringBuilder sb = new StringBuilder();
        Iterator<String> iterator = mDealerIds.keySet().iterator();
        while(iterator.hasNext()){
            String styleId = iterator.next();
            if(sb.length() != 0){
                sb.append("$");
            }
            sb.append(styleId);
            final List<String> list = mDealerIds.get(styleId);
            if(list.size() != 0){
                sb.append("|");
                for(int i = 0;i < list.size();i++){
                    if(i != 0){
                        sb.append("#");
                    }
                    String key = list.get(i);
                    DealerItemBean bean = mDealers.get(key);
                    sb.append(bean.getDealerId());
                    sb.append(",");

                    sb.append(bean.getName());
                    sb.append(",");
                    sb.append(bean.getAddress());
                    sb.append(",");
                    sb.append(bean.getPhoneNum());
                }
            }
        }
        return sb.toString();
    }
    private void toRequestJxsList(){
        if(mRequestDealersMsgParamsModels == null){
            return;
        }
        ShowDialogTool.showLoadingDialog(ReplaceSubmitApplyActivity.this);
        mLoginService.toRequestNet(
                mRequestDealersMsgParamsModels, RequestDealersMsgResultModels.class, REQUEST_JXS_LIST);
    }
    private final int REQUEST_JXS_LIST = 0x5001;
    private final int REQUEST_APPLY_REPLACE = 0x5002;
    private String mAutoPhoneNum = null;
    private String mAutoCodeFromNet = null;
    private final int TO_LOGIN = 0x1001;
    private final int TO_GET_AUTO = 0x1002;
    private RequestDealersMsgResultModels mRequestDealersMsgResultModels;
    private ApplyReplaceResultModels mApplyReplaceResultModels;
    @SuppressLint("NewApi")
    @Override
    public void onRequestSuccess(Message msg) {
        ShowDialogTool.dismissLoadingDialog();
        switch(msg.what){
            case REQUEST_JXS_LIST:
                RequestDealersMsgResultModels dealersList = (RequestDealersMsgResultModels) msg.obj;
                if(dealersList.getStatus() == 100){
                    mRequestDealersMsgResultModels = dealersList;
                    initDealersData();
//				Log.i("gf", "返回成功!");
                } else {
                    mRequestDealersMsgResultModels = null;
//				Log.i("gf", "返回失败!");
                }

                break;
            case REQUEST_APPLY_REPLACE:
                ApplyReplaceResultModels replaceResult = (ApplyReplaceResultModels) msg.obj;
                if(replaceResult.getStatus() == 100){
                    mApplyReplaceResultModels = replaceResult;
                    ShowDialogTool.showViewDialog(ReplaceSubmitApplyActivity.this, null, "客服会在24小时之内与您联系，沟通相关事宜请耐心等待。",
                            new ShowDialogTool.DialogCallBack() {
                                @Override
                                public void cancelBack(View v) {
                                }
                                @Override
                                public void applyBack(View v) {
                                }
                            }, new DialogInterface.OnDismissListener() {
                                @Override
                                public void onDismiss(DialogInterface dialog) {
                                    Intent intent = new Intent();
                                    intent.putExtra("result", true);
                                    setResult(100,intent);
                                    ReplaceSubmitApplyActivity.this.finish();
                                }
                            });

                } else {
//				Log.i("gf", "返回失败!");
                    ShowDialogTool.showCenterToast(ReplaceSubmitApplyActivity.this,replaceResult.getMsg() );
                }
                break;
            case TO_GET_AUTO:
                mAutoCodeFromNet = null;
                GetAutoCodeResultModels autoCode = (GetAutoCodeResultModels) msg.obj;
                if(autoCode.getStatus() == 100){
                    mAutoCodeFromNet = autoCode.getMobileCookie();
                } else {
                    if(mAsync != null && mAsync.getStatus() != AsyncTask.Status.FINISHED){
                        mAsync.cancel(true);
                    }
                    mAutoPhoneNum = null;
                    mTvGetAutoCode.setText("获取验证码");
                    mTvGetAutoCode.setClickable(true);
                    ShowDialogTool.showCenterToast(ReplaceSubmitApplyActivity.this, autoCode.getMsg());
                }
                break;
            case TO_LOGIN:
                LoginResultModels loginRe = (LoginResultModels) msg.obj;
                if(loginRe.getStatus() == 100){
                    ConstantForAct.saveUserMobile(ReplaceSubmitApplyActivity.this, mAutoPhoneNum);
                    ConstantForAct.saveUserLoginState(ReplaceSubmitApplyActivity.this, mAutoPhoneNum,true);
                    ConstantForAct.saveUserMsg(ReplaceSubmitApplyActivity.this, getStringFromObj(loginRe));
                    AppContext.mLoginResultModels = loginRe.getGetPersonalInfo();
                    mApplyReplaceParamsModels.setUserId(AppContext.mLoginResultModels.getId());
                    ShowDialogTool.showLoadingDialog(ReplaceSubmitApplyActivity.this);
                    mLoginService.toRequestNet(
                            mApplyReplaceParamsModels, ApplyReplaceResultModels.class, REQUEST_APPLY_REPLACE);
                } else {
                    ShowDialogTool.showCenterToast(ReplaceSubmitApplyActivity.this, "注册用户失败");
                }
                break;
        }
    }
    @Override
    public void onRequestFault(Message msg) {
        ShowDialogTool.dismissLoadingDialog();
        switch(msg.what){
            case TO_GET_AUTO:
                mAutoPhoneNum = null;
                mAutoCodeFromNet = null;
            case REQUEST_APPLY_REPLACE:
            case REQUEST_JXS_LIST:
            case TO_LOGIN:
                ShowDialogTool.showCenterToast(ReplaceSubmitApplyActivity.this, getResources().getString(R.string.error_net));
                break;
        }
    }
    private <T extends BaseResultModels> String getStringFromObj(T obj){
        if(obj == null)
            return null;
        Gson g = new Gson();
        return g.toJson(obj);
    }
}
