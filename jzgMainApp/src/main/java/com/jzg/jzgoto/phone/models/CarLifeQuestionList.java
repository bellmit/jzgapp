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
 * 寻车问诊列表对象
  * @ClassName: CarLifeNewsList
  * @Description: TODO
  * @author Comsys-jzg
  * @date 2015-12-23 下午5:39:41
  *
 */

public class CarLifeQuestionList implements Serializable
{

	/**
	  * @Fields serialVersionUID : TODO
	  */
	
	private static final long serialVersionUID = 1L;
	
	


    /**
     * EveryPageNum : 10
     * status : 100
     * curPage : 1
     * totalRecords : 24
     * Invitationlist : [{"InvitationContent":"分热","InvitationTitle":"快来看看看","InviationDate":"2015/12/12 22:37:32","images":"http://res.jingzhengu.com/ptv/5.0/images/zjjd.jpg","Id":"51","UserNumber":"JZG91288552","CommentCount":"0"},{"InvitationContent":"33333","InvitationTitle":"343434","InviationDate":"2015/12/12 22:34:17","images":"http://res.jingzhengu.com/ptv/5.0/images/zjjd.jpg","Id":"50","UserNumber":"JZG91288552","CommentCount":"0"},{"InvitationContent":"45555","InvitationTitle":"3333","InviationDate":"2015/12/12 22:05:31","images":"http://res.jingzhengu.com/ptv/5.0/images/zjjd.jpg","Id":"49","UserNumber":"JZG91288552","CommentCount":"0"},{"InvitationContent":"yyyyyyy","InvitationTitle":"wwwwww","InviationDate":"2015/12/12 20:07:52","images":"http://res.jingzhengu.com/ptv/5.0/images/zjjd.jpg","Id":"48","UserNumber":"JZG91288552","CommentCount":"0"},{"InvitationContent":"我手里有2000块钱 应该买个什么样子的车呢？","InvitationTitle":"我手里有2000块钱 应该买个什么样子的车呢？","InviationDate":"2015/12/11 18:04:30","images":"http://imageup.jingzhengu.com/2015/07/18/img10503179_901.jpg","Id":"43","UserNumber":"JZG80218171","CommentCount":"1"},{"InvitationContent":"女生想买个15-22万的车（包括上牌等），求大神指点买什么样的？女生想买个15-22万的车（包括上牌等），求大神指点买什么样的？","InvitationTitle":"女生想买个15-22万的车（包括上牌等），求大神指点买什么样的？女生想买个15-22万的车（包括上牌等），求大神指点买什么样的？","InviationDate":"2015/12/2 16:58:18","images":"http://res.jingzhengu.com/ptv/5.0/images/zjjd.jpg","Id":"16","UserNumber":"","CommentCount":"6"},{"InvitationContent":"女生想买个15-22万的车（包括上牌等），求大神指点买什么样的？女生想买个15-22万的车（包括上牌等），求大神指点买什么样的？","InvitationTitle":"女生想买个15-22万的车（包括上牌等），求大神指点买什么样的？女生想买个15-22万的车（包括上牌等），求大神指点买什么样的？","InviationDate":"2015/12/2 16:50:45","images":"http://res.jingzhengu.com/ptv/5.0/images/zjjd.jpg","Id":"15","UserNumber":"","CommentCount":"10"},{"InvitationContent":"女生想买个15-22万的车（包括上牌等），求大神指点买什么样的？女生想买个15-22万的车（包括上牌等），求大神指点买什么样的？","InvitationTitle":"女生想买个15-22万的车（包括上牌等），求大神指点买什么样的？女生想买个15-22万的车（包括上牌等），求大神指点买什么样的？","InviationDate":"2015/12/2 16:50:41","images":"http://res.jingzhengu.com/ptv/5.0/images/zjjd.jpg","Id":"14","UserNumber":"","CommentCount":"20"},{"InvitationContent":"女生想买3223","InvitationTitle":"女生想买个15-22万的车（包括上牌等），求大神指点买什么样的？女生想买个15-22万的车（包括上牌等），求大神指点买什么样的？","InviationDate":"2015/12/2 16:46:19","images":"http://res.jingzhengu.com/ptv/5.0/images/zjjd.jpg","Id":"13","UserNumber":"","CommentCount":"23"},{"InvitationContent":"IE化工IE鬼哦IE我给列，为各位一个！吻能否符合划分是否是考核方式开会方式开会发放五全方哈哈哈哈哈哈的味道窝窝头额外羊头肉画的父一费覅分Joe即佛挖墙脚佛号分为几分空间法，偶就氛围方法额外给个人 额外。","InvitationTitle":"这是一个测试帖","InviationDate":"2015/11/12 0:00:00","images":"http://res.jingzhengu.com/ptv/5.0/images/zjjd.jpg","Id":"2","UserNumber":"JZG53779777","CommentCount":"30"}]
     * msg : 成功
     * totalPages : 3
     */
    private String EveryPageNum;
    private String status;
    private String curPage;
    private String totalRecords;
    private List<InvitationlistEntity> Invitationlist;
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

    public void setInvitationlist(List<InvitationlistEntity> Invitationlist) {
        this.Invitationlist = Invitationlist;
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

    public List<InvitationlistEntity> getInvitationlist() {
        return Invitationlist;
    }

    public String getMsg() {
        return msg;
    }

    public String getTotalPages() {
        return totalPages;
    }

    public static class InvitationlistEntity implements Serializable{
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		/**
         * InvitationContent : 分热
         * InvitationTitle : 快来看看看
         * InviationDate : 2015/12/12 22:37:32
         * images : http://res.jingzhengu.com/ptv/5.0/images/zjjd.jpg
         * Id : 51
         * UserNumber : JZG91288552
         * CommentCount : 0
         */
        private String InvitationContent;
        private String InvitationTitle;
        private String InviationDate;
        private String images;
        private String Id;
        private String UserNumber;
        private String CommentCount;

        public void setInvitationContent(String InvitationContent) {
            this.InvitationContent = InvitationContent;
        }

        public void setInvitationTitle(String InvitationTitle) {
            this.InvitationTitle = InvitationTitle;
        }

        public void setInviationDate(String InviationDate) {
            this.InviationDate = InviationDate;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public void setUserNumber(String UserNumber) {
            this.UserNumber = UserNumber;
        }

        public void setCommentCount(String CommentCount) {
            this.CommentCount = CommentCount;
        }

        public String getInvitationContent() {
            return InvitationContent;
        }

        public String getInvitationTitle() {
            return InvitationTitle;
        }

        public String getInviationDate() {
            return InviationDate;
        }

        public String getImages() {
            return images;
        }

        public String getId() {
            return Id;
        }

        public String getUserNumber() {
            return UserNumber;
        }

        public String getCommentCount() {
            return CommentCount;
        }
    }
	
}

