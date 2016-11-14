package com.jzg.jzgoto.phone.models.business.buy;

import java.io.Serializable;

public class CompareNameAndData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Name;
	private String Data;
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getData() {
		return Data;
	}
	public void setData(String data) {
		Data = data;
	}
	@Override
	public String toString() {
		return "CompareNameAndData [Name=" + Name + ", Data=" + Data + "]";
	}
	
}
