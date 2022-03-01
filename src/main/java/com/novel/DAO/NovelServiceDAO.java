package com.novel.DAO;

import com.novel.entity.Novel;
import com.novel.entity.Page;

import java.util.Map;

public interface NovelServiceDAO {
    /**
     * 逻辑层接口类
     */


    // 修改小说信息
    void updateNovel(Novel novel);

    // 查询小说
    Novel queryNovel(int _number);

    // 按页数查询小说
    Page<Novel> queryNovelByPage(int currentPage, int pageSize,
                                 Map<String, String> paramMap, Map<String, String> conditionMap);

}
