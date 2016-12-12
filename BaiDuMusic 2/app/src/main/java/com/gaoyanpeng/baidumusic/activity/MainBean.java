package com.gaoyanpeng.baidumusic.activity;

/**
 * Created by 高研鹏 on 2016/11/24.
 */

public class MainBean {
    private String title, name;
    private int immage;
    private String url;

    public MainBean(String name, String title, String url) {
        this.name = name;
        this.title = title;
        this.url = url;
    }

    public MainBean(int immage, String name, String title, String url) {
        this.immage = immage;
        this.name = name;
        this.title = title;
        this.url = url;
    }

    @Override
    public String toString() {
        return "MainBean{" +
                "immage=" + immage +
                ", title='" + title + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }


    public int getImmage() {
        return immage;
    }

    public MainBean setImmage(int immage) {
        this.immage = immage;
        return this;
    }

    public String getName() {
        return name;
    }

    public MainBean setName(String name) {
        this.name = name;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public MainBean setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public MainBean setUrl(String url) {
        this.url = url;
        return this;
    }
}
