package com.tarot.sdfnash.tarot.registnew.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sdfnash on 16/9/2.
 */
public class OrderListModel implements Serializable {


    /**
     * list : [{"id":"25","service":"s3","amount":0.01,"status_date":"1473090223","status":"0","t_id":"1","is_comment":"0","tls_name":"ceshitaluoshi"},{"id":"24","service":"s3","amount":0.01,"status_date":"1473090223","status":"0","t_id":"1","is_comment":"0","tls_name":"ceshitaluoshi"},{"id":"23","service":"s3","amount":0.01,"status_date":"1473090222","status":"0","t_id":"1","is_comment":"0","tls_name":"ceshitaluoshi"},{"id":"22","service":"s3","amount":0.01,"status_date":"1473090222","status":"0","t_id":"1","is_comment":"0","tls_name":"ceshitaluoshi"},{"id":"21","service":"s3","amount":0.01,"status_date":"1473090222","status":"0","t_id":"1","is_comment":"0","tls_name":"ceshitaluoshi"},{"id":"20","service":"s3","amount":0.01,"status_date":"1473090222","status":"0","t_id":"1","is_comment":"0","tls_name":"ceshitaluoshi"},{"id":"19","service":"s3","amount":0.01,"status_date":"1473090221","status":"0","t_id":"1","is_comment":"0","tls_name":"ceshitaluoshi"},{"id":"18","service":"s3","amount":0.01,"status_date":"1473090220","status":"0","t_id":"1","is_comment":"0","tls_name":"ceshitaluoshi"},{"id":"17","service":"s3","amount":0.01,"status_date":"1473090219","status":"0","t_id":"1","is_comment":"0","tls_name":"ceshitaluoshi"},{"id":"16","service":"s3","amount":0.01,"status_date":"1473090219","status":"0","t_id":"1","is_comment":"0","tls_name":"ceshitaluoshi"}]
     * count : 18
     * page : 1
     * total : 2
     */

    private DataBean data;
    /**
     * data : {"list":[{"id":"25","service":"s3","amount":0.01,"status_date":"1473090223","status":"0","t_id":"1","is_comment":"0","tls_name":"ceshitaluoshi"},{"id":"24","service":"s3","amount":0.01,"status_date":"1473090223","status":"0","t_id":"1","is_comment":"0","tls_name":"ceshitaluoshi"},{"id":"23","service":"s3","amount":0.01,"status_date":"1473090222","status":"0","t_id":"1","is_comment":"0","tls_name":"ceshitaluoshi"},{"id":"22","service":"s3","amount":0.01,"status_date":"1473090222","status":"0","t_id":"1","is_comment":"0","tls_name":"ceshitaluoshi"},{"id":"21","service":"s3","amount":0.01,"status_date":"1473090222","status":"0","t_id":"1","is_comment":"0","tls_name":"ceshitaluoshi"},{"id":"20","service":"s3","amount":0.01,"status_date":"1473090222","status":"0","t_id":"1","is_comment":"0","tls_name":"ceshitaluoshi"},{"id":"19","service":"s3","amount":0.01,"status_date":"1473090221","status":"0","t_id":"1","is_comment":"0","tls_name":"ceshitaluoshi"},{"id":"18","service":"s3","amount":0.01,"status_date":"1473090220","status":"0","t_id":"1","is_comment":"0","tls_name":"ceshitaluoshi"},{"id":"17","service":"s3","amount":0.01,"status_date":"1473090219","status":"0","t_id":"1","is_comment":"0","tls_name":"ceshitaluoshi"},{"id":"16","service":"s3","amount":0.01,"status_date":"1473090219","status":"0","t_id":"1","is_comment":"0","tls_name":"ceshitaluoshi"}],"count":18,"page":1,"total":2}
     * code : 0
     * msg : null
     */

    private int code;
    private Object msg;

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

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public static class DataBean {
        private int count;
        private int page;
        private int total;
        /**
         * id : 25
         * service : s3
         * amount : 0.01
         * status_date : 1473090223
         * status : 0
         * t_id : 1
         * is_comment : 0
         * tls_name : ceshitaluoshi
         */

        private List<ListBean> list;

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

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private String id;
            private String service;
            private double amount;
            private String status_date;
            private String status;
            private String t_id;
            private String is_comment;
            private String tls_name;
            /**
             * create_date : 1473488835
             * u_id : 1
             * user_name : aaaa
             */

            private String create_date;
            private String u_id;
            private String user_name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getService() {
                return service;
            }

            public void setService(String service) {
                this.service = service;
            }

            public double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
                this.amount = amount;
            }

            public String getStatus_date() {
                return status_date;
            }

            public void setStatus_date(String status_date) {
                this.status_date = status_date;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getT_id() {
                return t_id;
            }

            public void setT_id(String t_id) {
                this.t_id = t_id;
            }

            public String getIs_comment() {
                return is_comment;
            }

            public void setIs_comment(String is_comment) {
                this.is_comment = is_comment;
            }

            public String getTls_name() {
                return tls_name;
            }

            public void setTls_name(String tls_name) {
                this.tls_name = tls_name;
            }

            public String getCreate_date() {
                return create_date;
            }

            public void setCreate_date(String create_date) {
                this.create_date = create_date;
            }

            public String getU_id() {
                return u_id;
            }

            public void setU_id(String u_id) {
                this.u_id = u_id;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }
        }
    }
}
