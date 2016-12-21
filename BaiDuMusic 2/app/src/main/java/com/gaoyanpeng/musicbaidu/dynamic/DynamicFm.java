package com.gaoyanpeng.musicbaidu.dynamic;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.gaoyanpeng.musicbaidu.R;
import com.gaoyanpeng.musicbaidu.api.AllApi;
import com.gaoyanpeng.musicbaidu.besa.BaseFragment;
import com.gaoyanpeng.musicbaidu.tool.DividerItemDecoration;
import com.gaoyanpeng.musicbaidu.tools.VolleySingleton;

/**
 * Created by 高研鹏 on 2016/11/22.
 */
public class DynamicFm extends BaseFragment {
    private RecyclerView mRecyclerView;
    private DynamicAdapter mDynamicAdapter;
    private LinearLayoutManager mManager;
    private DividerItemDecoration mDividerItemDecoration;

    @Override
    protected int initLayout() {
        return R.layout.fm_dynamic;
    }

    @Override
    protected void initView() {
        mRecyclerView = getFindView(R.id.dy_ryl);
        mDynamicAdapter = new DynamicAdapter(getContext());
        mManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mManager);
        mDividerItemDecoration = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL_LIST);
        mRecyclerView.addItemDecoration(mDividerItemDecoration);

    }

    @Override
    protected void initData() {

    }

    private void getDyAllIntent() {
        VolleySingleton.addRequest(AllApi.URL_DY, DYBean.class, new Response.Listener<DYBean>() {
            @Override
            public void onResponse(DYBean response) {
                mDynamicAdapter.setDYBean(response);
                mRecyclerView.setAdapter(mDynamicAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.getMessage();
            }
        });
    }
}
