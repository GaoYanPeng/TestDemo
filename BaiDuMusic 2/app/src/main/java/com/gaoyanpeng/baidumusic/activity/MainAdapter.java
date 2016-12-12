package com.gaoyanpeng.baidumusic.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.gaoyanpeng.baidumusic.activity.MainTabInfo;

/**
 * Created by 高研鹏 on 2016/11/22.
 */

public class MainAdapter extends FragmentPagerAdapter {
    public MainAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return MainTabInfo.getMainTabInfo().get(position).getF();
    }

    @Override
    public int getCount() {
        return MainTabInfo.getMainTabInfo().size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return MainTabInfo.getMainTabInfo().get(position).getTitle();
    }
}
