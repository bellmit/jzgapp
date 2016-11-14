package com.jzg.jzgoto.phone.models.business.buy;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;

/**
 * Created by WY on 2016/9/27.
 * Function :
 */
public class BuyCarSearchSaveKeyWordsResult extends BaseResultModels{
    private static final long serialVersionUID = 1L;

    @Override
    public void setResult(Object obj) {
        System.out.println(obj.toString());
        Gson gson = new Gson();
        BuyCarSearchSaveKeyWordsResult result = gson.fromJson(obj.toString(), BuyCarSearchSaveKeyWordsResult.class);
        setStatus(result.getStatus());
        setMsg(result.getMsg());
    }

    @Override
    public String toResultString() {
        // TODO Auto-generated method stub
        return null;
    }
}
