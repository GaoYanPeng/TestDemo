package com.gaoyanpeng.musicbaidu.activity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.gaoyanpeng.musicbaidu.R;
import com.gaoyanpeng.musicbaidu.activity.chrunItemsongbean.ChrunItenSongBean;
import com.gaoyanpeng.musicbaidu.activity.toolstow.NetHelper;
import com.gaoyanpeng.musicbaidu.activity.toolstow.NetListener;
import com.gaoyanpeng.musicbaidu.activity.toolstow.PopViewPagerAdapter;
import com.gaoyanpeng.musicbaidu.activity.toolstow.lrc.DefaultLrcBuilder;
import com.gaoyanpeng.musicbaidu.activity.toolstow.lrc.ILrcBuilder;
import com.gaoyanpeng.musicbaidu.activity.toolstow.lrc.ILrcViewListener;
import com.gaoyanpeng.musicbaidu.activity.toolstow.lrc.LrcRow;
import com.gaoyanpeng.musicbaidu.activity.toolstow.lrc.LrcView;
import com.gaoyanpeng.musicbaidu.api.AllApi;
import com.gaoyanpeng.musicbaidu.db.HousDBTools;
import com.gaoyanpeng.musicbaidu.db.HousPerson;
import com.gaoyanpeng.musicbaidu.mian.LickFm;
import com.gaoyanpeng.musicbaidu.mian.MianFm;
import com.gaoyanpeng.musicbaidu.music.musicfragment.crunch.crunitem.CrunItemFm;
import com.gaoyanpeng.musicbaidu.music.musicfragment.crunch.CrunchFm;
import com.gaoyanpeng.musicbaidu.music.musicfragment.recommended.RecomAdapter;
import com.gaoyanpeng.musicbaidu.music.musicfragment.recommended.secondaryinterface.SingerFm;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.io.UnsupportedEncodingException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * 主页显示的 Activity
 */
public class MainActivity extends SupportActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, CompoundButton.OnCheckedChangeListener {
    //---------------------------------
    // TODO: 2016/12/14 Activity 最下方的播放音乐的 开始  停止 下一曲 头像
    private ImageView start, stop, gengduo, next, heard;
    /**
     * 在 Activity 里的进度条
     */
    private ProgressBar mProgressBar;
    /**
     * 最下方在所有控件之上的布局
     */
    private RelativeLayout mRelativeLayout;
    /**
     * 服务里的 Binder 在 Activity 的作用的是通过 Activity 控件来控制服务里的方法
     * 列如:上一曲下一曲
     */
    private MusucService.MusicBinder mBinder;
    /**
     * Intent 用来开启服务的
     */
    private Intent mIntent;
    /**
     * name :获取歌手的名字, singName: 获取歌曲的名字
     * 设置在 Pop 上的
     */
    private TextView name, singName;
    /**
     * 定义这个变量是用来拼接歌曲的网址
     */
    private int songPos;
    /**
     * 应用组件  可以调动bindService() 绑定到一个服务
     * 他会返回来一个用来与 service 交互的 IBinder;
     * 从客户端绑定到一个service，必须：
     * 实现ServiceConnection.然后重写两个回调的方法
     */
    private ServiceConnection sc = new ServiceConnection() {
        /**
         * 1.onServiceConnected()
         系统调用这个来传送在service的onBind()中返回的IBinder．
         * @param componentName
         * @param iBinder
         */
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mBinder = (MusucService.MusicBinder) iBinder;
        }

