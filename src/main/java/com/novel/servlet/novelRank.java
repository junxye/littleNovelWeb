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
import java.util.List;
import java.util.Map;

@WebServlet("/NovelRank")
public class novelRank extends HttpServlet {
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
        NovelServiceDAO ns = new NovelServiceMPL();
        Map<String, String> conditionMap1 = new HashMap<>();
        Map<String, String> conditionMap2 = new HashMap<>();
        Map<String, String> conditionMap3 = new HashMap<>();
        conditionMap1.put("addTime", "addTime");
        conditionMap2.put("readTime", "readTime");
        conditionMap3.put("collectionTimes", "collectionTimes");
        Page<Novel> pages1 = ns.queryNovelByPage(1, 10, null, conditionMap1);
        Page<Novel> pages2 = ns.queryNovelByPage(1, 10, null, conditionMap2);
        Page<Novel> pages3 = ns.queryNovelByPage(1, 10, null, conditionMap3);
        List<Novel> timeRank= pages1.getPageLists();
        List<Novel> readTimesRank = pages2.getPageLists();
        List<Novel> collectionTimesRank = pages3.getPageLists();
        request.setAttribute("timeRank", timeRank);
        request.setAttribute("readTimesRank", readTimesRank);
        request.setAttribute("collectionTimesRank", collectionTimesRank);
        request.getRequestDispatcher("jsp/novel/novelRanking.jsp").forward(request, response);
    }

}
