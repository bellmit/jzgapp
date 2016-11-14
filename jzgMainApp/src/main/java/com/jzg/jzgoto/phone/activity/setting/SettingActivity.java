package com.jzg.jzgoto.phone.activity.setting;

import java.io.File;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.TextView;
import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.activity.common.ConstantForAct;
import com.jzg.jzgoto.phone.activity.shared.BaseActivity;
import com.jzg.jzgoto.phone.event.LogoutEvent;
import com.jzg.jzgoto.phone.global.AppContext;
import com.jzg.jzgoto.phone.global.GlobalData;
import com.jzg.jzgoto.phone.global.HttpServiceHelper;
import com.jzg.jzgoto.phone.models.business.buy.ShareModel;
import com.jzg.jzgoto.phone.models.business.settings.GetPushStatusParamsModels;
import com.jzg.jzgoto.phone.models.business.settings.GetPushStatusResultModels;
import com.jzg.jzgoto.phone.models.business.settings.GradeJzgParamsModels;
import com.jzg.jzgoto.phone.models.business.settings.GradeJzgResultModels;
import com.jzg.jzgoto.phone.services.business.login.LoginService;
import com.jzg.jzgoto.phone.tools.ClickControlTool;
import com.jzg.jzgoto.phone.tools.DataCleanManager;
import com.jzg.jzgoto.phone.tools.DialogManager;
import com.jzg.jzgoto.phone.tools.ShareTools;
import com.jzg.jzgoto.phone.tools.ShowDialogTool;
import com.jzg.jzgoto.phone.view.ViewUtility;
import com.umeng.socialize.media.UMImage;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;


public class SettingActivity extends BaseActivity implements OnClickListener{

    private ShareTools mShareTools = null;
    private View mGradeView;

    @Bind(R.id.view_exit_item_container)
    View mBtnExit;
    @Bind(R.id.view_msg_push_imageview)
    ImageView mMsgPushImageView;
    @Bind(R.id.view_clearcache_textview)
    TextView mCacheSizeTextView;

