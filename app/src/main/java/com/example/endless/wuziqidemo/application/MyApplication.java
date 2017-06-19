package com.example.endless.wuziqidemo.application;

import android.app.Application;

import cn.bmob.v3.Bmob;

/**
 * Created by Administrator on 2017/6/19.
 */

public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(this,"3ecd6fff16e9c8587312907a692d0b7b");


    }
}
