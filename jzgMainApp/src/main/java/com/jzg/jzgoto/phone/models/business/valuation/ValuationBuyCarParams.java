package com.jzg.jzgoto.phone.models.business.valuation;

import com.jzg.jzgoto.phone.global.HttpServiceHelper;
import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.tools.MD5Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by WY on 2016/9/28.
 * Function :买家估值参数
 */
public class ValuationBuyCarParams extends BaseParamsModels{

    private String mUrl = HttpServiceHelper.BASE_URL+ "/APPV5/BuyCarAppraiseResult.ashx";

   //?op=GetValuationInfo&sourcetype=3&uid=13&styleid=15190&
   // CityId=201&cityname=北京&&mileage=2.1&regdate=2015-01-01
    public String uid = "0";
    public String styleid;
    public String CityId;
    public String cityname;
    public String mileage;
    public String regdate;
    @Override
    public Map<String, String> getParams() {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("op", "GetValuationInfo");
        map.put("sourcetype", "3");
        map.put("uid", uid);
        map.put("styleid", styleid);
        map.put("CityId", CityId);
        map.put("cityname", cityname);
        map.put("mileage", mileage);
        map.put("regdate", regdate);
        Map<String,String> params = new HashMap<String, String>();
        params.put("op", "GetValuationInfo");
        params.put("sourcetype", "3");
        params.put("uid", uid);
        params.put("styleid", styleid);
        params.put("CityId", CityId);
        params.put("cityname", cityname);
        params.put("mileage", mileage);
        params.put("regdate", regdate);
        params.put("sign", MD5Utils.getMD5Sign(map));
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
