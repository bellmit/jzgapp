package com.jzg.pricechange.phone;

import java.io.Serializable;

/**
 * ClassName:Make <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: 品牌实体. <br/>
 * Date: 2014-6-12 下午4:12:57 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class JzgCarChooseMake implements Serializable
{
	private int makeId;

	private String makeName;

	private String makeLogo;

	private String groupName;

	private int fontColor;

	// ListView中item被点击的颜色
	private int itemColor;

	public int getFontColor()
	{
		return fontColor;
	}

	public void setFontColor(int fontColor)
	{
		this.fontColor = fontColor;
	}

	public int getMakeId()
	{
		return makeId;
	}

	public void setMakeId(int makeId)
	{
		this.makeId = makeId;
	}

	public String getMakeName()
	{
		return makeName;
	}

	public void setMakeName(String makeName)
	{
		this.makeName = makeName;
	}

	public String getMakeLogo()
	{
		return makeLogo;
	}

	public void setMakeLogo(String makeLogo)
	{
		this.makeLogo = makeLogo;
	}

	public String getGroupName()
	{
		return groupName;
	}

	public void setGroupName(String groupName)
	{
		this.groupName = groupName;
	}

	public int getItemColor()
	{
		return itemColor;
	}

	public void setItemColor(int itemColor)
	{
		this.itemColor = itemColor;
	}

	@Override
	public String toString() {
		return "Make [makeId=" + makeId + ", makeName=" + makeName
				+ ", makeLogo=" + makeLogo + ", groupName=" + groupName
				+ ", fontColor=" + fontColor + ", itemColor=" + itemColor + "]";
	}
	
}
