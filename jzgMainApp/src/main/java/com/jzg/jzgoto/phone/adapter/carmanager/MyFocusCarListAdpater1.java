package com.jzg.jzgoto.phone.adapter.carmanager;

import android.content.Context;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.event.DeleteMyFocusCarEvent;
import com.jzg.jzgoto.phone.models.business.carmanager.FocusCarData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.greenrobot.event.EventBus;

public class MyFocusCarListAdpater1 extends BaseAdapter {

    private Context context;
    private List<FocusCarData> mCarDataList;
    private View.OnClickListener mClickListener = null;
    final int INVALID_ID = -1;

     Listener listener;
    final Map<FocusCarData, Integer> mIdMap = new HashMap<>();

    public MyFocusCarListAdpater1(Context context) {
        super();
        this.context = context;
        mCarDataList = new ArrayList<>();

    }

    public void setOnClickListener(View.OnClickListener clickListener){
        mClickListener = clickListener;
    }

    public void setCarDataList(List<FocusCarData> dataList) {
        if (dataList != null) {
            mCarDataList.clear();
            mCarDataList.addAll(dataList);
            mIdMap.clear();
            for (int i = 0; i < mCarDataList.size(); ++i) {
                mIdMap.put(mCarDataList.get(i), i);
            }
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return  mCarDataList == null ? 0 : mCarDataList.size();
    }

    @Override
    public FocusCarData getItem(int position) {
        return mCarDataList == null || mCarDataList.size() <= position ? null : mCarDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        if (position < 0 || position >= mIdMap.size()) {
            return INVALID_ID;
        }
        FocusCarData item = getItem(position);
        return mIdMap.get(item);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
       final ViewHolder viewHolder;
        if(convertView==null){
             viewHolder = new ViewHolder();
            convertView = View.inflate(context,R.layout.view_carmanager_myfocuscar_item_view,null);
            viewHolder.mCarNameTextView = (TextView) convertView.findViewById(R.id.view_car_name_text);
            viewHolder.iv_delete = (ImageView) convertView.findViewById(R.id.view_delete_imageview);
            viewHolder.iv_move = (ImageView) convertView.findViewById(R.id.view_drag_imageview);
            viewHolder.ll_parent = (LinearLayout) convertView.findViewById(R.id.ll_lineaer);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(DeleteMyFocusCarEvent.build(mCarDataList.get(position)));
            }
        });
        viewHolder.iv_move.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(listener!=null){
                    listener.onGrab(position, viewHolder.ll_parent);
                }

                return false;
            }
        });
        viewHolder.mCarNameTextView.setText(mCarDataList.get(position).getMkMdName());
        return convertView;
    }
    
//    protected View getItemView(final int position, View convertView) {
//        MyFocusCarListItemView carView = null;
//        if (convertView != null && convertView instanceof MyFocusCarListItemView) {
//            carView = (MyFocusCarListItemView) convertView;
//        } else {
//            carView = new MyFocusCarListItemView(context);
//        }
//        final LinearLayout row = (LinearLayout) carView.findViewById(R.id.ll_lineaer);
//        carView.setData(mCarDataList.get(position));
//        carView.setDivider(position == getCount() - 1);
//        carView.getmDragImageView().setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                if(listener!=null){
//                    listener.onGrab(position, row);
//                }
//
//                return false;
//            }
//        });
//        return carView;
//    }

    public void setOnListener(Listener listener){
        this.listener = listener;
    }
    public interface Listener {
        void onGrab(int position, LinearLayout row);
    }


    @Override
    public boolean hasStableIds() {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP;
    }

    public class ViewHolder{
        TextView mCarNameTextView;
        ImageView iv_delete;
        ImageView iv_move;
        LinearLayout ll_parent;
    }

    public List<FocusCarData> getmCarDataList() {
        return mCarDataList;
    }
}
