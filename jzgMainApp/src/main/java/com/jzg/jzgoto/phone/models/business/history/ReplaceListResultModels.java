package com.jzg.jzgoto.phone.models.business.history;

import java.util.List;

import android.util.Log;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;
/**
 * @Description: 置换列表结果
 * @Package com.jzg.jzgoto.phone.models.business.history ReplaceListResultModels.java
 * @author gf
 * @date 2015-12-29 下午2:42:29
 */
public class ReplaceListResultModels extends BaseResultModels {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	
	private String totalPages;
	private String totalRecords;
	private String curPage;
	private String EveryPageNum;
	private List<CompareBean> comparelist;
	@Override
	public void setResult(Object obj) {
		Log.i("gf", "置换列表:" + (String)obj);
		Gson gson = new Gson();
		ReplaceListResultModels models = gson.fromJson((String)obj, ReplaceListResultModels.class);
		setMsg(models.getMsg());
		setStatus(models.getStatus());
		
		setTotalPages(models.getTotalPages());
		setTotalRecords(models.getTotalRecords());
		setCurPage(models.getCurPage());
		setEveryPageNum(models.getEveryPageNum());
		setComparelist(models.getComparelist());
	}

	@Override
	public String toResultString() {
		return null;
	}
	public class CompareBean{
		private String id;
		private String PriceC2BA;
		private String PriceC2BB;
		private String PriceC2BC;
		private String MyFullName;
		private String ImageUrl;
		private String RegistrationTime;
		private String MyMileage;
		private String MyCityName;
		private String CreateTIme;
		public String getPriceC2BA() {
			return PriceC2BA;
		}
		public void setPriceC2BA(String priceC2BA) {
			PriceC2BA = priceC2BA;
		}
		public String getPriceC2BB() {
			return PriceC2BB;
		}
		public void setPriceC2BB(String priceC2BB) {
			PriceC2BB = priceC2BB;
		}
		public String getPriceC2BC() {
			return PriceC2BC;
		}
		public void setPriceC2BC(String priceC2BC) {
			PriceC2BC = priceC2BC;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getMyFullName() {
			return MyFullName;
		}
		public void setMyFullName(String myFullName) {
			MyFullName = myFullName;
		}
		public String getImageUrl() {
			return ImageUrl;
		}
		public void setImageUrl(String imageUrl) {
			ImageUrl = imageUrl;
		}
		public String getRegistrationTime() {
			return RegistrationTime;
		}
		public void setRegistrationTime(String registrationTime) {
			RegistrationTime = registrationTime;
		}
		public String getMyMileage() {
			return MyMileage;
		}
		public void setMyMileage(String myMileage) {
			MyMileage = myMileage;
		}
		public String getMyCityName() {
			return MyCityName;
		}
		public void setMyCityName(String myCityName) {
			MyCityName = myCityName;
		}
		public String getCreateTIme() {
			return CreateTIme;
		}
		public void setCreateTIme(String createTIme) {
			CreateTIme = createTIme;
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

	public List<CompareBean> getComparelist() {
		return comparelist;
	}

	public void setComparelist(List<CompareBean> comparelist) {
		this.comparelist = comparelist;
	}
}
