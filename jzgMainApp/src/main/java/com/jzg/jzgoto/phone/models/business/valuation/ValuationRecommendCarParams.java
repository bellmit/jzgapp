package com.jzg.jzgoto.phone.models.business.valuation;

import com.jzg.jzgoto.phone.global.HttpServiceHelper;
import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.tools.MD5Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jzg on 2016/10/8.
 * Function :加价推荐 参数
 */
public class ValuationRecommendCarParams  extends BaseParamsModels{
    private String mUrl = HttpServiceHelper.BASE_URL+ "/APPV5/SellCarAppraiseResult.ashx";
 // op=GetGuzhiTjCar&guzhiPrice=3.1&AddPrice=5&PageIndex=1&PageSize=5
    public String guzhiPrice;
    public String AddPrice;
    public Map<String, String> getParams() {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("op", "GetGuzhiTjCar");
        map.put("guzhiPrice", guzhiPrice);
        map.put("AddPrice", AddPrice);
        map.put("PageIndex", "1");
        map.put("PageSize", "5");
        Map<String,String> params = new HashMap<String, String>();
        params.put("op", "GetGuzhiTjCar");
        params.put("guzhiPrice", guzhiPrice);
        params.put("AddPrice", AddPrice);
        params.put("PageIndex", "1");
        params.put("PageSize", "5");
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
        return mUrl;
    }
}
