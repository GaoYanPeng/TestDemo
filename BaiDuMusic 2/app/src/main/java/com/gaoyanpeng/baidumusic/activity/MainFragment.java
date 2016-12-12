package com.gaoyanpeng.baidumusic.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.gaoyanpeng.baidumusic.R;
import com.gaoyanpeng.baidumusic.loin.LoinActivity;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by 高研鹏 on 2016/12/3.
 */

public class MainFragment extends SupportFragment implements View.OnClickListener {
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private MainAdapter mMainAdapter;
    private DrawerLayout mDrawLayout;
    private ImageView mImageView;
    private Animation mAnimation;
    private ImageView mImageDrawClose;
    private RelativeLayout mLoin,mMyMessageLayout;
    private IntentFilter mCloseLoinInF ;
    //---登录后
  private RelativeLayout mLoinH,mDrCloseLoin;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fm_main,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }
    private void initView() {
        mViewPager = (ViewPager) getView().findViewById(R.id.main_vp);
        mTabLayout = (TabLayout)getView(). findViewById(R.id.main_tab);
        mMainAdapter = new MainAdapter(getChildFragmentManager());
        mDrawLayout = (DrawerLayout)getActivity(). findViewById(R.id.activity_main);
        mImageView = (ImageView) getView(). findViewById(R.id.main_tab_normal);
        mImageView.setOnClickListener(this);
//
        mLoin = (RelativeLayout) getActivity().findViewById(R.id.draw_include).findViewById(R.id.loin_layout);
        mLoin.setOnClickListener(this);
        mImageDrawClose = (ImageView) getActivity().findViewById(R.id.image_draw_close);
        mImageDrawClose.setOnClickListener(this);
        mMyMessageLayout = (RelativeLayout) getActivity().findViewById(R.id.my_message_layout);
        mMyMessageLayout.setOnClickListener(this);


      //登录后
        mLoinH = (RelativeLayout) getActivity().findViewById(R.id.loin_layout_H);
        mDrCloseLoin = (RelativeLayout) getActivity().findViewById(R.id.dr_close_loin);
        mDrCloseLoin.setOnClickListener(this);
        mCloseLoinInF = new IntentFilter();
        mCloseLoinInF.addAction(LoinActivity.LOIN);
        getActivity().registerReceiver(mCloseLoinBR,mCloseLoinInF);
    }
    private void initData() {
        mViewPager.setAdapter(mMainAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setSelectedTabIndicatorColor(0x00000000);
        mTabLayout.setTabTextColors(0x99ffffff, Color.rgb(0xff, 0xff, 0xff));
        // TODO: 2016/12/6  设置 viewpager 当前位置 这个方法一定要在setAdapter之后
        mViewPager.setCurrentItem(1);
        mDrawLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(_mActivity, "我是 DrawerLayout", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_tab_normal:
                // TODO: 2016/12/6 这是动画
                mAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.in_lefttoright);
                // TODO: 2016/12/6 点击的时候让 Draw 显示同事打开抽屉 打开的同时设置动画
                mDrawLayout.setVisibility(View.VISIBLE);
                mDrawLayout.openDrawer(Gravity.RIGHT);
                mDrawLayout.setAnimation(mAnimation);
                // TODO: 2016/12/6 监听 DrawLayout
                mDrawLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        // TODO: 2016/11/22 不详
                    }
                    @Override
                    public void onDrawerOpened(View drawerView) {
                        // TODO: 2016/11/22 打开
                    }
                    @Override
                    public void onDrawerClosed(View drawerView) {
                        // TODO: 2016/11/22 关闭
                        mDrawLayout.setVisibility(View.GONE);
                        mDrawLayout.closeDrawer(Gravity.RIGHT);
                        mDrawLayout.setAnimation(mAnimation);
                    }
                    @Override
                    public void onDrawerStateChanged(int newState) {
                        // TODO: 2016/11/22 开始
                    }
                });
                break;
            case R.id.image_draw_close:
                mAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.on_lefttoright);
                mDrawLayout.closeDrawer(Gravity.RIGHT);
                mDrawLayout.setAnimation(mAnimation);
                mDrawLayout.setVisibility(View.GONE);
                break;
            case R.id.loin_layout:
                Intent intent = new Intent(getContext(),LoinActivity.class);
                startActivity(intent);
                break;
            case R.id.my_message_layout:
            break;
            case R.id.dr_close_loin:
                mDrCloseLoin.setVisibility(View.GONE);
                mLoin.setVisibility(View.VISIBLE);
                mLoinH.setVisibility(View.GONE);
                Toast.makeText(_mActivity, "已退出", Toast.LENGTH_SHORT).show();
                break;
        }
    }
    private BroadcastReceiver mCloseLoinBR = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            mDrCloseLoin.setVisibility(View.VISIBLE);
            mLoin.setVisibility(View.GONE);
            mLoinH.setVisibility(View.VISIBLE);
            mDrawLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
                @Override
                public void onDrawerSlide(View drawerView, float slideOffset) {
                    // TODO: 2016/11/22 不详
                }
                @Override
                public void onDrawerOpened(View drawerView) {
                    // TODO: 2016/11/22 打开
                }
                @Override
                public void onDrawerClosed(View drawerView) {
                    // TODO: 2016/11/22 关闭
                    mDrawLayout.setVisibility(View.GONE);
                    mDrawLayout.closeDrawer(Gravity.RIGHT);
                    mDrawLayout.setAnimation(mAnimation);
                }
                @Override
                public void onDrawerStateChanged(int newState) {
                    // TODO: 2016/11/22 开始
                }
            });



        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(mCloseLoinBR);

    }
}
