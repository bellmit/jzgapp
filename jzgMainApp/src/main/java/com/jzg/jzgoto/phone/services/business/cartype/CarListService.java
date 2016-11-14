package com.jzg.jzgoto.phone.services.business.cartype;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.jzg.jzgoto.phone.models.business.car.CarMakeBean;
import com.jzg.jzgoto.phone.models.business.car.CarMakeResultModels;
import com.jzg.jzgoto.phone.models.business.car.CarModelBean;
import com.jzg.jzgoto.phone.models.business.car.CarModelResultModels;
import com.jzg.jzgoto.phone.models.business.car.CarStyleBean;
import com.jzg.jzgoto.phone.models.business.car.CarStyleResultModels;
import com.jzg.jzgoto.phone.models.business.db.brand.BrandBean;
import com.jzg.jzgoto.phone.models.business.db.brand.BrandValuationBean;
import com.jzg.jzgoto.phone.models.business.db.brand.CarLastUpdateTimeBean;
import com.jzg.jzgoto.phone.models.business.db.brand.CarStyleValuationBean;
import com.jzg.jzgoto.phone.models.business.db.brand.ChexiBean;
import com.jzg.jzgoto.phone.models.business.db.brand.ChexiValuationBean;
import com.jzg.jzgoto.phone.models.business.db.brand.ChexingModelBean;
import com.jzg.jzgoto.phone.models.common.BaseDBModel;
import com.jzg.jzgoto.phone.services.contentprovider.GetDataFromDBToolBase;
/**
 * @Description: 获取车型列表等
 * @Package com.jzg.jzgoto.phone.services.business.cartype CarListService.java
 * @author gf
 * @date 2016-6-13 上午11:47:52
 */
public class CarListService extends GetDataFromDBToolBase implements GetCarListInterface{
	protected CarListService(){}
	/** 插入品牌 **/
	public synchronized boolean insertCarBrand(CarMakeResultModels models){
		if(null == mSelfDbOpenHelper)return false;
		if(null == models || null == models.getMakeList() ||models.getMakeList().size() == 0){
			return false;
		}
		mSelfDbOpenHelper.toClearTable(BrandBean.getInstance());
		mSelfDbOpenHelper.toCheckTable(BrandBean.getInstance());
		SQLiteDatabase db = mSelfDbOpenHelper.getWritableDatabase();
		boolean bl = false;
		try {
			List<CarMakeBean> makeList = models.getMakeList();
			for(CarMakeBean item:makeList){
				ContentValues cv = new ContentValues();
				cv.put(BrandBean.getInstance().getKEY_GROUP_NAME(), item.getGroupName());
				cv.put(BrandBean.getInstance().getKEY_MAKE_ID(), item.getMakeId());
				cv.put(BrandBean.getInstance().getKEY_MAKE_LOGO(), item.getMakeLogo());
				cv.put(BrandBean.getInstance().getKEY_MAKE_NAME(), item.getMakeName());
				cv.put(BrandBean.getInstance().getKEY_STATUS(), item.getStatus());
				cv.put(BrandBean.getInstance().getKEY_IS_NEW_CAR(), item.getIsNewCar());
				
				db.insert(BrandBean.getInstance().getTableName(), null, cv);
				
			}
			ContentValues last = new ContentValues();
			last.put(CarLastUpdateTimeBean.getInstance().getKEY_TIME(), models.getLastUpData());
			last.put(CarLastUpdateTimeBean.getInstance().getKEY_TYPE(), BrandBean.getInstance().getTableName());
			db.insert(CarLastUpdateTimeBean.getInstance().getTableName(), null, last);
			
			bl = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.close();
		}
		return bl;
	}
	@Override
	public boolean addCarBrand(CarMakeResultModels models) {
		if(null == mSelfDbOpenHelper)return false;
		if(null == models || null == models.getMakeList() ||models.getMakeList().size() == 0){
			return false;
		}
		mSelfDbOpenHelper.toCheckTable(BrandBean.getInstance());
		SQLiteDatabase db = mSelfDbOpenHelper.getWritableDatabase();
		boolean bl = false;
		try {
			List<CarMakeBean> makeList = models.getMakeList();
			for(CarMakeBean item:makeList){
				ContentValues cv = new ContentValues();
				cv.put(BrandBean.getInstance().getKEY_GROUP_NAME(), item.getGroupName());
				cv.put(BrandBean.getInstance().getKEY_MAKE_ID(), item.getMakeId());
				cv.put(BrandBean.getInstance().getKEY_MAKE_LOGO(), item.getMakeLogo());
				cv.put(BrandBean.getInstance().getKEY_MAKE_NAME(), item.getMakeName());
				cv.put(BrandBean.getInstance().getKEY_STATUS(), item.getStatus());
				cv.put(BrandBean.getInstance().getKEY_IS_NEW_CAR(), item.getIsNewCar());
				db.insert(BrandBean.getInstance().getTableName(), null, cv);
			}
			ContentValues last = new ContentValues();
			last.put(CarLastUpdateTimeBean.getInstance().getKEY_TIME(), models.getLastUpData());
			last.put(CarLastUpdateTimeBean.getInstance().getKEY_TYPE(), BrandBean.getInstance().getTableName());
			db.insert(CarLastUpdateTimeBean.getInstance().getTableName(), null, last);
			
			bl = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.close();
		}
		return bl;
	}
	@Override
	public boolean delCarBrand(String makeId) {
		if(mSelfDbOpenHelper == null)return false;
		if(TextUtils.isEmpty(makeId))return false;
		mSelfDbOpenHelper.toCheckTable(BrandBean.getInstance());
		SQLiteDatabase db = mSelfDbOpenHelper.getWritableDatabase();
		db.delete(BrandBean.getInstance().getTableName(), 
				BrandBean.getInstance().getKEY_MAKE_ID() + "=?", new String[]{makeId});
		return true;
	}
	/**
	 * 更新品牌
	 * @param models
	 * @return
	 */
	public synchronized boolean updateCarBrand(CarMakeResultModels models){
		if(null == mSelfDbOpenHelper)return false;
		if(null == models || null == models.getMakeList() ||models.getMakeList().size() == 0){
			return false;
		}
		mSelfDbOpenHelper.toCheckTable(BrandBean.getInstance());
		SQLiteDatabase db = mSelfDbOpenHelper.getWritableDatabase();
		boolean bl = false;
		try {
			List<CarMakeBean> makeList = models.getMakeList();
			for(CarMakeBean item:makeList){
				ContentValues cv = new ContentValues();
				cv.put(BrandBean.getInstance().getKEY_GROUP_NAME(), item.getGroupName());
				cv.put(BrandBean.getInstance().getKEY_MAKE_ID(), item.getMakeId());
				cv.put(BrandBean.getInstance().getKEY_MAKE_LOGO(), item.getMakeLogo());
				cv.put(BrandBean.getInstance().getKEY_MAKE_NAME(), item.getMakeName());
				cv.put(BrandBean.getInstance().getKEY_STATUS(), item.getStatus());
				cv.put(BrandBean.getInstance().getKEY_IS_NEW_CAR(), item.getIsNewCar());
				
				db.update(BrandBean.getInstance().getTableName(), cv, 
						BrandBean.getInstance().getKEY_MAKE_ID() + "=?", new String[]{item.getMakeId()});
			}
			
			ContentValues last = new ContentValues();
			last.put(CarLastUpdateTimeBean.getInstance().getKEY_TIME(), models.getLastUpData());
			db.update(CarLastUpdateTimeBean.getInstance().getTableName(), last, 
					CarLastUpdateTimeBean.getInstance().getKEY_TYPE() + "=?", new String[]{BrandBean.getInstance().getTableName()});
			
			bl = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.close();
		}
		return bl;
	}
	/**
	 * 查询品牌列表
	 * @return
	 */
	public synchronized CarMakeResultModels queryCarBrand(final String isNewCar){
		if(mSelfDbOpenHelper == null)return null;
		mSelfDbOpenHelper.toCheckTable(BrandBean.getInstance());
		SQLiteDatabase db = mSelfDbOpenHelper.getReadableDatabase();
		Cursor cursor;
		if(!TextUtils.isEmpty(isNewCar) && "1".equals(isNewCar)){
			cursor = db.query(BrandBean.getInstance().getTableName(), null, 
					BrandBean.getInstance().getKEY_IS_NEW_CAR() + "=?", new String[]{isNewCar}, null, null, null);
		} else {
			cursor = db.query(BrandBean.getInstance().getTableName(), null, 
					null, null, null, null, null);
		}
		
		CarMakeResultModels models = new CarMakeResultModels();
		final List<CarMakeBean> list = new ArrayList<CarMakeBean>();
		models.setMakeList(list);
		models.setLastUpData(getLastUpdate(BrandBean.getInstance()));
		cursor.moveToFirst();
		if(cursor.getCount() != 0){
			models.setStatus(100);
			do{
				CarMakeBean item = new CarMakeBean();
				item.setGroupName(cursor.getString(cursor.getColumnIndex(BrandBean.getInstance().getKEY_GROUP_NAME())));
				item.setMakeId(cursor.getString(cursor.getColumnIndex(BrandBean.getInstance().getKEY_MAKE_ID())));
				item.setMakeLogo(cursor.getString(cursor.getColumnIndex(BrandBean.getInstance().getKEY_MAKE_LOGO())));
				item.setMakeName(cursor.getString(cursor.getColumnIndex(BrandBean.getInstance().getKEY_MAKE_NAME())));
				item.setIsNewCar(cursor.getString(cursor.getColumnIndex(BrandBean.getInstance().getKEY_IS_NEW_CAR())));
				item.setStatus(cursor.getString(cursor.getColumnIndex(BrandBean.getInstance().getKEY_STATUS())));
				list.add(item);
			}while(cursor.moveToNext());
		} else {
			models.setStatus(500);
		}
		cursor.close();
		db.close();
		return models;
	}
	
