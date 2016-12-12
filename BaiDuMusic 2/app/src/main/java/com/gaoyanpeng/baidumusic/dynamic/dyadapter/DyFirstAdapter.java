package com.gaoyanpeng.baidumusic.dynamic.dyadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gaoyanpeng.baidumusic.R;
import com.gaoyanpeng.baidumusic.dynamic.DYBean;
import com.squareup.picasso.Picasso;

/**
 * Created by 高研鹏 on 2016/11/28.
 */

public class DyFirstAdapter extends RecyclerView.Adapter<DyFirstAdapter.NewDyFirstVH> {
    private DYBean mDYFirstBean;
    private Context mContext;

    public DyFirstAdapter(Context context) {
        mContext = context;
    }

    public DyFirstAdapter setDYFirstBean(DYBean DYFirstBean) {
        mDYFirstBean = DYFirstBean;
        notifyDataSetChanged();
        return this;
    }

    @Override
    public NewDyFirstVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dy_item_first_item,parent,false);
        NewDyFirstVH newDyFirstVH = new NewDyFirstVH(view);
        return newDyFirstVH;
    }

    @Override
    public void onBindViewHolder(NewDyFirstVH holder, int position) {
        Picasso.with(mContext).load(mDYFirstBean.getTopics().get(position).getPic_350x170()).into(holder.mImageView);
        holder.mTextView.setText("#  "+mDYFirstBean.getTopics().get(position).getTopic_title());

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    class NewDyFirstVH extends RecyclerView.ViewHolder{

        private ImageView mImageView;
        private TextView mTextView;
        public NewDyFirstVH(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.dy_item_item_pic);
            mTextView= (TextView) itemView.findViewById(R.id.dy_item_item_title);
        }
    }
}
