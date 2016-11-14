package com.jzg.jzgoto.phone.models.business.buy;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WY on 2016/9/27.
 * Function :买车详情
 */
public class BuyCarDetailResult extends BaseResultModels {
	private static final long serialVersionUID = 1L;
	private String CarSourceId;
	private String CarSourceFrom;
	private String MakeID;
	private String MakeName;
	private String ModelID;
	private String ModelName;
	private String StyleID;
	private String StyleFullName;
	private String PublishTime;
	private String Cstype;
	private String SellPrice;
	private String JzgGuzhiPrice;
	private String From;
	private String ShouFuPrice;
	private String YuegongPrice;
	private String DaikuanYear;
	private String DaikuanDuohuai;
	private String QwZsTotal;
	private String QwZsMinPrice;
	private String QwZsMaxPrice;
	private String B2CBLowPrice;
	private String B2CBUpPrice;
	private String IsCollection;
	private String CollectionId;
	private String LinkName;
	private String LinkPhone;
	private String RegDate;
	private String strRegDate;
	private String RegDateSpan;
	private String Mileage;
	private String CityId;
	private String CityName;
	private String PaiFangBZ;
	private String BSQ;
	private String TransFerCount;
	private String CurrentModelLevelId;
	private String CurrentModelLevelName;
	private String BaoZhilvRank;
	private String BaoZhilvCityId;
	private String BaoZhilvCityName;
	private String UserId;
	private String StylePeizhiUrl;
	private String LoanUrl;
	private String TotalPic;
	private List<BuyCarDetailPicModel> PicList = new ArrayList<>();
	private List<BuyCarDetailFirstLikeModel> FirstLikeModelList = new ArrayList<>();
	private List<BuyCarItemModel> TjOldCarList = new ArrayList<>();
	private List<BuyCarItemModel> TjNewCarList = new ArrayList<>();

	@Override
	public void setResult(Object obj) {
		Gson gson = new Gson();
		System.out.println("买车详情——"+obj.toString());
		BuyCarDetailResult result = gson.fromJson(obj.toString(), BuyCarDetailResult.class);
		setStatus(result.getStatus());
		setMsg(result.getMsg());
		if(result.getStatus()==SUCCESS){
			setCarSourceId(result.getCarSourceId());
			setCarSourceFrom(result.getCarSourceFrom());
			setMakeID(result.getMakeID());
			setMakeName(result.getMakeName());
			setModelID(result.getModelID());
			setModelName(result.getModelName());
			setStyleID(result.getStyleID());
			setStyleFullName(result.getStyleFullName());
			setPublishTime(result.getPublishTime());
			setCstype(result.getCstype());
			setSellPrice(result.getSellPrice());
			setJzgGuzhiPrice(result.getJzgGuzhiPrice());
			setFrom(result.getFrom());
			setShouFuPrice(result.getShouFuPrice());
			setYuegongPrice(result.getYuegongPrice());
			setDaikuanYear(result.getDaikuanYear());
			setDaikuanDuohuai(result.getDaikuanDuohuai());
			setQwZsTotal(result.getQwZsTotal());
			setQwZsMinPrice(result.getQwZsMinPrice());
			setQwZsMaxPrice(result.getQwZsMaxPrice());
			setB2CBLowPrice(result.getB2CBLowPrice());
			setB2CBUpPrice(result.getB2CBUpPrice());
			setIsCollection(result.getIsCollection());
			setCollectionId(result.getCollectionId());
			setLinkName(result.getLinkName());
			setLinkPhone(result.getLinkPhone());
			setRegDate(result.getRegDate());
			setStrRegDate(result.getStrRegDate());
			setRegDateSpan(result.getRegDateSpan());
			setMileage(result.getMileage());
			setCityId(result.getCityId());
			setCityName(result.getCityName());
			setPaiFangBZ(result.getPaiFangBZ());
			setBSQ(result.getBSQ());
			setTransFerCount(result.getTransFerCount());
			setCurrentModelLevelId(result.getCurrentModelLevelId());
			setCurrentModelLevelName(result.getCurrentModelLevelName());
			setBaoZhilvRank(result.getBaoZhilvRank());
			setBaoZhilvCityId(result.getBaoZhilvCityId());
			setBaoZhilvCityName(result.getBaoZhilvCityName());
			setUserId(result.getUserId());
			setStylePeizhiUrl(result.getStylePeizhiUrl());
			setLoanUrl(result.getLoanUrl());
			setTotalPic(result.getTotalPic());
			PicList.addAll(result.getPicList());
			FirstLikeModelList.addAll(result.getFirstLikeModelList());
			TjOldCarList.addAll(result.getTjOldCarList());
			TjNewCarList.addAll(result.getTjNewCarList());
		}
	}

