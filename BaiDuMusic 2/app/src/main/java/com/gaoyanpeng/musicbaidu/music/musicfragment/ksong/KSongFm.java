package com.gaoyanpeng.musicbaidu.music.musicfragment.ksong;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gaoyanpeng.musicbaidu.R;
import com.gaoyanpeng.musicbaidu.besa.BaseFragment;

/**
 * Created by 高研鹏 on 2016/11/23.
 */
public class KSongFm extends BaseFragment {


    @Override
    protected int initLayout() {
        return R.layout.music_fm_ksong;
    }

    @Override
    protected void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());

    }

    @Override
    protected void initData() {
        

    }
}
