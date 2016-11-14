package com.jzg.jzgoto.phone.activity.buy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.jzg.jzgoto.phone.R;
import com.jzg.jzgoto.phone.adapter.buy.BuyCarChooseStyleAdapter;

public class BuyCarStyleChooseActivity extends Activity implements OnClickListener{
//	1	微型车
//	2	小型车
//	3	紧凑型车
//	4	中型车
//	5	中大型车
//	6	大型车
//	7	小型SUV
//	8	紧凑型SUV
//	9	中型SUV
//	10	中大型SUV
//	11	全尺寸SUV
//	12	入门级跑车
//	13	中级跑车
//	14	超级跑车
//	15	小型MPV
//	16	大型MPV
//  51  SUV(小型SUV,紧凑型SUV,中型SUV,中大型SUV,全尺寸SUV)
//  52  跑车(入门级跑车,中级跑车,超级跑车)
//  53  MVP(小型MPV,大型MPV)
	private String[] mStyleStr = new String[]{"微型车","小型车","紧凑型车","中型车","中大型车","大型车","小型SUV","紧凑型SUV","中型SUV","中大型SUV","全尺寸SUV","入门级跑车","中级跑车","超级跑车","小型MVP","大型MVP"};
	private TextView mChooseColor;
	private ListView mColorListView;
	public static final String CHOOSE_CAR_STYLE ="choose_car_style";
	public static final String CHOOSE_CAR_STYLE_ID ="choose_car_style_id";
	public static final int CAR_STYLE_CODE = 1002;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buycar_style_choose);
		init();
	}
	public void init() {
		mChooseColor = (TextView) findViewById(R.id.car_color_choose_color);
		mChooseColor.setText("不限车型");
		mChooseColor.setVisibility(View.VISIBLE);
		mChooseColor.setOnClickListener(this);
		mColorListView = (ListView) findViewById(R.id.car_color_choose_listview);
		mColorListView.setAdapter(new BuyCarChooseStyleAdapter(mStyleStr,null,this));
		mColorListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent in = new Intent();
				in.putExtra(CHOOSE_CAR_STYLE, mStyleStr[position]);
				in.putExtra(CHOOSE_CAR_STYLE_ID, ""+(position+1));
				setResult(CAR_STYLE_CODE, in);
				finish();
			}
		});
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.car_color_choose_color:
			Intent in = new Intent();
			in.putExtra(CHOOSE_CAR_STYLE, "不限");
			in.putExtra(CHOOSE_CAR_STYLE_ID, ""+0);
			setResult(CAR_STYLE_CODE, in);
			finish();
			break;
		default:
			break;
		}
	}
}
