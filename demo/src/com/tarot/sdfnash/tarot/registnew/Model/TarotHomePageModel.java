package com.tarot.sdfnash.tarot.registnew.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sdfnash on 16/9/2.
 */
public class TarotHomePageModel implements Serializable {

    /**
     * info : {"haoping_count":"14","zhongping_count":"2","chaping_count":"0","total_count":"16","haopinglv":87.5,"avg_star1":1.27,"avg_star2":2.27,"avg_star3":1.13,"avg_star4":2.67,"avg_star":1.77,"tls_id":"1","tls_nickname":"ceshitaluoshi","tls_accid":"taluoshi_1","tls_photo":"/Uploads/admin/s_file_579d9d69005ec.jpg"}
     * commentlist : [{"id":"18","o_id":"2","u_id":"1","t_id":"1","s_id":"3","cate":"1","want":"0","comment":"1232131","star1":"1","star2":"2","star3":"1","star4":"3","star_avg":"1.80","add_date":"1470929420","modify_date":"1470929421","is_del":"0"},{"id":"17","o_id":"2","u_id":"1","t_id":"1","s_id":"3","cate":"0","want":"0","comment":"1232131","star1":"1","star2":"2","star3":"1","star4":"3","star_avg":"1.80","add_date":"1470929323","modify_date":"1470929323","is_del":"0"},{"id":"16","o_id":"2","u_id":"2","t_id":"1","s_id":"3","cate":"0","want":"0","comment":"1232131","star1":"1","star2":"2","star3":"1","star4":"3","star_avg":"1.80","add_date":"1470929289","modify_date":"1470929289","is_del":"0"},{"id":"15","o_id":"2","u_id":"1","t_id":"1","s_id":"3","cate":"0","want":"0","comment":"1232131","star1":"1","star2":"2","star3":"1","star4":"3","star_avg":"1.80","add_date":"1470928438","modify_date":"1470928438","is_del":"0"}]
     * servicelist : [{"id":"4","name":"ceshi23","description":"121233","price":"31311.00","add_date":"1471916725","modify_date":"1471916725","good_count":"1"},{"id":"3","name":"","description":"1","price":"0.00","add_date":"1471916561","modify_date":"1471916561","good_count":"2"},{"id":"2","name":"s1","description":"adsaaas","price":"11.00","add_date":"111111111","modify_date":"111111","good_count":"0"},{"id":"1","name":"s1","description":"adsaaas","price":"11.00","add_date":"111111","modify_date":"111111","good_count":"0"}]
     */

    private Data data;
    /**
     * data : {"info":{"haoping_count":"14","zhongping_count":"2","chaping_count":"0","total_count":"16","haopinglv":87.5,"avg_star1":1.27,"avg_star2":2.27,"avg_star3":1.13,"avg_star4":2.67,"avg_star":1.77,"tls_id":"1","tls_nickname":"ceshitaluoshi","tls_accid":"taluoshi_1","tls_photo":"/Uploads/admin/s_file_579d9d69005ec.jpg"},"commentlist":[{"id":"18","o_id":"2","u_id":"1","t_id":"1","s_id":"3","cate":"1","want":"0","comment":"1232131","star1":"1","star2":"2","star3":"1","star4":"3","star_avg":"1.80","add_date":"1470929420","modify_date":"1470929421","is_del":"0"},{"id":"17","o_id":"2","u_id":"1","t_id":"1","s_id":"3","cate":"0","want":"0","comment":"1232131","star1":"1","star2":"2","star3":"1","star4":"3","star_avg":"1.80","add_date":"1470929323","modify_date":"1470929323","is_del":"0"},{"id":"16","o_id":"2","u_id":"2","t_id":"1","s_id":"3","cate":"0","want":"0","comment":"1232131","star1":"1","star2":"2","star3":"1","star4":"3","star_avg":"1.80","add_date":"1470929289","modify_date":"1470929289","is_del":"0"},{"id":"15","o_id":"2","u_id":"1","t_id":"1","s_id":"3","cate":"0","want":"0","comment":"1232131","star1":"1","star2":"2","star3":"1","star4":"3","star_avg":"1.80","add_date":"1470928438","modify_date":"1470928438","is_del":"0"}],"servicelist":[{"id":"4","name":"ceshi23","description":"121233","price":"31311.00","add_date":"1471916725","modify_date":"1471916725","good_count":"1"},{"id":"3","name":"","description":"1","price":"0.00","add_date":"1471916561","modify_date":"1471916561","good_count":"2"},{"id":"2","name":"s1","description":"adsaaas","price":"11.00","add_date":"111111111","modify_date":"111111","good_count":"0"},{"id":"1","name":"s1","description":"adsaaas","price":"11.00","add_date":"111111","modify_date":"111111","good_count":"0"}]}
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
         * haoping_count : 14
         * zhongping_count : 2
         * chaping_count : 0
         * total_count : 16
         * haopinglv : 87.5
         * avg_star1 : 1.27
         * avg_star2 : 2.27
         * avg_star3 : 1.13
         * avg_star4 : 2.67
         * avg_star : 1.77
         * tls_id : 1
         * tls_nickname : ceshitaluoshi
         * tls_accid : taluoshi_1
         * tls_photo : /Uploads/admin/s_file_579d9d69005ec.jpg
         */

