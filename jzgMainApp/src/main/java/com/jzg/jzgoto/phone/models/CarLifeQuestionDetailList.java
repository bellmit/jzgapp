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
 * 寻车问诊详情列表对象
  * @ClassName: CarLifeNewsList
  * @Description: TODO
  * @author Comsys-jzg
  * @date 2015-12-23 下午5:39:41
  *
 */

public class CarLifeQuestionDetailList implements Serializable
{

	/**
	  * @Fields serialVersionUID : TODO
	  */
	
	private static final long serialVersionUID = 1L;
	
	/**
     * CommentList : [{"yhCommentContent":"呵呵呵呵呵呵","yhUserNumber":"JZG70327142","yhCommentDate2":"2015-12-10 14:44:55","yhimages":"http://imageup.jingzhengu.com/2015/07/24/img16245052_901.jpg"}]
     * Date : 2015/12/9 17:11:40
     * status : 100
     * images : http://imageup.jingzhengu.com/2015/07/24/img16245052_901.jpg
     * Content : 安安
     * UserNumber : JZG70327142
     * msg : 成功
     * ZJList : {"ZJUserNumber":"","ZJDate":"2015-12-15 18:41:09","ZJContent":"9999999999999999","ZJimages":"http://www.jingzhengu.com/resources/images/ExpertDefPic.jpg"}
     */
    private List<CommentListEntity> CommentList;
    private String Date;
    private String status;
    private String images;
    private String Content;
    private String UserNumber;
    private String msg;
    private ZJListEntity ZJList;

    public void setCommentList(List<CommentListEntity> CommentList) {
        this.CommentList = CommentList;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public void setUserNumber(String UserNumber) {
        this.UserNumber = UserNumber;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setZJList(ZJListEntity ZJList) {
        this.ZJList = ZJList;
    }

    public List<CommentListEntity> getCommentList() {
        return CommentList;
    }

    public String getDate() {
        return Date;
    }

    public String getStatus() {
        return status;
    }

    public String getImages() {
        return images;
    }

    public String getContent() {
        return Content;
    }

    public String getUserNumber() {
        return UserNumber;
    }

    public String getMsg() {
        return msg;
    }

    public ZJListEntity getZJList() {
        return ZJList;
    }

    public class CommentListEntity {
        /**
         * yhCommentContent : 呵呵呵呵呵呵
         * yhUserNumber : JZG70327142
         * yhCommentDate2 : 2015-12-10 14:44:55
         * yhimages : http://imageup.jingzhengu.com/2015/07/24/img16245052_901.jpg
         */
        private String yhCommentContent;
        private String yhUserNumber;
        private String yhCommentDate2;
        private String yhimages;
        private String tiezistatus;

        public void setYhCommentContent(String yhCommentContent) {
            this.yhCommentContent = yhCommentContent;
        }

        public void setYhUserNumber(String yhUserNumber) {
            this.yhUserNumber = yhUserNumber;
        }

        public void setYhCommentDate2(String yhCommentDate2) {
            this.yhCommentDate2 = yhCommentDate2;
        }

        public void setYhimages(String yhimages) {
            this.yhimages = yhimages;
        }

        public String getYhCommentContent() {
            return yhCommentContent;
        }

        public String getYhUserNumber() {
            return yhUserNumber;
        }

        public String getYhCommentDate2() {
            return yhCommentDate2;
        }

        public String getYhimages() {
            return yhimages;
        }

		public String getTiezistatus() {
			return tiezistatus;
		}

		public void setTiezistatus(String tiezistatus) {
			this.tiezistatus = tiezistatus;
		}
        
        
    }

    public class ZJListEntity {
        /**
         * ZJUserNumber : 
         * ZJDate : 2015-12-15 18:41:09
         * ZJContent : 9999999999999999
         * ZJimages : http://www.jingzhengu.com/resources/images/ExpertDefPic.jpg
         */
        private String ZJUserNumber;
        private String ZJDate;
        private String ZJContent;
        private String ZJimages;

        public void setZJUserNumber(String ZJUserNumber) {
            this.ZJUserNumber = ZJUserNumber;
        }

        public void setZJDate(String ZJDate) {
            this.ZJDate = ZJDate;
        }

        public void setZJContent(String ZJContent) {
            this.ZJContent = ZJContent;
        }

        public void setZJimages(String ZJimages) {
            this.ZJimages = ZJimages;
        }

        public String getZJUserNumber() {
            return ZJUserNumber;
        }

        public String getZJDate() {
            return ZJDate;
        }

        public String getZJContent() {
            return ZJContent;
        }

        public String getZJimages() {
            return ZJimages;
        }
    }
	
}

