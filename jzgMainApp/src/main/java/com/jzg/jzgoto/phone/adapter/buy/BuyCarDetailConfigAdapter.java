package com.jzg.jzgoto.phone.adapter.buy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.jzg.jzgoto.phone.view.buy.BuyCarDetailConfigView.ConfigItemModel;

import com.jzg.jzgoto.phone.R;

import java.util.List;

/**
 * Created by WY on 2016/9/19.
 * Function :买车详情——配置适配器
 */
public class BuyCarDetailConfigAdapter extends BaseAdapter{

    private Context mContext;
    private List<ConfigItemModel> mList;
    public BuyCarDetailConfigAdapter(Context context,List<ConfigItemModel> list){
        this.mContext = context;
        this.mList = list;
    }
    @Override
    public int getCount() {
        return 6;
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
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_buycar_detail_config_layout,null);
            holder.content = (TextView )convertView.findViewById(R.id.item_buycar_detail_config_content);
            holder.title = (TextView)convertView.findViewById(R.id.item_buycar_detail_config_title);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.content.setText(mList.get(position).getContent());
        holder.title.setText(mList.get(position).getTitle());
        return convertView;
    }

    class ViewHolder{
        TextView title,content;
    }
}
