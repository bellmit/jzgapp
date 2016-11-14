package com.jzg.jzgoto.phone.models.business.db.brand;

import com.jzg.jzgoto.phone.models.common.BaseDBModel;
/**
 * @Description: 汽车更新时间存储
 * @Package com.jzg.jzgoto.phone.models.business.db.brand CarLastUpdateTimeBean.java
 * @author gf
 * @date 2016-6-16 下午2:20:14
 */
public class CarLastUpdateTimeBean extends BaseDBModel {

	private final String TABLE_CONTACTS = "last_up_data";
	private final String KEY_ID = "id";
	private final String KEY_TIME = "up_date";
	private final String KEY_TYPE = "type";
	private CarLastUpdateTimeBean(){};
	private final static CarLastUpdateTimeBean mCarLastUpdateTimeBean = new CarLastUpdateTimeBean();
	public static CarLastUpdateTimeBean getInstance(){
		return mCarLastUpdateTimeBean;
	}
	@Override
	public String getTableSql() {
		String createTable = "create table " + TABLE_CONTACTS + "(" +
				KEY_ID + " integer primary key," +
				KEY_TIME + " text," +
				KEY_TYPE + " text" +
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
	
	public String getKEY_TIME() {
		return KEY_TIME;
	}
	public String getKEY_TYPE() {
		return KEY_TYPE;
	}

}
