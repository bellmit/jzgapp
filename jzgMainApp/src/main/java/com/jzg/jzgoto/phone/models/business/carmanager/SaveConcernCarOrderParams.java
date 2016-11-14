package com.jzg.jzgoto.phone.models.business.carmanager;

import com.jzg.jzgoto.phone.global.HttpServiceHelper;
import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.tools.MD5Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * author: guochen
 * date: 2016/10/11 16:25
 * email: 
 */
public class SaveConcernCarOrderParams extends BaseParamsModels {
    private String mUrl = HttpServiceHelper.BASE_CAR_MANAGER_URL + "/appv5/CarButler.ashx";
    public String MyCareListStr;
    @Override
    public Map<String, String> getParams() {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("op", "MyCareMoveIndexOrder");
        map.put("MyCareListStr", MyCareListStr);

        Map<String, String> params = new HashMap<String, String>();
        params.put("op", "MyCareMoveIndexOrder");
        params.put("MyCareListStr", MyCareListStr);
        params.put("sign", MD5Utils.getMD5Sign(map) );
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
