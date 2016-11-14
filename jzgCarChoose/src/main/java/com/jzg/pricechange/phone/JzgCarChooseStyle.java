package com.jzg.pricechange.phone;

import java.io.Serializable;

/**
 * ClassName:Style <br/>
 * Function: 车型实体. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-6-16 上午10:21:03 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class JzgCarChooseStyle implements Serializable
{
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private int year;
	private String nowMsrp;
	private int itemColor;
	private String FullName;
	private String MaxYEAR;
	private String MinYEAR;
	public String getMaxYEAR() {
		return MaxYEAR;
	}

	public void setMaxYEAR(String maxYEAR) {
		MaxYEAR = maxYEAR;
	}

	public String getMinYEAR() {
		return MinYEAR;
	}

	public void setMinYEAR(String minYEAR) {
		MinYEAR = minYEAR;
	}

	/**
	 * 品牌ID
	 */
	private int brandId;
	/**
	 * 品牌名称
	 */
	private String brandName;
	/**
	 * 车系ID
	 */
	private int modeId;
	/**
	 * 车系名称
	 */
	private String modeName;

	/**
	 * 年限名称
	 */
	private String manufacturerName;

	/**
	 * 字体颜色
	 */
	private int fontColor;
	private String modelimgpath;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getFontColor()
	{
		return fontColor;
	}

	public void setFontColor(int fontColor)
	{
		this.fontColor = fontColor;
	}

	public int getYear()
	{
		return year;
	}

	public void setYear(int year)
	{
		this.year = year;
	}

	public String getNowMsrp()
	{
		return nowMsrp;
	}

	public void setNowMsrp(String nowMsrp)
	{
		this.nowMsrp = nowMsrp;
	}

	public String getManufacturerName()
	{
		return manufacturerName;
	}

	public void setManufacturerName(String manufacturerName)
	{
		this.manufacturerName = manufacturerName;
	}

	public int getItemColor()
	{
		return itemColor;
	}

	public void setItemColor(int itemColor)
	{
		this.itemColor = itemColor;
	}

	public String getFullName() {
		return FullName;
	}

	public void setFullName(String fullName) {
		FullName = fullName;
	}

	@Override
	public String toString() {
		return "Style [id=" + id + ", name=" + name + ", year=" + year
				+ ", nowMsrp=" + nowMsrp + ", itemColor=" + itemColor
				+ ", FullName=" + FullName + ", manufacturerName="
				+ manufacturerName + ", fontColor=" + fontColor + "]";
	}

	public int getBrandId() {
		return brandId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public int getModeId() {
		return modeId;
	}

	public void setModeId(int modeId) {
		this.modeId = modeId;
	}

	public String getModeName() {
		return modeName;
	}

	public void setModeName(String modeName) {
		this.modeName = modeName;
	}

	public String getModelimgpath() {
		return modelimgpath;
	}

	public void setModelimgpath(String modelimgpath) {
		this.modelimgpath = modelimgpath;
	}

	
}
