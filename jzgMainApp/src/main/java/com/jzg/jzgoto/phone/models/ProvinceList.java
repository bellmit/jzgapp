/**
 * Project Name:JingZhenGu
 * File Name:ProvinceList.java
 * Package Name:com.gc.jingzhengu.vo
 * Date:2014-6-16下午5:58:20
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.jzg.jzgoto.phone.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * ClassName:ProvinceList <br/>
 * Function:省实体列表. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-6-16 下午5:58:20 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class ProvinceList extends BaseObject implements Serializable
{
	private ArrayList<Province> provinces = new ArrayList<Province>();

	public ArrayList<Province> getProvinces()
	{
		return provinces;
	}

	public void setProvinces(ArrayList<Province> provinces)
	{
		this.provinces = provinces;
	}

}
