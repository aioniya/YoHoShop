package com.example.myapplication.mvp.model.entity;

public class PinLeiTitileBean {

    String title;
    int type;

    @Override
    public String toString() {
        return "PinLeiTitileBean{" +
                "title='" + title + '\'' +
                ", type=" + type +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public PinLeiTitileBean(String title, int type) {
        this.title = title;
        this.type = type;
    }
}