        /**
         * 2.OnServiceDisconnected()
         Android系统在同service的连接意外丢失时调用这个．比如当service崩溃了或被强杀了．当客户端解除绑定时，这个方法不会被调用．
         * @param componentName
         */
        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
    /**
     * 定义过滤器
     * 当Intent被传递出后，Android系统会找到适合的activity，
     * service,或者是多个broadcast receiver去响应这个intent。,
     * 这三种情况不会存在重叠的部分，它们相互独立，互不干扰。（
     * 调用Context.startActivity()后 intent只会被相应的activity接收到）
     */
    private IntentFilter mFilter, mChrunFilter, mChrunItemFmId, mRECFourOpen, mFMLick;
    //获取歌曲的名字与歌手名
    private String mNames, mSing;
    //定义一个变量用来进行方便的操作
    private int mProgress;
    //pop
    private PopupWindow mPopupWindow;
    //    private String mId;
    //歌曲 id 集合
    private ArrayList<String> mId;
    //pop 的 ViewPager
    private ViewPager mPopViewPager;
    private String mChrunSong;
    //Pop 的布局 View
    private View mView;
    //-----pop中的控件---------------------------------------------
    private ImageView mPopReturn, mPopPlay, mPopStop, mPopPrevious, mPopNext, mPopTowPic, mPopTowList;
    //分享   和3中播放模式
    private ImageView mPopShare, mPopRoundsingle, mOrderNormal, mPopRandomNormal;
    //pop 的控件
    private TextView mPopSongName, mPopName;
    //Pop里的进度条
    private SeekBar mPopSeekBar;
    //pop3个视图的集合
    private ArrayList<View> mViewArrayList;
    //pop 适配器
    private PopViewPagerAdapter mPopViewPagerAdapter;
    //pop 里的3歌视图
    private View mOneView, mThreeView, mTowView;
    //pop 的 lick  收藏按钮
    private CheckBox mPopCheckBox;
    //歌曲的 bean
    private ChrunItenSongBean mChrunItenSongBean;
    //整理好的歌曲网址
    private String mSongUrl;
    //获取歌曲的一系列内容
    private String mSongId;
    private String mName;
    private String mSongName;
    private String mPic_radio;
    //数据库的 bean 类
    private HousPerson mHousPerson;
    //收索歌词的控件
    private Button btn_Lrc;
    //歌词的自定义 view
    private LrcView mLrcView;
    private Timer mTimer;
    //更新歌词的定时任务
    private TimerTask mTask;
    //更新歌词的频率，每秒更新一次
    private int mPalyTimerDuration = 1000;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //给 替换的Fragment加载进出动画  这个动画是 SupportActivity 自带的 都是写好的
        setFragmentAnimator(new FragmentAnimator(R.anim.h_fragment_enter, R.anim.h_fragment_exit));
        //判断savedInstanceState == null 时候替换一个 fragment
        if (savedInstanceState == null) {
            loadRootFragment(R.id.my_fm_id, new MainFragment());
        }
        //开始的时候用 SP 一下来判断前一次关闭的是播放模式是哪个
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

        //=============PoP===================
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


