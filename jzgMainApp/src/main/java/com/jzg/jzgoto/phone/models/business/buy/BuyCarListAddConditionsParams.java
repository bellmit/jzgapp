package com.jzg.jzgoto.phone.models.business.buy;

import android.text.TextUtils;

import com.jzg.jzgoto.phone.global.HttpServiceHelper;
import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.tools.MD5Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jzg on 2016/10/9.
 * Function :
 */
public class BuyCarListAddConditionsParams extends BaseParamsModels{

    private String mUrl = HttpServiceHelper.BASE_URL+"/appv5/CarSource.ashx";
   // op=&uid=1&CSUserType=1&ModelLevel=1&ModelLevelName=SUV&MakeID=9&ModelID=100&
   // BeginSellPric=2&EndSellPrice=10&BeginCarAge=0&EndCarAge=3&BeginMileage=0&EndMileage=1
   // &BsqSimpleValue=1&BeginPL=1&EndPL=1.6&PlatForm=1&Countries=1&Seats=5&CarType=1&CityID=201
    public String uid ="0";
    public String CSUserType="";
    public String ModelLevel="";
    public String ModelLevelName="";

    public String MakeID="";
    public String ModelID="";
    public String BeginSellPrice="";
    public String EndSellPrice="";
    public String BeginCarAge="";
    public String EndCarAge="";
    public String BeginMileage="";
    public String EndMileage="";

    public String BsqSimpleValue="";

    public String BeginPL="";
    public String EndPL="";

    public String PlatForm="";
    public String Countries="";
    public String Seats="";
    public String CarType="";
    public String CityID="";
    @Override
    public Map<String, String> getParams() {
        if(TextUtils.isEmpty(MakeID)){
            MakeID = "0";
        }
        if(TextUtils.isEmpty(ModelID)){
            ModelID = "0";
        }
        if(TextUtils.isEmpty(BeginSellPrice)){
            BeginSellPrice = "0";
        }
        if(TextUtils.isEmpty(EndSellPrice)){
            EndSellPrice = "0";
        }
        if(TextUtils.isEmpty(BeginCarAge)){
            BeginCarAge = "0";
        }
        if(TextUtils.isEmpty(EndCarAge)){
            EndCarAge = "0";
        }
        if(TextUtils.isEmpty(BeginMileage)){
            BeginMileage = "0";
        }
        if(TextUtils.isEmpty(EndMileage)){
            EndMileage = "0";
        }
        if(BeginPL==null|| TextUtils.isEmpty(BeginPL)){
            BeginPL ="0";
        }
        if(EndPL==null||TextUtils.isEmpty(EndPL)){
            EndPL ="0";
        }
        if(TextUtils.isEmpty(CityID)){
            CityID = "0";
        }
        if("不限".equals(ModelLevelName)){
            ModelLevelName = "";
        }
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("op", "AddCarSourceConditions");
        map.put("uid", uid);
        map.put("CSUserType", CSUserType);
        map.put("ModelLevel", ModelLevel);
        map.put("ModelLevelName", ModelLevelName);
        map.put("MakeID", MakeID);
        map.put("ModelID", ModelID);
        map.put("MakeName", "");
        map.put("ModelName", "");
        map.put("BeginSellPrice", BeginSellPrice);
        map.put("EndSellPrice", EndSellPrice);
        map.put("BeginCarAge", BeginCarAge);
        map.put("EndCarAge", EndCarAge);
        map.put("BeginMileage", BeginMileage);
        map.put("EndMileage", EndMileage);
        map.put("BsqSimpleValue", BsqSimpleValue);
        map.put("BeginPL", BeginPL);
        map.put("EndPL", EndPL);
        map.put("CarSourceFrom", PlatForm);
        map.put("Countries", Countries);
        map.put("Seats", Seats);
        map.put("CarType", CarType);
        map.put("CityID", CityID);
        map.put("CityName", "");
        Map<String,String> params = new HashMap<String, String>();
        params.put("op", "AddCarSourceConditions");
        params.put("uid", uid);
        params.put("CSUserType", CSUserType);
        params.put("ModelLevel", ModelLevel);
        params.put("ModelLevelName", ModelLevelName);
        params.put("MakeID", MakeID);
        params.put("ModelID", ModelID);
        params.put("MakeName", "");
        params.put("ModelName", "");
        params.put("BeginSellPrice", BeginSellPrice);
        params.put("EndSellPrice", EndSellPrice);
        params.put("BeginCarAge", BeginCarAge);
        params.put("EndCarAge", EndCarAge);
        params.put("BeginMileage", BeginMileage);
        params.put("EndMileage", EndMileage);
        params.put("BsqSimpleValue", BsqSimpleValue);
        params.put("BeginPL", BeginPL);
        params.put("EndPL", EndPL);
        params.put("CarSourceFrom", PlatForm);
        params.put("Countries", Countries);
        params.put("Seats", Seats);
        params.put("CarType", CarType);
        params.put("CityID", CityID);
        params.put("CityName", "");
        params.put("sign", MD5Utils.getMD5Sign(map) );
        return params;
    }

    @Override
    public String toParamsString() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getUrl() {
        // TODO Auto-generated method stub
        return mUrl;
    }
}
