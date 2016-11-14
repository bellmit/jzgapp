package com.jzg.jzgoto.phone.models.business.car;

import java.io.Serializable;
/**
 * @Description: 车品牌
 * @Package com.jzg.jzgoto.phone.models.business.car CarMakeBean.java
 * @author gf
 * @date 2016-6-16 下午3:20:52
 */
public class CarMakeBean implements Serializable {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	/** 品牌组名 **/
	private String GroupName;
	/** 品牌ID **/
	private String MakeId;
	/** 品牌LOGO **/
	private String MakeLogo;
	/** 品牌名称 **/
	private String MakeName;
	/** 是否是新车  **/
	private String IsNewCar = "";
	/** 是否为更新状态  1添加，2修改，3，删除 **/
	private String Status;
	public String getGroupName() {
		return GroupName;
	}
	public void setGroupName(String groupName) {
		GroupName = groupName;
	}
	public String getMakeId() {
		return MakeId;
	}
	public void setMakeId(String makeId) {
		MakeId = makeId;
	}
	public String getMakeLogo() {
		return MakeLogo;
	}
	public void setMakeLogo(String makeLogo) {
		MakeLogo = makeLogo;
	}
	public String getMakeName() {
		return MakeName;
	}
	public void setMakeName(String makeName) {
		MakeName = makeName;
	}
	public String getIsNewCar() {
		return IsNewCar;
	}
	public void setIsNewCar(String isNewCar) {
		IsNewCar = isNewCar;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	
}
