package com.gaoyanpeng.musicbaidu.music.musicfragment.video;

import com.google.gson.annotations.SerializedName;

/**视频点击后显示的 bean
 * Created by 高研鹏 on 2016/12/2.
 */

public class VideoItemBean {

    private ResultBean result;
    public ResultBean getResult() {
        return result;
    }

    public VideoItemBean setResult(ResultBean result) {
        this.result = result;
        return this;
    }

    public static class ResultBean {
        private VideoInfoBean video_info;
        private FilesBean files;

        public FilesBean getFiles() {
            return files;
        }

        public ResultBean setFiles(FilesBean files) {
            this.files = files;
            return this;
        }

        public VideoInfoBean getVideo_info() {
            return video_info;
        }

        public ResultBean setVideo_info(VideoInfoBean video_info) {
            this.video_info = video_info;
            return this;
        }
    }

    public static class VideoInfoBean {
        private String video_id;
        private String mv_id;
        private String provider;
        private String sourcepath;
        private String thumbnail;
        private String thumbnail2;
        private String del_status;
        private String distribution;




        public String getVideo_id() {
            return video_id;
        }

        public void setVideo_id(String video_id) {
            this.video_id = video_id;
        }

        public String getMv_id() {
            return mv_id;
        }

        public void setMv_id(String mv_id) {
            this.mv_id = mv_id;
        }

        public String getProvider() {
            return provider;
        }

        public void setProvider(String provider) {
            this.provider = provider;
        }

        public String getSourcepath() {
            return sourcepath;
        }

        public void setSourcepath(String sourcepath) {
            this.sourcepath = sourcepath;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getThumbnail2() {
            return thumbnail2;
        }

        public void setThumbnail2(String thumbnail2) {
            this.thumbnail2 = thumbnail2;
        }

        public String getDel_status() {
            return del_status;
        }

        public void setDel_status(String del_status) {
            this.del_status = del_status;
        }

        public String getDistribution() {
            return distribution;
        }

        public void setDistribution(String distribution) {
            this.distribution = distribution;
        }
    }

    public static  class  FilesBean {
        @SerializedName("21")
        private NumberBean num;

        public NumberBean getNum() {
            return num;
        }

        public FilesBean setNum(NumberBean num) {
            this.num = num;
            return this;
        }
    }
    public static  class NumberBean{

        private String file_link;

        public String getFile_link() {
            return file_link;
        }

        public NumberBean setFile_link(String file_link) {
            this.file_link = file_link;
            return this;
        }
    }



}
