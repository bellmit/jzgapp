package com.jzg.jzgoto.phone.models.business.choosestyle;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jzg on 2016/11/14.
 * Function :
 */
public class ChooseStyleResult extends BaseResultModels{

    private List<ChooseStyleGroupModel> YearGroupList = new ArrayList<>();
    @Override
    public void setResult(Object obj) {
        Gson gson = new Gson();
        ChooseStyleResult result = gson.fromJson(obj.toString(),ChooseStyleResult.class);
        setStatus(result.getStatus());
        setMsg(result.getMsg());
        if(getStatus()==SUCCESS){
            YearGroupList.addAll(result.getYearGroupList());
        }
    }

    @Override
    public String toResultString() {
        return null;
    }

    public List<ChooseStyleGroupModel> getYearGroupList() {
        return YearGroupList;
    }

    public void setYearGroupList(List<ChooseStyleGroupModel> yearGroupList) {
        YearGroupList = yearGroupList;
    }

    @Override
    public String toString() {
        return "ChooseStyleResult{" +
                "YearGroupList=" + YearGroupList +
                '}';
    }
}
