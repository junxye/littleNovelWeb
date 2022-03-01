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

@WebServlet("/UpdateUser")
public class updateUser extends HttpServlet {
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
        Logger log = Logger.getLogger(String.valueOf(updateUser.class));
        PropertyConfigurator.configure("log4j.properties");
        Map<String, String[]> parameterMap = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, parameterMap);
            UserServiceDAO us = new UserServiceMPL();
            if(us.updateUser(user)) {
                System.out.println("个人信息更新成功...");
            }else {
                System.out.println("个人信息更新失败...");
            }
            response.sendRedirect("queryUser");
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            log.info("User update information error. "+e.getMessage());
        }
    }

}
