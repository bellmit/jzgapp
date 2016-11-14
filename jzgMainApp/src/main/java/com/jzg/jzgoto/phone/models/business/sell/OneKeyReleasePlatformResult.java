package com.jzg.jzgoto.phone.models.business.sell;

import android.util.Log;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;

import java.util.List;

/**
 * author: guochen
 * date: 2016/9/28 21:09
 * email:
 * 一键发布平台实体
 */
public class OneKeyReleasePlatformResult extends BaseResultModels {


    /**
     * SendCarPlatName : 选择卖给个人的平台
     * SendCarPlatList : [{"ID":"1","ImageUrl":"http://192.168.6.147:9001/logo/logo_renrenche_mr@2x.png","ImageUrlDef":"http://192.168.6.147:9001/logo/logo_renrenche_def@2x.png"},{"ID":"2","ImageUrl":"http://192.168.6.147:9001/logo/logo_guazi_mr@2x.png","ImageUrlDef":"http://192.168.6.147:9001/logo/logo_guazi_def@2x.png"}]
     */

    private List<SendCarPlatTotalListBean> SendCarPlatTotalList;

    @Override
    public void setResult(Object obj) {
        Gson gson = new Gson();
        Log.i("gf", "车抵贷-"+obj.toString());
        OneKeyReleasePlatformResult models = gson.fromJson((String)obj, OneKeyReleasePlatformResult.class);
        setMsg(models.getMsg());
        setStatus(models.getStatus());
        setSendCarPlatTotalList(models.getSendCarPlatTotalList());

    }

    @Override
    public String toResultString() {
        return null;
    }

    public List<SendCarPlatTotalListBean> getSendCarPlatTotalList() {
        return SendCarPlatTotalList;
    }

    public void setSendCarPlatTotalList(List<SendCarPlatTotalListBean> SendCarPlatTotalList) {
        this.SendCarPlatTotalList = SendCarPlatTotalList;
    }

    public static class SendCarPlatTotalListBean {
        private String SendCarPlatName;
        /**
         * ID : 1
         * ImageUrl : http://192.168.6.147:9001/logo/logo_renrenche_mr@2x.png
         * ImageUrlDef : http://192.168.6.147:9001/logo/logo_renrenche_def@2x.png
         */

        private List<SendCarPlatListBean> SendCarPlatList;

        public String getSendCarPlatName() {
            return SendCarPlatName;
        }

        public void setSendCarPlatName(String SendCarPlatName) {
            this.SendCarPlatName = SendCarPlatName;
        }

        public List<SendCarPlatListBean> getSendCarPlatList() {
            return SendCarPlatList;
        }

        public void setSendCarPlatList(List<SendCarPlatListBean> SendCarPlatList) {
            this.SendCarPlatList = SendCarPlatList;
        }

        public static class SendCarPlatListBean {
            private String ID;
            private String ImageUrl;
            private String ImageUrlDef;

            public String getID() {
                return ID;
            }

            public void setID(String ID) {
                this.ID = ID;
            }

            public String getImageUrl() {
                return ImageUrl;
            }

            public void setImageUrl(String ImageUrl) {
                this.ImageUrl = ImageUrl;
            }

            public String getImageUrlDef() {
                return ImageUrlDef;
            }

            public void setImageUrlDef(String ImageUrlDef) {
                this.ImageUrlDef = ImageUrlDef;
            }
        }
    }
}
