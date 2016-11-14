package com.jzg.jzgoto.phone.models.business.db.brand;

import com.jzg.jzgoto.phone.models.common.BaseDBModel;
/**
 * @Description: 车系
 * @Package com.jzg.jzgoto.phone.models.business.db.brand ChexiBean.java
 * @author gf
 * @date 2016-6-16 下午2:18:43
 */
public class ChexiBean extends BaseDBModel {
	
	private final String TABLE_CONTACTS = "chexi";
	private final String KEY_ID = "id";
	private final String KEY_MAKE_ID = "make_id";
	private final String KEY_MANUFACTURER_ID = "manufacturer_id";
	private final String KEY_MANUFACTURER_NAME = "manufacturer_name";
	private final String KEY_CHEXI_ID = "chexi_id";
	private final String KEY_CHEXI_NAME = "chexi_name";
	private final String KEY_MODEL_IMGPATH = "model_imgpath";
	private final String KEY_STATUS = "status";
	private final String KEY_IS_NEW_CAR = "is_new_car";
	
	private ChexiBean(){};
	private final static ChexiBean mChexiBean = new ChexiBean();
	public static ChexiBean getInstance(){
		return mChexiBean;
	}
	
	@Override
	public String getTableSql() {
		String createTable = "create table " + TABLE_CONTACTS + "(" +
				KEY_ID + " integer primary key," +
				KEY_MAKE_ID + " text," +
				KEY_MANUFACTURER_ID + " text," +
				KEY_MANUFACTURER_NAME + " text," +
				KEY_CHEXI_ID + " text," +
				KEY_CHEXI_NAME + " text," +
				KEY_MODEL_IMGPATH + " text," +
				KEY_STATUS + " text," +
				KEY_IS_NEW_CAR + " text" +
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

	public String getKEY_MAKE_ID() {
		return KEY_MAKE_ID;
	}

	public String getKEY_MANUFACTURER_ID() {
		return KEY_MANUFACTURER_ID;
	}

	public String getKEY_MANUFACTURER_NAME() {
		return KEY_MANUFACTURER_NAME;
	}

	public String getKEY_CHEXI_ID() {
		return KEY_CHEXI_ID;
	}

	public String getKEY_CHEXI_NAME() {
		return KEY_CHEXI_NAME;
	}

	public String getKEY_MODEL_IMGPATH() {
		return KEY_MODEL_IMGPATH;
	}

	public String getKEY_STATUS() {
		return KEY_STATUS;
	}

	public String getKEY_IS_NEW_CAR() {
		return KEY_IS_NEW_CAR;
	}

}
