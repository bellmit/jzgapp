package com.jzg.jzgoto.phone.models;


import android.text.TextUtils;

import java.io.Serializable;

public class CarData implements Serializable {
	public static final String CAR_STATUS_ON_SELL = "1";//在售
	public static final String CAR_STATUS_OFF_SELL = "0";//下架

	public int CarSourceID;
	public int StyleId;
	public String CarSourceImageUrl;
	public String FullName;
	public int PersonalBusiness;
	public String Mileage;
	public String ReleaseTime;
	public String PublishTime;
	public String SellPrice;
	public int CarSourceFrom;
	public String CityName;
	public String ApprisePrice;
	public String JZGB2CPrice;
	public int ModelId;
	public String ModelLevelName;
	public String Url;
	public int IsNewCar;
	public String MinMsrp;
	public String MaxMsrp;
	public String IsLoan;
	public int ScId;
	public String Status;

	public CarData(){

	}

	public int getCarId(){
		return CarSourceID;
	}

	public String getCarName(){
		return FullName;
	}

	public String getImageUrl(){
		return CarSourceImageUrl;
	}

	public String getCityName(){
		return CityName;
	}

	public String getMileage(){
		return Mileage;
	}

	public String getPublishTime(){
		return PublishTime;
	}

	public String getReleaseTime(){
		return ReleaseTime;
	}

	public String getSellPrice(){
		return SellPrice;
	}

	public String getApprisePrice(){
		return ApprisePrice;
	}

	public String getB2CPrice(){
		return JZGB2CPrice;
	}

	public int getFavoriteId(){
		return ScId;
	}

	public String getStatus(){
		return Status;
	}

	public int getCarSourceFrom(){
		return CarSourceFrom;
	}

	public boolean isOnSell(){
		if (!TextUtils.isEmpty(Status)){
			if (Status.equals(CAR_STATUS_ON_SELL)){
				return true;
			}
		}
		return false;
	}


};
