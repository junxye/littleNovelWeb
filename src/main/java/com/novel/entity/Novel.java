package com.novel.entity;

public class Novel {
    //小说
    private int id;
    private String novelName;
    private String author;
    private String introduction;
    private String image;
    private String category;    //小说类别
    private int num; //小说编号
    private String addTime;    //上架时间
    private int readTime;   //阅读次数
    private int totalWords; //总字数
    private int collectionTimes;    //收藏数

    public Novel(){

    }

    public Novel(String novelName,String author,String introduction,String image,String category,int num,int totalWords){
        this.novelName = novelName;
        this.author = author;
        this.introduction = introduction;
        this.image = image;
        this.category = category;
        this.num = num;
        this.totalWords = totalWords;
    }

    public Novel(String novelName,String author,String introduction,String image,String category,
                 int number,String addTime,int readTime,int totalWords,int collectionTimes){
        this.novelName = novelName;
        this.author = author;
        this.introduction = introduction;
        this.image = image;
        this.category = category;
        this.num = number;
        this.addTime = addTime;
        this.readTime = readTime;
        this.totalWords = totalWords;
        this.collectionTimes = collectionTimes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNovelName() {
        return novelName;
    }

    public void setNovelName(String novelName) {
        this.novelName = novelName;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public int getReadTime() {
        return readTime;
    }

    public void setReadTime(int readTime) {
        this.readTime = readTime;
    }

    public int getTotalWords() {
        return totalWords;
    }

    public void setTotalWords(int totalWords) {
        this.totalWords = totalWords;
    }

    public int getCollectionTimes() {
        return collectionTimes;
    }

    public void setCollectionTimes(int collectionTimes) {
        this.collectionTimes = collectionTimes;
    }

    @Override
    public String toString(){
        return "Book [id=" + id + ", novelName=" + novelName + ", author=" + author
                + ", introduction=" + introduction + ", image=" + image
                + ", category=" + category + ", num=" + num + ", addTime="
                + addTime + ", readTime=" + readTime + ", totalWords="
                + totalWords + ", collectionTimes=" + collectionTimes + "]";
    }
}
