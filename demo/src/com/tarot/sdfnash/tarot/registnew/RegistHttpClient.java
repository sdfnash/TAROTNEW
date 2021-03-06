package com.tarot.sdfnash.tarot.registnew;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.netease.sdfnash.uikit.common.util.log.LogUtil;
import com.netease.sdfnash.uikit.common.util.string.MD5;
import com.tarot.sdfnash.tarot.DemoCache;
import com.tarot.sdfnash.tarot.common.http.NimHttpClient;
import com.tarot.sdfnash.tarot.config.DemoServers;
import com.tarot.sdfnash.tarot.registnew.Model.CommentDetailModel;
import com.tarot.sdfnash.tarot.registnew.Model.CommentListModel;
import com.tarot.sdfnash.tarot.registnew.Model.CreateOrderModel;
import com.tarot.sdfnash.tarot.registnew.Model.GetStatusModel;
import com.tarot.sdfnash.tarot.registnew.Model.LoginModel;
import com.tarot.sdfnash.tarot.registnew.Model.OrderListModel;
import com.tarot.sdfnash.tarot.registnew.Model.RegistModel;
import com.tarot.sdfnash.tarot.registnew.Model.ServiceListModel;
import com.tarot.sdfnash.tarot.registnew.Model.TarotHomePageModel;
import com.tarot.sdfnash.tarot.registnew.Model.TarotListModel;
import com.tarot.sdfnash.tarot.registnew.Model.UpLoadModel;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Liuyi on 2016/8/4.
 */
public class RegistHttpClient {
    private static final String TAG = "RegistHttpClient";

    // code
    private static final int RESULT_CODE_SUCCESS = 0;

    // api
    private static final String API_NAME_REGISTER = "/domain/user/reg";

    // header
    private static final String HEADER_KEY_APP_KEY = "AppKey";
    private static final String HEADER_CONTENT_TYPE = "Content-Type";
    private static final String HEADER_USER_AGENT = "User-Agent";

    // request
    private static final String REQUEST_PHONE = "phone";
    private static final String REQUEST_CODE = "code";
    private static final String REQUEST_PASSWORD = "password";
    private static final String REQUEST_TOKEN = "token";

    // result
    private static final String RESULT_KEY_RES = "code";
    private static final String RESULT_KEY_ERROR_MSG = "msg";

    private static final String SERVER_URL = "https://api.netease.im/sms/sendtemplate.action";//请求的URL
    private static final String APP_KEY = "b674a21c3b971381d66e7f534b802057";//账号
    private static final String APP_SECRET = "8ca1180d272c";//密码
    private static final String MOULD_ID = "填入设置的模板ID";//模板ID
    private static final String NONCE = "123456";

    // 接口1
    public interface SendSmsHttpCallback<T> {
        void onSuccess(T t);

        void onFailed(int code, String errorMsg);
    }

    // 接口2
    public interface LoginHttpCallback<T> {
        void onSuccess(T t);

        void onFailed(int code, String errorMsg);
    }

    // 接口3
    public interface RegistHttpCallback<T> {
        void onSuccess(T t);

        void onFailed(int code, String errorMsg);
    }


    //接口4
    public interface SaveInfoHttpCallback<T> {
        void onSuccess(T t);

        void onFailed(int code, String errorMsg);
    }


    //接口5
    public interface RepassHttpCallback<T> {
        void onSuccess(T t);

        void onFailed(int code, String errorMsg);
    }

    //接口6
    public interface UpLoadHttpCallback<T> {
        void onSuccess(T t);

        void onFailed(int code, String errorMsg);
    }

    //接口7
    public interface UpdateYXUerInfoHttpCallback<T> {
        void onSuccess(T t);

        void onFailed(int code, String errorMsg);
    }

    //接口8
    public interface TarotListHttpCallBack<T> {
        void onSuccess(T t);

        void onFailed(int code, String errorMsg);
    }


    //接口9
    public interface ClistHttpCallBack<T> {
        void onSuccess(T t);

        void onFailed(int code, String errorMsg);
    }

    //接口10
    public interface AddHttpCallBack<T> {
        void onSuccess(T t);

        void onFailed(int code, String errorMsg);
    }

    //接口11
    public interface DeleteHttpCallBack<T> {
        void onSuccess(T t);

        void onFailed(int code, String errorMsg);
    }

    //接口12
    public interface EditHttpCallBack<T> {
        void onSuccess(T t);

        void onFailed(int code, String errorMsg);
    }

    //接口13
    public interface DetialHttpCallBack<T> {
        void onSuccess(T t);

        void onFailed(int code, String errorMsg);
    }

    public interface MyClistHttpCallBack<T> {
        void onSuccess(T t);

        void onFailed(int code, String errorMsg);
    }

    //接口14
    public interface MakeorderHttpCallBack<T> {
        void onSuccess(T t);

        void onFailed(int code, String errorMsg);
    }

    //接口15
    public interface TarotCollectionListHttpCallBack<T> {
        void onSuccess(T t);

        void onFailed(int code, String errorMsg);
    }

    //接口16
    public interface SetTarotCollectionHttpCallBack<T> {
        void onSuccess(T t);

        void onFailed(int code, String errorMsg);
    }

    //接口17
    public interface GetTarotServiceListHttpCallBack<T> {
        void onSuccess(T t);

        void onFailed(int code, String errorMsg);
    }

    public interface GetTarotHomePageHttpCallBack<T> {
        void onSuccess(T t);

        void onFailed(int code, String errorMsg);
    }

    public interface OrderListHttpCallBack<T> {
        void onSuccess(T t);

        void onFailed(int code, String errorMsg);
    }

    public interface OrderPayHttpCallBack<T> {
        void onSuccess(T t);

        void onFailed(int code, String errorMsg);
    }

    public interface CreateOrderHttpCallBack<T> {
        void onSuccess(T t);

