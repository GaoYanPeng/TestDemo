package com.gaoyanpeng.musicbaidu.music.musicfragment.crunch;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gaoyanpeng.musicbaidu.R;
import com.squareup.picasso.Picasso;

/**
 * Created by 高研鹏 on 2016/11/26.
 */
public class CrunAdapter extends RecyclerView.Adapter<CrunAdapter.CrunVH> {
    private Context mContext;
    private CrunBean mCrunBean;
    private CrunchOnClick mCrunchOnClick;

    public CrunAdapter setCrunchOnClick(CrunchOnClick crunchOnClick) {
        mCrunchOnClick = crunchOnClick;
        return this;
    }

    public CrunAdapter(Context context) {
        mContext = context;
    }

    public CrunAdapter setCrunBean(CrunBean crunBean) {
        mCrunBean = crunBean;
        notifyDataSetChanged();
        return this;
    }

    @Override
    public CrunVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_crun_ad, parent, false);
        CrunVH crunVH = new CrunVH(view);
        return crunVH;
    }

    @Override
    public void onBindViewHolder(CrunVH holder, final int position) {
        holder.mCrunName.setText(mCrunBean.getContent().get(position).getName());
        holder.mCrunTitle.setText(mCrunBean.getContent().get(position).getContent().get(0).getTitle());
        holder.mCrunAuthor.setText(mCrunBean.getContent().get(position).getContent().get(0).getAuthor());
        holder.mCrunTitleTow.setText(mCrunBean.getContent().get(position).getContent().get(1).getTitle());
        holder.mCrunAuthorTow.setText(mCrunBean.getContent().get(position).getContent().get(1).getAuthor());
        holder.mCrunTitleThree.setText(mCrunBean.getContent().get(position).getContent().get(2).getTitle());
        holder.mCrunAuthorThree.setText(mCrunBean.getContent().get(position).getContent().get(2).getAuthor());
        String url = mCrunBean.getContent().get(position).getPic_s210();
        String name = mCrunBean.getContent().get(3).getName();
        if (url != null && name == "T榜-全球原创音乐现金榜") {
            Picasso.with(mContext).load(mCrunBean.getContent().get(position).getPic_s210()).into(holder.mPic);
        }
        Picasso.with(mContext).load(mCrunBean.getContent().get(position).getPic_s192()).into(holder.mPic);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCrunchOnClick.CrOnClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mCrunBean.getContent().size();
    }

    class CrunVH extends RecyclerView.ViewHolder {

        private ImageView mPic;
        private TextView mCrunName,mCrunTitle,mCrunAuthor;
        private TextView mCrunTitleTow,mCrunAuthorTow;
        private TextView mCrunTitleThree,mCrunAuthorThree;

        public CrunVH(View itemView) {
            super(itemView);
            mPic = (ImageView) itemView.findViewById(R.id.crun_item_pic);
            mCrunName = (TextView) itemView.findViewById(R.id.crun_item_name);
            mCrunTitle = (TextView) itemView.findViewById(R.id.crun_item_title);
            mCrunAuthor = (TextView) itemView.findViewById(R.id.crun_item_author);
            mCrunTitleTow = (TextView) itemView.findViewById(R.id.crun_item_tow_title);
            mCrunAuthorTow = (TextView) itemView.findViewById(R.id.crun_item_tow_author);
            mCrunTitleThree = (TextView) itemView.findViewById(R.id.crun_item_three_title);
            mCrunAuthorThree = (TextView) itemView.findViewById(R.id.crun_item_three_author);
        }
    }
}
