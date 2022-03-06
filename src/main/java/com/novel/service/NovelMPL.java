package com.novel.service;

import com.novel.DAO.NovelDAO;
import com.novel.entity.Novel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.novel.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
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
        //System.out.println(runner.query("SELECT * FROM novel WHERE id =?", new ScalarHandler(), number));
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
        String sql = "SELECT COUNT(*) FROM novel";
        sql += queryCategory(paramMap);
        //System.out.println(sql);
        try {
            Long l = (long) runner.query(sql, new ScalarHandler());
            if (paramMap != null && paramMap.get("param") != null && !paramMap.get("param").equals("")){
                String s = "SELECT COUNT(*) FROM novel WHERE author LIKE '%"+paramMap.get("param")+"%' AND novelName NOT LIKE '%"+paramMap.get("param")+"%'";
                l += (long) runner.query(s, new ScalarHandler());
            }
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
        String sql = "select * from novel";
        sql += queryCategory(paramMap);
        sql += " ORDER BY ";
        if (conditionMap != null){
            if (conditionMap.get("addTime") !=null && !conditionMap.get("addTime").equals("")) sql += "addTime ";
            else if (conditionMap.get("totalWords") !=null && !conditionMap.get("totalWords").equals("")) sql += "totalWords ";
            else if (conditionMap.get("readTime") !=null && !conditionMap.get("readTime").equals("")) sql += "readTime ";
            else if (conditionMap.get("collectionTimes") !=null && !conditionMap.get("collectionTimes").equals("")) sql += "collectionTimes" ;
            else sql += "collectionTimes ";
        }

        try {
            //sql += " DESC LIMIT " + pageSize + " OFFSET " + (currentPage-1);
            sql += " DESC ";
            List<Novel> list = runner.query(sql,new BeanListHandler<Novel>(Novel.class));
            if (paramMap != null && paramMap.get("param") != null && !paramMap.get("param").equals("")){
                String s = "SELECT * FROM novel WHERE author LIKE '%"+paramMap.get("param")+"%' AND novelName NOT LIKE '%"+paramMap.get("param")+"%'";
                list.addAll(runner.query(s, new BeanListHandler<Novel>(Novel.class)));
            }
            if (currentPage * pageSize > list.size())
                return list.subList((currentPage-1) * pageSize, list.size());
            return list.subList((currentPage-1) * pageSize, currentPage * pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
            log.info("Get sort error. "+ e.getMessage());
        }
        return null;
    }

    private String queryCategory(Map<String, String> paramMap){
        String sql = "";
        if (paramMap != null){
            if (paramMap.get("category") != null && !paramMap.get("category").equals("")) {
                if (paramMap.get("category").equals("玄幻")) sql += " WHERE category = \"玄幻\"";
                else if (paramMap.get("category").equals("科幻")) sql += " WHERE category = \"科幻\"";
                else if (paramMap.get("category").equals("仙侠")) sql += " WHERE category = \"仙侠\"";
                else if (paramMap.get("category").equals("都市")) sql += " WHERE category = \"都市\"";
                else if (paramMap.get("category").equals("游戏")) sql += " WHERE category = \"游戏\"";
                else if (paramMap.get("category").equals("古言")) sql += " WHERE category = \"古言\"";
            }
            else if (paramMap.get("param") != null && !paramMap.get("param").equals("")){
                sql += " WHERE novelName like '%"+paramMap.get("param")+"%'";
            }
        }
        return sql;
    }
}
