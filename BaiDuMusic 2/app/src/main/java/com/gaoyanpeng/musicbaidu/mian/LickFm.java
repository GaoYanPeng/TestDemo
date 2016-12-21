package com.gaoyanpeng.musicbaidu.mian;

import android.widget.ListView;

import com.gaoyanpeng.musicbaidu.R;
import com.gaoyanpeng.musicbaidu.besa.BaseXFragment;
import com.gaoyanpeng.musicbaidu.db.HousDBTools;
import com.gaoyanpeng.musicbaidu.db.HousPerson;

import java.util.ArrayList;
import java.util.List;

/**在我的页面中收藏的 Fragment
 * Created by 高研鹏 on 2016/12/13.
 */

public class LickFm extends BaseXFragment {
    private ListView mListView;
    private List<HousPerson> mHousPersons;
    private LickAdapter mLickAdapter;


    @Override
    protected int initLayout() {
        return R.layout.fm_lick;
    }

    @Override
    protected void initView() {
        mListView = getFindView(R.id.lick_lst);
        mLickAdapter = new LickAdapter(mHousPersons, getContext());
        mHousPersons = new ArrayList<>();
    }

    @Override
    protected void initData() {
        mHousPersons = HousDBTools.getInstance().queryAll();
        mLickAdapter.setLickBean(mHousPersons);
        mListView.setAdapter(mLickAdapter);
    }
}