	/** 插入车系 **/
	public synchronized boolean insertCarModel(CarModelResultModels models){
		if(null == mSelfDbOpenHelper)return false;
		if(null == models || null == models.getModelList() ||models.getModelList().size() == 0){
			return false;
		}
		mSelfDbOpenHelper.toClearTable(ChexiBean.getInstance());
		mSelfDbOpenHelper.toCheckTable(ChexiBean.getInstance());
		SQLiteDatabase db = mSelfDbOpenHelper.getWritableDatabase();
		boolean bl = false;
		try {
			List<CarModelBean> modelList = models.getModelList();
			for(CarModelBean item:modelList){
				ContentValues cv = new ContentValues();
				cv.put(ChexiBean.getInstance().getKEY_MAKE_ID(), item.getMakeId());
				cv.put(ChexiBean.getInstance().getKEY_MANUFACTURER_ID(), item.getManufacturerId());
				cv.put(ChexiBean.getInstance().getKEY_MANUFACTURER_NAME(), item.getManufacturerName());
				cv.put(ChexiBean.getInstance().getKEY_CHEXI_ID(), item.getId());
				cv.put(ChexiBean.getInstance().getKEY_CHEXI_NAME(), item.getName());
				cv.put(ChexiBean.getInstance().getKEY_MODEL_IMGPATH(), item.getModelimgpath());
				cv.put(ChexiBean.getInstance().getKEY_STATUS(), item.getStatus());
				cv.put(ChexiBean.getInstance().getKEY_IS_NEW_CAR(), item.getIsNewCar());
				db.insert(ChexiBean.getInstance().getTableName(), null, cv);
			}
			ContentValues last = new ContentValues();
			last.put(CarLastUpdateTimeBean.getInstance().getKEY_TIME(), models.getLastUpData());
			last.put(CarLastUpdateTimeBean.getInstance().getKEY_TYPE(), ChexiBean.getInstance().getTableName());
			db.insert(CarLastUpdateTimeBean.getInstance().getTableName(), null, last);
			
			bl = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.close();
		}
		return bl;
	}

