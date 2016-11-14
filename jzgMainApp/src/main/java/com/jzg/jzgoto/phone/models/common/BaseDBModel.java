package com.jzg.jzgoto.phone.models.common;
/**
 * @Description: 数据库类父类
 * @Package com.jzg.jzgoto.phone.models.common BaseDBModel.java
 * @author gf
 * @date 2016-6-12 下午3:30:38
 */
public abstract class BaseDBModel {
	/**
	 * 获取建表
	 * @return
	 */
	public abstract String getTableSql();
	/**
	 * 获取表名
	 * @return
	 */
	public abstract String getTableName();
	/**
	 * 获取表版本号
	 * @return
	 */
	public abstract int getTableVersion();
	/**
	 * 删除表
	 * @return
	 */
	public abstract String delTableSql();
}