        void onFailed(int code, String errorMsg);
    }
    public interface SetStatusHttpCallBack<T> {
        void onSuccess(T t);

        void onFailed(int code, String errorMsg);
    }
    public interface GetStatusHttpCallBack<T> {
        void onSuccess(T t);

        void onFailed(int code, String errorMsg);
    }
    private static RegistHttpClient instance;

    public static synchronized RegistHttpClient getInstance() {
        if (instance == null) {
            instance = new RegistHttpClient();
        }

        return instance;
    }

    private RegistHttpClient() {
        NimHttpClient.getInstance().init();
    }


    /**
     * 向应用服务器创建账号（注册账号）
     * 由应用服务器调用WEB SDK接口将新注册的用户数据同步到云信服务器
     */

    //接口1 获取验证码
    public void sendSmsCode(String phone, final SendSmsHttpCallback<Void> callback) {
        String url = DemoServers.apiServer() + "yunxin/sendSMS";
        String token = MD5.getStringMD5(phone + "0djeo2xww23bca1sd311971381d631gd");


        Map<String, String> headers = new HashMap<>(1);
        headers.put("phone", phone);
        headers.put("token", token);

        StringBuilder body = new StringBuilder();
        body.append("phone").append("=").append(phone).append("&")
                .append("token").append("=").append(token);

        String bodyString = body.toString();

        NimHttpClient.getInstance().execute(url, headers, bodyString, false, new NimHttpClient.NimHttpCallback() {
            @Override
            public void onResponse(String response, int code, String errorMsg) {
                if (code != 0) {
                    LogUtil.e(TAG, "sendSms failed : code = " + code + ", errorMsg = " + errorMsg);
                    if (callback != null) {
                        callback.onFailed(code, errorMsg);
                    }
                    return;
                }

                try {
                    JSONObject resObj = JSONObject.parseObject(response);
                    int resCode = resObj.getIntValue(RESULT_KEY_RES);
                    if (resCode == RESULT_CODE_SUCCESS) {
                        callback.onSuccess(null);
                    } else {
                        String error = resObj.getString(RESULT_KEY_ERROR_MSG);
                        callback.onFailed(resCode, error);
                    }
                } catch (JSONException e) {
                    callback.onFailed(-1, e.getMessage());
                }
            }
        });
    }


    // 接口2 登陆
    public void loginCode(String phone, String password, final LoginHttpCallback<LoginModel.DataBean> callback) {
        String url = DemoServers.apiServer() + "taluoshi/login";


        Map<String, String> headers = new HashMap<>(1);


        StringBuilder body = new StringBuilder();
        body.append("phone").append("=").append(phone).append("&")
                .append("password").append("=").append(password);

        String bodyString = body.toString();

        NimHttpClient.getInstance().execute(url, headers, bodyString, false, new NimHttpClient.NimHttpCallback() {
            @Override
            public void onResponse(String response, int code, String errorMsg) {
                if (code != 0) {
                    LogUtil.e(TAG, "login failed : code = " + code + ", errorMsg = " + errorMsg);
                    if (callback != null) {
                        callback.onFailed(code, errorMsg);
                    }
                    return;
                }

                try {
                    JSONObject resObj = JSONObject.parseObject(response);
                    int resCode = resObj.getIntValue(RESULT_KEY_RES);
                    if (resCode == RESULT_CODE_SUCCESS) {
                        LoginModel model = new LoginModel();
                        model.setData(resObj.getObject("data", LoginModel.DataBean.class));
                        model.setMsg(resObj.getString("msg"));
                        model.setCode(resCode);
                        callback.onSuccess(model.getData());
                    } else {
                        String error = resObj.getString(RESULT_KEY_ERROR_MSG);
                        callback.onFailed(resCode, error);
                    }
                } catch (JSONException e) {
                    callback.onFailed(-1, e.getMessage());
                }
            }
        });
    }


    //接口3
    public void register(String phone, String code, String password, final RegistHttpCallback<RegistModel.DataBean> callback) {
        String url = DemoServers.apiServer() + "user/reg";
        password = MD5.getStringMD5(password);
        String token = MD5.getStringMD5(phone + code + "0djeo2xww23bca1sd311971381d631gd");


        Map<String, String> headers = new HashMap<>(1);


        StringBuilder body = new StringBuilder();
        body.append("phone").append("=").append(phone).append("&")
                .append("code").append("=").append(code).append("&")
                .append("password").append("=").append(password).append("&")
                .append("token").append("=").append(token);
        String bodyString = body.toString();

        NimHttpClient.getInstance().execute(url, headers, bodyString, false, new NimHttpClient.NimHttpCallback() {
            @Override
            public void onResponse(String response, int code, String errorMsg) {
                if (code != 0) {
                    LogUtil.e(TAG, "register failed : code = " + code + ", errorMsg = " + errorMsg);
                    if (callback != null) {
                        callback.onFailed(code, errorMsg);
                    }
                    return;
                }

                try {
                    JSONObject resObj = JSONObject.parseObject(response);
                    int resCode = resObj.getIntValue(RESULT_KEY_RES);
                    if (resCode == RESULT_CODE_SUCCESS) {
                        RegistModel model = new RegistModel();
                        model.setData(resObj.getObject("data", RegistModel.DataBean.class));
                        model.setMsg(resObj.getString("msg"));
                        model.setStatus(resCode);
                        callback.onSuccess(model.getData());
                    } else {
                        String error = resObj.getString(RESULT_KEY_ERROR_MSG);
                        callback.onFailed(resCode, error);
                    }
                } catch (JSONException e) {
                    callback.onFailed(-1, e.getMessage());
                }
            }
        });
    }

