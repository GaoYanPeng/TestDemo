package com.gaoyanpeng.baidumusic.activity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.gaoyanpeng.baidumusic.R;
import com.gaoyanpeng.baidumusic.activity.chrunItemsongbean.ChrunItenSongBean;
import com.gaoyanpeng.baidumusic.activity.toolstow.NetHelper;
import com.gaoyanpeng.baidumusic.activity.toolstow.NetListener;
import com.gaoyanpeng.baidumusic.activity.toolstow.PopViewPagerAdapter;
import com.gaoyanpeng.baidumusic.api.AllApi;
import com.gaoyanpeng.baidumusic.music.musicfragment.crunch.crunitem.CrunItemFm;
import com.gaoyanpeng.baidumusic.music.musicfragment.crunch.CrunchFm;
import com.gaoyanpeng.baidumusic.music.musicfragment.recommended.RecomAdapter;
import com.gaoyanpeng.baidumusic.music.musicfragment.recommended.secondaryinterface.SingerFm;
import com.gaoyanpeng.baidumusic.tool.NetTool;
import com.gaoyanpeng.baidumusic.tool.onHttpCallBack;
import com.gaoyanpeng.baidumusic.tools.VolleySingleton;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.ArrayList;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

public class MainActivity extends SupportActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    //---------------------------------
    private ImageView start, stop, gengduo, next, heard;
    private ProgressBar mProgressBar;
    private RelativeLayout mRelativeLayout;
    private MusucService.MusicBinder mBinder;
    private Intent mIntent;
    private TextView name, singName;
    private int songPos;
    private ServiceConnection sc = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mBinder = (MusucService.MusicBinder) iBinder;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
    private IntentFilter mFilter, mChrunFilter, mChrunItemFmId, mRECFourOpen;
    private String mNames;
    private String mSing;
    private int mProgress;
    private PopupWindow mPopupWindow;
    //    private String mId;
    private ArrayList<String> mId;
    private ViewPager mPopViewPager;
    private String mChrunSong;
    private View mView;
    //-----pop中的控件---------------------------------------------
    private ImageView mPopReturn, mPopPlay, mPopStop, mPopPrevious, mPopNext, mPopTowPic, mPopTowList;
    private ImageView mPopShare, mPopRoundsingle, mOrderNormal, mPopRandomNormal;
    private TextView mPopSongName, mPopName;
    private SeekBar mPopSeekBar;
    private ArrayList<View> mViewArrayList;
    private PopViewPagerAdapter mPopViewPagerAdapter;
    private View mOneView, mThreeView, mTowView;

    private ChrunItenSongBean mChrunItenSongBean;
    private String mSongUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setFragmentAnimator(new FragmentAnimator(R.anim.h_fragment_enter, R.anim.h_fragment_exit));
        if (savedInstanceState == null) {
            loadRootFragment(R.id.my_fm_id, new MainFragment());

        }
        SharedPreferences OUR = getSharedPreferences("single", MODE_PRIVATE);
        int Mode = OUR.getInt("1", -1);
        if (OUR == null || Mode == -1) {
            SharedPreferences sharedPreferences = getSharedPreferences("single", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("1", 1);
            editor.commit();
        }


        initView();
        initData();
    }

    public void initView() {
        name = (TextView) findViewById(R.id.main_song_name);
        singName = (TextView) findViewById(R.id.main_name);
        start = (ImageView) findViewById(R.id.bt_minibar_play_normal);
        stop = (ImageView) findViewById(R.id.bt_minibar_stop_normal);
        heard = (ImageView) findViewById(R.id.main_small_title);

        //-------------pop-------------------
        mView = getLayoutInflater().inflate(R.layout.main_pop, null);
        initPopView();
        stop.setOnClickListener(this);
        start.setOnClickListener(this);
        start.setClickable(false);

        gengduo = (ImageView) findViewById(R.id.bt_minibar_playinglist_normal);
        gengduo.setOnClickListener(this);
        next = (ImageView) findViewById(R.id.bt_minibar_next_normal);
        next.setOnClickListener(this);
        mId = new ArrayList<>();
        mRelativeLayout = (RelativeLayout) findViewById(R.id.recy_layout);
        mRelativeLayout.setOnClickListener(this);
        //去掉标题栏上的通知栏
        // mRelativeLayout.setSystemUiVisibility(View.INVISIBLE );
        mProgressBar = (ProgressBar) findViewById(R.id.main_pro_bar);


        //---------------------------------------------------------------------------------
        mIntent = new Intent(this, MusucService.class);
        startService(mIntent);
//
        mFilter = new IntentFilter();
        mFilter.addAction(MusucService.ACTION_PROGRESS);
        registerReceiver(mReceiver, mFilter);
        //
        mChrunFilter = new IntentFilter();
        mChrunFilter.addAction(CrunchFm.URL);
        registerReceiver(mReChrun, mChrunFilter);
        //
        mChrunItemFmId = new IntentFilter();
        mChrunItemFmId.addAction(CrunItemFm.ID);
        registerReceiver(mChrunItID, mChrunItemFmId);
        // TODO: 2016/12/6 在音乐页的适配器中第二行行布局点击每一行发广播通知 Activity 打开 fragment
        mRECFourOpen = new IntentFilter();
        mRECFourOpen.addAction(RecomAdapter.OpenOneFm);

        registerReceiver(mOpenOneFm, mRECFourOpen);
        bindService(mIntent, sc, Context.BIND_AUTO_CREATE);

    }

    private void initPopView() {
        mPopupWindow = new PopupWindow(mView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        mPopViewPagerAdapter = new PopViewPagerAdapter();
        mPopReturn = (ImageView) mView.findViewById(R.id.return_normal_new);
        mPopReturn.setOnClickListener(this);
        mPopPlay = (ImageView) mView.findViewById(R.id.play_normal_new);
        mPopPlay.setOnClickListener(this);
        mPopStop = (ImageView) mView.findViewById(R.id.pause_normal_new);
        mPopStop.setOnClickListener(this);
        mPopSeekBar = (SeekBar) mView.findViewById(R.id.seek_pop_main);
        mPopSeekBar.setOnSeekBarChangeListener(this);
        mPopPrevious = (ImageView) mView.findViewById(R.id.previous_normal_new);
        mPopPrevious.setOnClickListener(this);
        mPopNext = (ImageView) mView.findViewById(R.id.next_normal_new);
        mPopNext.setOnClickListener(this);
        mPopTowList = (ImageView) mView.findViewById(R.id.previous_normal_new);
        mPopTowList.setOnClickListener(this);
        mOrderNormal = (ImageView) mView.findViewById(R.id.order_normal_new);
        mOrderNormal.setOnClickListener(this);
        mPopRoundsingle = (ImageView) mView.findViewById(R.id.pop_roundsingle);
        mPopRoundsingle.setOnClickListener(this);
        mPopRandomNormal = (ImageView) mView.findViewById(R.id.pop_random_normal);
        mPopRandomNormal.setOnClickListener(this);
        SharedPreferences OUR = getSharedPreferences("single", MODE_PRIVATE);
        int mode = OUR.getInt("1", -1);
        if (mode == 1) {
            mOrderNormal.setImageResource(R.drawable.playage_pressed);

        } else if (mode == 2) {
            mPopRoundsingle.setImageResource(R.drawable.roundsing_pressed);

        } else if (mode == 3) {
            mPopRandomNormal.setImageResource(R.drawable.random_pressed);
        }

        mViewArrayList = new ArrayList<>();
        mPopViewPager = (ViewPager) mView.findViewById(R.id.pop_view_pager);
        mOneView = getLayoutInflater().inflate(R.layout.pop_view_one, null);
        mTowView = getLayoutInflater().inflate(R.layout.pop_view_tow, null);
        mThreeView = getLayoutInflater().inflate(R.layout.pop_view_three, null);
        mViewArrayList.add(mOneView);
        mViewArrayList.add(mTowView);
        mViewArrayList.add(mThreeView);
        mPopViewPagerAdapter.setViewArrayList(mViewArrayList);
        mPopViewPager.setAdapter(mPopViewPagerAdapter);
        mPopViewPager.setCurrentItem(1);
        mPopupWindow.setAnimationStyle(R.style.AnimationFade);
        mPopupWindow.setFocusable(true);
        mPopShare = (ImageView) mView.findViewById(R.id.share_normal_new);
        mPopShare.setOnClickListener(this);
        //pop 第二个 View
        mPopTowPic = (ImageView) mTowView.findViewById(R.id.pop_tow_pic);
        mPopSongName = (TextView) mTowView.findViewById(R.id.pop_tow_song_name);
        mPopName = (TextView) mTowView.findViewById(R.id.pop_name);

    }


    protected void initData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
// 透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    //
    @Override
    protected void onResume() {
        super.onResume();
        bindService(mIntent, sc, Context.BIND_AUTO_CREATE);
        IntentFilter filter = new IntentFilter();
        filter.addAction(MusucService.ACTION_PROGRESS);
        registerReceiver(mReceiver, filter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_minibar_play_normal:

                start.setVisibility(View.GONE);
                stop.setVisibility(View.VISIBLE);
                mPopPlay.setVisibility(View.GONE);
                mPopStop.setVisibility(View.VISIBLE);
                mBinder.playStop();
                break;
            case R.id.bt_minibar_stop_normal:
                mBinder.playStop();
                start.setVisibility(View.VISIBLE);
                stop.setVisibility(View.GONE);
                mPopPlay.setVisibility(View.VISIBLE);
                mPopStop.setVisibility(View.GONE);
                break;
            case R.id.bt_minibar_next_normal:
                heard.setImageResource(R.mipmap.default_album_playing_new);
                mBinder.playNext();
                start.setClickable(true);
                start.setVisibility(View.GONE);
                stop.setVisibility(View.VISIBLE);
                break;
            case R.id.recy_layout:
                // 创建PopupWindow实例,200,LayoutParams.MATCH_PARENT分别是宽度和高度
                // 设置动画效果
                mPopupWindow.showAtLocation(v, Gravity.BOTTOM, 0, 0);

                break;
            //---------------pop---------------------
            case R.id.return_normal_new:
                //关闭抽屉
                mPopupWindow.dismiss();
                break;
            //开始
            case R.id.play_normal_new:
                start.setVisibility(View.GONE);       //隐藏 pop里的开始键
                mPopPlay.setVisibility(View.GONE);    //隐藏  Main里的开始键
                stop.setVisibility(View.VISIBLE);     //显示 Main里的暂停键
                mPopStop.setVisibility(View.VISIBLE);  //显示 pop里的暂停键 
                mBinder.playStop();
                break;
            //暂停
            case R.id.pause_normal_new:
                mBinder.playStop();
                start.setVisibility(View.VISIBLE);      //显示 pop里的开始键
                mPopPlay.setVisibility(View.VISIBLE);   //显示  Main里的开始键
                stop.setVisibility(View.GONE);          //隐藏 Main里的暂停键
                mPopStop.setVisibility(View.GONE);      //隐藏 pop里的暂停键
                break;
            //上一曲
            case R.id.previous_normal_new:
                heard.setImageResource(R.mipmap.default_album_playing_new);
                mBinder.playLast();//播放上一曲
                start.setVisibility(View.GONE);       //隐藏 pop里的开始键
                mPopPlay.setVisibility(View.GONE);    //隐藏  Main里的开始键
                stop.setVisibility(View.VISIBLE);     //显示 Main里的暂停键
                mPopStop.setVisibility(View.VISIBLE);  //显示 pop里的暂停键 
                break;
            //下一曲
            case R.id.next_normal_new:
                heard.setImageResource(R.mipmap.default_album_playing_new);
                mBinder.playNext();//播放下一曲
                mPopPlay.setVisibility(View.GONE);   //隐藏 pop里的开始键
                start.setVisibility(View.GONE);      //隐藏  Main里的开始键
                stop.setVisibility(View.VISIBLE);    //显示 Main里的暂停键
                mPopStop.setVisibility(View.VISIBLE);//显示 pop里的暂停键
                break;
            case R.id.share_normal_new:
                //分享
                showShare();
                break;
//顺序播放
            case R.id.order_normal_new:
                // TODO: 2016/12/9 用 SP 存录  在服务里取
                SharedPreferences sharedPreferences = getSharedPreferences("single", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("1", 2);
                editor.commit();
                // TODO: 2016/12/9 点击的时候 隐藏顺序,随机  显示单曲 
                mPopRoundsingle.setVisibility(View.VISIBLE);//单曲
                mOrderNormal.setVisibility(View.GONE);//顺序
                mPopRandomNormal.setVisibility(View.GONE);//随机
                break;
            //单曲
            case R.id.pop_roundsingle:
                // TODO: 2016/12/9   点击的时候 隐藏单曲,顺序  显示随机
                SharedPreferences ROU = getSharedPreferences("single", MODE_PRIVATE);
                SharedPreferences.Editor editor1 = ROU.edit();
                editor1.putInt("1", 3);
                editor1.commit();
                Log.d("MainActivity", "dfghjk");
                mPopRoundsingle.setVisibility(View.GONE);//单曲
                mOrderNormal.setVisibility(View.GONE);//顺序
                mPopRandomNormal.setVisibility(View.VISIBLE);//随机
                break;
            case R.id.pop_random_normal:
                // TODO: 2016/12/9   点击的时候 隐藏单曲,随机  显示顺序
                SharedPreferences random = getSharedPreferences("single", MODE_PRIVATE);
                SharedPreferences.Editor editor2 = random.edit();
                editor2.putInt("1", 1);
                editor2.commit();
                mPopRoundsingle.setVisibility(View.GONE);//单曲
                mOrderNormal.setVisibility(View.VISIBLE);//顺序
                mPopRandomNormal.setVisibility(View.GONE);//随机
                break;
        }
    }


    //-----------------------------------------------------------------------------------------
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action) {
                case MusucService.ACTION_PROGRESS:
                    mProgress = intent.getIntExtra("progress", 0);
                    mNames = intent.getStringExtra("n");
                    mSing = intent.getStringExtra("s");
                    Log.d("MainActivity", "-->" + mNames);
                    name.setText(mNames);
                    mPopName.setText(mNames);
                    mPopSongName.setText(mSing);
                    singName.setText(mSing);
                    mProgressBar.setProgress(mProgress);
                    mPopSeekBar.setProgress(mProgress);
                    break;
            }
        }
    };
    // TODO: 2016/12/9 歌单详情点击开启 Fragment 同时传来一个data  和一个图片 
    private BroadcastReceiver mReChrun = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            CrunItemFm crunItemFm = new CrunItemFm();
            crunItemFm.setUrl(intent.getStringExtra("data"));
            crunItemFm.setImage(intent.getStringExtra("image"));
            start(crunItemFm);
        }
    };
    private BroadcastReceiver mChrunItID = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, final Intent intent) {
            mId = intent.getStringArrayListExtra("id");
            Log.d("MainActivity", "mId:" + mId);
            songPos = intent.getIntExtra("position", 100);
            String songId = mId.get(songPos);
            Log.d("MainActivity", songId);
            start.setVisibility(View.GONE);
            stop.setVisibility(View.VISIBLE);
            mPopPlay.setVisibility(View.GONE);
            mPopStop.setVisibility(View.VISIBLE);
            mChrunSong = AllApi.URL_Music_Q + mId.get(songPos) + AllApi.URL_Music_H;
            NetHelper.MyRequest(mChrunSong, new NetListener<String>() {
                @Override
                public void successListener(String response) {
                    response = response.substring(1, response.length() - 2);
                    Gson gson = new Gson();
                    mChrunItenSongBean = gson.fromJson(response, ChrunItenSongBean.class);
                    Intent intentUrl = new Intent("crunsong");
                    mSongUrl = mChrunItenSongBean.getBitrate().getFile_link();
                    String name = mChrunItenSongBean.getSonginfo().getAuthor();
                    String songName = mChrunItenSongBean.getSonginfo().getAlbum_title();
                    Picasso.with(getBaseContext()).load(mChrunItenSongBean.getSonginfo().getPic_radio()).into(heard);
                    //pop 里第二个 View
                    Picasso.with(getBaseContext()).load(mChrunItenSongBean.getSonginfo().getPic_radio()).into(mPopTowPic);
                    mPopSongName.setText(songName);
                    mPopName.setText(name);

                    intentUrl.putExtra("name", name);
                    intentUrl.putExtra("urlSong", mSongUrl);
                    Log.d("MainActivity", mSongUrl);
                    intentUrl.putExtra("songName", songName);
                    sendBroadcast(intentUrl);
                    mProgress = intent.getIntExtra("progress", 0);
                    mProgressBar.setProgress(mProgress);
                    mPopSeekBar.setProgress(mProgress);
                }

                @Override
                public void errorListener(VolleyError error) {

                }
            });
        }
    };
    private BroadcastReceiver mOpenOneFm = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            SingerFm msingerFm = new SingerFm();
            start(msingerFm);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除
        unbindService(sc);
        unregisterReceiver(mReceiver);
        unregisterReceiver(mReChrun);
        unregisterReceiver(mChrunItID);
        unregisterReceiver(mOpenOneFm);
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (fromUser) {
            mBinder.seekTo(progress);
            seekBar.setProgress(progress);
        }

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        //mBinder.getMiedPlayer().seekTo(seekBar.getProgress());

    }

    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
//关闭sso授权
        oks.disableSSOWhenAuthorize();

// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("好的音乐就在百度音乐");
// titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl("http://music.baidu.com/song/266322598?pst=sug");
// text是分享文本，所有平台都需要这个字段
        oks.setText("让我们尽情的享受吧");
// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
// url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
// comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
// site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(this.getString(R.string.app_name));
// siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");
// 启动分享GUI
        oks.show(this);
    }
}