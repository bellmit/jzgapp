/**
 * Project Name:JingZhenGu
 * File Name:CityList.java
 * Package Name:com.gc.jingzhengu.vo
 * Date:2014-6-17上午11:08:49
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
*/

package com.jzg.jzgoto.phone.models;

import java.io.Serializable;
import java.util.List;


public class ValuationList implements Serializable
{

	/**
	  * @Fields serialVersionUID : TODO
	  */
	
	private static final long serialVersionUID = 1L;
	
	
	/**
     * status : 100
     * ValutionList : [{"CarAge":1,"ValutionFlag":1,"FullName":"奥迪A3 2015款 1.4T 手动 Limousine 35TFSI 进取型","CityName":"甘肃 酒泉","RegDate":"2014-12-01","Price":14.81,"CreateTime":"2016-1-12","Styleid":113850,"Id":1181811,"Mileage":1.5},{"CarAge":1,"ValutionFlag":1,"FullName":"奥迪A3 2015款 1.4T 手动 Limousine 35TFSI 进取型","CityName":"甘肃 酒泉","RegDate":"2014-12-01","Price":14.81,"CreateTime":"2016-1-12","Styleid":113850,"Id":1181809,"Mileage":1},{"CarAge":1,"ValutionFlag":0,"FullName":"奥迪A3 2015款 1.4T 手动 Limousine 35TFSI 进取型","CityName":"甘肃 酒泉","RegDate":"2014-12-01","Price":15.7,"CreateTime":"2016-1-12","Styleid":113850,"Id":1181808,"Mileage":1},{"CarAge":1,"ValutionFlag":1,"FullName":"奥迪A3 2015款 1.4T 手动 Limousine 35TFSI 进取型","CityName":"北京","RegDate":"2014-12-01","Price":14.65,"CreateTime":"2016-1-12","Styleid":113850,"Id":1181796,"Mileage":1},{"CarAge":1,"ValutionFlag":1,"FullName":"奥迪A3 2015款 1.4T 手动 Limousine 35TFSI 进取型","CityName":"甘肃 酒泉","RegDate":"2014-12-01","Price":14.65,"CreateTime":"2016-1-12","Styleid":113850,"Id":1181795,"Mileage":1},{"CarAge":1,"ValutionFlag":0,"FullName":"奥迪A4L 2016款 1.8T CVT 30TFSI舒适型","CityName":"甘肃 酒泉","RegDate":"2015-09-01","Price":22.84,"CreateTime":"2016-1-12","Styleid":116517,"Id":1181793,"Mileage":1},{"CarAge":1,"ValutionFlag":0,"FullName":"奥迪A3 2015款 1.4T 手动 Limousine 35TFSI 进取型","CityName":"甘肃 酒泉","RegDate":"2014-12-01","Price":15.46,"CreateTime":"2016-1-12","Styleid":113850,"Id":1181792,"Mileage":1},{"CarAge":2,"ValutionFlag":0,"FullName":"比亚迪E6 2014款 电动 豪华型(北京版)","CityName":"甘肃 酒泉","RegDate":"2013-12-01","Price":14.84,"CreateTime":"2016-1-12","Styleid":106595,"Id":1181791,"Mileage":1},{"CarAge":1,"ValutionFlag":0,"FullName":"比亚迪E6 2014款 电动 豪华型(北京版)","CityName":"甘肃 酒泉","RegDate":"2014-12-01","Price":14.84,"CreateTime":"2016-1-12","Styleid":106595,"Id":1181789,"Mileage":1},{"CarAge":1,"ValutionFlag":0,"FullName":"比亚迪E6 2014款 电动 豪华型(北京版)","CityName":"甘肃 酒泉","RegDate":"2014-12-01","Price":14.84,"CreateTime":"2016-1-12","Styleid":106595,"Id":1181787,"Mileage":1}]
     * msg : 
     */
    private int status;
    private List<ValutionListEntity> ValutionList;
    private String msg;

    public void setStatus(int status) {
        this.status = status;
    }

    public void setValutionList(List<ValutionListEntity> ValutionList) {
        this.ValutionList = ValutionList;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public List<ValutionListEntity> getValutionList() {
        return ValutionList;
    }

    public String getMsg() {
        return msg;
    }

    public class ValutionListEntity implements Serializable{
        /**
         * CarAge : 1
         * ValutionFlag : 1
         * FullName : 奥迪A3 2015款 1.4T 手动 Limousine 35TFSI 进取型
         * CityName : 甘肃 酒泉
         * RegDate : 2014-12-01
         * Price : 14.81
         * CreateTime : 2016-1-12
         * Styleid : 113850
         * Id : 1181811
         * Mileage : 1.5
         */
        private int CarAge;
        private int ValutionFlag;
        private String FullName;
        private String CityName;
        private String RegDate;
        private double Price;
        private String CreateTime;
        private int Styleid;
        private int Id;
        private double Mileage;

        public void setCarAge(int CarAge) {
            this.CarAge = CarAge;
        }

        public void setValutionFlag(int ValutionFlag) {
            this.ValutionFlag = ValutionFlag;
        }

        public void setFullName(String FullName) {
            this.FullName = FullName;
        }

        public void setCityName(String CityName) {
            this.CityName = CityName;
        }

        public void setRegDate(String RegDate) {
            this.RegDate = RegDate;
        }

        public void setPrice(double Price) {
            this.Price = Price;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public void setStyleid(int Styleid) {
            this.Styleid = Styleid;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public void setMileage(double Mileage) {
            this.Mileage = Mileage;
        }

        public int getCarAge() {
            return CarAge;
        }

        public int getValutionFlag() {
            return ValutionFlag;
        }

        public String getFullName() {
            return FullName;
        }

        public String getCityName() {
            return CityName;
        }

        public String getRegDate() {
            return RegDate;
        }

        public double getPrice() {
            return Price;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public int getStyleid() {
            return Styleid;
        }

        public int getId() {
            return Id;
        }

        public double getMileage() {
            return Mileage;
        }
    }
    
	
}

