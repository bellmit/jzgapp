package com.jzg.jzgoto.phone.models.business.sell;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;

/**
 * author: guochen
 * date: 2016/9/28 20:11
 * email: 
 */
public class OneKeySellCarResult extends BaseResultModels {


    /**
     * BeginC2BPrice : 4.47
     * EndC2BPrice : 5.41
     */

    private String BeginC2BPrice;
    private String EndC2BPrice;

    @Override
    public void setResult(Object obj) {
        Gson gson = new Gson();
        OneKeySellCarResult models = gson.fromJson((String)obj, OneKeySellCarResult.class);
        setMsg(models.getMsg());
        setStatus(models.getStatus());

        setBeginC2BPrice(models.getBeginC2BPrice());
        setEndC2BPrice(models.getEndC2BPrice());
    }

    @Override
    public String toResultString() {
        return null;
    }

    public String getBeginC2BPrice() {
        return BeginC2BPrice;
    }

    public void setBeginC2BPrice(String BeginC2BPrice) {
        this.BeginC2BPrice = BeginC2BPrice;
    }

    public String getEndC2BPrice() {
        return EndC2BPrice;
    }

    public void setEndC2BPrice(String EndC2BPrice) {
        this.EndC2BPrice = EndC2BPrice;
    }
}
