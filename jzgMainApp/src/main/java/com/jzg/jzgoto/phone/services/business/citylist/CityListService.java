package com.jzg.jzgoto.phone.services.business.citylist;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.jzg.jzgoto.phone.activity.choosecity.ChooseCity;
import com.jzg.jzgoto.phone.activity.choosecity.ChooseCity.CityListEntity;
import com.jzg.jzgoto.phone.models.business.db.city.CityListBean;
import com.jzg.jzgoto.phone.services.contentprovider.GetDataFromDBToolBase;
/**
 * @Description: 城市列表控制
 * @Package com.jzg.jzgoto.phone.services.business.citylist CityListService.java
 * @author gf
 * @date 2016-6-13 上午11:44:55
 */
public class CityListService extends GetDataFromDBToolBase {
	private static CityListService mCityListService = new CityListService();
	public static CityListService getInstance(Context context){
		mCityListService.initDb(context);
		return mCityListService;
	}
	private CityListService(){}
	/**
	 * 插入城市列表
	 * @param obj
	 */
	public synchronized boolean insertCityList(ChooseCity city){
		if(mDBOpenHelper == null)return false;
		if(city == null || city.getCityList() == null || city.getCityList().size() == 0){
			return false;
		}
		mDBOpenHelper.toClearTable(CityListBean.getInstance());
		mDBOpenHelper.toCheckTable(CityListBean.getInstance());
		SQLiteDatabase db = mDBOpenHelper.getWritableDatabase();
		final List<CityListEntity> list = city.getCityList();
		final String lastUp = city.getLastUpdateTime();
		try {
			for(int i = 0;i < list.size();i++){
				CityListEntity item = list.get(i);
				ContentValues cv = new ContentValues();
				cv.put(CityListBean.getInstance().getKEY_IS_HOT_CITY(), item.getIsHotCity());
				cv.put(CityListBean.getInstance().getKEY_ORDER_INDEX(), item.getOrderIndex());
				cv.put(CityListBean.getInstance().getKEY_PROV_ID(), item.getProvID());
				cv.put(CityListBean.getInstance().getKEY_CITY_ID(), item.getCityID());
				cv.put(CityListBean.getInstance().getKEY_NAME(), item.getName());
				cv.put(CityListBean.getInstance().getKEY_GROUP_NAME(), item.getGroupName());
				cv.put(CityListBean.getInstance().getKEY_STATUS(), item.getStatus());
				cv.put(CityListBean.getInstance().getKEY_LAST_UPDATE_TIME(), lastUp);
				db.insert(CityListBean.getInstance().getTableName(), null, cv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			db.close();
			return false;
		}
		db.close();
		
		return true;
	}
	/**
	 * 更新城市列表
	 * @param city
	 * @return
	 */
	public synchronized boolean updateListList(ChooseCity city){
		if(mDBOpenHelper == null)return false;
		if(city == null || city.getCityList() == null || city.getCityList().size() == 0){
			return false;
		}
		mDBOpenHelper.toCheckTable(CityListBean.getInstance());
		SQLiteDatabase db = mDBOpenHelper.getWritableDatabase();
		final List<CityListEntity> list = city.getCityList();
		final String lastUp = city.getLastUpdateTime();
		try {
			for(int i = 0;i < list.size();i++){
				CityListEntity item = list.get(i);
				ContentValues cv = new ContentValues();
				cv.put(CityListBean.getInstance().getKEY_IS_HOT_CITY(), item.getIsHotCity());
				cv.put(CityListBean.getInstance().getKEY_ORDER_INDEX(), item.getOrderIndex());
				cv.put(CityListBean.getInstance().getKEY_PROV_ID(), item.getProvID());
				cv.put(CityListBean.getInstance().getKEY_CITY_ID(), item.getCityID());
				cv.put(CityListBean.getInstance().getKEY_NAME(), item.getName());
				cv.put(CityListBean.getInstance().getKEY_GROUP_NAME(), item.getGroupName());
				cv.put(CityListBean.getInstance().getKEY_STATUS(), item.getStatus());
				cv.put(CityListBean.getInstance().getKEY_LAST_UPDATE_TIME(), lastUp);
				
				db.update(CityListBean.getInstance().getTableName(), cv, 
						CityListBean.getInstance().getKEY_CITY_ID() + "=?", new String[]{item.getCityID()});
			}
		} catch (SQLException e) {
			e.printStackTrace();
			db.close();
			return false;
		}
		db.close();
		
		return true;
	}
	/**
	 * 获取城市列表
	 * @return
	 */
	public synchronized ChooseCity queryListList(){
		if(mDBOpenHelper == null)return null;
		mDBOpenHelper.toCheckTable(CityListBean.getInstance());
		SQLiteDatabase db = mDBOpenHelper.getReadableDatabase();
		Cursor cursor = db.query(CityListBean.getInstance().getTableName(), null, null, null, null, null, null);
		
		ChooseCity city = new ChooseCity();
		final List<CityListEntity> list = new ArrayList<ChooseCity.CityListEntity>();
		city.setCityList(list);
		
		cursor.moveToFirst();
		if(cursor.getCount() != 0){
			city.setStatus(100);
			do{
				CityListEntity item = new CityListEntity();
				item.setIsHotCity(cursor.getString(cursor.getColumnIndex(CityListBean.getInstance().getKEY_IS_HOT_CITY())));
				item.setOrderIndex(cursor.getString(cursor.getColumnIndex(CityListBean.getInstance().getKEY_ORDER_INDEX())));
				item.setProvID(cursor.getString(cursor.getColumnIndex(CityListBean.getInstance().getKEY_PROV_ID())));
				item.setCityID(cursor.getString(cursor.getColumnIndex(CityListBean.getInstance().getKEY_CITY_ID())));
				item.setName(cursor.getString(cursor.getColumnIndex(CityListBean.getInstance().getKEY_NAME())));
				item.setGroupName(cursor.getString(cursor.getColumnIndex(CityListBean.getInstance().getKEY_GROUP_NAME())));
				item.setStatus(cursor.getString(cursor.getColumnIndex(CityListBean.getInstance().getKEY_STATUS())));
				city.setLastUpdateTime(cursor.getString(cursor.getColumnIndex(CityListBean.getInstance().getKEY_LAST_UPDATE_TIME())));
				list.add(item);
			}while(cursor.moveToNext());
		} else {
			city.setStatus(500);
		}
		cursor.close();
		db.close();
		return city;
	}
	/**
	 * 清空城市列表
	 * @return
	 */
	public synchronized boolean clearCityList(){
		if(mDBOpenHelper == null)return false;
		mDBOpenHelper.toClearTable(CityListBean.getInstance());
		return true;
	}
}
