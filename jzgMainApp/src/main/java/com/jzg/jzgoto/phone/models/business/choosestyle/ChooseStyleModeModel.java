package com.jzg.jzgoto.phone.models.business.choosestyle;

import java.io.Serializable;

/**
 * Created by jzg on 2016/11/14.
 * Function :
 */
public class ChooseStyleModeModel implements Serializable{


    private String Id;
    private String Name;
    private String modelimgpath;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getModelimgpath() {
        return modelimgpath;
    }

    public void setModelimgpath(String modelimgpath) {
        this.modelimgpath = modelimgpath;
    }

    @Override
    public String toString() {
        return "ChooseStyleModeModel{" +
                "Id='" + Id + '\'' +
                ", Name='" + Name + '\'' +
                ", modelimgpath='" + modelimgpath + '\'' +
                '}';
    }
}
