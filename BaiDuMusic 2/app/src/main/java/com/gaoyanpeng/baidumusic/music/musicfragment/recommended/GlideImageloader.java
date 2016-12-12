package com.gaoyanpeng.baidumusic.music.musicfragment.recommended;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by 高研鹏 on 2016/11/24.
 */

public class GlideImageloader extends com.youth.banner.loader.ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context).load(path).into(imageView);
    }
}
