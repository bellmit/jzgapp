package com.jzg.jzgoto.phone.models.business.buy;

import java.io.Serializable;

public class SimilarItemData implements Serializable{

	private static final long serialVersionUID = 1L;
//	  "CarSourceFrom": 4,
//      "CarSourceFromName": "大搜车",
//      "CarSourceID": 529367,
//      "CarSourceImageUrl": "http://f.souche.com/18456593550/708174720ae6a00ab28feb430ad5123e?watermark/1/image/aHR0cDovL29wZW4uc291Y2hlLmNvbS9kYXNvdWNoZS5wbmc=/gravity/SouthWest/dx/24/dy/24",
//      "FullName": "标致207两厢 2011款 1.4L 手动 两厢驭乐版",
//      "PersonalBusiness": 2,
//      "Mileage": "3.26万公里",
//      "ReleaseTime": "2013年01月",
//      "PublishTime": "26天前",
//      "SellPrice": "4.31",
//      "ProvName": "安徽",
//      "CityName": "合肥"

	private String CarSourceFrom;
	private String CarSourceFromName;
	private String CarSourceID;
	private String CarSourceImageUrl;
	private String FullName;
	private String PersonalBusiness;
	private String Mileage;
	private String ReleaseTime;
	private String PublishTime;
	private String SellPrice;
	private String ProvName;
	private String CityName;
	public String getCarSourceFrom() {
		return CarSourceFrom;
	}
	public void setCarSourceFrom(String carSourceFrom) {
		CarSourceFrom = carSourceFrom;
	}
	public String getCarSourceFromName() {
		return CarSourceFromName;
	}
	public void setCarSourceFromName(String carSourceFromName) {
		CarSourceFromName = carSourceFromName;
	}
	public String getCarSourceID() {
		return CarSourceID;
	}
	public void setCarSourceID(String carSourceID) {
		CarSourceID = carSourceID;
	}
	public String getCarSourceImageUrl() {
		return CarSourceImageUrl;
	}
	public void setCarSourceImageUrl(String carSourceImageUrl) {
		CarSourceImageUrl = carSourceImageUrl;
	}
	public String getFullName() {
		return FullName;
	}
	public void setFullName(String fullName) {
		FullName = fullName;
	}
	public String getPersonalBusiness() {
		return PersonalBusiness;
	}
	public void setPersonalBusiness(String personalBusiness) {
		PersonalBusiness = personalBusiness;
	}
	public String getMileage() {
		return Mileage;
	}
	public void setMileage(String mileage) {
		Mileage = mileage;
	}
	public String getReleaseTime() {
		return ReleaseTime;
	}
	public void setReleaseTime(String releaseTime) {
		ReleaseTime = releaseTime;
	}
	public String getPublishTime() {
		return PublishTime;
	}
	public void setPublishTime(String publishTime) {
		PublishTime = publishTime;
	}
	public String getSellPrice() {
		return SellPrice;
	}
	public void setSellPrice(String sellPrice) {
		SellPrice = sellPrice;
	}
	public String getProvName() {
		return ProvName;
	}
	public void setProvName(String provName) {
		ProvName = provName;
	}
	public String getCityName() {
		return CityName;
	}
	public void setCityName(String cityName) {
		CityName = cityName;
	}
	@Override
	public String toString() {
		return "SimilarItemData [CarSourceFrom=" + CarSourceFrom
				+ ", CarSourceFromName=" + CarSourceFromName + ", CarSourceID="
				+ CarSourceID + ", CarSourceImageUrl=" + CarSourceImageUrl
				+ ", FullName=" + FullName + ", PersonalBusiness="
				+ PersonalBusiness + ", Mileage=" + Mileage + ", ReleaseTime="
				+ ReleaseTime + ", PublishTime=" + PublishTime + ", SellPrice="
				+ SellPrice + ", ProvName=" + ProvName + ", CityName="
				+ CityName + "]";
	}
}
