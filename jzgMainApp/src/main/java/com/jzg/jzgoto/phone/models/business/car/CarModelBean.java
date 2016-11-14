package com.jzg.jzgoto.phone.models.business.car;

import java.io.Serializable;
/**
 * @Description: 汽车系列
 * @Package com.jzg.jzgoto.phone.models.business.car CarModelBean.java
 * @author gf
 * @date 2016-6-16 下午3:21:38
 */
public class CarModelBean implements Serializable {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	/** 品牌ID **/
	private String MakeId;
	/** 制造商ID **/
	private String ManufacturerId;
	/** 制造商名称 **/
	private String ManufacturerName;
	/** 车系ID **/
	private String Id;
	/** 车系名称 **/
	private String Name;
	/** 车系图片 **/
	private String modelimgpath;
	/** 是否为更新状态  1添加，2修改，3，删除 **/
	private String Status;
	/** 是否是新车  **/
	private String IsNewCar = "";
	public String getMakeId() {
		return MakeId;
	}
	public void setMakeId(String makeId) {
		MakeId = makeId;
	}
	public String getManufacturerId() {
		return ManufacturerId;
	}
	public void setManufacturerId(String manufacturerId) {
		ManufacturerId = manufacturerId;
	}
	public String getManufacturerName() {
		return ManufacturerName;
	}
	public void setManufacturerName(String manufacturerName) {
		ManufacturerName = manufacturerName;
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
	public String getModelimgpath() {
		return modelimgpath;
	}
	public void setModelimgpath(String modelimgpath) {
		this.modelimgpath = modelimgpath;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getIsNewCar() {
		return IsNewCar;
	}
	public void setIsNewCar(String isNewCar) {
		IsNewCar = isNewCar;
	}
	
}
