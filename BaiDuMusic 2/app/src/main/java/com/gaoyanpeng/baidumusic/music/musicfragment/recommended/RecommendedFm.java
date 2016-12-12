package com.gaoyanpeng.baidumusic.music.musicfragment.recommended;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.gaoyanpeng.baidumusic.R;
import com.gaoyanpeng.baidumusic.api.AllApi;
import com.gaoyanpeng.baidumusic.besa.BaseFragment;

import com.gaoyanpeng.baidumusic.tools.VolleySingleton;

/**
 * Created by 高研鹏 on 2016/11/23.
 */
public class RecommendedFm extends BaseFragment {
    private RecyclerView mRecyclerView;
    private RecomAdapter mRecomAdapter;

    @Override
    protected int initLayout() {
        return R.layout.music_fm_recom;
    }

    @Override
    protected void initView() {
        mRecyclerView = getFindView(R.id.music_fm_recom_rv);
        mRecomAdapter = new RecomAdapter(getActivity());
       mRecomAdapter.setFragmentManager(getFragmentManager());
        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayout.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
    }


    @Override
    protected void initData() {
        VolleySingleton.addRequest(AllApi.URL_Music, RecommBean.class, new Response.Listener<RecommBean>() {
            @Override
            public void onResponse(RecommBean response) {
                mRecomAdapter.setRecommBean(response);
                      mRecyclerView.setAdapter(mRecomAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.getMessage();
            }
        });
    }
}
