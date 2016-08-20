package com.tarot.sdfnash.tarot.registnew.Model;

import java.io.Serializable;

/**
 * Created by Liuyi on 2016/8/18.
 */
public class CommentDetailModel implements Serializable {

    /**
     * id : 2
     * o_id : 1
     * u_id : 1
     * t_id : 1
     * s_id : 1
     * want : 0
     * comment : 1232131
     * star1 : 1
     * star2 : 2
     * star3 : 1
     * star4 : 0
     * star_avg : 1.3
     * add_date : 1470928193
     * modify_date : 1470928193
     * is_del : 0
     */

    private DataBean data;
    /**
     * data : {"id":"2","o_id":"1","u_id":"1","t_id":"1","s_id":"1","want":"0","comment":"1232131","star1":"1","star2":"2","star3":"1","star4":"0","star_avg":"1.3","add_date":"1470928193","modify_date":"1470928193","is_del":"0"}
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
        private String id;
        private String o_id;
        private String u_id;
        private String t_id;
        private String s_id;
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
}
