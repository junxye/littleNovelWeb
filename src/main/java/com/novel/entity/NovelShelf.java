package com.novel.entity;

public class NovelShelf {
    // 书架
    private int shelfId;    //小说在书架中的编号
    private int novelNumber;
    private String account; //收入书架的账户
    private String time;    //收藏时间
    private String novelName;

    private Novel novel;

    public NovelShelf(){

    }

    public NovelShelf(int novelNumber,String account){
        this.novelNumber = novelNumber;
        this.account = account;
    }

    public int getShelfId() {
        return shelfId;
    }

    public void setShelfId(int shelfId) {
        this.shelfId = shelfId;
    }

    public int getNovelNumber() {
        return novelNumber;
    }

    public void setNovelNumber(int novelNumber) {
        this.novelNumber = novelNumber;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Novel getNovel() {
        return novel;
    }

    public void setNovel(Novel novel) {
        this.novel = novel;
    }

    public String getNovelName() {
        return novelName;
    }

    public void setNovelName(String novelName) {
        this.novelName = novelName;
    }

    @Override
    public String toString(){
        return "BookShelf [shelfId=" + shelfId + ", novelNumber=" + novelNumber
                + ", novelName=" + novelName + ", account=" + account + ", time=" + time + "]";
    }

}
