package com.example.myapplication.mvp.model.entity;

public class LoginAfterBean {

    Integer image;
    String title;

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LoginAfterBean(Integer image, String title) {
        this.image = image;
        this.title = title;
    }
}
