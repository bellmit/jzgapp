package com.jzg.jzgoto.phone.models.business.valuation;

import java.io.Serializable;

/**
 * Created by WY on 2016/9/28.
 * Function :
 */
public class ValuationSellCarReplaceCycleModel implements Serializable{

    private String Year;
    private String Percentage;

    public String getYear() {
        return Year;
    }

    public void setYear(String Year) {
        this.Year = Year;
    }

    public String getPercentage() {
        return Percentage;
    }

    public void setPercentage(String Percentage) {
        this.Percentage = Percentage;
    }

    @Override
    public String toString() {
        return "ValuationSellCarReplaceCycleModel{" +
                "Year='" + Year + '\'' +
                ", Percentage='" + Percentage + '\'' +
                '}';
    }
}
