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

public class RECFirstTowAdapter extends RecyclerView.Adapter<RECFirstTowAdapter.RECFirstTowVH>{
    private Context mContext;
    int pos;
    private RecommBean mRECFirstTowBean;

    public RECFirstTowAdapter(Context context, int pos) {
        mContext = context;
        this.pos = pos;
    }

    public RECFirstTowAdapter setRECFirstTowBean(RecommBean RECFirstTowBean) {
        mRECFirstTowBean = RECFirstTowBean;
        notifyDataSetChanged();
        return this;
    }

    @Override
    public RECFirstTowVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.first_one_item_item,parent,false);
        RECFirstTowVH recFirstTowVH = new RECFirstTowVH(view);
        return recFirstTowVH;
    }

    @Override
    public void onBindViewHolder(RECFirstTowVH holder, int position) {
        holder.mFTowTitle.setText(mRECFirstTowBean.getResult().getMix_1().getResult().get(position).getTitle());
        holder.mFTowTitleSmall.setText(mRECFirstTowBean.getResult().getMix_1().getResult().get(position).getAuthor());
        Picasso.with(mContext).load(mRECFirstTowBean.getResult().getMix_1().getResult().get(position).getPic()).into(holder.mFTowpic);

    }

    @Override
    public int getItemCount() {
        return 6;
    }

    class RECFirstTowVH extends RecyclerView.ViewHolder{

        private ImageView mFTowpic;
        private TextView mFTowTitle,mFTowTitleSmall;

        public RECFirstTowVH(View itemView) {
            super(itemView);
            mFTowpic = (ImageView) itemView.findViewById(R.id.firs_one_item_pic);
            mFTowTitle = (TextView) itemView.findViewById(R.id.first_one_item_title);
            mFTowTitleSmall = (TextView) itemView.findViewById(R.id.first_one_item_title_small);
        }
    }
}
