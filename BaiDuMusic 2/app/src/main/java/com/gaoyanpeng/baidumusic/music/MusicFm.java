package com.gaoyanpeng.baidumusic.music;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaoyanpeng.baidumusic.R;
import com.gaoyanpeng.baidumusic.besa.BaseFragment;

/**
 * Created by 高研鹏 on 2016/11/22.
 */
public class MusicFm extends BaseFragment{
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private MusicAdapter mMusicAdapter;

    @Override
    protected int initLayout() {
        return R.layout.fm_music;
    }

    @Override
    protected void initView() {
        mTabLayout = getFindView(R.id.music_tab);
        mViewPager = getFindView(R.id.music_vp);
        mMusicAdapter = new MusicAdapter(getChildFragmentManager());
    }


    @Override
    protected void initData() {
        getMusicTabInfo();

    }

    private void getMusicTabInfo() {
        mViewPager.setAdapter(mMusicAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setSelectedTabIndicatorColor(0xff00b4ff);
        mTabLayout.setTabTextColors(Color.rgb(0x00,0x00,0x00), Color.rgb(0x49,0xB3,0xE8));
    }
}
