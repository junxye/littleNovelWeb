package com.novel.util;

import com.novel.entity.Page;

public class PageUtils {

    public static <T> Page<T> getPages(int currentPageNumber, int pageSize, int totalData) {
        //创建Page对象
        Page<T> pages = new Page<T>();
        if(totalData == 0) {//如果结果为0 直接返回
            pages.setTotalData(totalData);
            pages.setTotalPage(0);
            return pages;
        }
        int totalPage = totalData % pageSize == 0
                ? totalData / pageSize : totalData / pageSize + 1;
        // 这里是用于控制翻页的第一页
        if (currentPageNumber <= 0) {
            currentPageNumber = 1;
        }
        // 这里是于控制翻页的最后一页
        if (currentPageNumber >= totalPage) {
            currentPageNumber = totalPage;
        }
        int currentPage = (currentPageNumber - 1) * pageSize;

        // 将数据进行封装
        pages.setCurrentPage(currentPage);// 当前页面
        pages.setPageSize(pageSize);// 页面大小
        pages.setCurrentPageNumber(currentPageNumber);// 当前页码
        pages.setTotalData(totalData);// 总数据数
        pages.setTotalPage(totalPage);// 总页面数

        return pages;
    }

}
