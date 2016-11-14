package com.jzg.jzgoto.phone.models.business.valuation.sell;

import java.io.Serializable;

public class RequestValFragmentBanner implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	"ID": "4",
//    "BannerName": "2",
//    "BannerUrl": "www.baidu.com",
//    "ImageUrl": "http://imageup.jingzhengu.com/2016/06/07/1ed3fb4c-67f7-4e7c-8285-509b3f50b045_501.jpg"
	private String ID;
	private String BannerName;
	private String BannerUrl;
	private String ImageUrl;
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getBannerName() {
		return BannerName;
	}
	public void setBannerName(String bannerName) {
		BannerName = bannerName;
	}
	public String getBannerUrl() {
		return BannerUrl;
	}
	public void setBannerUrl(String bannerUrl) {
		BannerUrl = bannerUrl;
	}
	public String getImageUrl() {
		return ImageUrl;
	}
	public void setImageUrl(String imageUrl) {
		ImageUrl = imageUrl;
	}
	@Override
	public String toString() {
		return "RequestValFragmentBanner [ID=" + ID + ", BannerName="
				+ BannerName + ", BannerUrl=" + BannerUrl + ", ImageUrl="
				+ ImageUrl + "]";
	}
	
}
