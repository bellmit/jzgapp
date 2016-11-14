package com.jzg.pricechange.phone;



import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.graphics.Color;

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
public class JzgCarChooseJsonObjectImpl implements JzgCarChooseJsonObject
{

	private JSONObject jsonObject;

	private int SUCCESS = 100;

	/*@Override
	public HotCarList parserHotCarList(String result)
	{
		HotCarList hotCarList = new HotCarList();
		ArrayList<HotCar> hotList = new ArrayList<HotCar>();
		HotCar hotCar = null;
		try
		{
			jsonObject = new JSONObject(result);
			int status = getresult(jsonObject);
			String msg = jsonObject.getString("msg");
			// 成功
			if (SUCCESS == status)
			{
				JSONArray hotlists = jsonObject.getJSONArray("MakeList");
				// System.out
				// .println("makelists.length() is " + hotlists.length());
				for (int i = 0; i < hotlists.length(); i++)
				{
					hotCar = new HotCar();
					hotCar.setMakeId(hotlists.getJSONObject(i).getInt("MakeId"));
					hotCar.setMakeLogo(hotlists.getJSONObject(i).getString(
							"MakeLogo"));
					hotCar.setMakeName(hotlists.getJSONObject(i).getString(
							"MakeName"));
					hotList.add(hotCar);
				}
			}
			hotCarList.setStatus(status);
			hotCarList.setMsg(msg);
			hotCarList.setHotCars(hotList);
		} catch (JSONException e)
		{
			e.printStackTrace();
		}
		return hotCarList;
	}*/

	@Override
	public JzgCarChooseMakeList parserMakeList(String jsonData) {
		JzgCarChooseMakeList makes = new JzgCarChooseMakeList();
		ArrayList<JzgCarChooseMake> makeList = new ArrayList<JzgCarChooseMake>();
		JzgCarChooseMake make = null;
		try {
			jsonObject = new JSONObject(jsonData);
			boolean status = getResult(jsonObject);
			String msg = jsonObject.getString("msg");
			// 成功
			if (status) {
				JSONArray makelists = jsonObject.getJSONArray("MakeList");
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
			}
			makes.setSuccess(status);
			makes.setMsg(msg);
			makes.setMakes(makeList);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return makes;
	}

	@Override
	public JzgCarChooseModelList parserModelList(String jsonData) {
		JzgCarChooseModelList models = new JzgCarChooseModelList();
		ArrayList<JzgCarChooseModelCategory> modelCategoryList = new ArrayList<JzgCarChooseModelCategory>();
		JzgCarChooseModelCategory modelCategory = null;
		JzgCarChooseModel model = null;
		try {
			JSONObject jsonObject = new JSONObject(jsonData);
			boolean status = getResult(jsonObject);
			String msg = jsonObject.getString("msg");
			if (status) {
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

				models.setModels(modelCategoryList);
			}
			models.setSuccess(status);
			models.setMsg(msg);
		} catch (JSONException e) {

			e.printStackTrace();
		}
		return models;
	}

	@Override
	public JzgCarChooseStyleList parserStyleList(String jsonData) {
		JzgCarChooseStyleList styles = new JzgCarChooseStyleList();
		ArrayList<JzgCarChooseStyleCategory> styleCategoryList = new ArrayList<JzgCarChooseStyleCategory>();
		JzgCarChooseStyleCategory styleCategory = null;
		JzgCarChooseStyle style = null;
		try {
			JSONObject jsonObject = new JSONObject(jsonData);
			boolean status = getResult(jsonObject);
			String msg = jsonObject.getString("msg");
			styles.setSuccess(status);
			styles.setMsg(msg);
			if (status) {
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
						style.setFontColor(Color.rgb(51, 51, 51));
						style.setItemColor(Color.WHITE);
						styleCategory.addItem(style);
					}
					styleCategoryList.add(styleCategory);
				}

				styles.setCarStyles(styleCategoryList);
			}
			styles.setSuccess(status);
			styles.setMsg(msg);
		} catch (JSONException e) {

			e.printStackTrace();

		}
		return styles;
	}

	@Override
	public String generateJson(Serializable object)
	{
		return null;
	}

	private int getresult(JSONObject jsonObject) throws JSONException
	{
		return jsonObject.getInt("status");
	}
	private boolean getResult(JSONObject jsonObject) throws JSONException
	{
		return jsonObject.getBoolean("success");
	}
	
	
}
