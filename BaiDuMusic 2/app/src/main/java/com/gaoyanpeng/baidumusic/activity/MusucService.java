package com.gaoyanpeng.baidumusic.activity;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.zip.Inflater;

/**
 * Created by dllo on 16/7/26.
 */
public class MusucService extends Service {
    public static final String ACTION_PROGRESS = "ACTION_PROGRESS";
    public static int playMode = 1;//1.单曲循环 2.列表循环 0.随机播放
    private IntentFilter mInflater;
    private Intent mIntent;
    private MediaPlayer mplayer;
    private ArrayList<MusicBean> musicList;
    //随机播放
    private Random mRandom = new Random();
    private ServiceConnection sc = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mMusicBinder = (MusicBinder) iBinder;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    //// TODO: 16/7/26
    private boolean isBind;
    private String mUrlSong;
    private MusicBinder mMusicBinder;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("MusucService", "ghjk23456");

        isBind = true;
        new Thread(new Runnable() {
            /**开个线程*/
            @Override
            public void run() {
                while (true) {
                    /**判断播放是发广播*/

                    if (mplayer != null && mplayer.isPlaying()) {
                        //获取当前歌曲的进度条进度
                        int position = mplayer.getCurrentPosition();
                        int duration = mplayer.getDuration();
                        //f*f=f才对
                        int progress = (int) (position * 100f / duration);
                        mIntent.putExtra("progress", progress);
                        sendBroadcast(mIntent);
                    }
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        return new MusicBinder();
    }


    //1
    @Override
    public void onCreate() {
        super.onCreate();
        mplayer = new MediaPlayer();
        Log.d("MusucService", "ghjk");
        /**设置歌曲播放完毕的监听*/
        mplayer.setOnCompletionListener(new OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                SharedPreferences OUR = getSharedPreferences("single", MODE_PRIVATE);
                int Mode = OUR.getInt("1", 1);
                if (Mode == 1) {
                    //顺序播放
                    Log.d("MusucService", "Mode:" + Mode);
                    MusucService.this.playNext();
                } else if (Mode == 2) {
                    //单曲
                    upPlayer();
                    Log.d("MusucService", "Mode:" + Mode);
                } else if (Mode == 3) {
                    //随机
                    play(getRandom());
                }

            }
        });
        //123
        /**准备结束的时候会调用找这个方法*/
        mplayer.setOnPreparedListener(new OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });
        musicList = new ArrayList<>();
        //定义广播,用来向Activity发送进度
        getMusicData();
        int number = musicList.size();
        Log.d("MusucService", "number:" + number);
        mIntent = new Intent(ACTION_PROGRESS);

        mIntent.putExtra("all", musicList.size());
        sendBroadcast(mIntent);
        mInflater = new IntentFilter();
        mInflater.addAction("crunsong");
        registerReceiver(mCurnSong, mInflater);


    }