    private boolean mNeedClearCache = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_layout);
        ButterKnife.bind(this);
    }

    @Override
    @OnClick({R.id.view_feedback_item_container, R.id.view_grade_item_container, R.id.view_share_item_container, R.id.view_clearcache_container,
            R.id.view_exit_item_container, R.id.view_msg_push_imageview, R.id.view_about_item_container, R.id.view_msg_push_container})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.view_feedback_item_container:
                if (AppContext.isLogin()) {
                    ViewUtility.navigateToFeedbackActivity(this);
                } else {
                    DialogManager.toLoginDialog(this);
                }
                break;
            case R.id.view_msg_push_imageview:
            case R.id.view_msg_push_container:
                if (AppContext.isLogin()) {
                    handlePushSwitchClicked();
                } else {
                    DialogManager.toLoginDialog(this);
                }
                break;
            case R.id.view_grade_item_container:
                // 给我们打分
                toShowPingFen();
                break;
            case R.id.view_share_item_container:
                // 分享给好友
                ShareModel shareModel = new ShareModel();
                shareModel.setShareTitle("您身边的二手车专家，最好用的评估工具");
                shareModel.setShareText("买卖，置换二手车先来估个价");
                shareModel.setShareUrl(HttpServiceHelper.BASE_SHARE_URL + "/appdown.html");
                UMImage image = new UMImage(SettingActivity.this,
                        BitmapFactory.decodeResource(getResources(), R.drawable.jzg_icon));
                shareModel.setUMImage(image);
                if (mShareTools == null) {
                    mShareTools = new ShareTools(SettingActivity.this);
                }
                mShareTools.openShareBroad(shareModel);
                break;
            case R.id.view_clearcache_container:

                if (!mNeedClearCache){
                    ShowDialogTool.showCenterToast(this, "已无缓存可清理，无需清理");
                    return;
                }
                toShowLoadingDialog();
                // 清理缓存
                File file = this.getCacheDir();
                toRunBack(file, 0);
                break;
            case R.id.view_about_item_container:
                ViewUtility.navigateToAboutUsActivity(this);
                break;
            case R.id.view_exit_item_container:
                String mobile = ConstantForAct.getUserMobile(SettingActivity.this);
                if (!TextUtils.isEmpty(mobile) && AppContext.isLogin()) {
                    ConstantForAct.saveUserLoginState(SettingActivity.this, mobile, false);
                    ConstantForAct.saveUserId(this, "0");
                    ConstantForAct.saveCarManagerId(this, "0");
                    GlobalData.getInstance().setUserId("0");
                    GlobalData.getInstance().setCarManagerId("0");
                    EventBus.getDefault().post(LogoutEvent.build(true));
                }
                AppContext.mLoginResultModels = null;
                mBtnExit.setVisibility(View.GONE);
                setResult(201);
                SettingActivity.this.finish();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mShareTools != null) {
            mShareTools.closeShareBroad();
        }
        if (AppContext.isLogin()) {
            mBtnExit.setVisibility(View.VISIBLE);
            if ("1".equals(AppContext.mLoginResultModels.getPushStatus())) {
                mMsgPushImageView.setSelected(true);
            } else {
                mMsgPushImageView.setSelected(false);
            }
        } else {
            mBtnExit.setVisibility(View.GONE);
            mMsgPushImageView.setSelected(true);
        }
        File file = this.getCacheDir();
        toRunBack(file, 1);
    }

    private void handlePushSwitchClicked(){
        if (!AppContext.isLogin()){
            ShowDialogTool.showCenterToast(this, "此功能需要登录后使用");
            return;
        }
        if (!ClickControlTool.isCanToClick()) {
            return;
        }
        if (mMsgPushImageView.isSelected()) {
            toSetTuisongStatus("0");
        } else {
            toSetTuisongStatus("1");
        }
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0:
                    dismissLoadingDialog();
                    ShowDialogTool.showTitleAndMsgViewDialog(SettingActivity.this, "提示", "确定清理缓存?",
                            new ShowDialogTool.DialogCallBack() {
                                @Override
                                public void cancelBack(View v) {
                                }

                                @Override
                                public void applyBack(View v) {
                                    DataCleanManager.cleanInternalCache(SettingActivity.this);
                                    mCacheSizeTextView.setText("");
                                    mNeedClearCache = false;
                                }
                            });
                    break;
                case 1:
                    String size = (String) msg.obj;
                    if (TextUtils.isEmpty(size)) {
                        mCacheSizeTextView.setText("");
                        mNeedClearCache = false;
                    } else {
                        mNeedClearCache = true;
                        mCacheSizeTextView.setText("(" + size + ")");
                    }
                    break;
            }
        }

        ;
    };

    private void toShowPingFen() {
        if (mGradeView == null) {
            mGradeView = LayoutInflater.from(SettingActivity.this).inflate(R.layout.dialog_setting_grade_layout, null);
            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!ClickControlTool.isCanToClick()) {
                        return;
                    }
                    switch (v.getId()) {
                        case R.id.btn_dialog_system_set_pingfen_one:
                            toRequestGrade("1");
                            break;
                        case R.id.btn_dialog_system_set_pingfen_two:
                            toRequestGrade("2");
                            break;
                        case R.id.btn_dialog_system_set_pingfen_three:
                            // TODO
                            ShowDialogTool.dismissSelfViewDialog();
                            break;
                    }
                }
            };
            mGradeView.findViewById(R.id.btn_dialog_system_set_pingfen_one).setOnClickListener(listener);
            mGradeView.findViewById(R.id.btn_dialog_system_set_pingfen_two).setOnClickListener(listener);
            mGradeView.findViewById(R.id.btn_dialog_system_set_pingfen_three).setOnClickListener(listener);
        }
        if (mGradeView.getParent() != null) {
            ViewParent viewParent = mGradeView.getParent();
            if (viewParent instanceof ViewGroup) {
                ViewGroup v = (ViewGroup) viewParent;
                v.removeView(mGradeView);
            }
        }
        ShowDialogTool.showSelfViewDialog(SettingActivity.this, mGradeView, true, null);
    }

    private void toRequestGrade(String type) {
//        if (!AppContext.isLogin()) {
//            DialogManager.toLoginDialog(SettingActivity.this);
//            return;
//        }
        if (TextUtils.isEmpty(type)) return;
        toShowLoadingDialog();
        GradeJzgParamsModels params = new GradeJzgParamsModels();
        params.uid = "0";
        if (AppContext.isLogin()){
            params.uid = AppContext.mLoginResultModels.getId();
        }
        params.type = type;

        new LoginService(SettingActivity.this, SettingActivity.this).toRequestNet(params,
                GradeJzgResultModels.class, REQUEST_GRADE);
    }

    private void toSetTuisongStatus(String status) {
        if (TextUtils.isEmpty(status)) return;
        toShowLoadingDialog();
        GetPushStatusParamsModels params = new GetPushStatusParamsModels();
        params.Status = status;
        params.Id = AppContext.mLoginResultModels.getId();
        new LoginService(SettingActivity.this, SettingActivity.this).toRequestNet(params,
                GetPushStatusResultModels.class, REQUEST_TUISONG_SET);
    }

    private final int REQUEST_GRADE = 0x1000;
    private final int REQUEST_TUISONG_SET = 0x1001;

    @Override
    public void onRequestSuccess(Message msg) {
        super.onRequestSuccess(msg);
        switch (msg.what) {
            case REQUEST_GRADE:
                GradeJzgResultModels result = (GradeJzgResultModels) msg.obj;
                if (result.getStatus() == 100) {
    				ShowDialogTool.showCenterToast(SettingActivity.this, "打分成功！");
                    ShowDialogTool.dismissSelfViewDialog();
                } else {
                    ShowDialogTool.showCenterToast(SettingActivity.this, "数据返回失败，请重试。");
                }
                break;
            case REQUEST_TUISONG_SET:
                GetPushStatusResultModels push = (GetPushStatusResultModels) msg.obj;
                if (push.getStatus() == 100) {
                    if (mMsgPushImageView.isSelected()) {
                        mMsgPushImageView.setSelected(false);
                        AppContext.mLoginResultModels.setPushStatus("0");
                    } else {
                        mMsgPushImageView.setSelected(true);
                        AppContext.mLoginResultModels.setPushStatus("1");
                    }
                } else {
                    ShowDialogTool.showCenterToast(SettingActivity.this, "数据返回失败，请重试。");
                }
                break;
        }
    }

    @Override
    public void onRequestFault(Message msg) {
        super.onRequestFault(msg);
        switch (msg.what) {
            case REQUEST_TUISONG_SET:
            case REQUEST_GRADE:
                ShowDialogTool.showCenterToast(SettingActivity.this, "无法与服务器建立连接，请重试。");
                break;
        }
    }

    private double toGetLength(File file) {
        double length = 0;
        if (file.exists() && file.isFile()) {
            return file.length();
        }
        for (File f : file.listFiles()) {
            length += toGetLength(f);
        }
        return length;
    }

    private void toRunBack(File file, final int what) {
        new AsyncTask<File, String, String>() {
            @Override
            protected String doInBackground(File... params) {
                double result = toGetLength(params[0]);
                String re = "0B";
                if (result / 2014 > 1) {
                    result = result / 1024;
                    re = ShowDialogTool.getValueFromDouble(result) + "B";
                }
                if (result / 2014 > 1) {
                    result = result / 1024;
                    re = ShowDialogTool.getValueFromDouble(result) + "KB";
                }
                if (result / 2014 > 1) {
                    result = result / 1024;
                    re = ShowDialogTool.getValueFromDouble(result) + "MB";
                }
                if (result / 2014 > 1) {
                    result = result / 1024;
                    re = ShowDialogTool.getValueFromDouble(result) + "GB";
                }
                return re;
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                Message msg = mHandler.obtainMessage(what);
                msg.obj = result;
                mHandler.sendMessageDelayed(msg, 500);
            }
        }.execute(file);
    }
}
