package com.novel.entity;

public class Manager {

    private int id;
    private String name;
    private String passWord;
    private String sex;
    private int age;
    private String time;

    public Manager(){}

    public Manager(String name,String passWord){
        this.name = name;
        this.passWord = passWord;
    }

    public Manager(String name,String passWord,String sex,int age,String time){
        this.name = name;
        this.passWord = passWord;
        this.sex = sex;
        this.age = age;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", account=" + name + ", passWord=" + passWord
                + ", sex=" + sex + ", age=" + age + ", time=" + time + "]";
    }

}