	@Override
	public boolean addCarModel(CarModelResultModels models) {
		if(null == mSelfDbOpenHelper)return false;
		if(null == models || null == models.getModelList() ||models.getModelList().size() == 0){
			return false;
		}
		mSelfDbOpenHelper.toCheckTable(ChexiBean.getInstance());
		SQLiteDatabase db = mSelfDbOpenHelper.getWritableDatabase();
		boolean bl = false;
		try {
			List<CarModelBean> modelList = models.getModelList();
			for(CarModelBean item:modelList){
				ContentValues cv = new ContentValues();
				cv.put(ChexiBean.getInstance().getKEY_MAKE_ID(), item.getMakeId());
				cv.put(ChexiBean.getInstance().getKEY_MANUFACTURER_ID(), item.getManufacturerId());
				cv.put(ChexiBean.getInstance().getKEY_MANUFACTURER_NAME(), item.getManufacturerName());
				cv.put(ChexiBean.getInstance().getKEY_CHEXI_ID(), item.getId());
				cv.put(ChexiBean.getInstance().getKEY_CHEXI_NAME(), item.getName());
				cv.put(ChexiBean.getInstance().getKEY_MODEL_IMGPATH(), item.getModelimgpath());
				cv.put(ChexiBean.getInstance().getKEY_STATUS(), item.getStatus());
				cv.put(ChexiBean.getInstance().getKEY_IS_NEW_CAR(), item.getIsNewCar());
				db.insert(ChexiBean.getInstance().getTableName(), null, cv);
			}
			ContentValues last = new ContentValues();
			last.put(CarLastUpdateTimeBean.getInstance().getKEY_TIME(), models.getLastUpData());
			last.put(CarLastUpdateTimeBean.getInstance().getKEY_TYPE(), ChexiBean.getInstance().getTableName());
			db.insert(CarLastUpdateTimeBean.getInstance().getTableName(), null, last);
			
			bl = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.close();
		}
		return bl;
	}
	@Override
	public boolean delCarModel(String modelId) {
		if(mSelfDbOpenHelper == null)return false;
		if(TextUtils.isEmpty(modelId))return false;
		mSelfDbOpenHelper.toCheckTable(ChexiBean.getInstance());
		SQLiteDatabase db = mSelfDbOpenHelper.getWritableDatabase();
		db.delete(ChexiBean.getInstance().getTableName(), 
				ChexiBean.getInstance().getKEY_CHEXI_ID() + "=?", new String[]{modelId});
		return true;
	}
	/**
	 * 更新车系
	 * @param models
	 * @return
	 */
	public synchronized boolean updateCarModel(CarModelResultModels models){
		if(null == mSelfDbOpenHelper)return false;
		if(null == models || null == models.getModelList() ||models.getModelList().size() == 0){
			return false;
		}
		mSelfDbOpenHelper.toCheckTable(ChexiBean.getInstance());
		SQLiteDatabase db = mSelfDbOpenHelper.getWritableDatabase();
		boolean bl = false;
		try {
			List<CarModelBean> modelsList = models.getModelList();
			for(CarModelBean item:modelsList){
				ContentValues cv = new ContentValues();
				cv.put(ChexiBean.getInstance().getKEY_MAKE_ID(), item.getMakeId());
				cv.put(ChexiBean.getInstance().getKEY_MANUFACTURER_ID(), item.getManufacturerId());
				cv.put(ChexiBean.getInstance().getKEY_MANUFACTURER_NAME(), item.getManufacturerName());
				cv.put(ChexiBean.getInstance().getKEY_CHEXI_ID(), item.getId());
				cv.put(ChexiBean.getInstance().getKEY_CHEXI_NAME(), item.getName());
				cv.put(ChexiBean.getInstance().getKEY_MODEL_IMGPATH(), item.getModelimgpath());
				cv.put(ChexiBean.getInstance().getKEY_STATUS(), item.getStatus());
				cv.put(ChexiBean.getInstance().getKEY_IS_NEW_CAR(), item.getIsNewCar());
				
				db.update(ChexiBean.getInstance().getTableName(), cv, 
						ChexiBean.getInstance().getKEY_CHEXI_ID() + "=?", new String[]{item.getId()});
			}
			
			ContentValues last = new ContentValues();
			last.put(CarLastUpdateTimeBean.getInstance().getKEY_TIME(), models.getLastUpData());
			db.update(CarLastUpdateTimeBean.getInstance().getTableName(), last, 
					CarLastUpdateTimeBean.getInstance().getKEY_TYPE() + "=?", new String[]{ChexiBean.getInstance().getTableName()});
			
			bl = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.close();
		}
		return bl;
	}
	/**
	 * 查询车系列表
	 * @return
	 */
	public synchronized CarModelResultModels queryCarModel(final String makeId,final String isNewCar){
		if(mSelfDbOpenHelper == null)return null;
		mSelfDbOpenHelper.toCheckTable(ChexiBean.getInstance());
		SQLiteDatabase db = mSelfDbOpenHelper.getReadableDatabase();
		Cursor cursor = null;
		if(!TextUtils.isEmpty(isNewCar) && "1".equals(isNewCar)){
			cursor = db.query(ChexiBean.getInstance().getTableName(), null, 
					ChexiBean.getInstance().getKEY_MAKE_ID() + "=?," + 
					ChexiBean.getInstance().getKEY_IS_NEW_CAR() + "=?", 
					new String[]{makeId,isNewCar}, null, null,
					ChexiBean.getInstance().getKEY_MANUFACTURER_ID(), null);
		} else {
			cursor = db.query(ChexiBean.getInstance().getTableName(), null, 
					ChexiBean.getInstance().getKEY_MAKE_ID() + "=?",
					new String[]{makeId}, null, null,
					ChexiBean.getInstance().getKEY_MANUFACTURER_ID(), null);
		}
		
		CarModelResultModels models = new CarModelResultModels();
		final List<CarModelBean> list = new ArrayList<CarModelBean>();
		models.setModelList(list);
		models.setLastUpData(getLastUpdate(ChexiBean.getInstance()));
		cursor.moveToFirst();
		if(cursor.getCount() != 0){
			models.setStatus(100);
			do{
				CarModelBean item = new CarModelBean();
				item.setMakeId(cursor.getString(cursor.getColumnIndex(ChexiBean.getInstance().getKEY_MAKE_ID())));
				item.setManufacturerId(cursor.getString(cursor.getColumnIndex(ChexiBean.getInstance().getKEY_MANUFACTURER_ID())));
				item.setManufacturerName(cursor.getString(cursor.getColumnIndex(ChexiBean.getInstance().getKEY_MANUFACTURER_NAME())));
				item.setId(cursor.getString(cursor.getColumnIndex(ChexiBean.getInstance().getKEY_CHEXI_ID())));
				item.setName(cursor.getString(cursor.getColumnIndex(ChexiBean.getInstance().getKEY_CHEXI_NAME())));
				item.setModelimgpath(cursor.getString(cursor.getColumnIndex(ChexiBean.getInstance().getKEY_MODEL_IMGPATH())));
				item.setStatus(cursor.getString(cursor.getColumnIndex(ChexiBean.getInstance().getKEY_STATUS())));
				item.setIsNewCar(cursor.getString(cursor.getColumnIndex(ChexiBean.getInstance().getKEY_IS_NEW_CAR())));
				list.add(item);
			}while(cursor.moveToNext());
		} else {
			models.setStatus(500);
		}
		cursor.close();
		db.close();
		return models;
	}
	
	
	/** 插入车型 **/
	public synchronized boolean insertCarStyle(CarStyleResultModels models){
		if(null == mSelfDbOpenHelper)return false;
		if(null == models || null == models.getStyleList() ||models.getStyleList().size() == 0){
			return false;
		}
		mSelfDbOpenHelper.toClearTable(ChexingModelBean.getInstance());
		mSelfDbOpenHelper.toCheckTable(ChexingModelBean.getInstance());
		SQLiteDatabase db = mSelfDbOpenHelper.getWritableDatabase();
		boolean bl = false;
		try {
			List<CarStyleBean> styleList = models.getStyleList();
			for(CarStyleBean item:styleList){
				ContentValues cv = new ContentValues();
				cv.put(ChexingModelBean.getInstance().getKEY_MODEL_ID(), item.getModelId());
				cv.put(ChexingModelBean.getInstance().getKEY_CHEXING_ID(), item.getId());
				cv.put(ChexingModelBean.getInstance().getKEY_CHEXING_NAME(), item.getName());
				cv.put(ChexingModelBean.getInstance().getKEY_YEAR(), item.getYear());
				cv.put(ChexingModelBean.getInstance().getKEY_NEXT_YEAR(), item.getNextYear());
				cv.put(ChexingModelBean.getInstance().getKEY_NOW_MSRP(), item.getNowMsrp());
				cv.put(ChexingModelBean.getInstance().getKEY_FULL_NAME(), item.getFullName());
				cv.put(ChexingModelBean.getInstance().getKEY_STATUS(), item.getStatus());
				cv.put(ChexingModelBean.getInstance().getKEY_PRODUCTION_SALE_STATUS(), item.getProductionSaleStatus());
				
				db.insert(ChexingModelBean.getInstance().getTableName(), null, cv);
			}
			ContentValues last = new ContentValues();
			last.put(CarLastUpdateTimeBean.getInstance().getKEY_TIME(), models.getLastUpData());
			last.put(CarLastUpdateTimeBean.getInstance().getKEY_TYPE(), ChexingModelBean.getInstance().getTableName());
			db.insert(CarLastUpdateTimeBean.getInstance().getTableName(), null, last);
			
			bl = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.close();
		}
		return bl;
	}

