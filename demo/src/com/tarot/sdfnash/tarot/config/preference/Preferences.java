package com.tarot.sdfnash.tarot.config.preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.tarot.sdfnash.tarot.DemoCache;

/**
 * Created by hzxuwen on 2015/4/13.
 */
public class Preferences {
    private static final String KEY_USER_ACCOUNT = "account";
    private static final String KEY_USER_TOKEN = "token";
    private static final String KEY_YX_ACCOUNT = "account_yunxin";
    private static final String KEY_YX_TOKEN = "token_yunxin";
    private static final String KEY_USER_ID="uId";
    private static final String KEY_USER_PHOTO="photo";
    private static final String KEY_USER_NICKNAME="nickname";
    public static void saveUserAccount(String account) {
        saveString(KEY_USER_ACCOUNT, account);
    }
    public static void saveUserID(String uId) {
        saveString(KEY_USER_ID, uId);
    }
    public static void saveYXAccount(String account_yx) {
        saveString(KEY_YX_ACCOUNT, account_yx);
    }
    public static String getUserAccount() {
        return getString(KEY_USER_ACCOUNT);
    }//shoujihao
    public static String getUserId() {//use_id
        return getString(KEY_USER_ID);
    }
    public static String getYXAcount() {
        return getString(KEY_YX_ACCOUNT);
    }//yunxin_id

    public static void saveUserToken(String token) {
        saveString(KEY_USER_TOKEN, token);
    }

    public static void saveYXToken(String token) {
        saveString(KEY_YX_TOKEN, token);
    }
    public static String getUserToken() {
        return getString(KEY_USER_TOKEN);
    }//ticket

    public static String getYXToken() {
        return getString(KEY_YX_TOKEN);
    }

    public static void saveUserPhoto(String photo) {
        saveString(KEY_USER_PHOTO, photo);
    }
    public static String getUserUsePhoto() {
        return getString(KEY_USER_PHOTO);
    }

    public static void saveUserNickName(String photo) {
        saveString(KEY_USER_NICKNAME, photo);
    }
    public static String getKeyUserNickname() {
        return getString(KEY_USER_NICKNAME);
    }

    private static void saveString(String key, String value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(key, value);
        editor.commit();
    }

    private static String getString(String key) {
        return getSharedPreferences().getString(key, null);
    }

    static SharedPreferences getSharedPreferences() {
        return DemoCache.getContext().getSharedPreferences("Tarot", Context.MODE_PRIVATE);
    }
}
