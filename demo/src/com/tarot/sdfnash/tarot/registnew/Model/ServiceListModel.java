package com.tarot.sdfnash.tarot.registnew.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sdfnash on 16/8/29.
 */
public class ServiceListModel implements Serializable {

    /**
     * tls : {"id":"1","nickname":"ceshitaluoshi","star":"1.77","photo_s":"/Uploads/admin/s_file_579d9d69005ec.jpg","yx_accid":"taluoshi_1"}
     * list : [{"id":"4","name":"ceshi23","description":"121233","price":"31311.00","add_date":"1471916725","modify_date":"1471916725","good_count":"1"},{"id":"3","name":"","description":"1","price":"0.00","add_date":"1471916561","modify_date":"1471916561","good_count":"2"},{"id":"2","name":"s1","description":"adsaaas","price":"11.00","add_date":"111111111","modify_date":"111111","good_count":"0"},{"id":"1","name":"s1","description":"adsaaas","price":"11.00","add_date":"111111","modify_date":"111111","good_count":"0"}]
     * count : 4
     * page : 1
     * total : 1
     */

    private Data data;
    /**
     * data : {"tls":{"id":"1","nickname":"ceshitaluoshi","star":"1.77","photo_s":"/Uploads/admin/s_file_579d9d69005ec.jpg","yx_accid":"taluoshi_1"},"list":[{"id":"4","name":"ceshi23","description":"121233","price":"31311.00","add_date":"1471916725","modify_date":"1471916725","good_count":"1"},{"id":"3","name":"","description":"1","price":"0.00","add_date":"1471916561","modify_date":"1471916561","good_count":"2"},{"id":"2","name":"s1","description":"adsaaas","price":"11.00","add_date":"111111111","modify_date":"111111","good_count":"0"},{"id":"1","name":"s1","description":"adsaaas","price":"11.00","add_date":"111111","modify_date":"111111","good_count":"0"}],"count":4,"page":1,"total":1}
     * code : 0
     * msg :
     */

    private int code;
    private String msg;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
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

    public static class Data {
        /**
         * id : 1
         * nickname : ceshitaluoshi
         * star : 1.77
         * photo_s : /Uploads/admin/s_file_579d9d69005ec.jpg
         * yx_accid : taluoshi_1
         */

        private TarotModel tls;
        private int count;
        private int page;
        private int total;
        /**
         * id : 4
         * name : ceshi23
         * description : 121233
         * price : 31311.00
         * add_date : 1471916725
         * modify_date : 1471916725
         * good_count : 1
         */

        private java.util.List<ServiceModel> list;

        public TarotModel getTls() {
            return tls;
        }

        public void setTls(TarotModel tls) {
            this.tls = tls;
        }

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

        public List<ServiceModel> getList() {
            return list;
        }

        public void setList(List<ServiceModel> list) {
            this.list = list;
        }

        public static class Tls {
            private String id;
            private String nickname;
            private String star;
            private String photo_s;
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

            public String getStar() {
                return star;
            }

            public void setStar(String star) {
                this.star = star;
            }

            public String getPhoto_s() {
                return photo_s;
            }

            public void setPhoto_s(String photo_s) {
                this.photo_s = photo_s;
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
