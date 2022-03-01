package com.novel.service;

import com.novel.DAO.NovelShelfDAO;
import com.novel.entity.NovelShelf;
import com.novel.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.log4j.PropertyConfigurator;

import java.sql.SQLException;
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
                num = (long) runner.query("SELECT COUNT(*) FROM novelShelf WHERE account=?", new ScalarHandler(), _account);
            else
                num = (long) runner.query("SELECT COUNT(*) FROM novelShelf WHERE account=? AND novelNumber=?", new ScalarHandler(), _account, _number);

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
            runner.update("INSERT INTO novelshelf (`novelNumber`, `account`) VALUE (?,?)", _number, _account);
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
        String sql = "SELECT * FROM novelshelf WHERE account="+ _account +" ORDER BY ";
        if (paramMap.get("novelNumber") != null) sql += "novelNumber";
        else sql += "time";
        sql += " LIMIT "+ pageSize + "OFFSET"+ currentPage;
        try {
            return (List<NovelShelf>) runner.query(sql, new BeanHandler<List>(List.class));
        } catch (SQLException e) {
            e.printStackTrace();
            log.info("User "+_account+" shelf search failure. "+e.getMessage());
        }
        return null;
    }
}
