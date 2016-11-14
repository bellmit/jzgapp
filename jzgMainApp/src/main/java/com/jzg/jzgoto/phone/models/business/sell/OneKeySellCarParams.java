package com.jzg.jzgoto.phone.models.business.sell;

import com.jzg.jzgoto.phone.global.HttpServiceHelper;
import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.tools.MD5Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * author: guochen
 * date: 2016/9/28 20:11
 * email: 
 */
public class OneKeySellCarParams extends BaseParamsModels {
    private String mUrl = HttpServiceHelper.BASE_URL +"/appv5/SendCar.ashx";
    public String styleid;
    public String regdate;
    public String mileage;
    public String cityid;
    @Override
    public Map<String, String> getParams() {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("op", "SellC2BPrice");
        map.put("styleid", styleid);
        map.put("mileage", mileage);
        map.put("regdate", regdate);
        map.put("cityid", cityid);
        Map<String,String> params = new HashMap<String, String>();
        params.put("op", "SellC2BPrice");
        params.put("styleid", styleid+"");
        params.put("mileage", mileage);
        params.put("regdate", regdate);
        params.put("cityid", cityid+"");
        params.put("sign", MD5Utils.getMD5Sign(map));
        return params;
    }

    @Override
    public String toParamsString() {
        return null;
    }

    @Override
    public String getUrl() {
        return mUrl;
    }
}
