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

public class RECFirstOneAdapter extends RecyclerView.Adapter<RECFirstOneAdapter.RECFirstOneVH> {
    private RecommBean RECFirstOneBean;
    private Context mContext;
    int pos;

    public RECFirstOneAdapter(Context context, int pos) {
        mContext = context;
        this.pos = pos;
    }

    public RECFirstOneAdapter setRECFirstOneBean(RecommBean RECFirstOneBean) {
        this.RECFirstOneBean = RECFirstOneBean;
        notifyDataSetChanged();
        return this;
    }

    @Override
    public RECFirstOneVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.first_one_item_item,parent,false);
        RECFirstOneVH recFirstOneVH = new RECFirstOneVH(view);
        return recFirstOneVH;
    }

    @Override
    public void onBindViewHolder(RECFirstOneVH holder, int position) {

        holder.title.setText(RECFirstOneBean.getResult().getDiy().getResult().get(position).getTitle());
        Picasso.with(mContext).load(RECFirstOneBean.getResult().getDiy().getResult().get(position).getPic()).into(holder.mPic);

    }

    @Override
    public int getItemCount() {
        return 6;
    }

    class RECFirstOneVH extends RecyclerView.ViewHolder{

        private ImageView mPic;
        private TextView title;
        public RECFirstOneVH(View itemView) {
            super(itemView);
            mPic = (ImageView) itemView.findViewById(R.id.firs_one_item_pic);
            title = (TextView) itemView.findViewById(R.id.first_one_item_title);
        }
    }

}
