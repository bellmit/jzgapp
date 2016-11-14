package com.jzg.jzgoto.phone.models.business.valuation.buy;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.business.valuation.sell.RequestValuCarLikeModel;
import com.jzg.jzgoto.phone.models.business.valuation.sell.RequestValuKoubeiModel;
import com.jzg.jzgoto.phone.models.business.valuation.sell.RequestValuTipsModel;
import com.jzg.jzgoto.phone.models.business.valuation.sell.RequestValuationBuyResult.HistoryChengjiaoPrice;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;

public class RequestValuationSellResult extends BaseResultModels {

	/**
	 *  "status": 100,
    "msg": "",
    "ReportID": "1184370",
    "MakeId": "15",
    "ModelId": "2971",
    "StyleId": 15190,
    "ImgUrl": "http://img1.bitautoimg.com/autoalbum/files/20131025/373/15394937374737_2882008_4.jpg",
    "FullName": "比亚迪L3 2012款 1.5L 手动 舒适型",
    "RegDate": "2015年01月",
    "RegDateTime": "2015-01-01",
//  "MakeName": "比亚迪",
//  "ModelName": "L3",
 *  midYearPrice
    "Mileage": 2.1,
    "ProvId": "2",
    "CityId": "201",
    "CityName": "北京",
    "NowMsrp": 5.39,
    "PageIndex_KBGood": "1",
    "KoubeiList_Good": [],
    "PageIndex_KBBad": "1",
    "KoubeiList_Bad": [],
    "Artical": [],
    
    "C2BALowPrice": "20.0",
    "C2BAUpPrice": "12.0",
    "C2BBLowPrice": "20.0",
    "C2BBUpPrice": "12.0",
    "C2BCLowPrice": "20.0",
    "C2BCUpPrice": "12.0",
    "C2CALowPrice": "20.0",
    "C2CAUpPrice": "12.0",
    "C2CBLowPrice": "20.0",
    "C2CBUpPrice": "12.0",
    "C2CCLowPrice": "20.0",
    "C2CCUpPrice": "12.0",
    
    "PriceLevel": "A",
    "CurrentModelLevelId": "3",
    "CurrentModelLevelName": "紧凑型车",
    "YearPrice": [HistoryChengjiaoPrice],
    "ThreeYearPrice": [HistoryChengjiaoPrice],
    "PageIndex_TJ": "1",
    "CarList": [RequestValuCarLikeModel]
	 */
	
	private static final long serialVersionUID = 1L;
	private String ReportID;
	private String MakeId;
	private String ModelId;
	private String StyleId;
	private String ImgUrl;
	private String FullName;
	private String RegDate;
	private String RegDateTime;
	private String MakeName;
	private String ModelName;
	private String Mileage;
	private String SixMonthLessPrice;
	private String ProvId;
	private String ProvName;
	private String CityId;
	private String CityName;
	private String NowMsrp;
	private List<RequestValuTipsModel> Artical = new ArrayList<RequestValuTipsModel>();
	private String PageIndex_KBGood;
	private List<RequestValuKoubeiModel> KoubeiList_Good = new ArrayList<RequestValuKoubeiModel>();
	private String PageIndex_KBBad;
	private List<RequestValuKoubeiModel> KoubeiList_Bad = new ArrayList<RequestValuKoubeiModel>();
	
	private String C2BALowPrice;
    private String C2BAUpPrice;
    private String C2BBLowPrice;
    private String C2BBUpPrice;
    private String C2BCLowPrice;
    private String C2BCUpPrice;
    private String C2CALowPrice;
    private String C2CAUpPrice;
    private String C2CBLowPrice;
    private String C2CBUpPrice;
    private String C2CCLowPrice;
    private String C2CCUpPrice;
    private String PriceLevel;
	private String CurrentModelLevelId;
	private String CurrentModelLevelName;
	private List<HistoryChengjiaoPrice> YearPrice = new ArrayList<HistoryChengjiaoPrice>();
	private List<HistoryChengjiaoPrice> ThreeYearPrice = new ArrayList<HistoryChengjiaoPrice>();
	private String PageIndex_TJ;
	private List<RequestValuCarLikeModel> CarList = new ArrayList<RequestValuCarLikeModel>();
	
