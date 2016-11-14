package com.jzg.jzgoto.phone.view.buy;

import android.content.Context;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.adapter.buy.BuyCarListAdapter;
import com.jzg.jzgoto.phone.constant.Constant;
import com.jzg.jzgoto.phone.global.AppContext;
import com.jzg.jzgoto.phone.global.HttpServiceHelper;
import com.jzg.jzgoto.phone.models.business.buy.BuyCarDetailParams;
import com.jzg.jzgoto.phone.models.business.buy.BuyCarDetailResult;
import com.jzg.jzgoto.phone.models.business.buy.BuyCarItemModel;
import com.jzg.jzgoto.phone.models.business.valuation.ValuationSellCarResult;
import com.jzg.jzgoto.phone.services.business.buy.BuyCarService;
import com.jzg.jzgoto.phone.services.common.OnRequestFinishListener;
import com.jzg.jzgoto.phone.tools.ClickControlTool;
import com.jzg.jzgoto.phone.tools.ListViewUtils;
import com.jzg.jzgoto.phone.tools.ShowDialogTool;
import com.jzg.jzgoto.phone.tools.view.swipemenulistview.SwipeMenuView;
import com.jzg.jzgoto.phone.view.ViewUtility;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WY on 2016/9/18.
 * Function :买车详情 类似车源推荐
 */
public class BuyCarDetailSimilarCarView extends LinearLayout implements OnRequestFinishListener{
    private Context mContext;
    public BuyCarDetailSimilarCarView(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public BuyCarDetailSimilarCarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    public BuyCarDetailSimilarCarView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
        initView();
    }

