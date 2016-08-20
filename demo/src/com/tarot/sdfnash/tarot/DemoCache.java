package com.tarot.sdfnash.tarot;

import android.content.Context;

import com.netease.sdfnash.uikit.NimUIKit;
import com.netease.nimlib.sdk.StatusBarNotificationConfig;

/**
 * Created by jezhee on 2/20/15.
 */
public class DemoCache {

    private static Context context;

    private static String account;

    private static StatusBarNotificationConfig notificationConfig;

    public static void clear() {
        account = null;
    }

    public static String getAccount() {
        return account;
    }

    public static void setAccount(String account_tarot, String account_yunxin) {
        DemoCache.account = account_tarot;
        NimUIKit.setAccount(account_yunxin);
    }

    public static void setNotificationConfig(StatusBarNotificationConfig notificationConfig) {
        DemoCache.notificationConfig = notificationConfig;
    }

    public static StatusBarNotificationConfig getNotificationConfig() {
        return notificationConfig;
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        DemoCache.context = context.getApplicationContext();
    }
}
