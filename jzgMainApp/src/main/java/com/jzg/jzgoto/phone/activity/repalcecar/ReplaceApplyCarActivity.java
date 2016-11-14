package com.jzg.jzgoto.phone.activity.repalcecar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.global.AppContext;
import com.jzg.jzgoto.phone.models.business.sell.NewCarRepalceParamsModels;
import com.jzg.jzgoto.phone.models.business.sell.NewCarReplaceResultModels;
import com.jzg.jzgoto.phone.models.business.sell.NewCarReplaceResultModels.NewCarBean;
import com.jzg.jzgoto.phone.models.business.sell.RequestDealersMsgParamsModels;
import com.jzg.jzgoto.phone.services.business.login.LoginService;
import com.jzg.jzgoto.phone.services.common.OnRequestFinishListener;
import com.jzg.jzgoto.phone.tools.ShowDialogTool;
import com.jzg.jzgoto.phone.tools.view.swipemenulistview.SwipeMenu;
import com.jzg.jzgoto.phone.tools.view.swipemenulistview.SwipeMenuCreator;
import com.jzg.jzgoto.phone.tools.view.swipemenulistview.SwipeMenuItem;
import com.jzg.jzgoto.phone.tools.view.swipemenulistview.SwipeMenuListView;
import com.jzg.jzgoto.phone.view.replacecar.ReplaceAddNewCarItemView.NewCarItemBean;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by jzg on 2016/9/22.
 * Function :
 */
