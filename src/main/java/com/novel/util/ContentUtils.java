package com.novel.util;

import com.novel.entity.NovelContent;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContentUtils {

    static List<NovelContent> cLists = new ArrayList<NovelContent>();

    /**
     * 分割章节的方法
     * @param srcFilePath 该txt小说放在磁盘中的位置
     * @param novelName 该txt小说的书名
     * @param encoding 编码类型
     * @return	该txt小说的所有章节
     */
    public static List<NovelContent> cut(String srcFilePath, String novelName,
                                        String encoding) {
        BufferedReader br = null;
        InputStreamReader read;
        try {
            read = new InputStreamReader(
                    new FileInputStream(new File(srcFilePath)), encoding);
            br = new BufferedReader(read);
            String tempString = null;
            StringBuffer sb = new StringBuffer();
            Pattern p = Pattern.compile("第.{1,5}章\\s.*");//分割章节号
            Matcher m = null;
            int index = 0;//用于记录下次匹配到的下标
            String allStr = "";//用于记录章节内容
            int i = 0;//计数
            while ((tempString = br.readLine()) != null) {//读取一行
                tempString += "\n";
                sb.append(tempString);
            }
            m = p.matcher(sb.toString());//对文章的所有内容进行匹配
            while (m.find()) {
                i++;
                NovelContent bc = new NovelContent();
                String group = m.group();
                String novelChapter = group.trim();//章节号 如：第一章
                int start = m.start();
                if (m.find()) {//再匹配一次
                    index = m.start();
                    allStr = sb.toString()
                            .substring(start + novelChapter.length(), index);
                    m.region(index, sb.length());//重置匹配器，从下标为index开始匹配，到sb.length结束
                } else {
                    allStr = sb.toString().substring(
                            start + novelChapter.length(),
                            sb.toString().length());
                }
                //封装
                bc.setNovelName(novelName);
                bc.setChapterName(novelChapter);
                bc.setChapterContent(allStr);
                bc.setChapterNumber(i);
                cLists.add(bc);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ie) {
            ie.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return cLists;
    }

    /**
     * 该方法用于将切割出来的的小说章节内容进行格式处理
     * @param cuts 切割出来的小说章节集合
     * @param novelNumber 小说的编号
     * @return 进行格式处理后的小说章节集合
     */
    public static List<NovelContent> novelContentFormat(List<NovelContent> cuts, int novelNumber) {
        //遍历该小说所有章节
        for (NovelContent novelContent : cuts) {
            String _content = "";//用于存处理过后的章节内容
            Pattern p = Pattern.compile(".*");//匹配除"\r\n"之外的任何字符
            Matcher matcher = p.matcher(novelContent.getChapterContent());
            while(matcher.find()) {
                String group = matcher.group();
                String trim = group.trim();
                if(trim != null && !trim.equals("")) {
                    trim="<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+trim+"</p>";//对章节内容进行简单处理
                    _content += trim;
                }
            }
            //封装
            novelContent.setChapterContent(_content);
            novelContent.setNovelNumber(novelNumber);
        }
        return cuts;
    }

}
