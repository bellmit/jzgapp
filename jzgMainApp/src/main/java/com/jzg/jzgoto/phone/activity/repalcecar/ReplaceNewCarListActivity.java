package com.jzg.jzgoto.phone.activity.repalcecar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.components.self.discover.xlistview.XListView;
import com.jzg.jzgoto.phone.global.AppContext;
import com.jzg.jzgoto.phone.models.business.sell.NewCarListParamsModels;
import com.jzg.jzgoto.phone.models.business.sell.NewCarListResultModels;
import com.jzg.jzgoto.phone.models.business.sell.NewCarRepalceParamsModels;
import com.jzg.jzgoto.phone.services.business.login.LoginService;
import com.jzg.jzgoto.phone.services.common.OnRequestFinishListener;
import com.jzg.jzgoto.phone.tools.ClickControlTool;
import com.jzg.jzgoto.phone.tools.ShowDialogTool;
import com.jzg.jzgoto.phone.valuationchoosecarstyle.HttpServiceValuation;
import com.jzg.pricechange.phone.JzgCarChooseConstant;
import com.jzg.pricechange.phone.JzgCarChooseMake;
import com.jzg.pricechange.phone.JzgCarChooseMakeList;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by jzg on 2016/9/22.
 * Function :
 */
public class ReplaceNewCarListActivity extends Activity implements OnRequestFinishListener {


    private LoginService mLoginService;

