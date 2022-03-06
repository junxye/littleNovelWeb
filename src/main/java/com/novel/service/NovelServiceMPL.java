package com.novel.service;

import com.novel.DAO.NovelDAO;
import com.novel.DAO.NovelServiceDAO;
import com.novel.entity.Novel;
import com.novel.entity.Page;
import com.novel.util.PageUtils;
import com.novel.util.SessionUtils;
import com.novel.util.TimeUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

public class NovelServiceMPL implements NovelServiceDAO {

    @Override
    public void updateNovel(Novel novel) {
        //SqlSession session = SessionUtils.getSession();
        //NovelMPL novelDAO = session.getMapper(NovelMPL.class);
        NovelDAO novelDAO = new NovelMPL();
        novelDAO.updateNovel(novelDAO.queryNovel(novel.getNum()));
        //session.commit();
        SessionUtils.closeSession();
    }

    @Override
    public Novel queryNovel(int _number) {
        //SqlSession session = SessionUtils.getSession();
        //NovelMPL novelDAO = session.getMapper(NovelMPL.class);
        NovelDAO novelDAO = new NovelMPL();
        Novel novel = novelDAO.queryNovel(_number);System.out.println(novel);
        if (novel != null)
            novel.setAddTime(TimeUtils.parseTime(novel.getAddTime()));
        SessionUtils.closeSession();
        return novel;
    }

    @Override
    public Page<Novel> queryNovelByPage(int currentPage, int pageSize, Map<String, String> paramMap, Map<String, String> conditionMap) {
        //SqlSession session = SessionUtils.getSession();
        //NovelMPL novelDAO = session.getMapper(NovelMPL.class);
        NovelDAO novelDAO = new NovelMPL();
        int totalData = novelDAO.queryNovelNumber(paramMap);
        Page<Novel> pages = PageUtils.getPages(currentPage,pageSize,totalData);
        if (totalData == 0){
            SessionUtils.closeSession();
            return pages;
        }
        //System.out.println("pages: "+pages.getCurrentPageNumber());
        List<Novel> pageLists = novelDAO.queryNovelByPage(pages.getCurrentPageNumber(),pageSize,paramMap,conditionMap);
        //System.out.println(pageLists);
        for (Novel novel : pageLists){
            if (novel != null)
                novel.setAddTime(TimeUtils.parseTime(novel.getAddTime()));
        }
        pages.setPageLists(pageLists);
        SessionUtils.closeSession();
        return pages;
    }

}
