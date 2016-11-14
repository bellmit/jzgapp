package com.jzg.jzgoto.phone.models.business.history;

import java.util.List;

import android.util.Log;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;
/**
 * @Description: 提问列表
 * @Package com.jzg.jzgoto.phone.models.business.history AskQuestionListResultModels.java
 * @author gf
 * @date 2016-1-6 上午9:34:01
 */
public class AskQuestionListResultModels extends BaseResultModels {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	
	private String totalPages;
	private String totalRecords;
	private String curPage;
	private String EveryPageNum;
	private List<ItemBean> Invitationlist;
	@Override
	public void setResult(Object obj) {
		Log.i("gf", "提问列表:" + (String)obj);
		Gson gson = new Gson();
		AskQuestionListResultModels models = gson.fromJson((String)obj, AskQuestionListResultModels.class);
		setMsg(models.getMsg());
		setStatus(models.getStatus());
		
		setTotalPages(models.getTotalPages());
		setTotalRecords(models.getTotalRecords());
		setCurPage(models.getCurPage());
		setEveryPageNum(models.getEveryPageNum());
		setInvitationlist(models.getInvitationlist());
	}
	@Override
	public String toResultString() {
		return null;
	}
	public class ItemBean{
		private String Id;
		private String images;
		private String UserNumber;
		
		private String CommentCount;
		private String InvitationTitle;
		private String InviationDate;
		private String InvitationContent;
		
		private String StatusInfo;
		public String getStatusInfo() {
			return StatusInfo;
		}
		public void setStatusInfo(String statusInfo) {
			StatusInfo = statusInfo;
		}
		public String getId() {
			return Id;
		}
		public void setId(String id) {
			Id = id;
		}
		public String getImages() {
			return images;
		}
		public void setImages(String images) {
			this.images = images;
		}
		public String getUserNumber() {
			return UserNumber;
		}
		public void setUserNumber(String userNumber) {
			UserNumber = userNumber;
		}
		public String getCommentCount() {
			return CommentCount;
		}
		public void setCommentCount(String commentCount) {
			CommentCount = commentCount;
		}
		public String getInvitationTitle() {
			return InvitationTitle;
		}
		public void setInvitationTitle(String invitationTitle) {
			InvitationTitle = invitationTitle;
		}
		public String getInviationDate() {
			return InviationDate;
		}
		public void setInviationDate(String inviationDate) {
			InviationDate = inviationDate;
		}
		public String getInvitationContent() {
			return InvitationContent;
		}
		public void setInvitationContent(String invitationContent) {
			InvitationContent = invitationContent;
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
	public List<ItemBean> getInvitationlist() {
		return Invitationlist;
	}
	public void setInvitationlist(List<ItemBean> invitationlist) {
		Invitationlist = invitationlist;
	}
	
}
