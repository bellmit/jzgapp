package com.jzg.jzgoto.phone.models.business.buy;

import java.io.Serializable;

import com.umeng.socialize.media.UMImage;

public class ShareModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String shareUrl;
	private String shareText;
	private String shareTitle;
	private UMImage umImage;
	public String getShareUrl() {
		return shareUrl;
	}
	public void setShareUrl(String shareUrl) {
		this.shareUrl = shareUrl;
	}
	public String getShareText() {
		return shareText;
	}
	public void setShareText(String shareText) {
		this.shareText = shareText;
	}
	public String getShareTitle() {
		return shareTitle;
	}
	public void setShareTitle(String shareTitle) {
		this.shareTitle = shareTitle;
	}
	public UMImage getUMImage() {
		return umImage;
	}
	public void setUMImage(UMImage umImage) {
		this.umImage = umImage;
	}
	@Override
	public String toString() {
		return "ShareModel [shareUrl=" + shareUrl + ", shareText=" + shareText
				+ ", shareTitle=" + shareTitle + ", umImage=" + umImage
				+ "]";
	}
}