	@Override
	public String toResultString() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getCarSourceId() {
		return CarSourceId;
	}

	public void setCarSourceId(String CarSourceId) {
		this.CarSourceId = CarSourceId;
	}

	public String getCarSourceFrom() {
		return CarSourceFrom;
	}

	public void setCarSourceFrom(String CarSourceFrom) {
		this.CarSourceFrom = CarSourceFrom;
	}

	public String getMakeID() {
		return MakeID;
	}

	public void setMakeID(String MakeID) {
		this.MakeID = MakeID;
	}

	public String getMakeName() {
		return MakeName;
	}

	public void setMakeName(String MakeName) {
		this.MakeName = MakeName;
	}

	public String getModelID() {
		return ModelID;
	}

	public void setModelID(String ModelID) {
		this.ModelID = ModelID;
	}

	public String getModelName() {
		return ModelName;
	}

	public void setModelName(String ModelName) {
		this.ModelName = ModelName;
	}

	public String getStyleID() {
		return StyleID;
	}

	public void setStyleID(String StyleID) {
		this.StyleID = StyleID;
	}

	public String getStyleFullName() {
		return StyleFullName;
	}

	public void setStyleFullName(String StyleFullName) {
		this.StyleFullName = StyleFullName;
	}

	public String getPublishTime() {
		return PublishTime;
	}

	public void setPublishTime(String PublishTime) {
		this.PublishTime = PublishTime;
	}

	public String getCstype() {
		return Cstype;
	}

	public void setCstype(String Cstype) {
		this.Cstype = Cstype;
	}

	public String getSellPrice() {
		return SellPrice;
	}

	public void setSellPrice(String SellPrice) {
		this.SellPrice = SellPrice;
	}

	public String getJzgGuzhiPrice() {
		return JzgGuzhiPrice;
	}

	public void setJzgGuzhiPrice(String JzgGuzhiPrice) {
		this.JzgGuzhiPrice = JzgGuzhiPrice;
	}

	public String getFrom() {
		return From;
	}

	public void setFrom(String From) {
		this.From = From;
	}

	public String getShouFuPrice() {
		return ShouFuPrice;
	}

	public void setShouFuPrice(String ShouFuPrice) {
		this.ShouFuPrice = ShouFuPrice;
	}

	public String getYuegongPrice() {
		return YuegongPrice;
	}

	public void setYuegongPrice(String YuegongPrice) {
		this.YuegongPrice = YuegongPrice;
	}

	public String getDaikuanYear() {
		return DaikuanYear;
	}

	public void setDaikuanYear(String DaikuanYear) {
		this.DaikuanYear = DaikuanYear;
	}

	public String getDaikuanDuohuai() {
		return DaikuanDuohuai;
	}

	public void setDaikuanDuohuai(String DaikuanDuohuai) {
		this.DaikuanDuohuai = DaikuanDuohuai;
	}

	public String getQwZsTotal() {
		return QwZsTotal;
	}

	public void setQwZsTotal(String QwZsTotal) {
		this.QwZsTotal = QwZsTotal;
	}

	public String getQwZsMinPrice() {
		return QwZsMinPrice;
	}

	public void setQwZsMinPrice(String QwZsMinPrice) {
		this.QwZsMinPrice = QwZsMinPrice;
	}

	public String getQwZsMaxPrice() {
		return QwZsMaxPrice;
	}

	public void setQwZsMaxPrice(String QwZsMaxPrice) {
		this.QwZsMaxPrice = QwZsMaxPrice;
	}

