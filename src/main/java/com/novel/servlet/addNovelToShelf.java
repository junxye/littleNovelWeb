package com.novel.servlet;

import com.novel.DAO.NovelServiceDAO;
import com.novel.DAO.ShelfServiceDAO;
import com.novel.entity.Novel;
import com.novel.service.NovelServiceMPL;
import com.novel.service.ShelfServiceMPL;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/AddNovelToShelf")
public class addNovelToShelf extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");
        Logger log = Logger.getLogger(String.valueOf(addNovelToShelf.class));
        //PropertyConfigurator.configure("log4j.properties");
        String _account = request.getParameter("_account");
        String _number = request.getParameter("_number");
        int number = Integer.parseInt(_number);
        String _collectionTimes = request.getParameter("_collectionTimes");
        String _chapterNumber = request.getParameter("_chapterNumber");
        //更新收藏数
        if(_collectionTimes!=null && !_collectionTimes.equals("")) {
            NovelServiceDAO novelServiceDAO = new NovelServiceMPL();
            Novel novel = new Novel();
            int collectionTimes = Integer.parseInt(_collectionTimes);
            novel.setCollectionTimes(collectionTimes);
            novel.setNum(number);
            novelServiceDAO.updateNovel(novel);
            log.info("Number "+number+" novel collection times update.");
        }
        //添加进书架
        ShelfServiceDAO shelfServiceDAO = new ShelfServiceMPL();
        if(!shelfServiceDAO.addToShelf(_account, number)) {
            System.out.println("添加失败");
        }
        if(_chapterNumber!=null && !_chapterNumber.equals("")) {
            int chapter_num = Integer.parseInt(_chapterNumber);
            //跳转到BookContentServlet
            response.sendRedirect("BookContentServlet?number="+number+"&chapter_num="+chapter_num);
        }else {
            //跳转到QueryBookServlet
            response.sendRedirect("QueryBookServlet?number="+number);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
