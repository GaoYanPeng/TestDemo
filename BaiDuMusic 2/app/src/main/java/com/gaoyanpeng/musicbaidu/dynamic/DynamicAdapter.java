package com.gaoyanpeng.musicbaidu.dynamic;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaoyanpeng.musicbaidu.R;
import com.gaoyanpeng.musicbaidu.dynamic.dyadapter.DyFirstAdapter;
import com.gaoyanpeng.musicbaidu.dynamic.dyadapter.DySecondAdapter;
import com.gaoyanpeng.musicbaidu.tool.DividerItemDecoration;

/**
 * Created by 高研鹏 on 2016/11/28.
 */

public class DynamicAdapter extends RecyclerView.Adapter {
    private DYBean mDYBean;
    private Context mContext;
    private View mView;
    private DyFirstAdapter mDyFirstAdapter;
    private DYFirstVH mDyFirstVH;
    private LinearLayoutManager mManager;
    private DYSecondVH mDySecondVH;
    private DySecondAdapter mDySecondAdapter;
    private DividerItemDecoration dividerItemDecoration ;

    public DynamicAdapter(Context context) {
        mContext = context;
    }

    public DynamicAdapter setDYBean(DYBean DYBean) {
        mDYBean = DYBean;
        notifyDataSetChanged();
        return this;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case 0:
                mView = LayoutInflater.from(mContext).inflate(R.layout.dy_first_item, parent, false);
                holder = new DYFirstVH(mView);
                break;
            case 1:
                mView = LayoutInflater.from(mContext).inflate(R.layout.dy_second_item,parent,false);
                holder = new DYSecondVH( mView);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        int type = getItemViewType(position);
        switch (type) {
            case 0:
                mDyFirstVH = (DYFirstVH) holder;
                mManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
                mDyFirstVH.mRecyclerView.setLayoutManager(mManager);
                mDyFirstAdapter = new DyFirstAdapter(mContext);
                mDyFirstAdapter.setDYFirstBean(mDYBean);
                mDyFirstVH.mRecyclerView.setAdapter(mDyFirstAdapter);
                break;
            case 1:
                mDySecondVH = (DYSecondVH) holder;
                dividerItemDecoration = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST);
                mDySecondVH.mRecyclerView.addItemDecoration(dividerItemDecoration);
                mManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
                mDySecondVH.mRecyclerView.setLayoutManager(mManager);
                mDySecondAdapter = new DySecondAdapter(mContext);
                mDySecondAdapter.setDYSdSecndBean(mDYBean);
                mDySecondVH.mRecyclerView.setAdapter(mDySecondAdapter);

                break;
        }
    }

    @Override
    public int getItemCount() {

        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else if (position == 1) {
            return 1;
        } else {
            return 2;
        }
    }

    class DYFirstVH extends RecyclerView.ViewHolder {
        private RecyclerView mRecyclerView;

        public DYFirstVH(View itemView) {
            super(itemView);
            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.dy_first_item_rly);
        }
    }

    class DYSecondVH extends RecyclerView.ViewHolder {
        private RecyclerView mRecyclerView;
        public DYSecondVH(View itemView) {
            super(itemView);
            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.dy_se_rel);


        }
    }

}
