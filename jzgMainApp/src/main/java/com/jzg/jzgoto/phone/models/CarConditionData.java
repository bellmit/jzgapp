package com.jzg.jzgoto.phone.models;



import java.io.Serializable;
import java.util.List;

public class CarConditionData implements Serializable {

	public int Id = 0;
	public int CSUserType = 0;
	public String CSUserTypeName = "";
	public int CarSourceFrom = 0;
	public String CarSourceFromName = "";
	public int ModelLevel = 0;
	public String ModelLevelName = "";
	public int MakeID = 0;
	public String MakeName = "";
	public int ModelID = 0;
	public String ModelName = "";
	public String BeginSellPrice = "";
	public String EndSellPrice = "";
	public String BeginCarAge = "";//起始车龄
	public String EndCarAge = "";//结束车龄
	public int BeginMileage = 0;//起始行驶里程 单位：公里
	public int EndMileage = 0;//结束行驶里程 单位：公里
	public String BeginPL = "" ;
	public String EndPL = "";
	public int BsqSimpleValue = 0;//变速箱 0：全部 2：手动 3：自动
	public String BsqSimpleText = "";
	public int Seats = 0;
	public int Countries = 0;
	public String CountriesName = "";
	public int CarType = 0;//类型（0全部 1二手车 2新车）
	public int CityID = 0;
	public String CityName = "";

	public List<CarData> OldCarlist;

	public CarConditionData(){

	}

	public int getId(){
		return Id;
	}

	public List<CarData> getCarList(){
		return OldCarlist;
	}

	public void setCarList(List<CarData> dataList){
		OldCarlist = dataList;
	}

	public String getMakeName(){
		return MakeName;
	}

	public String getUserTypeName(){
		return CSUserTypeName;
	}

	public String getCarSourceFromName(){
		return CarSourceFromName;
	}

	public String getModelLevelName(){
		return ModelLevelName;
	}
};
