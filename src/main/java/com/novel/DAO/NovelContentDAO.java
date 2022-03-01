package com.novel.DAO;

import com.novel.entity.NovelContent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NovelContentDAO {

    // 查询书籍内容
    boolean queryNovelContentIsExist(@Param("novelNumber")int novelNumber);

    // 获取章节
    NovelContent queryNovelContent(@Param("novelNumber")int novelNumber, @Param("chapterNumber")int chapterNumber);

    // 增加章节
    boolean addNovelContent(NovelContent novelContent);

    // 获取总章节数目
    int queryContentNumber(int _number);

    // 返回章节目录
    List<NovelContent> queryNovelLog(int _number);

}
