package com.gaoyanpeng.baidumusic.music;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by 高研鹏 on 2016/11/23.
 */

public class MusicAdapter extends FragmentPagerAdapter {
    public MusicAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return MusicTabInfo.getMusicInfo().get(position).getF();
    }

    @Override
    public int getCount() {
        return MusicTabInfo.getMusicInfo().size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return MusicTabInfo.getMusicInfo().get(position).getTitle();
    }
}
