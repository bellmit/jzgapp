package com.jzg.jzgoto.phone.models.business.carmanager;


import com.jzg.jzgoto.phone.models.BannerData;

import java.io.Serializable;
import java.util.List;

public class CarManagerMyCarData implements Serializable {

    public int MyLoveCarId;
    public String StylePic;
    public String MkMdName;
    public String ModelName;
    public String FullName;
    public String CarNumber;
    public String EngineNumber;
    public String MakeId;
    public String ModelId;
    public String StyleId;
    public String Mileage;
    public String Regdate;
    public String ProvId;
    public String CityId;
    public String CityName;
    public String LastGuzhiTime;
    public String LastGuzhiPrice;
    public List<PriceTrendData> PriceTrend;
    public String WeizhangCount;
    public String NextBaoYangTime;
    public String NextBaoxianTime;
    public List<FocusCarData> FirstChangeModelList;


    public List<BannerData> carBannerList;

    public CarManagerMyCarData() {
    }

    public void setCarId(int id) {
        this.MyLoveCarId = id;
    }

    public int getCarId() {
        return MyLoveCarId;
    }


    public void setImageUrl(String carImageUrl) {
        this.StylePic = carImageUrl;
    }

    public String getImageUrl() {
        return StylePic;
    }

    public String getMakerModelName(){
        return MkMdName;
    }

    public String getFullName(){
        return FullName;
    }

    public String getEngineNumber(){
        return EngineNumber;
    }
    public String getMakeId(){
        return MakeId;
    }
    public String getModelId(){
        return ModelId;
    }
    public String getStyleId(){
        return StyleId;
    }

    public void setMileage(String mileage){
        Mileage = mileage;
    }

    public String getMileage(){
        return Mileage;
    }

    public String getRegisterDate(){
        return Regdate;
    }

    public String getProvinceId(){
        return ProvId;
    }

    public List<PriceTrendData> getPriceTrend(){
        return PriceTrend;
    }

    public String getLastEvaluationTime(){
        return LastGuzhiTime;
    }

    public String getLastEvaluationPrice(){
        return LastGuzhiPrice;
    }

    public String getOffenceCount(){
        return WeizhangCount;
    }

    public String getNextMaintenanceTime(){
        return NextBaoYangTime;
    }

    public String getNextRepairTime(){
        return NextBaoxianTime;
    }

    public List<FocusCarData> getRecommendFocusCarList(){
        return FirstChangeModelList;
    }

    public void setCarModelName(String carModelName) {
        this.ModelName = carModelName;
    }

    public String getCarModelName() {
        return ModelName;
    }

    public void setCarLicenseNo(String carLicenseNo) {
        this.CarNumber = carLicenseNo;
    }

    public String getCarLicenseNo() {
        return CarNumber;
    }

    public String getCityId(){
        return CityId;
    }

    public String getCityName(){
        return CityName;
    }


    public void setBannerList( List<BannerData> carBannerList) {
        this.carBannerList = carBannerList;
    }

    public  List<BannerData> getBannerList() {
        return carBannerList;
    }
};
