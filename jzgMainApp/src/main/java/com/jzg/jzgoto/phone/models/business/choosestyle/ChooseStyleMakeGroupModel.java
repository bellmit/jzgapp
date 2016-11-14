package com.jzg.jzgoto.phone.models.business.choosestyle;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jzg on 2016/11/14.
 * Function :
 */
public class ChooseStyleMakeGroupModel implements Serializable{

    private String GroupName;
    private List<ChooseStyleMakeModel> MakeList;

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String groupName) {
        GroupName = groupName;
    }

    public List<ChooseStyleMakeModel> getMakeList() {
        return MakeList;
    }

    public void setMakeList(List<ChooseStyleMakeModel> makeList) {
        MakeList = makeList;
    }

    @Override
    public String toString() {
        return "ChooseStyleMakeGroupModel{" +
                "GroupName='" + GroupName + '\'' +
                ", MakeList=" + MakeList +
                '}';
    }
}
