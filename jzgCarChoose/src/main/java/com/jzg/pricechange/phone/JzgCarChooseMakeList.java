package com.jzg.pricechange.phone;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * ClassName:MakeList <br/>
 * Function: 品牌实体列表对象. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-6-12 下午12:20:59 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class JzgCarChooseMakeList extends JzgCarChooseBaseObject implements Serializable
{
	private ArrayList<JzgCarChooseMake> makes;
	private boolean success;
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public ArrayList<JzgCarChooseMake> getMakes()
	{
		return makes;
	}

	public void setMakes(ArrayList<JzgCarChooseMake> makes)
	{
		this.makes = makes;
	}
	
}