    //接口4
    public void saveinfoCode(String uid, String ticket, String nickname, String photo, String photo_s, final SaveInfoHttpCallback<Void> callback) {
        String url = DemoServers.apiServer() + "taluoshi/saveinfo";


        Map<String, String> headers = new HashMap<>(1);
        StringBuilder body = new StringBuilder();
//if(!TextUtils.isEmpty(nickname)){
//    body.append("uid").append("=").append(uid).append("&")
//            .append("ticket").append("=").append(ticket).append("&")
//            .append("nickname").append("=").append(nickname);
//}else{
//    body.append("uid").append("=").append(uid).append("&")
//            .append("ticket").append("=").append(ticket).append("&")
//            .append("photo").append("=").append(photo).append("&")
//            .append("photo_s").append("=").append(photo_s);
//}

        body.append("tid").append("=").append(uid).append("&")
                .append("ticket").append("=").append(ticket).append("&")
                .append("nickname").append("=").append(nickname).append("&")
                .append("photo").append("=").append(photo).append("&")
                .append("photo_s").append("=").append(photo_s);

        String bodyString = body.toString();

        NimHttpClient.getInstance().execute(url, headers, bodyString, false, new NimHttpClient.NimHttpCallback() {
            @Override
            public void onResponse(String response, int code, String errorMsg) {
                if (code != 0) {
                    LogUtil.e(TAG, "saveinfo failed : code = " + code + ", errorMsg = " + errorMsg);
                    if (callback != null) {
                        callback.onFailed(code, errorMsg);
                    }
                    return;
                }

                try {
                    JSONObject resObj = JSONObject.parseObject(response);
                    int resCode = resObj.getIntValue(RESULT_KEY_RES);
                    if (resCode == RESULT_CODE_SUCCESS) {
                        callback.onSuccess(null);
                    } else {
                        String error = resObj.getString(RESULT_KEY_ERROR_MSG);
                        callback.onFailed(resCode, error);
                    }
                } catch (JSONException e) {
                    callback.onFailed(-1, e.getMessage());
                }
            }
        });
    }

    //接口5
    public void repassCode(String phone, String code, String password, final RepassHttpCallback<LoginModel.DataBean> callback) {
        String url = DemoServers.apiServer() + "taluoshi/repassword";
        password = MD5.getStringMD5(password);
        String token = MD5.getStringMD5(phone + code + "0djeo2xww23bca1sd311971381d631gd");

        Map<String, String> headers = new HashMap<>(1);


        StringBuilder body = new StringBuilder();
        body.append("phone").append("=").append(phone).append("&")
                .append("code").append("=").append(code).append("&")
                .append("password").append("=").append(password).append("&")
                .append("token").append("=").append(token);

        String bodyString = body.toString();

        NimHttpClient.getInstance().execute(url, headers, bodyString, false, new NimHttpClient.NimHttpCallback() {
            @Override
            public void onResponse(String response, int code, String errorMsg) {
                if (code != 0) {
                    LogUtil.e(TAG, "repass failed : code = " + code + ", errorMsg = " + errorMsg);
                    if (callback != null) {
                        callback.onFailed(code, errorMsg);
                    }
                    return;
                }

                try {
                    JSONObject resObj = JSONObject.parseObject(response);
                    int resCode = resObj.getIntValue(RESULT_KEY_RES);
                    if (resCode == RESULT_CODE_SUCCESS) {
                        LoginModel model = new LoginModel();
                        model.setData(resObj.getObject("data", LoginModel.DataBean.class));
                        model.setMsg(resObj.getString("msg"));
                        model.setCode(resCode);
                        callback.onSuccess(model.getData());
                    } else {
                        String error = resObj.getString(RESULT_KEY_ERROR_MSG);
                        callback.onFailed(resCode, error);
                    }
                } catch (JSONException e) {
                    callback.onFailed(-1, e.getMessage());
                }
            }
        });
    }

    //接口6
    public void upLoadCode(String uid, String ticket, String photo, File file, final UpLoadHttpCallback<UpLoadModel.DataBean> callback) {
        String url = DemoServers.apiServer() + "user/upload";


        Map<String, String> headers = new HashMap<>(1);


        StringBuilder body = new StringBuilder();
        body.append("tid").append("=").append(uid).append("&")
                .append("ticket").append("=").append(ticket);

        String bodyString = body.toString();

        NimHttpClient.getInstance().execute(url, headers, bodyString, true, true, file, new NimHttpClient.NimHttpCallback() {
            @Override
            public void onResponse(String response, int code, String errorMsg) {
                if (code != 0) {
                    LogUtil.e(TAG, "upload failed : code = " + code + ", errorMsg = " + errorMsg);
                    if (callback != null) {
                        callback.onFailed(code, errorMsg);
                    }
                    return;
                }

                try {
                    JSONObject resObj = JSONObject.parseObject(response);
                    int resCode = resObj.getIntValue(RESULT_KEY_RES);
                    if (resCode == RESULT_CODE_SUCCESS) {
                        UpLoadModel model = new UpLoadModel();
                        model.setData(resObj.getObject("data", UpLoadModel.DataBean.class));
                        model.setMsg(resObj.getString("msg"));
                        model.setCode(resCode);
                        callback.onSuccess(model.getData());
                    } else {
                        String error = resObj.getString(RESULT_KEY_ERROR_MSG);
                        callback.onFailed(resCode, error);
                    }
                } catch (JSONException e) {
                    callback.onFailed(-1, e.getMessage());
                }
            }
        });
    }

