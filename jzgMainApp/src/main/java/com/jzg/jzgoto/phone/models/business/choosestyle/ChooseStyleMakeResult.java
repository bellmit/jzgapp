package com.jzg.jzgoto.phone.models.business.choosestyle;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jzg on 2016/11/14.
 * Function :
 */
public class ChooseStyleMakeResult extends BaseResultModels{

    private List<ChooseStyleMakeModel> HotMakeList = new ArrayList<>();
    private List<ChooseStyleMakeGroupModel> MakeGroupList = new ArrayList<>();
    @Override
    public void setResult(Object obj) {
        Gson gson = new Gson();
        ChooseStyleMakeResult result = gson.fromJson(obj.toString(),ChooseStyleMakeResult.class);
        setStatus(result.getStatus());
        setMsg(result.getMsg());
        if(getStatus()==SUCCESS){
            HotMakeList.addAll(result.HotMakeList);
            MakeGroupList.addAll(result.getMakeGroupList());
        }
    }

    @Override
    public String toResultString() {
        return null;
    }

    public List<ChooseStyleMakeModel> getHotMakeList() {
        return HotMakeList;
    }

    public void setHotMakeList(List<ChooseStyleMakeModel> hotMakeList) {
        HotMakeList = hotMakeList;
    }

    public List<ChooseStyleMakeGroupModel> getMakeGroupList() {
        return MakeGroupList;
    }

    public void setMakeGroupList(List<ChooseStyleMakeGroupModel> makeGroupList) {
        MakeGroupList = makeGroupList;
    }

    @Override
    public String toString() {
        return "ChooseStyleMakeResult{" +
                "HotMakeList=" + HotMakeList +
                ", MakeGroupList=" + MakeGroupList +
                '}';
    }
}
