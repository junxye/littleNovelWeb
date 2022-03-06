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

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.logging.Logger;

@WebServlet("/AddUser")
public class addUser extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");
        Logger log = Logger.getLogger("com.MyLog");
        //PropertyConfigurator.configure("log4j.properties");

        Map<String, String[]> paramMap = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, paramMap);
            UserServiceDAO us = new UserServiceMPL();
            if(us.addUser(user)) {
                request.getSession().setAttribute("loginMessage", "恭喜您，注册成功，请登录!");
                request.getRequestDispatcher("jsp/user/login.jsp").forward(request, response);
            }else {
                response.sendRedirect("jsp/user/register.jsp");
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            log.info("Register error. "+e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
