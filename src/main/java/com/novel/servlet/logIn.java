package com.novel.servlet;

import com.novel.DAO.UserServiceDAO;
import com.novel.entity.User;
import com.novel.service.UserServiceMPL;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.logging.Logger;

@WebServlet("/LogIn")
public class logIn extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");
        Logger log = Logger.getLogger(String.valueOf(logIn.class));
        //PropertyConfigurator.configure("log4j.properties");


        Map<String, String[]> parameterMap = request.getParameterMap();
        User user = new User();
        try {
            //将获取的数据进行封装
            BeanUtils.populate(user, parameterMap);
            UserServiceDAO us = new UserServiceMPL();
            User user2 = us.queryUser(user.getAccount());//数据库查询回来的User
            if(user2!=null) {
                if(user.getPassWord().equals(user2.getPassWord())) {
                    request.getSession().setAttribute("user", user2);
                    if("管理员".equals(user2.getRole())) {
                        response.sendRedirect("QueryUserByPage");
                    }else {
                        response.sendRedirect("jsp/user/index_2.jsp");
                    }
                    //response.sendRedirect("jsp/user/index_2.jsp");
                }else {
                    request.setAttribute("loginMessage", "用户密码错误");
                    request.getRequestDispatcher("jsp/user/login.jsp").forward(request, response);
                }
            }else {
                request.setAttribute("loginMessage", "该用户不存在");
                request.getRequestDispatcher("jsp/user/login.jsp").forward(request, response);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            log.info("Login failure. "+e.getMessage());
        }

    }

}