	public String getB2CBLowPrice() {
		return B2CBLowPrice;
	}

	public void setB2CBLowPrice(String B2CBLowPrice) {
		this.B2CBLowPrice = B2CBLowPrice;
	}

	public String getB2CBUpPrice() {
		return B2CBUpPrice;
	}

	public void setB2CBUpPrice(String B2CBUpPrice) {
		this.B2CBUpPrice = B2CBUpPrice;
	}

	public String getIsCollection() {
		return IsCollection;
	}

	public void setIsCollection(String IsCollection) {
		this.IsCollection = IsCollection;
	}

	public String getCollectionId() {
		return CollectionId;
	}

	public void setCollectionId(String CollectionId) {
		this.CollectionId = CollectionId;
	}

	public String getLinkName() {
		return LinkName;
	}

	public void setLinkName(String LinkName) {
		this.LinkName = LinkName;
	}

	public String getLinkPhone() {
		return LinkPhone;
	}

	public void setLinkPhone(String LinkPhone) {
		this.LinkPhone = LinkPhone;
	}

	public String getRegDate() {
		return RegDate;
	}

	public void setRegDate(String RegDate) {
		this.RegDate = RegDate;
	}

	public String getStrRegDate() {
		return strRegDate;
	}

	public void setStrRegDate(String strRegDate) {
		this.strRegDate = strRegDate;
	}

	public String getRegDateSpan() {
		return RegDateSpan;
	}

	public void setRegDateSpan(String RegDateSpan) {
		this.RegDateSpan = RegDateSpan;
	}

	public String getMileage() {
		return Mileage;
	}

	public void setMileage(String Mileage) {
		this.Mileage = Mileage;
	}

	public String getCityId() {
		return CityId;
	}

	public void setCityId(String CityId) {
		this.CityId = CityId;
	}

	public String getCityName() {
		return CityName;
	}

	public void setCityName(String CityName) {
		this.CityName = CityName;
	}

	public String getPaiFangBZ() {
		return PaiFangBZ;
	}

	public void setPaiFangBZ(String PaiFangBZ) {
		this.PaiFangBZ = PaiFangBZ;
	}

	public String getBSQ() {
		return BSQ;
	}

	public void setBSQ(String BSQ) {
		this.BSQ = BSQ;
	}

	public String getTransFerCount() {
		return TransFerCount;
	}

	public void setTransFerCount(String TransFerCount) {
		this.TransFerCount = TransFerCount;
	}

	public String getCurrentModelLevelId() {
		return CurrentModelLevelId;
	}

	public void setCurrentModelLevelId(String CurrentModelLevelId) {
		this.CurrentModelLevelId = CurrentModelLevelId;
	}

	public String getCurrentModelLevelName() {
		return CurrentModelLevelName;
	}

	public void setCurrentModelLevelName(String CurrentModelLevelName) {
		this.CurrentModelLevelName = CurrentModelLevelName;
	}

	public String getBaoZhilvRank() {
		return BaoZhilvRank;
	}

	public void setBaoZhilvRank(String BaoZhilvRank) {
		this.BaoZhilvRank = BaoZhilvRank;
	}

	public String getBaoZhilvCityId() {
		return BaoZhilvCityId;
	}

	public void setBaoZhilvCityId(String BaoZhilvCityId) {
		this.BaoZhilvCityId = BaoZhilvCityId;
	}

	public String getBaoZhilvCityName() {
		return BaoZhilvCityName;
	}

