package com.jzg.jzgoto.phone.models.business.choosestyle;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jzg on 2016/11/14.
 * Function :
 */
public class ChooseStyleGroupModel implements Serializable{

    private String Year;
    private List<ChooseStyleModel> StyleList;

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public List<ChooseStyleModel> getStyleList() {
        return StyleList;
    }

    public void setStyleList(List<ChooseStyleModel> styleList) {
        StyleList = styleList;
    }

    @Override
    public String toString() {
        return "ChooseStyleGroupModel{" +
                "Year='" + Year + '\'' +
                ", StyleList=" + StyleList +
                '}';
    }
}
