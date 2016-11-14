package com.jzg.jzgoto.phone.models.business.valuation;

import java.util.Arrays;

/**
 * Created by jzg on 2016/10/8.
 * Function :
 */
public class DrawViewSectorViewTypeModel {

    private int type;
    private String[] showText;
    private int clickIndex;
    private boolean isShowPriceText = true;
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String[] getShowText() {
        return showText;
    }

    public void setShowText(String[] showText) {
        this.showText = showText;
    }

    public int getClickIndex() {
        return clickIndex;
    }

    public void setClickIndex(int clickIndex) {
        this.clickIndex = clickIndex;
    }

    public boolean isShowPriceText() {
        return isShowPriceText;
    }

    public void setShowPriceText(boolean showPriceText) {
        isShowPriceText = showPriceText;
    }

    @Override
    public String toString() {
        return "DrawViewSectorViewTypeModel{" +
                "type=" + type +
                ", showText=" + Arrays.toString(showText) +
                ", clickIndex=" + clickIndex +
                ", isShowPriceText=" + isShowPriceText +
                '}';
    }
}
