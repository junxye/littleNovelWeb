package com.novel.service;

import com.novel.DAO.NovelDAO;
import com.novel.DAO.NovelShelfDAO;
import com.novel.entity.Novel;
import com.novel.entity.NovelShelf;
import com.novel.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.log4j.PropertyConfigurator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class ShelfMPL implements NovelShelfDAO {

    @Override
    public boolean queryNovelExist(String _account, int _number) {
        Logger log = Logger.getLogger(String.valueOf(ShelfMPL.class));
        //PropertyConfigurator.configure("log4j.properties");
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        long num = 0l;
        try {
            if (_number != 0)
                num = (long) runner.query("SELECT COUNT(*) FROM novelShelf WHERE account=? AND novelNumber=?", new ScalarHandler(), _account, _number);
            else
                num = (long) runner.query("SELECT COUNT(*) FROM novelShelf WHERE account=?", new ScalarHandler(), _account);

            if (num > 0) return true;
            else return false;
        } catch (SQLException e) {
            e.printStackTrace();
            log.info("User "+_account+" cannot find shelf. "+e.getMessage());
        }
        return false;
    }

    @Override
    public boolean addNovelToShelf(String _account, int _number) {
        Logger log = Logger.getLogger(String.valueOf(ShelfMPL.class));
        //PropertyConfigurator.configure("log4j.properties");
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        try {
            NovelDAO novelDAO = new NovelMPL();
            Novel novel = novelDAO.queryNovel(_number);
            runner.update("INSERT INTO novelshelf (`novelNumber`, `account`,`novelName`) VALUE (?,?,?)", _number, _account,novel.getNovelName());
            log.info("User "+_account+" add novel success. ");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            log.info("User "+_account+" add novel failure. "+e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteNovelFromShelf(String _account, int _number) {
        Logger log = Logger.getLogger(String.valueOf(ShelfMPL.class));
        //PropertyConfigurator.configure("log4j.properties");
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        try {
            runner.update("DELETE FROM novelshelf WHERE account=? AND novelNumber=?", _account, _number);
            log.info("User "+_account+" delete novel success. ");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            log.info("User "+_account+" delete novel failure. "+e.getMessage());
        }
        return false;
    }

    @Override
    public int queryNovelNumberInShelf(String _account, Map<String, String[]> paramMap) {
        Logger log = Logger.getLogger(String.valueOf(ShelfMPL.class));
        //PropertyConfigurator.configure("log4j.properties");
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "SELECT COUNT(*) FROM novelshelf WHERE account="+ _account;
        /*
        if (paramMap.get("_name").toString() != null || Arrays.toString(paramMap.get("_name")) != ""){
            sql += " AND novelName = "+Arrays.toString(paramMap.get("_name"));
        }*/
        for (String key : paramMap.keySet()) {
            System.out.println("QueryNovelNumberInShelf: key= "+ key + " and value= " + paramMap.get("_name").toString());
        }
        try {
            Long l = (Long) runner.query("SELECT COUNT(*) FROM novelshelf WHERE account=?", new ScalarHandler(), _account);
            return Integer.parseInt(String.valueOf(l));
        } catch (SQLException e) {
            e.printStackTrace();
            log.info("User "+_account+" find novel in shelf failure. "+e.getMessage());
        }
        return 0;
    }

    @Override
    public List<NovelShelf> queryNovelInShelf(String _account, int currentPage, int pageSize, Map<String, String[]> paramMap) {
        Logger log = Logger.getLogger(String.valueOf(ShelfMPL.class));
        //PropertyConfigurator.configure("log4j.properties");
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "SELECT * FROM novelshelf WHERE account= '" + _account + "' ORDER BY ";
        if (paramMap.get("novelNumber") != null) sql += "novelNumber";
        else sql += "time";
        //sql += " LIMIT "+ pageSize + "OFFSET"+ currentPage;
        for (String key : paramMap.keySet()) {
            System.out.println("QueryNovelInShelf: key= "+ key + " and value= " + Arrays.toString(paramMap.get(key)));
        }
        try {
            /*
            List<NovelShelf> list = new ArrayList<NovelShelf>();
            for (int i = currentPage*pageSize; i<=currentPage*pageSize; i++){
                String s = sql + " LIMIT " + 1 + " OFFSET " + i;
                list.add(runner.query(s, new BeanHandler<NovelShelf>(NovelShelf.class)));
            }
            return list;
             */
            currentPage++;
            List<NovelShelf> list = runner.query(sql,new BeanListHandler<NovelShelf>(NovelShelf.class));
            if (currentPage * pageSize > list.size())
                return list.subList((currentPage-1) * pageSize, list.size());
            return list.subList((currentPage-1) * pageSize, currentPage * pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
            log.info("User "+_account+" shelf search failure. "+e.getMessage());
        }
        return null;
    }
}
