package com.jzg.jzgoto.phone.models;


import com.jzg.pricechange.phone.JzgCarChooseStyle;

import java.io.Serializable;

public class CarValuateOptionData implements Serializable {

	public JzgCarChooseStyle mCarChooseStyle;
	public String mSytleId;
	public String mCityName = "";
	public String mCityId = "";
	public String mRegisterDate = "";
	public String mMileage = "";

	public CarValuateOptionData(){
	}

};
