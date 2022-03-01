package com.novel.entity;

public class NovelContent {
    // 章节
    private int chapterId;  // 章节编号
    private String novelName;   //小说名
    private String chapterName; //章节标题
    private String chapterContent;  //章节内容
    private int novelNumber;    //小说编号
    private int chapterNumber;  //章节编号

    public NovelContent(){

    }

    public NovelContent(String novelName,String chapterName,String chapterContent,int novelNumber,int chapterNumber){
        this.novelName = novelName;
        this.chapterName = chapterName;
        this.chapterContent = chapterContent;
        this.novelNumber = novelNumber;
        this.chapterNumber = chapterNumber;
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public String getNovelName() {
        return novelName;
    }

    public void setNovelName(String novelName) {
        this.novelName = novelName;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getChapterContent() {
        return chapterContent;
    }

    public void setChapterContent(String chapterContent) {
        this.chapterContent = chapterContent;
    }

    public int getNovelNumber() {
        return novelNumber;
    }

    public void setNovelNumber(int novelNumber) {
        this.novelNumber = novelNumber;
    }

    public int getChapterNumber() {
        return chapterNumber;
    }

    public void setChapterNumber(int chapterNumber) {
        this.chapterNumber = chapterNumber;
    }

    @Override
    public String toString(){
        return "BookContent [chapterId=" + chapterId + ", novelName=" + novelName
                + ", chapterNumber=" + chapterNumber + ", chapterContent=" + chapterContent
                + ", novelNumber=" + novelNumber + ", chapterNumber=" + chapterNumber + "]";
    }
}
