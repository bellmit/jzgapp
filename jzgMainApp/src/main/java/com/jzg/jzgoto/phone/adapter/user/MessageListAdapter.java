package com.jzg.jzgoto.phone.adapter.user;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.jzg.jzgoto.phone.models.business.user.MessageData;
import com.jzg.jzgoto.phone.view.user.MessageItemView;

import java.util.ArrayList;
import java.util.List;

public class MessageListAdapter extends BaseAdapter {
    private Context mContext;
    private List<MessageData> mDataList = new ArrayList<MessageData>();
    private ListView mListView;

	
    public MessageListAdapter(Context context) {
        super();
        this.mContext = context;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        return getItemView(position, convertView);
    }
    
    protected View getItemView(int position, View convertView) {
        MessageItemView itemView = null;
        if (convertView != null && convertView instanceof MessageItemView) {
            itemView = (MessageItemView) convertView;
        } else {
            itemView = new MessageItemView(mContext);
        }
        itemView.setListAdapter(this);
        itemView.setData(mDataList.get(position));
        itemView.setDivider(position == getCount() - 1);
        return itemView;
    }

    public void setListView(ListView listView){
        mListView = listView;
    }

    public void handleItemExpand(MessageItemView itemView){
        itemView.getData().isExpand = true;
    }

    public void handleResizeListHeight(){
        int totalHeight = 0;
        for(int i=0;i<this.getCount();i++) {
            View viewItem = getView(i, null, mListView);//这个很重要，那个展开的item的measureHeight比其他的大
            viewItem.measure(0, 0);
            totalHeight += viewItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = mListView.getLayoutParams();
        params.height = totalHeight
                + (mListView.getDividerHeight() * (mListView.getCount() - 1));
        mListView.setLayoutParams(params);
        notifyDataSetChanged();
    }

}
