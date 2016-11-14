package com.jzg.jzgoto.phone.adapter.sell;
/**
 * author: gcc
 * date: 2016/9/29 10:21
 * email:
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.models.business.sell.OneKeyReleasePlatformResult;
import com.jzg.jzgoto.phone.tools.ShowDialogTool;
import com.jzg.jzgoto.phone.view.sellcar.FlowLayout;
import com.jzg.jzgoto.phone.view.sellcar.ImageItemView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.view.View.inflate;

/**
 * author: guochen
 * date: 2016/9/29 10:21
 * email:
 */
public class OneKeyReleasePlatformAdapter extends BaseAdapter implements View.OnClickListener {
    public List<OneKeyReleasePlatformResult.SendCarPlatTotalListBean> SendCarPlatTotalList;
    public Context context;
    public Map<Integer, List<SelectItem>> selectimages = new HashMap<Integer, List<SelectItem>>();
    //    public List<SelectItem> selectItems = new ArrayList<>();
    public int myPosition;
    private ArrayList<SelectItem> selectItems;
    private List<Boolean> selects = new ArrayList<>();

    public OneKeyReleasePlatformAdapter(List<OneKeyReleasePlatformResult.SendCarPlatTotalListBean> SendCarPlatTotalList, Context context) {
        this.SendCarPlatTotalList = SendCarPlatTotalList;
        this.context = context;
        initData();
    }

    private void initData() {
        for (int i = 0; i < SendCarPlatTotalList.size(); i++) {
            selectItems = new ArrayList<>();
            for (int k = 0; k < SendCarPlatTotalList.get(i).getSendCarPlatList().size(); k++) {
                selectItems.add(new SelectItem(i, k, true));
            }
            selectimages.put(i, selectItems);
        }
    }

    @Override
    public int getCount() {
        return SendCarPlatTotalList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View contentView, ViewGroup viewGroup) {
        this.myPosition = position;
        ViewHolder viewHolder = null;
        if (contentView == null) {
            viewHolder = new ViewHolder();
            contentView = inflate(context, R.layout.activity_sellcar_item, null);
            viewHolder.title = (TextView) contentView.findViewById(R.id.tv_title);
            viewHolder.flowLayout = ((FlowLayout) contentView.findViewById(R.id.flowlayout));
            contentView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) contentView.getTag();
        }
        viewHolder.title.setText(SendCarPlatTotalList.get(position).getSendCarPlatName());
        List<OneKeyReleasePlatformResult.SendCarPlatTotalListBean.SendCarPlatListBean> sendCarPlatList = SendCarPlatTotalList.get(position).getSendCarPlatList();
        viewHolder.flowLayout.removeAllViews();
        for (int i = 0; i < sendCarPlatList.size(); i++) {
            ImageItemView imageItemView = new ImageItemView(context);
            imageItemView.setOnClickListener(this);
            imageItemView.setItemPosition(i);
            imageItemView.setPosition(position);
            if (selectimages.get(position).get(i).isSelect) {
                imageItemView.getImageselect().setVisibility(View.GONE);
                imageItemView.setUri(sendCarPlatList.get(i).getImageUrlDef(), sendCarPlatList.get(i).getID());
            } else {
                imageItemView.getImageselect().setVisibility(View.GONE);
                imageItemView.setUri(sendCarPlatList.get(i).getImageUrl(), sendCarPlatList.get(i).getID());
            }
            FlowLayout.LayoutParam layoutParams = new FlowLayout.LayoutParam(200, 80);
            imageItemView.setLayoutParams(layoutParams);
            viewHolder.flowLayout.addView(imageItemView);

        }
        return contentView;
    }

    @Override
    public void onClick(View view) {
//        getTripartiteId();
        if (!selectimages.get(((ImageItemView) view).getPosition()).get(((ImageItemView) view).getItemPosition()).isSelect) {
            selectimages.get(((ImageItemView) view).getPosition()).get(((ImageItemView) view).getItemPosition()).setSelect(true);
        } else {
            selectimages.get(((ImageItemView) view).getPosition()).get(((ImageItemView) view).getItemPosition()).setSelect(false);
        }
        notifyDataSetChanged();
        getTripartiteId();
        if (selects.size() == 0) {
            selectimages.get(((ImageItemView) view).getPosition()).get(((ImageItemView) view).getItemPosition()).setSelect(true);
            notifyDataSetChanged();
            ShowDialogTool.showCenterToast(context, "请至少选择一个帮卖平台");
            return;
        }
    }

    public class ViewHolder {
        public TextView title;
        public FlowLayout flowLayout;
    }

    public class SelectItem {
        public int position;//大的itemposition
        public int itemPosition;//小itemposition
        public boolean isSelect;

        public SelectItem(int position, int itemPosition, boolean isSelect) {
            this.position = position;
            this.itemPosition = itemPosition;
            this.isSelect = isSelect;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public int getItemPosition() {
            return itemPosition;
        }

        public void setItemPosition(int itemPosition) {
            this.itemPosition = itemPosition;
        }

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }
    }

    public void getTripartiteId() {
        selects.clear();
        for (Integer key : selectimages.keySet()) {
            for (int i = 0; i < selectimages.get(key).size(); i++) {
                if (selectimages.get(key).get(i).isSelect) {
                    selects.add(selectimages.get(key).get(i).isSelect);
                }

            }
        }


    }
}
