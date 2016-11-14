package com.jzg.jzgoto.phone.adapter.user;

import android.content.Context;
import android.text.TextPaint;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.models.business.user.MessageData;
import com.jzg.jzgoto.phone.tools.DisplayUtils;

import java.util.ArrayList;
import java.util.List;

public class MessageListAdapter1 extends BaseAdapter {
    private Context mContext;
    private List<MessageData> mDataList = new ArrayList<MessageData>();
    private ListView mListView;
    private int txtcount;
    private int mMaxTextWidth;


    public MessageListAdapter1(Context context) {
        super();
        this.mContext = context;
        mMaxTextWidth = DisplayUtils.getDisplayPixelWidth(mContext) - 2 * DisplayUtils.dpToPx(mContext, 15);
    }

    public void setDataList(List<MessageData> dataList) {
        if (dataList != null) {
            mDataList.clear();
            mDataList.addAll(dataList);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return  mDataList == null ? 0 : mDataList.size();
    }

    @Override
    public MessageData getItem(int position) {
        return mDataList == null || mDataList.size() <= position ? null : mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder =null;
        if(convertView==null){
            viewHolder = new ViewHolder();
            convertView = View.inflate(mContext,R.layout.view_user_message_item_view, null);
            viewHolder.title = (TextView) convertView.findViewById(R.id.view_message_title_textview);
            viewHolder.tv_time = (TextView) convertView.findViewById(R.id.view_message_time_textview);
            viewHolder.content = (TextView) convertView.findViewById(R.id.view_message_description_textview);
            viewHolder.details = (TextView) convertView.findViewById(R.id.view_message_description_expand_textview);
            viewHolder.btn_show_all = (TextView) convertView.findViewById(R.id.btn_show_all);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String title = mDataList.get(position).getTitle();
        String time = mDataList.get(position).getUpdateTime();
        String detail = mDataList.get(position).getDetail();
        boolean isAll = mDataList.get(position).isAll;
        viewHolder.title.setText(title);
        viewHolder.tv_time.setText(time);

        TextPaint textPaint = viewHolder.details.getPaint();
        int textWidth = (int)textPaint.measureText(detail);

        ViewTreeObserver vto = viewHolder.content.getViewTreeObserver();
//        final ViewHolder finalViewHolder = viewHolder;
//        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
//            @Override
//            public boolean onPreDraw() {
//                txtcount = finalViewHolder.content.getLineCount();
//                return true;
//            }
//        });

        //大于两行
        if (textWidth > mMaxTextWidth * 3){
            if(isAll){
                viewHolder.content.setVisibility(View.GONE);
                viewHolder.details.setVisibility(View.VISIBLE);
                viewHolder.details.setText(detail);
                viewHolder.btn_show_all.setVisibility(View.VISIBLE);
                viewHolder.btn_show_all.setText("收起");
            }else {
                viewHolder.content.setVisibility(View.VISIBLE);
                viewHolder.details.setVisibility(View.GONE);
                viewHolder.content.setText(detail);
                viewHolder.btn_show_all.setText("全部");
            }
        }else{
            viewHolder.content.setVisibility(View.GONE);
            viewHolder.details.setVisibility(View.VISIBLE);
            viewHolder.details.setText(detail);
            viewHolder.btn_show_all.setVisibility(View.GONE);
        }

        viewHolder.btn_show_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mDataList.get(position).isAll ){
                    mDataList.get(position).setAll(false);
                }else{
                    mDataList.get(position).setAll(true);
                }
                notifyDataSetChanged();

            }
        });
        return convertView;
    }
    

    public class ViewHolder{
        public TextView title;//view_message_title_textview
        public TextView tv_time;//view_message_time_textview
        public TextView content;//view_message_description_textview
        public TextView details;//view_message_description_expand_textview
        public TextView btn_show_all;//btn_show_all
    }



}
