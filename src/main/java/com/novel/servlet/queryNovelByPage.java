package com.novel.servlet;

import com.novel.DAO.NovelServiceDAO;
import com.novel.entity.Novel;
import com.novel.entity.Page;
import com.novel.service.NovelServiceMPL;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/QueryNovelByPage")
public class queryNovelByPage extends HttpServlet {
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

        String _param = request.getParameter("_param");
        String addTime = request.getParameter("addTime");
        String totalWords = request.getParameter("totalWords");
        String _category = request.getParameter("_category");
        String _currentPageNumber = request.getParameter("currentPageNumber");

        Map<String, String> parameterMap = new HashMap<>(); //放参数
        Map<String, String> conditionMap = new HashMap<>(); //放排序条件
        parameterMap.put("param", _param);
        parameterMap.put("category", _category);
        conditionMap.put("addTime", addTime);
        conditionMap.put("totalWords", totalWords);

        if(_currentPageNumber==null || _currentPageNumber.equals("")) _currentPageNumber = "1";
        int currentPageNumber = Integer.parseInt(_currentPageNumber);
        NovelServiceDAO ns = new NovelServiceMPL();
        Page<Novel> novelPages = ns.queryNovelByPage(currentPageNumber, 10, parameterMap, conditionMap);
        //System.out.println("!!!novelPages "+novelPages.getPageLists());
        request.setAttribute("_param", _param);//用于页面回写
        request.setAttribute("novelParamMap", parameterMap);//用于页面回写
        request.setAttribute("novelPages", novelPages);
        request.setAttribute("addTime", addTime);//用于标识是否使用了时间排序
        request.setAttribute("totalWords", totalWords);//用于标识是否使用了字数排序
        request.getRequestDispatcher("jsp/novel/novels.jsp").forward(request, response);
    }

}
