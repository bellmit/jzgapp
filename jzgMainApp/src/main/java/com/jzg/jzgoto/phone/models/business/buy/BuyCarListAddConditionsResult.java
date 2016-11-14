package com.jzg.jzgoto.phone.models.business.buy;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;

/**
 * Created by jzg on 2016/10/9.
 * Function :
 */
public class BuyCarListAddConditionsResult extends BaseResultModels{

    @Override
    public void setResult(Object obj) {
        Gson gson = new Gson();
        System.out.println("添加订阅--"+obj.toString());
        BuyCarListAddConditionsResult result = gson.fromJson(obj.toString(), BuyCarListAddConditionsResult.class);
        setMsg(result.getMsg());
        setStatus(result.getStatus());
    }

    @Override
    public String toResultString() {
        // TODO Auto-generated method stub
        return null;
    }
}
