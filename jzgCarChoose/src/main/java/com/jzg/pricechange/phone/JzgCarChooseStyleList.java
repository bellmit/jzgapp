package com.jzg.pricechange.phone;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * ClassName:StyleList <br/>
 * Function: 车型列表实体对象. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-6-16 上午10:19:54 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class JzgCarChooseStyleList extends JzgCarChooseBaseObject implements Serializable
{
	private ArrayList<JzgCarChooseStyleCategory> carStyles;
	private boolean success;
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public ArrayList<JzgCarChooseStyleCategory> getCarStyles()
	{
		return carStyles;
	}

	public void setCarStyles(ArrayList<JzgCarChooseStyleCategory> carStyles)
	{
		this.carStyles = carStyles;
	}
}