    //接口7
    public void updateYXUerInfoCode(String uid, String ticket, final UpdateYXUerInfoHttpCallback<Void> callback) {
        String url = DemoServers.apiServer() + "taluoshi/updateTLSUserInfo";


        Map<String, String> headers = new HashMap<>(1);


        StringBuilder body = new StringBuilder();
        body.append("tid").append("=").append(uid).append("&")
                .append("ticket").append("=").append(ticket);

        String bodyString = body.toString();

        NimHttpClient.getInstance().execute(url, headers, bodyString, false, new NimHttpClient.NimHttpCallback() {
            @Override
            public void onResponse(String response, int code, String errorMsg) {
                if (code != 0) {
                    LogUtil.e(TAG, "updateYXUserInfo failed : code = " + code + ", errorMsg = " + errorMsg);
                    if (callback != null) {
                        callback.onFailed(code, errorMsg);
                    }
                    return;
                }

                try {
                    JSONObject resObj = JSONObject.parseObject(response);
                    int resCode = resObj.getIntValue(RESULT_KEY_RES);
                    if (resCode == RESULT_CODE_SUCCESS) {
                        callback.onSuccess(null);
                    } else {
                        String error = resObj.getString(RESULT_KEY_ERROR_MSG);
                        callback.onFailed(resCode, error);
                    }
                } catch (JSONException e) {
                    callback.onFailed(-1, e.getMessage());
                }
            }
        });
    }

    //接口8 获取列表
    public void getList(int page, int num, final TarotListHttpCallBack<TarotListModel.DataBean> callback) {
        String url = DemoServers.apiServer() + "taluoshi/tlslist";


        Map<String, String> headers = new HashMap<>(1);


        StringBuilder body = new StringBuilder();
        body.append("page").append("=").append(page).append("&")
                .append("num").append("=").append(num);

        String bodyString = body.toString();

        NimHttpClient.getInstance().execute(url, headers, bodyString, false, new NimHttpClient.NimHttpCallback() {
            @Override
            public void onResponse(String response, int code, String errorMsg) {
                if (code != 0) {
                    LogUtil.e(TAG, "updateYXUserInfo failed : code = " + code + ", errorMsg = " + errorMsg);
                    if (callback != null) {
                        callback.onFailed(code, errorMsg);
                    }
                    return;
                }

                try {
                    JSONObject resObj = JSONObject.parseObject(response);
                    int resCode = resObj.getIntValue(RESULT_KEY_RES);
                    if (resCode == RESULT_CODE_SUCCESS) {
                        TarotListModel model = new TarotListModel();
                        model.setData(resObj.getObject("data", TarotListModel.DataBean.class));
                        model.setMsg(resObj.getString("msg"));
                        model.setCode(resCode);
                        callback.onSuccess(model.getData());
                    } else {
                        String error = resObj.getString(RESULT_KEY_ERROR_MSG);
                        callback.onFailed(resCode, error);
                    }
                } catch (JSONException e) {
                    callback.onFailed(-1, e.getMessage());
                }
            }
        });
    }


    //接口9
    public void clistCode(int t_id, int s_id, int pages, int num, final ClistHttpCallBack<CommentListModel.Data> callback) {
        String url = DemoServers.apiServer() + "comment/clist";


        Map<String, String> headers = new HashMap<>(1);


        StringBuilder body = new StringBuilder();
        body.append("t_id").append("=").append(t_id).append("&")
//                .append("s_id").append("=").append(s_id).append("&")
                .append("pages").append("=").append(pages).append("&")
                .append("num").append("=").append(num);

        String bodyString = body.toString();

        NimHttpClient.getInstance().execute(url, headers, bodyString, false, new NimHttpClient.NimHttpCallback() {
            @Override
            public void onResponse(String response, int code, String errorMsg) {
                if (code != 0) {
                    LogUtil.e(TAG, "clist failed : code = " + code + ", errorMsg = " + errorMsg);
                    if (callback != null) {
                        callback.onFailed(code, errorMsg);
                    }
                    return;
                }

                try {
                    JSONObject resObj = JSONObject.parseObject(response);
                    int resCode = resObj.getIntValue(RESULT_KEY_RES);
                    if (resCode == RESULT_CODE_SUCCESS) {
                        CommentListModel model = new CommentListModel();
                        model.setData(resObj.getObject("data", CommentListModel.Data.class));
                        model.setMsg(resObj.getString("msg"));
                        model.setCode(resCode);
                        callback.onSuccess(model.getData());
                    } else {
                        String error = resObj.getString(RESULT_KEY_ERROR_MSG);
                        callback.onFailed(resCode, error);
                    }
                } catch (JSONException e) {
                    callback.onFailed(-1, e.getMessage());
                }
            }
        });
    }

    //接口10
    public void addCode(String uid, String ticket, int o_id, String comment, int star1, int star2, int star3, int star4, int want, int haoping, final AddHttpCallBack<Void> callback) {
        String url = DemoServers.apiServer() + "comment/add";


        Map<String, String> headers = new HashMap<>(1);


        StringBuilder body = new StringBuilder();
        body.append("uid").append("=").append(uid).append("&")
                .append("ticket").append("=").append(ticket).append("&")
                .append("o_id").append("=").append(o_id).append("&")
                .append("comment").append("=").append(comment).append("&")
                .append("star1").append("=").append(star1).append("&")
                .append("star2").append("=").append(star2).append("&")
                .append("star3").append("=").append(star3).append("&")
                .append("star4").append("=").append(star4).append("&")
                .append("want").append("=").append(want).append("&")
                .append("cate").append("=").append(haoping);

        String bodyString = body.toString();

        NimHttpClient.getInstance().execute(url, headers, bodyString, false, new NimHttpClient.NimHttpCallback() {
            @Override
            public void onResponse(String response, int code, String errorMsg) {
                if (code != 0) {
                    LogUtil.e(TAG, "add failed : code = " + code + ", errorMsg = " + errorMsg);
                    if (callback != null) {
                        callback.onFailed(code, errorMsg);
                    }
                    return;
                }

                try {
                    JSONObject resObj = JSONObject.parseObject(response);
                    int resCode = resObj.getIntValue(RESULT_KEY_RES);
                    if (resCode == RESULT_CODE_SUCCESS) {

                        callback.onSuccess(null);
                    } else {
                        String error = resObj.getString(RESULT_KEY_ERROR_MSG);
                        callback.onFailed(resCode, error);
                    }
                } catch (JSONException e) {
                    callback.onFailed(-1, e.getMessage());
                }
            }
        });
    }

