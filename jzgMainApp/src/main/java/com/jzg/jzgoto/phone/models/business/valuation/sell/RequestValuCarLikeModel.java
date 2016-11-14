package com.jzg.jzgoto.phone.models.business.valuation.sell;

import java.io.Serializable;

/**
 * 估值结果，猜你喜欢
 * 
 * @author wangying
 * @date 2016年6月12日
 * @className
 */
public class RequestValuCarLikeModel implements Serializable{
	private static final long serialVersionUID = 1L;
	// "CarSourceID": "1491",
	// "CarSourceFrom": "2",
	// "imgurl":
	// "http://imageup.jingzhengu.com/2016/05/10/22c631f6-6d10-42bb-b6c5-bc0767515c6b_917.jpg",
	// "StyleFullName": "宝马2系运动旅行车(进口) 2015款 1.5T 自动 218i运动设计套装",
	// "sellprice": "12.00",
	// "Mileage": "12.00万公里",
	// "RegDate": "2014-12",
	// "CsourceTypes": "个人",
	// "didian": "北京"
	private String CarSourceID;
	private String CarSourceFrom;
	private String imgurl;
	private String StyleFullName;
	private String sellprice;
	private String Mileage;
	private String RegDate;
	private String CsourceTypes;
	private String didian;
	
	public String getCarSourceID() {
		return CarSourceID;
	}
	public void setCarSourceID(String carSourceID) {
		CarSourceID = carSourceID;
	}
	public String getCarSourceFrom() {
		return CarSourceFrom;
	}
	public void setCarSourceFrom(String carSourceFrom) {
		CarSourceFrom = carSourceFrom;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public String getStyleFullName() {
		return StyleFullName;
	}
	public void setStyleFullName(String styleFullName) {
		StyleFullName = styleFullName;
	}
	public String getSellprice() {
		return sellprice;
	}
	public void setSellprice(String sellprice) {
		this.sellprice = sellprice;
	}
	public String getMileage() {
		return Mileage;
	}
	public void setMileage(String mileage) {
		Mileage = mileage;
	}
	public String getRegDate() {
		return RegDate;
	}
	public void setRegDate(String regDate) {
		RegDate = regDate;
	}
	public String getCsourceTypes() {
		return CsourceTypes;
	}
	public void setCsourceTypes(String csourceTypes) {
		CsourceTypes = csourceTypes;
	}
	public String getDidian() {
		return didian;
	}
	public void setDidian(String didian) {
		this.didian = didian;
	}
	@Override
	public String toString() {
		return "RequestValCarLikeModel [CarSourceID=" + CarSourceID
				+ ", CarSourceFrom=" + CarSourceFrom + ", imgurl=" + imgurl
				+ ", StyleFullName=" + StyleFullName + ", sellprice="
				+ sellprice + ", Mileage=" + Mileage + ", RegDate=" + RegDate
				+ ", CsourceTypes=" + CsourceTypes + ", didian=" + didian + "]";
	}

}
