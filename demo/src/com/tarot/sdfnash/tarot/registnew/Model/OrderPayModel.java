package com.tarot.sdfnash.tarot.registnew.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Liuyi on 2016/9/4.
 */
public class OrderPayModel implements Serializable {

    /**
     * id : ch_yPifj59Gmb9GXbrL0Sib14WT
     * object : charge
     * created : 1472875109
     * livemode : false
     * paid : false
     * refunded : false
     * app : app_nr188OinjXL8q1Cy
     * channel : wx
     * order_no : 11
     * client_ip : 127.0.0.1
     * amount : 1223
     * amount_settle : 1223
     * currency : cny
     * subject : s1的订单
     * body : s1的订单
     * extra : {}
     * time_paid :
     * time_expire : 1472882309
     * time_settle :
     * transaction_no :
     * refunds : {"object":"list","url":"/v1/charges/ch_yPifj59Gmb9GXbrL0Sib14WT/refunds","has_more":false,"data":[]}
     * amount_refunded : 0
     * failure_code :
     * failure_msg :
     * metadata : {}
     * credential : {"object":"credential","wx":{"appId":"wx0omdgo4mxt4400y5","partnerId":"1269954601","prepayId":"11010000001609038wpi9czj9o4kpowr","nonceStr":"c241a615f5c114fa7b877fec589c7bea","timeStamp":1472875109,"packageValue":"Sign=WXPay","sign":"50555babef3023591e7e6dfd3997073311fac5a4"}}
     * description :
     */

    private String id;
    private String object;
    private int created;
    private boolean livemode;
    private boolean paid;
    private boolean refunded;
    private String app;
    private String channel;
    private String order_no;
    private String client_ip;
    private int amount;
    private int amount_settle;
    private String currency;
    private String subject;
    private String body;
    private ExtraBean extra;
    private String time_paid;
    private int time_expire;
    private String time_settle;
    private String transaction_no;
    /**
     * object : list
     * url : /v1/charges/ch_yPifj59Gmb9GXbrL0Sib14WT/refunds
     * has_more : false
     * data : []
     */

    private RefundsBean refunds;
    private int amount_refunded;
    private String failure_code;
    private String failure_msg;
    private MetadataBean metadata;
    /**
     * object : credential
     * wx : {"appId":"wx0omdgo4mxt4400y5","partnerId":"1269954601","prepayId":"11010000001609038wpi9czj9o4kpowr","nonceStr":"c241a615f5c114fa7b877fec589c7bea","timeStamp":1472875109,"packageValue":"Sign=WXPay","sign":"50555babef3023591e7e6dfd3997073311fac5a4"}
     */

    private CredentialBean credential;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public int getCreated() {
        return created;
    }

    public void setCreated(int created) {
        this.created = created;
    }

    public boolean isLivemode() {
        return livemode;
    }

    public void setLivemode(boolean livemode) {
        this.livemode = livemode;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public boolean isRefunded() {
        return refunded;
    }

    public void setRefunded(boolean refunded) {
        this.refunded = refunded;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getClient_ip() {
        return client_ip;
    }

    public void setClient_ip(String client_ip) {
        this.client_ip = client_ip;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount_settle() {
        return amount_settle;
    }

    public void setAmount_settle(int amount_settle) {
        this.amount_settle = amount_settle;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public ExtraBean getExtra() {
        return extra;
    }

    public void setExtra(ExtraBean extra) {
        this.extra = extra;
    }

    public String getTime_paid() {
        return time_paid;
    }

    public void setTime_paid(String time_paid) {
        this.time_paid = time_paid;
    }

    public int getTime_expire() {
        return time_expire;
    }

    public void setTime_expire(int time_expire) {
        this.time_expire = time_expire;
    }

    public String getTime_settle() {
        return time_settle;
    }

    public void setTime_settle(String time_settle) {
        this.time_settle = time_settle;
    }

    public String getTransaction_no() {
        return transaction_no;
    }

    public void setTransaction_no(String transaction_no) {
        this.transaction_no = transaction_no;
    }

    public RefundsBean getRefunds() {
        return refunds;
    }

    public void setRefunds(RefundsBean refunds) {
        this.refunds = refunds;
    }

    public int getAmount_refunded() {
        return amount_refunded;
    }

    public void setAmount_refunded(int amount_refunded) {
        this.amount_refunded = amount_refunded;
    }

    public String getFailure_code() {
        return failure_code;
    }

    public void setFailure_code(String failure_code) {
        this.failure_code = failure_code;
    }

    public String getFailure_msg() {
        return failure_msg;
    }

    public void setFailure_msg(String failure_msg) {
        this.failure_msg = failure_msg;
    }

    public MetadataBean getMetadata() {
        return metadata;
    }

    public void setMetadata(MetadataBean metadata) {
        this.metadata = metadata;
    }

    public CredentialBean getCredential() {
        return credential;
    }

    public void setCredential(CredentialBean credential) {
        this.credential = credential;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static class ExtraBean {
    }

    public static class RefundsBean {
        private String object;
        private String url;
        private boolean has_more;
        private List<?> data;

        public String getObject() {
            return object;
        }

        public void setObject(String object) {
            this.object = object;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isHas_more() {
            return has_more;
        }

        public void setHas_more(boolean has_more) {
            this.has_more = has_more;
        }

        public List<?> getData() {
            return data;
        }

        public void setData(List<?> data) {
            this.data = data;
        }
    }

    public static class MetadataBean {
    }

    public static class CredentialBean {
        private String object;
        /**
         * appId : wx0omdgo4mxt4400y5
         * partnerId : 1269954601
         * prepayId : 11010000001609038wpi9czj9o4kpowr
         * nonceStr : c241a615f5c114fa7b877fec589c7bea
         * timeStamp : 1472875109
         * packageValue : Sign=WXPay
         * sign : 50555babef3023591e7e6dfd3997073311fac5a4
         */

        private WxBean wx;

        public String getObject() {
            return object;
        }

        public void setObject(String object) {
            this.object = object;
        }

        public WxBean getWx() {
            return wx;
        }

        public void setWx(WxBean wx) {
            this.wx = wx;
        }

        public static class WxBean {
            private String appId;
            private String partnerId;
            private String prepayId;
            private String nonceStr;
            private int timeStamp;
            private String packageValue;
            private String sign;

            public String getAppId() {
                return appId;
            }

            public void setAppId(String appId) {
                this.appId = appId;
            }

            public String getPartnerId() {
                return partnerId;
            }

            public void setPartnerId(String partnerId) {
                this.partnerId = partnerId;
            }

            public String getPrepayId() {
                return prepayId;
            }

            public void setPrepayId(String prepayId) {
                this.prepayId = prepayId;
            }

            public String getNonceStr() {
                return nonceStr;
            }

            public void setNonceStr(String nonceStr) {
                this.nonceStr = nonceStr;
            }

            public int getTimeStamp() {
                return timeStamp;
            }

            public void setTimeStamp(int timeStamp) {
                this.timeStamp = timeStamp;
            }

            public String getPackageValue() {
                return packageValue;
            }

            public void setPackageValue(String packageValue) {
                this.packageValue = packageValue;
            }

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }
        }
    }
}
