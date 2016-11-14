package com.jzg.jzgoto.phone.models.business.valuation.sell;

import java.io.Serializable;

public class RequestValFragmentHistory implements Serializable{

	private static final long serialVersionUID = 1L;
	/**
	 *  "StyleID": "1000",
	 "ImageUrl": "http://img2.bitautoimg.com/autoalbum/files/20090904/053/200909041409013587_952781_4.jpg",
	 "ProvID": "2",
	 "CityID": "201",
	 "StyleName": "日产骐达 2008款 1.6L 手动 智能型",
	 "UpdateTime": "2016/6/3 0:00:00",
	 "AppraiseType": "1",
	 "OperationType": "1",
	 "OperationTypeName": "进行卖车估值"
	 "CityName": "北京",
	 "Mils": "5",
	 "regdate": "2015-06-01",
	 "RDate": "2015年06月",
	 "ModelName": "宝马1系(进口)",
	 "MinYEAR":"2015-06",
	 "MaxYEAR":"2016-05"

	 */
	private String StyleID;
	private String ImageUrl;
	private String ProvID;
	private String CityID;
	private String StyleName;
	private String UpdateTime;
	private String AppraiseType;//1-快速评估	2-精确评估
	private String OperationType;//1-卖车估值	2-买车估值
	private String OperationTypeName;
	private String CityName;
	private String Mils;
	private String regdate;
	private String DamageID;
	private String RDate;
	private String ModelName;
	private String MinYEAR;
	private String MaxYEAR;

	private String styleNameOther;
	private int styleYear;
	private String styleNowMsrp;
	private int styleItemColor;
	private String styleFullName;
	private int styleBrandId;
	private String styleBrandName;
	private int styleModeId;
	private String styleModeName;
	private String styleManufacturerName;
	private int styleFontColor;
	private String styleModelimgpath;



	public int getStyleBrandId() {
		return styleBrandId;
	}
	public void setStyleBrandId(int styleBrandId) {
		this.styleBrandId = styleBrandId;
	}
	public String getStyleBrandName() {
		return styleBrandName;
	}
	public void setStyleBrandName(String styleBrandName) {
		this.styleBrandName = styleBrandName;
	}
	public int getStyleModeId() {
		return styleModeId;
	}
	public void setStyleModeId(int styleModeId) {
		this.styleModeId = styleModeId;
	}
	public String getStyleModeName() {
		return styleModeName;
	}
	public void setStyleModeName(String styleModeName) {
		this.styleModeName = styleModeName;
	}
	public String getStyleManufacturerName() {
		return styleManufacturerName;
	}
	public void setStyleManufacturerName(String styleManufacturerName) {
		this.styleManufacturerName = styleManufacturerName;
	}
	public int getStyleFontColor() {
		return styleFontColor;
	}
	public void setStyleFontColor(int styleFontColor) {
		this.styleFontColor = styleFontColor;
	}
	public String getStyleModelimgpath() {
		return styleModelimgpath;
	}
	public void setStyleModelimgpath(String styleModelimgpath) {
		this.styleModelimgpath = styleModelimgpath;
	}
	public int getStyleYear() {
		return styleYear;
	}
	public void setStyleYear(int styleYear) {
		this.styleYear = styleYear;
	}
	public String getStyleNowMsrp() {
		return styleNowMsrp;
	}
	public void setStyleNowMsrp(String styleNowMsrp) {
		this.styleNowMsrp = styleNowMsrp;
	}
	public int getStyleItemColor() {
		return styleItemColor;
	}
	public void setStyleItemColor(int styleItemColor) {
		this.styleItemColor = styleItemColor;
	}

	public String getStyleName() {
		return StyleName;
	}
	public void setStyleName(String styleName) {
		StyleName = styleName;
	}
	public String getStyleNameOther() {
		return styleNameOther;
	}
	public void setStyleNameOther(String styleNameOther) {
		this.styleNameOther = styleNameOther;
	}
	public String getStyleFullName() {
		return styleFullName;
	}
	public void setStyleFullName(String styleFullName) {
		this.styleFullName = styleFullName;
	}
	public String getStyleID() {
		return StyleID;
	}
	public void setStyleID(String styleID) {
		StyleID = styleID;
	}
	public String getImageUrl() {
		return ImageUrl;
	}
	public void setImageUrl(String imageUrl) {
		ImageUrl = imageUrl;
	}
	public String getProvID() {
		return ProvID;
	}
	public void setProvID(String provID) {
		ProvID = provID;
	}
	public String getCityID() {
		return CityID;
	}
	public void setCityID(String cityID) {
		CityID = cityID;
	}
	public String getUpdateTime() {
		return UpdateTime;
	}
	public void setUpdateTime(String updateTime) {
		UpdateTime = updateTime;
	}
	public String getAppraiseType() {
		return AppraiseType;
	}
	public void setAppraiseType(String appraiseType) {
		AppraiseType = appraiseType;
	}
	public String getOperationType() {
		return OperationType;
	}
	public void setOperationType(String operationType) {
		OperationType = operationType;
	}
	public String getOperationTypeName() {
		return OperationTypeName;
	}
	public void setOperationTypeName(String operationTypeName) {
		OperationTypeName = operationTypeName;
	}

	public String getCityName() {
		return CityName;
	}
	public void setCityName(String cityName) {
		CityName = cityName;
	}
	public String getMils() {
		return Mils;
	}
	public void setMils(String mils) {
		Mils = mils;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getDamageID() {
		return DamageID;
	}
	public void setDamageID(String damageID) {
		DamageID = damageID;
	}

	public String getRDate() {
		return RDate;
	}
	public void setRDate(String rDate) {
		RDate = rDate;
	}
	public String getModelName() {
		return ModelName;
	}
	public void setModelName(String modelName) {
		ModelName = modelName;
	}

	public String getMinYEAR() {
		return MinYEAR;
	}
	public void setMinYEAR(String minYEAR) {
		MinYEAR = minYEAR;
	}
	public String getMaxYEAR() {
		return MaxYEAR;
	}
	public void setMaxYEAR(String maxYEAR) {
		MaxYEAR = maxYEAR;
	}
	@Override
	public String toString() {
		return "RequestValFragmentHistory [StyleID=" + StyleID + ", ImageUrl="
				+ ImageUrl + ", ProvID=" + ProvID + ", CityID=" + CityID
				+ ", StyleName=" + StyleName + ", UpdateTime=" + UpdateTime
				+ ", AppraiseType=" + AppraiseType + ", OperationType="
				+ OperationType + ", OperationTypeName=" + OperationTypeName
				+ ", CityName=" + CityName + ", Mils=" + Mils + ", regdate="
				+ regdate + ", DamageID=" + DamageID + ", RDate=" + RDate
				+ ", ModelName=" + ModelName + ", MinYEAR=" + MinYEAR
				+ ", MaxYEAR=" + MaxYEAR
				+ ", styleName=" + styleNameOther + ", styleYear=" + styleYear
				+ ", styleNowMsrp=" + styleNowMsrp + ", styleItemColor="
				+ styleItemColor + ", styleFullName=" + styleFullName + "]";
	}

}
