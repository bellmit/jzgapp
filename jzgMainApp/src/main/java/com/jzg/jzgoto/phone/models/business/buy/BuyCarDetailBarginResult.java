package com.jzg.jzgoto.phone.models.business.buy;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;

/**
 * Created by WY on 2016/9/28.
 * Function :
 */
public class BuyCarDetailBarginResult extends BaseResultModels{

    public void setResult(Object obj) {
        System.out.println(obj.toString());
        Gson gson = new Gson();
        BuyCarDetailBarginResult result = gson.fromJson(obj.toString(), BuyCarDetailBarginResult.class);
        setStatus(result.getStatus());
        setMsg(result.getMsg());
    }

    @Override
    public String toResultString() {
        // TODO Auto-generated method stub
        return null;
    }
}
