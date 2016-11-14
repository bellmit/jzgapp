package com.jzg.jzgoto.phone.models.business.valuation.sell;

import java.io.Serializable;

/**
 * 历史成交记录ItemData
 * @author wangying
 * @date 2016年6月14日
 * @className
 */
public class RequestHistoryDealItemModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	"FullName": "大众途锐(进口) 2010款 4.2L 自动 V8限量顶配型",
//    "PurchaseDate": "2009.10.01",
//    "Mileage": "80000",
//    "ListingPrice": "28.67",
//    "PublishDate": "2016.05.21",
//    "CityName": "宁波"
	private String FullName;
	private String PurchaseDate;
	private String Mileage;
	private String ListingPrice;
	private String PublishDate;
	private String CityName;
	public String getFullName() {
		return FullName;
	}
	public void setFullName(String fullName) {
		FullName = fullName;
	}
	public String getPurchaseDate() {
		return PurchaseDate;
	}
	public void setPurchaseDate(String purchaseDate) {
		PurchaseDate = purchaseDate;
	}
	public String getMileage() {
		return Mileage;
	}
	public void setMileage(String mileage) {
		Mileage = mileage;
	}
	public String getListingPrice() {
		return ListingPrice;
	}
	public void setListingPrice(String listingPrice) {
		ListingPrice = listingPrice;
	}
	public String getPublishDate() {
		return PublishDate;
	}
	public void setPublishDate(String publishDate) {
		PublishDate = publishDate;
	}
	public String getCityName() {
		return CityName;
	}
	public void setCityName(String cityName) {
		CityName = cityName;
	}
	@Override
	public String toString() {
		return "RequestHistoryDealItemModel [FullName=" + FullName
				+ ", PurchaseDate=" + PurchaseDate + ", Mileage=" + Mileage
				+ ", ListingPrice=" + ListingPrice + ", PublishDate="
				+ PublishDate + ", CityName=" + CityName + "]";
	}
	
}
