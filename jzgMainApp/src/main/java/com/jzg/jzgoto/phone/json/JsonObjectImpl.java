/**
 * Project Name:JZGPingGuShi
 * File Name:JsonObjectImpl.java
 * Package Name:com.gc.jzgpinggushi.json
 * Date:2014-9-1上午10:52:01
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.jzg.jzgoto.phone.json;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.Context;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.BzlDetail;
import com.jzg.jzgoto.phone.models.CarLifeNewsList;
import com.jzg.jzgoto.phone.models.CarLifeQuestionDetailList;
import com.jzg.jzgoto.phone.models.CarLifeQuestionList;
import com.jzg.jzgoto.phone.models.City;
import com.jzg.jzgoto.phone.models.CityList;
import com.jzg.jzgoto.phone.models.MyHistoryList;
import com.jzg.jzgoto.phone.models.Province;
import com.jzg.jzgoto.phone.models.ProvinceList;
import com.jzg.jzgoto.phone.models.ValuationDetail;
import com.jzg.jzgoto.phone.models.ValuationList;
import com.jzg.jzgoto.phone.tools.ShowDialogTool;

/**
 * ClassName:JsonObjectImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-9-1 上午10:52:01 <br/>
 * 
 * @author 郑有权
 * @version
 * @since JDK 1.6
 * @see
 */
@SuppressLint("ResourceAsColor")
public class JsonObjectImpl 
{

	private JSONObject jsonObject;
	
	private int SUCCESS = 100;
	
	public static boolean isSuccess(Context context, String resu){
		boolean isSuccess = false;
		try {
			JSONObject jsonObject = new JSONObject(resu);
			String status = jsonObject.getString("status");
			if("100".equals(status)){
				isSuccess = true;
			}else{
//				Toast.makeText(context, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
				ShowDialogTool.showCenterToast(context, jsonObject.getString("msg"));
				isSuccess = false;
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isSuccess = false;
		}
		
		return isSuccess;
	}
	
	
	public ProvinceList parserProvinceList(String jsonData)
	{

		ProvinceList provinces = new ProvinceList();
		ArrayList<Province> provinceList = new ArrayList<Province>();
		Province province = null;
		try
		{
			jsonObject = new JSONObject(jsonData);
			int status = getresult(jsonObject);
			String msg = jsonObject.getString("msg");
			// 成功
			if (SUCCESS == status)
			{
				JSONArray provincelists = jsonObject.getJSONArray("content");
				// System.out.println("provincelists.length() is "
				// + provincelists.length());
				for (int i = 0; i < provincelists.length(); i++)
				{
					JSONObject object = provincelists.getJSONObject(i);
					province = new Province();
					province.setId(object.getInt("Id"));
					province.setName(object.getString("Name"));
					provinceList.add(province);
				}
			}
			provinces.setStatus(status);
			provinces.setMsg(msg);
			provinces.setProvinces(provinceList);
		} catch (JSONException e)
		{
			e.printStackTrace();
		}
		return provinces;
	}

	public CityList parserCityList(String jsonData)
	{

		CityList citys = new CityList();
		ArrayList<City> cityList = new ArrayList<City>();
		City city = null;
		try
		{
			jsonObject = new JSONObject(jsonData);
			int status = getresult(jsonObject);
			String msg = jsonObject.getString("msg");
			// 成功
			if (SUCCESS == status)
			{
				JSONArray citylists = jsonObject.getJSONArray("content");
				// System.out.println("provincelists.length() is "
				// + citylists.length());
				for (int i = 0; i < citylists.length(); i++)
				{
					JSONObject object = citylists.getJSONObject(i);
					city = new City();
					city.setId(object.getInt("Id"));
					city.setName(object.getString("Name"));
					cityList.add(city);
				}
			}
			citys.setStatus(status);
			citys.setMsg(msg);
			citys.setCitys(cityList);
		} catch (JSONException e)
		{
			e.printStackTrace();
		}
		return citys;
	}
	
	private int getresult(JSONObject jsonObject) throws JSONException
	{
		return jsonObject.getInt("status");
	}
	
	//历史记录
	public static MyHistoryList parserMyHistoryList(String result)
	{
		MyHistoryList myHistoryList = null; 
		Gson gson = new Gson();
		myHistoryList = (MyHistoryList)gson.fromJson(result, MyHistoryList.class);
		return myHistoryList;
	}
	public static CarLifeNewsList parserCarLifeNewsList(String result)
	{
		CarLifeNewsList carLifeNewsList = null; 
		Gson gson = new Gson();
		carLifeNewsList = (CarLifeNewsList)gson.fromJson(result, CarLifeNewsList.class);
		return carLifeNewsList;
	}
	public static CarLifeQuestionList parserCarLifeQuestionList(String result)
	{
		CarLifeQuestionList carLifeQuestionList = null; 
		Gson gson = new Gson();
		carLifeQuestionList = (CarLifeQuestionList)gson.fromJson(result, CarLifeQuestionList.class);
		return carLifeQuestionList;
	}
	public static CarLifeQuestionDetailList parserCarLifeQuestionDetailList(String result)
	{
		CarLifeQuestionDetailList carLifeQuestionDetailList = null; 
		Gson gson = new Gson();
		carLifeQuestionDetailList = (CarLifeQuestionDetailList)gson.fromJson(result, CarLifeQuestionDetailList.class);
		return carLifeQuestionDetailList;
	}
	public static BzlDetail parserBzlDetail(String result)
	{
		BzlDetail bzlDetail = null; 
		Gson gson = new Gson();
		bzlDetail = (BzlDetail)gson.fromJson(result, BzlDetail.class);
		return bzlDetail;
	}
	public static ValuationDetail parserValuationDetail(String result)
	{
		ValuationDetail valuationDetail = null; 
		Gson gson = new Gson();
		valuationDetail = (ValuationDetail)gson.fromJson(result, ValuationDetail.class);
		return valuationDetail;
	}
	public static ValuationList parserValuationList(String result)
	{
		ValuationList valuationList = null; 
		Gson gson = new Gson();
		valuationList = (ValuationList)gson.fromJson(result, ValuationList.class);
		return valuationList;
	}
	
	
}
