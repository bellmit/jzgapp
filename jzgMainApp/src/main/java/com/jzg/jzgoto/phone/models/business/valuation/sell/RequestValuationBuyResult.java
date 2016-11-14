package com.jzg.jzgoto.phone.models.business.valuation.sell;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.business.valuation.buy.GetNewCarResultModels.NewCar;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;

public class RequestValuationBuyResult extends BaseResultModels {


//   "status": 100,
//    "msg": "",
//    "ReportID": "1184301",
//    "MakeId": "15",
//    "ModelId": "2971",
//    "StyleId": 15190,
//    "ImgUrl": "http://img1.bitautoimg.com/autoalbum/files/20131025/373/15394937374737_2882008_4.jpg",
//    "FullName": "比亚迪L3 2012款 1.5L 手动 舒适型",
//    "RegDate": "2015年01月",
//	  RegDateTime	2015-01-01
//    "Mileage": 2.1,
//    "ProvId": "2",
//    "CityId": "201",
//    "CityName": "北京",
//    "MakeName": "比亚迪",
//    "ModelName": "L3",
//    "NowMsrp": 5.39,
//    "CurrentModelLevelId": "3",
//    "CurrentModelLevelName": "紧凑型车",
//    "BaoZhilvRank": "2",
//    "BaoZhilvCityId": "201",
//    "BaoZhilvCityName": "北京",
//    "BaoZhilvLevel": "10",
//    "BaoZhilvLevelName": "中大型SUV",
//    "B2CLowPrice": "0",
//    "B2CUpPrice": "0",
//    "C2CLowPrice": "0",
//    "C2CUpPrice": "0",
//    "HistoricalAveragePrice": [历史成交价	HistoryChengjiaoPrice],
//    "NearCity": [RequestValuNearCityModel],
//   "Artical": [RequestValuTipsModel],
//   "PageIndex_KBGood": "1",
//   "KoubeiList_Good": [RequestValuKoubeiModel],
//   "PageIndex_KBBad": "1",
//   "KoubeiList_Bad": [RequestValuKoubeiModel],
//   "CostYear": "14247",
//   "InsuranceYear": "0.2943",
//   "MaintainYear": "0.1066",
//   "DepreciationYear": "0.4421",
//   "OilYear": "0.5467",
//   "TaxYear": "0.035",
//   "PageIndex_TJOldCar": "1",
//   "CarOldList": [RequestValCarLikeModel 二手车源推荐],
//   "PageIndex_TJNewCar": "1",
//   "CarNewList": [RequestValCarLikeModel 新车列表],
//   "PageIndex_TJLikeCar": "1",
//   "CarLikeList": [RequestValCarLikeModel	猜你喜欢]
	private String ReportID;
	private String MakeId;
	private String ModelId;
	private String StyleId;
	private String ImgUrl;
	private String FullName;
	private String RegDate;
	private String RegDateTime;
	private String Mileage;
	private String ProvId;
	private String CityId;
	private String CityName;
	private String NowMsrp;
	private String MakeName;
	private String ModelName;
	private String CurrentModelLevelId;
	private String CurrentModelLevelName;
	private String BaoZhilvRank;
	private String BaoZhilvCityId;
	private String BaoZhilvCityName;
	private String BaoZhilvLevel;
	private String BaoZhilvLevelName;
	private String B2CLowPrice;
	private String B2CUpPrice;
	private String C2CLowPrice;
	private String C2CUpPrice;
	private List<HistoryChengjiaoPrice> HistoricalAveragePrice
						= new ArrayList<HistoryChengjiaoPrice>();
	private List<RequestValuNearCityModel> NearCity = new ArrayList<RequestValuNearCityModel>();
	
	private List<RequestValuTipsModel> Artical = new ArrayList<RequestValuTipsModel>();
	private String PageIndex_KBGood;
	private List<RequestValuKoubeiModel> KoubeiList_Good = new ArrayList<RequestValuKoubeiModel>();
	private String PageIndex_KBBad;
	private List<RequestValuKoubeiModel> KoubeiList_Bad = new ArrayList<RequestValuKoubeiModel>();
	
	private String CostYear;
	private String InsuranceYear;
	private String MaintainYear;
	private String DepreciationYear;
	private String OilYear;
	private String TaxYear;
	private String PageIndex_TJOldCar;
	private List<RequestValuCarLikeModel> CarOldList = new ArrayList<RequestValuCarLikeModel>();
	private String PageIndex_TJNewCar;
	private List<NewCar> CarNewList = new ArrayList<NewCar>();
	private String PageIndex_TJLikeCar;
	private List<RequestValuCarLikeModel> CarLikeList = new ArrayList<RequestValuCarLikeModel>();

	private static final long serialVersionUID = 1L;

