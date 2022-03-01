package com.novel.service;

import com.novel.DAO.NovelServiceDAO;
import com.novel.DAO.NovelShelfDAO;
import com.novel.DAO.ShelfServiceDAO;
import com.novel.entity.NovelShelf;
import com.novel.entity.Page;
import com.novel.util.PageUtils;
import com.novel.util.SessionUtils;
import com.novel.util.TimeUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

public class ShelfServiceMPL implements ShelfServiceDAO {

    @Override
    public boolean queryShelf(String _account, int _number) {
        //SqlSession session = SessionUtils.getSession();
        //ShelfMPL shelfDAO = session.getMapper(ShelfMPL.class);
        NovelShelfDAO shelfDAO = new ShelfMPL();
        boolean result = shelfDAO.queryNovelExist(_account, _number);
        SessionUtils.closeSession();
        return result;
    }

    @Override
    public boolean addToShelf(String _account, int _number) {
        //SqlSession session = SessionUtils.getSession();
        //ShelfMPL shelfDAO = session.getMapper(ShelfMPL.class);
        NovelShelfDAO shelfDAO = new ShelfMPL();
        // 判断小说存在
        if (!shelfDAO.queryNovelExist(_account, _number)){
            boolean result = shelfDAO.addNovelToShelf(_account, _number);
            //session.commit();
            SessionUtils.closeSession();
            return result;
        }
        SessionUtils.closeSession();
        return false;
    }

    @Override
    public boolean deleteFromShelf(String _account, int _number) {
        //SqlSession session = SessionUtils.getSession();
        //ShelfMPL shelfDAO = session.getMapper(ShelfMPL.class);
        NovelShelfDAO shelfDAO = new ShelfMPL();
        if (shelfDAO.queryNovelExist(_account, _number)){
            boolean result = shelfDAO.deleteNovelFromShelf(_account, _number);
            //session.commit();
            SessionUtils.closeSession();
            return result;
        }
        SessionUtils.closeSession();
        return false;
    }

    @Override
    public Page<NovelShelf> queryShelfByAccount(String _account, int currentPageNumber, int pageSize, Map<String, String[]> paramMap) {
        //SqlSession session = SessionUtils.getSession();
        //ShelfMPL shelfDAO = session.getMapper(ShelfMPL.class);
        NovelShelfDAO shelfDAO = new ShelfMPL();
        int totalData = shelfDAO.queryNovelNumberInShelf(_account, paramMap);
        Page<NovelShelf> pages = PageUtils.getPages(currentPageNumber, pageSize, totalData);
        if (totalData == 0){
            SessionUtils.closeSession();
            return pages;
        }
        List<NovelShelf> pageLists = shelfDAO.queryNovelInShelf(_account, pages.getCurrentPage(), pageSize, paramMap);
        for (NovelShelf novelShelf : pageLists){
            if (novelShelf != null)
                novelShelf.setTime(TimeUtils.parseTime(novelShelf.getTime()));
        }
        pages.setPageLists(pageLists);
        SessionUtils.closeSession();
        return pages;
    }
}
