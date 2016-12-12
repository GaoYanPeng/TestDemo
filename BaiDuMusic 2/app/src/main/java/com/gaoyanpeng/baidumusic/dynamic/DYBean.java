package com.gaoyanpeng.baidumusic.dynamic;

import java.util.List;

/**
 * Created by 高研鹏 on 2016/11/28.
 */

public class DYBean {


    private String last_timestamp;
    private int is_guide;
    private int error_code;
    private String last_msg_id;
    private List<MsgBean> msg;
    private List<TopicBean> topics;

    public String getLast_timestamp() {
        return last_timestamp;
    }

    public void setLast_timestamp(String last_timestamp) {
        this.last_timestamp = last_timestamp;
    }

    public int getIs_guide() {
        return is_guide;
    }

    public void setIs_guide(int is_guide) {
        this.is_guide = is_guide;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getLast_msg_id() {
        return last_msg_id;
    }

    public void setLast_msg_id(String last_msg_id) {
        this.last_msg_id = last_msg_id;
    }

    public List<MsgBean> getMsg() {
        return msg;
    }

    public void setMsg(List<MsgBean> msg) {
        this.msg = msg;
    }

    public List<TopicBean> getTopics() {
        return topics;
    }

    public void setTopics(List<TopicBean> topics) {
        this.topics = topics;
    }

    public static class MsgBean {
        private String msg_parent_id;
        private int rtime;
        private int share_num;
        private int ctime;
        private AuthorBean author;
        private int comment_num;
        private int isAuthor;
        private int specFlag;
        private int islike;
        private int msgtype;
        private ContentBean content;
        private int isFriend;
        private int status;
        private int zan_num;
        private String msgid;
        private String msg;
        private TopicBean topic;
        private List<?> msg_users;
        private List<PiclistBean> piclist;

        public String getMsg_parent_id() {
            return msg_parent_id;
        }

        public void setMsg_parent_id(String msg_parent_id) {
            this.msg_parent_id = msg_parent_id;
        }

        public int getRtime() {
            return rtime;
        }

        public void setRtime(int rtime) {
            this.rtime = rtime;
        }

        public int getShare_num() {
            return share_num;
        }

        public void setShare_num(int share_num) {
            this.share_num = share_num;
        }

        public int getCtime() {
            return ctime;
        }

        public void setCtime(int ctime) {
            this.ctime = ctime;
        }

        public AuthorBean getAuthor() {
            return author;
        }

        public void setAuthor(AuthorBean author) {
            this.author = author;
        }

        public int getComment_num() {
            return comment_num;
        }

        public void setComment_num(int comment_num) {
            this.comment_num = comment_num;
        }

        public int getIsAuthor() {
            return isAuthor;
        }

        public void setIsAuthor(int isAuthor) {
            this.isAuthor = isAuthor;
        }

        public int getSpecFlag() {
            return specFlag;
        }

        public void setSpecFlag(int specFlag) {
            this.specFlag = specFlag;
        }

        public int getIslike() {
            return islike;
        }

        public void setIslike(int islike) {
            this.islike = islike;
        }

        public int getMsgtype() {
            return msgtype;
        }

        public void setMsgtype(int msgtype) {
            this.msgtype = msgtype;
        }

        public ContentBean getContent() {
            return content;
        }

        public void setContent(ContentBean content) {
            this.content = content;
        }

        public int getIsFriend() {
            return isFriend;
        }

        public void setIsFriend(int isFriend) {
            this.isFriend = isFriend;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getZan_num() {
            return zan_num;
        }

        public void setZan_num(int zan_num) {
            this.zan_num = zan_num;
        }

        public String getMsgid() {
            return msgid;
        }

        public void setMsgid(String msgid) {
            this.msgid = msgid;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public TopicBean getTopic() {
            return topic;
        }

        public void setTopic(TopicBean topic) {
            this.topic = topic;
        }

        public List<?> getMsg_users() {
            return msg_users;
        }

        public void setMsg_users(List<?> msg_users) {
            this.msg_users = msg_users;
        }

        public List<PiclistBean> getPiclist() {
            return piclist;
        }

        public void setPiclist(List<PiclistBean> piclist) {
            this.piclist = piclist;
        }

        public static class AuthorBean {
            private String userpic;
            private String flag;
            private String userpic_small;
            private String userid;
            private String username;

            public String getUserpic() {
                return userpic;
            }

            public void setUserpic(String userpic) {
                this.userpic = userpic;
            }

            public String getFlag() {
                return flag;
            }

            public void setFlag(String flag) {
                this.flag = flag;
            }

            public String getUserpic_small() {
                return userpic_small;
            }

            public void setUserpic_small(String userpic_small) {
                this.userpic_small = userpic_small;
            }

            public String getUserid() {
                return userid;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }
        }

        public static class ContentBean {
            private int content_id;
            private int tinguid;
            private int content_type;
            private String title;
            private int status;
            private String artist_name;
            private String pic;
            private String artist_id;

            public int getContent_id() {
                return content_id;
            }

            public void setContent_id(int content_id) {
                this.content_id = content_id;
            }

            public int getTinguid() {
                return tinguid;
            }

            public void setTinguid(int tinguid) {
                this.tinguid = tinguid;
            }

            public int getContent_type() {
                return content_type;
            }

            public void setContent_type(int content_type) {
                this.content_type = content_type;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getArtist_name() {
                return artist_name;
            }

            public void setArtist_name(String artist_name) {
                this.artist_name = artist_name;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getArtist_id() {
                return artist_id;
            }

            public void setArtist_id(String artist_id) {
                this.artist_id = artist_id;
            }
        }

        public static class TopicBean {
            private String pic_750x215;
            private String pic_50x50;
            private String pic_350x170;
            private String editor;
            private String attr;
            private String topic_title;
            private int ctime;
            private int is_recommend;
            private String topic_id;
            private String pic_980x280;
            private String pic_min;
            private int mod_state;
            private String desc;
            private String pic;
            private String pic_700x340;
            private String min_pic;
            private int usertype;
            private String pic_180x88;
            private int nums;
            private int status;

            public String getPic_750x215() {
                return pic_750x215;
            }

            public void setPic_750x215(String pic_750x215) {
                this.pic_750x215 = pic_750x215;
            }

            public String getPic_50x50() {
                return pic_50x50;
            }

            public void setPic_50x50(String pic_50x50) {
                this.pic_50x50 = pic_50x50;
            }

            public String getPic_350x170() {
                return pic_350x170;
            }

            public void setPic_350x170(String pic_350x170) {
                this.pic_350x170 = pic_350x170;
            }

            public String getEditor() {
                return editor;
            }

            public void setEditor(String editor) {
                this.editor = editor;
            }

            public String getAttr() {
                return attr;
            }

            public void setAttr(String attr) {
                this.attr = attr;
            }

            public String getTopic_title() {
                return topic_title;
            }

            public void setTopic_title(String topic_title) {
                this.topic_title = topic_title;
            }

            public int getCtime() {
                return ctime;
            }

            public void setCtime(int ctime) {
                this.ctime = ctime;
            }

            public int getIs_recommend() {
                return is_recommend;
            }

            public void setIs_recommend(int is_recommend) {
                this.is_recommend = is_recommend;
            }

            public String getTopic_id() {
                return topic_id;
            }

            public void setTopic_id(String topic_id) {
                this.topic_id = topic_id;
            }

            public String getPic_980x280() {
                return pic_980x280;
            }

            public void setPic_980x280(String pic_980x280) {
                this.pic_980x280 = pic_980x280;
            }

            public String getPic_min() {
                return pic_min;
            }

            public void setPic_min(String pic_min) {
                this.pic_min = pic_min;
            }

            public int getMod_state() {
                return mod_state;
            }

            public void setMod_state(int mod_state) {
                this.mod_state = mod_state;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getPic_700x340() {
                return pic_700x340;
            }

            public void setPic_700x340(String pic_700x340) {
                this.pic_700x340 = pic_700x340;
            }

            public String getMin_pic() {
                return min_pic;
            }

            public void setMin_pic(String min_pic) {
                this.min_pic = min_pic;
            }

            public int getUsertype() {
                return usertype;
            }

            public void setUsertype(int usertype) {
                this.usertype = usertype;
            }

            public String getPic_180x88() {
                return pic_180x88;
            }

            public void setPic_180x88(String pic_180x88) {
                this.pic_180x88 = pic_180x88;
            }

            public int getNums() {
                return nums;
            }

            public void setNums(int nums) {
                this.nums = nums;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }

        public static class PiclistBean {
            private String pic_large;
            private String master;
            private String pic_middle;
            private String pic_small;
            private String pic_360;
            private String thumbnail;

            public String getPic_large() {
                return pic_large;
            }

            public void setPic_large(String pic_large) {
                this.pic_large = pic_large;
            }

            public String getMaster() {
                return master;
            }

            public void setMaster(String master) {
                this.master = master;
            }

            public String getPic_middle() {
                return pic_middle;
            }

            public void setPic_middle(String pic_middle) {
                this.pic_middle = pic_middle;
            }

            public String getPic_small() {
                return pic_small;
            }

            public void setPic_small(String pic_small) {
                this.pic_small = pic_small;
            }

            public String getPic_360() {
                return pic_360;
            }

            public void setPic_360(String pic_360) {
                this.pic_360 = pic_360;
            }

            public String getThumbnail() {
                return thumbnail;
            }

            public void setThumbnail(String thumbnail) {
                this.thumbnail = thumbnail;
            }
        }
    }
    public static  class TopicBean{
        private String pic_750x215;
        private String pic_50x50;
        private String pic_350x170;
        private String editor;
        private String attr;
        private String topic_title;
        private int ctime;
        private int is_recommend;
        private String topic_id;
        private String pic_980x280;
        private String pic_min;
        private int mod_state;
        private String desc;
        private String pic;
        private String pic_700x340;
        private String min_pic;
        private int usertype;
        private String pic_180x88;
        private int nums;
        private int status;

        public String getPic_750x215() {
            return pic_750x215;
        }

        public void setPic_750x215(String pic_750x215) {
            this.pic_750x215 = pic_750x215;
        }

        public String getPic_50x50() {
            return pic_50x50;
        }

        public void setPic_50x50(String pic_50x50) {
            this.pic_50x50 = pic_50x50;
        }

        public String getPic_350x170() {
            return pic_350x170;
        }

        public void setPic_350x170(String pic_350x170) {
            this.pic_350x170 = pic_350x170;
        }

        public String getEditor() {
            return editor;
        }

        public void setEditor(String editor) {
            this.editor = editor;
        }

        public String getAttr() {
            return attr;
        }

        public void setAttr(String attr) {
            this.attr = attr;
        }

        public String getTopic_title() {
            return topic_title;
        }

        public void setTopic_title(String topic_title) {
            this.topic_title = topic_title;
        }

        public int getCtime() {
            return ctime;
        }

        public void setCtime(int ctime) {
            this.ctime = ctime;
        }

        public int getIs_recommend() {
            return is_recommend;
        }

        public void setIs_recommend(int is_recommend) {
            this.is_recommend = is_recommend;
        }

        public String getTopic_id() {
            return topic_id;
        }

        public void setTopic_id(String topic_id) {
            this.topic_id = topic_id;
        }

        public String getPic_980x280() {
            return pic_980x280;
        }

        public void setPic_980x280(String pic_980x280) {
            this.pic_980x280 = pic_980x280;
        }

        public String getPic_min() {
            return pic_min;
        }

        public void setPic_min(String pic_min) {
            this.pic_min = pic_min;
        }

        public int getMod_state() {
            return mod_state;
        }

        public void setMod_state(int mod_state) {
            this.mod_state = mod_state;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getPic_700x340() {
            return pic_700x340;
        }

        public void setPic_700x340(String pic_700x340) {
            this.pic_700x340 = pic_700x340;
        }

        public String getMin_pic() {
            return min_pic;
        }

        public void setMin_pic(String min_pic) {
            this.min_pic = min_pic;
        }

        public int getUsertype() {
            return usertype;
        }

        public void setUsertype(int usertype) {
            this.usertype = usertype;
        }

        public String getPic_180x88() {
            return pic_180x88;
        }

        public void setPic_180x88(String pic_180x88) {
            this.pic_180x88 = pic_180x88;
        }

        public int getNums() {
            return nums;
        }

        public void setNums(int nums) {
            this.nums = nums;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
