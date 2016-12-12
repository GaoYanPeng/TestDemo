package com.gaoyanpeng.baidumusic.activity;

import android.support.v4.app.Fragment;

import com.gaoyanpeng.baidumusic.dynamic.DynamicFm;
import com.gaoyanpeng.baidumusic.LiveFm;
import com.gaoyanpeng.baidumusic.mian.MianFm;
import com.gaoyanpeng.baidumusic.music.MusicFm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 高研鹏 on 2016/11/22.
 */

public class MainTabInfo {
    private String title;
    private Fragment f;

    public MainTabInfo(Fragment f, String title) {
        this.f = f;
        this.title = title;
    }

    public Fragment getF() {
        return f;
    }

    public MainTabInfo setF(Fragment f) {
        this.f = f;
        return this;
    }



    public String getTitle() {
        return title;
    }

    public MainTabInfo setTitle(String title) {
        this.title = title;
        return this;
    }
    public static List<MainTabInfo> getMainTabInfo(){
        List<MainTabInfo> infos = new ArrayList<>();
        infos.add(new MainTabInfo(new MianFm(),"我的"));
        infos.add(new MainTabInfo(new MusicFm(),"音乐"));
        infos.add(new MainTabInfo(new DynamicFm(),"动态"));
        infos.add(new MainTabInfo(new LiveFm(),"直播"));
        return infos;

    }
}
