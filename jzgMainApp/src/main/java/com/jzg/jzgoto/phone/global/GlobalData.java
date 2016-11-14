package com.jzg.jzgoto.phone.global;

import com.jzg.jzgoto.phone.models.business.carmanager.CarManagerMyCarData;
import com.jzg.jzgoto.phone.models.business.carmanager.ProvinceSummaryData;
import java.util.List;


public class GlobalData {
    private static GlobalData sInstance = new GlobalData();
	
	private String carManagerId = "0";
    private String userId = "0";
    private List<ProvinceSummaryData> provinceList = null;
    private CarManagerMyCarData currMyCarData;

    private GlobalData() {

    }

    public static GlobalData getInstance() {
        return sInstance;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setCarManagerId(String carManagerId) {
        this.carManagerId = carManagerId;
    }

    public String getCarManagerId() {
        return carManagerId;
    }

    public List<ProvinceSummaryData> getProvinceList(){
        return provinceList;
    }

    public void setProvinceList(List<ProvinceSummaryData> provinceList){
        this.provinceList = provinceList;
    }

    public void setMyCarData(CarManagerMyCarData currMyCarData){
        this.currMyCarData = currMyCarData;
    }

    public CarManagerMyCarData getMyCarData(){
        return currMyCarData;
    }
}
