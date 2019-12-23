package com.example.myapplication.mvp.model.entity;

public class DuShuBean {
    float one;
    float two;
    float three;
    float four;

    @Override
    public String toString() {
        return "DuShuBean{" +
                "one=" + one +
                ", two=" + two +
                ", three=" + three +
                ", four=" + four +
                '}';
    }

    public float getOne() {
        return one;
    }

    public void setOne(float one) {
        this.one = one;
    }

    public float getTwo() {
        return two;
    }

    public void setTwo(float two) {
        this.two = two;
    }

    public float getThree() {
        return three;
    }

    public void setThree(float three) {
        this.three = three;
    }

    public float getFour() {
        return four;
    }

    public void setFour(float four) {
        this.four = four;
    }

    public DuShuBean(float one, float two, float three, float four) {
        this.one = one;
        this.two = two;
        this.three = three;
        this.four = four;
    }
}
