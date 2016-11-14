package com.jzg.jzgoto.phone.models.business.valuation;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.constant.Constant;
import com.jzg.jzgoto.phone.models.business.buy.BuyCarDetailFirstLikeModel;
import com.jzg.jzgoto.phone.models.business.buy.BuyCarItemModel;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by WY on 2016/9/28.
 * Function :车主估值结果
 */
public class ValuationSellCarResult extends BaseResultModels {

	private static final long serialVersionUID = 1L;

	private String ReportID;
	private String MakeId;
	private String ModelId;
	private String StyleId;
	private String ImgUrl;
	private String MakeName;
	private String ModelName;
	private String FullName;
	private String RegDateTime;
	private String RegDate;
	private String Mileage;
	private String ProvId;
	private String ProvName;
	private String CityId;
	private String CityName;
	private String NowMsrp;
	private String C2BALowPrice;
	private String C2BAMidPrice;
	private String C2BAUpPrice;
	private String C2BBLowPrice;
	private String C2BBMidPrice;
	private String C2BBUpPrice;
	private String C2BCLowPrice;
	private String C2BCMidPrice;
	private String C2BCUpPrice;
	private String C2CALowPrice;
	private String C2CAMidPrice;
	private String C2CAUpPrice;
	private String C2CBLowPrice;
	private String C2CBMidPrice;
	private String C2CBUpPrice;
	private String C2CCLowPrice;
	private String C2CCMidPrice;
	private String C2CCUpPrice;
	private String PriceLevel;
	private String CurrentModelLevelId;
	private String CurrentModelLevelName;
	private String BestChangeYear;
	private String BestChangePerc;
	private String TjModelName;
	private String ShareUrl;
	private List<ValuationSellCarReplaceCycleModel> ChangeCycle = new ArrayList<>();
	private List<BuyCarDetailFirstLikeModel> FirstChangeModelList = new ArrayList<>();
	private List<BuyCarItemModel> TjOldCarList = new ArrayList<>();
	private List<BuyCarItemModel> TjNewCarList = new ArrayList<>();

