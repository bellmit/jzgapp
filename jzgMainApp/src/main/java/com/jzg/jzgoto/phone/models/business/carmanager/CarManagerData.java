package com.jzg.jzgoto.phone.models.business.carmanager;


import com.jzg.jzgoto.phone.models.RecommendCardData;
import com.jzg.jzgoto.phone.models.RecommendCardList;

import java.io.Serializable;
import java.util.List;


public class CarManagerData implements Serializable {

    public int BulterId;
    public int TotalMyLoveCar;
    public List<CarManagerMyCarData> ListMyLoveCar;
    public int TotalMyCareCar;
    public List<FocusCarData> ListMyCareCar;
    public List<RecommendCardList> CardTotalList;

    public CarManagerData() {
    }

    public int getCarManagerId(){
        return BulterId;
    }

    public int getMyCarCount(){
        return TotalMyLoveCar;
    }

    public List<CarManagerMyCarData> getMyCarList(){
        return ListMyLoveCar;
    }

    public int getFocusCarCount(){
        return TotalMyCareCar;
    }

    public List<FocusCarData> getFocusCarList(){
        return ListMyCareCar;
    }

    public List<RecommendCardList> getRecommendCardList(){
        return CardTotalList;
    }
};
