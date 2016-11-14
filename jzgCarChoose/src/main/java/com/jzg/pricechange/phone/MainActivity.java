package com.jzg.pricechange.phone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jzg.jzgcarchoose.R;

public class MainActivity extends Activity {

	TextView text_show;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		 text_show = (TextView)findViewById(R.id.text_show);
	}
	
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		switch (requestCode) {
		case JzgCarChooseConstant.Valuation_QUERYCAR_MSG:
			if (data != null)
			{
				JzgCarChooseStyle style = (JzgCarChooseStyle) data.getSerializableExtra("mQueryCarStyle");
				if (style != null)
				{
					text_show.setText(style.getFullName());
				}
				
			}
			break;
		
		default:
			break;
		}

	};
	
	
	
	
	public void choosecar(View v){
		Intent intent = new Intent(MainActivity.this, CarReleaseIndexActivity.class);
		startActivityForResult(intent, JzgCarChooseConstant.Valuation_QUERYCAR_MSG);
	}

}
