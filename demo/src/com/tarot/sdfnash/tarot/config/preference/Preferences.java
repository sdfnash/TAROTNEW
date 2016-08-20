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
    public static void saveUserAccount(String account) {
        saveString(KEY_USER_ACCOUNT, account);
    }

    public static void saveYXAccount(String account_yx) {
        saveString(KEY_YX_ACCOUNT, account_yx);
    }
    public static String getUserAccount() {
        return getString(KEY_USER_ACCOUNT);
    }

    public static String getYXAcount() {
        return getString(KEY_YX_ACCOUNT);
    }

    public static void saveUserToken(String token) {
        saveString(KEY_USER_TOKEN, token);
    }

    public static void saveYXToken(String token) {
        saveString(KEY_YX_TOKEN, token);
    }
    public static String getUserToken() {
        return getString(KEY_USER_TOKEN);
    }

    public static String getYXToken() {
        return getString(KEY_YX_TOKEN);
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
