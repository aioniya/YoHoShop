package com.example.myapplication.mvp.model.entity;

public class UiBean {
    String title;
    boolean flag;

    @Override
    public String toString() {
        return "UiBean{" +
                "title='" + title + '\'' +
                ", flag=" + flag +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public UiBean(String title, boolean flag) {
        this.title = title;
        this.flag = flag;
    }
}
