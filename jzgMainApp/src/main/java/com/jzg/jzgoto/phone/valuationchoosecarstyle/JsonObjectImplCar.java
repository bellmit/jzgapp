/**
 * Project Name:JZGPingGuShi
 * File Name:JsonObjectImpl.java
 * Package Name:com.gc.jzgpinggushi.json
 * Date:2014-9-1上午10:52:01
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.jzg.jzgoto.phone.valuationchoosecarstyle;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.widget.Toast;

import com.jzg.pricechange.phone.JzgCarChooseMake;
import com.jzg.pricechange.phone.JzgCarChooseMakeList;
import com.jzg.pricechange.phone.JzgCarChooseModel;
import com.jzg.pricechange.phone.JzgCarChooseModelCategory;
import com.jzg.pricechange.phone.JzgCarChooseModelList;
import com.jzg.pricechange.phone.JzgCarChooseStyle;
import com.jzg.pricechange.phone.JzgCarChooseStyleCategory;
import com.jzg.pricechange.phone.JzgCarChooseStyleList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * ClassName:JsonObjectImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-9-1 上午10:52:01 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
@SuppressLint("ResourceAsColor")
public class JsonObjectImplCar
{

	private JSONObject jsonObject;

	public static  int SUCCESS = 100;

	public static boolean isSuccess(Context context, String resu){
		boolean isSuccess = false;
		try {
			JSONObject jsonObject = new JSONObject(resu);
			int status = jsonObject.getInt("status");
			if(status == SUCCESS){
				isSuccess = true;
			}else{
				Toast.makeText(context, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
				isSuccess = false;
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isSuccess = false;
		}
		
		return isSuccess;
	}
	
	
	
	public JzgCarChooseMakeList parserMakeList(String jsonData) {
		JzgCarChooseMakeList makes = new JzgCarChooseMakeList();
		ArrayList<JzgCarChooseMake> makeList = new ArrayList<JzgCarChooseMake>();
		JzgCarChooseMake make = null;
		try {
			jsonObject = new JSONObject(jsonData);
			
				JSONArray makelists = jsonObject.getJSONArray("MakeListNew");
				for (int i = 0; i < makelists.length(); i++) {
					make = new JzgCarChooseMake();
					make.setMakeId(makelists.getJSONObject(i).getInt("MakeId"));
					make.setMakeLogo(makelists.getJSONObject(i).getString(
							"MakeLogo"));
					make.setGroupName(makelists.getJSONObject(i).getString(
							"GroupName"));
					make.setMakeName(makelists.getJSONObject(i).getString(
							"MakeName"));
					make.setFontColor(Color.rgb(51, 51, 51));
					make.setItemColor(Color.WHITE);
					makeList.add(make);
				}
			makes.setMakes(makeList);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return makes;
	}

	public JzgCarChooseModelList parserModelList(String jsonData) {
		JzgCarChooseModelList models = new JzgCarChooseModelList();
		ArrayList<JzgCarChooseModelCategory> modelCategoryList = new ArrayList<JzgCarChooseModelCategory>();
		JzgCarChooseModelCategory modelCategory = null;
		JzgCarChooseModel model = null;
		try {
			JSONObject jsonObject = new JSONObject(jsonData);
				JSONArray manufacturerList = jsonObject
						.getJSONArray("ManufacturerList");
				for (int i = 0; i < manufacturerList.length(); i++) {
					JSONObject object = manufacturerList.getJSONObject(i);
					modelCategory = new JzgCarChooseModelCategory(
							object.getString("ManufacturerName"));
					
					JSONArray modellists = object.getJSONArray("ModelList");
					for (int j = 0; j < modellists.length(); j++) {
						JSONObject modelObj = modellists.getJSONObject(j);
						model = new JzgCarChooseModel();
						model.setId(modelObj.getInt("Id"));
						model.setName(modelObj.getString("Name"));
						model.setModelimgpath(modelObj.getString("modelimgpath"));
						model.setFontColor(Color.rgb(51, 51, 51));
						model.setItemColor(Color.WHITE);
						modelCategory.addItem(model);
					}
					modelCategoryList.add(modelCategory);
				}

				models.setSuccess(true);
				models.setModels(modelCategoryList);
		} catch (JSONException e) {

			e.printStackTrace();
		}
		return models;
	}

	public JzgCarChooseStyleList parserStyleList(String jsonData) {
		JzgCarChooseStyleList styles = new JzgCarChooseStyleList();
		ArrayList<JzgCarChooseStyleCategory> styleCategoryList = new ArrayList<JzgCarChooseStyleCategory>();
		JzgCarChooseStyleCategory styleCategory = null;
		JzgCarChooseStyle style = null;
		try {
			JSONObject jsonObject = new JSONObject(jsonData);
			styles.setSuccess(true);
				JSONArray yearGroupList = jsonObject.optJSONArray("YearGroupList");
					//	.getJSONArray("YearGroupList");
				if(yearGroupList==null){
					return styles;
				}
				for (int i = 0; i < yearGroupList.length(); i++) {
					JSONObject object = yearGroupList.getJSONObject(i);
					styleCategory = new JzgCarChooseStyleCategory(object.getString("Year"));
					JSONArray modellists = object.getJSONArray("StyleList");
					for (int j = 0; j < modellists.length(); j++) {
						JSONObject modelObj = modellists.getJSONObject(j);
						style = new JzgCarChooseStyle();
						style.setId(modelObj.getInt("Id"));
						style.setName(modelObj.getString("Name"));
						style.setYear(modelObj.getInt("Year"));
						style.setNowMsrp(modelObj.getString("NowMsrp"));
						style.setFullName(modelObj.getString("FullName"));
//						style.setMinYEAR(modelObj.getString("MinYEAR"));
//						style.setMaxYEAR(modelObj.getString("MaxYEAR"));
						
						style.setFontColor(Color.rgb(51, 51, 51));
						style.setItemColor(Color.WHITE);
						styleCategory.addItem(style);
					}
					styleCategoryList.add(styleCategory);
				}

				styles.setCarStyles(styleCategoryList);
		} catch (JSONException e) {

			e.printStackTrace();

		}
		return styles;
	}

	
}
