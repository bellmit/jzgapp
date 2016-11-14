package com.jzg.jzgoto.phone.models.business.choosestyle;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jzg on 2016/11/14.
 * Function :
 */
public class ChooseStyleModeResult extends BaseResultModels{


    private List<ChooseStyleModeGroupModel> ManufacturerList = new ArrayList<>();
    @Override
    public void setResult(Object obj) {
        Gson gson = new Gson();
        ChooseStyleModeResult result = gson.fromJson(obj.toString(),ChooseStyleModeResult.class);
        setStatus(result.getStatus());
        setMsg(result.getMsg());
        if(getStatus()==SUCCESS){
            ManufacturerList.addAll(result.getManufacturerList());
        }
    }

    @Override
    public String toResultString() {
        return null;
    }

    public List<ChooseStyleModeGroupModel> getManufacturerList() {
        return ManufacturerList;
    }

    public void setManufacturerList(List<ChooseStyleModeGroupModel> manufacturerList) {
        ManufacturerList = manufacturerList;
    }

    @Override
    public String toString() {
        return "ChooseStyleModeResult{" +
                "ManufacturerList=" + ManufacturerList +
                '}';
    }
}
