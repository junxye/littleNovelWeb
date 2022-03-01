package com.novel.DAO;

import com.novel.entity.Novel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface NovelDAO {

    // 查询小说
    Novel queryNovel(int number);

    // 修改小说信息
    boolean updateNovel(Novel novel);

    // 增加小说
    boolean addNovel(Novel novel);

    // 删除小说
    boolean deleteNovel(int number);

    // 查询小说数目
    int queryNovelNumber(@Param("paramMap") Map<String,String> paramMap);

    // 按页查询小说
    List<Novel> queryNovelByPage(@Param("currentPage")int currentPage,
                                 @Param("pageSize")int pageSize,
                                 @Param("paramMap")Map<String ,String> paramMap,
                                 @Param("conditionMap")Map<String, String> conditionMap);

}
