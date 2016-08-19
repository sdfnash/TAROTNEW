package com.tarot.sdfnash.tarot.registnew.Model;

import java.io.Serializable;

/**
 * Created by wangxuebing on 16/8/7.
 */
public class UpdateYXUerInfoModel implements Serializable {


    /**
     * data :
     * code : 0
     * msg :
     */

    private String data;
    private int code;
    private String msg;

    public String getData() {
        return data;
    }

    public void setData(String data) {
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
}
