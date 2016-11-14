package com.jzg.jzgoto.phone.models.business.choosestyle;

import java.io.Serializable;

/**
 * Created by jzg on 2016/11/14.
 * Function :
 */
public class ChooseStyleModel implements Serializable{

    private String Id;
    private String Name;
    private String Year;
    private String NextYear;
    private String NowMsrp;
    private String FullName;

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

    public String getYear() {
        return Year;
    }

    public void setYear(String Year) {
        this.Year = Year;
    }

    public String getNextYear() {
        return NextYear;
    }

    public void setNextYear(String NextYear) {
        this.NextYear = NextYear;
    }

    public String getNowMsrp() {
        return NowMsrp;
    }

    public void setNowMsrp(String NowMsrp) {
        this.NowMsrp = NowMsrp;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    @Override
    public String toString() {
        return "ChooseStyleModel{" +
                "Id='" + Id + '\'' +
                ", Name='" + Name + '\'' +
                ", Year='" + Year + '\'' +
                ", NextYear='" + NextYear + '\'' +
                ", NowMsrp='" + NowMsrp + '\'' +
                ", FullName='" + FullName + '\'' +
                '}';
    }
}
