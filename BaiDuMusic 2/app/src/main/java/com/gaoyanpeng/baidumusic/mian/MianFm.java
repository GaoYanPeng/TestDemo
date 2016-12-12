package com.gaoyanpeng.baidumusic.mian;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gaoyanpeng.baidumusic.R;
import com.gaoyanpeng.baidumusic.activity.MainBean;
import com.gaoyanpeng.baidumusic.activity.MusucService;
import com.gaoyanpeng.baidumusic.api.AllApi;
import com.gaoyanpeng.baidumusic.besa.BaseFragment;
import com.gaoyanpeng.baidumusic.tool.NetTool;
import com.gaoyanpeng.baidumusic.tool.onHttpCallBack;

/**
 * Created by 高研鹏 on 2016/11/22.
 */
public class MianFm extends BaseFragment implements View.OnClickListener {

    private TextView mTextView, number;
    private Intent mIntent;
    private MusucService.MusicBinder mBinder;

    @Override
    protected int initLayout() {
        return R.layout.fm_mian;
    }

    @Override
    protected void initView() {

        mTextView = (TextView) getView().findViewById(R.id.saslkdfjh);
        mIntent = new Intent(getContext(), MusucService.class);
//        getActivity().bindService(mIntent, sc, Context.BIND_AUTO_CREATE);
//        getActivity().startService(mIntent);
        IntentFilter filter = new IntentFilter();
        filter.addAction(MusucService.ACTION_PROGRESS);
        getActivity().registerReceiver(mReceiver, filter);

    }

    @Override
    protected void initData() {
        number = getFindView(R.id.mian_first_num);

    }

    @Override
    public void onClick(View v) {
    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int alls = intent.getIntExtra("all", 1);
            Log.d("MianFm", "alls:" + alls);
            number.setText(alls + "首");


        }
    };





    @Override
    public void onDestroy() {
        super.onDestroy();

        getActivity().unregisterReceiver(mReceiver);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