    //接口11
    public void deleteCode(String uid, String ticket, String cmt_id, final DeleteHttpCallBack<Void> callback) {
        String url = DemoServers.apiServer() + "comment/delete";


        Map<String, String> headers = new HashMap<>(1);


        StringBuilder body = new StringBuilder();
        body.append("uid").append("=").append(uid).append("&")
                .append("ticket").append("=").append(ticket).append("&")
                .append("cmt_id").append("=").append(cmt_id);

        String bodyString = body.toString();

        NimHttpClient.getInstance().execute(url, headers, bodyString, false, new NimHttpClient.NimHttpCallback() {
            @Override
            public void onResponse(String response, int code, String errorMsg) {
                if (code != 0) {
                    LogUtil.e(TAG, "delete failed : code = " + code + ", errorMsg = " + errorMsg);
                    if (callback != null) {
                        callback.onFailed(code, errorMsg);
                    }
                    return;
                }

                try {
                    JSONObject resObj = JSONObject.parseObject(response);
                    int resCode = resObj.getIntValue(RESULT_KEY_RES);
                    if (resCode == RESULT_CODE_SUCCESS) {
                        callback.onSuccess(null);
                    } else {
                        String error = resObj.getString(RESULT_KEY_ERROR_MSG);
                        callback.onFailed(resCode, error);
                    }
                } catch (JSONException e) {
                    callback.onFailed(-1, e.getMessage());
                }
            }
        });
    }

    //接口12
    public void editCode(String uid, String ticket, int cmt_id, String comment, int star1, int star2, int star3, int star4, int want, int haoping, final EditHttpCallBack<Void> callback) {
        String url = DemoServers.apiServer() + "comment/edit";


        Map<String, String> headers = new HashMap<>(1);


        StringBuilder body = new StringBuilder();
        body.append("uid").append("=").append(uid).append("&")
                .append("ticket").append("=").append(ticket).append("&")
                .append("cmt_id").append("=").append(cmt_id).append("&")
                .append("comment").append("=").append(comment).append("&")
                .append("star1").append("=").append(star1).append("&")
                .append("star2").append("=").append(star2).append("&")
                .append("star3").append("=").append(star3).append("&")
                .append("star4").append("=").append(star4).append("&")
                .append("want").append("=").append(want).append("&")
                .append("cate").append("=").append(haoping);

        String bodyString = body.toString();

        NimHttpClient.getInstance().execute(url, headers, bodyString, false, new NimHttpClient.NimHttpCallback() {
            @Override
            public void onResponse(String response, int code, String errorMsg) {
                if (code != 0) {
                    LogUtil.e(TAG, "edit failed : code = " + code + ", errorMsg = " + errorMsg);
                    if (callback != null) {
                        callback.onFailed(code, errorMsg);
                    }
                    return;
                }

                try {
                    JSONObject resObj = JSONObject.parseObject(response);
                    int resCode = resObj.getIntValue(RESULT_KEY_RES);
                    if (resCode == RESULT_CODE_SUCCESS) {
                        callback.onSuccess(null);
                    } else {
                        String error = resObj.getString(RESULT_KEY_ERROR_MSG);
                        callback.onFailed(resCode, error);
                    }
                } catch (JSONException e) {
                    callback.onFailed(-1, e.getMessage());
                }
            }
        });
    }


    //接口13 get comment detail
    public void detialCode(String uid, String ticket, int cmt_id, final DetialHttpCallBack<CommentDetailModel.DataBean> callback) {
        String url = DemoServers.apiServer() + "comment/detial";


        Map<String, String> headers = new HashMap<>(1);


        StringBuilder body = new StringBuilder();
        body.append("uid").append("=").append(uid).append("&")
                .append("ticket").append("=").append(ticket).append("&")
                .append("cmt_id").append("=").append(cmt_id);

        String bodyString = body.toString();

        NimHttpClient.getInstance().execute(url, headers, bodyString, false, new NimHttpClient.NimHttpCallback() {
            @Override
            public void onResponse(String response, int code, String errorMsg) {
                if (code != 0) {
                    LogUtil.e(TAG, "detial failed : code = " + code + ", errorMsg = " + errorMsg);
                    if (callback != null) {
                        callback.onFailed(code, errorMsg);
                    }
                    return;
                }

                try {
                    JSONObject resObj = JSONObject.parseObject(response);
                    int resCode = resObj.getIntValue(RESULT_KEY_RES);
                    if (resCode == RESULT_CODE_SUCCESS) {
                        CommentDetailModel model = new CommentDetailModel();
                        model.setData(resObj.getObject("data", CommentDetailModel.DataBean.class));
                        model.setMsg(resObj.getString("msg"));
                        model.setCode(resCode);
                        callback.onSuccess(model.getData());
                    } else {
                        String error = resObj.getString(RESULT_KEY_ERROR_MSG);
                        callback.onFailed(resCode, error);
                    }
                } catch (JSONException e) {
                    callback.onFailed(-1, e.getMessage());
                }
            }
        });
    }

