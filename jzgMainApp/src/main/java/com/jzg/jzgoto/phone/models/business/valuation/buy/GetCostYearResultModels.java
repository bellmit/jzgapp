package com.jzg.jzgoto.phone.models.business.valuation.buy;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;
/**
 * @Description: 买车估值计算使用成本
 * @Package com.jzg.jzgoto.phone.models.business.valuation.buy GetCostYearResultModels.java
 * @author gf
 * @date 2016-6-15 下午3:02:26
 */
public class GetCostYearResultModels extends BaseResultModels {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 年总成本
	 */
	private String totalPriceZYear;
	/**
	 * 月使用成本
	 */
	private String totalPriceZMonth;
	/** 年折旧 **/
	private String DepreciationYear;
	/** 年税费 **/
	private String TaxYear;
	/** 年保险 **/
	private String InsuranceYear;
	/** 年保养 **/
	private String MaintainYear;
	/** 年燃油 **/
	private String OilYear;
	/** 折旧百分比 **/
	private String DepreciationPer;
	/** 保险百分比 **/
	private String InsurancePer;
	/** 保养百分比 **/
	private String MaintainPer;
	/** 税费百分比 **/
	private String TaxPer;
	/** 燃油百分比 **/
	private String OilPer;
	@Override
	public void setResult(Object obj) {
		
		Gson gson = new Gson();
		GetCostYearResultModels models = gson.fromJson((String)obj, GetCostYearResultModels.class);
		setMsg(models.getMsg());
		setStatus(models.getStatus());
		
		setTotalPriceZMonth(models.getTotalPriceZMonth());
		setTotalPriceZYear(models.getTotalPriceZYear());
		setDepreciationYear(models.getDepreciationYear());
		setTaxYear(models.getTaxYear());
		setInsuranceYear(models.getInsuranceYear());
		setMaintainYear(models.getMaintainYear());
		setOilYear(models.getOilYear());
		setDepreciationPer(models.getDepreciationPer());
		setInsurancePer(models.getInsurancePer());
		setMaintainPer(models.getMaintainPer());
		setTaxPer(models.getTaxPer());
		setOilPer(models.getOilPer());
	}

	@Override
	public String toResultString() {
		return null;
	}

	public String getTotalPriceZYear() {
		return totalPriceZYear;
	}

	public void setTotalPriceZYear(String totalPriceZYear) {
		this.totalPriceZYear = totalPriceZYear;
	}

	public String getTotalPriceZMonth() {
		return totalPriceZMonth;
	}

	public void setTotalPriceZMonth(String totalPriceZMonth) {
		this.totalPriceZMonth = totalPriceZMonth;
	}

	public String getDepreciationYear() {
		return DepreciationYear;
	}

	public void setDepreciationYear(String depreciationYear) {
		DepreciationYear = depreciationYear;
	}

	public String getTaxYear() {
		return TaxYear;
	}

	public void setTaxYear(String taxYear) {
		TaxYear = taxYear;
	}

	public String getInsuranceYear() {
		return InsuranceYear;
	}

	public void setInsuranceYear(String insuranceYear) {
		InsuranceYear = insuranceYear;
	}

	public String getMaintainYear() {
		return MaintainYear;
	}

	public void setMaintainYear(String maintainYear) {
		MaintainYear = maintainYear;
	}

	public String getOilYear() {
		return OilYear;
	}

	public void setOilYear(String oilYear) {
		OilYear = oilYear;
	}

	public String getDepreciationPer() {
		return DepreciationPer;
	}

	public void setDepreciationPer(String depreciationPer) {
		DepreciationPer = depreciationPer;
	}

	public String getInsurancePer() {
		return InsurancePer;
	}

	public void setInsurancePer(String insurancePer) {
		InsurancePer = insurancePer;
	}

	public String getMaintainPer() {
		return MaintainPer;
	}

	public void setMaintainPer(String maintainPer) {
		MaintainPer = maintainPer;
	}

	public String getTaxPer() {
		return TaxPer;
	}

	public void setTaxPer(String taxPer) {
		TaxPer = taxPer;
	}

	public String getOilPer() {
		return OilPer;
	}

	public void setOilPer(String oilPer) {
		OilPer = oilPer;
	}
}
