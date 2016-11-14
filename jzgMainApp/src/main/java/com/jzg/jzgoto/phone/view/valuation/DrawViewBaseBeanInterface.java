package com.jzg.jzgoto.phone.view.valuation;

import android.graphics.Canvas;

/**
 * Created by jzg on 2016/9/21.
 * Function :
 */
public interface DrawViewBaseBeanInterface {

    /**
     * 设置画笔色值。文字颜色，灰色条线
     * @param colors
     */
    public void setColors(int[] colors);
    /**
     * 初始化笔
     */
    public void initAllPaint();
    /**
     * 绘画底图
     * @param canvas
     */
    public void toDrawBase(Canvas canvas);
    /**
     * 绘画折线
     * @param canvas
     */
    public void toDrawLine(Canvas canvas);
    /**
     * 绘画圆柱
     * @param canvas
     */
    public void toDrawColumn(Canvas canvas);
    /**
     * 绘画饼图
     * @param canvas
     */
    public void toDrawPie(Canvas canvas);
    /**
     * 编写文字
     * @param canvas
     */
    public void toDrawText(Canvas canvas);
    /**
     * 初始化显示数据
     */
    public void initShowData();
    /**
     * 设置比例
     * @param ratio
     */
    public void setRatio(float ratio);
}
