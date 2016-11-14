package com.jzg.jzgoto.phone.models.business.sell;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;

/**
 * author: guochen
 * date: 2016/9/29 17:19
 * email: 
 */
public class ReleaseSellTruckMessageResult extends BaseResultModels {


    /**
     * CarButlerId : 9
     */

    private int CarButlerId;

    @Override
    public void setResult(Object obj) {
        Gson gson = new Gson();
        ReleaseSellTruckMessageResult models = gson.fromJson((String)obj, ReleaseSellTruckMessageResult.class);
        setMsg(models.getMsg());
        setStatus(models.getStatus());
        setCarButlerId(models.getCarButlerId());
    }

    @Override
    public String toResultString() {
        return null;
    }

    public int getCarButlerId() {
        return CarButlerId;
    }

    public void setCarButlerId(int CarButlerId) {
        this.CarButlerId = CarButlerId;
    }
}
