package com.gaoyanpeng.musicbaidu.activity.toolstow.lrc;

import java.util.List;

/**
 * Created by dllo on 16/12/6.
 * 解析歌词,得到LrcRow的集合
 */

public interface ILrcBuilder {
    List<LrcRow> getLrcRows(String rawLrc);
}
