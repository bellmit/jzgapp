package com.jzg.jzgoto.phone.models.business.choosestyle;

import com.jzg.jzgoto.phone.global.HttpServiceHelper;
import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.tools.MD5Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jzg on 2016/11/14.
 * Function :
 */
public class ChooseStyleMakeParams extends BaseParamsModels{

    private final String url = HttpServiceHelper.BASE_URL + "/appv5/GetMakeModelStyleAll.ashx";

    @Override
    public Map<String, String> getParams() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("op", "GetMake");
        Map<String, Object> md = new HashMap<String,Object>();
        md.put("op", "GetMake");
        params.put("sign", MD5Utils.getMD5Sign(md));
        return params;
    }
    @Override
    public String toParamsString() {
        return null;
    }
    @Override
    public String getUrl() {
        return url;
    }
}
