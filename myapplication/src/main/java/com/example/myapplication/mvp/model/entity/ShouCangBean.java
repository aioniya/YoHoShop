package com.example.myapplication.mvp.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class ShouCangBean implements Parcelable {

    String name;
    String pic;
    String price;

    protected ShouCangBean(Parcel in) {
        name = in.readString();
        pic = in.readString();
        price = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(pic);
        dest.writeString(price);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ShouCangBean> CREATOR = new Creator<ShouCangBean>() {
        @Override
        public ShouCangBean createFromParcel(Parcel in) {
            return new ShouCangBean(in);
        }

        @Override
        public ShouCangBean[] newArray(int size) {
            return new ShouCangBean[size];
        }
    };

    @Override
    public String toString() {
        return "ShouCangBean{" +
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

    public ShouCangBean(String name, String pic, String price) {
        this.name = name;
        this.pic = pic;
        this.price = price;
    }
}