	public void setBaoZhilvCityName(String BaoZhilvCityName) {
		this.BaoZhilvCityName = BaoZhilvCityName;
	}

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String UserId) {
		this.UserId = UserId;
	}

	public String getStylePeizhiUrl() {
		return StylePeizhiUrl;
	}

	public void setStylePeizhiUrl(String StylePeizhiUrl) {
		this.StylePeizhiUrl = StylePeizhiUrl;
	}

	public String getLoanUrl() {
		return LoanUrl;
	}

	public void setLoanUrl(String LoanUrl) {
		this.LoanUrl = LoanUrl;
	}

	public String getTotalPic() {
		return TotalPic;
	}

	public void setTotalPic(String TotalPic) {
		this.TotalPic = TotalPic;
	}

	public List<BuyCarDetailPicModel> getPicList() {
		return PicList;
	}

	public void setPicList(List<BuyCarDetailPicModel> picList) {
		PicList = picList;
	}

	public List<BuyCarDetailFirstLikeModel> getFirstLikeModelList() {
		return FirstLikeModelList;
	}

	public void setFirstLikeModelList(List<BuyCarDetailFirstLikeModel> firstLikeModelList) {
		FirstLikeModelList = firstLikeModelList;
	}

	public List<BuyCarItemModel> getTjOldCarList() {
		return TjOldCarList;
	}

	public void setTjOldCarList(List<BuyCarItemModel> tjOldCarList) {
		TjOldCarList = tjOldCarList;
	}

	public List<BuyCarItemModel> getTjNewCarList() {
		return TjNewCarList;
	}

	public void setTjNewCarList(List<BuyCarItemModel> tjNewCarList) {
		TjNewCarList = tjNewCarList;
	}

	@Override
	public String toString() {
		return "BuyCarDetailResult{" +
				"CarSourceId='" + CarSourceId + '\'' +
				", CarSourceFrom='" + CarSourceFrom + '\'' +
				", MakeID='" + MakeID + '\'' +
				", MakeName='" + MakeName + '\'' +
				", ModelID='" + ModelID + '\'' +
				", ModelName='" + ModelName + '\'' +
				", StyleID='" + StyleID + '\'' +
				", StyleFullName='" + StyleFullName + '\'' +
				", PublishTime='" + PublishTime + '\'' +
				", Cstype='" + Cstype + '\'' +
				", SellPrice='" + SellPrice + '\'' +
				", JzgGuzhiPrice='" + JzgGuzhiPrice + '\'' +
				", From='" + From + '\'' +
				", ShouFuPrice='" + ShouFuPrice + '\'' +
				", YuegongPrice='" + YuegongPrice + '\'' +
				", DaikuanYear='" + DaikuanYear + '\'' +
				", DaikuanDuohuai='" + DaikuanDuohuai + '\'' +
				", QwZsTotal='" + QwZsTotal + '\'' +
				", QwZsMinPrice='" + QwZsMinPrice + '\'' +
				", QwZsMaxPrice='" + QwZsMaxPrice + '\'' +
				", B2CBLowPrice='" + B2CBLowPrice + '\'' +
				", B2CBUpPrice='" + B2CBUpPrice + '\'' +
				", IsCollection='" + IsCollection + '\'' +
				", CollectionId='" + CollectionId + '\'' +
				", LinkName='" + LinkName + '\'' +
				", LinkPhone='" + LinkPhone + '\'' +
				", RegDate='" + RegDate + '\'' +
				", strRegDate='" + strRegDate + '\'' +
				", RegDateSpan='" + RegDateSpan + '\'' +
				", Mileage='" + Mileage + '\'' +
				", CityId='" + CityId + '\'' +
				", CityName='" + CityName + '\'' +
				", PaiFangBZ='" + PaiFangBZ + '\'' +
				", BSQ='" + BSQ + '\'' +
				", TransFerCount='" + TransFerCount + '\'' +
				", CurrentModelLevelId='" + CurrentModelLevelId + '\'' +
				", CurrentModelLevelName='" + CurrentModelLevelName + '\'' +
				", BaoZhilvRank='" + BaoZhilvRank + '\'' +
				", BaoZhilvCityId='" + BaoZhilvCityId + '\'' +
				", BaoZhilvCityName='" + BaoZhilvCityName + '\'' +
				", UserId='" + UserId + '\'' +
				", StylePeizhiUrl='" + StylePeizhiUrl + '\'' +
				", LoanUrl='" + LoanUrl + '\'' +
				", TotalPic='" + TotalPic + '\'' +
				", PicList=" + PicList +
				", FirstLikeModelList=" + FirstLikeModelList +
				", TjOldCarList=" + TjOldCarList +
				", TjNewCarList=" + TjNewCarList +
				'}';
	}
}