	@Override
	public boolean addCarStyle(CarStyleResultModels models) {
		if(null == mSelfDbOpenHelper)return false;
		if(null == models || null == models.getStyleList() ||models.getStyleList().size() == 0){
			return false;
		}
		mSelfDbOpenHelper.toCheckTable(ChexingModelBean.getInstance());
		SQLiteDatabase db = mSelfDbOpenHelper.getWritableDatabase();
		boolean bl = false;
		try {
			List<CarStyleBean> styleList = models.getStyleList();
			for(CarStyleBean item:styleList){
				ContentValues cv = new ContentValues();
				cv.put(ChexingModelBean.getInstance().getKEY_MODEL_ID(), item.getModelId());
				cv.put(ChexingModelBean.getInstance().getKEY_CHEXING_ID(), item.getId());
				cv.put(ChexingModelBean.getInstance().getKEY_CHEXING_NAME(), item.getName());
				cv.put(ChexingModelBean.getInstance().getKEY_YEAR(), item.getYear());
				cv.put(ChexingModelBean.getInstance().getKEY_NEXT_YEAR(), item.getNextYear());
				cv.put(ChexingModelBean.getInstance().getKEY_NOW_MSRP(), item.getNowMsrp());
				cv.put(ChexingModelBean.getInstance().getKEY_FULL_NAME(), item.getFullName());
				cv.put(ChexingModelBean.getInstance().getKEY_STATUS(), item.getStatus());
				cv.put(ChexingModelBean.getInstance().getKEY_PRODUCTION_SALE_STATUS(), item.getProductionSaleStatus());
				
				db.insert(ChexingModelBean.getInstance().getTableName(), null, cv);
			}
			ContentValues last = new ContentValues();
			last.put(CarLastUpdateTimeBean.getInstance().getKEY_TIME(), models.getLastUpData());
			last.put(CarLastUpdateTimeBean.getInstance().getKEY_TYPE(), ChexingModelBean.getInstance().getTableName());
			db.insert(CarLastUpdateTimeBean.getInstance().getTableName(), null, last);
			
			bl = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.close();
		}
		return bl;
	}
	@Override
	public boolean delCarStyle(String styleId) {
		if(mSelfDbOpenHelper == null)return false;
		if(TextUtils.isEmpty(styleId))return false;
		mSelfDbOpenHelper.toCheckTable(ChexingModelBean.getInstance());
		SQLiteDatabase db = mSelfDbOpenHelper.getWritableDatabase();
		db.delete(ChexingModelBean.getInstance().getTableName(), 
				ChexingModelBean.getInstance().getKEY_CHEXING_ID() + "=?", new String[]{styleId});
		return true;
	}
	/**
	 * 更新车型
	 * @param models
	 * @return
	 */
	public synchronized boolean updateCarStyle(CarStyleResultModels models){
		if(null == mSelfDbOpenHelper)return false;
		if(null == models || null == models.getStyleList() ||models.getStyleList().size() == 0){
			return false;
		}
		mSelfDbOpenHelper.toCheckTable(ChexingModelBean.getInstance());
		SQLiteDatabase db = mSelfDbOpenHelper.getWritableDatabase();
		boolean bl = false;
		try {
			List<CarStyleBean> makeList = models.getStyleList();
			for(CarStyleBean item:makeList){
				ContentValues cv = new ContentValues();
				cv.put(ChexingModelBean.getInstance().getKEY_MODEL_ID(), item.getModelId());
				cv.put(ChexingModelBean.getInstance().getKEY_CHEXING_ID(), item.getId());
				cv.put(ChexingModelBean.getInstance().getKEY_CHEXING_NAME(), item.getName());
				cv.put(ChexingModelBean.getInstance().getKEY_YEAR(), item.getYear());
				cv.put(ChexingModelBean.getInstance().getKEY_NEXT_YEAR(), item.getNextYear());
				cv.put(ChexingModelBean.getInstance().getKEY_NOW_MSRP(), item.getNowMsrp());
				cv.put(ChexingModelBean.getInstance().getKEY_FULL_NAME(), item.getFullName());
				cv.put(ChexingModelBean.getInstance().getKEY_STATUS(), item.getStatus());
				cv.put(ChexingModelBean.getInstance().getKEY_PRODUCTION_SALE_STATUS(), item.getProductionSaleStatus());
				
				db.update(ChexingModelBean.getInstance().getTableName(), cv, 
						ChexingModelBean.getInstance().getKEY_CHEXING_ID() + "=?", new String[]{item.getId()});
			}
			
			ContentValues last = new ContentValues();
			last.put(CarLastUpdateTimeBean.getInstance().getKEY_TIME(), models.getLastUpData());
			db.update(CarLastUpdateTimeBean.getInstance().getTableName(), last, 
					CarLastUpdateTimeBean.getInstance().getKEY_TYPE() + "=?", new String[]{ChexingModelBean.getInstance().getTableName()});
			
			bl = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.close();
		}
		return bl;
	}
	/**
	 * 查询车型列表
	 * @return
	 */
	public synchronized CarStyleResultModels queryCarStyle(final String modelId,final String isNewCar){
		if(mSelfDbOpenHelper == null)return null;
		mSelfDbOpenHelper.toCheckTable(ChexingModelBean.getInstance());
		SQLiteDatabase db = mSelfDbOpenHelper.getReadableDatabase();
		
		Cursor cursor = null;
		if(!TextUtils.isEmpty(isNewCar) && "1".equals(isNewCar)){
			cursor = db.query(ChexingModelBean.getInstance().getTableName(), null, 
					ChexingModelBean.getInstance().getKEY_MODEL_ID() + "=?," +
					ChexingModelBean.getInstance().getKEY_PRODUCTION_SALE_STATUS() + "=?", 
					new String[]{modelId,isNewCar}, null, null, 
					ChexingModelBean.getInstance().getKEY_CHEXING_NAME(),null);
		} else {
			cursor = db.query(ChexingModelBean.getInstance().getTableName(), null, 
					ChexingModelBean.getInstance().getKEY_MODEL_ID() + "=?", 
					new String[]{modelId}, null, null, 
					ChexingModelBean.getInstance().getKEY_CHEXING_NAME(),null);
		}
		
		CarStyleResultModels models = new CarStyleResultModels();
		final List<CarStyleBean> list = new ArrayList<CarStyleBean>();
		models.setStyleList(list);
		models.setLastUpData(getLastUpdate(ChexingModelBean.getInstance()));
		cursor.moveToFirst();
		if(cursor.getCount() != 0){
			models.setStatus(100);
			do{
				CarStyleBean item = new CarStyleBean();
				item.setModelId(cursor.getString(cursor.getColumnIndex(ChexingModelBean.getInstance().getKEY_MODEL_ID())));
				item.setId(cursor.getString(cursor.getColumnIndex(ChexingModelBean.getInstance().getKEY_CHEXING_ID())));
				item.setName(cursor.getString(cursor.getColumnIndex(ChexingModelBean.getInstance().getKEY_CHEXING_NAME())));
				item.setYear(cursor.getString(cursor.getColumnIndex(ChexingModelBean.getInstance().getKEY_YEAR())));
				item.setNextYear(cursor.getString(cursor.getColumnIndex(ChexingModelBean.getInstance().getKEY_NEXT_YEAR())));
				item.setNowMsrp(cursor.getString(cursor.getColumnIndex(ChexingModelBean.getInstance().getKEY_NOW_MSRP())));
				item.setFullName(cursor.getString(cursor.getColumnIndex(ChexingModelBean.getInstance().getKEY_FULL_NAME())));
				item.setStatus(cursor.getString(cursor.getColumnIndex(ChexingModelBean.getInstance().getKEY_STATUS())));
				item.setProductionSaleStatus(cursor.getString(cursor.getColumnIndex(ChexingModelBean.getInstance().getKEY_PRODUCTION_SALE_STATUS())));
				list.add(item);
			}while(cursor.moveToNext());
		} else {
			models.setStatus(500);
		}
		cursor.close();
		db.close();
		return models;
	}
	
