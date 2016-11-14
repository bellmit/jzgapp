package com.jzg.jzgoto.phone.adapter.buy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.models.business.buy.BuyCarItemModel;
import com.jzg.jzgoto.phone.tools.ImageRender;

import java.util.List;

/**
 * Created by WY on 2016/9/13.
 * Function :买车列表适配器
 */
public class BuyCarListAdapter extends BaseAdapter{

    private static final int OLD_CAR = 0;
    private static final int NEW_CAR = 1;
    private Context mContext;
    private List<BuyCarItemModel> mList;
    public BuyCarListAdapter(Context context,List<BuyCarItemModel> list){
        this.mContext = context;
        this.mList = list;
    }
    public void setDataList(List<BuyCarItemModel> list){
        this.mList = list;
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
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if("0".equals(mList.get(position).getIsNewCar())){
            //旧车
            return OLD_CAR;
        }else{
            //新车
            return NEW_CAR;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        switch (getItemViewType(position)){
            case OLD_CAR:
                OldCarViewHolder oldViewHolder = null;
                if(convertView==null){
                    oldViewHolder =new OldCarViewHolder();
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.item_buycar_old_layout,null);
                    oldViewHolder.oldIcon = (ImageView)convertView.findViewById(R.id.item_buy_car_icon);
                    oldViewHolder.oldName = (TextView)convertView.findViewById(R.id.item_buy_car_name);
                    oldViewHolder.oldMileage = (TextView)convertView.findViewById(R.id.item_buy_car_mileage);
                    oldViewHolder.oldRegDate = (TextView)convertView.findViewById(R.id.item_buy_car_date);
                    oldViewHolder.oldCity = (TextView)convertView.findViewById(R.id.item_buy_car_region);
                    oldViewHolder.oldIsLoan = (TextView)convertView.findViewById(R.id.item_buy_car_payment_textView);
                    oldViewHolder.oldPrice = (TextView)convertView.findViewById(R.id.item_buycar_price_textView);
                    oldViewHolder.oldJzgPrice = (TextView)convertView.findViewById(R.id.item_buycar_jzg_price_textView);
                //    oldViewHolder.oldSourceType = (TextView)convertView.findViewById(R.id.item_buycar_sourceType_textView);
                    convertView.setTag(oldViewHolder);
                }else{
                    oldViewHolder = (OldCarViewHolder) convertView.getTag();
                }
                BuyCarItemModel model = mList.get(position);
                ImageRender.getInstance().setImage(oldViewHolder.oldIcon,
                        model.getCarSourceImageUrl(), R.drawable.jingzhengu_moren);
                oldViewHolder.oldName.setText(model.getFullName());
                oldViewHolder.oldMileage.setText(model.getMileage());
                oldViewHolder.oldRegDate.setText(model.getReleaseTime());
                oldViewHolder.oldCity.setText(model.getCityName());
                if(model.getIsLoan()!=null){
                    //贷款
                    oldViewHolder.oldIsLoan.setText(model.getIsLoan());
                }else{
                    oldViewHolder.oldIsLoan.setText("");
                }
                oldViewHolder.oldPrice.setText(model.getSellPrice());
                if("0".equals(model.getApprisePrice())||"0.00".equals(model.getApprisePrice())){
                    oldViewHolder.oldJzgPrice.setText("");
                }else{
                    oldViewHolder.oldJzgPrice.setText("精真估估价"+model.getApprisePrice()+"万");
                }
                break;
            case NEW_CAR:
                NewCarViewHolder newViewHolder = null;
                if(convertView==null){
                    newViewHolder =new NewCarViewHolder();
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.item_buycar_new_layout,null);
                    newViewHolder.newIcon = (ImageView)convertView.findViewById(R.id.item_buycar_new_icon);
                    newViewHolder.newName = (TextView)convertView.findViewById(R.id.item_buycar_new_name);
                    newViewHolder.newCarType = (TextView)convertView.findViewById(R.id.item_buycar_new_Type_textView);
                    newViewHolder.newBeginPrice = (TextView)convertView.findViewById(R.id.item_buycar_new_startprice_textView);
                    newViewHolder.newEndPrice = (TextView)convertView.findViewById(R.id.item_buycar_new_endPrice_textView);
                    convertView.setTag(newViewHolder);
                }else{
                    newViewHolder = (NewCarViewHolder) convertView.getTag();
                }
                BuyCarItemModel newModel = mList.get(position);
                ImageRender.getInstance().setImage(newViewHolder.newIcon,
                        newModel.getCarSourceImageUrl(), R.drawable.jingzhengu_moren);
                newViewHolder.newName.setText(newModel.getFullName());
                newViewHolder.newCarType.setText(newModel.getModelLevelName());
                newViewHolder.newBeginPrice.setText(newModel.getMinMsrp());
                newViewHolder.newEndPrice.setText(newModel.getMaxMsrp());
                break;
        }
        return convertView;
    }

    class OldCarViewHolder{
        ImageView oldIcon;
        TextView oldName;
        TextView oldMileage,oldRegDate,oldCity;
        TextView oldIsLoan,oldPrice,oldJzgPrice,oldSourceType;
    }
    class NewCarViewHolder{
        ImageView newIcon;
        TextView newName;
        TextView newCarType;
        TextView newBeginPrice,newEndPrice;
    }
}