        private Info info;
        /**
         * id : 18
         * o_id : 2
         * u_id : 1
         * t_id : 1
         * s_id : 3
         * cate : 1
         * want : 0
         * comment : 1232131
         * star1 : 1
         * star2 : 2
         * star3 : 1
         * star4 : 3
         * star_avg : 1.80
         * add_date : 1470929420
         * modify_date : 1470929421
         * is_del : 0
         */

        private List<Commentlist> commentlist;
        /**
         * id : 4
         * name : ceshi23
         * description : 121233
         * price : 31311.00
         * add_date : 1471916725
         * modify_date : 1471916725
         * good_count : 1
         */

        private List<Servicelist> servicelist;

        public Info getInfo() {
            return info;
        }

        public void setInfo(Info info) {
            this.info = info;
        }

        public List<Commentlist> getCommentlist() {
            return commentlist;
        }

        public void setCommentlist(List<Commentlist> commentlist) {
            this.commentlist = commentlist;
        }

        public List<Servicelist> getServicelist() {
            return servicelist;
        }

        public void setServicelist(List<Servicelist> servicelist) {
            this.servicelist = servicelist;
        }

        public static class Info {
            private String haoping_count;
            private String zhongping_count;
            private String chaping_count;
            private String total_count;
            private String haopinglv;
            private String avg_star1;
            private String avg_star2;
            private String avg_star3;
            private String avg_star4;
            private String avg_star;
            private String tls_id;
            private String tls_nickname;
            private String tls_accid;
            private String tls_photo;

            /**
             * fans : 1
             */

            private String fans;
            /**
             * total_sold : 4
             */

            private String total_sold;

            public String getHaoping_count() {
                return haoping_count;
            }

            public void setHaoping_count(String haoping_count) {
                this.haoping_count = haoping_count;
            }

            public String getZhongping_count() {
                return zhongping_count;
            }

            public void setZhongping_count(String zhongping_count) {
                this.zhongping_count = zhongping_count;
            }

            public String getChaping_count() {
                return chaping_count;
            }

            public void setChaping_count(String chaping_count) {
                this.chaping_count = chaping_count;
            }

            public String getTotal_count() {
                return total_count;
            }

            public void setTotal_count(String total_count) {
                this.total_count = total_count;
            }

            public String getHaopinglv() {
                return haopinglv;
            }

            public void setHaopinglv(String haopinglv) {
                this.haopinglv = haopinglv;
            }

            public String getAvg_star1() {
                return avg_star1;
            }

            public void setAvg_star1(String avg_star1) {
                this.avg_star1 = avg_star1;
            }

            public String getAvg_star2() {
                return avg_star2;
            }

            public void setAvg_star2(String avg_star2) {
                this.avg_star2 = avg_star2;
            }

            public String getAvg_star3() {
                return avg_star3;
            }

