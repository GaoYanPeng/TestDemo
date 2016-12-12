package com.gaoyanpeng.baidumusic.music.musicfragment.video;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.VideoView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.gaoyanpeng.baidumusic.R;
import com.gaoyanpeng.baidumusic.activity.MainAdapter;
import com.gaoyanpeng.baidumusic.api.AllApi;
import com.gaoyanpeng.baidumusic.besa.BaseActivity;
import com.gaoyanpeng.baidumusic.tools.VolleySingleton;

/**
 * Created by 高研鹏 on 2016/12/2.
 */
public class VideoActivity extends BaseActivity {

    private WebView mWebView;
    private WebViewClient mWebViewClient;
    private WebSettings mWebSettings;

    @Override
    protected int getLayout() {
        return R.layout.aty_video;
    }

    @Override
    public void initView() {
        mWebView = findView(R.id.video_we_view);
        mWebViewClient = new WebViewClient();
        mWebView.setWebViewClient(mWebViewClient);
        mWebView.setFocusable(true);
        mWebSettings = mWebView.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setCacheMode(mWebSettings.LOAD_CACHE_ELSE_NETWORK);
        //隐藏标题之上所有的
        mWebView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        Intent intent = getIntent();
        String Path = intent.getStringExtra("num");
        final String Paths = AllApi.URL_Before + Path + AllApi.URL_After;
        Log.d("VideoActivity", Path);

        VolleySingleton.addRequest(Paths, VideoItemBean.class, new Response.Listener<VideoItemBean>() {
            @Override
            public void onResponse(VideoItemBean response) {
                mWebView.loadUrl(response.getResult().getFiles().getNum().getFile_link());
            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                error.getMessage();
            }
        });

    }

    //物理返回键返回的时候直接退出
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            if (mWebView.canGoBack()){
                mWebView.goBack();
            }else{
                System.exit(0);
            }
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    protected void initData() {

    }

}
