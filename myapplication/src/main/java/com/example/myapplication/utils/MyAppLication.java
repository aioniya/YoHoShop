package com.example.myapplication.utils;

import android.app.Application;

import androidx.multidex.MultiDex;

public class MyAppLication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
    }
}
