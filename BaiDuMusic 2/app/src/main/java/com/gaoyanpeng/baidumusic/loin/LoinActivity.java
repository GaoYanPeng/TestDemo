package com.gaoyanpeng.baidumusic.loin;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.gaoyanpeng.baidumusic.R;
import com.gaoyanpeng.baidumusic.besa.BaseActivity;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;

/**
 * Created by 高研鹏 on 2016/12/9.
 */
public class LoinActivity extends BaseActivity implements View.OnClickListener, PlatformActionListener {
    private EditText mPhone, mPressword;
    private TextView mLoinTV;
    public static final String LOIN = "loin";
    private ImageView mPP,mWeiBo;
    private Intent mIntent;

    @Override
    protected int getLayout() {
        return R.layout.aty_loin;
    }

    @Override
    public void initView() {

        ShareSDK.initSDK(this);
        mPhone = findView(R.id.ed_phone_name);
        mPressword = findView(R.id.ed_presswored);
        mLoinTV = findView(R.id.loin_aty_tv);
        mLoinTV.setOnClickListener(this);
        mPP = findView(R.id.qq_share);
        mPP.setOnClickListener(this);
        mWeiBo = findView(R.id.webo_share);
        mWeiBo.setOnClickListener(this);

    }

    @Override
    protected void initData() {
       StringBuffer phnone = new StringBuffer(mPhone.getText().toString());

        String pressword = mPressword.getText().toString();
        if (phnone.length()>3){
            mLoinTV.setTextColor(Color.WHITE);
        }else {
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.loin_aty_tv:
                mIntent = new Intent(LOIN);
                sendBroadcast(mIntent);
                finish();
               break;
            case R.id.qq_share:
                Platform sina = ShareSDK.getPlatform(QQ.NAME);
                sina.setPlatformActionListener(this);
                sina.showUser(null);
                sina.authorize();
                authorize(sina);
                mIntent = new Intent(LOIN);
                sendBroadcast(mIntent);
                break;
            case R.id.webo_share:
                Platform sinaqq = ShareSDK.getPlatform(SinaWeibo.NAME);
                sinaqq.setPlatformActionListener(this);
                sinaqq.showUser(null);
                //sinaqq.authorize();
                mIntent = new Intent(LOIN);
                sendBroadcast(mIntent);
                break;
        }

    }
    private void authorize(Platform plat) {
        if (plat == null) {
            //popupOthers();
            return;
        }

        plat.setPlatformActionListener(this);
        //关闭SSO授权
        plat.SSOSetting(true);
        plat.showUser(null);
    }


    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {

    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {

    }

    @Override
    public void onCancel(Platform platform, int i) {

    }
}
