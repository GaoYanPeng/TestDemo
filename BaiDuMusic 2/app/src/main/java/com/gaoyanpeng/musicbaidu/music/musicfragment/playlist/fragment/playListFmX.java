package com.gaoyanpeng.musicbaidu.music.musicfragment.playlist.fragment;

import android.widget.ListView;

import com.gaoyanpeng.musicbaidu.R;
import com.gaoyanpeng.musicbaidu.besa.BaseFragment;

/**
 * Created by 高研鹏 on 2016/12/live_one.
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
