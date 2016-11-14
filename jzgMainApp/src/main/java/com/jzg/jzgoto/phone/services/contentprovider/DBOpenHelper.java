package com.jzg.jzgoto.phone.services.contentprovider;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

import com.jzg.jzgoto.phone.models.business.db.brand.CarLastUpdateTimeBean;
import com.jzg.jzgoto.phone.models.business.db.city.CityListBean;
import com.jzg.jzgoto.phone.models.common.BaseDBModel;
/**
 * @Description: 创建表
 * @Package com.jzg.jzgoto.phone.services.contentprovider DBOpenHelper.java
 * @author gf
 * @date 2016-6-12 下午3:23:38
 */
public class DBOpenHelper extends SQLiteOpenHelper {

	public static DBOpenHelper mDBOpenHelper;
	public static DBOpenHelper getInstance(Context context, String name, CursorFactory factory,
			int version, DatabaseErrorHandler errorHandler){
		if(mDBOpenHelper == null){
			mDBOpenHelper = new DBOpenHelper(context,name,factory,version,null);
		}
		return mDBOpenHelper;
	}
	
	private String mDbName = "";
	private DBOpenHelper(Context context, String name, CursorFactory factory,
			int version, DatabaseErrorHandler errorHandler) {
		super(context, name, factory, version, errorHandler);
		mDbName = name;
	}
	private DBOpenHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		mDbName = name;
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CarLastUpdateTimeBean.getInstance().getTableSql());
		db.execSQL(CityListBean.getInstance().getTableSql());
		
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if(oldVersion != newVersion){
			db.execSQL("drop table if exists " + CarLastUpdateTimeBean.getInstance().getTableName());
			db.execSQL("drop table if exists " + CityListBean.getInstance().getTableName());
			
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
