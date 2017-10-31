package com.tarot.sdfnash.tarot.registnew.Model;

import java.io.Serializable;

/**
 * Created by sdfnash on 16/9/17.
 */
public class CreateOrderModel implements Serializable {

    /**
     * u_id : 1
     * t_id : 1
     * s_id : 1
     * service : s1
     * amount : 1223
     * status : 0
     * ip : 127.0.0.1
     * status_date : 1472874881
     * create_date : 1472874881
     * channel : wx
     * id : 11
     */

    private Data data;
    /**
     * data : {"u_id":1,"t_id":1,"s_id":1,"service":"s1","amount":1223,"status":0,"ip":"127.0.0.1","status_date":1472874881,"create_date":1472874881,"channel":"wx","id":"11"}
     * code : 0
     * msg : null
     */

    private int code;
    private Object msg;

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

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public static class Data {
        private int u_id;
        private int t_id;
        private int s_id;
        private String service;
        private int amount;
        private int status;
        private String ip;
        private int status_date;
        private int create_date;
        private String channel;
        private String id;

        public int getU_id() {
            return u_id;
        }

        public void setU_id(int u_id) {
            this.u_id = u_id;
        }

        public int getT_id() {
            return t_id;
        }

        public void setT_id(int t_id) {
            this.t_id = t_id;
        }

        public int getS_id() {
            return s_id;
        }

        public void setS_id(int s_id) {
            this.s_id = s_id;
        }

        public String getService() {
            return service;
        }

        public void setService(String service) {
            this.service = service;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public int getStatus_date() {
            return status_date;
        }

        public void setStatus_date(int status_date) {
            this.status_date = status_date;
        }

        public int getCreate_date() {
            return create_date;
        }

        public void setCreate_date(int create_date) {
            this.create_date = create_date;
        }

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