    public void play(int position) {
        //6判断集合大小等于0 mplayer等于0 集合等于0 就不播
        if (mplayer == null || musicList == null || musicList.size() == 0) {
            return;
        }
        if (mplayer.isPlaying()) {
            mplayer.pause();
            mplayer.stop();
        }
        //重置MediaPlayer
        mplayer.reset();//2
        try {

            //为设置数据源//3/0可以写成一个变量填几就播放几
            mplayer.setDataSource(musicList.get(position).getUrl());
            //网络路径
            // mplayer.setDataSource(this,Uri.parse("http://sc1.111ttt.com/2016/1/11/16/204161628136.mp3"));

            //mplayer.prepareAsync();
            //缓存
            //缓存完才能开始
            mplayer.prepare();
            //5播放
            mplayer.start();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void seekTo(int progress) {
        //获取当前歌曲的总时长
        int duration = mplayer.getDuration();
        //计算出进度条拖动的比例
        float scale = progress / 100f;
        //得出歌曲应该播放的位置
        int position = (int) (duration * scale);
        mplayer.seekTo(position);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        isBind = false;
        return super.onUnbind(intent);
    }

    private int position;

    /**
     * 把MusicBinder的playNext()方法拿到MusucService服务里
     * public void onCreate()才能调用这个方法进行下一首
     */
    private void playNext() {
        //判断什时候++
        position++;
        if (position == musicList.size()) {
            position = 0;
        }
        mIntent.putExtra("n", musicList.get(position).getName());
        mIntent.putExtra("s", musicList.get(position).getSinger());
        sendBroadcast(mIntent);
        play(position);
    }

    //单曲
    public void upPlayer() {
        Log.d("反弹的风格的", position + "");
        play(position);

    }

    private int getRandom() {
        position = mRandom.nextInt(musicList.size());
        mIntent.putExtra("n", musicList.get(position).getName());
        mIntent.putExtra("s", musicList.get(position).getSinger());
        sendBroadcast(mIntent);
        return position;
    }

    //先知道当前是第几首
    //在下面的方法中添加几个方法
    public class MusicBinder extends Binder {
        public void playFist() {
            if (mplayer.isPlaying()) {
                mplayer.pause();
            }
            mplayer.reset();
            try {
                if (musicList != null) {
                    mplayer.setDataSource(mUrlSong);
                    Log.d("MusucService", mUrlSong + "--->");
                    mplayer.prepareAsync();
                    //5播放
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        public MediaPlayer getMiedPlayer() {
            return mplayer;
        }

        public void playLast() {
            //当歌曲播放完就 return  然后播放下一曲 position --
            if (position == 0) {
                return;
            }
            play(--position);
            mIntent.putExtra("n", musicList.get(position).getName());
            mIntent.putExtra("s", musicList.get(position).getSinger());
            sendBroadcast(mIntent);

        }

        public void playNext() {
            //判断什时候++
            MusucService.this.playNext();
            mIntent.putExtra("n", musicList.get(position).getName());
            mIntent.putExtra("s", musicList.get(position).getSinger());
            sendBroadcast(mIntent);
        }

        public void playStop() {
            if (mplayer.isPlaying()) {
                mplayer.pause();
                sendBroadcast(mIntent);
            } else {
                mplayer.start();
            }
        }

        public void seekTo(int progress) {
            MusucService.this.seekTo(progress);
        }


    }

    /**
     * 获取音乐的一系列的数据 获取音乐的方法
     * 在 onCreate 调用方法
     */
    public void getMusicData() {//8

        Cursor cursor = getContentResolver().
                query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                        null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            do {
                /*
                获取名
                 */
                String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                /*
                获取歌手
                 */
                String singer = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                /*
                获取路径
                 */
                String url = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                /*
                获取音乐类型 0  不代表音乐
                 */
                int isMusic = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.IS_MUSIC));
                /*
                获取音乐时长
                 */
                Long duringTiem = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
                int siz = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE));
                MusicBean bean = new MusicBean(title, singer, siz, url);
                musicList.add(bean);
            } while (cursor.moveToNext());
            for (MusicBean bean : musicList) {
                Log.d("MySever", bean.getName() + "-->" + bean.getSinger() + "-->" + bean.getUrl() + "出来了");
            }
        }
        //关闭游标
        cursor.close();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mplayer.isPlaying()) {
            mplayer.stop();
        }
        mplayer.release();
        mplayer = null;
        unregisterReceiver(mCurnSong);
        Log.d("MusucService", "我走啦");
    }

    private BroadcastReceiver mCurnSong = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            mUrlSong = intent.getStringExtra("urlSong");
            String name = intent.getStringExtra("name");
            String songName = intent.getStringExtra("songName");
            Log.d("MusucService", mUrlSong + "--->>>");
            if (mplayer.isPlaying()) {
                mplayer.pause();
            }
            mplayer.reset();
            try {
                mplayer.setDataSource(mUrlSong);
                mplayer.prepareAsync();
                mIntent.putExtra("n", songName);
                mIntent.putExtra("s", name);
                sendBroadcast(mIntent);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d("MusucService", mUrlSong + "--->");

        }
    };


}
