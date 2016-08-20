package com.tarot.sdfnash.tarot.registnew.Model;

import java.io.Serializable;

/**
 * Created by wangxuebing on 16/8/7.
 */
public class RepassModel implements Serializable {


    /**
     * id : 10
     * account : 18646000881
     * password : 173326cbef2fe83bc760b1de84884961
     * nickname :
     * photo :
     * photo_s :
     * login_ip : 127.0.0.1
     * reg_date : 1469255900
     * login_date : 1469255900
     * modify_date : 1469255900
     * is_del : 0
     * yx_token : 1fff1ab3da43076a76a9ea38a340e6fa
     * yx_accid : tls_usr_10
     * yx_name :
     * yx_date : 1469970549
     */

    private DataBean data;
    /**
     * data : {"id":"10","account":"18646000881","password":"173326cbef2fe83bc760b1de84884961","nickname":"","photo":"","photo_s":"","login_ip":"127.0.0.1","reg_date":"1469255900","login_date":"1469255900","modify_date":"1469255900","is_del":"0","yx_token":"1fff1ab3da43076a76a9ea38a340e6fa","yx_accid":"tls_usr_10","yx_name":"","yx_date":"1469970549"}
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
        private String account;
        private String password;
        private String nickname;
        private String photo;
        private String photo_s;
        private String login_ip;
        private String reg_date;
        private String login_date;
        private String modify_date;
        private String is_del;
        private String yx_token;
        private String yx_accid;
        private String yx_name;
        private String yx_date;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
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

        public String getLogin_ip() {
            return login_ip;
        }

        public void setLogin_ip(String login_ip) {
            this.login_ip = login_ip;
        }

        public String getReg_date() {
            return reg_date;
        }

        public void setReg_date(String reg_date) {
            this.reg_date = reg_date;
        }

        public String getLogin_date() {
            return login_date;
        }

        public void setLogin_date(String login_date) {
            this.login_date = login_date;
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

        public String getYx_token() {
            return yx_token;
        }

        public void setYx_token(String yx_token) {
            this.yx_token = yx_token;
        }

        public String getYx_accid() {
            return yx_accid;
        }

        public void setYx_accid(String yx_accid) {
            this.yx_accid = yx_accid;
        }

        public String getYx_name() {
            return yx_name;
        }

        public void setYx_name(String yx_name) {
            this.yx_name = yx_name;
        }

        public String getYx_date() {
            return yx_date;
        }

        public void setYx_date(String yx_date) {
            this.yx_date = yx_date;
        }
    }
}
