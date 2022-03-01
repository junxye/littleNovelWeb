package com.novel.servlet;

import com.novel.DAO.UserServiceDAO;
import com.novel.entity.User;
import com.novel.service.UserServiceMPL;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/QueryUser")
public class queryUser extends HttpServlet {
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
        User user = (User)request.getSession().getAttribute("user");
        UserServiceDAO us = new UserServiceMPL();
        User u = us.queryUser(user.getAccount());
        request.setAttribute("u", u);
        request.getRequestDispatcher("jsp/user/user.jsp").forward(request, response);
    }

}
