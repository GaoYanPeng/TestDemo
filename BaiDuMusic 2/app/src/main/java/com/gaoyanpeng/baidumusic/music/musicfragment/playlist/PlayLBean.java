package com.gaoyanpeng.baidumusic.music.musicfragment.playlist;

import java.util.List;

/**
 * Created by 高研鹏 on 2016/11/25.
 */

public class PlayLBean {


    private int have_more;
    private int nums;
    private int error_code;
    private List<DiyInfoBean> diyInfo;

    public int getHave_more() {
        return have_more;
    }

    public void setHave_more(int have_more) {
        this.have_more = have_more;
    }

    public int getNums() {
        return nums;
    }

    public void setNums(int nums) {
        this.nums = nums;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<DiyInfoBean> getDiyInfo() {
        return diyInfo;
    }

    public void setDiyInfo(List<DiyInfoBean> diyInfo) {
        this.diyInfo = diyInfo;
    }

    public static class DiyInfoBean {
        private int listen_num;
        private String username;
        private int song_num;
        private String userid;
        private String list_id;
        private String title;
        private String list_pic;
        private String list_pic_large;
        private String list_pic_small;
        private String list_pic_huge;
        private String list_pic_middle;

        public int getListen_num() {
            return listen_num;
        }

        public void setListen_num(int listen_num) {
            this.listen_num = listen_num;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public int getSong_num() {
            return song_num;
        }

        public void setSong_num(int song_num) {
            this.song_num = song_num;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getList_id() {
            return list_id;
        }

        public void setList_id(String list_id) {
            this.list_id = list_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getList_pic() {
            return list_pic;
        }

        public void setList_pic(String list_pic) {
            this.list_pic = list_pic;
        }

        public String getList_pic_large() {
            return list_pic_large;
        }

        public void setList_pic_large(String list_pic_large) {
            this.list_pic_large = list_pic_large;
        }

        public String getList_pic_small() {
            return list_pic_small;
        }

        public void setList_pic_small(String list_pic_small) {
            this.list_pic_small = list_pic_small;
        }

        public String getList_pic_huge() {
            return list_pic_huge;
        }

        public void setList_pic_huge(String list_pic_huge) {
            this.list_pic_huge = list_pic_huge;
        }

        public String getList_pic_middle() {
            return list_pic_middle;
        }

        public void setList_pic_middle(String list_pic_middle) {
            this.list_pic_middle = list_pic_middle;
        }
    }
}