            public void setAvg_star3(String avg_star3) {
                this.avg_star3 = avg_star3;
            }

            public String getAvg_star4() {
                return avg_star4;
            }

            public void setAvg_star4(String avg_star4) {
                this.avg_star4 = avg_star4;
            }

            public String getAvg_star() {
                return avg_star;
            }

            public void setAvg_star(String avg_star) {
                this.avg_star = avg_star;
            }

            public String getTls_id() {
                return tls_id;
            }

            public void setTls_id(String tls_id) {
                this.tls_id = tls_id;
            }

            public String getTls_nickname() {
                return tls_nickname;
            }

            public void setTls_nickname(String tls_nickname) {
                this.tls_nickname = tls_nickname;
            }

            public String getTls_accid() {
                return tls_accid;
            }

            public void setTls_accid(String tls_accid) {
                this.tls_accid = tls_accid;
            }

            public String getTls_photo() {
                return tls_photo;
            }

            public void setTls_photo(String tls_photo) {
                this.tls_photo = tls_photo;
            }

            public String getFans() {
                return fans;
            }

            public void setFans(String fans) {
                this.fans = fans;
            }

            public String getTotal_sold() {
                return total_sold;
            }

            public void setTotal_sold(String total_sold) {
                this.total_sold = total_sold;
            }
        }

        public static class Commentlist {
            private String id;
            private String o_id;
            private String u_id;
            private String t_id;
            private String s_id;
            private String cate;
            private String want;
            private String comment;
            private String star1;
            private String star2;
            private String star3;
            private String star4;
            private String star_avg;
            private String add_date;
            private String modify_date;
            private String is_del;
            private String nickname;
            private String photo;

            public String getPhoto() {

                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getO_id() {
                return o_id;
            }

            public void setO_id(String o_id) {
                this.o_id = o_id;
            }

            public String getU_id() {
                return u_id;
            }

            public void setU_id(String u_id) {
                this.u_id = u_id;
            }

            public String getT_id() {
                return t_id;
            }

            public void setT_id(String t_id) {
                this.t_id = t_id;
            }

            public String getS_id() {
                return s_id;
            }

            public void setS_id(String s_id) {
                this.s_id = s_id;
            }

            public String getCate() {
                return cate;
            }

            public void setCate(String cate) {
                this.cate = cate;
            }

            public String getWant() {
                return want;
            }

            public void setWant(String want) {
                this.want = want;
            }

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public String getStar1() {
                return star1;
            }

            public void setStar1(String star1) {
                this.star1 = star1;
            }

            public String getStar2() {
                return star2;
            }

            public void setStar2(String star2) {
                this.star2 = star2;
            }

            public String getStar3() {
                return star3;
            }

            public void setStar3(String star3) {
                this.star3 = star3;
            }

            public String getStar4() {
                return star4;
            }

            public void setStar4(String star4) {
                this.star4 = star4;
            }

            public String getStar_avg() {
                return star_avg;
            }

            public void setStar_avg(String star_avg) {
                this.star_avg = star_avg;
            }

            public String getAdd_date() {
                return add_date;
            }

            public void setAdd_date(String add_date) {
                this.add_date = add_date;
            }

            public String getModify_date() {
                return modify_date;
            }

            public void setModify_date(String modify_date) {
                this.modify_date = modify_date;
            }

            public String getIs_del() {
                return is_del;
            }

            public void setIs_del(String is_del) {
                this.is_del = is_del;
            }
        }

        public static class Servicelist {
            private String id;
            private String name;
            private String description;
            private String price;
            private String add_date;
            private String modify_date;
            private String good_count;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getAdd_date() {
                return add_date;
            }

            public void setAdd_date(String add_date) {
                this.add_date = add_date;
            }

            public String getModify_date() {
                return modify_date;
            }

            public void setModify_date(String modify_date) {
                this.modify_date = modify_date;
            }

            public String getGood_count() {
                return good_count;
            }

            public void setGood_count(String good_count) {
                this.good_count = good_count;
            }
        }
    }
}