        //================================================================================
        mIntent = new Intent(this, MusucService.class);
        startService(mIntent);
// TODO: 2016 服务里的广播
        mFilter = new IntentFilter();
        mFilter.addAction(AllApi.ACTION_PROGRESS);
        registerReceiver(mReceiver, mFilter);
        //是用来开启这个fragment的
        mChrunFilter = new IntentFilter();
        mChrunFilter.addAction(AllApi.CrunchFmURL);
        registerReceiver(mReChrun, mChrunFilter);
        //
        mChrunItemFmId = new IntentFilter();
        mChrunItemFmId.addAction(AllApi.CrunItemID);
        registerReceiver(mChrunItID, mChrunItemFmId);
        // TODO: 2016/12/6 在音乐页的适配器中第二行行布局点击每一行发广播通知 Activity 打开 fragment
        mRECFourOpen = new IntentFilter();
        mRECFourOpen.addAction(RecomAdapter.OpenOneFm);
        registerReceiver(mOpenOneFm, mRECFourOpen);
        //
        mFMLick = new IntentFilter();
        mFMLick.addAction(AllApi.LIKE);
        //使用registerReceiver()来注册 BR
        registerReceiver(mLickBR, mFMLick);

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
        mPopCheckBox = (CheckBox) mView.findViewById(R.id.like_normal_new);
        mPopCheckBox.setOnCheckedChangeListener(this);
        //播放模式
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
        //pop 第三个 View
        btn_Lrc = (Button) mThreeView.findViewById(R.id.pop_three_search_lrc_btn);
        btn_Lrc.setOnClickListener(this);
        mLrcView = (LrcView) mThreeView.findViewById(R.id.right_lrcView);

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
        filter.addAction(AllApi.ACTION_PROGRESS);
        registerReceiver(mReceiver, filter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_minibar_play_normal:
                btn_Lrc.setVisibility(View.VISIBLE);
                start.setVisibility(View.GONE);
                stop.setVisibility(View.VISIBLE);
                mPopPlay.setVisibility(View.GONE);
                mPopStop.setVisibility(View.VISIBLE);
                mBinder.playStop();
                break;
            case R.id.bt_minibar_stop_normal:
                mBinder.playStop();
                btn_Lrc.setVisibility(View.VISIBLE);
                start.setVisibility(View.VISIBLE);
                stop.setVisibility(View.GONE);
                mPopPlay.setVisibility(View.VISIBLE);
                mPopStop.setVisibility(View.GONE);
                break;
            case R.id.bt_minibar_next_normal:
                btn_Lrc.setVisibility(View.VISIBLE);
                heard.setImageResource(R.mipmap.default_album_playing_new);
                mPopTowPic.setImageResource(R.mipmap.default_album_playing_new);
                mBinder.playNext();
                start.setClickable(true);
                start.setVisibility(View.GONE);
                mPopPlay.setVisibility(View.GONE);
                stop.setVisibility(View.VISIBLE);
                mPopStop.setVisibility(View.VISIBLE);
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
                btn_Lrc.setVisibility(View.VISIBLE);
                start.setVisibility(View.GONE);       //隐藏 pop里的开始键
                mPopPlay.setVisibility(View.GONE);    //隐藏  Main里的开始键
                stop.setVisibility(View.VISIBLE);     //显示 Main里的暂停键
                mPopStop.setVisibility(View.VISIBLE);  //显示 pop里的暂停键 
                mBinder.playStop();
                break;
            //暂停
            case R.id.pause_normal_new:
                btn_Lrc.setVisibility(View.VISIBLE);
                mBinder.playStop();
                start.setVisibility(View.VISIBLE);      //显示 pop里的开始键
                mPopPlay.setVisibility(View.VISIBLE);   //显示  Main里的开始键
                stop.setVisibility(View.GONE);          //隐藏 Main里的暂停键
                mPopStop.setVisibility(View.GONE);      //隐藏 pop里的暂停键
                break;
            //上一曲
            case R.id.previous_normal_new:
                btn_Lrc.setVisibility(View.VISIBLE);
                heard.setImageResource(R.mipmap.default_album_playing_new);
                mPopTowPic.setImageResource(R.mipmap.default_album_playing_new);
                mBinder.playLast();//播放上一曲
                start.setVisibility(View.GONE);       //隐藏 pop里的开始键
                mPopPlay.setVisibility(View.GONE);    //隐藏  Main里的开始键
                stop.setVisibility(View.VISIBLE);     //显示 Main里的暂停键
                mPopStop.setVisibility(View.VISIBLE);  //显示 pop里的暂停键 
                break;
            //下一曲
            case R.id.next_normal_new:
                btn_Lrc.setVisibility(View.VISIBLE);
                heard.setImageResource(R.mipmap.default_album_playing_new);
                mPopTowPic.setImageResource(R.mipmap.default_album_playing_new);
                mBinder.playNext();//播放下一曲
                mPopPlay.setVisibility(View.GONE);   //隐藏 pop里的开始键           11       11
                start.setVisibility(View.GONE);      //隐藏  Main里的开始键           11   11
                stop.setVisibility(View.VISIBLE);    //显示 Main里的暂停键              11
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
                Toast.makeText(this, "单曲播放", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(this, "随机播放", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(this, "顺序播放", Toast.LENGTH_SHORT).show();
                mPopRoundsingle.setVisibility(View.GONE);//单曲
                mOrderNormal.setVisibility(View.VISIBLE);//顺序
                mPopRandomNormal.setVisibility(View.GONE);//随机
                break;
            case R.id.pop_three_search_lrc_btn:
                btn_Lrc.setVisibility(View.GONE);
                if (mChrunItenSongBean.getSonginfo() != null) {
                    getLrc(mChrunItenSongBean.getSonginfo().getLrclink());
                } else {
                    btn_Lrc.setText("没有搜索到歌词");
                }
                Log.d("dgkhjkdfghsdjkl", mChrunItenSongBean.getSonginfo().getLrclink());
                break;
        }
    }

    private void getLrc(String lrclink) {
        NetHelper.MyRequest(lrclink, new NetListener<String>() {
            @Override
            public void successListener(String response) {
                //解决中文乱码问题
                String praseResult = null;
                try {
                    if (java.nio.charset.Charset.forName("ISO-8859-1").newEncoder().canEncode(response)) {
                        praseResult = new String(response.getBytes("ISO8859-1"), "utf-8"); // 解决中文乱码问题
                    } else {
                        praseResult = response;
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                String lrc = praseResult;

                //解析歌词构造器
                ILrcBuilder builder = new DefaultLrcBuilder();
                //解析歌词返回LrcRow集合
                List<LrcRow> rows = builder.getLrcRows(lrc);
                if (rows.size() > 0) {
                    //将得到的歌词集合传给mLrcView用来展示
                    mLrcView.setLrc(rows);
                    //开始播放歌曲并同步展示歌词
                    beginLrcPlay();

                    //设置自定义的LrcView上下拖动歌词时监听
                    mLrcView.setListener(new ILrcViewListener() {
                        //当歌词被用户上下拖动的时候回调该方法,从高亮的那一句歌词开始播放
                        public void onLrcSeeked(int newPosition, LrcRow row) {
                            //if (mPlayer != null) {
                            //mPlayer.seekTo((int) row.time);
                            mBinder.seekTo((int) row.time);
                            //}
                        }
                    });
                } else {
                    Toast.makeText(MainActivity.this, "歌词没有解析出来", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }
    //===================================================================

    /**
     * 开始播放歌曲并同步展示歌词
     */
    public void beginLrcPlay() {
        try {
            if (mTimer == null) {
                mTimer = new Timer();
                mTask = new LrcTask();
                mTimer.scheduleAtFixedRate(mTask, 0, mPalyTimerDuration);
            }
            mBinder.getMiedPlayer().setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    stopLrcPlay();
                }
            });
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }
    //===========================================

    /**
     * 展示歌曲的定时任务
     */
    class LrcTask extends TimerTask {
        @Override
        public void run() {
            //获取歌曲播放的位置
            final long timePassed = mBinder.getMiedPlayer().getCurrentPosition();
            MainActivity.this.runOnUiThread(new Runnable() {
                public void run() {
                    //滚动歌词
                    mLrcView.seekLrcToTime(timePassed);
                }
            });

        }
    }
//=================================================

    /**
     * 停止展示歌曲
     */
    public void stopLrcPlay() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }


    //-----------------------------------------------------------------------------------------
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action) {
                case AllApi.ACTION_PROGRESS:
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
            mSongId = mId.get(songPos);
            Log.d("MainActivity", mSongId);
            start.setVisibility(View.GONE);
            stop.setVisibility(View.VISIBLE);
            mPopPlay.setVisibility(View.GONE);
            mPopStop.setVisibility(View.VISIBLE);
            /**
             * 显示歌词点击事件
             */
            btn_Lrc.setVisibility(View.VISIBLE);
            mChrunSong = AllApi.URL_Music_Q + mId.get(songPos) + AllApi.URL_Music_H;
            //调用的是这回封装的方法
            NetHelper.MyRequest(mChrunSong, new NetListener<String>() {
                @Override
                public void successListener(String response) {
                    response = response.substring(1, response.length() - 2);
                    Gson gson = new Gson();
                    mChrunItenSongBean = gson.fromJson(response, ChrunItenSongBean.class);
                    Intent intentUrl = new Intent("crunsong");
                    mSongUrl = mChrunItenSongBean.getBitrate().getFile_link();
                    mName = mChrunItenSongBean.getSonginfo().getAuthor();
                    mSongName = mChrunItenSongBean.getSonginfo().getAlbum_title();
                    mPic_radio = mChrunItenSongBean.getSonginfo().getPic_radio();
                    Picasso.with(getBaseContext()).load(mChrunItenSongBean.getSonginfo().getPic_radio()).into(heard);
                    //pop 里第二个 View
                    Picasso.with(getBaseContext()).load(mChrunItenSongBean.getSonginfo().getPic_radio()).into(mPopTowPic);
                    mPopSongName.setText(mSongName);
                    mPopName.setText(mName);
                    //吧取下来的数据用 广播 到服务里在服务里进行一系列的逻辑
                    intentUrl.putExtra("name", mName);
                    intentUrl.putExtra("urlSong", mSongUrl);
                    Log.d("MainActivity", mSongUrl);
                    intentUrl.putExtra("songName", mSongName);
                    sendBroadcast(intentUrl);
                    //通过数据库来查询一个名字来进行判断
                    //当有的时候变为红心
                    //没有的时候恢复原来的
                    if (HousDBTools.getInstance().isSave(mName)) {
                        mPopCheckBox.setBackgroundResource(R.mipmap.bt_playpage_button_like_hl_new);
                    } else {
                        mPopCheckBox.setBackgroundResource(R.drawable.like_pressed);
                    }
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
    private BroadcastReceiver mLickBR = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            LickFm lickFm = new LickFm();
            start(lickFm);

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
        unregisterReceiver(mLickBR);
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

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            //用 CheckBox   它本身就带一个 boolean 类型
            //可以判断选中与没选中
            mPopCheckBox.setBackgroundResource(R.mipmap.bt_playpage_button_like_hl_new);
            mHousPerson = new HousPerson(null, mName, mSongName, mPic_radio);
            //吧数据都放在 Person里 在把 Person 添加到数据库
            HousDBTools.getInstance().insertPerson(mHousPerson);
            Log.d("MainActivity", mHousPerson.getSongName() + "-->" + mHousPerson.getName());
            for (HousPerson housPerson : HousDBTools.getInstance().queryAll()) {
                Log.d("ooooo", housPerson.getSongName());
            }
            Log.d("111111", "HousDBTools.getInstance().isSave(mHousPerson.getName()):" + HousDBTools.getInstance().isSave(mHousPerson.getName()));

        } else {
            mPopCheckBox.setBackgroundResource(R.drawable.like_pressed);
            HousDBTools.getInstance().deleteByName(mName);
        }
    }
}