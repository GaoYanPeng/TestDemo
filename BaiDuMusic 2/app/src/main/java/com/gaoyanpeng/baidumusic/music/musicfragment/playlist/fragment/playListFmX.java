package com.gaoyanpeng.baidumusic.music.musicfragment.playlist.fragment;

import android.widget.ListView;

import com.gaoyanpeng.baidumusic.R;
import com.gaoyanpeng.baidumusic.besa.BaseFragment;

/**
 * Created by 高研鹏 on 2016/12/1.
 */

public class playListFmX extends BaseFragment {
    private ListView mListView;
    private PlayListXBean mPlayListXBean;
    @Override
    protected int initLayout() {
        return R.layout.fm_pl_x;
    }

    public playListFmX setPlayListXBean(PlayListXBean playListXBean) {
        mPlayListXBean = playListXBean;
        return this;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}
