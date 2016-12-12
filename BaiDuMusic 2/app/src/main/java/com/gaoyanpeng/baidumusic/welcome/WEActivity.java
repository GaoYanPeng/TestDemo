package com.gaoyanpeng.baidumusic.welcome;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.gaoyanpeng.baidumusic.R;
import com.gaoyanpeng.baidumusic.besa.BaseActivity;

/**
 * Created by 高研鹏 on 2016/11/30.
 */
public class WEActivity extends BaseActivity {
    private WebView mWebView;
    private WebViewClient mWebViewClient;
    private WebSettings mWebSettings;
    private TextView mTitle;
    private ImageView mClose;


    @Override
    protected int getLayout() {
        return R.layout.activity_wb;
    }

    @Override
    public void initView() {
        mWebView = findView(R.id.aty_wb);
        mWebViewClient = new WebViewClient();
        mWebView.setWebViewClient(mWebViewClient);
        mWebView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        mWebSettings = mWebView.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setCacheMode(mWebSettings.LOAD_CACHE_ELSE_NETWORK);
        mTitle = findView(R.id.we_title_view);
        mClose = findView(R.id.we_close_view);
        Intent intent = getIntent();
        String recEit = intent.getStringExtra("recRiyUrl");
        String url = intent.getStringExtra("url");
        String text = intent.getStringExtra("text");
        mTitle.setText(text);

        mWebView.loadUrl(url);
        mWebView.loadUrl(recEit);
        mClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {

    }
}
