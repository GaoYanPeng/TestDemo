package com.gaoyanpeng.baidumusic.music;

import android.support.v4.app.Fragment;

import com.gaoyanpeng.baidumusic.music.musicfragment.crunch.CrunchFm;
import com.gaoyanpeng.baidumusic.music.musicfragment.video.VideoFm;
import com.gaoyanpeng.baidumusic.music.musicfragment.ksong.KSongFm;
import com.gaoyanpeng.baidumusic.music.musicfragment.playlist.PlaylistFm;
import com.gaoyanpeng.baidumusic.music.musicfragment.recommended.RecommendedFm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 高研鹏 on 2016/11/23.
 */

public class MusicTabInfo {
    private String title;
    private Fragment f;

    public MusicTabInfo(Fragment f, String title) {
        this.f = f;
        this.title = title;
    }

    public Fragment getF() {
        return f;
    }

    public MusicTabInfo setF(Fragment f) {
        this.f = f;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public MusicTabInfo setTitle(String title) {
        this.title = title;
        return this;
    }
    public static List<MusicTabInfo> getMusicInfo(){
        List<MusicTabInfo> infos = new ArrayList<>();
        infos.add(new MusicTabInfo(new RecommendedFm(),"推荐"));
        infos.add(new MusicTabInfo(new PlaylistFm(),"歌单"));
        infos.add(new MusicTabInfo(new CrunchFm(),"榜单"));
        infos.add(new MusicTabInfo(new VideoFm(),"视频"));
        infos.add(new MusicTabInfo(new KSongFm(),"K歌"));
        return infos;
    }
}
