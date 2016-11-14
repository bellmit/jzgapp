package com.jzg.jzgoto.phone.models.business.buy;

import java.io.Serializable;

/**
 * Created by WY on 2016/9/27.
 * Function :
 */
public class BuyCarDetailPicModel implements Serializable{
    private static final long serialVersionUID = 1L;
    private String Pic;

    public String getPic() {
        return Pic;
    }

    public void setPic(String Pic) {
        this.Pic = Pic;
    }

    @Override
    public String toString() {
        return "BuyCarDetailPicModel{" +
                "Pic='" + Pic + '\'' +
                '}';
    }
}
