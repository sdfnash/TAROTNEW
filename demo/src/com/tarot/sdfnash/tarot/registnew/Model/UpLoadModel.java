package com.tarot.sdfnash.tarot.registnew.Model;

import java.io.Serializable;

/**
 * Created by wangxuebing on 16/8/7.
 */
public class UpLoadModel implements Serializable {


    /**
     * photo_url : /Uploads/2016-07-24/5794575208cc6.jpg
     * thumb_photo_url : /Uploads/2016-07-24/s_5794575208cc6.jpg
     */

    private DataBean data;
    /**
     * data : {"photo_url":"/Uploads/2016-07-24/5794575208cc6.jpg","thumb_photo_url":"/Uploads/2016-07-24/s_5794575208cc6.jpg"}
     * code : 0
     * msg :
     */

    private int code;
    private String msg;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        private String photo_url;
        private String thumb_photo_url;

        public String getPhoto_url() {
            return photo_url;
        }

        public void setPhoto_url(String photo_url) {
            this.photo_url = photo_url;
        }

        public String getThumb_photo_url() {
            return thumb_photo_url;
        }

        public void setThumb_photo_url(String thumb_photo_url) {
            this.thumb_photo_url = thumb_photo_url;
        }
    }
}
