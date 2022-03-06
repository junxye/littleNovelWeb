package com.novel.service;

import com.novel.DAO.UserDAO;
import com.novel.entity.Novel;
import com.novel.entity.User;
import com.novel.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.log4j.PropertyConfigurator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class UserMPL implements UserDAO {

    @Override
    public User queryUser(String account) {

        Logger log = Logger.getLogger("com.MyLog");
        //PropertyConfigurator.configure("log4j.properties");
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        try {
            return runner.query("SELECT * FROM users WHERE account =?", new BeanHandler<User>(User.class), account);
        } catch (SQLException e) {
            e.printStackTrace();
            log.info("User"+account+" found fail. "+ e.getMessage());
        }
        return null;
    }

    @Override
    public boolean updateUser(User user) {
        Logger log = Logger.getLogger("com.MyLog");
        //PropertyConfigurator.configure("log4j.properties");
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "UPDATE users SET  ";
        if (user.getPassWord() != null && !user.getPassWord().equals("")) sql += "`passWord`=\""+user.getPassWord()+"\" ";
        if (user.getSex() != null && !user.getSex().equals("")) {
            if (sql.charAt(sql.length()-2) == '"') sql += ", ";
            sql += "`sex`=\""+user.getSex()+"\" ";
        }
        if (user.getAge() > 0) {
            if (sql.charAt(sql.length()-2) == '"') sql += ", ";
            sql += "`age`=\""+user.getAge()+"\" ";
        }
        if (user.getIntroduce() != null && !user.getIntroduce().equals("")) {
            if (sql.charAt(sql.length()-2) == '"') sql += ", ";
            sql += "`introduce`=\""+user.getIntroduce()+"\" ";
        }
        sql += " WHERE account = \""+user.getAccount() + "\"";
        try {
            runner.update(sql);
            log.info("User "+ user.getAccount()+" update success.");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            log.info("User "+ user.getAccount()+" update failure. "+e.getMessage());
        }
        return false;
    }

    @Override
    public boolean addUser(User user) {
        Logger log = Logger.getLogger("com.MyLog");
        //PropertyConfigurator.configure("log4j.properties");
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        try {
            runner.update("INSERT INTO users (`account`,`passWord`,`sex`,`age`,`introduce`) VALUE (?,?,?,?,?)",
                    user.getAccount(),user.getPassWord(),user.getSex(),user.getAge(),user.getIntroduce());
            log.info("User "+ user.getAccount()+" add success.");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            log.info("User "+ user.getAccount()+" add failure. "+e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteUser(String account) {
        Logger log = Logger.getLogger("com.MyLog");
        //PropertyConfigurator.configure("log4j.properties");
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        try {
            runner.update("DELETE FROM users WHERE account = ?",account);
            log.info("User "+ account+" delete success.");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            log.info("User "+ account+" delete failure.");
        }
        return false;
    }

    @Override
    public int queryUserNumber(Map<String, String[]> parameterMap) {
        Logger log = Logger.getLogger("com.MyLog");
        //PropertyConfigurator.configure("log4j.properties");
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        try {
            Long l = (Long) runner.query("SELECT COUNT(*) FROM users", new ScalarHandler());
            return Integer.parseInt(String.valueOf(l));
        } catch (SQLException e) {
            e.printStackTrace();
            log.info("User number get failure. "+e.getMessage());
        }
        return 0;
    }

    @Override
    public List<User> queryUserByPage(int currentPage, int pageSize, Map<String, String[]> paramMap) {
        Logger log = Logger.getLogger("com.MyLog");
        //PropertyConfigurator.configure("log4j.properties");
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "SELECT * FROM users ORDER BY ";
        if (paramMap != null){
            if (paramMap.get("account") != null) sql += "account";
            else sql += "id";
        }
        //sql += " DESC LIMIT "+pageSize + " OFFSET " + currentPage;
        try {
            currentPage++;
            List<User> list = runner.query(sql,new BeanListHandler<User>(User.class));
            if (currentPage * pageSize > list.size())
                return list.subList((currentPage-1) * pageSize, list.size());
            System.out.println("queryUserByPage : "+(currentPage-1) * pageSize+" "+ currentPage * pageSize);
            return list.subList((currentPage-1) * pageSize, currentPage * pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
            log.info("Get user message error. "+e.getMessage());
        }
        return null;
    }
}
