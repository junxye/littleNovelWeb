package com.novel.service;

import com.novel.DAO.NovelContentDAO;
import com.novel.entity.NovelContent;
import com.novel.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContentMPL implements NovelContentDAO {

    @Override
    public boolean queryNovelContentIsExist(int novelNumber) {
        Logger log = Logger.getLogger(ContentMPL.class);
        //PropertyConfigurator.configure("log4j.properties");
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        try {
            int i = (int) runner.query("SELECT * FROM novelcontent WHERE novelNumber=?", new ScalarHandler(), novelNumber);
            if (i > 0) return true;
            else return false;
        } catch (SQLException e) {
            e.printStackTrace();
            log.info("Novel chapter query error. "+e.getMessage());
        }
        return false;
    }

    @Override
    public NovelContent queryNovelContent(int novelNumber, int chapterNumber) {
        Logger log = Logger.getLogger(ContentMPL.class);
        //PropertyConfigurator.configure("log4j.properties");
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        try {
            return runner.query("SELECT * FROM novelcontent WHERE novelNumber=? AND chapterNumber=?", new BeanHandler<NovelContent>(NovelContent.class), novelNumber, chapterNumber);
        } catch (SQLException e) {
            e.printStackTrace();
            log.info("Novel chapter gain error. "+e.getMessage());
        }
        return null;
    }

    @Override
    public boolean addNovelContent(NovelContent novelContent) {
        Logger log = Logger.getLogger(ContentMPL.class);
        //PropertyConfigurator.configure("log4j.properties");
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        try {
            runner.update("INSERT INTO novelcontent (chapterId, novelName, chapterName, chapterContent, novelNumber, chapterNumber) VALUE (?,?,?,?,?,?)",
                    novelContent.getChapterId(), novelContent.getNovelName(), novelContent.getChapterName(),
                    novelContent.getChapterContent(), novelContent.getNovelNumber(), novelContent.getChapterNumber());
            log.info("Chapter "+ novelContent.getChapterName() +" add success.");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            log.info("Chapter "+ novelContent.getChapterName() +" add failure. "+e.getMessage());
        }
        return false;
    }

    @Override
    public int queryContentNumber(int _number) {
        Logger log = Logger.getLogger(ContentMPL.class);
        //PropertyConfigurator.configure("log4j.properties");
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        try {
            Long s = (Long) runner.query("SELECT COUNT(*) FROM novelcontent WHERE novelNumber=?", new ScalarHandler(), _number);
            return Integer.parseInt(String.valueOf(s));
        } catch (SQLException e) {
            e.printStackTrace();
            log.info("Novel chapter number find error. "+e.getMessage());
        }
        return 0;
    }

    @Override
    public List<NovelContent> queryNovelLog(int _number) {
        Logger log = Logger.getLogger(ContentMPL.class);
        //PropertyConfigurator.configure("log4j.properties");
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "SELECT chapterNumber,chapterName FROM novelcontent WHERE novelNumber = " + _number;
        try {
            List<NovelContent> list = new ArrayList<NovelContent>();
            for (int i = 0; i < queryContentNumber(_number); i++){
                String s = sql + " LIMIT " + 1 + " OFFSET " + i;
                list.add(runner.query(s, new BeanHandler<NovelContent>(NovelContent.class)));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            log.info("Return chapter menu error. "+e.getMessage());
        }
        return null;
    }
}
