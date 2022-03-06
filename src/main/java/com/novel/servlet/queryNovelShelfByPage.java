package com.novel.servlet;


import com.novel.DAO.NovelDAO;
import com.novel.DAO.ShelfServiceDAO;
import com.novel.entity.NovelShelf;
import com.novel.entity.Page;
import com.novel.entity.User;
import com.novel.service.NovelMPL;
import com.novel.service.ShelfServiceMPL;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

@WebServlet("/QueryNovelShelfByPage")
public class queryNovelShelfByPage extends HttpServlet {
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
        Map<String, String[]> parameterMap = request.getParameterMap();//查询参数
        User user = (User)request.getSession().getAttribute("user");
        String _account = user.getAccount();
        if (_currentPageNumber == null || _currentPageNumber.equals("")) _currentPageNumber = "1";
        int currentPageNumber = Integer.parseInt(_currentPageNumber);//当前页码
        ShelfServiceDAO sd = new ShelfServiceMPL();
        Page<NovelShelf> novelShelfPage = sd.queryShelfByAccount(_account, currentPageNumber, 10, parameterMap);
        NovelDAO novelDAO = new NovelMPL();
        for (int i = 0;i < novelShelfPage.getTotalData();i++){
            novelShelfPage.getPageLists().get(i).setNovel(novelDAO.queryNovel(novelShelfPage.getPageLists().get(i).getNovelNumber()));
        }
        //System.out.println("NovelShelf : "+novelShelfPage.getPageLists().get(0).getNovel());
        request.setAttribute("novelShelfPage", novelShelfPage);
        request.setAttribute("novelShelfParamMap", parameterMap);//用于页面回写
        request.getRequestDispatcher("jsp/shelf/novelShelf.jsp").forward(request, response);
    }

}
