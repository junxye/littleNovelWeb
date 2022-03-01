package com.novel.entity;

import java.util.List;

public class Page<T> {

    private int totalData;
    private int pageSize;
    private int totalPage;
    private int currentPage;
    private int currentPageNumber;
    private List<T> pageLists;

    public Page(){}

    public Page(int totalData,int pageSize,int totalPage,int currentPage,int currentPageNumber,List<T> pageLists){
        this.totalData = totalData;
        this.pageSize = pageSize;
        this.totalPage = totalPage;
        this.currentPage = currentPage;
        this.currentPageNumber = currentPageNumber;
        this.pageLists = pageLists;
    }

    public int getTotalData() {
        return totalData;
    }

    public void setTotalData(int totalData) {
        this.totalData = totalData;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getCurrentPageNumber() {
        return currentPageNumber;
    }

    public void setCurrentPageNumber(int currentPageNumber) {
        this.currentPageNumber = currentPageNumber;
    }

    public List<T> getPageLists() {
        return pageLists;
    }

    public void setPageLists(List<T> pageLists) {
        this.pageLists = pageLists;
    }

    @Override
    public String toString() {
        return "Page [totalData=" + totalData + ", pageSize=" + pageSize
                + ", totalPage=" + totalPage + ", currentPage=" + currentPage
                + ", currentPageNumber=" + currentPageNumber + ", pageLists="
                + pageLists + "]";
    }

}
