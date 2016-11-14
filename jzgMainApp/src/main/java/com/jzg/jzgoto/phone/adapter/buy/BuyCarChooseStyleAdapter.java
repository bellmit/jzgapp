package com.jzg.jzgoto.phone.adapter.buy;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jzg.jzgoto.phone.R;

/**
 * Created by WY on 2016/9/21.
 * Function :车型选择适配器
 */
public class BuyCarChooseStyleAdapter extends BaseAdapter{

    private String[] mString;
    private int[]mColor;
    private Context mContext;

    public BuyCarChooseStyleAdapter(String[] mString,int[] mColor, Context mContext) {
        super();
        this.mString = mString;
        this.mColor = mColor;
        this.mContext = mContext;
    }

    public void setmString(String[] mString) {
        this.mString = mString;
    }

    @Override
    public int getCount() {
        return mString.length;
    }

    @Override
    public Object getItem(int position) {
        return mString[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ListHolder holder = null;
        if(convertView == null){
            holder= new ListHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_buycar_style_choose_layout, null);
            holder.img = (ImageView) convertView.findViewById(R.id.item_buy_car_color_img);
            holder.tv = (TextView) convertView.findViewById(R.id.item_buy_car_color_text);
            convertView.setTag(holder);
        }else{
            holder = (ListHolder) convertView.getTag();
        }
        holder.tv.setText(mString[position]);
        if(mColor==null){
            holder.img.setVisibility(View.GONE);
        }else{
            holder.img.setImageResource(mColor[position]);
        }
        return convertView;
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                mContext.getResources().getDisplayMetrics());
    }
    class ListHolder {
        ImageView img;
        TextView tv;
    }
}
