package com.jzg.jzgoto.phone.models.business.sell;

import java.io.Serializable;

/**
 * Created by WY on 2016/10/12.
 * Function :跳转至新车置换界面
 */
public class ReplaceNewCarIntentModel implements Serializable{

    private String makeId;
    private String modelId;
    private String styleId;
    private String fullName;
    private String regDate;
    private String mileage;
    private String provinceName;
    private String cityName;
    private String cityId;
    private String reportId = "";

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getMakeId() {
        return makeId;
    }

    public void setMakeId(String makeId) {
        this.makeId = makeId;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getStyleId() {
        return styleId;
    }

    public void setStyleId(String styleId) {
        this.styleId = styleId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    @Override
    public String toString() {
        return "ReplaceNewCarIntentModel{" +
                "makeId='" + makeId + '\'' +
                ", modelId='" + modelId + '\'' +
                ", styleId='" + styleId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", regDate='" + regDate + '\'' +
                ", mileage='" + mileage + '\'' +
                ", provinceName='" + provinceName + '\'' +
                ", cityName='" + cityName + '\'' +
                ", cityId='" + cityId + '\'' +
                ", reportId='" + reportId + '\'' +
                '}';
    }
}
