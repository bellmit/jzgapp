package com.jzg.jzgoto.phone.adapter.choosecity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.activity.choosecity.ChooseCity.CityListEntity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChooseCityAdapter extends BaseAdapter{

	private LayoutInflater inflater;
	
	private List<Map<String, Object>> list;
	
	Map<String, Integer> alphaIndexer;
	
	public ChooseCityAdapter(Context context, List<Map<String, Object>> list) {
		this.inflater = LayoutInflater.from(context);
		this.list = list;
		alphaIndexer = new HashMap<String, Integer>();
//		sections = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			// 当前汉语拼音首字母
			String currentStr = list.get(i).get("Sort").toString();
			// 上一个汉语拼音首字母，如果不存在为“ ”
			String previewStr = (i - 1) >= 0 ? list.get(i - 1).get("Sort")
					.toString() : " ";
			if (!previewStr.equals(currentStr)) {
				String name = list.get(i).get("Sort").toString();
				alphaIndexer.put(name, i);
//				System.out.println("name is " + name + ", position is " + i);
//				sections[i] = name;
			}
		}
//		index = (HashMap<String, Integer>) alphaIndexer;

	}
	
	public int getPosition(String name){
		try {
			return alphaIndexer.get(name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println("没有对应字母");
			return -1;
		}
	}
	
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_choose_city_layout, null);
			holder = new ViewHolder();
			holder.name = (TextView) convertView
					.findViewById(R.id.city_name);
			holder.alpha = (TextView) convertView.findViewById(R.id.alpha);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.name.setText(((CityListEntity)(list.get(position).get("name"))).getName().toString());
		
		String currentStr = list.get(position).get("Sort").toString();
		String previewStr = (position - 1) >= 0 ? list.get(position - 1)
				.get("Sort").toString() : " ";
		if (!previewStr.equals(currentStr)) {
			holder.alpha.setVisibility(View.VISIBLE);
			if("热".equals(list.get(position).get("Sort").toString())){
				holder.alpha.setText("热门城市");
			}else{
				holder.alpha.setText(currentStr);
			}
			
		} else {
			holder.alpha.setVisibility(View.GONE);
		}
		
		
		
		return convertView;
	}

	private class ViewHolder {
		TextView name;
		TextView alpha;
	}

}