	/** 插入品牌 **/
	public synchronized boolean insertCarBrandValua(CarMakeResultModels models){
		if(null == mSelfDbOpenHelper)return false;
		if(null == models || null == models.getMakeList() ||models.getMakeList().size() == 0){
			return false;
		}
		mSelfDbOpenHelper.toClearTable(BrandValuationBean.getInstance());
		mSelfDbOpenHelper.toCheckTable(BrandValuationBean.getInstance());
		SQLiteDatabase db = mSelfDbOpenHelper.getWritableDatabase();
		boolean bl = false;
		try {
			List<CarMakeBean> makeList = models.getMakeList();
			for(CarMakeBean item:makeList){
				ContentValues cv = new ContentValues();
				cv.put(BrandValuationBean.getInstance().getKEY_GROUP_NAME(), item.getGroupName());
				cv.put(BrandValuationBean.getInstance().getKEY_MAKE_ID(), item.getMakeId());
				cv.put(BrandValuationBean.getInstance().getKEY_MAKE_LOGO(), item.getMakeLogo());
				cv.put(BrandValuationBean.getInstance().getKEY_MAKE_NAME(), item.getMakeName());
				cv.put(BrandValuationBean.getInstance().getKEY_STATUS(), item.getStatus());
				
				db.insert(BrandValuationBean.getInstance().getTableName(), null, cv);
				
			}
			ContentValues last = new ContentValues();
			last.put(CarLastUpdateTimeBean.getInstance().getKEY_TIME(), models.getLastUpData());
			last.put(CarLastUpdateTimeBean.getInstance().getKEY_TYPE(), BrandValuationBean.getInstance().getTableName());
			db.insert(CarLastUpdateTimeBean.getInstance().getTableName(), null, last);
			
			bl = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.close();
		}
		return bl;
	}

	@Override
	public boolean addCarBrandValua(CarMakeResultModels models) {
		if(null == mSelfDbOpenHelper)return false;
		if(null == models || null == models.getMakeList() ||models.getMakeList().size() == 0){
			return false;
		}
		mSelfDbOpenHelper.toCheckTable(BrandValuationBean.getInstance());
		SQLiteDatabase db = mSelfDbOpenHelper.getWritableDatabase();
		boolean bl = false;
		try {
			List<CarMakeBean> makeList = models.getMakeList();
			for(CarMakeBean item:makeList){
				ContentValues cv = new ContentValues();
				cv.put(BrandValuationBean.getInstance().getKEY_GROUP_NAME(), item.getGroupName());
				cv.put(BrandValuationBean.getInstance().getKEY_MAKE_ID(), item.getMakeId());
				cv.put(BrandValuationBean.getInstance().getKEY_MAKE_LOGO(), item.getMakeLogo());
				cv.put(BrandValuationBean.getInstance().getKEY_MAKE_NAME(), item.getMakeName());
				cv.put(BrandValuationBean.getInstance().getKEY_STATUS(), item.getStatus());
				
				db.insert(BrandValuationBean.getInstance().getTableName(), null, cv);
				
			}
			ContentValues last = new ContentValues();
			last.put(CarLastUpdateTimeBean.getInstance().getKEY_TIME(), models.getLastUpData());
			last.put(CarLastUpdateTimeBean.getInstance().getKEY_TYPE(), BrandValuationBean.getInstance().getTableName());
			db.insert(CarLastUpdateTimeBean.getInstance().getTableName(), null, last);
			
			bl = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.close();
		}
		return bl;
	}
	@Override
	public boolean delCarBrandValua(String makeId) {
		if(mSelfDbOpenHelper == null)return false;
		if(TextUtils.isEmpty(makeId))return false;
		mSelfDbOpenHelper.toCheckTable(BrandValuationBean.getInstance());
		SQLiteDatabase db = mSelfDbOpenHelper.getWritableDatabase();
		db.delete(BrandValuationBean.getInstance().getTableName(), 
				BrandValuationBean.getInstance().getKEY_MAKE_ID() + "=?", new String[]{makeId});
		return true;
	}
	
	/**
	 * 更新品牌
	 * @param models
	 * @return
	 */
	public synchronized boolean updateCarBrandValua(CarMakeResultModels models){
		if(null == mSelfDbOpenHelper)return false;
		if(null == models || null == models.getMakeList() ||models.getMakeList().size() == 0){
			return false;
		}
		mSelfDbOpenHelper.toCheckTable(BrandValuationBean.getInstance());
		SQLiteDatabase db = mSelfDbOpenHelper.getWritableDatabase();
		boolean bl = false;
		try {
			List<CarMakeBean> makeList = models.getMakeList();
			for(CarMakeBean item:makeList){
				ContentValues cv = new ContentValues();
				cv.put(BrandValuationBean.getInstance().getKEY_GROUP_NAME(), item.getGroupName());
				cv.put(BrandValuationBean.getInstance().getKEY_MAKE_ID(), item.getMakeId());
				cv.put(BrandValuationBean.getInstance().getKEY_MAKE_LOGO(), item.getMakeLogo());
				cv.put(BrandValuationBean.getInstance().getKEY_MAKE_NAME(), item.getMakeName());
				cv.put(BrandValuationBean.getInstance().getKEY_STATUS(), item.getStatus());
				
				db.update(BrandValuationBean.getInstance().getTableName(), cv, 
						BrandValuationBean.getInstance().getKEY_MAKE_ID() + "=?", new String[]{item.getMakeId()});
			}
			
			ContentValues last = new ContentValues();
			last.put(CarLastUpdateTimeBean.getInstance().getKEY_TIME(), models.getLastUpData());
			db.update(CarLastUpdateTimeBean.getInstance().getTableName(), last, 
					CarLastUpdateTimeBean.getInstance().getKEY_TYPE() + "=?", new String[]{BrandValuationBean.getInstance().getTableName()});
			
			bl = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.close();
		}
		return bl;
	}
	/**
	 * 查询品牌列表
	 * @return
	 */
	public synchronized CarMakeResultModels queryCarBrandValua(){
		if(mSelfDbOpenHelper == null)return null;
		mSelfDbOpenHelper.toCheckTable(BrandValuationBean.getInstance());
		SQLiteDatabase db = mSelfDbOpenHelper.getReadableDatabase();
		Cursor cursor = db.query(BrandValuationBean.getInstance().getTableName(), null, null, null, null, null, null);
		
		CarMakeResultModels models = new CarMakeResultModels();
		final List<CarMakeBean> list = new ArrayList<CarMakeBean>();
		models.setMakeList(list);
		models.setLastUpData(getLastUpdate(BrandValuationBean.getInstance()));
		cursor.moveToFirst();
		if(cursor.getCount() != 0){
			models.setStatus(100);
			do{
				CarMakeBean item = new CarMakeBean();
				item.setGroupName(cursor.getString(cursor.getColumnIndex(BrandValuationBean.getInstance().getKEY_GROUP_NAME())));
				item.setMakeId(cursor.getString(cursor.getColumnIndex(BrandValuationBean.getInstance().getKEY_MAKE_ID())));
				item.setMakeLogo(cursor.getString(cursor.getColumnIndex(BrandValuationBean.getInstance().getKEY_MAKE_LOGO())));
				item.setMakeName(cursor.getString(cursor.getColumnIndex(BrandValuationBean.getInstance().getKEY_MAKE_NAME())));
				item.setStatus(cursor.getString(cursor.getColumnIndex(BrandValuationBean.getInstance().getKEY_STATUS())));
				list.add(item);
				
			}while(cursor.moveToNext());
		} else {
			models.setStatus(500);
		}
		cursor.close();
		db.close();
		return models;
	}
	
