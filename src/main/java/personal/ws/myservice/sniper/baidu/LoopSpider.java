package personal.ws.myservice.sniper.baidu;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import personal.ws.myservice.sniper.baidu.config.Configuration;
//import personal.ws.myservice.sniper.baidu.service.LoopSpiderMongoService;
import personal.ws.myservice.sniper.baidu.service.LoopSpiderService;
import personal.ws.util.http.CharTransUtil;

import java.io.IOException;
import java.util.*;

@Scope("prototype")
@Controller
@RequestMapping("/baidu")
public class LoopSpider {

	public static LoopSpiderService lss		= new LoopSpiderService();
//	public static LoopSpiderMongoService lsms	= new LoopSpiderMongoService();
	//贴吧帖子列表
	public static List<Map<String,String>>	srcList	= new ArrayList<Map<String,String>>();

	@RequestMapping("/beginLoop")
    @ResponseBody
	public String beginLoop() {
		long currentTimeMillisBegin = System.currentTimeMillis();
		System.out.println("开始:" + new Date());
//		lsms.createDB();
		try {
			mainPageMethod(Configuration.CONNECT_URL + Configuration.CONNECT_PACKAGE);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//		System.out.println(srcList.size());
		int size = srcList.size();
		if ("test".equals(Configuration.MODE)) {
			size = 1;
		}
		for (int i = 0; i < size; i++) {
			Map<String,String> map = srcList.get(i);
			String title = map.get("title");
			String src = map.get("src");
			if ("open".equals(Configuration.PRINT_MODE))
				toTxtSave("帖子标题:" + title + "\n" + "           帖子链接:" + src);
            try {
                childLoop(title, src);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //System.out.println(map.get("title") + "                 " + map.get("src"));
			//toFile(divs.html());
			for (int j = 0; j < 3; j++) {
				if ("open".equals(Configuration.PRINT_MODE))
					toTxtSave("\r\n");
			}
		}
		//lsms.closeDB();
		long currentTimeMillisEnd = System.currentTimeMillis();
		System.out.println("结束:" + new Date());
        long l = (currentTimeMillisEnd - currentTimeMillisBegin) / 1000;
        System.out.println("耗时:" + l + "秒");
		return String.valueOf(l);
	}

	/**
	 * 主页面循环
	 *
	 * @param url
	 * @throws IOException
	 */
	public static void mainPageMethod(String url) throws IOException {
		Configuration.titlePageNum--;
		Element body = getBody(url);
		Elements pager = body.getElementsByClass("next pagination-item ");
		System.out.println(pager);
		if (Configuration.titlePageNum > 0) {
			mainPageMethod(pager.get(0).attr("href"));
		}
		//System.out.println(pager.toString());
		//根据元素标签查找元素
		//Elements aTag = content.get(0).getElementsByTag("a");
		//根据Class属性查找元素
		Elements content = body.getElementsByClass("content");
		if (content.size() > 0) {
			Elements classTag = body.getElementsByClass("j_th_tit ");
			int size = classTag.size();
			if ("test".equals(Configuration.MODE)) {
				size = 1;
			}
			for (int i = 0; i < size; i++) {
				Element srcTag = classTag.get(i);
				String title = srcTag.attr("title");
				String src = Configuration.CONNECT_URL + srcTag.attr("href");
				//判断是否有关键字
				if (Configuration.TITLE_SEARCH_KEY != "") {
					//判断是否包含查找关键字
					if (title.contains(Configuration.TITLE_SEARCH_KEY)) {
						getTitleData(title, src);
						//childLoop(src);
					}
				} else {//无关键字方法
					getTitleData(title, src);
					//childLoop(src);
				}
			}
		}
	}

	/**
	 * 帖子页面
	 *
	 * @param url
	 * @throws IOException
	 */
	public static void childLoop(String title, String url) throws IOException {
		//toAppendFile("帖子链接:" + url);
		//System.out.println(url);
		int judgeFirst = url.indexOf("pn=");
		int this_page_num = 0;
		if (judgeFirst >= 0) {
			this_page_num = Integer.valueOf(url.substring(judgeFirst + 3, url.length()));
		}
		Element body = getBody(url);
		//toTxtSave(body.toString());
		//TODO 实际值会发生顺序变化,需要进行判断
		Elements articlediv = findArticleDiv(body);
		int size = articlediv.size();
		if ("test".equals(Configuration.MODE)) {
			size = 1;
		}
		for (int i = 0; i < size; i++) {
			Element thisDiv = articlediv.get(i);
			//每层层主
			Elements users = thisDiv.getElementsByClass("d_name");
			if (checkEmpty(users)) {
				int floor = i + 1 + 30 * this_page_num;
				String author = users.get(0).text().toString();
				if ("open".equals(Configuration.PRINT_MODE)) {
					toTxtSave(floor + "楼");
					toTxtSave("发送人:" + author);
				}
				//每层层主消息
				Elements cc = thisDiv.getElementsByTag("cc");
				int ccsize = cc.size();
				if ("test".equals(Configuration.MODE)) {
					ccsize = 1;
				}
				for (int j = 0; j < ccsize; j++) {
					//每层文件
					String text = cc.text();
					//System.out.println(cc.toString());
					//是否开启图片下载
					if ("open".equals(Configuration.RESOURSE_IMG_DOWNLOAD)) {
						toImgSave(cc, j);
					}
					if ("open".equals(Configuration.PRINT_MODE)) {
						toTxtSave(text);
					}
					/*if ("open".equals(Configuration.DBSAVE)) {
						toMongoSave(title, url, floor, author, text);
					}*/
					//System.out.println("内容:" + cc.text());
					//TODO 评论消息
				}
			}
		}
		//翻页
		Elements pageEle = body.getElementsByClass("l_posts_num");
		if (pageEle.size() > 0) {
			//如果有分页标签,再进行下一页查询
			Elements aTag = pageEle.get(0).getElementsByTag("a");
			//大于1页的页面才进行翻页
			if (aTag.size() > 0) {
				//取尾页
				Element end_page = aTag.get(aTag.size() - 1);
				String end_href = end_page.attr("href");
				int all_page_num = Integer.valueOf(end_href.substring(end_href.indexOf("pn=") + 3, end_href.length()));
				//判断是否是帖子第一页
				if (judgeFirst >= 0) {
					if (this_page_num < all_page_num) {
						childLoop(title, url.substring(0, judgeFirst + 3) + (this_page_num + 1));
					}
				} else {
					//是第一页直接访问第二页
					childLoop(title, url + "/pn=1");
				}
			}
		}
	}

	/**
	 * 获取正文div
	 *
	 * @param body
	 * @return
	 */
	public static Elements findArticleDiv(Element body) {
		Elements allElements = body.getAllElements();
		Elements articlediv = new Elements();
		for (Element element : allElements) {
			String className = element.className();
			if (className.contains("l_post") && className.contains("j_l_post") && className.contains("l_post_bright")) {
				articlediv.add(element);
			}
		}
		return articlediv;
	}

	/**
	 * 存储到MongoDB数据库
	 *
	 * @param title
	 * @param url
	 * @param floor
	 * @param author
	 * @param text
	 */
	public static void toMongoSave(String title, String url, int floor, String author, String text) {
//		lsms.toMongoSave(CharTransUtil.charEncode(Configuration.CONNECT_BARNAME), url, title, String.valueOf(floor), author, "text", text);
	}

	/**
	 * 存储到本文
	 *
	 * @param text
	 */
	public static void toTxtSave(String text) {
		lss.toAppendFile("内容:" + text);
	}

	/**
	 * 图片下载
	 *
	 * @param cc
	 * @param j
	 */
	public static void toImgSave(Elements cc, int j) {
		Elements imgEles = cc.get(j).getElementsByTag("img");
		if (imgEles.size() > 0) {
			for (Element imgEle : imgEles) {
				lss.img_download(imgEle);
			}
		}
	}

	/**
	 * 获取页面body
	 *
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static Element getBody(String url) throws IOException {
		Connection connect = Jsoup.connect(url);
		connect.userAgent("Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
		Document document = connect.get();
		//获取页面的head
		//System.out.println(document.head());
		//获取页面的body
		Element body = document.body();
		return body;
	}

	/**
	 * 记录标题和链接地址
	 *
	 * @param title
	 * @param src
	 */
	public static void getTitleData(String title, String src) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("title", title);
		map.put("src", src);
		srcList.add(map);
	}

	/**
	 * 判断元素是否为空
	 *
	 * @param obj
	 * @return boolean
	 */
	public static boolean checkEmpty(Object obj) {
		if (obj instanceof Elements) {
			boolean b = ((Elements) obj).size() > 0;
			return b ? true : false;
		}
		return false;
	}
}
