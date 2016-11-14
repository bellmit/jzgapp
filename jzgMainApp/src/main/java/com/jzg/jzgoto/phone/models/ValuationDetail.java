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


public class ValuationDetail implements Serializable
{

	/**
	  * @Fields serialVersionUID : TODO
	  */
	
	private static final long serialVersionUID = 1L;
	
	private int MakeId;
	private int ModelId;
	
	
	public int getMakeId() {
		return MakeId;
	}

	public void setMakeId(int makeId) {
		MakeId = makeId;
	}

	public int getModelId() {
		return ModelId;
	}

	public void setModelId(int modelId) {
		ModelId = modelId;
	}

	/**
     * MaintainValue : {"Year3":"53.33","Year4":"46.78","Year1":"68.05","Year2":"60.35","Year5":"40.65"}
     * status : 100
     * Valuation : {"InsurancePer":24,"Option":"未发生任何事故~车身漆面未涉及任何修复~无任何磨损、脏污~发动机、变速器性能良好，无任何异常及修理情况~无任何电子、电器、按键故障~全部4S店保养且按厂家要求保养","CityName":"龙岩","totalPriceThree":90009,"MaintainPer":10,"Mileage":1.5,"ProvName":"福建","CostThree":45355,"ImgUrl":"http://img1.bitautoimg.com/autoalbum/files/20150427/716/14170671611294_4014532_4.jpg","FullName":"奥迪A3 2015款 1.4T 手动 Limousine 35TFSI 进取型","LoanThree":0,"InsuranceMonth":604,"MaintainMonth":243,"DepreciationMonth":1240,"NowMsrp":19.09,"TaxPer":1,"CarAge":1,"B2CPricesC":15.64,"totalPriceZMonth":2500,"B2CPricesB":16.64,"OilMonth":384,"RegDate":"2014-12-01","B2CPricesA":17.63,"OilThree":13815,"ReportID":1181854,"Nature":"非运营","InsuranceThree":21742,"TransferNum":2,"Color":"棕色","MaintainThree":8748,"TaxMonth":29,"TaxThree":1050,"Level":1,"C2BMidCPrice":14.65,"OilPer":15,"C2BMidDPrice":13.77,"totalPriceMonth":1260,"DepreciationPer":50,"StyleId":113850,"C2BMidBPrice":15.52}
     * ScrapValue : {"Year3":"10.18","Year4":"8.93","Year1":"12.99","Year2":"11.52","Year5":"7.76"}
     * msg : 
     */
    private MaintainValueEntity MaintainValue;
    private int status;
    private ValuationEntity Valuation;
    private ScrapValueEntity ScrapValue;
    private String msg;

