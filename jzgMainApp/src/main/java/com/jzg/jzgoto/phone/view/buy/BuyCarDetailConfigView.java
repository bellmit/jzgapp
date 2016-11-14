package com.jzg.jzgoto.phone.view.buy;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.maps.model.Text;
import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.adapter.buy.BuyCarDetailConfigAdapter;
import com.jzg.jzgoto.phone.global.HttpServiceHelper;
import com.jzg.jzgoto.phone.models.business.buy.BuyCarDetailResult;
import com.jzg.jzgoto.phone.tools.ClickControlTool;
import com.jzg.jzgoto.phone.tools.CountClickTool;
import com.jzg.jzgoto.phone.view.ViewUtility;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WY on 2016/9/18.
 * Function :买车详情 配置界面
 */
public class BuyCarDetailConfigView extends LinearLayout{
    private Context mContext;
    public BuyCarDetailConfigView(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public BuyCarDetailConfigView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    public BuyCarDetailConfigView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
        initView();
    }

    private GridView mGridView;
    private TextView mConfigInfo;

    private void initView(){
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_buycar_detail_config_layout,null);
        mGridView = (GridView) view.findViewById(R.id.buycar_detail_config_GridView);
        mConfigInfo = (TextView) view.findViewById(R.id.buycar_detail_config_info);
        mConfigInfo.setOnClickListener(mClickListener);
        WindowManager windowManager = ((Activity)getContext()).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        LayoutParams params = new LayoutParams(display.getWidth(), LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);
        mGridView.setLayoutParams(params);
        this.addView(view);
    }
    private String configUrl = "";
    public void showConfigData(BuyCarDetailResult result){
        configUrl = result.getStylePeizhiUrl();
        List<ConfigItemModel> list = new ArrayList<>();
        if(TextUtils.isEmpty(result.getRegDateSpan())){
            list.add(new ConfigItemModel(result.getStrRegDate()+"上牌","--"));
        }else{
            list.add(new ConfigItemModel(result.getStrRegDate()+"上牌",result.getRegDateSpan()));
        }
        if(TextUtils.isEmpty(result.getMileage())){
            list.add(new ConfigItemModel("行驶里程","--"));
        }else{
            list.add(new ConfigItemModel("行驶里程",result.getMileage()+"万"));
        }
        if(TextUtils.isEmpty(result.getCityName())){
            list.add(new ConfigItemModel("上牌地区","--"));
        }else{
            list.add(new ConfigItemModel("上牌地区",result.getCityName()));
        }
        if(TextUtils.isEmpty(result.getPaiFangBZ())){
            list.add(new ConfigItemModel("排放标准","--"));
        }else{
            list.add(new ConfigItemModel("排放标准",result.getPaiFangBZ()));
        }
        if(TextUtils.isEmpty(result.getBSQ())){
            list.add(new ConfigItemModel("变速箱","--"));
        }else{
            list.add(new ConfigItemModel("变速箱",result.getBSQ()));
        }
        if(TextUtils.isEmpty(result.getTransFerCount())){
            list.add(new ConfigItemModel("过户次数","--"));
        }else{
            list.add(new ConfigItemModel("过户次数",result.getTransFerCount()));
        }
        mGridView.setAdapter(new BuyCarDetailConfigAdapter(mContext,list));
    }
    private OnClickListener mClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!ClickControlTool.isCanToClick())return;
            switch (v.getId()){
                case R.id.buycar_detail_config_info:
                    //跳转到配置详情
                    CountClickTool.onEvent(getContext(), "V5_buyCar_config");
                    if(!TextUtils.isEmpty(configUrl))
                        ViewUtility.navigateToWebView(getContext(),"参数配置", HttpServiceHelper.BASE_SHARE_URL+configUrl);
                    break;
            }
        }
    };

    public class ConfigItemModel{
        private String title;
        private String content;

        public ConfigItemModel(String title, String content) {
            this.title = title;
            this.content = content;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
