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
 * Created by 高研鹏 on 2016/11/23.
 */

public class RECFourOneAdapter extends RecyclerView.Adapter<RECFourOneAdapter.NewFirstOneVH>{
    private Context mContext;
    private RecommBean mFirstBean;
    int pos;
    private AllOnclick mAllOnclick;

    public RECFourOneAdapter setAllOnclick(AllOnclick allOnclick) {
        mAllOnclick = allOnclick;
        return this;
    }

    public RECFourOneAdapter(int pos, Context context) {
        this.pos = pos;
        mContext = context;
    }

    public RECFourOneAdapter setFirstBean(RecommBean firstBean) {
        mFirstBean = firstBean;
        notifyDataSetChanged();
        return this;
    }

    @Override
    public NewFirstOneVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.rec_item_fout_item,parent,false);
        NewFirstOneVH newFirstOneVH = new NewFirstOneVH(view);
        return newFirstOneVH;
    }

    @Override
    public void onBindViewHolder(NewFirstOneVH holder, final int position) {
        holder.title.setText(mFirstBean.getResult().getEntry().getResult().get(position).getTitle() );
        Picasso.with(mContext).load(mFirstBean.getResult().getEntry().getResult().get(position).getIcon()).into(holder.mIcon);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAllOnclick.allOnclick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    class NewFirstOneVH extends RecyclerView.ViewHolder{
        private ImageView mIcon;
        private TextView title;

        public NewFirstOneVH(View itemView) {
            super(itemView);
            mIcon = (ImageView) itemView.findViewById(R.id.item_item_four_image);
            title = (TextView) itemView.findViewById(R.id.item_item_title);
        }
    }
}
