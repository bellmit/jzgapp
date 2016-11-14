package com.jzg.jzgoto.phone.activity.valuation;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.amap.api.maps.model.Text;
import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.global.AppContext;
import com.jzg.jzgoto.phone.models.business.valuation.sell.RequestHistoryDealItemModel;
import com.jzg.jzgoto.phone.models.business.valuation.sell.RequestHistoryDealParams;
import com.jzg.jzgoto.phone.models.business.valuation.sell.RequestHistoryDealResult;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;
import com.jzg.jzgoto.phone.services.business.valuation.ValuationService;
import com.jzg.jzgoto.phone.services.common.OnRequestFinishListener;
import com.jzg.jzgoto.phone.tools.DialogManager;
import com.jzg.jzgoto.phone.tools.ListViewUtils;
import com.jzg.jzgoto.phone.tools.ShowDialogTool;
import com.jzg.jzgoto.phone.tools.view.swipemenulistview.SwipeMenuListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jzg on 2016/10/31.
 * Function :
 */
public class ValuationHistoryDealPriceActivity extends Activity implements
        OnRequestFinishListener{
    public static final String GET_VAL_TYPE_PARAMS ="get_val_type_params";
    private RequestHistoryDealParams mParams;
    private LinearLayout mEmptyLayout,mListLayout;
    private ListView mListView;
    private TextView mSeeMore;
    private TextView mNetErrorTextView;
    private List<RequestHistoryDealItemModel> mItemDataList = new ArrayList<>();
    private int mPageIndex = 1;
    private HistoryAdapter mAdapter ;
    private ValuationService mValService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valuation_sell_history_layout);
        init();
        mParams = (RequestHistoryDealParams) getIntent().getSerializableExtra(GET_VAL_TYPE_PARAMS);
        mValService = new ValuationService(this, this);
        toRequestHistoryList();
    }

    public void init() {
        mListView = (ListView) findViewById(R.id.val_history_deal_list);
        mEmptyLayout = (LinearLayout) findViewById(R.id.history_result_empty_view);
        mListLayout = (LinearLayout) findViewById(R.id.val_history_deal_list_Layout);
        mSeeMore = (TextView) findViewById(R.id.val_history_deal_text_seeMore);
        mNetErrorTextView = (TextView) findViewById(R.id.history_result_empty_Textview);
        mSeeMore.setOnClickListener(mOnClickListener);
    //    mListView.setEmptyView(mEmptyLayout);
        //   mListView.isShowCarRefreshAndLoadMoreAnim(true, true);
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.val_history_deal_text_seeMore:
                        if(AppContext.isLogin()){
                            mPageIndex++;
                            toRequestHistoryList();
                        }else{
                            DialogManager.toLoginDialog(ValuationHistoryDealPriceActivity.this);
                        }
                    break;
            }
        }
    };
    private void toRequestHistoryList(){
        mParams.pageindex = mPageIndex+"";
        ShowDialogTool.showLoadingDialog(ValuationHistoryDealPriceActivity.this);
        mValService.toResuestHistoryValDealList(
                mParams, RequestHistoryDealResult.class, R.id.request_val_sell_history_deal);
    }
    private void toShowList(RequestHistoryDealResult result){
        if(mPageIndex==1){
            mItemDataList.clear();
            mSeeMore.setVisibility(View.VISIBLE);
        }
        if(result.getHistoryList().size()<5){
            mSeeMore.setVisibility(View.GONE);
        }
        mItemDataList.addAll(result.getHistoryList());
        if(mItemDataList.size()==0){
            mEmptyLayout.setVisibility(View.VISIBLE);
            mListLayout.setVisibility(View.GONE);
            mNetErrorTextView.setText("暂无历史成交记录");
        }else{
            mEmptyLayout.setVisibility(View.GONE);
            mListLayout.setVisibility(View.VISIBLE);
        }
        if(mAdapter==null){
            mAdapter = new HistoryAdapter();
            mAdapter.setList(mItemDataList);
            mListView.setAdapter(mAdapter);
        }else{
            mAdapter.setList(mItemDataList);
            mAdapter.notifyDataSetChanged();
        }
        ListViewUtils.setListViewHeight(mListView);
    }

    public void onRequestSuccess(Message msg) {
        ShowDialogTool.dismissLoadingDialog();
        switch (msg.what) {
            case R.id.request_val_sell_history_deal:
                RequestHistoryDealResult result = (RequestHistoryDealResult) msg.obj;
                if(result.getStatus() == BaseResultModels.SUCCESS){
                    toShowList(result);
                }else{
                    mEmptyLayout.setVisibility(View.VISIBLE);
                    mListLayout.setVisibility(View.GONE);
                    mNetErrorTextView.setText("暂无历史成交记录");
                    ShowDialogTool.showCenterToast(this,getResources().getString(R.string.error_net));
                }
                break;

            default:
                break;
        }
    }

    @Override
    public void onRequestFault(Message msg) {
        ShowDialogTool.dismissLoadingDialog();
        mEmptyLayout.setVisibility(View.VISIBLE);
        mListLayout.setVisibility(View.GONE);
        mNetErrorTextView.setText(getResources().getString(R.string.error_net));
        ShowDialogTool.showCenterToast(this,getResources().getString(R.string.error_net));
    }
    class HistoryAdapter extends BaseAdapter {
        private List<RequestHistoryDealItemModel> list;

        public void setList(List<RequestHistoryDealItemModel> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if(convertView==null){
                holder = new ViewHolder();
                convertView = LayoutInflater.from(ValuationHistoryDealPriceActivity.this).inflate(R.layout.item_valuation_sell_history_layout, null);
                holder.title = (TextView) convertView.findViewById(R.id.item_val_history_name);
                holder.price = (TextView) convertView.findViewById(R.id.item_val_history_price);
                holder.regDate = (TextView) convertView.findViewById(R.id.item_val_history_time);
                holder.mileage = (TextView) convertView.findViewById(R.id.item_val_history_mileage);
                holder.region = (TextView) convertView.findViewById(R.id.item_val_history_city);
                holder.dealTime = (TextView) convertView.findViewById(R.id.item_val_history_dealTime);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder) convertView.getTag();
            }
            RequestHistoryDealItemModel itemModel = list.get(position);
            holder.title.setText(itemModel.getFullName());
            holder.price.setText(itemModel.getListingPrice());
            holder.regDate.setText(itemModel.getPurchaseDate());
            holder.mileage.setText(itemModel.getMileage()+"万公里");
            holder.region.setText(itemModel.getCityName());
            holder.dealTime.setText(itemModel.getPublishDate()+"成交");
            return convertView;
        }

        class ViewHolder{
            TextView title, price,regDate,mileage,region,dealTime;
        }
    }
}
