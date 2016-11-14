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
 * Function :买家估值结果
 */
public class ValuationBuyCarResult extends BaseResultModels{

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
    private String CurrentModelLevelId;
    private String CurrentModelLevelName;
    private String BaoZhilvRank;
    private String BaoZhilvCityId;
    private String BaoZhilvCityName;
    private String BaoZhilvLevel;
    private String BaoZhilvLevelName;
    private String B2CAMidPrice;
    private String B2CBMidPrice;
    private String B2CCMidPrice;
    private String C2CAMidPrice;
    private String C2CBMidPrice;
    private String C2CCMidPrice;
    private String InSaleSum;
    private String InSaleMinPrice;
    private String InSaleMaxPrice;
    private String TjModelName;
    private String ShareUrl;
    private List<ValuationBuyCarNearCityModel>NearCity = new ArrayList<>();
    private List<BuyCarDetailFirstLikeModel>FirstLikeModelList = new ArrayList<>();
    private List<BuyCarItemModel>TjOldCarList = new ArrayList<>();
    private List<BuyCarItemModel>TjNewCarList = new ArrayList<>();

    @Override
    public void setResult(Object obj) {
        System.out.println("买车估值"+obj.toString());
        Gson gson = new Gson();
        ValuationBuyCarResult result = gson.fromJson(obj.toString(), ValuationBuyCarResult.class);
        setStatus(result.getStatus());
        setMsg(result.getMsg());
        if(result.getStatus()== Constant.SUCCESS){
            NearCity.addAll(result.getNearCity());
            FirstLikeModelList.addAll(result.getFirstLikeModelList());
            TjOldCarList.addAll(result.getTjOldCarList());
            TjNewCarList.addAll(result.getTjNewCarList());
            setReportID(result.getReportID());
            setMakeId(result.getMakeId());
            setModelId(result.getModelId());
            setStyleId(result.getStyleId());
            setImgUrl(result.getImgUrl());
            setMakeName(result.getMakeName());
            setModelName(result.getModelName());
            setFullName(result.getFullName());
            setRegDateTime(result.getRegDateTime());
            setRegDate(result.getRegDate());
            setMileage(result.getMileage());
            setProvId(result.getProvId());
            setProvName(result.getProvName());
            setCityId(result.getCityId());
            setCityName(result.getCityName());
            setNowMsrp(result.getNowMsrp());
            setCurrentModelLevelId(result.getCurrentModelLevelId());
            setCurrentModelLevelName(result.getCurrentModelLevelName());
            setB2CAMidPrice(result.getB2CAMidPrice());
            setB2CBMidPrice(result.getB2CBMidPrice());
            setB2CCMidPrice(result.getB2CCMidPrice());
            setC2CAMidPrice(result.getC2CAMidPrice());
            setC2CBMidPrice(result.getC2CBMidPrice());
            setC2CCMidPrice(result.getC2CCMidPrice());
            setInSaleSum(result.getInSaleSum());
            setInSaleMinPrice(result.getInSaleMinPrice());
            setInSaleMaxPrice(result.getInSaleMaxPrice());
            setTjModelName(result.getTjModelName());
            setShareUrl(result.getShareUrl());
        }
    }

    @Override
    public String toResultString() {
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

    public String getBaoZhilvLevel() {
        return BaoZhilvLevel;
    }

    public void setBaoZhilvLevel(String BaoZhilvLevel) {
        this.BaoZhilvLevel = BaoZhilvLevel;
    }

    public String getBaoZhilvLevelName() {
        return BaoZhilvLevelName;
    }

    public void setBaoZhilvLevelName(String BaoZhilvLevelName) {
        this.BaoZhilvLevelName = BaoZhilvLevelName;
    }

    public String getB2CAMidPrice() {
        return B2CAMidPrice;
    }

    public void setB2CAMidPrice(String B2CAMidPrice) {
        this.B2CAMidPrice = B2CAMidPrice;
    }

    public String getB2CBMidPrice() {
        return B2CBMidPrice;
    }

    public void setB2CBMidPrice(String B2CBMidPrice) {
        this.B2CBMidPrice = B2CBMidPrice;
    }

    public String getB2CCMidPrice() {
        return B2CCMidPrice;
    }

    public void setB2CCMidPrice(String B2CCMidPrice) {
        this.B2CCMidPrice = B2CCMidPrice;
    }

    public String getC2CAMidPrice() {
        return C2CAMidPrice;
    }

    public void setC2CAMidPrice(String C2CAMidPrice) {
        this.C2CAMidPrice = C2CAMidPrice;
    }

    public String getC2CBMidPrice() {
        return C2CBMidPrice;
    }

    public void setC2CBMidPrice(String C2CBMidPrice) {
        this.C2CBMidPrice = C2CBMidPrice;
    }

    public String getC2CCMidPrice() {
        return C2CCMidPrice;
    }

    public void setC2CCMidPrice(String C2CCMidPrice) {
        this.C2CCMidPrice = C2CCMidPrice;
    }

    public String getInSaleSum() {
        return InSaleSum;
    }

    public void setInSaleSum(String InSaleSum) {
        this.InSaleSum = InSaleSum;
    }

    public String getInSaleMinPrice() {
        return InSaleMinPrice;
    }

    public void setInSaleMinPrice(String InSaleMinPrice) {
        this.InSaleMinPrice = InSaleMinPrice;
    }

    public String getInSaleMaxPrice() {
        return InSaleMaxPrice;
    }

    public void setInSaleMaxPrice(String InSaleMaxPrice) {
        this.InSaleMaxPrice = InSaleMaxPrice;
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

    public List<ValuationBuyCarNearCityModel> getNearCity() {
        return NearCity;
    }

    public void setNearCity(List<ValuationBuyCarNearCityModel> nearCity) {
        NearCity = nearCity;
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
        return "ValuationBuyCarResult{" +
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
                ", CurrentModelLevelId='" + CurrentModelLevelId + '\'' +
                ", CurrentModelLevelName='" + CurrentModelLevelName + '\'' +
                ", BaoZhilvRank='" + BaoZhilvRank + '\'' +
                ", BaoZhilvCityId='" + BaoZhilvCityId + '\'' +
                ", BaoZhilvCityName='" + BaoZhilvCityName + '\'' +
                ", BaoZhilvLevel='" + BaoZhilvLevel + '\'' +
                ", BaoZhilvLevelName='" + BaoZhilvLevelName + '\'' +
                ", B2CAMidPrice='" + B2CAMidPrice + '\'' +
                ", B2CBMidPrice='" + B2CBMidPrice + '\'' +
                ", B2CCMidPrice='" + B2CCMidPrice + '\'' +
                ", C2CAMidPrice='" + C2CAMidPrice + '\'' +
                ", C2CBMidPrice='" + C2CBMidPrice + '\'' +
                ", C2CCMidPrice='" + C2CCMidPrice + '\'' +
                ", InSaleSum='" + InSaleSum + '\'' +
                ", InSaleMinPrice='" + InSaleMinPrice + '\'' +
                ", InSaleMaxPrice='" + InSaleMaxPrice + '\'' +
                ", TjModelName='" + TjModelName + '\'' +
                ", ShareUrl='" + ShareUrl + '\'' +
                ", NearCity=" + NearCity +
                ", FirstLikeModelList=" + FirstLikeModelList +
                ", TjOldCarList=" + TjOldCarList +
                ", TjNewCarList=" + TjNewCarList +
                '}';
    }
}
