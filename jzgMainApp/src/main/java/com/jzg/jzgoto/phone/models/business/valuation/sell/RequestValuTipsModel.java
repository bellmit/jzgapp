package com.jzg.jzgoto.phone.models.business.valuation.sell;

import java.io.Serializable;
/**
 * 估值结果	贴士
 * @author wangying
 * @date 2016年6月12日
 * @className
 */
public class RequestValuTipsModel implements Serializable{

	private static final long serialVersionUID = 1L;
//	 "ArticalId": "2544",
//     "Articaltitle": "买多少价位的二手车适合练手？",
//     "ArticalSummary": "当你拿到“新手上路 请多关照”的纸贴时，标志着你已...",
//     "img": "http://image.jingzhengu.com/CMSUploadFiles/20160525/18!20160525133959454_904.jpg",
//     "url": "http://10.58.0.134/life/ArticleDetail-2544.html?type=app"

	private String ArticalId;
	private String Articaltitle;
	private String ArticalSummary;
	private String img;
	private String url;
	
	public String getArticalId() {
		return ArticalId;
	}
	public void setArticalId(String articalId) {
		ArticalId = articalId;
	}
	public String getArticaltitle() {
		return Articaltitle;
	}
	public void setArticaltitle(String articaltitle) {
		Articaltitle = articaltitle;
	}
	public String getArticalSummary() {
		return ArticalSummary;
	}
	public void setArticalSummary(String articalSummary) {
		ArticalSummary = articalSummary;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "RequestValuTipsModel [ArticalId=" + ArticalId
				+ ", Articaltitle=" + Articaltitle + ", ArticalSummary="
				+ ArticalSummary + ", img=" + img + ", url=" + url + "]";
	}
	
}
