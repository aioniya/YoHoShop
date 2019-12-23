package com.example.myapplication.mvp.model.entity;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class GuanZhuBean {
    String pic;
    String name;
    String guanzhu;

    @Override
    public String toString() {
        return "GuanZhuBean{" +
                "pic='" + pic + '\'' +
                ", name='" + name + '\'' +
                ", guanzhu='" + guanzhu + '\'' +
                '}';
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGuanzhu() {
        return guanzhu;
    }

    public void setGuanzhu(String guanzhu) {
        this.guanzhu = guanzhu;
    }

    public GuanZhuBean(String pic, String name, String guanzhu) {
        this.pic = pic;
        this.name = name;
        this.guanzhu = guanzhu;
    }
}