public class ReplaceApplyCarActivity extends Activity implements OnRequestFinishListener{
    private LoginService mLoginService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replace_applycar_layout);
        initView();
    }
    public void initView() {

        mLoginService = new LoginService(this, this);
        mSwipeMenuListView = (SwipeMenuListView) findViewById(R.id.swipelist_apply_request);

        mSwipeMenuListView.setPullLoadEnable(false);
        mSwipeMenuListView.setPullRefreshEnable(false);
        mSwipeMenuListView.getmFooterView().hide();

        mSwipeMenuListView.setAdapter(mMoreSytleAdapter);
        mNewCarRepalceParamsModels = (NewCarRepalceParamsModels) getIntent().getSerializableExtra("apply_replace_car");

        mSwipeMenuListView.setMenuCreator(new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                if(menu.getViewType()!=1)return;
                SwipeMenuItem item = new SwipeMenuItem(ReplaceApplyCarActivity.this);
                item.setBackground(new ColorDrawable(Color.rgb(0xF9,

                        0x3F, 0x25)));
                item.setWidth(dp2px(90));
                item.setTitle("删除");
                item.setTitleSize(18);
                item.setTitleColor(Color.WHITE);
                menu.addMenuItem(item);
            }
        });
        mSwipeMenuListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public void onMenuItemClick(int position, SwipeMenu menu, int index) {
                NewCarReplaceResultModels.NewCarBean bean = (NewCarReplaceResultModels.NewCarBean) mMoreSytleAdapter.getItem(position);
                String styleId = bean.getNewStyleId();
                if(TextUtils.isEmpty(styleId))return;
                // TODO
                String newStyleIds = mNewCarRepalceParamsModels.getNewStyleID();
                if(newStyleIds.contains(styleId)){
                    newStyleIds = newStyleIds.replace(styleId, "");
                    if(newStyleIds.contains(",,")){
                        newStyleIds = newStyleIds.replace(",,", ",");
                    }
                    if(newStyleIds.endsWith(",")){
                        newStyleIds = newStyleIds.substring(0, newStyleIds.lastIndexOf(","));
                    }
                    if(newStyleIds.startsWith(",")){
                        newStyleIds = newStyleIds.substring(1, newStyleIds.length());
                    }
                }
                if(newStyleIds.equals(",")){
                    newStyleIds = "";
                }
                mNewCarRepalceParamsModels.setNewStyleID(newStyleIds);
                mMoreSytleAdapter.removeItem(position);

            }
        });
    }
    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }
    private SwipeMenuListView mSwipeMenuListView;
    private NewCarRepalceParamsModels mNewCarRepalceParamsModels;

    @Override
    protected void onResume() {
        super.onResume();
        if(mNewCarReplaceResultModels == null)
            toRequestReplaceMsg();

    }
    private void toRequestReplaceMsg(){
        if(mNewCarRepalceParamsModels != null){
            ShowDialogTool.showLoadingDialog(ReplaceApplyCarActivity.this);
            new LoginService(ReplaceApplyCarActivity.this, ReplaceApplyCarActivity.this).toRequestNet(
                    mNewCarRepalceParamsModels, NewCarReplaceResultModels.class, REQUEST_REPLACE_CAR_MSG);
        }
    }
    private RequestDealersMsgParamsModels mRequestDealersMsgParamsModels = new RequestDealersMsgParamsModels();
    public void toRepalceNext(View view){
        if(TextUtils.isEmpty(mNewCarRepalceParamsModels.getNewStyleID())){
            ShowDialogTool.showCenterToast(ReplaceApplyCarActivity.this, "无置换车辆");
            return;
        }
        Intent intent = new Intent(ReplaceApplyCarActivity.this,ReplaceSubmitApplyActivity.class);
        mRequestDealersMsgParamsModels.setCityId(mNewCarRepalceParamsModels.getNewCityName());
        mRequestDealersMsgParamsModels.setStyleId(mNewCarRepalceParamsModels.getNewStyleID());
        intent.putExtra("request_jxs", mRequestDealersMsgParamsModels);
        intent.putExtra("my_car_msg", mNewCarRepalceParamsModels);
        startActivityForResult(intent, 99);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case 99:
                if(resultCode == 100){
                    if(data != null){
                        if(data.getBooleanExtra("result", false)){
                            Intent intent = new Intent();
                            intent.putExtra("result", true);
                            setResult(100,intent);
                            ReplaceApplyCarActivity.this.finish();
                        }
                    }
                }
                break;
        }
    }
    private final int REQUEST_REPLACE_CAR_MSG = 0x3001;
    private NewCarReplaceResultModels mNewCarReplaceResultModels;
    @Override
    public void onRequestSuccess(Message msg) {
        ShowDialogTool.dismissLoadingDialog();
        switch(msg.what){
            case REQUEST_REPLACE_CAR_MSG:
                NewCarReplaceResultModels replaceResult = (NewCarReplaceResultModels) msg.obj;
                if(replaceResult.getStatus() == 100){
//				Log.i("gf", "返回成功:");
                    mNewCarReplaceResultModels = replaceResult;

                    mMoreSytleAdapter.notifyDataSetInvalidated();
                } else {
                    mNewCarReplaceResultModels = null;
//				Log.i("gf", "返回失败:" + replaceResult.getMsg());
                }
                break;
        }
    }
    @Override
    public void onRequestFault(Message msg) {
        ShowDialogTool.dismissLoadingDialog();
        switch(msg.what){
            case REQUEST_REPLACE_CAR_MSG:
                mNewCarReplaceResultModels = null;
//			Log.i("gf", "请求失败:");
                break;
        }
    }
    private final MoreSytleAdapter mMoreSytleAdapter = new MoreSytleAdapter();;
    private class MoreSytleAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            if(mNewCarReplaceResultModels == null || mNewCarReplaceResultModels.getNewSytleList().size() == 0){
                return 2;
            }
            return mNewCarReplaceResultModels.getNewSytleList().size() + 2;
        }

        @Override
        public Object getItem(int position) {
            if(position == 0)return "one";
            if(position >0 && position == getCount()-1){
                return "return";
            }
            return mNewCarReplaceResultModels.getNewSytleList().get(position-1);
        }
        public void removeItem(int position){
            if(position < 0 || position >= (getCount()-1)){
                return;
            }
            mNewCarReplaceResultModels.getNewSytleList().remove(position-1);
            this.notifyDataSetInvalidated();
        }
        @Override
        public long getItemId(int position) {
            return 0;
        }
        @Override
        public int getItemViewType(int position) {
            if(position == 0){
                return 0;
            }
            if(position >0 && position == getCount()-1){
                return 2;
            }
            return 1;
        }
        @Override
        public int getViewTypeCount() {
            return 3;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            switch(getItemViewType(position)){
                case 0:
                    if(convertView == null){
                        convertView  = LayoutInflater.from(ReplaceApplyCarActivity.this).inflate(R.layout.item_replace_apply_oldcar_layout, null);
                    }
                    ImageView mImgMyCarImg = (ImageView) convertView.findViewById(R.id.img_apply_request_imgpath);
                    TextView mTvMyCarFullName = (TextView) convertView.findViewById(R.id.tv_apply_request_car_fullname);
                    TextView mTvMyCarMsg = (TextView) convertView.findViewById(R.id.tv_apply_request_car_msg);
                    TextView mTvNewCarPice = (TextView) convertView.findViewById(R.id.tv_apply_request_car_price);

                    ImageView mImgPriceLevel = (ImageView) convertView.findViewById(R.id.img_apply_request_one_level);

                    TextView mTvPriceOne = (TextView) convertView.findViewById(R.id.tv_apply_request_car_price_one);
                    TextView mTvPriceTwo = (TextView) convertView.findViewById(R.id.tv_apply_request_car_price_two);
                    TextView mTvPriceThree = (TextView) convertView.findViewById(R.id.tv_apply_request_car_price_three);
                    TextView mTvPriceFour = (TextView) convertView.findViewById(R.id.tv_apply_request_car_price_four);
                    if(mNewCarReplaceResultModels != null){
                        ImageLoader.getInstance().displayImage(mNewCarReplaceResultModels.getOldImageUrl(), mImgMyCarImg, AppContext.mOptions);

                        mTvMyCarFullName.setText(mNewCarReplaceResultModels.getOldFullName());
                        mTvMyCarMsg.setText(mNewCarReplaceResultModels.getRegistDate() + "|"
                                + mNewCarReplaceResultModels.getOldMileage()
                                + "万公里|" + mNewCarReplaceResultModels.getOldCityName());
                        mTvNewCarPice.setText( checkStr(mNewCarReplaceResultModels.getOldNowMsrp() ));

                        mTvPriceOne.setText(checkStr(mNewCarReplaceResultModels.getC2BA()));
                        mTvPriceTwo.setText(checkStr(mNewCarReplaceResultModels.getC2BB()));
                        mTvPriceThree.setText(checkStr(mNewCarReplaceResultModels.getC2BC()));
                        mTvPriceFour.setText(checkStr(mNewCarReplaceResultModels.getOldNowMsrp()));

                        mTvPriceOne.setTextColor(getResources().getColor(R.color.grey3));
                        mTvPriceTwo.setTextColor(getResources().getColor(R.color.grey3));
                        mTvPriceThree.setTextColor(getResources().getColor(R.color.grey3));

                        String level = mNewCarRepalceParamsModels.getLevel();
                        if(!TextUtils.isEmpty(level)){
                            if("1".equals(level)){
                                mImgPriceLevel.setBackgroundResource(R.drawable.price_01);
                                mTvPriceOne.setTextColor(getResources().getColor(R.color.text_red));
                            } else if("3".equals(level)){
                                mImgPriceLevel.setBackgroundResource(R.drawable.price_03);
                                mTvPriceThree.setTextColor(getResources().getColor(R.color.text_red));
                            } else {
                                mImgPriceLevel.setBackgroundResource(R.drawable.price_02);
                                mTvPriceTwo.setTextColor(getResources().getColor(R.color.text_red));
                            }
                        } else {
                            mImgPriceLevel.setBackgroundResource(R.drawable.price_02);
                            mTvPriceTwo.setTextColor(getResources().getColor(R.color.text_red));
                        }

                    } else {
                        mImgMyCarImg.setImageResource(R.drawable.icon_launcher);
                        mTvMyCarFullName.setText("");
                        mTvMyCarMsg.setText("--年车辆|--万公里|--");
                        mTvNewCarPice.setText("暂无");

                        mTvPriceOne.setText("暂无");
                        mTvPriceTwo.setText("暂无");
                        mTvPriceThree.setText("暂无");
                        mTvPriceFour.setText("暂无");
                    }
                    break;
                case 1:
                    if(convertView == null){
                        convertView  = LayoutInflater.from(ReplaceApplyCarActivity.this).inflate(R.layout.item_replace_apply_newcar_layout, null);;
                    }
                    ImageView mImgCarImg = (ImageView) convertView.findViewById(R.id.img_add_item_new_car_pic);
                    TextView mTvCarFullName = (TextView) convertView.findViewById(R.id.tv_add_item_new_car_fullname);
                    TextView mTvCarPrice = (TextView) convertView.findViewById(R.id.tv_add_item_new_car_price);
                    TextView mTvCarPriceBetween = (TextView) convertView.findViewById(R.id.tv_add_item_new_car_price_between);
                    TextView mTvCount = (TextView) convertView.findViewById(R.id.tv_add_item_new_car_count);

                    NewCarItemBean bean = new NewCarItemBean();
                    NewCarBean car = (NewCarBean) getItem(position);
                    bean.mObj = car;
                    bean.picPath = car.getNewImageUrl();
                    bean.carFullname = car.getNewFullName();
                    bean.carPrice = car.getNewNowMsrp();
                    bean.carPriceBetween = car.getNewBuChaPice();
                    ImageLoader.getInstance().displayImage(bean.picPath, mImgCarImg ,AppContext.mOptions);
                    mTvCarFullName.setText(bean.carFullname);
                    mTvCarPrice.setText(checkStr(bean.carPrice) );

                    mTvCarPriceBetween.setText(bean.carPriceBetween);

                    break;
                case 2:
                    if(convertView == null){
                        convertView  = LayoutInflater.from(ReplaceApplyCarActivity.this).inflate(R.layout.view_replace_apply_submit_layout, null);
                    }
                    convertView.findViewById(R.id.btn_apply_request_three_submit).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            toRepalceNext(v);
                        }
                    });

                    break;
            }
            return convertView;
        }

    }
    private String checkStr(String price){
        if(TextUtils.isEmpty(price)){
            return "暂无";
        }
        double dou = Double.valueOf(price);
        if(dou == 0){
            return "暂无";
        }
        return price + "万";
    }
}
