/**
 * Project Name:JZGPingGuShiWangLuo
 * File Name:Gallery.java
 * Package Name:com.gc.jzgpgswl.vo
 * Date:2014-12-2下午4:18:01
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.jzg.jzgoto.phone.models.business.buy;

import java.io.Serializable;
import java.util.List;

/**
 * ClassName:Gallery <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-12-2 下午4:18:01 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class CarSourceGallery implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int position;

	private List<String> pics;

	public int getPosition()
	{
		return position;
	}

	public void setPosition(int position)
	{
		this.position = position;
	}

	public List<String> getPics()
	{
		return pics;
	}

	public void setPics(List<String> pics)
	{
		this.pics = pics;
	}

}
