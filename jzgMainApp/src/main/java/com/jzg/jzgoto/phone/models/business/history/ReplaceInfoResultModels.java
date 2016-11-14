package com.jzg.jzgoto.phone.models.business.history;

import java.util.List;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;
/**
 * @Description: 置换详情
 * @Package com.jzg.jzgoto.phone.models.business.history ReplaceInfoResultModels.java
 * @author gf
 * @date 2015-12-30 下午4:39:33
 */
public class ReplaceInfoResultModels extends BaseResultModels {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	private List<NewCarStyleBean> NewStyleList;
	@Override
	public void setResult(Object obj) {
		Gson gson = new Gson();
		ReplaceInfoResultModels models = gson.fromJson((String)obj, ReplaceInfoResultModels.class);
		setMsg(models.getMsg());
		setStatus(models.getStatus());
		setNewStyleList(models.getNewStyleList());
	}
	@Override
	public String toResultString() {
		return null;
	}
	public class NewCarStyleBean{
		private String StyleId;
		private String NewImageUrl;
		private String NewFullName;
		private String NewNowMsrp;
		private List<DealersBean> New4SList;
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
		public List<DealersBean> getNew4SList() {
			return New4SList;
		}
		public void setNew4SList(List<DealersBean> new4sList) {
			New4SList = new4sList;
		}
	}
	public class DealersBean{
		private String DealerId;
		private String FullName;
		private String Address;
		private String tel;
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
			return tel;
		}
		public void setTel(String tel) {
			this.tel = tel;
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
	public List<NewCarStyleBean> getNewStyleList() {
		return NewStyleList;
	}

	public void setNewStyleList(List<NewCarStyleBean> newStyleList) {
		NewStyleList = newStyleList;
	}
}
