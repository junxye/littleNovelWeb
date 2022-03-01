package com.novel.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

public class TimeUtils {

    public static String parseTime(String time) {
        String formatTime = null;
        Logger log = Logger.getLogger(String.valueOf(TimeUtils.class));
        //PropertyConfigurator.configure("log4j.properties");
        try {
            //System.out.println(time);
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time);
            formatTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        } catch (ParseException e) {
            System.out.println("时间转化失败!");
            log.info("Time change wrong!");
            formatTime = "";
        }
        return formatTime;
    }
}