	/** 插入车系 **/
	public synchronized boolean insertCarModelValua(CarModelResultModels models){
		if(null == mSelfDbOpenHelper)return false;
		if(null == models || null == models.getModelList() ||models.getModelList().size() == 0){
			return false;
		}
		mSelfDbOpenHelper.toClearTable(ChexiValuationBean.getInstance());
		mSelfDbOpenHelper.toCheckTable(ChexiValuationBean.getInstance());
		SQLiteDatabase db = mSelfDbOpenHelper.getWritableDatabase();
		boolean bl = false;
		try {
			List<CarModelBean> modelList = models.getModelList();
			for(CarModelBean item:modelList){
				ContentValues cv = new ContentValues();
				cv.put(ChexiValuationBean.getInstance().getKEY_MAKE_ID(), item.getMakeId());
				cv.put(ChexiValuationBean.getInstance().getKEY_MANUFACTURER_ID(), item.getManufacturerId());
				cv.put(ChexiValuationBean.getInstance().getKEY_MANUFACTURER_NAME(), item.getManufacturerName());
				cv.put(ChexiValuationBean.getInstance().getKEY_CHEXI_ID(), item.getId());
				cv.put(ChexiValuationBean.getInstance().getKEY_CHEXI_NAME(), item.getName());
				cv.put(ChexiValuationBean.getInstance().getKEY_MODEL_IMGPATH(), item.getModelimgpath());
				cv.put(ChexiValuationBean.getInstance().getKEY_STATUS(), item.getStatus());
				db.insert(ChexiValuationBean.getInstance().getTableName(), null, cv);
			}
			ContentValues last = new ContentValues();
			last.put(CarLastUpdateTimeBean.getInstance().getKEY_TIME(), models.getLastUpData());
			last.put(CarLastUpdateTimeBean.getInstance().getKEY_TYPE(), ChexiValuationBean.getInstance().getTableName());
			db.insert(CarLastUpdateTimeBean.getInstance().getTableName(), null, last);
			
			bl = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.close();
		}
		return bl;
	}

