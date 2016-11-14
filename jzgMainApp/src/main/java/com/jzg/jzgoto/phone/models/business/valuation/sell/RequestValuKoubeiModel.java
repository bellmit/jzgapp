package com.jzg.jzgoto.phone.models.business.valuation.sell;

import java.io.Serializable;

/**
 * 估值结果    口碑
 * @author wangying
 * @date 2016年6月12日
 * @className
 */
public class RequestValuKoubeiModel implements Serializable{

	/**
	 * "Id": "1",
            "text": "外观大气舒适，便宜，好用不错",
            "userName": "精真估网友",
            "addTime": "2016.06.12 11:24:34",
            "favorCount": "23"
	 */
	private static final long serialVersionUID = 1L;
	private String Id;
	private String text;
	private String userName;
	private String addTime;
	private String favorCount;
	
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getFavorCount() {
		return favorCount;
	}
	public void setFavorCount(String favorCount) {
		this.favorCount = favorCount;
	}
	@Override
	public String toString() {
		return "RequestValuKoubeiModel [Id=" + Id + ", text=" + text
				+ ", userName=" + userName + ", addTime=" + addTime
				+ ", favorCount=" + favorCount + "]";
	}
	
}
