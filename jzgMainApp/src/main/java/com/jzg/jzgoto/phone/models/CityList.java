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
import java.util.ArrayList;

/**
 * ClassName:CityList <br/>
 * Function: 市列表. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-6-17 上午11:08:49 <br/>
 * @author   汪渝栋
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class CityList extends BaseObject implements Serializable
{
	private ArrayList<City> citys;

	public ArrayList<City> getCitys()
	{
		return citys;
	}

	public void setCitys(ArrayList<City> citys)
	{
		this.citys = citys;
	}

	@Override
	public String toString()
	{
		return "CityList [citys=" + citys + "]";
	}
	
}

