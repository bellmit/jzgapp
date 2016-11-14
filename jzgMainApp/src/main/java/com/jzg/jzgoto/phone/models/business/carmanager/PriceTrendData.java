package com.jzg.jzgoto.phone.models.business.carmanager;


import java.io.Serializable;


public class PriceTrendData implements Serializable {

    public String Date;
    public String C2BPrice;

    public PriceTrendData() {
    }

    public PriceTrendData(String date, String price) {
        Date = date;
        C2BPrice = price;
    }

    public String getDate() {
        return Date;
    }
	
	public String getPrice() {
        return C2BPrice;
    }

};
