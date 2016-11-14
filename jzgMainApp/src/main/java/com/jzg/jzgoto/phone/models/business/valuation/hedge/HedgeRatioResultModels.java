package com.jzg.jzgoto.phone.models.business.valuation.hedge;

import android.util.Log;

import java.util.List;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;
/**
 * @Description: 保值率排行结果
 * @Package com.jzg.jzgoto.phone.models.business.valuation.buy HedgeRatioResultModels.java
 * @author gf
 * @date 2016-6-13 下午3:33:08
 */
public class HedgeRatioResultModels extends BaseResultModels {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	private String PageIndex;
	private List<Rank> RankList;
	@Override
	public void setResult(Object obj) {
		Gson gson = new Gson();
		Log.e("保值率排行数据-",(String)obj);
		HedgeRatioResultModels models = gson.fromJson((String)obj, HedgeRatioResultModels.class);
		setMsg(models.getMsg());
		setStatus(models.getStatus());
		setPageIndex(models.getPageIndex());
		setRankList(models.getRankList());
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

	public List<Rank> getRankList() {
		return RankList;
	}

	public void setRankList(List<Rank> rankList) {
		RankList = rankList;
	}

	public class Rank{
		
		private String Rank;
		private String MakeID;
		private String MakeName;
		private String ModelId;
		private String ModelName;
		private String ModelPic;
		private List<Detail> detailList;
		
		public String getMakeID() {
			return MakeID;
		}
		public void setMakeID(String makeID) {
			MakeID = makeID;
		}
		public String getMakeName() {
			return MakeName;
		}
		public void setMakeName(String makeName) {
			MakeName = makeName;
		}
		public String getRank() {
			return Rank;
		}
		public void setRank(String rank) {
			Rank = rank;
		}
		public String getModelId() {
			return ModelId;
		}
		public void setModelId(String modelId) {
			ModelId = modelId;
		}
		public String getModelName() {
			return ModelName;
		}
		public void setModelName(String modelName) {
			ModelName = modelName;
		}
		public List<Detail> getDetailList() {
			return detailList;
		}
		public void setDetailList(List<Detail> detailList) {
			this.detailList = detailList;
		}
		public String getModelPic() {
			return ModelPic;
		}
		public void setModelPic(String modelPic) {
			ModelPic = modelPic;
		}
		
	}
	
	public class Detail{
		private String Year;
		private String data;
		public String getYear() {
			return Year;
		}
		public void setYear(String year) {
			Year = year;
		}
		public String getData() {
			return data;
		}
		public void setData(String data) {
			this.data = data;
		}
	}
}
