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


/**
 * 保值率详情
  * @ClassName: CarLifeNewsList
  * @Description: TODO
  * @author Comsys-jzg
  * @date 2015-12-23 下午5:39:41
  *
 */

public class BzlDetail implements Serializable
{

	/**
	  * @Fields serialVersionUID : TODO
	  */
	
	private static final long serialVersionUID = 1L;
	
	/**
     * B2CLowPrice : 16.21
     * B2CUpPrice : 18.28
     * TrimId : 113850
     * C2BMidPrice : 15.18
     * B2CMidPrice : 17.25
     * status : 100
     * CityName : 酒泉
     * img : http://img1.bitautoimg.com/autoalbum/files/20150427/716/14170671611294_4014532_3.jpg
     * C2BLowPrice : 14.27
     * msg : 成功
     * C2BUpPrice : 16.10
     * milage : 1.5
     * YearPrice : {"Year3":"11.48","Year4":"10.27","Year1":"13.92","Year2":"12.69","Year5":"9.07"}
     * FullName : 奥迪A3 2015款 1.4T 手动 Limousine 35TFSI 进取型
     * RegistDate : 2014-12-01
     * Msrp : 19.09
     * BzlValue : {"Value5":"47.51","Value3":"60.14","Value4":"53.8","Value1":"72.92","Value2":"66.47"}
     */
    private String B2CLowPrice;
    private String B2CUpPrice;
    private String TrimId;
    private String C2BMidPrice;
    private String B2CMidPrice;
    private String status;
    private String CityName;
    private String img;
    private String C2BLowPrice;
    private String msg;
    private String C2BUpPrice;
    private String milage;
    private YearPriceEntity YearPrice;
    private String FullName;
    private String RegistDate;
    private String Msrp;
    private BzlValueEntity BzlValue;

    public void setB2CLowPrice(String B2CLowPrice) {
        this.B2CLowPrice = B2CLowPrice;
    }

    public void setB2CUpPrice(String B2CUpPrice) {
        this.B2CUpPrice = B2CUpPrice;
    }

    public void setTrimId(String TrimId) {
        this.TrimId = TrimId;
    }

    public void setC2BMidPrice(String C2BMidPrice) {
        this.C2BMidPrice = C2BMidPrice;
    }

    public void setB2CMidPrice(String B2CMidPrice) {
        this.B2CMidPrice = B2CMidPrice;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCityName(String CityName) {
        this.CityName = CityName;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setC2BLowPrice(String C2BLowPrice) {
        this.C2BLowPrice = C2BLowPrice;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setC2BUpPrice(String C2BUpPrice) {
        this.C2BUpPrice = C2BUpPrice;
    }

    public void setMilage(String milage) {
        this.milage = milage;
    }

    public void setYearPrice(YearPriceEntity YearPrice) {
        this.YearPrice = YearPrice;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public void setRegistDate(String RegistDate) {
        this.RegistDate = RegistDate;
    }

    public void setMsrp(String Msrp) {
        this.Msrp = Msrp;
    }

    public void setBzlValue(BzlValueEntity BzlValue) {
        this.BzlValue = BzlValue;
    }

    public String getB2CLowPrice() {
        return B2CLowPrice;
    }

    public String getB2CUpPrice() {
        return B2CUpPrice;
    }

    public String getTrimId() {
        return TrimId;
    }

    public String getC2BMidPrice() {
        return C2BMidPrice;
    }

    public String getB2CMidPrice() {
        return B2CMidPrice;
    }

    public String getStatus() {
        return status;
    }

    public String getCityName() {
        return CityName;
    }

    public String getImg() {
        return img;
    }

    public String getC2BLowPrice() {
        return C2BLowPrice;
    }

    public String getMsg() {
        return msg;
    }

    public String getC2BUpPrice() {
        return C2BUpPrice;
    }

    public String getMilage() {
        return milage;
    }

    public YearPriceEntity getYearPrice() {
        return YearPrice;
    }

    public String getFullName() {
        return FullName;
    }

    public String getRegistDate() {
        return RegistDate;
    }

    public String getMsrp() {
        return Msrp;
    }

    public BzlValueEntity getBzlValue() {
        return BzlValue;
    }

    public class YearPriceEntity implements Serializable{
        /**
         * Year3 : 11.48
         * Year4 : 10.27
         * Year1 : 13.92
         * Year2 : 12.69
         * Year5 : 9.07
         */
        private String Year3;
        private String Year4;
        private String Year1;
        private String Year2;
        private String Year5;

        public void setYear3(String Year3) {
            this.Year3 = Year3;
        }

        public void setYear4(String Year4) {
            this.Year4 = Year4;
        }

        public void setYear1(String Year1) {
            this.Year1 = Year1;
        }

        public void setYear2(String Year2) {
            this.Year2 = Year2;
        }

        public void setYear5(String Year5) {
            this.Year5 = Year5;
        }

        public String getYear3() {
            return Year3;
        }

        public String getYear4() {
            return Year4;
        }

        public String getYear1() {
            return Year1;
        }

        public String getYear2() {
            return Year2;
        }

        public String getYear5() {
            return Year5;
        }
    }

    public class BzlValueEntity implements Serializable{
        /**
         * Value5 : 47.51
         * Value3 : 60.14
         * Value4 : 53.8
         * Value1 : 72.92
         * Value2 : 66.47
         */
        private String Value5;
        private String Value3;
        private String Value4;
        private String Value1;
        private String Value2;

        public void setValue5(String Value5) {
            this.Value5 = Value5;
        }

        public void setValue3(String Value3) {
            this.Value3 = Value3;
        }

        public void setValue4(String Value4) {
            this.Value4 = Value4;
        }

        public void setValue1(String Value1) {
            this.Value1 = Value1;
        }

        public void setValue2(String Value2) {
            this.Value2 = Value2;
        }

        public String getValue5() {
            return Value5;
        }

        public String getValue3() {
            return Value3;
        }

        public String getValue4() {
            return Value4;
        }

        public String getValue1() {
            return Value1;
        }

        public String getValue2() {
            return Value2;
        }
    }


   
	
}

