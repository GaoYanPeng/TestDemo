package com.gaoyanpeng.musicbaidu.activity.toolstow;

import com.android.volley.VolleyError;

/**
 * 定义一个接口 两个方法 分别是成功时候的回调
 * 失败时候的回调
 * Created by 高研鹏 on 2016/12/8.
 */
public interface NetListener<T> {
    //参数 就是解析后的数据
    void successListener(T response);

    //参数 对错误信息进行操作
    void errorListener(VolleyError error);
}
