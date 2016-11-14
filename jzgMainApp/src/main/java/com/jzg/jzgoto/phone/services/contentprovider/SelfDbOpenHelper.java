package com.jzg.jzgoto.phone.services.contentprovider;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

import com.jzg.jzgoto.phone.models.business.db.brand.BrandBean;
import com.jzg.jzgoto.phone.models.business.db.brand.BrandValuationBean;
import com.jzg.jzgoto.phone.models.business.db.brand.CarLastUpdateTimeBean;
import com.jzg.jzgoto.phone.models.business.db.brand.ChexingModelBean;
import com.jzg.jzgoto.phone.models.business.db.brand.CarStyleValuationBean;
import com.jzg.jzgoto.phone.models.business.db.brand.ChexiBean;
import com.jzg.jzgoto.phone.models.business.db.brand.ChexiValuationBean;
import com.jzg.jzgoto.phone.models.common.BaseDBModel;

/**
 * @Description: 自定义数据库
 * @Package com.jzg.jzgoto.phone.services.contentprovider SelfDbOpenHelper.java
 * @author gf
 * @date 2016-6-16 上午10:54:24
 */
public class SelfDbOpenHelper extends SQLiteOpenHelper{
	
	public static SelfDbOpenHelper mSelfDbOpenHelper;
	public static SelfDbOpenHelper getInstance(Context context, String name, CursorFactory factory,
			int version, DatabaseErrorHandler errorHandler){
		if(mSelfDbOpenHelper == null){
			mSelfDbOpenHelper = new SelfDbOpenHelper(context,name,factory,version);
		}
		return mSelfDbOpenHelper;
	}
	
	
	private String mDbName = "";
	
	private SelfDbOpenHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context,name, factory, version);
		mDbName = name;
	}
	
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CarLastUpdateTimeBean.getInstance().getTableSql());
		db.execSQL(BrandBean.getInstance().getTableSql());
		db.execSQL(ChexiBean.getInstance().getTableSql());
		db.execSQL(ChexingModelBean.getInstance().getTableSql());
		db.execSQL(BrandValuationBean.getInstance().getTableSql());
		db.execSQL(ChexiValuationBean.getInstance().getTableSql());
		db.execSQL(CarStyleValuationBean.getInstance().getTableSql());
		
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if(oldVersion != newVersion){
			db.execSQL("drop table if exists " + CarLastUpdateTimeBean.getInstance().getTableName());
			db.execSQL("drop table if exists " + BrandBean.getInstance().getTableName());
			db.execSQL("drop table if exists " + ChexiBean.getInstance().getTableName());
			db.execSQL("drop table if exists " + ChexingModelBean.getInstance().getTableName());
			db.execSQL("drop table if exists " + BrandValuationBean.getInstance().getTableName());
			db.execSQL("drop table if exists " + ChexiValuationBean.getInstance().getTableName());
			db.execSQL("drop table if exists " + CarStyleValuationBean.getInstance().getTableName());
			onCreate(db);
		}
	}
	/**
	 * 对表进行检查
	 * @param model
	 */
	public <T extends BaseDBModel> void toCheckTable(T model){
		if(TextUtils.isEmpty(mDbName))return;
		String sql = "select count(*) as c from sqlite_master where type ='table' and name ='" + model.getTableName() + "'";
		SQLiteDatabase db = this.getWritableDatabase();
		try {
			Cursor cursor = db.rawQuery(sql, null);
			if(cursor.getCount() == 0){
				db.execSQL(model.getTableSql());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 清空表
	 * @param model
	 */
	public <T extends BaseDBModel> void toClearTable(T model){
		if(TextUtils.isEmpty(mDbName))return;
		String sql = "select count(*) as c from sqlite_master where type ='table' and name ='" + model.getTableName() + "'";
		SQLiteDatabase db = this.getWritableDatabase();
		try {
			Cursor cursor = db.rawQuery(sql, null);
			if(cursor.getCount() != 0){
				db.execSQL(model.delTableSql());
			}
			db.execSQL(model.getTableSql());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
