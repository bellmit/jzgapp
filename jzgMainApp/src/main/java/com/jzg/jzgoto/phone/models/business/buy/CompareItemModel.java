package com.jzg.jzgoto.phone.models.business.buy;

import java.io.Serializable;
import java.util.Map;

import com.jzg.jzgoto.phone.R;

public class CompareItemModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int textColor = R.color.text_item_lightgrey;
	private Map<String,String> mDataMap;
	
	
	public CompareItemModel(int textColor, Map<String, String> mDataMap) {
		super();
		this.textColor = textColor;
		this.mDataMap = mDataMap;
	}
	public int getTextColor() {
		return textColor;
	}
	public void setTextColor(int textColor) {
		this.textColor = textColor;
	}
	public Map<String, String> getmDataMap() {
		return mDataMap;
	}
	public void setmDataMap(Map<String, String> mDataMap) {
		this.mDataMap = mDataMap;
	}
	@Override
	public String toString() {
		return "CompareItemModel [textColor=" + textColor + ", mDataMap="
				+ mDataMap + "]";
	}
}
