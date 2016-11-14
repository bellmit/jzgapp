package com.jzg.jzgoto.phone.view.buy;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.activity.buy.BuyCarActivity;
import com.jzg.jzgoto.phone.adapter.buy.BuyCarSortListAdapter;
import com.jzg.jzgoto.phone.global.AppContext;
import com.jzg.jzgoto.phone.models.business.buy.BuyCarFilterIndexModel;
import com.jzg.jzgoto.phone.models.business.buy.BuyCarListParams;
import com.jzg.jzgoto.phone.models.business.buy.PopListDataModel;
import com.jzg.jzgoto.phone.models.business.car.CarMakeBean;
import com.jzg.jzgoto.phone.models.business.car.CarMakeResultModels;
import com.jzg.jzgoto.phone.models.business.car.CarModelBean;
import com.jzg.jzgoto.phone.models.business.car.CarModelResultModels;
import com.jzg.jzgoto.phone.services.business.cartype.GetCarListInterface;
import com.jzg.jzgoto.phone.services.business.cartype.GetCarListService;
import com.jzg.jzgoto.phone.tools.ClickControlTool;
import com.jzg.jzgoto.phone.tools.ShowDialogTool;
import com.jzg.jzgoto.phone.valuationchoosecarstyle.HttpServiceCar;
import com.jzg.jzgoto.phone.view.ViewUtility;
import com.jzg.pricechange.phone.JzgCarChooseConstant;
import com.jzg.pricechange.phone.JzgCarChooseMake;
import com.jzg.pricechange.phone.JzgCarChooseMakeList;
import com.jzg.pricechange.phone.JzgCarChooseModelList;
import com.jzg.pricechange.phone.JzgCarChooseStyle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WY on 2016/9/13.
 * Function :买车 排序/品牌筛选 界面
 */
public class BuyCarTitleFilterView extends LinearLayout{
    private Context mContext;
    public BuyCarTitleFilterView(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public BuyCarTitleFilterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    public BuyCarTitleFilterView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
        initView();
    }

    private RelativeLayout mIsLoanLayout;
    private LinearLayout mSortLayout,mBrandLayout;
    private TextView mSortTextView,mBrandTextView;
    private ImageView mIsLoanImage;
    private TextView mMoreTextView;
    private LinearLayout mMoreLayout;
    public static final String SORT_TIME = "2";
    public static final String SORT_MILEAGE = "3";
    public static final String SORT_PRICE_MIN = "5";
    public static final String SORT_PRICE_MAX = "6";
    public static final String SORT_CARAGE = "7";
    // 2:发布时间降序(默认) 3:行驶里程升序 4:行驶里程降序 5:售价升序 6:售价降序 7:车龄升序 8:车龄降序

    private PopupWindow mPopWindow;
    private RelativeLayout mPopContainer;