	@Override
	public boolean addCarModelValua(CarModelResultModels models) {
		if(null == mSelfDbOpenHelper)return false;
		if(null == models || null == models.getModelList() ||models.getModelList().size() == 0){
			return false;
		}
		mSelfDbOpenHelper.toCheckTable(ChexiValuationBean.getInstance());
		SQLiteDatabase db = mSelfDbOpenHelper.getWritableDatabase();
		boolean bl = false;
		try {
			List<CarModelBean> modelList = models.getModelList();
			for(CarModelBean item:modelList){
				ContentValues cv = new ContentValues();
				cv.put(ChexiValuationBean.getInstance().getKEY_MAKE_ID(), item.getMakeId());
				cv.put(ChexiValuationBean.getInstance().getKEY_MANUFACTURER_ID(), item.getManufacturerId());
				cv.put(ChexiValuationBean.getInstance().getKEY_MANUFACTURER_NAME(), item.getManufacturerName());
				cv.put(ChexiValuationBean.getInstance().getKEY_CHEXI_ID(), item.getId());
				cv.put(ChexiValuationBean.getInstance().getKEY_CHEXI_NAME(), item.getName());
				cv.put(ChexiValuationBean.getInstance().getKEY_MODEL_IMGPATH(), item.getModelimgpath());
				cv.put(ChexiValuationBean.getInstance().getKEY_STATUS(), item.getStatus());
				db.insert(ChexiValuationBean.getInstance().getTableName(), null, cv);
			}
			ContentValues last = new ContentValues();
			last.put(CarLastUpdateTimeBean.getInstance().getKEY_TIME(), models.getLastUpData());
			last.put(CarLastUpdateTimeBean.getInstance().getKEY_TYPE(), ChexiValuationBean.getInstance().getTableName());
			db.insert(CarLastUpdateTimeBean.getInstance().getTableName(), null, last);
			
			bl = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.close();
		}
		return bl;
	}
	@Override
	public boolean delCarModelValua(String modelId) {
		if(mSelfDbOpenHelper == null)return false;
		if(TextUtils.isEmpty(modelId))return false;
		mSelfDbOpenHelper.toCheckTable(ChexiValuationBean.getInstance());
		SQLiteDatabase db = mSelfDbOpenHelper.getWritableDatabase();
		db.delete(ChexiValuationBean.getInstance().getTableName(), 
				ChexiValuationBean.getInstance().getKEY_CHEXI_ID() + "=?", new String[]{modelId});
		return true;
	}
	/**
	 * 更新车系
	 * @param models
	 * @return
	 */
	public synchronized boolean updateCarModelValua(CarModelResultModels models){
		if(null == mSelfDbOpenHelper)return false;
		if(null == models || null == models.getModelList() ||models.getModelList().size() == 0){
			return false;
		}
		mSelfDbOpenHelper.toCheckTable(ChexiValuationBean.getInstance());
		SQLiteDatabase db = mSelfDbOpenHelper.getWritableDatabase();
		boolean bl = false;
		try {
			List<CarModelBean> modelsList = models.getModelList();
			for(CarModelBean item:modelsList){
				ContentValues cv = new ContentValues();
				cv.put(ChexiValuationBean.getInstance().getKEY_MAKE_ID(), item.getMakeId());
				cv.put(ChexiValuationBean.getInstance().getKEY_MANUFACTURER_ID(), item.getManufacturerId());
				cv.put(ChexiValuationBean.getInstance().getKEY_MANUFACTURER_NAME(), item.getManufacturerName());
				cv.put(ChexiValuationBean.getInstance().getKEY_CHEXI_ID(), item.getId());
				cv.put(ChexiValuationBean.getInstance().getKEY_CHEXI_NAME(), item.getName());
				cv.put(ChexiValuationBean.getInstance().getKEY_MODEL_IMGPATH(), item.getModelimgpath());
				cv.put(ChexiValuationBean.getInstance().getKEY_STATUS(), item.getStatus());
				
				db.update(ChexiValuationBean.getInstance().getTableName(), cv, 
						ChexiValuationBean.getInstance().getKEY_CHEXI_ID() + "=?", new String[]{item.getId()});
			}
			
			ContentValues last = new ContentValues();
			last.put(CarLastUpdateTimeBean.getInstance().getKEY_TIME(), models.getLastUpData());
			db.update(CarLastUpdateTimeBean.getInstance().getTableName(), last, 
					CarLastUpdateTimeBean.getInstance().getKEY_TYPE() + "=?", new String[]{ChexiValuationBean.getInstance().getTableName()});
			
			bl = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.close();
		}
		return bl;
	}
	/**
	 * 查询车系列表
	 * @return
	 */
	public synchronized CarModelResultModels queryCarModelValua(final String makeId){
		if(mSelfDbOpenHelper == null)return null;
		mSelfDbOpenHelper.toCheckTable(ChexiValuationBean.getInstance());
		SQLiteDatabase db = mSelfDbOpenHelper.getReadableDatabase();
		Cursor cursor = db.query(ChexiValuationBean.getInstance().getTableName(), null, 
				ChexiValuationBean.getInstance().getKEY_MAKE_ID() + "=?", new String[]{makeId}, null,null,
				ChexiValuationBean.getInstance().getKEY_MANUFACTURER_ID(), null);
		
		CarModelResultModels models = new CarModelResultModels();
		final List<CarModelBean> list = new ArrayList<CarModelBean>();
		models.setModelList(list);
		models.setLastUpData(getLastUpdate(ChexiValuationBean.getInstance()));
		cursor.moveToFirst();
		if(cursor.getCount() != 0){
			models.setStatus(100);
			do{
				CarModelBean item = new CarModelBean();
				item.setMakeId(cursor.getString(cursor.getColumnIndex(ChexiValuationBean.getInstance().getKEY_MAKE_ID())));
				item.setManufacturerId(cursor.getString(cursor.getColumnIndex(ChexiValuationBean.getInstance().getKEY_MANUFACTURER_ID())));
				item.setManufacturerName(cursor.getString(cursor.getColumnIndex(ChexiValuationBean.getInstance().getKEY_MANUFACTURER_NAME())));
				item.setId(cursor.getString(cursor.getColumnIndex(ChexiValuationBean.getInstance().getKEY_CHEXI_ID())));
				item.setName(cursor.getString(cursor.getColumnIndex(ChexiValuationBean.getInstance().getKEY_CHEXI_NAME())));
				item.setModelimgpath(cursor.getString(cursor.getColumnIndex(ChexiValuationBean.getInstance().getKEY_MODEL_IMGPATH())));
				item.setStatus(cursor.getString(cursor.getColumnIndex(ChexiValuationBean.getInstance().getKEY_STATUS())));
				list.add(item);
			}while(cursor.moveToNext());
		} else {
			models.setStatus(500);
		}
		cursor.close();
		db.close();
		return models;
	}
	
	
	/** 插入车型 **/
	public synchronized boolean insertCarStyleValua(CarStyleResultModels models){
		if(null == mSelfDbOpenHelper)return false;
		if(null == models || null == models.getStyleList() ||models.getStyleList().size() == 0){
			return false;
		}
		mSelfDbOpenHelper.toClearTable(CarStyleValuationBean.getInstance());
		mSelfDbOpenHelper.toCheckTable(CarStyleValuationBean.getInstance());
		SQLiteDatabase db = mSelfDbOpenHelper.getWritableDatabase();
		boolean bl = false;
		try {
			List<CarStyleBean> styleList = models.getStyleList();
			for(CarStyleBean item:styleList){
				ContentValues cv = new ContentValues();
				cv.put(CarStyleValuationBean.getInstance().getKEY_MODEL_ID(), item.getModelId());
				cv.put(CarStyleValuationBean.getInstance().getKEY_CHEXING_ID(), item.getId());
				cv.put(CarStyleValuationBean.getInstance().getKEY_CHEXING_NAME(), item.getName());
				cv.put(CarStyleValuationBean.getInstance().getKEY_YEAR(), item.getYear());
				cv.put(CarStyleValuationBean.getInstance().getKEY_NEXT_YEAR(), item.getNextYear());
				cv.put(CarStyleValuationBean.getInstance().getKEY_NOW_MSRP(), item.getNowMsrp());
				cv.put(CarStyleValuationBean.getInstance().getKEY_FULL_NAME(), item.getFullName());
				cv.put(CarStyleValuationBean.getInstance().getKEY_STATUS(), item.getStatus());
				
				db.insert(CarStyleValuationBean.getInstance().getTableName(), null, cv);
			}
			ContentValues last = new ContentValues();
			last.put(CarLastUpdateTimeBean.getInstance().getKEY_TIME(), models.getLastUpData());
			last.put(CarLastUpdateTimeBean.getInstance().getKEY_TYPE(), CarStyleValuationBean.getInstance().getTableName());
			db.insert(CarLastUpdateTimeBean.getInstance().getTableName(), null, last);
			
			bl = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.close();
		}
		return bl;
	}