	@Override
	public void setResult(Object obj) {
		Gson gson = new Gson();
		RequestValuationSellResult result = gson.fromJson(obj.toString(), RequestValuationSellResult.class);
		setStatus(result.getStatus());
		setMsg(result.getMsg());
		if(result.getStatus() == SUCCESS){
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
			setProvName(result.getProvName());
			setCityId(result.getCityId());
			setCityName(result.getCityName());
			setMakeName(result.getMakeName());
			setModelName(result.getModelName());
			setSixMonthLessPrice(result.getSixMonthLessPrice());
			Artical.addAll(result.getArtical());
			setPageIndex_KBGood(result.getPageIndex_KBGood());
			KoubeiList_Good.addAll(result.getKoubeiList_Good());
			setPageIndex_KBBad(result.getPageIndex_KBBad());
			KoubeiList_Bad.addAll(result.getKoubeiList_Bad());
			setC2BALowPrice(result.getC2BALowPrice());
			setC2BAUpPrice(result.getC2BAUpPrice());
			setC2BBLowPrice(result.getC2BBLowPrice());
			setC2BBUpPrice(result.getC2BBUpPrice());
			setC2BCLowPrice(result.getC2BCLowPrice());
			setC2BCUpPrice(result.getC2BCUpPrice());
			setC2CALowPrice(result.getC2CALowPrice());
			setC2CAUpPrice(result.getC2CAUpPrice());
			setC2CBLowPrice(result.getC2CBLowPrice());
			setC2CBUpPrice(result.getC2CBUpPrice());
			setC2CCLowPrice(result.getC2CCLowPrice());
			setC2CCUpPrice(result.getC2CCUpPrice());
			setPriceLevel(result.getPriceLevel());
			setCurrentModelLevelId(result.getCurrentModelLevelId());
			setCurrentModelLevelName(result.getCurrentModelLevelName());
			YearPrice.addAll(result.getYearPrice());
			ThreeYearPrice.addAll(result.getThreeYearPrice());
			setPageIndex_TJ(result.getPageIndex_TJ());
			CarList.addAll(result.getCarList());
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

	public String getModelId() {
		return ModelId;
	}

	public void setModelId(String modelId) {
		ModelId = modelId;
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

	

	public String getSixMonthLessPrice() {
		return SixMonthLessPrice;
	}

	public void setSixMonthLessPrice(String sixMonthLessPrice) {
		SixMonthLessPrice = sixMonthLessPrice;
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

	public String getC2BALowPrice() {
		return C2BALowPrice;
	}

	public void setC2BALowPrice(String c2baLowPrice) {
		C2BALowPrice = c2baLowPrice;
	}

	public String getC2BAUpPrice() {
		return C2BAUpPrice;
	}

	public void setC2BAUpPrice(String c2baUpPrice) {
		C2BAUpPrice = c2baUpPrice;
	}

	public String getC2BBLowPrice() {
		return C2BBLowPrice;
	}

	public void setC2BBLowPrice(String c2bbLowPrice) {
		C2BBLowPrice = c2bbLowPrice;
	}

	public String getC2BBUpPrice() {
		return C2BBUpPrice;
	}

	public void setC2BBUpPrice(String c2bbUpPrice) {
		C2BBUpPrice = c2bbUpPrice;
	}

	public String getC2BCLowPrice() {
		return C2BCLowPrice;
	}

	public void setC2BCLowPrice(String c2bcLowPrice) {
		C2BCLowPrice = c2bcLowPrice;
	}

	public String getC2BCUpPrice() {
		return C2BCUpPrice;
	}

	public void setC2BCUpPrice(String c2bcUpPrice) {
		C2BCUpPrice = c2bcUpPrice;
	}

	public String getC2CALowPrice() {
		return C2CALowPrice;
	}

	public void setC2CALowPrice(String c2caLowPrice) {
		C2CALowPrice = c2caLowPrice;
	}

	public String getC2CAUpPrice() {
		return C2CAUpPrice;
	}

	public void setC2CAUpPrice(String c2caUpPrice) {
		C2CAUpPrice = c2caUpPrice;
	}

	public String getC2CBLowPrice() {
		return C2CBLowPrice;
	}

	public void setC2CBLowPrice(String c2cbLowPrice) {
		C2CBLowPrice = c2cbLowPrice;
	}

	public String getC2CBUpPrice() {
		return C2CBUpPrice;
	}

	public void setC2CBUpPrice(String c2cbUpPrice) {
		C2CBUpPrice = c2cbUpPrice;
	}

	public String getC2CCLowPrice() {
		return C2CCLowPrice;
	}

	public void setC2CCLowPrice(String c2ccLowPrice) {
		C2CCLowPrice = c2ccLowPrice;
	}

	public String getC2CCUpPrice() {
		return C2CCUpPrice;
	}

	public void setC2CCUpPrice(String c2ccUpPrice) {
		C2CCUpPrice = c2ccUpPrice;
	}

	public String getPriceLevel() {
		return PriceLevel;
	}

	public void setPriceLevel(String priceLevel) {
		PriceLevel = priceLevel;
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

	public List<HistoryChengjiaoPrice> getYearPrice() {
		return YearPrice;
	}

	public void setYearPrice(List<HistoryChengjiaoPrice> yearPrice) {
		YearPrice = yearPrice;
	}

	public List<HistoryChengjiaoPrice> getThreeYearPrice() {
		return ThreeYearPrice;
	}

	public void setThreeYearPrice(List<HistoryChengjiaoPrice> threeYearPrice) {
		ThreeYearPrice = threeYearPrice;
	}

	public String getPageIndex_TJ() {
		return PageIndex_TJ;
	}

	public void setPageIndex_TJ(String pageIndex_TJ) {
		PageIndex_TJ = pageIndex_TJ;
	}

	public List<RequestValuCarLikeModel> getCarList() {
		return CarList;
	}

	public void setCarList(List<RequestValuCarLikeModel> carList) {
		CarList = carList;
	}

	public String getProvName() {
		return ProvName;
	}

	public void setProvName(String provName) {
		ProvName = provName;
	}

	@Override
	public String toString() {
		return "RequestValuationSellResult [ReportID=" + ReportID + ", MakeId="
				+ MakeId + ", ModelId=" + ModelId + ", StyleId=" + StyleId
				+ ", ImgUrl=" + ImgUrl + ", FullName=" + FullName
				+ ", RegDate=" + RegDate + ", RegDateTime=" + RegDateTime
				+ ", MakeName=" + MakeName + ", ModelName=" + ModelName
				+ ", Mileage=" + Mileage + ", midYearPrice=" + SixMonthLessPrice
				+ ", ProvId=" + ProvId + ", ProvName=" + ProvName + ", CityId="
				+ CityId + ", CityName=" + CityName + ", NowMsrp=" + NowMsrp
				+ ", Artical=" + Artical + ", PageIndex_KBGood="
				+ PageIndex_KBGood + ", KoubeiList_Good=" + KoubeiList_Good
				+ ", PageIndex_KBBad=" + PageIndex_KBBad + ", KoubeiList_Bad="
				+ KoubeiList_Bad + ", C2BALowPrice=" + C2BALowPrice
				+ ", C2BAUpPrice=" + C2BAUpPrice + ", C2BBLowPrice="
				+ C2BBLowPrice + ", C2BBUpPrice=" + C2BBUpPrice
				+ ", C2BCLowPrice=" + C2BCLowPrice + ", C2BCUpPrice="
				+ C2BCUpPrice + ", C2CALowPrice=" + C2CALowPrice
				+ ", C2CAUpPrice=" + C2CAUpPrice + ", C2CBLowPrice="
				+ C2CBLowPrice + ", C2CBUpPrice=" + C2CBUpPrice
				+ ", C2CCLowPrice=" + C2CCLowPrice + ", C2CCUpPrice="
				+ C2CCUpPrice + ", PriceLevel=" + PriceLevel
				+ ", CurrentModelLevelId=" + CurrentModelLevelId
				+ ", CurrentModelLevelName=" + CurrentModelLevelName
				+ ", YearPrice=" + YearPrice + ", ThreeYearPrice="
				+ ThreeYearPrice + ", PageIndex_TJ=" + PageIndex_TJ
				+ ", CarList=" + CarList + "]";
	}
	
}
