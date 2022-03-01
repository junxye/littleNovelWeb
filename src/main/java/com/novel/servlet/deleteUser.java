package com.novel.servlet;

import com.novel.DAO.UserServiceDAO;
import com.novel.service.UserServiceMPL;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/DeleteUser")
public class deleteUser extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String _account = request.getParameter("_account");//点击了删除按钮
        String[] _accounts = request.getParameterValues("selectBox");//删除所选中的
        UserServiceDAO us = new UserServiceMPL();
        if(_account!=null && _account.equals("")) {
            if(!us.deleteUser(_account)) {
                System.out.println("该用户删除失败...");
            }
        }
        if(_accounts!=null) {
            if(_accounts.length>0) {
                for (String string : _accounts) {
                    if(!us.deleteUser(string)) {
                        System.out.println(string+"用户删除失败...");
                    }
                }
            }
        }
        response.sendRedirect("queryUserByPage");
    }

}
