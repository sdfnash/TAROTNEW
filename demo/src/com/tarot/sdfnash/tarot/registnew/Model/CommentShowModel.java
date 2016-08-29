package com.tarot.sdfnash.tarot.registnew.Model;

import java.io.Serializable;

/**
 * Created by sdfnash on 16/8/19.
 */
public class CommentShowModel  implements Serializable{

    /**
     * id : 9
     * comment : 1232131
     * u_id : 1
     * nickname : aaaa
     * photo_s : /Uploads/2016-07-28/s_579a1dc1b0cf6.jpg
     */

    private String id;
    private String comment;
    private String u_id;
    private String nickname;
    private String photo_s;

    /**
     * add_date : 1470929420
     * modify_date : 1470929421
     * cate : 1
     * can_edit : 0
     */

    private String add_date;
    private String modify_date;
    private String cate;
    private int can_edit;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhoto_s() {
        return photo_s;
    }

    public void setPhoto_s(String photo_s) {
        this.photo_s = photo_s;
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

    public String getCate() {
        return cate;
    }

    public void setCate(String cate) {
        this.cate = cate;
    }

    public int getCan_edit() {
        return can_edit;
    }

    public void setCan_edit(int can_edit) {
        this.can_edit = can_edit;
    }
}
