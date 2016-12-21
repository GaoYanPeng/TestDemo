package com.gaoyanpeng.musicbaidu.welcome;

import android.content.Intent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.gaoyanpeng.musicbaidu.R;
import com.gaoyanpeng.musicbaidu.activity.MainActivity;
import com.gaoyanpeng.musicbaidu.besa.BaseActivity;

/**
 * Created by 高研鹏 on 2016/12/3.
 */
public class WelcomeWbActivity extends BaseActivity implements View.OnClickListener {
    private WebView mWebView;
    private WebViewClient mWebViewClient;
    private WebSettings mWebSettings;
    private TextView mTextView;
    private ImageView mImageView;

    @Override
    protected int getLayout() {
        return R.layout.aty_we_wb;
    }

    @Override
    public void initView() {
        mTextView = findView(R.id.bai_du);
        mTextView.setOnClickListener(this);
        mWebView = findView(R.id.we_wb_url);
        mImageView = findView(R.id.wb_close);
        mWebView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
        getImageOnClin();
        getWebView();

    }

    //WebView 的数据
    private void getWebView(){
        mWebViewClient = new WebViewClient();
        //设置焦点
        mWebView.setFocusable(true);
        mWebView.setWebViewClient(mWebViewClient);
        mWebSettings = mWebView.getSettings();
        //设置缓存路径
        mWebSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        mWebSettings.setJavaScriptEnabled(true);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        mWebView.loadUrl(url);
    }

    private void getImageOnClin() {
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeWbActivity.this, MainActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {

    }
//    View.SYSTEM_UI_FLAG_VISIBLE
//    默认标记
//    View.SYSTEM_UI_FLAG_LOW_PROFILE
//    低功耗模式, 会隐藏状态栏图标, 在4.0上可以实现全屏
//    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//    保持整个View稳定, 常跟bar 悬浮, 隐藏共用, 使View不会因为SystemUI的变化而做layout
//    View.SYSTEM_UI_FLAG_FULLSCREEN
//    状态栏隐藏
//    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//    状态栏上浮于Activity
//    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//    隐藏导航栏,
//            4.0 - 4.3如果使用这个属性,将会导致在下一次touch时候自动show出status跟navigation bar,源于系统clear掉其所有的状态
//    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//    导航栏上浮于Activity
//    View.SYSTEM_UI_FLAG_IMMERSIVE
//    Kitkat新加入的Flag, 沉浸模式, 可以隐藏掉status跟navigation bar, 并且在第一次会弹泡提醒, 它会覆盖掉之前两个隐藏bar的标记, 并且在bar出现的位置滑动可以呼出bar
//    View.SYSTEM_UI_FLAG_IMMERSIVE_STIKY
//    与上面唯一的区别是, 呼出隐藏的bar后会自动再隐藏掉
    private void hideSystemUI() {
        mTextView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN//状态栏上浮于Activity
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION//    隐藏导航栏, 4.0 - 4.3如果
                        // 使用这个属性,将会导致在下一次touch时候自动show出status跟navigation bar,
                        // 源于系统clear掉其所有的状态
                        | View.SYSTEM_UI_FLAG_FULLSCREEN//状态栏隐藏
        );
    }

    @Override
    public void onClick(View v) {
        hideSystemUI();
    }
}
