package com.gaoyanpeng.baidumusic.music.musicfragment.ksong;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.gaoyanpeng.baidumusic.R;
import com.gaoyanpeng.baidumusic.besa.BaseFragment;
import com.gaoyanpeng.baidumusic.tools.VolleySingleton;

/**
 * Created by 高研鹏 on 2016/11/23.
 */
public class KSongFm extends BaseFragment {
    private RecyclerView mRecyclerView;

    @Override
    protected int initLayout() {
        return R.layout.music_fm_ksong;
    }

    @Override
    protected void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView = getFindView(R.id.k_song_reyl);
        mRecyclerView.setLayoutManager(manager);

    }

    @Override
    protected void initData() {
        

    }
}
