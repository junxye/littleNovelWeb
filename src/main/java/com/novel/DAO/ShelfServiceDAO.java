package com.novel.DAO;

import com.novel.entity.NovelShelf;
import com.novel.entity.Page;

import java.util.Map;

public interface ShelfServiceDAO {

    // 判断书架是否存在小说
    boolean queryShelf(String _account, int _number);

    // 加入书架
    boolean addToShelf(String _account, int _number);

    // 从书架删除
    boolean deleteFromShelf(String _account, int _number);

    // 类查询
    Page<NovelShelf> queryShelfByAccount(String _account, int currentPageNumber,
                                         int pageSize, Map<String, String[]> paramMap);

}
