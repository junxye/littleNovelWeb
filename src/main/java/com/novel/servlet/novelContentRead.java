package com.novel.servlet;

import com.novel.DAO.ContentServiceDAO;
import com.novel.DAO.NovelServiceDAO;
import com.novel.DAO.ShelfServiceDAO;
import com.novel.entity.Novel;
import com.novel.entity.NovelContent;
import com.novel.entity.User;
import com.novel.service.ContentServiceMPL;
import com.novel.service.NovelServiceMPL;
import com.novel.service.ShelfServiceMPL;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@WebServlet("/NovelContentRead")
public class novelContentRead extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");
        Logger log = Logger.getLogger(String.valueOf(novelContentRead.class));
        PropertyConfigurator.configure("log4j.properties");
        String __number = request.getParameter("__number");
        String _readTimes = request.getParameter("_readTimes");
        String _collectionTimes = request.getParameter("_collectionTimes");
        String _chapterNumber = request.getParameter("_chapterNumber");
        if(_chapterNumber==null || _chapterNumber.equals("")) _chapterNumber = "1";
        if(_collectionTimes==null || _collectionTimes.equals("")) _collectionTimes = "1";
        int chapterNumber = Integer.parseInt(_chapterNumber);
        int _number = Integer.parseInt(__number);
        int collectionTimes = Integer.parseInt(_collectionTimes);
        //更新书籍总阅读数
        ContentServiceDAO cs = new ContentServiceMPL();
        if(_readTimes!=null && !_readTimes.equals("")) {
            int readTimes = Integer.parseInt(_readTimes);
            NovelServiceDAO ns = new NovelServiceMPL();
            Novel novel = new Novel();
            if(cs.queryContentIsExist(_number)) {
                readTimes--;
            }
            novel.setNum(_number);
            novel.setReadTime(readTimes);
            ns.updateNovel(novel);
            log.info("Number "+_number+" novel read times update.");
        }
        //查询书架是否有该书
        ShelfServiceDAO sd = new ShelfServiceMPL();
        User user = (User)request.getSession().getAttribute("user");
        if(sd.queryShelf(user.getAccount(), _number)) {
            request.setAttribute("IsInNovelShelf", "IsInNovelShelf");
        }
        //如果章节对象不为空，回写章节对象，总章数，目录，收藏数
        NovelContent novelContent = cs.queryContent(_number, chapterNumber);
        int contentTotal = cs.queryTotalContent(_number);
        List<NovelContent> novelContentList = cs.queryNovel(_number);
        request.setAttribute("novelContent", novelContent);
        if(novelContentList!=null && novelContentList.size() > 0) {
            request.setAttribute("contentTotal", contentTotal);
            request.setAttribute("novelContentList", novelContentList);
        }else {
            request.setAttribute("chapterNumber", chapterNumber);
        }
        request.setAttribute("_number", _number);
        request.setAttribute("collectionTimes", collectionTimes);
        request.getRequestDispatcher("jsp/novel/novelContent.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
