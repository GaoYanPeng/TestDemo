package com.gaoyanpeng.musicbaidu.activity.toolstow;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by 高研鹏 on 2016/12/8.
 */

public class PopViewPagerAdapter extends PagerAdapter {
    private ArrayList<View> mViewArrayList;

    public PopViewPagerAdapter setViewArrayList(ArrayList<View> viewArrayList) {
        mViewArrayList = viewArrayList;
        return this;
    }

    @Override
    public int getCount() {
        return mViewArrayList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mViewArrayList.get(position));
        return mViewArrayList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
