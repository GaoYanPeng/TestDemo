package com.gaoyanpeng.musicbaidu.app;

import android.app.Application;
import android.content.Context;

import com.gaoyanpeng.musicbaidu.db.DaoMaster;
import com.gaoyanpeng.musicbaidu.db.DaoSession;


/**
 * Created by leisure on 16/5/17.
 */
public class MyApplication extends Application {
    private static Context mContext;
    private static DaoMaster sDaoMaster;
    private static DaoSession sDaoSession;


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    //对外提供一个方法 这个方法就是让别的类获取自己的context对象
    public static Context getContext() {
        return mContext;
    }

    public static DaoMaster getDaoMaster() {
        DaoMaster.DevOpenHelper helper =
                new DaoMaster.DevOpenHelper
                        (getContext(),"Person.db");
        //初始化Daoaster对象
        sDaoMaster = new DaoMaster(helper.getWritableDb());
        return sDaoMaster;
    }

    public static DaoSession getDaoSession() {
        if (sDaoSession == null){
            if (sDaoMaster == null){
                sDaoMaster = getDaoMaster();
            }
            //初始化DaoSession对象
            sDaoSession = sDaoMaster.newSession();
        }
        return sDaoSession;
    }
}
