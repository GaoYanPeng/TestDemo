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

public class RECFirstThreeAdapter extends RecyclerView.Adapter<RECFirstThreeAdapter.RECFirstThreeVH> {
    private RecommBean RECFirstOneBean;
    private Context mContext;
    int pos;

    public RECFirstThreeAdapter(Context context, int pos) {
        mContext = context;
        this.pos = pos;
    }

    public RECFirstThreeAdapter setRECFirstOneBean(RecommBean RECFirstOneBean) {
        this.RECFirstOneBean = RECFirstOneBean;
        notifyDataSetChanged();
        return this;
    }

    @Override
    public RECFirstThreeVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.first_one_item_item,parent,false);
        RECFirstThreeVH recFirstOneVH = new RECFirstThreeVH(view);
        return recFirstOneVH;
    }

    @Override
    public void onBindViewHolder(RECFirstThreeVH holder, int position) {

        holder.title.setText(RECFirstOneBean.getResult().getMix_22().getResult().get(position).getTitle());
        holder.smallTitle.setText(RECFirstOneBean.getResult().getMix_22().getResult().get(position).getAuthor());
        Picasso.with(mContext).load(RECFirstOneBean.getResult().getMix_22().getResult().get(position).getPic()).into(holder.mPic);

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    class RECFirstThreeVH extends RecyclerView.ViewHolder{

        private ImageView mPic;
        private TextView title,smallTitle;
        public RECFirstThreeVH(View itemView) {
            super(itemView);
            mPic = (ImageView) itemView.findViewById(R.id.firs_one_item_pic);
            title = (TextView) itemView.findViewById(R.id.first_one_item_title);
            smallTitle = (TextView) itemView.findViewById(R.id.first_one_item_title_small);
        }
    }

}