    //接口14 我的评价列表
    public void myClist(String uid, String ticket, int page, int num, final MyClistHttpCallBack<CommentListModel.Data> callback) {
        String url = DemoServers.apiServer() + "comment/myclist";


        Map<String, String> headers = new HashMap<>(1);


        StringBuilder body = new StringBuilder();
        body.append("uid").append("=").append(uid).append("&")
                .append("ticket").append("=").append(ticket).append("&")
                .append("page").append("=").append(page).append("&")
                .append("num").append("=").append(num);

        String bodyString = body.toString();

        NimHttpClient.getInstance().execute(url, headers, bodyString, false, new NimHttpClient.NimHttpCallback() {
            @Override
            public void onResponse(String response, int code, String errorMsg) {
                if (code != 0) {
                    LogUtil.e(TAG, "detial failed : code = " + code + ", errorMsg = " + errorMsg);
                    if (callback != null) {
                        callback.onFailed(code, errorMsg);
                    }
                    return;
                }

                try {
                    JSONObject resObj = JSONObject.parseObject(response);
                    int resCode = resObj.getIntValue(RESULT_KEY_RES);
                    if (resCode == RESULT_CODE_SUCCESS) {
                        CommentListModel model = new CommentListModel();
                        model.setData(resObj.getObject("data", CommentListModel.Data.class));
                        model.setMsg(resObj.getString("msg"));
                        model.setCode(resCode);
                        callback.onSuccess(model.getData());
                    } else {
                        String error = resObj.getString(RESULT_KEY_ERROR_MSG);
                        callback.onFailed(resCode, error);
                    }
                } catch (JSONException e) {
                    callback.onFailed(-1, e.getMessage());
                }
            }
        });
    }

    //接口14
    public void makeorderCode(int uid, String ticket, String payment, int t_id, int s_id, final MakeorderHttpCallBack<Void> callback) {
        String url = DemoServers.apiServer() + "order/makeorder";


        Map<String, String> headers = new HashMap<>(1);


        StringBuilder body = new StringBuilder();
        body.append("uid").append("=").append(uid).append("&")
                .append("ticket").append("=").append(ticket).append("&")
                .append("payment").append("=").append(payment).append("&")
                .append("t_id").append("=").append(t_id).append("&")
                .append("s_id").append("=").append(s_id);

        String bodyString = body.toString();

        NimHttpClient.getInstance().execute(url, headers, bodyString, false, new NimHttpClient.NimHttpCallback() {
            @Override
            public void onResponse(String response, int code, String errorMsg) {
                if (code != 0) {
                    LogUtil.e(TAG, "makeorder failed : code = " + code + ", errorMsg = " + errorMsg);
                    if (callback != null) {
                        callback.onFailed(code, errorMsg);
                    }
                    return;
                }

                try {
                    JSONObject resObj = JSONObject.parseObject(response);
                    int resCode = resObj.getIntValue(RESULT_KEY_RES);
                    if (resCode == RESULT_CODE_SUCCESS) {
                        callback.onSuccess(null);
                    } else {
                        String error = resObj.getString(RESULT_KEY_ERROR_MSG);
                        callback.onFailed(resCode, error);
                    }
                } catch (JSONException e) {
                    callback.onFailed(-1, e.getMessage());
                }
            }
        });
    }

    //接口15 获取列表
    public void getCollectionList(String uid, String ticket, final TarotCollectionListHttpCallBack<TarotListModel.DataBean> callback) {
        String url = DemoServers.apiServer() + "user/favorlist";


        Map<String, String> headers = new HashMap<>(1);


        StringBuilder body = new StringBuilder();
        body.append("uid").append("=").append(uid).append("&")
                .append("ticket").append("=").append(ticket);

        String bodyString = body.toString();

        NimHttpClient.getInstance().execute(url, headers, bodyString, false, new NimHttpClient.NimHttpCallback() {
            @Override
            public void onResponse(String response, int code, String errorMsg) {
                if (code != 0) {
                    LogUtil.e(TAG, "updateYXUserInfo failed : code = " + code + ", errorMsg = " + errorMsg);
                    if (callback != null) {
                        callback.onFailed(code, errorMsg);
                    }
                    return;
                }

                try {
                    JSONObject resObj = JSONObject.parseObject(response);
                    int resCode = resObj.getIntValue(RESULT_KEY_RES);
                    if (resCode == RESULT_CODE_SUCCESS) {
                        TarotListModel model = new TarotListModel();
                        model.setData(resObj.getObject("data", TarotListModel.DataBean.class));
                        model.setMsg(resObj.getString("msg"));
                        model.setCode(resCode);
                        callback.onSuccess(model.getData());
                    } else {
                        String error = resObj.getString(RESULT_KEY_ERROR_MSG);
                        callback.onFailed(resCode, error);
                    }
                } catch (JSONException e) {
                    callback.onFailed(-1, e.getMessage());
                }
            }
        });
    }

    //接口15 add/remove collection
    public void setCollection(String uid, String ticket, String tId, String is_favor, final SetTarotCollectionHttpCallBack<Void> callback) {
        String url = DemoServers.apiServer() + "user/addfavor";


        Map<String, String> headers = new HashMap<>(1);


        StringBuilder body = new StringBuilder();
        body.append("uid").append("=").append(uid).append("&")
                .append("ticket").append("=").append(ticket).append("&")
                .append("tid").append("=").append(tId).append("&")
                .append("is_favor").append("=").append(is_favor);

        String bodyString = body.toString();

        NimHttpClient.getInstance().execute(url, headers, bodyString, false, new NimHttpClient.NimHttpCallback() {
            @Override
            public void onResponse(String response, int code, String errorMsg) {
                if (code != 0) {
                    LogUtil.e(TAG, "updateYXUserInfo failed : code = " + code + ", errorMsg = " + errorMsg);
                    if (callback != null) {
                        callback.onFailed(code, errorMsg);
                    }
                    return;
                }

                try {
                    JSONObject resObj = JSONObject.parseObject(response);
                    int resCode = resObj.getIntValue(RESULT_KEY_RES);
                    if (resCode == RESULT_CODE_SUCCESS) {

                        callback.onSuccess(null);
                    } else {
                        String error = resObj.getString(RESULT_KEY_ERROR_MSG);
                        callback.onFailed(resCode, error);
                    }
                } catch (JSONException e) {
                    callback.onFailed(-1, e.getMessage());
                }
            }
        });
    }

