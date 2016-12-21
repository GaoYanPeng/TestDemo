package com.gaoyanpeng.musicbaidu.welcome;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 高研鹏 on 2016/11/29.
 */

public class WelcomeBean {

    private int error_code;
    private MaterialMapBean material_map;

    public MaterialMapBean getMaterial_map() {
        return material_map;
    }

    public WelcomeBean setMaterial_map(MaterialMapBean material_map) {
        this.material_map = material_map;
        return this;
    }

    public static class MaterialMapBean {
        //        @SerializedName
        @SerializedName("712")
        private WeNumberBean number;

        public WeNumberBean getNumber() {
            return number;
        }

        public MaterialMapBean setNumber(WeNumberBean number) {
            this.number = number;
            return this;
        }
    }

    public static  class WeNumberBean{

        private DisPlayContent display_content;

        public DisPlayContent getDisplay_content() {
            return display_content;
        }

        public WeNumberBean setDisplay_content(DisPlayContent display_content) {
            this.display_content = display_content;
            return this;
        }

        public static  class  DisPlayContent{

            private  String weburl;
            private String  picture;
            private String link_value;

            public String getLink_value() {
                return link_value;
            }

            public DisPlayContent setLink_value(String link_value) {
                this.link_value = link_value;
                return this;
            }

            public String getPicture() {
                return picture;
            }

            public DisPlayContent setPicture(String picture) {
                this.picture = picture;
                return this;
            }

            public String getWeburl() {
                return weburl;
            }

            public DisPlayContent setWeburl(String weburl) {
                this.weburl = weburl;
                return this;
            }
        }
    }
}
