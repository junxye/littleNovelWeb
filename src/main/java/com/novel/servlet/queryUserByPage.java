package com.novel.servlet;

import com.novel.DAO.UserServiceDAO;
import com.novel.entity.Page;
import com.novel.entity.User;
import com.novel.service.UserServiceMPL;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

@WebServlet("/QueryUserByPage")
public class queryUserByPage extends HttpServlet {
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
        String _currentPageNumber = request.getParameter("_currentPageNumber");
        if(_currentPageNumber==null || _currentPageNumber.equals("")) _currentPageNumber = "1";
        int currentPageNumber = Integer.parseInt(_currentPageNumber);
        Map<String, String[]> paramMap = request.getParameterMap();
        UserServiceDAO us = new UserServiceMPL();
        Page<User> userPages = us.queryByPage(currentPageNumber, 10, paramMap);
        request.setAttribute("userPages", userPages);//查询出来的一页用户数据
        request.setAttribute("userParamMap", paramMap);//用于页面回写
        request.getRequestDispatcher("jsp/user/admin.jsp").forward(request, response);
    }

}
