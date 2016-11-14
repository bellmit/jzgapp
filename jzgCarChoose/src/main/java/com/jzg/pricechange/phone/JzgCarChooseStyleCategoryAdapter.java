package com.jzg.pricechange.phone;

import java.util.List;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jzg.jzgcarchoose.R;

/**
 * ClassName:CarTypeAdapter <br/>
 * Function: TODO 车型年度款式列表适配器. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-5-26 上午11:28:41 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class JzgCarChooseStyleCategoryAdapter extends BaseAdapter
{
	private LayoutInflater inflater;
	private List<JzgCarChooseStyle> list;
	private List<String> groupkey;

	public JzgCarChooseStyleCategoryAdapter(Context context, List<JzgCarChooseStyle> list,
			List<String> groupkey)
	{
		super();
		inflater = LayoutInflater.from(context);
		this.list = list;
		this.groupkey = groupkey;
	}

	@Override
	public int getCount()
	{
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position)
	{
		if(!TextUtils.isEmpty(list.get(position).getNowMsrp())){
			
			return list.get(position).getName();
		}
		return list.get(position).getName();
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public boolean isEnabled(int position)
	{
		if (groupkey.contains(getItem(position)))
		{
			return false;
		}
		return super.isEnabled(position);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View view = convertView;
		if (groupkey.contains(getItem(position)))
		{
			view = inflater.inflate(R.layout.jzgcarchoose_addexam_list_item_tag, null);
		} else
		{
			view = inflater.inflate(R.layout.jzgcarchoose_addexam_list_item, null);
			view.setBackgroundColor(list.get(position).getItemColor());
		}
		ImageView image = (ImageView) view.findViewById(R.id.addexam_list_icon);
		image.setVisibility(View.GONE);
		 TextView text = (TextView) view
				.findViewById(R.id.addexam_list_item_text);
		TextView priceText = (TextView) view
				.findViewById(R.id.addexam_list_item_price);
		TextView priceHint = (TextView) view
				.findViewById(R.id.addexam_list_item_priceHint);
		if (!TextUtils.isEmpty(list.get(position).getNowMsrp()))
		{
			priceText.setVisibility(View.VISIBLE);
			priceHint.setVisibility(View.VISIBLE);
			priceText.setText(list.get(position).getNowMsrp()+"万元");
		}
		text.setText((CharSequence) getItem(position));
		text.setTextColor(list.get(position).getFontColor());
		return view;
	}
}
