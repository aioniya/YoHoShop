package com.example.myapplication.mvp.model.entity;

import java.util.List;

public class OrderEntity extends BaseEntity {

    /**
     * ordernum : 1234567890
     * values : [{"goods_id":"1","car_path":"/goods_img/goods_img_2.jpg","shop_name":"hehe","shop_color":"1","shop_size":"1","shop_num":"1","shop_price":"1"},{"goods_id":"1","car_path":"/goods_img/goods_img_2.jpg","shop_name":"hehe","shop_color":"1","shop_size":"1","shop_num":"1","shop_price":"1"},{"goods_id":"1","car_path":"/goods_img/goods_img_2.jpg","shop_name":"hehe","shop_color":"1","shop_size":"1","shop_num":"1","shop_price":"1"}]
     */

    private String ordernum;
    private List<ValuesBean> values;

    public String getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(String ordernum) {
        this.ordernum = ordernum;
    }

    public List<ValuesBean> getValues() {
        return values;
    }

    public void setValues(List<ValuesBean> values) {
        this.values = values;
    }

    public static class ValuesBean {
        /**
         * goods_id : 1
         * car_path : /goods_img/goods_img_2.jpg
         * shop_name : hehe
         * shop_color : 1
         * shop_size : 1
         * shop_num : 1
         * shop_price : 1
         */

        private String goods_id;
        private String car_path;
        private String shop_name;
        private String shop_color;
        private String shop_size;
        private String shop_num;
        private String shop_price;

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getCar_path() {
            return car_path;
        }

        public void setCar_path(String car_path) {
            this.car_path = car_path;
        }

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }

        public String getShop_color() {
            return shop_color;
        }

        public void setShop_color(String shop_color) {
            this.shop_color = shop_color;
        }

        public String getShop_size() {
            return shop_size;
        }

        public void setShop_size(String shop_size) {
            this.shop_size = shop_size;
        }

        public String getShop_num() {
            return shop_num;
        }

        public void setShop_num(String shop_num) {
            this.shop_num = shop_num;
        }

        public String getShop_price() {
            return shop_price;
        }

        public void setShop_price(String shop_price) {
            this.shop_price = shop_price;
        }
    }
}
