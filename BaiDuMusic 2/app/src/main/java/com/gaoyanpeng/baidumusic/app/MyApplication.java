package com.gaoyanpeng.baidumusic.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;


/**
 * Created by leisure on 16/5/17.
 */
public class MyApplication extends Application {
    private static Context mContext;


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    //对外提供一个方法 这个方法就是让别的类获取自己的context对象
    public static Context getContext() {
        return mContext;
    }

}
