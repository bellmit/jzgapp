package com.jzg.jzgoto.phone.models.business.sell;

import java.io.Serializable;
import java.util.List;

import android.util.Log;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;
/**
 * @Description: 置换申请结果--从新车列表过来
 * @Package com.jzg.jzgoto.phone.models.business.sell NewCarReplaceResultModels.java
 * @author gf
 * @date 2015-12-24 下午4:26:46
 */
public class NewCarReplaceResultModels extends BaseResultModels {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	
	private String OldImageUrl;
	private String OldFullName;
	private String OldNowMsrp;
	private String OldReg;
	private String RegistDate;
	
	private String OldMileage;
	private String OldCityName;
	private String C2BA;
	private String C2BB;
	private String C2BC;
	
	private List<NewCarBean> NewSytleList;
	
	@Override
	public void setResult(Object obj) {
		Log.i("gf", "新车置换结果:" + (String)obj);
		Gson gson = new Gson();
		NewCarReplaceResultModels models = gson.fromJson((String)obj, NewCarReplaceResultModels.class);
		setMsg(models.getMsg());
		setStatus(models.getStatus());
		
		setOldImageUrl(models.getOldImageUrl());
		setOldFullName(models.getOldFullName());
		setOldNowMsrp(models.getOldNowMsrp());
		setOldReg(models.getOldReg());
		
		setRegistDate(models.getRegistDate());
		
		setOldMileage(models.getOldMileage());
		setOldCityName(models.getOldCityName());
		setC2BA(models.getC2BA());
		setC2BB(models.getC2BB());
		setC2BC(models.getC2BC());
		
		setNewSytleList(models.getNewSytleList());
	}
	@Override
	public String toResultString() {
		return null;
	}
	
	public class NewCarBean implements Serializable{
		/**
		 * UID
		 */
		private static final long serialVersionUID = 1L;
		private String NewImageUrl;
		private String NewFullName;
		private String NewNowMsrp;
		private String NewBuChaPice;
		private String NewStyleId;
		
		public String getNewStyleId() {
			return NewStyleId;
		}
		public void setNewStyleId(String newStyleId) {
			NewStyleId = newStyleId;
		}
		public String getNewImageUrl() {
			return NewImageUrl;
		}
		public void setNewImageUrl(String newImageUrl) {
			NewImageUrl = newImageUrl;
		}
		public String getNewFullName() {
			return NewFullName;
		}
		public void setNewFullName(String newFullName) {
			NewFullName = newFullName;
		}
		public String getNewNowMsrp() {
			return NewNowMsrp;
		}
		public void setNewNowMsrp(String newNowMsrp) {
			NewNowMsrp = newNowMsrp;
		}
		public String getNewBuChaPice() {
			return NewBuChaPice;
		}
		public void setNewBuChaPice(String newBuChaPice) {
			NewBuChaPice = newBuChaPice;
		}
	}
	public String getRegistDate() {
		return RegistDate;
	}
	public void setRegistDate(String registDate) {
		RegistDate = registDate;
	}
	public String getOldImageUrl() {
		return OldImageUrl;
	}
	public void setOldImageUrl(String oldImageUrl) {
		OldImageUrl = oldImageUrl;
	}
	public String getOldFullName() {
		return OldFullName;
	}
	public void setOldFullName(String oldFullName) {
		OldFullName = oldFullName;
	}
	public String getOldNowMsrp() {
		return OldNowMsrp;
	}
	public void setOldNowMsrp(String oldNowMsrp) {
		OldNowMsrp = oldNowMsrp;
	}
	public String getOldReg() {
		return OldReg;
	}
	public void setOldReg(String oldReg) {
		OldReg = oldReg;
	}
	public String getOldMileage() {
		return OldMileage;
	}
	public void setOldMileage(String oldMileage) {
		OldMileage = oldMileage;
	}
	public String getOldCityName() {
		return OldCityName;
	}
	public void setOldCityName(String oldCityName) {
		OldCityName = oldCityName;
	}
	public String getC2BA() {
		return C2BA;
	}
	public void setC2BA(String c2ba) {
		C2BA = c2ba;
	}
	public String getC2BB() {
		return C2BB;
	}
	public void setC2BB(String c2bb) {
		C2BB = c2bb;
	}
	public String getC2BC() {
		return C2BC;
	}
	public void setC2BC(String c2bc) {
		C2BC = c2bc;
	}
	public List<NewCarBean> getNewSytleList() {
		return NewSytleList;
	}
	public void setNewSytleList(List<NewCarBean> newSytleList) {
		NewSytleList = newSytleList;
	}
}