	@Override
	public void setResult(Object obj) {
		System.out.println("车主估值详情"+obj.toString());
		Gson gson = new Gson();
		ValuationSellCarResult result = gson.fromJson(obj.toString(), ValuationSellCarResult.class);
		setStatus(result.getStatus());
		setMsg(result.getMsg());
		if(result.getStatus()== Constant.SUCCESS){
			ChangeCycle.addAll(result.getChangeCycle());
			FirstChangeModelList.addAll(result.getFirstChangeModelList());
			TjOldCarList.addAll(result.getTjOldCarList());
			TjNewCarList.addAll(result.getTjNewCarList());
			setReportID(result.getReportID());
			setMakeId(result.getMakeId());
			setMakeName(result.getMakeName());
			setStyleId(result.getStyleId());
			setImgUrl(result.getImgUrl());
			setModelId(result.getModelId());
			setModelName(result.getModelName());
			setFullName(result.getFullName());
			setRegDateTime(result.getRegDateTime());
			setRegDate(result.getRegDate());
			setMileage(result.getMileage());
			setProvId(result.getProvId());
			setProvName(result.getProvName());
			setNowMsrp(result.getNowMsrp());
			setC2BALowPrice(result.getC2BALowPrice());
			setC2BAMidPrice(result.getC2BAMidPrice());
			setC2BAUpPrice(result.getC2BAUpPrice());
			setC2BBLowPrice(result.getC2BBLowPrice());
			setC2BBMidPrice(result.getC2BBMidPrice());
			setC2BBUpPrice(result.getC2BBUpPrice());
			setC2BCLowPrice(result.getC2BCLowPrice());
			setC2BCMidPrice(result.getC2BCMidPrice());
			setC2BCUpPrice(result.getC2BCUpPrice());
			setC2CALowPrice(result.getC2CALowPrice());
			setC2CAMidPrice(result.getC2CAMidPrice());
			setC2CAUpPrice(result.getC2CAUpPrice());
			setC2CBLowPrice(result.getC2CBLowPrice());
			setC2CBMidPrice(result.getC2CBMidPrice());
			setC2CBUpPrice(result.getC2CBUpPrice());
			setC2CCLowPrice(result.getC2CCLowPrice());
			setC2CCMidPrice(result.getC2CCMidPrice());
			setC2CCUpPrice(result.getC2CCUpPrice());
			setPriceLevel(result.getC2BALowPrice());
			setCurrentModelLevelId(result.getCurrentModelLevelId());
			setCurrentModelLevelName(result.getCurrentModelLevelName());
			setBestChangePerc(result.getBestChangePerc());
			setBestChangeYear(result.getBestChangeYear());
			setTjModelName(result.getTjModelName());
			setShareUrl(result.getShareUrl());
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

	public void setReportID(String ReportID) {
		this.ReportID = ReportID;
	}

	public String getMakeId() {
		return MakeId;
	}

	public void setMakeId(String MakeId) {
		this.MakeId = MakeId;
	}

	public String getModelId() {
		return ModelId;
	}

	public void setModelId(String ModelId) {
		this.ModelId = ModelId;
	}

	public String getStyleId() {
		return StyleId;
	}

	public void setStyleId(String StyleId) {
		this.StyleId = StyleId;
	}

	public String getImgUrl() {
		return ImgUrl;
	}

	public void setImgUrl(String ImgUrl) {
		this.ImgUrl = ImgUrl;
	}

	public String getMakeName() {
		return MakeName;
	}

	public void setMakeName(String MakeName) {
		this.MakeName = MakeName;
	}

	public String getModelName() {
		return ModelName;
	}

	public void setModelName(String ModelName) {
		this.ModelName = ModelName;
	}

	public String getFullName() {
		return FullName;
	}

	public void setFullName(String FullName) {
		this.FullName = FullName;
	}

	public String getRegDateTime() {
		return RegDateTime;
	}

	public void setRegDateTime(String RegDateTime) {
		this.RegDateTime = RegDateTime;
	}

	public String getRegDate() {
		return RegDate;
	}

	public void setRegDate(String RegDate) {
		this.RegDate = RegDate;
	}

	public String getMileage() {
		return Mileage;
	}

	public void setMileage(String Mileage) {
		this.Mileage = Mileage;
	}

	public String getProvId() {
		return ProvId;
	}

	public void setProvId(String ProvId) {
		this.ProvId = ProvId;
	}

	public String getProvName() {
		return ProvName;
	}

	public void setProvName(String ProvName) {
		this.ProvName = ProvName;
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

	public String getNowMsrp() {
		return NowMsrp;
	}

	public void setNowMsrp(String NowMsrp) {
		this.NowMsrp = NowMsrp;
	}

	public String getC2BALowPrice() {
		return C2BALowPrice;
	}

	public void setC2BALowPrice(String C2BALowPrice) {
		this.C2BALowPrice = C2BALowPrice;
	}

	public String getC2BAMidPrice() {
		return C2BAMidPrice;
	}

	public void setC2BAMidPrice(String C2BAMidPrice) {
		this.C2BAMidPrice = C2BAMidPrice;
	}

	public String getC2BAUpPrice() {
		return C2BAUpPrice;
	}

	public void setC2BAUpPrice(String C2BAUpPrice) {
		this.C2BAUpPrice = C2BAUpPrice;
	}

	public String getC2BBLowPrice() {
		return C2BBLowPrice;
	}

	public void setC2BBLowPrice(String C2BBLowPrice) {
		this.C2BBLowPrice = C2BBLowPrice;
	}

	public String getC2BBMidPrice() {
		return C2BBMidPrice;
	}

	public void setC2BBMidPrice(String C2BBMidPrice) {
		this.C2BBMidPrice = C2BBMidPrice;
	}

	public String getC2BBUpPrice() {
		return C2BBUpPrice;
	}

	public void setC2BBUpPrice(String C2BBUpPrice) {
		this.C2BBUpPrice = C2BBUpPrice;
	}

	public String getC2BCLowPrice() {
		return C2BCLowPrice;
	}

	public void setC2BCLowPrice(String C2BCLowPrice) {
		this.C2BCLowPrice = C2BCLowPrice;
	}

	public String getC2BCMidPrice() {
		return C2BCMidPrice;
	}

	public void setC2BCMidPrice(String C2BCMidPrice) {
		this.C2BCMidPrice = C2BCMidPrice;
	}

	public String getC2BCUpPrice() {
		return C2BCUpPrice;
	}

	public void setC2BCUpPrice(String C2BCUpPrice) {
		this.C2BCUpPrice = C2BCUpPrice;
	}

	public String getC2CALowPrice() {
		return C2CALowPrice;
	}

	public void setC2CALowPrice(String C2CALowPrice) {
		this.C2CALowPrice = C2CALowPrice;
	}

	public String getC2CAMidPrice() {
		return C2CAMidPrice;
	}

	public void setC2CAMidPrice(String C2CAMidPrice) {
		this.C2CAMidPrice = C2CAMidPrice;
	}

	public String getC2CAUpPrice() {
		return C2CAUpPrice;
	}

	public void setC2CAUpPrice(String C2CAUpPrice) {
		this.C2CAUpPrice = C2CAUpPrice;
	}

	public String getC2CBLowPrice() {
		return C2CBLowPrice;
	}

	public void setC2CBLowPrice(String C2CBLowPrice) {
		this.C2CBLowPrice = C2CBLowPrice;
	}

	public String getC2CBMidPrice() {
		return C2CBMidPrice;
	}

	public void setC2CBMidPrice(String C2CBMidPrice) {
		this.C2CBMidPrice = C2CBMidPrice;
	}

	public String getC2CBUpPrice() {
		return C2CBUpPrice;
	}

	public void setC2CBUpPrice(String C2CBUpPrice) {
		this.C2CBUpPrice = C2CBUpPrice;
	}

	public String getC2CCLowPrice() {
		return C2CCLowPrice;
	}

	public void setC2CCLowPrice(String C2CCLowPrice) {
		this.C2CCLowPrice = C2CCLowPrice;
	}

	public String getC2CCMidPrice() {
		return C2CCMidPrice;
	}

	public void setC2CCMidPrice(String C2CCMidPrice) {
		this.C2CCMidPrice = C2CCMidPrice;
	}

	public String getC2CCUpPrice() {
		return C2CCUpPrice;
	}

	public void setC2CCUpPrice(String C2CCUpPrice) {
		this.C2CCUpPrice = C2CCUpPrice;
	}

	public String getPriceLevel() {
		return PriceLevel;
	}

	public void setPriceLevel(String PriceLevel) {
		this.PriceLevel = PriceLevel;
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

	public String getBestChangeYear() {
		return BestChangeYear;
	}

	public void setBestChangeYear(String BestChangeYear) {
		this.BestChangeYear = BestChangeYear;
	}

	public String getBestChangePerc() {
		return BestChangePerc;
	}

	public void setBestChangePerc(String BestChangePerc) {
		this.BestChangePerc = BestChangePerc;
	}

	public String getTjModelName() {
		return TjModelName;
	}

	public void setTjModelName(String TjModelName) {
		this.TjModelName = TjModelName;
	}

	public String getShareUrl() {
		return ShareUrl;
	}

	public void setShareUrl(String ShareUrl) {
		this.ShareUrl = ShareUrl;
	}

	public List<BuyCarItemModel> getTjNewCarList() {
		return TjNewCarList;
	}

	public void setTjNewCarList(List<BuyCarItemModel> tjNewCarList) {
		TjNewCarList = tjNewCarList;
	}

	public List<ValuationSellCarReplaceCycleModel> getChangeCycle() {
		return ChangeCycle;
	}

	public void setChangeCycle(List<ValuationSellCarReplaceCycleModel> changeCycle) {
		ChangeCycle = changeCycle;
	}

	public List<BuyCarDetailFirstLikeModel> getFirstChangeModelList() {
		return FirstChangeModelList;
	}

	public void setFirstChangeModelList(List<BuyCarDetailFirstLikeModel> firstChangeModelList) {
		FirstChangeModelList = firstChangeModelList;
	}

	public List<BuyCarItemModel> getTjOldCarList() {
		return TjOldCarList;
	}

	public void setTjOldCarList(List<BuyCarItemModel> tjOldCarList) {
		TjOldCarList = tjOldCarList;
	}

	@Override
	public String toString() {
		return "ValuationSellCarResult{" +
				"ReportID='" + ReportID + '\'' +
				", MakeId='" + MakeId + '\'' +
				", ModelId='" + ModelId + '\'' +
				", StyleId='" + StyleId + '\'' +
				", ImgUrl='" + ImgUrl + '\'' +
				", MakeName='" + MakeName + '\'' +
				", ModelName='" + ModelName + '\'' +
				", FullName='" + FullName + '\'' +
				", RegDateTime='" + RegDateTime + '\'' +
				", RegDate='" + RegDate + '\'' +
				", Mileage='" + Mileage + '\'' +
				", ProvId='" + ProvId + '\'' +
				", ProvName='" + ProvName + '\'' +
				", CityId='" + CityId + '\'' +
				", CityName='" + CityName + '\'' +
				", NowMsrp='" + NowMsrp + '\'' +
				", C2BALowPrice='" + C2BALowPrice + '\'' +
				", C2BAMidPrice='" + C2BAMidPrice + '\'' +
				", C2BAUpPrice='" + C2BAUpPrice + '\'' +
				", C2BBLowPrice='" + C2BBLowPrice + '\'' +
				", C2BBMidPrice='" + C2BBMidPrice + '\'' +
				", C2BBUpPrice='" + C2BBUpPrice + '\'' +
				", C2BCLowPrice='" + C2BCLowPrice + '\'' +
				", C2BCMidPrice='" + C2BCMidPrice + '\'' +
				", C2BCUpPrice='" + C2BCUpPrice + '\'' +
				", C2CALowPrice='" + C2CALowPrice + '\'' +
				", C2CAMidPrice='" + C2CAMidPrice + '\'' +
				", C2CAUpPrice='" + C2CAUpPrice + '\'' +
				", C2CBLowPrice='" + C2CBLowPrice + '\'' +
				", C2CBMidPrice='" + C2CBMidPrice + '\'' +
				", C2CBUpPrice='" + C2CBUpPrice + '\'' +
				", C2CCLowPrice='" + C2CCLowPrice + '\'' +
				", C2CCMidPrice='" + C2CCMidPrice + '\'' +
				", C2CCUpPrice='" + C2CCUpPrice + '\'' +
				", PriceLevel='" + PriceLevel + '\'' +
				", CurrentModelLevelId='" + CurrentModelLevelId + '\'' +
				", CurrentModelLevelName='" + CurrentModelLevelName + '\'' +
				", BestChangeYear='" + BestChangeYear + '\'' +
				", BestChangePerc='" + BestChangePerc + '\'' +
				", TjModelName='" + TjModelName + '\'' +
				", ShareUrl='" + ShareUrl + '\'' +
				", ChangeCycle=" + ChangeCycle +
				", FirstChangeModelList=" + FirstChangeModelList +
				", TjOldCarList=" + TjOldCarList +
				", TjNewCarList=" + TjNewCarList +
				'}';
	}
}
