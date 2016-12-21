package com.gaoyanpeng.musicbaidu.welcome;

import android.content.Intent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.gaoyanpeng.musicbaidu.R;
import com.gaoyanpeng.musicbaidu.activity.MainActivity;
import com.gaoyanpeng.musicbaidu.api.AllApi;
import com.gaoyanpeng.musicbaidu.besa.BaseActivity;
import com.gaoyanpeng.musicbaidu.tools.VolleySingleton;
import com.squareup.picasso.Picasso;

/**
 * Created by 高研鹏 on 2016/11/29.
 */

public class WelcomeActivity extends BaseActivity {
    // TODO: 2016/12/6 欢迎页的图片
    private ImageView mImageView;
    // TODO: 2016/12/6 跳转
    private TextView mImTv;
    // TODO: 2016/12/6 动画
    private Animation mAnimation;
    private RelativeLayout mRelativeLayout;
    @Override
    protected int getLayout() {
        return R.layout.adtivity_we;
    }

    @Override
    public void initView() {
        mImageView = findView(R.id.image_we);
        mRelativeLayout = findView(R.id.branang_We);
        mRelativeLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        mImageView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        mImTv = findView(R.id.we_tv_intent);
        mAnimation = AnimationUtils.loadAnimation(this, R.anim.we_lefttoright);

        mImTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                intent.setAction(String.valueOf(mAnimation));
                finish();
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        VolleySingleton.addRequest(AllApi.URL_Welcome, WelcomeBean.class, new Response.Listener<WelcomeBean>() {
            @Override
            public void onResponse(final WelcomeBean response) {
                //当这两个数据都不为空的时候显示图片 否者隐藏
                if (response.getMaterial_map() != null) {
                    Picasso.with(getApplicationContext()).load(response.getMaterial_map().getNumber().getDisplay_content().getPicture()).into(mImageView);
                    mRelativeLayout.setVisibility(View.GONE);
                } else {
                    mImageView.setVisibility(View.GONE);
                    mRelativeLayout.setVisibility(View.VISIBLE);
                }
                mImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(WelcomeActivity.this, WelcomeWbActivity.class);
                        String url = response.getMaterial_map().getNumber().getDisplay_content().getWeburl();
                        intent.putExtra("url", url);
                        finish();
                        startActivity(intent);
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



//    private void hideSystemUI() {
//        mImageView.setSystemUiVisibility(
//                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                        | View.SYSTEM_UI_FLAG_FULLSCREEN//显示小标栏目变为透明
//        );
//    }
//    private void showSystemUI(){
//        mImageView.setSystemUiVisibility(
//                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                        |View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                        |View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//
//        );
//    }
}
