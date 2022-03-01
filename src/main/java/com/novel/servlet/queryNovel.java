package com.novel.servlet;

import com.novel.DAO.NovelServiceDAO;
import com.novel.DAO.ShelfServiceDAO;
import com.novel.entity.Novel;
import com.novel.entity.User;
import com.novel.service.NovelServiceMPL;
import com.novel.service.ShelfServiceMPL;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/QueryNovel")
public class queryNovel extends HttpServlet {
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
        String __number = request.getParameter("__number");
        int _number = Integer.parseInt(__number);
        NovelServiceDAO ns = new NovelServiceMPL();
        Novel novel = ns.queryNovel(_number);
        request.setAttribute("novel", novel);
/*
        //接收评论所需参数
        String _currentpageNum = request.getParameter("currentpageNum");
        if(_currentpageNum==null || _currentpageNum.equals("")) {
            _currentpageNum = "1";
        }
        int currentpageNum = Integer.parseInt(_currentpageNum);
        CommentsService cs = new CommentsServiceImpl();
        Page<Comments> commentsparameterMap = cs.queryComments(bnum, currentpageNum, 10);
        request.setAttribute("commentsparameterMap", commentsparameterMap);
*/
        //判断书架中是否有该书
        ShelfServiceDAO sd = new ShelfServiceMPL();
        User user = (User)request.getSession().getAttribute("user");
        if(sd.queryShelf(user.getAccount(), _number)) {
            request.setAttribute("IsInNovelShelf", "IsInNovelShelf");
        }
        request.getRequestDispatcher("jsp/novel/novel.jsp").forward(request, response);
    }

}
