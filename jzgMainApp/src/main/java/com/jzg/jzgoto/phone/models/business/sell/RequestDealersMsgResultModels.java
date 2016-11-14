package com.jzg.jzgoto.phone.models.business.sell;

import java.util.List;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;
/**
 * @Description: 获取经销商列表
 * @Package com.jzg.jzgoto.phone.models.business.sell RequestDealersMsgResultModels.java
 * @author gf
 * @date 2015-12-25 下午2:10:08
 */
public class RequestDealersMsgResultModels extends BaseResultModels {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;

	private List<CarStyleList> NewStyleList;
	
	@Override
	public void setResult(Object obj) {
		
		Gson gson = new Gson();
		RequestDealersMsgResultModels models = gson.fromJson((String)obj, RequestDealersMsgResultModels.class);
		setMsg(models.getMsg());
		setStatus(models.getStatus());
		
		setNewStyleList(models.getNewStyleList());
	}

	@Override
	public String toResultString() {
		return null;
	}
	public class CarStyleList{
		private String StyleId;
		private String NewImageUrl;
		private String NewFullName;
		private String NewNowMsrp;
		private List<DealersList> New4SList;
		public String getStyleId() {
			return StyleId;
		}
		public void setStyleId(String styleId) {
			StyleId = styleId;
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
		public List<DealersList> getNew4SList() {
			return New4SList;
		}
		public void setNew4SList(List<DealersList> new4sList) {
			New4SList = new4sList;
		}
	}
	public class DealersList{
		private String DealerId;
		private String FullName;
		private String Address;
		private String Tel;
		private String Tel400;
		private String Price;
		public String getDealerId() {
			return DealerId;
		}
		public void setDealerId(String dealerId) {
			DealerId = dealerId;
		}
		public String getFullName() {
			return FullName;
		}
		public void setFullName(String fullName) {
			FullName = fullName;
		}
		public String getAddress() {
			return Address;
		}
		public void setAddress(String address) {
			Address = address;
		}
		public String getTel() {
			return Tel;
		}
		public void setTel(String tel) {
			Tel = tel;
		}
		public String getTel400() {
			return Tel400;
		}
		public void setTel400(String tel400) {
			Tel400 = tel400;
		}
		public String getPrice() {
			return Price;
		}
		public void setPrice(String price) {
			Price = price;
		}
	}
	public List<CarStyleList> getNewStyleList() {
		return NewStyleList;
	}

	public void setNewStyleList(List<CarStyleList> newStyleList) {
		NewStyleList = newStyleList;
	}
}
