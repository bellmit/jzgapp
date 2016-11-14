package com.jzg.jzgoto.phone.models.business.valuation.sell;

import java.io.Serializable;

public class RequestTipsItemModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	"id": "2588",
//    "title": "排放标准国五升国六 对二手车行业影响几何？",
//    "AddDate": "06.03 02:39",
//    "Summary": "对于不少地区来讲，“国五”排放标准才刚刚实施，就已...",
//    "img": "http://image.jingzhengu.com/CMSUploadFiles/20160603/18!20160603154615403_904.jpg",
//    "url": "http://10.58.0.134/life/ArticleDetail-2588.html?type=app",
//    "tosum": "1604"
	private String id;
	private String title;
	private String AddDate;
	private String Summary;
	private String img;
	private String url;
	private String tosum;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAddDate() {
		return AddDate;
	}
	public void setAddDate(String addDate) {
		AddDate = addDate;
	}
	public String getSummary() {
		return Summary;
	}
	public void setSummary(String summary) {
		Summary = summary;
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
	public String getTosum() {
		return tosum;
	}
	public void setTosum(String tosum) {
		this.tosum = tosum;
	}
	@Override
	public String toString() {
		return "RequestTipsItemModel [id=" + id + ", title=" + title
				+ ", AddDate=" + AddDate + ", Summary=" + Summary + ", img="
				+ img + ", url=" + url + ", tosum=" + tosum + "]";
	}
	
}
