package com.gaoyanpeng.musicbaidu.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 高研鹏 on 2016/12/13.
 */
@Entity
public class HousPerson {
    @Id
    private Long id;
    private String name,songName,image;
    @Generated(hash = 1076692319)
    public HousPerson(Long id, String name, String songName, String image) {
        this.id = id;
        this.name = name;
        this.songName = songName;
        this.image = image;
    }
    @Generated(hash = 588671688)
    public HousPerson() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSongName() {
        return this.songName;
    }
    public void setSongName(String songName) {
        this.songName = songName;
    }
    public String getImage() {
        return this.image;
    }
    public void setImage(String image) {
        this.image = image;
    }

}
