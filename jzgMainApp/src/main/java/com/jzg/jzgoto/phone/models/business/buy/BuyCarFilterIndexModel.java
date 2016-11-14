package com.jzg.jzgoto.phone.models.business.buy;

import java.io.Serializable;

public class BuyCarFilterIndexModel implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private int sourceType = 0;
	private int carStyleIndex =0;
	private String carStyleText = "不限";
	private int biansuxiang = 0;
	private int cheling = 0;
	private int licheng = 0;
	private int pailiang = 0;
	private int sortIndex = 0;//排序方式
	private String brandName = "";
	private String modeName = "";
	private String priceText = "";//自定义价格区间(其他界面跳转带入)
	private int priceIndex = 0;//价格选中位置
	private int carPlatformIndex = 0;//平台选中位置
	private int carSeatIndex = 0;//座位数选中位置
	private int countryIndex = 0;//国别选中位置
	private BuyCarListParams params;

	public int getCarSeatIndex() {
		return carSeatIndex;
	}

	public void setCarSeatIndex(int carSeatIndex) {
		this.carSeatIndex = carSeatIndex;
	}

	public int getCountryIndex() {
		return countryIndex;
	}

	public void setCountryIndex(int countryIndex) {
		this.countryIndex = countryIndex;
	}

	public int getCarPlatformIndex() {
		return carPlatformIndex;
	}

	public void setCarPlatformIndex(int carPlatformIndex) {
		this.carPlatformIndex = carPlatformIndex;
	}

	public int getSourceType() {
		return sourceType;
	}
	public void setSourceType(int sourceType) {
		this.sourceType = sourceType;
	}
	public int getCarStyleIndex() {
		return carStyleIndex;
	}
	public void setCarStyleIndex(int carStyleIndex) {
		this.carStyleIndex = carStyleIndex;
	}
	public String getCarStyleText() {
		return carStyleText;
	}
	public void setCarStyleText(String carStyleText) {
		this.carStyleText = carStyleText;
	}
	public int getBiansuxiang() {
		return biansuxiang;
	}
	public void setBiansuxiang(int biansuxiang) {
		this.biansuxiang = biansuxiang;
	}
	public int getCheling() {
		return cheling;
	}
	public void setCheling(int cheling) {
		this.cheling = cheling;
	}
	public int getLicheng() {
		return licheng;
	}
	public void setLicheng(int licheng) {
		this.licheng = licheng;
	}
	public int getPailiang() {
		return pailiang;
	}
	public void setPailiang(int pailiang) {
		this.pailiang = pailiang;
	}

	public int getSortIndex() {
		return sortIndex;
	}

	public void setSortIndex(int sortIndex) {
		this.sortIndex = sortIndex;
	}

	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getModeName() {
		return modeName;
	}
	public void setModeName(String modeName) {
		this.modeName = modeName;
	}
	public BuyCarListParams getParams() {
		return params;
	}
	public void setParams(BuyCarListParams params) {
		this.params = params;
	}

	public int getPriceIndex() {
		return priceIndex;
	}
	public void setPriceIndex(int priceIndex) {
		this.priceIndex = priceIndex;
	}

	public String getPriceText() {
		return priceText;
	}

	public void setPriceText(String priceText) {
		this.priceText = priceText;
	}

	@Override
	public String toString() {
		return "BuyCarFilterIndexModel{" +
				"sourceType=" + sourceType +
				", carStyleIndex=" + carStyleIndex +
				", carStyleText='" + carStyleText + '\'' +
				", biansuxiang=" + biansuxiang +
				", cheling=" + cheling +
				", licheng=" + licheng +
				", pailiang=" + pailiang +
				", sortIndex=" + sortIndex +
				", brandName='" + brandName + '\'' +
				", modeName='" + modeName + '\'' +
				", priceText='" + priceText + '\'' +
				", priceIndex=" + priceIndex +
				", carPlatformIndex=" + carPlatformIndex +
				", carSeatIndex=" + carSeatIndex +
				", countryIndex=" + countryIndex +
				", params=" + params +
				'}';
	}
}
