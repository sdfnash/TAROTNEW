package com.tarot.sdfnash.tarot.config;

public class DemoServers {

    //
    // 好友列表信息服务器地址
    //
    private static final String API_SERVER_TEST = "https://apptest.netease.im/api/"; // 测试
    private static final String API_SERVER = "http://tls.hititp.com/"; // 线上

    public static final String apiServer() {
        return ServerConfig.testServer() ? API_SERVER_TEST : API_SERVER;
    }

    public static final String chatRoomAPIServer() {
        return apiServer() + "chatroom/";
    }
}
