package com.jzg.jzgoto.phone.models.business.user;


import com.jzg.jzgoto.phone.models.CarData;

import java.io.Serializable;
import java.util.List;


public class SubscribeData implements Serializable {

    public int Id;

    public List<CarData> OldCarlist;

    public SubscribeData() {
    }

    public void setCarList(List<CarData> dataList){
        OldCarlist = dataList;
    }

    public List<CarData> getCarList(){
        return OldCarlist;
    }
};
