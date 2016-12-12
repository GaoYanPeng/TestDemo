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
import com.gaoyanpeng.baidumusic.music.musiconclick.AllOnclick;
import com.squareup.picasso.Picasso;

/**
 * Created by 高研鹏 on 2016/11/24.
 */

public class RECFirstEightAdapter extends RecyclerView.Adapter<RECFirstEightAdapter.RECFirstEightVH>{
    private Context mContext;
    private RecommBean mRecommEightBean;
    int pos;
    private RECFirstEightVH mRecFirstEightVH;
    private AllOnclick mAllOnclick;

    public RECFirstEightAdapter setAllOnclick(AllOnclick allOnclick) {
        mAllOnclick = allOnclick;
        return this;
    }

    public RECFirstEightAdapter(Context context, int pos) {
        mContext = context;
        this.pos = pos;
    }

    public RECFirstEightAdapter setRecommEightBean(RecommBean recommEightBean) {
        mRecommEightBean = recommEightBean;
        notifyDataSetChanged();
        return this;
    }

    @Override
    public RECFirstEightVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.third_item_item,parent,false);
        mRecFirstEightVH = new RECFirstEightVH(view);
        return mRecFirstEightVH;
    }

    @Override
    public void onBindViewHolder(RECFirstEightVH holder, final int position) {
        holder.desc.setText(mRecommEightBean.getResult().getMod_7().getResult().get(position).getDesc());
        holder.title.setText(mRecommEightBean.getResult().getMod_7().getResult().get(position).getTitle());
        Picasso.with(mContext).load(mRecommEightBean.getResult().getMod_7().getResult().get(position).getPic()).into(holder.pic);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAllOnclick.allOnclick(position);

            }
        });

    }

    @Override
    public int getItemCount() {
        return 7;
    }


    class RECFirstEightVH extends RecyclerView.ViewHolder{
        private ImageView pic;
        private TextView title,desc;
        public RECFirstEightVH(View itemView) {
            super(itemView);
            pic = (ImageView) itemView.findViewById(R.id.third_item_item_pic);
            title = (TextView) itemView.findViewById(R.id.third_item_item_title);
            desc = (TextView) itemView.findViewById(R.id.third_item_item_desc);
        }
    }

}
