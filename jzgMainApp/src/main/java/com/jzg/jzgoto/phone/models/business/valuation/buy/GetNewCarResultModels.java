package com.jzg.jzgoto.phone.models.business.valuation.buy;

import java.io.Serializable;
import java.util.List;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;
/**
 * @Description: 获取推荐新车
 * @Package com.jzg.jzgoto.phone.models.business.valuation.buy GetNewCarResultModels.java
 * @author gf
 * @date 2016-6-14 上午10:48:26
 */
public class GetNewCarResultModels extends BaseResultModels {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	private String PageIndex;
	private List<NewCar> CarNewList;
	@Override
	public void setResult(Object obj) {
		Gson gson = new Gson();
		GetNewCarResultModels models = gson.fromJson((String)obj, GetNewCarResultModels.class);
		setMsg(models.getMsg());
		setStatus(models.getStatus());
		
		setCarNewList(models.getCarNewList());
		setPageIndex(models.getPageIndex());
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

	public List<NewCar> getCarNewList() {
		return CarNewList;
	}

	public void setCarNewList(List<NewCar> carNewList) {
		CarNewList = carNewList;
	}

	public static class NewCar implements Serializable{
		private String MakeId;
		private String ModelId;
		private String StyleId;
		private String styleFullName;
		
		private String NowMsrp;
		private String ImageUrl;
		private String YicheUrl;
		public String getMakeId() {
			return MakeId;
		}
		public void setMakeId(String makeId) {
			MakeId = makeId;
		}
		public String getModelId() {
			return ModelId;
		}
		public void setModelId(String modelId) {
			ModelId = modelId;
		}
		public String getStyleId() {
			return StyleId;
		}
		public void setStyleId(String styleId) {
			StyleId = styleId;
		}
		public String getStyleFullName() {
			return styleFullName;
		}
		public void setStyleFullName(String styleFullName) {
			this.styleFullName = styleFullName;
		}
		public String getNowMsrp() {
			return NowMsrp;
		}
		public void setNowMsrp(String nowMsrp) {
			NowMsrp = nowMsrp;
		}
		public String getImageUrl() {
			return ImageUrl;
		}
		public void setImageUrl(String imageUrl) {
			ImageUrl = imageUrl;
		}
		public String getYicheUrl() {
			return YicheUrl;
		}
		public void setYicheUrl(String yicheUrl) {
			YicheUrl = yicheUrl;
		}
	}
}
