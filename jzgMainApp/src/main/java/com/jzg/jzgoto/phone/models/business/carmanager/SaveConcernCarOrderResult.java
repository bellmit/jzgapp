package com.jzg.jzgoto.phone.models.business.carmanager;


import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;

/**
 * author: guochen
 * date: 2016/10/11 16:31
 * email: 
 */
public class SaveConcernCarOrderResult extends BaseResultModels {

    @Override
    public void setResult(Object obj) {
        Gson gson = new Gson();
        SaveConcernCarOrderResult models = gson.fromJson((String)obj, SaveConcernCarOrderResult.class);
        setMsg(models.getMsg());
        setStatus(models.getStatus());
    }

    @Override
    public String toResultString() {
        return null;
    }
}
