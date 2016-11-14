/**
 * Project Name:JingZhenGu
 * File Name:Province.java
 * Package Name:com.gc.jingzhengu.vo
 * Date:2014-6-16下午5:58:47
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.jzg.jzgoto.phone.models;

import java.io.Serializable;

/**
 * ClassName:Province <br/>
 * Function: 省实体. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-6-16 下午5:58:47 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class Province implements Serializable
{
	/**
	 * 省id
	 */
	private int id;

	/**
	 * 名称
	 */
	private String name;

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
		return "Province [id=" + id + ", name=" + name + "]";
	}
	
	
	
}