    private LinearLayout mLinearOne;
    private ImageView mImgOne;
    private LinearLayout mLinearTwo;
    private ImageView mImgTwo;
    private LinearLayout mLinearThree;
    private ImageView mImgThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replace_newcar_list_layout);
        initView();
        initListener();
        initOther();
        initPopWindow();
    }

    public void initView() {
        mLoginService = new LoginService(this, this);
        mLinearOne = (LinearLayout) findViewById(R.id.linear_replace_new_car_title_one);
        mImgOne = (ImageView) findViewById(R.id.img_replace_new_car_title_one_img);
        mLinearTwo = (LinearLayout) findViewById(R.id.linear_replace_new_car_title_two);
        mImgTwo = (ImageView) findViewById(R.id.img_replace_new_car_title_two_img);
        mLinearThree = (LinearLayout) findViewById(R.id.linear_replace_new_car_title_three);
        mImgThree = (ImageView) findViewById(R.id.img_replace_new_car_title_three_img);

        mXListView = (XListView) findViewById(R.id.xlist_replace_new_car_list_show);
//		mXListView.setPullLoadEnable(true);
//       mXListView.setRefreshHeaderArrow(R.drawable.loading,false);
        mXListView.setPullRefreshEnable(true);
        mTvListEmpty = (TextView) findViewById(R.id.tv_replace_new_car_list_empty);
        mTvListEmpty.setVisibility(View.GONE);

        mViewTopLine = findViewById(R.id.view_for_pop);

        mTvApplyCount = (TextView) findViewById(R.id.tv_replace_new_car_list_count);
        mTvApplyCount.setVisibility(View.GONE);

        mNewCarListParamsModels = (NewCarListParamsModels) getIntent().getSerializableExtra("new_car");

        mNewCarRepalceParamsModels = (NewCarRepalceParamsModels) getIntent().getSerializableExtra("my_car");
    }

    private NewCarRepalceParamsModels mNewCarRepalceParamsModels;

    private NewCarListParamsModels mNewCarListParamsModels;

    // REQEUST_NEW_CAR_LIST
    private void toRequestNewCarList(int position, int requestCode) {
        if (mNewCarListParamsModels == null) {
            return;
        }
        mNewCarListParamsModels.PageIndex = String.valueOf(position);
        ShowDialogTool.showLoadingDialog(this);
        mLoginService.toRequestNet(
                mNewCarListParamsModels, NewCarListResultModels.class, requestCode);
    }

    private View mViewTopLine;

    private TextView mTvApplyCount;

    private XListView mXListView;
    private TextView mTvListEmpty;

    public void initListener() {
        mXListView.setAdapter(mMyAdapter);
        mXListView.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                // TODO
                mXListView.stopRefresh();
                mXListView.getmFooterView().show();
                toRequestNewCarList(1, REQEUST_NEW_CAR_LIST);
            }

            @Override
            public void onLoadMore() {
                // TODO
                mXListView.stopLoadMore();
                String pageIndex = mNewCarListParamsModels.PageIndex;
                if (TextUtils.isEmpty(pageIndex)) {
                    toRequestNewCarList(1, REQEUST_NEW_CAR_LIST);
                } else {
                    int index = Integer.valueOf(pageIndex);
                    index++;
                    toRequestNewCarList(index, REQEUST_NEW_CAR_LIST_ADD);
                }
            }
        });

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView ttv = new TextView(ReplaceNewCarListActivity.this);
                View view = null;
                View viewContent = null;
                switch (v.getId()) {
                    case R.id.linear_replace_new_car_title_one:
                        ShowDialogTool.showCenterToast(ReplaceNewCarListActivity.this, "品牌选择");
                        ShowDialogTool.showLoadingDialog(ReplaceNewCarListActivity.this);
                        toRequestBrandList();
                        return;
//					break;
                    case R.id.linear_replace_new_car_title_two:
                        ShowDialogTool.showCenterToast(ReplaceNewCarListActivity.this, "车系选择");
                        ttv.setText("车系选择");
                        viewContent = ttv;
                        view = mImgTwo;
                        break;
                    case R.id.linear_replace_new_car_title_three:
                        //价格选择
                        view = mImgThree;
                        viewContent = initPriceShowView();
                        break;
                }
                controlPopWindowShow(mViewTopLine, view, viewContent);
            }
        };
        mLinearOne.setOnClickListener(listener);
        mLinearTwo.setOnClickListener(listener);
        mLinearThree.setOnClickListener(listener);
        mTvApplyCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReplaceNewCarListActivity.this, ReplaceApplyCarActivity.class);
                // TODO 爱车信息
                String chooseCar = mMyAdapter.getChooseCarStyle();
                if (TextUtils.isEmpty(chooseCar)) {
                    ShowDialogTool.showCenterToast(ReplaceNewCarListActivity.this, "请选择要申请的新车");
                    return;
                }
                mNewCarRepalceParamsModels.setNewStyleID(chooseCar);

                intent.putExtra("apply_replace_car", mNewCarRepalceParamsModels);
                startActivityForResult(intent, 99);
            }
        });
        mTvApplyCount.setText("申请");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mNewCarListResultModels == null) {
            mXListView.getmFooterView().show();
            toRequestNewCarList(1, REQEUST_NEW_CAR_LIST);
            ;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 99:
                if (resultCode == 100) {
                    if (data != null) {
                        if (data.getBooleanExtra("result", false)) {
                            Intent intent = new Intent();
                            intent.putExtra("result", true);
                            setResult(100, intent);
                            ReplaceNewCarListActivity.this.finish();
                        }
                    }
                }
                break;
        }
    }

    private final MyAdapter mMyAdapter = new MyAdapter();

    private class MyAdapter extends BaseAdapter {
        private final List<ItemBean> list = new ArrayList<ItemBean>();

        public void setShowData(List<ItemBean> l) {
            list.clear();
            list.addAll(l);
            notifyDataSetChanged();
        }

        public void addShowData(List<ItemBean> l) {
            list.addAll(l);
            notifyDataSetChanged();
        }

        public void initItemChecked(int position) {
            if (position >= list.size() || position < 0) {
                return;
            }
            if (list.get(position).isChecked) {
                list.get(position).isChecked = false;
            } else {
                list.get(position).isChecked = true;
            }
            notifyDataSetChanged();
        }

        public String getChooseCarStyle() {
            StringBuffer sb = new StringBuffer();
            for (ItemBean item : list) {
                if (item.isChecked) {
                    if (sb.length() != 0) {
                        sb.append(",");
                    }
                    sb.append(item.mStyleList.get(item.hasChoosePosition).getStyleId());
                }
            }
            return sb.toString();
        }

        public int getCheckedCount() {
            int count = 0;
            for (ItemBean bean : list) {
                if (bean.isChecked) {
                    count++;
                }
            }
            return count;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public int getItemViewType(int position) {
            if (list.get(position).isChecked) {
                return 1;
            }
            return 0;
        }

        @SuppressLint("NewApi")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            HanBean han;
            if (convertView == null) {
                convertView = View.inflate(ReplaceNewCarListActivity.this, R.layout.item_replace_newcar_list_layout, null);
                han = new HanBean();
                convertView.setTag(han);
                han.imgPic = (ImageView) convertView.findViewById(R.id.img_item_new_car_carpicpath);
                han.tvFullname = (TextView) convertView.findViewById(R.id.tv_item_new_car_carfullname);
                han.tvPrice = (TextView) convertView.findViewById(R.id.tv_item_new_car_carprice);
                han.tvChecked = (TextView) convertView.findViewById(R.id.tv_item_new_car_checked);
                han.spinner = (Spinner) convertView.findViewById(R.id.spinner_item_new_car_for_replace_choose);
            } else {
                han = (HanBean) convertView.getTag();
            }

            final ItemBean itemBean = (ItemBean) getItem(position);
            final List<String> strs = new ArrayList<String>();
            for (int i = 0; i < itemBean.mStyleList.size(); i++) {
                strs.add(itemBean.mStyleList.get(i).getStyleName());
            }
            ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(ReplaceNewCarListActivity.this,
                    R.layout.item_replace_spinner_checked_layout, strs) {
                @Override
                public View getDropDownView(int position,
                                            View convertView, ViewGroup parent) {
                    if (convertView == null) {
                        convertView = LayoutInflater.from(ReplaceNewCarListActivity.this).inflate(R.layout.item_replace_spinner_drop_layout,
                                null);
                    }
                    ((TextView) convertView.findViewById(R.id.tv_spinner_show)).setText(strs.get(position));
                    return convertView;
                }
            };
            han.spinner.setAdapter(listAdapter);
            han.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view,
                                           int position, long id) {
                    itemBean.hasChoosePosition = position;
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            if (!TextUtils.isEmpty(itemBean.imgPath)) {
                ImageLoader.getInstance().displayImage(itemBean.imgPath, han.imgPic, AppContext.mOptions);
            }
            han.tvFullname.setText(itemBean.name);
            han.tvPrice.setText(itemBean.priceMin + "-" + itemBean.priceMax + "万");
            han.tvChecked.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!ClickControlTool.isCanToClickFor200()) {
                        return;
                    }

                    if (v.isSelected()) {
                        v.setSelected(false);
                        itemBean.isChecked = false;
                    } else {
                        if (getCheckedCount() >= 4) {
                            ShowDialogTool.showCenterToast(ReplaceNewCarListActivity.this, "最多可以选择四个置换新车");
                            return;
                        } else {
                            v.setSelected(true);
                            itemBean.isChecked = true;
                        }
                    }
                    initRightNumCount();
                }
            });

            if (itemBean.isChecked) {
                han.tvChecked.setSelected(true);
            } else {
                han.tvChecked.setSelected(false);
            }
            return convertView;
        }

        class HanBean {
            ImageView imgPic;
            //			ImageView imgApplyState;
            TextView tvFullname;
            TextView tvPrice;
            Spinner spinner;
            TextView tvChecked;
        }
    }

    private void initRightNumCount() {
        int count = mMyAdapter.getCheckedCount();
        if (count <= 0) {
            mTvApplyCount.setText("申请 ");
            mTvApplyCount.setVisibility(View.GONE);
        } else {
            mTvApplyCount.setText("申请 " + String.valueOf(count));
            mTvApplyCount.setVisibility(View.VISIBLE);
        }
    }

    private class ItemBean implements Serializable {

        public Object mObj;
        public String id;
        public String imgPath;
        public String name;
        public String priceMin;
        public String priceMax;
        public boolean isChecked = false;
        public int hasChoosePosition = 0;

        public List<NewCarListResultModels.ItemStyleList> mStyleList;

    }

    private class ItemStylesBean implements Serializable {

    }

    private final String[] mPricesForCar = new String[]{"不限", "5-10万", "10-20万", "20-30万", "30-50万", "50万以上"};
    private final String[] mPricesForCarValue = new String[]{"0", "1", "2", "3", "4", "5"};

    private View initPriceShowView() {

        final List<String> strs = new ArrayList<String>();

        for (String c : mPricesForCar) {
            strs.add(c);
        }

        ListView list = new ListView(ReplaceNewCarListActivity.this);
        list.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        list.setDivider(getResources().getDrawable(R.drawable.long_line));
        list.setDividerHeight((int) (1 * getResources().getDisplayMetrics().density));

        list.setAdapter(new BaseAdapter() {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = LayoutInflater.from(ReplaceNewCarListActivity.this).inflate(R.layout.item_replace_spinner_drop_layout,
                            null);
                }
                ((TextView) convertView.findViewById(R.id.tv_spinner_show)).setText(strs.get(position));
                return convertView;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public Object getItem(int position) {
                return strs.get(position);
            }

            @Override
            public int getCount() {
                return strs.size();
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (!ClickControlTool.isCanToClick()) {
                    return;
                }
                // TODO 进行该价格查询
                dismissPop();
            }
        });

        return list;
    }

    private void toRequestBrandList() {
        HttpServiceValuation.getMakeList(this, mHandler,
                AppContext.getRequestQueue(), JzgCarChooseConstant.makelist, "1");
    }

    private List<JzgCarChooseMake> mMakeList;
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            ShowDialogTool.dismissLoadingDialog();
            switch (msg.what) {
                case JzgCarChooseConstant.makelist:
                    // 组装汽车品牌数据
                    JzgCarChooseMakeList makeList = (JzgCarChooseMakeList) msg.obj;
                    mMakeList = makeList.getMakes();

                    List<String> listS = new ArrayList<String>();
                    List<MakeBean> listMB = new ArrayList<MakeBean>();

                    for (JzgCarChooseMake make : mMakeList) {
                        String groupName = make.getGroupName();
                        if (!TextUtils.isEmpty(groupName) && groupName.length() != 1) {
                            groupName = groupName.substring(0, 1).toUpperCase(Locale.CHINA);
                        }
                        if (!listS.contains(groupName)) {
                            listS.add(groupName);
                            listMB.add(new MakeBean(groupName, true));
                        }
                        listMB.add(new MakeBean(make.getMakeName(), true));
                    }

                    controlPopWindowShow(mViewTopLine, mImgOne, initBrandListView(listMB));
                    break;
            }
        }

        ;
    };

    private class MakeBean {
        public MakeBean(String ti, boolean is) {
            title = ti;
            isTitle = is;
        }

        public String title;
        public boolean isTitle = false;
    }

    private View initBrandListView(final List<MakeBean> listS) {
        ListView list = new ListView(ReplaceNewCarListActivity.this);
        list.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        list.setDivider(getResources().getDrawable(R.drawable.long_line));
        list.setDividerHeight((int) (1 * getResources().getDisplayMetrics().density));

        list.setAdapter(new BaseAdapter() {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = LayoutInflater.from(ReplaceNewCarListActivity.this).inflate(R.layout.item_replace_spinner_drop_layout,
                            null);
                }
                ((TextView) convertView.findViewById(R.id.tv_spinner_show)).setText(listS.get(position).title);


                return convertView;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public Object getItem(int position) {
                return listS.get(position);
            }

            @Override
            public int getCount() {
                return listS.size();
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO 进行品牌展示
                MakeBean bean = (MakeBean) parent.getItemAtPosition(position);
                if (bean.isTitle) return;

                dismissPop();
            }
        });
        return list;
    }

    private NewCarListResultModels mNewCarListResultModels;

    private final int REQEUST_NEW_CAR_LIST = 0x2001;
    private final int REQEUST_NEW_CAR_LIST_ADD = 0x2002;

    @Override
    public void onRequestSuccess(Message msg) {
        ShowDialogTool.dismissLoadingDialog();
        switch (msg.what) {
            case REQEUST_NEW_CAR_LIST:
                NewCarListResultModels carList = (NewCarListResultModels) msg.obj;
                mTvListEmpty.setVisibility(View.GONE);
                if (carList.getStatus() == 100 && carList.getModelList() != null) {
                    mNewCarListResultModels = carList;
                    if (TextUtils.isEmpty(carList.getTotalRecords()) || Integer.valueOf(carList.getTotalRecords()) == 0) {
                        List<ItemBean> list = new ArrayList<ItemBean>();
                        mMyAdapter.setShowData(list);
                        mTvListEmpty.setVisibility(View.VISIBLE);
                        mXListView.setPullLoadEnable(false);
                        return;
                    }
                    if (carList.getModelList().size() < 10) {
                        mXListView.setPullLoadEnable(false);
                    } else {
                        mXListView.setPullLoadEnable(true);
                    }

                    List<ItemBean> list = new ArrayList<ItemBean>();
                    for (NewCarListResultModels.ModelListBean item : carList.getModelList()) {
                        ItemBean itemBean = new ItemBean();
                        itemBean.mStyleList = item.getStyleList();
                        itemBean.mObj = item;

                        itemBean.id = item.getModelID();
                        itemBean.imgPath = item.getImageUrl();
                        itemBean.name = item.getModelName();
                        itemBean.priceMin = item.getMinMsrp();
                        itemBean.priceMax = item.getMaxMsrp();
                        itemBean.isChecked = false;

                        list.add(itemBean);
                    }
                    mMyAdapter.setShowData(list);
                    mTvListEmpty.setVisibility(View.GONE);
                    initRightNumCount();
                } else {
                    List<ItemBean> list = new ArrayList<ItemBean>();
                    mMyAdapter.setShowData(list);
                    mTvListEmpty.setVisibility(View.VISIBLE);
                    mXListView.getmFooterView().hide();
//				ShowDialogTool.showCenterToast(NewCarForReplaceActivity.this, "请求失败！");
                }
                break;
            case REQEUST_NEW_CAR_LIST_ADD:
                NewCarListResultModels carListLoad = (NewCarListResultModels) msg.obj;
                if (carListLoad.getStatus() == 100) {
                    mNewCarListResultModels = carListLoad;

                    if (carListLoad.getModelList() == null || carListLoad.getModelList().size() <= 0) {
                        mXListView.setPullLoadEnable(false);
                        return;
                    } else if (carListLoad.getModelList().size() < 10) {
                        mXListView.setPullLoadEnable(false);
                    } else {
                        mXListView.setPullLoadEnable(true);
                    }

                    List<ItemBean> list = new ArrayList<ItemBean>();
                    for (NewCarListResultModels.ModelListBean item : carListLoad.getModelList()) {
                        ItemBean itemBean = new ItemBean();
                        itemBean.mStyleList = item.getStyleList();
                        itemBean.mObj = item;

                        itemBean.id = item.getModelID();
                        itemBean.imgPath = item.getImageUrl();
                        itemBean.name = item.getModelName();
                        itemBean.priceMin = item.getMinMsrp();
                        itemBean.priceMax = item.getMaxMsrp();
                        itemBean.isChecked = false;

                        list.add(itemBean);
                    }
                    mMyAdapter.addShowData(list);
                }
                break;
        }
    }

    @Override
    public void onRequestFault(Message msg) {
        ShowDialogTool.dismissLoadingDialog();
        switch (msg.what) {
            case REQEUST_NEW_CAR_LIST_ADD:
            case REQEUST_NEW_CAR_LIST:
                ShowDialogTool.showCenterToast(ReplaceNewCarListActivity.this, "无法与服务器建立连接，请重试。");
                break;
        }
    }


    private PopupWindow mPopWindow;
    private RelativeLayout mPopContainer;

    private void initPopWindow() {
        if (mPopWindow == null) {
            mPopWindow = new PopupWindow(this);
            View contentView = LayoutInflater.from(this).inflate(
                    R.layout.view_replace_newcar_popwindow_layout, null);
            mPopContainer = (RelativeLayout) contentView
                    .findViewById(R.id.cars_popWindow);
            contentView.findViewById(R.id.cars_out_popWindow)
                    .setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mPopWindow.dismiss();
                        }
                    });
            mPopWindow.setContentView(contentView);
            mPopWindow.setWindowLayoutMode(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            mPopWindow.setBackgroundDrawable(new ColorDrawable(getResources()
                    .getColor(android.R.color.transparent)));
        }
        mPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (mCurentView != null)
                    mCurentView.startAnimation(mAnimationDown);
            }
        });
        mPopWindow.setFocusable(true);
        mPopWindow.setOutsideTouchable(true);
    }

    private Animation mAnimationUp, mAnimationDown;
    private View mCurentView;
    private void initOther(){
        mAnimationUp = AnimationUtils.loadAnimation(this,
                R.anim.list_title_drawable_right_up);
        mAnimationUp.setFillAfter(true);
        mAnimationDown = AnimationUtils.loadAnimation(this,
                R.anim.list_title_drawable_right_down);
        mAnimationDown.setFillAfter(true);
    }
    public void controlPopWindowShow(View hintLine, View imgView, View showView) {
        if (mPopContainer != null) mPopContainer.removeAllViews();
        mPopContainer.addView(showView);
        mCurentView = imgView;
        if (mPopWindow.isShowing()) {
            mPopWindow.dismiss();
        } else {
            mPopWindow.showAsDropDown(hintLine);
            imgView.startAnimation(mAnimationUp);
        }
    }

    public void dismissPop() {
        if (mPopWindow != null && mPopWindow.isShowing()) {
            mPopWindow.dismiss();
        }
    }
}
