package com.gaoyanpeng.baidumusic.api;

/**
 * Created by 高研鹏 on 2016/11/23.
 */

public class AllApi {
    /**
     * 欢迎页
     */
    public static final String URL_Welcome = "http://baifen.music.baidu.com/api/v2/offline?from=android&product=music&version=5.9.0.0&cuid=FD91A86A9F44B1249C42381F417D4253&deviceid=MDPp0%2FbS5waqt8vKnbohvA%3D%3D&width=1080&height=1776&ad_pos_id=1";
    /**
     * 音乐
     */
    public static final String URL_Music = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.0.0&channel=360safe&operator=3&method=baidu.ting.plaza.index&cuid=FD91A86A9F44B1249C42381F417D4253";
    /**
     * 歌单
     */
    public static final String URL_Music_Play_List = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.0.0&channel=360safe&operator=3&method=baidu.ting.ugcdiy.getChanneldiy&param=%2BAwtWRF72CKYAtqDM2Nf8%2BnLj%2BSz8CUk%2BX2WKgMOmeMrHrs12iANZHUXkJ0fW6VTGoOS7HkbFcae4BQ%2Fa%2Fqhhj7j%2FzBRYFSZNHFxF%2F%2BX8egRYVMgBQJlUjefWo2mDG6l&timestamp=1480054307&sign=6e8763041d038759eb7fb750b4c92769";
    /**
     * 歌单最新
     */
    public static final String URL_Music_Play_List_New = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.0.0&channel=360safe&operator=3&method=baidu.ting.ugcdiy.getChanneldiy&param=EizM6Vat4Il0Cw3Ks69f0ZB7PduqSUmBTbbnWSfhRYZwU1Fv32cEck2NWBK60ourk1m2zM84o20UlSFkpICY%2BXh53eS%2FgAmRhOp2c44SjT3KQSduG5QZ8sv1Htdr6tVj&timestamp=1480076353&sign=d8c0485cdc629c15c9f2cffb3219e5b2";
    /*
    视频最新
     */
    public static final String URL_Music_Video = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.0.0&channel=360safe&operator=3&provider=11%2C12&method=baidu.ting.mv.searchMV&format=json&order=1&page_num=1&page_size=20&query=%E5%85%A8%E9%83%A8";
    /**
     * 拼接前
     */
    public static  final  String URL_Before= "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.0.0&channel=360safe&operator=3&provider=11%2C12&method=baidu.ting.mv.playMV&format=json&mv_id=";
    public static  final  String URL_After= "&song_id=&definition=0";
    /**
     * 视频最热
     */
    public static final String URL_Music_Video_Host = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.0.0&channel=360safe&operator=3&provider=11%2C12&method=baidu.ting.mv.searchMV&format=json&order=0&page_num=1&page_size=20&query=%E5%85%A8%E9%83%A8";
    /**
     * 榜单
     */
    public static final String URL_Music_Crunch = " http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.0.0&channel=360safe&operator=3&method=baidu.ting.billboard.billCategory&format=json&kflag=2";
    //榜单详情前
    public static  final  String URL_Music_CR_Item_Q = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.billboard.billList&type=";
    //榜单后
    public static  final  String URL_Music_CR_Item_H = "&format=json&offset=0&size=50&from=ios&fields=title,song_id,author,resource_type,havehigh,is_new,has_mv_mobile,album_title,ting_uid,album_id,charge,all_rate&version=5.2.1&from=ios&channel=appstore";
    /**
     * 动态
     */
    public static final String URL_DY = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.0.0&channel=360safe&operator=3&method=baidu.ting.ugcfriend.getList&format=json&param=Q08k%2BYD0BBB0ANwsik2CCnfRQp98OWITt41TVNVSaiyWpyMomhWKgnxsH%2BkzXwlvjAAuW7DNxTkjkCXiAY7qig%3D%3D&timestamp=1480294407&sign=2dddfb1906c81b48412fa8c740a27b66";
    /**
     * 网络音乐前
     */
    public static final String URL_Music_Q = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=webapp_music&method=baidu.ting.song.play&format=json&callback=&songid=";
    /**
     * 网络音乐后
     */
    public static final String URL_Music_H = "&_=1413017198449";
    /**
     * 音乐页 推荐 歌手
     */
    public static  final  String URL_Music_T_Song = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.0.0&channel=360safe&operator=3&method=baidu.ting.artist.getList&format=json&offset=0&limit=48&order=1&area=0&sex=0";
}