    private View mIsLoanLine,mSortLine;
    private void initView(){
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_buycar_titlefilter_layout,null);
        mIsLoanLayout = (RelativeLayout) view.findViewById(R.id.view_buycar_titlefilter_isLoanLayout);
        mIsLoanLine = view.findViewById(R.id.view_buycar_titlefilter_defaultsort_line);
        mSortLine = view.findViewById(R.id.view_buycar_titlefilter_allbrand_Line);
        mSortLayout = (LinearLayout) view.findViewById(R.id.view_buycar_titlefilter_defaultsort_Layout);
        mBrandLayout = (LinearLayout) view.findViewById(R.id.view_buycar_titlefilter_allbrand_layout);
      //  mMoreTextView = (TextView) view.findViewById(R.id.view_buycar_titlefilter_more_textView);
        mMoreLayout = (LinearLayout) view.findViewById(R.id.view_buycar_titlefilter_more_layout);
        mSortTextView = (TextView) view.findViewById(R.id.view_buycar_titlefilter_defaultsort_textView);
        mBrandTextView = (TextView) view.findViewById(R.id.view_buycar_titlefilter_allbrand_textView);
        mIsLoanImage = (ImageView) view.findViewById(R.id.view_buycar_titlefilter_isLoan_ImageView);
        mIsLoanLayout.setOnClickListener(mClickListener);
        mSortLayout.setOnClickListener(mClickListener);
        mBrandLayout.setOnClickListener(mClickListener);
        mMoreLayout.setOnClickListener(mClickListener);
        initPopWindow();
        initCarSortList();
        initPopWindowView();
        WindowManager windowManager = ((Activity)getContext()).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        int screenWidth = display.getWidth();
        LayoutParams params = new LayoutParams(screenWidth, LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);
        this.addView(view);
    }

    /**
     * 新车时隐藏贷款购车按钮
     */
    public void hideIsLoanButton(){
        mIsLoanLayout.setVisibility(View.GONE);
        mIsLoanLine.setVisibility(View.GONE);
        mSortLayout.setVisibility(View.GONE);
        mSortLine.setVisibility(View.GONE);
    }
    public void showIsLoanButton(){
        mIsLoanLayout.setVisibility(View.VISIBLE);
        mIsLoanLine.setVisibility(View.VISIBLE);
        mSortLayout.setVisibility(View.VISIBLE);
        mSortLine.setVisibility(View.VISIBLE);
    }
    public void initTitleView(BuyCarFilterIndexModel filterIndexParams){
        mToFilterParams = filterIndexParams;
        if("1".equals(mToFilterParams.getParams().IsLoan)){
            mIsLoanImage.setVisibility(View.VISIBLE);
        }else{
            mIsLoanImage.setVisibility(View.GONE);
        }
    }
    public void setSortTextView(int sortIndex,String keyWords){
        if(sortIndex!=-1){
            mSortTextView.setText(mCarSortList.get(sortIndex).getText());
        }
        if(mToFilterParams!=null){
            if (mToFilterParams.getParams()!=null)
                mToFilterParams.getParams().keyword = keyWords;
        }
    }
    private OnClickListener mClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!ClickControlTool.isCanToClick())return;
            switch (v.getId()){
                case R.id.view_buycar_titlefilter_isLoanLayout:
                    //是否贷款购车
                    if("1".equals(mToFilterParams.getParams().IsLoan)){
                        mIsLoanImage.setVisibility(View.GONE);
                        mToFilterParams.getParams().IsLoan = "0";
                    }else{
                        mIsLoanImage.setVisibility(View.VISIBLE);
                        mToFilterParams.getParams().IsLoan = "1";
                    }
                    mRequestCallBack.toRequestBuyCarListCallBack(mToFilterParams,false);
                    break;
                case R.id.view_buycar_titlefilter_defaultsort_Layout:
                    //默认排序
                    clickType = CAR_SORT;
                    mSortTextView.setTextColor(getResources().getColor(R.color.text_blue));
                    mBrandTextView.setTextColor(getResources().getColor(R.color.text_item_lightgrey));
                    controlPopWindowShow();
                    break;
                case R.id.view_buycar_titlefilter_allbrand_layout:
                    //品牌
                    clickType = CAR_STYLE;
                    if (toInitFirstCarListView() == null) {
                        break;
                    }
                    controlPopWindowShow();
                    mBrandTextView.setTextColor(getResources().getColor(R.color.text_blue));
                    mSortTextView.setTextColor(getResources().getColor(R.color.text_item_lightgrey));
                    break;
                case R.id.view_buycar_titlefilter_more_layout:
                    //更多选项
                    mRequestCallBack.toFilterMoreActivity();
                    break;
            }
        }
    };

    private PopupWindow.OnDismissListener mDismissListener = new PopupWindow.OnDismissListener() {
        @Override
        public void onDismiss() {
            mSortTextView.setTextColor(getResources().getColor(R.color.text_item_lightgrey));
            mBrandTextView.setTextColor(getResources().getColor(R.color.text_item_lightgrey));
        }
    };
    /**
     * 初始化PopUpWindow
     */
    private void initPopWindow() {
        if (mPopWindow == null) {
            mPopWindow = new PopupWindow(mContext);
            View contentView = LayoutInflater.from(mContext).inflate(
                    R.layout.view_buycar_popupwindow, null);
            mPopContainer = (RelativeLayout) contentView
                    .findViewById(R.id.view_buycar_popWindow);
            contentView.findViewById(R.id.view_buycar_out_popWindow)
                    .setOnClickListener(new OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            mPopWindow.dismiss();
                        }
                    });
            mPopWindow.setContentView(contentView);
            mPopWindow.setWindowLayoutMode(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            mPopWindow.setBackgroundDrawable(new ColorDrawable(getResources()
                    .getColor(android.R.color.transparent)));
        }
        mPopWindow.setOnDismissListener(mDismissListener);
        mPopWindow.setFocusable(true);
        mPopWindow.setOutsideTouchable(true);
    }
    /**
     * 初始化排序
     */
    private List<PopListDataModel> mCarSortList = new ArrayList<>();
    private void initCarSortList() {
        mCarSortList.clear();
        mCarSortList.add(new PopListDataModel("最新发布", SORT_TIME,
                R.color.text_blue));
        mCarSortList.add(new PopListDataModel("价格最高", SORT_PRICE_MAX,
                R.color.text_item_lightgrey));
        mCarSortList.add(new PopListDataModel("价格最低", SORT_PRICE_MIN,
                R.color.text_item_lightgrey));
        mCarSortList.add(new PopListDataModel("里程最少", SORT_MILEAGE,
                R.color.text_item_lightgrey));
        mCarSortList.add(new PopListDataModel("车龄最短", SORT_CARAGE,
                R.color.text_item_lightgrey));
    }
    /**
     * 初始化排序
     */
    private int clickType = -1;
    private static final int CAR_STYLE = 1;
    private static final int CAR_SORT = 2;

    private BuyCarSelfCarStyleListRelative mSelfCarStyleListRelative;

    /**
     * 初始化PopUpWindow里的View
     * @return
     */
    private View initPopWindowView() {
        if (clickType == CAR_STYLE) {
              return mSelfCarStyleListRelative;
        }
        View view = LayoutInflater.from(mContext).inflate(
                R.layout.view_buycar_sortlist_layout, null);
        ListView listview = (ListView) view
                .findViewById(R.id.view_buycar_sort_listview);
        BuyCarSortListAdapter adapter = new BuyCarSortListAdapter(mContext);
        listview.setAdapter(adapter);
        switch (clickType) {
            case CAR_SORT:
                adapter.setListData(mCarSortList);
                initListItemColor(mCarSortList, mToFilterParams.getSortIndex());
                adapter.notifyDataSetChanged();
                break;
            default:
                break;
        }

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (clickType == CAR_SORT) {
                    mToFilterParams.getParams().Sore = mCarSortList.get(position).getTextId();
                    mToFilterParams.setSortIndex(position);
                    mRequestCallBack.toRequestBuyCarListCallBack(mToFilterParams,false);
                    controlPopWindowShow();
                }
            }
        });

        return view;
    }

    /**
     * 修改点击的Item颜色
     * @param list
     * @param index
     */
    private void initListItemColor(List<PopListDataModel> list, int index) {
        for (int i = 0; i < list.size(); i++) {
            if (i == index) {
                list.get(i).setTextColor(R.color.text_blue);
            } else {
                list.get(i).setTextColor(R.color.text_item_lightgrey);
            }
        }
    }

    /**
     * 控制PopUpWindow是否显示
     */
    private void controlPopWindowShow() {

        if (mPopContainer != null)
            mPopContainer.removeAllViews();
        mPopContainer.addView(initPopWindowView());
        if (mPopWindow.isShowing()) {
            mPopWindow.dismiss();
        } else {
            mPopWindow.showAsDropDown(mBrandLayout);
        }
    }
    private BuyCarFilterIndexModel mToFilterParams = null;
    /**
     * 初始化品牌筛选数据
     * @return
     */
    private View toInitFirstCarListView() {
        if (mSelfCarStyleListRelative == null) {
            mSelfCarStyleListRelative = new BuyCarSelfCarStyleListRelative(
                    getContext(), mPopWindow);
            mSelfCarStyleListRelative
                    .setCallbackForCarList(new BuyCarSelfCarStyleListRelative.CallbackForCarList() {
                        @Override
                        public void toRequestCarModel(String brandId) {
                            CarModelResultModels carModelResultModels  = getCarListInterface.queryCarModel(brandId, "0");
                            List<CarModelBean> modelLists = carModelResultModels.getModelList();
                            mSelfCarStyleListRelative.showModelListNew(modelLists);
                        //    startModelListThread(brandId);
                        }

                        @Override
                        public void toCallBackCarStyle(
                                JzgCarChooseStyle choseStyle) {
                            if (mPopWindow.isShowing()) {
                                mPopWindow.dismiss();
                            }
                            if (choseStyle != null) {
                                mToFilterParams.setBrandName(choseStyle.getBrandName());
                                mToFilterParams.setModeName(choseStyle.getModeName());
                                mToFilterParams.getParams().MakeID = String.valueOf(choseStyle.getBrandId());
                                mToFilterParams.getParams().ModelID = String.valueOf(choseStyle.getModeId());
                                //如果有搜索关键词时，清掉关键词
                                mRequestCallBack.toRequestBuyCarListCallBack(mToFilterParams,true);
                            }
                        }
                    });
        }
        if(mSelfCarStyleListRelative!=null)
            mSelfCarStyleListRelative.clearClickPosition();
        if (!mSelfCarStyleListRelative.checkHasMakesList()) {
            if(getMakeList().size()==0){
                startMakeListThread();
            }else{
                mSelfCarStyleListRelative.showMakeList(getMakeList());
                controlPopWindowShow();
                mBrandTextView.setTextColor(getResources().getColor(R.color.text_blue));
                mSortTextView.setTextColor(getResources().getColor(R.color.text_item_lightgrey));
            }
            return null;
        }
        return mSelfCarStyleListRelative;
    }
    GetCarListInterface getCarListInterface = null;
    private ArrayList<JzgCarChooseMake> getMakeList(){
        getCarListInterface = GetCarListService.getInstance(getContext());
        CarMakeResultModels carMakeResultModels = getCarListInterface.queryCarBrand("0");
        List<CarMakeBean> ds = carMakeResultModels.getMakeList();
        ArrayList<JzgCarChooseMake> makes = new ArrayList<>();
        for(int i = 0;i<ds.size();i++){
            JzgCarChooseMake JzgCarChooseMake = new JzgCarChooseMake();
            JzgCarChooseMake.setMakeName(ds.get(i).getMakeName());
            JzgCarChooseMake.setGroupName(ds.get(i).getGroupName());
            JzgCarChooseMake.setMakeId(Integer.parseInt(ds.get(i).getMakeId()));
            JzgCarChooseMake.setMakeLogo(ds.get(i).getMakeLogo());
            makes.add(JzgCarChooseMake);
        }
        return makes;
    }

    /**
     * 如果是新车参数为1，旧车为0
     *
     */
    private void startMakeListThread() {
        ShowDialogTool.showLoadingDialog(getContext());
        HttpServiceCar.getMakeList(getContext(), mHandForRequest,
                AppContext.getRequestQueue(), JzgCarChooseConstant.makelist,
                "1", "0");
    }

    /**
     * 车系查询线程 startModelListThread:
     */
    private void startModelListThread(final String makeid) {
        	ShowDialogTool.showLoadingDialog(getContext());
        HttpServiceCar.getModelList(getContext(), mHandForRequest,
                AppContext.getRequestQueue(), JzgCarChooseConstant.modellist,
                makeid, "1", "0");
    }

    private Handler mHandForRequest = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int id = msg.what;
            ShowDialogTool.dismissLoadingDialog();
            switch (id) {
                case JzgCarChooseConstant.makelist:
                    // 组装汽车品牌数据
                    final JzgCarChooseMakeList mMakeList = (JzgCarChooseMakeList) msg.obj;
                    if (mMakeList.getMakes() == null
                            || mMakeList.getMakes().size() == 0) {
                        ShowDialogTool.showCenterToast(getContext(),
                                getResources().getString(R.string.error_noData));
                        break;
                    }
                    mSelfCarStyleListRelative.showMakeList(mMakeList.getMakes());
                    controlPopWindowShow();
                    mBrandTextView.setTextColor(getResources().getColor(R.color.text_blue));
                    mSortTextView.setTextColor(getResources().getColor(R.color.text_item_lightgrey));
                    break;
                case JzgCarChooseConstant.modellist:
                    final JzgCarChooseModelList modelList = (JzgCarChooseModelList) msg.obj;
                    if (modelList.getModels() == null
                            || modelList.getModels().size() == 0) {
                        ShowDialogTool.showCenterToast(getContext(),
                                getResources().getString(R.string.error_noData));
                        break;
                    }
                  //  mSelfCarStyleListRelative.showModelList(modelList.getModels());
                    break;
                case JzgCarChooseConstant.net_error_back:
                    ShowDialogTool.showCenterToast(getContext(), getResources()
                            .getString(R.string.error_noConnect));
                    break;
                default:
                    break;
            }

        }

    };
    private RequestBuyCarCallback mRequestCallBack;

    public void setRequestBuyCarCallback(RequestBuyCarCallback callback) {
        mRequestCallBack = callback;
    }
    public interface RequestBuyCarCallback {

        public void toRequestBuyCarListCallBack(BuyCarFilterIndexModel requestParams,boolean isClearKeyWords);
        public void toFilterMoreActivity();
    }
}
