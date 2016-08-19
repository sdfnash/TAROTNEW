package com.tarot.sdfnash.tarot.registnew.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sdfnash on 16/8/19.
 */
public class CommentListModel implements Serializable{


    /**
     * list : [{"id":"13","comment":"1232131","u_id":"1","nickname":"aaaa","photo_s":"/Uploads/2016-07-28/s_579a1dc1b0cf6.jpg"},{"id":"12","comment":"1232131","u_id":"1","nickname":"aaaa","photo_s":"/Uploads/2016-07-28/s_579a1dc1b0cf6.jpg"},{"id":"11","comment":"1232131","u_id":"1","nickname":"aaaa","photo_s":"/Uploads/2016-07-28/s_579a1dc1b0cf6.jpg"},{"id":"10","comment":"1232131","u_id":"1","nickname":"aaaa","photo_s":"/Uploads/2016-07-28/s_579a1dc1b0cf6.jpg"},{"id":"9","comment":"1232131","u_id":"1","nickname":"aaaa","photo_s":"/Uploads/2016-07-28/s_579a1dc1b0cf6.jpg"}]
     * count : 17
     * page : 2
     * total : 4
     * info : {"haoping_count":13,"zhongping_count":"3","chaping_count":"1","total_count":"17","haopinglv":76.47,"avg_star1":4.45,"avg_star2":4.45,"avg_star3":4.45,"avg_star4":4.45,"avg_star":4.45,"tls_id":"1","tls_nickname":"bbb","tls_accid":"taluoshi_1","tls_photo":"/Uploads/admin/s_file_579d9d69005ec.jpg"}
     */

    private Data data;
    /**
     * data : {"list":[{"id":"13","comment":"1232131","u_id":"1","nickname":"aaaa","photo_s":"/Uploads/2016-07-28/s_579a1dc1b0cf6.jpg"},{"id":"12","comment":"1232131","u_id":"1","nickname":"aaaa","photo_s":"/Uploads/2016-07-28/s_579a1dc1b0cf6.jpg"},{"id":"11","comment":"1232131","u_id":"1","nickname":"aaaa","photo_s":"/Uploads/2016-07-28/s_579a1dc1b0cf6.jpg"},{"id":"10","comment":"1232131","u_id":"1","nickname":"aaaa","photo_s":"/Uploads/2016-07-28/s_579a1dc1b0cf6.jpg"},{"id":"9","comment":"1232131","u_id":"1","nickname":"aaaa","photo_s":"/Uploads/2016-07-28/s_579a1dc1b0cf6.jpg"}],"count":17,"page":2,"total":4,"info":{"haoping_count":13,"zhongping_count":"3","chaping_count":"1","total_count":"17","haopinglv":76.47,"avg_star1":4.45,"avg_star2":4.45,"avg_star3":4.45,"avg_star4":4.45,"avg_star":4.45,"tls_id":"1","tls_nickname":"bbb","tls_accid":"taluoshi_1","tls_photo":"/Uploads/admin/s_file_579d9d69005ec.jpg"}}
     * code : 0
     * msg :
     */

    private int code;
    private String msg;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
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

    public static class Data {
        private int count;
        private int page;
        private int total;
        /**
         * haoping_count : 13
         * zhongping_count : 3
         * chaping_count : 1
         * total_count : 17
         * haopinglv : 76.47
         * avg_star1 : 4.45
         * avg_star2 : 4.45
         * avg_star3 : 4.45
         * avg_star4 : 4.45
         * avg_star : 4.45
         * tls_id : 1
         * tls_nickname : bbb
         * tls_accid : taluoshi_1
         * tls_photo : /Uploads/admin/s_file_579d9d69005ec.jpg
         */

        private Info info;
        /**
         * id : 13
         * comment : 1232131
         * u_id : 1
         * nickname : aaaa
         * photo_s : /Uploads/2016-07-28/s_579a1dc1b0cf6.jpg
         */

        private List<CommentShowModel> list;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public Info getInfo() {
            return info;
        }

        public void setInfo(Info info) {
            this.info = info;
        }

        public List<CommentShowModel> getList() {
            return list;
        }

        public void setList(List<CommentShowModel> list) {
            this.list = list;
        }

        public static class Info {
            private int haoping_count;
            private String zhongping_count;
            private String chaping_count;
            private String total_count;
            private double haopinglv;
            private double avg_star1;
            private double avg_star2;
            private double avg_star3;
            private double avg_star4;
            private double avg_star;
            private String tls_id;
            private String tls_nickname;
            private String tls_accid;
            private String tls_photo;

            public int getHaoping_count() {
                return haoping_count;
            }

            public void setHaoping_count(int haoping_count) {
                this.haoping_count = haoping_count;
            }

            public String getZhongping_count() {
                return zhongping_count;
            }

            public void setZhongping_count(String zhongping_count) {
                this.zhongping_count = zhongping_count;
            }

            public String getChaping_count() {
                return chaping_count;
            }

            public void setChaping_count(String chaping_count) {
                this.chaping_count = chaping_count;
            }

            public String getTotal_count() {
                return total_count;
            }

            public void setTotal_count(String total_count) {
                this.total_count = total_count;
            }

            public double getHaopinglv() {
                return haopinglv;
            }

            public void setHaopinglv(double haopinglv) {
                this.haopinglv = haopinglv;
            }

            public double getAvg_star1() {
                return avg_star1;
            }

            public void setAvg_star1(double avg_star1) {
                this.avg_star1 = avg_star1;
            }

            public double getAvg_star2() {
                return avg_star2;
            }

            public void setAvg_star2(double avg_star2) {
                this.avg_star2 = avg_star2;
            }

            public double getAvg_star3() {
                return avg_star3;
            }

            public void setAvg_star3(double avg_star3) {
                this.avg_star3 = avg_star3;
            }

            public double getAvg_star4() {
                return avg_star4;
            }

            public void setAvg_star4(double avg_star4) {
                this.avg_star4 = avg_star4;
            }

            public double getAvg_star() {
                return avg_star;
            }

            public void setAvg_star(double avg_star) {
                this.avg_star = avg_star;
            }

            public String getTls_id() {
                return tls_id;
            }

            public void setTls_id(String tls_id) {
                this.tls_id = tls_id;
            }

            public String getTls_nickname() {
                return tls_nickname;
            }

            public void setTls_nickname(String tls_nickname) {
                this.tls_nickname = tls_nickname;
            }

            public String getTls_accid() {
                return tls_accid;
            }

            public void setTls_accid(String tls_accid) {
                this.tls_accid = tls_accid;
            }

            public String getTls_photo() {
                return tls_photo;
            }

            public void setTls_photo(String tls_photo) {
                this.tls_photo = tls_photo;
            }
        }


    }
}
