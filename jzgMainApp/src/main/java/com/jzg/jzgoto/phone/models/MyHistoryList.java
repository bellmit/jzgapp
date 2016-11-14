package com.jzg.jzgoto.phone.models;

import java.io.Serializable;
import java.util.List;

import com.jzg.jzgoto.phone.models.business.valuation.sell.RequestValFragmentHistory;

public class MyHistoryList implements Serializable{

	/**
	  * @Fields serialVersionUID : TODO
	  */
	
	private static final long serialVersionUID = 1L;
	/**
     * status : 100
     * msg : 成功
     * totalPages : 8
     * totalRecords : 72
     * curPage : 1
     * HistoryList : [{"StyleID":"118314","ImageUrl":"http://img1.bitautoimg.com/autoalbum/files/20150504/000/18263400018882_4020208_4.jpg","ProvID":"17","CityID":"1701","CityName":"沈阳","Mils":"1","regdate":"2015-05-01","StyleName":"标致2008 2015款 1.6L 自动 翡翠型 玩酷版","UpdateTime":"2016.06.16","AppraiseType":"1","OperationType":"1","DamageID":"","OperationTypeName":"进行卖车估值"},{"StyleID":"117719","ImageUrl":"http://img.jingzhengu.com/Logo/Make/m_3_100.jpg","ProvID":"5","CityID":"501","CityName":"广州","Mils":"1","regdate":"2015-06-01","StyleName":"宝马1系(进口) 2016款 1.5T 自动 118i 都市设计套装","UpdateTime":"2016.06.16","AppraiseType":"1","OperationType":"1","DamageID":"","OperationTypeName":"进行卖车估值"},{"StyleID":"118602","ImageUrl":"http://img.jingzhengu.com/Logo/Make/m_26_100.jpg","ProvID":"2","CityID":"201","CityName":"北京","Mils":"2","regdate":"2015-11-01","StyleName":"本田雅阁 2016款 2.0L CVT 豪华版","UpdateTime":"2016.06.16","AppraiseType":"1","OperationType":"2","DamageID":"","OperationTypeName":"进行买车估值"},{"StyleID":"27","ImageUrl":"http://img.jingzhengu.com/Logo/Make/m_4_100.jpg","ProvID":"2","CityID":"201","CityName":"北京","Mils":"10","regdate":"2005-06-01","StyleName":"Jeep2500 2005款 2.5L 手动 两驱","UpdateTime":"2016.06.16","AppraiseType":"1","OperationType":"2","DamageID":"","OperationTypeName":"进行买车估值"},{"StyleID":"111280","ImageUrl":"http://img3.bitautoimg.com/autoalbum/files/20140626/862/15551186204972_3410970_4.JPG","ProvID":"2","CityID":"201","CityName":"北京","Mils":"5","regdate":"2013-08-01","StyleName":"本田飞度 2014款 1.5L CVT EX精英型","UpdateTime":"2016.06.16","AppraiseType":"1","OperationType":"2","DamageID":"","OperationTypeName":"进行买车估值"},{"StyleID":"117889","ImageUrl":"http://img.jingzhengu.com/Logo/Make/m_5_100.jpg","ProvID":"17","CityID":"1701","CityName":"沈阳","Mils":"1","regdate":"2015-06-01","StyleName":"标致301 2016款 1.6L 手动 豪华版","UpdateTime":"2016.06.16","AppraiseType":"1","OperationType":"1","DamageID":"","OperationTypeName":"进行卖车估值"},{"StyleID":"113112","ImageUrl":"http://img1.bitautoimg.com/autoalbum/files/20150126/177/17543417771445_3844872_4.JPG","ProvID":"17","CityID":"1701","CityName":"沈阳","Mils":"1","regdate":"2015-05-01","StyleName":"标致3008 2015款 1.6T 自动 THP至尚版","UpdateTime":"2016.06.16","AppraiseType":"1","OperationType":"1","DamageID":"","OperationTypeName":"进行卖车估值"},{"StyleID":"3633","ImageUrl":"http://img.jingzhengu.com/Logo/Make/m_92_100.jpg","ProvID":"21","CityID":"2101","CityName":"济南","Mils":"1","regdate":"2015-05-01","StyleName":"阿尔法罗密欧Gtv 2004款 3.2L 手动","UpdateTime":"2016.06.16","AppraiseType":"1","OperationType":"1","DamageID":"","OperationTypeName":"进行卖车估值"},{"StyleID":"3633","ImageUrl":"http://img.jingzhengu.com/Logo/Make/m_92_100.jpg","ProvID":"10","CityID":"1001","CityName":"郑州","Mils":"1","regdate":"2015-05-01","StyleName":"阿尔法罗密欧Gtv 2004款 3.2L 手动","UpdateTime":"2016.06.16","AppraiseType":"1","OperationType":"1","DamageID":"","OperationTypeName":"进行卖车估值"},{"StyleID":"117719","ImageUrl":"http://img.jingzhengu.com/Logo/Make/m_3_100.jpg","ProvID":"2","CityID":"201","CityName":"北京","Mils":"1","regdate":"2015-06-01","StyleName":"宝马1系(进口) 2016款 1.5T 自动 118i 都市设计套装","UpdateTime":"2016.06.16","AppraiseType":"1","OperationType":"1","DamageID":"","OperationTypeName":"进行卖车估值"}]
     */

    private String status;
    private String msg;
    private String totalPages;
    private String totalRecords;
    private String curPage;
    /**
     * StyleID : 118314
     * ImageUrl : http://img1.bitautoimg.com/autoalbum/files/20150504/000/18263400018882_4020208_4.jpg
     * ProvID : 17
     * CityID : 1701
     * CityName : 沈阳
     * Mils : 1
     * regdate : 2015-05-01
     * StyleName : 标致2008 2015款 1.6L 自动 翡翠型 玩酷版
     * UpdateTime : 2016.06.16
     * AppraiseType : 1
     * OperationType : 1
     * DamageID : 
     * OperationTypeName : 进行卖车估值
     */

    private List<RequestValFragmentHistory> HistoryList;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setTotalPages(String totalPages) {
        this.totalPages = totalPages;
    }

    public void setTotalRecords(String totalRecords) {
        this.totalRecords = totalRecords;
    }

    public void setCurPage(String curPage) {
        this.curPage = curPage;
    }

    public void setHistoryList(List<RequestValFragmentHistory> HistoryList) {
        this.HistoryList = HistoryList;
    }

    public String getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public String getTotalPages() {
        return totalPages;
    }

    public String getTotalRecords() {
        return totalRecords;
    }

    public String getCurPage() {
        return curPage;
    }

    public List<RequestValFragmentHistory> getHistoryList() {
        return HistoryList;
    }

    
}
