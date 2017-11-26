package yuanshe;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

public class JsoupHtmlTest extends JUnitTestBase {

    @Test
    public void test() {
        Document doc = null;
        try {
            /* System.out.println(URLEncoder.encode("http://huihui.kim/2017/04/18/mysql自连接/", "UTF-8")); */
            doc = Jsoup.connect("http://chriscindy.top/post/Vue-plugin-development-introduction/").get();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("爬虫失败");
        }
        System.out.println(doc.title());
        Elements elements = doc.select("img");
        /*
         * System.out.println(JsoupHtmlUtil.getThumbnailImage(
         * "http://huihui.kim/2017/04/18/mysql%E8%87%AA%E8%BF%9E%E6%8E%A5/"));
         */
        System.out.println(elements.size());
        for (Element e : elements) {
            System.out.println(e.baseUri()); // 服务器地址
            System.out.println(e.attr("abs:src"));
            System.out.println(e.attr("src"));
            System.out.println("***********************");
        }
    }
}
