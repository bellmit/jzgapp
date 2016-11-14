package com.jzg.jzgoto.phone.models.business.choosestyle;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jzg on 2016/11/14.
 * Function :
 */
public class ChooseStyleModeGroupModel implements Serializable {

    private  String ManufacturerName;
    private List<ChooseStyleModeModel> ModelList;

    public String getManufacturerName() {
        return ManufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        ManufacturerName = manufacturerName;
    }

    public List<ChooseStyleModeModel> getModelList() {
        return ModelList;
    }

    public void setModelList(List<ChooseStyleModeModel> modelList) {
        ModelList = modelList;
    }

    @Override
    public String toString() {
        return "ChooseStyleModeGroupModel{" +
                "ManufacturerName='" + ManufacturerName + '\'' +
                ", ModelList=" + ModelList +
                '}';
    }
}
