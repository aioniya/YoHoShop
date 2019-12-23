package com.example.myapplication.mvp.model.entity;

public class UfoBean {

    String pic;
    String title;

    @Override
    public String toString() {
        return "UfoBean{" +
                "pic='" + pic + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UfoBean(String pic, String title) {
        this.pic = pic;
        this.title = title;
    }
}
