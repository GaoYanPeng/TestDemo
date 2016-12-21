package com.gaoyanpeng.musicbaidu.music.musicfragment.crunch.crunitem;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.gaoyanpeng.musicbaidu.R;
import com.gaoyanpeng.musicbaidu.api.AllApi;
import com.gaoyanpeng.musicbaidu.besa.BaseXFragment;
import com.gaoyanpeng.musicbaidu.music.musicfragment.crunch.CrunchOnClick;
import com.gaoyanpeng.musicbaidu.tool.DividerItemDecoration;
import com.gaoyanpeng.musicbaidu.tools.VolleySingleton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by 高研鹏 on 2016/12/3.
 */

public class CrunItemFm extends BaseXFragment {

    private String url;
    private String image;
    private CrunItemAdapter mCrunItemAdapter;
    private RecyclerView mRecyclerView;
    private ImageView mPic;
    private TextView num;

    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private com.gaoyanpeng.musicbaidu.tool.DividerItemDecoration mDividerItemDecoration;


    public CrunItemFm setUrl(String url) {
        this.url = url;
        return this;
    }

    public CrunItemFm setImage(String image) {
        this.image = image;
        return this;
    }

    @Override
    protected int initLayout() {
        return R.layout.fm_item_crun;
    }
    @Override
    protected void initView() {
        mPic = getFindView(R.id.crun_imageView);
        mCollapsingToolbarLayout = getFindView(R.id.coll_toolBar);
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        mCollapsingToolbarLayout.setCollapsedTitleGravity(Gravity.VERTICAL_GRAVITY_MASK);

        mRecyclerView = getFindView(R.id.fm_item_crun_ryl);
        mDividerItemDecoration = new com.gaoyanpeng.musicbaidu.tool.DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST);
        mRecyclerView.addItemDecoration(mDividerItemDecoration);
        mRecyclerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        mCrunItemAdapter = new CrunItemAdapter(getContext());
        Picasso.with(getContext()).load(image).into(mPic);
        num = getFindView(R.id.new_crun_item);
        getCrunItemFm();

    }

    private void getCrunItemFm() {
        VolleySingleton.addRequest(url, CrunItemBean.class, new Response.Listener<CrunItemBean>() {
            @Override
            public void onResponse(final CrunItemBean response) {
                LinearLayoutManager manager = new LinearLayoutManager(getContext());
                mRecyclerView.setLayoutManager(manager);
                mCrunItemAdapter.setCrunItemBean(response);

                mRecyclerView.setAdapter(mCrunItemAdapter);
                mCollapsingToolbarLayout.setTitle(response.getBillboard().getName());
                num.setText("播放全部/" + response.getSong_list().size() + "首歌");
                Log.d("CrunItemFm", "response:" + response.getSong_list().size());
                mCrunItemAdapter.setCrunchOnClick(new CrunchOnClick() {
                    @Override
                    public void CrOnClick(int pos) {
                        mCrunItemAdapter.setColoce(pos);

                        //发个广播吧 Id 发过去
                      ArrayList<String > id ;
                        id = new ArrayList<>();
                        for (int i = 0; i < response.getSong_list().size(); i++) {
                            id.add(response.getSong_list().get(i).getSong_id());
                        }
                        Intent intent = new Intent(AllApi.CrunItemID);
                        intent.putStringArrayListExtra("id",id);
                        intent.putExtra("position",pos);
                        mContext.sendBroadcast(intent);

                    }
                });
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.getMessage();
            }
        });
    }

    @Override
    protected void initData() {

    }


}
