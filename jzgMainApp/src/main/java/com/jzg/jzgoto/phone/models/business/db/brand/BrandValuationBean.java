package com.jzg.jzgoto.phone.models.business.db.brand;

import com.jzg.jzgoto.phone.models.common.BaseDBModel;
/**
 * @Description: 估值品牌
 * @Package com.jzg.jzgoto.phone.models.business.db.brand BrandValuationBean.java
 * @author gf
 * @date 2016-6-16 下午2:21:16
 */
public class BrandValuationBean extends BaseDBModel {

	private final String TABLE_CONTACTS = "brand_v";
	private final String KEY_ID = "id";
	private final String KEY_GROUP_NAME = "group_name";
	private final String KEY_MAKE_ID = "make_id";
	private final String KEY_MAKE_LOGO = "make_logo";
	private final String KEY_MAKE_NAME = "make_name";
	private final String KEY_STATUS = "status";
	
	private BrandValuationBean(){};
	private final static BrandValuationBean mBrandValuationBean = new BrandValuationBean();
	public static BrandValuationBean getInstance(){
		return mBrandValuationBean;
	}
	
	@Override
	public String getTableSql() {
		String createTable = "create table " + TABLE_CONTACTS + "(" +
				KEY_ID + " integer primary key," +
				KEY_GROUP_NAME + " text," +
				KEY_MAKE_ID + " text," +
				KEY_MAKE_LOGO + " text," +
				KEY_MAKE_NAME + " text," +
				KEY_STATUS + " text" +
				")";
				return createTable;
	}

	@Override
	public String getTableName() {
		return TABLE_CONTACTS;
	}

	@Override
	public int getTableVersion() {
		return 0;
	}

	@Override
	public String delTableSql() {
		String delSql = "DROP TABLE " + TABLE_CONTACTS;
		return delSql;
	}

	public String getKEY_GROUP_NAME() {
		return KEY_GROUP_NAME;
	}

	public String getKEY_MAKE_ID() {
		return KEY_MAKE_ID;
	}

	public String getKEY_MAKE_LOGO() {
		return KEY_MAKE_LOGO;
	}

	public String getKEY_MAKE_NAME() {
		return KEY_MAKE_NAME;
	}

	public String getKEY_STATUS() {
		return KEY_STATUS;
	}

}
