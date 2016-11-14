package com.jzg.jzgoto.phone.models.business.sell;

import com.jzg.jzgoto.phone.global.HttpServiceHelper;
import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.tools.MD5Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * author: guochen
 * date: 2016/9/28 21:09
 * email:
 * 一键发布平台参数
 */
public class OneKeyReleasePlatformParams extends BaseParamsModels {
    private String mUrl = HttpServiceHelper.BASE_URL +"/appv5/SendCar.ashx";
    @Override
    public Map<String, String> getParams() {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("op", "SendCarPlatList");
        Map<String,String> params = new HashMap<String, String>();
        params.put("op", "SendCarPlatList");
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
