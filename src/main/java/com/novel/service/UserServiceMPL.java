package com.novel.service;


import com.novel.DAO.UserDAO;
import com.novel.DAO.UserServiceDAO;
import com.novel.entity.Page;
import com.novel.entity.User;
import com.novel.util.PageUtils;
import com.novel.util.SessionUtils;
import com.novel.util.TimeUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

public class UserServiceMPL implements UserServiceDAO {

    @Override
    public User queryUser(String _account) {
        //SqlSession session = SessionUtils.getSession();
        //UserMPL userMPL = session.getMapper(UserMPL.class);
        UserDAO userMPL = new UserMPL();
        User user = userMPL.queryUser(_account);
        //时间转化
        System.out.println(user);
        if(user!=null) {
            user.setAddTime(TimeUtils.parseTime(user.getAddTime()));
        }
        SessionUtils.closeSession();
        return user;
    }

    @Override
    public boolean updateUser(User user) {
        //SqlSession session = SessionUtils.getSession();
        //UserMPL userMPL = session.getMapper(UserMPL.class);
        UserDAO userMPL = new UserMPL();
        if(userMPL.queryUser(user.getAccount())!=null) {
            boolean result = userMPL.updateUser(user);
            //session.commit();
            SessionUtils.closeSession();
            return result;
        }else {
            SessionUtils.closeSession();
            return false;
        }
    }

    @Override
    public boolean addUser(User user) {
        //SqlSession session = SessionUtils.getSession();
        //UserMPL userMPL = session.getMapper(UserMPL.class);
        UserDAO userMPL = new UserMPL();
        if (userMPL.queryUser(user.getAccount()) == null){
            boolean result = userMPL.addUser(user);
            //session.commit();
            SessionUtils.closeSession();
            return result;
        }
        SessionUtils.closeSession();
        return false;
    }

    @Override
    public boolean deleteUser(String _account) {
        //SqlSession session = SessionUtils.getSession();
        //UserMPL userMPL = session.getMapper(UserMPL.class);
        UserDAO userMPL = new UserMPL();
        //先判断用户是否存在
        if(userMPL.queryUser(_account)!=null) {
            boolean result = userMPL.deleteUser(_account);
            //session.commit();
            SessionUtils.closeSession();
            return result;
        }else {
            SessionUtils.closeSession();
            return false;
        }
    }

    @Override
    public Page<User> queryByPage(int currentPageNumber, int pageSize, Map<String, String[]> paramMap) {
        //SqlSession session = SessionUtils.getSession();
        //UserMPL userMPL = session.getMapper(UserMPL.class);
        UserDAO userMPL = new UserMPL();
        int totalData = userMPL.queryUserNumber(paramMap);//总数据
        Page<User> pages = PageUtils.getPages(currentPageNumber, pageSize, totalData);
        if(totalData==0) {
            SessionUtils.closeSession();
            return pages;
        }
        List<User> pageLists = userMPL.queryUserByPage(pages.getCurrentPage(), pageSize, paramMap);
        //处理时间
        for (User user : pageLists) {
            if(user!=null) {
                user.setAddTime(TimeUtils.parseTime(user.getAddTime()));
            }
        }
        pages.setPageLists(pageLists);//封装一页结果集
        SessionUtils.closeSession();
        return pages;
    }
}
