package com.jzg.jzgoto.phone.models.business.db.brand;

import com.jzg.jzgoto.phone.models.common.BaseDBModel;
/**
 * @Description: 估值车型
 * @Package com.jzg.jzgoto.phone.models.business.db.brand CarModelValuationBean.java
 * @author gf
 * @date 2016-6-16 下午2:22:34
 */
public class CarStyleValuationBean extends BaseDBModel {

	private final String TABLE_CONTACTS = "chexing_v";
	private final String KEY_ID = "id";
	private final String KEY_MODEL_ID = "model_id";
	private final String KEY_CHEXING_ID = "chexing_id";
	private final String KEY_CHEXING_NAME = "chexing_name";
	private final String KEY_YEAR = "year";
	private final String KEY_NEXT_YEAR = "next_year";
	private final String KEY_NOW_MSRP = "now_msrp";
	private final String KEY_FULL_NAME = "full_name";
	private final String KEY_STATUS = "status";
	
	private CarStyleValuationBean(){};
	private final static CarStyleValuationBean mCarModelValuationBean = new CarStyleValuationBean();
	public static CarStyleValuationBean getInstance(){
		return mCarModelValuationBean;
	}
	@Override
	public String getTableSql() {
		String createTable = "create table " + TABLE_CONTACTS + "(" +
				KEY_ID + " integer primary key," +
				KEY_MODEL_ID + " text," +
				KEY_CHEXING_ID + " text," +
				KEY_CHEXING_NAME + " text," +
				KEY_YEAR + " text," +
				KEY_NEXT_YEAR + " text," +
				KEY_NOW_MSRP + " text," +
				KEY_FULL_NAME + " text," +
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
	public String getKEY_MODEL_ID() {
		return KEY_MODEL_ID;
	}
	public String getKEY_CHEXING_ID() {
		return KEY_CHEXING_ID;
	}
	public String getKEY_CHEXING_NAME() {
		return KEY_CHEXING_NAME;
	}
	public String getKEY_YEAR() {
		return KEY_YEAR;
	}
	public String getKEY_NEXT_YEAR() {
		return KEY_NEXT_YEAR;
	}
	public String getKEY_NOW_MSRP() {
		return KEY_NOW_MSRP;
	}
	public String getKEY_FULL_NAME() {
		return KEY_FULL_NAME;
	}
	public String getKEY_STATUS() {
		return KEY_STATUS;
	}

}
