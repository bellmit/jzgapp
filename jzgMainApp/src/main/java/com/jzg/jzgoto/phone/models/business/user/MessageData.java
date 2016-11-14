package com.jzg.jzgoto.phone.models.business.user;
import android.text.TextUtils;

import java.io.Serializable;

public class MessageData implements Serializable {
	public static final String CATEGORY_PRIVATE = "1";
	public static final String CATEGORY_PUBLIC = "2";

	public boolean isExpand;
    public int messageId;
    public String Title;
	public String Content;
	public String UpdateTime;
	public String imageUrl;
	public String webUrl;
	public String Category;

	public String getUpdateTime() {
		return UpdateTime;
	}

	public void setUpdateTime(String updateTime) {
		UpdateTime = updateTime;
	}

	public boolean isRead;
	public boolean isAll = false;

	public boolean isAll() {
		return isAll;
	}

	public void setAll(boolean all) {
		isAll = all;
	}

	public MessageData(){
		//test data
		Title = "title";
		Content = "description";
		UpdateTime = "11.06 12:00";
		imageUrl = "http://imageup.jingzhengu.com/2016/06/07/1ed3fb4c-67f7-4e7c-8285-509b3f50b045_501.jpg";
		webUrl = "https://m.baidu.com/";
	}

    public int MessageData() {
		return messageId;
    }

	public int getId(){
		return messageId;
	}

	public String getTitle(){
		return Title;
	}

	public String getDetail(){
		return Content;
	}

	public String getTime(){
		return UpdateTime;
	}

	public String getCategory(){
		return Category;
	}

	public String getImageUrl(){
		return imageUrl;
	}

	public String getWebUrl(){
		return webUrl;
	}

	public boolean isPublic(){
		if (!TextUtils.isEmpty(Category)){
			if (Category.equals(CATEGORY_PUBLIC)){
				return true;
			}
		}
		return false;
	}
   
};
