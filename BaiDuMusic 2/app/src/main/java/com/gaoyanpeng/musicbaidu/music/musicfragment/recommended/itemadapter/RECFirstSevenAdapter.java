package com.gaoyanpeng.musicbaidu.music.musicfragment.recommended.itemadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gaoyanpeng.musicbaidu.R;
import com.gaoyanpeng.musicbaidu.music.musicfragment.recommended.RecommBean;
import com.squareup.picasso.Picasso;

/**
 * Created by 高研鹏 on 2016/11/23.
 */

public class RECFirstSevenAdapter extends RecyclerView.Adapter<RECFirstSevenAdapter.RECFirSevenVH>{
    private Context mContext;
    int pos;
    private RecommBean mRECFirstSevenBean;
    private View mView;
    private RECFirSevenVH mRecFirstTowVH;

    public RECFirstSevenAdapter(Context context, int pos) {
        mContext = context;
        this.pos = pos;
    }

    public RECFirstSevenAdapter setRECFirstSevenBean(RecommBean RECFirstSevenBean) {
        mRECFirstSevenBean = RECFirstSevenBean;
        return this;
    }

    @Override
    public RECFirSevenVH onCreateViewHolder(ViewGroup parent, int viewType) {
        mView = LayoutInflater.from(mContext).inflate(R.layout.first_one_item_item,parent,false);
        mRecFirstTowVH = new RECFirSevenVH(mView);
        return mRecFirstTowVH;
    }

    @Override
    public void onBindViewHolder(RECFirSevenVH holder, int position) {
        holder.mFTowTitle.setText(mRECFirstSevenBean.getResult().getRadio().getResult().get(position).getTitle());
        Picasso.with(mContext).load(mRECFirstSevenBean.getResult().getRadio().getResult().get(position).getPic()).into(holder.mFTowpic);

    }

    @Override
    public int getItemCount() {
        return 6;
    }

    class RECFirSevenVH extends RecyclerView.ViewHolder{

        private ImageView mFTowpic;
        private TextView mFTowTitle;

        public RECFirSevenVH(View itemView) {
            super(itemView);
            mFTowpic = (ImageView) itemView.findViewById(R.id.firs_one_item_pic);
            mFTowTitle = (TextView) itemView.findViewById(R.id.first_one_item_title);
        }
    }
}
