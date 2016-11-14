package com.jzg.jzgoto.phone.adapter.valuation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.models.business.valuation.sell.RequestValFragmentHistory;
import com.jzg.jzgoto.phone.tools.ImageRender;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by WY on 2016/9/19.
 * Function :估值历史记录 适配器
 */
public class ValuationHistoryAdapter extends BaseAdapter{

    private Context mContext;
    private List<RequestValFragmentHistory> mList;

    public ValuationHistoryAdapter(Context mContext,List<RequestValFragmentHistory> mList) {
        super();
        this.mContext = mContext;
        this.mList = mList;
    }

    public void setmList(List<RequestValFragmentHistory> mList) {
        this.mList = mList;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_valuation_history_layout,null);
            holder.icon = (ImageView) convertView.findViewById(R.id.item_valuation_history_icon);
            holder.title = (TextView) convertView.findViewById(R.id.item_valuation_history_title);
            holder.time = (TextView) convertView.findViewById(R.id.item_valuation_history_time);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        RequestValFragmentHistory item = mList.get(position);
        ImageRender.getInstance().setImage(holder.icon, item.getImageUrl(), R.drawable.jingzhengu_moren);
        holder.title.setText(item.getStyleName());
        holder.time.setText(item.getUpdateTime()+"    "+item.getOperationTypeName());
        return convertView;
    }

    class ViewHolder{
        ImageView icon;
        TextView title,time;
    }
}
