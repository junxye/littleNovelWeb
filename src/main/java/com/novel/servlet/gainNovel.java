package com.novel.servlet;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.testng.annotations.Test;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class gainNovel {

    @Test
    public void test() throws Exception {

        String indexUrl = "https://read.qidian.com/chapter/o-5I1BUC-ZnmkXioLmMPXw2/LVYT4ylMeYK2uJcMpdsVgA2/";
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("G://automation/extend/xieronline/NovelWeb/web/txt/小说章节.txt")));

        while (true) {
            CloseableHttpClient httpClient = HttpClients.createDefault();

            HttpGet httpGet = new HttpGet(indexUrl);
            httpGet.setHeader("User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36");

            CloseableHttpResponse response = httpClient.execute(httpGet);

            String html = EntityUtils.toString(response.getEntity(), "UTF-8");
            //System.out.println(html);

            Document document = Jsoup.parse(html);

            org.jsoup.select.Elements chapterName = document.select(".j_chapterName");
            System.out.println(chapterName.text());
            bw.write(chapterName.text());
            bw.newLine();
            bw.flush();

            Elements pEl = document.select("[class=read-content j_readContent] p");
            for (Element p : pEl) {
                bw.write(p.text());
                System.out.println(p.text());
                bw.newLine();
                bw.flush();
            }
/*
            Elements aEl = document.select("#j_chapterNext[href*=chapter]");

            if (aEl == null || aEl.size() == 0) {
                break;
            }
            Object nextUrl = aEl.attr("href");
            indexUrl = "http:" + nextUrl;
            System.out.println(indexUrl);
            */
            httpClient.close();
            break;
        }

    }
}
