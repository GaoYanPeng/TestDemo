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

public class RECFirstFiveAdapter extends RecyclerView.Adapter<RECFirstFiveAdapter.RECFirstFiveVH> {
    private RecommBean RECFiveOneBean;
    private Context mContext;
    int pos;

    public RECFirstFiveAdapter(Context context, int pos) {
        mContext = context;
        this.pos = pos;
    }

    public RECFirstFiveAdapter setRECFiveOneBean(RecommBean RECFiveOneBean) {
        this.RECFiveOneBean = RECFiveOneBean;
        notifyDataSetChanged();
        return this;
    }

    @Override
    public RECFirstFiveVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.first_one_item_item,parent,false);
        RECFirstFiveVH recFirstOneVH = new RECFirstFiveVH(view);
        return recFirstOneVH;
    }

    @Override
    public void onBindViewHolder(RECFirstFiveVH holder, int position) {

        holder.title.setText(RECFiveOneBean.getResult().getMix_9().getResult().get(position).getTitle());
        Picasso.with(mContext).load(RECFiveOneBean.getResult().getMix_9().getResult().get(position).getPic()).into(holder.mPic);

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    class RECFirstFiveVH extends RecyclerView.ViewHolder{

        private ImageView mPic;
        private TextView title;
        public RECFirstFiveVH(View itemView) {
            super(itemView);
            mPic = (ImageView) itemView.findViewById(R.id.firs_one_item_pic);
            title = (TextView) itemView.findViewById(R.id.first_one_item_title);

        }
    }

}
