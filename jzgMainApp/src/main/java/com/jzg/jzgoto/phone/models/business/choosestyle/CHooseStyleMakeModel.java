package com.jzg.jzgoto.phone.models.business.choosestyle;

import java.io.Serializable;

/**
 * Created by jzg on 2016/11/14.
 * Function :
 */
public class ChooseStyleMakeModel implements Serializable{

    private String MakeId;
    private String MakeName;
    private String MakeLogo;
    private String GroupName;

    public String getMakeId() {
        return MakeId;
    }

    public void setMakeId(String MakeId) {
        this.MakeId = MakeId;
    }

    public String getMakeName() {
        return MakeName;
    }

    public void setMakeName(String MakeName) {
        this.MakeName = MakeName;
    }

    public String getMakeLogo() {
        return MakeLogo;
    }

    public void setMakeLogo(String MakeLogo) {
        this.MakeLogo = MakeLogo;
    }

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String GroupName) {
        this.GroupName = GroupName;
    }

    @Override
    public String toString() {
        return "CHooseStyleMakeModel{" +
                "MakeId='" + MakeId + '\'' +
                ", MakeName='" + MakeName + '\'' +
                ", MakeLogo='" + MakeLogo + '\'' +
                ", GroupName='" + GroupName + '\'' +
                '}';
    }
}
