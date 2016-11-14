package com.jzg.jzgoto.phone.models.business.car;

import java.io.Serializable;
/**
 * @Description: 汽车车型
 * @Package com.jzg.jzgoto.phone.models.business.car CarStyleBean.java
 * @author gf
 * @date 2016-6-16 下午3:22:35
 */
public class CarStyleBean implements Serializable {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	/** 车系ID **/
	private String ModelId;
	/** 车型ID **/
	private String Id;
	/** 车型名称 **/
	private String Name;
	/** 年限 **/
	private String Year;
	private String NextYear;
	/** 售价 **/
	private String NowMsrp;
	/** 车型全称 **/
	private String FullName;
	/** 是否为更新状态  1添加，2修改，3，删除 **/
	private String Status;
	/** 新旧车状态 **/
	private String ProductionSaleStatus;
	public String getModelId() {
		return ModelId;
	}
	public void setModelId(String modelId) {
		ModelId = modelId;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getYear() {
		return Year;
	}
	public void setYear(String year) {
		Year = year;
	}
	public String getNextYear() {
		return NextYear;
	}
	public void setNextYear(String nextYear) {
		NextYear = nextYear;
	}
	public String getNowMsrp() {
		return NowMsrp;
	}
	public void setNowMsrp(String nowMsrp) {
		NowMsrp = nowMsrp;
	}
	public String getFullName() {
		return FullName;
	}
	public void setFullName(String fullName) {
		FullName = fullName;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getProductionSaleStatus() {
		return ProductionSaleStatus;
	}
	public void setProductionSaleStatus(String productionSaleStatus) {
		ProductionSaleStatus = productionSaleStatus;
	}
}
