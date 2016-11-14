package com.jzg.jzgoto.phone.valuationchoosecarstyle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jzg.pricechange.phone.JzgCarChooseConstant;
import com.jzg.pricechange.phone.JzgCarChooseModel;

import java.util.List;

/**
 * (去掉前面图标) 车系分类列表适配器 ClassName: ModelCategoryAdapter <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * date: 2014-6-18 下午3:15:31 <br/>
 */
public class JzgCarChooseModelCategoryAdapter1 extends BaseAdapter {
	private LayoutInflater inflater;
	private List<JzgCarChooseModel> list;
	private List<String> groupkey;

	public JzgCarChooseModelCategoryAdapter1(Context context,
											 List<JzgCarChooseModel> list, List<String> groupkey) {
		super();
		inflater = LayoutInflater.from(context);
		this.list = list;
		this.groupkey = groupkey;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// return list.get(position).getName();
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public boolean isEnabled(int position) {
		if (groupkey
				.contains(((JzgCarChooseModel) getItem(position)).getName())
				&& JzgCarChooseConstant.IS_TITLE
						.equals(((JzgCarChooseModel) getItem(position))
								.getManufacturerName())) {
			return false;
		} else {
			return true;
		}
		// return super.isEnabled(position);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		JzgCarChooseModel model = (JzgCarChooseModel) getItem(position);

		if (groupkey.contains(model.getName())
				&& model.getManufacturerName() == null) {
			model.setName(model.getName() + " ");
		}
		if (groupkey.contains(model.getName())
				&& JzgCarChooseConstant.IS_TITLE.equals(model
						.getManufacturerName())) {
			if (convertView == null)
				convertView = inflater.inflate(
						com.jzg.jzgcarchoose.R.layout.jzgcarchoose_addexam_list_item_tag, null);
			convertView.setBackgroundResource(com.jzg.jzgcarchoose.R.color.title);
		} else {
			if (convertView == null)
				convertView = inflater.inflate(
						com.jzg.jzgcarchoose.R.layout.jzgcarchoose_addexam_list_item, null);

			convertView.setBackgroundColor(list.get(position).getItemColor());
		}
		ImageView image = (ImageView) convertView
				.findViewById(com.jzg.jzgcarchoose.R.id.addexam_list_icon);
		// if (!TextUtils.isEmpty(list.get(position).getModelimgpath())) {
		// if (image.getVisibility() != View.VISIBLE)
		// image.setVisibility(View.VISIBLE);
		// ImageLoader.getInstance().displayImage(
		// list.get(position).getModelimgpath(), image);
		// } else {
		// image.setVisibility(View.GONE);
		// }
		image.setVisibility(View.GONE);

		TextView text = (TextView) convertView
				.findViewById(com.jzg.jzgcarchoose.R.id.addexam_list_item_text);
		// text.setText((CharSequence) getItem(position).);
		text.setText(list.get(position).getName());
		text.setTextColor(list.get(position).getFontColor());
		return convertView;
	}
}
