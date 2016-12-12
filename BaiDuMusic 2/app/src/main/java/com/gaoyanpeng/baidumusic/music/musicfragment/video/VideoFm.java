package com.gaoyanpeng.baidumusic.music.musicfragment.video;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.gaoyanpeng.baidumusic.R;
import com.gaoyanpeng.baidumusic.api.AllApi;
import com.gaoyanpeng.baidumusic.besa.BaseFragment;
import com.gaoyanpeng.baidumusic.tools.VolleySingleton;


/**
 * Created by 高研鹏 on 2016/11/23.
 */
public class VideoFm extends BaseFragment implements View.OnClickListener {
    private RecyclerView mRecyclerView;
    private VideoAdapter mVideoAdapter;
    private GridLayoutManager mManager;
    private boolean isUp1 = false;
    private boolean isDown1 = false;
    private LinearLayout mLinearLayout;
    private TextView musicVideoNew, playVideoHost;
    private Animation mMAnimation;


    @Override
    protected int initLayout() {
        return R.layout.music_fm_video;
    }

    @Override
    protected void initView() {

        mRecyclerView = getFindView(R.id.music_video_relv);
        mVideoAdapter = new VideoAdapter(getContext());
        mManager = new GridLayoutManager(getContext(), 2);
        mRecyclerView.setLayoutManager(mManager);
        mLinearLayout = getFindView(R.id.music_video_layout);
        musicVideoNew = getFindView(R.id.music_video_new);
        playVideoHost = getFindView(R.id.play_video_host);
        playVideoHost.setOnClickListener(this);
        musicVideoNew.setOnClickListener(this);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy>0){
                    if (!isUp1) {
                        mMAnimation = null;
                        mMAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.recvcler_up_lefttoright);
                        mMAnimation.setFillAfter(true);
                        mLinearLayout.setAnimation(mMAnimation);
                        mLinearLayout.setVisibility(View.GONE);
                        isUp1 = true;
                        isDown1 = false;
                    }
                }else {
                    if (!isDown1){
                      mMAnimation = null;
                      Animation  mAnimation1 = AnimationUtils.loadAnimation(getContext(), R.anim.recvcler_down);
                        mAnimation1.setFillAfter(true);
                        mLinearLayout.setAnimation(mAnimation1);
                        mLinearLayout.setVisibility(View.VISIBLE);
                        isDown1 = true;
                        isUp1= false;
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

        getVideoNew();

    }

    private void getVideoHost() {
        VolleySingleton.addRequest(AllApi.URL_Music_Video_Host, VideoBean.class, new Response.Listener<VideoBean>() {
            @Override
            public void onResponse(VideoBean response) {
                mVideoAdapter.setVideoBean(response);
                mRecyclerView.setAdapter(mVideoAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.getMessage();
            }
        });
    }

    private void getVideoNew() {
        VolleySingleton.addRequest(AllApi.URL_Music_Video, VideoBean.class, new Response.Listener<VideoBean>() {
            @Override
            public void onResponse(VideoBean response) {
                Log.d("VideoFm", "response:" + response.getResult().getMv_list().size());
                mVideoAdapter.setVideoBean(response);
                mRecyclerView.setAdapter(mVideoAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.getMessage();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.music_video_new:
                musicVideoNew.setTextColor(Color.rgb(0x68, 0x75, 0xff));
                playVideoHost.setTextColor(Color.rgb(0xab, 0xbb, 0xbb));
                getVideoHost();
                break;
            case R.id.play_video_host:
                playVideoHost.setTextColor(Color.rgb(0x68, 0x75, 0xff));
                musicVideoNew .setTextColor(Color.rgb(0xab, 0xbb, 0xbb));
               getVideoNew();
                break;
        }
    }
}