	@Override
	public void setResult(Object obj) {
		System.out.println("买车估值结果——"+obj.toString());
		Gson gson = new Gson();
		RequestValuationBuyResult result = gson.fromJson(obj.toString(), RequestValuationBuyResult.class);
		setStatus(result.getStatus());
		setMsg(result.getMsg());
		if(result.getStatus()==SUCCESS){
			setReportID(result.getReportID());
			setMakeId(result.getMakeId());
			setModelId(result.getModelId());
			setStyleId(result.getStyleId());
			setImgUrl(result.getImgUrl());
			setFullName(result.getFullName());
			setRegDate(result.getRegDate());
			setRegDateTime(result.getRegDateTime());
			setMileage(result.getMileage());
			setProvId(result.getProvId());
			setCityId(result.getCityId());
			setCityName(result.getCityName());
			setNowMsrp(result.getNowMsrp());
			setMakeName(result.getMakeName());
			setModelName(result.getModelName());
			setCurrentModelLevelId(result.getCurrentModelLevelId());
			setCurrentModelLevelName(result.getCurrentModelLevelName());
			setBaoZhilvRank(result.getBaoZhilvRank());
			setBaoZhilvCityId(result.getBaoZhilvCityId());
			setBaoZhilvCityName(result.getBaoZhilvCityName());
			setBaoZhilvLevel(result.getBaoZhilvLevel());
			setBaoZhilvLevelName(result.getBaoZhilvLevelName());
			setB2CLowPrice(result.getB2CLowPrice());
			setB2CUpPrice(result.getB2CUpPrice());
			setC2CLowPrice(result.getC2CLowPrice());
			setC2CUpPrice(result.getC2CUpPrice());
			HistoricalAveragePrice.addAll(result.getHistoricalAveragePrice());
			NearCity.addAll(result.getNearCity());
			Artical.addAll(result.getArtical());
			setPageIndex_KBGood(result.getPageIndex_KBGood());
			KoubeiList_Good.addAll(result.getKoubeiList_Good());
			setPageIndex_KBBad(result.getPageIndex_KBBad());
			KoubeiList_Bad.addAll(result.getKoubeiList_Bad());
			setCostYear(result.getCostYear());
			setInsuranceYear(result.getInsuranceYear());
			setMaintainYear(result.getMaintainYear());
			setDepreciationYear(result.getDepreciationYear());
			setOilYear(result.getOilYear());
			setTaxYear(result.getTaxYear());
			setPageIndex_TJOldCar(result.getPageIndex_TJOldCar());
			CarOldList.addAll(result.getCarOldList());
			setPageIndex_TJNewCar(result.getPageIndex_TJNewCar());
			CarNewList.addAll(result.getCarNewList());
			setPageIndex_TJLikeCar(result.getPageIndex_TJLikeCar());
			CarLikeList.addAll(result.getCarLikeList());
			
		}
	}

	@Override
	public String toResultString() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getReportID() {
		return ReportID;
	}

	public void setReportID(String reportID) {
		ReportID = reportID;
	}

	public String getMakeId() {
		return MakeId;
	}

	public void setMakeId(String makeId) {
		MakeId = makeId;
	}

	public String getMakeName() {
		return MakeName;
	}

	public void setMakeName(String makeName) {
		MakeName = makeName;
	}

	public String getModelName() {
		return ModelName;
	}

	public void setModelName(String modelName) {
		ModelName = modelName;
	}

	public String getModelId() {
		return ModelId;
	}

	public void setModelId(String modelId) {
		ModelId = modelId;
	}

	public String getStyleId() {
		return StyleId;
	}

	public void setStyleId(String styleId) {
		StyleId = styleId;
	}

	public String getImgUrl() {
		return ImgUrl;
	}

	public void setImgUrl(String imgUrl) {
		ImgUrl = imgUrl;
	}

	public String getFullName() {
		return FullName;
	}

	public void setFullName(String fullName) {
		FullName = fullName;
	}

	public String getRegDate() {
		return RegDate;
	}

	public void setRegDate(String regDate) {
		RegDate = regDate;
	}

	public String getRegDateTime() {
		return RegDateTime;
	}

	public void setRegDateTime(String regDateTime) {
		RegDateTime = regDateTime;
	}

	public String getMileage() {
		return Mileage;
	}

	public void setMileage(String mileage) {
		Mileage = mileage;
	}

	public String getProvId() {
		return ProvId;
	}

