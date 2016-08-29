package com.tarot.sdfnash.tarot.registnew.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Liuyi on 2016/8/11.
 */
public class TarotListModel implements Serializable{
    private DataBean data;


    private int code;
    private String msg;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class DataBean {

        /**
         * list : [{"id":"3","nickname":"aaa23","photo":"","photo_s":"","about":"aaa","online_status":"1","star":"3.0","yx_accid":"taluoshi_3"},{"id":"2","nickname":"aaa2","photo":"","photo_s":"","about":"aaa","online_status":"0","star":"3.0","yx_accid":"taluoshi_2"},{"id":"4","nickname":"ceshi","photo":"域名/Uploads/admin/file_579d9bc1cd3e2.jpg","photo_s":"域名/Uploads/admin/s_file_579d9bc1cd3e2.jpg","about":"","online_status":"0","star":"3.0","yx_accid":"taluoshi_4"}]
         * count : 3
         * page : 1
         * total : 1
         */

        private int count;
        private int page;
        private int total;
        /**
         * id : 3
         * nickname : aaa23
         * photo :
         * photo_s :
         * about : aaa
         * online_status : 1
         * star : 3.0
         * yx_accid : taluoshi_3
         */

        private java.util.List<Tarot> list;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<Tarot> getList() {
            return list;
        }

        public void setList(List<Tarot> list) {
            this.list = list;
        }

        public static class Tarot {
            private String id;
            private String nickname;
            private String photo;
            private String photo_s;
            private String about;
            private String online_status;
            private String star;
            private String yx_accid;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public String getPhoto_s() {
                return photo_s;
            }

            public void setPhoto_s(String photo_s) {
                this.photo_s = photo_s;
            }

            public String getAbout() {
                return about;
            }

            public void setAbout(String about) {
                this.about = about;
            }

            public String getOnline_status() {
                return online_status;
            }

            public void setOnline_status(String online_status) {
                this.online_status = online_status;
            }

            public String getStar() {
                return star;
            }

            public void setStar(String star) {
                this.star = star;
            }

            public String getYx_accid() {
                return yx_accid;
            }

            public void setYx_accid(String yx_accid) {
                this.yx_accid = yx_accid;
            }
        }
    }
}
