package com.example.myapplication.mvp.model.entity;

public class FootBean {
    String name;
    String pic;
    String price;

    @Override
    public String toString() {
        return "FootBean{" +
                "name='" + name + '\'' +
                ", pic='" + pic + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public FootBean(String name, String pic, String price) {
        this.name = name;
        this.pic = pic;
        this.price = price;
    }
}
