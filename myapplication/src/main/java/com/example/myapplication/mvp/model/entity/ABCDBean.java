package com.example.myapplication.mvp.model.entity;

public class ABCDBean {
    String letter;
    String name;

    @Override
    public String toString() {
        return "ABCDBean{" +
                "letter='" + letter + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ABCDBean(String letter, String name) {
        this.letter = letter;
        this.name = name;
    }
}
