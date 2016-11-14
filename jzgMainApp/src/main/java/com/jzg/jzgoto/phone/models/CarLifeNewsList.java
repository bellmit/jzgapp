/**
 * Project Name:JingZhenGu
 * File Name:CityList.java
 * Package Name:com.gc.jingzhengu.vo
 * Date:2014-6-17上午11:08:49
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
*/

package com.jzg.jzgoto.phone.models;

import java.io.Serializable;
import java.util.List;


/**
 * 车市头条列表对象
  * @ClassName: CarLifeNewsList
  * @Description: TODO
  * @author Comsys-jzg
  * @date 2015-12-23 下午5:39:41
  *
 */

public class CarLifeNewsList implements Serializable
{

	/**
	  * @Fields serialVersionUID : TODO
	  */
	
	private static final long serialVersionUID = 1L;
	
	
	 /**
     * EveryPageNum : 10
     * status : 100
     * curPage : 1
     * totalRecords : 582
     * list : [{"id":"703","title":"测试800450图片","img":"","tosum":"582","AddDate":"12.01 11:24","url":"http://m.jingzhengu.com/html/articledetail.aspx?id=703","Summary":"测试800450图片测试800450图片测试80045，0图片测试800450图片测试800450图片测试800450图片"},{"id":"702","title":"添加第二张图片第二次","img":"","tosum":"582","AddDate":"11.30 08:50","url":"http://m.jingzhengu.com/html/articledetail.aspx?id=702","Summary":"添加第二张图片第二次"},{"id":"701","title":"成功添加图片","img":"","tosum":"582","AddDate":"11.30 08:44","url":"http://m.jingzhengu.com/html/articledetail.aspx?id=701","Summary":"成功添加图片"},{"id":"700","title":"这个不是链接类型","img":"","tosum":"582","AddDate":"11.03 01:22","url":"http://m.jingzhengu.com/html/articledetail.aspx?id=700","Summary":"霜期"},{"id":"699","title":"测试添加链接","img":"","tosum":"582","AddDate":"11.03 01:17","url":"http://m.jingzhengu.com/html/articledetail.aspx?id=699","Summary":"adsf"},{"id":"698","title":"你厅要","img":"","tosum":"582","AddDate":"11.03 11:41","url":"http://m.jingzhengu.com/html/articledetail.aspx?id=698","Summary":"基"},{"id":"697","title":"你好好","img":"","tosum":"582","AddDate":"11.03 11:40","url":"http://m.jingzhengu.com/html/articledetail.aspx?id=697","Summary":"要"},{"id":"696","title":"测试测试11212","img":"","tosum":"582","AddDate":"11.03 11:39","url":"http://m.jingzhengu.com/html/articledetail.aspx?id=696","Summary":"东奔西走基"},{"id":"681","title":"测试新闻111111111144","img":"","tosum":"582","AddDate":"09.16 09:39","url":"http://m.jingzhengu.com/html/articledetail.aspx?id=681","Summary":"测试新闻1111111111"},{"id":"674","title":"造型动感设计精美 三款个性两厢车推荐","img":"","tosum":"582","AddDate":"08.26 10:23","url":"http://m.jingzhengu.com/html/articledetail.aspx?id=674","Summary":"随着时代的变迁，人们的消费理念改变，越来越多的年轻消费群体在购车时也更倾向于自己的爱好和感受，这使得以往备受冷落的两厢车迎来了春天。其实两厢车在欧洲是主流车型，无论是造型设计、功能性，都更讨人喜欢，所以，今天小编就为您推荐三款个性两厢车。"}]
     * msg : 成功
     * totalPages : 59
     */
    private String EveryPageNum;
    private String status;
    private String curPage;
    private String totalRecords;
    private List<ListEntity> list;
    private String msg;
    private String totalPages;

    public void setEveryPageNum(String EveryPageNum) {
        this.EveryPageNum = EveryPageNum;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCurPage(String curPage) {
        this.curPage = curPage;
    }

    public void setTotalRecords(String totalRecords) {
        this.totalRecords = totalRecords;
    }

    public void setList(List<ListEntity> list) {
        this.list = list;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setTotalPages(String totalPages) {
        this.totalPages = totalPages;
    }

    public String getEveryPageNum() {
        return EveryPageNum;
    }

    public String getStatus() {
        return status;
    }

    public String getCurPage() {
        return curPage;
    }

    public String getTotalRecords() {
        return totalRecords;
    }

    public List<ListEntity> getList() {
        return list;
    }

    public String getMsg() {
        return msg;
    }

    public String getTotalPages() {
        return totalPages;
    }

    public class ListEntity implements Serializable{
        /**
         * id : 703
         * title : 测试800450图片
         * img : 
         * tosum : 582
         * AddDate : 12.01 11:24
         * url : http://m.jingzhengu.com/html/articledetail.aspx?id=703
         * Summary : 测试800450图片测试800450图片测试80045，0图片测试800450图片测试800450图片测试800450图片
         */
        private String id;
        private String title;
        private String img;
        private String tosum;
        private String AddDate;
        private String url;
        private String Summary;

        public void setId(String id) {
            this.id = id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public void setTosum(String tosum) {
            this.tosum = tosum;
        }

        public void setAddDate(String AddDate) {
            this.AddDate = AddDate;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setSummary(String Summary) {
            this.Summary = Summary;
        }

        public String getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getImg() {
            return img;
        }

        public String getTosum() {
            return tosum;
        }

        public String getAddDate() {
            return AddDate;
        }

        public String getUrl() {
            return url;
        }

        public String getSummary() {
            return Summary;
        }
    }
    
	
}

