package com.novel.entity;

public class User {

    private int id;
    private String account;
    private String passWord;
    private String sex;
    private int age;
    private String introduce;   //介绍
    private String role;
    private String addTime;    //注册时间

    public User(){}

    public User(String account,String passWord){
        this.account = account;
        this.passWord = passWord;
    }

    public User(String account,String passWord,String sex,int age,String introduce,String addTime){
        this.account = account;
        this.passWord = passWord;
        this.sex = sex;
        this.age = age;
        this.introduce = introduce;
        this.addTime = addTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", account=" + account + ", passWord=" + passWord
                + ", sex=" + sex + ", age=" + age +  ", introduce=" + introduce
                + ", addTime=" + addTime + "]";
    }
}
