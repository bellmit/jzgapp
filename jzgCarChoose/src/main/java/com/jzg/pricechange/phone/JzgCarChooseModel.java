package com.jzg.pricechange.phone;

import java.io.Serializable;

/**
 * ClassName:Model <br/>
 * Function: 车系实体对象. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-6-13 上午11:34:30 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class JzgCarChooseModel implements Serializable
{
	/**
	 * 车系id
	 */
	private int id;

	/**
	 * 车系名称
	 */
	private String name;

	/**
	 * 厂商名称
	 */
	private String manufacturerName;

	/**
	 * 字体颜色
	 */
	private int fontColor;

	/**
	 * item点击颜色
	 */
	private int itemColor;
	
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

	public String getManufacturerName()
	{
		return manufacturerName;
	}

	public void setManufacturerName(String manufacturerName)
	{
		this.manufacturerName = manufacturerName;
	}

	public int getFontColor()
	{
		return fontColor;
	}

	public void setFontColor(int fontColor)
	{
		this.fontColor = fontColor;
	}

	@Override
	public String toString()
	{
		return "Model [id=" + id + ", name=" + name + ", manufacturerName="
				+ manufacturerName + ", fontColor=" + fontColor + "]";
	}

	public int getItemColor()
	{
		return itemColor;
	}

	public void setItemColor(int itemColor)
	{
		this.itemColor = itemColor;
	}

	public String getModelimgpath() {
		return modelimgpath;
	}

	public void setModelimgpath(String modelimgpath) {
		this.modelimgpath = modelimgpath;
	}

}
