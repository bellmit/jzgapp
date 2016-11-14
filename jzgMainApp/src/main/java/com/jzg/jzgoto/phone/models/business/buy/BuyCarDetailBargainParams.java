package com.jzg.jzgoto.phone.models.business.buy;

import com.jzg.jzgoto.phone.global.HttpServiceHelper;
import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.tools.MD5Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by WY on 2016/9/28.
 * Function :
 */
public class BuyCarDetailBargainParams extends BaseParamsModels{

    //  op=BargainPrice&CarSourceId=1492&CarSourceFrom=2&
    // sourcetype=3&sellPrice=25.4&expectedPrice=23.5&mobile=13261297798
    private String mUrl = HttpServiceHelper.BASE_URL+"/APPV5/CarSourceDetail.ashx";

    public String CarSourceId;
    public String CarSourceFrom;
    public String sourcetype = "3";//3：Android
    public String sellPrice;
    public String expectedPrice;
    public String mobile;

    @Override
    public Map<String, String> getParams() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("op", "BargainPrice");
        map.put("CarSourceId", CarSourceId);
        map.put("CarSourceFrom", CarSourceFrom);
        map.put("sourcetype", sourcetype);
        map.put("sellPrice", sellPrice);
        map.put("expectedPrice", expectedPrice);
        map.put("mobile", mobile);
        Map<String,String>params = new HashMap<String, String>();
        params.put("op", "BargainPrice");
        params.put("CarSourceId", CarSourceId);
        params.put("CarSourceFrom", CarSourceFrom);
        params.put("sourcetype", sourcetype);
        params.put("sellPrice", sellPrice);
        params.put("expectedPrice", expectedPrice);
        params.put("mobile", mobile);
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
