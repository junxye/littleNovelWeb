package com.novel.service;

import com.novel.DAO.NovelDAO;
import com.novel.entity.Novel;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.novel.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.log4j.PropertyConfigurator;

public class NovelMPL implements NovelDAO {

    @Override
    public Novel queryNovel(int number) {
        Logger log = Logger.getLogger("com.MyLog");
        //PropertyConfigurator.configure("message/log4j.properties");
        try {
            return findNovel(number);
        } catch (SQLException e) {
            e.printStackTrace();
            log.info("Novel found fail. "+ e.getMessage());
        }
        return null;
    }

    Novel findNovel(int number) throws SQLException {
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        System.out.println(runner.query("SELECT * FROM novel WHERE id =?", new ScalarHandler(), number));
        return runner.query("SELECT * FROM novel WHERE id =?", new BeanHandler<Novel>(Novel.class), number);
    }


    @Override
    public boolean updateNovel(Novel novel) {
        Logger log = Logger.getLogger("com.MyLog");
        //PropertyConfigurator.configure("log4j.properties");
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        try {
            runner.update("UPDATE novel SET `novelName`=?, `author`=?, `introduction`=?, `category`=?," +
                    "`readTime`=?, `totalWords`=?, `collectionTimes`=? WHERE id =?",
                    novel.getNovelName(), novel.getAuthor(), novel.getIntroduction(), novel.getCategory(),
                    novel.getReadTime(), novel.getTotalWords(), novel.getCollectionTimes());
            log.info(novel.getNovelName() + " update.");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            log.info("Update failure. "+ e.getMessage());
        }
        return false;
    }

    @Override
    public boolean addNovel(Novel novel) {
        Logger log = Logger.getLogger(String.valueOf(NovelMPL.class));
        //PropertyConfigurator.configure("message/log4j.properties");
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        try {
            runner.update("INSERT INTO novel (" +
                    "novelName, author, introduction, image, category, num, totalWords) VALUES(?,?,?,?,?,?,?)",
                    novel.getNovelName(), novel.getAuthor(), novel.getIntroduction(), novel.getImage(),
                    novel.getCategory(), novel.getNovelName(), novel.getTotalWords());
            log.info(novel.getNovelName() + " had add.");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            log.info("Add failure. "+ e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteNovel(int number) {
        Logger log = Logger.getLogger(String.valueOf(NovelMPL.class));
        //PropertyConfigurator.configure("log4j.properties");
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        try {
            runner.update("DELETE FROM novel WHERE num = "+number);
            log.info(number +" had delete.");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            log.info("Delete failure. "+ e.getMessage());
        }
        return false;
    }

    @Override
    public int queryNovelNumber(Map<String, String> paramMap) {
        Logger log = Logger.getLogger(String.valueOf(NovelMPL.class));
        //PropertyConfigurator.configure("log4j.properties");
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        try {
            Long l = (long) runner.query("SELECT COUNT(*) FROM novel", new ScalarHandler());
            return Integer.parseInt(String.valueOf(l));
        } catch (SQLException e) {
            e.printStackTrace();
            log.info("Error get novels' number. "+ e.getMessage());
        }
        return 0;
    }

    @Override
    public List<Novel> queryNovelByPage(int currentPage, int pageSize, Map<String, String> paramMap, Map<String, String> conditionMap) {
        Logger log = Logger.getLogger(String.valueOf(NovelMPL.class));
        //PropertyConfigurator.configure("log4j.properties");
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select * from novel ORDER BY ";
        if (conditionMap != null){
            if (conditionMap.get("addTime") !=null && !conditionMap.get("addTime").equals("")) sql += "addTime ";
            else if (conditionMap.get("totalWords") !=null && !conditionMap.get("totalWords").equals("")) sql += "totalWords ";
            else if (conditionMap.get("readTime") !=null && !conditionMap.get("readTime").equals("")) sql += "readTime ";
            else if (conditionMap.get("collectionTimes") !=null && !conditionMap.get("collectionTimes").equals("")) sql += "collectionTimes" ;
            else sql += "collectionTimes ";
        }
        //sql += "DESC LIMIT " + pageSize + " OFFSET " + currentPage;
        //System.out.println(sql);
        try {
            List<Novel> list = new LinkedList<>();
            for (int i = (currentPage-1)*pageSize; i<currentPage*pageSize; i++){
                String s = sql + "DESC LIMIT " + 1 + " OFFSET " + i;
                list.add(runner.query(s, new BeanHandler<Novel>(Novel.class)));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            log.info("Get sort error. "+ e.getMessage());
        }
        return null;
    }
}
