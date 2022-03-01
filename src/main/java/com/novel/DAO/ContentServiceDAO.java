package com.novel.DAO;

import com.novel.entity.NovelContent;

import java.util.List;

public interface ContentServiceDAO {

    // 查询内容存在
    boolean queryContentIsExist(int _number);

    // 查询内容
    NovelContent queryContent(int _number, int chapterNumber);

    // 获取总章节数
    int queryTotalContent(int _number);

    // 增加章节
    boolean addContent(String filePath, String novelName, String code, int novelNumber);

    // 返回章节目录
    List<NovelContent> queryNovel(int _number);

}
