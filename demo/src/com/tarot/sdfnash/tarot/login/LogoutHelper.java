package com.tarot.sdfnash.tarot.login;

import com.tarot.sdfnash.tarot.DemoCache;
import com.tarot.sdfnash.tarot.chatroom.helper.ChatRoomHelper;
import com.netease.sdfnash.uikit.LoginSyncDataStatusObserver;
import com.netease.sdfnash.uikit.NimUIKit;

/**
 * 注销帮助类
 * Created by huangjun on 2015/10/8.
 */
public class LogoutHelper {
    public static void logout() {
        // 清理缓存&注销监听&清除状态
        NimUIKit.clearCache();
        ChatRoomHelper.logout();
        DemoCache.clear();
        LoginSyncDataStatusObserver.getInstance().reset();
    }
}
