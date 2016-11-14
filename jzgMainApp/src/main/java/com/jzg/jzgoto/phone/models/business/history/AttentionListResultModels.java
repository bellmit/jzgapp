package com.jzg.jzgoto.phone.models.business.history;

import java.util.List;

import android.util.Log;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;
/**
 * @Description: 关注列表
 * @Package com.jzg.jzgoto.phone.models.business.history AttentionResultModels.java
 * @author gf
 * @date 2015-12-28 上午9:58:03
 */
public class AttentionListResultModels extends BaseResultModels {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;

	private String totalPages;
	private String totalRecords;
	private String curPage;
	private String EveryPageNum;
	private List<CarMsgBean> carlist;
	@Override
	public void setResult(Object obj) {
		Log.i("gf", "关注列表:" + (String)obj);
		Gson gson = new Gson();
		AttentionListResultModels models = gson.fromJson((String)obj, AttentionListResultModels.class);
		setMsg(models.getMsg());
		setStatus(models.getStatus());
		
		setTotalPages(models.getTotalPages());
		setTotalRecords(models.getTotalRecords());
		setCurPage(models.getCurPage());
		setEveryPageNum(models.getEveryPageNum());
		setCarlist(models.getCarlist());
	}

	@Override
	public String toResultString() {
		return null;
	}
	public class CarMsgBean{
		private String ScId;
		private String CarSourceID;
		private String StyleId;
		private String CarSourceImageUrl;
		private String FullName;
		
		private String PersonalBusiness;
		private String Mileage;
		private String ReleaseTime;
		private String PublishTime;
		private String SellPrice;
		
		private String CarSourceFrom;
		private String CityName;
		public String getScId() {
			return ScId;
		}
		public void setScId(String scId) {
			ScId = scId;
		}
		public String getCarSourceFrom() {
			return CarSourceFrom;
		}
		public void setCarSourceFrom(String carSourceFrom) {
			CarSourceFrom = carSourceFrom;
		}
		public String getCarSourceID() {
			return CarSourceID;
		}
		public void setCarSourceID(String carSourceID) {
			CarSourceID = carSourceID;
		}
		public String getCarSourceImageUrl() {
			return CarSourceImageUrl;
		}
		public void setCarSourceImageUrl(String carSourceImageUrl) {
			CarSourceImageUrl = carSourceImageUrl;
		}
		public String getCityName() {
			return CityName;
		}
		public void setCityName(String cityName) {
			CityName = cityName;
		}
		public String getFullName() {
			return FullName;
		}
		public void setFullName(String fullName) {
			FullName = fullName;
		}
		public String getMileage() {
			return Mileage;
		}
		public void setMileage(String mileage) {
			Mileage = mileage;
		}
		public String getPersonalBusiness() {
			return PersonalBusiness;
		}
		public void setPersonalBusiness(String personalBusiness) {
			PersonalBusiness = personalBusiness;
		}
		public String getPublishTime() {
			return PublishTime;
		}
		public void setPublishTime(String publishTime) {
			PublishTime = publishTime;
		}
		public String getReleaseTime() {
			return ReleaseTime;
		}
		public void setReleaseTime(String releaseTime) {
			ReleaseTime = releaseTime;
		}
		public String getSellPrice() {
			return SellPrice;
		}
		public void setSellPrice(String sellPrice) {
			SellPrice = sellPrice;
		}
		public String getStyleId() {
			return StyleId;
		}
		public void setStyleId(String styleId) {
			StyleId = styleId;
		}
	}
	public String getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(String totalPages) {
		this.totalPages = totalPages;
	}

	public String getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}

	public String getCurPage() {
		return curPage;
	}

	public void setCurPage(String curPage) {
		this.curPage = curPage;
	}

	public String getEveryPageNum() {
		return EveryPageNum;
	}

	public void setEveryPageNum(String everyPageNum) {
		EveryPageNum = everyPageNum;
	}
	public List<CarMsgBean> getCarlist() {
		return carlist;
	}
	public void setCarlist(List<CarMsgBean> carlist) {
		this.carlist = carlist;
	}
}
