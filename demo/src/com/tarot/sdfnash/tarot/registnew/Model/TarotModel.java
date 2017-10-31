package com.tarot.sdfnash.tarot.registnew.Model;

import java.io.Serializable;

/**
 * Created by sdfnash on 16/8/29.
 */
public class TarotModel implements Serializable {
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
