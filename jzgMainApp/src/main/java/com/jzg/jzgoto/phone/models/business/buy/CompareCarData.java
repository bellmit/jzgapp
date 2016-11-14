package com.jzg.jzgoto.phone.models.business.buy;

import java.io.Serializable;
import java.util.List;

public class CompareCarData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String Csid;		//"Csid": "1658",
	private String csfrom;		//"csfrom": "1",
	private String StyleId;		//"StyleId": "116744",
	private String ImageUrl;		//"ImageUrl": "http://imageup.jingzhengu.com/2015/12/04/img19121184_501.jpg",
	private String MakeName;		//"MakeName": "奥迪",
	private String StyleFullName;		//"StyleFullName": "奥迪Q5 2016款 2.0T 自动 40TFSI 舒适型",
	private String NowMsrp;		//"NowMsrp": "47.90万",
	private String SellPrice;		//"SellPrice": "13.00万",
	private String CarType;		//"CarType": "SUV",
	private String MonthCostPrice;		//"MonthCostPrice": "5087.44元",
	private String MonthZhejiu;		//"MonthZhejiu": "3986.86元",
	private String MonthZongCehengben;		//"MonthZongCehengben": "1100.58元",
	private String Mileage;		//"Mileage": "5.0万公里",
	private String RegDate;		//"RegDate": "2015年01月",
	private String RegCity;		//"RegCity": "北京",
	private String CarsourceType;		//"CarsourceType": "估爷",
	private String biansuqi;		//"biansuqi": "",
	private String pailiang;		//"pailiang": "",
	private List<CompareNameAndData> CansuList;		//"CansuList":[] 
	private List<CompareNameAndData> PeizhiList;		//"PeizhiList":[]
	public String getCsid() {
		return Csid;
	}
	public void setCsid(String csid) {
		Csid = csid;
	}
	public String getCsfrom() {
		return csfrom;
	}
	public void setCsfrom(String csfrom) {
		this.csfrom = csfrom;
	}
	public String getStyleId() {
		return StyleId;
	}
	public void setStyleId(String styleId) {
		StyleId = styleId;
	}
	public String getImageUrl() {
		return ImageUrl;
	}
	public void setImageUrl(String imageUrl) {
		ImageUrl = imageUrl;
	}
	public String getMakeName() {
		return MakeName;
	}
	public void setMakeName(String makeName) {
		MakeName = makeName;
	}
	public String getStyleFullName() {
		return StyleFullName;
	}
	public void setStyleFullName(String styleFullName) {
		StyleFullName = styleFullName;
	}
	public String getNowMsrp() {
		return NowMsrp;
	}
	public void setNowMsrp(String nowMsrp) {
		NowMsrp = nowMsrp;
	}
	public String getSellPrice() {
		return SellPrice;
	}
	public void setSellPrice(String sellPrice) {
		SellPrice = sellPrice;
	}
	public String getCarType() {
		return CarType;
	}
	public void setCarType(String carType) {
		CarType = carType;
	}
	public String getMonthCostPrice() {
		return MonthCostPrice;
	}
	public void setMonthCostPrice(String monthCostPrice) {
		MonthCostPrice = monthCostPrice;
	}
	public String getMonthZhejiu() {
		return MonthZhejiu;
	}
	public void setMonthZhejiu(String monthZhejiu) {
		MonthZhejiu = monthZhejiu;
	}
	public String getMonthZongCehengben() {
		return MonthZongCehengben;
	}
	public void setMonthZongCehengben(String monthZongCehengben) {
		MonthZongCehengben = monthZongCehengben;
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
	public String getRegCity() {
		return RegCity;
	}
	public void setRegCity(String regCity) {
		RegCity = regCity;
	}
	public String getCarsourceType() {
		return CarsourceType;
	}
	public void setCarsourceType(String carsourceType) {
		CarsourceType = carsourceType;
	}
	public String getBiansuqi() {
		return biansuqi;
	}
	public void setBiansuqi(String biansuqi) {
		this.biansuqi = biansuqi;
	}
	public String getPailiang() {
		return pailiang;
	}
	public void setPailiang(String pailiang) {
		this.pailiang = pailiang;
	}
	public List<CompareNameAndData> getCansuList() {
		return CansuList;
	}
	public void setCansuList(List<CompareNameAndData> cansuList) {
		CansuList = cansuList;
	}
	public List<CompareNameAndData> getPeizhiList() {
		return PeizhiList;
	}
	public void setPeizhiList(List<CompareNameAndData> peizhiList) {
		PeizhiList = peizhiList;
	}
	
	@Override
	public String toString() {
		return "CompareCarData [Csid=" + Csid + ", csfrom=" + csfrom
				+ ", StyleId=" + StyleId + ", ImageUrl=" + ImageUrl
				+ ", MakeName=" + MakeName + ", StyleFullName=" + StyleFullName
				+ ", NowMsrp=" + NowMsrp + ", SellPrice=" + SellPrice
				+ ", CarType=" + CarType + ", MonthCostPrice=" + MonthCostPrice
				+ ", MonthZhejiu=" + MonthZhejiu + ", MonthZongCehengben="
				+ MonthZongCehengben + ", Mileage=" + Mileage + ", RegDate="
				+ RegDate + ", RegCity=" + RegCity + ", CarsourceType="
				+ CarsourceType + ", biansuqi=" + biansuqi + ", pailiang="
				+ pailiang + ", CansuList=" + CansuList + ", PeizhiList="
				+ PeizhiList + "]";
	}
	
}
