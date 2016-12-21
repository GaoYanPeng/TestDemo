package com.gaoyanpeng.musicbaidu.music.musicfragment.video;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.gaoyanpeng.musicbaidu.R;
import com.squareup.picasso.Picasso;

/**
 * Created by 高研鹏 on 2016/11/25.
 */
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoVH>{
    private Context mContext;
    private VideoBean mVideoBean;
    private OnClick mOnClick;

    public VideoAdapter setOnClick(OnClick onClick) {
        mOnClick = onClick;
        return this;
    }

    public VideoAdapter(Context context) {
        mContext = context;
    }

    public VideoAdapter setVideoBean(VideoBean videoBean) {
        mVideoBean = videoBean;
        notifyDataSetChanged();
        return this;
    }

    @Override
    public VideoVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.video_item,parent,false);
        VideoVH videoVH = new VideoVH(view);
        return videoVH;
    }

    @Override
    public void onBindViewHolder(final VideoVH holder, final int position) {
        holder.mArtist.setText(mVideoBean.getResult().getMv_list().get(position).getArtist());
        holder.mTitle.setText(mVideoBean.getResult().getMv_list().get(position).getTitle());
        Picasso.with(mContext).load(mVideoBean.getResult().getMv_list().get(position).getThumbnail()).into(holder.mVideoPic);
        holder.mVideoPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,VideoActivity.class);

                String number = mVideoBean.getResult().getMv_list().get(position).getMv_id();
                intent.putExtra("num",number);
                mContext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mVideoBean.getResult().getMv_list().size();
    }

    class VideoVH extends  RecyclerView.ViewHolder{

        private ImageView mVideoPic;
        private TextView mTitle,mArtist;
        public VideoVH(View itemView) {
            super(itemView);
            mVideoPic = (ImageView) itemView.findViewById(R.id.item_video_pic);
            mTitle = (TextView) itemView.findViewById(R.id.video_item_title);
            mArtist = (TextView) itemView.findViewById(R.id.video_item_video_item_artist);
        }

    }
}