	@Override
	public boolean addCarStyleValua(CarStyleResultModels models) {
		if(null == mSelfDbOpenHelper)return false;
		if(null == models || null == models.getStyleList() ||models.getStyleList().size() == 0){
			return false;
		}
		mSelfDbOpenHelper.toCheckTable(CarStyleValuationBean.getInstance());
		SQLiteDatabase db = mSelfDbOpenHelper.getWritableDatabase();
		boolean bl = false;
		try {
			List<CarStyleBean> styleList = models.getStyleList();
			for(CarStyleBean item:styleList){
				ContentValues cv = new ContentValues();
				cv.put(CarStyleValuationBean.getInstance().getKEY_MODEL_ID(), item.getModelId());
				cv.put(CarStyleValuationBean.getInstance().getKEY_CHEXING_ID(), item.getId());
				cv.put(CarStyleValuationBean.getInstance().getKEY_CHEXING_NAME(), item.getName());
				cv.put(CarStyleValuationBean.getInstance().getKEY_YEAR(), item.getYear());
				cv.put(CarStyleValuationBean.getInstance().getKEY_NEXT_YEAR(), item.getNextYear());
				cv.put(CarStyleValuationBean.getInstance().getKEY_NOW_MSRP(), item.getNowMsrp());
				cv.put(CarStyleValuationBean.getInstance().getKEY_FULL_NAME(), item.getFullName());
				cv.put(CarStyleValuationBean.getInstance().getKEY_STATUS(), item.getStatus());
				
				db.insert(CarStyleValuationBean.getInstance().getTableName(), null, cv);
			}
			ContentValues last = new ContentValues();
			last.put(CarLastUpdateTimeBean.getInstance().getKEY_TIME(), models.getLastUpData());
			last.put(CarLastUpdateTimeBean.getInstance().getKEY_TYPE(), CarStyleValuationBean.getInstance().getTableName());
			db.insert(CarLastUpdateTimeBean.getInstance().getTableName(), null, last);
			
			bl = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.close();
		}
		return bl;
	}
	@Override
	public boolean delCarStyleValua(String styleId) {
		if(mSelfDbOpenHelper == null)return false;
		if(TextUtils.isEmpty(styleId))return false;
		mSelfDbOpenHelper.toCheckTable(CarStyleValuationBean.getInstance());
		SQLiteDatabase db = mSelfDbOpenHelper.getWritableDatabase();
		db.delete(CarStyleValuationBean.getInstance().getTableName(), 
				CarStyleValuationBean.getInstance().getKEY_CHEXING_ID() + "=?", new String[]{styleId});
		return true;
	}
	/**
	 * 更新车型
	 * @param models
	 * @return
	 */
	public synchronized boolean updateCarStyleValua(CarStyleResultModels models){
		if(null == mSelfDbOpenHelper)return false;
		if(null == models || null == models.getStyleList() ||models.getStyleList().size() == 0){
			return false;
		}
		mSelfDbOpenHelper.toCheckTable(CarStyleValuationBean.getInstance());
		SQLiteDatabase db = mSelfDbOpenHelper.getWritableDatabase();
		boolean bl = false;
		try {
			List<CarStyleBean> makeList = models.getStyleList();
			for(CarStyleBean item:makeList){
				ContentValues cv = new ContentValues();
				cv.put(CarStyleValuationBean.getInstance().getKEY_MODEL_ID(), item.getModelId());
				cv.put(CarStyleValuationBean.getInstance().getKEY_CHEXING_ID(), item.getId());
				cv.put(CarStyleValuationBean.getInstance().getKEY_CHEXING_NAME(), item.getName());
				cv.put(CarStyleValuationBean.getInstance().getKEY_YEAR(), item.getYear());
				cv.put(CarStyleValuationBean.getInstance().getKEY_NEXT_YEAR(), item.getNextYear());
				cv.put(CarStyleValuationBean.getInstance().getKEY_NOW_MSRP(), item.getNowMsrp());
				cv.put(CarStyleValuationBean.getInstance().getKEY_FULL_NAME(), item.getFullName());
				cv.put(CarStyleValuationBean.getInstance().getKEY_STATUS(), item.getStatus());
				
				db.update(CarStyleValuationBean.getInstance().getTableName(), cv, 
						CarStyleValuationBean.getInstance().getKEY_CHEXING_ID() + "=?", new String[]{item.getId()});
			}
			
			ContentValues last = new ContentValues();
			last.put(CarLastUpdateTimeBean.getInstance().getKEY_TIME(), models.getLastUpData());
			db.update(CarLastUpdateTimeBean.getInstance().getTableName(), last, 
					CarLastUpdateTimeBean.getInstance().getKEY_TYPE() + "=?", new String[]{CarStyleValuationBean.getInstance().getTableName()});
			
			bl = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.close();
		}
		return bl;
	}
	/**
	 * 查询车型列表
	 * @return
	 */
	public synchronized CarStyleResultModels queryCarStyleValua(final String modelId){
		if(mSelfDbOpenHelper == null)return null;
		mSelfDbOpenHelper.toCheckTable(CarStyleValuationBean.getInstance());
		SQLiteDatabase db = mSelfDbOpenHelper.getReadableDatabase();
		
		Cursor cursor = db.query(CarStyleValuationBean.getInstance().getTableName(), null, 
				CarStyleValuationBean.getInstance().getKEY_MODEL_ID() + "=?", new String[]{modelId}, null, null, 
				CarStyleValuationBean.getInstance().getKEY_CHEXING_NAME() , null);
		
		CarStyleResultModels models = new CarStyleResultModels();
		final List<CarStyleBean> list = new ArrayList<CarStyleBean>();
		models.setStyleList(list);
		models.setLastUpData(getLastUpdate(CarStyleValuationBean.getInstance()));
		cursor.moveToFirst();
		if(cursor.getCount() != 0){
			models.setStatus(100);
			do {
				CarStyleBean item = new CarStyleBean();
				item.setModelId(cursor.getString(cursor.getColumnIndex(CarStyleValuationBean.getInstance().getKEY_MODEL_ID())));
				item.setId(cursor.getString(cursor.getColumnIndex(CarStyleValuationBean.getInstance().getKEY_CHEXING_ID())));
				item.setName(cursor.getString(cursor.getColumnIndex(CarStyleValuationBean.getInstance().getKEY_CHEXING_NAME())));
				item.setYear(cursor.getString(cursor.getColumnIndex(CarStyleValuationBean.getInstance().getKEY_YEAR())));
				item.setNextYear(cursor.getString(cursor.getColumnIndex(CarStyleValuationBean.getInstance().getKEY_NEXT_YEAR())));
				item.setNowMsrp(cursor.getString(cursor.getColumnIndex(CarStyleValuationBean.getInstance().getKEY_NOW_MSRP())));
				item.setFullName(cursor.getString(cursor.getColumnIndex(CarStyleValuationBean.getInstance().getKEY_FULL_NAME())));
				item.setStatus(cursor.getString(cursor.getColumnIndex(CarStyleValuationBean.getInstance().getKEY_STATUS())));
				list.add(item);
			}while(cursor.moveToNext());
		} else {
			models.setStatus(500);
		}
		cursor.close();
		db.close();
		return models;
	}
	
	private String getLastUpdate(BaseDBModel model){
		mSelfDbOpenHelper.toCheckTable(CarLastUpdateTimeBean.getInstance());
		SQLiteDatabase db = mSelfDbOpenHelper.getReadableDatabase();
		Cursor cursor = db.query(CarLastUpdateTimeBean.getInstance().getTableName(), 
				new String[]{CarLastUpdateTimeBean.getInstance().getKEY_TIME()}, 
				CarLastUpdateTimeBean.getInstance().getKEY_TYPE() + "=?", 
				new String[]{model.getTableName()}, null, null, null);
		if(cursor.getCount() == 0){
			return "";
		}
		cursor.moveToFirst();
		String last = cursor.getString(cursor.getColumnIndex(CarLastUpdateTimeBean.getInstance().getKEY_TIME()));
		return last;
	}
	@Override
	public String getCarBrandLastUpData() {
		return getLastUpdate(BrandBean.getInstance());
	}
	@Override
	public String getCarModelLastUpData() {
		return getLastUpdate(ChexiBean.getInstance());
	}
	@Override
	public String getCarStyleLastUpData() {
		return getLastUpdate(ChexingModelBean.getInstance());
	}
	@Override
	public String getCarBrandValuaLastUpData() {
		return getLastUpdate(BrandValuationBean.getInstance());
	}
	@Override
	public String getCarModelValuaLastUpData() {
		return getLastUpdate(ChexiValuationBean.getInstance());
	}
	@Override
	public String getCarStyleValuaLastUpData() {
		return getLastUpdate(CarStyleValuationBean.getInstance());
	}
	
}
