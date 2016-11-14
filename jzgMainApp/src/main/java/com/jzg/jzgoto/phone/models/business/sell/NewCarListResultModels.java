package com.jzg.jzgoto.phone.models.business.sell;

import java.util.List;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;
/**
 * @Description: 新车列表
 * @Package com.jzg.jzgoto.phone.models.business.sell NewCarListResultModels.java
 * @author gf
 * @date 2015-12-24 下午4:50:39
 */
public class NewCarListResultModels extends BaseResultModels {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	
	private String totalPages;
	private String totalRecords;
	private String curPage;
	private String EveryPageNum;
	private List<ModelListBean> ModelList;
	
	@Override
	public void setResult(Object obj) {
//		Log.i("gf", "新车列表结果:" + (String)obj);
		Gson gson = new Gson();
		NewCarListResultModels models = gson.fromJson((String)obj, NewCarListResultModels.class);
		setMsg(models.getMsg());
		setStatus(models.getStatus());
		
		setTotalPages(models.getTotalPages());
		setTotalRecords(models.getTotalRecords());
		setCurPage(models.getCurPage());
		setEveryPageNum(models.getEveryPageNum());
		setModelList(models.getModelList());
	}

	@Override
	public String toResultString() {
		return null;
	}
	
	public static class ModelListBean{
		private String StyleCount;
		private String ModelID;
		private String ModelName;
		private String MinMsrp;
		private String MaxMsrp;
		private String ImageUrl;
		private List<ItemStyleList> StyleList;
		public String getStyleCount() {
			return StyleCount;
		}
		public void setStyleCount(String styleCount) {
			StyleCount = styleCount;
		}
		public String getModelID() {
			return ModelID;
		}
		public void setModelID(String modelID) {
			ModelID = modelID;
		}
		public String getModelName() {
			return ModelName;
		}
		public void setModelName(String modelName) {
			ModelName = modelName;
		}
		public String getMinMsrp() {
			return MinMsrp;
		}
		public void setMinMsrp(String minMsrp) {
			MinMsrp = minMsrp;
		}
		public String getMaxMsrp() {
			return MaxMsrp;
		}
		public void setMaxMsrp(String maxMsrp) {
			MaxMsrp = maxMsrp;
		}
		public String getImageUrl() {
			return ImageUrl;
		}
		public void setImageUrl(String imageUrl) {
			ImageUrl = imageUrl;
		}
		public List<ItemStyleList> getStyleList() {
			return StyleList;
		}
		public void setStyleList(List<ItemStyleList> styleList) {
			StyleList = styleList;
		}
	}
	
	public static class ItemStyleList{

		private String StyleId;
		private String StyleName;
		private String NowMsrp;
		private String Year;
		private String CarFullName;
		public String getStyleId() {
			return StyleId;
		}
		public void setStyleId(String styleId) {
			StyleId = styleId;
		}
		public String getStyleName() {
			return StyleName;
		}
		public void setStyleName(String styleName) {
			StyleName = styleName;
		}
		public String getNowMsrp() {
			return NowMsrp;
		}
		public void setNowMsrp(String nowMsrp) {
			NowMsrp = nowMsrp;
		}
		public String getYear() {
			return Year;
		}
		public void setYear(String year) {
			Year = year;
		}
		public String getCarFullName() {
			return CarFullName;
		}
		public void setCarFullName(String carFullName) {
			CarFullName = carFullName;
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

	public List<ModelListBean> getModelList() {
		return ModelList;
	}

	public void setModelList(List<ModelListBean> modelList) {
		ModelList = modelList;
	}
}
