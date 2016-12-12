package com.gaoyanpeng.baidumusic.music.musicfragment.crunch;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.gaoyanpeng.baidumusic.R;
import com.gaoyanpeng.baidumusic.api.AllApi;
import com.gaoyanpeng.baidumusic.besa.BaseFragment;
import com.gaoyanpeng.baidumusic.tool.DividerItemDecoration;
import com.gaoyanpeng.baidumusic.tools.VolleySingleton;

/**
 * Created by 高研鹏 on 2016/11/23.
 */
public class CrunchFm extends BaseFragment {
    private RecyclerView mRecyclerView;
    private CrunAdapter mCrunAdapter;
    private DividerItemDecoration dividerItemDecoration;
    public static final String URL = "Url";

    @Override
    protected int initLayout() {
        return R.layout.music_fm_crunch;
    }

    @Override
    protected void initView() {
        mRecyclerView = getFindView(R.id.crun_ryle);
        mCrunAdapter = new CrunAdapter(getActivity());
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        //给 RecyclerView 划线
        dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST);
        mRecyclerView.addItemDecoration(dividerItemDecoration);

    }


    @Override
    protected void initData() {
        VolleySingleton.addRequest(AllApi.URL_Music_Crunch, CrunBean.class, new Response.Listener<CrunBean>() {
            @Override
            public void onResponse(final CrunBean response) {
                mCrunAdapter.setCrunBean(response);
                mRecyclerView.setAdapter(mCrunAdapter);
                mCrunAdapter.setCrunchOnClick(new CrunchOnClick() {
                    @Override
                    public void CrOnClick(int pos) {
                        Intent intent = new Intent(URL);
                        int type = response.getContent().get(pos).getType();
                        String urlItem = AllApi.URL_Music_CR_Item_Q + type + AllApi.URL_Music_CR_Item_H;
                        Log.d("CrunchFm", urlItem);
                        intent.putExtra("data", urlItem);
                        String image = response.getContent().get(pos).getPic_s210();
                        intent.putExtra("image", image);
                        getActivity().sendBroadcast(intent);

                        Toast.makeText(mContext, "pos:" + pos, Toast.LENGTH_SHORT).show();
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
}
