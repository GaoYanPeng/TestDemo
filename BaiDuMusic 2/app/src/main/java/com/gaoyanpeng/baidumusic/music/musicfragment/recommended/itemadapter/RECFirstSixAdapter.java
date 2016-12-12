package com.gaoyanpeng.baidumusic.music.musicfragment.recommended.itemadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gaoyanpeng.baidumusic.R;
import com.gaoyanpeng.baidumusic.music.musicfragment.recommended.RecommBean;
import com.squareup.picasso.Picasso;

/**
 * Created by 高研鹏 on 2016/11/23.
 */

public class RECFirstSixAdapter extends RecyclerView.Adapter<RECFirstSixAdapter.RECFirSixVH>{
    private Context mContext;
    int pos;
    private RecommBean mRECFirstSixBean;
    private View mView;
    private RECFirSixVH mRecFirstTowVH;

    public RECFirstSixAdapter(Context context, int pos) {
        mContext = context;
        this.pos = pos;
    }

    public RECFirstSixAdapter setRECFirstSixBean(RecommBean RECFirstSixBean) {
        mRECFirstSixBean = RECFirstSixBean;
        notifyDataSetChanged();
        return this;
    }

    @Override
    public RECFirSixVH onCreateViewHolder(ViewGroup parent, int viewType) {
        mView = LayoutInflater.from(mContext).inflate(R.layout.first_one_item_item,parent,false);
        mRecFirstTowVH = new RECFirSixVH(mView);
        return mRecFirstTowVH;
    }

    @Override
    public void onBindViewHolder(RECFirSixVH holder, int position) {
        holder.mFTowTitle.setText(mRECFirstSixBean.getResult().getMix_5().getResult().get(position).getTitle());
        holder.mFTowTitleSmall.setText(mRECFirstSixBean.getResult().getMix_5().getResult().get(position).getAuthor());
        Picasso.with(mContext).load(mRECFirstSixBean.getResult().getMix_5().getResult().get(position).getPic()).into(holder.mFTowpic);

    }

    @Override
    public int getItemCount() {
        return 6;
    }

    class RECFirSixVH extends RecyclerView.ViewHolder{

        private ImageView mFTowpic;
        private TextView mFTowTitle,mFTowTitleSmall;

        public RECFirSixVH(View itemView) {
            super(itemView);
            mFTowpic = (ImageView) itemView.findViewById(R.id.firs_one_item_pic);
            mFTowTitle = (TextView) itemView.findViewById(R.id.first_one_item_title);
            mFTowTitleSmall = (TextView) itemView.findViewById(R.id.first_one_item_title_small);
        }
    }
}
