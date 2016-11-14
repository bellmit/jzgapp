package com.jzg.jzgoto.phone.models.business.valuation;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Created by WY on 2016/10/10.
 * Function :折线图数据Model
 */
public class DrawViewLineChartDataModel implements Serializable{

    /**
     * 点数据集合-用来算Y轴显示
     */
    private List<Float> pointDataList;
    /**
     * 用来显示的数据
     */
    private float[] pointShowData;
    /**
     * X轴显示数据
     */
    private String[] xShowValue;

    /**
     * 是否显示Y轴%符号
     */
    private boolean isShowYPercent = false;


    public List<Float> getPointDataList() {
        return pointDataList;
    }

    public void setPointDataList(List<Float> pointDataList) {
        this.pointDataList = pointDataList;
    }

    public float[] getPointShowData() {
        return pointShowData;
    }

    public void setPointShowData(float[] pointShowData) {
        this.pointShowData = pointShowData;
    }

    public String[] getxShowValue() {
        return xShowValue;
    }

    public void setxShowValue(String[] xShowValue) {
        this.xShowValue = xShowValue;
    }

    public boolean isShowYPercent() {
        return isShowYPercent;
    }

    public void setShowYPercent(boolean showYPercent) {
        isShowYPercent = showYPercent;
    }

    @Override
    public String toString() {
        return "DrawViewLineChartDataModel{" +
                "pointDataList=" + pointDataList +
                ", pointShowData=" + Arrays.toString(pointShowData) +
                ", xShowValue=" + Arrays.toString(xShowValue) +
                ", isShowYPercent=" + isShowYPercent +
                '}';
    }
}
