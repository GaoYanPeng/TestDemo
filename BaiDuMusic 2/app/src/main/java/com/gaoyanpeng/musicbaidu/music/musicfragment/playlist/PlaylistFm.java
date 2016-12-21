package com.gaoyanpeng.musicbaidu.music.musicfragment.playlist;

import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.gaoyanpeng.musicbaidu.R;
import com.gaoyanpeng.musicbaidu.api.AllApi;
import com.gaoyanpeng.musicbaidu.besa.BaseFragment;
import com.gaoyanpeng.musicbaidu.tools.VolleySingleton;

/**
 * Created by 高研鹏 on 2016/11/23.
 */
public class PlaylistFm extends BaseFragment implements View.OnClickListener, OnClick {
    private RecyclerView mRecyclerView;
    private PlayListAdapter mPlayListAdapter;
    private LinearLayout mLinearLayout;
    private Animation mAnimation, mAnimation1;
    private TextView host, hostNew;

    //定义两个变量  分别是上跟下的原始的值
    private boolean isUp = false;
    private boolean isDown = false;

    @Override
    protected int initLayout() {
        return R.layout.music_fm_play_list;
    }

    @Override
    protected void initView() {
        mRecyclerView = getFindView(R.id.music_play_relv);
        mPlayListAdapter = new PlayListAdapter(getContext());
        mLinearLayout = getFindView(R.id.music_lis_layout);
        host = getFindView(R.id.play_list_host);
        hostNew = getFindView(R.id.music_play_new);
        hostNew.setOnClickListener(this);
        host.setOnClickListener(this);
        mPlayListAdapter.setOnClick(this);
        final GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        //监听RecyclerView 滑动事件用addOnScrollListener();
        /**
         * 其中setOnScrollListener已经过时（@deprecated），
         * 建议使用addOnScrollListener。
         * 如果在源码中没有addOnScrollListener方法，
         * 可能你的版本过旧，请升级recyclerview包
         * 在滚动过程中，此监听器会回调两个方法。
           onScrollStateChanged：滚动状态变化时回调
           onScrolled：滚动时回调
           DX：水平滚动距离  DY> 0时为向上滚动
           Dy：垂直滚动距离    DY <0时为向下滚动
         */
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy>0){
                    if (!isUp) {
                        mAnimation = null;
                        mAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.recvcler_up_lefttoright);
                        mAnimation.setFillAfter(true);
                        mLinearLayout.setAnimation(mAnimation);
                        mLinearLayout.setVisibility(View.GONE);
                        isUp = true;
                        isDown = false;
                    }
                }else {
                    if (!isDown){
                    mAnimation1 = null;
                    mAnimation1 = AnimationUtils.loadAnimation(getContext(), R.anim.recvcler_down);
                    mAnimation1.setFillAfter(true);
                    mLinearLayout.setAnimation(mAnimation1);
                    mLinearLayout.setVisibility(View.VISIBLE);
                        isDown = true;
                        isUp = false;
                    }
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });


    }

    @Override
    protected void initData() {
        getHottest();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play_list_host:
                host    .setTextColor(Color.rgb(0x68, 0x75, 0xff));
                hostNew .setTextColor(Color.rgb(0xab, 0xbb, 0xbb));
                getHottest();
                break;
            case R.id.music_play_new:
                hostNew.setTextColor(Color.rgb(0x68, 0x75, 0xff));
                host.setTextColor(Color.rgb(0xab, 0xbb, 0xbb));
                getLatest();
                break;
        }
    }

    private void getHottest() {
        VolleySingleton.addRequest(AllApi.URL_Music_Play_List, PlayLBean.class, new Response.Listener<PlayLBean>() {
            @Override
            public void onResponse(final PlayLBean response) {
                mPlayListAdapter.setPlayLBean(response);
                mRecyclerView.setAdapter(mPlayListAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.getMessage();
            }
        });
    }

    private void getLatest() {
        VolleySingleton.addRequest(AllApi.URL_Music_Play_List_New, PlayLBean.class, new Response.Listener<PlayLBean>() {
            @Override
            public void onResponse(PlayLBean response) {

                mPlayListAdapter.setPlayLBean(response);
                mRecyclerView.setAdapter(mPlayListAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.getMessage();
            }
        });
    }


    @Override
    public void onClick(int pos) {
        Toast.makeText(mContext, "pos:" + pos, Toast.LENGTH_SHORT).show();
    }
}
