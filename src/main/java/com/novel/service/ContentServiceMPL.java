package com.novel.service;

import com.novel.DAO.ContentServiceDAO;
import com.novel.DAO.NovelContentDAO;
import com.novel.entity.NovelContent;
import com.novel.util.ContentUtils;
import com.novel.util.SessionUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ContentServiceMPL implements ContentServiceDAO {

    @Override
    public boolean queryContentIsExist(int _number) {
        //SqlSession session = SessionUtils.getSession();
        //ContentMPL contentMPL = session.getMapper(ContentMPL.class);
        NovelContentDAO contentMPL = new ContentMPL();
        boolean result = contentMPL.queryNovelContentIsExist(_number);
        SessionUtils.closeSession();
        return result;
    }

    @Override
    public NovelContent queryContent(int _number, int chapterNumber) {
        //SqlSession session = SessionUtils.getSession();
        //ContentMPL contentMPL = session.getMapper(ContentMPL.class);
        NovelContentDAO contentMPL = new ContentMPL();
        NovelContent novelContent = contentMPL.queryNovelContent(_number, chapterNumber);
        SessionUtils.closeSession();
        return novelContent;
    }

    @Override
    public int queryTotalContent(int _number) {
        //SqlSession session = SessionUtils.getSession();
        //ContentMPL contentMPL = session.getMapper(ContentMPL.class);
        NovelContentDAO contentMPL = new ContentMPL();
        int total = contentMPL.queryContentNumber(_number);
        SessionUtils.closeSession();
        return total;
    }

    @Override
    public boolean addContent(String filePath, String novelName, String code, int novelNumber) {
        boolean flag = false;
        // 小说的章节集合
        List<NovelContent> cuts = ContentUtils.cut(filePath, novelName, code);
        // 格式处理后的章节集合
        List<NovelContent> contentFormat = ContentUtils.novelContentFormat(cuts, novelNumber);

        // 遍历进行了格式处理后的章节集合，将所有章节存放到数据库中
        for (NovelContent novelContent : contentFormat) {
            //SqlSession session = SessionUtils.getSession();
            //ContentMPL contentMPL = session.getMapper(ContentMPL.class);
            NovelContentDAO contentMPL = new ContentMPL();
            if(contentMPL.addNovelContent(novelContent)) {
                flag = true;
                //System.out.println(novelContent.getNovelName()+" "+novelContent.getChapterName()+" 添加成功...");
            }else {
                flag = false;
                //System.out.println(novelContent.getNovelName()+" "+novelContent.getChapterName()+" 添加失败...");
            }
        }
        return flag;
    }

    @Override
    public List<NovelContent> queryNovel(int _number) {
        //SqlSession session = SessionUtils.getSession();
        //ContentMPL contentMPL = session.getMapper(ContentMPL.class);
        NovelContentDAO contentMPL = new ContentMPL();
        List<NovelContent> novelLog = contentMPL.queryNovelLog(_number);
        SessionUtils.closeSession();
        return novelLog;
    }
}
