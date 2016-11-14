package com.jzg.jzgoto.phone.models.business.history;

import java.util.List;

import android.util.Log;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;
/**
 * @Description: 发布列表结果
 * @Package com.jzg.jzgoto.phone.models.business.history ReleaseListResultModels.java
 * @author gf
 * @date 2015-12-29 下午2:46:06
 */
public class ReleaseListResultModels extends BaseResultModels {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	private String totalPages;
	private String totalRecords;
	private String curPage;
	private String EveryPageNum;
	private List<CarBean> GZCarList;
	@Override
	public void setResult(Object obj) {
		Log.i("gf", "发布结果:" + (String)obj);
		Gson gson = new Gson();
		ReleaseListResultModels models = gson.fromJson((String)obj, ReleaseListResultModels.class);
		setMsg(models.getMsg());
		setStatus(models.getStatus());
		
		setTotalPages(models.getTotalPages());
		setTotalRecords(models.getTotalRecords());
		setCurPage(models.getCurPage());
		setEveryPageNum(models.getEveryPageNum());
		setGZCarList(models.getGZCarList());
	}

	@Override
	public String toResultString() {
		return null;
	}
	public class CarBean{
		// TODO 
		private String CarSourceFrom;
		private String CarSourceID;
		private String CarSourceImageUrl;
		private String CityName;
		private String FullName;
		private String Mileage;
		private String PersonalBusiness;
		private String PublishTime;
		private String ReleaseTime;
		private String SellPrice;
		private String StyleId;
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

	public List<CarBean> getGZCarList() {
		return GZCarList;
	}

	public void setGZCarList(List<CarBean> gZCarList) {
		GZCarList = gZCarList;
	}
}
