package com.jzg.jzgoto.phone.models.business.valuation;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.constant.Constant;
import com.jzg.jzgoto.phone.models.business.buy.BuyCarItemModel;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jzg on 2016/10/8.
 * Function :
 */
public class ValuationRecommendCarResult extends BaseResultModels {

    private String PageIndex;
    private String TjModelName;
    private List<BuyCarItemModel> TjOldCarList = new ArrayList<>();
    private List<BuyCarItemModel> TjNewCarList = new ArrayList<>();
    @Override
    public void setResult(Object obj) {
        System.out.println(obj.toString());
        Gson gson = new Gson();
        ValuationRecommendCarResult result = gson.fromJson(obj.toString(), ValuationRecommendCarResult.class);
        setStatus(result.getStatus());
        setMsg(result.getMsg());
        if(result.getStatus() == Constant.SUCCESS){
            setPageIndex(result.getPageIndex());
            TjOldCarList.addAll(result.getTjOldCarList());
            TjNewCarList.addAll(result.getTjNewCarList());
        }
    }

    @Override
    public String toResultString() {
        return null;
    }

    public String getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(String pageIndex) {
        PageIndex = pageIndex;
    }

    public List<BuyCarItemModel> getTjOldCarList() {
        return TjOldCarList;
    }

    public void setTjOldCarList(List<BuyCarItemModel> tjOldCarList) {
        TjOldCarList = tjOldCarList;
    }

    public List<BuyCarItemModel> getTjNewCarList() {
        return TjNewCarList;
    }

    public void setTjNewCarList(List<BuyCarItemModel> tjNewCarList) {
        TjNewCarList = tjNewCarList;
    }

    public String getTjModelName() {
        return TjModelName;
    }

    public void setTjModelName(String tjModelName) {
        TjModelName = tjModelName;
    }

    @Override
    public String toString() {
        return "ValuationRecommendCarResult{" +
                "PageIndex='" + PageIndex + '\'' +
                ", TjModelName='" + TjModelName + '\'' +
                ", TjOldCarList=" + TjOldCarList +
                ", TjNewCarList=" + TjNewCarList +
                '}';
    }
}
