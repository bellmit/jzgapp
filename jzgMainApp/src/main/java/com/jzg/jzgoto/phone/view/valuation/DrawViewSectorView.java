package com.jzg.jzgoto.phone.view.valuation;

import android.content.Context;
import android.util.AttributeSet;

import com.jzg.jzgoto.phone.models.business.valuation.DrawViewSectorViewTypeModel;

/**
 * Description 扇形图
 * Package com.jzg.jzgoto.phone.view.sector
 * Author gf
 * DATE 2016/9/21
 */

public class DrawViewSectorView extends DrawViewChartBaseView{
    private Context context;
    public DrawViewSectorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    public DrawViewSectorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public DrawViewSectorView(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public void toShowData(Object obj, float ratio) {
        //showType 1字少的，2
        DrawViewSectorViewTypeModel typeModel = (DrawViewSectorViewTypeModel)obj;
        DrawViewSectorBean sectorBean = new DrawViewSectorBean(context,this);
        if(typeModel.getShowText()!=null)
            sectorBean.setPricesShow(typeModel.getShowText());
        if(typeModel.getType()==2){
            sectorBean.setDrawSmallCir(true);
        }
        sectorBean.setPriceTextHide(typeModel.isShowPriceText());
        sectorBean.setShowType(typeModel.getType());
        sectorBean.setRatio(ratio);
        setDrawBean(sectorBean);
        setOnTouchListener(sectorBean.getOntouchListener());
    }
    public void toShowData(Object obj, float ratio,DrawViewSectorBean.OnSelfClickListener listener){
        DrawViewSectorBean sectorBean = new DrawViewSectorBean(context,this);
        DrawViewSectorViewTypeModel typeModel = (DrawViewSectorViewTypeModel)obj;
        if(typeModel.getShowText()!=null) {
            sectorBean.setPricesShow(typeModel.getShowText());
        }
        if(typeModel.getType()==2){
            sectorBean.setDrawSmallCir(true);
        }
        sectorBean.toRefreshClick(typeModel.getClickIndex());
        sectorBean.setShowType(typeModel.getType());
        sectorBean.setRatio(ratio);
        sectorBean.setOnSelfClickListener(listener);
        setDrawBean(sectorBean);
        setOnTouchListener(sectorBean.getOntouchListener());
    }

}
