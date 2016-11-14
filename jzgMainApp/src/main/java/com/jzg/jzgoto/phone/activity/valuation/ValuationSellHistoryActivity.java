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
import android.widget.TextView;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.models.business.valuation.sell.RequestHistoryDealItemModel;
import com.jzg.jzgoto.phone.models.business.valuation.sell.RequestHistoryDealParams;
import com.jzg.jzgoto.phone.models.business.valuation.sell.RequestHistoryDealResult;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;
import com.jzg.jzgoto.phone.services.business.valuation.ValuationService;
import com.jzg.jzgoto.phone.services.common.OnRequestFinishListener;
import com.jzg.jzgoto.phone.tools.ShowDialogTool;
import com.jzg.jzgoto.phone.tools.view.swipemenulistview.SwipeMenuListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jzg on 2016/10/8.
 * Function :
 */
public class ValuationSellHistoryActivity extends Activity
        implements OnRequestFinishListener,SwipeMenuListView.IXListViewListener{

    public static final String GET_VAL_TYPE_PARAMS ="get_val_type_params";
    private RequestHistoryDealParams mParams;
    private LinearLayout mEmptyLayout;
    private SwipeMenuListView mListView;
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
        mListView = (SwipeMenuListView) findViewById(R.id.val_history_deal_list);
        mEmptyLayout = (LinearLayout) findViewById(R.id.history_result_empty_view);
        mListView.setXListViewListener(this);
        mListView.setPullLoadEnable(true);
        mListView.setPullRefreshEnable(true);
        mListView.setEmptyView(mEmptyLayout);
     //   mListView.isShowCarRefreshAndLoadMoreAnim(true, true);
    }

    private void toRequestHistoryList(){
        mParams.pageindex = mPageIndex+"";
		ShowDialogTool.showLoadingDialog(ValuationSellHistoryActivity.this);
        mValService.toResuestHistoryValDealList(
                mParams, RequestHistoryDealResult.class, R.id.request_val_sell_history_deal);
    }
    private void toShowList(RequestHistoryDealResult result){
        if(mPageIndex==1){
            mItemDataList.clear();
        }
        if(result.getHistoryList().size()<5){
            mListView.setPullLoadEnable(false);
            mListView.getmFooterView().hide();
        }
        mListView.stopLoadMore();
        mListView.stopRefresh();
        mItemDataList.addAll(result.getHistoryList());
        if(mAdapter==null){
            mAdapter = new HistoryAdapter();
            mAdapter.setList(mItemDataList);
            mListView.setAdapter(mAdapter);
        }else{
            mAdapter.setList(mItemDataList);
            mAdapter.notifyDataSetChanged();
        }
    }
    private static final SimpleDateFormat formatter = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");
    private String setRefreshTime = "";
    @Override
    public void onRefresh() {
        mPageIndex = 1;
        toRequestHistoryList();
        if(TextUtils.isEmpty(setRefreshTime)){
            mListView.setRefreshTime("首次刷新");
        }else{
            mListView.setRefreshTime(setRefreshTime);
        }
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        setRefreshTime = formatter.format(curDate);
    }
    @Override
    public void onLoadMore() {
        mPageIndex++;
        toRequestHistoryList();
    }
    public void onRequestSuccess(Message msg) {
        ShowDialogTool.dismissLoadingDialog();
        switch (msg.what) {
            case R.id.request_val_sell_history_deal:
                RequestHistoryDealResult result = (RequestHistoryDealResult) msg.obj;
                if(result.getStatus() == BaseResultModels.SUCCESS){
                    toShowList(result);
                }
                break;

            default:
                break;
        }
    }

    @Override
    public void onRequestFault(Message msg) {
        ShowDialogTool.dismissLoadingDialog();
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
                convertView = LayoutInflater.from(ValuationSellHistoryActivity.this).inflate(R.layout.item_valuation_sell_history_layout, null);
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
