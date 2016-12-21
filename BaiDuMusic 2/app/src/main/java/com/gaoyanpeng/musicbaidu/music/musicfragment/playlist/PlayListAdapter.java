package com.gaoyanpeng.musicbaidu.music.musicfragment.playlist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.gaoyanpeng.musicbaidu.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

/**
 * Created by 高研鹏 on 2016/11/25.
 */

public class PlayListAdapter extends RecyclerView.Adapter<PlayListAdapter.PlayLVH> {
    private Context mContext;
    private PlayLBean mPlayLBean;
    private View mView;
    private RequestCreator mLoad;
    private OnClick mOnClick;

    public PlayListAdapter setOnClick(OnClick onClick) {
        mOnClick = onClick;
        return this;
    }

    public PlayListAdapter(Context context) {
        mContext = context;
    }

    public PlayListAdapter setPlayLBean(PlayLBean playLBean) {
        mPlayLBean = playLBean;
        notifyDataSetChanged();
        return this;
    }

    /**
     * 绑定布局
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public PlayLVH onCreateViewHolder(ViewGroup parent, int viewType) {
        mView = LayoutInflater.from(mContext).inflate(R.layout.item_play_list,parent,false);
        PlayLVH playLVH = new PlayLVH(mView);
        return playLVH;
    }
    @Override
    public void onBindViewHolder(PlayLVH holder, final int position) {
        holder.mLisTitle.setText(mPlayLBean.getDiyInfo().get(position).getTitle());
        holder.mLisNun.setText(mPlayLBean.getDiyInfo().get(position).getListen_num()+"");
        holder.mLisUsername.setText(mPlayLBean.getDiyInfo().get(position).getUsername());
        mLoad = Picasso.with(mContext).load(mPlayLBean.getDiyInfo().get(position).getList_pic());
        mLoad.into(holder.listPic);
        final Animation mAnimation = AnimationUtils.loadAnimation(mContext, R.anim.in_lefttoright);
        holder.itemView.setAnimation(mAnimation);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnClick.onClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mPlayLBean.getDiyInfo().size();
    }



    class PlayLVH extends RecyclerView.ViewHolder{

        private ImageView listPic;
        private TextView mLisNun,mLisTitle,mLisUsername;
        public PlayLVH(View itemView) {
            super(itemView);
            listPic = (ImageView) itemView.findViewById(R.id.item_list_pic);
            mLisNun = (TextView) itemView.findViewById(R.id.play_lis_item_number);
            mLisTitle = (TextView) itemView.findViewById(R.id.play_lis_item_title);
            mLisUsername = (TextView) itemView.findViewById(R.id.play_lis_item_username);
        }
    }
}
