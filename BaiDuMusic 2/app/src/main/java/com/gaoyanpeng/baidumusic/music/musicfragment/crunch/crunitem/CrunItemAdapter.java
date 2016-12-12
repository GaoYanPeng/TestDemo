package com.gaoyanpeng.baidumusic.music.musicfragment.crunch.crunitem;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gaoyanpeng.baidumusic.R;
import com.gaoyanpeng.baidumusic.music.musicfragment.crunch.CrunchOnClick;
import com.squareup.picasso.Picasso;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by 高研鹏 on 2016/12/3.
 */

public class CrunItemAdapter extends RecyclerView.Adapter<CrunItemAdapter.CrunItemVH> {
    private Context mContext;
    private CrunItemBean mCrunItemBean;

    private CrunchOnClick mCrunchOnClick;
    private int Coloce;

    public CrunItemAdapter setColoce(int coloce) {
        Coloce = coloce;
        notifyDataSetChanged();
        return this;
    }

    public CrunItemAdapter setCrunchOnClick(CrunchOnClick crunchOnClick) {
        mCrunchOnClick = crunchOnClick;
        return this;
    }

    public CrunItemAdapter(Context context) {
        mContext = context;
    }

    public CrunItemAdapter setCrunItemBean(CrunItemBean crunItemBean) {
        mCrunItemBean = crunItemBean;
        notifyDataSetChanged();
        return this;
    }

    @Override
    public CrunItemVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_crun_fm, parent, false);
        CrunItemVH crunItemVH = new CrunItemVH(view);
        return crunItemVH;
    }

    @Override
    public void onBindViewHolder(final CrunItemVH holder, final int position) {
        Picasso.with(mContext).load(mCrunItemBean.getSong_list().get(position).getPic_big()).into(holder.mCurnItemPic);
        holder.mSongName.setText(mCrunItemBean.getSong_list().get(position).getTitle());
        holder.cn_it_name.setText(mCrunItemBean.getSong_list().get(position).getArtist_name());
        holder.number.setText(position + 1 + "");
        if (position == 0) {
            holder.mPicNum.setImageResource(R.mipmap.img_king_mask01);
        } else if (position == 1) {
            holder.mPicNum.setImageResource(R.mipmap.img_king_mask02);
        } else if (position == 2) {
            holder.mPicNum.setImageResource(R.mipmap.img_king_mask03);
        } else {
            holder.mPicNum.setImageResource(R.mipmap.img_king_mask1);
        }
        if (position != Coloce) {
            holder.mSongName.setTextColor(Color.rgb(0x00, 0x00, 0x00));
            holder.cn_it_name.setTextColor(Color.rgb(0x00, 0x00, 0x00));

        } else if (position == Coloce) {
            holder.mSongName.setTextColor(Color.rgb(0x10, 0x5c, 0xFB));
            holder.cn_it_name.setTextColor(Color.rgb(0x10, 0x5c, 0xFB));


        }


        holder.mShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              showShare();

            }
        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCrunchOnClick.CrOnClick(position);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mCrunItemBean.getSong_list().size();
    }

    class CrunItemVH extends RecyclerView.ViewHolder {
        private ImageView mCurnItemPic;
        private TextView mSongName, cn_it_name, number;
        private ImageView mPicNum, mShare;

        public CrunItemVH(View itemView) {

            super(itemView);
            mCurnItemPic = (ImageView) itemView.findViewById(R.id.cr_image_heard);
            mSongName = (TextView) itemView.findViewById(R.id.cn_it_song_name);
            cn_it_name = (TextView) itemView.findViewById(R.id.cn_it_name);
            number = (TextView) itemView.findViewById(R.id.crun_number_tv);
            mPicNum = (ImageView) itemView.findViewById(R.id.crun_image_on);
            mShare = (ImageView) itemView.findViewById(R.id.crun_item_smaple);

        }
    }
    private void showShare() {
        ShareSDK.initSDK(mContext);
        OnekeyShare oks = new OnekeyShare();
//关闭sso授权
        oks.disableSSOWhenAuthorize();

// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("好的音乐就在百度音乐");
// titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl("http://music.baidu.com/song/266322598?pst=sug");
// text是分享文本，所有平台都需要这个字段
        oks.setText("让我们尽情的享受吧");
// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
// url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
// comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
// site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(mContext.getString(R.string.app_name));
// siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");
// 启动分享GUI
        oks.show(mContext);
    }

}