    public void setMaintainValue(MaintainValueEntity MaintainValue) {
        this.MaintainValue = MaintainValue;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setValuation(ValuationEntity Valuation) {
        this.Valuation = Valuation;
    }

    public void setScrapValue(ScrapValueEntity ScrapValue) {
        this.ScrapValue = ScrapValue;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public MaintainValueEntity getMaintainValue() {
        return MaintainValue;
    }

    public int getStatus() {
        return status;
    }

    public ValuationEntity getValuation() {
        return Valuation;
    }

    public ScrapValueEntity getScrapValue() {
        return ScrapValue;
    }

    public String getMsg() {
        return msg;
    }

    public class MaintainValueEntity implements Serializable{
        /**
         * Year3 : 53.33
         * Year4 : 46.78
         * Year1 : 68.05
         * Year2 : 60.35
         * Year5 : 40.65
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

    public static class ValuationEntity implements Serializable{
        /**
         * InsurancePer : 24
         * Option : 未发生任何事故~车身漆面未涉及任何修复~无任何磨损、脏污~发动机、变速器性能良好，无任何异常及修理情况~无任何电子、电器、按键故障~全部4S店保养且按厂家要求保养
         * CityName : 龙岩
         * totalPriceThree : 90009
         * MaintainPer : 10
         * Mileage : 1.5
         * ProvName : 福建
         * CostThree : 45355
         * ImgUrl : http://img1.bitautoimg.com/autoalbum/files/20150427/716/14170671611294_4014532_4.jpg
         * FullName : 奥迪A3 2015款 1.4T 手动 Limousine 35TFSI 进取型
         * LoanThree : 0
         * InsuranceMonth : 604
         * MaintainMonth : 243
         * DepreciationMonth : 1240
         * NowMsrp : 19.09
         * TaxPer : 1
         * CarAge : 1
         * B2CPricesC : 15.64
         * totalPriceZMonth : 2500
         * B2CPricesB : 16.64
         * OilMonth : 384
         * RegDate : 2014-12-01
         * B2CPricesA : 17.63
         * OilThree : 13815
         * ReportID : 1181854
         * Nature : 非运营
         * InsuranceThree : 21742
         * TransferNum : 2
         * Color : 棕色
         * MaintainThree : 8748
         * TaxMonth : 29
         * TaxThree : 1050
         * Level : 1
         * C2BMidCPrice : 14.65
         * OilPer : 15
         * C2BMidDPrice : 13.77
         * totalPriceMonth : 1260
         * DepreciationPer : 50
         * StyleId : 113850
         * C2BMidBPrice : 15.52
         */
        private int InsurancePer;
        private String Option;
        private String CityName;
        private int totalPriceThree;
        private int MaintainPer;
        private double Mileage;
        private String ProvName;
        private int CostThree;
        private String ImgUrl;
        private String FullName;
        private int LoanThree;
        private int InsuranceMonth;
        private int MaintainMonth;
        private int DepreciationMonth;
        private double NowMsrp;
        private int TaxPer;
        private int CarAge;
        private double B2CPricesC;
        private int totalPriceZMonth;
        private double B2CPricesB;
        private int OilMonth;
        private String RegDate;
        private double B2CPricesA;
        private int OilThree;
        private int ReportID;
        private String Nature;
        private int InsuranceThree;
        private int TransferNum;
        private String Color;
        private int MaintainThree;
        private int TaxMonth;
        private int TaxThree;
        private int Level;
        private double C2BMidCPrice;
        private int OilPer;
        private double C2BMidDPrice;
        private int totalPriceMonth;
        private int DepreciationPer;
        private int StyleId;
        private double C2BMidBPrice;

        
        public ValuationEntity() {
			super();
		}

		public void setInsurancePer(int InsurancePer) {
            this.InsurancePer = InsurancePer;
        }

        public void setOption(String Option) {
            this.Option = Option;
        }

        public void setCityName(String CityName) {
            this.CityName = CityName;
        }

        public void setTotalPriceThree(int totalPriceThree) {
            this.totalPriceThree = totalPriceThree;
        }

        public void setMaintainPer(int MaintainPer) {
            this.MaintainPer = MaintainPer;
        }

        public void setMileage(double Mileage) {
            this.Mileage = Mileage;
        }

        public void setProvName(String ProvName) {
            this.ProvName = ProvName;
        }

        public void setCostThree(int CostThree) {
            this.CostThree = CostThree;
        }

        public void setImgUrl(String ImgUrl) {
            this.ImgUrl = ImgUrl;
        }

        public void setFullName(String FullName) {
            this.FullName = FullName;
        }

        public void setLoanThree(int LoanThree) {
            this.LoanThree = LoanThree;
        }

        public void setInsuranceMonth(int InsuranceMonth) {
            this.InsuranceMonth = InsuranceMonth;
        }

        public void setMaintainMonth(int MaintainMonth) {
            this.MaintainMonth = MaintainMonth;
        }

        public void setDepreciationMonth(int DepreciationMonth) {
            this.DepreciationMonth = DepreciationMonth;
        }

        public void setNowMsrp(double NowMsrp) {
            this.NowMsrp = NowMsrp;
        }

        public void setTaxPer(int TaxPer) {
            this.TaxPer = TaxPer;
        }

        public void setCarAge(int CarAge) {
            this.CarAge = CarAge;
        }

        public void setB2CPricesC(double B2CPricesC) {
            this.B2CPricesC = B2CPricesC;
        }

        public void setTotalPriceZMonth(int totalPriceZMonth) {
            this.totalPriceZMonth = totalPriceZMonth;
        }

        public void setB2CPricesB(double B2CPricesB) {
            this.B2CPricesB = B2CPricesB;
        }

        public void setOilMonth(int OilMonth) {
            this.OilMonth = OilMonth;
        }

        public void setRegDate(String RegDate) {
            this.RegDate = RegDate;
        }

        public void setB2CPricesA(double B2CPricesA) {
            this.B2CPricesA = B2CPricesA;
        }

        public void setOilThree(int OilThree) {
            this.OilThree = OilThree;
        }

        public void setReportID(int ReportID) {
            this.ReportID = ReportID;
        }

        public void setNature(String Nature) {
            this.Nature = Nature;
        }

        public void setInsuranceThree(int InsuranceThree) {
            this.InsuranceThree = InsuranceThree;
        }

        public void setTransferNum(int TransferNum) {
            this.TransferNum = TransferNum;
        }

        public void setColor(String Color) {
            this.Color = Color;
        }

        public void setMaintainThree(int MaintainThree) {
            this.MaintainThree = MaintainThree;
        }

        public void setTaxMonth(int TaxMonth) {
            this.TaxMonth = TaxMonth;
        }

        public void setTaxThree(int TaxThree) {
            this.TaxThree = TaxThree;
        }

        public void setLevel(int Level) {
            this.Level = Level;
        }

        public void setC2BMidCPrice(double C2BMidCPrice) {
            this.C2BMidCPrice = C2BMidCPrice;
        }

        public void setOilPer(int OilPer) {
            this.OilPer = OilPer;
        }

        public void setC2BMidDPrice(double C2BMidDPrice) {
            this.C2BMidDPrice = C2BMidDPrice;
        }

        public void setTotalPriceMonth(int totalPriceMonth) {
            this.totalPriceMonth = totalPriceMonth;
        }

        public void setDepreciationPer(int DepreciationPer) {
            this.DepreciationPer = DepreciationPer;
        }

        public void setStyleId(int StyleId) {
            this.StyleId = StyleId;
        }

        public void setC2BMidBPrice(double C2BMidBPrice) {
            this.C2BMidBPrice = C2BMidBPrice;
        }

        public int getInsurancePer() {
            return InsurancePer;
        }

        public String getOption() {
            return Option;
        }

        public String getCityName() {
            return CityName;
        }

        public int getTotalPriceThree() {
            return totalPriceThree;
        }

        public int getMaintainPer() {
            return MaintainPer;
        }

        public double getMileage() {
            return Mileage;
        }

        public String getProvName() {
            return ProvName;
        }

        public int getCostThree() {
            return CostThree;
        }

        public String getImgUrl() {
            return ImgUrl;
        }

        public String getFullName() {
            return FullName;
        }

        public int getLoanThree() {
            return LoanThree;
        }

        public int getInsuranceMonth() {
            return InsuranceMonth;
        }

        public int getMaintainMonth() {
            return MaintainMonth;
        }

        public int getDepreciationMonth() {
            return DepreciationMonth;
        }

        public double getNowMsrp() {
            return NowMsrp;
        }

        public int getTaxPer() {
            return TaxPer;
        }

        public int getCarAge() {
            return CarAge;
        }

        public double getB2CPricesC() {
            return B2CPricesC;
        }

        public int getTotalPriceZMonth() {
            return totalPriceZMonth;
        }

        public double getB2CPricesB() {
            return B2CPricesB;
        }

        public int getOilMonth() {
            return OilMonth;
        }

        public String getRegDate() {
            return RegDate;
        }

        public double getB2CPricesA() {
            return B2CPricesA;
        }

        public int getOilThree() {
            return OilThree;
        }

        public int getReportID() {
            return ReportID;
        }

        public String getNature() {
            return Nature;
        }

        public int getInsuranceThree() {
            return InsuranceThree;
        }

        public int getTransferNum() {
            return TransferNum;
        }

        public String getColor() {
            return Color;
        }

        public int getMaintainThree() {
            return MaintainThree;
        }

        public int getTaxMonth() {
            return TaxMonth;
        }

        public int getTaxThree() {
            return TaxThree;
        }

        public int getLevel() {
            return Level;
        }

        public double getC2BMidCPrice() {
            return C2BMidCPrice;
        }

        public int getOilPer() {
            return OilPer;
        }

        public double getC2BMidDPrice() {
            return C2BMidDPrice;
        }

        public int getTotalPriceMonth() {
            return totalPriceMonth;
        }

        public int getDepreciationPer() {
            return DepreciationPer;
        }

        public int getStyleId() {
            return StyleId;
        }

        public double getC2BMidBPrice() {
            return C2BMidBPrice;
        }
    }

    public class ScrapValueEntity implements Serializable{
        /**
         * Year3 : 10.18
         * Year4 : 8.93
         * Year1 : 12.99
         * Year2 : 11.52
         * Year5 : 7.76
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
	
}