    private RelativeLayout mOldCarLayout,mNewCarLayout;
    private TextView mOldCarText,mNewCarText;
    private View mOldCarView,mNewCarView;
    private ListView mListView;
    private TextView mLoadMore;
    private LinearLayout mPriceHintShow;
    private void initView(){
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_buycar_detail_similarcar_layout,null);
        mPriceHintShow = (LinearLayout) view.findViewById(R.id.buycar_detail_similarOldCar_priceHintShow);
        mOldCarLayout = (RelativeLayout) view.findViewById(R.id.buycar_detail_similarOldCar_layout);
        mNewCarLayout = (RelativeLayout) view.findViewById(R.id.buycar_detail_similarNewCar_layout);
        mOldCarText = (TextView) view.findViewById(R.id.buycar_detail_similarOldCar_textView);
        mNewCarText = (TextView) view.findViewById(R.id.buycar_detail_similarNewCar_textView);
        mOldCarView =  view.findViewById(R.id.buycar_detail_similarOldCar_bottomView);
        mNewCarView =  view.findViewById(R.id.buycar_detail_similarNewCar_bottomView);
        mListView = (ListView) view.findViewById(R.id.buycar_detail_similarNewCar_listView);
        mLoadMore = (TextView) view.findViewById(R.id.buycar_detail_similarNewCar_more);
        mOldCarLayout.setOnClickListener(mClickListener);
        mNewCarLayout.setOnClickListener(mClickListener);
        mLoadMore.setOnClickListener(mClickListener);
        mListView.setOnItemClickListener(itemClickListener);
        this.addView(view);
    }
    private List<BuyCarItemModel> mOldCarList = new ArrayList<>();
    private List<BuyCarItemModel> mNewCarList = new ArrayList<>();
    private BuyCarListAdapter mAdapter;
    public void showSimilarListData(List<BuyCarItemModel> tjOldCarList,
                                    List<BuyCarItemModel> tjNewCarList){
        mOldCarList.clear();
        mNewCarList.clear();
        mOldCarList.addAll(tjOldCarList);
        mNewCarList.addAll(tjNewCarList);
        countrlShowList();
    }

    /**
     * 估值时隐藏"同价位选车推荐"文字
     * 新车Tab显示"同价位新车"
     */
    public void hidePriceHintShow(){
        mPriceHintShow.setVisibility(View.GONE);
        mNewCarText.setText("同价位新车");

    }
    private String paramsPriceBegin = "",paramsPriceEnd = "";
    public void setParamsPrice(String priceBegin,String priceEnd){
        this.paramsPriceBegin = priceBegin;
        this.paramsPriceEnd = priceEnd;

    }
    private boolean isOldCarSelect = true;
    private void countrlShowList(){
        if(mAdapter == null){
            mAdapter = new BuyCarListAdapter(mContext,mOldCarList);
            ListViewUtils.setListViewHeight(mListView);
            mListView.setAdapter(mAdapter);
        }
        if(isOldCarSelect){
            mAdapter.setDataList(mOldCarList);
            ListViewUtils.setListViewHeight(mListView);
            mAdapter.notifyDataSetChanged();
        }else{
            mAdapter.setDataList(mNewCarList);
            ListViewUtils.setListViewHeight(mListView);
            mAdapter.notifyDataSetChanged();
        }
    }
    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if(isOldCarSelect){
                //二手车
                BuyCarItemModel model = mOldCarList.get(position);
                toRequestBuyCarDetail(model);
            }else{
                //新车
                if(!TextUtils.isEmpty(mNewCarList.get(position).getUrl()))
                    ViewUtility.navigateToWebView(mContext,"",mNewCarList.get(position).getUrl());
            }
        }
    };
    private OnClickListener mClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!ClickControlTool.isCanToClick())return;
            switch (v.getId()){
                case R.id.buycar_detail_similarOldCar_layout:
                    //二手车
                    changeBtnBlue(mOldCarText,mOldCarView);
                    changeBtnGrey(mNewCarText,mNewCarView);
                    isOldCarSelect = true;
                    countrlShowList();
                    break;
                case R.id.buycar_detail_similarNewCar_layout:
                    //新车
                    changeBtnBlue(mNewCarText,mNewCarView);
                    changeBtnGrey(mOldCarText,mOldCarView);
                    isOldCarSelect = false;
                    countrlShowList();
                    break;
                case R.id.buycar_detail_similarNewCar_more:
                    //加载更多
                    if(isOldCarSelect){
                        ViewUtility.navigateToBuyCarActivity(mContext,"1","0","","0","","0","",paramsPriceBegin,paramsPriceEnd,"");
                    }else{
                        ViewUtility.navigateToBuyCarActivity(mContext,"2","0","","0","","0","",paramsPriceBegin,paramsPriceEnd,"");
                    }
                    break;
            }
        }
    };

    @Override
    public void onRequestSuccess(Message msg) {
        ShowDialogTool.dismissLoadingDialog();
        if(msg.what == R.id.request_buy_car_detail){
            BuyCarDetailResult detailResult = (BuyCarDetailResult)msg.obj;
            if(detailResult.getStatus() == Constant.SUCCESS){
                ViewUtility.navigateToBuyCarDetailActivity(mContext,detailResult);
            }else{
                ShowDialogTool.showCenterToast(mContext,getResources().getString(R.string.error_net));
            }
        }
    }

    @Override
    public void onRequestFault(Message msg) {
        ShowDialogTool.dismissLoadingDialog();
        ShowDialogTool.showCenterToast(mContext,getResources().getString(R.string.error_net));
    }

    private void toRequestBuyCarDetail(BuyCarItemModel model){
        ShowDialogTool.showLoadingDialog(mContext);
        BuyCarDetailParams params = new BuyCarDetailParams();
        if(AppContext.isLogin()){
            params.uid = AppContext.mLoginResultModels.getId();
        }
        params.CarSourceId =model.getCarSourceID();
        params.CarSourceFrom = model.getCarSourceFrom();
        new BuyCarService(mContext,this).toResuestBuyCarDetail(params,BuyCarDetailResult.class,R.id.request_buy_car_detail);
    }
    private void changeBtnBlue(TextView blueTextView,View blueBg){
        if(blueTextView!=null)
            blueTextView.setTextColor(getResources().getColor(R.color.text_blue));
        if(blueBg!=null)
            blueBg.setBackgroundColor(getResources().getColor(R.color.text_blue));
    }
    private void changeBtnGrey(TextView greyTextView,View greyBg){
        if(greyTextView!=null)
            greyTextView.setTextColor(getResources().getColor(R.color.grey_for_valuation));
        if(greyBg!=null)
            greyBg.setBackgroundColor(getResources().getColor(R.color.background));
    }
}
