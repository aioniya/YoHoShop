package com.example.myapplication.mvp.model.entity;

import java.util.List;

public class YouHuiEntity extends BaseEntity{

    private List<ValuesBean> values;

    public List<ValuesBean> getValues() {
        return values;
    }

    public void setValues(List<ValuesBean> values) {
        this.values = values;
    }

    public static class ValuesBean {
        /**
         * coupon_id : 1
         * coupon_title : 换新季新品减50元
         * coupon_condition : 50
         * coupon_data : 1552221466
         * coupon_explain : 特例商品不支持优惠券
         * coupon_ufo : 0
         */

        private String coupon_id;
        private String coupon_title;
        private String coupon_condition;
        private String coupon_data;
        private String coupon_explain;
        private String coupon_ufo;
        private int count=0;

        @Override
        public String toString() {
            return "ValuesBean{" +
                    "coupon_id='" + coupon_id + '\'' +
                    ", coupon_title='" + coupon_title + '\'' +
                    ", coupon_condition='" + coupon_condition + '\'' +
                    ", coupon_data='" + coupon_data + '\'' +
                    ", coupon_explain='" + coupon_explain + '\'' +
                    ", coupon_ufo='" + coupon_ufo + '\'' +
                    ", count=" + count +
                    '}';
        }

        public String getCoupon_id() {
            return coupon_id;
        }

        public void setCoupon_id(String coupon_id) {
            this.coupon_id = coupon_id;
        }

        public String getCoupon_title() {
            return coupon_title;
        }

        public void setCoupon_title(String coupon_title) {
            this.coupon_title = coupon_title;
        }

        public String getCoupon_condition() {
            return coupon_condition;
        }

        public void setCoupon_condition(String coupon_condition) {
            this.coupon_condition = coupon_condition;
        }

        public String getCoupon_data() {
            return coupon_data;
        }

        public void setCoupon_data(String coupon_data) {
            this.coupon_data = coupon_data;
        }

        public String getCoupon_explain() {
            return coupon_explain;
        }

        public void setCoupon_explain(String coupon_explain) {
            this.coupon_explain = coupon_explain;
        }

        public String getCoupon_ufo() {
            return coupon_ufo;
        }

        public void setCoupon_ufo(String coupon_ufo) {
            this.coupon_ufo = coupon_ufo;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public ValuesBean(String coupon_id, String coupon_title, String coupon_condition, String coupon_data, String coupon_explain, String coupon_ufo, int count) {
            this.coupon_id = coupon_id;
            this.coupon_title = coupon_title;
            this.coupon_condition = coupon_condition;
            this.coupon_data = coupon_data;
            this.coupon_explain = coupon_explain;
            this.coupon_ufo = coupon_ufo;
            this.count = count;
        }
    }
    }
