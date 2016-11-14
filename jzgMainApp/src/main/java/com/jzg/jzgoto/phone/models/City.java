/**
 * Project Name:JingZhenGu
 * File Name:City.java
 * Package Name:com.gc.jingzhengu.vo
 * Date:2014-6-17上午11:06:18
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.jzg.jzgoto.phone.models;

import java.io.Serializable;

/**
 * ClassName:City <br/>
 * Function: 城市实体. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-6-17 上午11:06:18 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class City implements Serializable
{
	private int id;

	private String name;

	private String provinceId;

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

	@Override
	public String toString()
	{
		return "City [id=" + id + ", name=" + name + "]";
	}

	public String getProvinceId()
	{
		return provinceId;
	}

	public void setProvinceId(String provinceId)
	{
		this.provinceId = provinceId;
	}

}
