package com.novel.DAO;

import com.novel.entity.NovelShelf;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface NovelShelfDAO {

    // 判断小说是否存在
    boolean queryNovelExist(@Param("_account")String _account, @Param("_number")int _number);

    // 将小说加入书架
    boolean addNovelToShelf(@Param("_account")String _account, @Param("_number")int _number);

    // 将小说从书架中删除
    boolean deleteNovelFromShelf(@Param("_account")String _account, @Param("_number")int _number);

    // 书架中小说数量
    int queryNovelNumberInShelf(@Param("_account")String _account,
                                @Param("paramMap") Map<String, String[]> paramMap);

    // 查询书架中的书
    List<NovelShelf> queryNovelInShelf(@Param("_account")String _account, @Param("currentPage")int currentPage,
                                       @Param("pageSize")int pageSize, @Param("paramMap")Map<String, String[]> paramMap);

}