    public void getServiceList(String tid, final GetTarotServiceListHttpCallBack<ServiceListModel.Data> callback) {
        String url = DemoServers.apiServer() + "service/servicelist";


        Map<String, String> headers = new HashMap<>(1);


        StringBuilder body = new StringBuilder();
        body.append("t_id").append("=").append(tid);

        String bodyString = body.toString();

        NimHttpClient.getInstance().execute(url, headers, bodyString, false, new NimHttpClient.NimHttpCallback() {
            @Override
            public void onResponse(String response, int code, String errorMsg) {
                if (code != 0) {
                    LogUtil.e(TAG, "updateYXUserInfo failed : code = " + code + ", errorMsg = " + errorMsg);
                    if (callback != null) {
                        callback.onFailed(code, errorMsg);
                    }
                    return;
                }

                try {
                    JSONObject resObj = JSONObject.parseObject(response);
                    int resCode = resObj.getIntValue(RESULT_KEY_RES);
                    if (resCode == RESULT_CODE_SUCCESS) {
                        ServiceListModel model = new ServiceListModel();
                        model.setData(resObj.getObject("data", ServiceListModel.Data.class));
                        model.setMsg(resObj.getString("msg"));
                        model.setCode(resCode);
                        callback.onSuccess(model.getData());
                    } else {
                        String error = resObj.getString(RESULT_KEY_ERROR_MSG);
                        callback.onFailed(resCode, error);
                    }
                } catch (JSONException e) {
                    callback.onFailed(-1, e.getMessage());
                }
            }
        });
    }

    public void getTarotHomePage(String tid, final GetTarotHomePageHttpCallBack<TarotHomePageModel.Data> callback) {
        String url = DemoServers.apiServer() + "taluoshi/detail";


        Map<String, String> headers = new HashMap<>(1);


        StringBuilder body = new StringBuilder();
        body.append("t_id").append("=").append(tid);

        String bodyString = body.toString();

        NimHttpClient.getInstance().execute(url, headers, bodyString, false, new NimHttpClient.NimHttpCallback() {
            @Override
            public void onResponse(String response, int code, String errorMsg) {
                if (code != 0) {
                    LogUtil.e(TAG, "updateYXUserInfo failed : code = " + code + ", errorMsg = " + errorMsg);
                    if (callback != null) {
                        callback.onFailed(code, errorMsg);
                    }
                    return;
                }

                try {
                    JSONObject resObj = JSONObject.parseObject(response);
                    int resCode = resObj.getIntValue(RESULT_KEY_RES);
                    if (resCode == RESULT_CODE_SUCCESS) {
                        TarotHomePageModel model = new TarotHomePageModel();
                        model.setData(resObj.getObject("data", TarotHomePageModel.Data.class));
                        model.setMsg(resObj.getString("msg"));
                        model.setCode(resCode);
                        callback.onSuccess(model.getData());
                    } else {
                        String error = resObj.getString(RESULT_KEY_ERROR_MSG);
                        callback.onFailed(resCode, error);
                    }
                } catch (JSONException e) {
                    callback.onFailed(-1, e.getMessage());
                }
            }
        });
    }

    public void getOrderList(String tid, String ticket, int status, int page, int num, final OrderListHttpCallBack<OrderListModel.DataBean> callback) {
        String url = DemoServers.apiServer() + "order/tlslist";


        Map<String, String> headers = new HashMap<>(1);


        StringBuilder body = new StringBuilder();
        body.append("tid").append("=").append(tid).append("&")
                .append("ticket").append("=").append(ticket).append("&")
                .append("status").append("=").append(status).append("&")
                .append("page").append("=").append(page).append("&")
                .append("num").append("=").append(num);

        String bodyString = body.toString();

        NimHttpClient.getInstance().execute(url, headers, bodyString, false, new NimHttpClient.NimHttpCallback() {
            @Override
            public void onResponse(String response, int code, String errorMsg) {
                if (code != 0) {
                    LogUtil.e(TAG, "updateYXUserInfo failed : code = " + code + ", errorMsg = " + errorMsg);
                    if (callback != null) {
                        callback.onFailed(code, errorMsg);
                    }
                    return;
                }

                try {
                    JSONObject resObj = JSONObject.parseObject(response);
                    int resCode = resObj.getIntValue(RESULT_KEY_RES);
                    if (resCode == RESULT_CODE_SUCCESS) {
                        OrderListModel model = new OrderListModel();
                        model.setData(resObj.getObject("data", OrderListModel.DataBean.class));
                        model.setMsg(resObj.getString("msg"));
                        model.setCode(resCode);
                        callback.onSuccess(model.getData());
                    } else {
                        String error = resObj.getString(RESULT_KEY_ERROR_MSG);
                        callback.onFailed(resCode, error);
                    }
                } catch (JSONException e) {
                    callback.onFailed(-1, e.getMessage());
                }
            }
        });
    }

    public void getOrder(String uid, String ticket, String t_id, String o_id, final OrderPayHttpCallBack<String> callback) {
        String url = DemoServers.apiServer() + "order/orderpay";


        Map<String, String> headers = new HashMap<>(1);


        StringBuilder body = new StringBuilder();
        body.append("uid").append("=").append(uid).append("&")
                .append("ticket").append("=").append(ticket).append("&")
                .append("t_id").append("=").append(t_id).append("&")
                .append("o_id").append("=").append(o_id);

        String bodyString = body.toString();

        NimHttpClient.getInstance().execute(url, headers, bodyString, false, new NimHttpClient.NimHttpCallback() {
            @Override
            public void onResponse(String response, int code, String errorMsg) {
                if (code != 0) {
                    LogUtil.e(TAG, "updateYXUserInfo failed : code = " + code + ", errorMsg = " + errorMsg);
                    if (callback != null) {
                        callback.onFailed(code, errorMsg);
                    }
                    return;
                }

                try {
                    JSONObject resObj = JSONObject.parseObject(response);
                    int resCode = resObj.getIntValue(RESULT_KEY_RES);
                    if (resCode == RESULT_CODE_SUCCESS) {

                        callback.onSuccess(response);
                    } else {
                        String error = resObj.getString(RESULT_KEY_ERROR_MSG);
                        callback.onFailed(resCode, error);
                    }
                } catch (JSONException e) {
                    callback.onFailed(-1, e.getMessage());
                }
            }
        });
    }

