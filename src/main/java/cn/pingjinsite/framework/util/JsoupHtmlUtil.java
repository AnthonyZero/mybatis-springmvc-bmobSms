package cn.pingjinsite.framework.util;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import cn.pingjinsite.framework.logger.MyLog4j;

/**
 * @ClassName: JsoupHtmlUtil
 * @Description: html 网络爬虫工具类
 * @author pingjin(736252868@qq.com)
 * @date 2017年4月22日 下午4:55:54
 */
public class JsoupHtmlUtil {

    /**
     * @Title: getThumbnailImage
     * @Description: 网络爬虫博客地址 得到第一张图片地址 没有图片则返回空字符串
     * @param url 编码之后的url地址
     * @return String 可直接访问的图片地址（节选博客中的第一张图片）
     */
    public static String getThumbnailImage(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            MyLog4j.info(JsoupHtmlUtil.class, "网络爬虫失败,网址为:" + url);
            return "";
        }
        Elements elements = doc.select("img");
        if (elements.size() > 0) {
            return elements.get(0).attr("abs:src"); // 返回包含根路径的第一张图片URL地址
        }
        return "";
    }
}
