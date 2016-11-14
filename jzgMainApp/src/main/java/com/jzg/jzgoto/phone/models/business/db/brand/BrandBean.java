package com.jzg.jzgoto.phone.models.business.db.brand;

import com.jzg.jzgoto.phone.models.common.BaseDBModel;
/**
 * @Description: 车品牌
 * @Package com.jzg.jzgoto.phone.models.business.db.brand BrandBean.java
 * @author gf
 * @date 2016-6-12 下午3:34:27
 */
public class BrandBean extends BaseDBModel {

	private final String TABLE_CONTACTS = "brand";
	private final String KEY_ID = "id";
	private final String KEY_GROUP_NAME = "group_name";
	private final String KEY_MAKE_ID = "make_id";
	private final String KEY_MAKE_LOGO = "make_logo";
	private final String KEY_MAKE_NAME = "make_name";
	private final String KEY_IS_NEW_CAR = "is_new_car";
	private final String KEY_STATUS = "status";
	
	private BrandBean(){};
	private final static BrandBean mBrandBean = new BrandBean();
	public static BrandBean getInstance(){
		return mBrandBean;
	}
	
	public String getTableSql() {
		String createTable = "create table " + TABLE_CONTACTS + "(" +
		KEY_ID + " integer primary key," +
		KEY_GROUP_NAME + " text," +
		KEY_MAKE_ID + " text," +
		KEY_MAKE_LOGO + " text," +
		KEY_MAKE_NAME + " text," +
		KEY_IS_NEW_CAR + " text," +
		KEY_STATUS + " text" +
		")";
		return createTable;
	}

	public String getTableName() {
		return TABLE_CONTACTS;
	}

	public int getTableVersion() {
		return 0;
	}

	@Override
	public String delTableSql() {
		String delSql = "DROP TABLE " + TABLE_CONTACTS;
		return delSql;
	}

	public String getTABLE_CONTACTS() {
		return TABLE_CONTACTS;
	}

	public String getKEY_ID() {
		return KEY_ID;
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

	public String getKEY_IS_NEW_CAR() {
		return KEY_IS_NEW_CAR;
	}

	public String getKEY_STATUS() {
		return KEY_STATUS;
	}
	
}
