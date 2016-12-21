package com.gaoyanpeng.musicbaidu.activity;

/**
 * Created by 高研鹏 on 2016/11/30.
 */

public class MusicBean {
    private String singer,name,data,url;
    private int siz;

    public MusicBean() {
    }

    public MusicBean(String name, String singer, int siz, String url) {
        this.name = name;
        this.singer = singer;
        this.siz = siz;
        this.url = url;
    }

    @Override
    public String toString() {
        return "MusicBean{" +
                "data='" + data + '\'' +
                ", SingerFm='" + singer + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", siz=" + siz +
                '}';
    }

    public String getData() {
        return data;
    }

    public MusicBean setData(String data) {
        this.data = data;
        return this;
    }

    public String getName() {
        return name;
    }

    public MusicBean setName(String name) {
        this.name = name;
        return this;
    }

    public String getSinger() {
        return singer;
    }

    public MusicBean setSinger(String singer) {
        this.singer = singer;
        return this;
    }

    public int getSiz() {
        return siz;
    }

    public MusicBean setSiz(int siz) {
        this.siz = siz;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public MusicBean setUrl(String url) {
        this.url = url;
        return this;
    }
}
