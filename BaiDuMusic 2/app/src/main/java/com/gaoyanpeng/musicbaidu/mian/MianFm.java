package com.gaoyanpeng.musicbaidu.mian;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.gaoyanpeng.musicbaidu.R;
import com.gaoyanpeng.musicbaidu.activity.MusucService;
import com.gaoyanpeng.musicbaidu.api.AllApi;
import com.gaoyanpeng.musicbaidu.besa.BaseFragment;
import com.gaoyanpeng.musicbaidu.db.HousDBTools;

/** 我的界面
 * Created by 高研鹏 on 2016/11/22.
 */
public class MianFm extends BaseFragment implements View.OnClickListener {

    private TextView mTextView, number;
    private LinearLayout mLinearLayout;
    private Intent mIntent;
    private MusucService.MusicBinder mBinder;


    @Override
    protected int initLayout() {
        return R.layout.fm_mian;
    }

    @Override
    protected void initView() {

        mTextView = (TextView) getView().findViewById(R.id.item_text_mian);

        mIntent = new Intent(getContext(), MusucService.class);
        IntentFilter filter = new IntentFilter();
        filter.addAction(AllApi.ACTION_PROGRESS);
        getActivity().registerReceiver(mReceiver, filter);
        mLinearLayout   = getFindView(R.id.fm_mian_lick);
        mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllApi.LIKE);
                getActivity().sendBroadcast(intent);
            }
        });

    }

    @Override
    protected void initData() {
        number = getFindView(R.id.mian_first_num);
       String content = HousDBTools.getInstance().queryAll().size()+"";
        mTextView.setText(content);

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
