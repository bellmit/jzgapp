package com.jzg.jzgoto.phone.models.business.buy;

import java.io.Serializable;

public class PopListDataModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sortName;
	private String Id;
	private int textColor;
	
	public PopListDataModel(String text, String textId, int textColor) {
		super();
		this.sortName = text;
		this.Id = textId;
		this.textColor = textColor;
	}
	public String getText() {
		return sortName;
	}
	public void setText(String text) {
		this.sortName = text;
	}
	public String getTextId() {
		return Id;
	}
	public void setTextId(String textId) {
		this.Id = textId;
	}
	public int getTextColor() {
		return textColor;
	}
	public void setTextColor(int textColor) {
		this.textColor = textColor;
	}
}
