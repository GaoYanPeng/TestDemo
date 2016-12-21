package com.gaoyanpeng.musicbaidu.mian;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gaoyanpeng.musicbaidu.R;
import com.gaoyanpeng.musicbaidu.db.HousPerson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 高研鹏 on 2016/12/13.
 */

public class LickAdapter extends BaseAdapter {
   private List<HousPerson> mLickBean;
    private Context mContext;

    public LickAdapter(List<HousPerson> lickBean, Context context) {
        mLickBean = lickBean;
        mContext = context;
    }

    public LickAdapter setLickBean(List<HousPerson> lickBean) {
        mLickBean = lickBean;
        notifyDataSetChanged();
        return this;
    }

    @Override
    public int getCount() {
        return mLickBean.size();
    }

    @Override
    public Object getItem(int position) {
        return mLickBean.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LickVH lickVH = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.lick_item,parent,false);
            lickVH = new LickVH(convertView);
            convertView.setTag(lickVH);
        }else {
            lickVH = (LickVH) convertView.getTag();
        }
        lickVH.songName.setText(mLickBean.get(position).getSongName());
        lickVH.name.setText(mLickBean.get(position).getName());
        Picasso.with(mContext).load(mLickBean.get(position).getImage()).into(lickVH.mHeard);
            return convertView;
    }
    class LickVH{
        private ImageView mHeard;
        private TextView name,songName;
        public LickVH(View view) {
            mHeard = (ImageView) view.findViewById(R.id.lick_image_heard);
            name = (TextView) view.findViewById(R.id.lick_it_name);
            songName = (TextView) view.findViewById(R.id.lick_it_song_name);


        }
    }
}
