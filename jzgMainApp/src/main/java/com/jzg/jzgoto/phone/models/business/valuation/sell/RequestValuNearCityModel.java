package com.jzg.jzgoto.phone.models.business.valuation.sell;

import java.io.Serializable;

/**
 * 估值结果	附近城市 柱状图
 * @author wangying
 * @date 2016年6月12日
 * @className
 */
public class RequestValuNearCityModel implements Serializable{

	/**
	 * "ProvId": "2",
            "ProvName": "北京",
            "CityId": "201",
            "CityName": "北京",
            "Sum": "100",
            "Price ": "24.3"
	 */
	private static final long serialVersionUID = 1L;
	private String ProvId;
	private String ProvName;
	private String CityId;
	private String CityName;
	private String Sum;
	private String Price;
	
	public String getProvId() {
		return ProvId;
	}
	public void setProvId(String provId) {
		ProvId = provId;
	}
	public String getProvName() {
		return ProvName;
	}
	public void setProvName(String provName) {
		ProvName = provName;
	}
	public String getCityId() {
		return CityId;
	}
	public void setCityId(String cityId) {
		CityId = cityId;
	}
	public String getCityName() {
		return CityName;
	}
	public void setCityName(String cityName) {
		CityName = cityName;
	}
	public String getSum() {
		return Sum;
	}
	public void setSum(String sum) {
		Sum = sum;
	}
	public String getPrice() {
		return Price;
	}
	public void setPrice(String price) {
		Price = price;
	}
	@Override
	public String toString() {
		return "RequestValuNearCityModel [ProvId=" + ProvId + ", ProvName="
				+ ProvName + ", CityId=" + CityId + ", CityName=" + CityName
				+ ", Sum=" + Sum + ", Price=" + Price + "]";
	}
	
}
