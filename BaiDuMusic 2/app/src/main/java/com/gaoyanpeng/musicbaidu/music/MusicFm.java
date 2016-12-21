package com.gaoyanpeng.musicbaidu.music;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.gaoyanpeng.musicbaidu.R;
import com.gaoyanpeng.musicbaidu.besa.BaseFragment;

/**音乐界面
 * Created by 高研鹏 on 2016/11/22.
 */
public class MusicFm extends BaseFragment{
    //这里只有 TabLayout ViewPager
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
