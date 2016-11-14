package com.jzg.jzgoto.phone.models.business.sell;

import com.jzg.jzgoto.phone.global.HttpServiceHelper;
import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.tools.MD5Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * author: guochen
 * date: 2016/9/29 17:19
 * email: 
 */
public class ReleaseSellTruckMessageParams extends BaseParamsModels {
    private String mUrl = HttpServiceHelper.BASE_URL + "/appv5/SendCar.ashx";
    public String styleid;
    public String ButlerId;//车管家id
    public String regdate;
    public String mileage;
    public String cityid;
    public String uid;
    public String cityname;
    public String sourcetype;//来源（3安卓 4iOS）
    public String sellprice;//销售价格
    public String mobile;
    public String Tripartite;//发送第三方拼接ID（1，2，3，4）
    public String ValidCodes;//验证码

    @Override
    public Map<String, String> getParams() {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("op", "SendCarSourceNew");
        map.put("styleid", styleid);
        map.put("ButlerId", ButlerId);
        map.put("regdate", regdate);
        map.put("mileage", mileage);
        map.put("cityid", cityid);
        map.put("uid", uid);
        map.put("cityname", cityname);
        map.put("sourcetype", "3");
        map.put("sellprice", sellprice);
        map.put("mobile", mobile);
        map.put("Tripartite", Tripartite);
        map.put("ValidCodes", ValidCodes);
        Map<String,String> params = new HashMap<String, String>();
        params.put("op", "SendCarSourceNew");
        params.put("styleid", styleid);
        params.put("ButlerId", ButlerId);
        params.put("regdate", regdate);
        params.put("mileage", mileage);
        params.put("cityid", cityid);
        params.put("uid", uid);
        params.put("cityname", cityname);
        params.put("sourcetype", "3");
        params.put("sellprice", sellprice);
        params.put("mobile", mobile);
        params.put("Tripartite", Tripartite);
        params.put("ValidCodes", ValidCodes);
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
