package personal.ws.myservice.sniper.joking.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
@RequestMapping("/joking")
public class JokkingDownLoadSniper {

    public static String url = "https://www.github.cn";
    public static String path = "E:"+File.separator + "imgtest" + File.separator;
    List<String> names = new ArrayList<String>();
    List<String> srcs = new ArrayList<String>();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @RequestMapping("/download")
    public String download() {
        // 12306!早晚有一天自己扒!//https协议
        // Connection conn = Jsoup.connect("https://kyfw.12306.cn/otn/");
        //Document doc = con(url);
        String url3 = "http://www.laifu.org/wangwen/jiongrenqiushi.htm";//囧人糗事
        String url4 = "http://www.laifu.org/wangwen/lengxiaohua.htm";//冷笑话
        String url11 = "http://www.laifudao.com/wangwen/youmoxiaohua.htm";//幽默笑话
        String url12 = "http://www.laifudao.com/wangwen/wuliwangwen.htm";//无厘网文
        String url13 = "http://www.laifudao.com/wangwen/quweixinwen.htm";//趣味新闻
        String url1 = "http://www.laifu.org/tupian/dongwutupian.htm";//动物
        String url2 = "http://www.laifu.org/tupian/ertongtupian.htm";//儿童
        String url5 = "http://www.laifudao.com/tupian/gaoxiaomanhua.htm";//爆笑
        String url6 = "http://www.laifudao.com/tupian/baozoumanhua.htm";//暴走
        String url7 = "http://www.laifudao.com/tupian/qiwenguaishi.htm";//奇闻
        String url8 = "http://www.laifudao.com/tupian/gaoxiaojiaotong.htm";//搞笑交通
        String url9 = "http://www.laifudao.com/tupian/junshi_tiyu.htm";//军事体育
        String url10 = "http://www.laifudao.com/tupian/gaoxiaogif.htm";//搞笑gif
        JokkingDownLoadSniper a = new JokkingDownLoadSniper();
        for (int i = 300; i < 500; i++) {
            String urltest = "http://www.laifudao.com/index_";//搞笑gif
            urltest += i;
            urltest += ".htm";
            a.pagePic(urltest);
        }
        //		a.pagePic(url2);
        //		a.page(url3);
        //		a.pageText(url13);
        //		a.pageCartoon(url6);
        //		a.nextPage(url1);
        //		a.nextPage(url6);
        //if (null != doc) { findsrc(url, doc, path); }
        return null;
    }

    /**
     * 资源匹配
     *
     * @param url
     * @param doc
     */
    public void findsrc(String url, Document doc, String path) {
        // 匹配图片
        Elements elementsByTag = doc.getElementsByTag("img");
        for (Element element : elementsByTag) {
            String findsrc = element.attr("src");
            String directsrc = "";
            // 判断资源位置
            if ( findsrc.indexOf("http") < 0 ) {
                directsrc = url + findsrc;
            } else {
                directsrc = findsrc;
            }
            System.out.println("-----direcsrc:" + directsrc);
            String string = findsrc.toString();
            String[] split = {};
            int len = string.length() - 4;
            int indexOf = string.lastIndexOf(".");
            if ( indexOf == len ) {
                split = string.split("\\.");
            }
            String name = "";
            if ( split.length > 0 ) {
                String string2 = split[split.length - 1];
                System.out.println(string2);
                name = path + System.currentTimeMillis() + "." + string2;
            } else {
                name = path + System.currentTimeMillis();
            }
            File file = new File(name);
            if ( !file.exists() ) {
                try {
                    file.createNewFile();
                    System.out.println(name);
                    download(new URL(directsrc), name);
                    System.out.println(directsrc);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 访问页面资源
     */
    public Document con(String url) {
        Connection conn = Jsoup.connect(url);
        conn.userAgent("Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        // 获取页面文本
        // Response execute = conn.execute();
        // String body = execute.body();
        // Document doc = Jsoup.parse(body);
        // 直接获取document
        Document doc = null;
        try {
            doc = conn.get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }

    /**
     * 资源下载
     *
     * @param url
     */
    public void download(URL url, String name) {
        try {
            // URL url = new URL("http://www.baidu.com");
            URLConnection connection = url.openConnection();
            HttpURLConnection http = (HttpURLConnection) connection;
            connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            InputStream is = http.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            FileOutputStream fos = new FileOutputStream(name);
            int i;
            while ((i = is.read()) != -1) {
                fos.write(i);
            }
            // String str = fos.toString();
            // String[] split = str.split("> <");
            // for (int j = 0; j < split.length; j++) {
            // if (split[j].indexOf("/") != 0) {
            // System.out.println(split[j]);
            // }
            // }
            fos.close();
            baos.close();
            is.close();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private String nextPage(String url) {
        try {
            Document doc = org.jsoup.Jsoup.connect(url).get();
            Element body = doc.body();
            Element next = body.getElementsByClass("next").first();
            Element a = next.getElementsByTag("a").first();
            String page = "http://www.laifudao.com" + a.attr("href");
            //			if (null != doc) {
            //				findsrc(url, doc, path);
            //			}
            // System.out.println("this is the href: " + sdf.format(new Date())
            // + " " + page);
            System.out.println("this is the href:  " + "  " + page);
            nextPage(page);
            return page;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void pagePic(String url) {
        try {
            Document doc = org.jsoup.Jsoup.connect(url).get();
            Element body = doc.body();
            Elements contents = body.getElementsByClass("pic-content");
            System.out.println(contents.toString());
            for (Element content : contents) {
                Element link = content.getElementsByTag("img").first();
                String name = link.attr("alt");
                String src = link.attr("src");
                if ( name == null || src == null )
                    continue;
                names.add(name);
                srcs.add(src);
            }
            System.out.println(" now names' size is :" + names.size() + "   srcs's size is :" + srcs.size());
            for (int i = 0; i < srcs.size(); i++) {
                System.out.println("name:" + names.get(i) + "\t\t\t\t\t\t\t\tsrc:" + srcs.get(i));
                download(new URL(srcs.get(i)), "E:"+File.separator + "imgtest" + File.separator + names.get(i) + ".gif");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void pageText(String url) {
        try {
            Document doc = org.jsoup.Jsoup.connect(url).get();
            Element body = doc.body();
            Elements contents = body.getElementsByClass("content").first().getElementsByTag("article");
            for (Element content : contents) {
                Element header = content.getElementsByClass("post-header").first().getElementsByTag("h1").first();
                Element article = content.getElementsByClass("article-content").first();
                System.out.println(header.text() + " " + article.text() + "\n-------------------------------------------------------------------");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