    public void createOrder(String tid, String ticket, String payment, String user_accid, String s_id, String price, String channel, final CreateOrderHttpCallBack<CreateOrderModel.Data> callback) {
        String url = DemoServers.apiServer() + "order/makeorder";


        Map<String, String> headers = new HashMap<>(1);


        StringBuilder body = new StringBuilder();
        body.append("tid").append("=").append(tid).append("&")
                .append("ticket").append("=").append(ticket).append("&")
                .append("user_accid").append("=").append(user_accid).append("&")
                .append("s_id").append("=").append(s_id).append("&")
                .append("price").append("=").append(price).append("&")
                .append("channel").append("=").append(channel);

        String bodyString = body.toString();

        NimHttpClient.getInstance().execute(url, headers, bodyString, false, new NimHttpClient.NimHttpCallback() {
            @Override
            public void onResponse(String response, int code, String errorMsg) {
                if (code != 0) {
                    LogUtil.e(TAG, "updateYXUserInfo failed : code = " + code + ", errorMsg = " + errorMsg);
                    if (callback != null) {
                        callback.onFailed(code, errorMsg);
                    }
                    return;
                }

                try {
                    JSONObject resObj = JSONObject.parseObject(response);
                    int resCode = resObj.getIntValue(RESULT_KEY_RES);
                    if (resCode == RESULT_CODE_SUCCESS) {
                        CreateOrderModel model = new CreateOrderModel();
                        model.setData(resObj.getObject("data", CreateOrderModel.Data.class));
                        model.setMsg(resObj.getString("msg"));
                        model.setCode(resCode);
                        callback.onSuccess(model.getData());

                    } else {
                        String error = resObj.getString(RESULT_KEY_ERROR_MSG);
                        callback.onFailed(resCode, error);
                    }
                } catch (JSONException e) {
                    callback.onFailed(-1, e.getMessage());
                }
            }
        });
    }
    public void setStatus(String tid, String ticket,int is_online, final SetStatusHttpCallBack<Void> callback) {
        String url = DemoServers.apiServer() + "taluoshi/setOnlineStatus";


        Map<String, String> headers = new HashMap<>(1);


        StringBuilder body = new StringBuilder();
        body.append("tid").append("=").append(tid).append("&")
                .append("ticket").append("=").append(ticket).append("&")
                .append("is_online").append("=").append(is_online);

        String bodyString = body.toString();

        NimHttpClient.getInstance().execute(url, headers, bodyString, false, new NimHttpClient.NimHttpCallback() {
            @Override
            public void onResponse(String response, int code, String errorMsg) {
                if (code != 0) {
                    LogUtil.e(TAG, "updateYXUserInfo failed : code = " + code + ", errorMsg = " + errorMsg);
                    if (callback != null) {
                        callback.onFailed(code, errorMsg);
                    }
                    return;
                }

                try {
                    JSONObject resObj = JSONObject.parseObject(response);
                    int resCode = resObj.getIntValue(RESULT_KEY_RES);
                    if (resCode == RESULT_CODE_SUCCESS) {

                        callback.onSuccess(null);

                    } else {
                        String error = resObj.getString(RESULT_KEY_ERROR_MSG);
                        callback.onFailed(resCode, error);
                    }
                } catch (JSONException e) {
                    callback.onFailed(-1, e.getMessage());
                }
            }
        });
    }
    public void getStatus(String tid, final GetStatusHttpCallBack<GetStatusModel.Data> callback) {
        String url = DemoServers.apiServer() + "taluoshi/onlinestatus";


        Map<String, String> headers = new HashMap<>(1);


        StringBuilder body = new StringBuilder();
        body.append("tid").append("=").append(tid);

        String bodyString = body.toString();

        NimHttpClient.getInstance().execute(url, headers, bodyString, false, new NimHttpClient.NimHttpCallback() {
            @Override
            public void onResponse(String response, int code, String errorMsg) {
                if (code != 0) {
                    LogUtil.e(TAG, "updateYXUserInfo failed : code = " + code + ", errorMsg = " + errorMsg);
                    if (callback != null) {
                        callback.onFailed(code, errorMsg);
                    }
                    return;
                }

                try {
                    JSONObject resObj = JSONObject.parseObject(response);
                    int resCode = resObj.getIntValue(RESULT_KEY_RES);
                    if (resCode == RESULT_CODE_SUCCESS) {
                        GetStatusModel model = new GetStatusModel();
                        model.setData(resObj.getObject("data", GetStatusModel.Data.class));
                        model.setMsg(resObj.getString("msg"));
                        model.setCode(resCode);
                        callback.onSuccess(model.getData());

                    } else {
                        String error = resObj.getString(RESULT_KEY_ERROR_MSG);
                        callback.onFailed(resCode, error);
                    }
                } catch (JSONException e) {
                    callback.onFailed(-1, e.getMessage());
                }
            }
        });
    }
    private String readAppKey() {
        try {
            ApplicationInfo appInfo = DemoCache.getContext().getPackageManager()
                    .getApplicationInfo(DemoCache.getContext().getPackageName(), PackageManager.GET_META_DATA);
            if (appInfo != null) {
                return appInfo.metaData.getString("com.netease.nim.appKey");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
