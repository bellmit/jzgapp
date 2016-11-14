package com.jzg.jzgoto.phone.view.valuation;

import android.graphics.Canvas;

/**
 * Created by jzg on 2016/9/21.
 * Function :
 */
public interface DrawViewMethodInterface {

    /**
     * 绘画
     * @param canvas
     */
    public void toDrawContent(Canvas canvas);

    /**
     * 设置要显示的数据
     * @param bean
     */
    public void setDrawBean(DrawViewBaseBean bean);

    /**
     * 获取显示的数据
     * @return
     */
    public DrawViewBaseBean getDrawBean();
    /**
     * 设置高宽
     * @param height
     * @param width
     */
    public void setBeanHeightAndWidth(int height,int width);
    /**
     * 刷新界面显示
     */
    public void toRefreshView();
    /**
     * 设置显示信息
     * @param obj
     * @param ratio
     */
    public void toShowData(Object obj,float ratio);
}
