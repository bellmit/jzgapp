package com.jzg.jzgoto.phone.models.business.valuation;

import java.io.Serializable;

/**
 * Created by WY on 2016/9/28.
 * Function :
 */
public class ValuationBuyCarNearCityModel implements Serializable{

    private String ProvId;
    private String ProvName;
    private String CityId;
    private String CityName;
    private String Sum;
    private String Price;

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

    public String getSum() {
        return Sum;
    }

    public void setSum(String Sum) {
        this.Sum = Sum;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String Price) {
        this.Price = Price;
    }

    @Override
    public String toString() {
        return "ValuationBuyCarNearCityModel{" +
                "ProvId='" + ProvId + '\'' +
                ", ProvName='" + ProvName + '\'' +
                ", CityId='" + CityId + '\'' +
                ", CityName='" + CityName + '\'' +
                ", Sum='" + Sum + '\'' +
                ", Price='" + Price + '\'' +
                '}';
    }
}