	public void setProvId(String provId) {
		ProvId = provId;
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

	public String getNowMsrp() {
		return NowMsrp;
	}

	public void setNowMsrp(String nowMsrp) {
		NowMsrp = nowMsrp;
	}

	public String getCurrentModelLevelId() {
		return CurrentModelLevelId;
	}

	public void setCurrentModelLevelId(String currentModelLevelId) {
		CurrentModelLevelId = currentModelLevelId;
	}

	public String getCurrentModelLevelName() {
		return CurrentModelLevelName;
	}

	public void setCurrentModelLevelName(String currentModelLevelName) {
		CurrentModelLevelName = currentModelLevelName;
	}

	public String getBaoZhilvRank() {
		return BaoZhilvRank;
	}

	public void setBaoZhilvRank(String baoZhilvRank) {
		BaoZhilvRank = baoZhilvRank;
	}

	public String getBaoZhilvCityId() {
		return BaoZhilvCityId;
	}

	public void setBaoZhilvCityId(String baoZhilvCityId) {
		BaoZhilvCityId = baoZhilvCityId;
	}

	public String getBaoZhilvCityName() {
		return BaoZhilvCityName;
	}

	public void setBaoZhilvCityName(String baoZhilvCityName) {
		BaoZhilvCityName = baoZhilvCityName;
	}

	public String getBaoZhilvLevel() {
		return BaoZhilvLevel;
	}

	public void setBaoZhilvLevel(String baoZhilvLevel) {
		BaoZhilvLevel = baoZhilvLevel;
	}

	public String getBaoZhilvLevelName() {
		return BaoZhilvLevelName;
	}

	public void setBaoZhilvLevelName(String baoZhilvLevelName) {
		BaoZhilvLevelName = baoZhilvLevelName;
	}

	public String getB2CLowPrice() {
		return B2CLowPrice;
	}

	public void setB2CLowPrice(String b2cLowPrice) {
		B2CLowPrice = b2cLowPrice;
	}

	public String getB2CUpPrice() {
		return B2CUpPrice;
	}

	public void setB2CUpPrice(String b2cUpPrice) {
		B2CUpPrice = b2cUpPrice;
	}

	public String getC2CLowPrice() {
		return C2CLowPrice;
	}

	public void setC2CLowPrice(String c2cLowPrice) {
		C2CLowPrice = c2cLowPrice;
	}

	public String getC2CUpPrice() {
		return C2CUpPrice;
	}

	public void setC2CUpPrice(String c2cUpPrice) {
		C2CUpPrice = c2cUpPrice;
	}

	public List<HistoryChengjiaoPrice> getHistoricalAveragePrice() {
		return HistoricalAveragePrice;
	}

	public void setHistoricalAveragePrice(
			List<HistoryChengjiaoPrice> historicalAveragePrice) {
		HistoricalAveragePrice = historicalAveragePrice;
	}

	public List<RequestValuNearCityModel> getNearCity() {
		return NearCity;
	}

	public void setNearCity(List<RequestValuNearCityModel> nearCity) {
		NearCity = nearCity;
	}

	public List<RequestValuTipsModel> getArtical() {
		return Artical;
	}

	public void setArtical(List<RequestValuTipsModel> artical) {
		Artical = artical;
	}

	public String getPageIndex_KBGood() {
		return PageIndex_KBGood;
	}

	public void setPageIndex_KBGood(String pageIndex_KBGood) {
		PageIndex_KBGood = pageIndex_KBGood;
	}

	public List<RequestValuKoubeiModel> getKoubeiList_Good() {
		return KoubeiList_Good;
	}

	public void setKoubeiList_Good(List<RequestValuKoubeiModel> koubeiList_Good) {
		KoubeiList_Good = koubeiList_Good;
	}

	public String getPageIndex_KBBad() {
		return PageIndex_KBBad;
	}

	public void setPageIndex_KBBad(String pageIndex_KBBad) {
		PageIndex_KBBad = pageIndex_KBBad;
	}

	public List<RequestValuKoubeiModel> getKoubeiList_Bad() {
		return KoubeiList_Bad;
	}

	public void setKoubeiList_Bad(List<RequestValuKoubeiModel> koubeiList_Bad) {
		KoubeiList_Bad = koubeiList_Bad;
	}

	public String getCostYear() {
		return CostYear;
	}

	public void setCostYear(String costYear) {
		CostYear = costYear;
	}

	public String getInsuranceYear() {
		return InsuranceYear;
	}

	public void setInsuranceYear(String insuranceYear) {
		InsuranceYear = insuranceYear;
	}

	public String getMaintainYear() {
		return MaintainYear;
	}

	public void setMaintainYear(String maintainYear) {
		MaintainYear = maintainYear;
	}

	public String getDepreciationYear() {
		return DepreciationYear;
	}

	public void setDepreciationYear(String depreciationYear) {
		DepreciationYear = depreciationYear;
	}

	public String getOilYear() {
		return OilYear;
	}

	public void setOilYear(String oilYear) {
		OilYear = oilYear;
	}

	public String getTaxYear() {
		return TaxYear;
	}

	public void setTaxYear(String taxYear) {
		TaxYear = taxYear;
	}

	public String getPageIndex_TJOldCar() {
		return PageIndex_TJOldCar;
	}

	public void setPageIndex_TJOldCar(String pageIndex_TJOldCar) {
		PageIndex_TJOldCar = pageIndex_TJOldCar;
	}

	public List<RequestValuCarLikeModel> getCarOldList() {
		return CarOldList;
	}

	public void setCarOldList(List<RequestValuCarLikeModel> carOldList) {
		CarOldList = carOldList;
	}

	public String getPageIndex_TJNewCar() {
		return PageIndex_TJNewCar;
	}

	public void setPageIndex_TJNewCar(String pageIndex_TJNewCar) {
		PageIndex_TJNewCar = pageIndex_TJNewCar;
	}

	public List<NewCar> getCarNewList() {
		return CarNewList;
	}

	public void setCarNewList(List<NewCar> carNewList) {
		CarNewList = carNewList;
	}

	public String getPageIndex_TJLikeCar() {
		return PageIndex_TJLikeCar;
	}

	public void setPageIndex_TJLikeCar(String pageIndex_TJLikeCar) {
		PageIndex_TJLikeCar = pageIndex_TJLikeCar;
	}

	public List<RequestValuCarLikeModel> getCarLikeList() {
		return CarLikeList;
	}

	public void setCarLikeList(List<RequestValuCarLikeModel> carLikeList) {
		CarLikeList = carLikeList;
	}


	@Override
	public String toString() {
		return "RequestValuationBuyResult [ReportID=" + ReportID + ", MakeId="
				+ MakeId + ", ModelId=" + ModelId + ", StyleId=" + StyleId
				+ ", ImgUrl=" + ImgUrl + ", FullName=" + FullName
				+ ", RegDate=" + RegDate + ", RegDateTime=" + RegDateTime
				+ ", Mileage=" + Mileage + ", ProvId=" + ProvId + ", CityId="
				+ CityId + ", CityName=" + CityName + ", NowMsrp=" + NowMsrp
				+ ", MakeName=" + MakeName + ", ModelName=" + ModelName
				+ ", CurrentModelLevelId=" + CurrentModelLevelId
				+ ", CurrentModelLevelName=" + CurrentModelLevelName
				+ ", BaoZhilvRank=" + BaoZhilvRank + ", BaoZhilvCityId="
				+ BaoZhilvCityId + ", BaoZhilvCityName=" + BaoZhilvCityName
				+ ", BaoZhilvLevel=" + BaoZhilvLevel + ", BaoZhilvLevelName="
				+ BaoZhilvLevelName + ", B2CLowPrice=" + B2CLowPrice
				+ ", B2CUpPrice=" + B2CUpPrice + ", C2CLowPrice=" + C2CLowPrice
				+ ", C2CUpPrice=" + C2CUpPrice + ", HistoricalAveragePrice="
				+ HistoricalAveragePrice + ", NearCity=" + NearCity
				+ ", Artical=" + Artical + ", PageIndex_KBGood="
				+ PageIndex_KBGood + ", KoubeiList_Good=" + KoubeiList_Good
				+ ", PageIndex_KBBad=" + PageIndex_KBBad + ", KoubeiList_Bad="
				+ KoubeiList_Bad + ", CostYear=" + CostYear
				+ ", InsuranceYear=" + InsuranceYear + ", MaintainYear="
				+ MaintainYear + ", DepreciationYear=" + DepreciationYear
				+ ", OilYear=" + OilYear + ", TaxYear=" + TaxYear
				+ ", PageIndex_TJOldCar=" + PageIndex_TJOldCar
				+ ", CarOldList=" + CarOldList + ", PageIndex_TJNewCar="
				+ PageIndex_TJNewCar + ", CarNewList=" + CarNewList
				+ ", PageIndex_TJLikeCar=" + PageIndex_TJLikeCar
				+ ", CarLikeList=" + CarLikeList + "]";
	}


	public class HistoryChengjiaoPrice implements Serializable{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
//		"Date": "16年1月",
//        "Personal ": "12.3",
//        "Business ": "24.3"
        	private String Date;
        	private Float Personal;
        	private Float Business;
			public String getDate() {
				return Date;
			}
			public void setDate(String date) {
				Date = date;
			}
			public Float getPersonal() {
				return Personal;
			}
			public void setPersonal(Float personal) {
				Personal = personal;
			}
			public Float getBusiness() {
				return Business;
			}
			public void setBusiness(Float business) {
				Business = business;
			}
			@Override
			public String toString() {
				return "HistoryChengjiaoPrice [Date=" + Date + ", Personal="
						+ Personal + ", Business=" + Business + "]";
			}
        	
	}
}
