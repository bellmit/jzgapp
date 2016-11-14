package com.jzg.jzgoto.phone.models.business.db.city;

import com.jzg.jzgoto.phone.models.common.BaseDBModel;
/**
 * @Description: 城市列表
 * @Package com.jzg.jzgoto.phone.models.business.db.city CityListBean.java
 * @author gf
 * @date 2016-6-13 上午9:06:54
 */
public class CityListBean extends BaseDBModel {
	private final String TABLE_CONTACTS = "citylist";
	private final String KEY_ID = "id";
	private final String KEY_IS_HOT_CITY = "is_hot_city";
	// OrderIndex
	private final String KEY_ORDER_INDEX = "order_index";
	private final String KEY_PROV_ID = "prov_id";
	private final String KEY_CITY_ID = "city_id";
	private final String KEY_NAME = "name";
	private final String KEY_GROUP_NAME = "group_name";
	private final String KEY_STATUS = "status";
	private final String KEY_LAST_UPDATE_TIME = "last_update_time";
	private final static CityListBean mCityListBean = new CityListBean();
	private CityListBean(){};
	public static CityListBean getInstance(){
		return mCityListBean;
	}
	
	public String getTableSql() {
		String createTable = "create table " + TABLE_CONTACTS + "(" +
		KEY_ID + " integer primary key," +
		KEY_IS_HOT_CITY + " text," +
		KEY_ORDER_INDEX + " text," +
		KEY_PROV_ID + " text," +
		KEY_CITY_ID + " text," +
		KEY_NAME + " text," +
		KEY_GROUP_NAME + " text," +
		KEY_STATUS + " text," +
		KEY_LAST_UPDATE_TIME + " text" +
		")";
		return createTable;
	}

	public String getTableName() {
		return TABLE_CONTACTS;
	}

	public int getTableVersion() {
		return 0;
	}

	public String getKEY_STATUS() {
		return KEY_STATUS;
	}

	public String getKEY_ID() {
		return KEY_ID;
	}

	public String getKEY_IS_HOT_CITY() {
		return KEY_IS_HOT_CITY;
	}

	public String getKEY_ORDER_INDEX() {
		return KEY_ORDER_INDEX;
	}

	public String getKEY_PROV_ID() {
		return KEY_PROV_ID;
	}

	public String getKEY_CITY_ID() {
		return KEY_CITY_ID;
	}

	public String getKEY_NAME() {
		return KEY_NAME;
	}

	public String getKEY_GROUP_NAME() {
		return KEY_GROUP_NAME;
	}

	public String getKEY_LAST_UPDATE_TIME() {
		return KEY_LAST_UPDATE_TIME;
	}

	@Override
	public String delTableSql() {
		String delSql = "DROP TABLE " + TABLE_CONTACTS;
		return delSql;
	}
}
