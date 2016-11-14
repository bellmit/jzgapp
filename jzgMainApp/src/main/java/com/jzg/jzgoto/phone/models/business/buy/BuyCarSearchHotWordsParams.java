package com.jzg.jzgoto.phone.models.business.buy;

import com.jzg.jzgoto.phone.global.HttpServiceHelper;
import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.tools.MD5Utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by WY on 2016/9/26.
 * Function :
 */
public class BuyCarSearchHotWordsParams extends BaseParamsModels
implements Serializable{
    private static final long serialVersionUID = 1L;
    private String mUrl = HttpServiceHelper.BASE_URL+"/appv5/CarSource.ashx";
    @Override
    public Map<String, String> getParams() {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("op", "SearchListName");
        Map<String,String> params = new HashMap<String, String>();
        params.put("op", "SearchListName");
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
