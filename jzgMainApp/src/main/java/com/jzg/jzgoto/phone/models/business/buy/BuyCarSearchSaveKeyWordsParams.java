package com.jzg.jzgoto.phone.models.business.buy;

import com.jzg.jzgoto.phone.global.HttpServiceHelper;
import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.tools.MD5Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jzg on 2016/9/27.
 * Function :
 */
public class BuyCarSearchSaveKeyWordsParams extends BaseParamsModels{

    private static final long serialVersionUID = 1L;
    private String mUrl = HttpServiceHelper.BASE_URL+"/appv5/CarSource.ashx";
    public String Keyword;
    @Override
    public Map<String, String> getParams() {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("op", "AddSearchName");
        map.put("Keyword", Keyword);
        Map<String,String> params = new HashMap<String, String>();
        params.put("op", "AddSearchName");
        params.put("Keyword", Keyword);
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
