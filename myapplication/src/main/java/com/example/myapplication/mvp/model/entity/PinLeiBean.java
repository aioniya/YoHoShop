package com.example.myapplication.mvp.model.entity;

public class PinLeiBean {

    String title;
    String pic;
    String pictitle;
    int type;

    @Override
    public String toString() {
        return "PinLeiBean{" +
                "title='" + title + '\'' +
                ", pic='" + pic + '\'' +
                ", pictitle='" + pictitle + '\'' +
                ", type=" + type +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPictitle() {
        return pictitle;
    }

    public void setPictitle(String pictitle) {
        this.pictitle = pictitle;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public PinLeiBean(String title, String pic, String pictitle, int type) {
        this.title = title;
        this.pic = pic;
        this.pictitle = pictitle;
        this.type = type;
    }
    public PinLeiBean(String title,int type) {
        this.title = title;
        this.type = type;
    }
    public PinLeiBean(String pic, String pictitle, int type) {
        this.pic = pic;
        this.pictitle = pictitle;
        this.type = type;
    }
    public PinLeiBean() {

    }
}
