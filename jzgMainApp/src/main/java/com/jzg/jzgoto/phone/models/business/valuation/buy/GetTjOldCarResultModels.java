package com.jzg.jzgoto.phone.models.business.valuation.buy;

import java.util.List;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;
/**
 * @Description: 获取推荐旧车
 * @Package com.jzg.jzgoto.phone.models.business.valuation.buy GetTjOldCarResultModels.java
 * @author gf
 * @date 2016-6-14 下午3:12:52
 */
public class GetTjOldCarResultModels extends BaseResultModels {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	private String PageIndex;
	private List<OldCar> CarOldList;
	@Override
	public void setResult(Object obj) {
		Gson gson = new Gson();
		GetTjOldCarResultModels models = gson.fromJson((String)obj, GetTjOldCarResultModels.class);
		setMsg(models.getMsg());
		setStatus(models.getStatus());
		setPageIndex(models.getPageIndex());
		setCarOldList(models.getCarOldList());
	}
	@Override
	public String toResultString() {
		return null;
	}
	public String getPageIndex() {
		return PageIndex;
	}
	public void setPageIndex(String pageIndex) {
		PageIndex = pageIndex;
	}
	public List<OldCar> getCarOldList() {
		return CarOldList;
	}
	public void setCarOldList(List<OldCar> carOldList) {
		CarOldList = carOldList;
	}

	public static class OldCar{
		private String CarSourceID;
		private String CarSourceFrom;
		private String imgurl;
		private String StyleFullName;
		private String sellprice;
		private String Mileage;
		private String RegDate;
		private String CsourceTypes;
		private String didian;
		public String getCarSourceID() {
			return CarSourceID;
		}
		public void setCarSourceID(String carSourceID) {
			CarSourceID = carSourceID;
		}
		public String getCarSourceFrom() {
			return CarSourceFrom;
		}
		public void setCarSourceFrom(String carSourceFrom) {
			CarSourceFrom = carSourceFrom;
		}
		public String getImgurl() {
			return imgurl;
		}
		public void setImgurl(String imgurl) {
			this.imgurl = imgurl;
		}
		public String getStyleFullName() {
			return StyleFullName;
		}
		public void setStyleFullName(String styleFullName) {
			StyleFullName = styleFullName;
		}
		public String getSellprice() {
			return sellprice;
		}
		public void setSellprice(String sellprice) {
			this.sellprice = sellprice;
		}
		public String getMileage() {
			return Mileage;
		}
		public void setMileage(String mileage) {
			Mileage = mileage;
		}
		public String getRegDate() {
			return RegDate;
		}
		public void setRegDate(String regDate) {
			RegDate = regDate;
		}
		public String getCsourceTypes() {
			return CsourceTypes;
		}
		public void setCsourceTypes(String csourceTypes) {
			CsourceTypes = csourceTypes;
		}
		public String getDidian() {
			return didian;
		}
		public void setDidian(String didian) {
			this.didian = didian;
		}
	}
}
