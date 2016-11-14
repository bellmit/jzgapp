package com.jzg.jzgoto.phone.adapter.buy;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.models.business.buy.PopListDataModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WY on 2016/9/13.
 * Function :买车界面，排序适配器
 */
public class BuyCarSortListAdapter extends BaseAdapter{
    private final List<PopListDataModel> mList = new ArrayList<PopListDataModel>();
    private Context mContext;

    public BuyCarSortListAdapter(Context mContext) {
        super();
        this.mContext = mContext;
    }

    public void setListData(List<PopListDataModel> list) {
        mList.clear();
        mList.addAll(list);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ListHolder holder;
        if (convertView == null) {
            holder = new ListHolder();
            LinearLayout linear = new LinearLayout(mContext);
            linear.setOrientation(LinearLayout.HORIZONTAL);
            linear.setLayoutParams(new AbsListView.LayoutParams(
                    AbsListView.LayoutParams.MATCH_PARENT,
                    AbsListView.LayoutParams.WRAP_CONTENT));
            linear.setGravity(Gravity.CENTER_VERTICAL);
            linear.setPadding(20, 20, 5, 20);
            TextView tv = new TextView(mContext);
            tv.setTextSize(14f);
            tv.setText("");
            tv.setTextColor(mContext.getResources()
                    .getColor(R.color.text_item_lightgrey));
            holder.tv = tv;
            linear.addView(tv);
            convertView = linear;
            convertView.setTag(holder);
        }
        holder = (ListHolder) convertView.getTag();
        holder.tv.setText(mList.get(position).getText());
        holder.tv.setTextColor(mContext.getResources().getColor(
                mList.get(position).getTextColor()));
        return convertView;
    }

    class ListHolder {
        TextView tv;
    }
}
