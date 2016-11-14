package com.jzg.jzgoto.phone.models.business.sell;

import java.io.Serializable;

import com.jzg.pricechange.phone.JzgCarChooseStyle;

public class SellModel implements Serializable{
	
	//车型
	private JzgCarChooseStyle sellCar = null;
	//上牌时间
	private String regdate = "";
	//行驶里程
	private String mileage = "";
	//城市id
	private String cityid = "";
	//城市名称
	private String cityname = "";
	public JzgCarChooseStyle getSellCar() {
		return sellCar;
	}
	public void setSellCar(JzgCarChooseStyle sellCar) {
		this.sellCar = sellCar;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getMileage() {
		return mileage;
	}
	public void setMileage(String mileage) {
		this.mileage = mileage;
	}
	public String getCityid() {
		return cityid;
	}
	public void setCityid(String cityid) {
		this.cityid = cityid;
	}
	public String getCityname() {
		return cityname;
	}
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	

	
}
