package com.tarot.sdfnash.tarot.registnew.Model;

import java.io.Serializable;

/**
 * Created by sdfnash on 16/9/19.
 */
public class GetStatusModel implements Serializable {

    /**
     * online_status : 0
     */

    private Data data;
    /**
     * data : {"online_status":"0"}
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
        private String online_status;

        public String getOnline_status() {
            return online_status;
        }

        public void setOnline_status(String online_status) {
            this.online_status = online_status;
        }
    }
}
