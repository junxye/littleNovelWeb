package com.novel.servlet;

import com.novel.DAO.ShelfServiceDAO;
import com.novel.entity.User;
import com.novel.service.ShelfServiceMPL;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/DeleteNovelFromShelf")
public class deleteNovelFromShelf extends HttpServlet {
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
        Logger log = Logger.getLogger(String.valueOf(deleteNovelFromShelf.class));
        PropertyConfigurator.configure("message/log4j.properties");
        String __number = request.getParameter("__number");//点击了删除按钮
        String[] checkItem = request.getParameterValues("checkItem");//删除所选中的
        User user = (User)request.getSession().getAttribute("user");
        String _account = user.getAccount();
        ShelfServiceDAO sd = new ShelfServiceMPL();
        if(__number!=null && !__number.equals("")) {
            int _number = Integer.parseInt(__number);
            if(!sd.deleteFromShelf(_account, _number)) {
                System.out.println("删除失败");
            }
        }
        if(checkItem!=null) {
            if(checkItem.length>0) {
                for (String string : checkItem) {
                    int _number = Integer.parseInt(string);
                    if(sd.deleteFromShelf(_account, _number)) {
                        System.out.println("删除失败");
                    }
                }
            }
        }
        response.sendRedirect("queryNovelShelfByPage");
    }

}
