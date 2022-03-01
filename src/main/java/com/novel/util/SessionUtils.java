package com.novel.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SessionUtils {

    private static SqlSessionFactory sessionFactory = null;

    private static ThreadLocal<SqlSession> sessionThread = new ThreadLocal<>();

    static {
        try {
            //InputStream inputStream = Resources.getResourceAsStream("message/c3p0-config.xml");
            InputStream inputStream = Resources.getResourceAsStream("message/conf.xml");
            sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSessionFactory() {
        return sessionFactory;
    }

    //新建session，将session放入本地线程中
    private static void newSession() {
        SqlSession session = sessionFactory.openSession();
        sessionThread.set(session);
    }

    //获取session
    public static SqlSession getSession() {
        SqlSession session = sessionThread.get();
        if(session == null) {
            //调用newSession(),新建session，将session放入本地线程中
            newSession();
            //从本地线程中获取session
            session = sessionThread.get();
        }
        return session;
    }

    //关闭session
    public static void closeSession() {
        SqlSession session = sessionThread.get();
        if(session != null) {
            //关闭session
            session.close();
            //将本地线程的SqlSession设为null
            sessionThread.set(null);
        }
    }
}
